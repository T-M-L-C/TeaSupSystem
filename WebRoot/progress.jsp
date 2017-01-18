<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="k"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学习进度</title>
 <script src="js/jquery-1.11.1.min.js"></script>
<script src="js/page.js"></script>
<script src="js/cookie.js"></script>
<script src="js/ready.js"></script>
<k:import_css />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
 
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
        
       
     /*   $.ajax({
            type: "get",
            url: "Login",
            data: {
                "action": "canexam",
                "username": getCookieValue("username")
            },
            success: function(data) {
                if (data == "false") {
                    
                    $("#exam").css("display", "none");
                }
            }
        });*/
        $(function() {
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
					<li><a href="userinfo.jsp"> <i class="linecons-cog"></i> <span
							class="title">个人资料</span> </a>
					</li>
					<li><a href="regist.jsp"> <i class="linecons-desktop"></i>
							<span class="title">报名监考</span> </a>
					</li>
					<li><a href="status.jsp"> <i class="linecons-note"></i> <span
							class="title">报名状态</span> </a>
					</li>
					<li class="active opened active"><a href="progress.jsp"> <i
							class="linecons-star"></i> <span class="title">学习进度</span> </a>
					</li>
					<li><a href="examplace.jsp"> <i class="linecons-mail"></i>
							<span class="title">查看考场分布</span> </a>
					</li>
					<li><a href="guide.jsp"> <i class="linecons-star"></i> <span
							class="title">操作说明</span> </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="main-content">
			<!-- User Info, Notifications and Menu Bar -->
			<nav class="navbar user-info-navbar" role="navigation"> <!-- Left links for user info navbar -->
			<ul class="user-info-menu left-links list-inline list-unstyled">
				<li class="hidden-sm hidden-xs"><a href="#"
					data-toggle="sidebar"> <i class="fa-bars"></i> </a>
				</li>
				<li class="dropdown hover-line"><a href="#"
					data-toggle="dropdown"> <i class="fa-envelope-o"></i> <span
						class="badge badge-green">1</span> </a>
					<ul class="dropdown-menu messages">
						<li>
							<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
								<li class="active">
									<!-- "active" class means message is unread --> <a href="#">
										<span class="line"> <strong>使用帮助</strong> </span> <span
										class="line desc small"> 请仔细阅读，会对您有帮助的！ </span> </a>
								</li>
							</ul>
						</li>
						<li class="external"><a href="blank-sidebar.html"> <span>All
									Messages</span> <i class="fa-link-ext"></i> </a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- Right links for user info navbar -->
			<ul class="user-info-menu right-links list-inline list-unstyled">
				<li class="dropdown user-profile"><a href=""
					data-toggle="dropdown"> <span id="username"> </span> <span>
							，欢迎您！ </span> </a>
				</li>
				<li class="dropdown user-profile"><a href=""
					data-toggle="dropdown" id="logout"> <span> 退出登录 </span> </a>
				</li>
			</ul>
			</nav>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">课程</h3>
					<div>

						&nbsp;&nbsp;&nbsp; <select class="form-control" id="exams"
							style="width: 50%;position: absolute;left:190px;top:-5px;"
							onchange="changeSubject()">
						</select>
						<script type="text/javascript">
								     var changeSubject=function(){
								         getTraRes($("#exams").val());
								         queryexam($("#exams").val());
								     };
								</script>
					</div>
					<div style="float:right;font-family: 微软雅黑" id="exam">
						<a href="javascript:void(0)" ; style="font-size: 24px;"
							onclick="ParticipateExam()"
							; class="btn btn-danger btn-sm btn-icon icon-left"; >参加考试</a>
					</div>
				</div>
				<div class="panel-body">
					<script type="text/javascript">
					//获取上传的考试科目信息
			       $.ajax({
			        url:"Subject",
			        data:{"action":"getexams"},
			        type:"POST",
			        success:function(data)
			        {
			            var t = JSON.parse(data);
			            var ops = $("#exams");
			            ops.html("");
			            for (var i = 0; i < t.length; i++) {
			               /*  if(i==0)
			                 ops.append("<option  value=\""+t[i].sub_no+"\"  selected=\""+'selected'+"\">"+t[i].sub_name+"</option>") ;
			                 else */
			                ops.append("<option value=\""+t[i].sub_no+"\">"+t[i].sub_name+"</option>");
			            };
			            var selectedValue=ops.val();
			             getTraRes(selectedValue);
			             queryexam(selectedValue);
			          }
           });
           
					function getTraRes(selectedValue){
						   $.ajax({
				            url: "tra?method=getcounts",
				            data: {
				                "username": getCookieValue("username"),
				                 subno:selectedValue
				            },
				            success: function(data) {
				                function funcdata(n) {
				                    this.action = 'canexam';
				                    this.username=getCookieValue("username");
				                    this.subno=selectedValue;
				                    this.count = 10;
				                    this.page = n;
				                }
				                page($('#pagenumber'), Math.ceil(data / 10), 10, 'Login', funcdata, dataadd);
				            }
				        });
		}
                  function ParticipateExam() {
                        var name=$("#exams").find("option:selected").text();
                        var value=$("#exams").val();
                        
                        if (name != null && name != "") {
                            $.ajax({
                                url: "Login",
                                type: "post",
                                data: {
                                    action: "participateexam",
                                    teano: getCookieValue("username"),
                                    subname: name
                                },
                                success: function(data, textStatus) {
                                    var json = jQuery.parseJSON(data);
                                    if (json.state == "空值") {
                                        alert('您选择的考试科目不存在，请您重新选择');
                                    } else
                                    if (json.state == '完成') {
	                                                 $.ajax({
	                                                   url:"Subject",
	                                                   type:"post",
	                                                   dataType:"json",
	                                                   data:{
	                                                    action:"getExamPassTime",
	                                                    subno:value
	                                                   },
	                                                   success:function(data){
	                                                    	 var exam=eval(data);
	                                                         var starttime=exam.sub_examstart;
								                             var endtime=exam.sub_examend;
								                             var date=exam.date;
								                             starttime=starttime.substring(0,starttime.length-2).replace(/-/g,"/");
								                             endtime=endtime.substring(0,endtime.length-2).replace(/-/g,"/");
								                             date=date.replace(/-/g,"/");
								                             var resultstart= compare(starttime, date);
								                             var resultend=compare(endtime, date);
								                             if(resultstart>0){
								                               alert('正式开始考试时间是:'+starttime+" "+"请你耐心等待");
								                             }
								                             else
								                              if(resultend<0){
								                                     alert('抱歉，现在已超过规定考试时间');
									                                           }
									                                             else{
									                                              window.location.href = "exam.jsp?subno=" + value;
									                                             }
									                                    }
								                           });
								                                         
		                                    } else if(json.state=='未完成'){
		                                             alert('您的学习还未完成，请您完成必学内容后再参加考试');
		                                     }
                                }
                            });
                        }
                    }

      
    			function dataadd(data) {
                        $('.middle-align').html('');
                        var json = jQuery.parseJSON(data);
                        for(var i=0;i<json.length;i++){
                          if (json[i].watchtime == 0) {
                                add(json[i].tri_name, json[i].tri_pagetime, json[i].tri_time, json[i].tri_no, json[i].tri_newpath, json[i].tri_type, json[i].tri_countpage,json[i].tri_muststudy,json[i].tri_candownload, "未学习",json[i].tri_path);
                            } else
                           if (json[i].watchtime == 1) {
                                if (json[i].exampass == 0) {
                                    add(json[i].tri_name, json[i].tri_pagetime, json[i].tri_time, json[i].tri_no, json[i].tri_newpath, json[i].tri_type, json[i].tri_countpage,json[i].tri_muststudy,json[i].tri_candownload, "已学习但未通过考试",json[i].tri_path);
                                } else {
                                    add(json[i].tri_name, json[i].tri_pagetime, json[i].tri_time, json[i].tri_no, json[i].tri_newpath, json[i].tri_type, json[i].tri_countpage, json[i].tri_muststudy,json[i].tri_candownload,"已通过考试",json[i].tri_path);
                                }
                            }
                        }
                          
                     }
         

                    var schoolstate;
                  /*  $(function(){
                        $.ajax({
                        url: "Login",
                        type: "post",
                        data: {
                            action: 'validstatus',
                            teano: getCookieValue("username")
                        },
                        success: function(data, textStatus) {
                      
                            if (data == null) {
                                schoolstate = data;
                            } else {
                                schoolstate = (eval(data)).id;
                            }
                        },
                        dataType: "json"
                    });
                       
                    });*/
                   
                    function gowatch(filename) {
                            var select=$('#exams').val();
                            var obj = $(event.srcElement);
                            $.ajax({
                                url: "Login",
                                type: "post",
                                data: {
                                    action: 'validsubno',
                                    teano: getCookieValue("username"),
                                    fname: filename,
                                    selectValue:select
                                },
                                success: function(data) {
                                    var item=jQuery.parseJSON(data);
                                    schoolstate=item.id;
                                    JudgeWatch(schoolstate,filename,obj);
                                }
                            });
                    }
                    
                    function JudgeWatch(schoolstate,filename,obj){
                     var subjectValue=$("#exams").val();
                       $.ajax({
                         url:"Subject",
                         type:"post",
                         data:{
                           action:"judgestudy",
                           subno:subjectValue
                         },
                         dataType:"json",
                         success:  function(data){
                             var exam=eval(data);
                             var starttime=exam.sub_studystart;
                             var endtime=exam.sub_studyend;
                             var date=exam.date;
                             starttime=starttime.substring(0,starttime.length-2).replace(/-/g,"/");
                             endtime=endtime.substring(0,endtime.length-2).replace(/-/g,"/");
                             date=date.replace(/-/g,"/");
                             var resultstart= compare(starttime, date);
                             var resultend=compare(endtime, date);
                             if(resultstart>0){
                               alert('正式开始学习时间是:'+starttime+" "+"请你耐心等待");
                             }
                             else
                              if(resultend<0){
                                    alert('抱歉，现在已超过规定学习时间');
                              }else{
			                            if(schoolstate==0){
			                           //格式=路径&名字=已学习时间&要求时间=id&ppt页数
			                         var src = obj.parents("tr");
			                     		   name = src.find("a").first().html();
			                              src = src.find("input[type=hidden]");
			                            var t = "?" + $(src[0]).val() +
			                            "=" + $(src[1]).val() +
			                            "&" + name + "=" + $(src[2]).val() +
			                            "&" + $(src[3]).val() + "=" + $(src[4]).val() +"&"+$(src[5]).val()
			                            ;
			                            //alert($(src[0]).val()+","+$(src[1]).val()+","+$(src[2]).val()+","+$(src[3]).val()+","+$(src[4]).val()+","+$(src[5]).val());
			                         window.location.href = "study.jsp" + t + "&fname=" + filename;
			                        }else
			                          if(schoolstate==1){
			                            	alert('您该科目的报名正在审核中，审核通过后才可参与培训学习');
			                        }      
                              }
                           
                     
                          }
                       });
                        
                    }
                    //com.jacob.com.ComFailException: Invoke of: SaveCopyAs (Office版本的问题)
                    function compare(start ,end){
                        var  a=new Date(start);
                        var  b=new Date(end);
                        return a.valueOf()-b.valueOf();
                    }
                  var reload = true;
                    function reload() {
                        if (reload) {
                            window.location.reload();
                            reload = false;
                        }
                    }
                    //增加行
                    function add(name, mintime, time, id, url, format, pages, tri_muststudy,tri_candownload,learnstate,path) {
                        var n = $("#main-table").find("tbody").find("tr").length % 2;
                        var str = $("<tr role=\"row\" class=\"\"><td><a href=\"javascript:void(0);\" onclick=\"gowatch('" + name + "')\"></a></td><td></td><td></td><td></td>" +
                            "<input type=\"hidden\" /><input type=\"hidden\" /><input type=\"hidden\" /><input type=\"hidden\"  /><input type=\"hidden\" /><input type=\"hidden\" /></tr>");
                        if (n == 0) {
                            str.addClass("odd");
                        } else {
                            str.addClass("even");
                        }
                        var t = str.find("td");

                        $(t[0]).find("a").html(name);
                        mintime = parseInt(mintime) * 60;
                        if(tri_muststudy==0){
                         $(t[1]).html("");
                         $(t[3]).html("选学");
                        }else if(tri_muststudy==1){
                            $(t[1]).html(learnstate);
                            $(t[3]).html("必学").css('color','red');
                        }
                        
                        if(tri_candownload==0){
                        	$(t[2]).html("");
                        }
                        else
                        if(tri_candownload==1)
                    		 {
                         		$(t[2]).html("<a href=\"" + path + "\">下载</a>").css("text-decoration","none");
                       		 }
                         $(str).find("input[type=hidden]").first().val(format).next().val(url).next().val(mintime).next().val(mintime).next().val(id).next().val(pages);
                      /*    var number=$(str).find("input[type=hidden]").length;
                         alert(number); */
                         $("#main-table").find("tbody").append(str).css("font-family","微软雅黑");
                         
                    }
                     
                    function add1(name, finish, status) {
                        var n = $("#main-table").find("tbody").find("tr").length%2;
                        var str = $("<tr role=\"row\" class=\"\"><td>mhk</td><td>1002</td><td>Social and human service</td>" +
                            "</tr>");
                        if (n == 0) {
                            str.addClass("odd");
                        } else {
                            str.addClass("even");
                        }
                        var t = str.find("td");
                        $(t[0]).html(name);
                        $(t[1]).html(finish);
                        $(t[2]).html(status);
                        $("#main-table").find("tbody").append(str);
                     
                    }
                    function queryexam(subno){
                        $.ajax({
						      url:"tra",
						      type:"post",
						      data:{
						        method:"studypasstime",
						        teano:getCookieValue("username"),
						        subno:subno
						      },
						      success:function(data){
						        
						      }
						    },"json");
                    }
                    </script>
					<div id="example-2_wrapper"
						class="dataTables_wrapper form-inline dt-bootstrap no-footer">
						<table
							class="table table-bordered table-striped dataTable no-footer"
							id="main-table" role="grid" aria-describedby="example-2_info">
							<thead>
								<tr role="row">
									<th class="sorting" aria-controls="example-2" rowspan="1"
										colspan="1" style="width: 20%;">课程名称</th>
									<th class="sorting" aria-controls="example-2" rowspan="1"
										colspan="1" style="width: 20%;">学习进度</th>
									<!-- <th class="sorting" aria-controls="example-2" rowspan="1" colspan="1" style="width: 30%;">考场安排</th> -->
									<th class="sorting" aria-controls="example-2" rowspan="1"
										colspan="1" style="width: 20%;">下载</th>
									<th class="sorting" aria-controls="example-2" rowspan="1"
										colspan="1" style="width: 20%;">选学/必学</th>

								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div class="row">
							<div class="col-xs-6">
								<div class="dataTables_info" id="example-2_info" role="status"
									aria-live="polite"></div>
							</div>
							<div class="col-xs-6">
								<div class="dataTables_paginate paging_simple_numbers"
									id="pagenumber">
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
	<!-- Bottom Scripts -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/TweenMax.min.js"></script>
	<script src="js/resizeable.js"></script>
	<script src="js/xenon-toggles.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="js/xenon-custom.js"></script>
</body>
</html>
