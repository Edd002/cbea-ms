package com.pucminas.cbea.cbea.swimmingstyle;

import com.pucminas.cbea.cbea.swimmingstyle.builder.SwimmingStyleSpecificationBuilder;
import com.pucminas.cbea.cbea.swimmingstyle.dto.SwimmingStyleRequestDTO;
import com.pucminas.cbea.cbea.swimmingstyle.dto.SwimmingStyleRequestFilter;
import com.pucminas.cbea.cbea.swimmingstyle.dto.response.SwimmingStyleResponseDTO;
import com.pucminas.cbea.global.exception.EntityNotFoundException;
import com.pucminas.cbea.global.paginator.BasePageableResponse;
import com.pucminas.cbea.global.specification.builder.PageableBuilder;
import com.pucminas.cbea.global.specification.enums.SortOrder;
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
public class SwimmingStyleService {

    @Autowired
    private ISwimmingStyleRepository swimmingStyleRepository;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<SwimmingStyleResponseDTO> findByFilter(SwimmingStyleRequestFilter filter) {
        SwimmingStyleSpecificationBuilder specificationBuilder = new SwimmingStyleSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<SwimmingStyle>> specification = specificationBuilder.build(filter);

        Page<SwimmingStyle> page = specification
                .map(spec -> swimmingStyleRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageSwimmingStyleToBasePageableResponse(page);
    }

    public SwimmingStyleResponseDTO save(SwimmingStyleRequestDTO swimmingStyleRequestDTO) {
        SwimmingStyle swimmingStyle = swimmingStyleRequestDTOToSwimmingStyle(swimmingStyleRequestDTO);
        return swimmingStyleToSwimmingStyleResponseDTO(swimmingStyleRepository.save(swimmingStyle));
    }

    public void update(Long id, SwimmingStyleRequestDTO swimmingStyleRequestDTO) {
        if (!swimmingStyleRepository.existsById(id)) {
            throw new EntityNotFoundException("O estilo natatório de id " + id + " não foi encontrado.");
        }

        SwimmingStyle swimmingStyle = swimmingStyleRequestDTOToSwimmingStyle(id, swimmingStyleRequestDTO);
        swimmingStyleRepository.save(swimmingStyle);
    }

    public void delete(Long id) {
        swimmingStyleRepository.deleteById(id);
    }

    public List<SwimmingStyle> findBySwimmingStyleIds(List<Long> ids) {
        return swimmingStyleRepository.findAllById(ids);
    }

    private BasePageableResponse<SwimmingStyleResponseDTO> parsePageSwimmingStyleToBasePageableResponse(Page<SwimmingStyle> page) {
        List<SwimmingStyleResponseDTO> list = page.getContent()
                .stream()
                .map(this::swimmingStyleToSwimmingStyleResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<SwimmingStyleResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private SwimmingStyleResponseDTO swimmingStyleToSwimmingStyleResponseDTO(SwimmingStyle swimmingStyle) {
        //return modelMapper.map(swimmingStyle, SwimmingStyleResponseDTO.class);
        return SwimmingStyleResponseDTO.builder()
                .id(swimmingStyle.getId())
                .name(swimmingStyle.getName())
                .description(swimmingStyle.getDescription())
                .build();
    }

    private SwimmingStyle swimmingStyleRequestDTOToSwimmingStyle(SwimmingStyleRequestDTO swimmingStyleRequestDTO) {
        return SwimmingStyle.builder()
                .name(swimmingStyleRequestDTO.getName())
                .description(swimmingStyleRequestDTO.getDescription())
                .build();
    }

    private SwimmingStyle swimmingStyleRequestDTOToSwimmingStyle(Long id, SwimmingStyleRequestDTO swimmingStyleRequestDTO) {
        return SwimmingStyle.builder()
                .id(id)
                .name(swimmingStyleRequestDTO.getName())
                .description(swimmingStyleRequestDTO.getDescription())
                .build();
    }
}