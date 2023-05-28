package com.pucminas.cbea.cbea.record;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.proof.dto.response.ProofResponseDTO;
import com.pucminas.cbea.cbea.record.builder.RecordSpecificationBuilder;
import com.pucminas.cbea.cbea.record.dto.RecordRequestDTO;
import com.pucminas.cbea.cbea.record.dto.RecordRequestFilter;
import com.pucminas.cbea.cbea.record.dto.response.RecordResponseDTO;
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
public class RecordService {

    @Autowired
    private IRecordRepository recordRepository;

    @Autowired
    private PageableBuilder pageableBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public BasePageableResponse<RecordResponseDTO> findByFilter(RecordRequestFilter filter) {
        RecordSpecificationBuilder specificationBuilder = new RecordSpecificationBuilder();
        filter.setSortDirection(SortOrder.BOTH.name());

        Pageable pageable = pageableBuilder.build(filter);

        Optional<Specification<Record>> specification = specificationBuilder.build(filter);

        Page<Record> page = specification
                .map(spec -> recordRepository.findAll(spec, pageable))
                .orElseGet(() -> new PageImpl<>(new ArrayList<>()));

        return parsePageRecordToBasePageableResponse(page);
    }

    public RecordResponseDTO save(RecordRequestDTO recordRequestDTO) {
        Record record = recordRequestDTOToRecord(recordRequestDTO);
        return recordToRecordResponseDTO(recordRepository.save(record));
    }

    public void update(Long id, RecordRequestDTO recordRequestDTO) {
        if (!recordRepository.existsById(id)) {
            throw new EntityNotFoundException("O recorde de id " + id + " não foi encontrado.");
        }

        Record record = recordRequestDTOToRecord(id, recordRequestDTO);
        recordRepository.save(record);
    }

    public void delete(Long id) {
        recordRepository.deleteById(id);
    }

    private BasePageableResponse<RecordResponseDTO> parsePageRecordToBasePageableResponse(Page<Record> page) {
        List<RecordResponseDTO> list = page.getContent()
                .stream()
                .map(this::recordToRecordResponseDTO)
                .collect(Collectors.toList());

        return BasePageableResponse.<RecordResponseDTO>builder()
                .list(list)
                .page(page.getNumber())
                .totalElements(page.getTotalElements())
                .offset(page.getSize())
                .build();
    }

    private RecordResponseDTO recordToRecordResponseDTO(Record record) {
        //return modelMapper.map(record, RecordResponseDTO.class);
        return RecordResponseDTO.builder()
                .id(record.getId())
                .name(record.getName())
                .description(record.getDescription())
                .distance(record.getDistance())
                .realizationDate(record.getRealizationDate())
                .realizationTime(record.getRealizationTime())
                .proof(proofToProofResponseDTO(record.getProof()))
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

    private Record recordRequestDTOToRecord(RecordRequestDTO recordRequestDTO) {
        return Record.builder()
                .name(recordRequestDTO.getName())
                .description(recordRequestDTO.getDescription())
                .distance(recordRequestDTO.getDistance())
                .realizationDate(recordRequestDTO.getRealizationDate())
                .realizationTime(recordRequestDTO.getRealizationTime())
                .proof(Proof.builder().id(recordRequestDTO.getProofId()).build())
                .build();
    }

    private Record recordRequestDTOToRecord(Long id, RecordRequestDTO recordRequestDTO) {
        return Record.builder()
                .id(id)
                .name(recordRequestDTO.getName())
                .description(recordRequestDTO.getDescription())
                .distance(recordRequestDTO.getDistance())
                .realizationDate(recordRequestDTO.getRealizationDate())
                .realizationTime(recordRequestDTO.getRealizationTime())
                .proof(Proof.builder().id(recordRequestDTO.getProofId()).build())
                .build();
    }
}