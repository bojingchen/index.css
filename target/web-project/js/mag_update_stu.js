$(function(){
    $(".location span").html($(".current a").html()+"页面");
    $(".check>div").click(function(){
        $(this).addClass("current").siblings().removeClass("current");
        var index=$(this).index();
        $(".con>div").eq(index).stop().show().siblings().hide();
    })
    if(window.sessionStorage.getItem("pos")){
        $(".check div:contains("+window.sessionStorage.getItem("pos")+")").click();
    }
    window.sessionStorage.clear();
    for(var i=2010;i<2020;i++){
        $("<option value="+i+">"+i+"</option>").appendTo($(".year"));
    }
    $(".dept").change(alter());
    function alter(){
        if($(".dept").val()=="信息与电子工程学院"){
            $(".class").empty();
            for(var i=1;i<=2;i++){
                $("<option value=软件工程18"+i+">软件工程18"+i+"</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++) {
                $("<option value=软件工程19" + i + ">软件工程19" + i + "</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++){
                $("<option value=通信工程18"+i+">通信工程18"+i+"</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++){
                $("<option value=通信工程19"+i+">通信工程19"+i+"</option>").appendTo($(".class"));
            }
        }else if($(".dept").val()=="自动化与电气工程学院"){
            $(".class").empty();
            for(var i=1;i<=2;i++){
                $("<option value=自动化18"+i+">自动化18"+i+"</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++) {
                $("<option value=自动化19" + i + ">自动化19" + i + "</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++){
                $("<option value=电气工程18"+i+">电气工程18"+i+"</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++){
                $("<option value=电气工程19"+i+">电气工程19"+i+"</option>").appendTo($(".class"));
            }
        }else if($(".dept").val()=="生物与化学工程学院"){
            $(".class").empty();
            for(var i=1;i<=2;i++){
                $("<option value=生物工程18"+i+">生物工程18"+i+"</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++) {
                $("<option value=生物工程19" + i + ">生物工程19" + i + "</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++){
                $("<option value=化学工程18"+i+">化学工程18"+i+"</option>").appendTo($(".class"));
            }
            for(var i=1;i<=2;i++){
                $("<option value=化学工程19"+i+">化学工程19"+i+"</option>").appendTo($(".class"));
            }
        }else{
            $(".class").empty();
            $("<option value=0>请选择班级</option>").appendTo($(".class"));
        }
    }
})