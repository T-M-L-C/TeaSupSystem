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

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.backbone.common.DeleteFile;
import com.backbone.common.TransferOffice;
import com.backbone.entity.po.TraRes;
import com.backbone.service.TraResServices;

public class FileUpLoadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 保存文件的目录
	private static String PATH_FOLDER = "/";
	// 存放临时文件的目录
	private static String TEMP_FOLDER = "/";
	private static String PATH_UPLOAD_BEFORE = "/";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

		try {
			TraRes documents = new TraRes();
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String filename = getUploadFileName(item);

			documents.setTri_name(filename);

			String filetype = filename.substring(filename.lastIndexOf(".") + 1,
					filename.length());

			filename = Long.toString(System.currentTimeMillis()) + "."
					+ filetype;

			System.out.println("存放目录:" + PATH_FOLDER);
			System.out.println("文件名:" + filename);

			// 真正写到磁盘上
			item.write(new File(PATH_FOLDER, filename)); // 第三方提供的
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			documents.setTri_path(request.getContextPath()
					+ "/BackGround/upload/" + filename);
			System.out.println(request.getContextPath()+"/BackGround/upload/"+filename);
			String outPutPath = "";
			String newDir = Long.toString(System.currentTimeMillis());

			if (filetype.equals("doc") || filetype.equals("docx")) {
				
				documents.setTri_type(filetype);
				outPutPath = PATH_UPLOAD_BEFORE + "/docHtmlFiles/";
				
				if (!TransferOffice.wordToHtml(PATH_FOLDER + "/" + filename,
						outPutPath+"/"+newDir + ".html")) {
					out.print("null");
					return;
				}
				documents.setTri_newpath(request.getContextPath()
						+ "/BackGround/docHtmlFiles/" + newDir + ".html");
				documents.setTri_path(request.getContextPath()+"/BackGround/upload/"+filename);
				// DeleteFile.MoveDeleteFiel(documents.getTri_path(),PATH_FOLDER+
				// "/"+filename);
			} else if (filetype.equals("ppt")||filetype.equals("pptx")) {
				documents.setTri_type(filetype);
				
				outPutPath = PATH_UPLOAD_BEFORE + "/pptImageFiles/" + newDir;// 输出路径+文件名前缀
				int length = TransferOffice.ppt2Jpg(PATH_FOLDER + "/"+filename, outPutPath);
				
				// 图片数量，<0为异常
				if (length > 0) {
					documents.setTri_newpath(request.getContextPath()+ "/BackGround/pptImageFiles/" + newDir + "/pict_");
					documents.setTri_path(request.getContextPath()+"/BackGround/upload/"+filename);
				} else // 转换失败，删除上传的文件
				{
					//DeleteFile.deleteFile(PATH_FOLDER + "/" + filename);
					out.print("null");
					return;
				}
				documents.setTri_countpage(length);
                 
				// 图片为outPutPath+1.jpeg --outPutPath+length.jpeg
			} else if (filetype.equals("mp4") || filetype.equals("Mp4")
					|| filetype.equals("MP4")) {
				documents.setTri_type("mp4");

				outPutPath = PATH_UPLOAD_BEFORE + "/videoFiles/";// 输出路径+文件名前缀
				DeleteFile.MoveDeleteFiel(PATH_FOLDER + "/" + filename,
						outPutPath + newDir + "." + filetype);
                 
				documents.setTri_path(request.getContextPath()
						+ "/BackGround/videoFiles/" + newDir + "." + filetype);
				documents.setTri_newpath(request.getContextPath()
						+ "/BackGround/videoFiles/" + newDir + "." + filetype);
			} else {
				documents.setTri_type("其他");
			}

			//DeleteFile.deleteFile(PATH_FOLDER + "/" + filename);
			
			TraResServices tResServices = new TraResServices();
			documents.setTri_candownload(0);
			documents.setTri_muststudy(0);
			Integer result = tResServices.Register(documents);
			if (result > 0) {
				// Json返回实体
				JSONObject jsonObject = JSONObject.fromObject(documents);
				// 返给ajax请求
				out.print(jsonObject);
			}
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
		PATH_UPLOAD_BEFORE = servletCtx.getRealPath("BackGround");
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
