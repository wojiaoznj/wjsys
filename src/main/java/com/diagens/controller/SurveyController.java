package com.diagens.controller;

import com.diagens.bean.QuestionReplyOptionBean;
import com.diagens.bean.SurveyBean;
import com.diagens.bean.SurveyQuestionBean;
import com.diagens.dto.Page;
import com.diagens.dto.SurveyResult;
import com.diagens.service.SurveyService;
import com.diagens.util.API;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            List<SurveyBean> surveyBeans = page.getList();
            //问卷选择属性输出
            List<Map<Object, Object>> list = new ArrayList<>();
            for (SurveyBean surveyBean : surveyBeans) {
                Map<Object, Object> map = new HashMap<>();
                map.put("surveyType", surveyBean.getSurveyType());
                map.put("surveyName", surveyBean.getSurveyName());
                map.put("createTime", surveyBean.getCreateTime());
                map.put("surveyId", surveyBean.getSurveyId());
                map.put("surveyStatus", surveyBean.getSurveyStatus());
                map.put("userId", API.USERID);
                list.add(map);
            }
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
            surveyBean.getUserInfo().setUserId(API.USERID);
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
            Integer status = 0;
            //判断问卷状态
            if (surveyStatus == 0) {
                //草稿状态变为运行状态
                status = 1;
            } else if (surveyStatus == 1) {
                //运行状态变为暂停状态
                status = 2;
            } else if (surveyStatus == 2) {
                //暂停状态变为运行状态
                status = 1;
            }
            return surveyService.updateSurveyStatus(API.USERID, surveyId, surveyStatus, status);
        } catch (Exception e) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
    }

    //查询问卷及其题目
    @RequestMapping("/getSurveyWithQuestion")
    @ResponseBody
    public SurveyResult<List<Map<Object, Object>>> getSurveyWithQuestion(Integer surveyId) {
        try {
            return surveyService.getSurveyWithQuestion(API.USERID, surveyId);
        } catch (Exception e) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
    }


    //编辑问卷及其题目
    @RequestMapping(value = "/updateSurveyWithQuestion",method = RequestMethod.POST)
    @ResponseBody
    public SurveyResult<String> updateSurveyWithQuestion(SurveyBean surveyBean) {
        boolean flag = false;
        try {
            if (!StringUtils.isEmpty(surveyBean.getSurveyName()) || !StringUtils.isEmpty(surveyBean.getSurveyExplain())) {
                int updateCount = surveyService.updateSurvey(surveyBean, API.USERID);
                if (updateCount <= 0) {
                    return new SurveyResult<>(API.ERRORCODE, API.ERROR);
                }
                flag = true;
            }

            System.out.println(surveyBean.getQuestionBean());
            List<SurveyQuestionBean> questionBeans=surveyBean.getQuestionBean();

            //存储添加和修改的数据集合
            List<SurveyQuestionBean> insertSurveyList = new ArrayList<>();
            List<SurveyQuestionBean> updateSurveyList = new ArrayList<>();

            //存储添加和修改的题目数据集合
            List<QuestionReplyOptionBean> insertQuestionList = new ArrayList<>();
            List<QuestionReplyOptionBean> updateQuestionList = new ArrayList<>();

            for (SurveyQuestionBean sqb : questionBeans) {
                //题目Id不为NULL则是添加题目,将其放入到添加集合中
                if (StringUtils.isEmpty(sqb.getQuestionId())) {
                    //判断问卷状态，为0则是草稿，不给题目上锁
                    if (surveyBean.getSurveyStatus() == 0) {
                        sqb.setIsEdit(0);
                    } else {
                        sqb.setIsEdit(1);
                        //如果问卷添加前的状态为暂停状态，则添加后需要将其发布出去
                        if (surveyBean.getSurveyStatus() == 2) {
                            updateSurveyStatus(surveyBean.getSurveyId(), surveyBean.getSurveyStatus());
                        }
                    }
                    sqb.getSurveyBean().setSurveyId(surveyBean.getSurveyId());
                    insertSurveyList.add(sqb);
                } else {
                    updateSurveyList.add(sqb);
                }

                //修改题目操作时:
                //   两种情况可独立
                //   -- 新增题目回复选项:没有题目回复选项ID有题目ID
                //   -- 修改题目回复选项:有题目回复选项ID
                //新增题目操作时:
                //-- 新增题目回复选项:没有题目回复选项ID没有题目ID,有题目回复内容
                for (QuestionReplyOptionBean qro : sqb.getQro()) {
                    if (!StringUtils.isEmpty(qro.getQroId())) {
                        updateQuestionList.add(qro);
                    } else if (!StringUtils.isEmpty(qro.getSqBean().getQuestionId())) {
                        insertQuestionList.add(qro);
                    }
                }
            }
            //如果不为空，则进行批量添加/修改
            if (insertSurveyList.size() > 0) {
                int insertCount = surveyService.insertSurveyQuestion(insertSurveyList);
                if (insertCount <= 0) {
                    return new SurveyResult<>(API.ERRORCODE, API.ERROR);
                }
                flag = true;
                //新增题目回复选项:没有题目回复选项ID没有题目ID,有题目回复内容
                for (SurveyQuestionBean sqb : insertSurveyList) {
                    for (QuestionReplyOptionBean qro : sqb.getQro()) {
                        if (!StringUtils.isEmpty(qro.getQroContent()) && StringUtils.isEmpty(qro.getQroId()) && StringUtils.isEmpty(qro.getSqBean().getQuestionId())) {
                           qro.getSqBean().setQuestionId(sqb.getQuestionId());
                           insertQuestionList.add(qro);
                        }
                    }
                }
            }
            if (updateSurveyList.size() > 0) {
                int updateCount = surveyService.updateSurveyQuestion(updateSurveyList);
                if (updateCount <= 0) {
                    return new SurveyResult<>(API.ERRORCODE, API.ERROR);
                }
                flag = true;
            }

            //如果添加/修改回复集合不为空，则进行批量操作
            if (insertQuestionList.size() > 0) {
                int insertCount = surveyService.insertQuestionReply(insertQuestionList);
                if (insertCount <= 0) {
                    return new SurveyResult<>(API.ERRORCODE, API.ERROR);
                }
                flag = true;
            }
            if (updateQuestionList.size() > 0) {
                int updateCount = surveyService.updateQuestionReply(updateQuestionList);
                if (updateCount <= 0) {
                    return new SurveyResult<>(API.ERRORCODE, API.ERROR);
                }
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
        if (flag) {
            return new SurveyResult<>(API.SUCCESSCODE, API.SUCCESS);
        }
        return new SurveyResult<>(API.ERRORCODE, API.ERROR);
    }

    //批量删除问卷题目
    @RequestMapping(value = "/deleteSurveyWithQuestion", method = RequestMethod.POST)
    @ResponseBody
    public SurveyResult<String> deleteSurveyWithQuestion(@RequestParam(value = "questionIds") List<Integer> questionIds) {
        try {
            return surveyService.deleteSurveyWithQuestion(questionIds);
        } catch (Exception e) {
            return new SurveyResult<>(API.ERRORCODE, API.ERROR);
        }
    }
}
