package io.taf.utils;

import io.taf.entity.Entity;
import lombok.experimental.UtilityClass;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EntityUtils {

    public <ENTITY extends Entity<? extends Serializable>> List<Field> getFields(Class<ENTITY> cls) {
        var fields = new ArrayList<Field>();
        ReflectionUtils.doWithFields(cls, fields::add);
        return fields;
    }

    public <ENTITY extends Entity<? extends Serializable>> List<Field> getAnnotatedFields(Class<ENTITY> cls, Class<? extends Annotation> annotation) {
        var fields = new ArrayList<Field>();
        ReflectionUtils.doWithFields(cls, field -> {
            if (field.isAnnotationPresent(annotation)) {
                fields.add(field);
            }
        }, ReflectionUtils.COPYABLE_FIELDS);
        return fields;
    }

}
