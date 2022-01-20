package com.andrey.crudapp.service;
import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.repository.TeamRepository;
import com.andrey.crudapp.repository.hibernate.HibernateTeamRepositoryImpl;
import java.util.List;

public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService() { this.teamRepository = new HibernateTeamRepositoryImpl(); }

    public TeamService(TeamRepository teamRepository) { this.teamRepository = teamRepository; }


    public Team getById(Long id) {
        return teamRepository.getById(id);
    }

    public Team create(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    public Team update(Team team) {
        return teamRepository.update(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}