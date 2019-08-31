layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    function submitAddUser(){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        var username = $(".userName").val();
        var name = $(".name").val();
        $.get(
            "/addUser?username="+username+"&name="+name,
            function (str) {
                top.layer.close(index);
                top.layer.msg(str);
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }
        );
        // return false;
    }

    $(".addUser").click(function () {
       submitAddUser();
    });

    // form.on("submit(addUser2)",function(data){
    //     //弹出loading
    //     var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    //     // 实际使用时的提交信息
    //     $.post(
    //         "/addUser",
    //         {
    //             username : $(".userName").val(),//登录名
    //             name : $(".name").val()//姓名
    //         },
    //         function (str) {
    //             top.layer.close(index);
    //             top.layer.msg(str);
    //             layer.closeAll("iframe");
    //             //刷新父页面
    //             parent.location.reload();
    //         }
    //     );
    //     return false;
    // });

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})