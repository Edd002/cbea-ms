package com.pucminas.cbea.cbea.team.builder;

import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.specification.BasicSpecification;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TeamSpecification extends BasicSpecification<Team> implements Specification<Team> {

    private static final long serialVersionUID = 1L;

    public TeamSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return super.genericPredicate(root, builder);
    }
}
