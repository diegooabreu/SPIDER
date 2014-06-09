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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Avaliacaoprojeto;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class AvaliacaoprojetoJpaController implements Serializable {

    public AvaliacaoprojetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AvaliacaoprojetoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliacaoprojeto avaliacaoprojeto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto idProjeto = avaliacaoprojeto.getIdProjeto();
            if (idProjeto != null) {
                idProjeto = em.getReference(idProjeto.getClass(), idProjeto.getIdProjeto());
                avaliacaoprojeto.setIdProjeto(idProjeto);
            }
            em.persist(avaliacaoprojeto);
            if (idProjeto != null) {
                idProjeto.getAvaliacaoprojetoList().add(avaliacaoprojeto);
                idProjeto = em.merge(idProjeto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacaoprojeto avaliacaoprojeto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacaoprojeto persistentAvaliacaoprojeto = em.find(Avaliacaoprojeto.class, avaliacaoprojeto.getIdAvaliacao());
            Projeto idProjetoOld = persistentAvaliacaoprojeto.getIdProjeto();
            Projeto idProjetoNew = avaliacaoprojeto.getIdProjeto();
            if (idProjetoNew != null) {
                idProjetoNew = em.getReference(idProjetoNew.getClass(), idProjetoNew.getIdProjeto());
                avaliacaoprojeto.setIdProjeto(idProjetoNew);
            }
            avaliacaoprojeto = em.merge(avaliacaoprojeto);
            if (idProjetoOld != null && !idProjetoOld.equals(idProjetoNew)) {
                idProjetoOld.getAvaliacaoprojetoList().remove(avaliacaoprojeto);
                idProjetoOld = em.merge(idProjetoOld);
            }
            if (idProjetoNew != null && !idProjetoNew.equals(idProjetoOld)) {
                idProjetoNew.getAvaliacaoprojetoList().add(avaliacaoprojeto);
                idProjetoNew = em.merge(idProjetoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacaoprojeto.getIdAvaliacao();
                if (findAvaliacaoprojeto(id) == null) {
                    throw new NonexistentEntityException("The avaliacaoprojeto with id " + id + " no longer exists.");
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
            Avaliacaoprojeto avaliacaoprojeto;
            try {
                avaliacaoprojeto = em.getReference(Avaliacaoprojeto.class, id);
                avaliacaoprojeto.getIdAvaliacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacaoprojeto with id " + id + " no longer exists.", enfe);
            }
            Projeto idProjeto = avaliacaoprojeto.getIdProjeto();
            if (idProjeto != null) {
                idProjeto.getAvaliacaoprojetoList().remove(avaliacaoprojeto);
                idProjeto = em.merge(idProjeto);
            }
            em.remove(avaliacaoprojeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacaoprojeto> findAvaliacaoprojetoEntities() {
        return findAvaliacaoprojetoEntities(true, -1, -1);
    }

    public List<Avaliacaoprojeto> findAvaliacaoprojetoEntities(int maxResults, int firstResult) {
        return findAvaliacaoprojetoEntities(false, maxResults, firstResult);
    }

    private List<Avaliacaoprojeto> findAvaliacaoprojetoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacaoprojeto.class));
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

    public Avaliacaoprojeto findAvaliacaoprojeto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacaoprojeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoprojetoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacaoprojeto> rt = cq.from(Avaliacaoprojeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Avaliacaoprojeto getAvaliacaoByIdProjeto(Projeto projeto){
        Avaliacaoprojeto avaliacaoProjeto = new Avaliacaoprojeto();
        EntityManager em = getEntityManager();
        try{
            avaliacaoProjeto = (Avaliacaoprojeto) em.createNamedQuery("Avaliacaoprojeto.findByIdProjeto")
                    .setParameter("idProjeto", projeto)
                    .getSingleResult();
        } catch( NoResultException e){
            return null;
        }
        
        catch (Exception e){
            System.out.println("Erro no método getAvaliacaoByIdProjeto da classe AvaliacaoprojetoJpaController");
            e.printStackTrace();
        }
        
        return avaliacaoProjeto;
    }
    
    
}
