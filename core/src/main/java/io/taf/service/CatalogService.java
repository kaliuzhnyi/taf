package io.taf.service;

import io.taf.catalogs.CatalogEntity;
import io.taf.repository.CatalogRepository;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

public interface CatalogService<ENTITY extends CatalogEntity<ID>, ID extends Serializable>
        extends DataService<ENTITY, ID> {

    @Nonnull
    @Override
    CatalogRepository<ENTITY, ID> getRepository();

}
