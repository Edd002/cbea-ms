package com.pucminas.cbea.cbea.proofswimmingstyle;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.swimmingstyle.SwimmingStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProofSwimmingStyleRepository extends JpaRepository<ProofSwimmingStyle, Long>, JpaSpecificationExecutor<ProofSwimmingStyle> {

    List<ProofSwimmingStyle> findAllBySwimmingStyleIn(List<SwimmingStyle> swimmingStyles);

    void deleteAllByProof(Proof proof);
}