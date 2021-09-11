package OneToManyBIDirectional;

import OneToManyBIDirectional.entity.Department;
import OneToManyBIDirectional.entity.Employee2;
import OneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.CascadeType;

public class Test2DeleteIncorrect {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee2.class).addAnnotatedClass( Department.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Employee2 employee = session.get(Employee2.class, 1);
            session.delete(employee); //удаление одного сотрудника повлечет за собой удаление департамента по FK, а с ним и всех остальных сотрудников.
            // Обязательно заменить в обоих классах CascadeType.All на другие, иначе все связанные таблицы и данные удалятся.

            session.getTransaction().commit();
            System.out.println("________Done!________");
        } finally {
            session.close();
            factory.close();
        }
    }
}
