/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Planomitigacao;
import model.Projeto;
import model.Risco;

/**
 *
 * @author MarcosSenna
 */
public class PlanomitigacaoJpaController implements Serializable {

    public PlanomitigacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PlanomitigacaoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planomitigacao planomitigacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(planomitigacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planomitigacao planomitigacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            planomitigacao = em.merge(planomitigacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planomitigacao.getIdPlanoMitigacao();
                if (findPlanomitigacao(id) == null) {
                    throw new NonexistentEntityException("The planomitigacao with id " + id + " no longer exists.");
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
            Planomitigacao planomitigacao;
            try {
                planomitigacao = em.getReference(Planomitigacao.class, id);
                planomitigacao.getIdPlanoMitigacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planomitigacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(planomitigacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planomitigacao> findPlanomitigacaoEntities() {
        return findPlanomitigacaoEntities(true, -1, -1);
    }

    public List<Planomitigacao> findPlanomitigacaoEntities(int maxResults, int firstResult) {
        return findPlanomitigacaoEntities(false, maxResults, firstResult);
    }

    private List<Planomitigacao> findPlanomitigacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planomitigacao.class));
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

    public Planomitigacao findPlanomitigacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planomitigacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanomitigacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planomitigacao> rt = cq.from(Planomitigacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Planomitigacao> getListPlanoMitigacaoByIdRisco(Risco risco){
        List<Planomitigacao> listaMitigacao = null;
        EntityManager em = getEntityManager();
        try{
            listaMitigacao = (List<Planomitigacao>) em.createNamedQuery("Planomitigacao.findPlanoMitigacaoByIdRisco")
                    .setParameter("idRisco",risco)
                    .getResultList();
            
        }catch( NoResultException e){
            return null;
        }catch (Exception e){
            System.out.println("Erro no método getListPlanoMitigacaoByIdRisco da classe PlanomitigacaoJpaController");
            e.printStackTrace();
        }
        return listaMitigacao;
    }
    
        
    public List<Planomitigacao> getPlanoMitigacaoByStatusRiscoAndDataLimiteAndIdProjeto(String statusRisco, Date dataLimite, Projeto idProjeto){
        List<Planomitigacao> listaPM = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaPM = em.createNamedQuery("Planomitigacao.findPlanoMitigacaoEmAcao")
                    .setParameter("statusRisco", statusRisco)
                    .setParameter("dataLimite", dataLimite)
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findMarcodoprojetoByIdProjeto da classe MarcodoprojetoJpaController");
            e.printStackTrace();
        }
        
        
        return listaPM;
    }
    
}
