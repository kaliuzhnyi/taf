package io.taf.entity;


import java.io.Serializable;

public interface DataEntity<ID extends Serializable> extends Entity<ID> {

    void setDeletionMark(boolean deletionMark);

    boolean isDeletionMark();

    void setDraftMark(boolean draftMark);

    boolean isDraftMark();

    default void setDeletionMark() {
        setDeletionMark(true);
    }

    default void unsetDeletionMark() {
        setDeletionMark(false);
    }

    default void toggleDeletionMark() {
        setDeletionMark(!isDeletionMark());
    }

    default void setDraftMark() {
        setDraftMark(true);
    }

    default void unsetDraftMark() {
        setDraftMark(false);
    }

    default void toggleDraftMark() {
        setDraftMark(!isDraftMark());
    }

}

