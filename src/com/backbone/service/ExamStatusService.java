package com.backbone.service;

import java.util.List;

import com.backbone.dao.ExamStatusDao;
import com.backbone.dao.implement.ExamStatusImple;
import com.backbone.entity.po.ExamStatus;


public class ExamStatusService {
    private ExamStatusDao examStatusDao=new ExamStatusImple();
    /**
     * 更新考场安排查看时间
     */
	public  Integer updateQueryTime(String teano,String sub_name){
		return examStatusDao.updateExamStatus(teano, sub_name);
	}
	/**
	 * 查看考场日志
	 */
	public List<ExamStatus> getExamStatus(String subname){
		List<ExamStatus> examStatus=examStatusDao.getAllRecords(subname);
		return examStatus;
	}
	/**
	 * 查看该科目是否已经安排考场
	 */
	public ExamStatus getExamStatus(String teano,String sub_name){
		return examStatusDao.getExamStatusObj(teano, sub_name);
	}
}
