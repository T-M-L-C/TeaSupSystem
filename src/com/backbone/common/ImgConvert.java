package com.backbone.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/*
 * 图片转换类
 * png转jpeg
 */
public class ImgConvert {
	public static void convertPngToJpeg(String src) throws IOException {
		File f = new File(src);
		if (f.isFile() && f.exists()) {
			f.canRead();
			BufferedImage srcouse = ImageIO.read(f);
			ImageIO.write(srcouse, "jpeg",
					new File(src.substring(0, src.length() - 3) + "jpeg"));
			f.delete();
			System.out.println("，success");
		} else {
			System.out.println("，fail");
		}
	}


	    // JGP格式
	    public static final String JPG = "jpeg";
	    // GIF格式
	    public static final String GIF = "gif";
	    // PNG格式
	    public static final String PNG = "png";
	    // BMP格式
	    public static final String BMP = "bmp";
	 
	   
	    public static void converter(File imgFile,String format,File formatFile)
	            throws IOException{
	        BufferedImage bIMG =ImageIO.read(imgFile);
	        ImageIO.write(bIMG, format, formatFile);
	    }
	 
	 
	  
	
}
