<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>轨迹回放</title>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=GXkbjcszaqwT3Wqq7piaAEeu"></script>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$
				.ajax({
					url : "locationFind.action",
					data : {
						account : "${param.account}",
						date : "${param.date}"
					},
					success : function(dataMap) {
						if (dataMap.msg != "1") {
							alert(dataMap.msg);
							return;
						}

						if (dataMap.ls.length == 0) {
							alert("指定时间内，没有此员工的位置信息！");
							return;
						}
						var points = new Array();
						for (var i = 0; i < dataMap.ls.length; i++) {
							var location = dataMap.ls[i];
							//alert(location.longitude + "=" + location.latitude);
							points[i] = new BMap.Point(location.longitude,
									location.latitude);
						}
						var map = new BMap.Map("container");
						map.centerAndZoom(points[0], 14);
						map.enableScrollWheelZoom();
						var driving = new BMap.DrivingRoute(map);

						for (var i = 0; i < points.length - 1; i++) {
							var start = points[i];
							var end = points[i + 1];
							driving.search(start, end);
							driving.setSearchCompleteCallback(function() {
								points1 = driving.getResults().getPlan(0)
										.getRoute(0).getPath();
								map.addOverlay(new BMap.Polyline(points1, {
									strokeColor : "black",
									strokeWeight : 5,
									strokeOpacity : 1
								}));
							});
							var lngmid = (points[0].lng + points[points.length - 1].lng) / 2;
							var latmid = (points[0].lat + points[points.length - 1].lat) / 2;
							var centerPoint = new BMap.Point(lngmid, latmid);
							map.panTo(centerPoint);
						}
					}
				});
	});
</script>
</head>
<body>

	<div id="container"
		style="width: 800px; height :600px;"></div>

</body>
</html>