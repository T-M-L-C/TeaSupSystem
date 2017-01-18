package com.backbone.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.backbone.dao.TeaOfExamDao;
import com.backbone.dao.implement.TeaOfExamImpl;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.AgreeTeacherInfo;
import com.backbone.entity.vo.DynamicSearchCondition;
import com.backbone.entity.vo.JsonResult;
import com.backbone.entity.vo.DynamicSearchCondition.DynamicSearchConditionAssist;
import com.backbone.service.AgreeTeacherInfoServices;
import com.backbone.service.ReserveListService;
import com.backbone.service.UserServices;

public class DynamicSearchServlet extends HttpServlet {
	private AgreeTeacherInfoServices atis = new AgreeTeacherInfoServices();
	 private UserServices us = new UserServices();
     private ReserveListService reserveListService=new ReserveListService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			return;
		}
		if (action.equals("agreeSearch")) {
			getAgreeSome(req, resp);
		}
		else if(action.equals("teacher"))
		{
			getTeacher(req, resp);
		}
		else if (action.equals("AddTeacherMenu")) {
			doAddTeacherMenu(req,resp);
			
		}
	}

  /**
   * 批量添加考试候选名单
   */
	private void doAddTeacherMenu(HttpServletRequest req,
			HttpServletResponse resp) {
		resp.setContentType("text/html;charset=UTF-8");

		//Object username = req.getSession().getAttribute("username");
		String username=req.getParameter("username");
		String subno=req.getParameter("subno");
		String subname=req.getParameter("subname");
		/*if(subname.indexOf("口试")==-1){
			try {
				resp.getWriter().print("0");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}*/
		Teacher t = us.get(username.toString()).get(0);
		if (t.getAuthority() != 1 && t.getAuthority() != 2 && t.getAuthority()!=3)
			return;
		// 查询条件，格式：DynamicSearchConditionAssist数组
		// 第几页
		Integer page;
		// 一页几条
		Integer count;
		JSONObject conditionJsonObj;
		DynamicSearchCondition<AgreeTeacherInfo> dsc = new DynamicSearchCondition<AgreeTeacherInfo>();
		try {
			JSONArray conditionJson = JSONArray.fromObject(req.getParameter("sc"));

			@SuppressWarnings("unchecked")
			List<DynamicSearchConditionAssist> ds = JSONArray.toList(conditionJson,
					DynamicSearchConditionAssist.class);
		
			if (t.getAuthority() == 1) {
				DynamicSearchConditionAssist dsca = new DynamicSearchConditionAssist();
				dsca.setPname("subordunit");
				dsca.setV(t.getSubordunit());
				ds.add(dsca);
			}
			dsc.ini(new AgreeTeacherInfo(), ds);
			page = Integer.valueOf(req.getParameter("page").toString());
			count = Integer.valueOf(req.getParameter("count").toString());
		} catch (NullPointerException e) {
			return;
		}
		
		  ExamSubject subject=new ExamSubject();
		  subject.setSub_name(subname);
		  subject.setSub_no(subno);
		  JsonResult jr = new JsonResult();
      	  List<Teacher> lstAti = atis.getSomeTeacher(dsc, page, count);
          
          for(int i=0;i<lstAti.size();i++){
        	  if(reserveListService.findInforById(lstAti.get(i).getTeano(), subno)==null){
            	  reserveListService.SaveReserveObj(subject, lstAti.get(i));
        	  }else {
				continue;
			}
          }
          try {
			resp.getWriter().print("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	protected void getTeacher(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
        String subno=req.getParameter("subno");
		String username=req.getParameter("username");
		Teacher t = us.get(username.toString()).get(0);
		if (t.getAuthority() != 3 )
			return;
		    if(subno==null){
		    	// 查询条件，格式：DynamicSearchConditionAssist数组
				// 第几页
				Integer page;
				// 一页几条
				Integer count;
				JSONObject conditionJsonObj;
				DynamicSearchCondition<AgreeTeacherInfo> dsc = new DynamicSearchCondition<AgreeTeacherInfo>();
				try {
					JSONArray conditionJson = JSONArray.fromObject(req.getParameter("sc"));

					@SuppressWarnings("unchecked")
					List<DynamicSearchConditionAssist> ds = JSONArray.toList(conditionJson,
							DynamicSearchConditionAssist.class);
				
					if (t.getAuthority() == 1) {
						DynamicSearchConditionAssist dsca = new DynamicSearchConditionAssist();
						dsca.setPname("subordunit");
						dsca.setV(t.getSubordunit());
						ds.add(dsca);
					}
					dsc.ini(new AgreeTeacherInfo(), ds);
					page = Integer.valueOf(req.getParameter("page").toString());
					count = Integer.valueOf(req.getParameter("count").toString());
				} catch (NullPointerException e) {
					return;
				}
				  JsonResult jr = new JsonResult();
				    
                	List<Teacher> lstAti = atis.getSomeTeacher(dsc, page, count);
    			
    				JSONArray jo = JSONArray.fromObject(lstAti);
    				if(jo.size() ==0)
    				{
    					Teacher tea =new Teacher();
    					tea.setTeano("0");
    					jr.setData(JSONObject.fromObject(tea).toString());
    					jr.setSum((long)1);
    				}
    				else
    				{
    					jr.setData(jo.toString());
    					jr.setSum(atis.getSomeTeacherCount(dsc));
    				}
				resp.getWriter().write(JSONObject.fromObject(jr).toString());
		    	
		    }
	
	}

	@SuppressWarnings("deprecation")
	protected void getAgreeSome(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");

		//Object username = req.getSession().getAttribute("username");
		String username=req.getParameter("username");
		Teacher t = us.get(username.toString()).get(0);
		if (t.getAuthority() != 1 && t.getAuthority() != 2 && t.getAuthority()!=3)
			return;
		String agreeThis = req.getParameter("isAgree");
		// 查询条件，格式：DynamicSearchConditionAssist数组
		AgreeTeacherInfo ati = new AgreeTeacherInfo();
		// 第几页
		Integer page;
		// 一页几条
		Integer count;
		JSONObject conditionJsonObj;
		DynamicSearchCondition<AgreeTeacherInfo> dsc = new DynamicSearchCondition<AgreeTeacherInfo>();
		try {
			JSONArray conditionJson = JSONArray.fromObject(req.getParameter("sc"));

			@SuppressWarnings("unchecked")
			List<DynamicSearchConditionAssist> ds = JSONArray.toList(conditionJson,
					DynamicSearchConditionAssist.class);
		
			if (t.getAuthority() == 1) {
				DynamicSearchConditionAssist dsca = new DynamicSearchConditionAssist();
				dsca.setPname("subordunit");
				dsca.setV(t.getSubordunit());
				ds.add(dsca);
			}
			dsc.ini(new AgreeTeacherInfo(), ds);
			page = Integer.valueOf(req.getParameter("page").toString());
			count = Integer.valueOf(req.getParameter("count").toString());

		} catch (NullPointerException e) {
			return;
		}

		JsonResult jr = new JsonResult();
		List<AgreeTeacherInfo> lstAti = atis.getSome(dsc, page, count);
		if(agreeThis!=null &&agreeThis.length()>0)
		{
			TeaOfExamDao toed = new TeaOfExamImpl();
			StringBuffer str = new StringBuffer();
			str.append("总计"+lstAti.size()+"条，成功");
			int sum=0;
			if(t.getAuthority() == 1)
			{
				for (AgreeTeacherInfo item : lstAti) {
					sum+=toed.updateRegister(0, item.getTeano(), item.getSub_no());
				}
			}
			else if (t.getAuthority() == 2)
			{
				for (AgreeTeacherInfo item : lstAti) {
					sum+=toed.AdvanceAgree(item.getSub_no(), item.getTeano());
				}
			}
			str.append(sum);
			str.append("条");
			System.out.println(sum);
			resp.getWriter().write(str.toString());
			return;
		}
		JSONArray jo = JSONArray.fromObject(lstAti);
		if(jo.size() ==0)
		{
			Teacher tea =new Teacher();
			tea.setTeano("0");
			jr.setData(JSONObject.fromObject(tea).toString());
			jr.setSum((long)1);
		}
		else
		{
			jr.setData(jo.toString());
			jr.setSum(atis.getSomeCount(dsc));
		}
		resp.getWriter().write(JSONObject.fromObject(jr).toString());
	}


}
