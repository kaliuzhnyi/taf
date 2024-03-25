package io.taf.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.GenericTypeResolver;

import java.util.Objects;

@UtilityClass
public class GenericUtils {

    @SuppressWarnings("unchecked")
    public <T> Class<T> getType(Class<?> currentClass, Class<?> genericClass, int genericNumber) {
        return (Class<T>) Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(currentClass, genericClass))[genericNumber];
    }

}
