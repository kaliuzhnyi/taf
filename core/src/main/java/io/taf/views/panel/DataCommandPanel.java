package io.taf.views.panel;

import io.taf.views.common.DataView;
import jakarta.annotation.Nonnull;

@SuppressWarnings("rawtypes")
public interface DataCommandPanel<DATA_VIEW extends DataView>
        extends CommandPanel {

    void setView(@Nonnull DATA_VIEW view);

    @Nonnull
    DATA_VIEW getView();

}
