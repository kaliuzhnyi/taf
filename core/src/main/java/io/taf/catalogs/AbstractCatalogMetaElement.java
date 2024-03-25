package io.taf.catalogs;

import com.vaadin.flow.component.Component;
import io.taf.meta.AbstractDataMetaElement;

import java.io.Serializable;

public abstract class AbstractCatalogMetaElement<ITEM_VIEW extends Component & CatalogItemView<ENTITY, ID, LIST_VIEW>,
        LIST_VIEW extends Component & CatalogListView<ENTITY, ID, ITEM_VIEW>,
        ENTITY extends CatalogEntity<ID>, ID extends Serializable>
        extends AbstractDataMetaElement<ITEM_VIEW, LIST_VIEW, ENTITY, ID>
        implements CatalogMetaElement<ITEM_VIEW, LIST_VIEW, ENTITY, ID> {
}
