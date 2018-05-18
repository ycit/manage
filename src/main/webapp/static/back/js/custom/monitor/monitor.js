
var Monitor = {
    params:{

    },
    cpuData:[],
    memData:[],
    diskData:[]
}

$(function () {
    //初始化cpu使用情况统计表
    getCpuData();
    Monitor.cpuData.push(Monitor.params.cpu);
    var cpuChart = echarts.init(document.getElementById('cpu-line'));
    var cpuOption = {
        title: {
            text: 'CPU(' + Monitor.params.cpuTitle + ')'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            name:"时间",
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            name:"% 利用率",
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            },
            min:0,
            max:100
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data:  Monitor.cpuData
        }]
    };
    cpuChart.setOption(cpuOption);
    setInterval(function () {
        if ( Monitor.cpuData.length > 60) {
            Monitor.cpuData.shift();
        }
        getCpuData();
        Monitor.cpuData.push(Monitor.params.cpu);
        cpuChart.setOption({
            series: [{
                data:  Monitor.cpuData
            }]
        });
    }, 1000);


    //初始化内存使用情况统计表
    getMemData();
    Monitor.memData.push(Monitor.params.mem);
    var memChart = echarts.init(document.getElementById('mem-line'));
    var memOption = {
        title: {
            text: '内存'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            name:"时间",
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            name:"GB 内存使用量",
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            },
            min:0,
            max:Monitor.params.memTotal
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data:  Monitor.memData
        }]
    };
    memChart.setOption(memOption);
    setInterval(function () {
        if ( Monitor.memData.length > 60) {
            Monitor.memData.shift();
        }
        getMemData();
        Monitor.memData.push(Monitor.params.mem);
        memChart.setOption({
            series: [{
                data:  Monitor.memData
            }]
        });
    }, 1000);

    //初始化硬盘使用情况统计表
    getDiskData();
    var params = Monitor.params.diskName.split(",");
    var html = "";
    _.forEach(params,function (param) {
       html += param + 'G' + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    })
    $("#detail").html(html);
    Monitor.diskData.push(Monitor.params.disk);
    var diskChart = echarts.init(document.getElementById('disk-line'));
    var diskOption = {
        title: {
            text: '磁盘'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            name:"时间",
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            name:"% 资源利用率",
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            },
            min:0,
            max:100
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data:  Monitor.memData
        }]
    };
    diskChart.setOption(diskOption);
    setInterval(function () {
        if ( Monitor.diskData.length > 60) {
            Monitor.diskData.shift();
        }
        getDiskData();
        Monitor.diskData.push(Monitor.params.disk);
        diskChart.setOption({
            series: [{
                data:  Monitor.diskData
            }]
        });
    }, 1000);

});

function getCpuData() {
    var ajax = $.ajax({
        url:"/back/monitor/cpu",
        type:"get",
        async:false,
        dataType:"json",
        success:function (data) {
            Monitor.params.cpu = {
                name:data.name,
                value:[data.name, data.value.substring(0, data.value.indexOf("%"))]
            };
            Monitor.params.cpuTitle = data.temp;
        }
    });
};

function getMemData() {
    var ajax = $.ajax({
        url:"/back/monitor/mem",
        type:"get",
        async:false,
        dataType:"json",
        success:function (data) {
            Monitor.params.mem = {
                name:data.name,
                value:[data.name, data.value]
            };
            Monitor.params.memTotal = data.temp;
        }
    });
};

function getDiskData() {
    var ajax = $.ajax({
        url:"/back/monitor/disk",
        type:"get",
        async:false,
        dataType:"json",
        success:function (data) {
            Monitor.params.disk = {
                name:data.name,
                value:[data.name, data.value.substring(0, data.value.indexOf("%"))]
            };
            Monitor.params.diskName = data.temp;
        }
    });
};