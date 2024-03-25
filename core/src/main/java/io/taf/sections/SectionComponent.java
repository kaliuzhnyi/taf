package io.taf.sections;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public @interface SectionComponent {

    boolean isVisible() default true;

    String title() default "";

    String description() default "";

    String tooltip() default "";

}
