package com.backbone.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.Teacher;

public class POIReadExcel {
	@Test
	 public static void ReadTest(String[] args) throws IOException {  
		 String file = "e:/test/2015.xls";
		 List<ExamStatus> esl = new POIReadExcel().ExcelToExamStatus(file);
	     for (ExamStatus item : esl) {
			System.out.println(item.getTeano()+","+item.getTeaname()+","+item.getSub_name()+","+item.getSubplace()+","+item.getSubplaceother());
		}
	    }  

	    public List<ExamStatus> ExcelToExamStatus(String excelPath)throws IOException  
	    {
	  	  List<ExamStatus> eslist = new ArrayList<ExamStatus>();

  		InputStream stream = new FileInputStream(excelPath);  
        Workbook wb = null;  
        String type = excelPath.substring(excelPath.lastIndexOf(".")+1,excelPath.length());
        if (type.equals("xls")) {  
            wb = new HSSFWorkbook(stream);  
        }  
        else if (type.equals("xlsx")) {  
            wb = new XSSFWorkbook(stream);  
        }  
        else {  
            System.out.println("您输入的excel格式不正确");  
        }  
        Sheet sheet1 = wb.getSheetAt(0);  
        for (Row row : sheet1) {  
            
	  			ExamStatus es = new ExamStatus();
	  			es.setTeano(cell2Str(row.getCell(0)));
	  			if(es.getTeano() == "")
	  				continue;
	  			
	  			es.setTeaname(cell2Str(row.getCell(1)));
	  			es.setSub_name(cell2Str(row.getCell(2)));
	  			es.setSubplace(cell2Str(row.getCell(3)));
	  			es.setSubplaceother(cell2Str(row.getCell(4)));
	  			eslist.add(es);
	  		}
	  	  return eslist;
	    }
	    
	    public List<Teacher> ExcelToTeacher(String excelPath) throws IOException  
	    {
	  		List<Teacher> tList = new ArrayList<Teacher>();
	  		InputStream stream = new FileInputStream(excelPath);  
	        Workbook wb = null;  
	        String type = excelPath.substring(excelPath.lastIndexOf(".")+1,excelPath.length());
	        if (type.equals("xls")) {  
	            wb = new HSSFWorkbook(stream);  
	        }  
	        else if (type.equals("xlsx")) {  
	            wb = new XSSFWorkbook(stream);  
	        }  
	        else {  
	            System.out.println("您输入的excel格式不正确");  
	        }  
	        Sheet sheet1 = wb.getSheetAt(0);  
	        for (Row row : sheet1) {  
	        
	            	Teacher t = new Teacher();
		  			t.setTeano(cell2Str(row.getCell(0)));
		  			if(t.getTeano() == "")
		  				continue;
		  			t.setTeapwd(t.getTeano());
		  			t.setTeaname(cell2Str( row.getCell(1)));
		  			t.setTeasex(cell2Str(row.getCell(2)).equals("男")?0:1);
		  			t.setTeatel(cell2Str(row.getCell(3)));
		  			t.setSubordunit(cell2Str(row.getCell(4)));
		  			t.setCashcardid(cell2Str(row.getCell(5)));
		  			t.setTeaaddress(cell2Str(row.getCell(6)));
		  			try{
		  				t.setAuthority(Integer.valueOf(cell2Str(row.getCell(7))));
		  			}
		  			catch (Exception e) {
		  				continue;
					}
		  			tList.add(t);
	            
	        }  
	  			
	  		return tList;
	    }
	   private static String cell2Str(Cell c)
	   {
		   if(c!=null)
		   {
       		c.setCellType(Cell.CELL_TYPE_STRING);
		   return c.getStringCellValue();
		   }
		   else
		   {
			   return "";
		   }
	   }
	    
}
