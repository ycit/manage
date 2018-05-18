var COMPONENT = {
    columns: [{
        "data": "name",
        "orderable": false
    }, {
        "data": "groupName",
        "orderable": false
    }, {
        "data": "cron",
        "orderable": false,
        "render":function (cron) {
            var array = cron.split(" ");
            return "每日 " + array[2] + "点" + array[1] +"分" + array[0] + "秒执行";
        }
    }, {
        "data": "status",
        "orderable": false,
        "render": function (status) {
            if (status === 0) {
                return "运行中";
            } else if (status === 1) {
                return "已停止"
            } else if (status === -1) {
                return "已删除"
            } else {
                return "未知";
            }
        }
    }, {
        "data": "createTime",
        "orderable": false,
        "render": function (createTime) {
            if (createTime == null) {
                return "";
            }
            return moment(createTime).format("YYYY-M-D HH:mm:ss");
        }
    },
        //     {
        //     "data": "modifyTime",
        //     "orderable": false,
        //     "render":function (modifyTime) {
        //         if (modifyTime == null) {
        //             return "";
        //         }
        //         return moment(modifyTime).format("YYYY-M-D HH:mm:ss");
        //     }
        // },
        {
            "data": null,
            "orderable": false,
            "render": function (data, type, row, meta) {
                return "<a class='edit-action' title='编辑' data-id='" + row.id + "'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<a class='delete-action' title='删除' data-id='" + row.id + "'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


            }
        }],
    validate: {
        rules: {
            time: {
                required: true
            }
        }
    },
    params: {}
}

$(function () {
    utils.myAjax.get("/back/component/list", {}, function (data) {
        COMPONENT.table = $("#component-table").DataTable({
            data: data,
            "destroy": true,
            columns: COMPONENT.columns,
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
    })
});


// 删除 行事件
var deleteClick = $("#component-table tbody").on("click", "a.delete-action", function () {
    var that = this;
    var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
        if (sure) {
            var id = $(that).data("id");
            $(that).parents('tr').attr('id', id); // 设置 row id
            utils.myAjax.get('/back/component/delete/' + id,{}, function (data) {
                if (data > 0) {
                    COMPONENT.table.row('#' + id).remove().draw(false); // 前端移除 row
                } else {
                    utils.modal.myAlert("删除失败");
                }
            })
        }
    });
});

var editClick =  $("#component-table tbody").on("click", "a.edit-action", function () {
    var id = $(this).data("id");
    window.location.href = "/back/component/edit/home?id=" + id;
});
