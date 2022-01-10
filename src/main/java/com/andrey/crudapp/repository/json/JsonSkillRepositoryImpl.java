package com.andrey.crudapp.repository.json;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.SkillRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JsonSkillRepositoryImpl implements SkillRepository {

    private final Gson gson = new Gson();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String SKILL_FILE_PATH = "src/main/resources/skills.json";

    @Override
    public Skill getById(Long id) {
        return skillsAsList().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> currentSkills = skillsAsList();
        skill.setId(generateMaxId(currentSkills));
        currentSkills.add(skill);
        writeSkillsToFile(currentSkills);

        return skill;
    }

    @Override
    public List<Skill> getAll() {
        return skillsAsList();
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> currentSkills = skillsAsList();
        currentSkills.forEach(s -> {
            if(s.getId().equals(skill.getId())) {
                s.setName(skill.getName());
            }
        });
        writeSkillsToFile(currentSkills);
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> currentList = skillsAsList();
        currentList.removeIf(s -> s.getId().equals(id));
        writeSkillsToFile(currentList);
    }

    private List<Skill> skillsAsList() {

        try (FileReader reader = new FileReader(SKILL_FILE_PATH)) {
            return new ArrayList<>(Arrays.asList(gson.fromJson(reader, Skill[].class)));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private void writeSkillsToFile(List<Skill> tempList) {
        try {
            mapper.writeValue(new File(SKILL_FILE_PATH), tempList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateMaxId(List<Skill> skills) {
        Skill skillWithMaxId = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        if(skillWithMaxId == null) {
            return 1L;
        }
        return  skillWithMaxId.getId() + 1;
    }
}