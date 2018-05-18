var SYSTEM = {
    validate:{
        rules:{
            cpu:{
                required:true,
                positive:true
            },
            mem:{
                required:true,
                positive:true
            },
            disk:{
                required:true,
                positive:true
            }
        }
    }
}


$(function () {
    var jForm = $("#system-form");
    jForm.validate(SYSTEM.validate);
    utils.quick.click({
        save:function () {
            var pass = jForm.valid();
            if (!pass) {
                return;
            }
            var cpu = $("#cpu").val();
            var mem = $("#mem").val();
            var disk= $("#disk").val();
            var param1={
                name:"cpu",
                value:cpu
            };
            var param2={
                name:"mem",
                value:mem
            };
            var param3={
                name:"disk",
                value:disk
            };
            var params = [];
            params.push(param1);
            params.push(param2);
            params.push(param3);
            $.ajax({
                url:"/back/system/config/edit",
                type:"post",
                dataType:"json",
                data:JSON.stringify(params),
                contentType:"application/json",
                success:function (data) {
                    $(".alert-notice-wrap").removeClass("hide");
                    setTimeout("$(\".alert-notice-wrap\").addClass(\"hide\")", 1000);
                }
            })
        }
    })
})