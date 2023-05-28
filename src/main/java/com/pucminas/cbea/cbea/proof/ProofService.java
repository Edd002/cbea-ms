package com.pucminas.cbea.cbea.proof;

import com.pucminas.cbea.cbea.championship.Championship;
import com.pucminas.cbea.cbea.championship.ChampionshipService;
import com.pucminas.cbea.cbea.championship.dto.response.ChampionshipResponseDTO;
import com.pucminas.cbea.cbea.proof.builder.ProofSpecificationBuilder;
import com.pucminas.cbea.cbea.proof.dto.ProofRequestDTO;
import com.pucminas.cbea.cbea.proof.dto.ProofRequestFilter;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.cbea.proofswimmingstyle.ProofSwimmingStyle;
import com.pucminas.cbea.cbea.proofswimmingstyle.ProofSwimmingStyleService;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.proofteam.ProofTeamService;
import com.pucminas.cbea.cbea.record.Record;
import com.pucminas.cbea.cbea.record.dto.response.RecordResponseDTO;
import com.pucminas.cbea.cbea.swimmingstyle.SwimmingStyleService;
import com.pucminas.cbea.cbea.swimmingstyle.dto.response.SwimmingStyleResponseDTO;
import com.pucminas.cbea.cbea.team.TeamService;
import com.pucminas.cbea.cbea.team.dto.response.TeamResponseDTO;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
public class ProofService {

    @Autowired
    private IProofRepository proofRepository;

    @Autowired
    private ChampionshipService championshipService;

    @Autowired
    private ProofSwimmingStyleService proofSwimmingStyleService;

    @Autowired
    private ProofTeamService proofTeamService;

    @Autowired
    private SwimmingStyleService swimmingStyleService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<ProofResponseDTO> findByFilter(ProofRequestFilter filter) {
        ProofSpecificationBuilder specificationBuilder = new ProofSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());
        filter.setProofSwimmingStyleIds(proofSwimmingStyleService.findProofSwimmingStyleIdsBySwimmingStylesIds(filter.getSwimmingStyleIds()));
        filter.setProofTeamIds(proofTeamService.findProofTeamIdsByTeamIds(filter.getTeamIds()));

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<Proof>> specification = specificationBuilder.build(filter);

        Page<Proof> page = specification
                .map(spec -> proofRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageProofToBasePageableResponse(page);
    }

    public ProofResponseDTO save(ProofRequestDTO proofRequestDTO) {
        Proof proof = proofRequestDTOToProof(proofRequestDTO);
        proof.setChampionship(championshipService.findById(proofRequestDTO.getChampionshipId()));

        Proof savedProof = proofRepository.save(proof);
        List<ProofSwimmingStyle> proofSwimmingStyles = proofSwimmingStyleService.save(savedProof, swimmingStyleService.findBySwimmingStyleIds(proofRequestDTO.getSwimmingStylesIds()));
        List<ProofTeam> proofTeams = proofTeamService.save(savedProof, teamService.findByTeamIds(proofRequestDTO.getTeamsIds()));

        savedProof.setProofSwimmingStyles(proofSwimmingStyles);
        savedProof.setProofTeams(proofTeams);

        return proofToProofResponseDTO(savedProof);
    }

    @Transactional
    public void update(Long id, ProofRequestDTO proofRequestDTO) {
        if (!proofRepository.existsById(id)) {
            throw new EntityNotFoundException("A prova de id " + id + " não foi encontrada.");
        }

        Proof proof = proofRequestDTOToProof(id, proofRequestDTO);
        proof.setChampionship(championshipService.findById(proofRequestDTO.getChampionshipId()));

        proofSwimmingStyleService.deleteAllByProof(proof);
        proofTeamService.deleteAllByProof(proof);

        Proof savedProof = proofRepository.save(proof);
        proofSwimmingStyleService.save(savedProof, swimmingStyleService.findBySwimmingStyleIds(proofRequestDTO.getSwimmingStylesIds()));
        proofTeamService.save(savedProof, teamService.findByTeamIds(proofRequestDTO.getTeamsIds()));
    }

    @Transactional
    public void delete(Long id) {
        proofRepository.deleteById(id);
        proofSwimmingStyleService.deleteAllByProof(Proof.builder().id(id).build());
        proofTeamService.deleteAllByProof(Proof.builder().id(id).build());
    }

