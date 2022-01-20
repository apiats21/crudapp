package com.andrey.crudapp.service;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.repository.DeveloperRepository;
import com.andrey.crudapp.repository.hibernate.HibernateDeveloperRepositoryImpl;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService{
    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl() { this.developerRepository = new HibernateDeveloperRepositoryImpl(); }

    public DeveloperServiceImpl(DeveloperRepository developerRepository) { this.developerRepository = developerRepository; }


    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    public Developer create(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Developer update(Developer developer) {
        return developerRepository.update(developer);
    }

    public void deleteById(Long id) {
        developerRepository.deleteById(id);
    }
}