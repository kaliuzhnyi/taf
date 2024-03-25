package io.taf.views.common;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import jakarta.annotation.Nonnull;

public interface View extends BeforeEnterObserver {

    @Nonnull
    HorizontalLayout getCommandPanel();

    @Nonnull
    HorizontalLayout getHeaderPanel();

    @Nonnull
    HorizontalLayout getBodyPanel();

    @Nonnull
    HorizontalLayout getFooterPanel();

    @Override
    default void beforeEnter(BeforeEnterEvent event) {}

}
