package OneToManyBIDirectional.entity;
//Source table - Employee - таблица-источник данных, с которой будет сравниваться целевая таблица,
//в данном примере здесь указан FK @JoinColumn(name = "department_id"), но это не обязательное условие, FK может быть и в таргет-таблице
//Target table - Department - целевая таблица, именно в нее данные будут добавлены, изменены или удалены в результате выполнения оператора.

import OneToOneRelation.entity.Detail;
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

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}) // каскадное выполнение всех операций во всех связанных таблицах (только для Entity, на котором операция вызывается, но и на связанных с ним Entity), !!! подходит не для всех таблиц. При удалении одного работника удаляются все данные на него из таблицы "детали"
    @JoinColumn(name = "department_id") // - @JoinColumn - всегда указывает на столбец с foreign key (FK), осуществляющий связь с другим объектом
    private Department department;

    public Employee2(String name, String surname, int salary) {
        this.firstname = name;
        this.surname = surname;
        this.salary = salary;
    }
    //Если в обоих классах при переопределении оставить ссылку на поля, то StackOverflowError
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

