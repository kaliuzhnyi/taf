package io.taf.app.catalogs.clients;

import io.taf.app.sections.CatalogsSection;
import io.taf.catalogs.AbstractCatalogMetaElement;
import io.taf.catalogs.CatalogMetaComponent;
import io.taf.sections.AssignSection;
import jakarta.annotation.Nonnull;
import org.springframework.core.annotation.Order;

@CatalogMetaComponent
@Order(10)
@AssignSection(CatalogsSection.class)
public class ClientsMetaElement
        extends AbstractCatalogMetaElement<ClientsItemView, ClientsListView, ClientsEntity, Long> {

    @Nonnull
    @Override
    public String getTitle() {
        return "Clients";
    }

}
