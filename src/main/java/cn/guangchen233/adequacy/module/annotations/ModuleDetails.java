package cn.guangchen233.adequacy.module.annotations;

import cn.guangchen233.adequacy.module.ModuleCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleDetails {
    String name();
    String description();
    ModuleCategory category();
    int defaultKeyBind() default 0;
    boolean defaultEnable() default false;
}
