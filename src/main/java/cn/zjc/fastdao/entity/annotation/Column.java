package cn.zjc.fastdao.entity.annotation;

import java.lang.annotation.*;

/**
 * @author zhangjinci
 * @version 2016/8/29 17:05
 * @function 行注解
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    String value() default "";
}
