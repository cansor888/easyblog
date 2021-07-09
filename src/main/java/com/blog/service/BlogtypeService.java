package com.blog.service;

import com.blog.entity.Blogtype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
public interface BlogtypeService extends IService<Blogtype> {
    String getBlogtypeNameAndBlogCount() throws Exception;
}
