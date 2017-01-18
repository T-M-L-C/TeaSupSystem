package com.backbone.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.backbone.web.SessionCounter;

/**
 * 
 * */
public class SystemListener  implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		SessionCounter sessionCounter = new SessionCounter();
		System.out.println(sessionCounter.hUserName.size());
	}

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("SystemListener, 初始化");
	}
}
