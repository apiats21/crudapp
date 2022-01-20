package com.andrey.crudapp.controller;

import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.service.DeveloperServiceImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperServiceImpl developerServiceImpl = new DeveloperServiceImpl();

    public Developer getById(Long id) {
        return developerServiceImpl.getById(id);
    }

    public Developer create(String firstName, String lastName, List<Skill> skills) {
        Developer developer = developerServiceImpl.create(Developer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .skills(skills)
                .build());
        return developer;
    }


    public List<Developer> getAll() {
        return developerServiceImpl.getAll();
    }

    public Developer update(Long id, String firstName, String lastname, List<Skill> skills) {
        return developerServiceImpl.update(Developer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastname)
                .skills(skills)
                .build());
    }


    public void deleteById(Long id) {
        developerServiceImpl.deleteById(id);
    }
}