    private BasePageableResponse<ProofResponseDTO> parsePageProofToBasePageableResponse(Page<Proof> page) {
        List<ProofResponseDTO> list = page.getContent()
                .stream()
                .map(this::proofToProofResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<ProofResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private ProofResponseDTO proofToProofResponseDTO(Proof proof) {
        //return modelMapper.map(proof, ProofResponseDTO.class);
        return ProofResponseDTO.builder()
                .id(proof.getId())
                .name(proof.getName())
                .description(proof.getDescription())
                .realizationDate(proof.getRealizationDate())
                .realizationTime(proof.getRealizationTime())
                .realizationDistance(proof.getRealizationDistance())
                .championship(championshipToChampionshipResponseDTO(proof.getChampionship()))
                .swimmingStyles(Validation.isNotEmpty(proof.getProofSwimmingStyles()) ? proof.getProofSwimmingStyles().stream().map(this::proofSwimmingStyleToSwimmingStyleResponseDTO).collect(Collectors.toList()) : null)
                .teams(Validation.isNotEmpty(proof.getProofTeams()) ? proof.getProofTeams().stream().map(this::proofTeamToTeamResponseDTO).collect(Collectors.toList()) : null)
                .records(Validation.isNotEmpty(proof.getRecords()) ? proof.getRecords().stream().map(this::recordToRecordResponseDTO).collect(Collectors.toList()) : null)
                .build();
    }

    private ChampionshipResponseDTO championshipToChampionshipResponseDTO(Championship championship) {
        return ChampionshipResponseDTO.builder()
                .id(championship.getId())
                .name(championship.getName())
                .description(championship.getDescription())
                .realizationYear(championship.getRealizationYear())
                .initialDate(championship.getInitialDate())
                .endDate(championship.getEndDate())
                .address(championship.getAddress())
                .build();
    }

    private SwimmingStyleResponseDTO proofSwimmingStyleToSwimmingStyleResponseDTO(ProofSwimmingStyle proofSwimmingStyle) {
        return SwimmingStyleResponseDTO.builder()
                .id(proofSwimmingStyle.getSwimmingStyle().getId())
                .name(proofSwimmingStyle.getSwimmingStyle().getName())
                .description(proofSwimmingStyle.getSwimmingStyle().getDescription())
                .build();
    }

    private TeamResponseDTO proofTeamToTeamResponseDTO(ProofTeam proofTeam) {
        return TeamResponseDTO.builder()
                .id(proofTeam.getTeam().getId())
                .name(proofTeam.getTeam().getName())
                .description(proofTeam.getTeam().getDescription())
                .foundationDate(proofTeam.getTeam().getFoundationDate())
                .titlesNumber(proofTeam.getTeam().getTitlesNumber())
                .championshipsNumber(proofTeam.getTeam().getChampionshipsNumber())
                .build();
    }

    private RecordResponseDTO recordToRecordResponseDTO(Record record) {
        return RecordResponseDTO.builder()
                .id(record.getId())
                .name(record.getName())
                .description(record.getDescription())
                .distance(record.getDistance())
                .realizationDate(record.getRealizationDate())
                .realizationTime(record.getRealizationTime())
                .build();
    }

    private Proof proofRequestDTOToProof(ProofRequestDTO proofRequestDTO) {
        return Proof.builder()
                .name(proofRequestDTO.getName())
                .description(proofRequestDTO.getDescription())
                .realizationDate(proofRequestDTO.getRealizationDate())
                .realizationTime(proofRequestDTO.getRealizationTime())
                .realizationDistance(proofRequestDTO.getRealizationDistance())
                .championship(Championship.builder().id(proofRequestDTO.getChampionshipId()).build())
                .build();
    }

    private Proof proofRequestDTOToProof(Long id, ProofRequestDTO proofRequestDTO) {
        return Proof.builder()
                .id(id)
                .name(proofRequestDTO.getName())
                .description(proofRequestDTO.getDescription())
                .realizationDate(proofRequestDTO.getRealizationDate())
                .realizationTime(proofRequestDTO.getRealizationTime())
                .realizationDistance(proofRequestDTO.getRealizationDistance())
                .championship(Championship.builder().id(proofRequestDTO.getChampionshipId()).build())
                .build();
    }
}