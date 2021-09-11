package hibernateMySQL;
/*
SessionFactory factory = new Configuration() .... //SessionFactory–фабрика по производству сессий, читает файл spring.hibernate.cfg.xml через new Configuration из org.spring.hibernate.cfg.
                             .configure("spring.hibernate.cfg.xml")...//(в данном случае не обязательно писать его в скобках, но желательно, для читаемости кода)
                             .addAnnotatedClass(Employee.class) ... //в скобках указываем класс, где указаны все аннотации
                             .buildSessionFactory(); ... // вызываем метод для построения SessionFactory
Session session = factory.getCurrentSession();...//с помощью SessionFactory. Session –это обёртка вокруг подключения к базе с помощью JDBC и основа для работы с Java Объектами в SQL (жизн.цикл короткий,заканчивается после его работы)
  */

import hibernateMySQL.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2Add_Get {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("Aleksandr", "Lenin", "IT", 700);
            session.beginTransaction();
            session.save(emp);
            //        session.getTransaction().commit();
            int id = emp.getId(); // достаем id добавленного работника
//          session = factory.getCurrentSession();
//          session.beginTransaction();
            Employee myEmp = session.get(Employee.class, id); //get(Class<T> aClass, java.io.Serializable serializable);
            session.getTransaction().commit(); //и после выполнения метода закрыть транзакцию (ROLLBACK -oтказ от транзакции/ COMMIT-Подтверждение транзакции- mySQL)
            System.out.println(myEmp);

        } finally {
            factory.close();
        }
    }
}
