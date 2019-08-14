package com.xzixi.swagger2.plus.annotation;

import java.lang.annotation.*;

/**
 * 设置swagger2文档需要忽略的参数
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSwagger2Parameter {
}
