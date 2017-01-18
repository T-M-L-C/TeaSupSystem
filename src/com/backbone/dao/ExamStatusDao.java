package com.backbone.dao;

import java.util.List;

import com.backbone.entity.po.ExamStatus;

public interface ExamStatusDao {
	public Integer saveExamStatus(ExamStatus es);
	
	public Integer updateExamStatus(String teano,String sub_name);
	
	public List<ExamStatus> getAllRecords(String subname);
	
	public ExamStatus getExamStatusObj(String teano,String subname);
	
	public long RemoveExamStatus();
}
