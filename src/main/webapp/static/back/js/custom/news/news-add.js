$(function () {
    var id;
    var formSelector = $("#news-form");
    var fileInputSelector = $("#news-image");
    fileInputSelector.fileinput({
        language: 'zh', //设置语言
        uploadUrl: '/back/newses/img/upload',
        uploadAsync: false,
        showCaption: false,//是否显示标题
        showUpload: false, //是否显示上传按钮
        showRemove: false,
//				 showCancel:false,
        browseIcon: '<i class="glyphicon glyphicon-upload"></i>&nbsp;',
        allowedFileExtensions: ['jpg', 'png', 'bmp', 'jpeg'],//接收的文件后缀
        enctype: 'multipart/form-data',
        dropZoneEnabled: false,//是否显示拖拽区域
        overwriteInitial: false,//不覆盖已存在的图片
        browseClass: "btn green-soft", //按钮样式
        layoutTemplates: {
            actionUpload: '',//设置为空可去掉上传按钮
            actionDelete:'',//设置为空可去掉删除按钮
            // actionZoom: '',//设置为空可去掉预览按钮
            indicator: ''//设置为空可去左侧状态
        },
        uploadExtraData: function (previewId, index) {
            //向后台传递id作为额外参数，是后台可以根据id修改对应的图片地址。
            return {id: id};
        }
    })
        .on('fileuploaded', function (event, data, previewId, index) {
            console.log("fileloaded");
        }).on('filebatchuploadcomplete', function (event, files, extra) {
        console.log('File batch upload complete');
    }).on('filebatchuploadsuccess', function(event, data) {
        window.location.href = "/back/newses";
    }).on('filebatchuploaderror', function (event, data, msg) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('File batch upload error');
        // get message
        alert(msg);
    });
    formSelector.validate({
        rules:{
            url:{
                required:true
            }
        }
    })
    utils.quick.click({
        submit:function () {
            var pass = formSelector.valid();
            if (!pass) {
                return;
            }
            var url = $("#news-url").val();
            var imgCount = fileInputSelector.fileinput('getFilesCount');
            if (imgCount < 1) {
                utils.modal.myAlert("提示", "请选择上传的图片");
                return;
            } else {
                utils.myAjax.post("/back/newses/add", {url:url}, function (data) {
                    if (data.code === 200) {
                        id = data.result[0].id;
                        fileInputSelector.fileinput("upload");
                    }else {
                        utils.modal.myAlert("提示", data.message);
                    }
                });
            }
        }
    })
})