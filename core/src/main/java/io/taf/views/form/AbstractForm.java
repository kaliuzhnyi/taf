package io.taf.views.form;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import io.taf.entity.Entity;
import io.taf.utils.FormUtils;
import io.taf.utils.GenericUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractForm<ENTITY extends Entity<? extends Serializable>>
        extends FormLayout
        implements Form<ENTITY> {

    public static final String PRIMARY_GROUP_COMPONENT_ID = "primary-group";


    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ENTITY> entityClass = GenericUtils.getType(getClass(), AbstractForm.class, 0);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final List<Component> fields = initFields();

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final VerticalLayout primaryGroup = initPrimaryGroup();

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final BeanValidationBinder<ENTITY> binder = initBinder();


    @PostConstruct
    @Override
    public void init() {
        setSizeFull();
        add(getPrimaryGroup());
    }

    @Nonnull
    protected List<Component> initFields() {
        return FormUtils.createFieldsBasedOnEntity(getEntityClass());
    }

    @Nonnull
    protected VerticalLayout initPrimaryGroup() {
        var component = new VerticalLayout();
        component.setId(PRIMARY_GROUP_COMPONENT_ID);
        component.setSizeFull();
        component.setSpacing(false);
        component.add(getFields());
        return component;
    }

    @Nonnull
    protected BeanValidationBinder<ENTITY> initBinder() {
        var binder = new BeanValidationBinder<>(getEntityClass());
        return binder;
    }

}
