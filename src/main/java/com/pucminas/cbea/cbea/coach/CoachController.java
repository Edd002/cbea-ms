package com.pucminas.cbea.cbea.coach;

import com.pucminas.cbea.cbea.coach.dto.CoachRequestDTO;
import com.pucminas.cbea.cbea.coach.dto.CoachRequestFilter;
import com.pucminas.cbea.cbea.coach.dto.response.CoachResponseDTO;
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
@RequestMapping(value = "/api/v1/coaches", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Coaches - Endpoints para Treinadores")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Operation(method = "GET", summary = "Buscar treinador(es)", description = "Buscar treinador(es).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<CoachResponseDTO>> findByFilter(@ParameterObject @Valid CoachRequestFilter coachRequestFilter) {
        log.info("Buscando treinador(es) por filtro...");

        BasePageableResponse<CoachResponseDTO> result = coachService.findByFilter(coachRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar treinador", description = "Cadastrar treinador.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<CoachResponseDTO> save(@RequestBody @Valid CoachRequestDTO coachRequestDTO) {
        log.info("Cadastrando treinador...");

        CoachResponseDTO coachResponseDTO = coachService.save(coachRequestDTO);

        return new ResponseEntity(coachResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar treinador", description = "Atualizar treinador.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody CoachRequestDTO coachRequestDTO) {
        log.info("Atualizando treinador...");

        coachService.update(id, coachRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir treinador", description = "Excluir treinador.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo treinador...");

        coachService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
