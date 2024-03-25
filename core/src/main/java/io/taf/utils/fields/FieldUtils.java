package io.taf.utils.fields;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * This utility class provides factory methods for creating Vaadin components based on field annotations.
 * It simplifies the creation of data-bound UI components by leveraging Java reflection and annotation processing.
 *
 * @author Ivan Kaliuzhnyi
 */
@UtilityClass
@SuppressWarnings("unused")
public class FieldUtils {

    public final String FIELD_ID_DELIMITER = "-";
    public final String FIELD_ID_PREFIX = "field";

    /**
     * Creates a Vaadin component based on the annotated field. This method checks the field's
     * annotations and type to determine which component to create and how to configure it.
     *
     * @param field the field to create the component for
     * @return the created Vaadin component, or null if the field type is not supported
     */
    @SuppressWarnings("rawtypes")
    public Optional<AbstractField> createField(@Nonnull Field field) {
        return createField(field, s -> s);
    }

    /**
     * Creates a Vaadin component based on the annotated field. This method checks the field's
     * annotations and type to determine which component to create and how to configure it.
     *
     * @param field        the field to create the component for
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return the created Vaadin component, or null if the field type is not supported
     */
    @SuppressWarnings("rawtypes")
    public Optional<AbstractField> createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        AbstractField component;

        if (isStringField(field) && field.isAnnotationPresent(TextFieldConfig.class)) {
            component = createTextField(field, textResolver);
        } else if (isStringField(field) && field.isAnnotationPresent(TextAreaConfig.class)) {
            component = createTextArea(field, textResolver);
        } else if (isNumberField(field) && field.isAnnotationPresent(NumberFieldConfig.class)) {
            component = createNumberField(field, textResolver);
        } else if (isBooleanField(field) && field.isAnnotationPresent(CheckboxConfig.class)) {
            component = createCheckbox(field, textResolver);
        } else if (isLocalDateField(field) && field.isAnnotationPresent(DatePickerConfig.class)) {
            component = createDatePicker(field, textResolver);
        } else if (isLocalTimeField(field) && field.isAnnotationPresent(TimePickerConfig.class)) {
            component = createTimePicker(field, textResolver);
        } else if (isLocalDateTimeField(field) && field.isAnnotationPresent(DateTimePickerConfig.class)) {
            component = createDateTimePicker(field, textResolver);
        } else if ((isArrayField(field) || isListField(field))
                && field.isAnnotationPresent(SelectConfig.class)) {
            component = createSelect(field, textResolver);
        } else {
            component = createTextField(field, textResolver);
        }

