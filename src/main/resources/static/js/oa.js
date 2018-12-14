$(function () {
    var error ="err250";
    var uname = $('input[id="username"]').val();
    isload =function () {
        $.ajax({
            type:"GET",
            url: "/isOnLine",
            dataType: "json",
            success:function (online) {
                if (online.msg!=error){
                    uname.setAttribute('innerText',online.msg.username)
                } else {
                    window.location.href="/login";
                }
            }
        })
    }
})