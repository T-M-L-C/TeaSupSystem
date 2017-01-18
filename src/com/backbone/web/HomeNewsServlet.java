package com.backbone.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.backbone.entity.po.HomeNews;
import com.backbone.service.HomeNewsServices;

public class HomeNewsServlet extends HttpServlet{
	HomeNewsServices newsService = new HomeNewsServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<HomeNews> lstHomeNews = newsService.getTop10();
		req.setAttribute("lstHomeNews", lstHomeNews);
		try {
			req.getRequestDispatcher("/home.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		 if (action.equals("addnews")) {
			doAddNews(req,resp);
		}
		 else if (action.equals("queryAll")) {
			doQueryAll(req,resp);
		}
		 else if (action.equals("deleteNews")) {
			doDeleteNews(req,resp);
		}
		 else if (action.equals("editNews")) {
			doEditNews(req,resp);
		}
	}
	
	/**
	 * 编辑指定ID对应的通知内容
	 */
	private void doEditNews(HttpServletRequest req, HttpServletResponse resp) {
		  String id=req.getParameter("id");
		  String content=req.getParameter("content");
		  HomeNews homeNews=new HomeNews();
		  homeNews.setHomenewscontent(content);
		  homeNews.setHomeNewsNo(Integer.parseInt(id));
		  long editresult=  newsService.EditHomeNews(homeNews);
		  try {
			resp.getWriter().print(editresult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定ID对应的新闻通知
	 */
	private void doDeleteNews(HttpServletRequest req, HttpServletResponse resp) {
		String id=req.getParameter("id");
		long flag= newsService.deleteHomeNewsInfor(Integer.valueOf(id));
		try {
			resp.getWriter().print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有的新闻通知
	 */
	private void doQueryAll(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/jsp");
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		List<HomeNews> homeNews=newsService.getAllHomeNews();
		for(HomeNews item:homeNews){
			  jsonObject.put("homeNewsNo", item.getHomeNewsNo());
			  jsonObject.put("homeNewsContent", item.getHomenewscontent());
			  jsonObject.put("homeNewsPublishTime", item.getHomenewspublishtime().toString());
			  jsonArray.add(jsonObject);
		}
		try {
			resp.getWriter().print(jsonArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 添加通知
	 */
  private void doAddNews(HttpServletRequest req, HttpServletResponse resp) {
	    HomeNews homeNews=new HomeNews();
		String newscontent=req.getParameter("newscontent");
		homeNews.setHomenewscontent(newscontent);
		long result=newsService.AddNews(homeNews);
		try {
			resp.getWriter().print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
