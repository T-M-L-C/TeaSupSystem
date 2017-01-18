package com.backbone.entity.po;

import java.util.Date;

public class TeaOfExam {
	// 教师工号
	private String teano;
	// 科目代码
	private String sub_no;
	// 学院审核状态(0表示审核通过，1表示正在审核)
	private Integer signstate;
	// 学校审核状态(0表示审核通过，1表示审核未通过)
	private Integer schoolstate;
    //后台管理系统查看哪些教师考试通过
	private Integer exampass;
	//考试通过时间
	 private Date exampassTime;
	 //学习通过时间
	 private Date studypassTime;
	public Integer getExampass() {
		return exampass;
	}

	public void setExampass(Integer exampass) {
		this.exampass = exampass;
	}

	public Integer getSchoolstate() {
		return schoolstate;
	}

	public void setSchoolstate(Integer schoolstate) {
		this.schoolstate = schoolstate;
	}

	public String getTeano() {
		return teano;
	}

	public void setTeano(String teano) {
		this.teano = teano;
	}

	
	public String getSub_no() {
		return sub_no;
	}

	public void setSub_no(String sub_no) {
		this.sub_no = sub_no;
	}

	public Integer getSignstate() {
		return signstate;
	}

	public void setSignstate(Integer signstate) {
		this.signstate = signstate;
	}

	public Date getExampassTime() {
		return exampassTime;
	}

	public void setExampassTime(Date exampassTime) {
		this.exampassTime = exampassTime;
	}

	public Date getStudypassTime() {
		return studypassTime;
	}

	public void setStudypassTime(Date studypassTime) {
		this.studypassTime = studypassTime;
	}
	
}
