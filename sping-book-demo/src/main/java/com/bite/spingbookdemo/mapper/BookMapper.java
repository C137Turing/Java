package com.bite.spingbookdemo.mapper;

import com.bite.spingbookdemo.model.BookInfo;
import com.bite.spingbookdemo.model.PageRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.awt.print.Book;
import java.util.List;

@Mapper
public interface BookMapper {

    @Insert("insert into book_info (book_name,author,count,price,publish)"+
             "values  (#{bookName},#{author},#{count},#{price},#{publish})")
    Integer insertBook(BookInfo bookInfo);

    @Select("select *from book_info where `status`!=0 limit #{offset},#{pageSize}")
    List<BookInfo> selectBookPage(PageRequest pageRequest);

    @Select("select count(1) from book_info where `status`!=0")
    Integer selectBookCount();

    @Select("select *from book_info where `status` !=0 and id=#{bookId}")
    BookInfo queryBookById(Integer bookId);

    Integer updateBook(BookInfo bookInfo);

    Integer batchDelete(List<Integer> ids);
}
