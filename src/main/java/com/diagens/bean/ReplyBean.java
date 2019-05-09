package com.diagens.bean;

import java.util.Objects;

/**
 * @author ZNJ
 * @create 2018-12-27 10:47
 */
public class ReplyBean {
   //题目回答Id
   private Integer replyId;
   //答卷的信息
   private AnswerBean answerBean=new AnswerBean();
   //题目的信息
   private SurveyQuestionBean sqBean=new SurveyQuestionBean();
   //题目回答选项的ID (单选：单个选项ID，多选：选项ID集，空格隔开)
   private String qroIds;
   //回答文本的内容
   private String replyContent;

    public ReplyBean() {
    }

    public ReplyBean(Integer replyId, AnswerBean answerBean, SurveyQuestionBean sqBean, String qroIds, String replyContent) {
        this.replyId = replyId;
        this.answerBean = answerBean;
        this.sqBean = sqBean;
        this.qroIds = qroIds;
        this.replyContent = replyContent;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public AnswerBean getAnswerBean() {
        return answerBean;
    }

    public void setAnswerBean(AnswerBean answerBean) {
        this.answerBean = answerBean;
    }

    public SurveyQuestionBean getSqBean() {
        return sqBean;
    }

    public void setSqBean(SurveyQuestionBean sqBean) {
        this.sqBean = sqBean;
    }

    public String getQroIds() {
        return qroIds;
    }

    public void setQroIds(String qroIds) {
        this.qroIds = qroIds;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    @Override
    public String toString() {
        return "ReplyBean{" +
                "replyId=" + replyId +
                ", answerBean=" + answerBean +
                ", sqBean=" + sqBean +
                ", qroIds='" + qroIds + '\'' +
                ", replyContent='" + replyContent + '\'' +
                '}';
    }
}
