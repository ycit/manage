var UserAdd = {
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
                required: true,
                maxlength:50
            },
            rePassword: {
                required: true,
                maxlength:50,
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
    params: {
        id: 0,
    }

};

$(function () {

    var jForm = $("#user-form");
    var jImg = $("#user-img-input");
    jImg.fileinput({
        language: 'zh', //设置语言
        uploadUrl: '/back/users/img/upload',
        uploadAsync: false,
        showCaption: false,//是否显示标题
        showUpload: false, //是否显示上传按钮
        showRemove: false,
//				 showCancel:false,
        browseIcon: '<i class="glyphicon glyphicon-upload"></i>&nbsp;',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        autoReplace: true,
        allowedFileExtensions: ['jpg', 'png', 'bmp', 'jpeg'],//接收的文件后缀
        enctype: 'multipart/form-data',
        dropZoneEnabled: false,//是否显示拖拽区域
        overwriteInitial: false,//不覆盖已存在的图片
        browseClass: "btn green-soft", //按钮样式
        layoutTemplates: {
            actionUpload: '',//设置为空可去掉上传按钮
            //actionDelete:'',//设置为空可去掉删除按钮
            // actionZoom: '',//设置为空可去掉预览按钮
            indicator: ''//设置为空可去左侧状态
        },
        uploadExtraData: function (previewId, index) {
            //向后台传递id作为额外参数，是后台可以根据id修改对应的图片地址。
            return {id: UserAdd.params.id};
        }
    }).on('fileuploaded', function (event, data, previewId, index) {
        $("#user-img").attr("src", data.response.img);
        window.location.href = "/back/users/home";
    }.on('filebatchuploadcomplete', function (event, files, extra) {
        // console.log('File batch upload complete');

    }).on('filebatchuploaderror', function (event, data, msg) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('File batch upload error');
        // get message
        alert(msg);
    });
    jForm.validate(UserAdd.validate);
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
            var imgCount = jImg.fileinput('getFilesCount');
            var params = jForm.serialize();
            utils.myAjax.post("/back/users/add", params, function (data) {
                UserAdd.params.id = data.id;
                if (imgCount > 0) {
                    jImg.fileinput("upload");
                } else {
                    window.location.href = "/back/users/home";
                }
            })
        },
        back:function () {
            window.location.href = "/back/users/home";
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

