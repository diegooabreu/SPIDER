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
import model.Historicoalteracao;
import model.Risco;

/**
 *
 * @author MarcosSenna
 */
public class HistoricoalteracaoJpaController implements Serializable {

    public HistoricoalteracaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public HistoricoalteracaoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicoalteracao historicoalteracao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historicoalteracao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historicoalteracao historicoalteracao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historicoalteracao = em.merge(historicoalteracao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicoalteracao.getIdHistoricoAlteracao();
                if (findHistoricoalteracao(id) == null) {
                    throw new NonexistentEntityException("The historicoalteracao with id " + id + " no longer exists.");
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
            Historicoalteracao historicoalteracao;
            try {
                historicoalteracao = em.getReference(Historicoalteracao.class, id);
                historicoalteracao.getIdHistoricoAlteracao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoalteracao with id " + id + " no longer exists.", enfe);
            }
            em.remove(historicoalteracao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historicoalteracao> findHistoricoalteracaoEntities() {
        return findHistoricoalteracaoEntities(true, -1, -1);
    }

    public List<Historicoalteracao> findHistoricoalteracaoEntities(int maxResults, int firstResult) {
        return findHistoricoalteracaoEntities(false, maxResults, firstResult);
    }

    private List<Historicoalteracao> findHistoricoalteracaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historicoalteracao.class));
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

    public Historicoalteracao findHistoricoalteracao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicoalteracao.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoalteracaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historicoalteracao> rt = cq.from(Historicoalteracao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Historicoalteracao> findHistoricoByIdRisco(Risco idRisco){
        List<Historicoalteracao> lista = null;
        EntityManager em = getEntityManager();
        try{
            lista = em.createNamedQuery("Historicoalteracao.findByIdRisco")
                    .setParameter("idRisco", idRisco)
                    .getResultList();
        } catch(Exception e){
            System.out.println("Erro no metodo findHistoricoByIdRisco da classe HistoricoalteracaoJpaController");
            e.printStackTrace();
        }
        
        return lista;
    }
    
}
