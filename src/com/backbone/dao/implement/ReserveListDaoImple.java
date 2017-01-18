package com.backbone.dao.implement;

import java.util.List;

import com.backbone.dao.ReserveListDao;
import com.backbone.database.QueryHelper;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.ReserveList;
import com.backbone.entity.po.Teacher;

public class ReserveListDaoImple  implements ReserveListDao{

	@Override
	public List<ReserveList> findAll(String subno) {
		String sql="select * from reservelist where sub_no=?";
		return QueryHelper.query(ReserveList.class, sql,subno);
	}

	@Override
	public ReserveList findByProperty(String subno, String teano) {
		String sql="select * from reservelist where sub_no=? and teano=?";
		return QueryHelper.queryUnique(ReserveList.class, sql, subno,teano);
	}

	@Override
	public ReserveList findUnique(String subno, String teano) {
		String sql="select * from reservelist where sub_no=? and teano=?";
		return QueryHelper.queryUnique(ReserveList.class, sql, subno,teano);
	}

	@Override
	public long SaveReserveList(ExamSubject subject, Teacher teacher) {
		String sql="insert into reservelist values(?,?,?,?,?)";
		return QueryHelper.update(sql, teacher.getTeano(), subject.getSub_no(),teacher.getTeaname(),subject.getSub_name(),teacher.getSubordunit());
	}

	@Override
	public long RemoveList() {
		String sql="delete from reservelist";
		return QueryHelper.update(sql);
	}



	@Override
	public ReserveList getReserveList(String subno) {
		String sql="select * from reservelist where sub_no=?";
		return QueryHelper.queryUnique(ReserveList.class, sql, subno);
	}

	@Override
	public List<ReserveList> getReserveAll() {
		String sql="select * from reservelist order by sub_no asc";
		return QueryHelper.query(ReserveList.class, sql);
	}



}
