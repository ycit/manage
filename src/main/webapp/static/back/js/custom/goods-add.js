$(function () {
    var goodsId;
    var fileInputSelector = $("#goods-image");
    var formSelector = $("#goods-form");
    // 表单验证
    formSelector.validate({
        rules: {
            name: {
                required: true,
                maxlength: 50
            }, price: {
                required: true,
                bigEqualZero: true
            }, stock: {
                required: true,
                bigEqualZero: true
            }, description: {
                maxlength: 800
            }, capacity: {
                required: true,
                positiveInteger: true
            }, voltage: {
                required: true,
                positiveInteger: true
            }
        },
        message: {
            name: {
                required: "名称不能为空",
                maxlength: "最大长度不能超过50"
            }, price: {
                required: "价格不能为空",
                positiveInteger: "价格必须为正整数"
            }, stock: {
                required: "库存不能为空",
                positiveInteger: "库存必须为正整数"
            }, description: {
                maxlength: "最大长度不能超过800"
            }, capacity: {
                required: "容量不能为空",
                positiveInteger: "容量必须为正整数"
            }, voltage: {
                required: "电压不能为空",
                positiveInteger: "电压必须为正整数"
            }
        }
    });
    //图片上传插件初始化以及事件监听
    fileInputSelector.fileinput({
        language: 'zh', //设置语言
        uploadUrl: '/back/goods/album/upload',
        uploadAsync: false,
        showCaption: false,//是否显示标题
        showUpload: false, //是否显示上传按钮
        showRemove: false,
//				 showCancel:false,
        browseIcon: '<i class="glyphicon glyphicon-upload"></i>&nbsp;',
        maxFileCount: 5, //表示允许同时上传的最大文件个数
        autoReplace: false,
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
            return {goodsId: goodsId};
        }
    })
        .on('fileuploaded', function (event, data, previewId, index) {
        console.log("fileloaded");
        window.location.href = "/back/goods";
    }).on('filebatchuploadcomplete', function (event, files, extra) {
        console.log('File batch upload complete');
        window.location.href = "/back/goods";
    }).on('filebatchuploaderror', function (event, data, msg) {
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('File batch upload error');
        // get message
        alert(msg);
    });
    //点击事件处理
    utils.quick.click({
        //表单提交事件
        submit: function () {
            var pass = formSelector.valid();
            if (!pass) {
                return;
            }
            var imgCount = fileInputSelector.fileinput('getFilesCount');
            if (imgCount < 0 || imgCount > 5) {
                utils.modal.myAlert("提示", "图片数量请保持在 1-5 张")
            }
            var params = formSelector.serialize();
            utils.myAjax.post("/back/goods/add", params, function (data) {
                if (data.code === 200) {
                    goodsId = data.result[0].id;
                    fileInputSelector.fileinput("upload");
                    // window.location.href = "/back/goods";
                } else {
                    var msg = data.message;
                    utils.modal.myAlert("提示", msg);
                }
                console.log(data.code);
                console.log("success");
            })
        },
        test: function () {
            var imgCount = formSelector.fileinput('getFilesCount');
            fileInputSelector.fileinput("upload");
            console.log(imgCount);
        }
    });


})