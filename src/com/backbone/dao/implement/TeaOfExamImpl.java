package com.backbone.dao.implement;

import java.util.List;

import com.backbone.dao.TeaOfExamDao;
import com.backbone.database.QueryHelper;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.TeaOfTra;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.TempTable;

public class TeaOfExamImpl implements TeaOfExamDao {
	
	public  List<Teacher> canExam(String teacherid,String examid)
	{
		/**
		 * select count(*)
		from teaoftra t, examoftra e, trares tr 
		where  ( tr.tri_type='ppt' or tr.tri_type='doc') 
		and tr.Tri_No=e.Tri_No and
		 e.Sub_No='0010' and t.Tri_No=e.Tri_No and t.TeaNo='lc' 
		 and t.Watch_Time='1'
		 */
	/*	String sql = "select count(*) tri_no ,e.sub_no teano,t.watch_time watch_time  " +
				"from `teaoftra` t,`examoftra` e,`trares` tr " +
				"where (tr.Tri_Type='ppt' )  and tr.Tri_No=e.Tri_No and e.Sub_No=? and t.Tri_No=e.Tri_No and t.TeaNo=?";*/
	    /*String sql="select  e.sub_no teano,t.watch_time teasex,tr.tri_type teaname ,tr.tri_time teatel" +
	    		" from `teaoftra` t,`examoftra` e,`trares` tr " +
	    		"  where  tr.Tri_No=e.Tri_No and e.Sub_No=? and t.Tri_No=e.Tri_No and t.TeaNo=?";*/
	    String  sql="SELECT tr.tri_name teaname ,tf.exampass exampass , tr.tri_no authority,t.watch_time teano,tr.tri_countpage teapwd,tr.tri_newpath teaaddress,tr.tri_type teatel,tr.tri_time tritime,tr.tri_pagetime  schoolstate ,tr.tri_muststudy signstate,tr.tri_candownload  teasex,tr.`Tri_Path`  tri_path  FROM `teaoftra` t,`trares` tr ,teaofexam tf ,  (SELECT ea.tri_no tri_no FROM examoftra ea,trares t WHERE ea.tri_no=t.`Tri_No` AND ea.sub_no=?) nt WHERE  tr.Tri_No=nt.Tri_No AND tf.Sub_No=?  AND tf.TeaNo=? AND tf.teano=t.teano  AND t.`Tri_No`=nt.tri_no ";
		return QueryHelper.query(Teacher.class, sql, examid,examid,teacherid);
	}
	
	public Integer finishExam(String teacherid,String examid)
	{
		/*String sql1 = "SELECT COUNT(*) FROM `teaofexam` t WHERE t.`TeaNo`=? AND t.`Sub_No`=?";
		if (QueryHelper.queryUnique(Long.class, sql1, teacherid,examid) >0) {
			String sql ="UPDATE `teaofexam` SET exampass=1 WHERE teano=? AND Sub_No=?";
			return QueryHelper.update(sql, teacherid,examid);
		}
		else {
			String sql ="INSERT INTO `teaofexam`(teano,sub_no,exampass) VALUES(?,?,1)";
			return QueryHelper.update(sql, teacherid,examid);
		}*/
		String sql="update teaofexam set exampass=1 where signstate=0 and schoolstate=0 and teano=? and sub_no=?";
		return QueryHelper.update(sql, teacherid,examid);
	}

	public  List<Teacher> getFinishExam(Integer page,Integer count,String subno)
	{
		String sql = "select  t.teano teano,t.teaname teaname,e.sub_name teatel " +
				"from`teaofexam` te,`teacher` t ,examsubject e  " +
				"where te.TeaNo=t.TeaNo and te.sub_no=e.sub_no and te.exampass=1 and te.sub_no=?";
		return QueryHelper.queryPage(Teacher.class, sql, page,count,subno);
	}
	
	public List<Teacher> getAllFinishExam()
	{
		String sql = "select t.* " +
				"from`teaofexam` te,`teacher` t " +
				"where te.TeaNo=t.TeaNo and te.exampass=1";
		return QueryHelper.query(Teacher.class, sql);
	}
	
	public Long getFinishCount()
	{
		String sql = "select count(*) " +
				"from`teaofexam` te,`teacher` t " +
				"where te.TeaNo=t.TeaNo and te.exampass=1;";
		return QueryHelper.queryUnique(Long.class, sql);
	}

