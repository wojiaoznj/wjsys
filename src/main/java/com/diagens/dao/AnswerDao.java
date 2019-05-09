package com.diagens.dao;

import com.diagens.bean.AnswerBean;
import com.diagens.bean.ReplyBean;
import com.diagens.bean.SurveyBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-31 14:35
 */
public interface AnswerDao {
    int insertAnswer(AnswerBean answerBean);

    int insertAnswerReply(List<ReplyBean> replyBeans);

    List<AnswerBean> getAnswer(Integer surveyId);

    List<SurveyBean> getAnswerDetail(Integer answerId);

    List<Map<String,Integer>> getQuestionNums(Integer surveyId);

    List<ReplyBean> getAnalysis(Integer surveyId);

    List<ReplyBean> getReplyContent(Integer surveyId);
}
