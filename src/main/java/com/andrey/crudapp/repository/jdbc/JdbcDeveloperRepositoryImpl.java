package com.andrey.crudapp.repository.jdbc;

import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.DeveloperRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    static final String DATABASE_URL = "jdbc:postgresql://localhost/crud";
    static final String JDBC_DRIVER = "org.postgresql.Driver";

    static final String USER = "postgres";
    static final String PASSWORD = "fri13tech";

    @Override
    public Developer getById(Long aLong) {
        return null;
    }

    @Override
    public Developer save(Developer developer) {
        return null;
    }

    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
