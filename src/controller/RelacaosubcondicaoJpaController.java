/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Subcondicao;
import model.Historicorisco;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Relacaosubcondicao;
import model.RelacaosubcondicaoPK;

/**
 *
 * @author Mariano
 */
public class RelacaosubcondicaoJpaController implements Serializable {

    public RelacaosubcondicaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RelacaosubcondicaoJpaController() {
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relacaosubcondicao relacaosubcondicao) throws PreexistingEntityException, Exception {
        if (relacaosubcondicao.getRelacaosubcondicaoPK() == null) {
            relacaosubcondicao.setRelacaosubcondicaoPK(new RelacaosubcondicaoPK());
        }
        if (relacaosubcondicao.getHistoricoriscoList() == null) {
            relacaosubcondicao.setHistoricoriscoList(new ArrayList<Historicorisco>());
        }
        relacaosubcondicao.getRelacaosubcondicaoPK().setIdSubcondicao2(relacaosubcondicao.getSubcondicao().getIdSubcondicao());
        relacaosubcondicao.getRelacaosubcondicaoPK().setIdSubcondicao1(relacaosubcondicao.getSubcondicao1().getIdSubcondicao());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subcondicao subcondicao = relacaosubcondicao.getSubcondicao();
            if (subcondicao != null) {
                subcondicao = em.getReference(subcondicao.getClass(), subcondicao.getIdSubcondicao());
                relacaosubcondicao.setSubcondicao(subcondicao);
            }
            Subcondicao subcondicao1 = relacaosubcondicao.getSubcondicao1();
            if (subcondicao1 != null) {
                subcondicao1 = em.getReference(subcondicao1.getClass(), subcondicao1.getIdSubcondicao());
                relacaosubcondicao.setSubcondicao1(subcondicao1);
            }
            List<Historicorisco> attachedHistoricoriscoList = new ArrayList<Historicorisco>();
            for (Historicorisco historicoriscoListHistoricoriscoToAttach : relacaosubcondicao.getHistoricoriscoList()) {
                historicoriscoListHistoricoriscoToAttach = em.getReference(historicoriscoListHistoricoriscoToAttach.getClass(), historicoriscoListHistoricoriscoToAttach.getIdHistoricoRisco());
                attachedHistoricoriscoList.add(historicoriscoListHistoricoriscoToAttach);
            }
            relacaosubcondicao.setHistoricoriscoList(attachedHistoricoriscoList);
            em.persist(relacaosubcondicao);
            if (subcondicao != null) {
                subcondicao.getRelacaosubcondicaoList().add(relacaosubcondicao);
                subcondicao = em.merge(subcondicao);
            }
            if (subcondicao1 != null) {
                subcondicao1.getRelacaosubcondicaoList().add(relacaosubcondicao);
                subcondicao1 = em.merge(subcondicao1);
            }
            for (Historicorisco historicoriscoListHistoricorisco : relacaosubcondicao.getHistoricoriscoList()) {
                historicoriscoListHistoricorisco.getRelacaosubcondicaoList().add(relacaosubcondicao);
                historicoriscoListHistoricorisco = em.merge(historicoriscoListHistoricorisco);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelacaosubcondicao(relacaosubcondicao.getRelacaosubcondicaoPK()) != null) {
                throw new PreexistingEntityException("Relacaosubcondicao " + relacaosubcondicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relacaosubcondicao relacaosubcondicao) throws NonexistentEntityException, Exception {
        relacaosubcondicao.getRelacaosubcondicaoPK().setIdSubcondicao2(relacaosubcondicao.getSubcondicao().getIdSubcondicao());
        relacaosubcondicao.getRelacaosubcondicaoPK().setIdSubcondicao1(relacaosubcondicao.getSubcondicao1().getIdSubcondicao());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Relacaosubcondicao persistentRelacaosubcondicao = em.find(Relacaosubcondicao.class, relacaosubcondicao.getRelacaosubcondicaoPK());
            Subcondicao subcondicaoOld = persistentRelacaosubcondicao.getSubcondicao();
            Subcondicao subcondicaoNew = relacaosubcondicao.getSubcondicao();
            Subcondicao subcondicao1Old = persistentRelacaosubcondicao.getSubcondicao1();
            Subcondicao subcondicao1New = relacaosubcondicao.getSubcondicao1();
            List<Historicorisco> historicoriscoListOld = persistentRelacaosubcondicao.getHistoricoriscoList();
            List<Historicorisco> historicoriscoListNew = relacaosubcondicao.getHistoricoriscoList();
            if (subcondicaoNew != null) {
                subcondicaoNew = em.getReference(subcondicaoNew.getClass(), subcondicaoNew.getIdSubcondicao());
                relacaosubcondicao.setSubcondicao(subcondicaoNew);
            }
            if (subcondicao1New != null) {
                subcondicao1New = em.getReference(subcondicao1New.getClass(), subcondicao1New.getIdSubcondicao());
                relacaosubcondicao.setSubcondicao1(subcondicao1New);
            }
            List<Historicorisco> attachedHistoricoriscoListNew = new ArrayList<Historicorisco>();
            for (Historicorisco historicoriscoListNewHistoricoriscoToAttach : historicoriscoListNew) {
                historicoriscoListNewHistoricoriscoToAttach = em.getReference(historicoriscoListNewHistoricoriscoToAttach.getClass(), historicoriscoListNewHistoricoriscoToAttach.getIdHistoricoRisco());
                attachedHistoricoriscoListNew.add(historicoriscoListNewHistoricoriscoToAttach);
            }
            historicoriscoListNew = attachedHistoricoriscoListNew;
            relacaosubcondicao.setHistoricoriscoList(historicoriscoListNew);
            relacaosubcondicao = em.merge(relacaosubcondicao);
            if (subcondicaoOld != null && !subcondicaoOld.equals(subcondicaoNew)) {
                subcondicaoOld.getRelacaosubcondicaoList().remove(relacaosubcondicao);
                subcondicaoOld = em.merge(subcondicaoOld);
            }
            if (subcondicaoNew != null && !subcondicaoNew.equals(subcondicaoOld)) {
                subcondicaoNew.getRelacaosubcondicaoList().add(relacaosubcondicao);
                subcondicaoNew = em.merge(subcondicaoNew);
            }
            if (subcondicao1Old != null && !subcondicao1Old.equals(subcondicao1New)) {
                subcondicao1Old.getRelacaosubcondicaoList().remove(relacaosubcondicao);
                subcondicao1Old = em.merge(subcondicao1Old);
            }
            if (subcondicao1New != null && !subcondicao1New.equals(subcondicao1Old)) {
                subcondicao1New.getRelacaosubcondicaoList().add(relacaosubcondicao);
                subcondicao1New = em.merge(subcondicao1New);
            }
            for (Historicorisco historicoriscoListOldHistoricorisco : historicoriscoListOld) {
                if (!historicoriscoListNew.contains(historicoriscoListOldHistoricorisco)) {
                    historicoriscoListOldHistoricorisco.getRelacaosubcondicaoList().remove(relacaosubcondicao);
                    historicoriscoListOldHistoricorisco = em.merge(historicoriscoListOldHistoricorisco);
                }
            }
            for (Historicorisco historicoriscoListNewHistoricorisco : historicoriscoListNew) {
                if (!historicoriscoListOld.contains(historicoriscoListNewHistoricorisco)) {
                    historicoriscoListNewHistoricorisco.getRelacaosubcondicaoList().add(relacaosubcondicao);
                    historicoriscoListNewHistoricorisco = em.merge(historicoriscoListNewHistoricorisco);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RelacaosubcondicaoPK id = relacaosubcondicao.getRelacaosubcondicaoPK();
                if (findRelacaosubcondicao(id) == null) {
                    throw new NonexistentEntityException("The relacaosubcondicao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RelacaosubcondicaoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Relacaosubcondicao relacaosubcondicao;
            try {
                relacaosubcondicao = em.getReference(Relacaosubcondicao.class, id);
                relacaosubcondicao.getRelacaosubcondicaoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relacaosubcondicao with id " + id + " no longer exists.", enfe);
            }
            Subcondicao subcondicao = relacaosubcondicao.getSubcondicao();
            if (subcondicao != null) {
                subcondicao.getRelacaosubcondicaoList().remove(relacaosubcondicao);
                subcondicao = em.merge(subcondicao);
            }
            Subcondicao subcondicao1 = relacaosubcondicao.getSubcondicao1();
            if (subcondicao1 != null) {
                subcondicao1.getRelacaosubcondicaoList().remove(relacaosubcondicao);
                subcondicao1 = em.merge(subcondicao1);
            }
            List<Historicorisco> historicoriscoList = relacaosubcondicao.getHistoricoriscoList();
            for (Historicorisco historicoriscoListHistoricorisco : historicoriscoList) {
                historicoriscoListHistoricorisco.getRelacaosubcondicaoList().remove(relacaosubcondicao);
                historicoriscoListHistoricorisco = em.merge(historicoriscoListHistoricorisco);
            }
            em.remove(relacaosubcondicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relacaosubcondicao> findRelacaosubcondicaoEntities() {
        return findRelacaosubcondicaoEntities(true, -1, -1);
    }

    public List<Relacaosubcondicao> findRelacaosubcondicaoEntities(int maxResults, int firstResult) {
        return findRelacaosubcondicaoEntities(false, maxResults, firstResult);
    }

    private List<Relacaosubcondicao> findRelacaosubcondicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relacaosubcondicao.class));
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

    public Relacaosubcondicao findRelacaosubcondicao(RelacaosubcondicaoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relacaosubcondicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelacaosubcondicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relacaosubcondicao> rt = cq.from(Relacaosubcondicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Relacaosubcondicao> findRelacaoSubcondicaoByIdSubcondicao1(int idSubcondicao1) {
        List<Relacaosubcondicao> listaRelacaoSubcondicao = null;
        EntityManager em = getEntityManager();
        try {
            listaRelacaoSubcondicao = em.createNamedQuery("Relacaosubcondicao.findByIdSubcondicao1")
                    .setParameter("idSubcondicao1", idSubcondicao1)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Erro no metodo findRelacaoSubcondicaoByIdSubcondicao1 da classe RelacaoSubcondicaoJpaController");
            e.printStackTrace();
        }

        return listaRelacaoSubcondicao;
    }

}
