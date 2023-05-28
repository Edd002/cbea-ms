package com.pucminas.cbea.cbea.championship.dto;

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
public class ChampionshipRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id do campeonato.", example = "1")
    private Long id;

    @Schema(description = "Nome do campeonato.", example = "Championship Name 01")
    private String name;

    @Schema(description = "Descrição do campeonato.", example = "Championship Description 01")
    private String description;

    @Schema(description = "Ano de realização do campeonato.", example = "2013")
    private Integer realizationYear;

    @Schema(description = "Data inicial do campeonato.", type = "string", example = "01/01/2013")
    private Date initialDate;

    @Schema(description = "Data final do campeonato.", type = "string", example = "31/12/2013")
    private Date endDate;

    @Schema(description = "Endereço do campeonato.", example = "Championship Address 01")
    private String address;

    @Schema(description = "Ids das provas do campeonato.")
    private List<Long> proofIds;
}