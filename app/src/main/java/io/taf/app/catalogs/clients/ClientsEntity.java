package io.taf.app.catalogs.clients;

import io.taf.catalogs.AbstractCatalogEntity;
import io.taf.utils.grid.GridColumnConfig;
import io.taf.views.list.DisplayOnListDataView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "clients")
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ClientsEntity
        extends AbstractCatalogEntity<Long> {

    @DisplayOnListDataView(order = 111)
    @GridColumnConfig(header = "${field.title.name}")
    @Column(name = "name")
    private String name;

    @DisplayOnListDataView(order = 112)
    @GridColumnConfig(header = "${field.title.surname}")
    @Column(name = "surname")
    private String surname;

}
