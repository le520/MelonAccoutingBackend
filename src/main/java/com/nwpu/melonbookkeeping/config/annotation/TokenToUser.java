package com.nwpu.melonbookkeeping.config.annotation;

import java.lang.annotation.*;
/**
 * 自定义Token转换为User注解
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToUser {
}
