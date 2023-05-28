package com.pucminas.cbea.cbea.swimmer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pucminas.cbea.cbea.team.dto.response.TeamResponseDTO;
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
public class SwimmerResponseDTO {

    @Schema(description = "Id do nadador.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do nadador.", example = "Swimmer Name 01")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Email do nadador.", example = "swimmeremail01@cbea.com.br")
    @JsonProperty("email")
    private String email;

    @Schema(description = "Número de registro do nadador.", example = "01")
    @JsonProperty("registrationNumber")
    private String registrationNumber;

    @Schema(description = "Data de registro do nadador.", type = "string", example = "01/01/2013")
    @JsonProperty("registrationDate")
    private Date registrationDate;

    @Schema(description = "Sexo do nadador.", example = "M")
    @JsonProperty("sexo")
    private String sexo;

    @Schema(description = "Telefone fixo do nadador.", example = "(11) 11111-1111")
    @JsonProperty("cellPhone")
    private String cellPhone;

    @Schema(description = "Telefone celular do nadador.", example = "(11) 111111-1111")
    @JsonProperty("landline")
    private String landline;

    @Schema(description = "CEP do nadador.", example = "11111-111")
    @JsonProperty("cep")
    private String cep;

    @Schema(description = "Endereço do nadador.", example = "Swimmer Address 01")
    @JsonProperty("address")
    private String address;

    @Schema(description = "Número de medalhas de bronze do nadador.", example = "1")
    @JsonProperty("numberBronzeMedals")
    private Integer numberBronzeMedals;

    @Schema(description = "Número de medalhas de prata do nadador.", example = "1")
    @JsonProperty("numberSilverMedals")
    private Integer numberSilverMedals;

    @Schema(description = "Número de medalhas de ouro do nadador.", example = "1")
    @JsonProperty("numberGoldMedals")
    private Integer numberGoldMedals;

    @Schema(description = "Time do nadador.")
    @JsonProperty("team")
    private TeamResponseDTO team;
}