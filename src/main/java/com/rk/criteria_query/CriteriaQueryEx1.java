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

public class CriteriaQueryEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            try {

                // JPQL: select s from Student s;
                // Type Safe критерия дает проверку на уровне компиляции + динам. формирование запросов

                // 1 Creation of Criteria Builder
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // 2 Creation of Criteria Query (с какой сущностью работаем  ожидаемый результат запроса)
                CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

                // 3 Root creation (Таблица ?  доступ к полям)
                Root<Student> root = criteriaQuery.from(Student.class); // from Student s

                // 4 Adding Root to Criteria Query
                criteriaQuery.select(root); // select s from Student s

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
