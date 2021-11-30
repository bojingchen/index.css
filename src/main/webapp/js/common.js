$(function(){
    //修改时间位数
    function change(obj){
        if(obj<10){
            obj="0"+obj;
        }
        return obj;
    }
    //显示时间
    time();
    function time(){
        var vWeek,vWeek_s;
        vWeek = ["星期天","星期一","星期二","星期三","星期四","星期五","星期六"];
        var date =  new Date();
        year = date.getFullYear();
        month = change(date.getMonth() + 1);
        day = change(date.getDate());
        hours = change(date.getHours());
        minutes = change(date.getMinutes());
        seconds = change(date.getSeconds());
        vWeek_s = date.getDay();
        $(".now").html(year + "年" + month + "月" + day + "日" + "\t" + hours + ":" + minutes +":" + seconds + "\t" + vWeek[vWeek_s]);
        if(hours>6&&hours<=12){
            $(".hello").html("早上好");
        }else if(hours>12&&hours<=18){
            $(".hello").html("下午好");
        }else {
            $(".hello").html("晚上好");
        }
    }
    clearInterval(timer);
    var timer= setInterval(time,1);
})