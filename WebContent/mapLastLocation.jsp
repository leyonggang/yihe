<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实时位置</title>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=GXkbjcszaqwT3Wqq7piaAEeu"></script>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : "locationFindLast.action",
			data : {
				account : "${param.account}"
			},
			success : function(dataMap) {
				if (dataMap.msg != "1") {
					alert(dataMap.msg);
					return;
				}
				if (dataMap.location == null) {
					alert("没有此员工的位置信息！");
					return;
				}

				var location = dataMap.location;
				//alert("时间："+location.locationDate+" "+location.locationTime);
				//alert("时间：" + location.dateTime);
				//var Longitude = 114.38;
				//var Latitude = 36.10;

				var Longitude = location.longitude;
				var Latitude = location.latitude;

				var map = new BMap.Map("allmap");
				map.centerAndZoom(new BMap.Point(Longitude, Latitude), 16);
				map.enableScrollWheelZoom(true);
				var point = new BMap.Point(Longitude, Latitude);
				var label = new BMap.Label(location.locationTime, {
					offset : new BMap.Size(-20, -20)
				});
				var marker1 = new BMap.Marker(point); // 创建标注  
				marker1.setLabel(label);
				map.addOverlay(marker1); // 将标注添加到地图中  
				map.panTo(point);
			}
		});
	});
</script>
</head>
<body>
	<div id="allmap" style="width: 800px; height: 600px"></div>
</body>
</html>