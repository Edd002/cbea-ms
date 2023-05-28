package com.pucminas.cbea.cbea.swimmingstyle.builder;

import com.pucminas.cbea.cbea.swimmingstyle.SwimmingStyle;
import com.pucminas.cbea.global.specification.BasicSpecification;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SwimmingStyleSpecification extends BasicSpecification<SwimmingStyle> implements Specification<SwimmingStyle> {

    private static final long serialVersionUID = 1L;

    public SwimmingStyleSpecification(SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<SwimmingStyle> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return super.genericPredicate(root, builder);
    }
}