	/**
	 * 验证特定用户是否已经报名并审核成功
	 */
	@Override
	public TeaOfExam getTeaOfExams(TeaOfExam subject) {
		String sql = "select * from teaofexam where teano=? and sub_no=? and signstate=? and schoolstate=?";
		TeaOfExam item = QueryHelper.queryUnique(TeaOfExam.class, sql,
				subject.getTeano(), subject.getSub_no(),
				subject.getSignstate(), subject.getSchoolstate());
		return item;
	}

	/**
	 * 报名
	 */
	@Override
	public Integer SaveTeaOfExam(TeaOfExam subject) {
		String sql = "insert into teaofexam values(?,?,?,?,0,null,null)";
		Integer result = QueryHelper.update(sql, subject.getSub_no(),
				subject.getTeano(), subject.getSignstate(),
				subject.getSchoolstate());
		return result;
	}

	/**
	 * 查看特定用户的审核状态
	 */
	@Override
	public List<TempTable> getAllInfor(Teacher teano) {
		String sql = "select e.sub_name,e.sub_no  state,e.sub_time,tf.signstate id,tf.schoolstate schoolstate "
				+ "from  teacher t,examsubject e,teaofexam tf  "
				+ "where tf.teano=? and t.teano=tf.teano  and tf.sub_no=e.sub_no "
				+ "order by e.sub_time DESC ";
		List<TempTable> tempTables = QueryHelper.query(TempTable.class, sql,
				teano.getTeano());
		return tempTables;
	}

	// all page count
	@Override
	public Long getcounts(Teacher teano) {
		String sql = "select count(*) id "
				+ "from  teacher t,examsubject e,teaofexam tf  "
				+ "where tf.teano=? and t.teano=tf.teano  and tf.sub_no=e.sub_no ";
		return QueryHelper.query(Long.class, sql, teano.getTeano()).get(0);
	}

	// 分页查询
	@Override
	public List<TempTable> getsome(Teacher teano, Integer page, Integer count) {
		String sql = "select e.sub_name,e.sub_no  state,e.sub_time,tf.signstate id,tf.schoolstate schoolstate "
				+ "from  teacher t,examsubject e,teaofexam tf  "
				+ "where tf.teano=? and t.teano=tf.teano  and tf.sub_no=e.sub_no "
				+ "order by e.sub_time DESC ";
		return QueryHelper.queryPage(TempTable.class, sql, page, count,
				teano.getTeano());
	}

	/**
	 * 教务办查看报名科目及其人数
	 */
	@Override
	public List<TempTable> getRegister() {
		String sql = "select count(*) id,e.sub_no state,e.sub_name sub_name,e.sub_time sub_time "
				+ "from teaofexam ef, examsubject e "
				+ "where ef.sub_no=e.sub_no "
				+ "group by ef.sub_no order by e.sub_time DESC";
		List<TempTable> tempTable = QueryHelper.query(TempTable.class, sql);
		return tempTable;
	}

	@Override
	public List<TempTable> getRegisterSome(Integer page, Integer count,String subordunit) {
	
		/*String sql = "select count(*) id,e.sub_no state,e.sub_name sub_name,e.sub_time sub_time "
				+ "from teaofexam ef, examsubject e ,teacher t"
				+ "where ef.sub_no=e.sub_no and t.teano=ef.teano and t.subordunit=? "
				+ "group by ef.sub_no order by e.sub_time DESC";*/
		String sql="select count(*) id,e.sub_no state,e.sub_name sub_name,e.sub_time sub_time from teaofexam ef, examsubject e,(select teano from teacher where subordunit=? ) t where ef.sub_no=e.sub_no and ef.teano=t.teano  group by ef.sub_no order by e.sub_time DESC";
		List<TempTable> tempTable = QueryHelper.queryPage(TempTable.class, sql,
					page, count,subordunit);
		return tempTable;
	}

	@Override
	public Long getRegisterCounts() {
		String sql = "SELECT COUNT(*) FROM examsubject e WHERE e.sub_time<= NOW()";
		return QueryHelper.query(Long.class, sql).get(0);
	}

	/**
	 * 教务处人员查询报名列表
	 */
	@Override
	public List<TempTable> getSingleRegister() {
		String sql = "select count(*) id,e.sub_no state,e.sub_name sub_name,e.sub_time sub_time "
				+ "from teaofexam ef, examsubject e  "
				+ "where ef.sub_no=e.sub_no  "
				+ "group by ef.sub_no order by e.sub_time asc";
		List<TempTable> tempTables = QueryHelper.query(TempTable.class, sql);
		return tempTables;
	}

