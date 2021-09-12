package hibernateOneToOneRelation;

import hibernateOneToOneRelation.entity.Detail;
import hibernateOneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2UniDirExceptions {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();
        Session session = null;
        try {
            //Session session= factory.getCurrentSession(); //session выносим за пределы блока try:  Session session = null; необходимо инициализировать, иначе не даст закрыть после блока try
            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, 20);
            //т.к. id 20 нет в базе, то выдается ERROR: Connection leak detected: there are 1 unclosed connections upon shutting down pool
            //После выброса исключения программа дальше не отрабатывает и сессия остается открытой, т.к не доходит до commit.
            //Чтобы это исправить необходимо вынести за пределы блока try,
            System.out.println(emp.getEmpDetail());
//            session.getTransaction().commit(); //выносим за пределы блока try: BEFORE TRY: Session session = null; AFTER TRYP: session.getTransaction().commit();
            System.out.println("______________Done!______________");
        } finally {
            session.getTransaction().commit(); //теперь исключение также выбрасывается, но проиисходит "Cleaning up connection pool", т.е. сессия очищена и код отрабатывает дальше
            factory.close();
        }


    }
}
