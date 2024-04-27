package io.taf.utils.fields;

import com.vaadin.flow.component.*;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This utility class provides factory methods for creating Vaadin components based on field annotations.
 * It simplifies the creation of data-bound UI components by leveraging Java reflection and annotation processing.
 *
 * @author Ivan Kaliuzhnyi
 */
@UtilityClass
@SuppressWarnings({"unchecked", "rawtypes"})
public class FieldUtils {

    public final String FIELD_ID_DELIMITER = "-";
    public final String FIELD_ID_PREFIX = "field";

    private final FieldCreationContext fieldCreationContext = new FieldCreationContext();

    /**
     * Creates a Vaadin component based on the annotated field. This method checks the field's
     * annotations and type to determine which component to create and how to configure it.
     *
     * @param field the field to create the component for
     * @return the created Vaadin component, or null if the field type is not supported
     */
    @Nonnull
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
    @Nonnull
    public Optional<AbstractField> createField(@Nonnull Field field, @Nonnull Function<String, String> textResolver) {
        return fieldCreationContext.createField(field, textResolver);
    }

    @Nonnull
    public Map<Field, AbstractField> createFields(@Nonnull Class<?> cls, @Nonnull Function<String, String> textResolver) {
        var result = new LinkedHashMap<Field, AbstractField>();
        ReflectionUtils.get(ReflectionUtils.Fields.of(cls))
                .stream()
                .sorted(Comparator.comparingInt(FieldUtils::getFiledOrder))
                .forEach(field -> createField(field, textResolver).ifPresent(component -> result.put(field, component)));
        return result;
    }


    /**
     * Checks if the given field represents an array.
     * <p>
     * This method determines whether the specified field is an array type by utilizing the {@link Field#getType()}
     * method to obtain the field's class. It then assesses if this class denotes an array by invoking {@link Class#isArray()}.
     *
     * @param field The field to check. This parameter must not be null.
     * @return true if the field is an array type; false otherwise.
     */
    public boolean isArrayField(@Nonnull Field field) {
        return field.getType().isArray();
    }

    /**
     * Checks if the given field is an array and if its component type is assignable from any of the provided classes.
     * <p>
     * This overloaded method first checks if the field is an array using {@link #isArrayField(Field)}. If the field is an array,
     * it further checks the compatibility of its component type with the specified element classes using {@link #isArrayFieldType(Field, Class[])}.
     *
     * @param field          The field to check. This parameter must not be null.
     * @param elementClasses Varargs of Class objects representing the types to which the array's component type should be assignable.
     * @return true if the field is an array type and its component type is assignable from any of the provided classes; false otherwise.
     */
    public boolean isArrayField(@Nonnull Field field, @Nonnull Class<?>... elementClasses) {
        return isArrayField(field) && isArrayFieldType(field, elementClasses);
    }

    /**
     * Checks if the given field represents a List.
     * <p>
     * Utilizes the {@code isFieldType} method to determine whether the specified field's type is assignable from {@link List}.
     * This effectively checks if the field is of type {@link List} or a subtype thereof.
     *
     * @param field The field to check. This parameter must not be null.
     * @return true if the field is of type {@link List} or a subtype thereof; false otherwise.
     */
    public boolean isListField(@Nonnull Field field) {
        return isFieldType(field, List.class);
    }

    /**
     * Checks if the given field is a List and if its elements are of types assignable from the provided classes.
     * <p>
     * This method first verifies whether the field is a List by calling {@link #isListField(Field)}. If the field is a List,
     * it then checks the compatibility of its element types with the specified classes using {@link #isListFieldType(Field, Class[])}.
     *
     * @param field          The field to check. This parameter must not be null.
     * @param elementClasses Varargs of Class objects representing the element types the list is expected to contain.
     * @return true if the field is a List and its element types are assignable from any of the provided classes; false otherwise.
     */
    public boolean isListField(@Nonnull Field field, @Nonnull Class<?>... elementClasses) {
        return isListField(field) && isListFieldType(field, elementClasses);
    }

    /**
     * Checks if the given field represents a Set.
     * <p>
     * Utilizes the {@code isFieldType} method to determine whether the specified field's type is assignable from {@link Set}.
     * This effectively checks if the field is of type {@link Set} or a subtype thereof.
     *
     * @param field The field to check. This parameter must not be null.
     * @return true if the field is of type {@link Set} or a subtype thereof; false otherwise.
     */
    public boolean isSetField(@Nonnull Field field) {
        return isFieldType(field, Set.class);
    }

