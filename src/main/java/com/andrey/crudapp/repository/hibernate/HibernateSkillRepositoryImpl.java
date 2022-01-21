package com.andrey.crudapp.repository.hibernate;

import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.SkillRepository;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Collections;
import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository {


    @Override
    public Skill getById(Long aLong) {

        try (Session session = HibernateUtils.getSession()) {
            return session.get(Skill.class, aLong);
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public Skill save(Skill skill) {

        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(skill);
            skill.setId(id);
            transaction.commit();
            return skill;
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public List<Skill> getAll() {
        try (Session session = HibernateUtils.getSession()) {
            List<Skill> skills = session.createQuery("FROM Skill", Skill.class).getResultList();
            return skills;
        } catch (Throwable t) {
            return Collections.emptyList();
        }
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Skill updSkill = session.get(Skill.class, skill.getId());
            updSkill.setName(skill.getName());
            session.update(updSkill);
            transaction.commit();
            return updSkill;
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public void deleteById(Long aLong) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Skill skill = session.get(Skill.class, aLong);
            session.delete(skill);
            transaction.commit();
        }
    }
}
