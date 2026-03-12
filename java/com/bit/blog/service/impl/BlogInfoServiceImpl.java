package com.bit.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bit.blog.common.constant.Constants;
import com.bit.blog.common.exception.BlogException;
import com.bit.blog.common.util.BeanTransUtils;
import com.bit.blog.mapper.BlogInfoMapper;
import com.bit.blog.pojo.dataobject.BlogInfo;
import com.bit.blog.pojo.request.AddBlogRequest;
import com.bit.blog.pojo.request.UpdateBlogRequest;
import com.bit.blog.pojo.response.BlogInfoResponse;
import com.bit.blog.service.BlogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,0);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);

        List<BlogInfoResponse> blogInfoResponses = blogInfos.stream()
                .map(blogInfo -> BeanTransUtils.trans(blogInfo))
                .collect(Collectors.toList());
       return blogInfoResponses;
    }

    @Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        return BeanTransUtils.trans(getBlogInfo(blogId));
    }

    @Override
    public BlogInfo getBlogInfo(Integer blogId) {
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL)
                .eq(BlogInfo::getId,blogId);
        return blogInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean addBlog(AddBlogRequest addBlogRequest) {
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(addBlogRequest, blogInfo);

        try {
            Integer result=blogInfoMapper.insert(blogInfo);
            if(result==1){
                return true;
            }
        } catch (Exception e) {
            log.error("博客插入失败吗,e:",e);
            throw new BlogException("添加博客失败，请稍后重试");
        }
        return false;
    }

    @Override
    public Boolean updateBlog(UpdateBlogRequest updateBlogRequest) {
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(updateBlogRequest, blogInfo);
        try {
            return blogInfoMapper.updateById(blogInfo)==1;
        } catch (Exception e) {
            log.error("更新错误,e:",e);
            throw new BlogException("更新错误");
        }
    }

    @Override
    public Boolean deleteBlog(Integer blogId) {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(Constants.BLOG_DELETE);
        try {
            return blogInfoMapper.updateById(blogInfo)==1;
        } catch (Exception e) {
            log.error("删除错误,e:",e);
            throw new BlogException("删除错误");
        }
    }

}
