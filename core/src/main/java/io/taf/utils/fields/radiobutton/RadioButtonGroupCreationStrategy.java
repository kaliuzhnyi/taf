package io.taf.utils.fields.radiobutton;

import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.provider.DataProvider;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.function.Function;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RadioButtonGroupCreationStrategy implements FieldCreationStrategy<RadioButtonGroup> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<RadioButtonGroupConfig> markedAnnotation = RadioButtonGroupConfig.class;

    @Nonnull
    @Override
    public RadioButtonGroup createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new RadioButtonGroup<String>();
        FieldUtils.resolveFieldId(component, field, annotation.id());

        // Data provider
        var dataProvider = annotation.dataProvider();
        var dataProviderInstance = FieldUtils.createInstance(dataProvider, DataProvider.class);
        component.setDataProvider(dataProviderInstance);

        // Default value
        FieldUtils.resolveFieldValue(component, annotation.defaultValueProvider());

        component.setVisible(annotation.visible());
        component.setRequiredIndicatorVisible(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        FieldUtils.resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setHelperText(textResolver.apply(annotation.helperText()));
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