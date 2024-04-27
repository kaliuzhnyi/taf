package io.taf.utils.fields;

import com.vaadin.flow.component.AbstractField;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "rawtypes"})
public class FieldCreationContext {

    @Getter(value = AccessLevel.PACKAGE, onMethod_ = {@Nonnull}, lazy = true)
    private final Reflections reflections = initReflections();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Set<FieldCreationStrategy> strategies = initStrategies();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Set<Class<? extends Annotation>> configAnnotations = initConfigAnnotations();

    @Nonnull
    public Optional<AbstractField> createField(@Nonnull Field field, @Nullable Function<String, String> textResolver) {
        return getStrategies().stream()
                .filter(fieldCreationStrategy -> fieldCreationStrategy.isSupported(field))
                .findFirst()
                .map(fieldCreationStrategy -> {
                    var tr = Optional.ofNullable(textResolver)
                            .orElseGet(fieldCreationStrategy::defaultTextResolver);
                    return fieldCreationStrategy.createField(field, tr);
                })
                .or(Optional::empty);
    }

    @Nonnull
    private Reflections initReflections() {
        var packageName = FieldCreationContext.class.getPackageName();
        var configuration = new ConfigurationBuilder().forPackage(packageName);
        return new Reflections(configuration);
    }

    @Nonnull
    private Set<FieldCreationStrategy> initStrategies() {

        var reflections = getReflections();
        var query = Scanners.SubTypes.of(FieldCreationStrategy.class).asClass();

        return reflections.get(query).stream()
                .map(clazz -> (Class<FieldCreationStrategy>) clazz)
                .map(cls -> FieldUtils.createInstance(cls, FieldCreationStrategy.class))
                .collect(Collectors.toSet());

    }

    @Nonnull
    private Set<Class<? extends Annotation>> initConfigAnnotations() {

        var reflections = getReflections();
        var query = Scanners.SubTypes.of(Annotation.class)
                .asClass()
                .filter(ReflectionUtils.withAnnotation(FieldCreationConfig.class))
                .map(cls -> (Class<? extends Annotation>) cls);

        return new HashSet<>(reflections.get(query));

    }

}
