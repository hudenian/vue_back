package com.huma.service.Impl;

import com.huma.dto.BookDto;
import com.huma.dto.PageDto;
import com.huma.elasticsearchDomain.Book;
import com.huma.elasticsearchRepository.BookRepository;
import com.huma.service.BookService;
import com.huma.utils.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/5
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookRepository bookRepository;

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void add(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        bookRepository.save(book);
    }

    @Override
    public void batchAdd(List<BookDto> bookDtoList) {

    }

    @Override
    public List<BookDto> findByBookName(String bookName) {
        List<Book> bookList = bookRepository.findBookByBookName(bookName);
        List<BookDto> bookDtoList = BeanCopyUtil.copyListProperties(bookList, BookDto::new);
        return bookDtoList;
    }

    @Override
    public PageDto<BookDto> pageFindByBookName(BookDto bookDto) {
        Pageable pageable = PageRequest.of(bookDto.getPageNum() - 1, bookDto.getPageSize());
        Page<Book> bookPage = bookRepository.findByBookNameContaining(bookDto.getBookName(), pageable);
        List<BookDto> bookDtoList = BeanCopyUtil.copyListProperties(bookPage.getContent(), BookDto::new);

        PageDto<BookDto> bookDtoPage = new PageDto<>();
        bookDtoPage.setTotal(bookPage.getTotalElements());
        bookDtoPage.setCurrent(bookDto.getPageNum());
        bookDtoPage.setSize(bookDto.getPageSize());
        bookDtoPage.setItems(bookDtoList);
        return bookDtoPage;
    }

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    @Override
    public Object singleTitle(String word, BookDto bookDto) {
        Pageable pageable = PageRequest.of(bookDto.getPageNum() - 1, bookDto.getPageSize());
        //使用queryStringQuery完成单字符串查询
        Query query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word).defaultField("bookName")).withPageable(pageable)
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC)).withHighlightFields(new HighlightBuilder.Field(word))
                .build();
        SearchHits<Book> bookSearchHits = elasticsearchTemplate.search(query, Book.class);
        bookSearchHits.forEach(item -> System.out.println(item.toString()));
        return null;
    }

}
