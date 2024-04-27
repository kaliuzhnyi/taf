package io.taf.utils.fields.datetimepicker;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.function.Function;

public class DateTimePickerCreationStrategy implements FieldCreationStrategy<DateTimePicker> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<DateTimePickerConfig> markedAnnotation = DateTimePickerConfig.class;

    @Nonnull
    @Override
    public DateTimePicker createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new DateTimePicker();
        FieldUtils.resolveFieldId(component, field, annotation.id());

        if (!annotation.defaultValue().isBlank()) {
            component.setValue(LocalDateTime.parse(textResolver.apply(annotation.defaultValue())));
        }

        component.setVisible(annotation.visible());
        component.setRequiredIndicatorVisible(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        FieldUtils.resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setHelperText(textResolver.apply(annotation.helperText()));
        component.setDatePlaceholder(textResolver.apply(annotation.datePlaceholder()));
        component.setTimePlaceholder(textResolver.apply(annotation.timePlaceholder()));
        component.setTooltipText(textResolver.apply(annotation.tooltipText()));
        component.setAutoOpen(annotation.autoOpen());

        if (!annotation.min().isBlank()) {
            component.setMin(LocalDateTime.parse(textResolver.apply(annotation.min())));
        }

        if (!annotation.max().isBlank()) {
            component.setMax(LocalDateTime.parse(textResolver.apply(annotation.max())));
        }

        FieldUtils.resolveFieldWidthFull(component, annotation.widthFull());
        FieldUtils.resolveFieldHeightFull(component, annotation.heightFull());
        FieldUtils.resolveFieldSizeFull(component, annotation.sizeFull());

        FieldUtils.resolveFieldWidth(component, annotation.width());
        FieldUtils.resolveFieldMinWidth(component, annotation.minWidth());
        FieldUtils.resolveFieldMaxWidth(component, annotation.maxWidth());

        FieldUtils.resolveFieldHeight(component, annotation.height());
        FieldUtils.resolveFieldMinHeight(component, annotation.minHeight());
        FieldUtils.resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;
    }
}
