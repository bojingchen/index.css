$(function(){
    $(".op2").click(function(){
        $(".op2").addClass("current").siblings().removeClass("current");
        $(".auto").hide();
        $(".forget").hide();
        $("#userid").val("");
        $("#pwd").val("");
        $(".username input").attr("placeholder","请输入管理员账号");
        createCode(5);
    })
    $(".op1").click(function(){
        $(".op1").addClass("current").siblings().removeClass("current");
        $(".auto").show();
        $(".forget").show();
        $("#userid").val("");
        $("#pwd").val("");
        $(".username input").attr("placeholder","请输入学号/工号");
        createCode(5);
    })
    //页面加载时，生成随机验证码
    window.onload=function(){
        createCode(5);    
       }
    
    //生成验证码的方法
    function createCode(length) {
        var codes = "";
        var codeLength = parseInt(length); //验证码的长度
        var code = document.querySelector(".code")
        var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); 
        //循环组成验证码的字符串
        for (var i = 0; i < codeLength; i++)
        {
            var charNum = Math.floor(Math.random() * 62);
            codes += codeChars[charNum];
        }
        if (code)
        {
            code.innerHTML = codes;
        }
    }
    $("#userid").focus(function (){
        if($(".error1").html()){
            $(".error1").html("");
            $("#userid").val("");
            $("#pwd").val(""); 
            $(".checkcode").val("");
            createCode(5);
        }
    })
    $(".change").click(function(){
        createCode(5);
    })
    //检查验证码是否正确
    var flag=false;
    function validateCode()
    {
        //获取显示区生成的验证码
        var checkCode = document.querySelector(".code").innerHTML;
        //获取输入的验证码
        var inputCode = document.querySelector(".checkcode").value;
        if (inputCode.length <= 0)
        {
            $(".error2").html("请输入验证码");
        }
        else if (inputCode.toUpperCase() != checkCode.toUpperCase())
        {
            $(".error2").html("验证码错误");
        }else{
            flag=true;
        }  
    }
    $(".submit").click(function(){
        validateCode();
        if(flag){
            $.ajax({
                type:"POST",
                url:"/login",
                data:{"username":$("#userid").val(),"password":$("#pwd").val()},
                dataType:"json",
                success:function(data,status){
                    console.log(data);
                    console.log(status);
                    if (data.flag==1){
                        if(($(".remember").is(":checked"))&&($(".op1").hasClass("current"))){
                            window.localStorage.setItem("username",$("#userid").val());
                            window.localStorage.setItem("password",$("#pwd").val());
                        }else{
                            window.localStorage.clear();
                        }
                    window.location.href=data.urlHref;}
                   else {$(".error1").html(data.errorMessage);}
                }
            })
        }
    })
    $(".checkcode").focus(function(){
        if($(".error2").html()){
            $(".error2").html("");
            createCode(5);
            $(".checkcode").val("");
        }
    })
    if(window.localStorage.getItem("username")&&window.localStorage.getItem("password")){
        $("#userid").val(window.localStorage.getItem("username"));
        $("#pwd").val(window.localStorage.getItem("password"));
    }
})