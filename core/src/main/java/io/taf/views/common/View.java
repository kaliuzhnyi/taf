package io.taf.views.common;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import io.taf.views.panel.AbstractCommandPanel;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface View extends BeforeEnterObserver {

    @Nullable
    AbstractCommandPanel getCommandPanel();

    @Nonnull
    HorizontalLayout getHeaderPanel();

    @Nonnull
    HorizontalLayout getBodyPanel();

    @Nonnull
    HorizontalLayout getFooterPanel();

    @Override
    default void beforeEnter(BeforeEnterEvent event) {
    }

}
