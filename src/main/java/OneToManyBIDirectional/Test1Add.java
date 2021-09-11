package OneToManyBIDirectional;

import OneToManyBIDirectional.entity.Department;
import OneToManyBIDirectional.entity.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1Add {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee2.class).addAnnotatedClass( Department.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            Department dep = new Department("IT", 300, 1300);
            Employee2 emp1 = new Employee2("Ivan", "Ivanov", 1000);
            Employee2 emp2 = new Employee2("Elena", "Orlova", 1100);
            dep.addEmployeeToDepartment(emp1);
            dep.addEmployeeToDepartment(emp2);

            session.beginTransaction();
            session.save(dep);
            session.getTransaction().commit();
            System.out.println("________Done!________");
        } finally {
            session.close();
            factory.close();
        }
    }
}
