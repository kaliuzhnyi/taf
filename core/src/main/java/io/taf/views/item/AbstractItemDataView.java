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
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractItemDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        LIST_DATA_VIEW extends Component & ListDataView<ENTITY, ID, ? extends ItemDataView<ENTITY, ID, LIST_DATA_VIEW>>>
        extends AbstractDataView<ENTITY, ID>
        implements ItemDataView<ENTITY, ID, LIST_DATA_VIEW> {

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<LIST_DATA_VIEW> listDataViewClass = GenericUtils.getType(getClass(), AbstractItemDataView.class, 2);

    @Autowired
    @Getter(onMethod_ = {@Override, @Nonnull})
    private ItemDataForm<ENTITY> form;


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
                .flatMap(id -> getService().find(id))
                .or(() -> Optional.of(BeanUtils.instantiateClass(getEntityClass())))
                .ifPresent(entity -> {
                    getForm().getBinder().setBean(entity);
                });

    }
}
