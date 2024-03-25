package io.taf.service;

import io.taf.entity.DataEntity;
import io.taf.repository.DataRepository;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

public abstract class AbstractDataService<ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends AbstractService<ENTITY, ID>
        implements DataService<ENTITY, ID> {

    @Nonnull
    @Override
    public DataRepository<ENTITY, ID> getRepository() {
        return (DataRepository<ENTITY, ID>) super.getRepository();
    }
}
