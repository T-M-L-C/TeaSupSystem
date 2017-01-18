package com.backbone.test;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.Request;

import com.backbone.dao.implement.TeaOfTraImple;
import com.backbone.database.DBManager;
import com.backbone.entity.po.TeaOfTra;


public class DBHelperTest {
	
	
	@Test
	public void testConn() throws Exception{
//		Class.forName("org.gjt.mm.mysql.Driver");
//		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://59.75.128.157:3309/teasystem","root","sqlroot");
//		connection.createStatement();
		DBManager dbManager = new DBManager();
	
	}
	@Test
	public void testInsert(){
		 List<TeaOfTra>  traTeaOfTras=new TeaOfTraImple().gettTeaOfTras();
		 System.out.println(traTeaOfTras.size());
		 long result=new TeaOfTraImple().UpdateTeaOfTra(12, traTeaOfTras);
		 System.out.println("====="+result+"=======");
	}
  
}
