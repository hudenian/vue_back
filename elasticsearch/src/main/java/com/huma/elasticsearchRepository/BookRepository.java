package com.huma.elasticsearchRepository;

import com.huma.elasticsearchDomain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/5
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, Long> {

    /**
     * 根据图书名称分页查找图书列表
     *
     * @param bookName 图书名称
     * @param pageable 分页信息
     * @return 分页图书列表
     */
    Page<Book> findByBookNameContaining(String bookName, Pageable pageable);

    /**
     * 根据图书名称精确查找图书列表
     *
     * @param bookName 图书名称
     * @return 图书列表
     */
    List<Book> findBookByBookName(String bookName);
}
