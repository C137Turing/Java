package com.bite.spingbookdemo.model;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

@Data
public class PageRequest {
    private Integer currentPage = 1;
    private Integer pageSize = 10;
    private Integer offset;

    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }

}
