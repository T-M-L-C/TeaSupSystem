package com.backbone.dao.implement;

import java.util.ArrayList;
import java.util.List;


import com.backbone.database.QueryHelper;
import com.backbone.entity.po.ExamOfTra;

public class ExamOfTraImple {
	public int add(ExamOfTra eot) {
		String sql = "insert into `examoftra` values(?,?)";
		return QueryHelper.update(sql, eot.getSub_no(), eot.getTri_no());
	}

	public Integer deleteByExamid(String examid,Integer traid)
	{
		String sql = "DELETE FROM `examoftra` WHERE Tri_No=? AND Sub_No=?";
		return QueryHelper.update(sql,traid,examid);
	}

	public Integer adds(String examid, List<Integer> resids) {
		String sql = "insert into `examoftra` values";
		String sqlvalue = "(?,?),";
		List<Object> objs = new ArrayList<Object>();
		for (int i = 0; i < resids.size(); i++) {
			sql += sqlvalue;
			objs.add(examid);
			objs.add(resids.get(i));
		}
		sql = sql.substring(0, sql.length() - 1);

		return QueryHelper.update(sql, objs.toArray());
	}

	public List<Integer> getResouceByExamid(String examid) {
		String sql = "SELECT e.`Tri_No` FROM `examoftra` e WHERE e.`Sub_No`=?";
		return QueryHelper.query(Integer.class, sql, examid);
	}

    public long deleteExamofTra(){
    	String sql="truncate table examoftra";
    	return QueryHelper.update(sql);
    }

}
