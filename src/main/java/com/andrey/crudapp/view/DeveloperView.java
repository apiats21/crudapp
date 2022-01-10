package com.andrey.crudapp.view;
import com.andrey.crudapp.controller.DeveloperController;
import com.andrey.crudapp.controller.SkillController;
import com.andrey.crudapp.model.Developer;
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
        Developer developer = developerController.create(firstName, lastName, skillController.getAll());
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
        Developer developer = developerController.update(id, firstname, lastName, skillController.getAll());
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
