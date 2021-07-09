package com.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Service
public interface BlogService extends IService<Blog> {
    String findBlogDateAndCount() throws Exception;
    IPage<Blog> findBlogPage(IPage<Blog> page,Blog blog);
    Blog findBlogById(int id) throws Exception;
    /*
     * 查看上一篇博客
     * */
    Blog findPrevBlog(int id) throws Exception;
    /*
     * 查看下一篇博客
     * */
    Blog findNextBlog(int id) throws Exception;

    int addBlog(Blog blog) throws Exception;
    /*
    * 删除博客
    * */
    int deleteBlogById(int blogId) throws Exception;
    /*
    * 修改博客
    * */
    int updateBlog(Blog blog) throws Exception;
}
