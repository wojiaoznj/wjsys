package com.diagens.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZNJ
 * @create 2018-12-27 8:36
 */
public class SurveyBean {
    //问卷Id
    private Integer surveyId;
    //问卷类别
    private String surveyType;
    //问卷名称
    private String surveyName;
    //问卷说明
    private String surveyExplain;
    //创建时间
    private Date createTime;
    //问卷状态 0草稿 1已发布 2暂停发布 3废弃
    private Integer surveyStatus;
    //题目数量
    private Integer questionNum;
    //问卷题目信息
    private List<SurveyQuestionBean> questionBean=new ArrayList<>();
    //用户信息
    private UserInfoBean userInfo=new UserInfoBean();

    public SurveyBean() {
    }

    public SurveyBean(Integer surveyId, String surveyType, String surveyName, String surveyExplain, Date createTime, Integer surveyStatus, Integer questionNum, List<SurveyQuestionBean> questionBean, UserInfoBean userInfo) {
        this.surveyId = surveyId;
        this.surveyType = surveyType;
        this.surveyName = surveyName;
        this.surveyExplain = surveyExplain;
        this.createTime = createTime;
        this.surveyStatus = surveyStatus;
        this.questionNum = questionNum;
        this.questionBean = questionBean;
        this.userInfo = userInfo;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyExplain() {
        return surveyExplain;
    }

    public void setSurveyExplain(String surveyExplain) {
        this.surveyExplain = surveyExplain;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(Integer surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public List<SurveyQuestionBean> getQuestionBean() {
        return questionBean;
    }

    public void setQuestionBean(List<SurveyQuestionBean> questionBean) {
        this.questionBean = questionBean;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "SurveyBean{" +
                "surveyId=" + surveyId +
                ", surveyType='" + surveyType + '\'' +
                ", surveyName='" + surveyName + '\'' +
                ", surveyExplain='" + surveyExplain + '\'' +
                ", createTime=" + createTime +
                ", surveyStatus=" + surveyStatus +
                ", questionNum=" + questionNum +
                ", questionBean=" + questionBean +
                ", userInfo=" + userInfo +
                '}';
    }
}
