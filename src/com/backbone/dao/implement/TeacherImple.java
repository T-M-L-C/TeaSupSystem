package com.backbone.dao.implement;

import java.util.List;

import com.backbone.dao.UserDao;
import com.backbone.database.QueryHelper;

import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.TempTable;

public class TeacherImple implements UserDao {

	@Override
	public <T> Integer UpdateObj(T obj) {
		String sql = "update teacher set  TeaPwd=? where  TeaNo=?";
		return QueryHelper.update(sql, ((Teacher) obj).getTeapwd(),
				((Teacher) obj).getTeano());
	}

	@Override
	public <T> void DeleteObj(T obj) {
		String sql = "delete from teacher where teano=?";
		QueryHelper.update(sql, ((Teacher) obj).getTeano());
	}

	@Override
	public List<Teacher> getAllObj() {
		String sql = "select * from teacher";
		List<Teacher> Teachers = null;
		Teachers = QueryHelper.query(Teacher.class, sql);
		return Teachers;
	}

	/**
	 * 验证登录信息
	 */
	@Override
	public  List<Teacher> getObjByProperty(String attrone, String attrtwo,
			Integer attrthree) {
		String sql = "select * from teacher where  TeaNo=? and TeaPwd=?  and Authority=?";
		List<Teacher> Teacher = QueryHelper.query(Teacher.class, sql, attrone,
				attrtwo, attrthree);
		return Teacher;
	}

	@Override
	public List<Teacher> getObj(String obj) {
		// TODO Auto-generated method stub
		List<Teacher> teachers = null;
		String sql = "select * from teacher where teano=?";
		teachers = QueryHelper.query(Teacher.class, sql, obj);
		return teachers;
	}

	@Override
	public List<Teacher> getObjByProperty(Teacher user) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改密码
	 */
	@Override
	public Integer CorrectPwd(String newpwd, String oldpwd, String loginuser,String teaname, String cardnumber,Integer teasex,String subordunit,String teatel,String teaaddress) {
		String rstpwd = "update teacher set teapwd=?,teaname=?,CashCardId=?,teasex=?,subordunit=?,teatel=?,teaaddress=? where teapwd=? and teano=?";
		Integer result = QueryHelper.update(rstpwd, newpwd,teaname,cardnumber,teasex,subordunit,teatel,teaaddress, oldpwd, loginuser);
		return result;
	}

	@Override
	public Integer getAuthority(String teano) {
		String sql = "select authority from teacher where teano=?";
		Teacher teacher = QueryHelper.queryUnique(Teacher.class, sql, teano);
		return teacher.getAuthority();
	}

	@Override
	public Integer CorrectInfor(String loginuser, String teaname,
			String cardnumber, Integer teasex, String subordunit,
			String teatel, String teaaddress) {
		String sql="update teacher set teaname=?,CashCardId=?,teasex=?,subordunit=?,teatel=?,teaaddress=? where  teano=? ";
		Integer result=QueryHelper.update(sql, teaname,cardnumber,teasex,subordunit,teatel,teaaddress,loginuser);
		return result;
	}

	@Override
	public Integer saveUser(Teacher teacher) {
		String sql="insert into teacher values(?,?,?,?,?,?,?,?,?)";
		Integer result=QueryHelper.update(sql, teacher.getTeano(),teacher.getTeaname(),teacher.getTeapwd()
				,teacher.getTeasex(),teacher.getTeatel(),teacher.getSubordunit(),teacher.getCashcardid(),teacher.getTeaaddress(),teacher.getAuthority());
		return result;
	}

