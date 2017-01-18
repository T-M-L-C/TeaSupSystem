package com.backbone.entity.po;

/**
 * 备选名单（限制教师报考指定科目）
 *
 */
public class ReserveList {
	//工号
   private String teano;
   //姓名
   private String teaname;
   //科目编号
   private String sub_no;
   //科目名称
   private String sub_name;
   //所在学院
   private String subordunit;
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
public String getSub_no() {
	return sub_no;
}
public void setSub_no(String sub_no) {
	this.sub_no = sub_no;
}
public String getSub_name() {
	return sub_name;
}
public void setSub_name(String sub_name) {
	this.sub_name = sub_name;
}
public String getSubordunit() {
	return subordunit;
}
public void setSubordunit(String subordunit) {
	this.subordunit = subordunit;
}
   
}
