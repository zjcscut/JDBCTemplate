package cn.zjc.dao.transaction.annotation;

import java.lang.annotation.*;

/**
 * @author zhangjinci
 * @version 2016/8/29 16:43
 * @function 事务注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trans {
}
