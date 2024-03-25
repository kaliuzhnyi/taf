package io.taf.app.catalogs.realtors;

import io.taf.catalogs.AbstractCatalogItemForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RealtorsItemForm
        extends AbstractCatalogItemForm<RealtorsEntity> {
}
