package com.io.hydralisk.annotate;

import java.lang.annotation.*;

/**
 * 免登录接口注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WithoutLogin {

}
