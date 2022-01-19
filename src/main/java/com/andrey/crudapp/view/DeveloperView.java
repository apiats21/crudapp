package com.andrey.crudapp.view;
import com.andrey.crudapp.controller.DeveloperController;
import com.andrey.crudapp.controller.SkillController;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.repository.hibernate.HibernateDeveloperRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private final Scanner scanner = new Scanner(System.in);
    private final DeveloperController developerController = new DeveloperController();
    private final SkillController skillController = new SkillController();

    public void getDeveloperById() {
        System.out.println("Enter developer's id:");
        Long id = scanner.nextLong();
        System.out.println("Developer: " + developerController.getById(id));
    }

    public void createDeveloper() {
        System.out.println("Enter developer first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter developer last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter skills id, -exit- to finish");
        List<Skill> skills = new ArrayList<>();
        while (scanner.hasNextInt()) {
            Long skillId = scanner.nextLong();
//            developerController.saveSkillToDevSkillDb(skillId);
            Skill result = skillController.getById(skillId);
            skills.add(result);
        }
        Developer developer = developerController.create(firstName, lastName, skills);
        System.out.println("Developer created: " + developer);
    }

    public void updateDeveloper() {
        System.out.println("Enter developer id:");
        Long id = scanner.nextLong();
        System.out.println("Enter developer first name:");
        scanner.nextLine();
        String firstname = scanner.nextLine();
        System.out.println("Enter developer last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter skills id, -exit- to finish");
        List<Skill> skills = new ArrayList<>();
        while (scanner.hasNextInt()) {
            Long skillId = scanner.nextLong();
//            developerController.saveSkillToDevSkillDb(skillId);
            Skill result1 = skillController.getById(skillId);
            skills.add(result1);
        }
        Developer developer = developerController.update(id, firstname, lastName, skills);
        System.out.println("Developer updated: " + developer);
    }

    public void getAllDevelopers() {
        System.out.println(developerController.getAll());
    }

    public void deleteDeveloperById() {
        System.out.println("Enter developer id: ");
        Long id = scanner.nextLong();
        developerController.deleteById(id);
        System.out.println("Developer was deleted");
    }
}
