package io.taf.views.common;

import io.taf.entity.DataEntity;
import io.taf.service.DataService;
import jakarta.annotation.Nonnull;
import lombok.SneakyThrows;

import java.io.Serializable;

public interface DataView<ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends View {

    @Nonnull
    Class<ENTITY> getEntityClass();

    @Nonnull
    Class<ID> getIdClass();

    @Nonnull
    DataService<ENTITY, ID> getService();

    @Nonnull
    @SneakyThrows
    default ENTITY getEntityInstance() {
        return getEntityClass().getConstructor().newInstance();
    }

}
