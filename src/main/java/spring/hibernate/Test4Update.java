package spring.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.hibernate.entity.Employee;

public class Test4Update {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        try {

            Session session = factory.getCurrentSession();
            session.beginTransaction();
//            Employee emp = session.get(Employee.class, 7); // for one
//            emp.setSalary(1500);
//            System.out.println(emp);
            session.createQuery("update Employee set salary = 1800 where firstname = 'Aleksandr' ").executeUpdate(); //for more than one/ without "where" - salary changes for all employees
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

}