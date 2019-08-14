package com.xzixi.swagger2.plus.demo.config;

import com.xzixi.swagger2.plus.annotation.EnableSwagger2Plus;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnExpression("${swagger2.enable}==true")
@EnableSwagger2Plus
public class Swagger2Config {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 只显示添加@Api注解的类
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("swagger2-plus演示案例")
                        .version("1.0")
                        .build());
    }

}
