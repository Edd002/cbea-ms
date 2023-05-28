package com.pucminas.cbea.cbea.record.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponseDTO {

    @Schema(description = "Id do recorde.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do recorde.", example = "Proof Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do recorde.", example = "Record Description 01")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Distância do recorde.", example = "100")
    @JsonProperty("distance")
    private Long distance;

    @Schema(description = "Data de realização do recorde.", type = "string", example = "01/01/2013")
    @JsonProperty("realizationDate")
    private Date realizationDate;

    @Schema(description = "Tempo de realização do recorde em milisegundos.", example = "100000")
    @JsonProperty("realizationTime")
    private Long realizationTime;

    @Schema(description = "Prova em que foi realizada o recorde.")
    @JsonProperty("proof")
    private ProofResponseDTO proof;
}