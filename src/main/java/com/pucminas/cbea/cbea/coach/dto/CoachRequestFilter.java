package com.pucminas.cbea.cbea.coach.dto;

import com.pucminas.cbea.global.specification.filter.BasePaginatorFilter;
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
public class CoachRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id do treinador.", example = "1")
    private Long id;

    @Schema(description = "Nome do treinador.", example = "Coach Name 01")
    private String name;

    @Schema(description = "Email do treinador.", example = "coachemail01@cbea.com.br")
    private String email;

    @Schema(description = "Número de registro do treinador.", example = "01")
    private String registrationNumber;

    @Schema(description = "Data de registro treinador.", type = "string", example = "01/01/2013")
    private Date registrationDate;

    @Schema(description = "Sexo do treinador.", example = "M")
    private String sexo;

    @Schema(description = "Telefone celular do treinador.", example = "(11) 11111-1111")
    private String cellPhone;

    @Schema(description = "Telefone fixo do treinador.", example = "(11) 1111-1111")
    private String landline;

    @Schema(description = "CEP do treinador.", example = "11111-111")
    private String cep;

    @Schema(description = "Endereço do treinador.", example = "Coach Address 01")
    private String address;

    @Schema(description = "Descrição profissional do treinador.", example = "Coach Professional Description 01")
    private String professionalFormationDescription;

    @Schema(description = "Número de títulos do treinador.", example = "1")
    private Integer titlesNumber;

    @Schema(description = "Ids dos times do treinador.")
    private List<Long> teamIds;
}