package spring.hibernate;
/*
  */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.hibernate.entity.Employee;

import java.util.List;

public class Test3Get_HQL {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction(); //обязательно открыть транзакцию (BEGIN||START TRANSACTION - mySQL)
            List<Employee> emps = session.createQuery("from Employee " + " where firstname = 'Aleksandr' AND surname = 'Lenin'").getResultList();// createQuery("from Employee")!!!название из Java кода, не из MySQL
            for (Employee e: emps){
                System.out.println(e);
            }
            session.getTransaction().commit();
            System.out.println("Done");

        } finally {
            factory.close();
        }

    }
}
