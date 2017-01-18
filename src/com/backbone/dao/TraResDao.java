package com.backbone.dao;

import java.util.List;

import com.backbone.entity.po.TraRes;
import com.backbone.entity.vo.WatchTimeTemp;

public interface TraResDao {
	/**
	 * @param <T>
	 * @return
	 */
	public <T> Integer Save(T obj);

	public List<WatchTimeTemp> getSomeAndWatchTime(Integer count,
			Integer number, String examid);

	public Integer getIdByPath(String sPath);

	public Integer update(TraRes trares);

	public int updatePageCounts(Integer id, Integer counts);

	public List<TraRes> getpage(Integer count, Integer number);

	public Long getcounts(String teacherid,String subno);

	/**
	 * @param <T>
	 * @return
	 */
	public <T> Integer UpdateObj(T obj);

	/**
	 * @param obj
	 */
	public <T> void DeleteObj(T obj);

	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public List<TraRes> getAllObj(String parameter,String subno,Integer page,Integer count);

	/**
	 * @param obj
	 */
	public TraRes getObj(Integer obj);

	public <T> List<TraRes> getObjByProperty(T obj);

	public <T> List<TraRes> getObjByProperty(T attrone, T attrtwo);
    
	public TraRes getRes(String subno,String tri_time,String tri_type);
	
	public List<TraRes> FinishLearnResource(String teano,String subno);
	
	 public List<TraRes> findAll();
	
	 public long PassTraCount(String teano, String subno);
}
