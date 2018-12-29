package com.diagens.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZNJ
 * @create 2018-12-27 10:30
 */
public class UserInfoBean {
    //用户Id
    private Integer userId;
    //管理员账号
    private String userName;
    //管理员密码
    private String userPassWord;
    //用户第三方昵称
    private String userNickName;
    //性别 0女 1男
    private Integer userSex;
    //第三方登录用户的唯一标识
    private String openId;
    //第三方平台的名字
    private String openType;
    //问卷信息
    private List<SurveyBean> surveyBean=new ArrayList<>();

    public UserInfoBean() {
    }

    public UserInfoBean(Integer userId) {
        this.userId = userId;
    }

    public UserInfoBean(Integer userId, String userName, String userPassWord, String userNickName, Integer userSex, String openId, String openType, List<SurveyBean> surveyBean) {
        this.userId = userId;
        this.userName = userName;
        this.userPassWord = userPassWord;
        this.userNickName = userNickName;
        this.userSex = userSex;
        this.openId = openId;
        this.openType = openType;
        this.surveyBean = surveyBean;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
