package com.boss.db;

import cn.bmob.v3.BmobObject;

/**
 * Created by 滕新科 on 2017/4/23.
 */

public class Company extends BmobObject{

    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司标签
     */
    private String comanyFlag;
    /**
     * 公司图标
     */
    private String comanyAvtar;
    /**
     * 公司地点
     */
    private String comanyPoi;
    /**
     * 公司规模
     */
    private String companyMemberNum;
    /**
     * 公司展示轮播图片1
     */
    private String companyAvatar1;
    /**
     * 公司展示轮播图片2
     */
    private String companyAvatar2;
    /**
     * 公司展示轮播图片3
     */
    private String companyAvatar3;
    /**
     * 公司详细介绍
     */
    private String companyDetailInfo;
    /**
     * 公司BOSS名称
     */
    private String companyBossName;
    /**
     * 公司BOSS电话
     */
    private String companyBossPhone;
    /**
     * 公司BOSS头像
     */
    private String companyBossAvatar;
    /**
     * 公司Email地址邮箱
     */
    private String companyEmail;
    /**
     * 公司联系人职位
     */
    private String companyBossJob;


    private Boolean isFromSpider = false;


    /**
     * 审核状态
     */
    private String companyState;
    /**
     * 失败理由
     */
    private String companyFailReason;


    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyFailReason() {
        return companyFailReason;
    }

    public void setCompanyFailReason(String companyFailReason) {
        this.companyFailReason = companyFailReason;
    }

    public Boolean getFromSpider() {
        return isFromSpider;
    }

    public void setFromSpider(Boolean fromSpider) {
        isFromSpider = fromSpider;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyBossJob() {
        return companyBossJob;
    }

    public void setCompanyBossJob(String companyBossJob) {
        this.companyBossJob = companyBossJob;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getComanyFlag() {
        return comanyFlag;
    }

    public void setComanyFlag(String comanyFlag) {
        this.comanyFlag = comanyFlag;
    }

    public String getComanyAvtar() {
        return comanyAvtar;
    }

    public void setComanyAvtar(String comanyAvtar) {
        this.comanyAvtar = comanyAvtar;
    }

    public String getComanyPoi() {
        return comanyPoi;
    }

    public void setComanyPoi(String comanyPoi) {
        this.comanyPoi = comanyPoi;
    }

    public String getCompanyMemberNum() {
        return companyMemberNum;
    }

    public void setCompanyMemberNum(String companyMemberNum) {
        this.companyMemberNum = companyMemberNum;
    }

    public String getCompanyAvatar1() {
        return companyAvatar1;
    }

    public void setCompanyAvatar1(String companyAvatar1) {
        this.companyAvatar1 = companyAvatar1;
    }

    public String getCompanyAvatar2() {
        return companyAvatar2;
    }

    public void setCompanyAvatar2(String companyAvatar2) {
        this.companyAvatar2 = companyAvatar2;
    }

    public String getCompanyAvatar3() {
        return companyAvatar3;
    }

    public void setCompanyAvatar3(String companyAvatar3) {
        this.companyAvatar3 = companyAvatar3;
    }

    public String getCompanyDetailInfo() {
        return companyDetailInfo;
    }

    public void setCompanyDetailInfo(String companyDetailInfo) {
        this.companyDetailInfo = companyDetailInfo;
    }

    public String getCompanyBossName() {
        return companyBossName;
    }

    public void setCompanyBossName(String companyBossName) {
        this.companyBossName = companyBossName;
    }

    public String getCompanyBossPhone() {
        return companyBossPhone;
    }

    public void setCompanyBossPhone(String companyBossPhone) {
        this.companyBossPhone = companyBossPhone;
    }

    public String getCompanyBossAvatar() {
        return companyBossAvatar;
    }

    public void setCompanyBossAvatar(String companyBossAvatar) {
        this.companyBossAvatar = companyBossAvatar;
    }

    public Company(){
        super();
    }

    public Company(String companyName, String comanyFlag, String comanyAvtar, String comanyPoi, String companyMemberNum, String companyAvatar1, String companyAvatar2, String companyAvatar3, String companyDetailInfo, String companyBossName, String companyBossPhone, String companyBossAvatar) {
        super();
        this.companyName = companyName;
        this.comanyFlag = comanyFlag;
        this.comanyAvtar = comanyAvtar;
        this.comanyPoi = comanyPoi;
        this.companyMemberNum = companyMemberNum;
        this.companyAvatar1 = companyAvatar1;
        this.companyAvatar2 = companyAvatar2;
        this.companyAvatar3 = companyAvatar3;
        this.companyDetailInfo = companyDetailInfo;
        this.companyBossName = companyBossName;
        this.companyBossPhone = companyBossPhone;
        this.companyBossAvatar = companyBossAvatar;
    }

    public Company(String companyName, String comanyFlag, String comanyAvtar, String comanyPoi, String companyMemberNum, String companyAvatar1, String companyAvatar2, String companyAvatar3, String companyDetailInfo, String companyBossName, String companyBossPhone, String companyBossAvatar, String companyEmail, String companyBossJob, Boolean isFromSpider) {
        super();
        this.companyName = companyName;
        this.comanyFlag = comanyFlag;
        this.comanyAvtar = comanyAvtar;
        this.comanyPoi = comanyPoi;
        this.companyMemberNum = companyMemberNum;
        this.companyAvatar1 = companyAvatar1;
        this.companyAvatar2 = companyAvatar2;
        this.companyAvatar3 = companyAvatar3;
        this.companyDetailInfo = companyDetailInfo;
        this.companyBossName = companyBossName;
        this.companyBossPhone = companyBossPhone;
        this.companyBossAvatar = companyBossAvatar;
        this.companyEmail = companyEmail;
        this.companyBossJob = companyBossJob;
        this.isFromSpider = isFromSpider;
    }


/*    public PrepareCompany toPrepareCompany(){
        return new PrepareCompany( companyName,  comanyFlag,  comanyAvtar,  comanyPoi,  companyMemberNum,  companyAvatar1,  companyAvatar2,  companyAvatar3,  companyDetailInfo,  companyBossName,  companyBossPhone,  companyBossAvatar,  companyEmail,  companyBossJob,  isFromSpider, "checking", "");
    }*/
}
