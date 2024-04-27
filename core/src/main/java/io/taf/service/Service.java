package io.taf.service;

import io.taf.entity.Entity;
import io.taf.repository.Repository;
import jakarta.annotation.Nonnull;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

/**
 * Defines a generic service interface for managing entities. This interface provides methods
 * for common CRUD operations and supports transactional execution where applicable.
 *
 * @param <ENTITY> The entity type this service manages. Must extend {@link Entity}.
 * @param <ID>     The type of the entity's identifier. Must implement {@link Serializable}.
 */
public interface Service<ENTITY extends Entity<ID>, ID extends Serializable> {

    /**
     * Provides access to the repository associated with the ENTITY type.
     *
     * @return The repository instance.
     */
    @Nonnull
    Repository<ENTITY, ID> getRepository();

    /**
     * Gets the class of the entity managed by this service.
     *
     * @return The entity class.
     */
    @Nonnull
    Class<ENTITY> getEntityClass();

    /**
     * Gets the class of the entity's identifier.
     *
     * @return The ID class.
     */
    @Nonnull
    Class<ID> getIdClass();

    /**
     * Creates a new entity in the repository.
     *
     * @param entity The entity to create.
     * @return The saved entity with updated state (e.g., assigned identifier).
     */
    @Transactional
    @Nonnull
    default ENTITY create(@Nonnull ENTITY entity) {
        return getRepository().save(entity);
    }

    /**
     * Creates or updates an entity.
     *
     * @param entity The entity to be created or updated.
     * @return The created or updated entity.
     */
    @Transactional
    default ENTITY createOrUpdate(@Nonnull ENTITY entity) {
        if (entity.isNew()) {
            return create(entity);
        } else {
            return update(entity);
        }
    }

    /**
     * Retrieves an entity by its identifier. Throws an exception if no entity found.
     *
     * @param id The identifier of the entity to retrieve.
     * @return The found entity.
     */
    @Nonnull
    default ENTITY read(@Nonnull ID id) {
        return getRepository().getReferenceById(id);
    }

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity The entity to update.
     * @return The updated entity.
     */
    @Transactional
    @Nonnull
    default ENTITY update(@Nonnull ENTITY entity) {
        return getRepository().save(entity);
    }

    /**
     * Deletes an entity by its identifier.
     *
     * @param id The identifier of the entity to delete.
     */
    @Transactional
    default void delete(@Nonnull ID id) {
        getRepository().deleteById(id);
    }

    /**
     * Deletes a given entity.
     *
     * @param entity The entity to delete.
     */
    @Transactional
    default void delete(@Nonnull ENTITY entity) {
        getRepository().delete(entity);
    }

    /**
     * Finds an entity by its identifier.
     *
     * @param id The identifier of the entity to find.
     * @return An Optional containing the found entity or empty if not found.
     */
    @Nonnull
    default Optional<ENTITY> find(@Nonnull ID id) {
        return getRepository().findById(id);
    }

    /**
     * Converts a String to the ID type of the entity using reflection. This method assumes the ID
     * class has a static method named "valueOf" that accepts a String and returns an instance of the ID class.
     *
     * @param id The String representation of the ID.
     * @return The ID converted from the String.
     * @throws ReflectiveOperationException If the reflection call fails.
     */
    @Nonnull
    @SneakyThrows
    @SuppressWarnings("unchecked")
    default ID convertId(@Nonnull String id) {
        var method = getIdClass().getMethod("valueOf", String.class);
        return (ID) method.invoke(null, id);
    }

}
