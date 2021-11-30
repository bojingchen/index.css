$(function(){
    $(".location span").html($(".current a").html()+"页面");
        $(".button").eq(0).click(function(){
            $(".mess").stop();
            $(".mess").eq(1).hide();
            $(".mess").eq(2).hide();
            $(".mess").eq(0).slideToggle(200,function(){
                if($(".button").eq(2).offset().top+$(".button").eq(2).height()>$(".footer").offset().top){
                    $(".main").height((window.innerHeight)*0.9);
                }else{
                    $(".main").height((window.innerHeight)*0.839);
                }
            });
            $(".dept").val("0");
            $(".class").empty();
            $("<option value=0>请选择班级</option>").appendTo($(".class"));
        })
        $(".button").eq(1).click(function(){
            $(".mess").stop();
            $(".mess").eq(0).hide();
            $(".mess").eq(2).hide();
            $(".mess").eq(1).slideToggle(200,function(){
                if($(".button").eq(2).offset().top+$(".button").eq(2).height()>$(".footer").offset().top){
                    $(".main").height((window.innerHeight)*0.9);
                }else{
                    $(".main").height((window.innerHeight)*0.839);
                }
            });
            $(".dept").val("0");
            $(".class").empty();
            $("<option value=0>请选择班级</option>").appendTo($(".class"));
        })
        $(".button").eq(2).click(function(){
            $(".mess").stop();
            $(".mess").eq(0).hide();
            $(".mess").eq(1).hide();
            $(".mess").eq(2).slideToggle(200,function(){
                console.log($(".mess").eq(2).offset().top+$(".mess").eq(2).height());
                console.log($(".footer").offset().top);
                if($(".mess").eq(2).offset().top+$(".mess").eq(2).height()+35>$(".footer").offset().top){
                    $(".main").height((window.innerHeight)*0.9);
                }else{
                    $(".main").height((window.innerHeight)*0.839);
                }
            });
            $(".dept").val("0");
            $(".class").empty();
            $("<option value=0>请选择班级</option>").appendTo($(".class"));
        })
        for(var i=2000;i<2020;i++){
            $("<option value="+i+">"+i+"</option>").appendTo($(".year"));
        }
        $(".dept").change(function(){
            if($(".dept").val()==="信息与电子工程学院"){
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
            }else if($(".dept").val()==="自动化与电气工程学院"){
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
            }else if($(".dept").val()==="生物与化学工程学院"){
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
        })
        
        $(".start,.end").change(function(){
            if($(".start").val()<=0||$(".start").val()>12){
                $(".start").val("");
            }if($(".end").val()<=0||$(".end").val()>12){
                $(".end").val("");
            }
        })
})