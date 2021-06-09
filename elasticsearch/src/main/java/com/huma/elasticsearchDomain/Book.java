package com.huma.elasticsearchDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author hudenian
 * @date 2021/6/5
 */
@ToString
@EqualsAndHashCode
@Data
@Document(indexName = "book_index")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @Field(store = true,index = false,type = FieldType.Keyword)
    private String id;

    /**
     * 图书名称
     */
    @Field(analyzer = "ik_smart",store = true,searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String bookName;

    /**
     * 图书作者
     */
    @Field(analyzer = "ik_smart",store = true,searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String author;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 图书类别
     */
    @Field(analyzer = "ik_smart",store = true,searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String bookType;

    /**
     * 备注
     */
    @Field(analyzer = "ik_smart",store = true,searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String bookDesc;


}
