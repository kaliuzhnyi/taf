package io.taf.views.item;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import io.taf.entity.DataEntity;
import io.taf.utils.GenericUtils;
import io.taf.utils.NavigateUtils;
import io.taf.views.common.AbstractDataView;
import io.taf.views.form.ItemDataForm;
import io.taf.views.list.ListDataView;
import io.taf.views.panel.AbstractItemDataCommandPanel;
import io.taf.views.panel.DefaultItemDataCommandPanel;
import io.taf.views.panel.ItemDataCommandPanel;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractItemDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        LIST_DATA_VIEW extends Component & ListDataView<ENTITY, ID, ITEM_DATA_VIEW, LIST_DATA_VIEW>,
        ITEM_DATA_VIEW extends Component & ItemDataView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW>>
        extends AbstractDataView<ENTITY, ID>
        implements ItemDataView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW> {

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<LIST_DATA_VIEW> listDataViewClass = GenericUtils.getType(getClass(), AbstractItemDataView.class, 2);

    @Autowired
    @Getter(onMethod_ = {@Override, @Nonnull})
    private ItemDataForm<ENTITY> form;

    @Autowired(required = false)
    private ItemDataCommandPanel<ITEM_DATA_VIEW> commandPanel;

    @Nonnull
    @Override
    public AbstractItemDataCommandPanel getCommandPanel() {
        return (AbstractItemDataCommandPanel) Objects.requireNonNull(super.getCommandPanel());
    }

    @Nonnull
    protected AbstractItemDataCommandPanel initCommandPanel() {
        return (AbstractItemDataCommandPanel) Optional.ofNullable(commandPanel)
                .orElseGet(() -> {
                    var commandPanel = getBeanFactory().createBean(DefaultItemDataCommandPanel.class);

                    // Save command
                    var saveCommand = commandPanel.getSaveCommand();
                    saveCommand.addClickListener(event -> {
                        if (!getForm().getBinder().validate().isOk()) {
                            return;
                        }

                        var bean = getForm().getBinder().getBean();
                        var updatedBean = getService().createOrUpdate(bean);

                        getForm().getBinder().setBean(updatedBean);

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

                    // Save and close command
                    var saveAndCloseCommand = commandPanel.getSaveAndCloseCommand();
                    saveAndCloseCommand.addClickListener(event -> {
                        if (!getForm().getBinder().validate().isOk()) {
                            return;
                        }

                        var bean = getForm().getBinder().getBean();
                        var updatedBean = getService().createOrUpdate(bean);

                        getForm().getBinder().setBean(updatedBean);

                        UI.getCurrent().navigate(getListDataViewClass());

                        // Show notification
                        var notification = getMessageProvider().getMessage("command.save.and.close.notification.success");
                        Notification.show(notification, 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    });

                    // Delete command
                    var deleteCommand = commandPanel.getDeleteCommand();
                    deleteCommand.addClickListener(event -> {

                        var bean = getForm().getBinder().getBean();
                        if (bean.isNew()) {
                            return;
                        }

                        getService().delete(bean);

                        UI.getCurrent().navigate(getListDataViewClass());

                        // Show notification
                        var notification = getMessageProvider().getMessage("commands.delete.notification.success");
                        Notification.show(notification, 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    });

                    // Close command
                    var closeCommand = commandPanel.getCloseCommand();
                    closeCommand.addClickListener(event -> {
                        UI.getCurrent().navigate(getListDataViewClass());
                    });

                    // Toggle deletion mark command
                    var toggleDeletionMarkCommand = commandPanel.getToggleDeletionMarkCommand();
                    toggleDeletionMarkCommand.addClickListener(event -> {

                        var bean = getForm().getBinder().getBean();
                        if (bean.isNew()) {
                            return;
                        }

                        getService().toggleDeletionMark(bean);
                        getForm().getBinder().refreshFields();

                        var notificationText = bean.isDeletionMark()
                                ? getMessageProvider().getMessage("command.toggle.deletion.mark.notification.success.marked")
                                : getMessageProvider().getMessage("command.toggle.deletion.mark.notification.success.unmarked");

                        Notification.show(notificationText, 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                    });

                    // Toddle draft mark
                    var draftMarkCommand = commandPanel.getToggleDraftMarkCommand();
                    draftMarkCommand.addClickListener(event -> {

                        var bean = getForm().getBinder().getBean();
                        if (bean.isNew()) {
                            return;
                        }

                        getService().toggleDraftMark(bean);
                        getForm().getBinder().refreshFields();

                        var notificationText = bean.isDeletionMark()
                                ? getMessageProvider().getMessage("command.toggle.draft.mark.notification.success.marked")
                                : getMessageProvider().getMessage("command.toggle.draft.mark.notification.success.unmarked");

                        Notification.show(notificationText, 3000, Notification.Position.TOP_CENTER)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                    });

                    return commandPanel;
                });
    }

    @Nonnull
    @Override
    protected HorizontalLayout initBodyPanel() {
        var component = super.initBodyPanel();
        component.add((Component) getForm());
        return component;
    }

    @Override
    @SneakyThrows
    public void beforeEnter(BeforeEnterEvent event) {
        super.beforeEnter(event);

        var params = event.getRouteParameters();

        params.get(NavigateUtils.URL_PARAM_ID_ALIAS)
                .flatMap(id -> getService().find(getService().convertId(id)))
                .or(() -> Optional.of(getBeanFactory().createBean(getEntityClass())))
                .ifPresent(entity -> getForm().getBinder().setBean(entity));

    }
}
