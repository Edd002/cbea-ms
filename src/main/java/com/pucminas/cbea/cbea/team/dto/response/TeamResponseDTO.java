package com.pucminas.cbea.cbea.team.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.coach.dto.response.CoachResponseDTO;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.cbea.swimmer.dto.response.SwimmerResponseDTO;
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
public class TeamResponseDTO {

    @Schema(description = "Id do time.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do time.", example = "Team Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do time.", example = "Team Description 01")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Data de fundação do time.", type = "string", example = "01/01/2013")
    @JsonProperty("foundationDate")
    private Date foundationDate;

    @Schema(description = "Número de títulos do time.", example = "1")
    @JsonProperty("titlesNumber")
    private Integer titlesNumber;

    @Schema(description = "Número de campeonatos do time.", example = "1")
    @JsonProperty("championshipsNumber")
    private Integer championshipsNumber;

    @Schema(description = "Treinador do time.")
    @JsonProperty("coach")
    private CoachResponseDTO coach;

    @Schema(description = "Nadadores do time.")
    @JsonProperty("swimmers")
    private List<SwimmerResponseDTO> swimmers;

    @Schema(description = "Provas que os times participaram.")
    @JsonProperty("proofs")
    private List<ProofResponseDTO> proofs;
}