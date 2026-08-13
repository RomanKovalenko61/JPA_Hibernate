package com.rk.hibernate;

import com.rk.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateEx2 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            Transaction transaction = session.getTransaction();

            try {
                transaction.begin();

                // Exception!!! .HibernateException: Calling method 'find' is not valid without an active transaction
                // First level cache --> один запрос в БД, потом студент в кеше
                Student student1 = session.find(Student.class, 2);
                Student student2 = session.find(Student.class, 2);

                // Proxy объект, знает только id, если нужны другие поля тогда только запрос в БД
                // Используется для установки связей (?) и каскадных операций
                // В дебаге виден select т.к. Idea показывает состояние объекта
                // После коммита можем получить LazyInitializationException если не загрузили поля
                Student studentProxy = session.getReference(Student.class, 1);
                System.out.println(studentProxy.getId()); // Id знаем, пока не идем в БД
                System.out.println(studentProxy); // Здесь только реальный запрос в БД, запрос других полей

                Student studentNotExists = session.find(Student.class, 100);
                System.out.println(studentNotExists);

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
