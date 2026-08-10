package com.rk.relationship.one_to_many;

import com.rk.relationship.one_to_many.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LoadingTypesEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {



            try {

                University university = entityManager.find(University.class, 1);
                System.out.println("University INFO");
                System.out.println(university);

                // EAGER - все подгружено LAZY - нет необходимых данных после закрытия LazyInitializationException
                // подгрузка данные т.к. обратились к связанной сущности
                university.getStudents().size();
                entityManager.close();

                System.out.println("Student INFO");
                System.out.println(university.getStudents());


            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}
