package com.andrey.crudapp.service;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.SkillRepository;
import com.andrey.crudapp.repository.hibernate.HibernateSkillRepositoryImpl;
import java.util.List;

public class SkillServiceImpl implements SkillService{
    private final SkillRepository skillRepository;

    public SkillServiceImpl() { this.skillRepository = new HibernateSkillRepositoryImpl(); }

    public SkillServiceImpl(SkillRepository skillRepository) { this.skillRepository = skillRepository; }


    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public Skill create(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill update(Skill skill) {
        return skillRepository.update(skill);
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}