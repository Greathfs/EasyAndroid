package com.hfs.easy.databus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author HuangFusheng
 * @date 2018/12/18
 * @description 在使用EventBus的类上面加上这个注解,表示使用EventBus
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {
}
