package com.backbone.dao;

import java.util.List;

import com.backbone.entity.po.HomeNews;

public interface HomeNewsDao {
	
	public List<HomeNews> getTop10();
	
	public long add(HomeNews hn);
	
	public List<HomeNews> getHomeNews();
	
	public long deleteHomeNews(Integer id);
	
	public long UpdateHomeNews(HomeNews homeNews);
}
