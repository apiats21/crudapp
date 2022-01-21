package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SkillServiceImplTest {

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

        Skill skill = session.get(Skill.class, id);

        Assertions.assertEquals("Java", skill.getName());
    }


    @Test
    @Order(3)
    void getByIdNotExist() {
        Long id = 1000L;

        Skill skill = session.get(Skill.class, id);

        Assertions.assertNull(skill);
    }

    @Test
    @Order(1)
    void create() {
        session.beginTransaction();
        Skill skill = Skill.builder()
                .name("Java")
                .build();
        Long id = (Long) session.save(skill);
        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Skill> skills = session.createQuery("FROM Skill", Skill.class).getResultList();

        Assertions.assertFalse(skills.isEmpty());
    }

    @Test
    @Order(5)
    void update() {
        Long id = 1L;
        Skill skill = Skill.builder()
                .id(id)
                .name("C#dd")
                .build();

        session.beginTransaction();
        session.update(skill);
        session.getTransaction().commit();

        Skill updSkill = session.get(Skill.class, id);

        Assertions.assertEquals("C#dd", updSkill.getName());
    }

    @Test
    @Order(6)
    void deleteById() {
        Long id = 1L;
        Skill skill = session.get(Skill.class, id);

        session.beginTransaction();
        session.delete(skill);
        session.getTransaction().commit();

        Skill deletedSkill = session.get(Skill.class, id);

        Assertions.assertNull(deletedSkill);
    }
}