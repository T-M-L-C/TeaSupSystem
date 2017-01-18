package com.backbone.common;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Picture;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sun.org.apache.commons.collections.Transformer;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;


public class TransferOffice {
	 static int WORD_HTML = 8;
	 int WORD_TXT = 7;
	 int EXCEL_HTML = 44;
    /**   
     * WORD转HTML   
     * @param docfile WORD文件全路径   
     * @param htmlfile 转换后HTML存放路径   
     */    
    public static boolean wordToHtml(String docfile, String htmlfile)
    {     
    	File filetemp = new File(htmlfile);
		System.out.println(filetemp);
		System.out.println(filetemp.exists());
		System.out.println(filetemp.isDirectory());
        // 启动word应用程序(Microsoft Office Word 2003)  
        ActiveXComponent app = new ActiveXComponent("Word.Application");  
        System.out.println("*****正在转换...*****");  
        try    
        {     
            // 设置word应用程序不可见    
            app.setProperty("Visible", new Variant(false));    
            // documents表示word程序的所有文档窗口，（word是多文档应用程序）  
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 打开要转换的word文件  
            Dispatch doc = Dispatch.invoke(
                    docs,     
                    "Open",     
                    Dispatch.Method,     
                    new Object[] { docfile, new Variant(false),   
                            new Variant(true) }, new int[1]).toDispatch();     
            // 作为html格式保存到临时文件  
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {     
                    htmlfile, new Variant(8) }, new int[1]);     
            // 关闭word文件  
            
            Dispatch.call(doc, "Close", new Object[]{new Variant(false)});
        }     
        catch (Exception e)     
        {     
            e.printStackTrace();  
            return false;
        }     
        finally    
        {     
            //关闭word应用程序  
            app.invoke("Quit", new Variant[] {});     
        }   
        System.out.println("*****转换完毕********");
        return true;
    }  

	
	public static Integer ppt2Jpg(String inputFile,String jpgfloder)
	{
		File filetemp = new File(jpgfloder);
		System.out.println(filetemp);
		System.out.println(filetemp.exists());
		System.out.println(filetemp.isDirectory());
		if (!filetemp.exists() && !filetemp.isDirectory()) {
			filetemp.mkdir();
		}

		try {
//			ppt2PDF(inputFile, newname);
//			return PdfToJpg(newname, jpgfloder);			
			System.out.println("ppt2PDF==========进入");
	           ActiveXComponent app = null;
	           try {
	               app = new ActiveXComponent("PowerPoint.Application");
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	           System.out.println("--------------------");
	           app.setProperty("Visible", true);
	           Dispatch ppts = app.getProperty("Presentations").toDispatch();
	           System.out.println("ppt2jpeg==========准备打开ppt文档");
	           System.out.println(new File(inputFile).exists());
	           Dispatch ppt = Dispatch.call(ppts,
	                   "Open",
	                   inputFile,
	                   true,//ReadOnly
	                   true,//Untitled指定文件是否有标题
	                   true//WithWindow指定文件是否可见
	           ).toDispatch();
	           System.out.println("ppt2jpeg==========准备转换ppt文档");
	           Dispatch.call(ppt,
	                   "SaveCopyAs",
	                   jpgfloder,
	                   17//转换成jpg
	           );
	           System.out.println("ppt2jpeg==========准备关闭ppt文档");
	           Dispatch.call(ppt, "Close");

	           app.invoke("Quit");
	           File directory = new File(jpgfloder);
	           for(File item : directory.listFiles())
	           {
	        		   item.renameTo(new File(item.getPath().replace("幻灯片", "pict_")));
	           }
	           return directory.listFiles().length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//DeleteFile.deleteFile(newname);
		}
		return -1;
	}
	
	public static void main(String[] args) {
		File f = new File("F:\\幻灯片1.txt");
		f.renameTo(new File(f.getPath().replace("幻灯片", "pict_")));
	}
	
	static boolean ppt2PDF(String inputFile,String pdfFile){
       try{
        /*ActiveXComponent app = new ActiveXComponent("PowerPoint.Application");
        //app.setProperty("Visible", msofalse);
        Dispatch ppts = app.getProperty("Presentations").toDispatch();
         
        Dispatch ppt = Dispatch.call(ppts,
                                    "Open",new Object[]{
                                    inputFile,
                                    new Boolean(true),//ReadOnly
                                    new Boolean(true),//Untitled指定文件是否有标题
                                    new Boolean(false)}//WithWindow指定文件是否可见
                                    ).toDispatch();
         System.out.println("convert start");
        Dispatch.call(ppt,
                    "SaveAs",new Object[]{
                    pdfFile,
                    new Variant(32) }
                    );
                 System.out.println("convert finish");
        Dispatch.call(ppt, "Close");
         
        app.invoke("Quit");
		*/
    	   System.out.println("ppt2PDF==========进入");
           ActiveXComponent app = null;
           try {
               app = new ActiveXComponent("PowerPoint.Application");
           } catch (Exception e) {
               e.printStackTrace();
           }
           System.out.println("--------------------");
           app.setProperty("Visible", true);
           Dispatch ppts = app.getProperty("Presentations").toDispatch();
           System.out.println("ppt2jpeg==========准备打开ppt文档");
           System.out.println(new File(inputFile).exists());
           Dispatch ppt = Dispatch.call(ppts,
                   "Open",
                   inputFile,
                   true,//ReadOnly
                   true,//Untitled指定文件是否有标题
                   true//WithWindow指定文件是否可见
           ).toDispatch();
           System.out.println("ppt2jpeg==========准备转换ppt文档");
           Dispatch.call(ppt,
                   "SaveCopyAs",
                   pdfFile,
                   32//转换成jpg
           );
           System.out.println("ppt2jpeg==========准备关闭ppt文档");
           Dispatch.call(ppt, "Close");

           app.invoke("Quit");

        return true;
        }catch(Exception e){
            return false;
        }
    }
    
	static Integer PdfToJpg(String pdffilename,String jpgfloder) throws IOException {

		// load a pdf from a byte buffer
		File file = new File(pdffilename);

		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel
				.size());
		PDFFile pdffile = new PDFFile(buf);

		System.out.println("页数： " + pdffile.getNumPages());

		for (int i = 1; i <= pdffile.getNumPages(); i++) {
			// draw the first page to an image
			PDFPage page = pdffile.getPage(i);

			// get the width and height for the doc at the default zoom
			Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
					.getWidth(), (int) page.getBBox().getHeight());

			// generate the image
			Image img = page.getImage(rect.width, rect.height, // width &
																// height
					rect, // clip rect
					null, // null for the ImageObserver
					true, // fill background with white
					true // block until drawing is done
					);

			BufferedImage tag = new BufferedImage(rect.width, rect.height,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
					null);
			FileOutputStream out = new FileOutputStream(jpgfloder+"/pict_"
							+ i + ".jpeg"); // 输出到文件流
			ImageIO.write(tag, "jpeg", out);// JPEG编码
			
			out.close();
		}
		return pdffile.getNumPages();
	}
	
	/**
	 * word转html副本
	 */
	 public static boolean  WordToHtml(String docfile, String htmlfile)
	 {
	  ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word
	  try
	  {
	   app.setProperty("Visible", new Variant(false));
	   Dispatch docs = app.getProperty("Documents").toDispatch();
	   Dispatch doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] { docfile, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
	   Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {htmlfile, new Variant(WORD_HTML) }, new int[1]);
	   Variant f = new Variant(false);
	   Dispatch.call(doc, "Close", f);
	  }
	  catch (Exception e)
	  {
	   e.printStackTrace();
	  }
	  finally
	  {
	   app.invoke("Quit", new Variant[] {});
	  }
	  return true;
	 }
}
