!function ($) {
    "use strict";
    var ChartJs = function () {
    };
    ChartJs.prototype.respChart = function (selector, type, data, options) {
        var ctx = selector.get(0).getContext("2d");
        var container = $(selector).parent();
        $(window).resize(generateChart);

        function generateChart() {
            var ww = selector.attr('width', $(container).width());
            switch (type) {
                case 'Radar':
                    new Chart(ctx, {type: 'radar', data: data, options: options});
                    break;
            }
        };generateChart();
    }, ChartJs.prototype.init = function () {
        var radarChart = {
            labels: ["Eating", "Designing", "Coding", "Cycling", "Running"],
            datasets: [{
                label: "Desktops",
                backgroundColor: "rgba(228, 86, 65, 0.72)",
                borderColor: "#e22a6f",
                pointBackgroundColor: "#e22a6f",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "#e22a6f",
                data: [65,  81, 56, 55, 40]
            }, {
                label: "Tablets",
                backgroundColor: "rgba(63, 81, 181, 0.71)",
                borderColor: "#3f51b5",
                pointBackgroundColor: "#3f51b5",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "#3f51b5",
                data: [28,  19, 96, 27, 100]
            }]
        };
        this.respChart($("#radar"), 'Radar', radarChart);
    }, $.ChartJs = new ChartJs, $.ChartJs.Constructor = ChartJs
}(window.jQuery), function ($) {
    "use strict";
    $.ChartJs.init()
}(window.jQuery);