package com.pucminas.cbea.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pucminas.cbea.cbea.championship.Championship;
import com.pucminas.cbea.cbea.championship.IChampionshipRepository;
import com.pucminas.cbea.cbea.coach.Coach;
import com.pucminas.cbea.cbea.coach.ICoachRepository;
import com.pucminas.cbea.cbea.proof.IProofRepository;
import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.proofswimmingstyle.IProofSwimmingStyleRepository;
import com.pucminas.cbea.cbea.proofswimmingstyle.ProofSwimmingStyle;
import com.pucminas.cbea.cbea.proofteam.IProofTeamRepository;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.record.IRecordRepository;
import com.pucminas.cbea.cbea.record.Record;
import com.pucminas.cbea.cbea.swimmer.ISwimmerRepository;
import com.pucminas.cbea.cbea.swimmer.Swimmer;
import com.pucminas.cbea.cbea.swimmingstyle.ISwimmingStyleRepository;
import com.pucminas.cbea.cbea.swimmingstyle.SwimmingStyle;
import com.pucminas.cbea.cbea.team.ITeamRepository;
import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.util.Validation;
import lombok.extern.java.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Log
@Configuration
public class RunOnReady {

    private static final String PATH_RESOURCE = "/ready/run_ready.json";

    @Autowired
    private IChampionshipRepository championshipRepository;

    @Autowired
    private ICoachRepository coachRepository;

    @Autowired
    private IProofRepository proofRepository;

    @Autowired
    private IProofSwimmingStyleRepository proofSwimmingStyleRepository;

    @Autowired
    private IProofTeamRepository proofTeamRepository;

    @Autowired
    private IRecordRepository recordRepository;

    @Autowired
    private ISwimmerRepository swimmerRepository;

    @Autowired
    private ISwimmingStyleRepository swimmingStyleRepository;

    @Autowired
    private ITeamRepository teamRepository;

    @Value("${runOnReady.championship}")
    private boolean runOnReadyChampionships;

    @Value("${runOnReady.coach}")
    private boolean runOnReadyCoaches;

    @Value("${runOnReady.proof}")
    private boolean runOnReadyProofs;

    @Value("${runOnReady.proofswimmingstyle}")
    private boolean runOnReadyProofSwimmingStyles;

    @Value("${runOnReady.proofteam}")
    private boolean runOnReadyProofTeams;

    @Value("${runOnReady.record}")
    private boolean runOnReadyRecords;

    @Value("${runOnReady.swimmer}")
    private boolean runOnReadySwimmers;

    @Value("${runOnReady.swimmingstyle}")
    private boolean runOnReadySwimmingStyles;

    @Value("${runOnReady.team}")
    private boolean runOnReadyTeams;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        List<Championship> championshipList = this.objectListFromJson("championship", new TypeToken<ArrayList<Championship>>() {
        }.getType());
        List<Coach> coachList = this.objectListFromJson("coach", new TypeToken<ArrayList<Coach>>() {
        }.getType());
        List<Proof> proofList = this.objectListFromJson("proof", new TypeToken<ArrayList<Proof>>() {
        }.getType());
        List<SwimmingStyle> swimmingStyleList = this.objectListFromJson("swimming-style", new TypeToken<ArrayList<SwimmingStyle>>() {
        }.getType());
        List<ProofSwimmingStyle> proofSwimmingStyleList = this.objectListFromJson("proof-swimming-style", new TypeToken<ArrayList<ProofSwimmingStyle>>() {
        }.getType());
        List<Team> teamList = this.objectListFromJson("team", new TypeToken<ArrayList<Team>>() {
        }.getType());
        List<ProofTeam> proofTeamList = this.objectListFromJson("proof-team", new TypeToken<ArrayList<ProofTeam>>() {
        }.getType());
        List<Record> recordList = this.objectListFromJson("record", new TypeToken<ArrayList<Record>>() {
        }.getType());
        List<Swimmer> swimmerList = this.objectListFromJson("swimmer", new TypeToken<ArrayList<Swimmer>>() {
        }.getType());

        if (runOnReadyChampionships) {
            championshipList.forEach(championship -> {
                this.createChampionshipIfNotExists(championship);
            });
        }

