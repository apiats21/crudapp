package com.andrey.crudapp.controller;

import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.service.SkillServiceImpl;

import java.util.List;

public class SkillController {

    private final SkillServiceImpl skillService = new SkillServiceImpl();

    public Skill getById(Long id) {
        return skillService.getById(id);
    }

    public Skill create(String name) {
        Skill skill = skillService.create(Skill.builder()
                .name(name)
                .build());
        return skill;
    }

    public List<Skill> getAll() {
        return skillService.getAll();
    }

    public Skill update(Long id, String name) {
        return skillService.update(Skill.builder()
                .id(id)
                .name(name)
                .build());
    }

    public void deleteById(Long id) {
        skillService.deleteById(id);
    }
}