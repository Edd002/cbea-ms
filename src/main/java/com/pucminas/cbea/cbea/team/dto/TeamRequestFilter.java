package com.pucminas.cbea.cbea.team.dto;

import com.pucminas.cbea.global.specification.filter.BasePaginatorFilter;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id do time.", example = "1")
    private Long id;

    @Schema(description = "Nome do time.", example = "Team Name 01")
    private String name;

    @Schema(description = "Descrição do time.", example = "Team Description 01")
    private String description;

    @Schema(description = "Data de fundação do time.", type = "string", example = "01/01/2013")
    private Date foundationDate;

    @Schema(description = "Número de títulos do time.", example = "1")
    private Integer titlesNumber;

    @Schema(description = "Número de campeonatos do time.", example = "1")
    private Integer championshipsNumber;

    @Schema(description = "Id do treinador do time.", example = "1")
    private Long coachId;

    @Schema(description = "Ids dos nadadores do time.")
    private List<Long> swimmersIds;

    @Schema(description = "Ids dos times da participantes da prova.")
    private List<Long> teamIds;
    @Hidden
    private List<Long> proofTeamIds;
}