package io.taf.catalogs;

import io.taf.entity.AbstractDataEntity;
import io.taf.utils.fields.TextAreaConfig;
import io.taf.utils.fields.TextFieldConfig;
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

    @DisplayOnItemDataView(order = 110)
    @TextFieldConfig(label = "${field.title.code}", widthFull = true, clearButtonVisible = true)
    @DisplayOnListDataView(order = 110)
    @GridColumnConfig(header = "${field.title.code}")
    @Column(name = "code", nullable = false)
    private String code;

    @DisplayOnItemDataView(order = 120)
    @TextFieldConfig(label = "${field.title.title}", widthFull = true, clearButtonVisible = true)
    @DisplayOnListDataView(order = 120)
    @GridColumnConfig(header = "${field.title.title}")
    @Column(name = "title", nullable = false)
    private String title;

    @DisplayOnItemDataView(order = 130)
    @TextAreaConfig(label = "${field.title.comment}", widthFull = true, clearButtonVisible = true)
    @DisplayOnListDataView(order = 130)
    @GridColumnConfig(header = "${field.title.comment}")
    @Column(name = "comment", nullable = false)
    private String comment;

}
