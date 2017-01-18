package com.backbone.dao.implement;


import java.util.List;

import com.backbone.database.QueryHelper;
import com.backbone.entity.po.Teacher;
import com.backbone.entity.vo.AgreeTeacherInfo;

public class AgreeTeacherInfoImple {
	public List<AgreeTeacherInfo> getSome(String sql, Object[] params,
			Integer page, Integer count, boolean teacherAllInfo) {
		StringBuffer strBuffer = new StringBuffer();
		
		// 考试资源为空的情况下
		strBuffer.append("SELECT COUNT(*) FROM examoftra WHERE Sub_No=? ");
		Long sum = QueryHelper.queryUnique(Long.class,
				strBuffer.toString(), params[0]);
		
		sql = sql.replace("teano", "t.teano")
				.replace("teaname", "t.teaname");
				
		
		if (sum > 0) {
			strBuffer = new StringBuffer();
			//链式操作
			sql  = sql.replace("sub_no", "t.sub_no")
					.replace("subordunit", "t.subordunit")
					.replace("signstate", "t.signstate")
					.replace("schoolstate", "t.schoolstate")
					.replace("exampass", "t.exampass")
					.replace("isstudy", "t.isstudy");
			strBuffer.append("SELECT * FROM (SELECT ");
			// 是否获取教师全部信息
			if (teacherAllInfo) {
				strBuffer.append("   t.*, ");
			} else {
				strBuffer.append("     t.TeaName,");
				strBuffer.append("     t.TeaNo,");
				strBuffer.append("     t.SubordUnit,");
			}
			strBuffer.append("   study.Sub_No,");
			strBuffer.append("   study.signstate,");
			strBuffer.append("    study.schoolstate,");
			strBuffer.append("    study.exampass,");
			strBuffer.append("    study.exampassTime,");
			strBuffer.append("    study.studypassTime,");
			strBuffer.append("    (CASE");
			strBuffer
					.append("         WHEN study.isstudy<>study.sumstudy THEN 0");
			strBuffer.append("         ELSE 1");
			strBuffer.append("      END) AS isstudy");
			strBuffer.append(" FROM `teacher` AS t,");
			strBuffer.append(" (SELECT tt.TeaNo,");
			strBuffer.append("        te.signstate,");
			strBuffer.append("      te.schoolstate,");
			strBuffer.append("      te.exampass,");
			strBuffer.append("      te.exampassTime,");
			strBuffer.append("      te.studypassTime,");
			strBuffer.append("       et.Sub_No,");
			strBuffer.append("       SUM(tt.Watch_Time) AS isstudy,");
			strBuffer.append("        COUNT(*) AS sumstudy");
			strBuffer.append("  FROM `teaoftra` tt,");
			strBuffer.append("     `examoftra` et ,");
			strBuffer.append("    `trares` t,");
			strBuffer.append("    `teaofexam` te");
			strBuffer.append(" WHERE et.Tri_No=tt.Tri_No");
			strBuffer.append("  AND et.Sub_No=te.Sub_No");
			strBuffer.append("   AND te.TeaNo=tt.TeaNo");
			strBuffer.append("   AND et.Tri_No=t.Tri_No");
			strBuffer.append("   AND t.Tri_No=tt.Tri_No");
			strBuffer.append("    AND t.Tri_MustStudy=1");
			strBuffer.append("   GROUP BY tt.TeaNo,");
			strBuffer.append("            et.Sub_No) study");
			strBuffer.append(" WHERE t.TeaNo=study.TeaNo) AS t ");
			strBuffer.append(sql);
			
			return QueryHelper.queryPage(AgreeTeacherInfo.class,
					strBuffer.toString(), page, count, params);
		} else {
			strBuffer = new StringBuffer();
			sql = sql.replace("t.sub_no", "te.sub_no")
					.replace("subordunit", "t.subordunit")
					.replace("signstate", "te.signstate")
					.replace("schoolstate", "te.schoolstate")
					.replace("exampass", "te.exampass");
			strBuffer.append("SELECT	 ");
			// 是否获取教师全部信息
			if (teacherAllInfo) {
				strBuffer.append("   t.*, ");
			} else {
				strBuffer.append("     t.TeaName,");
				strBuffer.append("     t.TeaNo,");
				strBuffer.append("     t.SubordUnit,");
			}
			strBuffer.append("te.Sub_No,");
			strBuffer.append("te.signstate,");
			strBuffer.append("te.schoolstate,");
			strBuffer.append("te.exampass ,");
			strBuffer.append("te.exampassTime,");
			strBuffer.append("te.studypassTime,");
			strBuffer.append("isStudy");
			strBuffer.append(" FROM teacher t,teaofexam te,(SELECT 0  AS isStudy) AS temp ");
			strBuffer.append(sql);
			strBuffer.append(" AND te.`TeaNo`=t.`TeaNo` ");
			
			return QueryHelper.queryPage(AgreeTeacherInfo.class,
					strBuffer.toString(), page, count, params);
		}

		// end

	}

