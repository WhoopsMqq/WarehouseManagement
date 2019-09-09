layui.use(['form','layer','table','laydate'],function(){
    var form = layui.form;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    $ = layui.jquery;
    var laydate = layui.laydate;
    table = layui.table;

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

    var tableIns = table.render({
        elem: '#analysisList',
        url : '/loadAnalysis',
        data:{
            startTime:$(".startTime").val(),
            endTime:$(".endTime").val(),
            kind:$(".kind").val()
        },
        cellMinWidth : 95,
        page : false,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "analysisListTable",
        cols : [[
            {field: 'id',title:'编号',minWidth:50,align:'center'},
            {field: 'name', title: '名称', minWidth:200, align:"center"},
            {field: 'typeName', title: '类型', minWidth:200, align:'center'},
            {field: 'number', title: '数量', minWidth:200, align:'center'},
            {field: 'totalPrice', title: '总价', minWidth:200, align:'center'}
        ]]
    });

    $(".search_btn").on('click',function () {
        table.reload('analysisListTable',{
           method: 'get',
           where: {
               startTime:$(".startTime").val(),
               endTime:$(".endTime").val(),
               kind:$(".kind").val()
           }
        });
    });

});