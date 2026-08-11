package com.rk.persistence_context.jpa_methods;

import com.rk.persistence_context.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MergeEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

                Teacher teacher = new Teacher("Vera", "Walton", "Geography", true);
                entityManager.persist(teacher);

                transaction.commit();
                entityManager.close();

                // -------------------------------------------------------------

                teacher.setSubject("Math");
                EntityManager entityManager1 = factory.createEntityManager();
                EntityTransaction transaction1 = entityManager1.getTransaction();

                transaction1.begin();

                // .EntityExistsException: detached entity passed to persist
                // нельзя перевести статус из detach в persist
//                entityManager1.persist(teacher);

                // 1 случай id совпадают значит скопируем поля объекта в тот который находится в контексте
                // переменная merge будет ссылаться на обновленный объект в контексте
                // 2 случай в контесте нет объекта с таким id берем его из БД и кладем в контекст, копируем инфу в него из detach объекта
                // переменная merge будет ссылаться на объект в контексте
                // 3 случай созданный объект без id (Transient) кладем его в контест и вставляем в БД (теперь он Persist)
                // переменная merge будет ссылаться на объект в контексте
                Teacher merged = entityManager1.merge(teacher);

                transaction1.commit();
                entityManager1.close();

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
