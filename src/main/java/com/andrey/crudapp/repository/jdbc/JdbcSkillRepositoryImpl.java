package com.andrey.crudapp.repository.jdbc;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.SkillRepository;
import com.andrey.crudapp.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillRepositoryImpl implements SkillRepository {

    private static final String GET_SKILL_BY_ID = "SELECT * FROM skills WHERE skill_id = %s";
    private static final String SAVE_SKILL =  "INSERT INTO skills (skill_name) VALUES ('%s')";
    private static final String GET_ALL_SKILLS = "SELECT * FROM skills";
    private static final String UPDATE_SKILL = "UPDATE skills SET skill_name='%s' WHERE skill_id='%s'";
    private static final String DELETE_SKILL_BY_ID = "DELETE FROM skills WHERE skill_id = %s";

    @Override
    public Skill getById(Long id) {

        try {
            PreparedStatement stmt = JdbcUtils.getPreparedStatement(String.format(GET_SKILL_BY_ID, id));
            ResultSet rs = stmt.executeQuery();

            return getSkillFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Skill save(Skill skill) {

        try {
            PreparedStatement stmt = JdbcUtils.getPreparedStatement(String.format(SAVE_SKILL, skill.getName()));
            stmt.executeUpdate();

            return skill;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();

        try {
            PreparedStatement stmt = JdbcUtils.getPreparedStatement(GET_ALL_SKILLS);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Skill skill = new Skill(rs.getLong(1), rs.getString(2));
                skills.add(skill);
            }
            return skills;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Skill update(Skill skill) {

        try {
            PreparedStatement stmt = JdbcUtils.getPreparedStatement
                    (String.format(UPDATE_SKILL, skill.getName(), skill.getId()));
            stmt.executeUpdate();

            return skill;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {

        try {
            PreparedStatement stmt = JdbcUtils.getPreparedStatement(String.format(DELETE_SKILL_BY_ID, id));
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Skill getSkillFromResultSet(ResultSet rs) throws SQLException {
        Skill skill = new Skill();
        skill.setId(null);

        while (rs.next()) {
            skill.setId(rs.getLong("skill_id"));
            skill.setName(rs.getString("skill_name"));
        }

        return skill;
    }
}
