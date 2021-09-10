package spring.hibernate.entity;
/*
JPA (Java Persistence API) –это стандартная спецификация, которая описывает систему для управления сохранением Java объектов в таблицы базы данных
Hibernate –самая популярная реализация спецификации JPA.
Т.е. JPA описывает правила, а Hibernate реализует их (+ имеет свой собственный функционал).
И т.к. Hibernate использует для реализации спецификации JPA, то разработчики Hib сами рекомендуют использовать аннотации javax.persistence, а не org.spring.hibernate.annotations
Кроме того, если завтра мы решим заменить Hibernate другую реализацию, нам не придется менять весь код и менять аннотации
 */
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="employees")
public class Employee {

    @Id // @Id marks the column as a primary key
    @Column(name = "id") //по умолчанию названия колонок совпадают с названием в БД, поэтому лучше указывать явно в скобках, даже если имена совпадают, т.к. при изменении названий мы увидим где несоответствие
    @GeneratedValue(strategy = GenerationType.IDENTITY) // если не указать явно эту строку, то будет GenerationType.AUTO–дефолтный тип, на который лучше не полагаться, т.к. он на разных версиях Hibernate разный
    private int id;

    @Column(name = "name") //!!! названия столбцов отличаются, в mySQL - name, в java - firstname
    private String firstname;

    @Column(name="surname")
    private String surname;

    @Column(name = "department" )
    private String department;

    @Column(name= "salary")
    private int salary;

    public Employee(){

    }

    public Employee(String name, String surname, String department, int salary) {
        this.firstname = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }
}

