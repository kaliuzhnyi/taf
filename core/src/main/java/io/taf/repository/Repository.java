package io.taf.repository;

import io.taf.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface Repository<ENTITY extends Entity<ID>, ID extends Serializable>
        extends JpaRepository<ENTITY, ID> {
}
