package io.taf.sections;

import io.taf.meta.MetaElement;
import io.taf.meta.MetaObject;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Section extends MetaObject {

    default boolean isVisible() {
        return Optional.ofNullable(getClass().getAnnotation(SectionComponent.class))
                .map(SectionComponent::isVisible)
                .orElse(true);
    }

    @Nonnull
    @Override
    default String getTitle() {
        return Optional.ofNullable(getClass().getAnnotation(SectionComponent.class))
                .map(SectionComponent::title)
                .orElseThrow();
    }

    @Nullable
    @Override
    default String getDescription() {
        return Optional.ofNullable(getClass().getAnnotation(SectionComponent.class))
                .map(SectionComponent::description)
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }

    @Nullable
    @Override
    default String getTooltip() {
        return Optional.ofNullable(getClass().getAnnotation(SectionComponent.class))
                .map(SectionComponent::tooltip)
                .filter(StringUtils::isNotBlank)
                .orElse(getDescription());
    }

    @Nonnull
    default List<MetaElement> getContent() {
        return new ArrayList<>();
    }

}
