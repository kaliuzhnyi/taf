package io.taf.catalogs;

import io.taf.views.form.AbstractItemDataForm;

import java.io.Serializable;

public abstract class AbstractCatalogItemForm<ENTITY extends CatalogEntity<? extends Serializable>>
        extends AbstractItemDataForm<ENTITY> implements
        CatalogItemForm<ENTITY> {
}
