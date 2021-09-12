package hibernateManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String sectionName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})//не CascadeType.All
    @JoinTable(name = "child_section", joinColumns = @JoinColumn(name = "section_id"),inverseJoinColumns = @JoinColumn(name = "child_id"))
    private List<Child> children;

    public Section(String sectionName) {
        this.sectionName = sectionName;
    }

    public void addChildToSection(Child child){
        if (children == null){
            children = new ArrayList<>();
        }
        children.add(child);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", sectionName='" + sectionName + '\'' +
                '}';
    }
}
