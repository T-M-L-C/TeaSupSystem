package com.backbone.service;

import java.util.List;

import com.backbone.dao.HomeNewsDao;
import com.backbone.dao.implement.HomeNewsImple;
import com.backbone.entity.po.HomeNews;

public class HomeNewsServices {
	HomeNewsDao newsImple = new HomeNewsImple();
	
	public List<HomeNews> getTop10()
	{
		return newsImple.getTop10();
	}
	
	public long AddNews(HomeNews homeNews){
		return newsImple.add(homeNews);
	}
	
	public List<HomeNews> getAllHomeNews(){
		return newsImple.getHomeNews();
	}
	
	public long deleteHomeNewsInfor(Integer homenewsId){
		return newsImple.deleteHomeNews(homenewsId);
	}
	
	public long EditHomeNews(HomeNews homeNews){
		return newsImple.UpdateHomeNews(homeNews);
	}
}
