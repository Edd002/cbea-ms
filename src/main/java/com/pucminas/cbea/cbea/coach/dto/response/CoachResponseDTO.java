package com.pucminas.cbea.cbea.coach.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.team.dto.response.TeamResponseDTO;
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
public class CoachResponseDTO {

    @Schema(description = "Id do treinador.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do treinador.", example = "Coach Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Email do treinador.", example = "coachemail01@cbea.com.br")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Número de registro do treinador.", example = "01")
    @JsonProperty("registrationNumber")
    private String registrationNumber;

    @Schema(description = "Data de registro treinador.", type = "string", example = "01/01/2013")
    @JsonProperty("registrationDate")
    private Date registrationDate;

    @Schema(description = "Sexo do treinador.", example = "M")
    @JsonProperty("sexo")
    private String sexo;

    @Schema(description = "Telefone celular do treinador.", example = "(11) 11111-1111")
    @JsonProperty("cellPhone")
    private String cellPhone;

    @Schema(description = "Telefone fixo do treinador.", example = "(11) 1111-1111")
    @JsonProperty("landline")
    private String landline;

    @Schema(description = "CEP do treinador.", example = "11111-111")
    @JsonProperty("cep")
    private String cep;

    @Schema(description = "Endereço do treinador.", example = "Coach Address 01")
    @JsonProperty("address")
    private String address;

    @Schema(description = "Descrição profissional do treinador.", example = "Coach Professional Description 01")
    @JsonProperty("professionalFormationDescription")
    private String professionalFormationDescription;

    @Schema(description = "Número de títulos do treinador.", example = "1")
    @JsonProperty("titlesNumber")
    private Integer titlesNumber;

    @Schema(description = "Times do treinador.")
    @JsonProperty("teams")
    private List<TeamResponseDTO> teams;
}