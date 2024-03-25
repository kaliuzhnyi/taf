package io.taf.views.form;

import io.taf.entity.DataEntity;

import java.io.Serializable;

public interface DataForm<ENTITY extends DataEntity<? extends Serializable>>
        extends Form<ENTITY> {
}
