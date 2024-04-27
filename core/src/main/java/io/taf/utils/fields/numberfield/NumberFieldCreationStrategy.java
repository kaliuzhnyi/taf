package io.taf.utils.fields.numberfield;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.NumberField;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.function.Function;

public class NumberFieldCreationStrategy implements FieldCreationStrategy<NumberField> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<NumberFieldConfig> markedAnnotation = NumberFieldConfig.class;

    @Nonnull
    @Override
    public NumberField createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new NumberField();
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
        component.setClearButtonVisible(annotation.clearButtonVisible());
        component.setStepButtonsVisible(annotation.stepButtonsVisible());

        // min, max, stem
        double min = 0;
        double max = 0;
        double step = 0;

        if (FieldUtils.isByteField(field)) {
            min = FieldUtils.isOutOfByteRange(Double.valueOf(annotation.min()).byteValue()) ? Byte.MIN_VALUE : Double.valueOf(annotation.min()).byteValue();
            max = FieldUtils.isOutOfByteRange(Double.valueOf(annotation.max()).byteValue()) ? Byte.MAX_VALUE : Double.valueOf(annotation.max()).byteValue();
            step = FieldUtils.isOutOfByteRange(Double.valueOf(annotation.step()).byteValue()) || Double.valueOf(annotation.step()).byteValue() <= 0 ? 1 : Double.valueOf(annotation.step()).byteValue();
        } else if (FieldUtils.isShortField(field)) {
            min = FieldUtils.isOutOfShortRange(Double.valueOf(annotation.min()).shortValue()) ? Short.MIN_VALUE : Double.valueOf(annotation.min()).shortValue();
            max = FieldUtils.isOutOfShortRange(Double.valueOf(annotation.max()).shortValue()) ? Short.MAX_VALUE : Double.valueOf(annotation.max()).shortValue();
            step = FieldUtils.isOutOfShortRange(Double.valueOf(annotation.step()).shortValue()) || Double.valueOf(annotation.step()).shortValue() <= 0 ? 1 : Double.valueOf(annotation.step()).shortValue();
        } else if (FieldUtils.isIntegerField(field)) {
            min = FieldUtils.isOutOfIntegerRange(Double.valueOf(annotation.min()).intValue()) ? Integer.MIN_VALUE : Double.valueOf(annotation.min()).intValue();
            max = FieldUtils.isOutOfIntegerRange(Double.valueOf(annotation.max()).intValue()) ? Integer.MAX_VALUE : Double.valueOf(annotation.max()).intValue();
            step = FieldUtils.isOutOfIntegerRange(Double.valueOf(annotation.step()).intValue()) || Double.valueOf(annotation.step()).intValue() <= 0 ? 1 : Double.valueOf(annotation.step()).intValue();
        } else if (FieldUtils.isLongField(field)) {
            min = FieldUtils.isOutOfLongRange(Double.valueOf(annotation.min()).longValue()) ? Long.MIN_VALUE : Double.valueOf(annotation.min()).longValue();
            max = FieldUtils.isOutOfLongRange(Double.valueOf(annotation.max()).longValue()) ? Long.MAX_VALUE : Double.valueOf(annotation.max()).longValue();
            step = FieldUtils.isOutOfLongRange(Double.valueOf(annotation.step()).longValue()) || Double.valueOf(annotation.step()).longValue() <= 0 ? 1 : Double.valueOf(annotation.step()).longValue();
        } else if (FieldUtils.isFloatField(field)) {
            min = FieldUtils.isOutOfFloatRange(Double.valueOf(annotation.min()).floatValue()) ? Float.MIN_VALUE : Double.valueOf(annotation.min()).floatValue();
            max = FieldUtils.isOutOfFloatRange(Double.valueOf(annotation.max()).floatValue()) ? Float.MAX_VALUE : Double.valueOf(annotation.max()).floatValue();
            step = FieldUtils.isOutOfFloatRange(Double.valueOf(annotation.step()).floatValue()) || Double.valueOf(annotation.step()).floatValue() <= 0 ? 0.01 : Double.valueOf(annotation.step()).floatValue();
        } else {
            min = FieldUtils.isOutOfDoubleRange(annotation.min()) ? Double.MIN_VALUE : annotation.min();
            max = FieldUtils.isOutOfDoubleRange(annotation.max()) ? Double.MAX_VALUE : annotation.max();
            step = FieldUtils.isOutOfDoubleRange(annotation.step()) || annotation.step() <= 0 ? 0.01 : annotation.step();
        }

        component.setMin(min);
        component.setMax(max);
        component.setStep(step);

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