	/**
	 * 教务办查看报名列表
	 */
	@Override
	public List<Teacher> getRegisterUserInfor(String subno) {
		String sql = "select t.teaname ,t.teano,t.subordunit,tf.signstate teasex,e.sub_name jobtitle  "
				+ "from  teaofexam tf ,teacher t ,examsubject e "
				+ " where tf.teano=t.teano and  e.sub_no=tf.sub_no and tf.sub_no=?";
		List<Teacher> teachers = QueryHelper.query(Teacher.class, sql, subno);
		return teachers;
	}

	@Override
	public List<Teacher> getRegisterUserInforSome(String subno, Integer page,
			Integer count,String subordunit) {
		String sql = "select t.teaname ,t.teano,t.subordunit,tf.signstate teasex,tf.schoolstate authority,e.sub_name jobtitle  "
				+ "from  teaofexam tf ,teacher t ,examsubject e "
				+ " where tf.teano=t.teano and  e.sub_no=tf.sub_no and tf.sub_no=? and t.subordunit=?";
		List<Teacher> teachers = QueryHelper.queryPage(Teacher.class, sql,
				page, count, subno,subordunit);
		return teachers;
	}

	public List<Teacher> getRegisterUserInforSomeDynamicSearch(String subno, Integer page,
			Integer count,String subordunit) {
		String sql = "select t.teaname ,t.teano,t.subordunit,tf.signstate teasex,tf.schoolstate authority,e.sub_name jobtitle  "
				+ "from  teaofexam tf ,teacher t ,examsubject e "
				+ " where tf.teano=t.teano and  e.sub_no=tf.sub_no and tf.sub_no=? and t.subordunit=?";
		List<Teacher> teachers = QueryHelper.queryPage(Teacher.class, sql,
				page, count, subno,subordunit);
		return teachers;
	}
	
	@Override
	public Long getRegisterUserInforCounts(String subno) {
		String sql = "select count(*) "
				+ "from  teaofexam tf ,teacher t ,examsubject e "
				+ " where tf.teano=t.teano and  e.sub_no=tf.sub_no and tf.sub_no=?";
		return QueryHelper.query(Long.class, sql, subno).get(0);
	}

	/**
	 * 审核通过单个人员报名信息
	 */
	@Override
	public Integer updateRegister(Integer signal, String teano, String subno) {
		String sql = "update teaofexam set  signstate=? where teano=? and sub_no=? and signstate=1 and schoolstate=1";
		Integer result = QueryHelper.update(sql, signal, teano, subno);
		return result;
	}

	/**
	 * 教务办审核通过所在学院的所有报名
	 */
	@Override
	public Integer updateAllTeacher(Integer signal, String subno,String subordunit) {
		String sql = "update teaofexam te," +
				" ( select t.teano teano" +
				" from teacher t,teaofexam tf " +
				" where tf.teano=t.teano and t.subordunit=?  and t.teano=tf.teano ) h " +
				" set  te.signstate=0  where te.sub_no=? and te.signstate=1 and te.schoolstate=1 and te.teano=h.teano";
		Integer result = QueryHelper.update(sql, subordunit,subno);
		return result;
	}

	/**
	 * 教务处查询指定科目的人员的报名信息
	 */
	@Override
	public List<Teacher> getSecondUserInfor(String subno) {
		String sql = "select t.teaname teaname,t.teano teano,t.subordunit subordunit ,tf.signstate authority,tf.schoolstate teasex, e.sub_name jobtitle"
				+ " from teacher t ,teaofexam tf,examsubject e "
				+ " where t.teano=tf.teano and e.sub_no=tf.sub_no  and tf.sub_no=?"
				+ " group by t.subordunit";
		List<Teacher> teachers = QueryHelper.query(Teacher.class, sql, subno);
		return teachers;
	}

