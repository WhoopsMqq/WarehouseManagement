layui.use(['form','layer','laydate'],function(){
    var form = layui.form;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;
    var laydate = layui.laydate;

    laydate.render({
        elem:'#startTime',
        format:'yyyy-MM-dd HH:mm:ss',
        max: $("#endTime").val()
    });
    laydate.render({
        elem:'#endTime',
        format:'yyyy-MM-dd HH:mm:ss',
        max: $("#startTime").val()
    });

    function loadAnalysis(){
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            url: "/loadAnalysis",
            type: 'GET',
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            data:{
                startTime:$(".startTime").val(),
                endTime:$(".endTime").val(),
                kind:$
            },
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

    $(".search_btn").click(function () {
        loadAnalysis();
    });

});