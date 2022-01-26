package com.andrey.crudapp.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;
    @Column(name = "team_name")
    private String name;
    @JoinTable(
            name = "teams_developers",
            joinColumns = @JoinColumn(
                    name = "team_id",
                    referencedColumnName = "team_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "developer_id",
                    referencedColumnName = "developer_id"
            )
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Developer> developers;
//    private TeamStatus status;

    public Team(String name, List<Developer> developers) {
        this.name = name;
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' + developers +
                '}';
    }
}
