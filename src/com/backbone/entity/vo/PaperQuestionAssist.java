package com.backbone.entity.vo;

import java.util.List;

public class PaperQuestionAssist {
	String t;
	Integer no;
	String right;
	List<String> answers;
	Integer isimg;
	
	public Integer getIsimg() {
		return isimg;
	}
	public void setIsimg(Integer isimg) {
		this.isimg = isimg;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	
}
