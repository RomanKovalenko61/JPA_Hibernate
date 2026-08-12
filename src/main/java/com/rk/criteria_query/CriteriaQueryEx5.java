package com.rk.criteria_query;

import com.rk.criteria_query.entity.Student;
import com.rk.criteria_query.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CriteriaQueryEx5 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            try {

                // JPQL: select u, s from University u JOIN u.students s;

                // 1 Creation of Criteria Builder
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // 2 Creation of Criteria Query (с какой сущностью работаем  ожидаемый результат запроса)
                CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

                // 3 Root creation (Таблица ?  доступ к полям)
                Root<University> root = criteriaQuery.from(University.class); // from University u

                // 3.1 JOIN
                Join<University, Student> join = root.join("students");

                // 4 Adding Root to Criteria Query
                // select u, s from University u JOIN u.students s;
                criteriaQuery.select(
                        criteriaBuilder.array(
                                root,
                                join)
                );

                // 5 Query creation
                TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);

                List<Object[]> studentInfo = query.getResultList();
                for (Object[] info : studentInfo) {
                    System.out.println(info[0] + " ---> " + info[1]);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
