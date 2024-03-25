package io.taf.app.catalogs.clients;

import io.taf.repository.CatalogRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository
        extends CatalogRepository<ClientsEntity, Long> {
}
