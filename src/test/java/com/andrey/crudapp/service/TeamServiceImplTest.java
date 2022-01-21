package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Team;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import java.util.Collections;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeamServiceImplTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    static void beforeAll() {sessionFactory = HibernateUtils.getSessionFactory();}

    @AfterAll
    static void afterAll() {sessionFactory.close();}

    @BeforeEach
    void setUp() {session = sessionFactory.openSession();}

    @AfterEach
    void tearDown() {session.close();}


    @Test
    @Order(2)
    void getById() {
        Long id = 1L;

        Team team = session.get(Team.class, id);

        Assertions.assertEquals("team A", team.getName());
    }


    @Test
    @Order(3)
    void getByIdNotExist() {
        Long id = 1000L;

        Team team = session.get(Team.class, id);

        Assertions.assertNull(team);
    }

    @Test
    @Order(1)
    void create() {

        session.beginTransaction();
        Team team = Team.builder()
                .name("team A")
                .developers(Collections.emptyList())
                .build();
        Long id = (Long) session.save(team);
        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Team> teams = session.createQuery("FROM Team", Team.class).getResultList();

        Assertions.assertFalse(teams.isEmpty());
    }

    @Test
    @Order(5)
    void update() {
        Long id = 1L;
        Team team = Team.builder()
                .id(id)
                .name("team B")
                .developers(Collections.emptyList())
                .build();

        session.beginTransaction();
        session.update(team);
        session.getTransaction().commit();

        Team updTeam =  session.get(Team.class, id);

        Assertions.assertEquals("team B", updTeam.getName());
    }

    @Test
    @Order(6)
    void deleteById() {
        Long id = 1L;
        Team team = session.get(Team.class, id);

        session.beginTransaction();
        session.delete(team);
        session.getTransaction().commit();

        Team deletedTeam = session.get(Team.class, id);

        Assertions.assertNull(deletedTeam);
    }
}