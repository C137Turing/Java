package com.bite.spingbookdemo.cotroller;

import com.bite.spingbookdemo.constant.Constants;
import com.bite.spingbookdemo.enums.ResultCodeEnum;
import com.bite.spingbookdemo.model.*;
import com.bite.spingbookdemo.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("addbook")
    public Result addBook(BookInfo bookInfo) {
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount() == null
                || bookInfo.getPrice() == null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus() == null
        ) {
            return Result.success("");
        }
        try {
            bookService.addBook(bookInfo);
            return Result.success("");
        } catch (Exception e) {
            return Result.fail("添加图书异常");
        }
    }

    @RequestMapping("/getListByPage")
    public Result<ResponSeResult<BookInfo>> getBookListByPage(PageRequest pageRequest, HttpSession session) {
        if(session.getAttribute(Constants.SEESION_USER_KEY)==null){
            return Result.unlogin();
        }
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SEESION_USER_KEY);
        if(userInfo == null||userInfo.getId()<=0) {
            return Result.unlogin();
        }

        ResponSeResult<BookInfo> result = bookService.getListByPage(pageRequest);
        return Result.success(result);
    }

    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId) {
        return bookService.queryBookById(bookId);
    }

    @RequestMapping("/updateBook")
    public Result updateBook(BookInfo bookInfo) {

        try {
            bookService.updateBook(bookInfo);
            return Result.success("");
        } catch (Exception e) {
            return Result.fail("修改图书发生异常");
        }
    }

    @RequestMapping("/batchDelete")
    public boolean batchDelete(@RequestParam List<Integer> ids) {
        try {
            bookService.batchDelete(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
