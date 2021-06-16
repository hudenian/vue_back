package com.huma.annotation;

import java.lang.annotation.*;

/**
 * @author hudenian
 * @date 2021/6/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermissions {
    String[] value();
}
