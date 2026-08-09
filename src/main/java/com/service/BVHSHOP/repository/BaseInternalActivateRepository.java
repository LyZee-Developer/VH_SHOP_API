package com.service.BVHSHOP.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseInternalActivateRepository<T, ID> extends JpaRepository<T, ID> , JpaSpecificationExecutor<T> {
    boolean existsByIdAndIsActivateTrue(ID id);
    Optional<T> findByIdAndIsActivateTrue(ID id);

}
