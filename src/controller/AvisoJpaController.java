/*
 * To change this template, choose Tools | Templates
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
import model.Aviso;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class AvisoJpaController implements Serializable {

    public AvisoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AvisoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aviso aviso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto idProjeto = aviso.getIdProjeto();
            if (idProjeto != null) {
                idProjeto = em.getReference(idProjeto.getClass(), idProjeto.getIdProjeto());
                aviso.setIdProjeto(idProjeto);
            }
            em.persist(aviso);
            if (idProjeto != null) {
                idProjeto.getAvisoList().add(aviso);
                idProjeto = em.merge(idProjeto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aviso aviso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aviso persistentAviso = em.find(Aviso.class, aviso.getIdAviso());
            Projeto idProjetoOld = persistentAviso.getIdProjeto();
            Projeto idProjetoNew = aviso.getIdProjeto();
            if (idProjetoNew != null) {
                idProjetoNew = em.getReference(idProjetoNew.getClass(), idProjetoNew.getIdProjeto());
                aviso.setIdProjeto(idProjetoNew);
            }
            aviso = em.merge(aviso);
            if (idProjetoOld != null && !idProjetoOld.equals(idProjetoNew)) {
                idProjetoOld.getAvisoList().remove(aviso);
                idProjetoOld = em.merge(idProjetoOld);
            }
            if (idProjetoNew != null && !idProjetoNew.equals(idProjetoOld)) {
                idProjetoNew.getAvisoList().add(aviso);
                idProjetoNew = em.merge(idProjetoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aviso.getIdAviso();
                if (findAviso(id) == null) {
                    throw new NonexistentEntityException("The aviso with id " + id + " no longer exists.");
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
            Aviso aviso;
            try {
                aviso = em.getReference(Aviso.class, id);
                aviso.getIdAviso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aviso with id " + id + " no longer exists.", enfe);
            }
            Projeto idProjeto = aviso.getIdProjeto();
            if (idProjeto != null) {
                idProjeto.getAvisoList().remove(aviso);
                idProjeto = em.merge(idProjeto);
            }
            em.remove(aviso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aviso> findAvisoEntities() {
        return findAvisoEntities(true, -1, -1);
    }

    public List<Aviso> findAvisoEntities(int maxResults, int firstResult) {
        return findAvisoEntities(false, maxResults, firstResult);
    }

    private List<Aviso> findAvisoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aviso.class));
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

    public Aviso findAviso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aviso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvisoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aviso> rt = cq.from(Aviso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
