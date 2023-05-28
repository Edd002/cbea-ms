package com.pucminas.cbea.global.specification;

import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class BasicSpecification<TYPE> {

    private transient SearchCriteria criteria;

    public BasicSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    protected Predicate genericPredicate(Root<TYPE> root, CriteriaBuilder builder) {
        boolean isChild = Validation.isNotBlank(criteria.getKey()) && criteria.getKey().contains(".");

        if (isChild && criteria.getOperation().equals(SearchOperation.EQUAL)) {
            String[] params = criteria.getKey().split("\\.");
            return builder.equal(root.join(params[0]).get(params[1]), criteria.getValue());
        }

        if (isChild && criteria.getOperation().equals(SearchOperation.LIKE)) {
            String[] params = criteria.getKey().split("\\.");
            return builder.like(
                    builder.lower(root.join(params[0]).get(params[1])),
                    "%" + String.valueOf(criteria.getValue()).trim().toLowerCase() + "%"
            );
        }

        if (isChild && criteria.getOperation().equals(SearchOperation.BETWEEN) && criteria.getValue() instanceof Date) {
            String[] params = criteria.getKey().split("\\.");
            return builder.between(root.join(params[0]).get(params[1]), (Date) criteria.getValue(), (Date) criteria.getParam());
        }

        if (isChild && criteria.getOperation().equals(SearchOperation.IN)) {
            String[] params = criteria.getKey().split("\\.");
            return root.join(params[0]).get(params[1]).in((Collection<?>) criteria.getValue());
        }

        if (isChild && criteria.getOperation().equals(SearchOperation.IN_JOIN)) {
            String[] params = criteria.getKey().split("\\.");
            return root.join(params[0]).join(params[1]).in((Collection<?>) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.IS_NULL) ) {
            return builder.isNull(root.get(criteria.getKey()));
        }

        if (criteria.getOperation().equals(SearchOperation.IS_NOT_NULL) ) {
            return builder.isNotNull(root.get(criteria.getKey()));
        }

        if (criteria.getOperation().equals(SearchOperation.LIKE)) {
            return builder.like(builder.lower(root.get(criteria.getKey())), "%" + String.valueOf(criteria.getValue()).trim().toLowerCase() + "%");
        }

        if (criteria.getOperation().equals(SearchOperation.GREATEST) && criteria.getValue() instanceof Date) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Date) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.GREATEST) && criteria.getValue() instanceof Long) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Long) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.GREATEST) && criteria.getValue() instanceof BigDecimal) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (BigDecimal) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.LEAST) && criteria.getValue() instanceof Date) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Date) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.LEAST) && criteria.getValue() instanceof Long) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Long) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.LEAST) && criteria.getValue() instanceof BigDecimal) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (BigDecimal) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.BETWEEN) && criteria.getValue() instanceof Date) {
            return builder.between(root.get(criteria.getKey()), (Date) criteria.getValue(), (Date) criteria.getParam());
        }

        if (criteria.getOperation().equals(SearchOperation.BETWEEN) && criteria.getValue() instanceof Long) {
            return builder.between(root.get(criteria.getKey()), (Long) criteria.getValue(), (Long) criteria.getParam());
        }

        if (criteria.getOperation().equals(SearchOperation.BETWEEN) && criteria.getValue() instanceof BigDecimal) {
            return builder.between(root.get(criteria.getKey()), (BigDecimal) criteria.getValue(), (BigDecimal) criteria.getParam());
        }

        if (criteria.getOperation().equals(SearchOperation.IN)) {
            return root.get(criteria.getKey()).in((Collection<?>) criteria.getValue());
        }

        if (criteria.getOperation().equals(SearchOperation.IN_JOIN)) {
            return root.join(criteria.getKey()).in((Collection<?>) criteria.getValue());
        }

        return null;
    }
}