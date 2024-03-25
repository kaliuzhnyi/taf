package io.taf.views.form;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.taf.entity.DataEntity;
import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.io.Serializable;

public abstract class AbstractItemDataForm<ENTITY extends DataEntity<? extends Serializable>>
        extends AbstractDataForm<ENTITY>
        implements ItemDataForm<ENTITY> {

    public static final String SECONDARY_GROUP_COMPONENT_ID = "secondary-group";

    @Getter(onMethod_ = {@Override, @Nonnull}, lazy = true)
    private final VerticalLayout secondaryGroup = initSecondaryGroup();

    @Nonnull
    private VerticalLayout initSecondaryGroup() {
        var component = new VerticalLayout();
        component.setId(SECONDARY_GROUP_COMPONENT_ID);
        component.setSizeFull();
        return component;
    }

    @Override
    public void init() {
        super.init();
        add(getSecondaryGroup());
    }

}
