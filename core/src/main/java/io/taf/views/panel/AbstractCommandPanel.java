package io.taf.views.panel;

import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.taf.utils.MessageProvider;
import io.taf.views.common.BaseComposite;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCommandPanel
        extends BaseComposite<HorizontalLayout>
        implements CommandPanel {

    public static final String MENU_COMPONENT_ID = "menu";
    public static final String MORE_MENU_COMPONENT_ID = "menu-more";

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private MessageProvider messageProvider;

    @Override
    @PostConstruct
    protected HorizontalLayout initContent() {
        var content = super.initContent();
        content.setWidthFull();
        content.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        content.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        content.add(getMenu(), getMoreMenu());
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
        menu.setId(MENU_COMPONENT_ID);
        return menu;
    }

    @Nonnull
    @Override
    public final MenuBar getMoreMenu() {
        return getComponent(MORE_MENU_COMPONENT_ID, MenuBar.class, initMoreMenu());
    }

    @Nonnull
    protected MenuBar initMoreMenu() {
        var menu = new MenuBar();
        menu.setId(MORE_MENU_COMPONENT_ID);
        return menu;
    }

}
