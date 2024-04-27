package io.taf.views.panel;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.taf.views.item.ItemDataView;
import jakarta.annotation.Nonnull;

@SuppressWarnings("rawtypes")
public abstract class AbstractItemDataCommandPanel<ITEM_DATA_VIEW extends ItemDataView>
        extends AbstractCommandPanel
        implements ItemDataCommandPanel<ITEM_DATA_VIEW> {

    public static final String SAVE_COMMAND_COMPONENT_ID = "command-save";
    public static final String SAVE_AND_CLOSE_COMMAND_COMPONENT_ID = "command-save-and-close";
    public static final String DELETE_COMMAND_COMPONENT_ID = "command-delete";
    public static final String CANCEL_COMMAND_COMPONENT_ID = "command-cancel";
    public static final String MORE_COMMAND_COMPONENT_ID = "command-more";
    public static final String TOGGLE_DELETION_MARK_COMMAND_COMPONENT_ID = "command-toggle-deletion-mark";
    public static final String TOGGLE_DRAFT_MARK_COMPONENT_ID = "command-toggle-draft-mark";

    @Nonnull
    @Override
    protected MenuBar initMenu() {
        var menu = super.initMenu();
        addComponent(SAVE_AND_CLOSE_COMMAND_COMPONENT_ID, initSaveAndCloseCommand(menu));
        addComponent(SAVE_COMMAND_COMPONENT_ID, initSaveCommand(menu));
        addComponent(DELETE_COMMAND_COMPONENT_ID, initDeleteCommand(menu));
        addComponent(CANCEL_COMMAND_COMPONENT_ID, initCancelCommand(menu));
        addComponent(MORE_COMMAND_COMPONENT_ID, initMoreCommand(menu));
        return menu;
    }

    @Nonnull
    @Override
    public final MenuItem getSaveCommand() {
        return getComponent(SAVE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initSaveCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(messageProvider.getMessage("command.save.title", SAVE_COMMAND_DEFAULT_TITLE));
        item.setId(SAVE_COMMAND_COMPONENT_ID);
        return item;
    }

    @Nonnull
    @Override
    public final MenuItem getSaveAndCloseCommand() {
        return getComponent(SAVE_AND_CLOSE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initSaveAndCloseCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(messageProvider.getMessage("command.save.and.close.title", SAVE_AND_CLOSE_COMMAND_COMPONENT_ID));
        item.setId(SAVE_AND_CLOSE_COMMAND_COMPONENT_ID);
        item.addClassNames(LumoUtility.TextColor.PRIMARY_CONTRAST, LumoUtility.Background.PRIMARY);
        return item;
    }

    @Nonnull
    @Override
    public final MenuItem getDeleteCommand() {
        return getComponent(DELETE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initDeleteCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(messageProvider.getMessage("command.delete.title", DELETE_COMMAND_DEFAULT_TITLE));
        item.setId(DELETE_COMMAND_COMPONENT_ID);
        item.addClassNames(LumoUtility.TextColor.WARNING);
        return item;
    }

    @Nonnull
    @Override
    public final MenuItem getCloseCommand() {
        return getComponent(CANCEL_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initCancelCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(messageProvider.getMessage("command.title.cancel", CANCEL_COMMAND_DEFAULT_TITLE));
        item.setId(CANCEL_COMMAND_COMPONENT_ID);
        return item;
    }

    @Nonnull
    @Override
    public final MenuItem getMoreCommand() {
        return getComponent(MORE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initMoreCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(messageProvider.getMessage("command.title.more", MORE_COMMAND_DEFAULT_TITLE));
        item.setId(MORE_COMMAND_COMPONENT_ID);
        item.addClassNames(LumoUtility.Background.TRANSPARENT);

        var subMenu = item.getSubMenu();
        addComponent(TOGGLE_DELETION_MARK_COMMAND_COMPONENT_ID, initToggleDeletionMarkCommand(subMenu));
        addComponent(TOGGLE_DRAFT_MARK_COMPONENT_ID, initToggleDraftMarkCommand(subMenu));

        return item;
    }

    @Nonnull
    @Override
    public MenuItem getToggleDeletionMarkCommand() {
        return getComponent(TOGGLE_DELETION_MARK_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initToggleDeletionMarkCommand(@Nonnull SubMenu subMenu) {
        var item = subMenu.addItem(messageProvider.getMessage("command.toggle.deletion.mark.title", TOGGLE_DELETION_MARK_COMMAND_DEFAULT_TITLE));
        item.setId(TOGGLE_DELETION_MARK_COMMAND_COMPONENT_ID);
        return item;
    }

    @Nonnull
    @Override
    public MenuItem getToggleDraftMarkCommand() {
        return getComponent(TOGGLE_DRAFT_MARK_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initToggleDraftMarkCommand(@Nonnull SubMenu subMenu) {
        var item = subMenu.addItem(messageProvider.getMessage("command.toggle.draft.mark.title", TOGGLE_DRAFT_MARK_COMMAND_DEFAULT_TITLE));
        item.setId(TOGGLE_DRAFT_MARK_COMPONENT_ID);
        return item;
    }

}
