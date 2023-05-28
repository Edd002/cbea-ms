package com.pucminas.cbea.cbea.proof.dto;

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
public class ProofRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id da prova.", example = "1")
    private Long id;

    @Schema(description = "Nome da prova.", example = "Proof Name 01")
    private String name;

    @Schema(description = "Nome da prova.", example = "Proof Description 01")
    private String description;

    @Schema(description = "Data de realização da prova.", type = "string", example = "01/01/2013")
    private Date realizationDate;

    @Schema(description = "Tempo de realização da prova.", example = "100000")
    private Long realizationTime;

    @Schema(description = "Distância a ser percorrida na prova.", example = "10")
    private Long realizationDistance;

    @Schema(description = "Id do campeonato da prova.")
    private Long championshipId;

    @Schema(description = "Ids dos estilos natatórios da prova.")
    private List<Long> swimmingStyleIds;
    @Hidden
    private List<Long> proofSwimmingStyleIds;

    @Schema(description = "Ids dos times da participantes da prova.")
    private List<Long> teamIds;
    @Hidden
    private List<Long> proofTeamIds;

    @Schema(description = "Ids dos recordes da prova.")
    private List<Long> recordIds;
}