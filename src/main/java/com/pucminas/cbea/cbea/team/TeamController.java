package com.pucminas.cbea.cbea.team;

import com.pucminas.cbea.cbea.team.dto.TeamRequestDTO;
import com.pucminas.cbea.cbea.team.dto.TeamRequestFilter;
import com.pucminas.cbea.cbea.team.dto.response.TeamResponseDTO;
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
@RequestMapping(value = "/api/v1/teams", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Teams - Endpoints para Times")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(method = "GET", summary = "Buscar time(s)", description = "Buscar time(s).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<TeamResponseDTO>> findByFilter(@ParameterObject @Valid TeamRequestFilter teamRequestFilter) {
        log.info("Buscando time(s) por filtro...");

        BasePageableResponse<TeamResponseDTO> result = teamService.findByFilter(teamRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar time", description = "Cadastrar time.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<TeamResponseDTO> save(@RequestBody @Valid TeamRequestDTO teamRequestDTO) {
        log.info("Cadastrando time...");

        TeamResponseDTO teamResponseDTO = teamService.save(teamRequestDTO);

        return new ResponseEntity(teamResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar time", description = "Atualizar time.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        log.info("Atualizando time...");

        teamService.update(id, teamRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir time", description = "Excluir time.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo time...");

        teamService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
