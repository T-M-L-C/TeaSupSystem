package com.backbone.dao;

import java.util.List;


import com.backbone.entity.po.ExamStatus;
import com.backbone.entity.po.TeaOfExam;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.TempTable;

public interface TeaOfExamDao {
	public TeaOfExam getTeaOfExams(TeaOfExam subject);

	public Integer SaveTeaOfExam(TeaOfExam subject);

	public List<TempTable> getAllInfor(Teacher teano);

	public List<TempTable> getsome(Teacher teano, Integer page, Integer count);

	public Long getcounts(Teacher teano);

	public List<TempTable> getRegister();

	public List<TempTable> getRegisterSome(Integer page, Integer count,String subordunit);

	public Long getRegisterCounts();

	public List<TempTable> getSingleRegister();

	public List<Teacher> getRegisterUserInfor(String subno);

	public Long getRegisterUserInforCounts(String subno);

	public List<Teacher> getRegisterUserInforSome(String subno, Integer page,
			Integer count,String subordunit);

	public Integer updateRegister(Integer signal, String teano, String subno);

	public Integer updateAllTeacher(Integer signal, String subno,String subordunit);

	public List<Teacher> getSecondUserInfor(String subno);

	public Long getSecondUserInforCounts(String subno);

	public List<Teacher> getSecondUserInforSome(String subno, Integer page,
			Integer count);

	public Integer updateSecondAll(String subno);

	public Integer updateSecond(String teano, String subno);
	
    public Integer DeleteRegister(String teano,String subno);
    
    public Integer DeleteRegisterResource(String teano);
    
    public Teacher getTeacher(String subno,String teano);
 
    public TeaOfExam getSchoolState(String teano,String tri_name,String subno);
    
    public Integer UpdateStudyPasstime(String subno,String teano);
    
    public Integer UpdateExamPasstime(String subno,String teano);
   
    public List<TeaOfExam> getTeaOfExams(String subno);
    
    public Integer AdvanceAgree(String subno,String teano);
    
    public long deleteTeaofExam();
    
    public TeaOfExam queryStudyTime(String subno,String teano);
    

}
