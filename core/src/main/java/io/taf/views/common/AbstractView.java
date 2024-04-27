package io.taf.views.common;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.taf.views.panel.AbstractCommandPanel;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Optional;

public abstract class AbstractView
        extends BaseComposite<VerticalLayout>
        implements View {

    public static final String COMMAND_PANEL_COMPONENT_ID = "command-panel";
    public static final String HEADER_PANEL_COMPONENT_ID = "header-panel";
    public static final String BODY_PANEL_COMPONENT_ID = "body-panel";
    public static final String FOOTER_PANEL_COMPONENT_ID = "footer-panel";

    @Nonnull
    @Override
    protected VerticalLayout initContent() {
        var content = super.initContent();
        content.setSizeFull();

        Optional.ofNullable(getCommandPanel())
                .ifPresent(content::add);

        content.add(
                getHeaderPanel(),
                getBodyPanel(),
                getFooterPanel()
        );
        return content;
    }

    @Nullable
    @Override
    public AbstractCommandPanel getCommandPanel() {
        return Optional.ofNullable(initCommandPanel())
                .map(commandPanel -> {
                    addComponent(COMMAND_PANEL_COMPONENT_ID, commandPanel);
                    return commandPanel;
                })
                .orElse(null);
    }

    @Nullable
    protected AbstractCommandPanel initCommandPanel() {
        return null;
    }

    @Nonnull
    @Override
    public final HorizontalLayout getHeaderPanel() {
        return getComponent(HEADER_PANEL_COMPONENT_ID, HorizontalLayout.class, initHeaderPanel());
    }

    @Nonnull
    protected HorizontalLayout initHeaderPanel() {
        var panel = new HorizontalLayout();
        panel.setId(HEADER_PANEL_COMPONENT_ID);
        panel.setWidthFull();
        panel.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return panel;
    }

    @Nonnull
    @Override
    public final HorizontalLayout getBodyPanel() {
        return getComponent(BODY_PANEL_COMPONENT_ID, HorizontalLayout.class, initBodyPanel());
    }

    @Nonnull
    protected HorizontalLayout initBodyPanel() {
        var panel = new HorizontalLayout();
        panel.setId(BODY_PANEL_COMPONENT_ID);
        panel.setSizeFull();
        return panel;
    }

    @Nonnull
    @Override
    public final HorizontalLayout getFooterPanel() {
        return getComponent(FOOTER_PANEL_COMPONENT_ID, HorizontalLayout.class, initFooterPanel());
    }

    @Nonnull
    protected HorizontalLayout initFooterPanel() {
        var panel = new HorizontalLayout();
        panel.setId(FOOTER_PANEL_COMPONENT_ID);
        panel.setWidthFull();
        panel.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return panel;
    }

}
