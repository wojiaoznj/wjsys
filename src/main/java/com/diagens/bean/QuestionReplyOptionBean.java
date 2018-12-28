package com.diagens.bean;

import java.util.Objects;

/**
 * @author ZNJ
 * @create 2018-12-27 11:03
 */
public class QuestionReplyOptionBean {
    //题目回答选项的Id
    private Integer qroId;
    //对应的题目信息
    private SurveyQuestionBean sqBean=new SurveyQuestionBean();
    //选项的内容
    private String qroContent;
    //选项当前所得的票数
    private Integer qroVotes;

    public QuestionReplyOptionBean() {
    }

    public QuestionReplyOptionBean(Integer qroId, SurveyQuestionBean sqBean, String qroContent, Integer qroVotes) {
        this.qroId = qroId;
        this.sqBean = sqBean;
        this.qroContent = qroContent;
        this.qroVotes = qroVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionReplyOptionBean that = (QuestionReplyOptionBean) o;
        return Objects.equals(qroId, that.qroId) &&
                Objects.equals(sqBean, that.sqBean) &&
                Objects.equals(qroContent, that.qroContent) &&
                Objects.equals(qroVotes, that.qroVotes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(qroId, sqBean, qroContent, qroVotes);
    }

    public Integer getQroId() {
        return qroId;
    }

    public void setQroId(Integer qroId) {
        this.qroId = qroId;
    }

    public SurveyQuestionBean getSqBean() {
        return sqBean;
    }

    public void setSqBean(SurveyQuestionBean sqBean) {
        this.sqBean = sqBean;
    }

    public String getQroContent() {
        return qroContent;
    }

    public void setQroContent(String qroContent) {
        this.qroContent = qroContent;
    }

    public Integer getQroVotes() {
        return qroVotes;
    }

    public void setQroVotes(Integer qroVotes) {
        this.qroVotes = qroVotes;
    }
}
