package com.backbone.entity.vo;

public class Temp {
	//资源编号
	private String tri_no;
	//资源名称
	private String tri_name;
	//资源类型
	private String tri_type;
	//资源本地路径
	private String tri_path;
	//资源转换后的路径
	private String path;
	//数量
	private Integer num;

	public String getTri_no() {
		return tri_no;
	}

	public void setTri_no(String tri_no) {
		this.tri_no = tri_no;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}