package io.taf.views.item;

import com.vaadin.flow.component.Component;
import io.taf.entity.DataEntity;
import io.taf.views.common.DataView;
import io.taf.views.form.Form;
import io.taf.views.list.ListDataView;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

public interface ItemDataView<ENTITY extends DataEntity<ID>, ID extends Serializable,
        LIST_DATA_VIEW extends Component & ListDataView<ENTITY, ID, ? extends ItemDataView<ENTITY, ID, LIST_DATA_VIEW>>>
        extends DataView<ENTITY, ID> {

    @Nonnull
    Class<LIST_DATA_VIEW> getListDataViewClass();

    @Nonnull
    Form<ENTITY> getForm();

}
