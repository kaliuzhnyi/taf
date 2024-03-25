package io.taf.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatalogsHolder {

    @Autowired
    private final List<CatalogMetaElement> catalogMetaElements = new ArrayList<>();

    public List<CatalogMetaElement> getSections() {
        catalogMetaElements.sort(OrderComparator.INSTANCE);
        return catalogMetaElements;
    }

}
