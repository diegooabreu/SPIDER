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
import model.Marcodoprojeto;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class MarcodoprojetoJpaController implements Serializable {

    public MarcodoprojetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MarcodoprojetoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marcodoprojeto marcodoprojeto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto idProjeto = marcodoprojeto.getIdProjeto();
            if (idProjeto != null) {
                idProjeto = em.getReference(idProjeto.getClass(), idProjeto.getIdProjeto());
                marcodoprojeto.setIdProjeto(idProjeto);
            }
            em.persist(marcodoprojeto);
            if (idProjeto != null) {
                idProjeto.getMarcodoprojetoList().add(marcodoprojeto);
                idProjeto = em.merge(idProjeto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marcodoprojeto marcodoprojeto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marcodoprojeto persistentMarcodoprojeto = em.find(Marcodoprojeto.class, marcodoprojeto.getIdMarcoDoProjeto());
            Projeto idProjetoOld = persistentMarcodoprojeto.getIdProjeto();
            Projeto idProjetoNew = marcodoprojeto.getIdProjeto();
            if (idProjetoNew != null) {
                idProjetoNew = em.getReference(idProjetoNew.getClass(), idProjetoNew.getIdProjeto());
                marcodoprojeto.setIdProjeto(idProjetoNew);
            }
            marcodoprojeto = em.merge(marcodoprojeto);
            if (idProjetoOld != null && !idProjetoOld.equals(idProjetoNew)) {
                idProjetoOld.getMarcodoprojetoList().remove(marcodoprojeto);
                idProjetoOld = em.merge(idProjetoOld);
            }
            if (idProjetoNew != null && !idProjetoNew.equals(idProjetoOld)) {
                idProjetoNew.getMarcodoprojetoList().add(marcodoprojeto);
                idProjetoNew = em.merge(idProjetoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marcodoprojeto.getIdMarcoDoProjeto();
                if (findMarcodoprojeto(id) == null) {
                    throw new NonexistentEntityException("The marcodoprojeto with id " + id + " no longer exists.");
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
            Marcodoprojeto marcodoprojeto;
            try {
                marcodoprojeto = em.getReference(Marcodoprojeto.class, id);
                marcodoprojeto.getIdMarcoDoProjeto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marcodoprojeto with id " + id + " no longer exists.", enfe);
            }
            Projeto idProjeto = marcodoprojeto.getIdProjeto();
            if (idProjeto != null) {
                idProjeto.getMarcodoprojetoList().remove(marcodoprojeto);
                idProjeto = em.merge(idProjeto);
            }
            em.remove(marcodoprojeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marcodoprojeto> findMarcodoprojetoEntities() {
        return findMarcodoprojetoEntities(true, -1, -1);
    }

    public List<Marcodoprojeto> findMarcodoprojetoEntities(int maxResults, int firstResult) {
        return findMarcodoprojetoEntities(false, maxResults, firstResult);
    }

    private List<Marcodoprojeto> findMarcodoprojetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marcodoprojeto.class));
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

    public Marcodoprojeto findMarcodoprojeto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marcodoprojeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcodoprojetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marcodoprojeto> rt = cq.from(Marcodoprojeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Marcodoprojeto> findMarcodoprojetoByIdProjeto(Projeto idProjeto){
        List<Marcodoprojeto> listaMarcoDoProjeto = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaMarcoDoProjeto = em.createNamedQuery("Marcodoprojeto.findByIdProjeto")
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findMarcodoprojetoByIdProjeto da classe MarcodoprojetoJpaController");
            e.printStackTrace();
        }
        
        
        return listaMarcoDoProjeto;
        
    }
    
    
}
