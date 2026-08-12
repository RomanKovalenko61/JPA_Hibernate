package com.rk.criteria_query;

import com.rk.criteria_query.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CriteriaQueryEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            try {

                // JPQL: select s.name from Student s;

                // 1 Creation of Criteria Builder
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // 2 Creation of Criteria Query (с какой сущностью работаем  ожидаемый результат запроса)
                CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

                // 3 Root creation (Таблица ?  доступ к полям)
                Root<Student> root = criteriaQuery.from(Student.class); // from Student s

                // 4 Adding Root to Criteria Query
                criteriaQuery.select(root.get("name")); // select s.name from Student s

                // 5 Query creation
                TypedQuery<String> query = entityManager.createQuery(criteriaQuery);

                List<String> students = query.getResultList();
                System.out.println(students);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
