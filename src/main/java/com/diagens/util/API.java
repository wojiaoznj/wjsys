package com.diagens.util;

import com.diagens.bean.SurveyBean;
import com.diagens.dto.SurveyResult;

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

    //判断影响的行数
    public static SurveyResult judgeEffect(int effectCount){
        if (effectCount<=0){
            return new SurveyResult(ERRORCODE,ERROR);
        }
        return new SurveyResult(SUCCESSCODE,SUCCESS);
    }
}
