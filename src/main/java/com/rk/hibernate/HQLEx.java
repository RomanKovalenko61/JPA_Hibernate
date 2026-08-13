package com.rk.hibernate;

import com.rk.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HQLEx {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            Transaction transaction = session.getTransaction();

            try {
                transaction.begin();

                // ALL Students
                // select * from students;
                // HQL работаем с классом и его полями, а не таблицами БД

//                Query<Student> query = session.createQuery("from Student ", Student.class);
//                List<Student> students = query.getResultList();
//                students.forEach(System.out::println);

                // ***************************************************************************************************

                // ALL Students where name with 'l' or 'L' and grade > 8
                // Также поддерживаются named and position params
                // HQL умеет все что JPQL и расширяет его

//                List<Student> students = session.createQuery("from Student s " +
//                        "where lower(s.name) LIKE '%l%' AND s.avgGrade > 8", Student.class).getResultList();
//                students.forEach(System.out::println);

                // ***************************************************************************************************

                // Update Student where length name = 5 set avg grade 10.0

//                session.createQuery("UPDATE Student s " +
//                        "SET s.avgGrade = 10.0 WHERE length(s.name) = 5").executeUpdate();

                // ***************************************************************************************************

                // Delete Student where avg grade < 9.0
                // session.createQuery('...') помечен устаревшим использовать createMutationQuery и createSelectionQuery
                Query<Student> query = session.createQuery("DELETE Student s WHERE s.avgGrade < :grade", Student.class);
                query.setParameter("grade", 9.0);
                query.executeUpdate();

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
