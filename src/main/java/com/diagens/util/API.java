package com.diagens.util;

import com.diagens.bean.SurveyBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZNJ
 * @create 2018-12-27 15:15
 */
public  class API {
    //每页显示的数值大小
    public static final Integer PAGESIZE=10;
    //返回成功的响应内容
    public static final String SUCCESS="成功";
    //返回成功的状态码
    public static final Integer SUCCESSCODE=8200;
    //返回失败的响应
    public static final String ERROR="失败";
    //返回失败的状态码
    public static final Integer ERRORCODE=8500;
    //用户的登录Id
    public static Integer USERID=1;


    //问卷选择属性输出
    public static List<Map<Object,Object>> selectProperty(List<SurveyBean> surveyBeans){
        List<Map<Object,Object>> list=new ArrayList<>();
        for(SurveyBean surveyBean:surveyBeans){
            Map<Object,Object> map=new HashMap<>();
            map.put("surveyType",surveyBean.getSurveyType());
            map.put("surveyName",surveyBean.getSurveyName());
            map.put("createTime",surveyBean.getCreateTime());
            map.put("surveyId",surveyBean.getSurveyId());
            map.put("surveyStatus",surveyBean.getSurveyStatus());
            map.put("userId",API.USERID);
            list.add(map);
        }
        return list;
    }

    //判断问卷状态
    public static Integer judgeStatus(Integer oldStatus){
        Integer newStatus=0;
        if(oldStatus==0){
            //草稿状态变为运行状态
            newStatus=1;
        }else if(oldStatus==1){
            //运行状态变为暂停状态
            newStatus=2;
        }else if(oldStatus==2){
            //暂停状态变为运行状态
            newStatus=1;
        }
        return newStatus;
    }
}
