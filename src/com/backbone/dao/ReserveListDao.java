package com.backbone.dao;

import java.util.List;

import com.backbone.entity.po.ExamSubject;
import com.backbone.entity.po.ReserveList;
import com.backbone.entity.po.Teacher;

public interface ReserveListDao {
   public List<ReserveList>  findAll(String subno);
   
   public ReserveList findByProperty(String subno,String teano);
     
   public ReserveList findUnique(String subno,String teano);
   
   public long SaveReserveList(ExamSubject subject,Teacher teacher);
   
   public long RemoveList();
   
   public ReserveList getReserveList(String subno);
   
   public List<ReserveList> getReserveAll();
}
