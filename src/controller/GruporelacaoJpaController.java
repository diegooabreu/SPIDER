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
import model.Gruporelacao;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author Diego
 */
public class GruporelacaoJpaController implements Serializable {

    public GruporelacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public GruporelacaoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gruporelacao gruporelacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gruporelacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gruporelacao gruporelacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gruporelacao = em.merge(gruporelacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gruporelacao.getIdGrupo();
                if (findGruporelacao(id) == null) {
                    throw new NonexistentEntityException("The gruporelacao with id " + id + " no longer exists.");
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
            Gruporelacao gruporelacao;
            try {
                gruporelacao = em.getReference(Gruporelacao.class, id);
                gruporelacao.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gruporelacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(gruporelacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gruporelacao> findGruporelacaoEntities() {
        return findGruporelacaoEntities(true, -1, -1);
    }

    public List<Gruporelacao> findGruporelacaoEntities(int maxResults, int firstResult) {
        return findGruporelacaoEntities(false, maxResults, firstResult);
    }

    private List<Gruporelacao> findGruporelacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gruporelacao.class));
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

    public Gruporelacao findGruporelacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gruporelacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getGruporelacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gruporelacao> rt = cq.from(Gruporelacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Gruporelacao> findByIdSubcondicao1(Subcondicao condicao){
        List<Gruporelacao> listaRelacao = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaRelacao = em.createNamedQuery("Gruporelacao.findByIdSubcondicao1")
                    .setParameter("idSubcondicao1", condicao.getIdSubcondicao())
                    .getResultList();
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro no metodo findByIdSubcondicao1 da classe gruporelacaoJpaController");
        }
        
        
        return listaRelacao;
    }
    
    public List<Gruporelacao> findByIdSubcondicao2(Subcondicao condicao){
        List<Gruporelacao> listaRelacao = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaRelacao = em.createNamedQuery("Gruporelacao.findByIdSubcondicao2")
                    .setParameter("idSubcondicao2", condicao.getIdSubcondicao())
                    .getResultList();
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro no metodo findByIdSubcondicao2 da classe gruporelacaoJpaController");
        }
        
        
        return listaRelacao;
    }
    
    public List<Gruporelacao> findByRisco(Risco risco){
        List<Gruporelacao> listaRelacao = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaRelacao = em.createNamedQuery("Gruporelacao.findGruporelacaoByRisco")
                    .setParameter("idRisco", risco)
                    .getResultList();
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro no metodo findByIdSubcondicao2 da classe gruporelacaoJpaController");
        }
        
        
        return listaRelacao;
    }
    
}
