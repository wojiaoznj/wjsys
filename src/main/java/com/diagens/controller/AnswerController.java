package com.diagens.controller;

import com.diagens.bean.AnswerBean;
import com.diagens.bean.SurveyBean;
import com.diagens.dto.SurveyResult;
import com.diagens.service.AnswerService;
import com.diagens.util.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-31 14:14
 */
@Controller
@RequestMapping("/diagens/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    //新增答卷和答卷回复
    @RequestMapping("/insertAnswer")
    @ResponseBody
    public SurveyResult insertAnswer(@RequestBody AnswerBean answerBean) {
        System.out.println(answerBean);
        try {
            return answerService.insertAnswer(answerBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
    }

    //答卷列表显示
    @RequestMapping("/getAnswer")
    @ResponseBody
    public SurveyResult<List<Map<Object, Object>>> getAnswer(Integer surveyId) {
        try {
            return answerService.getAnswer(surveyId);
        } catch (Exception e) {
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
    }

    //生成答卷详情
    @RequestMapping("/getAnswerDetail")
    @ResponseBody
    public SurveyResult<List<Map<Object, Object>>> getAnswerDetail(Integer answerId) {
        try {
            return answerService.getAnswerDetail(answerId);
        } catch (Exception e) {
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
    }

    //分析问卷选择题
    @RequestMapping("/getAnalysis")
    @ResponseBody
    public SurveyResult<List<Map<Object, Object>>> getAnalysis(Integer surveyId) {
        try {
            return answerService.getAnalysis(surveyId);
        } catch (Exception e) {
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
    }

    //列出所写的文本内容
    @RequestMapping("/getReplyContent")
    @ResponseBody
    public SurveyResult<List<Map<Object,Object>>> getReplyContent(Integer surveyId){
        try{
           return answerService.getReplyContent(surveyId);
        }catch(Exception e){
            return new SurveyResult(API.ERRORCODE, API.ERROR);
        }
    }

}
