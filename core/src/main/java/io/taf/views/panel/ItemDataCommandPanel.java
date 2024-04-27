package io.taf.views.panel;

import com.vaadin.flow.component.contextmenu.MenuItem;
import io.taf.views.item.ItemDataView;
import jakarta.annotation.Nonnull;

@SuppressWarnings("rawtypes")
public interface ItemDataCommandPanel<ITEM_DATA_VIEW extends ItemDataView>
        extends CommandPanel {

    String SAVE_COMMAND_DEFAULT_TITLE = "Save";
    String DELETE_COMMAND_DEFAULT_TITLE = "Delete";
    String CANCEL_COMMAND_DEFAULT_TITLE = "Cancel";
    String MORE_COMMAND_DEFAULT_TITLE = "More..";
    String TOGGLE_DELETION_MARK_COMMAND_DEFAULT_TITLE = "Toggle deletion mark";
    String TOGGLE_DRAFT_MARK_COMMAND_DEFAULT_TITLE = "Toggle draft mark";

    @Nonnull
    MenuItem getSaveCommand();

    @Nonnull
    MenuItem getSaveAndCloseCommand();

    @Nonnull
    MenuItem getDeleteCommand();

    @Nonnull
    MenuItem getCloseCommand();

    @Nonnull
    MenuItem getMoreCommand();

    @Nonnull
    MenuItem getToggleDeletionMarkCommand();

    @Nonnull
    MenuItem getToggleDraftMarkCommand();

}
