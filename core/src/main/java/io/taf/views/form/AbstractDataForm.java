package io.taf.views.form;

import io.taf.entity.DataEntity;

import java.io.Serializable;

public abstract class AbstractDataForm<ENTITY extends DataEntity<? extends Serializable>>
        extends AbstractForm<ENTITY>
        implements DataForm<ENTITY> {
}
