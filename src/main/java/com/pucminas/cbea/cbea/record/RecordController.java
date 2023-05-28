package com.pucminas.cbea.cbea.record;

import com.pucminas.cbea.cbea.record.dto.RecordRequestDTO;
import com.pucminas.cbea.cbea.record.dto.RecordRequestFilter;
import com.pucminas.cbea.cbea.record.dto.response.RecordResponseDTO;
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
@RequestMapping(value = "/api/v1/records", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Records - Endpoints para Recordes")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Operation(method = "GET", summary = "Buscar recorde(s)", description = "Buscar recorde(s).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<RecordResponseDTO>> findByFilter(@ParameterObject @Valid RecordRequestFilter recordRequestFilter) {
        log.info("Buscando recorde(s) por filtro...");

        BasePageableResponse<RecordResponseDTO> result = recordService.findByFilter(recordRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar recorde", description = "Cadastrar recorde.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<RecordResponseDTO> save(@RequestBody @Valid RecordRequestDTO recordRequestDTO) {
        log.info("Cadastrando recorde...");

        RecordResponseDTO recordResponseDTO = recordService.save(recordRequestDTO);

        return new ResponseEntity(recordResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar recorde", description = "Atualizar recorde.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody RecordRequestDTO recordRequestDTO) {
        log.info("Atualizando recorde...");

        recordService.update(id, recordRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir recorde", description = "Excluir recorde.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo recorde...");

        recordService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
