$(function () {
    // $.extend(true, $.fn.dataTable.defaults, {
    //     dom: "t<'row-fluid'<'span12'p>>",
    //     pageLength: 20,
    //     language: {
    //         "sProcessing": "处理中...",
    //         "sLengthMenu": "显示 _MENU_ 项结果",
    //         "sZeroRecords": "没有匹配结果",
    //         "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    //         "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    //         "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    //         "sInfoPostFix": "",
    //         "sSearch": "搜索:",
    //         "sUrl": "",
    //         "sEmptyTable": "表中数据为空",
    //         "sLoadingRecords": "载入中...",
    //         "sInfoThousands": ",",
    //         "oPaginate": {
    //             "sFirst": "首页",
    //             "sPrevious": "上页",
    //             "sNext": "下页",
    //             "sLast": "末页"
    //         },
    //         "oAria": {
    //             "sSortAscending": ": 以升序排列此列",
    //             "sSortDescending": ": 以降序排列此列"
    //         }
    //     }
    // });

    Handlebars.registerHelper("time", function(value) {
        if (typeof value !== "number") {
            return "";
        }
        return moment(value).format('YYYY-MM-DD HH:mm');
    });

    Handlebars.registerHelper("compare",function (left, operator, right, options) {
        if (arguments.length < 3) {
            throw new Error('Handlerbars Helper "compare" needs 2 parameters');
        }
        var operators = {
            '==':     function(l, r) {return l == r; },
            '===':    function(l, r) {return l === r; },
            '!=':     function(l, r) {return l != r; },
            '!==':    function(l, r) {return l !== r; },
            '<':      function(l, r) {return l < r; },
            '>':      function(l, r) {return l > r; },
            '<=':     function(l, r) {return l <= r; },
            '>=':     function(l, r) {return l >= r; },
            'typeof': function(l, r) {return typeof l == r; }
        };

        if (!operators[operator]) {
            throw new Error('Handlerbars Helper "compare" doesn\'t know the operator ' + operator);
        }

        var result = operators[operator](left, right);

        if (result) {
            return options.fn(this);
        } else {
            return options.inverse(this);
        }

    });

    jQuery.validator.addMethod("positiveInteger", function(value, element) {
        var reg = /^[1-9]\d*$/;
        return this.optional(element) || reg.test(value);
    }, "必须输入正整数");

    jQuery.validator.addMethod("bigEqualZero", function(value, element) {
        var reg = /^[1-9]\d*|0$/;
        return this.optional(element) || reg.test(value);
    }, "必须输入正整数 或者 0");

    jQuery.validator.addMethod("between", function(value, element) {
        return this.optional(element) ||  value > 0 & value < 100000;
    }, "充值金额不得高于100000");

    jQuery.validator.addMethod("telephone", function(value, element) {
        var reg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/
        return this.optional(element) || reg.test(value);
    }, "号码不合法(固定号码形式:xxx-xxxxx;手机号码为11位)");

    jQuery.validator.addMethod("float", function(value, element) {
        var reg = /^[1-9]\d*\.*\d*|0\.\d*[1-9]\d*$/
        return this.optional(element) || reg.test(value);
    }, "必须输入正浮点数");
})