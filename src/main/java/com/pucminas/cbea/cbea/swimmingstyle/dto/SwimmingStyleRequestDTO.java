package com.pucminas.cbea.cbea.swimmingstyle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwimmingStyleRequestDTO {

    @Schema(description = "Nome do estilo natatório.", example = "Swimming Style Name 11")
    @NotBlank(message = "O nome do estilo natatório deve ser informado.")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Descrição do estilo natatório.", example = "Swimming Style 11")
    @NotBlank(message = "A descrição do estilo natatório deve ser informada.")
    @JsonProperty("description")
    private String description;
}