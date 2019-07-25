/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thomework.daos;

import com.mycompany.thomework.entities.Trainer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author George
 */
public class TrainerDao {

    public List<Trainer> getAllTrainers() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("com.mycompany_Thomework_war_1.0PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Trainer.findAll", Trainer.class);
        @SuppressWarnings("unchecked")
        List<Trainer> resultList = (List<Trainer>) query.getResultList();
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
        return resultList;
    }

    public boolean addTrainer(Trainer t) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("com.mycompany_Thomework_war_1.0PU");
        EntityManager em = emf.createEntityManager();
        boolean res = false;
        em.getTransaction().begin();
        em.persist(t);
        try {
            em.getTransaction().commit();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
        return res;
    }

    public Trainer getTrainerById(int id) {

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("com.mycompany_Thomework_war_1.0PU");
        EntityManager em = emf.createEntityManager();
        return em.find(Trainer.class, id);

    }

    public void deleteTrainer(int id) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("com.mycompany_Thomework_war_1.0PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.merge(em.find(Trainer.class, id)));
        et.commit();
        em.close();

    }

    public void updateTrainer(Trainer t) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("com.mycompany_Thomework_war_1.0PU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(t);
        et.commit();
        em.close();

    }
}
