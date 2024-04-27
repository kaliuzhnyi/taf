package io.taf.meta;

import com.vaadin.flow.component.Component;
import io.taf.entity.DataEntity;
import io.taf.service.DataService;
import io.taf.views.item.ItemDataView;
import io.taf.views.list.ListDataView;

import java.io.Serializable;

public interface DataMetaElement<
        ITEM_VIEW extends Component & ItemDataView<ENTITY, ID, LIST_VIEW, ITEM_VIEW>,
        LIST_VIEW extends Component & ListDataView<ENTITY, ID, ITEM_VIEW, LIST_VIEW>,
        ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends MetaElement<LIST_VIEW> {

    Class<ITEM_VIEW> getItemViewClass();

    Class<LIST_VIEW> getListViewClass();

    Class<ENTITY> getEntityClass();

    Class<ID> getIdClass();

    DataService<ENTITY, ID> getService();

    @Override
    default Class<LIST_VIEW> getViewClass() {
        return getListViewClass();
    }

}
