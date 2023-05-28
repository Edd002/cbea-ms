package com.pucminas.cbea.cbea.swimmer.dto;

import com.pucminas.cbea.global.specification.filter.BasePaginatorFilter;
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
public class SwimmerRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id do nadador.", example = "1")
    private Long id;

    @Schema(description = "Nome do nadador.", example = "Swimmer Name 01")
    private String name;

    @Schema(description = "Email do nadador.", example = "swimmeremail01@cbea.com.br")
    private String email;

    @Schema(description = "Número de registro do nadador.", example = "01")
    private String registrationNumber;

    @Schema(description = "Data de registro do nadador.", type = "string", example = "01/01/2013")
    private Date registrationDate;

    @Schema(description = "Sexo do nadador.", example = "M")
    private String sexo;

    @Schema(description = "Telefone fixo do nadador.", example = "(11) 11111-1111")
    private String cellPhone;

    @Schema(description = "Telefone celular do nadador.", example = "(11) 1111-1111")
    private String landline;

    @Schema(description = "CEP do nadador.", example = "11111-111")
    private String cep;

    @Schema(description = "Endereço do nadador.", example = "Swimmer Address 01")
    private String address;

    @Schema(description = "Número de medalhas de bronze do nadador.", example = "1")
    private Integer numberBronzeMedals;

    @Schema(description = "Número de medalhas de prata do nadador.", example = "1")
    private Integer numberSilverMedals;

    @Schema(description = "Número de medalhas de ouro do nadador.", example = "1")
    private Integer numberGoldMedals;

    @Schema(description = "Id do time do nadador.", example = "1")
    private Long teamId;
}