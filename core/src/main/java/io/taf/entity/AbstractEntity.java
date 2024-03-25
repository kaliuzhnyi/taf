package io.taf.entity;

import io.taf.utils.fields.TextFieldConfig;
import io.taf.utils.grid.GridColumnConfig;
import io.taf.views.item.DisplayOnItemDataView;
import io.taf.views.list.DisplayOnListDataView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    @DisplayOnItemDataView(order = 100)
    @TextFieldConfig(label = "${field.title.id}", readOnly = true, enabled = false, width = "50%")
    @DisplayOnListDataView(order = 100)
    @GridColumnConfig(header = "${field.title.id}", frozen = true, resizable = false, flexGrow = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private ID id;

}
