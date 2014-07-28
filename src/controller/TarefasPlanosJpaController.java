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
import model.Tarefasplanos;

/**
 *
 * @author Diogo
 */
public class TarefasPlanosJpaController implements Serializable {

    public TarefasPlanosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public TarefasPlanosJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarefasplanos tarefasplanos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tarefasplanos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarefasplanos tarefasplanos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tarefasplanos = em.merge(tarefasplanos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarefasplanos.getIdTarefaPlanos();
                if (findTarefasplanos(id) == null) {
                    throw new NonexistentEntityException("The tarefasplanos with id " + id + " no longer exists.");
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
            Tarefasplanos tarefasplanos;
            try {
                tarefasplanos = em.getReference(Tarefasplanos.class, id);
                tarefasplanos.getIdTarefaPlanos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarefasplanos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tarefasplanos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarefasplanos> findTarefasplanosEntities() {
        return findTarefasplanosEntities(true, -1, -1);
    }

    public List<Tarefasplanos> findTarefasplanosEntities(int maxResults, int firstResult) {
        return findTarefasplanosEntities(false, maxResults, firstResult);
    }

    private List<Tarefasplanos> findTarefasplanosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarefasplanos.class));
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

    public Tarefasplanos findTarefasplanos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarefasplanos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarefasplanosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarefasplanos> rt = cq.from(Tarefasplanos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
