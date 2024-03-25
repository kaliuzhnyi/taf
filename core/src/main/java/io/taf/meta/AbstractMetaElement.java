package io.taf.meta;

import com.vaadin.flow.component.Component;
import io.taf.utils.GenericUtils;
import io.taf.views.common.View;
import jakarta.annotation.Nonnull;
import lombok.Getter;

public abstract class AbstractMetaElement<VIEW extends Component & View>
        implements MetaElement<VIEW> {

    @Getter(onMethod_ = {@Nonnull}, lazy = true)
    private final Class<VIEW> viewClass = GenericUtils.getType(getClass(), AbstractMetaElement.class, 0);

}
