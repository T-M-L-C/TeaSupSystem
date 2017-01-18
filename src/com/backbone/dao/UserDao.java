package com.backbone.dao;

import java.util.List;

import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.TempTable;

;

public interface UserDao {

	/**
	 * @param <T>
	 * @return
	 */
	public <T> Integer UpdateObj(T obj);
    
	public Integer saveUser(Teacher teacher);
	/**
	 * @param obj
	 */
	public <T> void DeleteObj(T obj);

	/**
	 * @return
	 */
	public List<Teacher> getAllObj();

	/**
	 * @param obj
	 */
	public List<Teacher> getObj(String obj);

	public  List<Teacher> getObjByProperty(String uname, String passwd,
			Integer authority);

	public Integer CorrectPwd(String newpwd, String oldpwd, String loginuser,String teaname, String cardnumber,Integer teasex,String subordunit,String teatel,String teaaddress);

	public List<Teacher> getObjByProperty(Teacher user);

	public Integer getAuthority(String teano);
	
	public Integer CorrectInfor( String loginuser,String teaname, String cardnumber,Integer teasex,String subordunit,String teatel,String teaaddress);
   
	public TeaOfExam getSchoolState(String teano);

	public TeaOfExam getExamPass(String teano,String subno);
	
	public ExamStatus getExampassInfor(String teano,String subno);
	
	public  Teacher getSubordUnit(String teano);
	
	public Teacher getValidUserInfor(String teano);
	
	public Integer RefuseRegist(String teano,String subno,Integer authority);
	
	public List<Teacher> getAllRegistInfor(String subno);
	
	public Teacher getTeaOfExam(String teano,String subno);
	
	public TeaOfExam getExam(String teano,Integer tri_no);
	
 /*   public void SaveRecord(ExamStatus record,String param);*/
    
    public Teacher getTeacher(String teano,String cardnumber);
    
    public Integer UpdatePwd(String newpwd,String teano,String cashcardid);
    
    public TempTable getCashCardId(String teano);
}
