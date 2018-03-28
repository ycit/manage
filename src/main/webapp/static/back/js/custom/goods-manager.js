$(function () {
     window.table;
    var columns = [{
        "data": "thumbnail",
        "orderable": false,
        "render":function (thumbnail) {
            if (thumbnail === null) {
                thumbnail = "../static/back/img/bad.jpg";
            }
            return "<a><img class='goods-thumbnail' src=" + thumbnail +"></a>";
        }
    }, {
        "data": "name",
        "orderable": false
    }, {
        "data": "brand",
        "orderable": false,
        "render":function (brand) {
            if (brand !== null) {
                return brand.name;
            } else {
                return "";
            }
        }
    }, {
        "data": "store",
        "orderable": false,
        "render": function (store) {
            if (store !== null) {
                return store.name;
            } else {
                return "";
            }
        }
    }, {
        "data": "price",
        "orderable": true,
    }, {
        "data": "stock",
        "orderable": true
    }, {
        "data": "purpose",
        "orderable": false,
        "render": function (purpose) {
            if (purpose !== null) {
                return purpose.name;
            } else {
                return "";
            }
        }
    },{
        "data": "category",
        "orderable": false,
        "render": function (category) {
            if (category !== null) {
                return category.name;
            } else {
                return "";
            }
        }
    },{
        "data": "capacity",
        "orderable": true
    },{
        "data": "voltage",
        "orderable": true
    },{
        "data": null,
        "orderable": false,
        "render":function (data, type, row, meta) {
            return "<a href='/back/goods/edit?id=" + row.id + "' class='edit-action' title='编辑' data-id='" + row.id +"'><i class=\"fa fa-x fa-pencil\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<a class='delete-action' title='删除' data-id='" + row.id +"'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        }
    }];
    // 请求 商品数据
    var params = $("#goods-form").serialize();
    search(params, window.table, columns);
    // 删除 行事件
    $("#goods-table tbody").on("click", "a.delete-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.post('/back/goods/delete',{id:id}, function (data) {
                    if (data > 0) {
                        window.table.row('#' + id).remove().draw(false); // 前端移除 row
                    }
                })
            }
        });
    });

    utils.quick.click({
        //查询
        search:function () {
            var params = $("#goods-form").serialize();
            search(params, window.table, columns);
        }
    });

});
function search(params, table, columns) {
    utils.myAjax.post("/back/goods",params,function (data) {
        window.table = $("#goods-table").DataTable({
            data:data,
            // "scrollX": true,
            columns:columns,
            searching:false,
            "destroy": true,
            info:false,
            bLengthChange: false,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }
            }
        });
    });
}