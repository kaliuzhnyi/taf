package io.taf.utils.fields.datapicker;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.function.Function;

public class DatePickerCreationStrategy implements FieldCreationStrategy<DatePicker> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<DatePickerConfig> markedAnnotation = DatePickerConfig.class;

    @Nonnull
    @Override
    public DatePicker createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new DatePicker();
        FieldUtils.resolveFieldId(component, field, annotation.id());

        if (!annotation.defaultValue().isBlank()) {
            component.setValue(LocalDate.parse(textResolver.apply(annotation.defaultValue())));
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
            component.setMin(LocalDate.parse(textResolver.apply(annotation.min())));
        }

        if (!annotation.max().isBlank()) {
            component.setMax(LocalDate.parse(textResolver.apply(annotation.max())));
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
