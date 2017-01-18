package com.backbone.service;

import java.util.ArrayList;
import java.util.List;

import com.backbone.dao.ExamSubjectDao;
import com.backbone.dao.implement.AgreeTeacherInfoImple;
import com.backbone.dao.implement.ExamOfTraImple;
import com.backbone.dao.implement.ExamStatusImple;
import com.backbone.dao.implement.ExamSubjectImple;
import com.backbone.dao.implement.TeaOfTraImple;
import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.PaperInfo;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.TeaOfTra;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.AgreeTeacherInfo;

public class ExamSubjectService {
	private static ExamSubjectDao examSubjectImple = new ExamSubjectImple();

	public List<AgreeTeacherInfo> getAllRegeditTeachers(String sub_no)
	{
		AgreeTeacherInfoImple atii = new AgreeTeacherInfoImple();
		Object[] objs = new Object[1];
		objs[0] = sub_no;
		return atii.getSome("    WHERE t.sub_no = ? ",objs,0,0,true);
	}
	
	public Integer saveExamStatus(ExamStatus es){
		return new ExamStatusImple().saveExamStatus(es); 
	}
	
	public List<ExamSubject> getAllExam() {
		return examSubjectImple.getAll();
	}

	public ExamSubject getExamByObj(String exam) {
		return examSubjectImple.getInfor(exam);
	}

	public List<ExamSubject> getcontent(Integer page, Integer count) {
		return examSubjectImple.getcontent(page, count);
	}

	public Long getcounts() {
		return examSubjectImple.getcounts();
	}
  /**
   * 添加考试
   * @param es
   * @return
   */
	public Integer add(ExamSubject es) {
		Integer res = 0;
		String subno = examSubjectImple.getSubNo(es.getSub_name());
		if(es.getSub_no()==null){
			if(subno == null)
			{
				Long no =examSubjectImple.getcounts()+1;
				while(examSubjectImple.getSubNo(no.toString()) != null)
				{
					no++;
				}
				es.setSub_no(no.toString());
				res =examSubjectImple.add(es);
			}
			else{
				es.setSub_no(subno);
				res =examSubjectImple.update(es);
			}
		}
		else {
			   if(subno==null){
				   res=examSubjectImple.add(es);
			   }
			   else {
				res=examSubjectImple.update(es);
			 }
		}
		
		return res ;
	}

	public Integer addResouce(String examid, List<Integer> resids) {
		ExamOfTraImple examOfTraImple = new ExamOfTraImple();
		List<Integer> addids = new ArrayList<Integer>();
		List<Integer> nowids = examOfTraImple.getResouceByExamid(examid);
		for(Integer item : resids)
		{
			if(!nowids.contains(item))
			{
				addids.add(item);
			}
		}
		if(addids.size()>0){
			examOfTraImple.adds(examid,addids);
		}

		for(Integer item :nowids)
		{
			if(!resids.contains(item))
			{
				examOfTraImple.deleteByExamid(examid,item);
			}
		}
		return addids.size();
	}

	public void regeditExam(String teacherid, String examid) {
		List<Integer> trs = new ExamOfTraImple().getResouceByExamid(examid);
		TeaOfTraImple toti = new TeaOfTraImple();
		TeaOfTra tot = new TeaOfTra();
		tot.setTeano(teacherid);
		tot.setWatch_time("0");
		for (Integer item : trs) {
			tot.setTri_no(item);
			toti.add(tot);
		}

	}

	public Boolean addpaper(PaperInfo pi) {
		Boolean resBoolean = false;
		if(examSubjectImple.savePaper(pi) > 0)
		{
			String idString = examSubjectImple.getPaperInfo(pi.getQuescontent()).getQuesid();
			if(examSubjectImple.savePaperAnswerList(idString,pi.getAnswers()) > 0)
			{
				resBoolean = true;
			}
		}
		return  resBoolean;
	}
	
	public List<PaperInfo> getPaperInfosBySubno(String subno)
	{
		return examSubjectImple.getPaperInfosBySubno(subno);
	}

	/**
	 * 查看此类考试是否已经注册过
	 */
	public boolean isExist(String subno) {
		ExamSubject examSubject = examSubjectImple.isExist(subno);
		if (examSubject == null)
			return true;
		return false;
	}
	/**
	 * 查看是否已报名
	 */
	public TeaOfExam HasInstance(String teano,String subno){
		 TeaOfExam exam= examSubjectImple.HasInstance(teano, subno);
		 return exam;
	}
	/**
	 * 通过科目编号查找科目名称
	 */
	public ExamSubject getExamSubject(String subno){
		return examSubjectImple.getSubName(subno);
	}
	/**
	 * 通过科目名称来查找科目编号
	 */
	public String getSubNo(String subname){
		return examSubjectImple.getSubNo(subname);
	}
	public void clearPaper(String subno)
	{
		examSubjectImple.clearPaper(subno);
	}
	
	/**
	 * 教务处取消教务办对该教师的报名拒绝
	 */
	
	public boolean sureAgain(String teano ,String subno){
		Integer flag=examSubjectImple.AgainAgree(teano, subno);
		if(flag>0)
			return true;
		return false;
	}
	/**
	 * 得到指定科目的所有正确答案
	 */
	public List<PaperInfo> getPaperAnswer(String subno){
		return examSubjectImple.getRightAnswer(subno);
	}
	
	/**
	 * 删除某一科目的考试
	 */
	public long deleteExamBySubno(String subno){
		return examSubjectImple.deleteExam(subno);
	}
	/**
	 * 更新考试
	 */
	public long UpdateExam(ExamSubject examSubject){
		return examSubjectImple.updateExamSubject(examSubject);
	}
	/**
	 * 发布考试
	 */
	public Integer updatePE(String subno){
		return examSubjectImple.publishExam(subno);
	}
}
