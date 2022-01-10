package com.andrey.crudapp.controller;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.service.DeveloperService;

import java.util.List;

public class DeveloperController {

    private final DeveloperService developerService = new DeveloperService();

    public Developer getById(Long id) {
        return developerService.getById(id);
    }

    public Developer create(String firstName, String lastName, List<Skill> skills) {
        return developerService.create(firstName, lastName, skills);
    }

    public List<Developer> getAll() {
        return developerService.getAll();
    }

    public Developer update(Long id, String firstName, String lastname, List<Skill> skills) {
        return developerService.update(id, firstName, lastname,skills);
    }

    public void deleteById(Long id) {
        developerService.deleteById(id);
    }
}
