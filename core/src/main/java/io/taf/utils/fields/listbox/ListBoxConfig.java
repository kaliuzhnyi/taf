package io.taf.utils.fields.listbox;

import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.data.provider.DataProvider;
import io.taf.utils.fields.FieldCreationConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@FieldCreationConfig
public @interface ListBoxConfig {

    /**
     * Specifies the component's ID.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.Component#setId(String)}
     *
     * @return The ID of the component.
     */
    String id() default "";

    /**
     * Specifies the class that will supply the default value for the annotated field.
     * This class must implement the {@link Supplier} interface, providing
     * a {@code get()} method that returns the default value.
     * <p>
     * If no default value supplier is specified, or if the specified class is {@code void.class},
     * no default value will be set.
     * <p>
     * Note: The supplied default value must be of a type compatible with the field it is intended for.
     * <p>
     * Example usage:
     * {@code @ListBoxConfig(defaultValueProvider = MyDefaultValueProvider.class)}
     * where {@code MyDefaultValueProvider} implements {@code Supplier<MyFieldType>}.
     *
     * @return The class that supplies the default value for the annotated field.
     * The class must implement {@link Supplier}.
     */
    Class<?> defaultValueProvider() default void.class;

    /**
     * Specifies the class that will be used as a DataProvider to populate the options for the {@link ListBox} component.
     * The specified class must implement the {@link DataProvider} interface. It is essential
     * that this class adheres to the expected contract of a DataProvider, as it will be responsible for providing the data
     * items to the Select component.
     * <p>
     * Note: Since this attribute accepts {@link Class<?>}, it does not enforce type safety at compile time. It is the
     * responsibility of the developer to ensure that the provided class correctly implements
     * {@link DataProvider}. Failure to do so will not be caught until runtime, potentially
     * leading to a {@link ClassCastException} if the class is not compatible.
     * <p>
     * To ensure that the specified class is a valid {@link DataProvider}, it is highly recommended to perform a runtime check
     * before its usage. For instance, you can use reflection to verify that the class indeed implements {@link DataProvider} and
     * to instantiate it. Handling this check gracefully and throwing an informative exception if the check fails can
     * significantly improve the debugging process and prevent runtime issues.
     * <p>
     * Example usage:
     * <p>
     * {@code
     *
     * @return The {@link DataProvider} class used to provide data for the {@link ListBox} component. This class must implement
     * {@link DataProvider}.
     * @ListBoxConfig(dataProvider = MyCustomDataProvider.class)
     * private String mySelectField;
     * }
     * <p>
     * Where {@code MyCustomDataProvider} must implement {@code DataProvider}.
     */
    Class<?> dataProvider();

    /**
     * Determines whether the component is visible.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.Component#setVisible(boolean)}
     *
     * @return True if the component should be visible, false otherwise.
     */
    boolean visible() default true;

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
     * Specifies the tooltip text for the field.
     * Corresponding method in Vaadin: {@link com.vaadin.flow.component.shared.HasTooltip#setTooltipText(String)}
     *
     * @return The tooltip text.
     */
    String tooltipText() default "";

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
     *
     * @return The width of the component.
     */
    String width() default "";

    /**
     * Specifies the min width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMinWidth(String)}
     *
     * @return The width of the component.
     */
    String minWidth() default "";

    /**
     * Specifies the max width of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMaxWidth(String)}
     *
     * @return The width of the component.
     */
    String maxWidth() default "";

    /**
     * Specifies the height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setHeight(String)}
     *
     * @return The width of the component.
     */
    String height() default "";

    /**
     * Specifies the min height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMinHeight(String)}
     *
     * @return The width of the component.
     */
    String minHeight() default "";

    /**
     * Specifies the max height of the field.
     * Acceptable values include CSS size units (e.g., "100px", "50%", "auto").
     * Corresponding methods in Vaadin: {@link com.vaadin.flow.component.HasSize#setMaxHeight(String)}
     *
     * @return The width of the component.
     */
    String maxHeight() default "";

}
