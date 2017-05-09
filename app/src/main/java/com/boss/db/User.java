package com.boss.db;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

import com.boss.im.db.NewFriend;

import cn.bmob.v3.BmobUser;


public class User extends BmobUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean sex = false;
	private String nick;
	private Integer age;

	private String companyAllName;
	private String companyEasyName;
	private String CompanyBelong;
	private String companyPeople;
	private String companyMoneyType;
	private String companyLogo;
	private String companyWebsite;

	/** 求职状态 */
	private String workState;
	/** 接收简历邮箱 */
	private String companyEmail;
	/** 我的职位 */
	private String companyEmployee;
	/** 微信号 */
	private String weChatNumber;
	/** 团队亮点 */
	private String companyBest;
	/** 团队介绍 */
	private String companyProduce;

	/** 注册公司 true为注册了 */
	private Boolean registerCompany;

	/** 注册求职* true为注册了 */
	private Boolean registerEmployee;

	/** MainActivity界面为CompanyActivity还是EmployeeActivity true为公司界面，false为员工界面 */
	private Boolean mainLayout;

	public Boolean getAdMin() {
		return isAdMin;
	}

	public void setAdMin(Boolean adMin) {
		isAdMin = adMin;
	}

	/**
	 * 是否是管理员
	 */
	private Boolean isAdMin = false;

	/** 是否使用默认头像 null 或者false则不是系统默认头像，true为系统默认头像 */
	private Boolean defaultpic;

	/** 用户头像 */
	private String avator;

	/** 判断用户是否使用本地头像 true 为是，空或者false为否 */
	private Boolean normalavator;
	/** 判断用户使用的是本地哪个头像 */
	private Integer normalavatorindex;
	/** 参加工作的年份 */
	private String employeeWorkyear;

	/** 用户学校名称 */
	private String schoolName;
	/** 用户学历 */
	private String education;
	/** 在校经历 */
	private String eduJingli;

	/** 用户读书的开始时间 */
	private String eduBeginTime;
	/** 用户读书的结束时间 */
	private String eduDoneTime;
	/** 用户学校的专业 */
	private String major;
	/** 用户学校的学院 */
	private String schoolSchoolName;
	/** 用户自我介绍 */
	private String introduce;
	/** 工作经验的作者是谁 */
	private User user;

	/** 项目名称 **/
	private String companyName;

	/** 工作起始时间 */
	private String beginTime;
	/** 工作结束时间 */
	private String doneTime;
	/** 职业类型 */
	private String workType;
	/** 职业技能 */
	private String skill;
	/** 工作内容 */
	private String content;
	/** 职业名称 */
	private String zhiyemingcheng;

	/** 公司行业 */
	private String mCompanyHangye;
	/** 所属部门 */
	private String mSuoshubumen;
	/** 工作内容 */
	private String mGongzuoneirong;
	/** 工作业绩 */
	private String mGongzuoyeji;

	// ***********************************************求职意向开始************************************************************

	/** 期望职位 */
	private String zhiwei;
	/** 期望行业 */
	private String hangye;
	/** 期望城市 */
	private String city;
	/** 期望薪资begin */
	private String xinziBegin;
	/** 期望薪资done */
	private String xinziDone;

	public String getSchoolSchoolName() {
		return schoolSchoolName;
	}

	public void setSchoolSchoolName(String schoolSchoolName) {
		this.schoolSchoolName = schoolSchoolName;
	}

	/** 期望行业 */
	public String getHangye() {
		return hangye;
	}

	/** 期望行业 */
	public void setHangye(String hangye) {
		this.hangye = hangye;
	}

	/** 期望城市 */
	public String getCity() {
		return city;
	}

	/** 期望城市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 期望薪资begin */
	public String getXinziBegin() {
		return xinziBegin;
	}

	/** 期望薪资begin */
	public void setXinziBegin(String xinziBegin) {
		this.xinziBegin = xinziBegin;
	}

	/** 期望薪资done */
	public String getXinziDone() {
		return xinziDone;
	}

	/** 期望薪资done */
	public void setXinziDone(String xinziDone) {
		this.xinziDone = xinziDone;
	}

	/** 期望职位 */
	public String getZhiwei() {
		return zhiwei;
	}

	/** 期望职位 */
	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}

	// **************************************************求职意向结束*******************************************************

	public String getmCompanyHangye() {
		return mCompanyHangye;
	}

	public void setmCompanyHangye(String mCompanyHangye) {
		this.mCompanyHangye = mCompanyHangye;
	}

	public String getmSuoshubumen() {
		return mSuoshubumen;
	}

	public void setmSuoshubumen(String mSuoshubumen) {
		this.mSuoshubumen = mSuoshubumen;
	}

	public String getmGongzuoneirong() {
		return mGongzuoneirong;
	}

	public void setmGongzuoneirong(String mGongzuoneirong) {
		this.mGongzuoneirong = mGongzuoneirong;
	}

	public String getmGongzuoyeji() {
		return mGongzuoyeji;
	}

	public void setmGongzuoyeji(String mGongzuoyeji) {
		this.mGongzuoyeji = mGongzuoyeji;
	}

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
	 * @return 项目名称
 	 */


	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return 项目名称
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
	 * @param workType
	 *            职业类型
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
	 * @param skill
	 *            职业技能
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
	 * @param content
	 *            工作内容
	 */

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return 公司接收简历邮箱
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @param companyEmail
	 *            公司接收简历邮箱
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @return 我的公司职位
	 */
	public String getCompanyEmployee() {
		return companyEmployee;
	}

	/**
	 * @param companyEmployee
	 *            我的公司职位
	 */
	public void setCompanyEmployee(String companyEmployee) {
		this.companyEmployee = companyEmployee;
	}

	/**
	 * @return 微信号
	 */
	public String getWeChatNumber() {
		return weChatNumber;
	}

	/**
	 * @param weChatNumber
	 *            微信号
	 */
	public void setWeChatNumber(String weChatNumber) {
		this.weChatNumber = weChatNumber;
	}

	/**
	 * @return 团队亮点
	 */
	public String getCompanyBest() {
		return companyBest;
	}

	/**
	 * @param companyBest
	 *            团队亮点
	 */
	public void setCompanyBest(String companyBest) {
		this.companyBest = companyBest;
	}

	/**
	 * @return 团队介绍
	 */
	public String getCompanyProduce() {
		return companyProduce;
	}

	/**
	 * @param companyProduce
	 *            团队介绍
	 */
	public void setCompanyProduce(String companyProduce) {
		this.companyProduce = companyProduce;
	}

	/**
	 * @return 用户性别 true为男，false为女
	 */
	public boolean getSex() {
		return this.sex;
	}

	/**
	 * @param sex
	 *            用户性别 true为男，false为女
	 */
	public void setSex(boolean sex) {
		this.sex = sex;
	}

	/**
	 * @return 用户昵称
	 */
	public String getNick() {
		return this.nick;
	}

	/**
	 * @param nick
	 *            用户昵称
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return 用户年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            用户年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return 公司全称
	 */
	public String getCompanyAllName() {
		return companyAllName;
	}

	/**
	 * 公司全称
	 */
	public void setCompanyAllName(String companyAllName) {
		this.companyAllName = companyAllName;
	}

	/**
	 * @return 公司简称
	 */
	public String getCompanyEasyName() {
		return companyEasyName;
	}

	/**
	 * @param companyEasyName
	 *            公司简称
	 */
	public void setCompanyEasyName(String companyEasyName) {
		this.companyEasyName = companyEasyName;
	}

	/**
	 * @return 所属行业
	 */
	public String getCompanyBelong() {
		return CompanyBelong;
	}

	/**
	 * @param companyBelong
	 *            所属行业
	 */
	public void setCompanyBelong(String companyBelong) {
		CompanyBelong = companyBelong;
	}

	/**
	 * @return 公司规模
	 */
	public String getCompanyPeople() {
		return companyPeople;
	}

	/**
	 * @param companyPeople
	 *            公司规模
	 */
	public void setCompanyPeople(String companyPeople) {
		this.companyPeople = companyPeople;
	}

	/**
	 * @return 融资阶段
	 */
	public String getCompanyMoneyType() {
		return companyMoneyType;
	}

	/**
	 * @param companyMoneyType
	 *            融资阶段
	 */
	public void setCompanyMoneyType(String companyMoneyType) {
		this.companyMoneyType = companyMoneyType;
	}

	/**
	 * @return 企业logo
	 */
	public String getCompanyLogo() {
		return companyLogo;
	}

	/**
	 * @param companyLogo
	 *            企业logo
	 */
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	/**
	 * @return 企业网址
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}

	/**
	 * @param companyWebsite
	 *            企业网址
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	/**
	 * @return 是否注册公司 true为注册了，空为没注册
	 */
	public Boolean getRegisterCompany() {
		return registerCompany;
	}

	/**
	 * @param registerCompany
	 *            是否注册公司 true为注册了，空为没注册
	 */
	public void setRegisterCompany(Boolean registerCompany) {
		this.registerCompany = registerCompany;
	}

	/**
	 * @return 是否注册员工 true为注册了，空为没注册
	 */
	public Boolean getRegisterEmployee() {
		return registerEmployee;
	}

	/**
	 * @param registerEmployee
	 *            是否注册员工 true为注册了，空为没注册
	 */
	public void setRegisterEmployee(Boolean registerEmployee) {
		this.registerEmployee = registerEmployee;
	}

	/**
	 * @return 判断当前界面为什么，null为没注册，true为公司界面 false为员工界面
	 */
	public Boolean getMainLayout() {
		return mainLayout;
	}

	/**
	 * @param mainLayout
	 *            判断当前界面为什么，null为没注册，true为公司界面 false为员工界面
	 */

	public void setMainLayout(Boolean mainLayout) {
		this.mainLayout = mainLayout;
	}

	/**
	 * @return 是否使用默认头像 null 或者false则不是系统默认头像，true为系统默认头像
	 */
	public Boolean getDefaultpic() {
		return defaultpic;
	}

	/**
	 * @param defaultpic
	 *            是否使用默认头像 null 或者false则不是系统默认头像，true为系统默认头像
	 */
	public void setDefaultpic(Boolean defaultpic) {
		this.defaultpic = defaultpic;
	}

	/**
	 * @return 用户头像地址
	 */
	public String getAvator() {
		return avator;
	}

	/**
	 * @param avator
	 *            用户头像地址
	 */
	public void setAvator(String avator) {
		this.avator = avator;
	}

	/**
	 * @return 判断用户是否使用本地头像 true 为是，空或者false为否
	 */
	public Boolean getNormalavator() {
		return normalavator;
	}

	/**
	 * @param normalavator
	 *            判断用户是否使用本地头像 true 为是，空或者false为否
	 */
	public void setNormalavator(Boolean normalavator) {
		this.normalavator = normalavator;
	}

	/**
	 * @return 判断用户使用的是本地哪个头像
	 */
	public Integer getNormalavatorindex() {
		return normalavatorindex;
	}

	/**
	 * @param normalavatorindex
	 *            判断用户使用的是本地哪个头像
	 */
	public void setNormalavatorindex(Integer normalavatorindex) {
		this.normalavatorindex = normalavatorindex;
	}

	/**
	 * @return 参加工作的年份
	 */
	public String getEmployeeWorkyear() {
		return employeeWorkyear;
	}

	/**
	 * @param employeeWorkyear
	 *            参加工作的年份
	 */
	public void setEmployeeWorkyear(String employeeWorkyear) {
		this.employeeWorkyear = employeeWorkyear;
	}

	/**
	 * @return 用户学校名称
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName
	 *            用户学校名称
	 */

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return 用户读书的开始时间
	 */
	public String getEduBeginTime() {
		return eduBeginTime;
	}

	/**
	 * @param eduBeginTime
	 *            用户读书的开始时间
	 */
	public void setEduBeginTime(String eduBeginTime) {
		this.eduBeginTime = eduBeginTime;
	}

	/**
	 * @return 用户学历
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education
	 *            用户学历
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return 用户读书的结束时间
	 */
	public String getEduDoneTime() {
		return eduDoneTime;
	}

	/**
	 * @param eduDoneTime
	 *            用户读书的结束时间
	 */
	public void setEduDoneTime(String eduDoneTime) {
		this.eduDoneTime = eduDoneTime;
	}

	/**
	 * @return 用户学校的专业
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major
	 *            用户学校的专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return 用户自我介绍
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce
	 *            用户自我介绍
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/** 求职状态 */
	public String getWorkState() {
		return workState;
	}

	/** 求职状态 */
	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getZhiyemingcheng() {
		return zhiyemingcheng;
	}

	public void setZhiyemingcheng(String zhiyemingcheng) {
		this.zhiyemingcheng = zhiyemingcheng;
	}

	public String getEduJingli() {
		return eduJingli;
	}

	public void setEduJingli(String eduJingli) {
		this.eduJingli = eduJingli;
	}

	public User(NewFriend friend){
		setObjectId(friend.getUid());
		setUsername(friend.getName());
		setAvator(friend.getAvatar());
	}

	public User(){}

}