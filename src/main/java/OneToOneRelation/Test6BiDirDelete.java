package OneToOneRelation;


import OneToOneRelation.entity.Detail;
import OneToOneRelation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test6BiDirDelete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class).buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Detail detail = session.get(Detail.class, 5);
            //мы заменили CascadeType.All на {CascadeType.PERSIST, CascadeType.REFRESH} в Detail.class. Теперь мы не можем удалять данные из обех таблиц.
            //т.к. Employee.class ссылается на details_id, то для удаления одной из таблиц необходимо разорвать связь м/у ними, присваиваем details_id=null
            detail.getEmployee().setEmpDetail(null); //теперь нет ссылки на id таблицы detailsб значит у работника нет деатльной информации
            session.delete(detail); //теперь удаляются детальные данные о сотруднике, но сам сотрудник остается в базе, но уже без деталей

        } finally {
            session.getTransaction().commit();
            System.out.println("______________Done!______________");
            session.close();
            factory.close();
        }
    }
}
