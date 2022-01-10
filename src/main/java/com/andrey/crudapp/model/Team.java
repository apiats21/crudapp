package com.andrey.crudapp.model;
import java.util.List;

public class Team {
    private Long id;
    private String name;
    private List<Developer> developers;
    private TeamStatus status;

    public Team(long id, String name, List<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }

    public Team(String name, List<Developer> developers) {
        this.name = name;
        this.developers = developers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developers=" + developers +
                '}';
    }
}
