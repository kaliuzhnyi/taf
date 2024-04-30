package io.taf.views.panel;

import io.taf.views.common.DataView;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
public abstract class AbstractDataCommandPanel<DATA_VIEW extends DataView>
        extends AbstractCommandPanel
        implements DataCommandPanel<DATA_VIEW> {

    @Setter(onMethod_ = {@Override, @Nonnull})
    @Getter(onMethod_ = {@Override, @Nonnull})
    private DATA_VIEW view;

}
