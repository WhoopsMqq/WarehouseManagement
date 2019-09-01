layui.use(['form','element','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element;
        $ = layui.jquery;

    function showMaterial(){
        $(".material").css("display","inline");
        $(".accessories").css("display","none");
        $(".product").css("display","none");
    }
    $(".materialLi").click(function () {
        showMaterial();
    });

    function showAccessories(){
        $(".material").css("display","none");
        $(".accessories").css("display","inline");
        $(".product").css("display","none");
    }

    $(".accessoriesLi").click(function () {
        showAccessories();
    });

    function showProduct(){
        $(".material").css("display","none");
        $(".accessories").css("display","none");
        $(".product").css("display","inline");
    }

    $(".productLi").click(function () {
        showProduct();
    });

});
