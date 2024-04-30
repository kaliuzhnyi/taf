package io.taf.views.panel;

import com.vaadin.flow.component.menubar.MenuBar;
import jakarta.annotation.Nonnull;

public interface CommandPanel {

    @Nonnull
    MenuBar getMenu();

    @Nonnull
    MenuBar getMoreMenu();

}
