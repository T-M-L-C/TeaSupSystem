package com.backbone.service;


import java.util.List;

import com.backbone.dao.implement.AgreeTeacherInfoImple;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.AgreeTeacherInfo;
import com.backbone.entity.vo.DynamicSearchCondition;

public class AgreeTeacherInfoServices {
	AgreeTeacherInfoImple atii = new AgreeTeacherInfoImple();
	
	public List<AgreeTeacherInfo> getSome(DynamicSearchCondition dsc,Integer page,Integer count)
	{
		return atii.getSome(dsc.getConditionSql() + dsc.getOrderBy(), dsc.getLstObj().toArray(), page, count,false);
	}
	public Long getSomeCount(DynamicSearchCondition dsc)
	{
		return atii.getSomeCount(dsc.getConditionSql(), dsc.getLstObj().toArray());
	}
	
	public List<Teacher> getSomeTeacher(DynamicSearchCondition dsc,Integer page,Integer count)
	{
		return atii.getSomeTeacher(dsc.getConditionSql() + dsc.getOrderBy(), dsc.getLstObj().toArray(), page, count);
	}
	public Long getSomeTeacherCount(DynamicSearchCondition dsc)
	{
		return atii.getSomeTeacherCount(dsc.getConditionSql(), dsc.getLstObj().toArray());
	}
}
