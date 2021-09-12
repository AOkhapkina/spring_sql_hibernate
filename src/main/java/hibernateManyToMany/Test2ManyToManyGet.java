package hibernateManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2ManyToManyGet {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Child.class).addAnnotatedClass(Section.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Child child = session.get(Child.class, 6);
            System.out.println(child);
            System.out.println(child.getSections());
            session.getTransaction().commit();
            System.out.println("_______DONE!_______");

//**********************************************************
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Section section = session.get(Section.class, 2);
//            System.out.println(section);
//            System.out.println(section.getChildren());
//            session.getTransaction().commit();
//            System.out.println("_______DONE!_______");
        } finally {

            session.close();
            factory.close();
        }
        //***********************************************

    }
}