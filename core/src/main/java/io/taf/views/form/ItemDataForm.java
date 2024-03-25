package io.taf.views.form;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.taf.entity.DataEntity;

import java.io.Serializable;

public interface ItemDataForm<ENTITY extends DataEntity<? extends Serializable>>
        extends DataForm<ENTITY> {

    VerticalLayout getSecondaryGroup();

}
