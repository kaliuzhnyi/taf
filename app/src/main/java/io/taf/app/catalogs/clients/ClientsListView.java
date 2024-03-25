package io.taf.app.catalogs.clients;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import io.taf.catalogs.AbstractCatalogListView;
import io.taf.layouts.DefaultAppLayout;

@Route(value = "clients", layout = DefaultAppLayout.class)
@PageTitle("Clients")
public class ClientsListView
        extends AbstractCatalogListView<ClientsEntity, Long, ClientsItemView> {
}
