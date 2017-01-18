package com.backbone.entity.po;

import java.util.Date;

public class ExamSubject {
	// 考试科目代码
	private String sub_no;
	// 考试科目名称
	private String sub_name;
	// 考试开始时间
	private Date sub_time;
	// 考试持续时间
	private int sub_proce_time;
   //考试开始报名时间
	private Date sub_RegistStart;
	//考试截止报名时间
	private Date sub_RegistEnd;
    //学习开始时间
	private Date sub_StudyStart;
	//学习结束时间
	private Date sub_StudyEnd;
	//考试开始时间
	private  Date sub_ExamStart;
	//考试截止时间
	private Date sub_ExamEnd;
	//获取当前系统时间
	private Date nowtime=new Date();
	//考试发布状态 0表示未发布，1表示已发布
	private int publishExam;
	public Date getNowtime() {
		return nowtime;
	}

	public void setNowtime(Date nowtime) {
		this.nowtime = nowtime;
	}

	public int getSub_proce_time() {
		return sub_proce_time;
	}

	public void setSub_proce_time(int sub_proce_time) {
		this.sub_proce_time = sub_proce_time;
	}

	public String getSub_no() {
		return sub_no;
	}

	public void setSub_no(String sub_no) {
		this.sub_no = sub_no;
	}
 
	public Date getSub_time() {
		return sub_time;
	}

	public void setSub_time(Date sub_time) {
		this.sub_time = sub_time;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public Date getSub_RegistStart() {
		return sub_RegistStart;
	}

	public void setSub_RegistStart(Date sub_RegistStart) {
		this.sub_RegistStart = sub_RegistStart;
	}

	public Date getSub_RegistEnd() {
		return sub_RegistEnd;
	}

	public void setSub_RegistEnd(Date sub_RegistEnd) {
		this.sub_RegistEnd = sub_RegistEnd;
	}

	public Date getSub_StudyStart() {
		return sub_StudyStart;
	}

	public void setSub_StudyStart(Date sub_StudyStart) {
		this.sub_StudyStart = sub_StudyStart;
	}

	public Date getSub_StudyEnd() {
		return sub_StudyEnd;
	}

	public void setSub_StudyEnd(Date sub_StudyEnd) {
		this.sub_StudyEnd = sub_StudyEnd;
	}

	public Date getSub_ExamStart() {
		return sub_ExamStart;
	}

	public void setSub_ExamStart(Date sub_ExamStart) {
		this.sub_ExamStart = sub_ExamStart;
	}

	public Date getSub_ExamEnd() {
		return sub_ExamEnd;
	}

	public void setSub_ExamEnd(Date sub_ExamEnd) {
		this.sub_ExamEnd = sub_ExamEnd;
	}

	public int getPublishExam() {
		return publishExam;
	}

	public void setPublishExam(int publishExam) {
		this.publishExam = publishExam;
	}


	
	
}
