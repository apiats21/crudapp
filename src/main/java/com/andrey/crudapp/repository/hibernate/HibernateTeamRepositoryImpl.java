package com.andrey.crudapp.repository.hibernate;
import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.repository.TeamRepository;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Collections;
import java.util.List;

public class HibernateTeamRepositoryImpl implements TeamRepository {
    @Override
    public Team getById(Long aLong) {
        try (Session session = HibernateUtils.getSession()) {
            Team result = session.get(Team.class, aLong);
            if (result != null) {
                Hibernate.initialize(result.getDevelopers());
            }
            return result;
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
            List<Team> teams = session.createQuery("FROM Team", Team.class).getResultList();
            for (Team team : teams) {
                Hibernate.initialize(team.getDevelopers());
            }
            return teams;
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
