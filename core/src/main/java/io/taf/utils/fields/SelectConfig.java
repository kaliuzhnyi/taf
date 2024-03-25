package io.taf.utils.fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectConfig {

    /**
     * Specifies the component's ID.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.Component#setId(String)}
     *
     * @return The ID of the component.
     */
    String id() default "";

}
