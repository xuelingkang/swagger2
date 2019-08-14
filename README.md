# swagger2-plus
>支持使用注解排除参数，基于springfox-swagger2:2.8.0版本<br>
<a href="https://blog.csdn.net/qq_35433926" target="_blank">博客主页</a>
## 使用方法
### 直接使用
项目已经发布到maven中央仓库，直接在pom.xml中引用即可
```xml
<dependencies>
    <dependency>
        <groupId>com.xzixi</groupId>
        <artifactId>swagger2-plus</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```
### 修改后使用
1. 下载项目<br>
打开git bash窗口，执行命令`git clone git@gitee.com:xuelingkang/swagger2.git`
2. 编译并安装到本地maven仓库<br>
进入工程目录，打开cmd窗口，执行命令`mvn clean install -Dmaven.test.skip=true`
3. 在自己的项目中引用
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
主要用到的类
```java
package com.xzixi.swagger2.plus.demo.controller;

import com.xzixi.swagger2.plus.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "demo", produces = "application/json; charset=UTF-8")
@Api(tags = "用户相关api")
public class UserController {

    @GetMapping
    @ApiOperation(value = "测试")
    public String test(User user) {
        System.out.println(user);
        return "随便返回点什么";
    }

}
```
```java
package com.xzixi.swagger2.plus.demo.entity;

import com.xzixi.swagger2.plus.annotation.IgnoreSwagger2Parameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户")
public class User {

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "部门")
    // 假设这个属性是我们不希望在swagger2文档页面显示的参数
    private Dept dept;

}
```
```java
package com.xzixi.swagger2.plus.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "部门")
public class Dept {

    @ApiModelProperty(value = "部门编号")
    private String deptNo;
    @ApiModelProperty(value = "部门名称")
    private String deptName;

}
```
* 先看一下不使用IgnoreSwagger2Parameter注解的效果
![不使用IgnoreSwagger2Parameter注解的效果](https://images.gitee.com/uploads/images/2019/0814/114929_385498c6_1672679.jpeg "1.jpg")
* 再看看使用IgnoreSwagger2Parameter注解的效果<br>
先修改User类
```java
package com.xzixi.swagger2.plus.demo.entity;

import com.xzixi.swagger2.plus.annotation.IgnoreSwagger2Parameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户")
public class User {

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "部门")
    @IgnoreSwagger2Parameter // 只需要添加注解就可以在文档中排除参数
    private Dept dept;

}
```
![使用IgnoreSwagger2Parameter注解的效果](https://images.gitee.com/uploads/images/2019/0814/114941_d87c0198_1672679.jpeg "2.jpg")
**详细使用方法请参考示例工程`swagger2-plus-demo`**
## 欢迎提出宝贵意见
如果我的代码对您有帮助，希望给我个star，谢谢！
