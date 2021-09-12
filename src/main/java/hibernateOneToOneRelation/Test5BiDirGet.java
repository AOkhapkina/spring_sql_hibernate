package hibernateOneToOneRelation;

import hibernateOneToOneRelation.entity.Detail;
import hibernateOneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test5BiDirGet {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp=session.get(Employee.class,1);
            System.out.println(emp.getEmpDetail());
            Detail detail = session.get(Detail.class, 2);
            System.out.println(detail.getEmployee());
                        System.out.println("______________Done!______________");

        } finally {
             //выносим за пределы блока try, чтобы в случае исключения сессия была закрыта, иначе при выбросе исключения до commit, то ыуыыия останется открытой
            session.getTransaction().commit();
            session.close();
            factory.close();
        }
    }
}
