$(function(){
    $("table tr td span").click(function(){
        $(".background").show();
        $(".leave_form").show();
        $(".Sno").val($(this).parent().siblings().eq(0).html());
        $(".Sname").val($(this).parent().siblings().eq(1).html());
        $(".Cid").val($(this).parent().siblings().eq(2).html());
        $(".Cname").val($(this).parent().siblings().eq(3).html());
        $(".week").val($(this).parent().siblings().eq(5).html());
        $(".reason").val($(this).siblings().val());
    })
    $(".close").click(function(){
        $(".background").hide();
        $(".leave_form").hide();
    })
})