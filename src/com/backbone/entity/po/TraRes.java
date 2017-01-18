package com.backbone.entity.po;

import java.util.Date;

public class TraRes {

	// 培训资源编号
	private Integer tri_no;
	// 培训资源名称
	private String tri_name;
	// 培训资源类型
	private String tri_type;
	// 培训资源存储路径
	private String tri_path;
	// 培训资源创建时间（时刻）
	private Date tri_time;
	//是否可下载
	private Integer tri_candownload=0;
	//是否必学
	private Integer tri_muststudy=0;
	// PPT总页数
	private Integer tri_countpage;
	// 资源总时间
	private String tri_pagetime;
	// 资源转换后新路径
	private String tri_newpath;

	
	
	public Integer getTri_candownload() {
		return tri_candownload;
	}

	public void setTri_candownload(Integer tri_candownload) {
		this.tri_candownload = tri_candownload;
	}

	public Integer getTri_muststudy() {
		return tri_muststudy;
	}

	public void setTri_muststudy(Integer tri_muststudy) {
		this.tri_muststudy = tri_muststudy;
	}

	public Integer getTri_countpage() {
		return tri_countpage;
	}

	public void setTri_countpage(Integer tri_countpage) {
		this.tri_countpage = tri_countpage;
	}

	public String getTri_pagetime() {
		return tri_pagetime;
	}

	public void setTri_pagetime(String tri_pagetime) {
		this.tri_pagetime = tri_pagetime;
	}

	public String getTri_newpath() {
		return tri_newpath;
	}

	public void setTri_newpath(String tri_newpath) {
		this.tri_newpath = tri_newpath;
	}

	public Date getTri_time() {
		return tri_time;
	}

	public void setTri_time(Date tri_time) {
		this.tri_time = tri_time;
	}

	public Integer getTri_no() {
		return tri_no;
	}

	public void setTri_no(Integer string) {
		this.tri_no = string;
	}

	public String getTri_name() {
		return tri_name;
	}

	public void setTri_name(String tri_name) {
		this.tri_name = tri_name;
	}

	public String getTri_type() {
		return tri_type;
	}

	public void setTri_type(String tri_type) {
		this.tri_type = tri_type;
	}

	public String getTri_path() {
		return tri_path;
	}

	public void setTri_path(String tri_path) {
		this.tri_path = tri_path;
	}
}
