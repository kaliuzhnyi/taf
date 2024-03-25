package io.taf.catalogs;

import com.vaadin.flow.component.Component;
import io.taf.meta.DataMetaElement;

import java.io.Serializable;

public interface CatalogMetaElement<ITEM_VIEW extends Component & CatalogItemView<ENTITY, ID, LIST_VIEW>,
        LIST_VIEW extends Component & CatalogListView<ENTITY, ID, ITEM_VIEW>,
        ENTITY extends CatalogEntity<ID>, ID extends Serializable>
        extends DataMetaElement<ITEM_VIEW, LIST_VIEW, ENTITY, ID> {
}
