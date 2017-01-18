package com.backbone.entity.po;

import java.util.Date;

/**
 * 考场安排
 * @author TracyMcGrady
 *
 */
public class ExamStatus {
  //教师工号
  private String teano;
  //教师姓名
  private String teaname;
  //科目名称
  private String sub_name;
  //第一考场
  private String subplace;
  //第二考场
  private String subplaceother;
  //查询时间
  private Date querytime;
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
public String getSub_name() {
	return sub_name;
}
public void setSub_name(String sub_name) {
	this.sub_name = sub_name;
}
public String getSubplace() {
	return subplace;
}
public void setSubplace(String subplace) {
	this.subplace = subplace;
}
public String getSubplaceother() {
	return subplaceother;
}
public void setSubplaceother(String subplaceother) {
	this.subplaceother = subplaceother;
}
public Date getQuerytime() {
	return querytime;
}
public void setQuerytime(Date querytime) {
	this.querytime = querytime;
}
  
}
