$(function () {
    $("#dict-form").validate({
        rules:{
            name:{
                required:true,
                maxlength:20
            },
            description:{
                maxlength:80
            }
        },
        message:{
            name:{
                required:"字典名称不能为空",
                maxlength:"字典名称最大长度不能超过20个字符"
            },
            description:{
                maxlength:"备注信息最大长度不能超过80个字符"
            }
        }
    })
    utils.quick.click({
        submit:function () {
            var pass = $("#dict-form").valid();
            if (!pass) {
                return;
            }
            var name =$("#dict-name").val();
            var desc = $("#dict-desc").val();
            var id = $("#dict-id").val();
            utils.myAjax.post("/back/dict/add", {name:name, description:desc, id:id}, function (data) {
                console.log("success");
                if (data.code === 200) {
                    window.location.href = "/back/dict";
                } else {
                    $("#notice").html(data);
                    // utils.modal.myAlert("新增失败");
                }
            }, function (XMLHttpRequest, msg, exception) {
                console.log(XMLHttpRequest);
                console.log(msg);
                console.log(exception);
            })
        }
    })
})