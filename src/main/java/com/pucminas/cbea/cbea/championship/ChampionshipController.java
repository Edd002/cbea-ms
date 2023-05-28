package com.pucminas.cbea.cbea.championship;

import com.pucminas.cbea.cbea.championship.dto.ChampionshipRequestDTO;
import com.pucminas.cbea.cbea.championship.dto.ChampionshipRequestFilter;
import com.pucminas.cbea.cbea.championship.dto.response.ChampionshipResponseDTO;
import com.pucminas.cbea.global.base.BaseController;
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
@RequestMapping(value = "/api/v1/championships", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Championships - Endpoints para Campeonatos")
public class ChampionshipController extends BaseController {

    @Autowired
    private ChampionshipService championshipService;

    @Operation(method = "GET", summary = "Buscar campeonatos(s)", description = "Buscar campeonatos(s).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<ChampionshipResponseDTO>> findByFilter(@ParameterObject @Valid ChampionshipRequestFilter championshipRequestFilter) {
        log.info("Buscando campeonato(s) por filtro...");

        BasePageableResponse<ChampionshipResponseDTO> result = championshipService.findByFilter(championshipRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar campeonato", description = "Cadastrar campeonato.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ChampionshipResponseDTO> save(@RequestBody @Valid ChampionshipRequestDTO championshipRequestDTO) {
        log.info("Cadastrando campeonato...");

        ChampionshipResponseDTO championshipResponseDTO = championshipService.save(championshipRequestDTO);

        return new ResponseEntity(championshipResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar campeonato", description = "Atualizar campeonato.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody ChampionshipRequestDTO championshipRequestDTO) {
        log.info("Atualizando campeonato...");

        championshipService.update(id, championshipRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir campeonato", description = "Excluir campeonato.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo campeonato...");

        championshipService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}