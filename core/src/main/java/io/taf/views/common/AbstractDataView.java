package io.taf.views.common;

import io.taf.entity.DataEntity;
import io.taf.service.DataService;
import io.taf.utils.GenericUtils;
import io.taf.utils.MessageProvider;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractDataView<ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends AbstractView
        implements DataView<ENTITY, ID> {

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ENTITY> entityClass = GenericUtils.getType(getClass(), AbstractDataView.class, 0);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ID> idClass = GenericUtils.getType(getClass(), AbstractDataView.class, 1);

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private DataService<ENTITY, ? extends Serializable> service;

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private MessageProvider messageProvider;

}
