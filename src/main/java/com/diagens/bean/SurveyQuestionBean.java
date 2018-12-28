package com.diagens.bean;

import java.util.Objects;

/**
 * @author ZNJ
 * @create 2018-12-27 10:29
 */
public class SurveyQuestionBean {
    //题目Id
    private Integer questionId;
    //题目类型
    private Integer questionType;
    //题目标题
    private String questionTitle;
    //题目说明
    private String questionExplain;
    //是否必填
    private Integer isRequired;
    //题目排序码
    private Integer questionSort;
    //是否能修改此题目，已发布过的不能修改 0 能修改 1 不能修改
    private Integer isEdit;

    public SurveyQuestionBean() {
    }

    public SurveyQuestionBean(Integer questionId, Integer questionType, String questionTitle, String questionExplain, Integer isRequired, Integer questionSort, Integer isEdit) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.questionExplain = questionExplain;
        this.isRequired = isRequired;
        this.questionSort = questionSort;
        this.isEdit = isEdit;
    }

    @Override
    public String toString() {
        return "SurveyQuestionBean{" +
                "questionId=" + questionId +
                ", questionType=" + questionType +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionExplain='" + questionExplain + '\'' +
                ", isRequired=" + isRequired +
                ", questionSort=" + questionSort +
                ", isEdit=" + isEdit +
                '}';
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionExplain() {
        return questionExplain;
    }

    public void setQuestionExplain(String questionExplain) {
        this.questionExplain = questionExplain;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getQuestionSort() {
        return questionSort;
    }

    public void setQuestionSort(Integer questionSort) {
        this.questionSort = questionSort;
    }

    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }
}
