package io.taf.utils.fields;

import com.vaadin.flow.component.AbstractField;
import jakarta.annotation.Nonnull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public interface FieldCreationStrategy<F extends AbstractField> {

    @Nonnull
    F createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver);

    @Nonnull
    Class<? extends Annotation> getMarkedAnnotation();

    default boolean isSupported(@Nonnull Field field) {
        return field.isAnnotationPresent(getMarkedAnnotation());
    }

    @Nonnull
    default F createField(@Nonnull Field field) {
        return createField(field, defaultTextResolver());
    }

    @Nonnull
    default Function<String, String> defaultTextResolver() {
        return s -> s;
    }

}
