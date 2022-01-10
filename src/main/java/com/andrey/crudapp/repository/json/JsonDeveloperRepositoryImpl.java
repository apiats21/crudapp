package com.andrey.crudapp.repository.json;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.repository.DeveloperRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {

    private final Gson gson = new Gson();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String DEVELOPER_FILE_PATH = "src/main/resources/developers.json";

    @Override
    public Developer getById(Long id) {
        return developersAsList().stream().filter(s -> s.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> currentDevelopers = developersAsList();
        developer.setId(generateMaxId(currentDevelopers));

        currentDevelopers.add(developer);
        writeDevelopersToFile(currentDevelopers);

        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return developersAsList();
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> currentDevelopers = developersAsList();
        currentDevelopers.forEach(s -> {
            if (s.getId().equals(developer.getId())) {
                s.setFirstName(developer.getFirstName());
                s.setLastName(developer.getLastName());
                s.setSkills(developer.getSkills());
            }
        });
        writeDevelopersToFile(currentDevelopers);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> currentDevelopers = developersAsList();
        currentDevelopers.removeIf(s -> s.getId().equals(id));
        writeDevelopersToFile(currentDevelopers);
    }

    private List<Developer> developersAsList() {
        try(FileReader reader = new FileReader(DEVELOPER_FILE_PATH)) {
            return new ArrayList<>(Arrays.asList(gson.fromJson(reader, Developer[].class)));

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private void writeDevelopersToFile(List<Developer> tempList) {
        try {
            mapper.writeValue(new File(DEVELOPER_FILE_PATH), tempList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long generateMaxId(List<Developer> developers) {
        Developer developerWithMaxId = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        if(developerWithMaxId == null) {
            return 1L;
        }
        return developerWithMaxId.getId() + 1;
    }
}
