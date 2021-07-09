package com.blog.controller.foreign;


import com.blog.entity.Blog;
import com.blog.lucene.BlogIndex;
import com.blog.service.BlogService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qiyao
 * @since 2021-05-29
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private BlogIndex blogIndex;
    @ResponseBody
    @RequestMapping("/blogDateList")
    public String blogDateList(){
        try {
            //调用查询博客日期的数量和方法
            return blogService.findBlogDateAndCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/view/{id}")
    public String view(@PathVariable int id, Model model){
        try {
            //查看详情
            Blog blog = blogService.findBlogById(id);
            //获取关键字
            if(blog!=null && blog.getKeyWord()!=null&&blog.getKeyWord().length()!=0){
                String[] keyword = blog.getKeyWord().split(" ");
                System.out.println(keyword);
                model.addAttribute("keywordList",keyword);
            }else{
                model.addAttribute("keywordList",null);
            }
            model.addAttribute("blog",blog);
            model.addAttribute("pageCode",getUpAndDownPageCode(blogService.findPrevBlog(id),blogService.findNextBlog(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("pageContent","foreign/blog/view");
        return "index";
    }
    private String getUpAndDownPageCode(Blog prev,Blog next){
        StringBuffer sb = new StringBuffer();
        if(prev==null||prev.getId()==null){
            sb.append("<p>上一篇：没有上一篇了</p>");
        }else{
            sb.append("<p>上一篇：<a href='/blog/view/"+prev.getId()+"'>"+prev.getTitle()+"</a></p>");
        }
        if(next==null||next.getId()==null){
            sb.append("<p>下一篇：没有下一篇了</p>");
        }else{
            sb.append("<p>下一篇：<a href='/blog/view/"+next.getId()+"'>"+next.getTitle()+"</a></p>");
        }
        return sb.toString();
    }
    @RequestMapping("/query")
    public String query(String keyWord, @RequestParam(required = false,defaultValue = "1") Integer page,Model model){
        try {
            //每页显示的数量
            int pageSize = 3;
            //调用查询博客的方法
            List<Blog> blogList = blogIndex.searchIndex(keyWord);
            //计算集合中的分页
            Integer toIndex = blogList.size() >= page*pageSize ? page*pageSize : blogList.size();
            //将数据放到模型subList(参数1：开始的下标位置 参数2：结束位置)
            model.addAttribute("blogList",blogList.subList((page-1)*pageSize,toIndex));
            model.addAttribute("keyWord",keyWord);
            model.addAttribute("total",blogList.size());
            model.addAttribute("pageContent","foreign/blog/result");
            //分页
            model.addAttribute("pageCode",getUpAndDownPageCode(page,pageSize,blogList.size(),keyWord));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    /*
    * pageNo 当前页码
    * pageSize 每页显示数量
    * totalCount 总数量
    * keyWord 用户输入查询的关键词
    * */
    private String getUpAndDownPageCode(int pageNo,int pageSize,int totalCount,String keyWord){
        StringBuffer pageCode = new StringBuffer();
        //计算总页数
        int totalPage = totalCount % pageSize == 0 ?totalCount/pageSize : totalCount/pageSize+1;
        //判断总页数
        if(totalPage == 0){
            return "";
        }else{
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager'>");
            //判断是否能点击上一页
            if(pageNo>1){
                pageCode.append("<li><a href='/blog/query?page="+(pageNo-1)+"&keyWord="+keyWord+"'>上一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a>上一页</a></li>");
            }
            //判断是否能点击下一页
            if(pageNo<totalPage){
                pageCode.append("<li><a href='/blog/query?page="+(pageNo+1)+"&keyWord="+keyWord+"'>下一页</a></li>");
            }else{
                pageCode.append("<li class='disabled'><a>下一页</a></li>");
            }
            pageCode.append("</ul></nav>");
        }
        return pageCode.toString();
    }
}