    /**
     * Checks if the given field is a Set and if its elements are of types assignable from the provided classes.
     * <p>
     * This method first verifies whether the field is a List by calling {@link #isSetField(Field)}. If the field is a Set,
     * it then checks the compatibility of its element types with the specified classes using {@link #isSetFieldType(Field, Class[])}.
     *
     * @param field          The field to check. This parameter must not be null.
     * @param elementClasses Varargs of Class objects representing the element types the list is expected to contain.
     * @return true if the field is a Set and its element types are assignable from any of the provided classes; false otherwise.
     */
    public boolean isSetField(@Nonnull Field field, @Nonnull Class<?>... elementClasses) {
        return isSetField(field) && isSetFieldType(field, elementClasses);
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
        return isFieldType(field,
                Number.class,
                byte.class, short.class, int.class, long.class, float.class, double.class,
                Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
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
     * Determines whether the type of the given field is assignable from any of the provided classes.
     *
     * @param field   The field whose type is to be checked.
     * @param classes The classes to compare against the field's type.
     * @return true if the field's type is assignable from any of the provided classes, false otherwise.
     */
    public boolean isFieldType(@Nonnull Field field, @Nonnull Class<?>... classes) {
        for (var cls : classes) {
            if (cls.isAssignableFrom(getFieldType(field))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the component type of the given array field is assignable from any of the provided classes.
     * Assumes that the field is an array.
     *
     * @param field   The array field whose component type is to be checked.
     * @param classes The classes to compare against the component type of the array.
     * @return true if the component type of the array is assignable from any of the provided classes, false otherwise.
     */
    public boolean isArrayFieldType(@Nonnull Field field, @Nonnull Class<?>... classes) {

        var elementType = getArrayFieldType(field);
        if (elementType == null) {
            return false;
        }

        for (var cls : classes) {
            if (cls.isAssignableFrom(elementType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the element type of the given list field is assignable from any of the provided classes.
     * Assumes that the field is a parameterized list.
     *
     * @param field   The list field whose element type is to be checked.
     * @param classes The classes to compare against the element type of the list.
     * @return true if the element type of the list is assignable from any of the provided classes, false otherwise.
     */
    public boolean isListFieldType(@Nonnull Field field, @Nonnull Class<?>... classes) {

        var elementType = getListFieldType(field);
        if (elementType == null) {
            return false;
        }

        for (var cls : classes) {
            if (cls.isAssignableFrom(elementType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the element type of the given set field is assignable from any of the provided classes.
     * Assumes that the field is a parameterized set.
     *
     * @param field   The list field whose element type is to be checked.
     * @param classes The classes to compare against the element type of the set.
     * @return true if the element type of the set is assignable from any of the provided classes, false otherwise.
     */
    public boolean isSetFieldType(@Nonnull Field field, @Nonnull Class<?>... classes) {

        var elementType = getSetFieldType(field);
        if (elementType == null) {
            return false;
        }

        for (var cls : classes) {
            if (cls.isAssignableFrom(elementType)) {
                return true;
            }
        }
        return false;
    }


    public int getFiledOrder(@Nonnull Field field) {
        return field.isAnnotationPresent(FieldOrder.class) ? field.getAnnotation(FieldOrder.class).value() : Integer.MAX_VALUE;
    }

    /**
     * Returns the type of the given field.
     *
     * @param field The field whose type is being returned.
     * @return A Class object representing the type of the field.
     */
    @Nonnull
    public Class<?> getFieldType(@Nonnull Field field) {
        return field.getType();
    }

    /**
     * Returns the component type of the array for the given field.
     * Assumes that the field is an array.
     *
     * @param field The array field whose component type is being returned.
     * @return A Class representing the component type of the array. If the field is not an array, returns null.
     */
    @Nullable
    public Class<?> getArrayFieldType(@Nonnull Field field) {
        return field.getType().getComponentType();
    }

    /**
     * Returns the element type of the list for the given parameterized list field.
     * Assumes that the field is a parameterized list.
     *
     * @param field The list field whose element type is being returned.
     * @return A Class representing the element type of the list. If the element type cannot be determined, returns the Object class.
     */
    @Nullable
    public Class<?> getListFieldType(@Nonnull Field field) {
        var type = field.getGenericType();
        var argumentsTypes = ((ParameterizedType) type).getActualTypeArguments();
        return argumentsTypes[0] instanceof Class<?> ? (Class<?>) argumentsTypes[0] : null;
    }

    /**
     * Returns the element type of the list for the given parameterized set field.
     * Assumes that the field is a parameterized list.
     *
     * @param field The set field whose element type is being returned.
     * @return A Class representing the element type of the set. If the element type cannot be determined, returns the Object class.
     */
    @Nullable
    public Class<?> getSetFieldType(@Nonnull Field field) {
        var type = field.getGenericType();
        var argumentsTypes = ((ParameterizedType) type).getActualTypeArguments();
        return argumentsTypes[0] instanceof Class<?> ? (Class<?>) argumentsTypes[0] : null;
    }


    @Nonnull
    public String generateFieldId(@Nonnull Field field, @Nullable String defaultValue) {
        return Optional.ofNullable(defaultValue)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> FIELD_ID_PREFIX.concat(FIELD_ID_DELIMITER).concat(field.getName().toLowerCase()));
    }

    @Nonnull
    public String generateFieldLabel(@Nonnull Field field, @Nullable String defaultValue) {
        return Optional.ofNullable(defaultValue)
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> {
                    var worlds = StringUtils.splitByCharacterTypeCamelCase(field.getName());
                    var genLabel = StringUtils.join(worlds, " ");
                    return StringUtils.capitalize(genLabel);
                });
    }

    // Resolvers

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
    public void resolveFieldId(@Nonnull Component component, @Nonnull Field field, @Nullable String defaultValue) {
        component.setId(generateFieldId(field, defaultValue));
    }

    /**
     * Dynamically sets the value of a component that implements the {@link HasValue} interface using a value provided by a supplier.
     * If the {@code valueProvider} class is not equivalent to {@code void.class}, this method creates an instance of the supplier from the
     * {@code valueProvider}. It then fetches a value using the supplier and sets this value to the component, if the value is not null.
     *
     * @param component     The component to which the value is to be set. This component must implement the {@link HasValue} interface.
     * @param valueProvider The class that provides the value, which must implement the {@link Supplier} interface. It should not be {@code void.class}.
     *                      If it is {@code void.class}, no action is taken.
     * @throws IllegalStateException if the {@code valueProvider} does not provide a valid instance or if it does not implement {@link Supplier}.
     * @implNote This method assumes that the {@code createInstance} method can instantiate {@code valueProvider} effectively. It also relies on the
     * assumption that the {@code get} method of the {@code Supplier} returns a value that is not null to set the component's value.
     */
    public void resolveFieldValue(@Nonnull HasValue component, @Nonnull Class<?> valueProvider) {
        if (!valueProvider.equals(void.class)) {
            var supplier = createInstance(valueProvider, Supplier.class);
            var value = supplier.get();
            if (value != null) {
                component.setValue(value);
            }
        }
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
    public void resolveFieldLabel(@Nonnull HasLabel component, @Nonnull Field field, @Nullable String defaultValue) {
        component.setLabel(generateFieldLabel(field, defaultValue));
    }

    /**
     * Sets the component's width to 100% if the given value is true.
     *
     * @param component The component implementing {@link HasSize} whose width is to be adjusted.
     * @param value     If true, sets the component's width to 100%.
     */
    public void resolveFieldWidthFull(@Nonnull HasSize component, boolean value) {
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
    public void resolveFieldWidth(@Nonnull HasSize component, @Nullable String value) {
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
    public void resolveFieldMinWidth(@Nonnull HasSize component, @Nullable String value) {
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
    public void resolveFieldMaxWidth(@Nonnull HasSize component, @Nullable String value) {
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
    public void resolveFieldHeightFull(@Nonnull HasSize component, boolean value) {
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
    public void resolveFieldHeight(@Nonnull HasSize component, @Nullable String value) {
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
    public void resolveFieldMinHeight(@Nonnull HasSize component, @Nullable String value) {
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
    public void resolveFieldMaxHeight(@Nonnull HasSize component, @Nullable String value) {
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
    public void resolveFieldSizeFull(@Nonnull HasSize component, boolean value) {
        if (value) {
            component.setSizeFull();
        }
    }

    // Other

    @SneakyThrows
    public <T> T createInstance(@Nonnull Class<?> cls, @Nonnull Class<T> targetType) {
        var instance = cls.getDeclaredConstructor().newInstance();
        if (targetType.isInstance(instance)) {
            return targetType.cast(instance);
        } else {
            throw new ClassCastException("Cannot cast instance of " + cls.getCanonicalName() + " to " + targetType.getCanonicalName());
        }
    }

}
