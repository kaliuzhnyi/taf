package io.taf.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import io.taf.entity.DataEntity;
import io.taf.views.common.DataView;
import io.taf.views.item.ItemDataView;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

public interface ListDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        ITEM_DATA_VIEW extends Component & ItemDataView<ENTITY, ID, ? extends ListDataView<ENTITY, ID, ITEM_DATA_VIEW>>>
        extends DataView<ENTITY, ID> {

    @Nonnull
    Class<ITEM_DATA_VIEW> getItemDataViewClass();

    @Nonnull
    Grid<ENTITY> getGrid();

}
