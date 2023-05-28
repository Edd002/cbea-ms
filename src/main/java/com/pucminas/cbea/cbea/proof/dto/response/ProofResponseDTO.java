package com.pucminas.cbea.cbea.proof.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.championship.dto.response.ChampionshipResponseDTO;
import com.pucminas.cbea.cbea.record.dto.response.RecordResponseDTO;
import com.pucminas.cbea.cbea.swimmingstyle.dto.response.SwimmingStyleResponseDTO;
import com.pucminas.cbea.cbea.team.dto.response.TeamResponseDTO;
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
public class ProofResponseDTO {

    @Schema(description = "Id da prova.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome da prova.", example = "Proof Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Nome da prova.", example = "Proof Description 01")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Data de realização da prova.", type = "string", example = "01/01/2013")
    @JsonProperty("realizationDate")
    private Date realizationDate;

    @Schema(description = "Tempo de realização da prova.", example = "100000")
    @JsonProperty("realizationTime")
    private Long realizationTime;

    @Schema(description = "Distância a ser percorrida na prova.", example = "10")
    @JsonProperty("realizationDistance")
    private Long realizationDistance;

    @Schema(description = "Campeonato da prova.")
    @JsonProperty("championship")
    private ChampionshipResponseDTO championship;

    @Schema(description = "Estilos natatórios da prova.")
    @JsonProperty("swimmingStyles")
    private List<SwimmingStyleResponseDTO> swimmingStyles;

    @Schema(description = "Times da participantes da prova.")
    @JsonProperty("teams")
    private List<TeamResponseDTO> teams;

    @Schema(description = "Recordes da prova.")
    @JsonProperty("records")
    private List<RecordResponseDTO> records;
}