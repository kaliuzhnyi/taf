package io.taf.utils.fields.timepicker;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.timepicker.TimePicker;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.function.Function;

public class TimePickerCreationStrategy implements FieldCreationStrategy<TimePicker> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<TimePickerConfig> markedAnnotation = TimePickerConfig.class;

    @Nonnull
    @Override
    public TimePicker createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new TimePicker();
        FieldUtils.resolveFieldId(component, field, annotation.id());

        if (!annotation.defaultValue().isBlank()) {
            component.setValue(LocalTime.parse(textResolver.apply(annotation.defaultValue())));
        }

        component.setVisible(annotation.visible());
        component.setRequired(annotation.required());
        component.setRequiredIndicatorVisible(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        FieldUtils.resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setHelperText(textResolver.apply(annotation.helperText()));
        component.setPlaceholder(textResolver.apply(annotation.placeholder()));
        component.setTooltipText(textResolver.apply(annotation.tooltipText()));
        component.setPrefixComponent(new Span(textResolver.apply(annotation.prefixText())));
        component.setClearButtonVisible(annotation.clearButtonVisible());
        component.setAutoOpen(annotation.autoOpen());

        if (!annotation.min().isBlank()) {
            component.setMin(LocalTime.parse(textResolver.apply(annotation.min())));
        }

        if (!annotation.max().isBlank()) {
            component.setMax(LocalTime.parse(textResolver.apply(annotation.max())));
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
