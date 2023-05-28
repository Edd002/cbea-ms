package com.pucminas.cbea.cbea.proofteam;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProofTeamRepository extends JpaRepository<ProofTeam, Long>, JpaSpecificationExecutor<ProofTeam> {

    List<ProofTeam> findAllByTeamIn(List<Team> teams);

    void deleteAllByProof(Proof proof);
}