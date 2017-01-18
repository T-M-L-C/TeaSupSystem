package com.backbone.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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

import net.sf.json.JSONObject;

import com.backbone.entity.po.ReserveList;

import com.backbone.entity.vo.TempTable;
import com.backbone.service.ReserveListService;

public class ReserveListServlet  extends HttpServlet{
    
	private ReserveListService reserveListService=new ReserveListService();
	
	private TempTable tempTable=new TempTable();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    String method=req.getParameter("action");
		    	if(method.equals("resetValue")){
		    		deleteReserveList(req, resp);
		    	}
		    	else if (method.equals("obtainReserveList")) {
		    		obtainReserveList(req,resp);
				}
	}
	/**
	 * 以Excel的形式导出考试备份名单
	 */
	private void obtainReserveList(HttpServletRequest req,
			HttpServletResponse resp) {
		String filename = "考试备份名单";
		resp.setContentType("text/html;charset=GBK"); 
		resp.setContentType("application/xml"); 
		try {
			resp.setHeader("Content-Disposition", "attachment;filename=" 
			+ new String(filename.getBytes(), "iso-8859-1") + ".xls");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String subno=req.getParameter("subno");
	    List<ReserveList> reserveLists=reserveListService.findLists(subno);
		
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
		String[] bt = new String[] { "科目编号","科目名称","教师编号","教师姓名","学院"}; 
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
				HSSFRow tableRow = sheet.createRow(0); // 创建一个行对象 
				tableRow.setHeightInPoints(18); 
				for (int i = 0; i < bt.length; i++) { 
				cell = tableRow.createCell(i); 
				cell.setCellValue(new HSSFRichTextString(bt[i])); 
				} 
				// 写入表格内容 
				for (int i = 0; i < reserveLists.size(); i++) { 
				tableRow = sheet.createRow(i + 1); // 创建一个行对象 
				tableRow.setHeightInPoints(16); 
				ReserveList  ati = reserveLists.get(i); 
				cell=tableRow.createCell(0);
				cell.setCellValue(new HSSFRichTextString(ati.getSub_no()));
				cell=tableRow.createCell(1);
				cell.setCellValue(new HSSFRichTextString(ati.getSub_name()));
				cell=tableRow.createCell(2);
				cell.setCellValue(new HSSFRichTextString(ati.getTeano()));
				cell=tableRow.createCell(3);
				cell.setCellValue(new HSSFRichTextString(ati.getTeaname()));
				cell=tableRow.createCell(4);
				cell.setCellValue(new HSSFRichTextString(ati.getSubordunit()));
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
	 * 清空备选名单
	 */
   private void deleteReserveList(HttpServletRequest req,
			HttpServletResponse resp) {
	        
		    long result=reserveListService.truncateList();
		    if(result>0)
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
