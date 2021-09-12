package hibernateOneToOneRelation;

import hibernateOneToOneRelation.entity.Detail;
import hibernateOneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test1UniDirAdd {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            Employee emp = new Employee("Elena", "Orlova", "HR", 1100);
            Detail detail = new Detail("Boston", "+1999-555-44-44", "elenaorlova@gmail.com");
            emp.setEmpDetail(detail);
            session.beginTransaction();
            session.save(emp);
            System.out.println("______________Done!______________");
            session.getTransaction().commit();
        } finally {
            //выносим за пределы блока try, чтобы в случае исключения сессия была закрыта, иначе при выбросе исключения до commit, то ыуыыия останется открытой
            session.close();
            factory.close();
        }


    }


}
