package com.backbone.entity.po;

import java.util.Date;




public class Teacher {
	// 教职工编号
	private String teano;

	// 教职工姓名
	private String teaname;

	// 教职工密码
	private String teapwd;

	// 教职工性别
	private Integer teasex;

	// 教职工所属单位
	private String subordunit;

	// 教职工联系方式
	private String teatel;

	// 银行卡号
	private String cashcardid;

	// 家庭住址

	private String teaaddress;
	// 权限
	private Integer authority;
    
	
	
	/**
	 * 下面的字段是方便多表查询时使用
	 */
	private String jobtitle;
     
	//教务办审核状态
	private Integer signstate;
	//教务处审核状态
	private Integer schoolstate;
	
	//教师考试通过情况
	private Integer exampass;
	
	//培训时间
	private String tritime;
	
	//学习完成时间
	private Date studypassTime;
	//考试完成时间
	private Date exampassTime;
	//文件上传到服务器端的具体哪个位置
	private String tri_path;
	
	private String subno;
	public String getTritime() {
		return tritime;
	}

	public void setTritime(String tritime) {
		this.tritime = tritime;
	}

	//资源学习情况（0表示未学习，1表示已学习）
	private String watchtime;
	
	public String getWatchtime() {
		return watchtime;
	}

	public void setWatchtime(String watchtime) {
		this.watchtime = watchtime;
	}

	public Integer getExampass() {
		return exampass;
	}

	public void setExampass(Integer exampass) {
		this.exampass = exampass;
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

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getSubordunit() {
		return subordunit;
	}

	public void setSubordunit(String subordunit) {
		this.subordunit = subordunit;
	}

	public String getTeano() {
		return teano;
	}

	public void setTeano(String teano) {
		this.teano = teano;
	}

	public String getTeaname() {
		return teaname;
	}

	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}

	

	public String getTeapwd() {
		return teapwd;
	}

	public void setTeapwd(String teapwd) {
		this.teapwd = teapwd;
	}

	public String getTeatel() {
		return teatel;
	}

	public void setTeatel(String teatel) {
		this.teatel = teatel;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Integer getTeasex() {
		return teasex;
	}

	public void setTeasex(Integer teasex) {
		this.teasex = teasex;
	}

	public String getCashcardid() {
		return cashcardid;
	}

	public void setCashcardid(String cashcardid) {
		this.cashcardid = cashcardid;
	}

	public String getTeaaddress() {
		return teaaddress;
	}

	public void setTeaaddress(String teaaddress) {
		this.teaaddress = teaaddress;
	}

	public Date getStudypassTime() {
		return studypassTime;
	}

	public void setStudypassTime(Date studypassTime) {
		this.studypassTime = studypassTime;
	}

	public Date getExampassTime() {
		return exampassTime;
	}

	public void setExampassTime(Date exampassTime) {
		this.exampassTime = exampassTime;
	}

	public String getTri_path() {
		return tri_path;
	}

	public void setTri_path(String tri_path) {
		this.tri_path = tri_path;
	}

	public String getSubno() {
		return subno;
	}

	public void setSubno(String subno) {
		this.subno = subno;
	}
	
   
}
