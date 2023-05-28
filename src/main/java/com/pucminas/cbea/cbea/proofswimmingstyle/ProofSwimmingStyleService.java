package com.pucminas.cbea.cbea.proofswimmingstyle;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.swimmingstyle.SwimmingStyle;
import com.pucminas.cbea.global.util.Validation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
public class ProofSwimmingStyleService {

    @Autowired
    private IProofSwimmingStyleRepository proofSwimmingStyleRepository;

    public List<Long> findProofSwimmingStyleIdsBySwimmingStylesIds(List<Long> swimmingStylesIds) {
        if (Validation.isNotEmpty(swimmingStylesIds)) {
            return proofSwimmingStyleRepository.findAllBySwimmingStyleIn(swimmingStylesIds.stream().map(swimmingStylesId -> SwimmingStyle.builder().id(swimmingStylesId).build()).collect(Collectors.toList()))
                    .stream().map(ProofSwimmingStyle::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<ProofSwimmingStyle> save(Proof proof, List<SwimmingStyle> swimmingStyles) {
        List<ProofSwimmingStyle> proofSwimmingStyles = new ArrayList<>();
        swimmingStyles.forEach(swimmingStyle -> proofSwimmingStyles.add(proofSwimmingStyleRepository.save(ProofSwimmingStyle.builder().proof(proof).swimmingStyle(swimmingStyle).build())));
        return proofSwimmingStyles;
    }

    public void deleteAllByProof(Proof proof) {
        proofSwimmingStyleRepository.deleteAllByProof(proof);
    }
}