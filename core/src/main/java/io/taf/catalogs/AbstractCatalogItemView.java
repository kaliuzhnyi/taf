package io.taf.catalogs;

import com.vaadin.flow.component.Component;
import io.taf.views.item.AbstractItemDataView;

import java.io.Serializable;

public abstract class AbstractCatalogItemView<ENTITY extends CatalogEntity<ID>, ID extends Serializable,
        LIST_DATA_VIEW extends Component & CatalogListView<ENTITY, ID, ? extends CatalogItemView<ENTITY, ID, LIST_DATA_VIEW>>>
        extends AbstractItemDataView<ENTITY, ID, LIST_DATA_VIEW>
        implements CatalogItemView<ENTITY, ID, LIST_DATA_VIEW> {
}
