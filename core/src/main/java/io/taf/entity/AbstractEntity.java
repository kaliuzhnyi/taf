package io.taf.entity;

import io.taf.utils.GenericUtils;
import io.taf.utils.fields.FieldOrder;
import io.taf.utils.fields.textfield.TextFieldConfig;
import io.taf.utils.grid.GridColumnConfig;
import io.taf.views.item.DisplayOnItemDataView;
import io.taf.views.list.DisplayOnListDataView;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldNameConstants
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable>
        implements Entity<ID> {

    @Getter(value = AccessLevel.PUBLIC, onMethod_ = {@Override, @Nonnull}, lazy = true)
    @Transient
    private final Class<ID> idClass = GenericUtils.getType(getClass(), AbstractEntity.class, 0);

    @TextFieldConfig(label = "${field.title.id}", readOnly = true, enabled = false, width = "50%")
    @FieldOrder(value = 100)
    @DisplayOnListDataView(order = 100)
    @GridColumnConfig(header = "${field.title.id}", frozen = true, resizable = false, flexGrow = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private ID id;

}
