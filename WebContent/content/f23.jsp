<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/easyui.css">
<link rel="stylesheet" href="../css/icon.css">
<link rel="stylesheet" href="../css/demo.css">
<script src="../js/jquery-2.0.3.min.js"></script>
<script src="../js/jquery.easyui.min.js"></script>
<script src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=GXkbjcszaqwT3Wqq7piaAEeu"></script>


<script type="text/javascript">
$(function(){
	$("#worker").datagrid(
			{
				url :"${pageContext.request.contextPath}/outPrint!findXunxian.action",
				singleSelect : true,
				pagination : true,/*是否有分页工具栏  */
				pageSize : 50,
				pageList : [ 10, 20, 30, 40, 50 ],
				method : 'POST',
				fit : true,/* 宽度自适应 */
				fitColumns : true,/* 有无横向滚动条 ,默认有false */
				nowrap : false,/* 内容自动折行 默认true */
				border : false,
				rownumbers : true,
				toolbar:"#condition",
				idField : 'account',/* 每行的标识，（记住每页的选择，翻页无影响，跨页关注） */
				 onClickRow: function (index, row) {
					 showMap(row.account);
				 },
				columns : [ [
				{
       				field: "account",
       				title: "人员编码",
       				width:100
  				 },
   				{
       				field: "name",
       				title: "姓名",
       				width:150
   				},
   				{
      				 field: "dname",
      				 title: "部门" ,
      				 width:150
   				}   ] ]
			});	
});	

function showMap(account){
	
	var beginTime = $('#beginTime').datetimebox('getValue');
	if(beginTime==null || beginTime=="")
	{		
		 $.messager.alert("操作提示", "起始时间不能为空！");  
		//alert("起始时间不能为空!");
		return;
	}
	
	var endTime = $('#endTime').datetimebox('getValue');

	if(endTime==null || endTime=="")
	{
		//alert("截止时间不能为空!");
		$.messager.alert("操作提示", "截止时间不能为空！");  
		return;
	}
	$.ajax({
		  url: "locationFind.action",
		  data: {
		      account: account,
		      beginTime:beginTime,
		      endTime:endTime
		  },		 
		  success: function( dataMap ) {
			  if(dataMap.msg!="1"){
				  alert(dataMap.msg);
				  return;
			  }
			  
			  if(dataMap.ls.length==0){
				  //alert("指定的日期内，此员工没有数据");
				  $.messager.alert("数据提示", "指定的日期内，没有此员工的位置信息数据！");  
				  return ;
			  }
			  var points= new Array();
			  for(var i=0;i<dataMap.ls.length;i++){
				  var location =dataMap.ls[i];
				  //alert(location.longitude+"="+location.latitude);
			  	  points[i]=new BMap.Point(location.longitude,location.latitude);
			   }
			   var map = new BMap.Map("container");
			   map.centerAndZoom(points[0], 17);
			   map.enableScrollWheelZoom();
			   var driving = new BMap.DrivingRoute(map);

			  for(var i=0;i<points.length-1;i++){
			  	  var start = points[i];
			  	  var end = points[i+1];	
			  	  driving.search(start, end);
			  	  driving.setSearchCompleteCallback(function() {
			  		  points1 = driving.getResults().getPlan(0).getRoute(0).getPath();	
			  		  map.addOverlay(new BMap.Polyline(points1, {strokeColor: "black", strokeWeight: 5, strokeOpacity: 1}));
			      });
			  	var centerPoint = new BMap.Point((points[0].lng + points[points.length - 1].lng)/2, (points[0].lat + points[points.length - 1].lat)/2);
				  map.panTo(centerPoint);			  	
			  }	  
		}
 });
}
</script>

</head>
<body style="width: 100%; margin: 0px; padding: 0px;">
	<div style="width: 400px; float: left; height: 550px;">
		<div class="easyui-panel" id="condition"
			style="width: 100%; padding: 10px 20px;">
			<div>
				<input class="easyui-datetimebox" id="beginTime" label="起始时间:"
					labelPosition="left" style="width: 300px;">
			</div>
			<div>
				<input class="easyui-datetimebox" id="endTime" label="截止时间:"
					labelPosition="left" style="width: 300px;">
			</div>
		</div>
		<table id="worker" class="table" width="400px" height="550px">
		</table>
	</div>
	<div id="container" style="width: 800px; height: 550px; float: right; border: 1px;">&nbsp;</div>

	<!-- <div id="alocationFrame"
		style="width: 100%; margin-left: 400px; height: 600px; border: 2px;"> -->
	<!-- <iframe id="locationFrame" src="mapLastLocation.jsp" width="800px"></iframe> -->
	<!-- </div> -->
</body>
</html>