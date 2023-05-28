package com.pucminas.cbea.cbea.proofteam.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.cbea.team.dto.response.TeamResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProofTeamResponseDTO {

    @Schema(description = "Id da associação entre prova e time.")
    @JsonProperty("id")
    private int id;

    @Schema(description = "Prova associada ao time.")
    @JsonProperty("proof")
    private ProofResponseDTO proof;

    @Schema(description = "Time associado à prova.")
    @JsonProperty("team")
    private TeamResponseDTO team;
}