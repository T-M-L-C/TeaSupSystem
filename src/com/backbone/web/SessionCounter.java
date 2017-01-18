package com.backbone.web;

import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//HttpSessionListener,
//HttpSessionBindingListener
public class SessionCounter  implements HttpSessionBindingListener,HttpSessionListener {

	@SuppressWarnings("rawtypes")
	public static Hashtable<String, String> hUserName = new Hashtable<String, String>();
	public static int sessionactive = 0;
	public static String tempuser;


	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("已创建新的用户：" + se.getSession());
		sessionactive++;
	}


	public void sessionDestroyed(HttpSessionEvent se) {
		try {
			se.getSession().setAttribute("EJBEQ", null);
			se.getSession().removeAttribute("EJBEQ");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
     //判断是否重复登录
	@SuppressWarnings("unchecked")
	public boolean isLogin(String sUserName, String inetaddress) {
		System.out.println("====="+inetaddress+"=====");
		System.out.println(hUserName.size());
		boolean flag = false;
		if (hUserName.containsKey(sUserName) && !hUserName.containsValue(inetaddress)) {
			flag = true;
		} else 
		     if(!hUserName.contains(sUserName) ){
			flag = false;
			hUserName.put(sUserName, inetaddress);
		}
		System.out.println("在线用户人数:" +hUserName.size());
		return flag;
	}
	//判断hashtable中是否包含用户ID
   public  boolean IsOnline(String username){
	   if(hUserName.contains(username)){
		   return true;
	   }
	   return false;
   } 
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
      
	}


	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
