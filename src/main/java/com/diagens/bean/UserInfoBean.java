package com.diagens.bean;

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
    private SurveyBean surveyBean=new SurveyBean();

    public UserInfoBean() {
    }

    public UserInfoBean(Integer userId) {
        this.userId = userId;
    }

    public UserInfoBean(Integer userId, String userName, String userPassWord, String userNickName, Integer userSex, String openId, String openType, SurveyBean surveyBean) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public SurveyBean getSurveyBean() {
        return surveyBean;
    }

    public void setSurveyBean(SurveyBean surveyBean) {
        this.surveyBean = surveyBean;
    }
}
