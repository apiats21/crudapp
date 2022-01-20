package com.andrey.crudapp.view;
import com.andrey.crudapp.controller.DeveloperController;
import com.andrey.crudapp.controller.TeamController;
import com.andrey.crudapp.model.Developer;
import com.andrey.crudapp.model.Skill;
import com.andrey.crudapp.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamView {
    private final Scanner scanner = new Scanner(System.in);
    private final TeamController teamController = new TeamController();
    private final DeveloperController developerController = new DeveloperController();

    public void getTeamById() {
        System.out.println("Enter team id");
        Long id = scanner.nextLong();
        System.out.println("Team: " + teamController.getById(id));
    }

    public void createTeam() {
        System.out.println("Enter team name:");
        String name = scanner.nextLine();
        System.out.println("Enter developers id, -exit- to finish");
        List<Developer> developers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            Long developerId = scanner.nextLong();
            Developer result = developerController.getById(developerId);
            developers.add(result);
        }
        Team team = teamController.create(name, developers);
        System.out.println("Team created: " + team);
    }

    public void updateTeam() {
        System.out.println("Enter team id:");
        Long id = scanner.nextLong();
        System.out.println("Enter team new name:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter developers id, -exit- to finish");
        List<Developer> developers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            Long developerId = scanner.nextLong();
            Developer result = developerController.getById(developerId);
            developers.add(result);
        }
        Team team = teamController.update(id, name, developers);
        System.out.println("Team was updated: " + team);
    }

    public void getAllTeams() {
        System.out.println(teamController.getAll());
    }

    public void deleteTeamById() {
        System.out.println("Enter team id: ");
        Long id = scanner.nextLong();
        teamController.deleteById(id);
        System.out.println("Team was deleted");
    }
}
