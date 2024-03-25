package io.taf.app.catalogs.clients;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.taf.catalogs.AbstractCatalogItemView;
import io.taf.layouts.DefaultAppLayout;

@Route(value = "clients/:id", layout = DefaultAppLayout.class)
@PageTitle("Client")
public class ClientsItemView
        extends AbstractCatalogItemView<ClientsEntity, Long, ClientsListView> {
}
