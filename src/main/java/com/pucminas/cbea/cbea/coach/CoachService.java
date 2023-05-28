package com.pucminas.cbea.cbea.coach;

import com.pucminas.cbea.cbea.coach.builder.CoachSpecificationBuilder;
import com.pucminas.cbea.cbea.coach.dto.CoachRequestDTO;
import com.pucminas.cbea.cbea.coach.dto.CoachRequestFilter;
import com.pucminas.cbea.cbea.coach.dto.response.CoachResponseDTO;
import com.pucminas.cbea.cbea.swimmer.Swimmer;
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
public class CoachService {

    @Autowired
    private ICoachRepository coachRepository;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<CoachResponseDTO> findByFilter(CoachRequestFilter filter) {
        CoachSpecificationBuilder specificationBuilder = new CoachSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<Coach>> specification = specificationBuilder.build(filter);

        Page<Coach> page = specification
                .map(spec -> coachRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageCoachToBasePageableResponse(page);
    }

    public CoachResponseDTO save(CoachRequestDTO coachRequestDTO) {
        Coach coach = coachRequestDTOToCoach(coachRequestDTO);
        return coachToCoachResponseDTO(coachRepository.save(coach));
    }

    public void update(Long id, CoachRequestDTO coachRequestDTO) {
        if (!coachRepository.existsById(id)) {
            throw new EntityNotFoundException("O treinador de id " + id + " não foi encontrado.");
        }

        Coach coach = coachRequestDTOToCoach(id, coachRequestDTO);
        coachRepository.save(coach);
    }

    public void delete(Long id) {
        coachRepository.deleteById(id);
    }

    private BasePageableResponse<CoachResponseDTO> parsePageCoachToBasePageableResponse(Page<Coach> page) {
        List<CoachResponseDTO> list = page.getContent()
                .stream()
                .map(this::coachToCoachResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<CoachResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private CoachResponseDTO coachToCoachResponseDTO(Coach coach) {
        //return modelMapper.map(coach, CoachResponseDTO.class);
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
                .teams(Validation.isNotEmpty(coach.getTeams()) ? coach.getTeams().stream().map(this::teamToTeamResponseDTO).collect(Collectors.toList()) : null)
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
                .swimmers(team.getSwimmers().stream().map(this::swimmerToSwimmerResponseDTO).collect(Collectors.toList()))
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

    private Coach coachRequestDTOToCoach(CoachRequestDTO coachRequestDTO) {
        return Coach.builder()
                .name(coachRequestDTO.getName())
                .email(coachRequestDTO.getEmail())
                .registrationNumber(coachRequestDTO.getRegistrationNumber())
                .registrationDate(coachRequestDTO.getRegistrationDate())
                .sexo(coachRequestDTO.getSexo())
                .cellPhone(coachRequestDTO.getCellPhone())
                .landline(coachRequestDTO.getLandline())
                .cep(coachRequestDTO.getCep())
                .address(coachRequestDTO.getAddress())
                .professionalFormationDescription(coachRequestDTO.getProfessionalFormationDescription())
                .titlesNumber(coachRequestDTO.getTitlesNumber())
                .build();
    }

    private Coach coachRequestDTOToCoach(Long id, CoachRequestDTO coachRequestDTO) {
        return Coach.builder()
                .id(id)
                .name(coachRequestDTO.getName())
                .email(coachRequestDTO.getEmail())
                .registrationNumber(coachRequestDTO.getRegistrationNumber())
                .registrationDate(coachRequestDTO.getRegistrationDate())
                .sexo(coachRequestDTO.getSexo())
                .cellPhone(coachRequestDTO.getCellPhone())
                .landline(coachRequestDTO.getLandline())
                .cep(coachRequestDTO.getCep())
                .address(coachRequestDTO.getAddress())
                .professionalFormationDescription(coachRequestDTO.getProfessionalFormationDescription())
                .titlesNumber(coachRequestDTO.getTitlesNumber())
                .build();
    }
}