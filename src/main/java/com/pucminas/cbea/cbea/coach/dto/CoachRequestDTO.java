package com.pucminas.cbea.cbea.coach.dto;

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
public class CoachRequestDTO {

    @Schema(description = "Nome do treinador.", example = "Coach Name 11")
    @NotBlank(message = "O nome do treinador deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Email do treinador.", example = "coachemail11@cbea.com.br")
    @NotBlank(message = "O email do treinador deve ser informado.")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Número de registro do treinador.", example = "11")
    @NotBlank(message = "O número de registro do treinador deve ser informado.")
    @JsonProperty("registrationNumber")
    private String registrationNumber;

    @Schema(description = "Data de registro treinador.", type = "string", example = "01/01/2023")
    @NotNull(message = "A data de registro do treinador deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("registrationDate")
    private Date registrationDate;

    @Schema(description = "Sexo do treinador.", example = "M")
    @NotBlank(message = "O sexo do treinador deve ser informado.")
    @JsonProperty("sexo")
    private String sexo;

    @Schema(description = "Telefone celular do treinador.", example = "(11) 11111-1111")
    @NotBlank(message = "O telefone celular do treinador deve ser informado.")
    @JsonProperty("cellPhone")
    private String cellPhone;

    @Schema(description = "Telefone fixo do treinador.", example = "(11) 1111-1111")
    @NotBlank(message = "O telefone fixo do treinador deve ser informado.")
    @JsonProperty("landline")
    private String landline;

    @Schema(description = "CEP do treinador.", example = "11111-111")
    @NotBlank(message = "O CEP do treinador deve ser informado.")
    @JsonProperty("cep")
    private String cep;

    @Schema(description = "Endereço do treinador.", example = "Coach Address 11")
    @NotBlank(message = "O endereço do treinador deve ser informado.")
    @JsonProperty("address")
    private String address;

    @Schema(description = "Descrição profissional do treinador.", example = "Coach Professional Description 11")
    @NotBlank(message = "A descrição profissional do treinador deve ser informada.")
    @JsonProperty("professionalFormationDescription")
    private String professionalFormationDescription;

    @Schema(description = "Número de títulos do treinador.", example = "11")
    @NotNull(message = "O número de títulos do treinador deve ser informado.")
    @JsonProperty("titlesNumber")
    private Integer titlesNumber;
}