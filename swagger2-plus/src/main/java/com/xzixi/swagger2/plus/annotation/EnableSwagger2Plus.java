package com.xzixi.swagger2.plus.annotation;

import com.xzixi.swagger2.plus.extension.CustomizeModelAttributeParameterExpander;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * 开启swagger2-plus，同时会开启swagger2
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableSwagger2
@Import({CustomizeModelAttributeParameterExpander.class})
public @interface EnableSwagger2Plus {
}
