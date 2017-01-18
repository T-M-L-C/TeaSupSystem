package com.backbone.dao.implement;

import java.util.List;

import javax.management.Query;



import com.backbone.dao.HomeNewsDao;
import com.backbone.database.QueryHelper;
import com.backbone.entity.po.HomeNews;

public class HomeNewsImple implements HomeNewsDao{
	@Override
	public List<HomeNews> getTop10()
	{
		/*String sql = "SELECT * FROM HomeNews h"
				+ " WHERE h.`homeNewsPublishTime`<=STR_TO_DATE(DATE_ADD(CURDATE(),INTERVAL 1 DAY),'%Y-%m-%d %h:%i:%s')"
				+ " ORDER BY h.`homeNewsPublishTime` DESC ";*/
		String sql="select * from homenews order by homeNewsPublishTime desc  limit 0,10";
		return QueryHelper.query(HomeNews.class, sql);
		//return QueryHelper.queryPage(HomeNews.class, sql, 1, 10);
	}
    @Override
	public long add(HomeNews hn)
	{
		String sql ="insert into HomeNews values(null,?,?)";
		return QueryHelper.update(sql,hn.getHomenewspublishtime(),hn.getHomenewscontent());
	}
    
	@Override
	public List<HomeNews> getHomeNews() {
	    String sql="select * from homenews order by homeNewsPublishTime desc ";
		return QueryHelper.query(HomeNews.class, sql);
	}
	@Override
	public long deleteHomeNews(Integer id) {
		String sql="delete from homenews where homeNewsNo=?";
		return QueryHelper.update(sql, id);
	}
	@Override
	public long UpdateHomeNews(HomeNews homeNews) {
		String sql="update homenews set homeNewsContent=? where homeNewsNo=?";
		return QueryHelper.update(sql, homeNews.getHomenewscontent(),homeNews.getHomeNewsNo());
	}
}