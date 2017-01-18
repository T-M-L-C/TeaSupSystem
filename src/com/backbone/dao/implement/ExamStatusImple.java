package com.backbone.dao.implement;

import java.util.List;

import com.backbone.dao.ExamStatusDao;
import com.backbone.database.QueryHelper;
import com.backbone.entity.po.ExamStatus;

public class ExamStatusImple implements ExamStatusDao {
	
	@Override
	public Integer saveExamStatus(ExamStatus es)
	{
		String sql = "INSERT	INTO	 examstatus(teano,teaname,sub_name,subplace,subplaceother) VALUES(?,?,?,?,?)";
		String sqlselect = "select count(*) from examstatus where TeaNo=? and Sub_Name=?";
		if (QueryHelper.queryUnique(Long.class, sqlselect, es.getTeano(),es.getSub_name()) == 1) {
			sql = "update examstatus set TeaName=?,SubPlace=?,SubPlaceOther=? where TeaNo=? and Sub_Name=?";
			return QueryHelper.update(sql,es.getTeaname(),es.getSubplace(),es.getSubplaceother(), es.getTeano(),es.getSub_name());
		}
		return QueryHelper.update(sql, es.getTeano(),es.getTeaname(),es.getSub_name(),es.getSubplace(),es.getSubplaceother());
	}

	@Override
	public Integer updateExamStatus(String teano, String sub_name) {
		String sql="update examstatus set querytime=now() where teano=? and sub_name=?";
		Integer result=QueryHelper.update(sql, teano,sub_name);
		return result;
	}

	@Override
	public List<ExamStatus> getAllRecords(String subname) {
		String sql="select * from examstatus where sub_name=? order by querytime  desc";
		return QueryHelper.query(ExamStatus.class, sql, subname);
	}

	@Override
	public ExamStatus getExamStatusObj(String teano, String subname) {
		String sql="select * from examstatus where teano=? and sub_name=?";
		ExamStatus examStatus=QueryHelper.queryUnique(ExamStatus.class, sql, teano,subname);
		return examStatus;
	}

	@Override
	public long RemoveExamStatus() {
		String sql="truncate table examstatus";
		return QueryHelper.update(sql);
	}

	
	
}
