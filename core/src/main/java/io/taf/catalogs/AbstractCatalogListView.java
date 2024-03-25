package io.taf.catalogs;

import com.vaadin.flow.component.Component;
import io.taf.views.list.AbstractListDataView;

import java.io.Serializable;

public abstract class AbstractCatalogListView<ENTITY extends CatalogEntity<ID>, ID extends Serializable,
        ITEM_DATA_VIEW extends Component & CatalogItemView<ENTITY, ID, ? extends CatalogListView<ENTITY, ID, ITEM_DATA_VIEW>>>
        extends AbstractListDataView<ENTITY, ID, ITEM_DATA_VIEW>
        implements CatalogListView<ENTITY, ID, ITEM_DATA_VIEW> {
}
