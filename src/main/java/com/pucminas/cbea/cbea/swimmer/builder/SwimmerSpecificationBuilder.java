package com.pucminas.cbea.cbea.swimmer.builder;

import com.pucminas.cbea.cbea.swimmer.Swimmer;
import com.pucminas.cbea.cbea.swimmer.dto.SwimmerRequestFilter;
import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import com.pucminas.cbea.global.util.date.DateUtil;

public class SwimmerSpecificationBuilder extends BasicSpecificationBuilder<Swimmer, SwimmerSpecification, SwimmerRequestFilter> {

    @Override
    protected void initParams(SwimmerRequestFilter filter) {
        if (Validation.isNotNull(filter.getId())) {
            where("id", SearchOperation.EQUAL, filter.getId());
        }

        if (Validation.isNotBlank(filter.getName())) {
            where("name", SearchOperation.LIKE, filter.getName());
        }

        if (Validation.isNotBlank(filter.getEmail())) {
            where("email", SearchOperation.LIKE, filter.getEmail());
        }

        if (Validation.isNotBlank(filter.getRegistrationNumber())) {
            where("registrationNumber", SearchOperation.LIKE, filter.getRegistrationNumber());
        }

        if (Validation.isNotNull(filter.getRegistrationDate())) {
            where("registrationDate", SearchOperation.EQUAL, DateUtil.formatUTCAndParseTruncate(filter.getRegistrationDate()));
        }

        if (Validation.isNotBlank(filter.getSexo())) {
            where("sexo", SearchOperation.EQUAL, filter.getSexo());
        }

        if (Validation.isNotBlank(filter.getCellPhone())) {
            where("cellPhone", SearchOperation.LIKE, filter.getCellPhone());
        }

        if (Validation.isNotBlank(filter.getLandline())) {
            where("landline", SearchOperation.LIKE, filter.getLandline());
        }

        if (Validation.isNotBlank(filter.getCep())) {
            where("cep", SearchOperation.LIKE, filter.getCep());
        }

        if (Validation.isNotBlank(filter.getAddress())) {
            where("address", SearchOperation.LIKE, filter.getAddress());
        }

        if (Validation.isNotNull(filter.getNumberBronzeMedals())) {
            where("numberBronzeMedals", SearchOperation.EQUAL, filter.getNumberBronzeMedals());
        }

        if (Validation.isNotNull(filter.getNumberSilverMedals())) {
            where("numberSilverMedals", SearchOperation.EQUAL, filter.getNumberSilverMedals());
        }

        if (Validation.isNotNull(filter.getNumberGoldMedals())) {
            where("numberGoldMedals", SearchOperation.EQUAL, filter.getNumberGoldMedals());
        }

        if (Validation.isNotNull(filter.getTeamId())) {
            where("team", SearchOperation.EQUAL, Team.builder().id(filter.getTeamId()).build());
        }
    }

    @Override
    protected SwimmerSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new SwimmerSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected SwimmerSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new SwimmerSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}