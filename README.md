# swagger2-plus
支持使用注解排除参数，基于swagger2:2.8.0版本
## 使用方法
### maven依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.xzixi</groupId>
        <artifactId>swagger2-plus</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```
### 配置类
**所有swagger2的配置都不用变，只需要将注解替换掉**
```java
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
@EnableSwagger2Plus // 只需要将@EnableSwagger2替换成@EnableSwagger2Plus即可
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
```
### IgnoreSwagger2Parameter注解
先看一下不使用IgnoreSwagger2Parameter注解的效果
再看看使用IgnoreSwagger2Parameter注解的效果
