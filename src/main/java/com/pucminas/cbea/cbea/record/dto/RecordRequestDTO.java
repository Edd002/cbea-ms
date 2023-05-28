package com.pucminas.cbea.cbea.record.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequestDTO {

    @Schema(description = "Nome do recorde.", example = "Proof Name 11")
    @NotBlank(message = "O nome do recorde deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do recorde.", example = "Record Description 11")
    @NotBlank(message = "A descrição do recorde deve ser informada.")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Distância do recorde.", example = "110")
    @NotNull(message = "A distância do recorde deve ser informada.")
    @JsonProperty("distance")
    private Long distance;

    @Schema(description = "Data de realização do recorde.", type = "string", example = "01/01/2023")
    @NotNull(message = "A data de realização do recorde deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("realizationDate")
    private Date realizationDate;

    @Schema(description = "Tempo de realização do recorde em milisegundos.", example = "110000")
    @NotNull(message = "O tempo de realização do recorde deve ser informado.")
    @JsonProperty("realizationTime")
    private Long realizationTime;

    @Schema(description = "Id da prova em que foi realizada o recorde.", example = "1")
    @NotNull(message = "O id da prova associada ao recorde deve ser informada.")
    @JsonProperty("proofId")
    private Long proofId;
}