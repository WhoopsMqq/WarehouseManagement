layui.use(['form','layer'],function(){
    var form = layui.form;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;

    function checkPwd(password) {
        $.ajax({
            url: "/checkPwd",
            type: 'GET',
            data:{
                password : password
            },
            success: function(data){
                return data;
            },
            error: function() {
                return false;
            }
        });
    }

    function changePwd(){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        //对数据进行判断
        var password = $.trim($(".password").val());
        if(checkPwd(password) == "0"){
            top.layer.close(index);
            top.layer.msg("密码错误！");
            return false;
        }
        var newPassword = $.trim($(".newPassword").val());
        var confirmPassword = $.trim($(".confirmPassword").val());
        if(newPassword != null && newPassword == confirmPassword){
            $.ajax({
                url: "/changePwd",
                type: 'POST',
                beforeSend: function(request) {
                    request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
                },
                data:{
                    newPassword: newPassword
                },
                success: function(data){
                    if(data.success){
                        top.layer.close(index);
                        top.layer.msg(data.message);
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.window.location.href="/login";
                    }else{
                        top.layer.close(index);
                        top.layer.msg(data.message);
                    }
                },
                error : function(data) {
                }
            });
        }else{
            top.layer.close(index);
            top.layer.msg("两次输入的密码不一致！");
        }
        // return false;
    }

    $(".changePwd").click(function () {
        changePwd();
    });
});