package com.backbone.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.backbone.common.DeleteFile;
import com.backbone.common.ImgConvert;
import com.backbone.entity.po.TraRes;
import com.backbone.service.TraResServices;

public class FileUpLoadAction2 extends HttpServlet {
	private static final long serialVersionUID = 1;
	// 保存文件的目录
	private static String PATH_FOLDER = "/";
	// 存放临时文件的目录
	private static String TEMP_FOLDER = "/";
	private static String PATH_UPLOAD_BEFORE = "/";
	// 当前已上传数量
	private static Integer numbers = 1;
	// 当前存放路径
	private static String SAVE_PATH = "";
	// 相对路劲
	private static String SAVE_WEB_PATH = "";
	private static Integer saveNo;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ini") != null) {
			inisave();
			return;
		}

		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

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

		String filename = "";
		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			@SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			filename = getUploadFileName(item);
			String filetype = filename.substring(filename.lastIndexOf(".") + 1,
					filename.length());
			filename = filename.substring(0, filename.length() - 5);

			System.out.println("存放目录:" + SAVE_PATH);
			System.out.println("文件名:" + filename);
			PrintWriter out = response.getWriter();
			if (filetype.equals("png") || filetype.equals("jpeg")
					|| filetype.equals("jpg")) {
				synchronized (numbers) {
					if (numbers == 1) {
						TraRes documents = new TraRes();
						documents.setTri_name(filename);
						documents.setTri_type("ppt");
						documents.setTri_countpage(1);

						filename = "pict_" + numbers + "." + filetype;
						documents.setTri_newpath(request.getContextPath()
								+ SAVE_WEB_PATH);

						DeleteFile.CreateFolder(SAVE_PATH);
						// 真正写到磁盘上
						item.write(new File(SAVE_PATH, filename)); // 第三方提供的
						System.out.println("success");
						if (filetype.equals("png") || filetype.equals("jpg")) {
							
							ImgConvert.convertPngToJpeg(SAVE_PATH + filename);
						}

						TraResServices tResServices = new TraResServices();
						Integer result = tResServices.Register(documents);
						if (result > 0) {
							out.print(numbers);
							saveNo = tResServices.getId(documents
									.getTri_newpath());
							numbers++;
						} else {
							DeleteFile.deleteFile(SAVE_PATH + filename);
							out.print("error");
						}
					} else {
						filename = "pict_" + numbers + ".jpeg";
						// 真正写到磁盘上
						item.write(new File(SAVE_PATH, "pict_" + numbers
								+ ".jpeg")); // 第三方提供的
						TraResServices tResServices = new TraResServices();
						tResServices.updateCounts(saveNo, numbers);
						out.print(numbers);
						numbers++;
					}
				}

				// 图片为outPutPath+1.jpeg --outPutPath+length.jpeg
			} else {
				out.print("其他");
			}
		} catch (Exception e) {
			DeleteFile.deleteFile(SAVE_PATH + filename);
			response.getWriter().print("error");
			e.printStackTrace();
		}

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 初始化路径
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath("BackGround/upload");
		PATH_UPLOAD_BEFORE = servletCtx.getRealPath("BackGround");
		// 存放临时文件的目录,存放xxx.tmp文件的目录
		TEMP_FOLDER = servletCtx.getRealPath("BackGround/uploadTemp");
		inisave();
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

	public static void inisave() {
		String str = UUID.randomUUID().toString();
		numbers = 1;
		SAVE_PATH = PATH_UPLOAD_BEFORE + "/pptImageFiles/" + str + "/";
		SAVE_WEB_PATH = "/BackGround/pptImageFiles/" + str + "/pict_";
	}

}
