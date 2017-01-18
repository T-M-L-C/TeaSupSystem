package com.backbone.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backbone.common.Common;

public class GuideServlet extends HttpServlet{

	private static String GUIDE;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 设置编码
		resp.setCharacterEncoding("utf-8");
		
		String funcion = req.getParameter("function");
		File guideFile = new File(GUIDE);
		if(funcion.equals("getOld")){
			String flag = req.getParameter("text");
			Reader reader = new InputStreamReader(new FileInputStream(guideFile),"UTF-8");
			char[] buffer = new char[(int)guideFile.length()];
			try {
				reader.read(buffer);
				String str = new String(buffer);
				if(flag!=null && flag.length()>0)
					str = Common.escapeHTMLTags(str);
				resp.getWriter().write(str);
			}
			catch(Exception e)
			{
				resp.getWriter().write("false");
			}
			finally{
				reader.close();
			}
		}
		else if(funcion.equals("replace")) {
			Boolean result = true;

			String content = req.getParameter("content");

			Writer writer = new OutputStreamWriter(new FileOutputStream(guideFile),"UTF-8");
			try {
				writer.write(content);
			}
			catch (Exception e) {
				result = false;
			}
			finally{
				writer.close();
			}
			
			resp.getWriter().write(result.toString());
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		GUIDE = servletCtx.getRealPath("/guide.txt");
	}
}
