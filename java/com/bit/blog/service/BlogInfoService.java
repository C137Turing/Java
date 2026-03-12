package com.bit.blog.service;

import com.bit.blog.pojo.dataobject.BlogInfo;
import com.bit.blog.pojo.request.AddBlogRequest;
import com.bit.blog.pojo.request.UpdateBlogRequest;
import com.bit.blog.pojo.response.BlogInfoResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface BlogInfoService {
    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDetail(Integer blogId);

    BlogInfo getBlogInfo(Integer blogId);

    Boolean addBlog(AddBlogRequest addBlogRequest);

    Boolean updateBlog(UpdateBlogRequest updateBlogRequest);

    Boolean deleteBlog(@NotNull(message = "id不能为空") Integer blogId);
}
