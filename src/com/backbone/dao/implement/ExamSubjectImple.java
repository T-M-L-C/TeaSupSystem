package com.backbone.dao.implement;


import java.util.ArrayList;
import java.util.List;

import com.backbone.dao.ExamSubjectDao;
import com.backbone.database.QueryHelper;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.PaperInfo;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.PaperInfo.Answer;

public class ExamSubjectImple implements ExamSubjectDao {

	@Override
	public Integer add(ExamSubject es) {
		String sql = "INSERT INTO `examsubject` VALUES(?,?,?,?,?,?,?,?,?,?,0)";
		return QueryHelper.update(sql, es.getSub_no(), es.getSub_name(),
				es.getSub_time(), es.getSub_proce_time(),es.getSub_RegistStart(),
				es.getSub_RegistEnd(),es.getSub_StudyStart(),es.getSub_StudyEnd(),
				es.getSub_ExamStart(),es.getSub_ExamEnd());
	}
	@Override
	public Integer update(ExamSubject es)
	{
		String sql = "UPDATE `examsubject` SET Sub_Name=?,Sub_Time=?," +
				"Sub_ProceTime=?,Sub_RegistStart=?,Sub_RegistEnd=?,Sub_StudyStart=?," +
				"Sub_StudyEnd=?,Sub_ExamStart=?,Sub_ExamEnd=? where sub_no=?";
		return QueryHelper.update(sql, es.getSub_name(),
				es.getSub_time(), es.getSub_proce_time(),es.getSub_RegistStart(),
				es.getSub_RegistEnd(),es.getSub_StudyStart(),es.getSub_StudyEnd(),
				es.getSub_ExamStart(),es.getSub_ExamEnd(),es.getSub_no());
	}
	@Override
	public Integer savePaper(PaperInfo pi) {
		String sql = "INSERT INTO examquestion(Subno,QuesContent,RightAnswer,QuesNo) VALUES (?,?,?,?)";
		return QueryHelper.update(sql, pi.getSubno(),pi.getQuescontent(),pi.getRightanswer(),pi.getQuesNo());
	}

	@Override
	public PaperInfo getPaperInfo(String content) {
		String sql = "SELECT * FROM examquestion WHERE QuesContent = ?";
		return QueryHelper.queryUnique(PaperInfo.class, sql, content);
	}
	
	@Override
	public List<PaperInfo> getPaperInfosBySubno(String subno) {
		
		String sql = "SELECT * FROM examquestion WHERE Subno = ?";
		String sqlanswer = "SELECT * FROM examanswer WHERE QuesId = ?";
		List<PaperInfo> pis = QueryHelper.query(PaperInfo.class, sql, subno);
		
		for (PaperInfo item : pis) {
			item.setAnswers(QueryHelper.query(PaperInfo.Answer.class, sqlanswer, item.getQuesid()));
		}
		return pis;
	}



	@Override
	public Integer savePaperAnswerList(String paperId,List<Answer> ans) {
		String sql = "INSERT INTO `examanswer` VALUES";
		List<Object> objs = new ArrayList<Object>();
		for (PaperInfo.Answer item : ans) {
			sql += "(?,?,?,?),";
			objs.add(item.getAnswer());
			objs.add(item.getIsImage());
			objs.add(paperId);
			objs.add(item.getAnswerItem());
		}
		sql = sql.substring(0, sql.length() - 1);
		return QueryHelper.update(sql, objs.toArray());
	}



	@Override
	public List<ExamSubject> getAll() {
		String sql = "select * from examsubject  order by sub_time DESC";
		List<ExamSubject> exam = QueryHelper.query(ExamSubject.class, sql);
		return exam;
	}

	@Override
	public ExamSubject getInfor(String sub_no) {
		String sql = "select * from examsubject where sub_no=?";
		ExamSubject examSubject = QueryHelper.queryUnique(ExamSubject.class,
				sql, sub_no);
		return examSubject;
	}

