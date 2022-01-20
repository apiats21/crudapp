package com.andrey.crudapp.service;

import com.andrey.crudapp.model.Developer;

import java.util.List;

public interface DeveloperService {
    public Developer getById(Long id);

    public Developer create(Developer developer);

    public List<Developer> getAll();

    public Developer update(Developer developer);

    public void deleteById(Long id);
}
