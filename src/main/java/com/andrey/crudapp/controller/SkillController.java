package com.andrey.crudapp.controller;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.service.SkillService;

import java.util.List;

public class SkillController {

    private final SkillService skillService = new SkillService();

    public Skill getById(Long id) {
        return skillService.getById(id);
    }

    public Skill create(String name) {
        return skillService.create(name);
    }

    public List<Skill> getAll() {
        return skillService.getAll();
    }

    public Skill update(Long id, String name) {
        return skillService.update(id, name);
    }

    public void deleteById(Long id) {
        skillService.deleteById(id);
    }
}