	@Override
	public List<ExamSubject> getcontent(Integer page, Integer count) {
		String sql = "SELECT * FROM `examsubject` es where publishExam=1 ORDER BY es.`Sub_No` DESC,es.Sub_Name desc,es.sub_time asc  ";
		List<ExamSubject> examSubjects = QueryHelper.queryPage(
				ExamSubject.class, sql, page, count);
		return examSubjects;
	}

	@Override
	public Long getcounts() {
		String sql = "SELECT COUNT(*) FROM `examsubject`";
		return QueryHelper.query(Long.class, sql).get(0);
	}

	@Override
	public ExamSubject isExist(String subno) {
		String sql = "select * from examsubject where sub_no=?";
		ExamSubject subject = QueryHelper.queryUnique(ExamSubject.class, sql,
				subno);
		return subject;
	}



	@Override
	public TeaOfExam HasInstance(String teano, String subno) {
		//String sql="select *  from teaofexam where teano=? and sub_no=? and signstate=1 and schoolstate=1";
		String sql=" select * from  teaofexam t,(select count(*) result from teaofexam  where (signstate=0 and schoolstate=0) or (signstate=0 and schoolstate=1) or (signstate=1 and schoolstate=1) ) h where  h.result>0 and t.teano=? and sub_no=?";
		TeaOfExam exam=QueryHelper.queryUnique(TeaOfExam.class, sql, teano,subno);
		return exam;
	}



	@Override
	public ExamSubject getSubName(String subno) {
		String sql="select * from examsubject where sub_no=?";
		return QueryHelper.queryUnique(ExamSubject.class, sql, subno);
	}



	@Override
	public String getSubNo(String subname) {
		String sql="select sub_no from examsubject where sub_name=?";
		String subno=QueryHelper.queryUnique(String.class, sql, subname);
		return subno;
	}



	@Override
	public void clearPaper(String subno) {
		String sql = "DELETE FROM examanswer WHERE QuesId IN (SELECT QuesId FROM examquestion WHERE Subno=?)";
		QueryHelper.update(sql, subno);
		String sql1 = "DELETE FROM examquestion WHERE Subno=?";
		QueryHelper.update(sql1, subno);
	}



	@Override
	public Integer AgainAgree(String teano, String subno) {
	    String sql="update teaofexam set signstate=0 ,schoolstate=0 where teano=? and sub_no=? ";
	    Integer result=QueryHelper.update(sql, teano,subno);
		return result;
	}
	@Override
	public List<PaperInfo> getRightAnswer(String subno) {
		String sql="select * from examquestion where subno=?";
		return QueryHelper.query(PaperInfo.class, sql, subno);
	}
	@Override
	public long deleteExam(String subno) {
	    String sql="delete from examsubject where sub_no=?";
		return QueryHelper.update(sql, subno);
	}
	@Override
	public long deleteExamSubject() {
		String sql="truncate table examsubject";
		return QueryHelper.update(sql);
	}
  
	public long deleteExamanswer(){
		String sql="truncate table examanswer";
		return QueryHelper.update(sql);
	}
  public long deleteExamQuestion(){
	  String sql="truncate table examquestion";
	  return QueryHelper.update(sql);
  }
@Override
public long updateExamSubject(ExamSubject examSubject) {
	StringBuffer sb=new StringBuffer();
	sb.append("update examsubject set sub_time=?,sub_procetime=?,");
	sb.append("sub_registstart=?,sub_registend=?");
	sb.append(",sub_studystart=?,sub_studyend=?,");
	sb.append("sub_examstart=?,sub_examend=?");
	sb.append(" where sub_no=? and sub_name=?");
	return QueryHelper.update(sb.toString(), examSubject.getSub_time(),
			examSubject.getSub_proce_time(),examSubject.getSub_RegistStart()
			,examSubject.getSub_RegistEnd(),examSubject.getSub_StudyStart()
			,examSubject.getSub_StudyEnd(),examSubject.getSub_ExamStart(),
			examSubject.getSub_ExamEnd(),examSubject.getSub_no(),examSubject.getSub_name());
}
@Override
public Integer publishExam(String subno) {
	String sql="update examsubject set publishExam=1  where sub_no=?";
	return QueryHelper.update(sql, subno);
}
}
