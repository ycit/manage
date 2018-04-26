$(function () {
    // var zTreeObj;
    // var setting = {};
    // var zNodes = [
    //     {name:"test1", open:true, children:[
    //             {name:"test1_1"}, {name:"test1_2"}]},
    //     {name:"test2", open:true, children:[
    //             {name:"test2_1"}, {name:"test2_2"}]}
    // ];
    // $(document).ready(function(){
    //     zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
    // });


    var zNodeAjax = utils.myAjax.get("/back/dept/tree", {}, function (data) {

    });
    var ztree = new $ZTree("tree", "/back/dept/tree");
    ztree.init();
})