package com.pucminas.cbea.cbea.championship;

import com.pucminas.cbea.cbea.championship.dto.ChampionshipRequestDTO;
import com.pucminas.cbea.cbea.championship.dto.ChampionshipRequestFilter;
import com.pucminas.cbea.cbea.championship.dto.response.ChampionshipResponseDTO;
import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.proof.builder.builder.ChampionshipSpecificationBuilder;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.global.exception.EntityNotFoundException;
import com.pucminas.cbea.global.paginator.BasePageableResponse;
import com.pucminas.cbea.global.specification.builder.PageableBuilder;
import com.pucminas.cbea.global.specification.enums.SortOrder;
import com.pucminas.cbea.global.util.Validation;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
public class ChampionshipService {

    @Autowired
    private IChampionshipRepository championshipRepository;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<ChampionshipResponseDTO> findByFilter(ChampionshipRequestFilter filter) {
        ChampionshipSpecificationBuilder specificationBuilder = new ChampionshipSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<Championship>> specification = specificationBuilder.build(filter);

        Page<Championship> page = specification
                .map(spec -> championshipRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageChampionshipToBasePageableResponse(page);
    }

    public ChampionshipResponseDTO save(ChampionshipRequestDTO championshipRequestDTO) {
        Championship championship = championshipRequestDTOToChampionship(championshipRequestDTO);
        return championshipToChampionshipResponseDTO(championshipRepository.save(championship));
    }

    public void update(Long id, ChampionshipRequestDTO championshipRequestDTO) {
        if (!championshipRepository.existsById(id)) {
            throw new EntityNotFoundException("O campeonato de id " + id + " não foi encontrado.");
        }

        Championship championship = championshipRequestDTOToChampionship(id, championshipRequestDTO);
        championshipRepository.save(championship);
    }

    public void delete(Long id) {
        championshipRepository.deleteById(id);
    }

    public Championship findById(Long id) {
        return championshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Campeonato de id " + id + " não encontrado."));
    }

    private BasePageableResponse<ChampionshipResponseDTO> parsePageChampionshipToBasePageableResponse(Page<Championship> page) {
        List<ChampionshipResponseDTO> list = page.getContent()
                .stream()
                .map(this::championshipToChampionshipResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<ChampionshipResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private ChampionshipResponseDTO championshipToChampionshipResponseDTO(Championship championship) {
        //return modelMapper.map(championship, ChampionshipResponseDTO.class);
        return ChampionshipResponseDTO.builder()
                .id(championship.getId())
                .name(championship.getName())
                .description(championship.getDescription())
                .realizationYear(championship.getRealizationYear())
                .initialDate(championship.getInitialDate())
                .endDate(championship.getEndDate())
                .address(championship.getAddress())
                .proofs(Validation.isNotEmpty(championship.getProofs()) ? championship.getProofs().stream().map(this::proofToProofResponseDTO).collect(Collectors.toList()) : null)
                .build();
    }

    private ProofResponseDTO proofToProofResponseDTO(Proof proof) {
        return ProofResponseDTO.builder()
                .id(proof.getId())
                .name(proof.getName())
                .description(proof.getDescription())
                .realizationDate(proof.getRealizationDate())
                .realizationTime(proof.getRealizationTime())
                .realizationDistance(proof.getRealizationDistance())
                .build();
    }

    private Championship championshipRequestDTOToChampionship(ChampionshipRequestDTO championshipRequestDTO) {
        return Championship.builder()
                .name(championshipRequestDTO.getName())
                .description(championshipRequestDTO.getDescription())
                .realizationYear(championshipRequestDTO.getRealizationYear())
                .initialDate(championshipRequestDTO.getInitialDate())
                .endDate(championshipRequestDTO.getEndDate())
                .address(championshipRequestDTO.getAddress())
                .build();
    }

    private Championship championshipRequestDTOToChampionship(Long id, ChampionshipRequestDTO championshipRequestDTO) {
        return Championship.builder()
                .id(id)
                .name(championshipRequestDTO.getName())
                .description(championshipRequestDTO.getDescription())
                .realizationYear(championshipRequestDTO.getRealizationYear())
                .initialDate(championshipRequestDTO.getInitialDate())
                .endDate(championshipRequestDTO.getEndDate())
                .address(championshipRequestDTO.getAddress())
                .build();
    }
}