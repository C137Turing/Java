package com.bit.blog.controller;

import com.bit.blog.pojo.request.AddBlogRequest;
import com.bit.blog.pojo.request.UpdateBlogRequest;
import com.bit.blog.pojo.response.BlogInfoResponse;
import com.bit.blog.service.BlogInfoService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogInfoController {

    @Resource(name = "blogInfoServiceImpl")
    private BlogInfoService blogInfoService;

    @RequestMapping("/getlist")
    public List<BlogInfoResponse> getList() {
        log.info("获取博客列表");
        List<BlogInfoResponse> blogInfoResponses = blogInfoService.getList();
        return blogInfoResponses;
    }
    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(@NotNull Integer blogId) {
        log.info("获取博客详细列表");
        return blogInfoService.getBlogDetail(blogId);
    }

    @RequestMapping("/add")
    public Boolean addBlog(@RequestBody @Validated AddBlogRequest addBlogRequest) {
        return blogInfoService.addBlog(addBlogRequest);
    }

    @RequestMapping("/update")
    public Boolean updateBlog(@RequestBody @Validated UpdateBlogRequest updateBlogRequest) {
        log.info("更新博客,request:{}", updateBlogRequest);
        return blogInfoService.updateBlog(updateBlogRequest);
    }

    @RequestMapping("/delete")
    public Boolean deleteBlog(@NotNull(message = "id不能为空") Integer blogId) {
        log.info("删除博客,id:{}", blogId);
        return blogInfoService.deleteBlog(blogId);
    }
}
