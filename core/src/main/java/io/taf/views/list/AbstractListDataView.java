package io.taf.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import io.taf.entity.DataEntity;
import io.taf.utils.GenericUtils;
import io.taf.utils.NavigateUtils;
import io.taf.utils.grid.GridUtils;
import io.taf.views.common.AbstractDataView;
import io.taf.views.item.ItemDataView;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.io.Serializable;
import java.util.Optional;

public abstract class AbstractListDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        ITEM_DATA_VIEW extends Component & ItemDataView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW>,
        LIST_DATA_VIEW extends Component & ListDataView<ENTITY, ID, ITEM_DATA_VIEW, LIST_DATA_VIEW>>
        extends AbstractDataView<ENTITY, ID>
        implements ListDataView<ENTITY, ID, ITEM_DATA_VIEW, LIST_DATA_VIEW> {

    public static final String GRID_COMPONENT_ID = "grid";


    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ITEM_DATA_VIEW> itemDataViewClass = GenericUtils.getType(getClass(), AbstractListDataView.class, 2);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Grid<ENTITY> grid = initGrid();


    @Nonnull
    protected Grid<ENTITY> initGrid() {
        var grid = GridUtils.createBasedOnEntity(getEntityClass());
        grid.setId(GRID_COMPONENT_ID);
        grid.setSizeFull();
        grid.addItemDoubleClickListener(event -> navigateToItem(event.getItem()));
        return grid;
    }

    protected void initGridContent() {
        getGrid().setItems(query -> getService().list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    protected void navigateToItem(@Nonnull ENTITY item) {
        Optional.of(item).map(ENTITY::getId)
                .ifPresent(id ->
                        UI.getCurrent().navigate(getItemDataViewClass(),
                                new RouteParam(NavigateUtils.URL_PARAM_ID_ALIAS, id.toString())));
    }

    @Nonnull
    @Override
    protected HorizontalLayout initBodyPanel() {
        var bodyPanel = super.initBodyPanel();
        bodyPanel.add(getGrid());
        initGridContent();
        return bodyPanel;
    }
}
