<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="k" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>添加考试</title>
	<link rel="stylesheet" href="css/css.css">
	<link rel="stylesheet" href="css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet"
		  href="css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/xenon-core.css">
	<link rel="stylesheet" href="css/xenon-forms.css">
	<link rel="stylesheet" href="css/xenon-components.css">
	<link rel="stylesheet" href="css/xenon-skins.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" href="css/jquery.datetimepicker.css">
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/cookie.js"></script>
	<script src="js/myjs.js"></script>
	<script src="js/ready.js"></script>
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
		
	      $.ajax({
		        url: "tra?method=getsimcontent", //url
		        success: function(data) {
		           //结果
		           var str = $("<option value=\"2\">操作规章.doc</option>");
		           for (var i = 0; i < data.length; i++) {
		                $("#multi-select").append(str.clone().val(data[i].no).html(data[i].name));
		           }
		
		          mulselec = $("#multi-select").multiSelect({
		               afterInit: function() {
		                    // Add alternative scrollbar to list
		                    this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
		               },
		               afterSelect: function() {
		                    // Update scrollbar size
		                    this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
		               }
		           });
	         },
		       dataType: "JSON"
		   });
		
		   $('#exams').parents('.form-group').on('click',focuval);
		   $('#examname').parents('.form-group').on('click',focuval);
		    focuval();
		
		   $('#logout').click(function() {
	          exit();
		    });
		}); 

		function focuval(t) {
			var theEvent = window.event || arguments.callee.caller.arguments[0];
			var obj = theEvent.target || theEvent.srcElement;
			obj = $(obj);
			var inputs = obj.find('input');

			if ($('#exams').val() == null || $('#exams').val() == 0 || (inputs != null && inputs.length > 0)) {
				$('#exams').attr('readonly', 'true');
				$('#exams').val(0);
				$('#field-submit').val('添加');
				$('#examname').removeAttr('readonly', 'false');
				$('#examname').removeAttr('placeholder', '');
			}
			else {
				$('#examname').attr('placeholder', '不使用');
				$('#examname').attr('readonly', 'true');
				$('#examname').val('');
				$('#field-submit').val('保存');
				$('#exams').removeAttr('readonly', 'false');
			}
		}
		window.onunload = function () {
			exit();
		};
		    //获取上传的考试科目信息
       $.ajax({
        url:"Subject",
        data:{"action":"getexams"},
        type:"POST",
        success:function(data)
        {
            var t = JSON.parse(data);
            var ops = $("#exams");
            for (var i = 0; i < t.length; i++) {
                ops.append("<option value=\""+t[i].sub_no+"\">"+t[i].sub_name+"</option>");
                $("#exams option").each(function() {
		               var val = $(this).val();
		              if ( $("#exams option[value='" + val + "']").length > 1 )
		                $("#exams option[value='" + val + "']:gt(0)").remove();
		      	  });
            };
        }
    });
      
        
	</script>
	
							<script type="text/javascript">
												    var record=function(){
												       var name=$("#exams").find("option:selected").text();
		                                               var subno=$("#exams").val();
					                                   if(name!=null && name!=""){
					                                       $.ajax({
					                                          url:"Login",
					                                          type:"post",
					                                          data:{
					                                            action:'querysubno',
		                                                         subname:name
					                                          },
					                                          success:function(data){+9
					                                            var json=jQuery.parseJSON(data);
						                                          if(json.state=='空值'){
						                                            alert("你选择的科目目前没有考场安排，请重新选择");
						                                          }
						                                          else{
						                                             window.location.href="Login?action=examrecord&subno="+subno;
						                                          }
					                                          }
					                                       });
			                                   			 }
												    };
											
												  
												</script>
</head>

