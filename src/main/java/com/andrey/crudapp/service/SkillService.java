package com.andrey.crudapp.service;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.SkillRepository;
import com.andrey.crudapp.repository.hibernate.HibernateSkillRepositoryImpl;
import com.andrey.crudapp.repository.jdbc.JdbcSkillRepositoryImpl;

import java.util.List;

public class SkillService {
//    private final SkillRepository skillRepository = new JdbcSkillRepositoryImpl();
    private final SkillRepository skillRepository = new HibernateSkillRepositoryImpl();

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public Skill create(String name) {
        Skill skill = new Skill(name);
        return skillRepository.save(skill);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill update(Long id, String name) {
        Skill skill = new Skill(id, name);
        return skillRepository.update(skill);
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
