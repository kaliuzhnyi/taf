package io.taf.utils.fields.passwordfield;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.function.Function;

public class PasswordFieldCreationStrategy implements FieldCreationStrategy<PasswordField> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<PasswordFieldConfig> markedAnnotation = PasswordFieldConfig.class;

    @Nonnull
    @Override
    public PasswordField createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new PasswordField();
        FieldUtils.resolveFieldId(component, field, annotation.id());
        component.setValue(annotation.defaultValue());
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
        component.setSuffixComponent(new Span(textResolver.apply(annotation.suffixText())));
        component.setMinLength(annotation.minLength());
        component.setMaxLength(annotation.maxLength());
        component.setClearButtonVisible(annotation.clearButtonVisible());

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
