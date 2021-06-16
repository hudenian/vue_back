package com.huma.config;

import com.huma.common.constants.SysConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {
    static final String GROUP_NAME = "V1.0.0";
    static final String BASE_PACKAGE = "com.huma.controller";
    static final String TOKEN_KEY_DESC = "用户登录token";
    static final String LANGUAGE_KEY_DESC = "用户使用的语言";
    static final String TOKEN_KEY_TYPE = "string";
    static final String LANGUAGE_KEY_TYPE = "string";
    static final String TOKEN_PARAM_TYPE = "header";
    static final String LANGUAGE_PARAM_TYPE = "header";

   /* private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }*/

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder languagePar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(SysConstant.HEADER_TOKEN_KEY).description(TOKEN_KEY_DESC).modelRef(new ModelRef(TOKEN_KEY_TYPE))
                .parameterType(TOKEN_PARAM_TYPE).required(false).build();
        languagePar.name(SysConstant.HEADER_LANGUAGE_KEY).description(LANGUAGE_KEY_DESC).modelRef(new ModelRef(LANGUAGE_KEY_TYPE))
                .parameterType(LANGUAGE_PARAM_TYPE).required(false).build();
        pars.add(tokenPar.build());
        pars.add(languagePar.build());

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
                .globalOperationParameters(pars);
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
