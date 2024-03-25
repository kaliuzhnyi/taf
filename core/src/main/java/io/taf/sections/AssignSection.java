package io.taf.sections;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AssignSections.class)
public @interface AssignSection {

    Class<? extends Section> value();

}
