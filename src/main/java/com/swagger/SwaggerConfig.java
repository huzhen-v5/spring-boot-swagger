package com.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket createRestApi() {
        logger.info("SwaggerConfig createRestApi 构建REST API");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.module.yapiDemo")) // Controller所在包(api文档扫描范围)
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        logger.info("SwaggerConfig apiInfo API信息");
        return new ApiInfoBuilder()
                .title("Swagger注解测试api文档")  // 标题
                .description("api文档")  // 描述
                .version("1.0") // 版本
                .contact(new Contact("huzhenv5", "url", "email")) // 联系人信息
                .build();
    }

}