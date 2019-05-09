package com.diagens.service.impl;

import com.diagens.bean.*;
import com.diagens.dao.AnswerDao;
import com.diagens.dto.SurveyResult;
import com.diagens.service.AnswerService;
import com.diagens.util.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-31 14:33
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerDao answerDao;

    @Override
    public SurveyResult insertAnswer(AnswerBean answerBean) {
        int insertCount = answerDao.insertAnswer(answerBean);
        if (insertCount <= 0) {
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
        for (ReplyBean replyBean : answerBean.getReplyBeans()) {
            replyBean.getAnswerBean().setAnswerId(answerBean.getAnswerId());
        }
        insertCount = answerDao.insertAnswerReply(answerBean.getReplyBeans());
        return API.judgeEffect(insertCount);
    }

    @Override
    public SurveyResult<List<Map<Object, Object>>> getAnswer(Integer surveyId) {
        List<AnswerBean> answerBeans = answerDao.getAnswer(surveyId);
        if (StringUtils.isEmpty(answerBeans)) {
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
        List<Map<Object, Object>> list = new ArrayList<>();
        for (AnswerBean answerBean : answerBeans) {
            Map<Object, Object> map = new HashMap<>();
            map.put("answerId", answerBean.getAnswerId());
            map.put("surveyId", surveyId);
            map.put("answerStart", answerBean.getAnswerStart());
            map.put("answerPerson", answerBean.getAnswerPerson());
            map.put("answerTime", answerBean.getAnswerTime());
            list.add(map);
        }
        return new SurveyResult<List<Map<Object, Object>>>(API.SUCCESSCODE, list, API.SUCCESS);
    }

    @Override
    public SurveyResult<List<Map<Object, Object>>> getAnswerDetail(Integer answerId) {
        if (StringUtils.isEmpty(answerId)) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
        List<SurveyBean> surveyBeans = answerDao.getAnswerDetail(answerId);
        if (surveyBeans.size() <= 0) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
        //整个Map,存储问卷信息
        Map<Object, Object> map = new HashMap<>();
        //存储题目和题目选项回复的信息
        Map<Object, Object> map1 = new HashMap<>();
        //存储答卷和答卷详细的信息
        Map<Object, Object> map3 = new HashMap<>();
        List<Map<Object, Object>> list = new ArrayList<>();
        List<Map<Object, Object>> list1 = new ArrayList<>();
        List<Map<Object, Object>> list2 = new ArrayList<>();
        List<Map<Object, Object>> list3 = new ArrayList<>();
        List<Map<Object, Object>> list4 = new ArrayList<>();
        SurveyBean survey = surveyBeans.get(0);
        //存储所需的问卷信息
        map.put("surveyId", survey.getSurveyId());
        map.put("surveyType", survey.getSurveyType());
        map.put("surveyName", survey.getSurveyName());
        map.put("surverExplain", survey.getSurveyExplain());
        for (SurveyBean surveyBean : surveyBeans) {
            for (SurveyQuestionBean sqs : surveyBean.getQuestionBean()) {
                //判断是否存储过有关的题目
                if (!map1.containsValue(sqs.getQuestionId())) {
                    map1 = new HashMap<>();
                    list2 = new ArrayList<>();
                    map1.put("questionId", sqs.getQuestionId());
                    map1.put("questionExplain", sqs.getQuestionExplain());
                    map1.put("questionType", sqs.getQuestionType());
                    map1.put("questionTitle", sqs.getQuestionTitle());
                    map1.put("isRequired", sqs.getIsRequired());
                    list1.add(map1);
                }
                for (QuestionReplyOptionBean qros : sqs.getQro()) {
                    Map<Object, Object> map2 = new HashMap<>();
                    map2.put("qroContent", qros.getQroContent());
                    map2.put("qroId", qros.getQroId());
                    list2.add(map2);
                    map1.put("reply", list2);
                }
            }

            for (AnswerBean answerBean : surveyBean.getAnswerBeans()) {
                for (ReplyBean replyBean : answerBean.getReplyBeans()) {
                    Map<Object, Object> map2 = new HashMap<>();
                    map2.put("replyId", replyBean.getReplyId());
                    map2.put("qroId", replyBean.getQroIds());
                    map2.put("replyContent", replyBean.getReplyContent());
                    list4.add(map2);
                    map3.put("answerReply", list4);
                }
                if (!map3.containsValue(answerBean.getAnswerId())) {
                    map3 = new HashMap<>();
                    list4 = new ArrayList<>();
                    map3.put("answerId", answerBean.getAnswerId());
                    map3.put("answerPerson", answerBean.getAnswerPerson());
                    map3.put("answerTime", answerBean.getAnswerTime());
                    list3.add(map3);
                }
            }
        }
        map.put("question", list1);
        map.put("answer", list3);
        list.add(map);
        return new SurveyResult<List<Map<Object, Object>>>(API.SUCCESSCODE, list, API.SUCCESS);
    }

    @Override
    public SurveyResult<List<Map<Object, Object>>> getAnalysis(Integer surveyId) {
        //查出每到题的答题人数
        List<Map<String, Integer>> list = answerDao.getQuestionNums(surveyId);
        Map<Integer, Integer> map = new HashMap<>();
        for (Map<String, Integer> map1 : list) {
            //将封装为题目ID-->人数
            map.put(map1.get("question_id"), map1.get("question_nums"));
        }

        //查出分析题目的具体数据
        List<ReplyBean> replyBeans = answerDao.getAnalysis(surveyId);
        List<Map<Object, Object>> sqList = new ArrayList<>();
        List<Map<Object, Object>> qroList;
        Map<Object, Object> sqMap = new HashMap<>();
        Map<Object, Object> qroMap = new HashMap<>();
        for (ReplyBean replyBean : replyBeans) {
            SurveyQuestionBean sqBean = replyBean.getSqBean();
            //避免添加多次
            if (!sqMap.containsValue(sqBean.getQuestionTitle())) {
                qroList = new ArrayList<>();
                qroMap = new HashMap<>();
                sqMap = new HashMap<>();
                sqMap.put("questionTitle", sqBean.getQuestionTitle());
                sqMap.put("questionType", sqBean.getQuestionType());
                sqMap.put("questionNums", map.get(sqBean.getQuestionId()));
                sqList.add(sqMap);
                qroList.add(qroMap);
                sqMap.put("reply", qroList);
            }
            for (QuestionReplyOptionBean qro : sqBean.getQro()) {
                //判断是否为文本类型
                if (sqBean.getQuestionType() != 2) {
                    //判断map里面是否存储了，没有存储则为0
                    if (!qroMap.containsKey(qro.getQroContent())) {
                        qroMap.put(qro.getQroContent(), 0);
                    }
                    //判断回复的题目ID集是否有此选项,有则加1
                    if (replyBean.getQroIds().contains(qro.getQroId().toString())) {
                        qroMap.put(qro.getQroContent(), (Integer) qroMap.get(qro.getQroContent()) + 1);
                    }
                }
            }
        }
        return new SurveyResult<>(API.SUCCESSCODE, sqList, API.SUCCESS);
    }

    @Override
    public SurveyResult<List<Map<Object, Object>>> getReplyContent(Integer surveyId) {
        List<ReplyBean> replyBeans=answerDao.getReplyContent(surveyId);
        //存储答卷的时间和回答的文本内容
        List<Map<Object,Object>> list=new ArrayList<>();
        for(ReplyBean replyBean:replyBeans){
            Map<Object,Object> map=new HashMap<>();
            map.put("replyContent",replyBean.getReplyContent());
            map.put("replyTime",replyBean.getAnswerBean().getAnswerStart());
            list.add(map);
        }
        return new SurveyResult<>(API.SUCCESSCODE,list,API.SUCCESS);
    }


}
