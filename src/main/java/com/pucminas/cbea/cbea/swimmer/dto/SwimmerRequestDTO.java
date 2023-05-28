package com.pucminas.cbea.cbea.swimmer.dto;

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
public class SwimmerRequestDTO {

    @Schema(description = "Nome do nadador.", example = "Swimmer Name 11")
    @NotBlank(message = "O nome do nadador deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Email do nadador.", example = "swimmeremail11@cbea.com.br")
    @NotBlank(message = "O email do nadador deve ser informado.")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Número de registro do nadador.", example = "11")
    @NotBlank(message = "O número de registro do nadador deve ser informado.")
    @JsonProperty("registrationNumber")
    private String registrationNumber;

    @Schema(description = "Data de registro do nadador.", type = "string", example = "01/01/2023")
    @NotNull(message = "A data de registro do nadador deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("registrationDate")
    private Date registrationDate;

    @Schema(description = "Sexo do nadador.", example = "M")
    @NotBlank(message = "O sexo do nadador deve ser informado.")
    @JsonProperty("sexo")
    private String sexo;

    @Schema(description = "Telefone fixo do nadador.", example = "(11) 11111-1111")
    @NotBlank(message = "O telefone celular do nadador deve ser informado.")
    @JsonProperty("cellPhone")
    private String cellPhone;

    @Schema(description = "Telefone celular do nadador.", example = "(11) 111111-1111")
    @NotBlank(message = "O telefone fixo do nadador deve ser informado.")
    @JsonProperty("landline")
    private String landline;

    @Schema(description = "CEP do nadador.", example = "11111-111")
    @NotBlank(message = "O CEP do nadador deve ser informado.")
    @JsonProperty("cep")
    private String cep;

    @Schema(description = "Endereço do nadador.", example = "Swimmer Address 11")
    @NotBlank(message = "O endereço do nadador deve ser informado.")
    @JsonProperty("address")
    private String address;

    @Schema(description = "Número de medalhas de bronze do nadador.", example = "11")
    @NotNull(message = "O número de medalhas de bronze do nadador deve ser informado.")
    @JsonProperty("numberBronzeMedals")
    private Integer numberBronzeMedals;

    @Schema(description = "Número de medalhas de prata do nadador.", example = "11")
    @NotNull(message = "O número de medalhas de prata do nadador deve ser informado.")
    @JsonProperty("numberSilverMedals")
    private Integer numberSilverMedals;

    @Schema(description = "Número de medalhas de ouro do nadador.", example = "11")
    @NotNull(message = "O número de medalhas de ouro do nadador deve ser informado.")
    @JsonProperty("numberGoldMedals")
    private Integer numberGoldMedals;

    @Schema(description = "Id do time do nadador.", example = "1")
    @NotNull(message = "O id do time do nadador deve ser informado.")
    @JsonProperty("teamId")
    private Long teamId;
}