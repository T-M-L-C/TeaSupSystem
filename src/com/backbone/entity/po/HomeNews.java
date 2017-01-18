package com.backbone.entity.po;

import java.util.Date;

/**
 * 发布新闻
 *
 */
public class HomeNews {
	//逻辑主键
	private Integer homeNewsNo;
	//发布时间
	private Date homenewspublishtime=new Date();
	//通知内容
	private String homenewscontent;
	
	public Integer getHomeNewsNo() {
		return homeNewsNo;
	}
	public void setHomeNewsNo(Integer homeNewsNo) {
		this.homeNewsNo = homeNewsNo;
	}
	public Date getHomenewspublishtime() {
		return homenewspublishtime;
	}
	public void setHomenewspublishtime(Date homenewstime) {
		this.homenewspublishtime = homenewstime;
	}
	public String getHomenewscontent() {
		return homenewscontent;
	}
	public void setHomenewscontent(String homenewscontent) {
		this.homenewscontent = homenewscontent;
	}

	
		
}
