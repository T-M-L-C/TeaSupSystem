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
    
    <title>上传文件</title>
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
    // $(function () {
    //     islogin(3);
    // });
    var flag = false;
    $(function() {
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
		//退出登录
        $('#logout').click(function() {
            exit();
        });
        
        //操作超时
          window.setTimeout(function(){
           alert("操作超时，请重新登录");
      		 exit();
        },3600000*2);
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
                            <li class="active opened active">
                                <a href="upload.jsp">
                                    <span class="title">上传资源</span>
                                </a>
                            </li>
                            <li>
                                <a href="manage.jsp">
                                    <span class="title">管理资源</span>
                                </a>
                            </li>
                            <li>
                                <a href="watch.jsp">
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
                    <li>
                        <a href="addteach.jsp">
                            <i class="linecons-star"></i>
                            <span class="title">添加教师信息</span>
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
                    <h3 class="panel-title">
                        上传资源
                    </h3>
                </div>
                <div class="panel-body">
                    <script type="text/javascript">
                    jQuery(document).ready(function($) {
                        var i = 1,
                            $example_dropzone_filetable = $("#example-dropzone-filetable"),
                            example_dropzone = $("#advancedDropzone").dropzone({
                                url: 'FileSvt2',
                                acceptedFiles: ".png,.jpg",
                                maxFilesize: 500,
                                // Events
                                addedfile: function(file) {
                                    if (i == 1) {
                                        $example_dropzone_filetable.find('tbody').html('');
                                    }

                                    var size = parseInt(file.size / 1024, 10);
                                    size = size < 1024 ? (size + " KB") : (parseInt(size / 1024, 10) + " MB");

                                    var $el = $('<tr>\
                                                    <td class="text-center">' + (i++) + '</td>\
                                                    <td>' + file.name + '</td>\
                                                    <td><div class="progress progress-striped"><div class="progress-bar progress-bar-warning"></div></div></td>\
                                                    <td>' + size + '</td>\
                                                    <td>Uploading...</td>\
                                                </tr>');

                                    $example_dropzone_filetable.find('tbody').append($el);
                                    file.fileEntryTd = $el;
                                    file.progressBar = $el.find('.progress-bar');
                                },

                                uploadprogress: function(file, progress, bytesSent) {
                                    file.progressBar.width(progress + '%');
                                },

                                success: function(file) {
                                    file.fileEntryTd.find('td:last').html('<span class="text-success">上传成功</span>');
                                    file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
                                },

                                error: function(file) {
                                    var t = file.name.substr(file.name.lastIndexOf(".")).toLowerCase();
                                    if (t != ".ppt" && t != ".doc" && t != ".mp4") {
                                        file.fileEntryTd.find('td:last').html('<span class="text-danger">文件格式不对</span>');
                                    } else {
                                        file.fileEntryTd.find('td:last').html('<span class="text-danger">上传失败</span>');
                                    }
                                    file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
                                }
                            });

                        $("#advancedDropzone").css({
                            minHeight: 200
                        });

                    });
                    </script>
                    <br>
                    <div class="row">
                        <div class="col-sm-3 text-center">
                            <div id="advancedDropzone" class="droppable-area dz-clickable" style="min-height: 200px;">
                                可以拖入文件
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <table class="table table-bordered table-striped" id="example-dropzone-filetable">
                                <thead>
                                    <tr>
                                        <th width="1%" class="text-center">#</th>
                                        <th width="50%">文件名</th>
                                        <th width="20%">上传进度</th>
                                        <th>文件大小</th>
                                        <th>上传状态</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="5">文件列表</td>
                                    </tr>
                                </tbody>
                            </table>
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
</body>
</html>
