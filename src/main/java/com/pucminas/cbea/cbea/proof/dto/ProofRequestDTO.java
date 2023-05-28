package com.pucminas.cbea.cbea.proof.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProofRequestDTO {

    @Schema(description = "Nome da prova.", example = "Proof Name 11")
    @NotBlank(message = "O nome da prova deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Nome da prova.", example = "Proof Description 11")
    @NotBlank(message = "A descrição da prova deve ser informada.")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Data de realização da prova.", type = "string", example = "01/01/2023")
    @NotNull(message = "A data de realização da prova deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("realizationDate")
    private Date realizationDate;

    @Schema(description = "Tempo de realização da prova.", example = "110000")
    @NotNull(message = "A hora de realização da prova deve ser informada.")
    @JsonProperty("realizationTime")
    private Long realizationTime;

    @Schema(description = "Distância a ser percorrida na prova.", example = "11")
    @NotNull(message = "A distância de realização da prova deve ser informada.")
    @JsonProperty("realizationDistance")
    private Long realizationDistance;

    @Schema(description = "Id do campeonato da prova.", example = "1")
    @NotNull(message = "O id do campeonato da prova deve ser informado.")
    @JsonProperty("championshipId")
    private Long championshipId;

    @Schema(description = "Ids dos estilos natatórios da prova.", type = "List", example = "[1, 2]")
    @NotEmpty(message = "Os ids dos estilos natatórios da prova devem serem informados.")
    @JsonProperty("proofSwimmingStylesIds")
    private List<Long> swimmingStylesIds;

    @Schema(description = "Ids dos times da participantes da prova.", type = "List", example = "[1, 2]")
    @NotEmpty(message = "Os ids dos times da prova devem serem informados.")
    @JsonProperty("teamsIds")
    private List<Long> teamsIds;
}