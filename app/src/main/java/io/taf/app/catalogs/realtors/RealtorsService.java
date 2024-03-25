package io.taf.app.catalogs.realtors;

import io.taf.app.catalogs.realtors.RealtorsEntity;
import io.taf.service.AbstractCatalogService;
import org.springframework.stereotype.Service;

@Service
public class RealtorsService
        extends AbstractCatalogService<RealtorsEntity, Long> {
}
