package com.pucminas.cbea.cbea.team.dto;

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
public class TeamRequestDTO {

    @Schema(description = "Nome do time.", example = "Team Name 11")
    @NotBlank(message = "O nome do time deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do time.", example = "Team Description 11")
    @NotBlank(message = "A descrição do time deve ser informada.")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Data de fundação do time.", type = "string", example = "01/01/2023")
    @NotNull(message = "A data de fundação do time deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("foundationDate")
    private Date foundationDate;

    @Schema(description = "Número de títulos do time.", example = "11")
    @NotNull(message = "O número de títulos do time deve ser informado.")
    @JsonProperty("titlesNumber")
    private Integer titlesNumber;

    @Schema(description = "Número de campeonatos do time.", example = "11")
    @NotNull(message = "O número de campeonatos do time deve ser informado.")
    @JsonProperty("championshipsNumber")
    private Integer championshipsNumber;

    @Schema(description = "Id do treinador do time.", example = "1")
    @NotNull(message = "O id do treinador do time deve ser informado.")
    @JsonProperty("coachId")
    private Long coachId;
}