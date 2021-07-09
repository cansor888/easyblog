package com.blog.service;

import com.blog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
public interface CommentService extends IService<Comment> {
    /*
    * 根据博客id删除评论
    * */
    int deleteCommentByBlogId(int blogId) throws Exception;
}
