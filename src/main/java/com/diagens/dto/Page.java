package com.diagens.dto;

/**
 * @author ZNJ
 * @create 2018-12-27 16:19
 */
public class Page<T> extends SurveyResult {
    //当前页数
    private Integer current_page;
    //当前页总记录数
    private Integer count;
    //页数大小
    private Integer page_size;
    //总页数
    private Integer total_page;

    public Page() {
    }

    public Page(Integer code, T data, String message, Integer current_page, Integer count, Integer page_size, Integer total_page) {
        super(code, data, message);
        this.current_page = current_page;
        this.count = count;
        this.page_size = page_size;
        this.total_page = total_page;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }
}
