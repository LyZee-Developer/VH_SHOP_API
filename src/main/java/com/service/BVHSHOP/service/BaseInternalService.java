package com.service.BVHSHOP.service;

import java.util.List;
import java.util.Optional;

public interface BaseInternalService<T, ID> {
    T saveData(T entity);
    T update(ID id, T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    T findThrowById(ID id);
}
