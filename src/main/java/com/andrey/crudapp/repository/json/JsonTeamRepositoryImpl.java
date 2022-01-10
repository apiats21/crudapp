package com.andrey.crudapp.repository.json;
import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class JsonTeamRepositoryImpl implements TeamRepository {

    private final Gson gson = new Gson();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String TEAM_FILE_PATH = "src/main/resources/teams.json";

    @Override
    public Team getById(Long id) {
        return teamsAsList().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Team save(Team team) {
        List<Team> currentTeams = teamsAsList();
        team.setId(generateMaxId(currentTeams));
        currentTeams.add(team);
        writeTeamsToFile(currentTeams);
        return team;
    }

    @Override
    public List<Team> getAll() {
        return teamsAsList();
    }

    @Override
    public Team update(Team team) {
        List<Team> currentTeams = teamsAsList();
        currentTeams.forEach(s -> {
            if(s.getId().equals(team.getId())) {
                s.setName(team.getName());
                s.setDevelopers(team.getDevelopers());
            }
        });
        writeTeamsToFile(currentTeams);
        return team;
    }

    @Override
    public void deleteById(Long id) {
        List<Team> currentTeams = teamsAsList();
        currentTeams.removeIf(s -> s.getId().equals(id));
        writeTeamsToFile(currentTeams);
    }

    private List<Team> teamsAsList() {
        try(FileReader reader = new FileReader(TEAM_FILE_PATH)) {
            return new ArrayList<>(Arrays.asList(gson.fromJson(reader, Team[].class)));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private void writeTeamsToFile(List<Team> tempList) {
        try{
            mapper.writeValue(new File(TEAM_FILE_PATH), tempList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long generateMaxId(List<Team> teams) {
        Team teamWithMaxId = teams.stream().max(Comparator.comparing(Team::getId)).orElse(null);
        if(teamWithMaxId == null) {
            return 1L;
        }
        return teamWithMaxId.getId() + 1;
    }
}
