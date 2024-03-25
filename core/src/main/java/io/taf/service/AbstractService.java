package io.taf.service;

import io.taf.entity.Entity;
import io.taf.repository.Repository;
import io.taf.utils.GenericUtils;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractService<ENTITY extends Entity<ID>, ID extends Serializable>
        implements Service<ENTITY, ID> {

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ENTITY> entityClass = GenericUtils.getType(getClass(), AbstractService.class, 0);

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final Class<ID> idClass = GenericUtils.getType(getClass(), AbstractService.class, 1);

    @Autowired
    @Getter(onMethod_ = {@Override, @Nonnull})
    private Repository<ENTITY, ID> repository;
    
}
