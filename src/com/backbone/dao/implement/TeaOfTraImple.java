package com.backbone.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.backbone.database.QueryHelper;
import com.backbone.entity.po.TeaOfTra;

public class TeaOfTraImple {
	public Integer add(TeaOfTra tot) {
		String sql = "insert into `teaoftra` values(?,?,?)";
		return QueryHelper.update(sql, tot.getTri_no(), tot.getTeano(), "0");
	}

	public Integer adds(String teacherid, List<Integer> resids) {
		StringBuffer sb=new StringBuffer();
		StringBuilder sbl=new StringBuilder();
		sb.append("select * from teaoftra where teano=? and tri_no=?");
		TeaOfTra teaOfTra=new TeaOfTra();
		for(Integer res :resids){
		 teaOfTra=	QueryHelper.queryUnique(TeaOfTra.class, sb.toString(), teacherid,res);
			 if(teaOfTra!=null)
			 {
				 sbl.append("delete from teaoftra where teano=? and tri_no=?");
				 QueryHelper.update(sbl.toString(), teaOfTra.getTeano(),teaOfTra.getTri_no());
			 }
		}
/*		String sqlquery="select * from teaoftra";
		List<TeaOfTra> teaOfTras=QueryHelper.query(TeaOfTra.class, sqlquery);
	    for(TeaOfTra items:teaOfTras){
	    	for(int i=0;i<resids.size();i++){
	    		if(items.getTeano()==teacherid && items.getTri_no()==resids.get(i)){
		    		resids.remove(i);
		    	}
	    	}   	
	    }*/
		String sql = "insert into `teaoftra` values";
		List<Object> objs = new ArrayList<Object>();
		for (Integer item : resids) {
			sql += "(?,?,?),";
			objs.add(item);
			objs.add(teacherid);
			objs.add(0);
		}
		sql = sql.substring(0, sql.length() - 1);
		return QueryHelper.update(sql, objs.toArray());
	}

	public Integer update(Integer resid, String teacherid, Integer process) {
		String sql1 = "select count(*) from `teaoftra` where Tri_No=? AND TeaNo=?";
		if(QueryHelper.queryUnique(Long.class, sql1, resid,teacherid) >0)
		{
			String sql ="UPDATE `teaoftra` SET Watch_Time=1 WHERE Tri_No=? AND TeaNo=?";
			return QueryHelper.update( sql, resid,teacherid);
		}
		else {
			String sql = "INSERT INTO `teaoftra` VALUES(?,?,?)";
			return QueryHelper.update(sql, resid, teacherid, process);
		}
	}

	public List<String> getTOTByPropery(String teacherid, Integer resid) {
		String sql = "SELECT t.`Watch_Time` FROM `teaoftra` t WHERE t.`TeaNo`=? AND t.`Tri_No`=?";
		return QueryHelper.query(String.class, sql, teacherid, resid);
	}
	//查询所有报名人员的工号
   public  List<TeaOfTra> gettTeaOfTras(){
	   String sql="select distinct(teano) from teaoftra";
	   return QueryHelper.query(TeaOfTra.class, sql);
   }
   //将报名人员与学习资源绑定在一起
  public long UpdateTeaOfTra(Integer tra_no,List<TeaOfTra> teano){
	  String sql="insert into teaoftra values";
	  String sqlvalue="(?,?,0),";
	  List<Object> objs = new ArrayList<Object>();
	  for(TeaOfTra item : teano){
		  sql+=sqlvalue;
		  objs.add(tra_no);
		  objs.add(item.getTeano());
	  }
	  sql=sql.substring(0, sql.length()-1);
	   return QueryHelper.update(sql, objs.toArray());
   }
  
  public Integer RemoveTeaofTra(){
	  String sql="truncate table teaoftra";
	  return QueryHelper.update(sql);
  }
}
