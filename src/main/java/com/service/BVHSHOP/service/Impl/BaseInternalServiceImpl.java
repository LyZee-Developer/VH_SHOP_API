package com.service.BVHSHOP.service.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.service.BaseInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseInternalServiceImpl<T, ID> implements BaseInternalService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public BaseInternalServiceImpl(JpaRepository<T, ID> repository){
        this.repository = repository;
    }

    @Override
    public T saveData(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        if(!repository.existsById(id)){
            throw new ApiException("can't update before id of your data was not found!");
        }
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findThrowById(ID id) {
        return findById(id).orElseThrow(()->new ApiException("not found!"));
    }
}
