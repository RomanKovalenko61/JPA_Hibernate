package com.rk.hibernate;

import com.rk.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateEx1 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            Transaction transaction = session.getTransaction();

            try {
//                Student student = new Student("Chanel", "King", 9.1);
                Student student = new Student("Leo", "Farrell", 8.4);

                transaction.begin();

//                System.out.println(session.contains(student));
                session.persist(student);
//                System.out.println(session.contains(student));
                System.out.println(student);

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
