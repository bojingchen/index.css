$(function(){
    $("table tr td span").click(function(){
        $(".background").show();
        $(".leave_form").show();
        $(".Cno").val($(this).parent().siblings().eq(0).html());
        $(".Cname").val($(this).parent().siblings().eq(1).html());
        $(".Sno").val(window.sessionStorage.getItem("Sno"));
        $(".Ctime").val($(this).parent().siblings().eq(2).html());
    })
    $(".close").click(function(){
        $(".background").hide();
        $(".leave_form").hide();
    })
    $(".cancel").click(function(){
        $(".background").hide();
        $(".leave_form").hide();
    })
})