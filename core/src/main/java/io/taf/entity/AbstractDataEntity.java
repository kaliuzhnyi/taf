package io.taf.entity;

import io.taf.utils.fields.CheckboxConfig;
import io.taf.views.item.DisplayOnItemDataView;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@MappedSuperclass
public abstract class AbstractDataEntity<ID extends Serializable>
        extends AbstractEntity<ID>
        implements DataEntity<ID> {

    @DisplayOnItemDataView(order = 101)
    @CheckboxConfig(label = "${field.deletionMark.comment}")
    @Builder.Default
    @Column(name = "deletion_mark", nullable = false)
    private boolean deletionMark = false;

    @DisplayOnItemDataView(order = 102)
    @CheckboxConfig(label = "${field.draftMark.comment}")
    @Builder.Default
    @Column(name = "draft_mark", nullable = false)
    private boolean draftMark = false;

}
