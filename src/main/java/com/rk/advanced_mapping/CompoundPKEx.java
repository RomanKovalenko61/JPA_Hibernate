package com.rk.advanced_mapping;

import com.rk.advanced_mapping.entity.Book;
import com.rk.advanced_mapping.entity.id.BookId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CompoundPKEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
             EntityManager entityManager = factory.createEntityManager()) {

            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();

//                Book book1 = new Book("Dostoevsky", "Crime and Punishment", 1866, 4.8);
//                Book book2 = new Book("Dostoevsky", "The Brothers Karamazov", 1880, 4.6);
//                Book book3 = new Book("Tolstoy", "War and Peace", 1867, 4.7);
//
//                entityManager.persist(book1);
//                entityManager.persist(book2);
//                entityManager.persist(book3);

                Book book = entityManager.find(Book.class, new BookId("Dostoevsky", "The Brothers Karamazov"));
                System.out.println(book);

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
