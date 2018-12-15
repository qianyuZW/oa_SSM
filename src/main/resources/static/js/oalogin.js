$(function () {
    $('#login').click(function () {
        //使用局部变量，保证拿到最新数据，全局变量仅仅拿到首次数据
        var password = $('input[name="password"]').val();
        var email =$('input[name="email"]').val();
        console.log(password)
        console.log(email)
        if (password=='' || email=='' || email==null || password==null){
            sweetAlert('错误', '信息未输入完全！', 'error');
            return;
        }
        $.ajax({
            type:"POST",
            url: "/login",
            data: {
                email: password,
                password: email
            },
            dataType: "json",
            success:function (result) {
                if(result.msg == "err250"){
                    sweetAlert('错误', '邮箱或密码不正确！', 'error');
                }
                if (result.msg == "suc520"){
                    window.location.href="/index";
                }
            }
        })
    })
    $('#regist').click(function () {
        window.location.href="/regist";
    })
    $('#forget').click(function () {
        var emai = prompt("请输入您的邮箱：");
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!reg.test(emai)){
            sweetAlert('错误', '邮箱格式错误，请重试！', 'error');
        }else {
            $.ajax({
                type:"GET",
                url: "/forgetPassword",
                data: {
                    email: emai
                },
                dataType: "json",
                success:function (result) {
                    switch (result.msg) {
                        case 'err250':
                            sweetAlert('信息', '该邮箱从未注册！请您重新注册！', 'info');
                            break;
                        case 'suc520':
                            sweetAlert('信息', '已经把重置密码下发到您的邮箱，建议登陆后立即更改密码！', 'info');
                            break;
                    }
                }
            })
        }

    })
})
