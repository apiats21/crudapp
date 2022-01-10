package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.repository.TeamRepository;
import com.andrey.crudapp.repository.json.JsonTeamRepositoryImpl;

import java.util.List;

public class TeamService {
    private final TeamRepository teamRepository = new JsonTeamRepositoryImpl();

    public Team getById(Long id) {
        return teamRepository.getById(id);
    }

    public Team create(String name, List<Developer> developers) {
        Team team = new Team(name, developers);
        return teamRepository.save(team);
    }

    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    public Team update(Long id, String name, List<Developer> developers) {
        Team team = new Team(id, name, developers);
        return teamRepository.update(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
