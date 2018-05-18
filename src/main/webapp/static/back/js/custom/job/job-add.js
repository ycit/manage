var DeptAdd = {
    validate:{
        rules:{
            fullName:{
                required:true,
                maxlength:50
            },
            nickname:{
                maxlength:50
            },
            deptName:{
                required:true
            }
        },
        messages:{

        }
    }
};

$(function () {
    var jForm = $("#job-add-form");
    jForm.validate(DeptAdd.validate);

    //请求部门数据
    var deptAjax = utils.myAjax.get("/back/dept/tree", {}, function (data) {
        var ztree = new $ZTree("tree", data);
        ztree.bindOnClick(deptTreeClick)
        ztree.init();
    });

    utils.quick.click({
        save:function () {
            var pass = jForm.valid();
            if (!pass) {
                return;
            }
            var params = jForm.serialize();
            utils.myAjax.post("/back/jobs/add", params, function (data) {
                window.location.href = "/back/jobs/home";
            })
        },
        back:function () {
            window.location.href = "/back/jobs/home";
        }
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