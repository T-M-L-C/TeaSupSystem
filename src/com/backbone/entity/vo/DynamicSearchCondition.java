package com.backbone.entity.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.backbone.common.Common;

public class DynamicSearchCondition<E> {
	// 原对象，用来存贮查询条件
	private E baseEntity;
	private Map<String, Order> orderType = new HashMap<String, DynamicSearchCondition.Order>();
	private List<Object> params = new ArrayList<Object>();
	private List<String> condi = new ArrayList<String>();

	public void ini(E e, List<DynamicSearchConditionAssist> dscas) {
		this.baseEntity = e;
		for (DynamicSearchConditionAssist item : dscas) {
             
			if (item.getV() != null && item.getV().toString().length() > 0) {
				
				System.out.println(item.getPname());
				// 赋值
				Common.setObjectValue(baseEntity, item.getPname(), item.getV());
				if (item.getCompare() != null && item.getCompare().toString().length() > 0) {
					SetQueryCondition(item.getCompare(),item.getPname(),Common.getObjectValue(baseEntity, item.getPname()));
				} else {
					try {
						SetQueryCondition(Common.getFieldType(baseEntity, item.getPname()),item.getPname(),Common.getObjectValue(baseEntity, item.getPname()));
					} catch (Exception e1) {
						e1.printStackTrace();
						continue;
					}
				}
			}
			if (item.getSort() != null)
				// 获取排序规则
				orderType.put(item.getPname(), item.getSort());
		}

	}

	public List<Object> getLstObj() {
		return params;
	}

	public void setLstObj(List<Object> lstObj) {
		this.params = lstObj;
	}

	public static enum Order {
		DESC, ASC
	}

	public String getConditionSql() {

		StringBuffer sBuffer = new StringBuffer();
		if (condi.size() != 0) {
			sBuffer.append("  WHERE ");
		} else {
			return "";
		}

		for (String str : condi)
			sBuffer.append(str + " AND ");

		return sBuffer.substring(0, sBuffer.length() - 5);
	}

	public String getOrderBy() {
		StringBuffer orderBuffer = new StringBuffer();
		if (orderType.size() != 0) {
			orderBuffer.append("  ORDER BY ");
		} else {
			return "";
		}

		for (Entry<String, Order> item : orderType.entrySet()) {
			orderBuffer.append(item.getKey() + " " + item.getValue().toString() + ",");
		}
		return orderBuffer.substring(0, orderBuffer.length() - 1);

	}

	private void SetQueryCondition(Integer i, String rowName, Object val) {
		switch (i) {
		default:
			condi.add(rowName + " = ?");
			params.add(val);
			break;
		}
	}

	private void SetQueryCondition(Class type, String rowName, Object val) {
		if (type == String.class || type == String.class) {
			condi.add(rowName + " LIKE ?");
			params.add("%" + val + "%");
		} else {
			condi.add(rowName + " <= ?");
			params.add(val);
		}
	}

	public static class DynamicSearchConditionAssist {
		// property name
		private String pname;
		// value
		private Object v;
		// srot type
		private Order sort;
		// compare type
		private Integer compare;

		public Integer getCompare() {
			return compare;
		}

		public void setCompare(Integer compare) {
			this.compare = compare;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public Object getV() {
			return v;
		}

		public void setV(Object v) {
			this.v = v;
		}

		public Order getSort() {
			return sort;
		}

		public void setSort(Order sort) {
			this.sort = sort;
		}

	}

}
