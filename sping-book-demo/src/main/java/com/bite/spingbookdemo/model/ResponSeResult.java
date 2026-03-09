package com.bite.spingbookdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponSeResult<T> {
    private  Integer total;
    private List<T> records;
    private PageRequest pageRequest;
}
