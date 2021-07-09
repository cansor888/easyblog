package com.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.entity.Blog;
import com.blog.dao.BlogMapper;
import com.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.utils.SysConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Service
@Transactional
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String findBlogDateAndCount() throws Exception {
        //先从缓存中读取数据，判断该缓存是否存在数据，如果缓存为空，则查询数据库
        String blogInfo = redisTemplate.opsForValue().get(SysConstant.BLOG_DATE_COUNT);
        if(blogInfo == null || blogInfo.equals("")||blogInfo.length()==0){
            //查询数据库
            List<Blog> blogList= blogMapper.findBlogDateAndCount();
            //转换成json字符串
            blogInfo = JSON.toJSONString((blogList));
            //将数据保存到缓存中
            redisTemplate.opsForValue().set(SysConstant.BLOG_DATE_COUNT,blogInfo);
        }
        return blogInfo;
    }

    @Override
    public IPage<Blog> findBlogPage(IPage<Blog> page, Blog blog) {
        return blogMapper.findBlogPage(page,blog);
    }

    @Override
    public Blog findBlogById(int id) throws Exception {
        //修改博客点击数量
        Blog blog = blogMapper.findBlogById(id);
        blog.setClickHit(blog.getClickHit()+1);
        blogMapper.updateById(blog);
        return blog;
    }

    @Override
    public Blog findPrevBlog(int id) throws Exception {
        return blogMapper.findPrevBlog(id);
    }

    @Override
    public Blog findNextBlog(int id) throws Exception {
        return blogMapper.findNextBlog(id);
    }

    @Override
    public int addBlog(Blog blog) throws Exception {
        blog.setClickHit(0);//阅读量
        blog.setReplyHit(0);//评论数量
        blog.setReleaseDate(new Date());//发布时间
        //清空两个缓存
        redisTemplate.delete(SysConstant.BLOG_DATE_COUNT);//清空按日期查询的缓存
        redisTemplate.delete(SysConstant.BLOG_NAME_COUNT);//清空按日志类别查询的缓存
        return blogMapper.insert(blog);
    }

    @Override
    public int deleteBlogById(int blogId) throws Exception {
        //清空缓存
        redisTemplate.delete(SysConstant.BLOG_DATE_COUNT);//清空按日期查询的缓存
        redisTemplate.delete(SysConstant.BLOG_NAME_COUNT);//清空按日志类别查询的缓存
        return blogMapper.deleteById(blogId);
    }

    @Override
    public int updateBlog(Blog blog) throws Exception {
        //清空缓存
        redisTemplate.delete(SysConstant.BLOG_DATE_COUNT);//清空按日期查询的缓存
        redisTemplate.delete(SysConstant.BLOG_NAME_COUNT);//清空按日志类别查询的缓存
        return blogMapper.updateById(blog);
    }
}
