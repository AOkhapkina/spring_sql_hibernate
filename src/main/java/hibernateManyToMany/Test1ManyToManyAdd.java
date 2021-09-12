package hibernateManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1ManyToManyAdd {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Child.class).addAnnotatedClass(Section.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            Section section1 = new Section("Volleyball");
            Section section2 = new Section("Piano");
            Section section3 = new Section("Dance");

            Child child1 = new Child("Masha", 7);
//            Child child2 = new Child("Misha", 8);

            child1.addSectionToChild(section2);
            child1.addSectionToChild(section3);
//            child2.addSectionToChild(section1);
//            child2.addSectionToChild(section2);

            session.beginTransaction();
            session.persist(child1);
//            session.persist(child2);
            session.getTransaction().commit();
            System.out.println(section1);
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
//            Child child3 = new Child("Ilya", 5);
//            section1.addChildToSection(child1);
//            section1.addChildToSection(child2);
//            section1.addChildToSection(child3);
//
//            session.beginTransaction();
//            session.save(section1); //т.к. мы изменили
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