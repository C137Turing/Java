package com.bite.spingbookdemo.service;

import com.bite.spingbookdemo.enums.BookStatusEnum;
import com.bite.spingbookdemo.mapper.BookMapper;
import com.bite.spingbookdemo.model.BookInfo;
import com.bite.spingbookdemo.model.PageRequest;
import com.bite.spingbookdemo.model.ResponSeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public void addBook(BookInfo bookInfo) {
        bookMapper.insertBook(bookInfo);
    }

    public ResponSeResult<BookInfo> getListByPage(PageRequest pageRequest) {
        Integer count=bookMapper.selectBookCount();
        List<BookInfo> bookInfos=bookMapper.selectBookPage(pageRequest);
        for (BookInfo bookInfo : bookInfos) {
            bookInfo.setStatusCn(BookStatusEnum.getStatusByCode(bookInfo.getStatus()).getDesc());
        }

        return new ResponSeResult<>(count,bookInfos,pageRequest);
    }

    public BookInfo queryBookById(Integer bookId) {
        return bookMapper.queryBookById(bookId);
    }

    public void updateBook(BookInfo bookInfo) {
        bookMapper.updateBook(bookInfo);
    }

    public Integer batchDelete(List<Integer> ids) {
        return bookMapper.batchDelete(ids);
    }
}
