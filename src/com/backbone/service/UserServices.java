package com.backbone.service;

import java.util.List;

import com.backbone.dao.UserDao;
import com.backbone.dao.implement.TeaOfExamImpl;
import com.backbone.dao.implement.TeacherImple;
import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.TempTable;


public class UserServices {
	@SuppressWarnings("unused")
	private UserDao userdao = new TeacherImple();

	/**
	 * 登录模块的用户验证
	 */
	public boolean Validate(String uname, String passwd, Integer authority) {
		List<Teacher> users = userdao
				.getObjByProperty(uname, passwd, authority);
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	public List<Teacher> getPassSome(Integer page,Integer count,String subno)
	{
		return new TeaOfExamImpl().getFinishExam(page, count, subno);
	}
	
	public Long getPassCount()
	{
		return new TeaOfExamImpl().getFinishCount();
	}
	
	public Integer finishExam(String teacherid,String examid)
	{
	  return  new TeaOfExamImpl().finishExam(teacherid, examid);
	}
	
	public  List<Teacher> canExam(String teacherid,String examid)
	{
		return new TeaOfExamImpl().canExam(teacherid, examid);
	}

	/**
	 * 查询所有用户信息
	 */
	public List<Teacher> getAlUsers() {
		return userdao.getAllObj();
	}

	/**
	 * 查询指定用户ID的信息
	 */
	public List<Teacher> get(String id) {
		return userdao.getObj(id);
	}

	/**
	 * 更新用户信息
	 */
	public Integer update(Teacher user) {
		return userdao.UpdateObj(user);
	}

	/**
	 * 忘记密码
	 */
	public boolean ValidProQuestion(Teacher user) {
		List<Teacher> users = userdao.getObjByProperty(user);
		if (users.size() > 0)
			return true;
		return false;
	}

	/**
	 * 修改信息(包含修改密码)
	 */
	public boolean CorrectPasswd(String newpwd, String oldpwd, String loginuser,String teaname, String cardnumber,Integer teasex,String subordunit,String teatel,String teaaddress) {
		Integer flag = userdao.CorrectPwd(newpwd, oldpwd, loginuser,teaname,cardnumber,teasex,subordunit,teatel,teaaddress);
		if (flag > 0)
			return true;
		return false;
	}
   /**
    * 修改信息（不包含修改密码）
    */
	public boolean CorrectInfor(String loginuser, String teaname,
			String cardnumber, Integer teasex, String subordunit,
			String teatel, String teaaddress){
		Integer result=	userdao.CorrectInfor(loginuser, teaname, cardnumber, teasex, subordunit, teatel, teaaddress);
	    if(result>0)
	    	return true;
	    return false;
	}
	/**
	 * 查询教职工的权限
	 */
	public Integer getAuthority(String teano) {
		return userdao.getAuthority(teano);
	}
   /**
    * 注册用户
    */
	public boolean SaveOrUpdateUser(Teacher teacher){
		Integer result= userdao.saveUser(teacher);
		if(result>0)
			return true;
		return false;
	}
	/**
	 * 通过查询审核状态来确定用户是否有学习培训资源的权限
	 */
	public TeaOfExam geTeaOfExam(String teano){
	   return	userdao.getSchoolState(teano);
	}
	/**
	 * 通过教师工号查询报名考试编号
	 */
	/*
	public List<TeaOfExam> getSubNo(String teano,String param){
		return userdao.getSubNo(teano,param);
	}*/
	/**
	 * 查询教师是否已完成考试
	 */
	public TeaOfExam IsFinishExam(String teano,String subno){
		TeaOfExam teaOfExam=userdao.getExamPass(teano, subno);
		return teaOfExam;
	}
	/**
	 * 查询通过考试的教师的考场分布信息
	 */
	public ExamStatus getExamInfor(String teano,String subno){
		return userdao.getExampassInfor(teano,subno);
	}
	/**
	 * 通过工号来查找员工所属学院
	 */
	public Teacher getSubordUnit(String teano)
	{
        return userdao.getSubordUnit(teano);
	}
	/**
	 * 查看教师的信息是否完整
	 */
	public Teacher   InforIsComplete(String teano){
		return userdao.getValidUserInfor(teano);
	}
	/**
	 * 分页查询所有用户信息
	 */
	public List<Teacher> getAlUsersPage(Integer page,Integer count) {

		return ((TeacherImple)userdao).getTeacherContent(page, count);
	}
	
	public List<Teacher> getTeacher(String k)
	{
		return ((TeacherImple)userdao).findUser(k);
	}
	
	public Long getAllUserCount()
	{
		return ((TeacherImple)userdao).getAllTeachercount();
	}
	
	public Integer RefuseRegist(String teano,String subno,Integer authority){
		return userdao.RefuseRegist(teano, subno, authority);
	}
	/**
	 * 考试中心得到所有报名人员的信息
	 */
	public List<Teacher> getRegistUserInfor(String subno){
		return userdao.getAllRegistInfor(subno);
	}
	
	public Teacher getTeacher(String teano,String subno){
		return userdao.getTeaOfExam(teano, subno);
	}
	public TeaOfExam getExam(String teano,Integer tri_no){
		return userdao.getExam(teano, tri_no);
	}
	
	public Teacher getTeacherInfor(String subno,String teano){
		return new TeaOfExamImpl().getTeacher(subno, teano);
	}

	/**
	 * 通过工号和银行卡号来查找此人是否存在
	 */
	public Teacher getUserInfor(String cashcardid,String teano){
		return userdao.getTeacher(teano, cashcardid);
	}
	/**
	 * 银行卡号验证通过，修改密码
	 */
	public Integer UpdatePwd(String newpwd,String teano,String cashcardid){
		return userdao.UpdatePwd(newpwd, teano, cashcardid);
	}
	
	/**
	 * 通过工号验证银行卡号是否为空
	 */
	public TempTable getCashCard(String teano){
		return userdao.getCashCardId(teano);
	}
}
