package io.taf.views.common;

import io.taf.entity.DataEntity;
import io.taf.service.DataService;
import io.taf.utils.GenericUtils;
import io.taf.utils.MessageProvider;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.io.Serializable;

public abstract class AbstractDataView<ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends AbstractView
        implements DataView<ENTITY, ID> {

    @Getter(value = AccessLevel.PUBLIC, onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ENTITY> entityClass = GenericUtils.getType(getClass(), AbstractDataView.class, 0);

    @Getter(value = AccessLevel.PUBLIC, onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ID> idClass = GenericUtils.getType(getClass(), AbstractDataView.class, 1);

    @Autowired
    @Getter(value = AccessLevel.PUBLIC, onMethod_ = {@Override, @Nonnull})
    private DataService<ENTITY, ID> service;

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private MessageProvider messageProvider;

    @Autowired
    @Getter(value = AccessLevel.PROTECTED, onMethod_ = {@Nonnull})
    private AutowireCapableBeanFactory beanFactory;

}
