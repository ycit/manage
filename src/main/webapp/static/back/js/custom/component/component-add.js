var COMPONENT_ADD = {
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
    jForm.validate(COMPONENT_ADD.validate);

    utils.quick.click({
        save:function () {
            var pass = jForm.valid();
            if (!pass) {
                return;
            }
            COMPONENT_ADD.params.task = jForm.serialize();
            utils.myAjax.post("/back/component/add", COMPONENT_ADD.params.task, function (data) {
                window.location.href = "/back/component/home";
            })
        },
        back:function () {
            window.location.href = "/back/component/home";
        }
    });
   
});

var initTime = function () {
    jeDate("#time",{
        onClose:false,
        isinitVal:false,
        format:"hh:mm:ss",
        // initDate:[{hh:hour,mm:minute,ss:second},false],
        donefun: function(obj) {
            COMPONENT_ADD.params.time = obj.val;
            console.log(obj.val );
        }
    });
};



