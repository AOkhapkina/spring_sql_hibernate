package hibernateOneToManyBIDirectional;

import hibernateOneToManyBIDirectional.entity.Department;
import hibernateOneToManyBIDirectional.entity.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2Delete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee2.class).addAnnotatedClass( Department.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Department dep = session.get(Department.class, 1);
            session.delete(dep);
            session.getTransaction().commit();
            System.out.println("________Done!________");
        } finally {
            session.close();
            factory.close();
        }
    }
}
