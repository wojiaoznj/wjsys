package com.diagens.dto;

/**
 * @author ZNJ
 * @create 2018-12-27 8:59
 */
public class SurveyResult<T> {
    //请求响应码
    private Integer code;
    //封装的数据
    private T data;
    //请求相应内容
    private String message;

    public SurveyResult() {
    }

    public SurveyResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public SurveyResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
