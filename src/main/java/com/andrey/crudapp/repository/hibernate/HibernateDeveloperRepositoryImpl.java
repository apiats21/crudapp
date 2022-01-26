package com.andrey.crudapp.repository.hibernate;

import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.repository.DeveloperRepository;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class HibernateDeveloperRepositoryImpl implements DeveloperRepository {

    private static final String GET_DEVELOPER_BY_ID = "FROM Developer d join fetch d.skills WHERE d.id = :aLong";
    private static final String GET_ALL_DEVELOPERS ="FROM Developer d join fetch d.skills";


    @Override
    public Developer getById(Long aLong) {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery(GET_DEVELOPER_BY_ID);
            query.setParameter("aLong", aLong);
            List results = query.getResultList();
            return (Developer) results.get(0);

        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public Developer save(Developer developer) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(developer);
            developer.setId(id);
            transaction.commit();
            return developer;
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public List<Developer> getAll() {
        try (Session session = HibernateUtils.getSession()) {
            return session.createQuery(GET_ALL_DEVELOPERS, Developer.class).getResultList();
        } catch (Throwable t) {
            return Collections.emptyList();
        }
    }

    @Override
    public Developer update(Developer developer) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Developer updDeveloper = session.get(Developer.class, developer.getId());
            updDeveloper.setFirstName(developer.getFirstName());
            updDeveloper.setLastName(developer.getLastName());
            updDeveloper.setSkills(developer.getSkills());
            session.update(updDeveloper);
            transaction.commit();
            return developer;
        }catch (Throwable t) {
            return null;
        }
    }

    @Override
    public void deleteById(Long aLong) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, aLong);
            session.delete(developer);
            transaction.commit();
        }
    }
}
