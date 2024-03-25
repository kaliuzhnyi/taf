package io.taf.catalogs;

import io.taf.views.form.ItemDataForm;

import java.io.Serializable;

public interface CatalogItemForm<ENTITY extends CatalogEntity<? extends Serializable>>
        extends ItemDataForm<ENTITY> {
}
