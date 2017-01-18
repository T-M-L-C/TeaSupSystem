<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib tagdir="/WEB-INF/tags"  prefix="k"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>开始学习</title>
    <k:import_js/>
    <k:import_css/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <script type="text/javascript">
    var flag = false;
     var url = window.location.search;
    $(document).ready(function() {
        islogin(0);
        var loginuser;
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
                alert("请求失败！");
            }
        });
        $('#logout').click(function() {
            exit();
        });
       
        if (url.indexOf("?") != -1) {
            //格式=路径&名字=已学习时间&要求时间=id&ppt页数
            var str = url.substr(1);
             strs = str.split("&");
            allmintime = strs[1].split("=")[1];
            allmintime = parseInt(allmintime) * 1000 - parseInt(strs[1].split("=")[1]); //转毫秒
            allmintimecpy = allmintime;
            srcid = strs[2].split("=")[1];
            var forma = strs[0].split("=")[1];
            srcid = strs[2].split("=")[1];
            showiframe(forma, strs[0].split("=")[0], decodeURI(strs[1].split("=")[0]), strs[3].split("=")[0]);
        }
    });
    var srcid;

    var timertime = 2000;
    function submitdoc() {

        // $.ajax({
        //     url: "tra?method=studying",
        //     data: {
        //         "srcid": srcid,
        //         "watched": minu * 60 + second,
        //         "username": getCookieValue("username")
        //     },
        //     success: function(data) {

        //     }
        // });
        setTimeout(submitdoc, 60000);
    }

    function submitmp4() {
        // $.ajax({
        //     url: "tra?method=studying",
        //     data: {
        //         "srcid": srcid,
        //         "watched": (allmintimecpy - allmintime) / 1000,
        //         "username": getCookieValue("username")
        //     },
        //     success: function(data) {

        //     }
        // });
        setTimeout(submitdmp4, 60000);
    }

    function srcevent(st) {
        if (st == 1) { //ppt
            setTimeout(mousemovingppt, 2000);
        } else if (st == 2) { //doc
            $("body").mousemove(function() {
                timertime = 30000;
            });
            setTimeout(submitdoc, 60000);
            setTimeout(handl, 1000);
            srctype = 2;
        } else if (st == 3) { //mp4
            $("body").mousemove(function() {
                timertime = 30000;
            });
            setTimeout(mousemovingmp4, 1000);
            srctype = 3;
        }

    }
    var subno=url.substring(url.lastIndexOf("=")+1);
    //alert(subno);
    function mousemovingppt() {
        $("#nextbtn[disabled]").removeAttr("disabled");
        if (nowpage == max) {
		alert("恭喜您，您已经学习完该课程");
//ppt进度提交
                            $.ajax({
                                url: "tra",
                                data: {
                                    "method":"studying",
                                    "username": getCookieValue("username"),
                                    "srcid":srcid,
                                    "watched":1
                                },
                                success: function(data) {
                                    if(confirm("是否返回学习进度页面"))
                                        window.location.href="progress.jsp";
                                }
                            });
            
            }
        
    }
   
    function mousemovingmp4() {
        if (timertime > 0) {
            setTimeout(mousemovingmp4, 1000);
            timertime -= 1000;
            allmintime -= 1000;
            if (allmintime <= 0) {
                // $.ajax({
                //     url: "tra?method=studying",
                //     data: {
                //         "srcid": srcid,
                //         "watched": allmintimecpy / 1000,
                //         "username": getCookieValue("username")
                //     },
                //     success: function(data) {

                //     }
                // });
            }
            return;
        }
        $('video').trigger('pause');
        setTimeout(mousemovingmp4, 1000);
    }

    var minu = 0;
    var second = 0;
    function learnTime(){
         alert("您需要学习"+Math.round(allmintime/(1000*60))+"分钟的时间，在学习期间不允许中断");
    }
     var flag=true;
    function handl() {
         if(flag){
                  learnTime();
                  flag=!flag;
         }
        if (timertime <= 0) {
            var t = confirm("还剩余"+Math.floor(allmintime/(1000*60))+"分"+Math.floor((allmintime-Math.floor(allmintime/(1000*60))*(1000*60))/1000)+"秒的时间,"+"请确认您还在学习");
            setTimeout(handl, 1000);
            timertime = 30000;
            return;
        }
        allmintime -= 1000;
        timertime -= 1000;
        if (second >= 59) {
            minu++;
            second = 0;
        } else {
            second++;
        }
        if (allmintime <= 0) {
               alert("恭喜您，您已经学习完该课程");
                $.ajax({
                    url: "tra",
                    data: {
                       "method":"studying",
                        "username": getCookieValue("username"),
                        "srcid":srcid,
                        "watched":1
                    },
                    success: function(data) {
                        if(confirm("是否返回学习进度页面"))
                             window.location.href="progress.jsp";
                    }
                });
            }
            else
            {
                setTimeout(handl, 1000);
            }
        $("#mytimer").html("已学习" + minu + "分钟" + second + "秒").css("color","red");
    }
    	 window.onunload = function() {   
               exit();
		};  
    </script>
  </head>
  