<body class="page-body">
<div class="settings-pane">
	<a href="#" data-toggle="settings-pane" data-animate="true">&times;</a>

	<div class="settings-pane-inner">
		<div class="row">
			<div class="col-md-4">
				<div class="user-info">
					<div class="user-image">
						<a href="extra-profile.html"> <img src="images/user-2.png"
														   class="img-responsive img-circle"/> </a>
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
					<a href="http://www.xju.edu.cn" class="logo-expanded"> <img
							src="images/logo.png" width="80" alt=""/> </a> <a
						href="http://www.xju.edu.cn" class="logo-collapsed"> <img
						src="images/logo.png" width="40" alt=""/> </a>
				</div>
				<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
				<div class="mobile-menu-toggle visible-xs">
					<a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i>
						<span class="badge badge-success">7</span> </a> <a href="#"
																		   data-toggle="mobile-menu"> <i
						class="fa-bars"></i> </a>
				</div>
			</header>
			<ul id="main-menu" class="main-menu">
				<!-- add class "multiple-expanded" to allow multiple submenus to open -->
				<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
				<li><a href="userinfo3.jsp"> <i class="linecons-cog"></i>
					<span class="title">个人资料</span> </a></li>
				<li><a href="upload.jsp"> <i class="linecons-desktop"></i>
					<span class="title">上传文件管理</span> </a>
					<ul>
						<li><a href="upload.jsp"> <span class="title">上传资源</span>
						</a></li>
						<li><a href="manage.jsp"> <span class="title">管理资源</span>
						</a></li>
					</ul>
				</li>
				<li class="active opened active"><a href="addexam.jsp"> <i
						class="linecons-note"></i> <span class="title">添加考试信息</span> </a></li>
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
										class="line desc small">请仔细阅读，会对您有帮助的！</span> </a></li>
							</ul>
						</li>
						<li class="external"><a href="blank-sidebar.html"> <span>All
										Messages</span> <i class="fa-link-ext"></i> </a></li>
					</ul>
				</li>
			</ul>
			<!-- Right links for user info navbar -->
			<ul class="user-info-menu right-links list-inline list-unstyled">
				<li class="dropdown user-profile" style="min-height: 75px;"><a
						href="" data-toggle="dropdown"> <span id="username"></span> <span>，欢迎您！</span>
				</a></li>
				<li class="dropdown user-profile" style="min-height: 75px;"><a
						href="" data-toggle="dropdown" id="logout"> <span>退出登录</span>
				</a></li>
			</ul>
		</nav>
		<div class="panel-body" style="display:none;">
			<!-- Auto initialization -->
			<form style="min-height:250px;"
				   class="dropzone"   id="addmakeid" action="addexammake">
				<input type="hidden" value=""  >
			</form>
		</div>
		 	
		<div class="row">
	       
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-body">
								<input type="button" value="查看考场浏览记录"
											class="form-control"  style="width:200px;background-color:lightblue;"
												onclick="record()"/>
						    
							<div style=" position: absolute;top:50px; right: 100px">
								<input type="button"
									style="width:200px;background-color:lightblue;"
									class="form-control" value="添加考场安排"
									onclick="$('#addmakeid').click()">
							</div>
						<form role="form" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">考试名称：</label>
								<div class="col-sm-4">
									<select class="form-control" id="exams">
									    <option value="0"  selected="selected">不使用</option>
										<option value="20140213">教育学与心理学</option>
										<option value="20150214">MHK口试</option>
										<option value="20150215">MHK笔试</option>
										<option value="20160215">CET</option>
									</select>
								</div>
								 <input type="button" value="删除选中科目的考试" id="delete" class="btn btn-danger"  style="display: block;">
								 <input type="button" value="发布考试" id="publish" class="btn btn-danger"  style="display: block;position: absolute;right: 380px;top:73px;">
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">新增考试名称：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="examname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">正式考试开始时间</label>
								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="startime">
										<input type="text" class="form-control datetimepicker"
											   style="width:130px;" readonly="readonly"/>
										<script src="js/jquery.datetimepicker.js"></script>
										
										<script>
											$(function () {
												$('.datetimepicker').datetimepicker({
													datepicker: false,
													format: 'H:i',
													step: 5
												});
											});
										</script>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">正式考试时长：</label>

								<div class="col-md-4">
									<!-- Input spinner, just add class "spinner" to input-group and it will be activated -->
									<div class="input-group spinner" data-step="5">
											<span class="input-group-btn">
												<button class="btn btn-gray" data-type="decrement">-</button>
											</span> <input type="text" class="form-control text-center"
														   id="examalltime" value="120"> <span
											class="input-group-btn">
												<button class="btn btn-gray" data-type="increment">+</button>
											</span>
									</div>
								</div>
								<label class="col-sm-1 control-label">分钟</label>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">培训报名时间</label>

								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="registstarttime">
										<input type="text" class="form-control datetimepicker"
											   style="width:130px;" readonly="readonly"/>
									</div>
								</div>
								<div class="col-xs-1">
									<label class="control-label">到</label>
								</div>
								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="registendtime">
										<input type="text" class="form-control datetimepicker"
											   style="width:130px;" readonly="readonly"/>
									</div>
								</div>
						<!-- 		<div class="col-sm-1">
									<input type="button" value="清空" class="form-control">
								</div> -->
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">培训学习时间</label>

								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="studystarttime">
										<input type="text" class="form-control datetimepicker"
											   style="width:130px;" readonly="readonly"/>
									</div>
								</div>
								<div class="col-xs-1">
									<label class="control-label">到</label>
								</div>
								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="studyendtime">
										<input type="text" class="form-control  datetimepicker"
											   style="width:130px;" readonly="readonly"/>
									</div>
								</div>
							<!-- 	<div class="col-sm-1">
									<input type="button" value="清空" class="form-control">
								</div> -->
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">培训考试时间</label>

								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="examstarttime">
										<input type="text" class="form-control datetimepicker" 
											   style="width:130px;" readonly="readonly"/>
									</div>
								</div>
								<div class="col-xs-1">
									<label class="control-label">到</label>
								</div>
								<div class="col-sm-4">
									<div class="date-and-time">
										<input type="text" class="form-control datepicker"
											   data-format="yyyy-MM-dd" readonly="readonly" id="examendtime">
										<input type="text" class="form-control datetimepicker"
											   style="width:130px;" readonly="readonly"/>
									</div>
								</div>
								<!-- <div class="col-sm-1">
									<input type="button" value="清空" class="form-control">
								</div> -->
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label">选择课程</label>

								<div class="col-sm-4">

									<select class="form-control" multiple="multiple"
											id="multi-select" name="my-select[]">暂无资源</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label"></label>

								<div class="col-sm-2">
									<input type="button" value="添加" class="form-control"
										   onclick="mysubmit();" id="field-submit" placeholder="">
								</div>
								<div class="col-sm-2">
									<input type="button" value="修改考试信息" class="form-control"
										   onclick="updateTime();" id="field-submit" placeholder="">
								</div>
								<div class="col-sm-2">
									<input type="button" value="重置" class="form-control"
										   id="field-reset" placeholder="">
								</div>

								<script type="text/javascript">
									function gettime(id){
										return $('#'+id).val()+" "+$('#'+id).next().val();
									}
									function mysubmit() {
										var lis = $("#ms-multi-select").find(".ms-selection").find(".ms-selected");
										var arrayObj = new Array();
										lis.each(function () {
											arrayObj.push($(this).attr("myid"));
										});
										var examname,subno;
										if($('#exams').val() == '0')
										{
											examname = $('#examname').val();
										}
										else
										   {
										     subno=$('#exams').val();
										     examname=$('#exams').find("option:selected").text();
										   }
									  /*if($('#examname').attr('readonly')==true ){
											//examname = $('#exams').find('option[value='+$('#exams').val()+']').html();
											examname=$('#exams').val();
										}*/
										
									
										var starttime = gettime('startime');
										var examalltime = $('#examalltime').val();
										var registstarttime = gettime('registstarttime');
										var registendtime = gettime('registendtime');
										var studystarttime = gettime('studystarttime');
										var studyendtime = gettime('studyendtime');
										var examstarttime = gettime('examstarttime');
										var examendtime = gettime('examendtime');
										 var form= validform(examname, starttime, registstarttime, registendtime, studystarttime, studyendtime, examstarttime, examendtime);
									      if(!form){
									        return;
									   }
									    registstarttime=(registstarttime+":00").replace("/-/g", "/");
									    registendtime=(registendtime+":00").replace("/-/g", "/");
									    var registresult=compare(registstarttime, registendtime);
								
									   
									    studystarttime=(studystarttime+":00").replace("/-/g", "/");
									    studyendtime=(studyendtime+":00").replace("/-/g", "/");
									    var studyresult=compare(studystarttime, studyendtime);
									  
									    examstarttime=(examstarttime+":00").replace("/-/g", "/");
									    examendtime=(examendtime+":00").replace("/-/g", "/");
									    var examresult=compare(examstarttime, examendtime);
									
									     if(registresult>=0){
									       alert('正式报名的结束时间应晚于开始时间');
									       return;
									    }
									    if(studyresult>=0){
									       alert('正式学习的结束时间应晚于结束时间');
									      return;
									    }
									    if(examresult>=0){
									      alert('正式培训的考试结束时间应该晚于开始时间');
									      return;
									    }
									     if(arrayObj!=""){
									        if(!confirm("确定将已选择的资源绑定到"+examname+"中吗？"))
									           return;
									     }
								        
								        $.ajax({
												url: 'Subject',
												type: 'post',
												data: {
													action: "addexam",
													method: "info",
													sub_time: starttime+":00",
													sub_no:subno,
													sub_name: examname,
													sub_procetime: examalltime,
													sub_registstart:registstarttime,
													sub_registend:registendtime,
													sub_studystart:studystarttime,
													sub_studyend:studyendtime,
													sub_examstart:examstarttime,
													sub_examend:examendtime,
													res: JSON.stringify(arrayObj)
												},
												success: function (data, textStatus) {
													if (data == "0") {
														alert('失败');
													} else if (data == "1") {
														alert('成功');
														window.location.reload();
													}
												}
										 });
									   
								
										return arrayObj;
									}
									function compare(start,end){
									  var datestart=new Date(start);
									  var dateend=new Date(end);
									  return datestart.valueOf()-dateend.valueOf();
									}
									
									$("#delete").click(function(){
									    var exam= $("#exams").val();
									    if(exam==0){
									      alert("请选择科目后再进行删除");
									      return;
									    }
									    $.ajax({
									     url:"Subject",
									     type:"post",
									     dataType:"json",
									     data:{
									     action:"deleteExam",
									      subno:exam
									     },
									     success:function(data){
									        var json=eval(data);
									        if(json.id==0){
									          alert("该科目还未添加考试");
									        }
									        else
									          if(json.id==1){
									            alert("该科目已经开始报名无法删除");
									          }
									          else
									             if(json.id==2){
									                alert("删除成功");
									                window.location.reload();
									             }
									     }
									    });
									});
									function updateTime(){
									   	var examname,subno;
										if($('#exams').val() == '0')
										{
											examname = $('#examname').val();
										}
										else
										   {
										     subno=$('#exams').val();
										     examname=$('#exams').find("option:selected").text();
										   }
									  /*if($('#examname').attr('readonly')==true ){
											//examname = $('#exams').find('option[value='+$('#exams').val()+']').html();
											examname=$('#exams').val();
										}*/
										
									
										var starttime = gettime('startime');
										var examalltime = $('#examalltime').val();
										var registstarttime = gettime('registstarttime');
										var registendtime = gettime('registendtime');
										var studystarttime = gettime('studystarttime');
										var studyendtime = gettime('studyendtime');
										var examstarttime = gettime('examstarttime');
										var examendtime = gettime('examendtime');
										 var form= validform(examname, starttime, registstarttime, registendtime, studystarttime, studyendtime, examstarttime, examendtime);
									      if(form==false){
									        return;
									   }
									    registstarttime=(registstarttime+":00").replace("/-/g", "/");
									    registendtime=(registendtime+":00").replace("/-/g", "/");
									    var registresult=compare(registstarttime, registendtime);
								
									   
									    studystarttime=(studystarttime+":00").replace("/-/g", "/");
									    studyendtime=(studyendtime+":00").replace("/-/g", "/");
									    var studyresult=compare(studystarttime, studyendtime);
									  
									    examstarttime=(examstarttime+":00").replace("/-/g", "/");
									    examendtime=(examendtime+":00").replace("/-/g", "/");
									    var examresult=compare(examstarttime, examendtime);
									
									     if(registresult>=0){
									       alert('正式报名的结束时间应晚于开始时间');
									       return;
									    }
									    if(studyresult>=0){
									       alert('正式学习的结束时间应晚于结束时间');
									      return;
									    }
									    if(examresult>=0){
									      alert('正式培训的考试结束时间应该晚于开始时间');
									      return;
									    }
									    if(!confirm("确认更新考试信息么？"))
									     return;
									   $.ajax({
												url: 'Subject',
												type: 'post',
												data: {
													action: "updateexam",
													sub_time: starttime+":00",
													sub_no:subno,
													sub_name: examname,
													sub_procetime: examalltime,
													sub_registstart:registstarttime,
													sub_registend:registendtime,
													sub_studystart:studystarttime,
													sub_studyend:studyendtime,
													sub_examstart:examstarttime,
													sub_examend:examendtime
												},
												success: function (data, textStatus) {
													if (data == "0") {
														alert("你还未添加该考试，无法更新考试信息");
													} else if (data == "1") {
														alert('考试信息更新成功');
														window.location.reload();
													}
												}
										 });
									   
									  
									}
								  $("#field-reset").click(function(){
								     window.location.reload();
								  });
								 //表单验证
								 function validform(examname,starttime,registstarttime,registendtime,studystarttime,studyendtime,examstarttime,examendtime){
								       if(examname==""){
										   alert("请选择或者添加一个考试科目");
										  return false;
										}
								    if(starttime==" " || starttime==null){
										   alert('请选择正式考试开始时间');
										   return false;
										}
										if(starttime.length<15){
										 alert("请将正式考试开始时间填写完整");
										 return false;
										}
									    if(registstarttime==" " || registendtime==" " || registstarttime==null || registendtime==null){
									       alert('请选择培训开始报名时间和截止时间');
									       return false;
									    }
									
									  if(registstarttime.length<15){
									      alert("请将正式开始报名时间填写完整");
									      return false;
									    }
									    if(registendtime.length<15){
									     alert("请将正式结束报名时间填写完整");
									     return false;
									    }
									    if(studystarttime==" " || studyendtime==""){
									        alert("请选择培训的开始学习时间和截止时间");
									        return false;
									    }
									      if(studystarttime.length<15){
									       alert("请将正式学习的开始时间填写完整");
									       return false;
									    }
									    if(studyendtime.length<15){
									     alert("请将正式学习的结束时间填写完整");
									     return false;
									    }
									    if(examstarttime=="" || examendtime==""){
									       alert("请选择培训的开始考试时间和截止时间");
									       return false;
									    }
									     if(examstarttime.length<15){
									         alert("请将正式培训考试的开始时间填写完整");
									      return false;
									    }
									    if(examendtime.length<15){
									      alert("请将正式培训考试的结束时间填写完整");
									      return false;
									    }
									    return true;
								 }
								 $("#publish").click(function(){
								    var subno=$("#exams").val();
								    if(subno=='0')
								         alert('请选择已经添加的考试,再确定发布');
								         else
								           {
								               $.post("Subject",{
								                 action:"PublishExam",
								                 subno:subno
								               },
								               function(data){
								                  var exam=JSON.parse(data);
								                  if(exam.id==0)
								                    alert('你还未添加该科目的考试，请添加后再发布');
								                    else
								                    if(exam.id==1){
								                         var subname=$("#exams").find("option:selected").text();
								                         alert(subname+'发布成功');
								                    }
								               });
								           }
								 });
								</script>
							</div>
							<div class="form-group-separator"></div>
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

