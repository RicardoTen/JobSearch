package com.boss.db;

import cn.bmob.v3.BmobObject;

/**
 * 工作历史
 */
public class HistoryWorkExp extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 工作经验的作者是谁 */
	private User user;

	/** 公司名称 */
	private String companyName;

	/** 工作起始时间 */
	private String beginTime;
	/** 工作结束时间 */
	private String doneTime;
	/**职业类型	 */
	private String workType;
	/** 职业技能*/
	private String skill;
	/**工作内容*/
	private String content;

	
	/**
	 * @return 工作经验的作者是谁
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            工作经验的作者是谁
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return 工作起始时间
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime
	 *            工作起始时间
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return 工作结束时间
	 */
	public String getDoneTime() {
		return doneTime;
	}

	/**
	 * @param doneTime
	 *            工作结束时间
	 */
	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}

	/**
	 * @return 职业类型
	 */
	public String getWorkType() {
		return workType;
	}
	/**
	 * @param workType 职业类型
	 */

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	/**
	 * @return 职业技能
	 */
	public String getSkill() {
		return skill;
	}

	/**
	 * @param skill 职业技能
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}

	/**
	 * @return 工作内容
	 */
	public String getContent() {
		return content;
	}
	/**
 	 * @param content 工作内容
	 */

	public void setContent(String content) {
		this.content = content;
	}

}
