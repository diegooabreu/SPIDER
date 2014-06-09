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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Planocontingencia;
import model.Risco;

/**
 *
 * @author MarcosSenna
 */
public class PlanocontingenciaJpaController implements Serializable {

    public PlanocontingenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PlanocontingenciaJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planocontingencia planocontingencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(planocontingencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planocontingencia planocontingencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            planocontingencia = em.merge(planocontingencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planocontingencia.getIdPlanoContingencia();
                if (findPlanocontingencia(id) == null) {
                    throw new NonexistentEntityException("The planocontingencia with id " + id + " no longer exists.");
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
            Planocontingencia planocontingencia;
            try {
                planocontingencia = em.getReference(Planocontingencia.class, id);
                planocontingencia.getIdPlanoContingencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planocontingencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(planocontingencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planocontingencia> findPlanocontingenciaEntities() {
        return findPlanocontingenciaEntities(true, -1, -1);
    }

    public List<Planocontingencia> findPlanocontingenciaEntities(int maxResults, int firstResult) {
        return findPlanocontingenciaEntities(false, maxResults, firstResult);
    }

    private List<Planocontingencia> findPlanocontingenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planocontingencia.class));
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

    public Planocontingencia findPlanocontingencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planocontingencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanocontingenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planocontingencia> rt = cq.from(Planocontingencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Planocontingencia> getListPlanoContingenciaByIdRisco(Risco risco){
        List<Planocontingencia> listaContingencia = null;
        EntityManager em = getEntityManager();
        
        try{
            listaContingencia = (List<Planocontingencia>) em.createNamedQuery("Planocontingencia.findPlanoContingenciaByIdRisco")
                    .setParameter("idRisco",risco)
                    .getResultList();
            
        }catch( NoResultException e){
            return null;
        }catch (Exception e){
            System.out.println("Erro no método getListPlanoContingenciaByIdRisco da classe PlanocontingenciaJpaController");
            e.printStackTrace();
        }
        return listaContingencia;
    }    
}
