package hibernateOneToManyUniDirectional;

import hibernateOneToManyUniDirectional.entity.Department;
import hibernateOneToManyUniDirectional.entity.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2Delete {
    public static void main(String[] args) {

    SessionFactory factory = new Configuration().configure().addAnnotatedClass(Department.class).addAnnotatedClass(Employee2.class).buildSessionFactory();
    Session session = null;

        try {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Department dep = session.get(Department.class, 3);
        session.delete(dep);
        session.getTransaction().commit();
        System.out.println("________DONE!_______");
    } finally {
        session.close();
        factory.close();
    }
}
}
