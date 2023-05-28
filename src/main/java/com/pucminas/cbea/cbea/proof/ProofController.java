package com.pucminas.cbea.cbea.proof;

import com.pucminas.cbea.cbea.proof.dto.ProofRequestDTO;
import com.pucminas.cbea.cbea.proof.dto.ProofRequestFilter;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
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
@RequestMapping(value = "/api/v1/proofs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Proofs - Endpoints para Provas")
public class ProofController {

    @Autowired
    private ProofService proofService;

    @Operation(method = "GET", summary = "Buscar prova(s)", description = "Buscar prova(s).")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<BasePageableResponse<ProofResponseDTO>> findByFilter(@ParameterObject @Valid ProofRequestFilter proofRequestFilter) {
        log.info("Buscando prova(s) por filtro...");

        BasePageableResponse<ProofResponseDTO> result = proofService.findByFilter(proofRequestFilter);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Operation(method = "POST", summary = "Cadastrar prova", description = "Cadastrar prova.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ProofResponseDTO> save(@RequestBody @Valid ProofRequestDTO proofRequestDTO) {
        log.info("Cadastrando prova...");

        ProofResponseDTO proofResponseDTO = proofService.save(proofRequestDTO);

        return new ResponseEntity(proofResponseDTO, HttpStatus.CREATED);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "PUT", summary = "Atualizar prova", description = "Atualizar prova.")
    @PutMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody ProofRequestDTO proofRequestDTO) {
        log.info("Atualizando prova...");

        proofService.update(id, proofRequestDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "OK", content = @Content)
    @Operation(method = "DELETE", summary = "Excluir prova", description = "Excluir prova.")
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        log.info("Excluindo prova...");

        proofService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
