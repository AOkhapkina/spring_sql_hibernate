package OneToOneRelation;


import OneToOneRelation.entity.Detail;
import OneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test3UniDirDelete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, 11);
            session.delete(emp);

        } finally {
            session.getTransaction().commit();
            System.out.println("______________Done!______________");

            factory.close();


        }
    }


}
