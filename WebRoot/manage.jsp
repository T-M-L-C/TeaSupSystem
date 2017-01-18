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
    
    <title>管理资源</title>
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
	
  <script type="text/javascript">
    $(document).ready(function() {
        islogin(3);
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

        $.ajax({
            url: "tra?method=getcounts",
            success: function(data) {
                function funcdata(n) {
                    this.pagecount = n;
                    this.pagecountmax = 10;
                    this.param = "all";
                    this.username = getCookieValue("username");
                }
                page($('#pagenumber'), Math.ceil(data / 10), 10, 'tra?method=getcontents', funcdata, dataadd);
            }
        });

        $('#logout').click(function() {
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
                            <li>
                                <a href="upload.jsp">
                                    <span class="title">上传资源</span>
                                </a>
                            </li>
                            <li class="active opened active">
                                <a href="manage.jsp">
                                    <span class="title">管理资源</span>
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
                        <a href="AddUserInfor.html">
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
                    <li class="active opened active">
		                        <a href="editGuide.jsp">
		                            <i class="linecons-star"></i>
		                            <span class="title">修改操作说明</span>
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
                    <h3 class="panel-title">已上传的资源</h3>
                </div>
                <div class="panel-body">
                    <script type="text/javascript">

                    function dataadd(data) {
                        data = JSON.parse(data);
                        for (var i = 0; i < data.length; i++) {
                            add(data[i].name, data[i].mintime, data[i].time, data[i].no, data[i].path, data[i].type, data[i].len,data[i].tri_CanDownLoad,data[i].tri_MustStudy);
                        }
                    }
                    //报名
                    function edit() {
                        var src = $(event.srcElement);
                        if (src.html() == "编辑") {
                            src.html("确定");
                            var t = $("<input type=\"text\" style=\"width:100%;\">");
                            var select = $('<select class="form-control"><option value="1">是</option><option value="0">否</option></select>');
                            var now = src.parents("tr").find("td").first()
                            now.html(t.clone().val(now.find("a").html()));
                            now = now.next();
                            now.html(t.clone().val(now.html()));
                            now = now.next();
                            now = now.next();
                            now.html(select.clone().val(now.html()=='是'?1:0));
                            now = now.next();
                            now.html(select.clone().val(now.html()=='是'?1:0));
                            return;
                        } else {
                            src.html("编辑");
                        }


                        var id = $(event.srcElement).parents("tr").find("input[type=hidden]").first().val();

                        var a = $("<a href=\"javascript:void(0);\" onclick=\"gowatch()\"></a>");
                        var n = src.parents("tr").find("input[type=text]").first().val();
                        var t = src.parents("tr").find("input[type=text]").last().val();
                        var now =src.parents("tr").find("td").first();
                        now.html(a.clone().html(n));
                        now = now.next();
                        now.html(t);
                        now = now.next().next();
                        var download = now.find('select').val();
                        now.html(now.find('option[value='+download+']').html());
                        now = now.next();
                        var study = now.find('select').val();
                        now.html(now.find('option[value='+study+']').html());

                        $.ajax({
                            type: 'POST',
                            url: "tra?method=edit",
                            data: {
                                "id": id,
                                "name": n,
                                "time": t,
                                'download':download,
                                'study':study
                            },
                            success: function(res) {
                            	if(res == '1')
                            	{
                                	alert("成功");
                                }
                                else
                                {
                                	alert("失败");
                                }
                            }
                        });
                    }

                    function gowatch() {
                        var src = $(event.srcElement).parents("tr");
                        var t = "?" + src.find("input[type=hidden]").last().prev().prev().val() +
                            "=" + src.find("input[type=hidden]").last().prev().val() +
                            "&" + src.find("td").first().find("a").html() + "=" + src.find("td").first().next().html() +
                            "&" + "n" + "=" + src.find("input[type=hidden]").last().val();
                            //alert(src.find("td").first().next().html());
                           
                            
                        location.href = "watch.jsp" + t;
                    }

                    function del() {
                        var t = $(event.srcElement).parents("tr").first();
                        var id = t.find("input[type=hidden]").first().val();

                        $.ajax({
                            url: "tra?method=del",
                            data: {
                                "id": id
                            },
                            success: function(data) {
                                t.remove();
                                alert("删除成功");
                            },
                            error: function() {
                                alert("删除失败");
                            }
                        });

                    }
                    //增加行
                    function add(name, mintime, time, id, url, format, pages,download,muststudy) {
                        var n = $("#main-table").find("tbody").find("tr").length % 2;
                        var str = $("<tr role=\"row\" class=\"\"><td><a href=\"javascript:void(0);\" onclick=\"gowatch()\"></a></td><td>1002</td><td>Social and human service</td><td></td><td></td><td><a href=\"javascript:void(0);\" class=\"btn btn-secondary btn-sm btn-icon icon-left\" onclick=\"edit()\">" +
                            "编辑</a><a onclick=\"del()\" class=\"btn btn-danger btn-sm btn-icon icon-left\">" +
                            "删除</a><input type=\"hidden\"><input type=\"hidden\"><input type=\"hidden\"><input type=\"hidden\"></td></tr>");
                        if (n == 0) {
                            str.addClass("odd");
                        } else {
                            str.addClass("even");
                        }
                        var t = str.find("td");
                        $(t[0]).find("a").html(name);
                        $(t[1]).html(mintime);
                        time=time.substring(0,time.length-2);
                        $(t[2]).html(time);
                        $(t[3]).html(download==1?"是":"否");
                        $(t[4]).html(muststudy==1?"是":"否");
                        $(str).find("input[type=hidden]").first().val(id).next().val(format).next().val(url).next().val(pages);
                        $("#main-table").find("tbody").append(str).css("font-family","微软雅黑");
                    }
                    </script>
                    <div id="example-2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <table class="table table-bordered table-striped dataTable no-footer" id="main-table" role="grid" aria-describedby="example-2_info">
                            <thead>
                                <tr role="row">
                                    <th class="sorting"  rowspan="1" colspan="1" >资源名称</th>
                                    <th class="sorting"  rowspan="1" colspan="1" >最少观看时间</th>
                                    <th class="sorting"  rowspan="1" colspan="1" >上传时间</th>
                                    <th class="sorting"  rowspan="1" colspan="1" >是否可下载</th>
                                    <th class="sorting"  rowspan="1" colspan="1" >是否必学</th>
                                    <th class="sorting"  rowspan="1" colspan="1" style="width: 150px;">操作</th>
                                </tr>
                            </thead>
                            <tbody class="middle-align"></tbody>
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
