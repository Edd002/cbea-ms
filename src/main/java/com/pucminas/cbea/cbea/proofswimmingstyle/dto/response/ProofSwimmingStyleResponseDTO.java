package com.pucminas.cbea.cbea.proofswimmingstyle.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.cbea.swimmingstyle.dto.response.SwimmingStyleResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProofSwimmingStyleResponseDTO {

    @Schema(description = "Id da associação entre prova e estilo natatório.")
    @JsonProperty("id")
    private int id;

    @Schema(description = "Prova associada ao estilo natatório.")
    @JsonProperty("proof")
    private ProofResponseDTO proof;

    @Schema(description = "Estilo natatório associado à prova.")
    @JsonProperty("swimmingStyle")
    private SwimmingStyleResponseDTO swimmingStyle;
}