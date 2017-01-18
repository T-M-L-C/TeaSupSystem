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
    
    <title>添加试卷</title>
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
        url:"Subject",
        data:{"action":"getexams"},
        type:"POST",
        success:function(data)
        {
            var t = JSON.parse(data);
            var ops = $("#exams");
            ops.html("");
            for (var i = 0; i < t.length; i++) {
                ops.append("<option value=\""+t[i].sub_no+"\">"+t[i].sub_name+"</option>");
            };
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
        <a href="#" data-toggle="settings-pane" data-animate="true">&times;</a>
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
                        <a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i>
                            <span class="badge badge-success">7</span>
                        </a>
                        <a href="#" data-toggle="mobile-menu"> <i class="fa-bars"></i>
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
                     <li class="active opened active">
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
                                            <span class="line"> <strong>使用帮助</strong>
                                            </span>
                                            <span class="line desc small">请仔细阅读，会对您有帮助的！</span>
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
                            <span>，欢迎您！</span>
                        </a>
                    </li>
                    <li class="dropdown user-profile" style="min-height: 75px;">
                        <a href="" data-toggle="dropdown" id="logout">
                            <span>退出登录</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="panel-body" style="display:none;">
                    <!-- Auto initialization -->            
                    <form action="addexammake" style="min-height:250px;" id="addmakeid" class="dropzone">
                        <input type="hidden" value="">
                    </form>

            </div>
            <script type="text/javascript">
                function mysubmit() {
                        $.ajax({
                            url: 'Subject',
                            type: 'post',
                            data: {
                                action: "addexam",
                                method:"paper",
                                sub_no: $("#exams").val(),
                                paper:tisToJsonClass(result)
                            },
                            success: function(data, textStatus) {
                                if (data == "0") {
                                    alert('该科目信息已经注册过，不能重复注册');
                                } else
                                if (data == "1") {
                                    alert('成功');
                                }
                            }
                        });
                    }
                </script>
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">请选择考试</h3>
                        </div>

                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="field-1">选择考试：</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="exams">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group-separator"></div>
                          
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">试卷</h3>
                        </div>
                        <script>
                        function rinfo(r)
                        {
                            $('input[type=file]').each(function (){
                               if($(this).attr("accept") == null)
                                {
                                    $(this).attr('accept','.xls,.xlsx');
                                    return false;
                                } 
                                return true;
                            });
                            alert(r);
                        }
                        //题目加答案，加题号，加正确答案
                        function titleclass()
                        {
                            this.answer = new Array();
                            this.attrno;
                            this.tit;
                            this.isimg = 0;
                            this.right = "未输入";
                            this.no = function (){return this.attrno;};
                            this.rightanswer = function (rightanswer){if(rightanswer !=null){this.right = rightanswer;}return this.right;};
                            this.t = function (t){
                                if (t != null) {
                                this.tit = t;
                                try{
                                this.attrno = parseInt(t.match(/^\w+/)[0]);
                                }
                                catch(e)
                                {
                                    alert("这部分有问题（"+t+"），无法识别，请检查");
                                    return null;
                                }
                            }

                            return this.tit;
                        };
                            this.ganswer = function (){return this.answer;};
                        }

                        function tisToJsonClass(arrayTitle)
                        {
                            var t = new Array();
                            var foo = function (a,b,c,d,e){
                                    this.t = a;
                                    this.no = b;
                                    this.right = c;
                                    this.answers = d;
                                    this.isimg = e;
                                };
                            for (var i = arrayTitle.length - 1; i >= 0; i--) {
                                
                                t.push(new foo(arrayTitle[i].t(),arrayTitle[i].no(),arrayTitle[i].rightanswer(),arrayTitle[i].ganswer(),arrayTitle[i].isimg));
                            };
                            return JSON.stringify(t);
                        }

                        var result = new Array();
                        function transferAssist(item)
                        {
                            if (a != null && b == null) {
                                b = temp[i].match(/(\s*B\S+)|((B[\.|。|、|\s])\S)+/);
                                if (typeof(a) != "string")
                                    a = a[0];
                            }
                        }

                        var chars = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];

                        function transferAssist(n)
                        {
                            var res ="(\\s*A\\S+)|((A[\\.|。|、|\\s])\\S)+";
                            res = res.replace(new RegExp("A", 'g'),chars[n-1]);
                            return new RegExp(res);
                        }
                        //将纯文本转换为html
                        function transfer() {

                            //获取文本内容
                            var content = prompt("请输入题目：","");
                            if (content != null || content.length>0) {}
                                        else{return ;}
                            content = content.replace(/[<]/g, "&lt;");
                            content = content.replace(/[>]/g, "&gt;");
                            //myreset(1);

                            var temp = content.split("\n");
                            var answer = new titleclass();
                            var titleStartFlag = 0;

                            var selectItemI=1;
                            var selectItemIcpy = 1;
                            for (var i = 0; i < temp.length; i++) {
                                if (answer.ganswer().length == 0) {
                                    var anstemp =  temp[i].match(transferAssist(1));
                                    if(anstemp ==null)
                                    {
                                        continue;
                                    }
                                    else if(anstemp.length<3)
                                    {
                                        alert("这部分有问题（"+temp[i]+"），无法识别，请检查");
                                        showResult();
                                        return;
                                    }
                                    answer.ganswer().push($.trim(typeof(anstemp) != "string"?anstemp[0]:anstemp));
                                    selectItemI++;
                                    if (answer.ganswer().length != 0) {
                                        var t = "";
                                        for (var j = titleStartFlag; j < i; j++)
                                            t += temp[j] + "\n";
                                        if (answer.t($.trim(t)) == null)
                                        {
                                            showResult();
                                            return;
                                        }
                                    }
                                }
                                if(selectItemI>1){
                                    var antemp ;
                                    while ((antemp = temp[i].match(transferAssist(selectItemI))) != null)
                                    {
                                            antemp = typeof(anstemp) != "string"?antemp[0]:anstemp;
                                           answer.ganswer().push($.trim(antemp));
                                           selectItemI++;
                                    }
                                    if(selectItemIcpy == selectItemI || i == temp.length-1)
                                    {
                                    	var j =0;
                                    	for ( j = 0; j < result.length; j++) {
                                    		if(result[j].no() == answer.no())
                                    		{
                                    			result.splice(j,1,answer);
                                    			break;
                                    		}
                                    	};
                                    	if(j >= result.length)
                                        	result.push(answer);

                                        answer = new titleclass();
                                        selectItemI=1;
                                        titleStartFlag=i;
                                    }

                                    selectItemIcpy = selectItemI;
                                }
                            }
                            showResult();
                        }

                        function myreset(t) {
                            if (t == null)
                                $("#field-title").val("");
                            $(".middle-align").html("");
                            result = new Array();
                        }
                        </script>
                        <div class="panel-body">
                            <form role="form" class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-sm-2 control-label" for="field-1">注意：</label>
                                    <div class="col-sm-4">
                                        <textarea readonly="readonly" style="height: 92px; width: 600px;resize:none;">
