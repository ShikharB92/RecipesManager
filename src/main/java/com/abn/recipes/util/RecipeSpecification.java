package com.abn.recipes.util;

import com.abn.recipes.common.SearchOperation;
import com.abn.recipes.entity.RecipeEntity;
import com.abn.recipes.model.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@Slf4j
public class RecipeSpecification implements Specification<RecipeEntity> {
    private final SearchCriteria searchCriteria;

    public RecipeSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<RecipeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        log.info("searching for {} {} {}", searchCriteria.getFilterKey(), searchCriteria.getOperation(), strToSearch);

        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            case DOES_NOT_CONTAIN:
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            case EQUAL:
                return cb.equal(root.get(searchCriteria.getFilterKey()), strToSearch);
            case NOT_EQUAL:
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), strToSearch);
            case GREATER_THAN:
                return cb.greaterThan(root.get(searchCriteria.getFilterKey()), strToSearch);
            case GREATER_THAN_EQUAL:
                return cb.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()), strToSearch);
            case LESS_THAN:
                return cb.lessThan(root.get(searchCriteria.getFilterKey()), strToSearch);
            case LESS_THAN_EQUAL:
                return cb.lessThanOrEqualTo(root.get(searchCriteria.getFilterKey()), strToSearch);
            default:
                return null;
        }
    }

}
