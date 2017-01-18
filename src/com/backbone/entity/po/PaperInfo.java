package com.backbone.entity.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 试卷
 *
 */
public class PaperInfo {
	//科目名称
	private String Subno;
	//题目
	private String quescontent;
	//题号
	private String quesid;
	//正确选项
	private String rightanswer;
	//题目编号
	private Integer QuesNo;
	List<Answer> answers = new ArrayList<PaperInfo.Answer>();


	public Integer getQuesNo() {
		return QuesNo;
	}

	public void setQuesNo(Integer quesNo) {
		QuesNo = quesNo;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getSubno() {
		return Subno;
	}

	public void setSubno(String subno) {
		Subno = subno;
	}

	public String getQuescontent() {
		return quescontent;
	}



	public void setQuescontent(String quescontent) {
		this.quescontent = quescontent;
	}






	public String getQuesid() {
		return quesid;
	}






	public void setQuesid(String quesid) {
		this.quesid = quesid;
	}






	public String getRightanswer() {
		return rightanswer;
	}






	public void setRightanswer(String rightanswer) {
		this.rightanswer = rightanswer;
	}






	public static class Answer{
		private String Answer;
		private	Integer IsImage;
		private String QuesId;
		private Integer AnswerItem;

		public String getAnswer() {
			return Answer;
		}
		public void setAnswer(String answer) {
			Answer = answer;
		}
		public Integer getIsImage() {
			return IsImage;
		}
		public void setIsImage(Integer isImage) {
			IsImage = isImage;
		}
		public String getQuesId() {
			return QuesId;
		}
		public void setQuesId(String quesId) {
			QuesId = quesId;
		}
		public Integer getAnswerItem() {
			return AnswerItem;
		}
		public void setAnswerItem(Integer answerItem) {
			AnswerItem = answerItem;
		}


	}
}

