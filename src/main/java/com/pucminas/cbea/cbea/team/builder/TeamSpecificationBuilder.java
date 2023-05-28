package com.pucminas.cbea.cbea.team.builder;

import com.pucminas.cbea.cbea.coach.Coach;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.swimmer.Swimmer;
import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.cbea.team.dto.TeamRequestFilter;
import com.pucminas.cbea.global.specification.BasicSpecificationBuilder;
import com.pucminas.cbea.global.specification.FetchDeletedEnum;
import com.pucminas.cbea.global.specification.specification.SearchCriteria;
import com.pucminas.cbea.global.specification.specification.SearchOperation;
import com.pucminas.cbea.global.util.Validation;
import com.pucminas.cbea.global.util.date.DateUtil;

import java.util.stream.Collectors;

public class TeamSpecificationBuilder extends BasicSpecificationBuilder<Team, TeamSpecification, TeamRequestFilter> {

    @Override
    protected void initParams(TeamRequestFilter filter) {
        if (Validation.isNotNull(filter.getId())) {
            where("id", SearchOperation.EQUAL, filter.getId());
        }

        if (Validation.isNotBlank(filter.getName())) {
            where("name", SearchOperation.LIKE, filter.getName());
        }

        if (Validation.isNotBlank(filter.getDescription())) {
            where("description", SearchOperation.LIKE, filter.getDescription());
        }

        if (Validation.isNotNull(filter.getFoundationDate())) {
            where("foundationDate", SearchOperation.EQUAL, DateUtil.formatUTCAndParseTruncate(filter.getFoundationDate()));
        }

        if (Validation.isNotNull(filter.getTitlesNumber())) {
            where("titlesNumber", SearchOperation.EQUAL,filter.getTitlesNumber());
        }

        if (Validation.isNotNull(filter.getChampionshipsNumber())) {
            where("championshipsNumber", SearchOperation.EQUAL, filter.getChampionshipsNumber());
        }

        if (Validation.isNotNull(filter.getCoachId())) {
            where("coach", SearchOperation.EQUAL, Coach.builder().id(filter.getCoachId()).build());
        }

        if (Validation.isNotEmpty(filter.getSwimmersIds())) {
            where("swimmers", SearchOperation.IN_JOIN, filter.getSwimmersIds().stream().map(swimmerId -> Swimmer.builder().id(swimmerId).build()).collect(Collectors.toList()));
        }

        if (Validation.isNotEmpty(filter.getProofTeamIds())) {
            where("proofTeams", SearchOperation.IN_JOIN, filter.getProofTeamIds().stream().map(proofTeamId -> ProofTeam.builder().id(proofTeamId).build()).collect(Collectors.toList()));
        }
    }

    @Override
    protected TeamSpecification buildSpecification(String key, SearchOperation operation, Object value) {
        return new TeamSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected TeamSpecification buildSpecification(String key, SearchOperation operation, Object value, Object param) {
        return new TeamSpecification(new SearchCriteria(key, operation, value, param));
    }

    @Override
    protected FetchDeletedEnum showDeleted() {
        return FetchDeletedEnum.FETCH_NOT_DELETED;
    }
}