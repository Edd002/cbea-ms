package com.pucminas.cbea.cbea.proof.builder.builder;

import com.pucminas.cbea.cbea.championship.Championship;
import com.pucminas.cbea.cbea.championship.dto.ChampionshipRequestFilter;
import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import com.pucminas.cbea.global.util.date.DateUtil;

import java.util.stream.Collectors;

public class ChampionshipSpecificationBuilder extends BasicSpecificationBuilder<Championship, ChampionshipSpecification, ChampionshipRequestFilter> {

    @Override
    protected void initParams(ChampionshipRequestFilter filter) {
        if (Validation.isNotNull(filter.getId())) {
            where("id", SearchOperation.EQUAL, filter.getId());
        }

        if (Validation.isNotBlank(filter.getName())) {
            where("name", SearchOperation.LIKE, filter.getName());
        }

        if (Validation.isNotBlank(filter.getDescription())) {
            where("description", SearchOperation.LIKE, filter.getDescription());
        }

        if (Validation.isNotNull(filter.getRealizationYear())) {
            where("realizationYear", SearchOperation.EQUAL, filter.getRealizationYear());
        }

        if (Validation.isNotNull(filter.getInitialDate())) {
            where("initialDate", SearchOperation.GREATEST, DateUtil.formatUTCAndParseTruncate(filter.getInitialDate()));
        }

        if (Validation.isNotNull(filter.getEndDate())) {
            where("endDate", SearchOperation.LEAST, DateUtil.formatUTCAndParseTruncate(filter.getEndDate()));
        }

        if (Validation.isNotBlank(filter.getAddress())) {
            where("address", SearchOperation.LIKE, filter.getAddress());
        }

        if (Validation.isNotEmpty(filter.getProofIds())) {
            where("proofs", SearchOperation.IN_JOIN, filter.getProofIds().stream().map(proofId -> Proof.builder().id(proofId).build()).collect(Collectors.toList()));
        }
    }

    @Override
    protected ChampionshipSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new ChampionshipSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected ChampionshipSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new ChampionshipSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}
