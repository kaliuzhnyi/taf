package io.taf.views.panel;

import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.taf.utils.MessageProvider;
import io.taf.views.common.BaseComposite;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCommandPanel
        extends BaseComposite<HorizontalLayout>
        implements CommandPanel {

    public static final String MENU_COMPONENT_ID = "menu";

    @Autowired
    protected MessageProvider messageProvider;

    @Override
    @PostConstruct
    protected HorizontalLayout initContent() {
        var content = super.initContent();
        content.setWidthFull();
        content.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        content.add(getMenu());
        return content;
    }

    @Nonnull
    @Override
    public final MenuBar getMenu() {
        return getComponent(MENU_COMPONENT_ID, MenuBar.class, initMenu());
    }

    @Nonnull
    protected MenuBar initMenu() {
        var menu = new MenuBar();
        return menu;
    }

}
