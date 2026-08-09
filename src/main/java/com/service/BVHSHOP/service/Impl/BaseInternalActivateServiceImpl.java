package com.service.BVHSHOP.service.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.repository.BaseInternalActivateRepository;
import com.service.BVHSHOP.service.BaseInternalService;
import com.service.BVHSHOP.util.EntityGraphUtils;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseInternalActivateServiceImpl<T, ID> implements BaseInternalService<T, ID> {

    protected final BaseInternalActivateRepository<T, ID> repository;
    protected final Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public BaseInternalActivateServiceImpl(BaseInternalActivateRepository<T, ID> repository, Class<T> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

    @Override
    public T saveData(T entity) {
        return repository.save(entity);
    }

    @Override
    public Page<T> findAllSpePageFetch(Specification<T> specification, Pageable pageable, List<String> fetchPaths) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        /*
         * =========================================================
         * 1. MAIN QUERY
         * =========================================================
         */

        CriteriaQuery<T> cq = cb.createQuery(entityClass);

        Root<T> root = cq.from(entityClass);

        /*
         * Specification / WHERE
         */
        if (specification != null) {

            Predicate predicate = specification.toPredicate(root, cq, cb);

            if (predicate != null) {
                cq.where(predicate);
            }
        }

        /*
         * =========================================================
         * 2. SORTING
         * =========================================================
         */

        if (pageable.getSort().isSorted()) {

            List<Order> orders = new ArrayList<>();

            pageable.getSort().forEach(sort -> {

                Path<?> path = root.get(sort.getProperty());

                Order order;

                if (sort.isAscending()) {
                    order = cb.asc(path);
                } else {
                    order = cb.desc(path);
                }

                orders.add(order);
            });

            cq.orderBy(orders);
        }

        /*
         * =========================================================
         * 3. DISTINCT
         * =========================================================
         *
         * Important when EntityGraph fetches @OneToMany.
         *
         * This prevents duplicate root entities.
         */

        if (fetchPaths != null && !fetchPaths.isEmpty()) {
            cq.distinct(true);
        }

        /*
         * =========================================================
         * 4. CREATE QUERY
         * =========================================================
         */

        TypedQuery<T> query = entityManager.createQuery(cq);

        /*
         * =========================================================
         * 5. DYNAMIC ENTITY GRAPH
         * =========================================================
         */

        if (fetchPaths != null && !fetchPaths.isEmpty()) {

            EntityGraph<T> graph = EntityGraphUtils.build(entityManager, entityClass, fetchPaths);

            query.setHint("jakarta.persistence.fetchgraph", graph);
        }

        /*
         * =========================================================
         * 6. PAGINATION
         * =========================================================
         */

        query.setFirstResult(Math.toIntExact(pageable.getOffset()));

        query.setMaxResults(pageable.getPageSize());

        List<T> content = query.getResultList();

        /*
         * =========================================================
         * 7. COUNT QUERY
         * =========================================================
         */

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);

        Root<T> countRoot = countQuery.from(entityClass);

        countQuery.select(cb.count(countRoot));

        /*
         * Apply the SAME specification
         * to the count query.
         */

        if (specification != null) {

            Predicate countPredicate = specification.toPredicate(countRoot, countQuery, cb);

            if (countPredicate != null) {
                countQuery.where(countPredicate);
            }
        }

        Long total = entityManager.createQuery(countQuery).getSingleResult();

        /*
         * =========================================================
         * 8. RETURN PAGE
         * =========================================================
         */

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<T> findAll(List<String> fetchPaths) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        cq.from(entityClass);

        TypedQuery<T> query = entityManager.createQuery(cq);

        if (fetchPaths != null && !fetchPaths.isEmpty()) {
            EntityGraph<T> graph = EntityGraphUtils.build(entityManager, entityClass, fetchPaths);
            query.setHint("jakarta.persistence.fetchgraph", graph);
        }

        return query.getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findByIdAndIsActivateTrue(id);
    }

    @Override
    public T findThrowById(ID id) {
        return findById(id).orElseThrow(() -> new ApiException("%s %s".formatted(entityClass.getSimpleName(), "not found!")));
    }
}
