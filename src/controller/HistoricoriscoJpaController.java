/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Historicorisco;
import model.Subcondicao;

/**
 *
 * @author MarcosSenna
 */
public class HistoricoriscoJpaController implements Serializable {

    public HistoricoriscoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public HistoricoriscoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicorisco historicorisco) {
//        if (historicorisco.getRelacaosubcondicaoList() == null) {
//            historicorisco.setRelacaosubcondicaoList(new ArrayList<Relacaosubcondicao>());
//        }
        if (historicorisco.getSubcondicaoList() == null) {
            historicorisco.setSubcondicaoList(new ArrayList<Subcondicao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
//            List<Relacaosubcondicao> attachedRelacaosubcondicaoList = new ArrayList<Relacaosubcondicao>();
//            for (Relacaosubcondicao relacaosubcondicaoListRelacaosubcondicaoToAttach : historicorisco.getRelacaosubcondicaoList()) {
//                relacaosubcondicaoListRelacaosubcondicaoToAttach = em.getReference(relacaosubcondicaoListRelacaosubcondicaoToAttach.getClass(), relacaosubcondicaoListRelacaosubcondicaoToAttach.getRelacaosubcondicaoPK());
//                attachedRelacaosubcondicaoList.add(relacaosubcondicaoListRelacaosubcondicaoToAttach);
//            }
//            historicorisco.setRelacaosubcondicaoList(attachedRelacaosubcondicaoList);
            List<Subcondicao> attachedSubcondicaoList = new ArrayList<Subcondicao>();
            for (Subcondicao subcondicaoListSubcondicaoToAttach : historicorisco.getSubcondicaoList()) {
                subcondicaoListSubcondicaoToAttach = em.getReference(subcondicaoListSubcondicaoToAttach.getClass(), subcondicaoListSubcondicaoToAttach.getIdSubcondicao());
                attachedSubcondicaoList.add(subcondicaoListSubcondicaoToAttach);
            }
            historicorisco.setSubcondicaoList(attachedSubcondicaoList);
            em.persist(historicorisco);
//            for (Relacaosubcondicao relacaosubcondicaoListRelacaosubcondicao : historicorisco.getRelacaosubcondicaoList()) {
//                relacaosubcondicaoListRelacaosubcondicao.getHistoricoriscoList().add(historicorisco);
//                relacaosubcondicaoListRelacaosubcondicao = em.merge(relacaosubcondicaoListRelacaosubcondicao);
//            }
            for (Subcondicao subcondicaoListSubcondicao : historicorisco.getSubcondicaoList()) {
                subcondicaoListSubcondicao.getHistoricoriscoList().add(historicorisco);
                subcondicaoListSubcondicao = em.merge(subcondicaoListSubcondicao);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historicorisco historicorisco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historicorisco persistentHistoricorisco = em.find(Historicorisco.class, historicorisco.getIdHistoricoRisco());
//            List<Relacaosubcondicao> relacaosubcondicaoListOld = persistentHistoricorisco.getRelacaosubcondicaoList();
//            List<Relacaosubcondicao> relacaosubcondicaoListNew = historicorisco.getRelacaosubcondicaoList();
//            List<Subcondicao> subcondicaoListOld = persistentHistoricorisco.getSubcondicaoList();
//            List<Subcondicao> subcondicaoListNew = historicorisco.getSubcondicaoList();
//            List<Relacaosubcondicao> attachedRelacaosubcondicaoListNew = new ArrayList<Relacaosubcondicao>();
//            for (Relacaosubcondicao relacaosubcondicaoListNewRelacaosubcondicaoToAttach : relacaosubcondicaoListNew) {
//                relacaosubcondicaoListNewRelacaosubcondicaoToAttach = em.getReference(relacaosubcondicaoListNewRelacaosubcondicaoToAttach.getClass(), relacaosubcondicaoListNewRelacaosubcondicaoToAttach.getRelacaosubcondicaoPK());
//                attachedRelacaosubcondicaoListNew.add(relacaosubcondicaoListNewRelacaosubcondicaoToAttach);
//            }
//            relacaosubcondicaoListNew = attachedRelacaosubcondicaoListNew;
//            historicorisco.setRelacaosubcondicaoList(relacaosubcondicaoListNew);
            List<Subcondicao> attachedSubcondicaoListNew = new ArrayList<Subcondicao>();
//            for (Subcondicao subcondicaoListNewSubcondicaoToAttach : subcondicaoListNew) {
//                subcondicaoListNewSubcondicaoToAttach = em.getReference(subcondicaoListNewSubcondicaoToAttach.getClass(), subcondicaoListNewSubcondicaoToAttach.getIdSubcondicao());
////                attachedSubcondicaoListNew.add(subcondicaoListNewSubcondicaoToAttach);
////            }
//            subcondicaoListNew = attachedSubcondicaoListNew;
//            historicorisco.setSubcondicaoList(subcondicaoListNew);
//            historicorisco = em.merge(historicorisco);
//            for (Relacaosubcondicao relacaosubcondicaoListOldRelacaosubcondicao : relacaosubcondicaoListOld) {
//                if (!relacaosubcondicaoListNew.contains(relacaosubcondicaoListOldRelacaosubcondicao)) {
//                    relacaosubcondicaoListOldRelacaosubcondicao.getHistoricoriscoList().remove(historicorisco);
//                    relacaosubcondicaoListOldRelacaosubcondicao = em.merge(relacaosubcondicaoListOldRelacaosubcondicao);
//                }
//            }
//            for (Relacaosubcondicao relacaosubcondicaoListNewRelacaosubcondicao : relacaosubcondicaoListNew) {
//                if (!relacaosubcondicaoListOld.contains(relacaosubcondicaoListNewRelacaosubcondicao)) {
//                    relacaosubcondicaoListNewRelacaosubcondicao.getHistoricoriscoList().add(historicorisco);
//                    relacaosubcondicaoListNewRelacaosubcondicao = em.merge(relacaosubcondicaoListNewRelacaosubcondicao);
//                }
//            }
//            for (Subcondicao subcondicaoListOldSubcondicao : subcondicaoListOld) {
//                if (!subcondicaoListNew.contains(subcondicaoListOldSubcondicao)) {
//                    subcondicaoListOldSubcondicao.getHistoricoriscoList().remove(historicorisco);
//                    subcondicaoListOldSubcondicao = em.merge(subcondicaoListOldSubcondicao);
//                }
//            }
//            for (Subcondicao subcondicaoListNewSubcondicao : subcondicaoListNew) {
//                if (!subcondicaoListOld.contains(subcondicaoListNewSubcondicao)) {
//                    subcondicaoListNewSubcondicao.getHistoricoriscoList().add(historicorisco);
//                    subcondicaoListNewSubcondicao = em.merge(subcondicaoListNewSubcondicao);
//                }
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicorisco.getIdHistoricoRisco();
                if (findHistoricorisco(id) == null) {
                    throw new NonexistentEntityException("The historicorisco with id " + id + " no longer exists.");
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
            Historicorisco historicorisco;
            try {
                historicorisco = em.getReference(Historicorisco.class, id);
                historicorisco.getIdHistoricoRisco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicorisco with id " + id + " no longer exists.", enfe);
            }
//            List<Relacaosubcondicao> relacaosubcondicaoList = historicorisco.getRelacaosubcondicaoList();
//            for (Relacaosubcondicao relacaosubcondicaoListRelacaosubcondicao : relacaosubcondicaoList) {
//                relacaosubcondicaoListRelacaosubcondicao.getHistoricoriscoList().remove(historicorisco);
//                relacaosubcondicaoListRelacaosubcondicao = em.merge(relacaosubcondicaoListRelacaosubcondicao);
//            }
            List<Subcondicao> subcondicaoList = historicorisco.getSubcondicaoList();
            for (Subcondicao subcondicaoListSubcondicao : subcondicaoList) {
                subcondicaoListSubcondicao.getHistoricoriscoList().remove(historicorisco);
                subcondicaoListSubcondicao = em.merge(subcondicaoListSubcondicao);
            }
            em.remove(historicorisco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historicorisco> findHistoricoriscoEntities() {
        return findHistoricoriscoEntities(true, -1, -1);
    }

    public List<Historicorisco> findHistoricoriscoEntities(int maxResults, int firstResult) {
        return findHistoricoriscoEntities(false, maxResults, firstResult);
    }

    private List<Historicorisco> findHistoricoriscoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historicorisco.class));
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

    public Historicorisco findHistoricorisco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicorisco.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoriscoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historicorisco> rt = cq.from(Historicorisco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Historicorisco> findHistoricoRiscoByIdRisco(int idRisco){
        
        List<Historicorisco> listaHistoricoRisco = null;
        try {
            EntityManager entityManager = getEntityManager();
            listaHistoricoRisco = entityManager.createNamedQuery("Historicorisco.findByIdRisco")
                                                               .setParameter("idRisco", idRisco)
                                                               .getResultList();
        } catch (Exception e){
            System.err.println("erro no metodo findHistoricoRiscoByIdRisco da classe HistoricoRiscoJpaController");
            e.printStackTrace();
        }
        return listaHistoricoRisco;
    }
    
    public List<Historicorisco> findHistoricoRiscoByIdProjeto(int idProjeto){
        
        List<Historicorisco> listaHistoricoriscos = null;
        try {
            EntityManager entityManager = getEntityManager();
            listaHistoricoriscos = entityManager.createNamedQuery("Historicorisco.findByIdProjeto")
                                                                 .setParameter("idProjeto", idProjeto)
                                                                 .getResultList();
        } catch (Exception e){
            System.err.println("erro no metodo findHistoricoRiscoByIdProjetoAndStatusRisco da classe HistoricoRiscoJpaController");
            e.printStackTrace();
        }
        return listaHistoricoriscos;
    }
    
}
