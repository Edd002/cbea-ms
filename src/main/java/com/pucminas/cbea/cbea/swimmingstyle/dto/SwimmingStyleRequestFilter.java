package com.pucminas.cbea.cbea.swimmingstyle.dto;

import com.pucminas.cbea.global.specification.filter.BasePaginatorFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwimmingStyleRequestFilter extends BasePaginatorFilter {

    @Schema(description = "Id do estilo natatório.", example = "1")
    private Long id;

    @Schema(description = "Nome do estilo natatório.", example = "Swimming Style Name 01")
    private String name;

    @Schema(description = "Descrição do estilo natatório.", example = "Swimming Style 01")
    private String description;
}