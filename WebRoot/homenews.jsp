<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="k"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发布通知</title>
<k:import_js />
<k:import_css />
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
            type: "post",
            url: "Login",
            timeout: 20000,
            cache: false,
            data: {
                action: 'query',
                teano:getCookieValue("username")
            },
            beforeSend: function(XMLHttpRequest) {

            },
            success: function(data, textStatus) {
                if (data != '') {
                    var obj = jQuery.parseJSON(data);
                    var user=getCookieValue("username");
                    $('#username').html(user);                
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
    });
    	 window.onunload = function() {   
               exit();
		};  
    </script>
</head>

<body class="page-body">
	<div class="settings-pane">
		<a href="#" data-toggle="settings-pane" data-animate="true">
			&times; </a>
		<div class="settings-pane-inner">
			<div class="row">
				<div class="col-md-4">
					<div class="user-info">
						<div class="user-image">
							<a href="extra-profile.html"> <img src="images/user-2.png"
								class="img-responsive img-circle" /> </a>
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
				<header class="logo-env"> <!-- logo -->
				<div class="logo">
					<a href="http://www.xju.edu.cn" class="logo-expanded"> <img
						src="images/logo.png" width="80" alt="" /> </a> <a
						href="http://www.xju.edu.cn" class="logo-collapsed"> <img
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
					<li class="active opened active"><a href="userinfo3.jsp">
							<i class="linecons-cog"></i> <span class="title">个人资料</span> </a></li>
					<li><a href="upload.jsp"> <i class="linecons-desktop"></i>
							<span class="title">上传文件管理</span> </a>
						<ul>
							<li><a href="upload.jsp"> <span class="title">上传资源</span>
							</a></li>
							<li><a href="manage.jsp"> <span class="title">管理资源</span>
							</a></li>
						</ul></li>
					<li><a href="addexam.jsp"> <i class="linecons-note"></i> <span
							class="title">添加考试信息</span> </a></li>
					<li><a href="addpaper.jsp"> <i class="linecons-note"></i>
							<span class="title">添加试卷</span> </a></li>
					<li><a href="AddUserInfor.html"> <i class="linecons-note"></i>
							<span class="title">添加人员信息</span> </a></li>
					<li><a href="addteach.html"> <i class="linecons-star"></i>
							<span class="title">教师考试情况</span> </a></li>
						<li class="active opened active">
		                        <a href="editGuide.jsp">
		                            <i class="linecons-star"></i>
		                            <span class="title">修改操作说明</span>
		                        </a>
                   		  </li>
					<li><a href="homenews.jsp"> <i class="linecons-star"></i>
							<span class="title">添加通告</span> </a></li>
				</ul>
			</div>
		</div>
		<div class="main-content">
			<!-- User Info, Notifications and Menu Bar -->
			<nav class="navbar user-info-navbar" role="navigation"> <!-- Left links for user info navbar -->
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
				<li class="dropdown user-profile" style="min-height: 75px;"><a
					href="" data-toggle="dropdown"> <span id="username"></span> <span>
							，欢迎您！ </span> </a></li>
				<li class="dropdown user-profile" style="min-height: 75px;"><a
					href="" data-toggle="dropdown" id="logout"> <span> 退出登录
					</span> </a></li>
			</ul>
			</nav>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">发布通知</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1"
									style="color:#8DC63F;font-size: 18px;">编辑新闻通知：</label>
								<div class="col-sm-4">
									<textarea rows="10" cols="50" class="form-control" id=”news“></textarea>
								</div>
							</div>
						
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1"></label>
								<div class="col-sm-2">
									<input type="button" value="保存" class="form-control"
										id="field-submit" placeholder="" onclick="savenews()">
								</div>
								<div class="col-sm-2">
									<input type="button" value="重置" class="form-control"
										id="field-reset" placeholder="">
								</div>
							</div>
					</div>
				</div>
			</div>
			 <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">已发布的通知</h3>
                </div>
                <div class="panel-body">
                    <script type="text/javascript">
                   $.ajax({
                      url:"HomeNews",
                      type:"post",
                      dataType:"json",
                      data:{
                        action:"queryAll"
                      },
                      success:function(data){
                        var homenews=eval(data);
                         for(var i=0;i<homenews.length;++i){
                           add(homenews[i].homeNewsNo, homenews[i].homeNewsContent, homenews[i].homeNewsPublishTime);
                         }
                      }
                     });
                  
                    //报名
                    function edit() {
                        var src = $(event.srcElement);
                        if (src.html() == "编辑") {
                            src.html("确定");
                            var t = $("<input type=\"text\" style=\"width:100%;\">");
                            src.parents("tr").find("td").first().html(t.clone().val(src.parents("tr").find("td").first().html()));
                          //  src.parents("tr").find("td").first().next().html(t.clone().val(src.parents("tr").find("td").first().next().html()));
                            return;
                        } else {
                            src.html("编辑");
                        }
                        var id = $(event.srcElement).parents("tr").find("input[type=hidden]").val();
                        var n = src.parents("tr").find("input[type=text]").first().val();
                        src.parents("tr").find("td").first().html(n);
                    //    src.parents("tr").find("td").first().next().html(t);
                        
                        $.ajax({
                            url:"HomeNews",
                            dataType:"json",
                            type:"post",
                            data:{
                               action:"editNews",
                               id:id,
                               content:n
                            },
                            success:function(data){
                              if(data==1){
                                window.location.reload();
                              }
                            }
                        });
                    }

                    function del() {
                        var t = $(event.srcElement).parents("tr").first();
                        var id = t.find("input[type=hidden]").first().val();
                        $.post("HomeNews",
                        {
                          action:"deleteNews",
                          id:id
                        },
                        function(data){
                            if(data==1){
                               window.location.reload();
                            }
                        });

                    }
                    //增加行
                    function add(id,content,publishTime) {
                        var n = $("#main-table").find("tbody").find("tr").length % 2;
                        var str = $("<tr role=\"row\" class=\"\"><td>1002</td><td>Social and human service</td><td><a href=\"javascript:void(0);\" class=\"btn btn-secondary btn-sm btn-icon icon-left\" onclick=\"edit()\">" +
                            "编辑</a><a onclick=\"del()\" class=\"btn btn-danger btn-sm btn-icon icon-left\">" +
                            "删除</a><input type=\"hidden\"></td></tr>");
                        if (n == 0) {
                            str.addClass("odd");
                        } else {
                            str.addClass("even");
                        }
                        var t = str.find("td");
                        $(t[0]).html(content);
                        publishTime=publishTime.substring(0,publishTime.length-2);
                        $(t[1]).html(publishTime);
                        str.find("input[type=hidden]").val(id);
                        $("#main-table").find("tbody").append(str).css("text-align","center");
                    }
                    </script>
                    <div id="example-2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <table class="table table-bordered table-striped dataTable no-footer" id="main-table" role="grid" aria-describedby="example-2_info">
                            <thead>
                                <tr role="row">
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 449px; text-align: center;">通知内容</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 301px; text-align: center;">上传时间</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 242px; text-align: center;">操作</th>
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
	<script type="text/javascript">
	     var savenews=function(){
	         var newscontent=$("textarea").val();
	         if(newscontent=="" || newscontent==null){
	          alert("通知内容不能为空");
	          return;
	         }
	         $.ajax({
	          url:"HomeNews",
	          type:"post",
	          dataType:"json",
	          data:{
	           action:"addnews",
	           newscontent:newscontent
	          },
	          success:function(data){
	             if(data==1){
	              alert('添加成功');
	              window.location.reload();
	             }
	          }
	         });
	     };
	     $("#field-reset").click(function(){
	       $("textarea").val(" ");
	     });
	</script>
</body>
</html>