        if (runOnReadyCoaches) {
            coachList.forEach(coach -> {
                this.createCoachIfNotExists(coach);
            });
        }

        if (runOnReadyProofs) {
            proofList.forEach(proof -> {
                this.createProofIfNotExists(proof);
            });
        }

        if (runOnReadySwimmingStyles) {
            swimmingStyleList.forEach(swimmingStyle -> {
                this.createSwimmingStyleIfNotExists(swimmingStyle);
            });
        }

        if (runOnReadyProofSwimmingStyles) {
            proofSwimmingStyleList.forEach(proofSwimmingStyle -> {
                this.createProofSwimmingStyleIfNotExists(proofSwimmingStyle);
            });
        }

        if (runOnReadyTeams) {
            teamList.forEach(team -> {
                this.createTeamIfNotExists(team);
            });
        }

        if (runOnReadyProofTeams) {
            proofTeamList.forEach(proofTeam -> {
                this.createProofTeamIfNotExists(proofTeam);
            });
        }

        if (runOnReadyRecords) {
            recordList.forEach(record -> {
                this.createRecordIfNotExists(record);
            });
        }

        if (runOnReadySwimmers) {
            swimmerList.forEach(swimmer -> {
                this.createSwimmerIfNotExists(swimmer);
            });
        }
    }

    private void createChampionshipIfNotExists(Championship championship) {
        if (Validation.isNull(championship.getId()) || !championshipRepository.existsById(championship.getId())) {
            championshipRepository.save(championship);
        }
    }

    private void createCoachIfNotExists(Coach coach) {
        if (Validation.isNull(coach.getId()) || !coachRepository.existsById(coach.getId())) {
            coachRepository.save(coach);
        }
    }

    private void createProofIfNotExists(Proof proof) {
        if (Validation.isNull(proof.getId()) || !proofRepository.existsById(proof.getId())) {
            proofRepository.save(proof);
        }
    }

    private void createSwimmingStyleIfNotExists(SwimmingStyle swimmingStyle) {
        if (Validation.isNull(swimmingStyle.getId()) || !swimmingStyleRepository.existsById(swimmingStyle.getId())) {
            swimmingStyleRepository.save(swimmingStyle);
        }
    }

    private void createProofSwimmingStyleIfNotExists(ProofSwimmingStyle proofSwimmingStyle) {
        if (Validation.isNull(proofSwimmingStyle.getId()) || !proofSwimmingStyleRepository.existsById(proofSwimmingStyle.getId())) {
            proofSwimmingStyleRepository.save(proofSwimmingStyle);
        }
    }

    private void createTeamIfNotExists(Team team) {
        if (Validation.isNull(team.getId()) || !teamRepository.existsById(team.getId())) {
            teamRepository.save(team);
        }
    }

    private void createProofTeamIfNotExists(ProofTeam proofTeam) {
        if (Validation.isNull(proofTeam.getId()) || !proofTeamRepository.existsById(proofTeam.getId())) {
            proofTeamRepository.save(proofTeam);
        }
    }

    private void createRecordIfNotExists(Record record) {
        if (Validation.isNull(record.getId()) || !recordRepository.existsById(record.getId())) {
            recordRepository.save(record);
        }
    }

    private void createSwimmerIfNotExists(Swimmer swimmer) {
        if (Validation.isNull(swimmer.getId()) || !swimmerRepository.existsById(swimmer.getId())) {
            swimmerRepository.save(swimmer);
        }
    }

    private String getContentFromResource(String dataGroup) {
        JSONObject jsonDataObject = null;
        try {
            InputStream stream = RunOnReady.class.getResourceAsStream(PATH_RESOURCE);

            jsonDataObject = new JSONObject(StreamUtils.copyToString(stream, Charset.forName("UTF-8")));

            assert jsonDataObject != null;

            return jsonDataObject.get(dataGroup).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> objectListFromJson(String dataGroup, Type listType) {
        List<T> list = null;

        try {
            String json = getContentFromResource(dataGroup);
            list = new Gson().fromJson(json, listType);
        } catch (JSONException exception) {
            throw new RuntimeException(exception);
        }

        return list;
    }
}