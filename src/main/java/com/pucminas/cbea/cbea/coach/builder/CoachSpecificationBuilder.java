package com.pucminas.cbea.cbea.coach.builder;

import com.pucminas.cbea.cbea.coach.Coach;
import com.pucminas.cbea.cbea.coach.dto.CoachRequestFilter;
import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import com.pucminas.cbea.global.util.date.DateUtil;

import java.util.stream.Collectors;

public class CoachSpecificationBuilder extends BasicSpecificationBuilder<Coach, CoachSpecification, CoachRequestFilter> {

    @Override
    protected void initParams(CoachRequestFilter filter) {
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

        if (Validation.isNotNull(filter.getProfessionalFormationDescription())) {
            where("professionalFormationDescription", SearchOperation.LIKE, filter.getProfessionalFormationDescription());
        }

        if (Validation.isNotNull(filter.getProfessionalFormationDescription())) {
            where("titlesNumber", SearchOperation.EQUAL, filter.getTitlesNumber());
        }

        if (Validation.isNotEmpty(filter.getTeamIds())) {
            where("teams", SearchOperation.IN_JOIN, filter.getTeamIds().stream().map(teamId -> Team.builder().id(teamId).build()).collect(Collectors.toList()));
        }
    }

    @Override
    protected CoachSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new CoachSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected CoachSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new CoachSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}