<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags"  prefix="k"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>参加考试</title>
    <k:import_js/>
    <k:import_css/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
 <script type="text/javascript">
    var flag = false;
    var loginuser;
   	var url=window.location.search;
    var patt = new RegExp("=");
    if(patt.test(url)){
        var subno=url.substring(url.lastIndexOf("=")+1,url.length);
       	if(subno!=null){
       	   $.ajax({
            type:"POST",
            url:"Subject",
            data:{
                "action":"getexam",
                "subno":subno
            },
            success:function (data){
                var t = JSON.parse(data);
                t.sort(function (a,b){return a.quesNo-b.quesNo;});
                for (var i = 0; i < t.length; i++) {
                    add(t[i].quescontent,t[i].answers,t[i].answers[0].isImage,i+1);
                    answers.push(t[i].rightanswer);
                }
            }
         });
       	}    	  
    }
	  else{
	   alert("请先完成培训学习");
	          location.replace("progress.jsp");
	  }
    var answers = new Array();
         $.ajax({
            type: "get",
            url: "Login",
            timeout: 20000,
            cache: false,
            data: {
                action: 'Uid'
            },
            beforeSend: function(XMLHttpRequest) {
   
            },
            success: function(data, textStatus) {
                if (data != '') {
                 var user=getCookieValue("username");
                    $('#username').html(user);
                    loginuser = user;
                }
            },
            complete: function(XMLHttpRequest, textStatus) {

            },
            error: function() {
                
            }
        });
          
         $(function(){
              $('#logout').click(function() {
          	 	 exit();
           	});
           	
           	 if(patt.test(url)){
           	  var subno=url.substring(url.lastIndexOf("=")+1,url.length);
           	   $.post("Subject",
	           	{
	           	   action:"Searchname",
	           	   subno:subno
	           	},
	           	function(data){
	           	  var json=jQuery.parseJSON(data);
	           	  $(".panel-title").html(json.sub_name);
	           	});
           	 }  	
         });
         //关闭浏览器
      	 window.onunload = function() {   
               exit();
		};  
		
    </script>
  </head>
  
