package com.service.BVHSHOP.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BaseInternalService<T, ID> {
    T findThrowById(ID id);

    T saveData(T entity);

    Optional<T> findById(ID id);

    List<T> findAll(List<String> fetchPaths);

    Page<T> findAllSpePageFetch(
            Specification<T> specification,
            Pageable pageable,
            List<String> fetchPaths
    );
}
