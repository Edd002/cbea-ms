package com.pucminas.cbea.cbea.team;

import com.pucminas.cbea.cbea.coach.Coach;
import com.pucminas.cbea.cbea.coach.dto.response.CoachResponseDTO;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.proofteam.ProofTeamService;
import com.pucminas.cbea.cbea.swimmer.Swimmer;
import com.pucminas.cbea.cbea.swimmer.dto.response.SwimmerResponseDTO;
import com.pucminas.cbea.cbea.team.builder.TeamSpecificationBuilder;
import com.pucminas.cbea.cbea.team.dto.TeamRequestDTO;
import com.pucminas.cbea.cbea.team.dto.TeamRequestFilter;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
public class TeamService {

    @Autowired
    private ITeamRepository teamRepository;

    @Autowired
    private ProofTeamService proofTeamService;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<TeamResponseDTO> findByFilter(TeamRequestFilter filter) {
        TeamSpecificationBuilder specificationBuilder = new TeamSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());
        filter.setProofTeamIds(proofTeamService.findProofTeamIdsByTeamIds(filter.getTeamIds()));

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<Team>> specification = specificationBuilder.build(filter);

        Page<Team> page = specification
                .map(spec -> teamRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageTeamToBasePageableResponse(page);
    }

    public TeamResponseDTO save(TeamRequestDTO teamRequestDTO) {
        Team team = teamRequestDTOToTeam(teamRequestDTO);
        return teamToTeamResponseDTO(teamRepository.save(team));
    }

    public void update(Long id, TeamRequestDTO teamRequestDTO) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("O time de id " + id + " não foi encontrado.");
        }

        Team team = teamRequestDTOToTeam(id, teamRequestDTO);
        teamRepository.save(team);
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    public List<Team> findByTeamIds(List<Long> ids) {
        return teamRepository.findAllById(ids);
    }

    private BasePageableResponse<TeamResponseDTO> parsePageTeamToBasePageableResponse(Page<Team> page) {
        List<TeamResponseDTO> list = page.getContent()
                .stream()
                .map(this::teamToTeamResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<TeamResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private TeamResponseDTO teamToTeamResponseDTO(Team team) {
        //return modelMapper.map(team, TeamResponseDTO.class);
        return TeamResponseDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .foundationDate(team.getFoundationDate())
                .titlesNumber(team.getTitlesNumber())
                .championshipsNumber(team.getChampionshipsNumber())
                .coach(coachToCoachResponseDTO(team.getCoach()))
                .swimmers(Validation.isNotNull(team.getSwimmers()) ? team.getSwimmers().stream().map(this::swimmerToSwimmerResponseDTO).collect(Collectors.toList()) : null)
                .proofs(Validation.isNotNull(team.getProofTeams()) ? team.getProofTeams().stream().map(this::proofTeamToProofResponseDTO).collect(Collectors.toList()) : null)
                .build();
    }

    private CoachResponseDTO coachToCoachResponseDTO(Coach coach) {
        return CoachResponseDTO.builder()
                .id(coach.getId())
                .name(coach.getName())
                .email(coach.getEmail())
                .registrationNumber(coach.getRegistrationNumber())
                .registrationDate(coach.getRegistrationDate())
                .sexo(coach.getSexo())
                .cellPhone(coach.getCellPhone())
                .landline(coach.getLandline())
                .cep(coach.getCep())
                .address(coach.getAddress())
                .professionalFormationDescription(coach.getProfessionalFormationDescription())
                .titlesNumber(coach.getTitlesNumber())
                .build();
    }

    private SwimmerResponseDTO swimmerToSwimmerResponseDTO(Swimmer swimmer) {
        return SwimmerResponseDTO.builder()
                .id(swimmer.getId())
                .name(swimmer.getName())
                .email(swimmer.getEmail())
                .registrationNumber(swimmer.getRegistrationNumber())
                .registrationDate(swimmer.getRegistrationDate())
                .sexo(swimmer.getSexo())
                .cellPhone(swimmer.getCellPhone())
                .landline(swimmer.getLandline())
                .cep(swimmer.getCep())
                .address(swimmer.getAddress())
                .numberBronzeMedals(swimmer.getNumberBronzeMedals())
                .numberSilverMedals(swimmer.getNumberSilverMedals())
                .numberGoldMedals(swimmer.getNumberGoldMedals())
                .build();
    }

    private ProofResponseDTO proofTeamToProofResponseDTO(ProofTeam proofTeam) {
        return ProofResponseDTO.builder()
                .id(proofTeam.getProof().getId())
                .name(proofTeam.getProof().getName())
                .description(proofTeam.getProof().getDescription())
                .realizationDate(proofTeam.getProof().getRealizationDate())
                .realizationTime(proofTeam.getProof().getRealizationTime())
                .realizationDistance(proofTeam.getProof().getRealizationDistance())
                .build();
    }

    private Team teamRequestDTOToTeam(TeamRequestDTO teamRequestDTO) {
        return Team.builder()
                .name(teamRequestDTO.getName())
                .description(teamRequestDTO.getDescription())
                .foundationDate(teamRequestDTO.getFoundationDate())
                .titlesNumber(teamRequestDTO.getTitlesNumber())
                .championshipsNumber(teamRequestDTO.getChampionshipsNumber())
                .coach(Coach.builder().id(teamRequestDTO.getCoachId()).build())
                .build();
    }

    private Team teamRequestDTOToTeam(Long id, TeamRequestDTO teamRequestDTO) {
        return Team.builder()
                .id(id)
                .name(teamRequestDTO.getName())
                .description(teamRequestDTO.getDescription())
                .foundationDate(teamRequestDTO.getFoundationDate())
                .titlesNumber(teamRequestDTO.getTitlesNumber())
                .championshipsNumber(teamRequestDTO.getChampionshipsNumber())
                .coach(Coach.builder().id(teamRequestDTO.getCoachId()).build())
                .build();
    }
}