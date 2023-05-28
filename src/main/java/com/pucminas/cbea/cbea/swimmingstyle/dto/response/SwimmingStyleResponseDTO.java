package com.pucminas.cbea.cbea.swimmingstyle.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.proofswimmingstyle.dto.response.ProofSwimmingStyleResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwimmingStyleResponseDTO {

    @Schema(description = "Id do estilo natatório.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do estilo natatório.", example = "Swimming Style Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do estilo natatório.", example = "Swimming Style 01")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Estilos natatórios associados à prova.")
    @JsonProperty("proofSwimmingStyles")
    private List<ProofSwimmingStyleResponseDTO> proofSwimmingStyles;
}