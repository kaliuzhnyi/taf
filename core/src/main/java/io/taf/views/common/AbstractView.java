package io.taf.views.common;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.Nonnull;

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
        content.add(
                getCommandPanel(),
                getHeaderPanel(),
                getBodyPanel(),
                getFooterPanel()
        );
        return content;
    }

    @Nonnull
    @Override
    public HorizontalLayout getCommandPanel() {
        return getComponent(COMMAND_PANEL_COMPONENT_ID, HorizontalLayout.class, initCommandPanel());
    }

    @Nonnull
    protected HorizontalLayout initCommandPanel() {
        var panel = new HorizontalLayout();
        panel.setId(COMMAND_PANEL_COMPONENT_ID);
        panel.setWidthFull();
        panel.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        return panel;
    }

    @Nonnull
    @Override
    public HorizontalLayout getHeaderPanel() {
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
    public HorizontalLayout getBodyPanel() {
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
    public HorizontalLayout getFooterPanel() {
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
