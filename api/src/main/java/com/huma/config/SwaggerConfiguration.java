package com.huma.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {
    static final String GROUP_NAME = "V1.0.0";
    static final String BASE_PACKAGE = "com.huma.controller";

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                // api info
                .apiInfo(apiInfo())
                // group name
                .groupName(GROUP_NAME)
                // select
                .select()
                // package
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                // path
                .paths(PathSelectors.any()).build()
                // 赋予插件体系
                .extensions(openApiExtensionResolver.buildExtensions(GROUP_NAME));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("elasticsearch接口文档")
                // 创建人信息
                .contact(new Contact("hudenian", "", "huma@163.com"))
                // 版本号
                .version("1.0.0")
                // 描述
                .description("本文档主要提供vueback后台的接口功能，用于描述清楚调用方需要传递的参数信息及响应的数据。").build();
    }
}
