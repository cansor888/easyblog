package com.blog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
public interface BlogMapper extends BaseMapper<Blog> {
    /*
    * 查询博客日期和博客数量
    * */
    List<Blog> findBlogDateAndCount() throws Exception;
    /*
    * 分页查询博客信息
    * */
    IPage<Blog> findBlogPage(@Param("page")IPage<Blog> page,@Param("blog")Blog blog);
    /*
    * 查看博客详情
    * */
    Blog findBlogById(int id) throws Exception;
    /*
    * 查看上一篇博客
    * */
    Blog findPrevBlog(int id) throws Exception;
    /*
    * 查看下一篇博客
    * */
    Blog findNextBlog(int id) throws Exception;
}
