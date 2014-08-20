/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Relacaoentreriscos;

/**
 *
 * @author ITAU
 */
public class RelacaoentreriscosJpaController implements Serializable {

    public RelacaoentreriscosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RelacaoentreriscosJpaController() {
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relacaoentreriscos relacaoentreriscos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(relacaoentreriscos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relacaoentreriscos relacaoentreriscos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            relacaoentreriscos = em.merge(relacaoentreriscos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = relacaoentreriscos.getIdRelacaoEntreRiscoscol();
                if (findRelacaoentreriscos(id) == null) {
                    throw new NonexistentEntityException("The relacaoentreriscos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Relacaoentreriscos relacaoentreriscos;
            try {
                relacaoentreriscos = em.getReference(Relacaoentreriscos.class, id);
                relacaoentreriscos.getIdRelacaoEntreRiscoscol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relacaoentreriscos with id " + id + " no longer exists.", enfe);
            }
            em.remove(relacaoentreriscos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relacaoentreriscos> findRelacaoentreriscosEntities() {
        return findRelacaoentreriscosEntities(true, -1, -1);
    }

    public List<Relacaoentreriscos> findRelacaoentreriscosEntities(int maxResults, int firstResult) {
        return findRelacaoentreriscosEntities(false, maxResults, firstResult);
    }

    private List<Relacaoentreriscos> findRelacaoentreriscosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relacaoentreriscos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Relacaoentreriscos findRelacaoentreriscos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relacaoentreriscos.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelacaoentreriscosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relacaoentreriscos> rt = cq.from(Relacaoentreriscos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
