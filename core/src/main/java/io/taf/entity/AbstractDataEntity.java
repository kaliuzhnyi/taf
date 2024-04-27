package io.taf.entity;

import io.taf.utils.fields.FieldOrder;
import io.taf.utils.fields.checkbox.CheckBoxConfig;
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

    @CheckBoxConfig(label = "${field.title.deletionMark}", readOnly = true, enabled = false)
    @FieldOrder(value = 98)
    @Builder.Default
    @Column(name = "deletion_mark", nullable = false)
    private boolean deletionMark = false;

    @CheckBoxConfig(label = "${field.title.draftMark}", readOnly = true, enabled = false)
    @FieldOrder(value = 99)
    @Builder.Default
    @Column(name = "draft_mark", nullable = false)
    private boolean draftMark = false;

}
