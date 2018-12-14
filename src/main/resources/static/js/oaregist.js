$(function () {
    var success = "suc520";
    var error ="err250";
    var notexsit="not320";//不存在
    var isexsit="exi330";
    $('#login').click(function () {
        window.location.href="/login";
    })
    $('#regist').click(function () {
        var pwd = $('input[id="pwd"]').val();
        var spwd = $('input[id="spwd"]').val();
        var email = $('input[id="email"]').val();
        var uname = $('input[id="username"]').val();
        var ivt = $('input[id=invite]').val();
        if (isnotnull(pwd)&& isnotnull(spwd) && isnotnull(email)&& isnotnull(uname)&&isnotnull(ivt)){
            if(spwd!=pwd){
                sweetAlert('错误', '两次密码输入不一致，请重试！', 'error');
                return;
            }
            if (spwd.length<8){
                sweetAlert('错误', '密码长度小于8位，请重试！', 'error');
                return;
            }
            var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            if (!reg.test(email)){
                sweetAlert('错误', '邮箱格式错误，请重试！', 'error');
                return;
            }
            $.ajax({
                type:"POST",
                url: "/regist",
                data: {
                    ivt:ivt,
                    email: email,
                    password: pwd,
                    username:uname
                },
                dataType: "json",
                success:function (data) {
                    if (data.msg == isexsit){
                        sweetAlert('注册失败', '邮箱已被注册！', 'info');
                    }
                    if (data.msg == success ){
                        sweetAlert('注册成功','赶紧登录吧！','info');
                    }
                    if(data.msg == error){
                        sweetAlert('注册失败','您无法完成注册，企业未对你进行邀请！','info');
                    }
                }
            })
        } else {
            sweetAlert('错误', '信息未输入完全！', 'error');
        }
    })

    isnotnull = function (str) {
        if (str == null || str == "")
            return false;
        else
            return true;
    }
})