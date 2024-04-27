package io.taf.views.form;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import io.taf.entity.Entity;
import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.util.List;

public interface Form<ENTITY extends Entity<? extends Serializable>> {

    void init();

    List<Component> getFields();

    @Nonnull
    Class<ENTITY> getEntityClass();

    @Nonnull
    BeanValidationBinder<ENTITY> getBinder();

}
