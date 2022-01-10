package com.andrey.crudapp.controller;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.service.TeamService;

import java.util.List;

public class TeamController {
    private final TeamService teamService = new TeamService();

    public Team getById(Long id) {
        return teamService.getById(id);
    }

    public Team create(String name, List<Developer> developers) {
        return teamService.create(name, developers);
    }

    public List<Team> getAll() {
        return teamService.getAll();
    }

    public Team update(Long id, String name, List<Developer> developers) {
        Team team = new Team(id, name, developers);
        return teamService.update(id, name, developers);
    }

    public void deleteById(Long id) {
        teamService.deleteById(id);
    }
}
