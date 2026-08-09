package com.service.BVHSHOP.repository;


import jakarta.persistence.EntityGraph;

import java.util.List;

public interface BaseEntityGraphRepository<T> {
    List<T> findAll(EntityGraph<T> graph);
}
