package io.taf.catalogs;

import com.vaadin.flow.component.Component;
import io.taf.views.item.ItemDataView;

import java.io.Serializable;

public interface CatalogItemView<ENTITY extends CatalogEntity<ID>, ID extends Serializable,
        LIST_DATA_VIEW extends Component & CatalogListView<ENTITY, ID, ITEM_DATA_VIEW, LIST_DATA_VIEW>,
        ITEM_DATA_VIEW extends Component & CatalogItemView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW>>
        extends ItemDataView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW> {
}
