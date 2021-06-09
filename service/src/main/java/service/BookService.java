package com.huma.service;

import com.huma.dto.PageDto;
import com.huma.dto.BookDto;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/5
 */
public interface BookService {

    /**
     * 新增图书
     *
     * @param bookDto 图书对象
     */
    void add(BookDto bookDto);

    /**
     * 批量添加图书
     *
     * @param bookDtoList 图书列表
     */
    void batchAdd(List<BookDto> bookDtoList);

    /**
     * 根据图书名称查找图书
     *
     * @param bookName 图书名称
     * @return 图书列表
     */
    List<BookDto> findByBookName(String bookName);

    /**
     * 根据图书名称分页查找图书
     *
     * @param bookDto 图书请求对像
     * @return 图书分页列表
     */
    PageDto<BookDto> pageFindByBookName(BookDto bookDto);

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     * @param word 关键词
     * @param bookDto 查询条件
     * @return 返回结果
     */
    Object singleTitle(String word, BookDto bookDto);
}
