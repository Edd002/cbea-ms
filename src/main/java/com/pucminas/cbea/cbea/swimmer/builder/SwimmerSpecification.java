package com.pucminas.cbea.cbea.swimmer.builder;

import com.pucminas.cbea.cbea.swimmer.Swimmer;
import com.pucminas.cbea.global.specification.BasicSpecification;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SwimmerSpecification extends BasicSpecification<Swimmer> implements Specification<Swimmer> {

    private static final long serialVersionUID = 1L;

    public SwimmerSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Swimmer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return super.genericPredicate(root, builder);
    }
}