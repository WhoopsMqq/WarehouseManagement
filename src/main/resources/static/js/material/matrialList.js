layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //成品种类列表
    var tableIns = table.render({
        elem: '#materialList',
        url : '/materialListJson',
        cellMinWidth : 95,
        page : false,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "materialListTable",
        cols : [[
            {field: 'id',title:'编号',minWidth:50,align:'center'},
            {field: 'name', title: '名称', minWidth:200, align:"center"},
            {field: 'size', title: '门幅', minWidth:200, align:'center'},
            {field: 'color', title: '颜色', minWidth:200, align:'center'},
            {title: '操作', minWidth:175, templet:'#materialListBar',fixed:"right",align:"center"}
        ]]
    });

    //添加用户
    function addMaterial(edit){
        var index = layui.layer.open({
            title : "添加原材料",
            type : 2,
            content : "/materialAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".id").val(edit.id);//成品id
                    body.find(".name").val(edit.name);  //登录名
                    body.find(".size").val(edit.size);  //门幅
                    body.find(".color").val(edit.color);  //颜色
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回成品列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        });
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addMaterial();
    });

    //列表操作
    table.on('tool(materialList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            addMaterial(data);
        }
    });

});
