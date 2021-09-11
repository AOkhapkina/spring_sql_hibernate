package OneToOneRelation.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "details")
public class Detail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name= "city")
    private String city;

    @Column(name= "phone_number")// !!! different names
    private String phoneNumber;

    @Column (name = "email")
    private String email;

    @OneToOne(mappedBy = "empDetail", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})//{больше одного типа перечислять в фигурных скобках}
    //Bi-directional отношениях c @OneToOne и mappedBy мы показываем Hibernate, что связь уже налажена, поищи ее в поле "empDetail" в Employee.class
    //
    private Employee employee;

    public Detail(String city, String phoneNumber, String email) {
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

//Если в обоих классах при переопределении оставить ссылку на поля, то StackOverflowError
    @Override
    public String toString() { //переопределить без поля private Employee employee, иначе информация выдается вместе с деталями. Если в обоих классах оставить ссылку на поля, то StackOverFlow
        return "Detail{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
