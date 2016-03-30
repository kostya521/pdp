package com.epam.pdp.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {
    private static final String PERSISTENCE_UNIT_NAME = "tutorialPU";

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        transaction.commit();

        em.close();
        emf.close();
    }
}
