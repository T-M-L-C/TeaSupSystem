package com.backbone.service;

import java.util.List;

import com.backbone.dao.implement.TeaOfExamImpl;
import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.TempTable;


public class TeaOfExamService {
	private static TeaOfExamImpl teImpl = new TeaOfExamImpl();
   
	/**
	 * 判断用户是否已经报名
	 */
	public TeaOfExam isExistRegistObj(TeaOfExam exam) {
		TeaOfExam flag = teImpl.getTeaOfExams(exam);
		return flag;
	}

	/**
	 * 保存用户的报名信息
	 */
	public boolean SaveOrUpdateObj(TeaOfExam exam) {
		Integer result = teImpl.SaveTeaOfExam(exam);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 获取用户的报名信息
	 */
	public List<TempTable> getRegisterAll(Teacher teano) {
		return teImpl.getAllInfor(teano);
	}

	public List<TempTable> getcontent(Teacher teano, Integer page, Integer count) {
		return teImpl.getsome(teano, page, count);
	}

	public Long getcounts(Teacher teano) {
		return teImpl.getcounts(teano);
	}

	/**
	 * 教务办查看所有科目的报名人员信息
	 */
	public List<TempTable> getSubjectCounter() {
		return teImpl.getRegister();
	}

	public List<TempTable> getSubjectCounterSome(Integer page, Integer count,String subordunit) {
		return teImpl.getRegisterSome(page, count,subordunit);
	}

	public Long getSubjectCounterCounts() {
		return teImpl.getRegisterCounts();
	}

	/**
	 * 教务处查询教务办已经审核通过的报名人员信息
	 */
	public List<TempTable> getRegisterCounter() {
		return teImpl.getSingleRegister();
	}

	/**
	 * 查看指定科目的报名列表(哪些人报名了此科目)
	 */
	public List<Teacher> getUserInfor(String subno) {
		List<Teacher> teachers = teImpl.getRegisterUserInfor(subno);
		return teachers;
	}

	public List<Teacher> getUserInforSome(String subno, Integer page,
			Integer count,String subordunit) {
		return teImpl.getRegisterUserInforSome(subno, page, count,subordunit);
	}

	public Long getUserInforCounts(String subno) {
		return teImpl.getRegisterUserInforCounts(subno);
	}

	/**
	 * 教务办人员审核单个教师报名
	 */
	public boolean isSinglePass(Integer signal, String teano, String subno) {
		Integer result = teImpl.updateRegister(signal, teano, subno);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 教务办人员审核所有教师报名
	 */
	public boolean isPass(Integer signal, String subno,String subordunit) {
		Integer result = teImpl.updateAllTeacher(signal, subno, subordunit);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 教务处查询指定科目且经过教务办人员审核过的人员信息
	 * 
	 * @param subno
	 * @return
	 */
	public List<Teacher> getSecondList(String subno) {
		List<Teacher> teachers = teImpl.getSecondUserInfor(subno);
		return teachers;
	}

	public Long getSecondListCounts(String subno) {
		return teImpl.getSecondUserInforCounts(subno);
	}

	public List<Teacher> getSecondListSome(String subno, Integer page,
			Integer count) {
		List<Teacher> teachers = teImpl.getSecondUserInforSome(subno, page,
				count);
		return teachers;
	}

	/**
	 * 教务处审核通过特定科目的所有的人员的报名信息
	 */
	public boolean UpdateSecondList(String subno) {
		Integer result = teImpl.updateSecondAll(subno);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 教务处审核通过特定科目所对应的特定人员的报名信息
	 */
	public boolean UpdateSecondObj(String teano, String subno) {
		Integer result = teImpl.updateSecond(teano, subno);
		if (result > 0)
			return true;
		return false;
	}
	/**
	 * 取消报名
	 */
	public boolean DeleteRegister(String teano,String subno){
		Integer result= teImpl.DeleteRegister(teano, subno);
		if(result>0)
			return true;
		return false;
	}
	/**
	 * 删除与报名相关的资源
	 */
	public boolean DeleteResource(String teano)
	{
	    Integer result =teImpl.DeleteRegisterResource(teano);
		  if(result>0)
		    return true;
		  return false;
	}

	
	public Integer getSchoolState(String tri_name,String teano,String subno){
		TeaOfExam teaOfExam=teImpl.getSchoolState(teano, tri_name, subno);
	    return teaOfExam.getSchoolstate();
	}
	/**
	 * 记录教师完成某一科目资源的学习时间
	 */
	public Integer updatequeryTime(String subno,String teano){
		return teImpl.UpdateStudyPasstime(subno, teano);
	}
  /**
   * 记录教师完成某一科目考试的时间
   */
	public Integer updateExamPasstime(String subno,String teano){
		return teImpl.UpdateExamPasstime(subno, teano);
	}
	/**
	 * 查询某一科目的所有报名信息
	 */
	public List<TeaOfExam>  getAllTeacherInfor(String subno){
		return teImpl.getTeaOfExams(subno);
	}
	
	public Integer UpdateTeacher(String subno,String teano){
		return teImpl.AdvanceAgree(subno, teano);
	}
	
	/**
	 * 查看某位教师的某一科目的学习完成时间是否为空
	 */
	 public TeaOfExam getTeaOfExam(String subno,String teano){
		 return teImpl.queryStudyTime(subno, teano);
	 }
}
