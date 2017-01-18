package com.backbone.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

import com.backbone.common.DeleteFile;
import com.backbone.dao.implement.ExamOfTraImple;
import com.backbone.dao.implement.ExamStatusImple;
import com.backbone.dao.implement.ExamSubjectImple;
import com.backbone.dao.implement.ReserveListDaoImple;
import com.backbone.dao.implement.TeaOfExamImpl;
import com.backbone.dao.implement.TeaOfTraImple;
import com.backbone.dao.implement.TraResImple;
import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.po.TraRes;
import com.backbone.entity.vo.TempTable;
import com.backbone.service.ExamStatusService;
import com.backbone.service.ExamSubjectService;
import com.backbone.service.TeaOfExamService;
import com.backbone.service.TraResServices;
import com.backbone.service.UserServices;

public class UserLogin extends HttpServlet {
	private static UserServices userService = new UserServices();
	private String loginuser;
	private static Integer statevalue, authority;
	private static SessionCounter sessionCounter=new  SessionCounter();
	private TempTable tempTable = new TempTable();
    private ExamSubjectService examSubjectService=new ExamSubjectService();
    private TeaOfExamService teaOfExamService=new TeaOfExamService();
    private ExamStatusService examStatusService=new ExamStatusService();
	private static TraResServices trs = new TraResServices();
	private static String PATH_FOLDER="/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		String method = req.getParameter("action");
		if (method.equals("study")) {
			doPost(req, resp);
			statevalue = 1;
		} else if (method.equals("Uid")) {
			resp.getWriter().print(loginuser);
		} else if (method.equals("canexam")) {
			canExam(req, resp);
		} else if (method.equals("finishexam")) {
			finishExam(req, resp);
		} else if (method.equals("getpasscount")) {
			getPassCount(req, resp);
		} else if (method.equals("isfinishexam")) {
			doIsFinishExam(req, resp);
		} 
		else if (method.equals("exportinfor")) {
			doGetUserInfor(req, resp);
		} else if (method.equals("examrecord")) {
			doGetExamRecord(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("action");
		if (method.equals("login")) {
			statevalue = 0;
		} else if (method.equals("corpwd")) {
			statevalue = 1;
		} else if (method.equals("query")) {
			statevalue = 2;
		} else if (method.equals("logout")) {
			statevalue = 3;
		} else if (method.equals("vlogin")) {
			statevalue = 4;
		} else if (method.equals("adduser")) {
			statevalue = 5;
		} else if (method.equals("validstatus")) {
			statevalue = 6;
		} else if (method.equals("validsubno")) {
			statevalue = 7;
		} else if (method.equals("validinfor")) {
			statevalue = 8;
		} else if (method.equals("getpasssome")) {
			getPassSome(req, resp);
			return;
		} else if (method.equals("getusers")) {
			statevalue = 9;
		} else if (method.equals("finduser")) {
			statevalue = 10;
		} else if (method.equals("firstrefuse")) {
			statevalue = 11;
		} else if (method.equals("participateexam")) {
			statevalue = 12;
		} else if (method.equals("querysubno")) {
			statevalue = 13;
		} else if (method.equals("querystate")) {
			statevalue = 14;
		} else if (method.equals("exampass")) {
			statevalue = 15;
		} else if (method.equals("querysinglexam")) {
			statevalue = 16;
		} else if (method.equals("examrecord")) {
			statevalue = 17;
		}else if (method.equals("canexam")) {
			statevalue = 18;
		}else if (method.equals("queryexam")) {
			statevalue = 19;
		}
		else if (method.equals("forgetpwd")) {
			statevalue=20;
		}else if (method.equals("SuperAgree")) {
			statevalue=21;
		}
		else if (method.equals("deleteAll")) {
			statevalue=22;
		}
		switch (statevalue) {
		case 0:
			doLogin(req, resp);
			break;
		case 1:
			doCorrectPwd(req, resp);
			break;
		case 2:
			doQueryInfor(req, resp);
			break;
		case 3:
			doLogOut(req, resp);
			break;
		case 4:
			vlogin(req, resp);
			break;
		case 5:
			doAddUser(req, resp);
			break;
		case 6:
			doQueryStatus(req, resp);
			break;
		case 7:
			doValidSubNo(req, resp);
			break;
		case 8:
			doValidInfor(req, resp);
			break;
		case 9:
			getUsers(req, resp);
			break;
		case 10:
			findUser(req, resp);
			break;
		case 11:
			doRefuse(req, resp);
			break;
		case 12:
			doParticipateExam(req, resp);
			break;
		case 13:
			doQuerySubno(req, resp);
			break;
		case 14:
			doQueryState(req, resp);
			break;
		case 15:
			doQueryExamPass(req, resp);
			break;
		case 16:
			doQuerySingleExam(req, resp);
			break;
		case 17:
			doExamStatus(req, resp);
			break;
		case 18:
			canExam(req, resp);
			break;
		/*case 19:
			doQueryExam(req, resp);
			break;*/
		case 20:
			doForgetPwd(req,resp);
			break;
		case 21:
			doadvanceAgree(req,resp);
			break;
		case 22:
			doDeleteExam(req,resp);
			break;
		default:
			break;
		}
		statevalue = -1;
	}


/**
 * 一键清空所有的考试信息
 */
private void doDeleteExam(HttpServletRequest req, HttpServletResponse resp) {
	resp.setCharacterEncoding("UTF-8");
    long teaofexam= new TeaOfExamImpl().deleteTeaofExam();
    long teaoftra=new TeaOfTraImple().RemoveTeaofTra();
    long examoftra = new ExamOfTraImple().deleteExamofTra();
    long  examsubject= new ExamSubjectImple().deleteExamSubject();
    long reservelist=new ReserveListDaoImple().RemoveList();
    long examstatus=new ExamStatusImple().RemoveExamStatus();
    long examanswer=new ExamSubjectImple().deleteExamanswer();
    long examquestion=new ExamSubjectImple().deleteExamQuestion();
    List<TraRes> res=new TraResImple().findAll();
    for (TraRes item:res) {
    	List<String> strs = trs.getpath(item.getTri_no());
		String path = PATH_FOLDER
				+ strs.get(1).substring(strs.get(1).indexOf(strs.get(1).split("/")[3]),
						strs.get(1).length());
		String  sourcepath=PATH_FOLDER+strs.get(2).substring(strs.get(2).indexOf(strs.get(2).split("/")[3]), strs.get(2).length());
		System.out.println(sourcepath);
		if (strs.get(0).equals("ppt") || strs.get(0).equals("pptx")) {
			DeleteFile.deleteDirectory(path.subSequence(0, path.length() - 6).toString());
		} else {
			DeleteFile.deleteFile(path);
		}
        if(strs.get(0).equals("ppt") || strs.get(0).equals("pptx") ||strs.get(0).equals("doc") || strs.get(0).equals("docx")){     	
        	DeleteFile.deleteFile(sourcepath);
        }
		trs.del(item.getTri_no());
	}
     tempTable.setId(1);
    JSONObject jsonObject=JSONObject.fromObject(tempTable);
    try {
		resp.getWriter().print(jsonObject);
	} catch (IOException e) {
		e.printStackTrace();
	}
  
	}

/**
   * 教务处跨级审核通过教务办指定科目的报名
   */
	private void doadvanceAgree(HttpServletRequest req, HttpServletResponse resp) {
	    String teano=req.getParameter("teano");
	    String subno=req.getParameter("subno");
	    Integer result= teaOfExamService.UpdateTeacher(subno, teano);
	    try {
			resp.getWriter().print(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 凭借银行卡号修改密码
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void doForgetPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String teano=req.getParameter("teano");
		String cardnumber=req.getParameter("cardnumber");
		String newpwd=req.getParameter("newpwd");
		TempTable tempvar =userService.getCashCard(teano);
		Teacher teacher=null;
		System.out.println(tempvar.getId());
		if(tempvar.getId()==0){
			tempTable.setId(0);
			JSONObject jsonObject=JSONObject.fromObject(tempTable);
			resp.getWriter().print(jsonObject);
		}
		else{
			 teacher=userService.getUserInfor(cardnumber, teano);
			  if(teacher==null){
	          tempTable.setId(2);
	          JSONObject jsonObject=JSONObject.fromObject(tempTable);
	          resp.getWriter().print(jsonObject);
			  }
			  else {
				Integer flag=userService.UpdatePwd(newpwd, teano, cardnumber);
				tempTable.setId(flag);
				JSONObject jsonObject=JSONObject.fromObject(tempTable);
				resp.getWriter().print(jsonObject);
			}
			
		}
		 
	   }

	/**
	 * 导出教师查看考场安排的日志
	 * 
	 * @param req
	 * @param resp
	 */
	private void doGetExamRecord(HttpServletRequest req, HttpServletResponse resp) {
		String subno = req.getParameter("subno");
		String filename = "教师查看考场安排日志";
		resp.setContentType("text/html;charset=UTF-8");
		resp.setContentType("application/xml");
		try {
			resp.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "iso-8859-1") + ".xls");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ExamSubject examSubject=examSubjectService.getExamByObj(subno);
		List<ExamStatus> records = examStatusService.getExamStatus(examSubject.getSub_name());
		HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象
		HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象
		HSSFCell cell = null; // 声明单元格对象
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 10000);
		// head
		String[] bt = new String[] { "教师工号", "教师姓名", "考试科目","第一考场","第二考场", "查询时间" };
		HSSFRow tableRow = sheet.createRow(0); // 创建一个行对象
		tableRow.setHeightInPoints(18);
		for (int i = 0; i < bt.length; i++) {
			cell = tableRow.createCell(i);
			cell.setCellValue(new HSSFRichTextString(bt[i]));
		}
		// 写入表格内容
		for (int i = 0; i < records.size(); i++) {
			tableRow = sheet.createRow(i + 1); // 创建一个行对象
			tableRow.setHeightInPoints(16);
			ExamStatus  cho=records.get(i);
			cell = tableRow.createCell(0);
			cell.setCellValue(new HSSFRichTextString(cho.getTeano()));
			cell = tableRow.createCell(1);
			cell.setCellValue(new HSSFRichTextString(cho.getTeaname()));
			cell = tableRow.createCell(2);
			cell.setCellValue(new HSSFRichTextString(cho.getSub_name()));
			cell = tableRow.createCell(3);
			cell.setCellValue(new HSSFRichTextString(cho.getSubplace()));
			cell=tableRow.createCell(4);
			cell.setCellValue(new HSSFRichTextString(cho.getSubplaceother()));
			cell = tableRow.createCell(5);
			cell.setCellValue(new HSSFRichTextString(cho.getQuerytime() == null ? "未查看" : cho
					.getQuerytime().toString()));
		}
		try {
			workBook.write(resp.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询并记录教师查询考场安排的记录
	 * 
	 * @param req
	 * @param resp
	 */
	private void doExamStatus(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String teano = req.getParameter("teano");
		String sub_name = req.getParameter("subname");
		ExamStatus examStatus= examStatusService.getExamStatus(teano, sub_name);
		Integer result=examStatus==null?0:1;
		JSONObject jsonObject=null;
		if(result==0){
			tempTable.setId(result);
			jsonObject=JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (result==1) {
			jsonObject=new JSONObject();
			jsonObject.put("teano", examStatus.getTeano());
			jsonObject.put("teaname", examStatus.getTeaname());
			jsonObject.put("subname", examStatus.getSub_name());
			jsonObject.put("subplace", examStatus.getSubplace());
			jsonObject.put("subplaceother", examStatus.getSubplaceother());
			examStatusService.updateQueryTime(teano, sub_name);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询特定教师的报名信息
	 */
	private void doQuerySingleExam(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String teano = req.getParameter("teano");
		String subno = req.getParameter("subno");
		Teacher teacher = userService.getTeacherInfor(subno, teano);
		JSONObject jsonObject = JSONObject.fromObject(teacher);
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看考试状态
	 * 
	 * @param req
	 * @param resp
	 */
	private void doQueryExamPass(HttpServletRequest req, HttpServletResponse resp) {
		String tri_no = req.getParameter("tri_no");
		String teano = req.getParameter("teano");
		TeaOfExam result = userService.getExam(teano, Integer.parseInt(tri_no));
		JSONObject jsonObject = JSONObject.fromObject(result);
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 考试中心查看单个人员的报名审核情况
	 * 
	 * @param req
	 * @param resp
	 */
	private void doQueryState(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String teano = req.getParameter("teano");
		String subno = req.getParameter("subno");
		Teacher teacher = userService.getTeacher(teano, subno);
		if (teacher == null) {
			teacher = new Teacher();
			teacher.setTeano("0");
			JSONObject jsonObject = JSONObject.fromObject(teacher);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JSONObject jsonObject = JSONObject.fromObject(teacher);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过科目名称查询科目代码
	 * 
	 * @param req
	 * @param resp
	 */
	private void doQuerySubno(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String subname = req.getParameter("subname");
		String subno = new ExamSubjectImple().getSubNo(subname);
		String str = "";
		if (subno == null || subno.equals("")) {
			tempTable.setState("空值");
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			str += jsonObject.toString();
			try {
				resp.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			tempTable.setState(subno);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 考试中心导出已报名人员信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void doGetUserInfor(HttpServletRequest req, HttpServletResponse resp) {
		String subno = req.getParameter("subno");
		String filename = "报名信息";
		resp.setContentType("text/html;charset=GBK");
		resp.setContentType("application/xml");
		try {
			resp.setHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes(), "iso-8859-1") + ".xls");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         ExamSubject examSubject= examSubjectService.getExamSubject(subno);
		List<Teacher> teas = userService.getRegistUserInfor(subno);
		HSSFWorkbook workBook = new HSSFWorkbook(); // 创建 一个excel文档对象
		HSSFSheet sheet = workBook.createSheet(); // 创建一个工作薄对象
		HSSFCell cell = null; // 声明单元格对象
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 10000);
		// head
		String[] bt = new String[] {"科目", "教师工号", "教师姓名", "联系电话", "所在学院", "性别", "家庭住址", "银行卡号", "审核状态",
				"学习状态", "考试状态" };
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
			Teacher cho = teas.get(i);
			cell=tableRow.createCell(0);
			cell.setCellValue(new HSSFRichTextString(examSubject.getSub_name()));
			cell = tableRow.createCell(1);
			cell.setCellValue(new HSSFRichTextString(cho.getTeano()));
			cell = tableRow.createCell(2);
			cell.setCellValue(new HSSFRichTextString(cho.getTeaname()));
			cell = tableRow.createCell(3);
			cell.setCellValue(new HSSFRichTextString(String.valueOf(cho.getTeatel())));
			cell = tableRow.createCell(4);
			cell.setCellValue(new HSSFRichTextString(cho.getSubordunit()));
			cell = tableRow.createCell(5);
			cell.setCellValue(new HSSFRichTextString(cho.getTeasex() == 1 ? "女" : "男"));
			cell = tableRow.createCell(6);
			cell.setCellValue(new HSSFRichTextString(cho.getTeaaddress()));
			cell = tableRow.createCell(7);
			cell.setCellValue(new HSSFRichTextString(cho.getCashcardid()));
			if (cho.getSignstate() == 1 && cho.getSchoolstate() == 1) {
				cell = tableRow.createCell(8);
				cell.setCellValue(new HSSFRichTextString("教务办审核中"));
			} else if (cho.getSignstate() == 0 && cho.getSchoolstate() == 1) {
				cell = tableRow.createCell(8);
				cell.setCellValue(new HSSFRichTextString("教务处审核中"));
			} else if (cho.getSignstate() == 0 && cho.getSchoolstate() == 0) {
				cell = tableRow.createCell(8);
				cell.setCellValue(new HSSFRichTextString("审核通过"));
			}
			if (cho.getExampass() == 1) {
				cell = tableRow.createCell(9);
				cell.setCellValue(new HSSFRichTextString((cho.getStudypassTime()).toString()+"完成学习"));
				cell = tableRow.createCell(10);
				cell.setCellValue(new HSSFRichTextString((cho.getExampassTime()).toString()+"完成考试"));
			} else if (cho.getSignstate() == 0 && cho.getSchoolstate() == 0) {
				  if(cho.getStudypassTime()==null){
						cell = tableRow.createCell(9);
						cell.setCellValue(new HSSFRichTextString("正在学习"));
						cell = tableRow.createCell(10);
						cell.setCellValue(new HSSFRichTextString("未通过考试"));
				  }else if (cho.getExampassTime()==null) {
						cell = tableRow.createCell(9);
						cell.setCellValue(new HSSFRichTextString(cho.getStudypassTime().toString()+"完成学习"));
						cell = tableRow.createCell(10);
						cell.setCellValue(new HSSFRichTextString("未通过考试"));
				}
			} else if (cho.getSchoolstate() == 1 || cho.getSignstate() == 1) {
				cell = tableRow.createCell(9);
				cell.setCellValue(new HSSFRichTextString("未学习"));
				cell = tableRow.createCell(10);
				cell.setCellValue(new HSSFRichTextString("未通过考试"));
			}
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

	/**
	 * 验证是否真的审核通过
	 * 
	 * @param req
	 * @param resp
	 */
	private void doValidSubNo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String teano = req.getParameter("teano");
		String triname = req.getParameter("fname");
		String subno=req.getParameter("selectValue");
		Integer schoolstate =teaOfExamService.getSchoolState(triname, teano,subno);
		tempTable.setId(schoolstate);
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("id", schoolstate.toString());
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 考试通过，查看考场分布
	 * 
	 * @param req
	 * @param resp
	 */
	/*
	private void doQueryExam(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		List<TeaOfExam> exam = userService.getSubNo(username, "querysingle");
		if(exam.size() == 0)
		{
			try {
				resp.getWriter().print(JSONObject.fromObject(null));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (TeaOfExam item : exam) {
			ExamSubject subject = new ExamSubjectService().getExamSubject(item.getSub_no());
			ExamStatus examStatus = userService.getExamInfor(username, subject.getSub_name());
			JSONObject jsonObject = JSONObject.fromObject(examStatus);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}*/

	/**
	 * 是否已完成考试
	 * 
	 * @param req
	 * @param resp
	 */
	private void doIsFinishExam(HttpServletRequest req, HttpServletResponse resp) {
		String teacherid = req.getParameter("username");
		String subno = req.getParameter("subno");
		TeaOfExam result = userService.IsFinishExam(teacherid, subno);
		tempTable.setId(result.getExampass());
		JSONObject jsonObject = JSONObject.fromObject(tempTable);
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void getPassCount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().write(userService.getPassCount().toString());
	}

	protected void getPassSome(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Integer page = Integer.parseInt(req.getParameter("page"));
		if (page == null || page == 0) {
			page = 1;
		}
		Integer count = Integer.parseInt(req.getParameter("count"));
		String subno = req.getParameter("subno");
		List<Teacher> teas = userService.getPassSome(page, count, subno);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().print(JSONArray.fromObject(teas));

	}

	/**
	 * 完成考试,修改相应的字段
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void finishExam(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String teacherid = req.getParameter("username");
		String subno = req.getParameter("subno");
		//如果考试已经通过，还继续参加考试，则最终考试完成时间以第一次为准
		 if(teaOfExamService.getTeaOfExam(subno, teacherid).getExampassTime()!=null){
            tempTable.setId(1);
		    JSONObject jsonObject=JSONObject.fromObject(tempTable);
		    resp.getWriter().print(jsonObject);
		    return;
		 }
			
		teaOfExamService.updateExamPasstime(subno, teacherid);
		Integer result = userService.finishExam(teacherid, subno);
		tempTable.setId(result);
		JSONObject jsonObject = JSONObject.fromObject(tempTable);
		resp.getWriter().print(jsonObject);
	}

	/**
	 * 是否已经参加完培训学习
	 */
	protected void canExam(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		List<Teacher> flag;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		String teacherid = req.getParameter("username");
		String subno=req.getParameter("subno");
		
	//	List<TeaOfExam> teaOfExam = userService.getSubNo(teacherid, "all");
		//for (TeaOfExam item : teaOfExam) {
			flag = userService.canExam(teacherid, subno);
			for (Teacher data : flag) {
				jsonObject.put("watchtime", data.getTeano());
				jsonObject.put("tri_name", data.getTeaname());
				jsonObject.put("tri_type", data.getTeatel());
				jsonObject.put("tri_newpath", data.getTeaaddress());
				jsonObject.put("tri_countpage", data.getTeapwd() == null ? 0 : data.getTeapwd());
				jsonObject.put("tri_no", data.getAuthority());
				jsonObject.put("tri_time", data.getTritime());
				jsonObject.put("tri_pagetime",
						data.getSchoolstate() == null ? 0 : data.getSchoolstate());
				jsonObject.put("exampass", data.getExampass());
				jsonObject.put("tri_muststudy", data.getSignstate());
				jsonObject.put("tri_candownload", data.getTeasex());
				jsonObject.put("tri_path", data.getTri_path());
				jsonArray.add(jsonObject);
			}
		resp.getWriter().print(jsonArray);
	}

	// 查看该科目的学习是否已完成
	private void doParticipateExam(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		String subname = req.getParameter("subname");
		String teano = req.getParameter("teano");
		String subno = new ExamSubjectImple().getSubNo(subname);
		String str = "";
		if (subno == null) {
			tempTable.setState("空值");
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			str += jsonObject.toString();
			try {
				resp.getWriter().print(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			List<TraRes> result = new TraResServices().IsFinishResource(teano, subno);
			long watchtime=new TraResServices().HasFinishTra(teano,subno);
            if(watchtime==result.size() && watchtime>0){
            	tempTable.setState("完成");
                JSONObject jsonObject=JSONObject.fromObject(tempTable);
                str+=jsonObject.toString();
                try {
					resp.getWriter().print(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            else if (watchtime<result.size()) {
            	tempTable.setState("未完成");
                JSONObject jsonObject=JSONObject.fromObject(tempTable);
                str+=jsonObject.toString();
                try {
					resp.getWriter().print(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            
		  }
	}

	/**
	 * 教务办或者教务处拒绝特定考生的报名
	 * 
	 * @param req
	 * @param resp
	 */
	private void doRefuse(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String teano = req.getParameter("teano");
		String subno = req.getParameter("subno");
		String authority = req.getParameter("authority");
		Integer result = userService.RefuseRegist(teano, subno, Integer.parseInt(authority));
		tempTable.setId(result);
		JSONObject jsonObject = JSONObject.fromObject(tempTable);
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看用户信息是否完整
	 * 
	 * @param req
	 * @param resp
	 */
	private void doValidInfor(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		Teacher teacher = userService.InforIsComplete(username);
		tempTable.setId(teacher.getTeasex());
		JSONObject jsonObject = JSONObject.fromObject(tempTable);
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 通过查询报名审核状态来确定自己是否有学习培训资源的权限
	 */
	private void doQueryStatus(HttpServletRequest req, HttpServletResponse resp) {
		String teano = req.getParameter("teano");
		TeaOfExam objExam = userService.geTeaOfExam(teano);
		tempTable.setId(objExam.getSchoolstate());
		JSONObject object = JSONObject.fromObject(tempTable);
		try {
			resp.getWriter().print(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 添加用户信息
	 */
	private void doAddUser(HttpServletRequest req, HttpServletResponse resp) {
		String teaname = req.getParameter("teaname");
		String teano = req.getParameter("teano");
		String teatel = req.getParameter("teatel");
		String cardnumber = req.getParameter("cardnumber");
		String teaaddress = req.getParameter("teaaddress");
		String subordunit = req.getParameter("subordunit");
		String authority = req.getParameter("authority");
		String teasex = req.getParameter("teasex");
		switch (Integer.parseInt(teaaddress)) {
		case 1:
			teaaddress = "本部";
			break;
		case 2:
			teaaddress = "本部附近";
			break;
		case 3:
			teaaddress = "南区";
			break;
		case 4:
			teaaddress = "南区附近";
			break;
		case 5:
			teaaddress = "北区";
			break;
		case 6:
			teaaddress = "北区附近";
			break;
		case 7:
			teaaddress = "其它";
			break;
		default:
			break;
		}
		List<Teacher> users = userService.get(teano);
		System.out.println(users.size());
		if (users.size() > 0) {
			tempTable.setId(0);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Teacher teacher = new Teacher();
			teacher.setTeano(teano);
			teacher.setTeapwd(teano);
			teacher.setTeasex(Integer.parseInt(teasex));
			teacher.setTeatel(teatel);
			teacher.setSubordunit(subordunit);
			teacher.setCashcardid(cardnumber);
			teacher.setAuthority(Integer.parseInt(authority));
			teacher.setTeaaddress(teaaddress);
			teacher.setTeaname(teaname);
			boolean flag = userService.SaveOrUpdateUser(teacher);
			if (flag == true) {
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

	/**
	 * 退出登录
	 */
	@SuppressWarnings("static-access")
	private void doLogOut(HttpServletRequest req, HttpServletResponse resp) {
	
			String username = req.getParameter("username");
			System.out.println(sessionCounter.IsOnline(username));
			FileUpLoadAction2.inisave();
			if (!sessionCounter.IsOnline(username)){
				// 删除session,用户名
				//req.getSession().removeAttribute("username");
				 sessionCounter.hUserName.remove(username);
			}
			System.out.println("在线用户人数:" +sessionCounter.hUserName.size());
			TempTable tempTable = new TempTable();
			tempTable.setId(0);
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * 查询人员信息
	 */
	private void doQueryInfor(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // 设置编码
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String teano = req.getParameter("teano");

		List<Teacher> teachers = userService.get(teano);
		JSONObject jsonObject = JSONObject.fromObject(teachers.get(0));
		try {
			resp.getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改个人信息
	 */
	private void doCorrectPwd(HttpServletRequest req, HttpServletResponse resp) {
		TempTable tempTable = new TempTable();
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // 设置编码
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String oldpwd = req.getParameter("oldpwd");
		String newpwd = req.getParameter("newpwd");
		String conpwd = req.getParameter("conpwd");
		String teaname = req.getParameter("teaname");
		String teatel = req.getParameter("teatel");
		String cardnumber = req.getParameter("cardnumber");
		String teasex = req.getParameter("teasex");
		String teaaddress = req.getParameter("teaaddress");
		String subordunit = req.getParameter("subordunit");
		String teano = req.getParameter("teano");
		switch (Integer.parseInt(teaaddress)) {
		case 1:
			teaaddress = "本部";
			break;
		case 2:
			teaaddress = "本部附近";
			break;
		case 3:
			teaaddress = "南区";
			break;
		case 4:
			teaaddress = "南区附近";
			break;
		case 5:
			teaaddress = "北区";
			break;
		case 6:
			teaaddress = "北区附近";
			break;
		default:
			teaaddress = "其它";
			break;
		}
		// 只修改用户信息，不修改密码
		if (oldpwd.equals("") && newpwd.equals("") && conpwd.equals("") && !teaname.equals("")
				&& !teatel.equals("") && !cardnumber.equals("") && !teasex.equals("")
				&& !subordunit.equals("")) {
			boolean flag = userService.CorrectInfor(teano, teaname, cardnumber,
					Integer.parseInt(teasex), subordunit, teatel, teaaddress);
			if (flag == true)
				tempTable.setState("successful");
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		// 修改密码和用户信息
		if (teaname.equals("") || teatel.equals("") || cardnumber.equals("")
				|| subordunit.equals("")) {
			tempTable.setState("用户信息不能为空");
			JSONObject jsonObject = JSONObject.fromObject(tempTable);
			try {
				resp.getWriter().print(jsonObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (oldpwd.equals("")) {
				tempTable.setState("原始密码不能为空");
				JSONObject jsonObject = JSONObject.fromObject(tempTable);
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (newpwd.equals("")) {
				tempTable.setState("新密码不能为空");
				JSONObject jsonObject = JSONObject.fromObject(tempTable);
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (conpwd.equals("")) {
				tempTable.setState("确认密码不能为空");
				JSONObject jsonObject = JSONObject.fromObject(tempTable);
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (!newpwd.equals(conpwd)) {
				tempTable.setState("确认密码与新密码不一致");
				JSONObject jsonObject = JSONObject.fromObject(tempTable);
				try {
					resp.getWriter().print(jsonObject);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				boolean flag = false;
				flag = userService.CorrectPasswd(newpwd, oldpwd, teano, teaname, cardnumber,
						Integer.parseInt(teasex), subordunit, teatel, teaaddress);
				if (flag) {
					try {
						tempTable.setState("successful");
						JSONObject jsonObject = JSONObject.fromObject(tempTable);
						resp.getWriter().print(jsonObject);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (!flag) {
					try {
						tempTable.setState("failure");
						JSONObject jsonObject = JSONObject.fromObject(tempTable);
						resp.getWriter().print(jsonObject);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	/*
	 * 登陆
	 */
	@SuppressWarnings("static-access")
	private void doLogin(HttpServletRequest req, HttpServletResponse resp) {
		// 将用户名作为hashtable的key,客户端IP作为value，采用下面的方法是避免Apache去反向代理
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("uname");
		String passwd = req.getParameter("passwd");
		authority = Integer.parseInt(req.getParameter("teacher"));
		if (username.equals("") || passwd.equals("")) {
			try {
				resp.getWriter().print("null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//验证身份
			boolean flag = userService.Validate(username, passwd, authority);
			if (flag) {
				//判断同一个用户是否在两台不同的机器上登录
				boolean islogin = sessionCounter.isLogin(username, ip);
				// 用户名存入session
				//req.getSession().setAttribute("username", username);
				if (islogin == false) {
					try {
						resp.getWriter().print("successful");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						resp.getWriter().print("already");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().print("failure");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("static-access")
	private void vlogin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("uname");
		String passwd = req.getParameter("passwd");
		authority = Integer.parseInt(req.getParameter("teacher"));
		if (username.equals("") || passwd.equals("")) {
			try {
				resp.getWriter().print("null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			boolean flag = userService.Validate(username, passwd, authority);
			if (flag) {
				try {
					resp.getWriter().print("true");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().print("false");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void getUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String m = req.getParameter("method");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		if (m.equals("count")) {
			out.print(userService.getAllUserCount());
		} else if (m.equals("content")) {
			Integer page = Integer.valueOf(req.getParameter("page"));
			Integer count = Integer.valueOf(req.getParameter("count"));
			JSONArray jsonArray = JSONArray.fromObject(userService.getAlUsersPage(page, count));
			out.print(jsonArray);
		} else {
			out.print("fail");
		}
	}

	private void findUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String k = req.getParameter("key");
		resp.setCharacterEncoding("UTF-8");
		JSONArray ja = JSONArray.fromObject(userService.getTeacher(k));
		resp.getWriter().print(ja);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext=config.getServletContext();
		PATH_FOLDER=servletContext.getRealPath("BackGround")+"\\";
	}
	
	
}
