package com.blog.controller.foreign;


import com.alibaba.fastjson.JSON;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import com.blog.utils.SysConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/addComment")
    public String addComment(Comment comment){
        Map<String,Object> map = new HashMap<String,Object>();
        //设置评论信息
        try {
            comment.setCommentDate(new Date());//评论时间为当前时间
            comment.setState(SysConstant.COMMENT_STATE_no);//未审核
            comment.setUserIp(InetAddress.getLocalHost().getHostAddress());
            boolean flag = commentService.save(comment);
            if(flag){
                map.put("success",true);
            }else{
                map.put("success",false);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}

