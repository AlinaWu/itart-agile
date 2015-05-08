package top.itart.agile.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import top.itart.agile.common.annotation.generator.Strategy;
import top.itart.agile.common.annotation.generator.StrategyUUID;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

    public String generator() default "";

    public Class<? extends Strategy> strategy() default StrategyUUID.class;
}
