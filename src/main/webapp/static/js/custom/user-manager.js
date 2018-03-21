$(function () {
    $("#user-table").DataTable({
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
    jQuery.validator.addMethod("positiveInteger", function(value, element) {
        var reg = /^[1-9]\d*$/;
        return this.optional(element) || reg.test(value);
    }, "必须输入正整数");
    jQuery.validator.addMethod("between", function(value, element) {
        return this.optional(element) ||  value > 0 & value < 10000;
    }, "充值金额不得高于10000");
    $("#inpour-form").validate({
        rules:{
            num:{
                required:true,
                positiveInteger:true,
                between:true
            }
        },
        message:{
            num:{
                required:"充值金额不能为空"
            }
        }
    });
    utils.quick.click({
        inpour:function () {
            var id = $(this).data("id");
            $("#user-id").attr("value", id);
            $("#inpour-modal").modal("show");
        },
        delete:function () {
            var id = $(this).data("id");
            utils.myAjax.post("/back/users/delete", {id:id}, function (data) {
                if (data > 0) {
                    $(this).parents("tr").remove();
                }
            })
        },
        recharge:function () {
            var pass = $("#inpour-form").valid();
            if (!pass) {
                return;
            }
            var id = $("#user-id").val();
            var num = $("#user-num").val();
            utils.myAjax.post("/back/users/inpour", {id:id, num:num}, function (data) {
                $("#" + id).html(data);
                $("#inpour-modal").modal("hide");
                console.log(data);
            })
        }
    });
})