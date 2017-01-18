package com.backbone.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.backbone.common.DeleteFile;
import com.backbone.entity.po.TraRes;
import com.backbone.service.TeaOfExamService;
import com.backbone.service.TraResServices;

public class TraServlet extends HttpServlet {
	private static TraResServices trs = new TraResServices();
	private static TeaOfExamService teaOfExamService=new TeaOfExamService();
	private static String PATH_FOLDER = "/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String str = req.getParameter("method");
		if (str.equals("getcontent")) {
			getsome(req, resp);
		} else if (str.equals("getcontents")) {
			getalltra(req, resp);
		} else if (str.equals("getcounts")) {
			getcounts(req, resp);
		} else if (str.equals("edit")) {
			edit(req, resp);
		} else if (str.equals("getsimcontent")) {
			simple(req, resp);
		} else if (str.equals("del")) {
			del(req, resp);
		} else if (str.equals("studying")) {
			gotime(req, resp);
		} else if (str.equals("gettra")) {
			getTrares(req, resp);
		}
		else if (str.equals("studypasstime")) {
			queryStudyPassTime(req,resp);
		}
	}
   /**
    * 查询必修课程是否已完成学习
    */
	private void queryStudyPassTime(HttpServletRequest req,
			HttpServletResponse resp) {
		   String teano=req.getParameter("teano");
		   String subno=req.getParameter("subno");
		   //如果已经完成学习，而又继续选择学习，则以第一次完成学习时间为准
		   if(teaOfExamService.getTeaOfExam(subno, teano).getStudypassTime()!=null)
			   return;
		   List<TraRes> result = new TraResServices().IsFinishResource(teano, subno);
		   long watchtime= new TraResServices().HasFinishTra(teano, subno);
			  if(watchtime==result.size() && watchtime>0){
				teaOfExamService.updatequeryTime(subno, teano);
			  }
	}

	/**
	 * 查询所有的培训资源
	 * 
	 */
	private void getalltra(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String parameter = req.getParameter("param");
		String subno = req.getParameter("subno");
		Integer pagecount = Integer.parseInt(req.getParameter("pagecount"));
		Integer number = Integer.parseInt(req.getParameter("pagecountmax"));
		String username = req.getParameter("username");
		resp.setCharacterEncoding("UTF-8");
		String str = "[";
		temp t = null;
		List<TraRes> traRes = trs.getsome(pagecount, number, username, parameter, subno);
		if (traRes.size() == 0) {
			t = new temp();
			t.no = "0";
			JSONObject jo = JSONObject.fromObject(t);
			str += jo.toString() + ",";
			str = str.substring(0, str.length() - 1);
			str += "]";
			// 返给ajax请求
			try {
				resp.getWriter().print(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (TraRes item : traRes) {
				t = new temp();
				t.no = item.getTri_no().toString();
				t.name = item.getTri_name();
				t.type = item.getTri_type();
				t.time = item.getTri_time().toString();
				t.path = item.getTri_newpath();
				t.setLen(item.getTri_countpage() == null ? 0 : item.getTri_countpage());
				t.mintime = item.getTri_pagetime();
				t.tri_CanDownLoad = item.getTri_candownload();
				t.tri_MustStudy = item.getTri_muststudy();
				JSONObject jo = JSONObject.fromObject(t);
				str += jo.toString() + ",";
			}
		}
		str = str.substring(0, str.length() - 1);
		str += "]";
		// 返给ajax请求
		try {
			resp.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void getTrares(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String subno = "";
		Integer page = 0, count = 0;
		try {
			subno = req.getParameter("sc").toString();
			page = Integer.valueOf(req.getParameter("page"));
			count = Integer.valueOf(req.getParameter("count"));
		} catch (NullPointerException e) {
			return;
		}
		resp.setCharacterEncoding("UTF-8");
		List<TraRes> lstAllTrares = trs.getAll("all", subno, page, count);
		List<TraRes> lstTrares = trs.getAll("distinct", subno, null, null);
		JSONArray ja = new JSONArray();
		int i = 0;
		for (TraRes item : lstAllTrares) {
			JSONObject o = JSONObject.fromObject(item);
			for (i = 0; i < lstTrares.size(); i++) {
				if (item.getTri_no() == lstTrares.get(i).getTri_no()) {
					o.put("add", 2);
					ja.add(o);
					break;
				}
			}
			if (i == lstTrares.size()) {
				o.put("add", 1);
				ja.add(o);
			}
		}
		JSONObject res = new JSONObject();
		res.put("data", ja);
		res.put("sum", trs.getAllCount());
		resp.getWriter().write(res.toString());
	}

	protected void gotime(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer resid = Integer.parseInt(req.getParameter("srcid"));
		Integer process = Integer.parseInt(req.getParameter("watched"));
		String teacherid = req.getParameter("username");
		trs.studying(teacherid, resid, process);
	}

	protected void getcounts(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object teacherid = req.getParameter("username");
		String subno=req.getParameter("subno");
		Long count = trs.getCounts(teacherid == null ? null : teacherid.toString(),subno);
		resp.getWriter().write(count.toString());

	}

	protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		List<String> strs = trs.getpath(id);
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
		trs.del(id);
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			String rname = req.getParameter("name");
			String mintime = req.getParameter("time");
			Integer download = Integer.parseInt(req.getParameter("download"));
			Integer study = Integer.parseInt(req.getParameter("study"));
			if(id == null || rname == null )
			{
				resp.getWriter().write("0");
				return;
			}
			TraRes traRes = new TraRes();
			traRes.setTri_no(id);
			traRes.setTri_name(rname);
			if(mintime!=null && !mintime.equals(""))
				traRes.setTri_pagetime(mintime);
			traRes.setTri_candownload(download);
			traRes.setTri_muststudy(study);
			Integer result = trs.update(traRes);
			resp.getWriter().write(result.toString());
		} catch (Exception e) {
			resp.getWriter().write("0");
		}
	}

	protected void simple(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/json");
		resp.setCharacterEncoding("UTF-8");
		String str = "[";
		temp t = null;
		for (TraRes item : trs.getAll("all", "",null,null)) {
			t = new temp();
			t.no = item.getTri_no().toString();
			t.name = item.getTri_name();
			JSONObject jo = JSONObject.fromObject(t);
			str += jo.toString() + ",";
		}
		str = str.substring(0, str.length() - 1);
		str += "]";
		// 返给ajax请求
		resp.getWriter().print(str);
	}

	protected void getsome(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String subno = req.getParameter("subno");
		String tri_type = req.getParameter("tri_type");
		String tri_time = req.getParameter("tri_time");
		resp.setContentType("text/json");
		resp.setCharacterEncoding("UTF-8");
		String str = "[";
		temp t = null;
		TraRes traRes = trs.getRes(subno, tri_time, tri_type);
		t = new temp();
		t.no = traRes.getTri_no().toString();
		t.name = traRes.getTri_name();
		t.type = traRes.getTri_type();
		t.time = traRes.getTri_time().toString();
		t.path = traRes.getTri_newpath();
		t.setLen(traRes.getTri_countpage() == null ? 0 : traRes.getTri_countpage());
		t.mintime = traRes.getTri_pagetime();
		t.subno = subno;
		t.tri_CanDownLoad=traRes.getTri_candownload();
		t.tri_MustStudy=traRes.getTri_muststudy();
		JSONObject jo = JSONObject.fromObject(t);
		str += jo.toString() + ",";
		str = str.substring(0, str.length() - 1);
		str += "]";
		// 返给ajax请求
		resp.getWriter().print(str);

	}

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化路径
				// 保存文件的目录
		ServletContext servletContext=config.getServletContext();
        PATH_FOLDER=servletContext.getRealPath("BackGround")+"\\";
	}


	/*@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 初始化路径
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath("BackGround") + "\\";
	}
*/
	public class temp {
		String no;
		String type;
		String name;
		String time;
		String path;
		Integer len;
		String mintime;
		Integer processtime;
		String subno;
        Integer tri_MustStudy;
        Integer tri_CanDownLoad;
		public String getSubno() {
			return subno;
		}

		public void setSubno(String subno) {
			this.subno = subno;
		}

		public Integer getProcesstime() {
			return processtime;
		}

		public void setProcesstime(Integer processtime) {
			this.processtime = processtime;
		}

		public String getMintime() {
			return mintime;
		}

		public void setMintime(String mintime) {
			this.mintime = mintime;
		}

		public Integer getLen() {
			return len;
		}

		public void setLen(Integer len) {
			this.len = len;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public Integer getTri_MustStudy() {
			return tri_MustStudy;
		}

		public void setTri_MustStudy(Integer tri_MustStudy) {
			this.tri_MustStudy = tri_MustStudy;
		}

		public Integer getTri_CanDownLoad() {
			return tri_CanDownLoad;
		}

		public void setTri_CanDownLoad(Integer tri_CanDownLoad) {
			this.tri_CanDownLoad = tri_CanDownLoad;
		}
    
	}
}