        return Optional.ofNullable(component);

    }

    /**
     * Creates a TextField component configured based on the TextFieldConfig annotation present on the field.
     *
     * @param field        the field with a TextFieldConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured TextField instance
     */
    public TextField createTextField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(TextFieldConfig.class);

        var component = new TextField();
        resolveFieldId(component, field, annotation.id());
        component.setValue(annotation.defaultValue());
        component.setVisible(annotation.visible());
        component.setRequired(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setHelperText(textResolver.apply(annotation.helperText()));
        component.setPlaceholder(textResolver.apply(annotation.placeholder()));
        component.setTooltipText(textResolver.apply(annotation.tooltipText()));
        component.setPrefixComponent(new Span(textResolver.apply(annotation.prefixText())));
        component.setSuffixComponent(new Span(textResolver.apply(annotation.suffixText())));
        component.setMinLength(annotation.minLength());
        component.setMaxLength(annotation.maxLength());
        component.setClearButtonVisible(annotation.clearButtonVisible());

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;
    }

    /**
     * Creates a TextArea component configured based on the TextAreaConfig annotation present on the field.
     *
     * @param field        the field with a TextAreaConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured TextArea instance
     */
    public TextArea createTextArea(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(TextAreaConfig.class);

        var component = new TextArea();
        resolveFieldId(component, field, annotation.id());
        component.setValue(annotation.defaultValue());
        component.setVisible(annotation.visible());
        component.setRequired(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setHelperText(textResolver.apply(annotation.helperText()));
        component.setPlaceholder(textResolver.apply(annotation.placeholder()));
        component.setTooltipText(textResolver.apply(annotation.tooltipText()));
        component.setPrefixComponent(new Span(textResolver.apply(annotation.prefixText())));
        component.setSuffixComponent(new Span(textResolver.apply(annotation.suffixText())));
        component.setMinLength(annotation.minLength());
        component.setMaxLength(annotation.maxLength());
        component.setClearButtonVisible(annotation.clearButtonVisible());

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;
    }

    /**
     * Creates a NumberField component configured based on the NumberFieldConfig annotation present on the field.
     *
     * @param field        the field with a NumberFieldConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured NumberField instance
     */
    public NumberField createNumberField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(NumberFieldConfig.class);

        var component = new NumberField();
        resolveFieldId(component, field, annotation.id());
        component.setValue(annotation.defaultValue());
        component.setVisible(annotation.visible());
        component.setRequired(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
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

        if (isByteField(field)) {
            min = isOutOfByteRange(annotation.min()) ? Byte.MIN_VALUE : annotation.min();
            max = isOutOfByteRange(annotation.max()) ? Byte.MAX_VALUE : annotation.max();
            step = isOutOfByteRange(annotation.step()) ? 1 : annotation.step();
        } else if (isShortField(field)) {
            min = isOutOfShortRange(annotation.min()) ? Short.MIN_VALUE : annotation.min();
            max = isOutOfShortRange(annotation.max()) ? Short.MAX_VALUE : annotation.max();
            step = isOutOfShortRange(annotation.step()) ? 1 : annotation.step();
        } else if (isIntegerField(field)) {
            min = isOutOfIntegerRange(annotation.min()) ? Integer.MIN_VALUE : annotation.min();
            max = isOutOfIntegerRange(annotation.max()) ? Integer.MAX_VALUE : annotation.max();
            step = isOutOfIntegerRange(annotation.step()) ? 1 : annotation.step();
        } else if (isLongField(field)) {
            min = isOutOfLongRange(annotation.min()) ? Long.MIN_VALUE : annotation.min();
            max = isOutOfLongRange(annotation.max()) ? Long.MAX_VALUE : annotation.max();
            step = isOutOfLongRange(annotation.step()) ? 1 : annotation.step();
        } else if (isFloatField(field)) {
            min = isOutOfFloatRange(annotation.min()) ? Float.MIN_VALUE : annotation.min();
            max = isOutOfFloatRange(annotation.max()) ? Float.MAX_VALUE : annotation.max();
            step = isOutOfFloatRange(annotation.step()) ? 0.01 : annotation.step();
        } else if (isDoubleField(field)) {
            min = isOutOfDoubleRange(annotation.min()) ? Double.MIN_VALUE : annotation.min();
            max = isOutOfDoubleRange(annotation.max()) ? Double.MAX_VALUE : annotation.max();
            step = isOutOfDoubleRange(annotation.step()) ? 0.01 : annotation.step();
        }

        component.setMin(min);
        component.setMax(max);
        component.setStep(step);

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;
    }

    /**
     * Creates a Checkbox component configured based on the CheckboxConfig annotation present on the field.
     *
     * @param field        the field with a CheckboxConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured Checkbox instance
     */
    public Checkbox createCheckbox(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(CheckboxConfig.class);

        var component = new Checkbox();
        resolveFieldId(component, field, annotation.id());
        component.setValue(annotation.defaultValue());
        component.setVisible(annotation.visible());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
        component.setTooltipText(textResolver.apply(annotation.tooltipText()));

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;
    }

    /**
     * Creates a DatePicker component configured based on the DatePickerConfig annotation present on the field.
     *
     * @param field        the field with a DatePickerConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured DatePicker instance
     */
    public DatePicker createDatePicker(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(DatePickerConfig.class);

        var component = new DatePicker();
        resolveFieldId(component, field, annotation.id());

        if (!annotation.defaultValue().isBlank()) {
            component.setValue(LocalDate.parse(textResolver.apply(annotation.defaultValue())));
        }

        component.setVisible(annotation.visible());
        component.setRequired(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
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

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;

    }

    /**
     * Creates a TimePicker component configured based on the TimePickerConfig annotation present on the field.
     *
     * @param field        the field with a TimePickerConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured TimePicker instance
     */
    public TimePicker createTimePicker(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(TimePickerConfig.class);

        var component = new TimePicker();
        resolveFieldId(component, field, annotation.id());

        if (!annotation.defaultValue().isBlank()) {
            component.setValue(LocalTime.parse(textResolver.apply(annotation.defaultValue())));
        }

        component.setVisible(annotation.visible());
        component.setRequired(annotation.required());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
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

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;

    }

    /**
     * Creates a DateTimePicker component configured based on the DateTimePickerConfig annotation present on the field.
     *
     * @param field        the field with a DateTimePickerConfig annotation
     * @param textResolver a function that resolves text for any labels or placeholders
     * @return a configured DateTimePicker instance
     */
    public DateTimePicker createDateTimePicker(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {

        var annotation = field.getAnnotation(DateTimePickerConfig.class);

        var component = new DateTimePicker();
        resolveFieldId(component, field, annotation.id());

        if (!annotation.defaultValue().isBlank()) {
            component.setValue(LocalDateTime.parse(textResolver.apply(annotation.defaultValue())));
        }

        component.setVisible(annotation.visible());
        component.setEnabled(annotation.enabled());
        component.setReadOnly(annotation.readOnly());
        resolveFieldLabel(component, field, textResolver.apply(annotation.label()));
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

        resolveFieldWidthFull(component, annotation.widthFull());
        resolveFieldHeightFull(component, annotation.heightFull());
        resolveFieldSizeFull(component, annotation.sizeFull());

        resolveFieldWidth(component, annotation.width());
        resolveFieldMinWidth(component, annotation.minWidth());
        resolveFieldMaxWidth(component, annotation.maxWidth());

        resolveFieldHeight(component, annotation.height());
        resolveFieldMinHeight(component, annotation.minHeight());
        resolveFieldMaxHeight(component, annotation.maxHeight());

        return component;

    }

    public Select createSelect(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        return null;
    }

    public boolean isArrayField(@Nonnull Field field) {
        return field.getType().isArray();
    }

    public boolean isListField(@Nonnull Field field) {
        return isFieldType(field, List.class);
    }

    /**
     * Checks if the specified field is of String type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a String type, false otherwise
     */
    public boolean isStringField(@Nonnull Field field) {
        return isFieldType(field, String.class);
    }

    /**
     * Checks if the specified field is of Boolean type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Boolean type, false otherwise
     */
    public boolean isBooleanField(@Nonnull Field field) {
        return isFieldType(field, boolean.class, Boolean.class);
    }

    /**
     * Checks if the specified field is of LocalDate type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a LocalDate type, false otherwise
     */
    public boolean isLocalDateField(@Nonnull Field field) {
        return isFieldType(field, LocalDate.class);
    }

    /**
     * Checks if the specified field is of LocalTime type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a LocalTime type, false otherwise
     */
    public boolean isLocalTimeField(@Nonnull Field field) {
        return isFieldType(field, LocalTime.class);
    }

    /**
     * Checks if the specified field is of LocalDateTime type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a LocalDateTime type, false otherwise
     */
    public boolean isLocalDateTimeField(@Nonnull Field field) {
        return isFieldType(field, LocalDateTime.class);
    }

    /**
     * Checks if the specified field is of Number type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Number type, false otherwise
     */
    public boolean isNumberField(@Nonnull Field field) {
        return isFieldType(field, Number.class, byte.class, short.class, int.class, long.class, float.class, double.class);
    }

    /**
     * Checks if the specified field is of Byte type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Byte type, false otherwise
     */
    public boolean isByteField(@Nonnull Field field) {
        return isFieldType(field, byte.class, Byte.class);
    }

    /**
     * Checks if the specified field is of Short type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Short type, false otherwise
     */
    public boolean isShortField(@Nonnull Field field) {
        return isFieldType(field, short.class, Short.class);
    }

    /**
     * Checks if the specified field is of Integer type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is an Integer type, false otherwise
     */
    public boolean isIntegerField(@Nonnull Field field) {
        return isFieldType(field, int.class, Integer.class);
    }

    /**
     * Checks if the specified field is of Long type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Long type, false otherwise
     */
    public boolean isLongField(@Nonnull Field field) {
        return isFieldType(field, long.class, Long.class);
    }

    /**
     * Checks if the specified field is of Float type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Float type, false otherwise
     */
    public boolean isFloatField(@Nonnull Field field) {
        return isFieldType(field, float.class, Float.class);
    }

    /**
     * Checks if the specified field is of Double type.
     *
     * @param field the field to check, must not be null
     * @return true if the field is a Double type, false otherwise
     */
    public boolean isDoubleField(@Nonnull Field field) {
        return isFieldType(field, double.class, Double.class);
    }


    /**
     * Checks if the given value is out of the permissible byte range.
     *
     * @param value the value to check
     * @return true if the value is less than Byte.MIN_VALUE or greater than Byte.MAX_VALUE, false otherwise
     */
    public boolean isOutOfByteRange(double value) {
        return isOutOfRange(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Checks if the given value is out of the permissible short range.
     *
     * @param value the value to check
     * @return true if the value is less than Short.MIN_VALUE or greater than Short.MAX_VALUE, false otherwise
     */
    public boolean isOutOfShortRange(double value) {
        return isOutOfRange(value, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * Checks if the given value is out of the permissible integer range.
     *
     * @param value the value to check
     * @return true if the value is less than Integer.MIN_VALUE or greater than Integer.MAX_VALUE, false otherwise
     */
    public boolean isOutOfIntegerRange(double value) {
        return isOutOfRange(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Checks if the given value is out of the permissible long range.
     *
     * @param value the value to check
     * @return true if the value is less than Long.MIN_VALUE or greater than Long.MAX_VALUE, false otherwise
     */
    public boolean isOutOfLongRange(double value) {
        return isOutOfRange(value, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Checks if the given value is out of the permissible float range.
     *
     * @param value the value to check
     * @return true if the value is less than Float.MIN_VALUE or greater than Float.MAX_VALUE, false otherwise
     */
    public boolean isOutOfFloatRange(double value) {
        return isOutOfRange(value, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    /**
     * Checks if the given value is out of the permissible double range.
     *
     * @param value the value to check
     * @return true if the value is less than Double.MIN_VALUE or greater than Double.MAX_VALUE, false otherwise
     */
    public boolean isOutOfDoubleRange(double value) {
        return isOutOfRange(value, Double.MIN_VALUE, Double.MAX_VALUE);
    }


    /**
     * Determines if the type of the given field matches any of the provided classes.
     *
     * @param field   the field whose type is to be checked
     * @param classes the classes to compare against the field's type
     * @return true if the field's type is assignable from any of the provided classes, false otherwise
     */
    public boolean isFieldType(@Nonnull Field field, @Nonnull Class<?>... classes) {
        for (var cls : classes) {
            if (cls.isAssignableFrom(field.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given value is out of the specified range.
     *
     * @param value the value to check
     * @param min   the minimum value of the range
     * @param max   the maximum value of the range
     * @return true if the value is less than min or greater than max, false otherwise
     */
    public boolean isOutOfRange(double value, double min, double max) {
        return value < min || value > max;
    }


    /**
     * Resolves the ID to be used for a field component in the UI. If a default value is provided
     * and is non-empty, it will be used as the ID. Otherwise, the ID is constructed using a predefined
     * prefix, a delimiter, and the field's name converted to lower case.
     *
     * @param component    The component implementing {@link Component} whose width is to be adjusted.
     * @param field        The field for which the ID is being resolved. Must not be null.
     * @param defaultValue The default ID value to use if provided. Can be null, in which case the ID
     *                     will be generated based on the field's name.
     */
    private void resolveFieldId(@Nonnull Component component, @Nonnull Field field, @Nullable String defaultValue) {
        var id = Optional.ofNullable(defaultValue)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> FIELD_ID_PREFIX.concat(FIELD_ID_DELIMITER).concat(field.getName().toLowerCase()));
        component.setId(id);
    }

    /**
     * Resolves the label to be used for a field component in the UI. If a default value is provided
     * and is non-empty, it will be used as the label. Otherwise, the field's name will be used as the label.
     *
     * @param component    The component implementing {@link HasLabel} whose width is to be adjusted.
     * @param field        The field for which the label is being resolved. Must not be null.
     * @param defaultValue The default label value to use if provided. Can be null, in which case the field's
     *                     name will be used as the label.
     */
    private void resolveFieldLabel(@Nonnull HasLabel component, @Nonnull Field field, @Nullable String defaultValue) {
        var label = Optional.ofNullable(defaultValue)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> {
                    var worlds = StringUtils.splitByCharacterTypeCamelCase(field.getName());
                    var genLabel = StringUtils.join(worlds, " ");
                    return StringUtils.capitalize(genLabel);
                });
        component.setLabel(label);
    }

    /**
     * Sets the component's width to 100% if the given value is true.
     *
     * @param component The component implementing {@link HasSize} whose width is to be adjusted.
     * @param value     If true, sets the component's width to 100%.
     */
    private void resolveFieldWidthFull(@Nonnull HasSize component, boolean value) {
        if (value) {
            component.setWidthFull();
        }
    }

    /**
     * Sets the component's width based on the given value. The width is set only if the provided
     * string value is not blank, allowing for dynamic layout adjustments based on configuration or context.
     *
     * @param component The component implementing {@link HasSize} interface whose width is to be set.
     *                  The component must not be null.
     * @param value     The width value as a string, which can include units (e.g., "50%", "100px").
     *                  If the value is blank, the width is not adjusted.
     */
    private void resolveFieldWidth(@Nonnull HasSize component, String value) {
        if (StringUtils.isNotBlank(value)) {
            component.setWidth(value);
        }
    }

    /**
     * Sets the component's minimum width based on the given value. The minimum width is applied only
     * if the provided string value is not blank. This allows components to maintain a minimum width
     * dynamically, ensuring content readability and layout consistency.
     *
     * @param component The component implementing {@link HasSize} interface whose minimum width is to be set.
     *                  The component must not be null.
     * @param value     The minimum width value as a string, which can include units (e.g., "200px", "30em").
     *                  If the value is blank, the minimum width is not adjusted.
     */
    private void resolveFieldMinWidth(@Nonnull HasSize component, String value) {
        if (StringUtils.isNotBlank(value)) {
            component.setMinWidth(value);
        }
    }

    /**
     * Sets the component's minimum width based on the given value. The minimum width is applied only
     * if the provided string value is not blank. This allows components to maintain a minimum width
     * dynamically, ensuring content readability and layout consistency.
     *
     * @param component The component implementing {@link HasSize} interface whose minimum width is to be set.
     *                  The component must not be null.
     * @param value     The minimum width value as a string, which can include units (e.g., "200px", "30em").
     *                  If the value is blank, the minimum width is not adjusted.
     */
    private void resolveFieldMaxWidth(@Nonnull HasSize component, String value) {
        if (StringUtils.isNotBlank(value)) {
            component.setMaxWidth(value);
        }
    }

    /**
     * Sets the component's height to 100% if the given value is true.
     *
     * @param component The component implementing {@link HasSize} whose height is to be adjusted.
     * @param value     If true, sets the component's height to 100%.
     */
    private void resolveFieldHeightFull(@Nonnull HasSize component, boolean value) {
        if (value) {
            component.setHeightFull();
        }
    }

    /**
     * Sets the component's height based on the given value. The height is set only if the provided
     * string value is not blank, allowing for dynamic layout adjustments. This method is useful for
     * configuring the component's height to specific values as needed for the UI design.
     *
     * @param component The component implementing {@link HasSize} interface whose height is to be set.
     *                  The component must not be null.
     * @param value     The height value as a string, which can include units (e.g., "150px", "50%").
     *                  If the value is blank, the height is not adjusted.
     */
    private void resolveFieldHeight(@Nonnull HasSize component, String value) {
        if (StringUtils.isNotBlank(value)) {
            component.setHeight(value);
        }
    }

    /**
     * Sets the component's minimum height based on the given value. The minimum height is applied only
     * if the provided string value is not blank. This allows components to maintain a minimum height
     * dynamically, ensuring content visibility and preserving layout integrity.
     *
     * @param component The component implementing {@link HasSize} interface whose minimum height is to be set.
     *                  The component must not be null.
     * @param value     The minimum height value as a string, which can include units (e.g., "100px", "20vh").
     *                  If the value is blank, the minimum height is not adjusted.
     */
    private void resolveFieldMinHeight(@Nonnull HasSize component, String value) {
        if (StringUtils.isNotBlank(value)) {
            component.setMinHeight(value);
        }
    }

    /**
     * Sets the component's maximum height based on the given value. The maximum height is applied only
     * if the provided string value is not blank, enabling the enforcement of layout boundaries dynamically
     * based on configuration or specific UI requirements. This is particularly useful for ensuring that
     * components do not exceed a certain height in responsive layouts.
     *
     * @param component The component implementing {@link HasSize} interface whose maximum height is to be set.
     *                  The component must not be null.
     * @param value     The maximum height value as a string, which can include units (e.g., "400px", "90vh").
     *                  If the value is blank, the maximum height is not adjusted.
     */
    private void resolveFieldMaxHeight(@Nonnull HasSize component, String value) {
        if (StringUtils.isNotBlank(value)) {
            component.setMaxHeight(value);
        }
    }

    /**
     * Sets both the width and height of the component to 100% if the given value is true.
     * This method provides a quick way to make the component occupy all available space.
     *
     * @param component The component implementing {@link HasSize} whose size is to be adjusted.
     * @param value     If true, sets both the width and height of the component to 100%.
     */
    private void resolveFieldSizeFull(@Nonnull HasSize component, boolean value) {
        if (value) {
            component.setSizeFull();
        }
    }

}
