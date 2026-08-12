package com.rk.criteria_query;

import com.rk.criteria_query.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CriteriaQueryEx3 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            try {

                // JPQL: select s from Student s where avgGrade >= 7.5;

                // 1 Creation of Criteria Builder
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // 2 Creation of Criteria Query (с какой сущностью работаем  ожидаемый результат запроса)
                CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

                // 3 Root creation (Таблица ?  доступ к полям)
                Root<Student> root = criteriaQuery.from(Student.class); // from Student s

                // 3.1 Condition creation
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("avgGrade"), 7.5);

                // 3.2 Adding condition to Criteria Query
                criteriaQuery.where(predicate);

                // 4 Adding Root to Criteria Query
                criteriaQuery.select(root); // select s from Student s where s.avgGrade >= 7.5

                // 5 Query creation
                TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);

                List<Student> students = query.getResultList();
                System.out.println(students);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
