package io.taf.meta;

import com.vaadin.flow.component.Component;
import io.taf.entity.DataEntity;
import io.taf.service.DataService;
import io.taf.utils.GenericUtils;
import io.taf.views.item.ItemDataView;
import io.taf.views.list.ListDataView;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractDataMetaElement<
        ITEM_VIEW extends Component & ItemDataView<ENTITY, ID, LIST_VIEW, ITEM_VIEW>,
        LIST_VIEW extends Component & ListDataView<ENTITY, ID, ITEM_VIEW, LIST_VIEW>,
        ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends AbstractMetaElement<LIST_VIEW>
        implements DataMetaElement<ITEM_VIEW, LIST_VIEW, ENTITY, ID> {

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ITEM_VIEW> itemViewClass = GenericUtils.getType(getClass(), AbstractDataMetaElement.class, 0);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<LIST_VIEW> listViewClass = GenericUtils.getType(getClass(), AbstractDataMetaElement.class, 1);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ENTITY> entityClass = GenericUtils.getType(getClass(), AbstractDataMetaElement.class, 2);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ID> idClass = GenericUtils.getType(getClass(), AbstractDataMetaElement.class, 3);

    @Autowired
    @Getter(onMethod_ = {@Nonnull})
    private DataService<ENTITY, ID> service;

}
