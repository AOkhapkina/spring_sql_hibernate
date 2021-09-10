package spring.hibernate;
/*
SessionFactory factory = new Configuration() .... //SessionFactory–фабрика по производству сессий, читает файл spring.hibernate.cfg.xml через new Configuration из org.spring.hibernate.cfg.
                             .configure("spring.hibernate.cfg.xml")...//(в данном случае не обязательно писать его в скобках, но желательно, для читаемости кода)
                             .addAnnotatedClass(Employee.class) ... //в скобках указываем класс, где указаны все аннотации
                             .buildSessionFactory(); ... // вызываем метод для построения SessionFactory
Session session = factory.getCurrentSession();...//с помощью SessionFactory. Session –это обёртка вокруг подключения к базе с помощью JDBC и основа для работы с Java Объектами в SQL (жизн.цикл короткий,заканчивается после его работы)
  */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spring.hibernate.entity.Employee;

public class Test1Add {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("Aleksandr", "Lenin", "IT", 800);
            session.beginTransaction(); //обязательно открыть транзакцию (BEGIN||START TRANSACTION - mySQL)
            session.save(emp);//метод сохраняет данные нового объекта в БД (SELECT, INSERT, UPDATE, DELETE - mySQL)
            session.getTransaction().commit(); //и после выполнения метода закрыть транзакцию (ROLLBACK -oтказ от транзакции/ COMMIT-Подтверждение транзакции- mySQL)

            System.out.println(emp);

        } finally {
            factory.close(); //SessionFactory часто выбрасывает exception, поэтому берем в try блок и затем в finally закрываем сессию, если будет exception, сессия закроется.            }
            // самостоятельно можно добавить log4j
        }

    }
}
