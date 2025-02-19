!function ($) {
    "use strict";
    var MorrisCharts = function () {
    };
    MorrisCharts.prototype.createLineChart = function (element, data, xkey, ykeys, labels, lineColors) {
        Morris.Line({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            hideHover: 'auto',
            gridLineColor: '#eef0f2',
            resize: true,
            lineColors: lineColors
        });
    }, MorrisCharts.prototype.createLineChart1 = function (element, data, xkey, ykeys, labels, lineColors) {
        Morris.Line({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            gridLineColor: '#3d434a',
            gridTextColor: '#eee',
            hideHover: 'auto',
            pointSize: 3,
            resize: true,
            lineColors: lineColors
        });
    }, MorrisCharts.prototype.createAreaChart = function (element, pointSize, lineWidth, data, xkey, ykeys, labels, lineColors) {
        Morris.Area({
            element: element,
            pointSize: 3,
            lineWidth: 2,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            resize: true,
            hideHover: 'auto',
            gridLineColor: '#eef0f2',
            lineColors: lineColors
        });
    }, MorrisCharts.prototype.createBarChart = function (element, data, xkey, ykeys, labels, lineColors) {
        Morris.Bar({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            gridLineColor: '#eef0f2',
            barSizeRatio: 0.4,
            resize: true,
            hideHover: 'auto',
            barColors: lineColors
        });
    }, MorrisCharts.prototype.createDonutChart = function (element, data, colors) {
        Morris.Donut({element: element, data: data, resize: true, colors: colors});
    }, MorrisCharts.prototype.createDonutChart1 = function (element, data, colors) {
        Morris.Donut({
            element: element,
            data: data,
            resize: true,
            colors: colors,
            labelColor: '#fff',
            backgroundColor: '#0097a7'
        });
    }, MorrisCharts.prototype.createStackedChart = function (element, data, xkey, ykeys, labels, lineColors) {
        Morris.Bar({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            stacked: true,
            labels: labels,
            hideHover: 'auto',
            resize: true,
            gridLineColor: '#4ac18e',
            gridTextColor: '#eee',
            barColors: lineColors
        });
    }, MorrisCharts.prototype.init = function () {
        var $data = [{y: '2018', a: 100, b: 90}, {y: '2019', a: 75, b: 65}, {y: '2020', a: 50, b: 40}, {
            y: '2021',
            a: 75,
            b: 65
        }, {y: '2022', a: 50, b: 40}, {y: '2023', a: 75, b: 65}, {y: '2024', a: 60, b: 50}, {
            y: '2025',
            a: 45,
            b: 35
        }, {y: '2026', a: 75, b: 65}, {y: '2027', a: 80, b: 70}];
        this.createLineChart('morris-line-example', $data, 'y', ['a', 'b'], ['注册用户', '活跃用户'], ['#e22a6f', '#0097a7']);
        var $areaData = [{y: '2018', a: 10, b: 20}, {y: '2019', a: 75, b: 65}, {y: '2020', a: 50, b: 40}, {
            y: '2021',
            a: 75,
            b: 65
        }, {y: '2022', a: 50, b: 40}, {y: '2023', a: 75, b: 65}, {y: '2024', a: 90, b: 60}, {
            y: '2025',
            a: 50,
            b: 40
        }, {y: '2026', a: 75, b: 65}, {y: '2027', a: 80, b: 70}];
        this.createAreaChart('morris-area-example', 0, 0, $areaData, 'y', ['a', 'b'], ['Series A', 'Series B'], ['#e22a6f', '#24d5d8']);
        var $barData = [{y: '2018', a: 100, b: 90}, {y: '2019', a: 75, b: 65}, {y: '2020', a: 50, b: 40}, {
            y: '2021',
            a: 75,
            b: 65
        }, {y: '2022', a: 50, b: 40}, {y: '2023', a: 75, b: 65}, {y: '2024', a: 100, b: 90}, {
            y: '2025',
            a: 90,
            b: 75
        }, {y: '2026', a: 75, b: 65}, {y: '2027', a: 80, b: 70}];
        this.createBarChart('morris-bar-example', $barData, 'y', ['a', 'b'], ['Series A', 'Series B'], ['#e22a6f', '#24d5d8']);
        var $stckedData = [{y: '2018', a: 45, b: 180}, {y: '2019', a: 75, b: 65}, {
            y: '2020',
            a: 100,
            b: 90
        }, {y: '2021', a: 75, b: 65}, {y: '2022', a: 100, b: 90}, {y: '2023', a: 75, b: 65}, {
            y: '2024',
            a: 50,
            b: 40
        }, {y: '2025', a: 75, b: 65}, {y: '2026', a: 50, b: 40}, {y: '2027', a: 75, b: 65}, {
            y: '2028',
            a: 100,
            b: 90
        }, {y: '2029', a: 80, b: 65}, {y: '2030', a: 70, b: 60}];
        this.createStackedChart('morris-bar-stacked', $stckedData, 'y', ['a', 'b'], ['Series A', 'Series B'], ['#e22a6f', '#24d5d8']);
        var $donutData = [{label: "Download Sales", value: 12}, {
            label: "In-Store Sales",
            value: 30
        }, {label: "Mail-Order Sales", value: 20}];
        this.createDonutChart('morris-donut-example', $donutData, ['#ffbb44', '#e22a6f', '#24d5d8']);
        var $donutData1 = [{label: "Download Sales", value: 12}, {
            label: "In-Store Sales",
            value: 30
        }, {label: "Mail-Order Sales", value: 20}];
    }, $.MorrisCharts = new MorrisCharts, $.MorrisCharts.Constructor = MorrisCharts
}(window.jQuery), function ($) {
    "use strict";
    $.MorrisCharts.init();
}(window.jQuery);