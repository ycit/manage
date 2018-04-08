$(function () {
    var table;
    var columns = [{
        "data": "img",
        "orderable": false,
        "render":function (img) {
            if (img === null) {
                img = "../static/back/img/store.jpg";
            }
            return "<a><img class='store-img' src=" + img +"></a>";
        }
    }, {
        "data": "name",
        "orderable": false
    }, {
        "data": null,
        "orderable": false,
        "render":function (data, type, row, meta) {
            if (row.districtName === null) {
                row.districtName = "";
            }
            if (row.addressDetail === null) {
                row.addressDetail = "";
            }
            return row.provinceName + row.cityName + row.districtName + row.addressDetail;
        }
    }, {
        "data": "tel",
        "orderable": false
    }, {
        "data": "brands",
        "orderable": false,
        "render":function (brands) {
            return _.pluck(brands, "brandName").join(",");
        }
    }, {
        "data": "createTime",
        "orderable": true,
        "render":function (createTime) {
            return moment(createTime).format("YYYY-MM-DD HH:mm:ss");
        }
    }, {
        "data": null,
        "orderable": false,
        "render":function (data, type, row, meta) {
            return "<a href='/back/stores/edit?id=" + row.id + "' class='edit-action' title='编辑' data-id='" + row.id +"'><i class=\"fa fa-x fa-pencil\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                "<a class='delete-action' title='删除' data-id='" + row.id +"'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        }
    }];
    // 请求 用户数据
    utils.myAjax.post("/back/stores",{},function (data) {
        table = $("#store-table").DataTable({
            data:data.result,
            columns:columns,
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
    });
    // 删除 行事件
    $("#store-table tbody").on("click", "a.delete-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.post('/back/stores/delete',{id:id}, function (data) {
                    if (data > 0) {
                        table.row('#' + id).remove().draw(false); // 前端移除 row
                    }
                })
            }
        });
    });
})