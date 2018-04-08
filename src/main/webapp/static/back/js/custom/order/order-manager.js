$(function () {
    var table;
    var columns = [{
        "data": "img",
        "orderable": false,
        "render":function (img) {
            if (img === null) {
                return "";
            }
            return "<a><img class='news-img' src=" + img +"></a>";
        }
    }, {
        "data": "url",
        "orderable": false
    }, {
        "data": "createTime",
        "orderable": false,
        "render":function (createTime) {
            return moment(createTime).format("YYYY-MM-DD HH:mm:ss");
        }
    }, {
        "data": null,
        "orderable": false,
        "render":function (data, type, row, meta) {
            return "<a href='/back/newses/edit?id=" + row.id + "' class='edit-action' title='编辑' data-id='" + row.id +"'><i class=\"fa fa-x fa-pencil\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "<a class='delete-action' title='删除' data-id='" + row.id +"'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        }
    }];
    // 请求 最新资讯
    utils.myAjax.post("/back/newses",{},function (data) {
        table = $("#news-table").DataTable({
            // data:data.result,
            // columns:columns,
            // "ordering": false,
            // language: {
            //     "sProcessing": "处理中...",
            //     "sLengthMenu": "显示 _MENU_ 项结果",
            //     "sZeroRecords": "没有匹配结果",
            //     "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            //     "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            //     "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            //     "sInfoPostFix": "",
            //     "sSearch": "搜索:",
            //     "sUrl": "",
            //     "sEmptyTable": "表中数据为空",
            //     "sLoadingRecords": "载入中...",
            //     "sInfoThousands": ",",
            //     "oPaginate": {
            //         "sFirst": "首页",
            //         "sPrevious": "上页",
            //         "sNext": "下页",
            //         "sLast": "末页"
            //     },
            //     "oAria": {
            //         "sSortAscending": ": 以升序排列此列",
            //         "sSortDescending": ": 以降序排列此列"
            //     }
            // }
        });
    });
    // 删除 行事件
    $("#news-table tbody").on("click", "a.delete-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.post('/back/newses/delete',{id:id}, function (data) {
                    if (data > 0) {
                        table.row('#' + id).remove().draw(false); // 前端移除 row
                    }
                })
            }
        });
    });
})