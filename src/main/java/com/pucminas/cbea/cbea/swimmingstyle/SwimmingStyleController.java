package com.pucminas.cbea.cbea.swimmingstyle;

import com.pucminas.cbea.cbea.swimmingstyle.dto.SwimmingStyleRequestDTO;
import com.pucminas.cbea.cbea.swimmingstyle.dto.SwimmingStyleRequestFilter;
import com.pucminas.cbea.cbea.swimmingstyle.dto.response.SwimmingStyleResponseDTO;
import com.pucminas.cbea.global.paginator.BasePageableResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log
@RestController
@RequestMapping(value = "/api/v1/swimming-styles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Swimmer Style - Endpoints para Estilos Natatórios")
public class SwimmingStyleController {

    @Autowired
    private SwimmingStyleService swimmingStyleService;

    @Operation(method = "GET", summary = "Buscar estilo(s) natatório(s)", description = "Buscar estilo(s) natatório(s).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<SwimmingStyleResponseDTO>> findByFilter(@ParameterObject @Valid SwimmingStyleRequestFilter swimmingStyleRequestFilter) {
        log.info("Buscando estilo(s) natatório(s) por filtro...");

        BasePageableResponse<SwimmingStyleResponseDTO> result = swimmingStyleService.findByFilter(swimmingStyleRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar estilo natatório", description = "Cadastrar estilo natatório.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<SwimmingStyleResponseDTO> save(@RequestBody @Valid SwimmingStyleRequestDTO swimmingStyleRequestDTO) {
        log.info("Cadastrando estilo natatório...");

        SwimmingStyleResponseDTO swimmingStyleResponseDTO = swimmingStyleService.save(swimmingStyleRequestDTO);

        return new ResponseEntity(swimmingStyleResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar estilo natatório", description = "Atualizar estilo natatório.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody SwimmingStyleRequestDTO swimmingStyleRequestDTO) {
        log.info("Atualizando estilo natatório...");

        swimmingStyleService.update(id, swimmingStyleRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir estilo natatório", description = "Excluir estilo natatório.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo estilo natatório...");

        swimmingStyleService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
