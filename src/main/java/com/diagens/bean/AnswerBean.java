package com.diagens.bean;

import java.util.Date;

/**
 * @author ZNJ
 * @create 2018-12-27 10:55
 */
public class AnswerBean {
    //答卷的Id
    private Integer answerId;
    //问卷信息
    private SurveyBean surveyBean=new SurveyBean();
    //用户信息
    private UserInfoBean uiBean=new UserInfoBean();
    //答题开始时间
    private Date answerStart;
    //答题结束时间
    private Date answerEnd;
    //答题用时
    private Date answerTime;

    public AnswerBean() {
    }

    public AnswerBean(Integer answerId, SurveyBean surveyBean, UserInfoBean uiBean, Date answerStart, Date answerEnd, Date answerTime) {
        this.answerId = answerId;
        this.surveyBean = surveyBean;
        this.uiBean = uiBean;
        this.answerStart = answerStart;
        this.answerEnd = answerEnd;
        this.answerTime = answerTime;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public SurveyBean getSurveyBean() {
        return surveyBean;
    }

    public void setSurveyBean(SurveyBean surveyBean) {
        this.surveyBean = surveyBean;
    }

    public UserInfoBean getUiBean() {
        return uiBean;
    }

    public void setUiBean(UserInfoBean uiBean) {
        this.uiBean = uiBean;
    }

    public Date getAnswerStart() {
        return answerStart;
    }

    public void setAnswerStart(Date answerStart) {
        this.answerStart = answerStart;
    }

    public Date getAnswerEnd() {
        return answerEnd;
    }

    public void setAnswerEnd(Date answerEnd) {
        this.answerEnd = answerEnd;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }
}
