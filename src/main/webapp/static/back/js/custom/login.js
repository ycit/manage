$(function () {
    $("#login-form").validate({
        rules:{
            username:{
                required:true,
                minlength:4,
                maxlength:12
            },
            password:{
                required:true,
                minlength:4,
                maxlength:12
            }
        },
        messages:{
            username:{
                required:"用户名不能为空",
                minlength:"用户名长度至少4位",
                maxlength:"用户名长度最多12位"
            },
            password:{
                required:"密码不能为空",
                minlength:"密码长度至少4位",
                maxlength:"密码长度最多12位"
            }
        }
    })

    utils.quick.click({
        login:function () {
            var pass = $("#login-form").valid();
            if (!pass) {
                return;
            }
            var username =$("#username").val();
            var password = $("#password").val();
            var remember = $("#remember").is(":checked");
            $.ajax({
                type:"post",
                url:"/back/login",
                dataType:"text",
                data:{
                    username:username,
                    password:password,
                    remember:remember
                },
                success:function (data) {
                    if (data === "success") {
                        window.location.href="/back/users";
                    }else {
                        $("#notice").html(data);
                    }
                    console.log("data is " + data);
                },
                error:function (XMLHttpRequest, msg, exception) {
                    console.log("XMLHttpRequest is " + XMLHttpRequest);
                    console.log("msg is " + msg);
                    console.log("exception is " + exception);
                }
            })
        }
    })
})