	@Override
	public Long getSecondUserInforCounts(String subno) {
		String sql = "select count(*)"
				+ " from teacher t ,teaofexam tf,examsubject e "
				+ " where t.teano=tf.teano and e.sub_no=tf.sub_no and tf.signstate=0 and tf.sub_no=?";
		return QueryHelper.query(Long.class, sql, subno).get(0);
	}
/**
 * 教务处查询指定科目的报名人员的信息
 */
	@Override
	public List<Teacher> getSecondUserInforSome(String subno, Integer page,
			Integer count) {
		    
		String sql = "select t.teaname teaname,t.teano teano,t.subordunit subordunit ,tf.schoolstate teasex, tf.signstate authority,e.sub_name  jobtitle"
				+ " from teacher t ,teaofexam tf,examsubject e "
				+ " where t.teano=tf.teano and e.sub_no=tf.sub_no  and tf.sub_no=?"
				;
		List<Teacher> teachers = QueryHelper.queryPage(Teacher.class, sql,
				page, count, subno);
		return teachers;
	}

	/***
	 * 教务处审核通过特定科目所有的报名
	 */
	@Override
	public Integer updateSecondAll(String subno) {
		String sql = "update teaofexam set schoolstate=0 where sub_no=? and signstate=0 and schoolstate=1";
		Integer result = QueryHelper.update(sql, subno);
		return result;
	}

	/**
	 * 教务处审核通过特定科目特定人员的报名信息
	 */
	@Override
	public Integer updateSecond(String teano, String subno) {
		String sql = "update teaofexam set schoolstate=0  where sub_no=? and teano=? and signstate=0 and schoolstate=1";
		Integer result = QueryHelper.update(sql, subno, teano);
		return result;
	}
	
    /**
     * 取消报名
     */
	@Override
	public Integer DeleteRegister(String teano, String subno) {
		String sql="delete from teaofexam where teano=? and sub_no=?";
		Integer result= QueryHelper.update(sql, teano,subno);
		return result;
	}
    /**
     * 删除与报名相关的资源
     */
	@Override
	public Integer DeleteRegisterResource(String teano) {
		String sql="delete from teaoftra where teano=?";
		Integer result=QueryHelper.update(sql, teano);
		return result;
	}

	@Override
	public Teacher getTeacher(String subno, String teano) {
		String sql="select  t.teano teano,t.teaname teaname,e.sub_no teatel from teaofexam te,teacher t ,examsubject e where te.TeaNo=t.TeaNo and e.sub_no=te.sub_no and te.exampass=1 and te.sub_no=? and t.teano=?";
	    Teacher teacher=QueryHelper.queryUnique(Teacher.class, sql, subno,teano);
		return teacher;
	}

	/*@Override
	public List<ExamStatus> getExamStatus(String subname) {
		String sql="select * from examstatus where sub_name=?";
		return QueryHelper.query(ExamStatus.class, sql, subname);
	}*/

	/**
	 * 查询某一资源对应的科目报名是否审核通过
	 */
	@Override
	public TeaOfExam getSchoolState(String teano, String tri_name,String subno) {
		String sql="select te.schoolstate from trares tr,teaoftra tf ,teaofexam te where tr.tri_no=tf.tri_no and te.teano=tf.teano and tri_name=? and te.teano=? and te.sub_no=?";
		return QueryHelper.queryUnique(TeaOfExam.class, sql, tri_name,teano,subno);
	}

	@Override
	public Integer UpdateStudyPasstime(String subno, String teano) {
		String sql="update teaofexam set studypasstime=now() where sub_no=? and teano=?";
		return QueryHelper.update(sql, subno,teano);
	}

	@Override
	public Integer UpdateExamPasstime(String subno, String teano) {
		String sql="update teaofexam set exampassTime=now() where sub_no=? and teano=?";
		return QueryHelper.update(sql, subno,teano);
	}

	@Override
	public List<TeaOfExam> getTeaOfExams(String subno) {
		String sql="select * from teaofexam where sub_no=?";
		return QueryHelper.query(TeaOfExam.class, sql, subno);
	}
/**
 * 教务处跨级审核
 */
	@Override
	public Integer AdvanceAgree(String subno, String teano) {
		String sql="update teaofexam set signstate=0,schoolstate=0 where sub_no=? and teano=?";
		return QueryHelper.update(sql, subno,teano);
	}

@Override
public long deleteTeaofExam() {
	String sql="truncate table teaofexam";
	return QueryHelper.update(sql);
}
/**
 * 查看某位教师的某一科目的学习完成时间或者考试完成时间是否为空
 */
@Override
public TeaOfExam queryStudyTime(String subno, String teano) {
	String sql="select * from teaofexam where sub_no=? and teano=?";
	return QueryHelper.queryUnique(TeaOfExam.class, sql, subno,teano);
}




}
