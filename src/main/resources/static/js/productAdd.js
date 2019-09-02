layui.use(['form','layer'],function(){
    var form = layui.form;
    layer = parent.layer === undefined ? layui.layer : top.layer;
        $ = layui.jquery;

    function addProduct(){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url: "/addProduct",
            type: 'POST',
            async: false,
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            processData: false,
            data:JSON.stringify({
                "id": Number($(".id").val()),
                "name": $(".name").val(),
                "size": $(".size").val(),
                "color": $(".color").val()
            }),
            // data: {
            //     "id": Number($(".id").val()),
            //     "name": $(".name").val(),
            //     "size": $(".size").val(),
            //     "color": $(".color").val()
            // },
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
        addProduct();
    });


});