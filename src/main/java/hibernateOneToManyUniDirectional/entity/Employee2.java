package hibernateOneToManyUniDirectional.entity;
//Source table - Employee2 - таблица-источник данных, с которой будет сравниваться целевая таблица,
//в данном примере здесь указан FK @JoinColumn(name = "department_id"), но это не обязательное условие, FK может быть и в таргет-таблице
//Target table - Department - целевая таблица, именно в нее данные будут добавлены, изменены или удалены в результате выполнения оператора.

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees2")
public class Employee2 {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name") //!!! названия столбцов отличаются, в mySQL - name, в java - firstname
    private String firstname;

    @Column(name="surname")
    private String surname;

    @Column(name= "salary")
    private int salary;

        public Employee2(String name, String surname, int salary) {
        this.firstname = name;
        this.surname = surname;
        this.salary = salary;
    }

    @Override
    public String toString() { //переопределить без поля private Detail empDetail, иначе информация выдается вместе с деталями. Если вобоих классах оставить ссылку на поля, то StackOverflowError
        return "Employee2{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                '}';
    }
}

