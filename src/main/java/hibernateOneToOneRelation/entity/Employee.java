package hibernateOneToOneRelation.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name") //!!! названия столбцов отличаются, в mySQL - name, в java - firstname
    private String firstname;

    @Column(name="surname")
    private String surname;

    @Column(name = "department" )
    private String department;

    @Column(name= "salary")
    private int salary;

    @OneToOne(cascade = CascadeType.ALL) // каскадное выполнение всех операций во всех связанных таблицах (только для Entity, на котором операция вызывается, но и на связанных с ним Entity), !!! подходит не для всех таблиц. При удалении одного работника удаляются все данные на него из таблицы "детали"
    @JoinColumn(name = "details_id") // - связующая колонка, указывает на столбец с foreign key (FK), осуществляющий связь с другим объектом
    private Detail empDetail;

    public Employee(){
    }

    public Employee(String name, String surname, String department, int salary) {
        this.firstname = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;

    }
    //Если в обоих классах при переопределении оставить ссылку на поля, то StackOverflowError
    @Override
    public String toString() { //переопределить без поля private Detail empDetail, иначе информация выдается вместе с деталями. Если вобоих классах оставить ссылку на поля, то StackOverflowError
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

