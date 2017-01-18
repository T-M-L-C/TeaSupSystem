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
    
    <title>预览资源</title>
    <k:import_js/>
    <k:import_css/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	    <script type="text/javascript">
    $(document).ready(function (){
        islogin(3);
         var loginuser; 
            $.ajax( {  
                     type : "get",  
                     url : "Login",  
                     timeout : 20000,  
                     cache : false,  
                     data:{action: 'Uid'},
                     beforeSend : function(XMLHttpRequest) {  
                       
                     },  
                     success : function(data, textStatus) {  
                        if(data!=''){
                          var user=getCookieValue("username");
                          $('#username').html(user);
                          loginuser=user;
                        }
                     },  
                     complete : function(XMLHttpRequest, textStatus) {  
                        
                     },  
                     error : function() {  
                        alert("请求失败！");  
                     }  
                    });
                    
                           $('#logout').click(function(){
                               exit();
                           });
    });
        var flag = false;
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
                        <a href="http://www.xju.edu.cn" class="logo-expanded">
                            <img src="images/logo.png" width="80" alt="" />
                        </a>

                        <a href="http://www.xju.edu.cn" class="logo-collapsed">
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
                    <li >
                        <a href="userinfo3.jsp">
                            <i class="linecons-cog"></i>
                            <span class="title">个人资料</span>
                        </a>
                    </li>
                    <li class="active opened active">
                        <a href="upload.jsp">
                            <i class="linecons-desktop"></i>
                            <span class="title">上传文件管理</span>
                        </a>
                        <ul>
                            <li>
                                <a href="upload.jsp">
                                    <span class="title">上传资源</span>
                                </a>
                            </li>
                            <li >
                                <a href="manage.jsp">
                                    <span class="title">管理资源</span>
                                </a>
                            </li>
                            <li class="active opened active">
                                <a href="#">
                                    <span class="title">预览资源</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="addexam.jsp">
                            <i class="linecons-note"></i>
                            <span class="title">添加考试信息</span>
                        </a>
                    </li>
                    <li >
                        <a href="addpaper.jsp">
                            <i class="linecons-note"></i>
                            <span class="title">添加试卷</span>
                        </a>
                    </li>
                    <li>
                        <a href="AddUserInfor.jsp">
                            <i class="linecons-note"></i>
                            <span class="title">添加人员信息</span>
                        </a>
                    </li>
                    <li>
                        <a href="addteach.html">
                            <i class="linecons-star"></i>
                            <span class="title">教师考试情况</span>
                        </a>
                    </li>
                     <li>
                       <a href="homenews.jsp">
                            <i class="linecons-star"></i>
                            <span class="title">添加通知</span>
                       </a>
                    </li>
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

                    <li class="dropdown user-profile" style="min-height: 75px;">
                        <a href="" data-toggle="dropdown">
                            <span id="username">tmlc</span>
                            <span>
                                ，欢迎您！
                            </span>
                        </a>
                    </li>

                    <li class="dropdown user-profile" style="min-height: 75px;">
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
                    var imgpath;
                    function downpage() {
                        if (imgpath != null && nowpage < max) {
                            nowpage++;
                            $("img").last().attr("src", imgpath + nowpage + ".JPG");
                        }
                        else
                        {
                            alert("最后一页");
                        }
                    }
                    function uppage() {
                        if (imgpath != null && nowpage > 1) {
                            nowpage--;
                            $("img").last().attr("src", imgpath + nowpage + ".JPG");
                        }
                        else
                        {
                            alert("第一页");
                        }
                    }
                    
                    $(document).ready(function () {
                        var url = window.location.search;
                        if (url.indexOf("?") != -1) {
                            var str = url.substr(1)
                            strs = str.split("&");
                            showiframe(strs[0].split("=")[1], strs[0].split("=")[0], decodeURI(strs[1].split("=")[0]),strs[2].split("=")[1]);
                        }
                    });
                    


                    function showiframe(url, format, name,len) {
                        $(".panel-title").html("正在学习  " + name + "  中...");
                        var t;
                        if (format == "ppt" || format == "pptx") {
                            t = $("<div >" +
                        "<div ><input type=\"button\" value=\"上一页\" onclick=\"uppage()\"></div>" +
                        "<div ><img style=\"height:100%;\" src=\"pict_42.jpeg\"></div>" +
                        "<div><input type=\"button\" value=\"下一页\" onclick=\"downpage()\"></div>" +
                        "</div>");
                            // "<div style=\"height: 100%;display: table;\">" +
                            // "<div style=\"display:table-cell;vertical-align: middle;\">" +
                            //     "<label>该页观看时间：</label><input type=\"text\" />" +
                            // "</div></div>

                            imgpath = url;
                            max = len;
                            var temp = t.addClass("ppt").find("div");
                            t.find("img").attr("src", imgpath + "1.JPG");
                            nowpage = 1;
                            $(temp[0]).addClass("pptpage").find("input").addClass("pptinput");
                            $(temp[1]).addClass("pptcontent");
                            $(temp[2]).addClass("pptpage").find("input").addClass("pptinput");
                        }
                        else if (format == "doc" || format == "docx") {
                            t = $("<div><iframe src=\"1427634485664.html\" style=\"width:100%;height:500px;\"></iframe></div>");
                            t.find("iframe").attr("src", url);
                        }
                        else if (format == "mp4") {
                            t = $("<div><video height=\"460\" src=\"123.mp4\" controls preload></video></div>");
                            t.find("video").attr("src", url);
                        }

                        $("iframe[name=manage]").attr("src", imgpath + nowpage + ".JPG");
                        $("#pagenum").html("当前" + nowpage + "页 / 共" + max + "页");

                        if (t != null) {
                            $("#main-content").html("").append(t);
                        }
                    }
                    //<iframe src="pict_42.jpeg" style="width:100%;height:100%;"></iframe>

                </script>


                <style type="text/css">
                    .ppt {
                        width:100%;
                        height:520px;
                    }
                    .pptpage {
                    width:5%;
                    height:100%;
                    float:left;
                    }
                    .pptcontent{
                        width: 90%;
                        height:100%;
                        float:left;
                    }
                    .pptinput{
                        height:100%;
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
