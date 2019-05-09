package com.diagens.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String answerPerson;
    //答题用时
    private String answerTime;
    //答题回复信息
    private List<ReplyBean> replyBeans=new ArrayList<>();

    public AnswerBean() {
    }

    public AnswerBean(Integer answerId, SurveyBean surveyBean, UserInfoBean uiBean, Date answerStart, String answerPerson, String answerTime, List<ReplyBean> replyBeans) {
        this.answerId = answerId;
        this.surveyBean = surveyBean;
        this.uiBean = uiBean;
        this.answerStart = answerStart;
        this.answerPerson = answerPerson;
        this.answerTime = answerTime;
        this.replyBeans = replyBeans;
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

    public String getAnswerPerson() {
        return answerPerson;
    }

    public void setAnswerPerson(String answerPerson) {
        this.answerPerson = answerPerson;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public List<ReplyBean> getReplyBeans() {
        return replyBeans;
    }

    public void setReplyBeans(List<ReplyBean> replyBeans) {
        this.replyBeans = replyBeans;
    }

    @Override
    public String toString() {
        return "AnswerBean{" +
                "answerId=" + answerId +
                ", surveyBean=" + surveyBean +
                ", uiBean=" + uiBean +
                ", answerStart=" + answerStart +
                ", answerPerson='" + answerPerson + '\'' +
                ", answerTime='" + answerTime + '\'' +
                ", replyBeans=" + replyBeans +
                '}';
    }
}
