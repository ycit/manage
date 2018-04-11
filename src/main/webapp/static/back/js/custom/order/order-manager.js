$(function () {
    window.pagination;
    window.currentPage = 1;
    window.size = 5;
    window.pageData;
    this.startTime = moment().subtract(29, 'days');
    this.endTime = moment();
    $("#dateRange").find("input").attr("value", this.startTime.format('YYYY-MM-DD') + ' ~ ' + this.endTime.format('YYYY-MM-DD'))
    var that = this;
    var options = {
        opens: 'left',
        //applyClass: 'btn-sm btn-primary',
        //cancelClass: 'btn-sm',
        showDropdowns: true,
        linkedCalendars: false,
        format: 'YYYY-MM-DD',
        locale: {
            applyLabel: '确认',
            cancelLabel: '取消',
            fromLabel: '从',
            toLabel: '到',
            weekLabel: 'W',
            customRangeLabel: '自定义',
            firstDay: 0
        },
        ranges: {
            '今天': [moment(), moment()],
            '过去 7 天': [moment().subtract(6, 'days'), moment()],
            '过去 30 天': [moment().subtract(29, 'days'), moment()],
            '这个月': [moment().startOf('month'), moment().endOf('month')],
            '过去 3 个月': [moment().subtract(3, 'month'), moment()]
        },
        startDate: this.startTime,
        endDate: this.endTime,
        minDate: '2010-01-01'
    }
    $("#dateRange").daterangepicker(options, function(start, end, label) {
        $("#dateRange").find("input").attr("value", start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'))
        that.startTime = start;
        that.endTime = end;
        params.startTime = start.valueOf();
        params.endTime = end.valueOf();
        search(params)
        console.log(start.valueOf());
        console.log(end.valueOf());
        console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
    });

    var results;
    var html = $("#orders-list").html();
    var template = Handlebars.compile(html);
    var tableSelector = $(".results");
    var userId = $("#user-id").val();
    var params = {
        userId:userId,
        startTime:this.startTime.valueOf(),
        endTime:this.endTime.valueOf()
    }
// 请求 订单数据
    search(params);

    function search(params) {
        utils.myAjax.post("/back/orders", params , function (data) {
            results = data.result;
            refresh(template, results);
        });
    };

    $("#user-id").on("change", function () {
        var userId = $(this).find("option:selected").val();
        params.userId = userId;
        search(params);
    })


    tableSelector.on("click", ".delete-action", function () {
        var that = this;
        var orderId = $(this).parents("tbody").find(".order-id").val();
        utils.myAjax.get("/back/orders/delete", {id:orderId}, function (data) {
            if (data > 0) {
                results = _.reject(results, function (obj) {
                    return obj.id === parseInt(orderId);
                });
                $(that).parents("tbody").remove();
                var size = $("#order-table").find("tbody").length;
                if (size < 1) {
                    if (window.currentPage === 1) {
                        $(".results").html("<div class='not-found'> </div><div style='text-align: center'><strong>暂无订单信息</strong></div>")
                    }else {
                        window.currentPage -= 1;
                        refresh(template, results);
                    }
                } else {
                    refresh(template, results);
                }
            }
        });
    });

    tableSelector.on("click", ".send-action", function () {
        var that = this;
        var orderId = $(this).parents("tbody").find(".order-id").val();
        utils.myAjax.get("/back/orders/send", {id:orderId}, function (data) {
            if (data > 0) {
                $(that).parents("tbody").find("td.order-status").html("待签收");
                $(that).parents("tbody").find("a.send-action").addClass("hide");
            }
        });
    });
    
    function refresh(template, data) {
        renderPage(template, data);
        if (typeof window.pagination !== 'undefined') {
            $("#orders-pages").bootstrapPaginator("destroy");
        }
        if (data.length > window.size) {
            var totalSize = data.length;
            var totalPage = Math.ceil(totalSize / window.size);
            var options = {
                bootstrapMajorVersion: 3,
                totalPages: totalPage,
                currentPage: window.currentPage,
                // size: 'small',
                numberOfPages: 5,
                onPageClicked: function (event, originalEvent, type, page) {
                    window.currentPage = page;
                    renderPage(template, data);
                },
                shouldShowPage: function (type) {
                    switch (type) {
                        case "first":
                        case "last":
                            return false;
                        default:
                            return true;
                    }
                }
            };
            window.pagination = $("#orders-pages").bootstrapPaginator(options);
        }
    }

    function renderPage(template, data) {
        var start = window.size * (window.currentPage - 1);
        var end = window.size * window.currentPage - 1;
        window.pageData = data.slice(start, end + 1);
        _.forEach(window.pageData, function (order) {
            order.goodsSize = order.goodsList.length;
            _.forEach(order.goodsList, function (goods) {
                if (goods.goodsName.length > 10) {
                    goods.omitName = goods.goodsName.substr(0, 10) + "..."
                } else {
                    goods.omitName = goods.goodsName;
                }
                goods.totalPrice = goods.goodsNum * goods.goodsPrice
            })
        });

        $(".results").html(template({orders: window.pageData}));
        $('[data-toggle="tooltip"]').tooltip();
    }
});