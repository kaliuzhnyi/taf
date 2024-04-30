package io.taf.views.panel;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.taf.entity.DataEntity;
import io.taf.entity.Entity;
import io.taf.views.item.ItemDataView;
import jakarta.annotation.Nonnull;

@SuppressWarnings("rawtypes")
public abstract class AbstractItemDataCommandPanel<ITEM_DATA_VIEW extends ItemDataView>
        extends AbstractDataCommandPanel<ITEM_DATA_VIEW>
        implements ItemDataCommandPanel<ITEM_DATA_VIEW> {

    public static final String SAVE_COMMAND_COMPONENT_ID = "command-save";
    public static final String DOUBLE_SAVE_COMMAND_COMPONENT_ID = SAVE_COMMAND_COMPONENT_ID + "-double";
    public static final String SAVE_AND_CLOSE_COMMAND_COMPONENT_ID = "command-save-and-close";
    public static final String DOUBLE_SAVE_AND_CLOSE_COMMAND_COMPONENT_ID = SAVE_AND_CLOSE_COMMAND_COMPONENT_ID + "-double";
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
        return menu;
    }

    @Nonnull
    @Override
    protected MenuBar initMoreMenu() {
        var menu = super.initMoreMenu();
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
        var item = menuBar.addItem(getMessageProvider().getMessage("command.save.title", SAVE_COMMAND_DEFAULT_TITLE));
        item.setId(SAVE_COMMAND_COMPONENT_ID);
        initSaveCommandClickListener(item);
        return item;
    }

    @Nonnull
    protected MenuItem initSaveCommand(@Nonnull SubMenu subMenu) {
        var item = subMenu.addItem(getMessageProvider().getMessage("command.save.title", SAVE_COMMAND_DEFAULT_TITLE));
        item.setId(DOUBLE_SAVE_COMMAND_COMPONENT_ID);
        initSaveCommandClickListener(item);
        return item;
    }

    @SuppressWarnings("unchecked")
    protected void initSaveCommandClickListener(@Nonnull MenuItem item) {
        item.addClickListener(event -> {
            var binder = getView().getForm().getBinder();
            if (!binder.validate().isOk()) {
                return;
            }

            var bean = binder.getBean();
            var updatedBean = getView().getService().createOrUpdate((Entity) bean);

            binder.setBean(updatedBean);

            // Update current URL
//                        if (ItemViewType.COPY.equals(itemViewType)) {
//                            var newRoute = RouteConfiguration.forSessionScope().getUrl(getClass(),
//                                    new RouteParameters(
//                                            new RouteParam(NavigateUtils.URL_PARAM_ID_ALIAS, updatedBean.getIdAsString()),
//                                            new RouteParam(NavigateUtils.URL_PARAM_ACTION_ALIAS, NavigateUtils.URL_PARAM_ACTION_VALUE_EDIT)
//                                    ));
//                            UI.getCurrent().getPage().getHistory().pushState(null, newRoute);
//                        }

            // Show notification
            var notification = getMessageProvider().getMessage("command.save.notification.success");
            Notification.show(notification, 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
    }

    @Nonnull
    @Override
    public final MenuItem getSaveAndCloseCommand() {
        return getComponent(SAVE_AND_CLOSE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initSaveAndCloseCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(getMessageProvider().getMessage("command.save.and.close.title", SAVE_AND_CLOSE_COMMAND_DEFAULT_TITLE));
        item.setId(SAVE_AND_CLOSE_COMMAND_COMPONENT_ID);
        item.addClassNames(LumoUtility.TextColor.PRIMARY_CONTRAST, LumoUtility.Background.PRIMARY);
        initSaveAndCloseCommandClickListener(item);
        return item;
    }

    @Nonnull
    protected MenuItem initSaveAndCloseCommand(@Nonnull SubMenu subMenu) {
        var item = subMenu.addItem(getMessageProvider().getMessage("command.save.and.close.title", SAVE_AND_CLOSE_COMMAND_DEFAULT_TITLE));
        item.setId(DOUBLE_SAVE_AND_CLOSE_COMMAND_COMPONENT_ID);
        initSaveAndCloseCommandClickListener(item);
        return item;
    }

    @SuppressWarnings("unchecked")
    protected void initSaveAndCloseCommandClickListener(@Nonnull MenuItem item) {
        item.addClickListener(event -> {
            var binder = getView().getForm().getBinder();
            if (!binder.validate().isOk()) {
                return;
            }

            var bean = binder.getBean();
            var updatedBean = getView().getService().createOrUpdate((Entity) bean);

            binder.setBean(updatedBean);

            UI.getCurrent().navigate(getView().getListDataViewClass());

            // Show notification
            var notification = getMessageProvider().getMessage("command.save.and.close.notification.success");
            Notification.show(notification, 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
    }

    @Nonnull
    @Override
    public final MenuItem getDeleteCommand() {
        return getComponent(DELETE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    protected MenuItem initDeleteCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(getMessageProvider().getMessage("command.delete.title", DELETE_COMMAND_DEFAULT_TITLE));
        item.setId(DELETE_COMMAND_COMPONENT_ID);
        item.addClassNames(LumoUtility.TextColor.WARNING);
        item.addClickListener(event -> {

            var binder = getView().getForm().getBinder();
            var bean = (Entity) binder.getBean();
            if (bean.isNew()) {
                return;
            }

            getView().getService().delete(bean);

            UI.getCurrent().navigate(getView().getListDataViewClass());

            // Show notification
            var notification = getMessageProvider().getMessage("commands.delete.notification.success");
            Notification.show(notification, 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        return item;
    }

    @Nonnull
    @Override
    public final MenuItem getCloseCommand() {
        return getComponent(CANCEL_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    protected MenuItem initCancelCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(getMessageProvider().getMessage("command.title.cancel", CANCEL_COMMAND_DEFAULT_TITLE));
        item.setId(CANCEL_COMMAND_COMPONENT_ID);
        item.addClickListener(event -> UI.getCurrent().navigate(getView().getListDataViewClass()));
        return item;
    }

    @Nonnull
    @Override
    public final MenuItem getMoreCommand() {
        return getComponent(MORE_COMMAND_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    protected MenuItem initMoreCommand(@Nonnull MenuBar menuBar) {
        var item = menuBar.addItem(getMessageProvider().getMessage("command.title.more", MORE_COMMAND_DEFAULT_TITLE));
        item.setId(MORE_COMMAND_COMPONENT_ID);

        var subMenu = item.getSubMenu();

        addComponent(DOUBLE_SAVE_AND_CLOSE_COMMAND_COMPONENT_ID, initSaveAndCloseCommand(subMenu));
        addComponent(DOUBLE_SAVE_COMMAND_COMPONENT_ID, initSaveCommand(subMenu));
        subMenu.addSeparator();
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
    @SuppressWarnings("unchecked")
    protected MenuItem initToggleDeletionMarkCommand(@Nonnull SubMenu subMenu) {
        var item = subMenu.addItem(getMessageProvider().getMessage("command.toggle.deletion.mark.title", TOGGLE_DELETION_MARK_COMMAND_DEFAULT_TITLE));
        item.setId(TOGGLE_DELETION_MARK_COMMAND_COMPONENT_ID);
        item.addClickListener(event -> {

            var binder = getView().getForm().getBinder();
            var bean = (DataEntity) binder.getBean();
            if (bean.isNew()) {
                return;
            }

            getView().getService().toggleDeletionMark(bean);
            binder.refreshFields();

            var notificationText = bean.isDeletionMark()
                    ? getMessageProvider().getMessage("command.toggle.deletion.mark.notification.success.marked")
                    : getMessageProvider().getMessage("command.toggle.deletion.mark.notification.success.unmarked");

            Notification.show(notificationText, 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        });
        return item;
    }

    @Nonnull
    @Override
    public MenuItem getToggleDraftMarkCommand() {
        return getComponent(TOGGLE_DRAFT_MARK_COMPONENT_ID, MenuItem.class);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    protected MenuItem initToggleDraftMarkCommand(@Nonnull SubMenu subMenu) {
        var item = subMenu.addItem(getMessageProvider().getMessage("command.toggle.draft.mark.title", TOGGLE_DRAFT_MARK_COMMAND_DEFAULT_TITLE));
        item.setId(TOGGLE_DRAFT_MARK_COMPONENT_ID);
        item.addClickListener(event -> {

            var binder = getView().getForm().getBinder();
            var bean = (DataEntity) binder.getBean();
            if (bean.isNew()) {
                return;
            }

            getView().getService().toggleDraftMark(bean);
            binder.refreshFields();

            var notificationText = bean.isDeletionMark()
                    ? getMessageProvider().getMessage("command.toggle.draft.mark.notification.success.marked")
                    : getMessageProvider().getMessage("command.toggle.draft.mark.notification.success.unmarked");

            Notification.show(notificationText, 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        });
        return item;
    }


}
