$(function () {
    var brands = [];
    var storeId = $("#store-id").val();
    var fileInputSelector = $("#store-image");
    var formSelector = $("#store-form");
    var cityPickerSelector = $('#city');
    cityPickerSelector.citypicker({
        province: $("#store-province").val(),
        city: $("#store-city").val(),
        district: $("#store-district").val()
    });
    var multiSelectOptions = {
        selectableHeader: "<div class=''>可选品牌</div>",
        selectionHeader: "<div class=''>已选品牌</div>",
        afterInit: function() {
            var selectedDoms = $('#my_multi_select1').find('option:selected');
            _.each(selectedDoms, function(selectedDom) {
                brands.push($(selectedDom).val());
            });
        },
        afterSelect: function(values) {
            _.each(values, function(value) {
                brands.push(value);
            });
        },
        afterDeselect: function(values) {
            _.each(values, function(value) {
                var index = brands.indexOf(value);
                if (index > -1) {
                    brands.splice(index, 1);
                }
            });
        },

    };
    $('#my_multi_select1').multiSelect(multiSelectOptions);
    var imgs = [$("#store-img-url").val()];
    fileInputSelector.fileinput({
        language: 'zh',
        uploadUrl: '/back/stores/img/upload',
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
        layoutTemplates: {
            actionUpload: '',//设置为空可去掉上传按钮
            actionDelete:'',//设置为空可去掉删除按钮
            // actionZoom: '',//设置为空可去掉预览按钮
            indicator: ''//设置为空可去左侧状态
        },
        uploadExtraData: function (previewId, index) {
            //向后台传递id作为额外参数，是后台可以根据id修改对应的图片地址。
            return {storeId: storeId};
        }
    }).on('filebatchuploadsuccess', function(event, data) {
        var id = storeId;
        var name = $("#store-name").val();
        var tel = $("#store-tel").val();
        var address = $("#store-address").val();
        var provinceId = cityPickerSelector.data('citypicker').getCode("province");
        var cityId = cityPickerSelector.data('citypicker').getCode("city");
        var district = cityPickerSelector.data('citypicker').getCode("district");
        // if (typeof provinceId=== 'undefined' || typeof cityId === 'undefined' ||typeof district === 'undefined') {
        //     utils.modal.myAlert("提示", "请选择省市区");
        //     return;
        // }
        var nameArray = cityPickerSelector.data('citypicker').getVal().split("/");
        var provinceName = nameArray[0];
        var cityName = nameArray[1];
        var districtName= nameArray[2];
        var params = {
            id:id,name:name,tel:tel,addressDetail:address,
            provinceName:provinceName,provinceId:provinceId,
            cityName:cityName,cityId:cityId,
            districtName:districtName,districtId:district,
            brands:brands
        };
        doAjax(params);
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
            name:{
                required:true
            },
            tel:{
                required:true,
                telephone:true
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
            var provinceId = $('#city').data('citypicker').getCode("province");
            var cityId = $('#city').data('citypicker').getCode("city");
            var district = $('#city').data('citypicker').getCode("district");
            if (fileInputSelector.fileinput("getFilesCount") < 1) { //图片未修改
                var id = storeId;
                var name = $("#store-name").val();
                var tel = $("#store-tel").val();
                var address = $("#store-address").val();
                var nameArray = cityPickerSelector.data('citypicker').getVal().split("/");
                var provinceName = nameArray[0];
                var cityName = nameArray[1];
                var districtName= nameArray[2];
                var params = {
                    id:id,name:name,tel:tel,addressDetail:address,
                    provinceName:provinceName,provinceId:provinceId,
                    cityName:cityName,cityId:cityId,
                    districtName:districtName,districtId:district,
                    brands:brands
                }
                doAjax(params);
            }else {
                fileInputSelector.fileinput("upload");
            }
        }
    });
})

function  doAjax(params) {
    $.ajax({
        url:  "/back/stores/edit",
        dataType: 'json',
        type: 'POST',
        data: JSON.stringify(params),
        cache: false,
        contentType:'application/json',
        success:function (data) {
            if (data.code === 200) {
                window.location.href = "/back/stores";
            }else {
                utils.modal.myAlert("提示", data.message);
            }
        }
    });
}