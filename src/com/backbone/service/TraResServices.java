package com.backbone.service;

import java.util.ArrayList;
import java.util.List;

import com.backbone.dao.TraResDao;
import com.backbone.dao.implement.ExamOfTraImple;
import com.backbone.dao.implement.TeaOfTraImple;
import com.backbone.dao.implement.TraResImple;
import com.backbone.entity.po.TraRes;

public class TraResServices {
	private static TraResDao traResDao = new TraResImple();

	/**
     * 
     */
	public Integer Register(TraRes docu) {
		return traResDao.Save(docu);
	}

	public List<TraRes> getAll(String paramter, String subno,Integer page,Integer count) {
		return traResDao.getAllObj(paramter, subno,page,count);
	}

	public Long getAllCount() {
		return ((TraResImple)traResDao).getAllCount();
	}

	public Integer studying(String teacherid, Integer resid, Integer process) {
		return new TeaOfTraImple().update(resid, teacherid, process);
	}

	public Long getCounts(String teacherid,String subno) {
		return traResDao.getcounts(teacherid,subno);
	}

	public List<TraRes> getsome(Integer count, Integer number, String teacherid, String parameter,
			String subno) {
		// List<TraRes> wtts = new ArrayList<TraRes>();
		// if(teacherid != null)
		// {
		// for(WatchTimeTemp item:traResDao.getSomeAndWatchTime(count, number,
		// teacherid))
		// {
		// wtts.add(item);
		// }
		// }
		// else
		// {
		// return traResDao.getpage(count, number);
		// }
		// return wtts;
		return traResDao.getAllObj(parameter, subno,count,number);
	}

	public Integer update(TraRes traRes) {
		 return traResDao.update(traRes);
	}

	public int updateCounts(Integer id, Integer counts) {
		return traResDao.updatePageCounts(id, counts);
	}

	public Integer getId(String sPath) {
		return traResDao.getIdByPath(sPath);
	}

	public List<String> getpath(Integer id) {
		TraRes tr = traResDao.getObj(id);
		List<String> strs = new ArrayList<String>();
		strs.add(tr.getTri_type());
		strs.add(tr.getTri_newpath());
		strs.add(tr.getTri_path());
		return strs;

	}

	public void del(Integer id) {
		traResDao.DeleteObj(id);
	}

	public List<Integer> addTeacherRes(String teacherid, String examid) {
		List<Integer> resids = new ExamOfTraImple().getResouceByExamid(examid);
	    return resids;
	}

	public TraRes getRes(String subno, String tri_time, String tri_type) {
		return traResDao.getRes(subno, tri_time, tri_type);
	}

	/**
	 * 教师查看自己是否已完成培训学习
	 */
	public List<TraRes> IsFinishResource(String teano, String subno) {
		return traResDao.FinishLearnResource(teano, subno);
	}
    
	/**
	 * 查看已通过学习的科目有哪些
	 */
	public long HasFinishTra(String teano,String subno){
		return traResDao.PassTraCount(teano,subno);
	}

}
