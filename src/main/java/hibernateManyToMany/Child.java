package hibernateManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "children")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "name" )
    private String firstName;

    @Column (name = "age")
    private int age;

    public Child(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})//не CascadeType.All
    @JoinTable(name = "child_section", joinColumns = @JoinColumn(name = "child_id"),inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<Section> sections;

    public void addSectionToChild(Section section){
        if (sections == null){
            sections = new ArrayList<>();
        }
        sections.add(section);
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
