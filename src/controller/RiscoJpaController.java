/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
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
import model.Contem;
import model.Risco;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Historicorisco;
import model.Projeto;
import model.Subcondicao;

/**
 *
 * @author ITAU
 */
public class RiscoJpaController implements Serializable {

    public RiscoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
        public RiscoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Risco risco) {
        if (risco.getRiscoList2() == null) {
            risco.setRiscoList2(new ArrayList<Risco>());
        }
        if (risco.getRiscoList3() == null) {
            risco.setRiscoList3(new ArrayList<Risco>());
        }
        if (risco.getPlanocontingenciaList() == null) {
            risco.setPlanocontingenciaList(new ArrayList<Planocontingencia>());
        }
        if (risco.getPlanomitigacaoList() == null) {
            risco.setPlanomitigacaoList(new ArrayList<Planomitigacao>());
        }
        if (risco.getHistoricoriscoList() == null) {
            risco.setHistoricoriscoList(new ArrayList<Historicorisco>());
        }
        if (risco.getSubcondicaoList() == null) {
            risco.setSubcondicaoList(new ArrayList<Subcondicao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contem contem = risco.getContem();
            if (contem != null) {
                contem = em.getReference(contem.getClass(), contem.getContemPK());
                risco.setContem(contem);
            }
            List<Risco> attachedRiscoList2 = new ArrayList<Risco>();
            for (Risco riscoList2RiscoToAttach : risco.getRiscoList2()) {
                riscoList2RiscoToAttach = em.getReference(riscoList2RiscoToAttach.getClass(), riscoList2RiscoToAttach.getIdRisco());
                attachedRiscoList2.add(riscoList2RiscoToAttach);
            }
            risco.setRiscoList2(attachedRiscoList2);
            List<Risco> attachedRiscoList3 = new ArrayList<Risco>();
            for (Risco riscoList3RiscoToAttach : risco.getRiscoList3()) {
                riscoList3RiscoToAttach = em.getReference(riscoList3RiscoToAttach.getClass(), riscoList3RiscoToAttach.getIdRisco());
                attachedRiscoList3.add(riscoList3RiscoToAttach);
            }
            risco.setRiscoList3(attachedRiscoList3);
            List<Planocontingencia> attachedPlanocontingenciaList = new ArrayList<Planocontingencia>();
            for (Planocontingencia planocontingenciaListPlanocontingenciaToAttach : risco.getPlanocontingenciaList()) {
                planocontingenciaListPlanocontingenciaToAttach = em.getReference(planocontingenciaListPlanocontingenciaToAttach.getClass(), planocontingenciaListPlanocontingenciaToAttach.getIdPlanoContingencia());
                attachedPlanocontingenciaList.add(planocontingenciaListPlanocontingenciaToAttach);
            }
            risco.setPlanocontingenciaList(attachedPlanocontingenciaList);
            List<Planomitigacao> attachedPlanomitigacaoList = new ArrayList<Planomitigacao>();
            for (Planomitigacao planomitigacaoListPlanomitigacaoToAttach : risco.getPlanomitigacaoList()) {
                planomitigacaoListPlanomitigacaoToAttach = em.getReference(planomitigacaoListPlanomitigacaoToAttach.getClass(), planomitigacaoListPlanomitigacaoToAttach.getIdPlanoMitigacao());
                attachedPlanomitigacaoList.add(planomitigacaoListPlanomitigacaoToAttach);
            }
            risco.setPlanomitigacaoList(attachedPlanomitigacaoList);
            List<Historicorisco> attachedHistoricoriscoList = new ArrayList<Historicorisco>();
            for (Historicorisco historicoriscoListHistoricoriscoToAttach : risco.getHistoricoriscoList()) {
                historicoriscoListHistoricoriscoToAttach = em.getReference(historicoriscoListHistoricoriscoToAttach.getClass(), historicoriscoListHistoricoriscoToAttach.getIdHistoricoRisco());
                attachedHistoricoriscoList.add(historicoriscoListHistoricoriscoToAttach);
            }
            risco.setHistoricoriscoList(attachedHistoricoriscoList);
            List<Subcondicao> attachedSubcondicaoList = new ArrayList<Subcondicao>();
            for (Subcondicao subcondicaoListSubcondicaoToAttach : risco.getSubcondicaoList()) {
                subcondicaoListSubcondicaoToAttach = em.getReference(subcondicaoListSubcondicaoToAttach.getClass(), subcondicaoListSubcondicaoToAttach.getIdSubcondicao());
                attachedSubcondicaoList.add(subcondicaoListSubcondicaoToAttach);
            }
            risco.setSubcondicaoList(attachedSubcondicaoList);
            em.persist(risco);
            if (contem != null) {
                contem.getRiscoList().add(risco);
                contem = em.merge(contem);
            }
            for (Risco riscoList2Risco : risco.getRiscoList2()) {
                riscoList2Risco.getRiscoList2().add(risco);
                riscoList2Risco = em.merge(riscoList2Risco);
            }
            for (Risco riscoList3Risco : risco.getRiscoList3()) {
                riscoList3Risco.getRiscoList2().add(risco);
                riscoList3Risco = em.merge(riscoList3Risco);
            }
            for (Planocontingencia planocontingenciaListPlanocontingencia : risco.getPlanocontingenciaList()) {
                Risco oldIdRiscoOfPlanocontingenciaListPlanocontingencia = planocontingenciaListPlanocontingencia.getIdRisco();
                planocontingenciaListPlanocontingencia.setIdRisco(risco);
                planocontingenciaListPlanocontingencia = em.merge(planocontingenciaListPlanocontingencia);
                if (oldIdRiscoOfPlanocontingenciaListPlanocontingencia != null) {
                    oldIdRiscoOfPlanocontingenciaListPlanocontingencia.getPlanocontingenciaList().remove(planocontingenciaListPlanocontingencia);
                    oldIdRiscoOfPlanocontingenciaListPlanocontingencia = em.merge(oldIdRiscoOfPlanocontingenciaListPlanocontingencia);
                }
            }
            for (Planomitigacao planomitigacaoListPlanomitigacao : risco.getPlanomitigacaoList()) {
                Risco oldIdRiscoOfPlanomitigacaoListPlanomitigacao = planomitigacaoListPlanomitigacao.getIdRisco();
                planomitigacaoListPlanomitigacao.setIdRisco(risco);
                planomitigacaoListPlanomitigacao = em.merge(planomitigacaoListPlanomitigacao);
                if (oldIdRiscoOfPlanomitigacaoListPlanomitigacao != null) {
                    oldIdRiscoOfPlanomitigacaoListPlanomitigacao.getPlanomitigacaoList().remove(planomitigacaoListPlanomitigacao);
                    oldIdRiscoOfPlanomitigacaoListPlanomitigacao = em.merge(oldIdRiscoOfPlanomitigacaoListPlanomitigacao);
                }
            }
            for (Historicorisco historicoriscoListHistoricorisco : risco.getHistoricoriscoList()) {
                Risco oldIdRiscoOfHistoricoriscoListHistoricorisco = historicoriscoListHistoricorisco.getIdRisco();
                historicoriscoListHistoricorisco.setIdRisco(risco);
                historicoriscoListHistoricorisco = em.merge(historicoriscoListHistoricorisco);
                if (oldIdRiscoOfHistoricoriscoListHistoricorisco != null) {
                    oldIdRiscoOfHistoricoriscoListHistoricorisco.getHistoricoriscoList().remove(historicoriscoListHistoricorisco);
                    oldIdRiscoOfHistoricoriscoListHistoricorisco = em.merge(oldIdRiscoOfHistoricoriscoListHistoricorisco);
                }
            }
            for (Subcondicao subcondicaoListSubcondicao : risco.getSubcondicaoList()) {
                Risco oldIdRiscoOfSubcondicaoListSubcondicao = subcondicaoListSubcondicao.getIdRisco();
                subcondicaoListSubcondicao.setIdRisco(risco);
                subcondicaoListSubcondicao = em.merge(subcondicaoListSubcondicao);
                if (oldIdRiscoOfSubcondicaoListSubcondicao != null) {
                    oldIdRiscoOfSubcondicaoListSubcondicao.getSubcondicaoList().remove(subcondicaoListSubcondicao);
                    oldIdRiscoOfSubcondicaoListSubcondicao = em.merge(oldIdRiscoOfSubcondicaoListSubcondicao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Risco risco) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Risco persistentRisco = em.find(Risco.class, risco.getIdRisco());
            Contem contemOld = persistentRisco.getContem();
            Contem contemNew = risco.getContem();
            List<Risco> riscoList2Old = persistentRisco.getRiscoList2();
            List<Risco> riscoList2New = risco.getRiscoList2();
            List<Risco> riscoList3Old = persistentRisco.getRiscoList3();
            List<Risco> riscoList3New = risco.getRiscoList3();
            List<Planocontingencia> planocontingenciaListOld = persistentRisco.getPlanocontingenciaList();
            List<Planocontingencia> planocontingenciaListNew = risco.getPlanocontingenciaList();
            List<Planomitigacao> planomitigacaoListOld = persistentRisco.getPlanomitigacaoList();
            List<Planomitigacao> planomitigacaoListNew = risco.getPlanomitigacaoList();
            List<Historicorisco> historicoriscoListOld = persistentRisco.getHistoricoriscoList();
            List<Historicorisco> historicoriscoListNew = risco.getHistoricoriscoList();
            List<Subcondicao> subcondicaoListOld = persistentRisco.getSubcondicaoList();
            List<Subcondicao> subcondicaoListNew = risco.getSubcondicaoList();
            List<String> illegalOrphanMessages = null;
            for (Planocontingencia planocontingenciaListOldPlanocontingencia : planocontingenciaListOld) {
                if (!planocontingenciaListNew.contains(planocontingenciaListOldPlanocontingencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planocontingencia " + planocontingenciaListOldPlanocontingencia + " since its idRisco field is not nullable.");
                }
            }
            for (Planomitigacao planomitigacaoListOldPlanomitigacao : planomitigacaoListOld) {
                if (!planomitigacaoListNew.contains(planomitigacaoListOldPlanomitigacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planomitigacao " + planomitigacaoListOldPlanomitigacao + " since its idRisco field is not nullable.");
                }
            }
            for (Historicorisco historicoriscoListOldHistoricorisco : historicoriscoListOld) {
                if (!historicoriscoListNew.contains(historicoriscoListOldHistoricorisco)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historicorisco " + historicoriscoListOldHistoricorisco + " since its idRisco field is not nullable.");
                }
            }
            for (Subcondicao subcondicaoListOldSubcondicao : subcondicaoListOld) {
                if (!subcondicaoListNew.contains(subcondicaoListOldSubcondicao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Subcondicao " + subcondicaoListOldSubcondicao + " since its idRisco field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contemNew != null) {
                contemNew = em.getReference(contemNew.getClass(), contemNew.getContemPK());
                risco.setContem(contemNew);
            }
            List<Risco> attachedRiscoList2New = new ArrayList<Risco>();
            for (Risco riscoList2NewRiscoToAttach : riscoList2New) {
                riscoList2NewRiscoToAttach = em.getReference(riscoList2NewRiscoToAttach.getClass(), riscoList2NewRiscoToAttach.getIdRisco());
                attachedRiscoList2New.add(riscoList2NewRiscoToAttach);
            }
            riscoList2New = attachedRiscoList2New;
            risco.setRiscoList2(riscoList2New);
            List<Risco> attachedRiscoList3New = new ArrayList<Risco>();
            for (Risco riscoList3NewRiscoToAttach : riscoList3New) {
                riscoList3NewRiscoToAttach = em.getReference(riscoList3NewRiscoToAttach.getClass(), riscoList3NewRiscoToAttach.getIdRisco());
                attachedRiscoList3New.add(riscoList3NewRiscoToAttach);
            }
            riscoList3New = attachedRiscoList3New;
            risco.setRiscoList3(riscoList3New);
            List<Planocontingencia> attachedPlanocontingenciaListNew = new ArrayList<Planocontingencia>();
            for (Planocontingencia planocontingenciaListNewPlanocontingenciaToAttach : planocontingenciaListNew) {
                planocontingenciaListNewPlanocontingenciaToAttach = em.getReference(planocontingenciaListNewPlanocontingenciaToAttach.getClass(), planocontingenciaListNewPlanocontingenciaToAttach.getIdPlanoContingencia());
                attachedPlanocontingenciaListNew.add(planocontingenciaListNewPlanocontingenciaToAttach);
            }
            planocontingenciaListNew = attachedPlanocontingenciaListNew;
            risco.setPlanocontingenciaList(planocontingenciaListNew);
            List<Planomitigacao> attachedPlanomitigacaoListNew = new ArrayList<Planomitigacao>();
            for (Planomitigacao planomitigacaoListNewPlanomitigacaoToAttach : planomitigacaoListNew) {
                planomitigacaoListNewPlanomitigacaoToAttach = em.getReference(planomitigacaoListNewPlanomitigacaoToAttach.getClass(), planomitigacaoListNewPlanomitigacaoToAttach.getIdPlanoMitigacao());
                attachedPlanomitigacaoListNew.add(planomitigacaoListNewPlanomitigacaoToAttach);
            }
            planomitigacaoListNew = attachedPlanomitigacaoListNew;
            risco.setPlanomitigacaoList(planomitigacaoListNew);
            List<Historicorisco> attachedHistoricoriscoListNew = new ArrayList<Historicorisco>();
            for (Historicorisco historicoriscoListNewHistoricoriscoToAttach : historicoriscoListNew) {
                historicoriscoListNewHistoricoriscoToAttach = em.getReference(historicoriscoListNewHistoricoriscoToAttach.getClass(), historicoriscoListNewHistoricoriscoToAttach.getIdHistoricoRisco());
                attachedHistoricoriscoListNew.add(historicoriscoListNewHistoricoriscoToAttach);
            }
            historicoriscoListNew = attachedHistoricoriscoListNew;
            risco.setHistoricoriscoList(historicoriscoListNew);
            List<Subcondicao> attachedSubcondicaoListNew = new ArrayList<Subcondicao>();
            for (Subcondicao subcondicaoListNewSubcondicaoToAttach : subcondicaoListNew) {
                subcondicaoListNewSubcondicaoToAttach = em.getReference(subcondicaoListNewSubcondicaoToAttach.getClass(), subcondicaoListNewSubcondicaoToAttach.getIdSubcondicao());
                attachedSubcondicaoListNew.add(subcondicaoListNewSubcondicaoToAttach);
            }
            subcondicaoListNew = attachedSubcondicaoListNew;
            risco.setSubcondicaoList(subcondicaoListNew);
            risco = em.merge(risco);
            if (contemOld != null && !contemOld.equals(contemNew)) {
                contemOld.getRiscoList().remove(risco);
                contemOld = em.merge(contemOld);
            }
            if (contemNew != null && !contemNew.equals(contemOld)) {
                contemNew.getRiscoList().add(risco);
                contemNew = em.merge(contemNew);
            }
            for (Risco riscoList2OldRisco : riscoList2Old) {
                if (!riscoList2New.contains(riscoList2OldRisco)) {
                    riscoList2OldRisco.getRiscoList2().remove(risco);
                    riscoList2OldRisco = em.merge(riscoList2OldRisco);
                }
            }
            for (Risco riscoList2NewRisco : riscoList2New) {
                if (!riscoList2Old.contains(riscoList2NewRisco)) {
                    riscoList2NewRisco.getRiscoList2().add(risco);
                    riscoList2NewRisco = em.merge(riscoList2NewRisco);
                }
            }
            for (Risco riscoList3OldRisco : riscoList3Old) {
                if (!riscoList3New.contains(riscoList3OldRisco)) {
                    riscoList3OldRisco.getRiscoList2().remove(risco);
                    riscoList3OldRisco = em.merge(riscoList3OldRisco);
                }
            }
            for (Risco riscoList3NewRisco : riscoList3New) {
                if (!riscoList3Old.contains(riscoList3NewRisco)) {
                    riscoList3NewRisco.getRiscoList2().add(risco);
                    riscoList3NewRisco = em.merge(riscoList3NewRisco);
                }
            }
            for (Planocontingencia planocontingenciaListNewPlanocontingencia : planocontingenciaListNew) {
                if (!planocontingenciaListOld.contains(planocontingenciaListNewPlanocontingencia)) {
                    Risco oldIdRiscoOfPlanocontingenciaListNewPlanocontingencia = planocontingenciaListNewPlanocontingencia.getIdRisco();
                    planocontingenciaListNewPlanocontingencia.setIdRisco(risco);
                    planocontingenciaListNewPlanocontingencia = em.merge(planocontingenciaListNewPlanocontingencia);
                    if (oldIdRiscoOfPlanocontingenciaListNewPlanocontingencia != null && !oldIdRiscoOfPlanocontingenciaListNewPlanocontingencia.equals(risco)) {
                        oldIdRiscoOfPlanocontingenciaListNewPlanocontingencia.getPlanocontingenciaList().remove(planocontingenciaListNewPlanocontingencia);
                        oldIdRiscoOfPlanocontingenciaListNewPlanocontingencia = em.merge(oldIdRiscoOfPlanocontingenciaListNewPlanocontingencia);
                    }
                }
            }
            for (Planomitigacao planomitigacaoListNewPlanomitigacao : planomitigacaoListNew) {
                if (!planomitigacaoListOld.contains(planomitigacaoListNewPlanomitigacao)) {
                    Risco oldIdRiscoOfPlanomitigacaoListNewPlanomitigacao = planomitigacaoListNewPlanomitigacao.getIdRisco();
                    planomitigacaoListNewPlanomitigacao.setIdRisco(risco);
                    planomitigacaoListNewPlanomitigacao = em.merge(planomitigacaoListNewPlanomitigacao);
                    if (oldIdRiscoOfPlanomitigacaoListNewPlanomitigacao != null && !oldIdRiscoOfPlanomitigacaoListNewPlanomitigacao.equals(risco)) {
                        oldIdRiscoOfPlanomitigacaoListNewPlanomitigacao.getPlanomitigacaoList().remove(planomitigacaoListNewPlanomitigacao);
                        oldIdRiscoOfPlanomitigacaoListNewPlanomitigacao = em.merge(oldIdRiscoOfPlanomitigacaoListNewPlanomitigacao);
                    }
                }
            }
            for (Historicorisco historicoriscoListNewHistoricorisco : historicoriscoListNew) {
                if (!historicoriscoListOld.contains(historicoriscoListNewHistoricorisco)) {
                    Risco oldIdRiscoOfHistoricoriscoListNewHistoricorisco = historicoriscoListNewHistoricorisco.getIdRisco();
                    historicoriscoListNewHistoricorisco.setIdRisco(risco);
                    historicoriscoListNewHistoricorisco = em.merge(historicoriscoListNewHistoricorisco);
                    if (oldIdRiscoOfHistoricoriscoListNewHistoricorisco != null && !oldIdRiscoOfHistoricoriscoListNewHistoricorisco.equals(risco)) {
                        oldIdRiscoOfHistoricoriscoListNewHistoricorisco.getHistoricoriscoList().remove(historicoriscoListNewHistoricorisco);
                        oldIdRiscoOfHistoricoriscoListNewHistoricorisco = em.merge(oldIdRiscoOfHistoricoriscoListNewHistoricorisco);
                    }
                }
            }
            for (Subcondicao subcondicaoListNewSubcondicao : subcondicaoListNew) {
                if (!subcondicaoListOld.contains(subcondicaoListNewSubcondicao)) {
                    Risco oldIdRiscoOfSubcondicaoListNewSubcondicao = subcondicaoListNewSubcondicao.getIdRisco();
                    subcondicaoListNewSubcondicao.setIdRisco(risco);
                    subcondicaoListNewSubcondicao = em.merge(subcondicaoListNewSubcondicao);
                    if (oldIdRiscoOfSubcondicaoListNewSubcondicao != null && !oldIdRiscoOfSubcondicaoListNewSubcondicao.equals(risco)) {
                        oldIdRiscoOfSubcondicaoListNewSubcondicao.getSubcondicaoList().remove(subcondicaoListNewSubcondicao);
                        oldIdRiscoOfSubcondicaoListNewSubcondicao = em.merge(oldIdRiscoOfSubcondicaoListNewSubcondicao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = risco.getIdRisco();
                if (findRisco(id) == null) {
                    throw new NonexistentEntityException("The risco with id " + id + " no longer exists.");
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
            Risco risco;
            try {
                risco = em.getReference(Risco.class, id);
                risco.getIdRisco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The risco with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Planocontingencia> planocontingenciaListOrphanCheck = risco.getPlanocontingenciaList();
            for (Planocontingencia planocontingenciaListOrphanCheckPlanocontingencia : planocontingenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Risco (" + risco + ") cannot be destroyed since the Planocontingencia " + planocontingenciaListOrphanCheckPlanocontingencia + " in its planocontingenciaList field has a non-nullable idRisco field.");
            }
            List<Planomitigacao> planomitigacaoListOrphanCheck = risco.getPlanomitigacaoList();
            for (Planomitigacao planomitigacaoListOrphanCheckPlanomitigacao : planomitigacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Risco (" + risco + ") cannot be destroyed since the Planomitigacao " + planomitigacaoListOrphanCheckPlanomitigacao + " in its planomitigacaoList field has a non-nullable idRisco field.");
            }
            List<Historicorisco> historicoriscoListOrphanCheck = risco.getHistoricoriscoList();
            for (Historicorisco historicoriscoListOrphanCheckHistoricorisco : historicoriscoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Risco (" + risco + ") cannot be destroyed since the Historicorisco " + historicoriscoListOrphanCheckHistoricorisco + " in its historicoriscoList field has a non-nullable idRisco field.");
            }
            List<Subcondicao> subcondicaoListOrphanCheck = risco.getSubcondicaoList();
            for (Subcondicao subcondicaoListOrphanCheckSubcondicao : subcondicaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Risco (" + risco + ") cannot be destroyed since the Subcondicao " + subcondicaoListOrphanCheckSubcondicao + " in its subcondicaoList field has a non-nullable idRisco field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Contem contem = risco.getContem();
            if (contem != null) {
                contem.getRiscoList().remove(risco);
                contem = em.merge(contem);
            }
            List<Risco> riscoList2 = risco.getRiscoList2();
            for (Risco riscoList2Risco : riscoList2) {
                riscoList2Risco.getRiscoList2().remove(risco);
                riscoList2Risco = em.merge(riscoList2Risco);
            }
            List<Risco> riscoList3 = risco.getRiscoList3();
            for (Risco riscoList3Risco : riscoList3) {
                riscoList3Risco.getRiscoList2().remove(risco);
                riscoList3Risco = em.merge(riscoList3Risco);
            }
            em.remove(risco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Risco> findRiscoEntities() {
        return findRiscoEntities(true, -1, -1);
    }

    public List<Risco> findRiscoEntities(int maxResults, int firstResult) {
        return findRiscoEntities(false, maxResults, firstResult);
    }

    private List<Risco> findRiscoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Risco.class));
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

    public Risco findRisco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Risco.class, id);
        } finally {
            em.close();
        }
    }

    public int getRiscoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Risco> rt = cq.from(Risco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
       public List<Risco> findRiscosPOrdemGrauDeEsposicao(Projeto projetoSelecionado) {
        List<Risco> riscos = null;
        EntityManager em = getEntityManager();
        try {
            riscos =  em.createNamedQuery("Risco.findAllPOrdemGrauDeEsposicao")
                    .setParameter("idProjeto", projetoSelecionado)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findContemByIdCategoriaDeRisco da classe ContemJpaController");
            e.printStackTrace();
        }
        return riscos;
    }
       
       public List<Risco> findRiscosByIdProjeto(Projeto idProjeto){
        
        List<Risco> listaRiscos = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaRiscos = em.createNamedQuery("Risco.findRiscosByIdProjeto")
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findRiscoByStatus da classe RiscoJpaController");
            e.printStackTrace();
        }
        
        return listaRiscos;
        
    }
       
       public List<Risco> findRiscosPOrdemGrauDeEsposicaoAndStatus(Projeto idProjeto, String statusRisco){
        
        List<Risco> listaRiscos = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaRiscos = em.createNamedQuery("Risco.findAllPOrdemGrauDeEsposicaoAndStatus")
                    .setParameter("idProjeto", idProjeto)
                    .setParameter("statusRisco", statusRisco)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findRiscosPOrdemGrauDeEsposicaoAndStatus da classe RiscoJpaController");
            e.printStackTrace();
        }
        
        return listaRiscos;
        
    }
}
