package com.huma.controller;

import com.huma.dto.BookDto;
import com.huma.dto.PageDto;
import com.huma.req.BookAddReq;
import com.huma.req.BookPageQueryReq;
import com.huma.req.BookQueryReq;
import com.huma.service.BookService;
import com.huma.vo.BookVo;
import com.huma.vo.PageVo;
import com.huma.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/5
 */
@Slf4j
@RestController
@Api(tags = "图书管理关接口")
@RequestMapping(value = "book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("add")
    @ApiOperation(value = "新增图书", notes = "新增图书")
    public ResponseVo<?> add(@RequestBody @Valid BookAddReq bookAddReq) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(bookAddReq, bookDto);
        bookService.add(bookDto);
        return ResponseVo.createSuccess();
    }

    @PostMapping("findByBookName")
    @ApiOperation(value = "根据图书名称查找", notes = "根据图书名称查找")
    public ResponseVo<List<BookVo>> findByBookName(@RequestBody @Valid BookQueryReq bookQueryReq) {
        List<BookDto> bookDtoList = bookService.findByBookName(bookQueryReq.getBookName());
        List<BookVo> bookVoList = new ArrayList<>();
        bookDtoList.forEach(item -> {
            BookVo bookVo = new BookVo();
            bookVo.setId(item.getId());
            bookVo.setBookName(item.getBookName());
            bookVo.setAuthor(item.getAuthor());
            bookVo.setPrice(item.getPrice());
            bookVo.setBookType(item.getBookType());
            bookVo.setBookDesc(item.getBookDesc());
            bookVoList.add(bookVo);
        });
        return ResponseVo.createSuccess(bookVoList);
    }

    @PostMapping("pageFindByBookName")
    @ApiOperation(value = "根据图书名称分页查找", notes = "根据图书名称分页查找")
    public ResponseVo<PageVo<BookVo>> pageFindByBookName(@RequestBody @Valid BookPageQueryReq bookPageQueryReq) {
        BookDto bookDto = new BookDto();
        bookDto.setPageNum(bookPageQueryReq.getPageNum().longValue());
        bookDto.setPageSize(bookPageQueryReq.getPageSize().longValue());
        bookDto.setBookName(bookPageQueryReq.getBookName());
        PageDto<BookDto> bookDtoPageDto = bookService.pageFindByBookName(bookDto);

        PageVo<BookVo> pageVo = getBookVoPageVo(bookDtoPageDto);
        return ResponseVo.createSuccess(pageVo);
    }

    private PageVo<BookVo> getBookVoPageVo(PageDto<BookDto> bookDtoPageDto) {
        PageVo<BookVo> pageVo = new PageVo<>();
        List<BookVo> bookVoList = new ArrayList<>();
        bookDtoPageDto.getItems().forEach(i -> {
            BookVo bookVo = new BookVo();
            bookVo.setId(i.getId());
            bookVo.setBookName(i.getBookName());
            bookVo.setAuthor(i.getAuthor());
            bookVo.setPrice(i.getPrice());
            bookVo.setBookType(i.getBookType());
            bookVo.setBookDesc(i.getBookDesc());
            bookVoList.add(bookVo);
        });
        pageVo.setItems(bookVoList);
        pageVo.setTotal(bookDtoPageDto.getTotal());
        pageVo.setCurrent(bookDtoPageDto.getCurrent());
        pageVo.setSize(bookDtoPageDto.getSize());
        return pageVo;
    }
}
