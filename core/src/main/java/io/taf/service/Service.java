package io.taf.service;

import io.taf.entity.Entity;
import io.taf.repository.Repository;
import jakarta.annotation.Nonnull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.util.Optional;

public interface Service<ENTITY extends Entity<ID>, ID extends Serializable> {

    @Nonnull
    Repository<ENTITY, ID> getRepository();

    @Nonnull
    Class<ENTITY> getEntityClass();

    @Nonnull
    Class<ID> getIdClass();


    @Transactional
    @Nonnull
    default ENTITY create(@Nonnull ENTITY entity) {
        return getRepository().save(entity);
    }

    @Nonnull
    default ENTITY read(@Nonnull ID id) {
        return getRepository().getReferenceById(id);
    }

    @Transactional
    @Nonnull
    default ENTITY update(@Nonnull ENTITY entity) {
        return getRepository().save(entity);
    }

    @Transactional
    default void delete(@Nonnull ID id) {
        getRepository().deleteById(id);
    }

    @Transactional
    default void delete(@Nonnull ENTITY entity) {
        getRepository().delete(entity);
    }


    @Nonnull
    default Optional<ENTITY> find(@Nonnull ID id) {
        return getRepository().findById(id);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    default Optional<ENTITY> find(@Nonnull String id) {
        return Optional.ofNullable(ReflectionUtils.findMethod(getIdClass(), "valueOf"))
                .map(method -> ReflectionUtils.invokeMethod(method, id))
                .flatMap(v -> find((ID) v));
    }

}
