package com.diagens.service.impl;

import com.diagens.bean.QuestionReplyOptionBean;
import com.diagens.bean.SurveyBean;
import com.diagens.bean.SurveyQuestionBean;
import com.diagens.dao.SurveyDao;
import com.diagens.dto.SurveyResult;
import com.diagens.service.SurveyService;
import com.diagens.util.API;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-27 8:38
 */
@Service
public class SurveyServiceImpl implements SurveyService{

    @Autowired
    private SurveyDao surveyDao;

    @Override
    public SurveyResult<String> insertSurvey(SurveyBean surveyBean) {
        if(StringUtils.isEmpty(surveyBean.getSurveyName())){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        surveyDao.insertSurvey(surveyBean);
        return new SurveyResult<>(API.SUCCESSCODE,API.SUCCESS);
    }

    @Override
    public PageInfo getSurvey(Integer userid,Integer pageNum) {
        PageHelper.startPage(pageNum,API.PAGESIZE);
        List<SurveyBean> list=surveyDao.getSurvey(userid);
        return new PageInfo(list);
    }

    @Override
    public SurveyResult<String> deleteSurvey(Integer surveyId, Integer userid) {
        if(StringUtils.isEmpty(surveyId)){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        int deleteCount=surveyDao.deleteSurvey(surveyId,userid);

        //没有删除记录
        return API.judgeEffect(deleteCount);
    }

    @Override
    public SurveyResult updateSurveyStatus(Integer userid, Integer surveyId, Integer surveyStatus, Integer status) {
        if(StringUtils.isEmpty(surveyId)||surveyStatus<0||surveyStatus>3){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        //如果是草稿状态，则是发布问卷，需要将该问卷题目上锁
        if(surveyStatus==0){
            surveyDao.updateQuestionStatus(surveyId);
        }
        int updateCount=surveyDao.updateSurveyStatus(userid,surveyId,surveyStatus,status);
        return API.judgeEffect(updateCount);
    }

    public SurveyResult<List<Map<Object,Object>>> getSurveyQuestionWithReply(Integer userid, Integer surveyId){
        if(StringUtils.isEmpty(userid)){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        List<SurveyQuestionBean> questionBeans=surveyDao.getSurveyQuestionWithReply(userid,surveyId);
        if(StringUtils.isEmpty(questionBeans)){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        Map<Object,Object> map=new HashMap<>();
        Map<Object,Object> map1=new HashMap<>();
        List<Map<Object,Object>> list=new ArrayList<>();
        List<Map<Object,Object>> list1=new ArrayList<>();
        List<Map<Object,Object>> list2=new ArrayList<>();
        SurveyBean surveyBean=questionBeans.get(0).getSurveyBean();
        map.put("surveyId",surveyBean.getSurveyId());
        map.put("surveyType",surveyBean.getSurveyType());
        map.put("surveyName",surveyBean.getSurveyName());
        map.put("surverExplain",surveyBean.getSurveyExplain());
        for(SurveyQuestionBean sqs:questionBeans){
            if(!map1.containsValue(sqs.getQuestionId())){
                map1=new HashMap<>();
                list2=new ArrayList<>();
                map1.put("questionId",sqs.getQuestionId());
                map1.put("questionType",sqs.getQuestionType());
                map1.put("questionTitle",sqs.getQuestionTitle());
                map1.put("questionExplain",sqs.getQuestionExplain());
                map1.put("isRequired",sqs.getIsRequired());
                list1.add(map1);
            }
            for(QuestionReplyOptionBean qros:sqs.getQro()){
                Map<Object,Object> map2=new HashMap<>();
                map2.put("qroId",qros.getQroId());
                map2.put("qroContent",qros.getQroContent());
                list2.add(map2);
                map1.put("reply",list2);
            }
        }
        map.put("question",list1);
        list.add(map);
        return new SurveyResult<List<Map<Object,Object>>>(API.SUCCESSCODE,list,API.SUCCESS);
    }

    @Override
    public SurveyResult<List<Map<Object,Object>>> getSurveyWithQuestion(Integer userid, Integer surveyId) {
        if(StringUtils.isEmpty(userid)){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        List<SurveyQuestionBean> surveyBeans=surveyDao.getSurveyWithQuestion(userid,surveyId);
        if(surveyBeans.size()<=0){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        Map<Object,Object> m=new HashMap<>();
        List<Map<Object,Object>> list=new ArrayList<>();
        List<Map<Object,Object>> list1=new ArrayList<>();
        SurveyBean survey=surveyBeans.get(0).getSurveyBean();
        m.put("surveyId",survey.getSurveyId());
        m.put("surveyType",survey.getSurveyType());
        m.put("surveyName",survey.getSurveyName());
        m.put("surverExplain",survey.getSurveyExplain());
        m.put("surverStatus",survey.getSurveyStatus());
        for(SurveyQuestionBean sqb:surveyBeans){
            Map<Object,Object> map=new HashMap<>();
            map.put("questionId",sqb.getQuestionId());
            map.put("questionType",sqb.getQuestionType());
            map.put("questionTitle",sqb.getQuestionTitle());
            map.put("questionExplain",sqb.getQuestionExplain());
            map.put("isRequired",sqb.getIsRequired());
            map.put("isEdit",sqb.getIsEdit());
            list1.add(map);
        }
        m.put("question",list1);
        list.add(m);
        return new SurveyResult<List<Map<Object,Object>>>(API.SUCCESSCODE,list,API.SUCCESS);
    }

    @Override
    public int updateSurvey(SurveyBean surveyBean, Integer USERID) {
        return surveyDao.updateSurvey(surveyBean,USERID);
    }

    @Override
    public int insertSurveyQuestion(List<SurveyQuestionBean> sqb) {
        return surveyDao.insertSurveyQuestion(sqb);
    }

    @Override
    public int updateSurveyQuestion(List<SurveyQuestionBean> sqb) {
        return surveyDao.updateSurveyQuestion(sqb);
    }

    @Override
    public SurveyResult<String> deleteSurveyWithQuestion(List<Integer> questionIds) {
        int deleteCount=surveyDao.deleteSurveyWithQuestion(questionIds);
        return API.judgeEffect(deleteCount);
    }

    @Override
    public SurveyResult<String> deleteQuestionReply(List<Integer> replyIds) {
        int deleteCount=surveyDao.deleteQuestionReply(replyIds);
        return API.judgeEffect(deleteCount);
    }

    @Override
    public int insertQuestionReply(List<QuestionReplyOptionBean> insertQuestionList) {
        return surveyDao.insertQuestionReply(insertQuestionList);
    }

    @Override
    public int updateQuestionReply(List<QuestionReplyOptionBean> updateQuestionList) {
        return surveyDao.updateQuestionReply(updateQuestionList);
    }

}

