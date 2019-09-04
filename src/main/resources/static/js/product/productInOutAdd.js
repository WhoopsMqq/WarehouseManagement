layui.use(['form','layer'],function(){
    var form = layui.form;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;

    function productInOutAdd(){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: "/productInOutAdd",
            type: 'POST',
            // async: false,
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },
            data:JSON.stringify({
                "product": {"id": $(".product_id").val()},
                "number": $(".number").val(),
                "formNum": $(".formNum").val(),
                "type": $(".type").val()
            }),
            success: function(data){
                if(data.success){
                    top.layer.close(index);
                    top.layer.msg(data.message);
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }else{
                    top.layer.close(index);
                    top.layer.msg(data.message);
                }
            },
            error : function(data) {

            }
        });
        // return false;
    }

    $(".addProduct").click(function () {
        productInOutAdd();
    });


});