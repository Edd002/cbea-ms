package com.pucminas.cbea.cbea.record.dto;

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
public class RecordRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id do recorde.", example = "1")
    private Long id;

    @Schema(description = "Nome do recorde.", example = "Record Name 01")
    private String name;

    @Schema(description = "Descrição do recorde.", example = "Record Description 01")
    private String description;

    @Schema(description = "Distância do recorde.", example = "100")
    private Long distance;

    @Schema(description = "Data de realização do recorde.", type = "string", example = "01/01/2013")
    private Date realizationDate;

    @Schema(description = "Tempo de realização do recorde em milisegundos.", example = "100000")
    private Long realizationTime;

    @Schema(description = "Id da prova em que foi realizada o recorde.", example = "1")
    private Long proofId;
}