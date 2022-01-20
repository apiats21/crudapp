package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Team;

import java.util.List;

public interface TeamService {
    public Team getById(Long id);

    public Team create(Team team);

    public List<Team> getAll();

    public Team update(Team team);

    public void deleteById(Long id);

}
