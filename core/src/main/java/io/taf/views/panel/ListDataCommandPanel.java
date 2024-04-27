package io.taf.views.panel;

import com.vaadin.flow.component.contextmenu.MenuItem;
import io.taf.views.list.ListDataView;
import jakarta.annotation.Nonnull;

@SuppressWarnings({"rawtypes", "unused"})
public interface ListDataCommandPanel<LIST_DATA_VIEW extends ListDataView>
        extends CommandPanel {

    @Nonnull
    MenuItem getOpenCommand();

    @Nonnull
    MenuItem getDeleteCommand();

    @Nonnull
    MenuItem getMoreCommand();

    @Nonnull
    MenuItem getToggleDeletionMarkCommand();

    @Nonnull
    MenuItem getToggleDraftMarkCommand();

}
