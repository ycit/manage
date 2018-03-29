$(function () {
    var id = $("#news-id").val();
    var url;
    var formSelector = $("#news-form");
    var fileInputSelector = $("#news-image");
    var imgs = [$("#news-img-url").val()];
    fileInputSelector.fileinput({
        language: 'zh',
        uploadUrl: '/back/newses/img/upload',
        uploadAsync: false,
        showCaption: false,//是否显示标题
        showUpload: false, //是否显示上传按钮
        showRemove: false,
        showCancel:true,
        browseIcon: '<i class="glyphicon glyphicon-upload"></i>&nbsp;',
        maxFileSize: 1000,
        allowedFileExtensions: ['jpg', 'png', 'bmp', 'jpeg'],//接收的文件后缀
        enctype: 'multipart/form-data',
        dropZoneEnabled: false,//是否显示拖拽区域
        overwriteInitial: true,//覆盖已存在的图片
        browseClass: "btn green-soft", //按钮样式
        initialPreview:imgs,
        initialPreviewAsData:true,
        initialPreviewShowDelete:true,
        validateInitialCount:true,
        layoutTemplates: {
            actionUpload: '',//设置为空可去掉上传按钮
            actionDelete:'',//设置为空可去掉删除按钮
            // actionZoom: '',//设置为空可去掉预览按钮
            indicator: ''//设置为空可去左侧状态
        },
        uploadExtraData: function (previewId, index) {
            //向后台传递id作为额外参数，是后台可以根据id修改对应的图片地址。
            return {id:id};
        }
    }).on('filebatchuploadsuccess', function(event, data) {
        window.location.href = "/back/newses";
    }).on('filebatchuploaderror', function (event, data, msg) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('File batch upload error');
        // get message
        alert(msg);
    });
    // 表单校验
    formSelector.validate({
        rules:{
            url:{
                required:true
            }
        }
    });
    // 点击事件处理
    utils.quick.click({
        edit: function () {
            var pass = formSelector.valid();
            if (!pass) {
                return;
            }
            url = $("#news-url").val();
            var imgCount = fileInputSelector.fileinput('getFilesCount');
            var files = fileInputSelector.fileinput('getFileStack');
            var params = {
                id:id,url:url
            }
            utils.myAjax.post("/back/newses/edit", params, function (data) {
                if (data.code === 200) {
                    if (imgCount < 1) {
                        window.location.href = "/back/newses";
                    } else {
                        fileInputSelector.fileinput("upload");
                    }
                }else {
                    utils.modal.myAlert("提示", data.message);
                }
            });
        }
    });


})