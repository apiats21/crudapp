package com.andrey.crudapp;
import com.andrey.crudapp.utils.HibernateUtils;
import com.andrey.crudapp.view.DeveloperView;
import com.andrey.crudapp.view.SkillView;
import com.andrey.crudapp.view.TeamView;

public class Main {

    public static void main(String[] args) {

//        JdbcUtils.getConnectionToDb();
        HibernateUtils.getSessionFactory();

        SkillView skillView = new SkillView();
//        skillView.createSkill();
//        skillView.updateSkill();
//        skillView.getAllSkills();
//        skillView.getSkillById();
//        skillView.deleteSkillById();

        DeveloperView developerView = new DeveloperView();
//        developerView.getDeveloperById();
//        developerView.createDeveloper();
//        developerView.getAllDevelopers();
        developerView.updateDeveloper();
//        developerView.deleteDeveloperById();

        TeamView teamView = new TeamView();
//        teamView.getTeamById();
//        teamView.createTeam();
//        teamView.getAllTeams();
//        teamView.updateTeam();
//        teamView.deleteTeamById();
    }
}
