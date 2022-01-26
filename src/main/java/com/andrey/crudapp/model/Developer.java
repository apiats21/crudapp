package com.andrey.crudapp.model;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "developers")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "developer_id")
    private Long id;
    @Column(name = "developer_firstname")
    private String firstName;
    @Column(name = "developer_lastname")
    private String lastName;
    @JoinTable(
            name = "developers_skills",
            joinColumns = @JoinColumn(
                    name = "developer_id",
                    referencedColumnName = "developer_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "skill_id",
                    referencedColumnName = "skill_id"
            )
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Skill> skills;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable()
    private List<Team> teams;

    public Developer(String firstName, String lastName, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\''
//                + skills + '}'
                ;
    }
}

