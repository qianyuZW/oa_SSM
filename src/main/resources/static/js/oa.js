$(function () {
    var error ="err250";
    var uname = document.getElementById('username');
    var sexit = document.getElementById('sexit');
    var onlineNum=document.getElementById('onlinenum');
    isload =function () {
        $.ajax({
            type:"POST",
            url: "/isOnLine",
            dataType: "json",
            success:function (online) {
                if (online.msg!=error){
                    uname.innerHTML=online.msg.userName+'('+online.msg.email+')';
                } else {
                    window.location.href="/login";
                }
            }
        })
    }
    isload();

    offline=function () {
        swal({
            title: "警告",
            text: "您确定要退出登陆吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    type: "POST",
                    url: "/offline",
                    dataType: "json",
                    success: function (online) {
                        window.location.href('/login')
                    }
                })
            }
        })
    }
    //轮询在线人数
    queryOnline = function () {
        $.ajax({
            type:"GET",
            url: "/getOnlineCount",
            dataType: "json",
            success:function (online) {
                onlineNum.innerHTML = '在线人数：'+online.msg
            }
        })
    }

    window.setInterval(queryOnline,10000)




})