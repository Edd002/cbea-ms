package com.pucminas.cbea.cbea.proof.builder.builder;

import com.pucminas.cbea.cbea.championship.Championship;
import com.pucminas.cbea.global.specification.BasicSpecification;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ChampionshipSpecification extends BasicSpecification<Championship> implements Specification<Championship> {

    private static final long serialVersionUID = 1L;

    public ChampionshipSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Championship> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return super.genericPredicate(root, builder);
    }
}