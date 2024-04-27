package io.taf.views.item;

import com.vaadin.flow.component.Component;
import io.taf.entity.DataEntity;
import io.taf.views.common.DataView;
import io.taf.views.form.Form;
import io.taf.views.list.ListDataView;
import io.taf.views.panel.AbstractItemDataCommandPanel;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

public interface ItemDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        LIST_DATA_VIEW extends Component & ListDataView<ENTITY, ID, ITEM_DATA_VIEW, LIST_DATA_VIEW>,
        ITEM_DATA_VIEW extends Component & ItemDataView<ENTITY, ID, LIST_DATA_VIEW, ITEM_DATA_VIEW>>
        extends DataView<ENTITY, ID> {

    @Nonnull
    Class<LIST_DATA_VIEW> getListDataViewClass();

    @Nonnull
    Form<ENTITY> getForm();

    @Nonnull
    @Override
    AbstractItemDataCommandPanel getCommandPanel();
}
