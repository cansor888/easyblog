package com.blog.controller.foreign;


import com.blog.service.BlogtypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Controller
@RequestMapping("/blogtype")
public class BlogtypeController {

    @Resource
    private BlogtypeService blogtypeService;

    @ResponseBody
    @GetMapping("/typelist")
    public String typelist(){
        try {
            //返回博客类别
            return blogtypeService.getBlogtypeNameAndBlogCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

