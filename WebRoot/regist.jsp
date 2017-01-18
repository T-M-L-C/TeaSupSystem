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
    
    <title>报名</title>
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
    islogin(0);
    $(document).ready(function() {
        //是否登陆，权限

        //获取信息
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
        //初始化页码和第一页
        $.ajax({
            url: "Subject?action=querycounts",
            success: function(data) //10单页内容条数
                {
                    function funcdata(n) {
                        this.action = "query";
                        this.page = n;
                        this.count = 10;
                        this.teano = getCookieValue("username");
                    }
                    page($('#pagenumber'), Math.ceil(data / 10), 10, 'Subject', funcdata, dataadd);
                }
        });
        //通过查看信息是否完整来确认改老师是否有报名的权限
        $.ajax({
            url: "Login",
            type: "post",
            data: {
                action: "validinfor",
                username: getCookieValue("username")
            },
            success: function(data) {
                var json = jQuery.parseJSON(data);
                if (json.id == 0) {
                    alert("请您先把个人信息填写完整,再选择继续报名");
                    // location.replace("userinfo.html");
                    window.location.href = "userinfo.jsp";
                }
            }
        });
        $('#logout').click(function() {
            exit();
        });

    });
    //报名
    function regedit(item) {
        // alert('该系统尚未开放报名功能，请您直接进入学习页面参与在线学习');
           $.ajax({
             url:"Subject",
             type:"post",
             dataType:"json",
             data:{
              action:"getRegistTime",
              subno:item 
             },
             success:function(data){
                             var exam=eval(data);
                             var starttime=exam.sub_registstart;
                             var endtime=exam.sub_registend;
                             var date=exam.date;       
                             starttime=starttime.substring(0,starttime.length-2).replace(/-/g,"/");
                             endtime=endtime.substring(0,endtime.length-2).replace(/-/g,"/");
                             date=date.replace(/-/g,"/");
                             var resultstart= compare(starttime, date);
                             var resultend=compare(endtime, date);
                             if(resultstart>0){
                               alert('正式开始报名时间是:'+starttime+" "+"请你耐心等待");
                             }
                             else
                              if(resultend<0){
                                    alert('抱歉，现在已超过规定报名时间');
                                 }
                                 else{
                                           $.ajax({
								                type: "post",
								                url: "Subject",
								                timeout: 20000,
								                cache: false,
								                data: {
								                    action: 'regist',
								                    username: getCookieValue("username"),
								                    subno: item
								                },
								                beforeSend: function(XMLHttpRequest) {
								                  
								                },
								                success: function(data, textStatus) {
								                    var item = jQuery.parseJSON(data);
								                    if (item.id == 1) {
								                     
								                    } else
								                    if (item.id == 2) {
								                        alert('报名成功');
								                        window.location.reload();
								                    } else
								                    if (item.id == 3) {
								
								                    } else
								                    //没有添加培训资源
								                    if (item.id == 4) {
								                        //alert('该科目暂不提供报名');
								                    }
								                },
								                complete: function(XMLHttpRequest, textStatus) {
								
								                },
								                error: function() {
								                  window.location.reload();
								                }
								            });
                                 }
               }
           });
    }
    	 window.onunload = function() {   
               exit();
		};
		
		    function compare(start ,end){
		             
                      var  a=new Date(start);
                       var  b=new Date(end);
                      return a.valueOf()-b.valueOf();
              }
    </script>
  </head>
  
<body class="page-body">
    <div class="settings-pane">
        <a href="#" data-toggle="settings-pane" data-animate="true">
            &times;
        </a>
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
                            <span class="title">个人资料</span>
                        </a>
                    </li>
                    <li class="active opened active">
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
                    <h3 class="panel-title">最近要举行的大型考试</h3>
                </div>
                <div class="panel-body">
                    <script type="text/javascript">
                    //增加行
                    function add(name, no, time, registstate) {
                        var n = $("#main-table").find("tbody").find("tr").length % 2;
                        var str = $("<tr role=\"row\" class=\"\"><td>mhk</td><td>1002</td><td>Social and human service</td><td><a href=\"javascript:regedit('" + no + "');\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">" +
                            "报名</a></td></tr>");
                        if (n == 0) {
                            str.addClass("odd");
                        } else {
                            str.addClass("even");
                        }
                        var t = str.find("td");
                        $(t[0]).html(name);
                        $(t[1]).html(no);
                        time=time.substring(0,time.length-2);
                        $(t[2]).html(time);
                        if (registstate == "报名成功") {
                            $(t[3]).html(registstate).css("color", "red");
                        } else
                        if (registstate == "报名被拒绝") {
                             $(t[3]).html("因报名人数已满您的" + name + "报名已被拒绝").css("color", "green");
                        }
                        $("#main-table").find("tbody").append(str).css("font-family","微软雅黑");
                    }
					
                    function dataadd(data) {
                       var result = JSON.parse(data);
                    /*    var arr=new Array();
                       for(var obj in result){
                           arr.push(new registInfor(result[obj].sub_no,result[obj].sub_name,result[obj].sub_time,result[obj].registstate));
                       } */
                      
                        $('.middle-align').html('');
                        if (result == '空值') {
                            alert('最近没有可报名的考试！');
                        } else {
                        // 在显示报名科目之前，看是否有人员限制
                           var sub_no,sub_time,registate,sub_name;
                                  for(var i=0;i<result.length;++i){
                                  add(result[i].sub_name,result[i].sub_no,result[i].sub_time,result[i].registstate);
                                               /* $.ajax({
	                                                   url:"Alternative",
	                                                   type:"post",
	                                                   dataType:"json",
	                                                   data:{
	                                                   action:"queryUnique",
	                                                   teano:getCookieValue("username"),
	                                                   subno:result[i].sub_no
	                                               },
	                                               success:function(flag){
	                                                    var item=eval(flag);
	                                       
	                                                      if(item.id==1 && item.subno==arr[i].sub_no){
                                                               add(arr[i].sub_name,arr[i].sub_no,arr[i].sub_time,arr[i].registstate);
	                                                    }
	                                                   
	                                                     //alert(result[i].sub_name+","+result[i].sub_no+","+result[i].sub_time+","+ result[i].registstate);
	                                                      //add(sub_no,sub_name,sub_time,registate);
	                                                     // add(item.sub_name,item.subno,item.subtime,item.registate);
                                                   }
                                                });*/
                                             
                                             
                                          }
                                          
                                       }
                                    }
                      function registInfor(sub_no,sub_name,sub_time,registate){
                         this.sub_no=sub_no;
                         this.sub_name=sub_name;
                         this.sub_time=sub_time;
                         this.registate=registate;
                         return this;
                      };

                    </script>
                    <div id="example-2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                        <table class="table table-bordered table-striped dataTable no-footer" id="main-table" role="grid" aria-describedby="example-2_info">
                            <thead>
                                <tr role="row">
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 249px;">考试名称</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 148px;" id="subno">考试代码</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 301px;">考试时间</th>
                                    <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 242px;">操作</th>
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
                        &copy; 2016
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
