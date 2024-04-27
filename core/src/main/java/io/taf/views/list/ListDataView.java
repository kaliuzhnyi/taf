package io.taf.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import io.taf.entity.DataEntity;
import io.taf.views.common.DataView;
import io.taf.views.item.ItemDataView;
import io.taf.views.panel.AbstractCommandPanel;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;

public interface ListDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        ITEM_DATA_VIEW extends Component & ItemDataView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW>,
        LIST_DATA_VIEW extends Component & ListDataView<ENTITY, ID, ITEM_DATA_VIEW, LIST_DATA_VIEW>>
        extends DataView<ENTITY, ID> {

    @Nonnull
    Class<ITEM_DATA_VIEW> getItemDataViewClass();

    @Nonnull
    Grid<ENTITY> getGrid();

    @Nullable
    @Override
    default AbstractCommandPanel getCommandPanel() {
        return null;
    }

}
