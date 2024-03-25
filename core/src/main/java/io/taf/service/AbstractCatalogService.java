package io.taf.service;

import io.taf.catalogs.CatalogEntity;
import io.taf.repository.CatalogRepository;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

public abstract class AbstractCatalogService<ENTITY extends CatalogEntity<ID>, ID extends Serializable>
        extends AbstractDataService<ENTITY, ID>
        implements CatalogService<ENTITY, ID> {

    @Nonnull
    @Override
    public CatalogRepository<ENTITY, ID> getRepository() {
        return (CatalogRepository<ENTITY, ID>) super.getRepository();
    }
}
