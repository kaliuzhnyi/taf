package io.taf.meta;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.OrderUtils;

public interface MetaObject extends Ordered {

    @Nonnull
    String getTitle();

    @Nullable
    default String getDescription() {
        return null;
    }

    @Nullable
    default String getTooltip() {
        return getDescription();
    }

    @Override
    default int getOrder() {
        var order = OrderUtils.getOrder(getClass());
        return order == null ? Ordered.LOWEST_PRECEDENCE : order;
    }

}
