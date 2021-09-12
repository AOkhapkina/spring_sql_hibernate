package hibernateOneToManyUniDirectional.entity;
/* В данном примере ссылка на сотрудника только в departments. В employees2 обратной ссылки на departments.
 Source table - departments - таблица-источник данных, с которой будет сравниваться целевая таблица.
 В BIDirectional departments был Target table, а здесь- source table, поэтому удаляем mappedBy = "department" добавляем  @JoinColumn(name = "department_id")
 При использовании связи One-to-Many в аннотации @JoinColumn name будет ссылаться на Foreign Key не из source, а из target таблицы.
 Не важно на столбец какой таблицы ссылаться, главное чтобы столбец был Foreign Key
 Когда связь OneToMany, Foreign Key всегда будет находиться в таблице, в которой отражены Many
 */
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "min_salary")
    private int minSalary;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//удаляем mappedBy = "department" добавляем  @JoinColumn(name = "department_id"), инф о fetch в файле fetchtypesEagerLazy
    @JoinColumn(name = "department_id")//добавляем  @JoinColumn(name = "department_id") FK не из source, а из target таблицы employee2.
    private List<Employee2> emps;

    public Department(String departmentName, int maxSalary, int minSalary) {
        this.departmentName = departmentName;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }

    public void addEmployeeToDepartment(Employee2 employee2) {
        if (emps == null) {
            emps = new ArrayList<>();
        }
        emps.add(employee2);
//        employee2.setDepartment(this);//в Uni-directional связях нет двусторонней связиб поэтому мы не можем ее прописать
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", maxSalary=" + maxSalary +
                ", minSalary=" + minSalary +
                '}';
    }
}
