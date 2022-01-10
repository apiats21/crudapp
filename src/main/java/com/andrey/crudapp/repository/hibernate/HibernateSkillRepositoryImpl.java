package com.andrey.crudapp.repository.hibernate;

import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.SkillRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if( sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    @Override
    public Skill getById(Long aLong) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, aLong);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public Skill save(Skill skill) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Skill newSkill = new Skill(skill.getName());
        session.save(newSkill);
        transaction.commit();
        session.close();
        return newSkill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Skill> skills = session.createQuery("FROM Skill", Skill.class).getResultList();
        transaction.commit();
        session.close();
        return skills;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Skill updSkill = session.get(Skill.class, skill.getId());
        updSkill.setName(skill.getName());
        session.update(updSkill);
        transaction.commit();
        session.close();
        return updSkill;
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, aLong);
        session.delete(skill);
        transaction.commit();
        session.close();
    }
}
