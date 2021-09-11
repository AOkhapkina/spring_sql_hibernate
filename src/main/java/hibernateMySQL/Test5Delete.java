package hibernateMySQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernateMySQL.entity.Employee;

public class Test5Delete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
     try{

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Employee emp = session.get(Employee.class, 8);
        session.delete(emp);
//        session.createQuery("delete Employee where id = 7").executeUpdate();
        session.getTransaction().commit();
    }
     finally{
         factory.close();
     }
}

}