<body class="page-body">
    <div class="settings-pane">
        <a href="#" data-toggle="settings-pane" data-animate="true">
            &times;
        </a>
        <div class="settings-pane-inner">
            <div class="row">
                <div class="col-md-4">
                    <div class="user-info">
                        <div class="user-image">
                            <a href="extra-profile.html">
                                <img src="images/user-2.png" class="img-responsive img-circle" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                        <a href="dashboard-1.html" class="logo-expanded">
                            <img src="images/logo.png" width="80" alt="" />
                        </a>
                        <a href="dashboard-1.html" class="logo-collapsed">
                            <img src="images/logo.png" width="40" alt="" />
                        </a>
                    </div>
                    <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
                    <div class="mobile-menu-toggle visible-xs">
                        <a href="#" data-toggle="user-info-menu">
                            <i class="fa-bell-o"></i>
                            <span class="badge badge-success">7</span>
                        </a>
                        <a href="#" data-toggle="mobile-menu">
                            <i class="fa-bars"></i>
                        </a>
                    </div>
                </header>
                <ul id="main-menu" class="main-menu">
                    <!-- add class "multiple-expanded" to allow multiple submenus to open -->
                    <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                    <li>
                        <a href="userinfo.jsp">
                            <i class="linecons-cog"></i>
                            <span class="title" id="person">个人资料</span>
                        </a>
                    </li>
                    <li>
                        <a href="regist.jsp">
                            <i class="linecons-desktop"></i>
                            <span class="title">报名监考</span>
                        </a>
                    </li>
                    <li>
                        <a href="status.jsp">
                            <i class="linecons-note"></i>
                            <span class="title">报名状态</span>
                        </a>
                    </li>
                    <li>
                        <a href="progress.jsp">
                            <i class="linecons-star"></i>
                            <span class="title">学习进度</span>
                        </a>
                    </li>
                            <li>
                        <a href="examplace.jsp">
                            <i class="linecons-mail"></i>
                            <span class="title">查看考场分布</span>
                        </a>
                    </li>
                    <li>
                        <a href="guide.jsp">
                            <i class="linecons-star"></i>
                            <span class="title">操作说明</span>
                        </a>
                    </li>
                     <!--      <li>
                        <a href="exam.html">
                            <i class="linecons-mail"></i>
                            <span class="title">在线考试</span>
                        </a>
                    </li>
                    -->
                </ul>
            </div>
        </div>
        <div class="main-content">
            <!-- User Info, Notifications and Menu Bar -->
            <nav class="navbar user-info-navbar" role="navigation">
                <!-- Left links for user info navbar -->
                <ul class="user-info-menu left-links list-inline list-unstyled">
                    <li class="hidden-sm hidden-xs">
                        <a href="#" data-toggle="sidebar">
                            <i class="fa-bars"></i>
                        </a>
                    </li>
                    <li class="dropdown hover-line">
                        <a href="#" data-toggle="dropdown">
                            <i class="fa-envelope-o"></i>
                            <span class="badge badge-green">1</span>
                        </a>
                        <ul class="dropdown-menu messages">
                            <li>
                                <ul class="dropdown-menu-list list-unstyled ps-scrollbar">
                                    <li class="active">
                                        <!-- "active" class means message is unread -->
                                        <a href="#">
                                            <span class="line">
                                                <strong>使用帮助</strong>
                                            </span>
                                            <span class="line desc small">
                                                请仔细阅读，会对您有帮助的！
                                            </span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="external">
                                <a href="blank-sidebar.html">
                                    <span>All Messages</span>
                                    <i class="fa-link-ext"></i>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Right links for user info navbar -->
                <ul class="user-info-menu right-links list-inline list-unstyled">
                    <li class="dropdown user-profile">
                        <a href="" data-toggle="dropdown">
                            <span id="username">
                                
                            </span>
                            <span>
                                ，欢迎您！
                            </span>
                        </a>
                    </li>
                    <li class="dropdown user-profile">
                        <a href="" data-toggle="dropdown" id="logout">
                            <span>
                                退出登录
                            </span>
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">正在学习 xxxx中</h3>
                </div>
                <script>
                var max, nowpage = 1;
                var minpage = 1;
                var imgpath;

                function downpage() {
                    
                    var pagecount="";
                    pagecount="幻灯片总共"+max+"页";
                    pagecount=pagecount+" "+"目前在第"+nowpage+"页";
                    alert(pagecount);
                    if (imgpath != null && nowpage < max) {
                        nowpage++;
                        $("img").last().attr("src", imgpath + nowpage + ".JPG");
                        //if (nowpage == minpage) {
                            $(event.srcElement).attr("disabled", "disabled");
                            minpage++;
                            
                       // }
                    } else {
                        alert("最后一页");
                    }
                    setTimeout(mousemovingppt, 2000);
                }

                function uppage() {
                     var pagecount="";
                    pagecount="幻灯片总共"+max+"页";
                    pagecount=pagecount+" "+"目前在第"+nowpage+"页";
                    alert(pagecount);
                    if (imgpath != null && nowpage > 1) {
                        nowpage--;
                        $("img").last().attr("src", imgpath + nowpage + ".JPG");
                        $("#nextbtn[disabled]").removeAttr("disabled");
                    } else {
                        alert("第一页");
                    }
                }

                var allmintime;
                var allmintimecpy;
                var srcid;


                function showiframe(url, format, name, len, time) {
                        $(".panel-title").html("正在学习  " + name + "  中...");
                        var t;
                        if (format == "ppt" || format == "pptx") {
                            t = $("<div >" +
                                "<div ><input type=\"button\" value=\"上一页\" onclick=\"uppage()\"></div>" +
                                "<div><img style=\"width:100%;height: 100%;\" src=\"\"></div>" +
                                "<div><input type=\"button\" id=\"nextbtn\" value=\"下一页\" disabled=\"disabled\" onclick=\"downpage()\"></div>");

                            imgpath = url;
                            max = len;
                            var temp = t.addClass("ppt").find("div");
                            t.find("img").attr("src", imgpath + "1.JPG");
                            nowpage = 1;
                            $(temp[0]).addClass("pptpage").find("input").addClass("pptinput");
                            $(temp[1]).addClass("pptcontent").css("width","85%");
                            $(temp[2]).addClass("pptpage").find("input").addClass("pptinput");

                            $("iframe[name=manage]").attr("src", imgpath + nowpage + ".JPG");
                            $("#pagenum").html("当前" + nowpage + "页 / 共" + max + "页");
                            $("#main-content").html("").append(t);

                            srcevent(1);
                        } else if (format == "doc"||format == "docx") {
                            t = $("<div><div style=\"margin:0 auto;width:100%;\"><label id=\"mytimer\" style=\"width:150px;\" ></label>" +
                                "</div><iframe src=\"1427634485664.html\" style=\"width:100%;height:900px;\"></iframe></div>");
                            t.find("iframe").attr("src", url);
                            $("#main-content").html("").append(t);
                            srcevent(2);
                        } else if (format == "mp4") {
                            t = $("<div><video height=\"460\" src=\"123.mp4\" controls=\"\" preload=\"\"></video></div>");
                            t.find("video").attr("src", url);
                            $("#main-content").html("").append(t);

                            srcevent(3);
                        }
                    }
                    //<iframe src="pict_42.JPG" style="width:100%;height:100%;"></iframe>
                </script>
                <style>
                .ppt {
                    width: 100%;
                    height: 520px;
                }
                
                .pptpage {
                    width: 5%;
                    height: 100%;
                    float: left;
                }
                
                .pptcontent {
                    height: 100%;
                    float: left;
                }
                
                .pptinput {
                    height: 100%;
                }
                </style>
                <div class="panel-body" id="main-content">
                    <!--<iframe src="1427634485664.html" style="width:100%;height:500px;"></iframe>-->
                    <!--<video height="460" src="123.mp4" controls preload></video>-->
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
                        &copy; 2015
                        <strong>Backbone Studio</strong>
                    </div>
                    <!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
                    <div class="go-up">
                        <a href="#" rel="go-top">
                            <i class="fa-angle-up"></i>
                        </a>
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
