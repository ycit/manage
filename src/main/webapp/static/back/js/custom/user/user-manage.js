var USER = {

    params: {},
}
$(function () {
    var table;

    utils.myAjax.get("/back/users/self", {}, function (data) {
        USER.params.user = data;
        var columns = [{
            "data": "username",
            "orderable": true
        }, {
            "data": "fullName",
            "orderable": false
        }, {
            "data": "nickname",
            "orderable": false
        }, {
            "data": "sex",
            "orderable": false,
            "render": function (sex) {
                if (sex === 0) {
                    return "女";
                } else if (sex === 1) {
                    return "男";
                } else {
                    return "未知";
                }
            }
        }, {
            "data": "dept",
            "orderable": false,
            "render": function (dept) {
                return dept.fullName;
            }
        }, {
            "data": "job",
            "orderable": false,
            "render": function (job) {
                return job.fullName;
            }
        }, {
            "data": null,
            "orderable": false,
            "render": function (data, type, row, meta) {
                if (row.role === 'user') {
                    return "否";
                } else {
                    return "是";
                }
            }
        }
            , {
                "data": null,
                "orderable": false,
                "render": function (data, type, row, meta) {
                    if (row.id !== 1) { //普通用户
                        return "<a class='edit-action' title='编辑' data-id='" + row.id + "'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                            "<a class='delete-action' title='删除' data-id='" + row.id + "'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                    } else { //超级管理员
                        return "<a class='edit-action' title='编辑' data-id='" + row.id + "'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    }

                }
                //     if (row.id !== 1) { //普通用户
                //         if (USER.params.user.id === 1) { //当前登录用户为超级管理员
                //             return "<a class='edit-action' title='编辑' data-id='" + row.id + "'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                //                 "<a class='delete-action' title='删除' data-id='" + row.id + "'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                //         } else { //当前登录用户为普通用户
                //             if (row.id === USER.params.user.id ) {
                //                 return "<a class='edit-action' title='编辑' data-id='" + row.id + "'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                //             }
                //         }
                //     } else { //超级管理员
                //         if (USER.params.user.id === 1) {//当前登录用户为超级管理员
                //             return "<a class='edit-action' title='编辑' data-id='" + row.id + "'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                //         } else { //当前登录用户为普通用户
                //             return "";
                //         }
                //     }
                //
                // }
            }];
        // 请求 用户数据
        utils.myAjax.get("/back/users", {}, function (data) {
            table = $("#user-table").DataTable({
                data: data,
                columns: columns,
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
    });


    // 删除 行事件
    var deleteClick = $("#user-table tbody").on("click", "a.delete-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.post('/back/users/delete', {id: id}, function (data) {
                    if (data.code === 200) {
                        table.row('#' + id).remove().draw(false); // 前端移除 row
                    } else {
                        utils.modal.myAlert(data.message);
                    }
                })
            }
        });
    });

    var editClick = $("#user-table tbody").on("click", "a.edit-action", function () {
        var id = $(this).data("id");
        window.location.href = "/back/users/edit/home?id=" + id;
    });

    var authClick = $("#user-table tbody").on("click", "a.auth-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认授予该用户权限吗？", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.post('/back/users/' + id + '/auth', {role: 'admin'}, function (data) {
                    $(that).parents('tr').find("td:eq(6)").html("是");
                    $(that).parents('tr').find("a.auth-action").addClass("hide");
                    $(that).parents('tr').find("a.no-auth-action").removeClass("hide");
                    $(".alert-notice-wrap").removeClass("hide");
                    setTimeout("$(\".alert-notice-wrap\").addClass(\"hide\")", 1000);
                })
            }
        });
    });

    var noAuthClick = $("#user-table tbody").on("click", "a.no-auth-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认取消该用户权限吗？", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.post('/back/users/' + id + '/auth', {role: 'user'}, function (data) {
                    $(that).parents('tr').find("td:eq(6)").html("否");
                    $(that).parents('tr').find("a.auth-action").removeClass("hide");
                    $(that).parents('tr').find("a.no-auth-action").addClass("hide");
                    $(".alert-notice-wrap").removeClass("hide");
                    setTimeout("$(\".alert-notice-wrap\").addClass(\"hide\")", 1000);
                })
            }
        });
    });

})