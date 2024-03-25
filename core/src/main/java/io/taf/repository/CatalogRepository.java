package io.taf.repository;

import io.taf.catalogs.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

@NoRepositoryBean
public interface CatalogRepository<ENTITY extends CatalogEntity<ID>, ID extends Serializable>
        extends DataRepository<ENTITY, ID> {

    @Override
    @Query(value = "select s from #{#entityName} s " +
            "where " +
            "lower(cast(s.id as string)) like lower(concat('%', :filterText, '%')) or " +
            "lower(s.code) like lower(concat('%', :filterText, '%')) or " +
            "lower(s.title) like lower(concat('%', :filterText, '%'))")
    Page<ENTITY> findAllBy(Pageable pageable, @Param("filterText") String filterText);

}
