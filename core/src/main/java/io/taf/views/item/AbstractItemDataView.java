package io.taf.views.item;

import com.vaadin.flow.component.Component;
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

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
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
        var result = (AbstractItemDataCommandPanel) Optional.ofNullable(commandPanel)
                .orElseGet(() -> getBeanFactory().createBean(DefaultItemDataCommandPanel.class));
        result.setView(this);
        return result;
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
