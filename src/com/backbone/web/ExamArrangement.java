package com.backbone.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.backbone.common.DeleteFile;
import com.backbone.common.POIReadExcel;
import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.Teacher;
import com.backbone.service.ExamSubjectService;
import com.backbone.service.UserServices;

@SuppressWarnings("serial")
public class ExamArrangement extends HttpServlet {
	// 保存文件的目录
	private static String PATH_FOLDER = "/";
	// 存放临时文件的目录
	private static String TEMP_FOLDER = "/";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 设置编码
		response.setContentType("application/json;charset=UTF-8");
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String filename = getUploadFileName(item);

			String filetype = filename.substring(filename.lastIndexOf(".") + 1,
					filename.length());

			filename = Long.toString(System.currentTimeMillis()) + "."
					+ filetype;

			System.out.println("存放目录:" + PATH_FOLDER);
			System.out.println("文件名:" + filename);

			
			PrintWriter out = response.getWriter();
			System.out.println(filetype);
			if (filetype.equals("xls") || filetype.equals("xlsx")) {
				// 真正写到磁盘上
				item.write(new File(PATH_FOLDER, filename)); // 第三方提供的
				
				List<ExamStatus> tList = new POIReadExcel().ExcelToExamStatus(PATH_FOLDER+"/"+filename);
				
				if(tList == null || tList.size() < 1){
					// Json返回实体
					
					// 返给ajax请求
					out.write("失败");
				}
				else {
					ExamSubjectService ess = new ExamSubjectService();
					UserServices us = new UserServices();

					String res = "这些人员添加失败了：\n";
					for (ExamStatus t : tList) {
						try{
							if(us.get(t.getTeano()) == null)
								throw new Exception("无此人");
							
							if(ess.saveExamStatus(t)<1)
								throw new Exception("失败");
						
						}
						catch (Exception e) {
							res += t.getTeano() + ","+t.getTeaname()+","+e +"\n";
						}
						
					}
					if (res.length()>11)
					// 返给ajax请求
					out.write(res);
				}
			}  else {
				// 返给ajax请求
				out.write("文件格式不对");
			}

			DeleteFile.deleteFile(PATH_FOLDER + "/" + filename);


				// Json返回实体
				
				// 返给ajax请求
			response.getWriter().print("成功");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 初始化路径
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath("BackGround/upload");
		// 存放临时文件的目录,存放xxx.tmp文件的目录
		TEMP_FOLDER = servletCtx.getRealPath("BackGround/uploadTemp");
	}

	private FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if (!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}

	private String getUploadFileName(FileItem item) {
		// 获取路径名
		String value = item.getName();
		// 索引到最后一个反斜杠
		int start = value.lastIndexOf("/");
		// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
		String filename = value.substring(start + 1);

		return filename;
	}
}
