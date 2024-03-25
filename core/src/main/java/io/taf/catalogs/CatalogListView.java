package io.taf.catalogs;

import com.vaadin.flow.component.Component;
import io.taf.views.list.ListDataView;

import java.io.Serializable;

public interface CatalogListView<ENTITY extends CatalogEntity<ID>, ID extends Serializable,
        ITEM_DATA_VIEW extends Component & CatalogItemView<ENTITY, ID, ? extends CatalogListView<ENTITY, ID, ITEM_DATA_VIEW>>>
        extends ListDataView<ENTITY, ID, ITEM_DATA_VIEW> {
}
