<!-- chart.jsp-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript">
        window.onload = function() {

            var dataPoints = [[],[]];
            var chart = new CanvasJS.Chart("chartContainer", {
                zoomEnabled: true,
                theme: "light2",
                title: {
                    text: "Pollutant Level in a City"
                },
                axisX: {
                    title: "chart updates every 1.5 secs",
                    interval: 30,
                    intervalType: "second"
                },
                axisY:{
                    title: "Concentration (in µg/m3)",
                    includeZero: false
                },
                toolTip: {
                    shared: true
                },
                legend: {
                    cursor:"pointer",
                    verticalAlign: "top",
                    fontSize: 20,
                    fontColor: "dimGrey",
                    itemclick : toggleDataSeries
                },
                data: [{
                    type: "line",
                    xValueType: "dateTime",
                    yValueFormatString: "#,##0.0 µg/m3",
                    xValueFormatString: "hh:mm:ss TT",
                    showInLegend: true,
                    name: "PM2.5",
                    dataPoints: dataPoints[0]
                },
                    {
                        type: "line",
                        xValueType: "dateTime",
                        yValueFormatString: "#,##0.0 µg/m3",
                        showInLegend: true,
                        name: "NO2" ,
                        dataPoints: dataPoints[1]
                    }]
            });

            var yValue;
            var xValue;

            <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
            <c:forEach items="${dataPoints}" var="dataPoint">
            yValue = parseFloat("${dataPoint.y}");
            xValue = parseInt("${dataPoint.x}");
            dataPoints["${loop.index}"].push({
                x : xValue,
                y : yValue,
            });
            </c:forEach>
            </c:forEach>

            chart.render();

            function toggleDataSeries(e) {
                if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                }
                else {
                    e.dataSeries.visible = true;
                }
                chart.render();
            }

            var updateInterval = 1500;
// initial value
            var yValue1 = 85;
            var yValue2 = 60;

            var time = new Date;
// starting at 6.30 am
            time.setHours(6);
            time.setMinutes(32);
            time.setSeconds(30);
            time.setMilliseconds(00);

            function updateChart(count) {
                count = count || 1;
                var deltaY1, deltaY2;
                for (var i = 0; i < count; i++) {
                    time.setTime(time.getTime()+ updateInterval);
                    deltaY1 = 1 + Math.random() *(-1-1);
                    deltaY2 = 0.5 + Math.random() *(-0.5-0.5);

                    // adding random value and rounding it to two digits.
                    yValue1 = Math.max(Math.round((yValue1 + deltaY1)*10)/10, 10);
                    yValue2 = Math.max(Math.round((yValue2 + deltaY2)*10)/10, 5);

                    // pushing the new values
                    dataPoints[0].push({
                        x: time.getTime(),
                        y: yValue1
                    });
                    dataPoints[1].push({
                        x: time.getTime(),
                        y: yValue2
                    });
                }

                // updating legend text with  updated with y Value
                chart.options.data[0].legendText = " PM2.5  " + yValue1 + " µg/m3";
                chart.options.data[1].legendText = " NO2  " + yValue2 + " µg/m3";
                chart.render();
            }

            setInterval(function(){updateChart()}, updateInterval);

        }
    </script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>