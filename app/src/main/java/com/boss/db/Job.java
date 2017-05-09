package com.boss.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by 滕新科 on 2017/4/22.
 */

public class Job extends BmobObject{
    /**
     * 工作名称
     */
    private String jobName;
    /**
     * 工作所属公司
     */
    private String jobCompany;
    /**
     * 公司图标
     */
    private String jobCompanyAvatar;
    /**
     * 公司地点
     */
    private String jobCompanyPoi;
    /**
     * 职位要求经验
     */
    private String jobRequestExp;
    /**
     * 职位要求学历
     */
    private String jobRequestGraduate;
    /**
     * BOSS的名字
     */
    private String jobBossName;
    /**
     * Boss的电话号码
     */
    private String jobBossPhoneNumber;
    /**
     * 职位提供的薪资
     */
    private String jobOfferMoney;
    /**
     * 职位具体要求
     */
    private String jobRequestInfo;
    /**
     * 职位技能要求
     */
    private String jobRequestSkill;
    /**
     * 详细地址
     */
    private String jobCompanyPoiDe;
    /**
     * 技能标签
     */
    private String jobFlag;
    /**
     * 公司行业标签
     */
    private String jobCompanyFlag;
    /**
     * BOSS头像
     */
    private String jobBossAvtar;

    private Boolean isFromSpider = false;

    private String jobRequestum;

    /**
     * 审核状态 fail , checking
     */
    private String jobState;
    /**
     * 失败理由
     */
    private String jobFailReason;

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public String getJobFailReason() {
        return jobFailReason;
    }

    public void setJobFailReason(String jobFailReason) {
        this.jobFailReason = jobFailReason;
    }


    public String getJobRequestum() {
        return jobRequestum;
    }

    public void setJobRequestum(String jobRequestum) {
        this.jobRequestum = jobRequestum;
    }

    public Boolean getFromSpider() {
        return isFromSpider;
    }

    public void setFromSpider(Boolean fromSpider) {
        isFromSpider = fromSpider;
    }

    public String getJobBossAvtar() {
        return jobBossAvtar;
    }



    public void setJobBossAvtar(String jobBossAvtar) {
        this.jobBossAvtar = jobBossAvtar;
    }

    public String getJobCompanyFlag() {
        return jobCompanyFlag;
    }

    public void setJobCompanyFlag(String jobCompanyFlag) {
        this.jobCompanyFlag = jobCompanyFlag;
    }

    public String getJobCompanyPoiDe() {
        return jobCompanyPoiDe;
    }

    public void setJobCompanyPoiDe(String jobCompanyPoiDe) {
        this.jobCompanyPoiDe = jobCompanyPoiDe;
    }

    public String getJobFlag() {
        return jobFlag;
    }

    public void setJobFlag(String jobFlag) {
        this.jobFlag = jobFlag;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobCompanyAvatar() {
        return jobCompanyAvatar;
    }

    public void setJobCompanyAvatar(String jobCompanyAvatar) {
        this.jobCompanyAvatar = jobCompanyAvatar;
    }

    public String getJobCompanyPoi() {
        return jobCompanyPoi;
    }

    public void setJobCompanyPoi(String jobCompanyPoi) {
        this.jobCompanyPoi = jobCompanyPoi;
    }

    public String getJobRequestExp() {
        return jobRequestExp;
    }

    public void setJobRequestExp(String jobRequestExp) {
        this.jobRequestExp = jobRequestExp;
    }

    public String getJobRequestGraduate() {
        return jobRequestGraduate;
    }

    public void setJobRequestGraduate(String jobRequestGraduate) {
        this.jobRequestGraduate = jobRequestGraduate;
    }

    public String getJobBossName() {
        return jobBossName;
    }

    public void setJobBossName(String jobBossName) {
        this.jobBossName = jobBossName;
    }

    public String getJobBossPhoneNumber() {
        return jobBossPhoneNumber;
    }

    public void setJobBossPhoneNumber(String jobBossPhoneNumber) {
        this.jobBossPhoneNumber = jobBossPhoneNumber;
    }

    public String getJobOfferMoney() {
        return jobOfferMoney;
    }

    public void setJobOfferMoney(String jobOfferMoney) {
        this.jobOfferMoney = jobOfferMoney;
    }

    public String getJobRequestInfo() {
        return jobRequestInfo;
    }

    public void setJobRequestInfo(String jobRequestInfo) {
        this.jobRequestInfo = jobRequestInfo;
    }

    public String getJobRequestSkill() {
        return jobRequestSkill;
    }

    public void setJobRequestSkill(String jobRequestSkill) {
        this.jobRequestSkill = jobRequestSkill;
    }

    public Job(){

    }

    public Job( String jobName, String jobCompany, String jobCompanyAvatar, String jobCompanyPoi, String jobRequestExp, String getJobRequestGraduate, String jobBossName, String jobBossPhoneNumber, String jobOfferMoney, String jobRequestInfo, String jobRequestSkill) {
        this.jobName = jobName;
        this.jobCompany = jobCompany;
        this.jobCompanyAvatar = jobCompanyAvatar;
        this.jobCompanyPoi = jobCompanyPoi;
        this.jobRequestExp = jobRequestExp;
        this.jobRequestGraduate = getJobRequestGraduate;
        this.jobBossName = jobBossName;
        this.jobBossPhoneNumber = jobBossPhoneNumber;
        this.jobOfferMoney = jobOfferMoney;
        this.jobRequestInfo = jobRequestInfo;
        this.jobRequestSkill = jobRequestSkill;
    }

    public Job(String jobName, String jobCompany, String jobCompanyAvatar, String jobCompanyPoi, String jobRequestExp, String jobRequestGraduate, String jobBossName, String jobBossPhoneNumber, String jobOfferMoney, String jobRequestInfo, String jobRequestSkill, String jobCompanyPoiDe, String jobFlag, String jobCompanyFlag, String jobBossAvtar, Boolean isFromSpider, String jobRequestum) {
        super();
        this.jobName = jobName;
        this.jobCompany = jobCompany;
        this.jobCompanyAvatar = jobCompanyAvatar;
        this.jobCompanyPoi = jobCompanyPoi;
        this.jobRequestExp = jobRequestExp;
        this.jobRequestGraduate = jobRequestGraduate;
        this.jobBossName = jobBossName;
        this.jobBossPhoneNumber = jobBossPhoneNumber;
        this.jobOfferMoney = jobOfferMoney;
        this.jobRequestInfo = jobRequestInfo;
        this.jobRequestSkill = jobRequestSkill;
        this.jobCompanyPoiDe = jobCompanyPoiDe;
        this.jobFlag = jobFlag;
        this.jobCompanyFlag = jobCompanyFlag;
        this.jobBossAvtar = jobBossAvtar;
        this.isFromSpider = isFromSpider;
        this.jobRequestum = jobRequestum;
    }


   /* public PrepareJob toPrepareJob(){
        PrepareJob job = new PrepareJob( jobName,  jobCompany,  jobCompanyAvatar,  jobCompanyPoi,  jobRequestExp,  jobRequestGraduate,  jobBossName,  jobBossPhoneNumber,  jobOfferMoney,  jobRequestInfo,  jobRequestSkill,  jobCompanyPoiDe,  jobFlag,  jobCompanyFlag,  jobBossAvtar,  isFromSpider,  jobRequestum) ;
        job.setJobState("checking");
        job.setJobFailReason("");
        return job;
    }*/
}
