package com.blog.utils;

public interface SysConstant {
    /*
    * 评论审核状态：1表示未审核 2表示审核通过
    * */
    int COMMENT_STATE_OK = 2;
    int COMMENT_STATE_no = 1;
    /*
    * 当前登录用户的key
    * */
    String LOGINUSER = "loginUser";
    /*
    * 博客类别和数量的缓存数据
    * */
    String BLOG_NAME_COUNT = "blog_name_count";
    /*
    * 博客时间和数量的缓存数据
    * */
    String BLOG_DATE_COUNT = "blog_date_count";
}