<body class="page-body">
	<div class="settings-pane">
		<a href="#" data-toggle="settings-pane" data-animate="true">
			&times; </a>
	</div>
	<div class="page-container">
		<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others fixed">
			<div class="sidebar-menu-inner">
				<header class="logo-env">
					<!-- logo -->
					<div class="logo">
						<a href="dashboard-1.html" class="logo-expanded"> <img
							src="images/logo.png" width="80" alt="" /> </a> <a
							href="dashboard-1.html" class="logo-collapsed"> <img
							src="images/logo.png" width="40" alt="" /> </a>
					</div>
					<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
					<div class="mobile-menu-toggle visible-xs">
						<a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i>
							<span class="badge badge-success">7</span> </a> <a href="#"
							data-toggle="mobile-menu"> <i class="fa-bars"></i> </a>
					</div>
				</header>
				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
					<li><a href="userinfo.jsp"> <i class="linecons-cog"></i>
							<span class="title">个人资料</span> </a></li>
					<li><a href="regist.jsp"> <i class="linecons-desktop"></i>
							<span class="title">报名监考</span> </a></li>
					<li><a href="status.jsp"> <i class="linecons-note"></i> <span
							class="title">报名状态</span> </a></li>
					<li><a href="progress.jsp"> <i class="linecons-star"></i>
							<span class="title">学习进度</span> </a></li>
					<li><a href="exam.jsp"> <i class="linecons-mail"></i> <span
							class="title">在线考试</span> </a></li>
					<li><a href="examplace.jsp"> <i class="linecons-mail"></i>
							<span class="title">查看考场分布</span> </a></li>
					<li class="active opened active"><a href="guide.jsp"> <i
							class="linecons-star"></i> <span class="title">操作说明</span> </a></li>
				</ul>
			</div>
		</div>
		<div class="main-content">
			<!-- User Info, Notifications and Menu Bar -->
			<nav class="navbar user-info-navbar" role="navigation">
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">
					<li class="hidden-sm hidden-xs"><a href="#"
						data-toggle="sidebar"> <i class="fa-bars"></i> </a></li>
					<li class="dropdown hover-line"><a href="#"
						data-toggle="dropdown"> <i class="fa-envelope-o"></i> <span
							class="badge badge-green">1</span> </a>
						<ul class="dropdown-menu messages">
							<li>
								<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
									<li class="active">
										<!-- "active" class means message is unread --> <a href="#">
											<span class="line"> <strong>使用帮助</strong> </span> <span
											class="line desc small"> 请仔细阅读，会对您有帮助的！ </span> </a></li>
								</ul></li>
							<li class="external"><a href="blank-sidebar.html"> <span>All
										Messages</span> <i class="fa-link-ext"></i> </a></li>
						</ul></li>
				</ul>
				<!-- Right links for user info navbar -->
				<ul class="user-info-menu right-links list-inline list-unstyled">
					<li class="dropdown user-profile"><a href=""
						data-toggle="dropdown"> <span id="username"> </span> <span>
								，欢迎您！ </span> </a></li>
					<li class="dropdown user-profile"><a href=""
						data-toggle="dropdown" id="logout"> <span> 退出登录 </span> </a></li>
				</ul>
			</nav>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">2015下MHK考试</h3>
				  
				</div>
				<div>
					<h3>有任何问题，可第一时间和考试中心联系，电话：8583363。或加入新疆大学监考QQ群：182457662</h3>
				</div>
				<div class="panel-body">

					<link rel="stylesheet" href="css/mkcenter.css" />
					<script type="text/javascript">
    </script>
					<div id="content2">
						<div id="examPage" class="clearfix">
							<div class="topic_sel">
								<a href="#chinesetag0" class="cur"><span>选择题。（每道题目至少有一个正确答案）</span>
								</a>
							</div>
							<script>
             function handleall(){
                	      
               		    var n = 1;
		                var flag=0;
		                $(".answers").each(function (){
		                    var t = "";
		                    var inputs = $(this).find("input[checked='checked']");
		                    $("#topic"+n).css("background-color","");
		                    for(var i=0;i<inputs.length;i++)
		                    {
		                        t+=$(inputs[i]).val();
		                    }
		                    if(answers[n-1]!=t)
		                    {
		                        $("#topic"+n).css("background-color","red");
		                        flag =1;
		                    }
		                    n++;
		                });
		              /* for(var i=0;i<answers.length;i++){
		                    if(answers[i]=="" || answers[i]==null){
		                      alert("请您做完所有题目，再重新提交答案");
		                      return;
		                    }
		                }*/
		                  /*    $.ajax({
				                 url:"Subject",
				                 type:"post",
				                 dataType:"json",
				                 data:{
				                  action:"validanswer",
				                  subno:url.substring(url.lastIndexOf("=")+1,url.length),
				                  answer:JSON.stringify(answers)
				                 },
				                 success:function(data){
				                   var json=eval(data);
				                   for	(var i=0;i<json.length;i++){
				                      if(json[i]=='0'){
				                        $("#topic"+parseInt(i+1)).css("background-color","red");
				                      }
				                      else{
				                        $("#topic"+parseInt(i+1)).css("background-color","");
				                      }
				                   }
				                 }
				                });*/
		              
		                
		               if(flag ==0)
		                  {
		                      var tag=$("#chinesetag0").find("div").length;
		                      if(tag>0){
		                            alert("恭喜您，答对了全部题目");
			                       $.ajax({
			                        url:"Login?action=finishexam",
			                        data:{
			                            "username":getCookieValue("username"),
			                            "subno":subno
			                        },
			                        success:function (data){
			                        var json=jQuery.parseJSON(data);
			                        if(json.id>0){
			                          if(confirm("是否返回学习进度页面")){
			                           	 window.location.href="progress.jsp";
			                           }
			                         }
			                        }
			                    });
		                   }else{
		                     window.location.reload();
		                   }
		               
		                }
		                else
		                {
		                    alert("红色背景的题目做错了");
		                }
             }
             
             
        

                function answerclick()
                {
                    var t = $(event.srcElement);
                    //clear all selected
                    // t.parent().find(".selected").removeClass("selected").find("input").each(function () {
                    //     if($(this).attr("checked") == "checked")
                    //         $(this).removeAttr("checked");
                    // });
                    // t.addClass("selected").find("input").first().attr("checked","checked");

                    var n = t.parent().parent().prev().find(".numberbg").first().html();
                    n = parseInt(n);

                    if(t.hasClass("selected"))
                    {
                        t.removeClass("selected");
                        t.find("input").first().removeAttr("checked");
                        if(t.parent().find(".selected").length<=0)
                        {
                           // getsmall(n).removeClass("cur");
                        }
                    }
                    else
                    {
                        t.addClass("selected");
                        t.find("input").first().attr("checked","checked");
                       // getsmall(n).addClass("cur");
                    }
                }

                
            </script>
							<div class="topic_area">
								<div class="sChoice" id="chinesetag0">
									<h3 class="topic_title">
										一、不定项选择题<span>(合计100分)</span>
									</h3>

								</div>
							</div>
							<a href="javascript:void(0);" style="margin-bottom: 100px;"
								onclick="handleall();return false;" class="handed_btn"></a>
						</div>
						<script>
        function bottomclick() {
            if ($("#answercard").css("display") == "none") {
                var flag = 0;
                if ($(document).scrollTop() >= $(document).height() - $(window).height() - 100)
                    flag = 1;
                $(".handed_btn").css("margin-bottom", "200px");
                $("#answercard").css("display", "block");
                if (flag == 1)
                    $(document).scrollTop(document.documentElement.scrollHeight);
            } else {
                $(".handed_btn").css("margin-bottom", "100px");
                $("#answercard").css("display", "none");
            }
        }

        var chars = ["A","B","C","D","E","F","G","H"];

        function add(t,items,flag,n)
        {
            var str = $("<div class=\"topic_con\" id=\"topic"+n+"\"><div class=\"exdescript\"><p><span class=\"numberbg\">1</span></p></div>"+
                        "<div class=\"chooseAnswer clearfix\"><div class=\"left answers\"><span>选择答案：</span>"+
                        "</div></div></div>");
            var numtemp = str.find("p").first();
            numtemp.find("span").html(n);
            numtemp.append(t);
            if (flag == 0) {
                for (var i = 0; i < items.length; i++) {
                    numtemp.append("<BR>"+ items[i].answer);
                };
            }
            else
            {
                for (var i = 0; i < items.length; i++) {
                    numtemp.append("<BR>" + chars[i] + "<img src=\""+items[i].answer+"\">");
                };
            }
            var andiv = str.find(".answers");
            var anitem = $("<span class=\"option \" onmouseup=\"answerclick()\"><input type=\"radio\" value=\"A\"></span>");
            for (var i = 0; i < items.length; i++) {
                var anitemtem = anitem.clone();
                anitemtem.append(chars[i]).find("input").val(chars[i]);
                andiv.append(anitemtem);
            };
            $("#chinesetag0").append(str);
        }



        var hou =0;
        var minu =0;
        var secd =0;

        function timertime(){
            if(secd >=59)
            {
                secd = 0;
                if(minu == 59)
                {
                    minu = 0;
                    hou++;
                }
                else
                    minu++;
            }
            else
            {
                secd++;
            }
            $("#time").find("span").first().html((hou<10?"0":"")+hou+":"+(minu<10?"0":"")+minu+":"+(secd<10?"0":"")+secd);
            setTimeout(timertime, 1000);
        }
        setTimeout(timertime, 1000);

        </script>

						<div id="Scantron">
					<!-- 	<div class="title" id="timer" >
                <a href="javascript:;" onclick="getallanswer();return false;" class="hand"></a>
                <div class="time" id="time">已用时间：<span>00:00:00</span></div>
            </div>
            <div class="con" id="answercard" style="display:none">
                <ul style="list-style:none;">
                <li><a href="#topic1">1</a><a href="#topic2">2</a><a href="#topic3">3</a><a href="#topic4">4</a><a href="#topic5">5</a></li><li><a href="#topic6">6</a><a href="#topic7">7</a><a href="#topic8">8</a><a href="#topic9">9</a><a href="#topic10">10</a></li><li><a href="#topic11">11</a></li></ul>
            </div> -->
        </div>
						</div>
					</div>
				</div>
				<!-- Main Footer -->
				<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
				<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
				<!-- Or class "fixed" to  always fix the footer to the end of page -->
				<footer class="main-footer sticky footer-type-1">
					<div class="footer-inner">
						<!-- Add your copyright text here -->
						<div class="footer-text">
							&copy; 2015 <strong>Backbone Studio</strong>
						</div>
						<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
						<div class="go-up">
							<a href="#" rel="go-top"> <i class="fa-angle-up"></i> </a>
						</div>
					</div>
				</footer>
			</div>
		</div>
		<div class="page-loading-overlay">
			<div class="loader-2"></div>
		</div>

</body>
</html>
