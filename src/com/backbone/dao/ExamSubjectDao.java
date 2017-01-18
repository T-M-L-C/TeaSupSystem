package com.backbone.dao;

import java.util.List;

import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.PaperInfo;
import com.backbone.entity.po.TeaOfExam;

public interface ExamSubjectDao {
	
public List<ExamSubject> getAll();
	
	public void clearPaper(String sunno);

	public ExamSubject getInfor(String sub_no);

	public List<ExamSubject> getcontent(Integer page, Integer count);

	public Long getcounts();

	public Integer add(ExamSubject es);

	public Integer update(ExamSubject es);

	public ExamSubject isExist(String subno);

	public Integer savePaperAnswerList(String paperId,List<PaperInfo.Answer> ans);
	
	public Integer savePaper(PaperInfo pi);
	
	public PaperInfo getPaperInfo(String subno);
	
	public List<PaperInfo> getPaperInfosBySubno(String subno);
	
	public  TeaOfExam HasInstance(String teano,String subno);
	
	public ExamSubject getSubName(String subno);
	
	public String getSubNo(String subname);
	
	public  Integer AgainAgree(String teano,String subno);
	
	public List<PaperInfo> getRightAnswer(String subno);
	
	public long deleteExam(String subno);
	
	public long deleteExamSubject();
    
	public long updateExamSubject(ExamSubject examSubject);
	
	public Integer publishExam(String subno);
}
