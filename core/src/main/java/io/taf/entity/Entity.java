package io.taf.entity;

import jakarta.annotation.Nullable;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * Represents a generic entity interface with an ID that is serializable.
 * This interface is intended to be implemented by domain entities that will be persisted.
 * Extends the {@link Persistable} interface, providing additional methods to aid in persistence.
 *
 * @param <ID> the type of the identifier, which must extend Serializable
 */
public interface Entity<ID extends Serializable>
        extends Persistable<ID> {

    Class<ID> getIdClass();

    /**
     * Gets the ID of the entity. Can be null for a new entity that hasn't been persisted yet.
     *
     * @return the ID of the entity, or null if it is a new entity
     */
    @Nullable
    @Override
    ID getId();

    /**
     * Sets the ID of the entity. This can be used to update the ID of an existing entity
     * or to set the ID for a new entity prior to persistence.
     *
     * @param id the ID to set for the entity
     */
    void setId(@Nullable ID id);

    /**
     * Determines if the entity is new or has been persisted.
     * An entity is considered new if its ID is null.
     *
     * @return true if the entity is new, false otherwise
     */
    @Override
    default boolean isNew() {
        return getId() == null;
    }

}