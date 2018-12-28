package com.diagens.service.impl;

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
        if(deleteCount<0){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
         return new SurveyResult<>(API.SUCCESSCODE,API.SUCCESS);
    }

    @Override
    public SurveyResult updateSurveyStatus(Integer userid, Integer surveyId, Integer surveyStatus, Integer status) {
        if(StringUtils.isEmpty(surveyId)||surveyStatus<0||surveyStatus>3){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        int updateCount=surveyDao.updateSurveyStatus(userid,surveyId,surveyStatus,status);
        if(updateCount<0){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        return new SurveyResult<>(API.SUCCESSCODE,API.SUCCESS);
    }

    @Override
    public SurveyResult<List<Map<Object,Object>>> getSurveyWithQuestion(Integer userid, Integer surveyId) {
        if(StringUtils.isEmpty(userid)){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        List<SurveyBean> surveyBeans=surveyDao.getSurveyWithQuestion(userid,surveyId);
        if(StringUtils.isEmpty(surveyBeans)){
            return new SurveyResult<>(API.ERRORCODE,API.ERROR);
        }
        Map<Object,Object> m=new HashMap<>();
        List<Map<Object,Object>> list=new ArrayList<>();
        List<Map<Object,Object>> list1=new ArrayList<>();
        SurveyBean survey=surveyBeans.get(0);
        m.put("surveyId",survey.getSurveyId());
        m.put("surveyType",survey.getSurveyType());
        m.put("surveyName",survey.getSurveyName());
        m.put("surverExplain",survey.getSurveyExplain());
        m.put("surverStatus",survey.getSurveyStatus());
        for(SurveyBean surveyBean:surveyBeans){
            SurveyQuestionBean sqb=surveyBean.getQuestionBean();
            Map<Object,Object> map=new HashMap<>();
            map.put("questionId",sqb.getQuestionId());
            map.put("questionType",sqb.getQuestionType());
            map.put("questionTitle",sqb.getQuestionTitle());
            map.put("questionExplain",sqb.getQuestionExplain());
            map.put("isRequired",sqb.getIsRequired());
            map.put("questionSort",sqb.getQuestionSort());
            map.put("isEdit",sqb.getIsEdit());
            list1.add(map);
        }
        m.put("question",list1);
        list.add(m);
        return new SurveyResult<List<Map<Object,Object>>>(API.SUCCESSCODE,list,API.SUCCESS);
    }
}
