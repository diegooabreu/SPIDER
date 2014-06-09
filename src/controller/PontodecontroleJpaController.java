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
import model.Pontodecontrole;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class PontodecontroleJpaController implements Serializable {

    public PontodecontroleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PontodecontroleJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pontodecontrole pontodecontrole) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto idProjeto = pontodecontrole.getIdProjeto();
            if (idProjeto != null) {
                idProjeto = em.getReference(idProjeto.getClass(), idProjeto.getIdProjeto());
                pontodecontrole.setIdProjeto(idProjeto);
            }
            em.persist(pontodecontrole);
            if (idProjeto != null) {
                idProjeto.getPontodecontroleList().add(pontodecontrole);
                idProjeto = em.merge(idProjeto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pontodecontrole pontodecontrole) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pontodecontrole persistentPontodecontrole = em.find(Pontodecontrole.class, pontodecontrole.getIdPontoDeControle());
            Projeto idProjetoOld = persistentPontodecontrole.getIdProjeto();
            Projeto idProjetoNew = pontodecontrole.getIdProjeto();
            if (idProjetoNew != null) {
                idProjetoNew = em.getReference(idProjetoNew.getClass(), idProjetoNew.getIdProjeto());
                pontodecontrole.setIdProjeto(idProjetoNew);
            }
            pontodecontrole = em.merge(pontodecontrole);
            if (idProjetoOld != null && !idProjetoOld.equals(idProjetoNew)) {
                idProjetoOld.getPontodecontroleList().remove(pontodecontrole);
                idProjetoOld = em.merge(idProjetoOld);
            }
            if (idProjetoNew != null && !idProjetoNew.equals(idProjetoOld)) {
                idProjetoNew.getPontodecontroleList().add(pontodecontrole);
                idProjetoNew = em.merge(idProjetoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pontodecontrole.getIdPontoDeControle();
                if (findPontodecontrole(id) == null) {
                    throw new NonexistentEntityException("The pontodecontrole with id " + id + " no longer exists.");
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
            Pontodecontrole pontodecontrole;
            try {
                pontodecontrole = em.getReference(Pontodecontrole.class, id);
                pontodecontrole.getIdPontoDeControle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pontodecontrole with id " + id + " no longer exists.", enfe);
            }
            Projeto idProjeto = pontodecontrole.getIdProjeto();
            if (idProjeto != null) {
                idProjeto.getPontodecontroleList().remove(pontodecontrole);
                idProjeto = em.merge(idProjeto);
            }
            em.remove(pontodecontrole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pontodecontrole> findPontodecontroleEntities() {
        return findPontodecontroleEntities(true, -1, -1);
    }

    public List<Pontodecontrole> findPontodecontroleEntities(int maxResults, int firstResult) {
        return findPontodecontroleEntities(false, maxResults, firstResult);
    }

    private List<Pontodecontrole> findPontodecontroleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pontodecontrole.class));
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

    public Pontodecontrole findPontodecontrole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pontodecontrole.class, id);
        } finally {
            em.close();
        }
    }

    public int getPontodecontroleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pontodecontrole> rt = cq.from(Pontodecontrole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Pontodecontrole> findPontodecontroleByIdProjeto(Projeto idProjeto){
        List<Pontodecontrole> listaPontosDeControle = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaPontosDeControle = em.createNamedQuery("Pontodecontrole.findPontodecontroleByIdProjeto")
                    .setParameter("idProjeto",idProjeto)
                    .getResultList();
        } catch (Exception e){
            System.out.println("erro no metodo findPontodecontroleByIdProjeto da classe MarcodoprojetoJpaController");
            e.printStackTrace();
        }
        
        return listaPontosDeControle;
    }
    
}
