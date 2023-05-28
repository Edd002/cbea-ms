package com.pucminas.cbea.cbea.proof.builder;

import com.pucminas.cbea.cbea.championship.Championship;
import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.proof.dto.ProofRequestFilter;
import com.pucminas.cbea.cbea.proofswimmingstyle.ProofSwimmingStyle;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.record.Record;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import com.pucminas.cbea.global.util.date.DateUtil;

import java.util.stream.Collectors;

public class ProofSpecificationBuilder extends BasicSpecificationBuilder<Proof, ProofSpecification, ProofRequestFilter> {

    @Override
    protected void initParams(ProofRequestFilter filter) {
        if (Validation.isNotNull(filter.getId())) {
            where("id", SearchOperation.EQUAL, filter.getId());
        }

        if (Validation.isNotBlank(filter.getName())) {
            where("name", SearchOperation.LIKE, filter.getName());
        }

        if (Validation.isNotBlank(filter.getDescription())) {
            where("description", SearchOperation.LIKE, filter.getDescription());
        }

        if (Validation.isNotNull(filter.getRealizationDate())) {
            where("realizationDate", SearchOperation.EQUAL, DateUtil.formatUTCAndParseTruncate(filter.getRealizationDate()));
        }

        if (Validation.isNotNull(filter.getRealizationTime())) {
            where("realizationTime", SearchOperation.EQUAL, filter.getRealizationTime());
        }

        if (Validation.isNotNull(filter.getRealizationDistance())) {
            where("realizationDistance", SearchOperation.EQUAL, filter.getRealizationDistance());
        }

        if (Validation.isNotNull(filter.getChampionshipId())) {
            where("championship", SearchOperation.EQUAL, Championship.builder().id(filter.getChampionshipId()).build());
        }

        if (Validation.isNotEmpty(filter.getProofSwimmingStyleIds())) {
            where("proofSwimmingStyles", SearchOperation.IN_JOIN, filter.getSwimmingStyleIds().stream().map(proofSwimmingStyleId -> ProofSwimmingStyle.builder().id(proofSwimmingStyleId).build()).collect(Collectors.toList()));
        }

        if (Validation.isNotEmpty(filter.getProofTeamIds())) {
            where("proofTeams", SearchOperation.IN_JOIN, filter.getTeamIds().stream().map(proofTeamId -> ProofTeam.builder().id(proofTeamId).build()).collect(Collectors.toList()));
        }

        if (Validation.isNotEmpty(filter.getRecordIds())) {
            where("records", SearchOperation.IN_JOIN, filter.getRecordIds().stream().map(recordId -> Record.builder().id(recordId).build()).collect(Collectors.toList()));
        }
    }

    @Override
    protected ProofSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new ProofSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected ProofSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new ProofSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}
