/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Historicorisco;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Relacaosubcondicao;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author MarcosSenna
 */
public class SubcondicaoJpaController implements Serializable {

    public SubcondicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SubcondicaoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subcondicao subcondicao) {
        if (subcondicao.getHistoricoriscoList() == null) {
            subcondicao.setHistoricoriscoList(new ArrayList<Historicorisco>());
        }
        if (subcondicao.getRelacaosubcondicaoList() == null) {
            subcondicao.setRelacaosubcondicaoList(new ArrayList<Relacaosubcondicao>());
        }
        if (subcondicao.getRelacaosubcondicaoList1() == null) {
            subcondicao.setRelacaosubcondicaoList1(new ArrayList<Relacaosubcondicao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historicorisco> attachedHistoricoriscoList = new ArrayList<Historicorisco>();
            for (Historicorisco historicoriscoListHistoricoriscoToAttach : subcondicao.getHistoricoriscoList()) {
                historicoriscoListHistoricoriscoToAttach = em.getReference(historicoriscoListHistoricoriscoToAttach.getClass(), historicoriscoListHistoricoriscoToAttach.getIdHistoricoRisco());
                attachedHistoricoriscoList.add(historicoriscoListHistoricoriscoToAttach);
            }
            subcondicao.setHistoricoriscoList(attachedHistoricoriscoList);
            List<Relacaosubcondicao> attachedRelacaosubcondicaoList = new ArrayList<Relacaosubcondicao>();
            for (Relacaosubcondicao relacaosubcondicaoListRelacaosubcondicaoToAttach : subcondicao.getRelacaosubcondicaoList()) {
                relacaosubcondicaoListRelacaosubcondicaoToAttach = em.getReference(relacaosubcondicaoListRelacaosubcondicaoToAttach.getClass(), relacaosubcondicaoListRelacaosubcondicaoToAttach.getRelacaosubcondicaoPK());
                attachedRelacaosubcondicaoList.add(relacaosubcondicaoListRelacaosubcondicaoToAttach);
            }
            subcondicao.setRelacaosubcondicaoList(attachedRelacaosubcondicaoList);
            List<Relacaosubcondicao> attachedRelacaosubcondicaoList1 = new ArrayList<Relacaosubcondicao>();
            for (Relacaosubcondicao relacaosubcondicaoList1RelacaosubcondicaoToAttach : subcondicao.getRelacaosubcondicaoList1()) {
                relacaosubcondicaoList1RelacaosubcondicaoToAttach = em.getReference(relacaosubcondicaoList1RelacaosubcondicaoToAttach.getClass(), relacaosubcondicaoList1RelacaosubcondicaoToAttach.getRelacaosubcondicaoPK());
                attachedRelacaosubcondicaoList1.add(relacaosubcondicaoList1RelacaosubcondicaoToAttach);
            }
            subcondicao.setRelacaosubcondicaoList1(attachedRelacaosubcondicaoList1);
            em.persist(subcondicao);
            for (Historicorisco historicoriscoListHistoricorisco : subcondicao.getHistoricoriscoList()) {
                historicoriscoListHistoricorisco.getSubcondicaoList().add(subcondicao);
                historicoriscoListHistoricorisco = em.merge(historicoriscoListHistoricorisco);
            }
            for (Relacaosubcondicao relacaosubcondicaoListRelacaosubcondicao : subcondicao.getRelacaosubcondicaoList()) {
                Subcondicao oldSubcondicaoOfRelacaosubcondicaoListRelacaosubcondicao = relacaosubcondicaoListRelacaosubcondicao.getSubcondicao();
                relacaosubcondicaoListRelacaosubcondicao.setSubcondicao(subcondicao);
                relacaosubcondicaoListRelacaosubcondicao = em.merge(relacaosubcondicaoListRelacaosubcondicao);
                if (oldSubcondicaoOfRelacaosubcondicaoListRelacaosubcondicao != null) {
                    oldSubcondicaoOfRelacaosubcondicaoListRelacaosubcondicao.getRelacaosubcondicaoList().remove(relacaosubcondicaoListRelacaosubcondicao);
                    oldSubcondicaoOfRelacaosubcondicaoListRelacaosubcondicao = em.merge(oldSubcondicaoOfRelacaosubcondicaoListRelacaosubcondicao);
                }
            }
            for (Relacaosubcondicao relacaosubcondicaoList1Relacaosubcondicao : subcondicao.getRelacaosubcondicaoList1()) {
                Subcondicao oldSubcondicao1OfRelacaosubcondicaoList1Relacaosubcondicao = relacaosubcondicaoList1Relacaosubcondicao.getSubcondicao1();
                relacaosubcondicaoList1Relacaosubcondicao.setSubcondicao1(subcondicao);
                relacaosubcondicaoList1Relacaosubcondicao = em.merge(relacaosubcondicaoList1Relacaosubcondicao);
                if (oldSubcondicao1OfRelacaosubcondicaoList1Relacaosubcondicao != null) {
                    oldSubcondicao1OfRelacaosubcondicaoList1Relacaosubcondicao.getRelacaosubcondicaoList1().remove(relacaosubcondicaoList1Relacaosubcondicao);
                    oldSubcondicao1OfRelacaosubcondicaoList1Relacaosubcondicao = em.merge(oldSubcondicao1OfRelacaosubcondicaoList1Relacaosubcondicao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subcondicao subcondicao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcondicao persistentSubcondicao = em.find(Subcondicao.class, subcondicao.getIdSubcondicao());
            List<Historicorisco> historicoriscoListOld = persistentSubcondicao.getHistoricoriscoList();
            List<Historicorisco> historicoriscoListNew = subcondicao.getHistoricoriscoList();
            List<Relacaosubcondicao> relacaosubcondicaoListOld = persistentSubcondicao.getRelacaosubcondicaoList();
            List<Relacaosubcondicao> relacaosubcondicaoListNew = subcondicao.getRelacaosubcondicaoList();
            List<Relacaosubcondicao> relacaosubcondicaoList1Old = persistentSubcondicao.getRelacaosubcondicaoList1();
            List<Relacaosubcondicao> relacaosubcondicaoList1New = subcondicao.getRelacaosubcondicaoList1();
            List<String> illegalOrphanMessages = null;
            for (Relacaosubcondicao relacaosubcondicaoListOldRelacaosubcondicao : relacaosubcondicaoListOld) {
                if (!relacaosubcondicaoListNew.contains(relacaosubcondicaoListOldRelacaosubcondicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Relacaosubcondicao " + relacaosubcondicaoListOldRelacaosubcondicao + " since its subcondicao field is not nullable.");
                }
            }
            for (Relacaosubcondicao relacaosubcondicaoList1OldRelacaosubcondicao : relacaosubcondicaoList1Old) {
                if (!relacaosubcondicaoList1New.contains(relacaosubcondicaoList1OldRelacaosubcondicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Relacaosubcondicao " + relacaosubcondicaoList1OldRelacaosubcondicao + " since its subcondicao1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historicorisco> attachedHistoricoriscoListNew = new ArrayList<Historicorisco>();
            for (Historicorisco historicoriscoListNewHistoricoriscoToAttach : historicoriscoListNew) {
                historicoriscoListNewHistoricoriscoToAttach = em.getReference(historicoriscoListNewHistoricoriscoToAttach.getClass(), historicoriscoListNewHistoricoriscoToAttach.getIdHistoricoRisco());
                attachedHistoricoriscoListNew.add(historicoriscoListNewHistoricoriscoToAttach);
            }
            historicoriscoListNew = attachedHistoricoriscoListNew;
            subcondicao.setHistoricoriscoList(historicoriscoListNew);
            List<Relacaosubcondicao> attachedRelacaosubcondicaoListNew = new ArrayList<Relacaosubcondicao>();
            for (Relacaosubcondicao relacaosubcondicaoListNewRelacaosubcondicaoToAttach : relacaosubcondicaoListNew) {
                relacaosubcondicaoListNewRelacaosubcondicaoToAttach = em.getReference(relacaosubcondicaoListNewRelacaosubcondicaoToAttach.getClass(), relacaosubcondicaoListNewRelacaosubcondicaoToAttach.getRelacaosubcondicaoPK());
                attachedRelacaosubcondicaoListNew.add(relacaosubcondicaoListNewRelacaosubcondicaoToAttach);
            }
            relacaosubcondicaoListNew = attachedRelacaosubcondicaoListNew;
            subcondicao.setRelacaosubcondicaoList(relacaosubcondicaoListNew);
            List<Relacaosubcondicao> attachedRelacaosubcondicaoList1New = new ArrayList<Relacaosubcondicao>();
            for (Relacaosubcondicao relacaosubcondicaoList1NewRelacaosubcondicaoToAttach : relacaosubcondicaoList1New) {
                relacaosubcondicaoList1NewRelacaosubcondicaoToAttach = em.getReference(relacaosubcondicaoList1NewRelacaosubcondicaoToAttach.getClass(), relacaosubcondicaoList1NewRelacaosubcondicaoToAttach.getRelacaosubcondicaoPK());
                attachedRelacaosubcondicaoList1New.add(relacaosubcondicaoList1NewRelacaosubcondicaoToAttach);
            }
            relacaosubcondicaoList1New = attachedRelacaosubcondicaoList1New;
            subcondicao.setRelacaosubcondicaoList1(relacaosubcondicaoList1New);
            subcondicao = em.merge(subcondicao);
            for (Historicorisco historicoriscoListOldHistoricorisco : historicoriscoListOld) {
                if (!historicoriscoListNew.contains(historicoriscoListOldHistoricorisco)) {
                    historicoriscoListOldHistoricorisco.getSubcondicaoList().remove(subcondicao);
                    historicoriscoListOldHistoricorisco = em.merge(historicoriscoListOldHistoricorisco);
                }
            }
            for (Historicorisco historicoriscoListNewHistoricorisco : historicoriscoListNew) {
                if (!historicoriscoListOld.contains(historicoriscoListNewHistoricorisco)) {
                    historicoriscoListNewHistoricorisco.getSubcondicaoList().add(subcondicao);
                    historicoriscoListNewHistoricorisco = em.merge(historicoriscoListNewHistoricorisco);
                }
            }
            for (Relacaosubcondicao relacaosubcondicaoListNewRelacaosubcondicao : relacaosubcondicaoListNew) {
                if (!relacaosubcondicaoListOld.contains(relacaosubcondicaoListNewRelacaosubcondicao)) {
                    Subcondicao oldSubcondicaoOfRelacaosubcondicaoListNewRelacaosubcondicao = relacaosubcondicaoListNewRelacaosubcondicao.getSubcondicao();
                    relacaosubcondicaoListNewRelacaosubcondicao.setSubcondicao(subcondicao);
                    relacaosubcondicaoListNewRelacaosubcondicao = em.merge(relacaosubcondicaoListNewRelacaosubcondicao);
                    if (oldSubcondicaoOfRelacaosubcondicaoListNewRelacaosubcondicao != null && !oldSubcondicaoOfRelacaosubcondicaoListNewRelacaosubcondicao.equals(subcondicao)) {
                        oldSubcondicaoOfRelacaosubcondicaoListNewRelacaosubcondicao.getRelacaosubcondicaoList().remove(relacaosubcondicaoListNewRelacaosubcondicao);
                        oldSubcondicaoOfRelacaosubcondicaoListNewRelacaosubcondicao = em.merge(oldSubcondicaoOfRelacaosubcondicaoListNewRelacaosubcondicao);
                    }
                }
            }
            for (Relacaosubcondicao relacaosubcondicaoList1NewRelacaosubcondicao : relacaosubcondicaoList1New) {
                if (!relacaosubcondicaoList1Old.contains(relacaosubcondicaoList1NewRelacaosubcondicao)) {
                    Subcondicao oldSubcondicao1OfRelacaosubcondicaoList1NewRelacaosubcondicao = relacaosubcondicaoList1NewRelacaosubcondicao.getSubcondicao1();
                    relacaosubcondicaoList1NewRelacaosubcondicao.setSubcondicao1(subcondicao);
                    relacaosubcondicaoList1NewRelacaosubcondicao = em.merge(relacaosubcondicaoList1NewRelacaosubcondicao);
                    if (oldSubcondicao1OfRelacaosubcondicaoList1NewRelacaosubcondicao != null && !oldSubcondicao1OfRelacaosubcondicaoList1NewRelacaosubcondicao.equals(subcondicao)) {
                        oldSubcondicao1OfRelacaosubcondicaoList1NewRelacaosubcondicao.getRelacaosubcondicaoList1().remove(relacaosubcondicaoList1NewRelacaosubcondicao);
                        oldSubcondicao1OfRelacaosubcondicaoList1NewRelacaosubcondicao = em.merge(oldSubcondicao1OfRelacaosubcondicaoList1NewRelacaosubcondicao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subcondicao.getIdSubcondicao();
                if (findSubcondicao(id) == null) {
                    throw new NonexistentEntityException("The subcondicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcondicao subcondicao;
            try {
                subcondicao = em.getReference(Subcondicao.class, id);
                subcondicao.getIdSubcondicao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subcondicao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Relacaosubcondicao> relacaosubcondicaoListOrphanCheck = subcondicao.getRelacaosubcondicaoList();
            for (Relacaosubcondicao relacaosubcondicaoListOrphanCheckRelacaosubcondicao : relacaosubcondicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subcondicao (" + subcondicao + ") cannot be destroyed since the Relacaosubcondicao " + relacaosubcondicaoListOrphanCheckRelacaosubcondicao + " in its relacaosubcondicaoList field has a non-nullable subcondicao field.");
            }
            List<Relacaosubcondicao> relacaosubcondicaoList1OrphanCheck = subcondicao.getRelacaosubcondicaoList1();
            for (Relacaosubcondicao relacaosubcondicaoList1OrphanCheckRelacaosubcondicao : relacaosubcondicaoList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subcondicao (" + subcondicao + ") cannot be destroyed since the Relacaosubcondicao " + relacaosubcondicaoList1OrphanCheckRelacaosubcondicao + " in its relacaosubcondicaoList1 field has a non-nullable subcondicao1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historicorisco> historicoriscoList = subcondicao.getHistoricoriscoList();
            for (Historicorisco historicoriscoListHistoricorisco : historicoriscoList) {
                historicoriscoListHistoricorisco.getSubcondicaoList().remove(subcondicao);
                historicoriscoListHistoricorisco = em.merge(historicoriscoListHistoricorisco);
            }
            em.remove(subcondicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Subcondicao> findSubcondicaoEntities() {
        return findSubcondicaoEntities(true, -1, -1);
    }

    public List<Subcondicao> findSubcondicaoEntities(int maxResults, int firstResult) {
        return findSubcondicaoEntities(false, maxResults, firstResult);
    }

    private List<Subcondicao> findSubcondicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subcondicao.class));
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

    public Subcondicao findSubcondicao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subcondicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubcondicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subcondicao> rt = cq.from(Subcondicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Subcondicao> findSubcondicaoByIdRisco(Risco idRisco){
        List<Subcondicao> listaSubcondicao = null;
        EntityManager em = getEntityManager();
        try{
            listaSubcondicao = em.createNamedQuery("Subcondicao.findSubcondicaoByIdRisco")
                    .setParameter("idRisco", idRisco)
                    .getResultList();
        }catch(Exception e){
            System.out.println("Erro no metodo findSubcondicaoByIdRisco da classe SubcondicaoJpaController");
            e.printStackTrace();
        }
        
        return listaSubcondicao;
    }
    
//    public List<Subcondicao> findHistoricoRiscoByIdRisco (Risco idRisco){
//        List<Subcondicao> listaSubcondicao = null;
//        EntityManager entityManager = getEntityManager();
//        
//        try {
//            listaSubcondicao = entityManager.createNamedQuery("Subcondicao.findHistoricoRiscoByIdRisco")
//                                                             .setParameter("idRisco", idRisco.getIdRisco())
//                                                             .getResultList();
//        } catch (Exception e) {
//            System.out.println("Erro no metodo findHistoricoRiscoByIdRisco da classe SubcondicaoJpaController");
//            e.printStackTrace();
//        }
//        
//        return listaSubcondicao;
//    }
}
