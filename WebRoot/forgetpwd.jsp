<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>忘记密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="新疆大学 监考报名" />
    <meta name="author" content="" />
    <link rel="shortcut icon" href="images/shortcut.ico">
    <link rel="stylesheet" href="css/css.css">
    <link rel="stylesheet" href="css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet" href="css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/xenon-core.css">
    <link rel="stylesheet" href="css/xenon-forms.css">
    <link rel="stylesheet" href="css/xenon-components.css">
    <link rel="stylesheet" href="css/xenon-skins.css">
    <link rel="stylesheet" href="css/custom.css">
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/cookie.js"></script>
    <script src="js/ready.js"></script>
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
                                    <span>所有消息</span>
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
                           
                            
                        </a>
                    </li>
                    <li class="dropdown user-profile">
                        <a href="" data-toggle="dropdown" id="logout">
                            
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">修改密码</h3>
                        </div>
                        <div class="panel-body">
                            <script>
                            function show(temp) {
                                temp.next().css("visibility", "hidden");
                                temp.parent().removeClass("has-error");
                                if (!temp.parent().hasClass("has-success"))
                                    temp.parent().addClass("has-success");
                            }

                            function hidden(temp) {
                                temp.next().css("visibility", "");
                                if (!temp.parent().hasClass("has-error"))
                                    temp.parent().addClass("has-error");
                            }

                            function valno() {
                                var v = event.srcElement.value;
                                var temp = $(event.srcElement.parentElement);
                                if (onlynumber(v)) {
                                    show(temp);
                                } else {
                                    hidden(temp);
                                }
                            }

                            function valnum() {
                                var v = event.srcElement.value;
                                var temp = $(event.srcElement.parentElement);
                                if (number(v)) {
                                    show(temp);
                                } else {
                                    hidden(temp);
                                }
                            }

                            function valname() {
                                var v = event.srcElement.value;
                                var temp = $(event.srcElement.parentElement);
                                if (isname(v)) {
                                    show(temp);
                                } else {
                                    hidden(temp);
                                }
                            }

                            function mysubmit() {
                                var f = $("form");
                                if (f.children().length > f.find(".has-success").length) {
                                    $(f.children().not(f.find(".has-success"))[0]).find("input,select").focus();
                                    return false;
                                } else {
                                    var name = $("#field-name").val();
                                    var no = $("#field-no").val();
                                    var sex = $($("input[type=radio]")[0]).attr("checked") == null ? 1 : 2;
                                    var tel = $("#field - number").val();
                                    var degree = $("#field-degree").val();
                                    var professional = $("#field-professional").val();
                                    var college = $("#field-college").val();

                                    $.ajax({
                                        type: 'POST',
                                        url: "",
                                        data: data,
                                        success: function(data) {
                                            alert(data);
                                        },
                                        dataType: JSON
                                    });
                                }
                            }

                            function myreset() {
                                $.ajax({
                                    type: 'POST',
                                    url: "",
                                    data: data,
                                    success: function(data) {
                                        alert(data);
                                        $("#field-name").val();
                                        $("#field-no").val();
                                        $($("input[type=radio]")[data.sex]).attr("checked", "checked");
                                        $("#field - number").val();
                                        $("#field-degree").val();
                                        $("#field-professional").val();
                                        $("#field-college").val();
                                    },
                                    dataType: JSON
                                });
                            }
                            </script>
                            <script type="text/javascript" >
                              function save(){
                                 var teano=$('#teano').val();
                                 var cardnumber=$('#card-number').val();
                                 var newpwd=$('#newpwd').val();
                                 var conpwd=$('#conpwd').val();
                                 if(teano=="" || cardnumber=="" || newpwd=="" || conpwd==""){
                                  alert('用户信息不能为空');
                                 }
                                 else
                                    if(newpwd!=conpwd){
                                      alert('新密码和确认密码不一致');
                                    }
                                 else{
                                    $.ajax({
                                      url:"Login",
                                      type:"post",
                                      data:{
                                       action:"forgetpwd",
                                       teano:teano,
                                       cardnumber:cardnumber,
                                       newpwd:newpwd,
                                      },
                                      success:function(data){
                                        var item=jQuery.parseJSON(data);
                                        if(item.id==0){
                                        	 alert("原始信息中的银行卡号为空，请联系考试中心人员");
                                        	}
                                       else
                                        if(item.id==2){
                                           alert("工号或者银行卡号有误");
                                        }
                                        else
                                          if(item.id==1)
                                       		 {
                                            alert('修改成功,请返回首页进行登录');
                                            window.location.href="index.jsp";
                                        	}
                                        	
                                      }
                                    });
                                 }
                              }
                           function reset(){
                                   $('#teano').val("");
                                   $('#teano').val("");
                                   $('newpwd').val("");
                                   $('conpwd').val("");
                           }
                           	 window.onunload = function() {   
					               exit();
							}  ;
                            </script>
                            <form role="form" class="form-horizontal">
                                <div class="form-group">
                                 
                                </div>
                                
                                  <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">员工编号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="teano" placeholder="" >
                                    </div>
                                </div>
                           
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1" style="color:#8DC63F">银行卡号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="card-number" placeholder="" >
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
                                        <input type="password" name="conpwd" id="conpwd" class="form-control" placeholder="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="field-1"></label>
                                      <div class="col-sm-2">
                                        <a href="index.jsp"   class="form-control"  placeholder=""  style="text-align: center;">返回登录</a>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="button" value="保存" class="form-control" id="field-submit" placeholder=""  onclick="save()">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="button" value="重置" class="form-control" id="field-reset" placeholder=""  onclick="reset()">
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
    <!-- Bottom Scripts -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/TweenMax.min.js"></script>
    <script src="js/resizeable.js"></script>
    <script src="js/xenon-toggles.js"></script>
    <!-- JavaScripts initializations and stuff -->
    <script src="js/xenon-custom.js"></script>
  </body>
</html>
