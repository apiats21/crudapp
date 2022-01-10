package com.andrey.crudapp.view;
import com.andrey.crudapp.controller.SkillController;
import com.andrey.crudapp.model.Skill;
import java.util.Scanner;

public class SkillView {

    private final Scanner scanner = new Scanner(System.in);
    private final SkillController skillController = new SkillController();

    public void getSkillById() {
        System.out.println("Enter skill's id: ");
        Long id = scanner.nextLong();
        System.out.println("Skill: " + skillController.getById(id));
    }

    public void createSkill() {
        System.out.println("Enter Skill name:");
        String name = scanner.nextLine();
        Skill skill = skillController.create(name);
        System.out.println("Skill was created: " + skill);
    }

    public void updateSkill() {
        System.out.println("Enter skill id: ");
        Long id = scanner.nextLong();
        System.out.println("Enter new skill name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        Skill skill = skillController.update(id, name);
        System.out.println("Skill was updated: " + skill);
    }

    public void getAllSkills() {
        System.out.println(skillController.getAll());
    }

    public void deleteSkillById() {
        System.out.println("Enter skill's id: ");
        Long id = scanner.nextLong();
        skillController.deleteById(id);
        System.out.println("Skill was deleted." );
    }
}