	@Override
	public TeaOfExam getSchoolState(String teano) {
		String sql="select * from teaofexam where teano=?";
		TeaOfExam teaOfExam=QueryHelper.queryUnique(TeaOfExam.class, sql, teano);
		return teaOfExam;
	}
/*
@Override
	public List<TeaOfExam> getSubNo(String teano,String param) {
		String sql="";
		if(param.equals("all")){
			sql="select sub_no from teaofexam where teano=?";
		}else if (param.equals("querysingle")) {
			sql="select sub_no from teaofexam where teano=? and exampass=1" ;
		}
		List<TeaOfExam> teaOfExam=QueryHelper.query(TeaOfExam.class, sql, teano);
		return teaOfExam;
	}*/
/**
 * 查看教师是否已通过考试
 */
	@Override
	public TeaOfExam getExamPass(String teano,String subno) {
		String sql="select *  from teaofexam where teano=? and sub_no=? ";
		TeaOfExam teaOfExam= QueryHelper.queryUnique(TeaOfExam.class, sql, teano,subno);
		return teaOfExam;
	}
/**
 * 查看通过考试的教师的考场分布
 */
@Override
public ExamStatus getExampassInfor(String teano,String subno) {
	String sql="select * from examstatus where teano=? and sub_name=?";
	ExamStatus examStatus=QueryHelper.queryUnique(ExamStatus.class, sql, teano,subno);
	return examStatus;
}

@Override
public Teacher getSubordUnit(String teano) {
	String sql="select * from teacher where teano=?";
	Teacher teacher=QueryHelper.queryUnique(Teacher.class, sql, teano);
	return teacher;
}
/**
 * 查看用户信息是否完整
 */
@Override
public Teacher getValidUserInfor(String teano) {
	String sql="select count(*) teasex from teacher  where authority=0 and  length(teatel)>0 and length(subordunit)>0 and length(cashcardid)>0 and teano=? ";
	return QueryHelper.queryUnique(Teacher.class, sql, teano);
}
public List<Teacher> getTeacherContent(Integer page,Integer count)
{
	String sql = "select * from teacher";
	return QueryHelper.queryPage(Teacher.class, sql, page, count);
}
public Long getAllTeachercount()
{
	String sql = "select COUNT(*) from teacher";
	return QueryHelper.queryUnique(Long.class, sql);
}

public List<Teacher> findUser(String k)
{
	String sql = "select * from teacher where TeaNo=? or TeaName=?";
	return QueryHelper.query(Teacher.class, sql, k,k);
}

@Override
public Integer RefuseRegist(String teano, String subno, Integer authority) {
	String sql="";
    if(authority==1){
    	sql="update teaofexam set signstate=2 where teano=? and sub_no=? ";
    }
    else if (authority==2) {
		sql="update teaofexam set schoolstate=2 where teano=? and sub_no=?";
	}
    Integer result=QueryHelper.update(sql, teano,subno);
	return result;
}

@Override
public List<Teacher> getAllRegistInfor(String subno) {
	//String sql="select  t.teano teano,t.teaname teaname,t.teatel teatel,t.subordunit subordunit ,t.teasex teasex,t.cashcardid cashcardid, tf.signstate signstate,tf.schoolstate schoolstate,tf.exampass exampass ,h.watch_time watchtime  from teacher t,teaofexam tf , teaoftra h,trares r where t.teano=tf.teano  and h.tri_no=r.tri_no and h.teano=t.teano and (r.tri_type='doc' or r.tri_type='ppt') and tf.sub_no=? ";
	String sql=" SELECT  t.teano teano,t.teaname teaname,t.teatel teatel,t.subordunit subordunit, t.teasex teasex,t.cashcardid cashcardid,t.TeaAddress teaaddress, tf.signstate signstate,tf.schoolstate schoolstate, tf.exampass exampass,tf.studypasstime studypassTime,tf.exampasstime exampassTime FROM teacher t,teaofexam tf  WHERE t.teano=tf.teano  AND tf.sub_no=? ORDER BY studypassTime DESC ,exampassTime DESC,SubordUnit DESC,TeaAddress DESC";
	return QueryHelper.query(Teacher.class, sql, subno);
}

/**
 * 教务办查看特定教师的报名信息
 */
	@Override
	public Teacher getTeaOfExam(String teano, String subno) {
		String sql="select t.teaname teaname,t.teano teano ,t.subordunit subordunit,tf.signstate signstate,tf.schoolstate schoolstate from teaofexam  tf,teacher t where tf.teano=t.teano and t.teano=? and tf.sub_no=?";
		Teacher teacher=QueryHelper.queryUnique(Teacher.class, sql, teano,subno);
		return teacher;
	}

@Override
public TeaOfExam getExam(String teano, Integer tri_no) {
	String sql="select te.exampass from teaofexam te,teaoftra tr where te.teano=tr.teano  and te.teano=? and tr.tri_no=?";
	return QueryHelper.queryUnique(TeaOfExam.class, sql, teano,tri_no);
}
/*
@Override
public void SaveRecord(ExamStatus record,String param) {
	String sql="";
	if(param.equals("update")){
	 sql="update examrecord set querytime=now() where teano=? and teaname=? and subname=? and subordunit=?";
	 QueryHelper.update(sql, record.getTeano(),record.getTeaname(),record.getSubname(),record.getSubordunit());
	}
	else if (param.equals("save")) {
		sql="insert into examrecord(teano,teaname,subname,subordunit,subplace,subplaceother) values(?,?,?,?,?,?)";
		QueryHelper.update(sql, record.getTeano(),record.getTeaname(),record.getSubname(),record.getSubordunit(),record.getSubplace(),record.getSubplaceother());
	}
	
}*/

@Override
public Teacher getTeacher(String teano, String cardnumber) {
	String sql="select * from teacher where teano=? and cashcardid=?";
	return QueryHelper.queryUnique(Teacher.class, sql, teano,cardnumber);
}

@Override
public Integer UpdatePwd( String newpwd, String teano,
		String cashcardid) {
     String sql="update teacher set teapwd=? where teano=? and cashcardid=?";
	return QueryHelper.update(sql, newpwd,teano,cashcardid);
}

@Override
public TempTable getCashCardId(String teano) {
	String sql="select length(cashcardid) id  from teacher where teano=?";
	return QueryHelper.queryUnique(TempTable.class, sql, teano);
}
}
