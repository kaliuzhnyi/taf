package io.taf.utils.fields.timepicker;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.shared.HasClearButton;
import com.vaadin.flow.component.shared.HasPrefix;
import com.vaadin.flow.component.shared.HasTooltip;
import com.vaadin.flow.component.timepicker.TimePicker;
import io.taf.utils.fields.FieldCreationConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalTime;

/**
 * Annotation to configure a {@link TimePicker} component in a Vaadin application.
 * Enables declarative configuration of a checkbox, specifying properties such as ID, initial checked state, visibility,
 * and other attributes related to its appearance and behavior.
 * Designed to streamline the setup of checkbox components, ensuring consistency and reducing boilerplate in UI code.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldCreationConfig
public @interface TimePickerConfig {

    /**
     * Specifies the component's ID.
     * Corresponding method in Vaadin: {@link Component#setId(String)}
     *
     * @return The ID of the component.
     */
    String id() default "";

    /**
     * Specifies the default value of the {@link TimePicker}.
     * The string should be in ISO-8601 time format (e.g., "HH:mm").
     * Corresponding method in Vaadin: {@link TimePicker#setValue(LocalTime)}
     *
     * @return The default value for the {@link TimePicker}.
     */
    String defaultValue() default "";

    /**
     * Determines whether the component is visible.
     * Corresponding method in Vaadin: {@link Component#setVisible(boolean)}
     *
     * @return True if the component should be visible, false otherwise.
     */
    boolean visible() default true;

    /**
     * Determines whether the field is required.
     * Corresponding method in Vaadin: {@link HasValue#setRequiredIndicatorVisible(boolean)}
     *
     * @return True if the field is required, false otherwise.
     */
    boolean required() default false;

    /**
     * Determines whether the component is enabled.
     * Corresponding method in Vaadin: {@link HasEnabled#setEnabled(boolean)}
     *
     * @return True if the component should be enabled, false otherwise.
     */
    boolean enabled() default true;

    /**
     * Sets the component to read-only mode.
     * Corresponding method in Vaadin: {@link HasValue#setReadOnly(boolean)}
     *
     * @return True if the component should be read-only, false otherwise.
     */
    boolean readOnly() default false;

    /**
     * Specifies the label for the field.
     * Corresponding method in Vaadin: {@link HasLabel#setLabel(String)}
     *
     * @return The label text.
     */
    String label() default "";

    /**
     * Specifies the helper text for the field.
     * Corresponding method in Vaadin: {@link HasHelper#setHelperText(String)}
     *
     * @return The helper text.
     */
    String helperText() default "";

    /**
     * Specifies the placeholder text for the field.
     * Corresponding method in Vaadin: {@link HasPlaceholder#setPlaceholder(String)}
     *
     * @return The placeholder text.
     */
    String placeholder() default "";

    /**
     * Specifies the tooltip text for the field.
     * Corresponding method in Vaadin: {@link HasTooltip#setTooltipText(String)}
     *
     * @return The tooltip text.
     */
    String tooltipText() default "";

    /**
     * Specifies the prefix text for the field.
     * Corresponding method in Vaadin: {@link HasPrefix#setPrefixComponent(com.vaadin.flow.component.Component)}
     *
     * @return The prefix text.
     */
    String prefixText() default "";

    /**
     * Determines whether the clear button is visible for the field.
     * Corresponding method in Vaadin: {@link HasClearButton#setClearButtonVisible(boolean)}
     *
     * @return True if the clear button should be visible, false otherwise.
     */
    boolean clearButtonVisible() default false;

    /**
     * Determines whether the picker's dropdown should automatically open when the field gains focus.
     * This is applicable to components like {@link TimePicker}.
     * Note: As of certain Vaadin versions, direct support for this feature might vary and could require
     * custom implementation or extension.
     *
     * @return True if the dropdown should automatically open on focus, false otherwise.
     */
    boolean autoOpen() default false;

    /**
     * Specifies the minimum time allowed to be selected in the time picker.
     * The string should be in ISO-8601 time format (e.g., "HH:mm").
     * Corresponding method in Vaadin: {@link TimePicker#setMin(LocalTime)}
     *
     * @return The minimum time in ISO-8601 format as a string.
     */
    String min() default "";

    /**
     * Specifies the maximum time allowed to be selected in the time picker.
     * The string should be in ISO-8601 time format (e.g., "HH:mm").
     * Corresponding method in Vaadin: {@link TimePicker#setMax(LocalTime)}
     *
     * @return The maximum time in ISO-8601 format as a string.
     */
    String max() default "";

    /**
     * Determines whether the text field should occupy the full width of its container.
     * Corresponding method in Vaadin: {@link HasSize#setWidthFull()}
     *
     * @return True if the component should have full width, false otherwise.
     */
    boolean widthFull() default false;

    /**
     * Determines whether the text field should occupy the full height of its container.
     * Corresponding method in Vaadin: {@link HasSize#setHeightFull()}
     *
     * @return True if the component should have full height, false otherwise.
     */
    boolean heightFull() default false;

    /**
     * Determines whether the text field should occupy the full size of its container, both width and height.
     * Corresponding methods in Vaadin: {@link HasSize#setSizeFull()}
     *
     * @return True if the component should have full size, false otherwise.
     */
    boolean sizeFull() default false;

    /**
     * Specifies the width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link HasSize#setWidth(String)}
     * @return The width of the component.
     */
    String width() default "";

    /**
     * Specifies the min width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link HasSize#setMinWidth(String)}
     * @return The width of the component.
     */
    String minWidth() default "";

    /**
     * Specifies the max width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link HasSize#setMaxWidth(String)}
     * @return The width of the component.
     */
    String maxWidth() default "";

    /**
     * Specifies the height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link HasSize#setHeight(String)}
     * @return The width of the component.
     */
    String height() default "";

    /**
     * Specifies the min height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link HasSize#setMinHeight(String)}
     * @return The width of the component.
     */
    String minHeight() default "";

    /**
     * Specifies the max height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link HasSize#setMaxHeight(String)}
     * @return The width of the component.
     */
    String maxHeight() default "";

}
