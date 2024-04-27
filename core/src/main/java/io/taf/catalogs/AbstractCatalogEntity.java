package io.taf.catalogs;

import io.taf.entity.AbstractDataEntity;
import io.taf.utils.fields.FieldOrder;
import io.taf.utils.fields.textarea.TextAreaConfig;
import io.taf.utils.fields.textfield.TextFieldConfig;
import io.taf.utils.grid.GridColumnConfig;
import io.taf.views.item.DisplayOnItemDataView;
import io.taf.views.list.DisplayOnListDataView;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@MappedSuperclass
public abstract class AbstractCatalogEntity<ID extends Serializable>
        extends AbstractDataEntity<ID>
        implements CatalogEntity<ID> {

    @TextFieldConfig(label = "${field.title.code}", widthFull = true, clearButtonVisible = true)
    @FieldOrder(value = 110)
    @DisplayOnListDataView(order = 110)
    @GridColumnConfig(header = "${field.title.code}")
    @Column(name = "code", nullable = false)
    private String code;

    @TextFieldConfig(label = "${field.title.title}", widthFull = true, clearButtonVisible = true)
    @FieldOrder(value = 120)
    @DisplayOnListDataView(order = 120)
    @GridColumnConfig(header = "${field.title.title}")
    @Column(name = "title", nullable = false)
    private String title;

    @TextAreaConfig(label = "${field.title.comment}", widthFull = true, clearButtonVisible = true)
    @FieldOrder(value = 130)
    @DisplayOnListDataView(order = 130)
    @GridColumnConfig(header = "${field.title.comment}")
    @Column(name = "comment", nullable = false)
    private String comment;

}
