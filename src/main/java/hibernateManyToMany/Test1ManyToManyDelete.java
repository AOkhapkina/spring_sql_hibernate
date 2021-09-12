package hibernateManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1ManyToManyDelete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Child.class).addAnnotatedClass(Section.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
//            Child child = session.get(Child.class,8);
            Section section = session.get(Section.class, 11);
//            session.delete(child);
            session.delete(section);
            session.getTransaction().commit();
            System.out.println("_______DONE!_______");
        } finally {

            session.close();
            factory.close();
        }
        //***********************************************
//        try {
//            session = factory.getCurrentSession();
//            Section section1 = new Section("Football");
//            Child child1 = new Child("Vasya", 5);
//            Child child2 = new Child("Vlad", 6);
//            Child child3 = new Child("Ilya", 5);  ``````````
//            section1.addChildToSection(child1);
//            section1.addChildToSection(child2);
//            section1.addChildToSection(child3);
//
//            session.beginTransaction();
//            session.save(section1);
//            session.getTransaction().commit();
//            System.out.println(section1);
//            System.out.println("_______DONE!_______");
//        } finally {
//
//            session.close();
//            factory.close();
//        }
    }
}