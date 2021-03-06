<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="k" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报名状态</title>
      <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/page.js"></script>
    <script src="js/cookie.js"></script>
    <script src="js/ready.js"></script>
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
    var loginuser;
    $(document).ready(function() {
        islogin(0);
        $.ajax({
            type: "get",
            url: "Login",
            cache: false,
            data: {
                action: 'Uid'
            },
            beforeSend: function(XMLHttpRequest) {

            },
            success: function(data, textStatus) {
                if (data != '') {
                    var user = getCookieValue("username");
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
        //暂时不开放此功能
        $.ajax({
            url: "Subject?action=querycheckcounts",
            data: {
                "username": getCookieValue("username")
            },
            success: function(data) {
                function funcdata(n) {
                    this.action = "querycheck";
                    this.username = getCookieValue("username");
                    this.count = 10;
                    this.page = n;
                }
                page($('#pagenumber'), Math.ceil(data / 10), 10, 'Subject', funcdata, dataadd);
            }
        });
        $('#logout').click(function() {
            exit();
        });
    });
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
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEYAAAASCAYAAADmMahlAAAA5UlEQVR42u2Y4Q6EIAyDff+XPuP9OgmOdvtQcnHRBJUhq+0YblvHPt9jHXPm0/a98k3HqDj+9jna7Xl1v9ePAkYFxxpzNHknIJJ1NDC4IrKUrrDlaZAldKNBo6CVNhGMItvsxzlNxKGgC1oPvGpgGTYMfZSXj56psprBmCmgqE5RUKutSoh0CGCybMjK+vbEq8rFAYZgSwUYhCmElNS84tZC2QSPSKsNzAmKZgxVmKF5pddWgKEYQ+5xSsC4ZbRz7TKG3m4g+UUt7iIpVRMlXdtMS7zO7lSpb6J6h15tVvuF8tq/2g6yCzflMEAI+AAAAABJRU5ErkJggg==" width="40" alt="" />
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
                    <li class="active opened active">
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
                    <h3 class="panel-title">报名状态</h3>
                </div>
                <script type="text/javascript">
                function dataadd(data) {
                    $('.middle-align').html('');
                    var temp;
                    var json = jQuery.parseJSON(data);
                    if (json.id == '0') {
                        alert('您尚未报名，请先报名后再查看报名列表');
                    } else {
                        for (var i = 0; i < json.length; i++) {
                            if (json[i].id == '0' && json[i].schoolstate == '0') {
                                temp = '审核通过';
                            } else
                            if (json[i].id == '1' && json[i].schoolstate == '1') {
                                temp = '教务办审核中';
                            } else
                            if (json[i].id == '0' && json[i].schoolstate == '1') {
                                temp = '教务处审核中';
                            }

                            add(json[i].sub_name, json[i].sub_no, json[i].sub_time, temp);
                        }
                    }
                }

                //增加行
                var str;

                function add(name, no, time, statu) {
                    var n = $("#main-table").find("tbody").find("tr").length % 2;
                    str = $("<tr role=\"row\" class=\"\"><td>mhk</td><td>1002</td><td>Social and human service</td><td>" +
                        "</td><td></td></tr>");
                    if (n == 0) {
                        str.addClass("odd");
                    } else {
                        str.addClass("even");
                    }
                    var t = str.find("td");
                    $(t[0]).html(name);
                    $(t[1]).html(no);
                    $(t[2]).html(time);
                    if (statu == '审核通过') {
                        $(t[3]).html(statu).css("color", "red");
                        status = 1;
                    } else {
                        $(t[3]).html(statu).css("color", "green");
                    }
                    $("#main-table").find("tbody").append(str).css("font-family","微软雅黑");
                }
                var status = 0;
                /* function cancel(subno){
                     if(status==1){
                  //       str.find("a").attr("href","");
                       alert('您已经审核通过，无法取消报名');
                     }
                      else{
                             $.ajax({
                                url: "Subject",
                                data: {
                                action:'deleteRegist',
                                   subno:subno,
                                   teano:loginuser 
                                },
                                success: function(data) {
                                    var json=jQuery.parseJSON(data);
                                    if(json.id==0){
                                     alert('取消报名成功');
                                     window.location.reload();
                                    }
                                }
                            });
                        }
                          
                }*/
                </script>
                <div class="panel-body">
                    <div id="example-2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <table class="table table-bordered table-striped dataTable no-footer" id="main-table" role="grid" aria-describedby="example-2_info">
                            <thead>
                                <tr role="row">
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 249px;">考试名称</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 148px;">考试代码</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 301px;">考试时间</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 242px;">报名进度</th>
                                    <!-- <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 200px;">取消报名</th> -->
                                </tr>
                            </thead>
                            <tbody class="middle-align">
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="dataTables_info" id="example-2_info" role="status" aria-live="polite"></div>
                            </div>
                            <div class="col-xs-6">
                                <div class="dataTables_paginate paging_simple_numbers" id="pagenumber">
                                    <ul class="pagination">
                                    </ul>
                                </div>
                            </div>
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
<!-- Bottom Scripts -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/TweenMax.min.js"></script>
    <script src="js/resizeable.js"></script>
    <script src="js/xenon-toggles.js"></script>
    <!-- JavaScripts initializations and stuff -->
    <script src="js/xenon-custom.js"></script>
</body>
</html>
