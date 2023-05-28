package com.pucminas.cbea.cbea.swimmer;

import com.pucminas.cbea.cbea.swimmer.dto.SwimmerRequestDTO;
import com.pucminas.cbea.cbea.swimmer.dto.SwimmerRequestFilter;
import com.pucminas.cbea.cbea.swimmer.dto.response.SwimmerResponseDTO;
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
@RequestMapping(value = "/api/v1/swimmers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Swimmer - Endpoints para Nadadores")
public class SwimmerController {

    @Autowired
    private SwimmerService swimmerService;

    @Operation(method = "GET", summary = "Buscar nadador(es)", description = "Buscar nadador(es).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<SwimmerResponseDTO>> findByFilter(@ParameterObject @Valid SwimmerRequestFilter swimmerRequestFilter) {
        log.info("Buscando nadador(es) por filtro...");

        BasePageableResponse<SwimmerResponseDTO> result = swimmerService.findByFilter(swimmerRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar nadador", description = "Cadastrar nadador.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<SwimmerResponseDTO> save(@RequestBody @Valid SwimmerRequestDTO swimmerRequestDTO) {
        log.info("Cadastrando nadador...");

        SwimmerResponseDTO swimmerResponseDTO = swimmerService.save(swimmerRequestDTO);

        return new ResponseEntity(swimmerResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar nadador", description = "Atualizar nadador.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody SwimmerRequestDTO swimmerRequestDTO) {
        log.info("Atualizando nadador...");

        swimmerService.update(id, swimmerRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir nadador", description = "Excluir nadador.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo nadador...");

        swimmerService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
