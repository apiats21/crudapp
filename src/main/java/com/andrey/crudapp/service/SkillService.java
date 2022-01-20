package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Skill;

import java.util.List;

public interface SkillService {

    public Skill getById(Long id);

    public Skill create(Skill skill);

    public List<Skill> getAll();

    public Skill update(Skill skill);

    public void deleteById(Long id);
}
