package com.diagens.dao;

import com.diagens.bean.SurveyBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZNJ
 * @create 2018-12-27 8:40
 */
public interface SurveyDao {
    void insertSurvey(SurveyBean surveyBean);

    List<SurveyBean> getSurvey(Integer userid);

    int deleteSurvey(@Param("surveyId")Integer surveyId, @Param("userId") Integer userid);

    int updateSurveyStatus(@Param("userId")Integer userid, @Param("surveyId")Integer surveyId, @Param("surveyStatus")Integer surveyStatus, @Param("status")Integer status);

    List<SurveyBean> getSurveyWithQuestion(@Param("userId")Integer userid, @Param("surveyId")Integer surveyId);
}
