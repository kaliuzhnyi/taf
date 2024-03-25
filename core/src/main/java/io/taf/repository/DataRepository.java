package io.taf.repository;

import io.taf.entity.DataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

@NoRepositoryBean
public interface DataRepository<ENTITY extends DataEntity<ID>, ID extends Serializable>
        extends Repository<ENTITY, ID> {

    @Query(value = "select s from #{#entityName} s " +
            "where " +
            "lower(cast(s.id as string)) like lower(concat('%', :filterText, '%'))")
    Page<ENTITY> findAllBy(Pageable pageable, @Param("filterText") String filterText);

}
