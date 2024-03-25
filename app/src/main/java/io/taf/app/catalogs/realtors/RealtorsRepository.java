package io.taf.app.catalogs.realtors;

import io.taf.repository.CatalogRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtorsRepository
        extends CatalogRepository<RealtorsEntity, Long> {
}
