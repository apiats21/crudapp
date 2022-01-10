package com.andrey.crudapp.service;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.DeveloperRepository;
import com.andrey.crudapp.repository.jdbc.JdbcDeveloperRepositoryImpl;
import com.andrey.crudapp.repository.json.JsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperService {
    private final DeveloperRepository developerRepository = new JdbcDeveloperRepositoryImpl();

    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    public Developer create(String firstName, String lastName, List<Skill> skills) {
        Developer developer = new Developer(firstName, lastName, skills);
        return developerRepository.save(developer);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Developer update(Long id, String firstName, String lastname, List<Skill> skills) {
        Developer developer = new Developer(id, firstName, lastname, skills);
        return developerRepository.update(developer);
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }
}
