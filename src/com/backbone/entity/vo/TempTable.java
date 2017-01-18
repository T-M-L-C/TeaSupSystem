package com.backbone.entity.vo;

import java.util.Date;

public class TempTable {
	private Integer id;
	private Integer schoolstate;
	private String state;
	private String sub_name;
	private Date sub_time;
    private String subtime;
    private String registate;
    private String subno;
	public Integer getSchoolstate() {
		return schoolstate;
	}

	public void setSchoolstate(Integer schoolstate) {
		this.schoolstate = schoolstate;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public Date getSub_time() {
		return sub_time;
	}

	public void setSub_time(Date sub_time) {
		this.sub_time = sub_time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state2) {
		// TODO Auto-generated method stub
		this.state = state2;
	}

	public String getSubtime() {
		return subtime;
	}

	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}

	public String getRegistate() {
		return registate;
	}

	public void setRegistate(String registate) {
		this.registate = registate;
	}

	public String getSubno() {
		return subno;
	}

	public void setSubno(String subno) {
		this.subno = subno;
	}
	
	
}
