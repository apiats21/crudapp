package com.andrey.crudapp.controller;

import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.service.TeamServiceImpl;

import java.util.List;

public class TeamController {
    private final TeamServiceImpl teamService = new TeamServiceImpl();

    public Team getById(Long id) {
        return teamService.getById(id);
    }

    public Team create(String name, List<Developer> developers) {
        Team team = teamService.create(Team.builder()
                .name(name)
                .developers(developers)
                .build());
        return team;
    }

    public List<Team> getAll() {
        return teamService.getAll();
    }

    public Team update(Long id, String name, List<Developer> developers) {
        Team team = teamService.update(Team.builder()
                .id(id)
                .name(name)
                .developers(developers)
                .build());
        return team;
    }

    public void deleteById(Long id) {
        teamService.deleteById(id);
    }
}
