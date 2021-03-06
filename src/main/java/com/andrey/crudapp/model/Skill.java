package com.andrey.crudapp.model;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skills")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id")
    private Long id;
    @Column(name = "skill_name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable()
    private List<Developer> developers;

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '}';
    }
}
