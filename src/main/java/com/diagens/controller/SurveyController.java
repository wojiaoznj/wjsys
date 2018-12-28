package com.diagens.controller;

import com.diagens.bean.SurveyBean;
import com.diagens.bean.UserInfoBean;
import com.diagens.dto.Page;
import com.diagens.dto.SurveyResult;
import com.diagens.service.SurveyService;
import com.diagens.util.API;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-27 8:34
 */
@Controller
@RequestMapping("/diagens/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    //分页显示问卷
    @RequestMapping("/getSurvey")
    @ResponseBody
    public SurveyResult<List<Map<Object, Object>>> getSurvey(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        try {
            PageInfo page = surveyService.getSurvey(API.USERID, pageNum);
            List list = API.selectProperty(page.getList());
            return new Page<List<Map<Object, Object>>>(API.SUCCESSCODE, list, API.SUCCESS, pageNum, page.getSize(), API.PAGESIZE, page.getPages());
        } catch (Exception e) {
            return new SurveyResult<List<Map<Object, Object>>>(API.ERRORCODE, API.ERROR);
        }
    }

    //添加问卷
    @RequestMapping("/insertSurvey")
    @ResponseBody
    public SurveyResult<String> insertSurvey(SurveyBean surveyBean) {
        try {
            surveyBean.getUserInfoList().add(new UserInfoBean(API.USERID));
            surveyBean.setCreateTime(new Date());
            return surveyService.insertSurvey(surveyBean);
        } catch (Exception e) {
            return new SurveyResult<String>(API.ERRORCODE, API.ERROR);
        }
    }

    //删除问卷
    @RequestMapping("/deleteSurvey")
    @ResponseBody
    public SurveyResult<String> deleteSurvey(Integer surveyId) {
        try {
            return surveyService.deleteSurvey(surveyId, API.USERID);
        } catch (Exception e) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
    }

    //修改问卷状态
    @RequestMapping("/updateSurveyStatus")
    @ResponseBody
    public SurveyResult updateSurveyStatus(Integer surveyId, Integer surveyStatus) {
        try {
            //修改问卷状态
            Integer status = API.judgeStatus(surveyStatus);
            return surveyService.updateSurveyStatus(API.USERID, surveyId, surveyStatus, status);
        } catch (Exception e) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
    }

    //查询问卷及其题目
    @RequestMapping("/getSurveyWithQuestion")
    @ResponseBody
    public SurveyResult<List<Map<Object,Object>>> getSurveyWithQuestion(Integer surveyId) {
        try {
            return surveyService.getSurveyWithQuestion(API.USERID, surveyId);
        } catch (Exception e) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
    }


    //编辑问卷及其题目

}
