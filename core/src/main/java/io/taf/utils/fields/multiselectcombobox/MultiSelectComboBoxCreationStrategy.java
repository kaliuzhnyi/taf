package io.taf.utils.fields.multiselectcombobox;

import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.function.SerializableFunction;
import io.taf.utils.fields.DefaultFilterConverter;
import io.taf.utils.fields.FieldCreationStrategy;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.function.Function;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MultiSelectComboBoxCreationStrategy implements FieldCreationStrategy<MultiSelectComboBox> {

    @Getter(onMethod_ = {@Nonnull, @Override})
    private final Class<MultiSelectComboBoxConfig> markedAnnotation = MultiSelectComboBoxConfig.class;

    @Nonnull
    @Override
    public MultiSelectComboBox createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        var annotation = field.getAnnotation(getMarkedAnnotation());

        var component = new MultiSelectComboBox<>();
        FieldUtils.resolveFieldId(component, field, annotation.id());

        // Data provider
        var dataProvider = annotation.dataProvider();
        var dataProviderInstance = FieldUtils.createInstance(dataProvider, DataProvider.class);

        var filterConvertor = annotation.filterConvertor();
        var filterConvertorInstance = filterConvertor.equals(void.class)
                ? new DefaultFilterConverter()
                : FieldUtils.createInstance(filterConvertor, SerializableFunction.class);

        component.setDataProvider(dataProviderInstance, filterConvertorInstance);

        // Default value
        FieldUtils.resolveFieldValue(component, annotation.defaultValueProvider());

        component.setVisible(annotation.visible());
        component.setRequiredIndicatorVisible(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        FieldUtils.resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setHelperText(textResolver.apply(annotation.helperText()));
        component.setPlaceholder(textResolver.apply(annotation.placeholder()));
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
