package OneToOneRelation;

import OneToOneRelation.entity.Detail;
import OneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test4BiDirAdd {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            Employee emp = new Employee("Alex", "Mirov", "IT", 1000);
            Detail detail = new Detail("Boston", "+1999-555-33-33", "alex@gmail.com");
            detail.setEmployee(emp);
            emp.setEmpDetail(detail);// если не добавлять обратную связь явно, то в столбце details_id (FK) будет пусто
            session.beginTransaction();
            session.save(emp);
            System.out.println("______________Done!______________");

        } finally {
            session.getTransaction().commit(); //выносим за пределы блока try, чтобы в случае исключения сессия была закрыта, иначе при выбросе исключения до commit, то ыуыыия останется открытой
            factory.close();
        }


    }


}
