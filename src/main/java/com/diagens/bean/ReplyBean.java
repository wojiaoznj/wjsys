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
   private String qroId;
   //回答文本的内容
   private String replyContent;

    public ReplyBean() {
    }

    public ReplyBean(Integer replyId, AnswerBean answerBean, SurveyQuestionBean sqBean, String qroId, String replyContent) {
        this.replyId = replyId;
        this.answerBean = answerBean;
        this.sqBean = sqBean;
        this.qroId = qroId;
        this.replyContent = replyContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyBean replyBean = (ReplyBean) o;
        return Objects.equals(replyId, replyBean.replyId) &&
                Objects.equals(answerBean, replyBean.answerBean) &&
                Objects.equals(sqBean, replyBean.sqBean) &&
                Objects.equals(qroId, replyBean.qroId) &&
                Objects.equals(replyContent, replyBean.replyContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(replyId, answerBean, sqBean, qroId, replyContent);
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

    public String getQroId() {
        return qroId;
    }

    public void setQroId(String qroId) {
        this.qroId = qroId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
