package com.pucminas.cbea.cbea.record.builder;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.record.Record;
import com.pucminas.cbea.cbea.record.dto.RecordRequestFilter;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import com.pucminas.cbea.global.util.date.DateUtil;

public class RecordSpecificationBuilder extends BasicSpecificationBuilder<Record, RecordSpecification, RecordRequestFilter> {

    @Override
    protected void initParams(RecordRequestFilter filter) {
        if (Validation.isNotNull(filter.getId())) {
            where("id", SearchOperation.EQUAL, filter.getId());
        }

        if (Validation.isNotBlank(filter.getName())) {
            where("name", SearchOperation.LIKE, filter.getName());
        }

        if (Validation.isNotBlank(filter.getDescription())) {
            where("description", SearchOperation.LIKE, filter.getDescription());
        }

        if (Validation.isNotNull(filter.getDistance())) {
            where("distance", SearchOperation.EQUAL, filter.getDistance());
        }

        if (Validation.isNotNull(filter.getRealizationDate())) {
            where("realizationDate", SearchOperation.EQUAL, DateUtil.formatUTCAndParseTruncate(filter.getRealizationDate()));
        }

        if (Validation.isNotNull(filter.getRealizationTime())) {
            where("realizationTime", SearchOperation.EQUAL, filter.getRealizationTime());
        }

        if (Validation.isNotNull(filter.getProofId())) {
            where("proof", SearchOperation.EQUAL, Proof.builder().id(filter.getProofId()).build());
        }
    }

    @Override
    protected RecordSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new RecordSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected RecordSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new RecordSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}
