package com.backbone.entity.po;

public class TeaOfTra {
	// 资源ID
	private Integer tri_no;
	// 教师工号
	private String teano;
	// 特定教师对特定资源已观看时间的记录
	private String watch_time;

	public Integer getTri_no() {
		return tri_no;
	}

	public void setTri_no(Integer tri_no) {
		this.tri_no = tri_no;
	}

	public String getTeano() {
		return teano;
	}

	public void setTeano(String teano) {
		this.teano = teano;
	}

	public String getWatch_time() {
		return watch_time;
	}

	public void setWatch_time(String watch_time) {
		this.watch_time = watch_time;
	}

}
