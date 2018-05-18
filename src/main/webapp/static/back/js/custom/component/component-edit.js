var COMPONENT_EDIT = {
    validate:{
        rules:{
            name:{
                required:true,
                maxlength:100
            },
            cron:{
                required:true
            }
        }
    },
    params:{}
};

$(function () {
    initTime();
    var jForm = $("#com-add-form");
    jForm.validate(COMPONENT_EDIT.validate);

    utils.quick.click({
        save:function () {
            var pass = jForm.valid();
            if (!pass) {
                return;
            }
            COMPONENT_EDIT.params.task = jForm.serialize();
            utils.myAjax.post("/back/component/edit", COMPONENT_EDIT.params.task, function (data) {
                window.location.href = "/back/component/home";
            });
        },
        back:function () {
            window.location.href = "/back/component/home";
        }
    });

});

var initTime = function () {
    var cron =$("#cron-time").val();
    var time = cron.split(" ");
    var hour = time[2];
    var minute = time[1];
    var second = time[0];
    COMPONENT_EDIT.params.time = hour + ":" + minute + ":" +second;
    jeDate("#time",{
        onClose:false,
        isinitVal:true,
        format:"hh:mm:ss",
        initDate:[{hh:hour,mm:minute,ss:second},false],
        donefun: function(obj) {
            COMPONENT_EDIT.params.time = obj.val;
            console.log(obj.val );
        }
    });
};