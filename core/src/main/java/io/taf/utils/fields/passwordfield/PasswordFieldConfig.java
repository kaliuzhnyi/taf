package io.taf.utils.fields.passwordfield;

import io.taf.utils.fields.FieldCreationConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to configure a {@link com.vaadin.flow.component.textfield.PasswordField} component in a Vaadin application.
 * Enables declarative configuration of a {@link com.vaadin.flow.component.textfield.PasswordField}, specifying properties such as ID, initial checked state, visibility,
 * and other attributes related to its appearance and behavior.
 * Designed to streamline the setup of {@link com.vaadin.flow.component.textfield.PasswordField} components, ensuring consistency and reducing boilerplate in UI code.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldCreationConfig
public @interface PasswordFieldConfig {

    /**
     * Specifies the component's ID.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.Component#setId(String)}
     *
     * @return The ID of the component.
     */
    String id() default "";

    /**
     * Specifies the default value of the {@link com.vaadin.flow.component.HasValue}.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasValue#setValue(Object)}
     *
     * @return The default value for the text field.
     */
    String defaultValue() default "";

    /**
     * Determines whether the component is visible.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.Component#setVisible(boolean)}
     *
     * @return True if the component should be visible, false otherwise.
     */
    boolean visible() default true;

    /**
     * Determines whether the field is required.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasValue#setRequiredIndicatorVisible(boolean)}
     *
     * @return True if the field is required, false otherwise.
     */
    boolean required() default false;

    /**
     * Determines whether the component is enabled.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasEnabled#setEnabled(boolean)}
     *
     * @return True if the component should be enabled, false otherwise.
     */
    boolean enabled() default true;

    /**
     * Sets the component to read-only mode.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasValue#setReadOnly(boolean)}
     *
     * @return True if the component should be read-only, false otherwise.
     */
    boolean readOnly() default false;

    /**
     * Specifies the label for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasLabel#setLabel(String)}
     *
     * @return The label text.
     */
    String label() default "";

    /**
     * Specifies the helper text for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasHelper#setHelperText(String)}
     *
     * @return The helper text.
     */
    String helperText() default "";

    /**
     * Specifies the placeholder text for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasPlaceholder#setPlaceholder(String)}
     *
     * @return The placeholder text.
     */
    String placeholder() default "";

    /**
     * Specifies the tooltip text for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.shared.HasTooltip#setTooltipText(String)}
     *
     * @return The tooltip text.
     */
    String tooltipText() default "";

    /**
     * Specifies the prefix text for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.shared.HasPrefix#setPrefixComponent(com.vaadin.flow.component.Component)}
     *
     * @return The prefix text.
     */
    String prefixText() default "";

    /**
     * Specifies the suffix text for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.shared.HasSuffix#setSuffixComponent(com.vaadin.flow.component.Component)}
     *
     * @return The prefix text.
     */
    String suffixText() default "";

    /**
     * Determines whether the clear button is visible for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.shared.HasClearButton#setClearButtonVisible(boolean)}
     *
     * @return True if the clear button should be visible, false otherwise.
     */
    boolean clearButtonVisible() default false;

    /**
     * Specifies the minimum length of the text field's value.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.textfield.TextField#setMinLength(int)}
     *
     * @return The minimum length.
     */
    int minLength() default 0;

    /**
     * Specifies the maximum length of the text field's value.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.textfield.TextField#setMaxLength(int)}
     *
     * @return The maximum length.
     */
    int maxLength() default 100;

    /**
     * Determines whether the text field should occupy the full width of its container.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasSize#setWidthFull()}
     *
     * @return True if the component should have full width, false otherwise.
     */
    boolean widthFull() default false;

    /**
     * Determines whether the text field should occupy the full height of its container.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.HasSize#setHeightFull()}
     *
     * @return True if the component should have full height, false otherwise.
     */
    boolean heightFull() default false;

    /**
     * Determines whether the text field should occupy the full size of its container, both width and height.
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setSizeFull()}
     *
     * @return True if the component should have full size, false otherwise.
     */
    boolean sizeFull() default false;

    /**
     * Specifies the width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setWidth(String)}
     * @return The width of the component.
     */
    String width() default "";

    /**
     * Specifies the min width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMinWidth(String)}
     * @return The width of the component.
     */
    String minWidth() default "";

    /**
     * Specifies the max width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMaxWidth(String)}
     * @return The width of the component.
     */
    String maxWidth() default "";

    /**
     * Specifies the height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setHeight(String)}
     * @return The width of the component.
     */
    String height() default "";

    /**
     * Specifies the min height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMinHeight(String)}
     * @return The width of the component.
     */
    String minHeight() default "";

    /**
     * Specifies the max height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMaxHeight(String)}
     * @return The width of the component.
     */
    String maxHeight() default "";

}
