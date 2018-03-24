$(function () {

    $("#dict-form").valid({
        rules:{
            name:{
                required:true
            }
        },messages:{
            name:{
                required:"字典名称不能为空"
            }
        }
    });

    utils.quick.click({
        new:function () {
            var html = "<tr><td><input type='text' name='name'></td>" +
                "<td><a class='delete-action'><i class='fa fa-x fa-trash-o'></i></a></td></tr>";
            $("#dict-table tbody").append(html);
        },
        save:function () {
            var size = $("#dict-table tbody tr").length;
            if (size < 1) {
                utils.modal.myAlert("至少包含一个字典数据");
                return;
            }
            var pass = $("#dict-form").valid();
            if (!pass) {
                return;
            }
            $("#dict-form").submit();
        }
    });
    
    $("#dict-table").on("click", ".delete-action", function () {
        $(this).parents("tr").remove();
        var size = $("#dict-table tbody tr").length;
        if (size < 1) {
            var html = "<tr style='text-align: center'><td colspan='2'>暂无字典数据</td></tr>";
            $("#dict-table tbody").append(html);
        }
    })

    // var table;
    // var columns = [{
    //     "data": null,
    //     "orderable": false,
    //     "targets":0
    // },{
    //     "data": "name",
    //     "orderable": false
    // },{
    //     "data": "description",
    //     "orderable": false
    // },{
    //     "data": "createTime",
    //     "orderable": true,
    //     "render":function (createTime) {
    //         return moment(createTime).format("YYYY-M-D H:m:s");
    //     }
    // },{
    //     "data": null,
    //     "orderable": false,
    //     "render":function (data, type, row, meta) {
    //         return "<a class='delete-action' title='删除' data-id='" + row.id +"'><i class=\"fa fa-x fa-trash-o\"></i></a> &nbsp;&nbsp;&nbsp;"
    //             + "<a class='edit-action' title='编辑' data-id='" + row.id +"'><i class=\"fa fa-x fa-pencil\"></i></a>&nbsp;&nbsp;&nbsp;";
    //     }
    // }];
    // // 请求 字典表数据
    // utils.myAjax.get("/back/dict/all",{},function (data) {
    //     table = $("#dict-table").DataTable({
    //         data:data,
    //         columns:columns,
    //         // "fnDrawCallback":function(){
    //         //     this.api().on( 'order.dt search.dt', function () {
    //         //         this.api().column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
    //         //             cell.innerHTML = i+1;
    //         //         } );
    //         //     } ).draw();
    //         // },
    //         language: {
    //             "sProcessing": "处理中...",
    //             "sLengthMenu": "显示 _MENU_ 项结果",
    //             "sZeroRecords": "没有匹配结果",
    //             "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    //             "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    //             "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    //             "sInfoPostFix": "",
    //             "sSearch": "搜索:",
    //             "sUrl": "",
    //             "sEmptyTable": "表中数据为空",
    //             "sLoadingRecords": "载入中...",
    //             "sInfoThousands": ",",
    //             "oPaginate": {
    //                 "sFirst": "首页",
    //                 "sPrevious": "上页",
    //                 "sNext": "下页",
    //                 "sLast": "末页"
    //             },
    //             "oAria": {
    //                 "sSortAscending": ": 以升序排列此列",
    //                 "sSortDescending": ": 以降序排列此列"
    //             }
    //         }
    //     });
    //     table.on( 'order.dt search.dt', function () {
    //         table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
    //             cell.innerHTML = i+1;
    //         } );
    //     } ).draw();
    // });
    // // 删除 行事件
    // $("#dict-table tbody").on("click", "a.delete-action", function () {
    //     var that = this;
    //     var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
    //         if (sure) {
    //             var id = $(that).data("id");
    //             $(that).parents('tr').attr('id', id); // 设置 row id
    //             utils.myAjax.post('/back/dict/delete',{id:id}, function (data) {
    //                 if (data > 0) {
    //                     table.row('#' + id).remove().draw(false); // 前端移除 row
    //                 }
    //             })
    //         }
    //     });
    // });
    //
    // utils.quick.click({
    //     //充值提交
    //     recharge: function () {
    //         var pass = $("#inpour-form").valid();
    //         if (!pass) {
    //             return;
    //         }
    //         var id = $("#user-id").val();
    //         var num = $("#user-num").val();
    //         utils.myAjax.post("/back/users/inpour", {id: id, num: num}, function (data) {
    //             $("#" + id).html(data);
    //             $("#inpour-modal").modal("hide");
    //             console.log(data);
    //         })
    //     }
    // });

})