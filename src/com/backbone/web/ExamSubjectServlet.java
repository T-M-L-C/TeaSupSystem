package com.backbone.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.backbone.dao.implement.TeaOfExamImpl;
import com.backbone.dao.implement.TeaOfTraImple;
import com.backbone.entity.po.ExamOfTra;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.PaperInfo;
import com.backbone.entity.po.ReserveList;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.AgreeTeacherInfo;
import com.backbone.entity.vo.PaperQuestionAssist;
import com.backbone.entity.vo.TempTable;
import com.backbone.service.ExamSubjectService;
import com.backbone.service.ReserveListService;
import com.backbone.service.TeaOfExamService;
import com.backbone.service.TraResServices;
import com.backbone.service.UserServices;

@SuppressWarnings("serial")
public class ExamSubjectServlet extends HttpServlet {
	private static int statecounter;
	private static ExamSubjectService exam = new ExamSubjectService();
	private static TeaOfExamService teml = new TeaOfExamService();
	private static TempTable tempTable = new TempTable();
	private static UserServices userservice = new UserServices();
	private static ReserveListService rlService=new ReserveListService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getParameter("action");
		if (method.equals("query")) {
			statecounter = 0;
		} else if (method.equals("regist")) {
			statecounter = 1;
		} else if (method.equals("querycheck")) {
			statecounter = 2;
		} else if (method.equals("singlequery")) {
			statecounter = 3;
		} else if (method.equals("registlist")) {
			statecounter = 4;
		} else if (method.equals("sureregist")) {
			statecounter = 5;
		} else if (method.equals("sureall")) {
			statecounter = 6;
		} else if (method.equals("secondregistlist")) {
			statecounter = 7;
		} else if (method.equals("suresecondall")) {
			statecounter = 8;
		} else if (method.equals("secondsure")) {
			statecounter = 9;
		} else if (method.equals("querycounts")) {
			statecounter = 10;
		} else if (method.equals("querycheckcounts")) {
			statecounter = 11;
		} else if (method.equals("singlequerycounts")) {
			statecounter = 12;
		} else if (method.equals("registlistcounts")) {
			statecounter = 13;
		} else if (method.equals("secondregistlistcounts")) {
			statecounter = 14;
		} else if (method.equals("addexam")) {
			statecounter = 15;
		}
		else if (method.equals("deleteRegist")) {
			statecounter=16;
		}
		else if (method.equals("passexamxls")) {
			statecounter=17;
		}
		else if (method.equals("getexam")) {
			statecounter=18;
		}else if (method.equals("getexams")) {
			statecounter=19;
		}
		else if(method.equals("sureagain")){
			statecounter=20;
		}
		else if (method.equals("Searchname")) {
			statecounter=21;
		}
		else if (method.equals("judgestudy")) {
			statecounter=22;
		}
		else if (method.equals("getExamPassTime")) {
			statecounter=23;
		}
		else if (method.equals("getRegistTime")) {
			statecounter=24;
		}
		else if (method.equals("ExistExam")) {
			statecounter=25;
		}
		else if (method.equals("validanswer")) {
			statecounter=26;
		}
		else if (method.equals("deleteExam")) {
			statecounter=27;
		}
		else if (method.equals("updateexam")) {
			statecounter=28;
		}
		else if (method.equals("PublishExam")) {
			statecounter=29;
		}
		switch (statecounter) {
		case 0:
			doQueryexam(req, resp);
			break;
		case 1:
			doRegist(req, resp);
			break;
		case 2:
			doQueryCheck(req, resp);
			break;
		case 3:
			doSingleQuery(req, resp);
			break;
		case 4:
			doRegistList(req, resp);
			break;
		case 5:
			doSureRegist(req, resp);
			break;
		case 6:
			doSureAll(req, resp);
			break;
		case 7:
			doSecondList(req, resp);
			break;
		case 8:
			doSureSecondAll(req, resp);
			break;
		case 9:
			doSecondSingleSure(req, resp);
			break;
		case 10:
			examcounts(req, resp);
			break;
		case 11:
			querycheckCounts(req, resp);
			break;
		case 12:
			siglecounts(req, resp);
			break;
		case 13:
			doRegistListCounts(req, resp);
			break;
		case 14:
			doSecondListCounts(req, resp);
			break;
		case 15:
			try {
				addExam(req, resp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 16:
			cancelRegist(req,resp);
			break;
		case 17:
			getPassExamXls(req,resp);
			break;
		case 18:
			getExam(req,resp);
			break;
		case 19:
			try {
				getExams(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 20:
			sureAgain(req,resp);
			break;
		case 21:
			querySubjectName(req,resp);
			break;
		case 22:
			doJudgeStudy(req,resp);
			break;
		case 23:
			doGetExamPassTime(req,resp);
			break;
		case 24:
			doGetRegistTime(req,resp);
			break;
		case 25:
			doExistExam(req,resp);
			break;
		case 26:
			doValidAnswer(req,resp);
			break;
		case 27:
			 doDeleteExam(resp,req);
			break;
		case 28:
			doUpdateExam(resp,req);
			break;
		case 29:
			doPublishExam(req,resp);
			break;
		default:
			break;
		}
	}
	/**
	 * 发布某科目的考试
	 * @param req
	 * @param resp
	 */
	private void doPublishExam(HttpServletRequest req, HttpServletResponse resp) {
		String subno=req.getParameter("subno");
		ExamSubject examSubject= exam.getExamSubject(subno);
		 if(examSubject==null){
			 tempTable.setId(0);
		 }
		 else{
			 tempTable.setId(exam.updatePE(subno));
		 }
		 JSONObject jsonObject=JSONObject.fromObject(tempTable);
		 try {
			resp.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新考试信息
	 * @param resp
	 * @param req
	 * @throws IOException 
	 */
	private void doUpdateExam(HttpServletResponse resp, HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String sub_no = req.getParameter("sub_no");
		ExamSubject es = new ExamSubject();
		es=exam.getExamSubject(sub_no);
		if(es==null){
			resp.getWriter().print("0");
			return;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sub_name = req.getParameter("sub_name");
		Date sub_time;
		try {
			sub_time = df.parse( req.getParameter("sub_time"));
			Integer procetime = Integer.parseInt(req.getParameter("sub_procetime"));
			Date sub_registstart=df.parse(req.getParameter("sub_registstart"));
			Date sub_registend=df.parse(req.getParameter("sub_registend"));
			Date sub_studystart=df.parse(req.getParameter("sub_studystart"));
			Date sub_studyend=df.parse(req.getParameter("sub_studyend"));
			Date sub_examstart=df.parse(req.getParameter("sub_examstart"));
			Date sub_examend=df.parse(req.getParameter("sub_examend"));
			es.setSub_name(sub_name);
			es.setSub_no(sub_no);
			es.setSub_time(sub_time);
			es.setSub_proce_time(procetime);
			es.setSub_RegistStart(sub_registstart);
			es.setSub_RegistEnd(sub_registend);
			es.setSub_StudyStart(sub_studystart);
			es.setSub_StudyEnd(sub_studyend);
			es.setSub_ExamStart(sub_examstart);
			es.setSub_ExamEnd(sub_examend);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       long result= exam.UpdateExam(es);
       resp.getWriter().print(result);
	}

	/**
	 * 删除已添加的某一科考试
	 */
	private void doDeleteExam(HttpServletResponse resp, HttpServletRequest req) {
		String subno=req.getParameter("subno");
		ExamSubject subject= exam.getExamSubject(subno);
		JSONObject jsonObject=null;
		if(subject==null){
			tempTable.setId(0);
		   jsonObject=JSONObject.fromObject(tempTable);
		}
		else {
			List<TeaOfExam> teacExams= teml.getAllTeacherInfor(subno);
			if(teacExams.size()>=1){
				tempTable.setId(1);
				jsonObject=jsonObject.fromObject(tempTable);
			}
			else {
				exam.deleteExamBySubno(subno);
				tempTable.setId(2);
				jsonObject=jsonObject.fromObject(tempTable);
			}
		}
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 验证答案
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private void doValidAnswer(HttpServletRequest req, HttpServletResponse resp) {
		   JSONArray jsonArray=JSONArray.fromObject(req.getParameter("answer"));
		   @SuppressWarnings("static-access")
		  List<String> answers=jsonArray.toList(jsonArray, String.class);
		   String subno=req.getParameter("subno");
		   List<PaperInfo> rightanswer=exam.getPaperAnswer(subno);
           String []item=new String[rightanswer.size()];
           for (int i = 0; i < item.length; i++) {
			 if(answers.get(i).equals(rightanswer.get(item.length-i-1).getRightanswer())){
				 item[i]="1"; 
			 }
			 else {
				item[i]="0";
			}
		}
          JSONArray json=JSONArray.fromObject(item);
           try {
			resp.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询该科目是否已注册报名
	 */
	private void doExistExam(HttpServletRequest req, HttpServletResponse resp) {
	   String subno=req.getParameter("subno");
	   ExamSubject examSubject=  exam.getExamSubject(subno);
	   Integer result=examSubject==null?0:1;
	   tempTable.setId(result);
	   JSONObject jsonObject=JSONObject.fromObject(tempTable);
	   try {
		resp.getWriter().print(jsonObject);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	/**
	 * 判断当前时间是否在合理报名时间段内
	 */
	private void doGetRegistTime(HttpServletRequest req,
			HttpServletResponse resp) {
		 	String subno=req.getParameter("subno");
		 	ExamSubject examSubject= exam.getExamSubject(subno);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			//System.out.println(df.format(examSubject.getNowtime()));// new Date()为获取当前系统时间
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("sub_registstart", examSubject.getSub_RegistStart().toString());
			jsonObject.put("sub_registend",examSubject.getSub_RegistEnd().toString());
			jsonObject.put("date", df.format(examSubject.getNowtime()).toString());
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	/**
	 * 判断是否在规定考试时间内
	 */
	private void doGetExamPassTime(HttpServletRequest req,
			HttpServletResponse resp) {
		    String subno=req.getParameter("subno");
			ExamSubject examSubject= exam.getExamSubject(subno);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			//System.out.println(df.format(examSubject.getNowtime()));// new Date()为获取当前系统时间
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("sub_examstart", examSubject.getSub_ExamStart().toString());
			jsonObject.put("sub_examend",examSubject.getSub_ExamEnd().toString());
			jsonObject.put("date", df.format(examSubject.getNowtime()).toString());
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	/**
	 * 判断是否在规定学习时间内
	 */
	private void doJudgeStudy(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String subno=req.getParameter("subno");
		ExamSubject examSubject= exam.getExamSubject(subno);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(examSubject.getNowtime()));// new Date()为获取当前系统时间
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("sub_studystart", examSubject.getSub_StudyStart().toString());
		jsonObject.put("sub_studyend",examSubject.getSub_StudyEnd().toString());
		jsonObject.put("date", df.format(examSubject.getNowtime()).toString());
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过科目编号查询科目名称
	 */
	private void querySubjectName(HttpServletRequest req,
			HttpServletResponse resp) {
		    resp.setCharacterEncoding("UTF-8");
	        String subno=req.getParameter("subno");
	        ExamSubject examSubject= exam.getExamSubject(subno);
	        JSONObject jsonObject=JSONObject.fromObject(examSubject);
	        try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * 考试中心取消教务办对该教师的报名拒绝，同意其申请
	 */
    private void sureAgain(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String teano=req.getParameter("teano");
		String subno=req.getParameter("subno");
		boolean flag=exam.sureAgain(teano, subno);
		if(flag){
			tempTable.setId(0);
			JSONObject jsonObject=JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void getExams(HttpServletRequest req, HttpServletResponse resp)  throws Exception{
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
    	List<ExamSubject> eslist = exam.getAllExam();
    	if(eslist!=null){
    		JSONArray jsona = JSONArray.fromObject(eslist);
    		resp.getWriter().print(jsona);
    	}
	}

	/**
     * 取消报名
     * @param req
     * @param resp
     */
	private void cancelRegist(HttpServletRequest req, HttpServletResponse resp) {
	 //先删除报名表，再删除与报名相关的资源
	 String subno=req.getParameter("subno");
	  String teano=req.getParameter("teano");
	  boolean flag=  teml.DeleteRegister(teano, subno);
	  if(flag==true){
		boolean reflag= teml.DeleteResource(teano);
		if(reflag==true){
			tempTable.setId(0);
			JSONObject jsonObject=JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  }
	}

	/**
	 * 门户管理人员上传考试信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void doExamRegist(HttpServletRequest req, HttpServletResponse resp) {

		String subno = req.getParameter("subno");
		String examname = req.getParameter("examname");
		String starttime = req.getParameter("starttime");
		String examalltime = req.getParameter("examalltime");
		if (subno.equals("") || examalltime.equals("") || starttime.equals("")
				|| examname.equals("")) {
			tempTable.setId(2);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ExamSubject examSubject = new ExamSubject();
			examSubject.setSub_name(examname);
			examSubject.setSub_no(subno);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
			try {
				examSubject.setSub_time(df.parse(starttime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			examSubject.setSub_proce_time(Integer.parseInt(examalltime));
			boolean flag = exam.isExist(subno);
			if (flag == false) {
				tempTable.setId(0);
				JSONObject jsonObject = JSONObject.fromObject(tempTable);
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Integer result = exam.add(examSubject);
				if (result > 0) {
					tempTable.setId(1);
					JSONObject jsonObject = JSONObject.fromObject(tempTable);
					try {
						resp.getWriter().print(jsonObject);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * 教务处审核通过单个人员信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void doSecondSingleSure(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		String teano = req.getParameter("teano");
		boolean flag = teml.UpdateSecondObj(teano, subno);
		if (flag == true) {
			tempTable.setId(0);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 教务处审核通过指定科目的所有人员的报名
	 * 
	 * @param req
	 * @param resp
	 */
	private void doSureSecondAll(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		boolean flag = teml.UpdateSecondList(subno);
		if (flag == true) {
			tempTable.setId(0);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 教务处查看教务办审核通过的人员信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void doSecondList(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer count = Integer.parseInt(req.getParameter("count"));
        if(page==null){
        	page=1;
        }
		List<Teacher> teachers = teml.getSecondListSome(subno, page, count);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		if (teachers.size() < 1) {
			jsonObject.put("teaname", "0");
			jsonArray.add(jsonObject);
			try {
				resp.getWriter().print(jsonArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			for (Teacher item : teachers) {
				jsonObject.put("teaname", item.getTeaname());
				jsonObject.put("teano", item.getTeano());
				jsonObject.put("subordunit", item.getSubordunit());
				jsonObject.put("signstate", item.getAuthority());
				jsonObject.put("schoolstate", item.getTeasex());
				jsonArray.add(jsonObject);
			}
			try {
				resp.getWriter().print(jsonArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 教务办审核通过所在学院所有人员的信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void doSureAll(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		String teano=req.getParameter("teano");
		Teacher teacher=userservice.getSubordUnit(teano);
		boolean flag = teml.isPass(0, subno,teacher.getSubordunit());
		if (flag == true) {
			tempTable.setId(0);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
  /**
   * 教务办通过所在学院的单个人员的报名信息
   * @param req
   * @param resp
   */
	private void doSureRegist(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String teano = req.getParameter("teano");
		String subno = req.getParameter("subno");
		boolean flag = teml.isSinglePass(0, teano, subno);
		if (flag == true) {
			tempTable.setId(0);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 *教务办 查询报名列表（指定科目和指定学院的对应的人员信息）
	 * 
	 * @param req
	 * @param resp
	 */
	private void doRegistList(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer count = Integer.parseInt(req.getParameter("count"));
        String teano=req.getParameter("teano");
        Teacher teach= userservice.getSubordUnit(teano);
		List<Teacher> teachers = teml.getUserInforSome(subno, page, count,teach.getSubordunit());
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if (teachers.size() < 1) {
			Teacher teacher = new Teacher();
			teacher.setTeano("0");
			jsonObject.put("teano", teacher.getTeano());
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (Teacher item : teachers) {
				jsonObject.put("teaname", item.getTeaname());
				jsonObject.put("teano", item.getTeano());
				jsonObject.put("subordunit", item.getSubordunit());
				jsonObject.put("signstate", item.getTeasex());
				jsonObject.put("schoolstate", item.getAuthority());
				jsonArray.add(jsonObject);
			}
			try {
				resp.getWriter().print(jsonArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查看报名科目和对应的人数
	 * 
	 * @param req
	 * @param resp
	 */
	private void doSingleQuery(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String loginuser = req.getParameter("loginuser");
		Integer authority = userservice.getAuthority(loginuser);
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer count = Integer.parseInt(req.getParameter("count"));
        if(page<0 || page==null){
        	page=1;
        }
		List<TempTable> tempTables;
		JSONObject jsonObject;
		JSONArray jsonArray;
		// 教务办人员查询报名人员信息
		if (authority == 1) {
			Teacher teacher=userservice.getSubordUnit(loginuser);
			jsonObject = new JSONObject();
			jsonArray = new JSONArray();
			//System.out.println("====="+teacher.getSubordunit()+"=====");
			tempTables = teml.getSubjectCounterSome(page, count,teacher.getSubordunit());
			System.out.println(tempTables.size());
			if (tempTables.size() < 1) {
				tempTable.setId(0);
				jsonObject.put("id", tempTable.getId().toString());
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				for (TempTable item : tempTables) {
					jsonObject.put("authority", authority);
					jsonObject.put("sub_name", item.getSub_name());
					jsonObject.put("sub_no", item.getState());
					jsonObject.put("sub_time", item.getSub_time().toString());
					jsonObject.put("id", item.getId().toString());
					jsonArray.add(jsonObject);
				}
				try {
					resp.getWriter().print(jsonArray);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// 教务处人员查询报名信息
		if (authority == 2) {
			jsonObject = new JSONObject();
			jsonArray = new JSONArray();
			tempTables = teml.getRegisterCounter();
			if (tempTables.size() < 1) {
				tempTable.setId(0);
				jsonObject.put("id", tempTable.getId().toString());
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				for (TempTable item : tempTables) {
					jsonObject.put("authority", authority);
					jsonObject.put("sub_name", item.getSub_name());
					jsonObject.put("sub_no", item.getState());
					jsonObject.put("sub_time", item.getSub_time().toString());
					jsonObject.put("id", item.getId().toString());
					jsonArray.add(jsonObject);
				}
				try {
					resp.getWriter().print(jsonArray);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 查询报名审核状态
	 * 
	 * @param req
	 * @param resp
	 */
	private void doQueryCheck(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		Integer page = Integer.parseInt(req.getParameter("page"));
		Integer count = Integer.parseInt(req.getParameter("count"));

		Teacher teacher = new Teacher();
		teacher.setTeano(username);
		List<TempTable> items = teml.getcontent(teacher, page, count);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if (items.size() < 1) {
			tempTable.setId(0);
			jsonObject.put("id", tempTable.getId().toString());
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (TempTable item : items) {
				jsonObject.put("sub_name", item.getSub_name());
				jsonObject.put("sub_no", item.getState());
				jsonObject.put("sub_time", item.getSub_time().toString());
				jsonObject.put("id", item.getId().toString());
				jsonObject.put("schoolstate", item.getSchoolstate().toString());
				jsonArray.add(jsonObject);
			}
			try {
				resp.getWriter().print(jsonArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 报名
	 * 
	 * @param req
	 * @param resp
	 */
	private void doRegist(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String subno = req.getParameter("subno");
		TeaOfExam teal = new TeaOfExam();
		teal.setTeano(username);
		teal.setSub_no(subno);
		teal.setSignstate(0);
		teal.setSchoolstate(0);
		TeaOfExam flag = teml.isExistRegistObj(teal);
		if (flag != null) {
			// 表示该用户已经报名了相关科目，并且已经审核成功
			tempTable.setId(3);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			teal.setSignstate(1);
			teal.setSchoolstate(1);
			flag = teml.isExistRegistObj(teal);
			if (flag != null) {
				// 用户已经报名并且教务办正在审核
				tempTable.setId(1);
				JSONObject jsonObject = JSONObject.fromObject(tempTable);
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				teal.setSignstate(0);
				teal.setSchoolstate(1);
				flag = teml.isExistRegistObj(teal);
				if (flag != null) {
					// 用户已经报名并且教务处正在审核
					tempTable.setId(1);
					JSONObject jsonObject = JSONObject.fromObject(tempTable);
					try {
						resp.getWriter().print(jsonObject);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					teal.setSchoolstate(1);
					teal.setSignstate(1);
					boolean result = teml.SaveOrUpdateObj(teal);
					List<Integer> examoftra= new TraResServices().addTeacherRes(username, subno);
					
					if (result == true ) {
						//如果资源已经绑定到科目上
	                      if(examoftra.size()>0){
	                    	  new TeaOfTraImple().adds(username, examoftra);
	                      }
						// 该用户报名成功
						tempTable.setId(2);
						JSONObject jsonObject = JSONObject
								.fromObject(tempTable);
						try {
							resp.getWriter().print(jsonObject);
						} catch (IOException e) {
							e.printStackTrace();
						}
					
					}
					else {
					    teml.DeleteRegister(username, subno);
						tempTable.setId(4);
						JSONObject jsonObject = JSONObject
								.fromObject(tempTable);
						try {
							resp.getWriter().print(jsonObject);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}

		}

	}

	/**
	 * 查询能够报名的考试
	 * 
	 * @param req
	 * @param resp
	 */
	private void doQueryexam(HttpServletRequest req, HttpServletResponse resp) {
		Integer page = null;
		Integer count = null;
		TeaOfExam flag;
		try {
			req.setCharacterEncoding("UTF-8");
			page = Integer.parseInt(req.getParameter("page"));
			count = Integer.parseInt(req.getParameter("count"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");
		String teano=req.getParameter("teano");
		List<ExamSubject> items = exam.getcontent(page, count);
		List<ReserveList> rl=rlService.findAll();
		ReserveList rsList=new ReserveList();
		if(rl.size()>0 && items.size()>0){
			for(int i=0;i<items.size();i++){
			      rsList=rlService.querySubno(items.get(i).getSub_no());
			      if(rsList!=null){
			         rsList=rlService.findInforById(teano,rsList.getSub_no());
			         if(rsList==null){
			        	 items.remove(i);
			         }
			      }
			}
		}
		
		if (items.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			for (ExamSubject data : items) {
                flag=exam.HasInstance(teano, data.getSub_no());
                if(flag!=null){
                	if(flag.getSignstate()==2 || flag.getSchoolstate()==2){
                		JSONObject jsonObject = new JSONObject();
        				jsonObject.put("sub_no", data.getSub_no());
        				jsonObject.put("sub_name", data.getSub_name());
        				jsonObject.put("sub_time", data.getSub_time().toString());
        				jsonObject.put("registstate","报名被拒绝");
        				jsonArray.add(jsonObject);
                	}else{
                		JSONObject jsonObject = new JSONObject();
        				jsonObject.put("sub_no", data.getSub_no());
        				jsonObject.put("sub_name", data.getSub_name());
        				jsonObject.put("sub_time", data.getSub_time().toString());
        				jsonObject.put("registstate","报名成功");
        				jsonArray.add(jsonObject);
                	}	
                }
                else {
                  	JSONObject jsonObject = new JSONObject();
    				jsonObject.put("sub_no", data.getSub_no());
    				jsonObject.put("sub_name", data.getSub_name());
    				jsonObject.put("sub_time", data.getSub_time().toString());
    				jsonObject.put("registstate", "还未报名");
    				jsonArray.add(jsonObject);
				}
			}
			try {
				resp.getWriter().print(jsonArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			tempTable.setState("空值");
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void examcounts(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().print(exam.getcounts());
	}

	protected void querycheckCounts(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		Teacher teacher = new Teacher();
		teacher.setTeano(username);
		resp.getWriter().print(teml.getcounts(teacher));
	}

	protected void siglecounts(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().print(teml.getSubjectCounterCounts());
	}

	protected void doRegistListCounts(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String subno = req.getParameter("subno");
		resp.getWriter().print(teml.getUserInforCounts(subno));
	}

	protected void doSecondListCounts(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		String subno = req.getParameter("subno");
		resp.getWriter().print(teml.getSecondListCounts(subno));
	}

	@SuppressWarnings("unchecked")
	private void addExam(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ParseException {
		resp.setCharacterEncoding("UTF-8");
		String sub_no = req.getParameter("sub_no");
		String method = req.getParameter("method");
		PrintWriter pw = resp.getWriter();
		ExamSubject es = new ExamSubject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (method.equals("info")) {
		/*	if(sub_no==null){
				sub_no=df.format(es.getNowtime().toString());
			}*/
			String sub_name = req.getParameter("sub_name");
			Date sub_time=df.parse( req.getParameter("sub_time"));
			Integer procetime = Integer.parseInt(req.getParameter("sub_procetime"));
			Date sub_registstart=df.parse(req.getParameter("sub_registstart"));
			Date sub_registend=df.parse(req.getParameter("sub_registend"));
			Date sub_studystart=df.parse(req.getParameter("sub_studystart"));
			Date sub_studyend=df.parse(req.getParameter("sub_studyend"));
			Date sub_examstart=df.parse(req.getParameter("sub_examstart"));
			Date sub_examend=df.parse(req.getParameter("sub_examend"));
			es.setSub_name(sub_name);
			es.setSub_no(sub_no);
			es.setSub_time(sub_time);
			es.setSub_proce_time(procetime);
			es.setSub_RegistStart(sub_registstart);
			es.setSub_RegistEnd(sub_registend);
			es.setSub_StudyStart(sub_studystart);
			es.setSub_StudyEnd(sub_studyend);
			es.setSub_ExamStart(sub_examstart);
			es.setSub_ExamEnd(sub_examend);
			// 创建考试科目信息
			if (exam.add(es) != 1) {
				pw.write("0");
				return;
			}
		    	// 创建 考试与资源关联
		    	   JSONArray ja = JSONArray.fromObject(req.getParameter("res"));
					@SuppressWarnings("deprecation")
					List<Integer> strs = JSONArray.toList(ja, Integer.class);
                    if(strs.size()==0){
                    	pw.write("1");
                    	return;
                    }else {
                    	sub_no= exam.getSubNo(es.getSub_name());
                    	exam.addResouce(sub_no, strs);
                    	List<TeaOfExam> exams=teml.getAllTeacherInfor(sub_no);
                    	if(exams.size()>0){
                    		for(TeaOfExam items :exams){
                    			new TeaOfTraImple().adds(items.getTeano(), strs);
                    		}
                    	}
				}
					
			
		}
		else if (method.equals("paper")) {
			JSONArray japaper = JSONArray.fromObject(req.getParameter("paper"));
			// 创建试卷
			@SuppressWarnings("deprecation")
			List<PaperQuestionAssist> papers = JSONArray.toList(japaper, PaperQuestionAssist.class);
			exam.clearPaper(sub_no);
			for (PaperQuestionAssist item : papers) {
				PaperInfo ea = new PaperInfo();
				ea.setSubno(sub_no);
				ea.setRightanswer(item.getRight());
				ea.setQuescontent(item.getT());
				ea.setQuesNo(item.getNo());
				
				for (int i = 0; i < item.getAnswers().size(); i++) {
					PaperInfo.Answer answer = new PaperInfo.Answer();
					answer.setAnswer(item.getAnswers().get(i));
					answer.setIsImage(item.getIsimg());
					answer.setAnswerItem(i);
					ea.getAnswers().add(answer);
				}
				
				exam.addpaper(ea);
			}
		}
		
		pw.write("1");
	}

	private void getExam(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		List<PaperInfo> pis = exam.getPaperInfosBySubno(subno);
		/*for (int i = 0; i < pis.size(); i++) {
			System.out.println(pis.get(i).getQuesid()+","+pis.get(i).getQuescontent()+","+pis.get(i).getRightanswer()+","+pis.get(i).getSubno());
		}*/
		JSONArray jsona = JSONArray.fromObject(pis);
		resp.getWriter().print(jsona);
	}

private void getPassExamXls(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{

	String filename = "教师考试情况";
	resp.setContentType("text/html;charset=GBK"); 
	resp.setContentType("application/xml"); 
	resp.setHeader("Content-Disposition", "attachment;filename=" 
	+ new String(filename.getBytes(), "iso-8859-1") + ".xls"); 
	
	String sub_no = req.getParameter("sub_no");
	if(sub_no==null)
		return ;
	 ExamSubject examSubject = exam.getExamSubject(sub_no);
	List<AgreeTeacherInfo> teas = exam.getAllRegeditTeachers(sub_no);
	
	HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象 
	HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象 
	HSSFCell cell = null; // 声明单元格对象 
	sheet.setColumnWidth(0, 2200); 
	sheet.setColumnWidth(1, 8600);
	sheet.setColumnWidth(2, 8200);
	sheet.setColumnWidth(3, 7600);
	sheet.setColumnWidth(6, 9200);
	sheet.setColumnWidth(7, 3700); 
	sheet.setColumnWidth(8, 11000); 
	sheet.setColumnWidth(9, 3700); 
	sheet.setColumnWidth(10, 11000); 
	//head
	String[] bt = new String[] { "科目","教师工号", "教师姓名","联系电话","所在学院","性别","家庭住址","银行卡号",
			"学习进度","学习完成时间","考试进度","考试完成时间"}; 
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
			HSSFRow tableRow = sheet.createRow(0); // 创建一个行对象 
			tableRow.setHeightInPoints(18); 
			for (int i = 0; i < bt.length; i++) { 
			cell = tableRow.createCell(i); 
			cell.setCellValue(new HSSFRichTextString(bt[i])); 
			} 
			// 写入表格内容 
			for (int i = 0; i < teas.size(); i++) { 
			tableRow = sheet.createRow(i + 1); // 创建一个行对象 
			tableRow.setHeightInPoints(16); 
			AgreeTeacherInfo ati = teas.get(i); 
			cell=tableRow.createCell(0);
			cell.setCellValue(new HSSFRichTextString(examSubject.getSub_name()));
			cell = tableRow.createCell(1); 
			cell.setCellValue(new HSSFRichTextString(ati.getTeano()));
			cell = tableRow.createCell(2); 
			cell.setCellValue(new HSSFRichTextString(ati.getTeaname()));
			cell = tableRow.createCell(3); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf(ati.getTeatel())));
			cell = tableRow.createCell(4); 
			cell.setCellValue(new HSSFRichTextString(ati.getSubordunit()));
			cell=tableRow.createCell(5);
			cell.setCellValue(new HSSFRichTextString(ati.getTeasex()==1?"女":"男"));
			cell = tableRow.createCell(6); 
			cell.setCellValue(new HSSFRichTextString(ati.getTeaaddress()));
			cell = tableRow.createCell(7); 
			cell.setCellValue(new HSSFRichTextString(ati.getCashcardid()));
			cell = tableRow.createCell(8); 
			cell.setCellValue(new HSSFRichTextString(ati.getIsstudy()==0?"未完成学习":"已完成学习"));
			cell = tableRow.createCell(9); 
			cell.setCellValue(new HSSFRichTextString(ati.getStudypassTime()==null?"":sdf.format(ati.getStudypassTime())));
			cell = tableRow.createCell(10); 
			cell.setCellValue(new HSSFRichTextString(ati.getExampass()==0?"未通过考试":"已通过考试"));
			cell = tableRow.createCell(11); 
			cell.setCellValue(new HSSFRichTextString(ati.getExampassTime()==null?"":sdf.format(ati.getExampassTime())));
			
			} 
			try {
				workBook.write(resp.getOutputStream());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
}

}
