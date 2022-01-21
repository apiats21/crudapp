package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeveloperServiceImplTest {

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

        Developer developer = session.get(Developer.class, id);

        Assertions.assertEquals("Andrei", developer.getFirstName());
        Assertions.assertEquals("Piatsevich", developer.getLastName());
    }


    @Test
    @Order(3)
    void getByIdNotExist() {
        Long id = 1000L;

        Developer developer = session.get(Developer.class, id);

        Assertions.assertNull(developer);
    }

    @Test
    @Order(1)
    void create() {

        session.beginTransaction();
        Developer developer = Developer.builder()
                .firstName("Andrei")
                .lastName("Piatsevich")
                .skills(Collections.emptyList())
                .build();
        Long id = (Long) session.save(developer);
        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Developer> developers = session.createQuery("FROM Developer", Developer.class).getResultList();

        Assertions.assertFalse(developers.isEmpty());
    }

    @Test
    @Order(5)
    void update() {

        Long id = 1L;
        Developer developer = Developer.builder()
                .id(id)
                .firstName("Daniel")
                .lastName("Popesku")
                .skills(Collections.emptyList())
                .build();

        session.beginTransaction();
        session.update(developer);
        session.getTransaction().commit();

        Developer updDeveloper =  session.get(Developer.class, id);

        Assertions.assertEquals("Daniel", updDeveloper.getFirstName());
        Assertions.assertEquals("Popesku", updDeveloper.getLastName());
    }

    @Test
    @Order(6)
    void deleteById() {

        Long id = 1L;
        Developer developer = session.get(Developer.class, id);

        session.beginTransaction();
        session.delete(developer);
        session.getTransaction().commit();

        Developer deletedDeveloper = session.get(Developer.class, id);

        Assertions.assertNull(deletedDeveloper);
    }
}