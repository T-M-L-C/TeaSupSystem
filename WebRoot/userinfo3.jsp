<%@page import="com.backbone.dao.implement.TeaOfTraImple"%>
<%@page import="com.backbone.dao.implement.ExamStatusImple"%>
<%@page import="com.backbone.dao.implement.ReserveListDaoImple"%>
<%@page import="com.backbone.dao.implement.ExamSubjectImple"%>
<%@page import="com.backbone.entity.po.TraRes"%>
<%@page import="com.backbone.dao.implement.TraResImple"%>
<%@page import="com.backbone.dao.implement.ExamOfTraImple"%>
<%@page import="com.backbone.dao.implement.TeaOfExamImpl"%>
<%@page import="com.backbone.dao.implement.TeacherImple"%>
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
    
    <title>个人信息</title>
    <k:import_js/>
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
                    loginuser = user;
                    $('#field-name').val(obj.teaname);
                    $('#field-no').val(obj.teano);
                    $('#field-number').val(obj.teatel);
                    if (obj.teasex == 0) {
                        $('input[name=radio-sex][value=0]').attr('checked', 'checked');
                    } else {
                        $('input[name=radio-sex][value=1]').attr('checked', 'checked');
                    }

                    $('#card-number').val(obj.cashcardid);
                    switch (obj.teaaddress) {
                        case '本部':
                            $("#address ").find("option[value=1]").attr("selected", 'selected');
                            break;
                        case '本部附近':
                            $("#address ").find("option[value=2]").attr("selected", 'selected');
                            break;
                        case '南区':
                            $("#address ").find("option[value=3]").attr("selected", 'selected');
                            break;
                        case '南区附近':
                            $("#address").find("option[value=4]").attr("selected", 'selected');
                            break;
                        case '北区':
                            $("#address ").find("option[value=5]").attr("selected", 'selected');
                            break;
                        case '北区附近':
                            $("#address ").find("option[value=6]").attr("selected", 'selected');
                            break;
                        default:
                            break;
                    }
                    $('#filed-board').val(obj.subordunit);
                }
            },
            complete: function(XMLHttpRequest, textStatus) {

            },
            error: function() {
                alert("您还未登录，登录后才可以进行相应操作");
            }
        });
        $('#field-submit').click(function() {
            $.ajax({
                type: "post",
                url: "Login",
                cache: false,
                dataType: 'json',
                data: {
                    action: 'corpwd',
                    oldpwd: $('#oldpwd').val(),
                    newpwd: $('#newpwd').val(),
                    conpwd: $('#conpwd').val(),
                    teaname:$('#field-name').val(),
                    teatel:$('#field-number').val(),
                    cardnumber:$('#card-number').val(),
                    teasex:$('input[name="radio-sex"]:checked').val(),
                    teaaddress:$('#address option:selected').val(),
                    subordunit:$('#filed-board').val(),
                    teano:getCookieValue("username")
                },
                success: function(data) {
                    var item = eval(data);
                    if (item.state == 'successful') {
                        $('span[id=newpasswd]').html('');
                        alert('密码修改成功');
                    } else
                    if (item.state == 'failure') {
                        $('span[id=newpasswd]').html('原始密码不正确。请重新输入！');
                    } else {
                        $('span[id=newpasswd]').html('');
                        alert(item.state);
                    }
                },
                error: function() {
                  
                }
            });
        });
        $('#oldpwd').click(function() {
            $('span[id=newpasswd]').html('不修改密码，请勿填写。');
        });
        $('#newpwd').click(function() {
            var oldpwd = $('#oldpwd').val();
            if (oldpwd != "") {
                $('span[id=newpasswd]').html('');
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
                    <li class="active opened active">
                        <a href="userinfo3.jsp">
                            <i class="linecons-cog"></i>
                            <span class="title">个人资料</span>
                        </a>
                    </li>
                    <li>
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
                            <li>
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
                    <li>
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
                            <span id="username"></span>
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
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">个人信息</h3>
                             <input type="button" value="一键清空所有考试信息"  onclick="deleteAll()" class="btn btn-danger"  style="display: block;float: right;">
                        </div>
                        
                        <div class="panel-body">
                            <script>
                            function deleteAll(){
                                                var username=getCookieValue("username");
                                if(username==""){
                                  alert("你还未登录，不具备权限");
                                  window.location.href="index.jsp";
                                  return;
                                }
                                if(!confirm("确定清空所有考试信息吗？"))
                                return;
                                $.ajax({
                                  url:"Login",
                                  type:"post",
                                  dataType:"json",
                                  data:{
                                    action:"deleteAll"
                                  },
                                  success:function(data){
                                    var json=eval(data);
                                    if(json.id==1){
                                     alert("成功");
                                     window.location.reload();
                                    }
                                  }
                                });
                                
                            }
                
                              </script>
                          
                            <form role="form" class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F"> 姓名：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="field-name"  placeholder="">
                                    </div>
                                    <div class="col-sm-2" style="visibility:hidden;">
                                        <input type="text" value="姓名有误" class="form-control"  placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">员工编号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="field-no" placeholder="" readonly="readonly">
                                    </div>
                                   
                                </div>
                                <div class="form-group has-success">
                                    <label class="col-sm-4 control-label">性别：</label>
                                    <div class="col-sm-4">
                                        <p>
                                            <label class="radio-inline">
                                                <input type="radio" name="radio-sex" checked="checked" value=0> 男
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="radio-sex" value=1> 女
                                            </label>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="field-number" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">银行卡号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="card-number" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">家庭住址：</label>
                                    <div class="col-sm-4">
                                        <select id="address" class="form-control">
                                            <option value=1>本部</option>
                                            <option value=2>本部附近</option>
                                            <option value=3>南区</option>
                                            <option value=4>南区附近</option>
                                            <option value=5>北区</option>
                                            <option value=6>北区附近</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">所在学院：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="filed-board" placeholder="" >
                                    </div>
                                  
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">原始密码：</label>
                                    <div class="col-sm-4">
                                        <span id="newpasswd" style=" color: red;"></span>
                                        <input type="password" name="oldpwd" id="oldpwd" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">新密码：</label>
                                    <div class="col-sm-4">
                                        <input type="password" name="newpwd" id="newpwd" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">确认密码：</label>
                                    <div class="col-sm-4">
                                        <input type="password" name="conpwd" id="conpwd" class="form-control" placeholder="" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1"></label>
                                    <div class="col-sm-2">
                                        <input type="button" value="保存" class="form-control" id="field-submit" placeholder="">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="button" value="添加人员信息"  class="form-control" placeholder=""  id="reset" />
                                    </div>
                                </div>
                                <!--   <div class="form-group-separator"  id="newpwd" style="color: red;">
                                      提示
                                    </div> -->
                            </form>
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
