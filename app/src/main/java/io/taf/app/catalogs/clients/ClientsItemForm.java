package io.taf.app.catalogs.clients;

import io.taf.catalogs.AbstractCatalogItemForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientsItemForm
        extends AbstractCatalogItemForm<ClientsEntity> {
}
