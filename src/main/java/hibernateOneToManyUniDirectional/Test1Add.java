package hibernateOneToManyUniDirectional;

import hibernateOneToManyUniDirectional.entity.Department;
import hibernateOneToManyUniDirectional.entity.Employee2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1Add {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee2.class).addAnnotatedClass( Department.class).buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            Department dep = new Department("Sales", 300, 1300);
            Employee2 emp1 = new Employee2("Mikhail", "Filin", 1300);
            Employee2 emp2 = new Employee2("Mark", "Shvedov", 1200);
            dep.addEmployeeToDepartment(emp1);
            dep.addEmployeeToDepartment(emp2);
            System.out.println(dep);
            System.out.println(dep.getEmps());

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