	public Long getSomeCount(String sql, Object[] params) {
		StringBuffer strBuffer = new StringBuffer();
		// 考试资源为空的情况下
		strBuffer.append("SELECT COUNT(*) FROM examoftra WHERE Sub_No=? ");
		Long sum = QueryHelper.queryUnique(Long.class,
				strBuffer.toString(), params[0]);

		sql = sql.replace("teano", "t.teano")
				.replace("teaname", "t.teaname");
		
		if (sum > 0) {
			strBuffer = new StringBuffer();
			sql = sql.replace("sub_no", "t.sub_no")
					.replace("subordunit", "t.subordunit")
					.replace("signstate", "t.signstate")
					.replace("schoolstate", "t.schoolstate")
					.replace("exampass", "t.exampass")
					.replace("isstudy", "t.isstudy");
			strBuffer.append("SELECT COUNT(*) FROM (SELECT t.TeaName,");
			strBuffer.append("     t.TeaNo,");
			strBuffer.append("     t.SubordUnit,");
			strBuffer.append("   study.Sub_No,");
			strBuffer.append("   study.signstate,");
			strBuffer.append("    study.schoolstate,");
			strBuffer.append("    study.exampass,");
			strBuffer.append("    (CASE");
			strBuffer
					.append("         WHEN study.isstudy<>study.sumstudy THEN 0");
			strBuffer.append("         ELSE 1");
			strBuffer.append("      END) AS isstudy");
			strBuffer.append(" FROM `teacher` AS t,");
			strBuffer.append(" (SELECT tt.TeaNo,");
			strBuffer.append("        te.signstate,");
			strBuffer.append("      te.schoolstate,");
			strBuffer.append("      te.exampass,");
			strBuffer.append("       et.Sub_No,");
			strBuffer.append("       SUM(tt.Watch_Time) AS isstudy,");
			strBuffer.append("        COUNT(*) AS sumstudy");
			strBuffer.append("  FROM `teaoftra` tt,");
			strBuffer.append("     `examoftra` et ,");
			strBuffer.append("    `trares` t,");
			strBuffer.append("    `teaofexam` te");
			strBuffer.append(" WHERE et.Tri_No=tt.Tri_No");
			strBuffer.append("  AND et.Sub_No=te.Sub_No");
			strBuffer.append("   AND te.TeaNo=tt.TeaNo");
			strBuffer.append("   AND et.Tri_No=t.Tri_No");
			strBuffer.append("   AND t.Tri_No=tt.Tri_No");
			strBuffer.append("    AND t.Tri_MustStudy=1");
			strBuffer.append("   GROUP BY tt.TeaNo,");
			strBuffer.append("            et.Sub_No) study");
			strBuffer.append(" WHERE t.TeaNo=study.TeaNo) AS t ");
			strBuffer.append(sql);
			return QueryHelper.queryUnique(Long.class, strBuffer.toString(),
					params);
		} else {
			strBuffer = new StringBuffer();
			strBuffer.append("SELECT COUNT(*)	 ");
			strBuffer.append(" FROM teacher t,teaofexam te,(SELECT 0 AS isStudy) AS temp ");
			sql = sql.replace("t.sub_no", "te.sub_no")
					.replace("subordunit", "t.subordunit")
					.replace("signstate", "te.signstate")
					.replace("schoolstate", "te.schoolstate")
					.replace("exampass", "te.exampass");
			strBuffer.append(sql);
			strBuffer.append(" AND te.`TeaNo`=t.`TeaNo` ");
			return QueryHelper.queryUnique(Long.class, strBuffer.toString(),
					params);
		}
	}

	public List<Teacher> getSomeTeacher(String sql, Object[] params,
			Integer page, Integer count) {
		String sqlStr = "SELECT * FROM teacher ";
		return QueryHelper.queryPage(Teacher.class, sqlStr + sql, page, count,
				params);
	}

	public Long getSomeTeacherCount(String sql, Object[] params) {
		String sqlStr = "SELECT COUNT(*) FROM teacher ";
		return QueryHelper.queryUnique(Long.class, sqlStr + sql, params);
	}
}
