package com.andrey.crudapp.repository.hibernate;

import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.repository.TeamRepository;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Collections;
import java.util.List;

public class HibernateTeamRepositoryImpl implements TeamRepository {

    private static final String GET_TEAM_BY_ID = "FROM Team t join fetch t.developers WHERE t.id = :aLong";
    private static final String GET_ALL_TEAMS = "FROM Team t join fetch t.developers";

    @Override
    public Team getById(Long aLong) {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery(GET_TEAM_BY_ID);
            query.setParameter("aLong", aLong);
            List results = query.getResultList();
            return (Team) results.get(0);

        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public Team save(Team team) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(team);
            team.setId(id);
            transaction.commit();
            return team;
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public List<Team> getAll() {
        try (Session session = HibernateUtils.getSession()) {
            return session.createQuery(GET_ALL_TEAMS, Team.class).getResultList();
        } catch (Throwable t) {
            return Collections.emptyList();
        }
    }

    @Override
    public Team update(Team team) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Team updTeam = session.get(Team.class, team.getId());
            updTeam.setName(team.getName());
            updTeam.setDevelopers(team.getDevelopers());
            session.update(updTeam);
            transaction.commit();
            return updTeam;
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public void deleteById(Long aLong) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Team team = session.get(Team.class, aLong);
            session.delete(team);
            transaction.commit();
        }
    }
}
