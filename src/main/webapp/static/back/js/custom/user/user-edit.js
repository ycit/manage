var UserEdit = {
    validate: {
        rules: {
            username: {
                required: true,
                maxlength: 50,
                remote: {
                    type: "get",
                    url: "/back/users/valid",
                    data: {
                        username: function () {
                            return $("#username").val();
                        },
                        id:function () {
                            return $("#user-id").val();
                        }
                    }
                }
            },
            fullName: {
                required: true,
                maxlength: 50
            },
            nickname: {},
            password: {
                required: true
            },
            rePassword: {
                required: true,
                equalTo: "#password"
            },
            deptName: {
                required: true,
            },
            jobId: {
                required: true,
            }
        }, messages: {
            username: {
                remote: "用户名已存在"
            }
        }
    },
    pwValidate:{
        rules:{
            oldPw:{
                required:true
            },
            newPw:{
                required:true,
                maxlength:50
            },
            reNewPw:{
                required:true,
                maxlength:50,
                equalTo: "#password-new"
            }
        },
        messages:{

        }
    },
    params: {
        id: 0,
    }

};

$(function () {

    var jForm = $("#user-form");
    var jPwForm = $("#pw-form");
    var jImg = $("#user-img-input");
    var userId = $("#user-id").val();
    var userInfoHtml = $("#user-info-template").html();
    var userInfoTemplate = Handlebars.compile(userInfoHtml);

    //请求用户数据
    var userAjax = utils.myAjax.get("/back/users/" + userId, {}, function (user) {
        var deptId = user.deptId;
        //初始化头像
        jImg.fileinput({
            language: 'zh', //设置语言
            uploadUrl: '/back/users/img/upload',
            uploadAsync: true,
            showCaption: false,//是否显示标题
            showUpload: true, //是否显示上传按钮
            showRemove: false,
            showCancel:false,
            browseIcon: '<i class="glyphicon glyphicon-upload"></i>&nbsp;',
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            autoReplace: true,
            allowedFileExtensions: ['jpg', 'png', 'bmp', 'jpeg'],//接收的文件后缀
            enctype: 'multipart/form-data',
            dropZoneEnabled: false,//是否显示拖拽区域
            overwriteInitial: true,//覆盖已存在的图片
            browseClass: "btn green-soft", //按钮样式
            initialPreview:[user.img],
            initialPreviewAsData:true,
            initialPreviewShowDelete:true,
            layoutTemplates: {
                actionUpload: '',//设置为空可去掉上传按钮
                //actionDelete:'',//设置为空可去掉删除按钮
                // actionZoom: '',//设置为空可去掉预览按钮
                indicator: ''//设置为空可去左侧状态
            },
            uploadExtraData: function (previewId, index) {
                //向后台传递id作为额外参数，是后台可以根据id修改对应的图片地址。
                return {id: user.id};
            }
        }).on('fileuploaded', function (event, data, previewId, index) {
            $("#user-img").attr("src", data.response.img);
            $(".alert-notice-wrap").removeClass("hide");
            autoAlert();
            console.log('File batch upload complete');
        }).on('filebatchuploadcomplete', function (event, files, extra) {

        }).on('filebatchuploaderror', function (event, data, msg) {
            alert(msg);
        });
        // 查询部门对应岗位信息
        var jobAjax = utils.myAjax.get("/back/jobs/" + deptId,{}, function (jobs) {
            $("#user-form").find(".form-body").html(userInfoTemplate({user:user, jobs:jobs}));

        });

    });


    jForm.validate(UserEdit.validate);
    jPwForm.validate(UserEdit.pwValidate);
    //请求部门数据
    var deptAjax = utils.myAjax.get("/back/dept/tree", {}, function (data) {
        var ztree = new $ZTree("tree", data);
        ztree.bindOnClick(deptTreeClick)
        ztree.init();
    });

    utils.quick.click({
        save: function () {
            var pass = jForm.valid();
            if (!pass) {
                return;
            }
            var params = jForm.serialize();
            utils.myAjax.post("/back/users/edit", params, function (data) {
                $(".alert-notice-wrap").removeClass("hide");
                autoAlert();
            });
        },
        back:function () {
            window.location.href = "/back/users/home";
        },
        password:function () {
            var pass = jPwForm.valid();
            if (!pass) {
                return;
            }
            var params = jPwForm.serialize();
            utils.myAjax.post("/back/users/pw", params, function (data) {
                if (data.code === 200) {
                    $(".alert-notice-wrap").removeClass("hide");
                    setTimeout("$(\".alert-notice-wrap\").addClass(\"hide\")", 1000);
                }else {
                    utils.modal.myAlert(data.message);
                }
            })
        }
    });
});

// 选择组织机构触发的事件
var deptTreeClick = function (e, treeId, treeNode) {
    $("#dept-input").attr("value", treeNode.name);
    $("#dept-id").attr("value", treeNode.id);
    var jobAjax = utils.myAjax.get("/back/jobs/" + treeNode.id, {}, function (data) {
        var html = $("#job-select-template").html();
        var template = Handlebars.compile(html);
        $("#job-select").html(template({jobs: data}));
    });
};

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
utils.modal
var autoAlert = function () {
    setTimeout("$(\".alert-notice-wrap\").addClass(\"hide\")", 1000);
};

