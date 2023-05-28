package com.pucminas.cbea.cbea.championship.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
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
public class ChampionshipResponseDTO {

    @Schema(description = "Id do campeonato.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do campeonato.", example = "Championship Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do campeonato.", example = "Championship Description 01")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Ano de realização do campeonato.", example = "2013")
    @JsonProperty("realizationYear")
    private Integer realizationYear;

    @Schema(description = "Data inicial do campeonato.", type = "string", example = "01/01/2013")
    @JsonProperty("initialDate")
    private Date initialDate;

    @Schema(description = "Data final do campeonato.", type = "string", example = "31/12/2013")
    @JsonProperty("endDate")
    private Date endDate;

    @Schema(description = "Endereço do campeonato.", example = "Championship Address 01")
    @JsonProperty("address")
    private String address;

    @Schema(description = "Provas do campeonato.")
    @JsonProperty("proofs")
    private List<ProofResponseDTO> proofs;
}