package com.bit.blog.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.MessageSource;

@Data
public class UpdateBlogRequest {

    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;
}