<!-- Imported styles on this page -->
<link rel="stylesheet"
	  href="js/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="js/select2/select2.css">
<link rel="stylesheet" href="js/select2/select2-bootstrap.css">
<link rel="stylesheet" href="js/multiselect/css/multi-select.css">
<link rel="stylesheet" href="js/dropzone/css/dropzone.css">
<!-- Bottom Scripts -->
<script src="js/bootstrap.min.js"></script>
<script src="js/TweenMax.min.js"></script>
<script src="js/resizeable.js"></script>
<script src="js/joinable.js"></script>
<script src="js/xenon-api.js"></script>
<script src="js/xenon-toggles.js"></script>
<!-- Imported scripts on this page -->
<script src="js/dropzone/dropzone.min.js"></script>
<script src="js/daterangepicker/daterangepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/timepicker/bootstrap-timepicker.min.js"></script>
<script src="js/select2/select2.min.js"></script>
<script src="js/jquery-ui/jquery-ui.min.js"></script>
<script src="js/selectboxit/jquery.selectBoxIt.min.js"></script>
<script src="js/tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="js/typeahead.bundle.js"></script>
<script src="js/handlebars.min.js"></script>
<script src="js/multiselect/js/jquery.multi-select.js"></script>

<!-- JavaScripts initializations and stuff -->
<script src="js/xenon-custom.js"></script>

</body>
</html>
