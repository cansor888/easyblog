package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.entity.Blogger;
import com.blog.dao.BloggerMapper;
import com.blog.service.BloggerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Service
public class BloggerServiceImpl extends ServiceImpl<BloggerMapper, Blogger> implements BloggerService {
    @Resource
    private BloggerMapper bloggerMapper;

    @Override
    public Blogger findBloggerByUserName(String userName) throws Exception {
        QueryWrapper<Blogger> queryWrapper = new QueryWrapper<Blogger>();
        queryWrapper.eq("userName",userName);
        return bloggerMapper.selectOne(queryWrapper);
    }
}
