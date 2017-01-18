package com.backbone.entity.vo;

import java.util.Date;

import com.backbone.entity.po.Teacher;

public class AgreeTeacherInfo extends Teacher {

	//一级审核状态
	private Integer signstate;
	//二级审核状态
	private Integer schoolstate;
	//考试编号
	private String sub_no;
	//总数
	private Integer sum;
	//是否完成学习
	private Integer isstudy;
	//是否通过考试
	private Integer exampass;
	
	
	public Integer getIsstudy() {
		return isstudy;
	}
	public void setIsstudy(Integer isstudy) {
		this.isstudy = isstudy;
	}
	public Integer getExampass() {
		return exampass;
	}
	public void setExampass(Integer exampass) {
		this.exampass = exampass;
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
	public Integer getSchoolstate() {
		return schoolstate;
	}
	public void setSchoolstate(Integer schoolstate) {
		this.schoolstate = schoolstate;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
	
}
