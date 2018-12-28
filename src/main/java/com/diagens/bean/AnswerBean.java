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
    private UserInfoBean uiBean;
    //答题开始时间
    private Date answerStart;
    //答题结束时间
    private Date answerEnd;
    //答题用时
    private Date answerTime;
}
