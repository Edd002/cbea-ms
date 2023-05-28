package com.pucminas.cbea.cbea.proof.builder;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.global.specification.BasicSpecification;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProofSpecification extends BasicSpecification<Proof> implements Specification<Proof> {

    private static final long serialVersionUID = 1L;

    public ProofSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Proof> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return super.genericPredicate(root, builder);
    }
}