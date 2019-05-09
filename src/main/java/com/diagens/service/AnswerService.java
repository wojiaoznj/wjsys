package com.diagens.service;

import com.diagens.bean.AnswerBean;
import com.diagens.bean.SurveyBean;
import com.diagens.dto.SurveyResult;

import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-31 14:32
 */
public interface AnswerService {
    SurveyResult insertAnswer(AnswerBean answerBean);

    SurveyResult<List<Map<Object,Object>>> getAnswer(Integer surveyId);

    SurveyResult<List<Map<Object,Object>>> getAnswerDetail(Integer answerId);

    SurveyResult<List<Map<Object, Object>>> getAnalysis(Integer surveyId);

    SurveyResult<List<Map<Object,Object>>> getReplyContent(Integer surveyId);
}
