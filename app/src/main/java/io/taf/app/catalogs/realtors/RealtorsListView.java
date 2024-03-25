package io.taf.app.catalogs.realtors;

import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import io.taf.catalogs.AbstractCatalogListView;
import io.taf.layouts.DefaultAppLayout;

@Route(value = "realtors", layout = DefaultAppLayout.class)
@PreserveOnRefresh
public class RealtorsListView
        extends AbstractCatalogListView<RealtorsEntity, Long, RealtorsItemView> {
}
