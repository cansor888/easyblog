package com.blog.controller;


import com.blog.entity.Blogger;
import com.blog.utils.SysConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @PostMapping("/login")
    public String login(Blogger blogger){
        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        //创建登录令牌
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(),blogger.getPassword());
            //登录
            subject.login(token);
            //保存当前登录用户
            subject.getSession().setAttribute(SysConstant.LOGINUSER,blogger);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "redirect:/login.html";
        }
        //登录成功去后台首页
        return "redirect:/admin/index";
    }
}

