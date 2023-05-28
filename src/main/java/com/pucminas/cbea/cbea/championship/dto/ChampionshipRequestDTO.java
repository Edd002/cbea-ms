package com.pucminas.cbea.cbea.championship.dto;

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
public class ChampionshipRequestDTO {

    @Schema(description = "Nome do campeonato.", example = "Championship Name 11")
    @NotBlank(message = "O nome do campeonato deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do campeonato.", example = "Championship Description 11")
    @NotBlank(message = "A descrição do campeonato deve ser informada.")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Ano de realização do campeonato.", example = "2023")
    @NotNull(message = "O ano de realização do campeonato deve ser informado.")
    @JsonProperty("realizationYear")
    private Integer realizationYear;

    @Schema(description = "Data inicial do campeonato.", type = "string", example = "01/01/2023")
    @NotNull(message = "A data de início de realização do campeonato deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("initialDate")
    private Date initialDate;

    @Schema(description = "Data final do campeonato.", type = "string", example = "31/12/2023")
    @NotNull(message = "A data final de realização do campeonato deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("endDate")
    private Date endDate;

    @Schema(description = "Endereço do campeonato.", example = "Championship Address 11")
    @NotNull(message = "O endereço do local da realização do campeonato deve ser informado.")
    @JsonProperty("address")
    private String address;
}