选项字母与内容之间不能有空格，错例：A. 答案A。 这样是可以的：A 答案A;A .答案A。 
题目没有题号是可以得，但题目结束后需要另起一行： 
题目一 
A答案A B答案B C答案C D答案D
                                        </textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="field-1">样例1：</label>
                                    <div class="col-sm-4">
                                        <textarea readonly="readonly" style="height: 92px; width: 600px;resize:none;">
1．党的十八届三中全会决定设立国家   ，完善国家安全体制和国家安全战略，确保国家安全。 
A、安全保障委   B、安全委员会 C、安全局        D、情报局
                                        </textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="field-1">样例2：</label>
                                    <div class="col-sm-4">
                                        <textarea readonly="readonly" style="height: 115px; width: 600px;resize:none;">
2．每年的12月4日是全国法制宣传日。2012年全国法制宣传日的主题是( ) 
A.弘扬宪法精神，服务科学发展 B．深入学习宣传宪法，大力弘扬法治精神 C.加强法制宣传教育，服务经济社会发展。 D．弘扬法治精神，促进社会和谐。
                                        </textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="field-1">样例3：</label>
                                    <div class="col-sm-4">
                                        <textarea readonly="readonly" style="height: 115px; width: 600px;resize:none;">
3．我国第一艘航空母舰“ ”已按计划完成建造和试验试航工作，9月25日上午在中国船舶重工集团公司大连造船厂正式交付海军。中共中央总书记、国家主席、中央军委主席胡锦涛出席交接入列仪式并登舰视察。中共中央政治局常委、国务院总理温家宝一同出席并宣读党中央、国务院、中央军委的贺电。（ ） 
A.辽宁舰 B.海巡01 C.吉林舰 D.中华舰
                                        </textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2">
                                    <input type="button" value="输入题目" class="form-control" onclick="transfer();">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="button" value="输入答案" class="form-control" onclick="inputAnswer();" placeholder="">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="button" value="删除一道题" class="form-control" onclick="delimgt('');" placeholder="">
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="button" value="重置" class="form-control" onclick="myreset();" placeholder="">
                                    </div>
                                    
                                    <div class="col-sm-4">
                                     	<input type="button" class="form-control" value="确认添加试卷" onclick="mysubmit();">
                                 	</div>
                                </div>
                                <div id="example-2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                                <script type="text/javascript">
                                function showResult()
                                {
                                    result.sort(function (a,b){return a.no()-b.no();});
                                    var t = $(".middle-align").html("");
                                    var str = $(" <tr><td>1</td><td>1</td><td>1</td><td>"+
                                    "<table class=\"table table-bordered table-striped table-condensed table-hover\"></table></td></tr>");
                                    for (var i = 0; i <result.length; i++) {
                                        
                                        var strcpy = str.clone();
                                        var tds = strcpy.find("td");
                                        $(tds[0]).html(result[i].no());
                                        $(tds[1]).html(result[i].rightanswer());
                                        $(tds[2]).html(result[i].t());
                                        var table = strcpy.find("table");
                                        if(result[i].isimg == 1)
                                        {
                                            for (var j = 0; j <result[i].ganswer().length; j++) {
                                                table.append("<tr><td>"+chars[j]+"<img src=\""+result[i].ganswer()[j]+"\"></td></tr>");
                                            };
                                        }
                                        else
                                        {
                                            for (var j = 0; j <result[i].ganswer().length; j++) {
                                            table.append("<tr><td>"+result[i].ganswer()[j]+"</td></tr>");
                                        };
                                        }
                                        t.append(strcpy);
                                    };
                                }
                                function insertToRes(name)
                                {
                                    var t;
                                    try{
                                        t = parseInt($("#titleno").val());
                                    }
                                    catch(e)
                                    {
                                        alert("题号只能有数字");
                                        return ;
                                    }
                                    var i;
                                    for (i = 0; i < result.length; i++) {
                                        if(result[i].no() == t)
                                        {
                                            result[i].isimg = 1;
                                            result[i].ganswer().push("BackGround/upload/"+name);
                                            break;
                                        }
                                    };
                                    if(i == result.length)
                                    {
                                        var antemp = new titleclass();
                                        antemp.t($("#titleno").parent().parent().find("textarea").first().val());
                                        antemp.isimg = 1;
                                        antemp.ganswer().push("BackGround/upload/"+name);
                                        result.push(antemp);
                                    }
                                    showResult();
                                    $("input[type=file]").each(function (){
                                        if($(this).attr("accept") == null)
                                        {
                                            $(this).attr("accept",".png,.jpg,.jpeg"); 
                                            return false;
                                        }
                                        return true;
                                    });
                                }
                                function inputAnswer()
                                {
                                    result.sort(function (a,b){return a.no()-b.no();});
                                    var content = prompt("请输入答案","");
                                    if (content != null || content.length>0) {}
                                        else{return ;}
                                    var t = content.split(/\W+/);
                                    for (var i = 0; i < t.length; i++) {
                                        if(/\d+/.test(t[i]))
                                        {
                                            
                                            if (i+1<t.length)
                                             {
                                            try{
                                                if (result.length < parseInt(t[i])) {
                                                    dis = 1;
                                                }
                                                else
                                                {
                                                    var dis  = result[parseInt(t[i])-1].no() - parseInt(t[i]);  
                                                }
                                            }
                                            catch(e)
                                            {
                                                alert(i+"有问题");
                                            }
                                                if (dis == 0)
                                                 {
                                                    result[parseInt(t[i])-1].rightanswer(t[i+1]);
                                                 }
                                                 else if(dis < 0)
                                                 {
                                                    var j;
                                                    for (j = parseInt(t[i]); j < result.length; j++) {
                                                        if(result[j].no() == t[i])
                                                        {
                                                            result[j].rightanswer(t[i+1]);
                                                            break;
                                                        }
                                                    };
                                                    if (j == result.length) {
                                                        var antemp = new titleclass();
                                                        antemp.rightanswer(t[i+1]);
                                                        antemp.t(t[i]);
                                                        result.push(antemp);
                                                    };
                                                 }
                                                 else if(dis > 0)
                                                 {
                                                    var j;
                                                    for (j = parseInt(t[i])>=result.length?result.length-1:parseInt(t[i]); j >= 0; j--) {
                                                        if(result[j].no() == t[i])
                                                        {
                                                            result[j].rightanswer(t[i+1]);
                                                            break;
                                                        }
                                                    };
                                                    if (j == -1) {
                                                        var antemp = new titleclass();
                                                        antemp.rightanswer(t[i+1]);
                                                        antemp.t(t[i]);
                                                        result.push(antemp);
                                                    };
                                                 }
                                             }
                                             i++;
                                        }
                                    };
                                    showResult();
                                }
                                </script>
                        <table class="table table-bordered table-striped table-condensed table-hover">
                            <thead>
                                <tr role="row">
                                    <th style="width: 10%;">题号</th>
                                    <th style="width: 10%;">答案</th>
                                    <th style="width: 40%;">题目</th>
                                    <th style="width: 40%;">选项</th>
                                </tr>
                            </thead>
                            <tbody class="middle-align">
                             
                            </tbody>
                        </table>
                    </div>
                                <div class="form-group-separator"></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel-heading">
                    <div class="panel-title">图片题上传</div>
                </div>
                <script type="text/javascript">
                function titleno(){
                    var t = $(event.srcElement);
                    var no = t.val().match(/[^\s]{0}\d+/);
                    if(no == null || no.length==0)
                        return ;
                    else if(no.length>1)
                    {
                    	no = no[0];
                    }
                    $("#titleno").val(no);
                }
                function v()
                {
                    var t = $("#titleno").val();
                    if(t == null || t.length <= 0)
                    {
                        alert("请先输入题号");
                        return false;
                    }
                }
                function addtitle()
                {
                    if(!titlenov())
                    {
                        return false;
                    }
                    if($("#img-title").val()!=null && $("#img-title").val().length > 5)
                    {

                    }
                    else
                    {
                        alert("题目字数太少");
                        return false;
                    }
                    $(".dropzone").find(".dz-preview").remove();
                    $("#titleno").val("");
                    $("#img-title").val("");
                }
                function titlenov()
                {
                    if(!/\d+/.test($('#titleno').val()))
                    {
                        alert("题号只能有数字");
                        return false;
                    }   
                    $('#paperid').val($(event.srcElement).val());
                    return true;
                }
                function delimgt(n)
                {
                	var no;
                	if(n == null){
                		no = $('#titleno').val();
                		if(no == null || no.length<1){
                			alert("没有题");
                			return ;
                		}
                	}
                	else
                	{
                		no = prompt("请输入要删除题目的题号:","");
                	}
                	var i=0;
                	for ( i = 0; i < result.length; i++) {
                		if(result[i].no() == no)
                		{
                			result.splice(i,1);
                			break;
                		}
                	};
                	if(i > result.length)
                	{
                		alert("没有这道题");
                	}
                	showResult();
                }
                </script>

                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="  width: 20%;" for="field-1">题号（可自动根据题目获取）：</label>
                        <input class="col-sm-4 form-control" style="  width: 80%;" id="titleno" type="text"  onkeyup="titlenov()">
                    </div>
                    <div class="form-group">
                        <label class="control-label" style="  width: 10%;" for="field-1">请输入题目：</label>
                    </div>
                    <div class="form-group-separator"></div>
                           
                    <div class="form-group">
                        <textarea class="form-control" cols="5" id="img-title" style="height: 250px; width: 90%;" onkeyup="titleno()"></textarea>
                    </div>
                    <div>
                        <label class="control-label" style=" width: 100%;" >请上传图片答案（请一个一个上传）：</label>
                    </div>
                    <!-- Auto initialization -->            
                    <form action="paper" style="min-height:250px;" class="dropzone">
                        <input type="hidden" id="paperid" value="">
                    </form>
                   	<div>
	                    <input class="form-control col-sm-2" type="button" value="添加下一道" onclick="addtitle()">
	                    <input class="form-control col-sm-2" type="button" value="删除本题" onclick="delimgt()">
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
    <!-- Imported styles on this page -->
    <link rel="stylesheet" href="js/daterangepicker/daterangepicker-bs3.css">
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
     <script type="text/javascript">
            jQuery(document).ready(function($)
            {

                $("input[type=file]").last().attr("accept",".png,.jpg,.jpeg");//.removeAttr("multiple");
                $("input[type=file]").first().attr("accept",".xls,.xlsx");
            });
	</script>
</body>
</html>
