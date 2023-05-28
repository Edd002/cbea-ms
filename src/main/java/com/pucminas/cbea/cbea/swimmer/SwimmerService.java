package com.pucminas.cbea.cbea.swimmer;

import com.pucminas.cbea.cbea.coach.Coach;
import com.pucminas.cbea.cbea.coach.dto.response.CoachResponseDTO;
import com.pucminas.cbea.cbea.swimmer.builder.SwimmerSpecificationBuilder;
import com.pucminas.cbea.cbea.swimmer.dto.SwimmerRequestDTO;
import com.pucminas.cbea.cbea.swimmer.dto.SwimmerRequestFilter;
import com.pucminas.cbea.cbea.swimmer.dto.response.SwimmerResponseDTO;
import com.pucminas.cbea.cbea.team.Team;
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
public class SwimmerService {

    @Autowired
    private ISwimmerRepository swimmerRepository;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<SwimmerResponseDTO> findByFilter(SwimmerRequestFilter filter) {
        SwimmerSpecificationBuilder specificationBuilder = new SwimmerSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<Swimmer>> specification = specificationBuilder.build(filter);

        Page<Swimmer> page = specification
                .map(spec -> swimmerRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageSwimmerToBasePageableResponse(page);
    }

    public SwimmerResponseDTO save(SwimmerRequestDTO swimmerRequestDTO) {
        Swimmer swimmer = swimmerRequestDTOToSwimmer(swimmerRequestDTO);
        return swimmerToSwimmerResponseDTO(swimmerRepository.save(swimmer));
    }

    public void update(Long id, SwimmerRequestDTO swimmerRequestDTO) {
        if (!swimmerRepository.existsById(id)) {
            throw new EntityNotFoundException("O nadador de id " + id + " não foi encontrado.");
        }

        Swimmer swimmer = swimmerRequestDTOToSwimmer(id, swimmerRequestDTO);
        swimmerRepository.save(swimmer);
    }

    public void delete(Long id) {
        swimmerRepository.deleteById(id);
    }

    private BasePageableResponse<SwimmerResponseDTO> parsePageSwimmerToBasePageableResponse(Page<Swimmer> page) {
        List<SwimmerResponseDTO> list = page.getContent()
                .stream()
                .map(this::swimmerToSwimmerResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<SwimmerResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private SwimmerResponseDTO swimmerToSwimmerResponseDTO(Swimmer swimmer) {
        //return modelMapper.map(swimmer, SwimmerResponseDTO.class);
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
                .team(teamToTeamResponseDTO(swimmer.getTeam()))
                .build();
    }

    private TeamResponseDTO teamToTeamResponseDTO(Team team) {
        return TeamResponseDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .foundationDate(team.getFoundationDate())
                .titlesNumber(team.getTitlesNumber())
                .championshipsNumber(team.getChampionshipsNumber())
                .coach(Validation.isNotNull(team.getCoach()) ? coachToCoachResponseDTO(team.getCoach()) : null)
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

    private Swimmer swimmerRequestDTOToSwimmer(SwimmerRequestDTO swimmerRequestDTO) {
        return Swimmer.builder()
                .name(swimmerRequestDTO.getName())
                .email(swimmerRequestDTO.getEmail())
                .registrationNumber(swimmerRequestDTO.getRegistrationNumber())
                .registrationDate(swimmerRequestDTO.getRegistrationDate())
                .sexo(swimmerRequestDTO.getSexo())
                .cellPhone(swimmerRequestDTO.getCellPhone())
                .landline(swimmerRequestDTO.getLandline())
                .cep(swimmerRequestDTO.getCep())
                .address(swimmerRequestDTO.getAddress())
                .numberBronzeMedals(swimmerRequestDTO.getNumberBronzeMedals())
                .numberSilverMedals(swimmerRequestDTO.getNumberSilverMedals())
                .numberGoldMedals(swimmerRequestDTO.getNumberGoldMedals())
                .team(Team.builder().id(swimmerRequestDTO.getTeamId()).build())
                .build();
    }

    private Swimmer swimmerRequestDTOToSwimmer(Long id, SwimmerRequestDTO swimmerRequestDTO) {
        return Swimmer.builder()
                .id(id)
                .name(swimmerRequestDTO.getName())
                .email(swimmerRequestDTO.getEmail())
                .registrationNumber(swimmerRequestDTO.getRegistrationNumber())
                .registrationDate(swimmerRequestDTO.getRegistrationDate())
                .sexo(swimmerRequestDTO.getSexo())
                .cellPhone(swimmerRequestDTO.getCellPhone())
                .landline(swimmerRequestDTO.getLandline())
                .cep(swimmerRequestDTO.getCep())
                .address(swimmerRequestDTO.getAddress())
                .numberBronzeMedals(swimmerRequestDTO.getNumberBronzeMedals())
                .numberSilverMedals(swimmerRequestDTO.getNumberSilverMedals())
                .numberGoldMedals(swimmerRequestDTO.getNumberGoldMedals())
                .team(Team.builder().id(swimmerRequestDTO.getTeamId()).build())
                .build();
    }
}