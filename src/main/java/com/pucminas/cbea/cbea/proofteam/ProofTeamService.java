package com.pucminas.cbea.cbea.proofteam;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.util.Validation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
public class ProofTeamService {

    @Autowired
    private IProofTeamRepository proofTeamRepository;

    public List<Long> findProofTeamIdsByTeamIds(List<Long> teamIds) {
        if (Validation.isNotEmpty(teamIds)) {
            return proofTeamRepository.findAllByTeamIn(teamIds.stream().map(teamId -> Team.builder().id(teamId).build()).collect(Collectors.toList()))
                    .stream().map(ProofTeam::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<ProofTeam> save(Proof proof, List<Team> teams) {
        List<ProofTeam> proofTeams = new ArrayList<>();
        teams.forEach(team -> proofTeams.add(proofTeamRepository.save(ProofTeam.builder().proof(proof).team(team).build())));
        return proofTeams;
    }

    public void deleteAllByProof(Proof proof) {
        proofTeamRepository.deleteAllByProof(proof);
    }
}