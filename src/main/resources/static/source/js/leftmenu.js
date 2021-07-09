$(function () {
    //加载日志博客类别
    $.get("/blogtype/typelist",function(data){
        console.log(data);
        var blogTypeList = $("#blogTypeList");
        //循环遍历json数组
        $(data).each(function(){
            var li = "<li><span><a href='/index?typeId="+this.id+"'>"+this.typeName+"("+this.blogCount+")</a></span></li>";
            $(blogTypeList).append(li);
        })
    },"json")
    $.get("/blog/blogDateList",function(data){
        console.log(data);
        var blogList = $("#dateList");
        //循环遍历json数组
        $(data).each(function(){
            var li = "<li><span><a href='/index?releaseDateStr="+this.releaseDateStr+"'>"+this.releaseDateStr+"("+this.blogCount+")</a></span></li>";
            $(blogList).append(li);
        })
    },"json")
})