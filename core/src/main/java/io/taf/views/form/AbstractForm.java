package io.taf.views.form;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import io.taf.entity.AbstractEntity;
import io.taf.entity.Entity;
import io.taf.utils.GenericUtils;
import io.taf.utils.MessageProvider;
import io.taf.utils.fields.FieldUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractForm<ENTITY extends Entity<? extends Serializable>>
        extends FormLayout
        implements Form<ENTITY> {

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ENTITY> entityClass = GenericUtils.getType(getClass(), AbstractForm.class, 0);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final List<Component> fields = initFields();

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final BeanValidationBinder<ENTITY> binder = initBinder();

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    @SuppressWarnings("rawtypes")
    private final Map<Field, AbstractField> fieldsMap = initFieldsMap();

    @PostConstruct
    @Override
    public void init() {
        setSizeFull();
        setResponsiveSteps(
                new ResponsiveStep("0px", 1),
                new ResponsiveStep("500px", 2)
        );
        add(getFields());
    }

    @Nonnull
    protected List<Component> initFields() {
        return getFieldsMap().values().stream()
                .map(Component.class::cast)
                .toList();
    }

    @Nonnull
    @SuppressWarnings("rawtypes")
    protected Map<Field, AbstractField> initFieldsMap() {
        return FieldUtils.createFields(getEntityClass(),
                s -> MessageProvider.getMessageProvider().getValue(s, s));
    }

    @Nonnull
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected BeanValidationBinder<ENTITY> initBinder() {
        var binder = new BeanValidationBinder<>(getEntityClass());

        getFields().stream()
                .filter(component -> HasValue.class.isAssignableFrom(component.getClass()))
                .forEach(component -> binder.bind((HasValue) component, createFieldGetter(component), createFieldSetter(component)));

        return binder;
    }

    protected ValueProvider<ENTITY, Object> createFieldGetter(@Nonnull Component component) {
        return entity -> findEntityField(component)
                .map(field -> getValueFromField(entity, field, component))
                .orElse(null);
    }

    protected Setter<ENTITY, Object> createFieldSetter(@Nonnull Component component) {
        return (entity, value) -> findEntityField(component)
                .ifPresent(field -> setValueToField(entity, field, value, component));
    }

    @SneakyThrows
    protected Object getValueFromField(@Nonnull ENTITY entity, @Nonnull Field field, @Nonnull Component component) {
        field.setAccessible(true);
        var value = field.get(entity);

        if (StringUtils.equals(field.getName(), AbstractEntity.Fields.id)) {
            return value.toString();
        } else {
            if (component.getClass().equals(NumberField.class) && value instanceof Number) {
                return ((Number) value).doubleValue();
            } else {
                return field.get(entity);
            }
        }
    }

    @SneakyThrows
    protected void setValueToField(@Nonnull ENTITY entity, @Nonnull Field field, @Nullable Object value, @Nonnull Component component) {
        field.setAccessible(true);

        if (StringUtils.equals(field.getName(), AbstractEntity.Fields.id)) {
            field.set(entity, entity.getIdClass().cast(value));
        } else {

            if (component.getClass().equals(NumberField.class) && value != null) {
                var numberValue = (Number) value;

                if (FieldUtils.isByteField(field)) {
                    field.set(entity, numberValue.byteValue());
                } else if (FieldUtils.isShortField(field)) {
                    field.set(entity, numberValue.shortValue());
                } else if (FieldUtils.isIntegerField(field)) {
                    field.set(entity, numberValue.intValue());
                } else if (FieldUtils.isLongField(field)) {
                    field.set(entity, numberValue.longValue());
                } else if (FieldUtils.isFloatField(field)) {
                    field.set(entity, numberValue.floatValue());
                } else if (FieldUtils.isDoubleField(field)) {
                    field.set(entity, numberValue.doubleValue());
                } else {
                    field.set(entity, null);
                }

            } else {
                field.set(entity, value);
            }

        }
    }

    @Nonnull
    protected Optional<Field> findEntityField(Component component) {
        return getFieldsMap().entrySet().stream()
                .filter(entry -> entry.getValue().equals(component))
                .findFirst()
                .map(Map.Entry::getKey);
    }

}
