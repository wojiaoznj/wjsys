package com.diagens.service;

import com.diagens.bean.QuestionReplyOptionBean;
import com.diagens.bean.SurveyBean;
import com.diagens.bean.SurveyQuestionBean;
import com.diagens.dto.SurveyResult;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-27 8:38
 */
public interface SurveyService {
    SurveyResult<String> insertSurvey(SurveyBean surveyBean);

    PageInfo getSurvey(Integer userid, Integer pageNum);

    SurveyResult<String> deleteSurvey(Integer surveyId, Integer userid);

    SurveyResult updateSurveyStatus(Integer userid, Integer surveyId, Integer surveyStatus, Integer status);

    SurveyResult<List<Map<Object,Object>>> getSurveyWithQuestion(Integer userid, Integer surveyId);

    SurveyResult<List<Map<Object,Object>>> getSurveyQuestionWithReply(Integer userid, Integer surveyId);

    int updateSurvey(SurveyBean surveyBean, Integer USERID);

    int insertSurveyQuestion(List<SurveyQuestionBean> surveyBean);

    int updateSurveyQuestion(List<SurveyQuestionBean> surveyBean);

    SurveyResult<String> deleteSurveyWithQuestion(List<Integer> questionIds);

    SurveyResult<String> deleteQuestionReply(List<Integer> replyIds);

    int insertQuestionReply(List<QuestionReplyOptionBean> insertQuestionList);

    int updateQuestionReply(List<QuestionReplyOptionBean> updateQuestionList);
}
