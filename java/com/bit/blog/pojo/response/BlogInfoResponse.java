package com.bit.blog.pojo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bit.blog.common.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BlogInfoResponse {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    //private Integer deleteFlag;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public String getContent() {
        if (content == null) {
            return "";
        }
        if (content.length() <= 50) {
            return content;
        }
        return content.substring(0, 50) + "...";
    }
//    public String getCreateTime() {
//        return DateUtils.dateFormat(createTime);
//    }
}
