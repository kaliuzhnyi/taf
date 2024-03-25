package io.taf.catalogs;

import io.taf.entity.DataEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;

public interface CatalogEntity<ID extends Serializable>
        extends DataEntity<ID> {

    @Nonnull
    String getCode();

    void setCode(@Nonnull String code);

    @Nonnull
    String getTitle();

    void setTitle(@Nonnull String title);

    @Nullable
    String getComment();

    void setComment(@Nullable String comment);

}
