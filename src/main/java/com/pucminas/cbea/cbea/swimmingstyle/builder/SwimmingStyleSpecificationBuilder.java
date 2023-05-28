package com.pucminas.cbea.cbea.swimmingstyle.builder;

import com.pucminas.cbea.cbea.swimmingstyle.SwimmingStyle;
import com.pucminas.cbea.cbea.swimmingstyle.dto.SwimmingStyleRequestFilter;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;

public class SwimmingStyleSpecificationBuilder extends BasicSpecificationBuilder<SwimmingStyle, SwimmingStyleSpecification, SwimmingStyleRequestFilter> {

    @Override
    protected void initParams(SwimmingStyleRequestFilter filter) {
        if (Validation.isNotNull(filter.getId())) {
            where("id", SearchOperation.EQUAL, filter.getId());
        }

        if (Validation.isNotBlank(filter.getName())) {
            where("name", SearchOperation.LIKE, filter.getName());
        }

        if (Validation.isNotBlank(filter.getDescription())) {
            where("description", SearchOperation.LIKE, filter.getDescription());
        }
    }

    @Override
    protected SwimmingStyleSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new SwimmingStyleSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected SwimmingStyleSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new SwimmingStyleSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}