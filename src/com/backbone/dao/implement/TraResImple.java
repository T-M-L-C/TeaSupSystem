package com.backbone.dao.implement;

import java.util.List;

import com.backbone.common.DeleteFile;
import com.backbone.dao.TraResDao;
import com.backbone.database.QueryHelper;
import com.backbone.entity.po.TraRes;
import com.backbone.entity.vo.WatchTimeTemp;

public class TraResImple implements TraResDao {

	@Override
	public <T> Integer Save(T obj) {
		String sql = "insert into trares values(null,?,?,?,now(),?,?,?,?,?)";
		return QueryHelper.update(sql, ((TraRes) obj).getTri_type(),
				((TraRes) obj).getTri_name(), ((TraRes) obj).getTri_path(),
				((TraRes) obj).getTri_countpage(),
				((TraRes) obj).getTri_pagetime(),
				((TraRes) obj).getTri_newpath(),((TraRes) obj).getTri_candownload(),((TraRes) obj).getTri_muststudy());
	}

	@Override
	public Integer getIdByPath(String sPath) {
		String sql = "SELECT t.`Tri_No` FROM `trares` t WHERE t.`Tri_NewPath`=?";
		return QueryHelper.query(Integer.class, sql, sPath).get(0);
	}

	@Override
	public int updatePageCounts(Integer id, Integer counts) {
		String sql = "UPDATE `trares` t SET t.`Tri_CountPage`=? WHERE t.`Tri_No`=?";
		return QueryHelper.update(sql, counts, id);
	}

	@Override
	public <T> Integer UpdateObj(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void DeleteObj(T obj) {
		String sql = "delete from trares where tri_no=?";
		QueryHelper.update(sql, obj);
	}

	@Override
	public List<TraRes> getAllObj(String parameter,String subno,Integer page,Integer count) {
		String sql="";
		List<TraRes> lists = null ;
		if(parameter.equals("distinct")){
			 sql = "select distinct(t.tri_no),t.tri_type,t.tri_Name,t.tri_path,t.tri_time,t.tri_countpage,t.tri_pagetime,t.tri_newpath "
					 +",t.tri_candownload,t.tri_muststudy "
					+" from trares t,(select  tf.teano teano,e.tri_no "
											+"from  examoftra e,teaofexam tf  "
											+"where e.sub_no=tf.sub_no and tf.teano is not null  and tf.sub_no=?"
										+") as h "
										+"where  t.tri_no=h.tri_no ";
			 if(page!=null)
			 {
				 lists = QueryHelper.queryPage(TraRes.class, sql, page, count, subno);
			 }else
			 lists = QueryHelper.query(TraRes.class, sql,subno);
		}
		else if (parameter.equals("all")) {
			sql = "SELECT * FROM `trares` ORDER BY Tri_Time DESC";
			if(page!=null)
			 {
				 lists = QueryHelper.queryPage(TraRes.class, sql, page, count);
			 }else
			 lists = QueryHelper.query(TraRes.class, sql);
		}
		
		return lists;
	}

	@Override
	public List<TraRes> getpage(Integer count, Integer number) {
		String sql = "SELECT * FROM `trares` t "
				+ "ORDER BY t.`Tri_Time` DESC ";
		List<TraRes> lists = QueryHelper.queryPage(TraRes.class, sql, number, count);
		return lists;
	}
	public Long getAllCount()
	{
		String sql = "SELECT COUNT(*) FROM `trares` t";
		return QueryHelper.queryUnique(Long.class, sql);
	}

	@Override
	public TraRes getObj(Integer obj) {
		String sql = "SELECT * FROM `trares` t where Tri_No=?";
		return QueryHelper.queryUnique(TraRes.class, sql, obj);
	}

	@Override
	public <T> List<TraRes> getObjByProperty(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<TraRes> getObjByProperty(T attrone, T attrtwo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(TraRes trares) {
		String sql = "UPDATE `trares` t "
				+ "SET t.`Tri_Name`=?,t.`Tri_PageTime`=?,t.Tri_CanDownload=?,t.Tri_MustStudy=? "
				+ "WHERE t.`Tri_No`=?";
		return QueryHelper.update(sql, trares.getTri_name(), trares.getTri_pagetime(),
				trares.getTri_candownload(),trares.getTri_muststudy(), trares.getTri_no());
	}

	@Override
	public List<WatchTimeTemp> getSomeAndWatchTime(Integer count,
			Integer number, String teacherid) {
		String sql = "SELECT * " + "FROM `trares` r,`teaoftra` t "
				+ "WHERE t.`Tri_No`=r.`Tri_No` AND t.`TeaNo`=? "
				+ "ORDER BY r.`Tri_Time` DESC";
		return QueryHelper.queryPage(WatchTimeTemp.class, sql, count, number,
				teacherid);
	}

	@Override
	public Long getcounts(String teacherid,String subno) {
        String sql="SELECT COUNT(*) " +
        		"FROM trares " ;
        	
		if (teacherid == null) {
			return QueryHelper.queryUnique(Long.class, sql);
		} else {
			//sql = "SELECT COUNT(*) FROM teaoftra t1,trares t2 WHERE t1.`TeaNo`=? AND t1.`Tri_No`=t2.`Tri_No`";
			 sql=" SELECT COUNT(*) FROM teaofexam te,teaoftra tf,trares t WHERE te.`TeaNo`=tf.`TeaNo` AND t.`Tri_No`=tf.`Tri_No` AND te.`Sub_No`=? AND te.`TeaNo`=?";
			return QueryHelper.queryUnique(Long.class, sql ,subno,teacherid);
		}

	}

	@Override
	public TraRes getRes(String subno, String tri_time,String tri_type) {
		String sql = "select distinct(t.tri_no),t.tri_type,t.tri_Name,t.tri_path,t.tri_time,t.tri_countpage,t.tri_pagetime,t.tri_newpath "
					+" from trares t,(select  tf.teano teano,e.tri_no "
											+"from  examoftra e,teaofexam tf  "
											+"where e.sub_no=tf.sub_no and tf.teano is not null  and tf.sub_no=?"
										+") as h "
										+"where  t.tri_no=h.tri_no and t.tri_time=? and t.tri_type=?";
		return QueryHelper.queryUnique(TraRes.class, sql, subno,tri_time,tri_type);
	}

	@Override
	public List<TraRes> FinishLearnResource(String teano, String subno) {
		String sql="SELECT t.`Tri_No`,t.`Tri_Name` FROM examoftra eo, trares t ,teaoftra tf WHERE eo.`Tri_No` = t.`Tri_No` AND t.`Tri_No`=tf.`Tri_No` AND tf.`TeaNo`=? AND eo.`Sub_No`=? AND t.`Tri_MustStudy`=1";
		return QueryHelper.query(TraRes.class, sql, teano,subno);
	}

	@Override
	public long PassTraCount(String teano,String subno) {
		String sql=" SELECT COUNT(*)  FROM teaoftra ts,(SELECT t.`Tri_No`, t.`Tri_Name` FROM examsubject e,trares t,examoftra ef WHERE  e.`Sub_No`=ef.`Sub_No` AND t.`Tri_No`=ef.`Tri_No` AND ef.`Sub_No`=? AND t.`Tri_MustStudy`=1) n WHERE ts.`Tri_No`=n.tri_no  AND ts.`TeaNo`=? AND ts.`Watch_Time`=1";
		return QueryHelper.queryUnique(long.class, sql,subno,teano);
	}

	@Override
	public List<TraRes> findAll() {
		String sql="select * from trares";
		return QueryHelper.query(TraRes.class, sql);
	}





}
