var Job = {
    columns: [{
        "data": "fullName",
        "orderable": false
    }, {
        "data": "nickname",
        "orderable": false
    }, {
        "data": "dept",
        "orderable": false,
        "render":function (dept) {
            return dept.fullName;
        }
    },{
        "data": "createTime",
        "orderable": false,
        "render":function (createTime) {
            return moment(createTime).format("YYYY-M-D HH:mm:ss");
        }
    },{
        "data": "modifyTime",
        "orderable": false,
        "render":function (modifyTime) {
            if (modifyTime == null) {
                return "";
            }
            return moment(modifyTime).format("YYYY-M-D HH:mm:ss");
        }
    }, {
        "data": null,
        "orderable": false,
        "render":function (data, type, row, meta) {
            if (row.id !== 1) {
                return "<a class='edit-action' title='编辑' data-id='" + row.id +"'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<a class='menu-action' title='权限' data-id='" + row.id +"'><span aria-hidden=\"true\" class=\"icon-user-following\"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<a class='delete-action' title='删除' data-id='" + row.id +"'><i class=\"fa fa-x fa-trash-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            } else {
                return "<a class='edit-action' title='编辑' data-id='" + row.id +"'><i class=\"fa fa-pencil-square-o\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }

        }
    }],
    params:{
        name:null,
        deptId:0
    },
    setting:{
        check: {
            enable: true,
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    }
}


$(function () {

    //请求部门数据
    var deptAjax = utils.myAjax.get("/back/dept/tree", Job.params, function (data) {
        var ztree = new $ZTree("tree", data);
        ztree.bindOnClick(deptTreeClick)
        ztree.init();
    });

    // 请求 岗位数据
    jobAjax();

    // 删除 行事件
    var deleteClick = $("#job-table tbody").on("click", "a.delete-action", function () {
        var that = this;
        var sure = utils.modal.myConfirm("提示", "确认删除该行数据吗", function (sure) {
            if (sure) {
                var id = $(that).data("id");
                $(that).parents('tr').attr('id', id); // 设置 row id
                utils.myAjax.get('/back/jobs/delete',{id:id}, function (data) {
                    if (data.code === 200) {
                        Job.table.row('#' + id).remove().draw(false); // 前端移除 row
                    } else {
                        utils.modal.myAlert(data.message);
                    }
                })
            }
        });
    });

    var editClick =  $("#job-table tbody").on("click", "a.edit-action", function () {
        var id = $(this).data("id");
        window.location.href = "/back/jobs/edit/home?id=" + id;
    });

    var menuClick =  $("#job-table tbody").on("click", "a.menu-action", function () {
        var id = $(this).data("id");
        $("#job-id").attr("value", id);
        var menuHtml = $("#menu-tree-template").html();
        var menuTemplate = Handlebars.compile(menuHtml);
        $("#menu-body").html(menuTemplate());
        var menuAjax = utils.myAjax.get("/back/menus/" + id,{}, function (data) {
            var menuTree = new $ZTree("menu-tree-ul", data);
            menuTree.setSettings(Job.setting);
            menuTree.init();
            $("#menu-modal").modal();
        });
    });
});


// 点击组织部门输入框事件
var deptClick = $("#dept-input").on("click", function () {
    $("#dept-tree").removeClass("hide");
});

// 组织部门树的隐藏和显示控制
var documentClick = $(document).on("click", function (event) {
    var inTree = $("#dept-tree").find(event.target).length;
    var id = event.target.id;
    if (inTree > 0 || id === "dept-input") {
        $("#dept-tree").removeClass("hide");
    } else {
        $("#dept-tree").addClass("hide");
    }
});


// 选择组织机构触发的事件
var deptTreeClick = function (e, treeId, treeNode) {
    $("#dept-input").attr("value", treeNode.name);
    $("#dept-id").attr("value", treeNode.id);
};

// 选择菜单触发的事件
var menuTreeClick = function(e, treeId, treeNode) {

}

utils.quick.click({
    search:function () {
        Job.params.name = $("#job-name").val();
        Job.params.deptId = $("#dept-id").val();
        jobAjax();
    },
    reset:function () {
        $("#job-name").val("");
        $("#dept-id").attr("value", "0");
        $("#dept-input").attr("value", "");
    },
    save:function () {
        var zTree = $.fn.zTree.getZTreeObj("menu-tree-ul");
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        ids = ids.substring(1);
        utils.myAjax.get("/back/menus/" + $("#job-id").val() + "/update", {menuIds:ids}, function (data) {
            $("#menu-modal").modal('hide');
            $(".alert-notice-wrap").removeClass("hide");
            setTimeout("$(\".alert-notice-wrap\").addClass(\"hide\")", 1000);
        })
    },
    back:function () {
        $("#menu-modal").modal('hide');
    }
});

// 请求 岗位数据
var jobAjax = function () {
    utils.myAjax.get("/back/jobs/find",Job.params,function (data) {
        Job.table = $("#job-table").DataTable({
            data:data,
            "destroy": true,
            columns:Job.columns,
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
}