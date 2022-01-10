package com.andrey.crudapp.model;

import javax.persistence.*;


@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "skill_id")
    private Long id;
    @Column (name = "skill_name")
    private String name;

    public Skill() {
    }

    public Skill(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return  "id= " + id +
                ", name= " + name;
    }
}