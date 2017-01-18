package com.backbone.service;

import java.util.List;

import com.backbone.dao.ReserveListDao;
import com.backbone.dao.implement.ReserveListDaoImple;
import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.ReserveList;
import com.backbone.entity.po.Teacher;

public class ReserveListService {
    private ReserveListDao reserveListDao=new ReserveListDaoImple();
    
    public List<ReserveList> findLists(String subno){
    	return reserveListDao.findAll(subno);
    }
    

    public ReserveList findInforById(String teano,String subno){
    	return reserveListDao.findByProperty(subno, teano);
    }
    
    public long SaveReserveObj(ExamSubject subject,Teacher teacher){
    	return reserveListDao.SaveReserveList(subject, teacher);
    }
    public ReserveList querySubno(String subno){
    	return reserveListDao.getReserveList(subno);
    }
    
    public List<ReserveList>  findAll(){
    	return reserveListDao.getReserveAll();
    }
    public long truncateList(){
    	return reserveListDao.RemoveList();
    }
}
