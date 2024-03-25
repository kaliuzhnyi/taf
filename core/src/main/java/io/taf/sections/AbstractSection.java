package io.taf.sections;

import io.taf.meta.MetaElement;
import io.taf.utils.MessageProvider;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractSection implements Section {

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private ApplicationContext applicationContext;

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private MessageProvider messageProvider;

    @Nonnull
    @Override
    public String getTitle() {
        return messageProvider.getValue(Section.super.getTitle(), "");
    }

    @Nullable
    @Override
    public String getDescription() {
        return Optional.ofNullable(Section.super.getDescription())
                .map(messageProvider::getValue)
                .orElse(null);
    }

    @Nullable
    @Override
    public String getTooltip() {
        return Optional.ofNullable(Section.super.getTooltip())
                .map(messageProvider::getValue)
                .orElseGet(this::getDescription);
    }

    @Nonnull
    @Override
    public List<MetaElement> getContent() {
        var content = new ArrayList<MetaElement>();
        var beans = applicationContext.getBeansWithAnnotation(AssignSection.class);
        beans.values().forEach(bean -> {
            var annotation = bean.getClass().getAnnotation(AssignSection.class);
            if (annotation.value().equals(getClass())) {
                content.add((MetaElement) bean);
            }
        });

        return content;
    }
}
