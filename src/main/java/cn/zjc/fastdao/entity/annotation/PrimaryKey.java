package cn.zjc.fastdao.entity.annotation;

import java.lang.annotation.*;

/**
 * @author zhangjinci
 * @version 2016/8/29 17:08
 * @function 主键注解
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryKey {

    String value() default "";

    boolean auto_increse() default false;  //是否自增,默认是
}
