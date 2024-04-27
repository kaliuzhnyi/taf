package io.taf.utils.fields.checkbox;

import com.vaadin.flow.component.checkbox.Checkbox;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.function.Function;

public class CheckBoxCreationStrategy implements FieldCreationStrategy<Checkbox> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<CheckBoxConfig> markedAnnotation = CheckBoxConfig.class;

    @Nonnull
    @Override
    public Checkbox createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new Checkbox();
        FieldUtils.resolveFieldId(component, field, annotation.id());
        component.setValue(annotation.defaultValue());
        component.setVisible(annotation.visible());
        component.setRequiredIndicatorVisible(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        FieldUtils.resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setTooltipText(textResolver.apply(annotation.tooltipText()));

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
