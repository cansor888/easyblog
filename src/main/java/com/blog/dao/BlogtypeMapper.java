package com.blog.dao;

import com.blog.entity.Blogtype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
public interface BlogtypeMapper extends BaseMapper<Blogtype> {
    List<Blogtype> getBlogtypeNameAndBlogCount() throws Exception;
}
