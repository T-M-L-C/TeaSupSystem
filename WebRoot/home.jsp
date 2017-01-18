<%@page import="com.backbone.entity.po.HomeNews"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>新疆大学大型考试监考老师培训平台</title>
  <script type="text/javascript"  src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript"  src="js/cookie.js"></script>
  
<script type="text/javascript">
     $(function(){
        var submit=document.getElementById("submit");
         submit.onclick=function(){
            $.post('Login',
           {
           	 action: 'login',
           	 teacher:$('#identity').val(),
             uname:$('#user_ID').val(),
             passwd:$('#user_pwd').val()
           },
           function(data){
               if(data=='successful'){

					setCookie("username", $('#user_ID').val(), "", "/");
	                setCookie("password", $('#user_pwd').val(), "", "/");
					setCookie("teacher", $('#identity').val(), "", "/");

                var authority=$('#identity').val();
                 if(authority=='0'){
                 //普通教师客户端跳转
                 window.location.href="guide.jsp";
                 }
                 else
                     if(authority=='1' || authority=='2'){
                     //教务办人员或者教务处人员跳转页面
                      window.location.href="userinfo2.jsp";
                     }
                     else
                        if(authority=='3'){
                        //门户管理人员跳转
                          window.location.href="userinfo3.jsp";
                        }
               }
               else
                  if(data=='null'){
                       alert('用户名或者密码不能为空');
                  }
                  else
                    if(data=='already'){
                      alert('您已经登录，不能重复登录!');
                     /* window.setTimeout(function(){
                           $.ajax({
					             type: "post",
					             url: "Login",
					             data: {
					             action:'logout',
					             username:$('#user_ID').val()
					             },
					             success: function(data){
					              var json=jQuery.parseJSON(data);
								       if(json.id==0){
								            window.location.href="index.jsp";
								         }
					             }
					         });
					         
                      }, 30000);*/
                    }
               else
                 if(data=='failure')
	                 {
	                 alert("请检查你的身份，用户名和密码是否正确");
	                 $('#user_ID').val("");
	                 $('#user_pwd').val("");
	               }
           });
          
        };
	$('#reset').click(function(){
	   $('#user_ID').val('');
	   $('#user_pwd').val('');
	});
    });
     function onkey()
	{
		if (window.event.keyCode==13)
		{
		   submit.onclick();
		}
	}
		
</script>
<style type="text/css">
    body {
        margin: 0;
        padding: 0;
        overflow: auto;
        min-width: 700px;
    }
    
    .infor {
        font-family: 微软雅黑;
    }
    
    .blank {
        height: 10px;
    }
    
    .breakline {
        height: 20px;
        width: 100%;
        background-color: #E8E8E8;
    }
    
    #top {
        height: 100px;
        width: 100%;
    }
    
    #logo_area {
        height: 87px;
        width: 100%;
    }
    
    #logo {
        height: 87px;
        width: 287px;
        background: url(images/XJ_Logo.jpg) no-repeat;
    }
    
    #logo_words {
        height: 87px;
        width: 400px;
        margin: 0 auto;
        background: url(images/Logo_words.png) no-repeat;
    }
    
    #mid {
        width: 100%;
        height: 350px;
        background-image: url(images/mid_back.jpg);
        -moz-background-size: 100% 100%;
        -o-background-size: 100% 100%;
        -webkit-background-size: 100% 100%;
        background-size: 100% 100%;
        -moz-border-image: url(images/mid_back.jpg) 0;
        background-repeat: no-repeat\9;
        background-image: none\9;
        filter: progid: DXImageTransform.Microsoft.AlphaImageLoader(src='images/mid_back.jpg', sizingMethod='scale')\9;
    }
    
    #identity {
        width: 156px;
    }
    
    #mid_infor {
        float: left;
        margin-left: 15%;
        height:350px;
    }
    
    tr {
        height: 40px;
    }
    
    #bottom {
        height: 100px;
        margin: 0 auto;
    }
    
    .RightWords {
        color: #8696BA;
        margin: 0 auto;
        width: 524px;
    }
    
    #mid_right {
        float: right;
        margin-right: 15%;
        width: 550px;
        height: 350px;
    }
    
    .newscontent {
        height: 215px;
        overflow: auto;
    }

    </style>
  </head>
  <body>
      <div>
        <div id="logo"></div>
        <div id="logo_words"></div>
    </div>
    <!-- <div class="breakline"></div> -->
    <div id="mid">
        <div id="mid_infor">
            <table>
                <thead>
                    <tr>
                        <td><b>登录入口</b></td>
                    </tr>
                    <tr style="height:20px;"></tr>
                </thead>
                
                <tbody>
                    <tr>
                        <td><span class="infor">身份：</span></td>
                        <td>
                            <select id="identity" name="teacher" style="width:100%">
                                <option value="0" selected="selected">教师</option>
                                <option value="1">教务办人员</option>
                                <option value="2">考试中心</option>
                                <option value="3">门户维护人员</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span>用户名:</span></td>
                        <td>
                            <input id="user_ID" type="text" name="uname" />
                        </td>
                    </tr>
                    <tr>
                        <td><span class="infor">密码：</span></td>
                        <td>
                            <input id="user_pwd" type="password" name="passwd" onkeydown="onkey()" class="teapwd" />
                        </td>
                        <td><a href="forgetpwd.jsp"  style="text-decoration: none; font-family: 微软雅黑">忘记密码</a></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button class="btn" type="button" style="float:left;" id="submit" onkeydown="onkey()">登录</button>
                            <button class="btn" type="reset" style="float:right;" id="reset">重置</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="mid_right">
            <table>
                <tr>
                   <td style="position: absolute; right: 520px ;top: 185px"><b>通知公告</b></td>
                </tr>
            </table>
            <div class="newscontent" style="overflow: hidden;" id="scrollDiv">
                <table>
                    <thead>
                        <tr style="margin: 3px">
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                         <ul>
                        <c:forEach items="${lstHomeNews}" varStatus="" var="item">
							   <li style="margin: 5px;font-family: 微软雅黑 ">${item.homenewscontent}
								[<fmt:formatDate value="${item.homenewspublishtime}" pattern="yyyy-MM-dd HH:mm:ss"/>]</li>
						</c:forEach>
						</ul>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="bottom">
        <div class="RightWords">
            <p>&copy; copyright by @Backbone Studio 2015-2016 all rights reserved</p>
        </div>
    </div>
      <script type="text/javascript">
          function AutoScroll(obj) {
            $(obj).find("ul:first").animate({
                marginTop: "0px"
            }, 1500, function() {
                $(this).css({ marginTop: "0px" }).find("li:first").appendTo(this);
            });
        }
        $(document).ready(function() {
        var obj=$("#scrollDiv").find("ul:first").find("li").length;
        if(obj<=5)
        return; 
            var myar = setInterval('AutoScroll("#scrollDiv")', 1500);
            $("#scrollDiv").hover(function() { 
                 clearInterval(myar); 
            },
             function() { 
             myar = setInterval('AutoScroll("#scrollDiv")', 
             1500)}); //当鼠标放上去的时候，滚动停止，鼠标离开的时候滚动开始
        });
       </script>
  </body>
</html>
