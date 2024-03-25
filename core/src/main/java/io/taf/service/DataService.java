package io.taf.service;

import io.taf.entity.DataEntity;
import io.taf.repository.DataRepository;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;

public interface DataService<ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends Service<ENTITY, ID> {

    @Nonnull
    @Override
    DataRepository<ENTITY, ID> getRepository();

    @Nonnull
    default Page<ENTITY> list(@Nonnull Pageable pageable) {
        return list(pageable, null);
    }

    @Nonnull
    default Page<ENTITY> list(@Nonnull Pageable pageable, @Nullable String filter) {
        return Optional.ofNullable(filter).filter(String::isBlank)
                .map(s -> getRepository().findAllBy(pageable, filter))
                .orElseGet(() -> getRepository().findAll(pageable));
    }

    default void toggleDeletionMark(ENTITY entity) {
        entity.toggleDeletionMark();
        update(entity);
    }

    default void toggleDraftMark(ENTITY entity) {
        entity.toggleDraftMark();
        update(entity);
    }

}
