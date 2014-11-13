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
import model.Organizacao;
import model.Avaliacaoprojeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import model.Contem;
import model.Pontodecontrole;
import model.Aviso;
import model.Marcodoprojeto;
import model.Projeto;

/**
 *
 * @author H
 */
public class ProjetoJpaController implements Serializable {

    public ProjetoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ProjetoJpaController() throws Exception {

        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() throws Exception {
        return emf.createEntityManager();
    }

    public void create(Projeto projeto) throws Exception {
        if (projeto.getAvaliacaoprojetoList() == null) {
            projeto.setAvaliacaoprojetoList(new ArrayList<Avaliacaoprojeto>());
        }
        if (projeto.getContemList() == null) {
            projeto.setContemList(new ArrayList<Contem>());
        }
        if (projeto.getPontodecontroleList() == null) {
            projeto.setPontodecontroleList(new ArrayList<Pontodecontrole>());
        }
        if (projeto.getAvisoList() == null) {
            projeto.setAvisoList(new ArrayList<Aviso>());
        }
        if (projeto.getMarcodoprojetoList() == null) {
            projeto.setMarcodoprojetoList(new ArrayList<Marcodoprojeto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao idOrganizacao = projeto.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao = em.getReference(idOrganizacao.getClass(), idOrganizacao.getIdOrganizacao());
                projeto.setIdOrganizacao(idOrganizacao);
            }
            List<Avaliacaoprojeto> attachedAvaliacaoprojetoList = new ArrayList<Avaliacaoprojeto>();
            for (Avaliacaoprojeto avaliacaoprojetoListAvaliacaoprojetoToAttach : projeto.getAvaliacaoprojetoList()) {
                avaliacaoprojetoListAvaliacaoprojetoToAttach = em.getReference(avaliacaoprojetoListAvaliacaoprojetoToAttach.getClass(), avaliacaoprojetoListAvaliacaoprojetoToAttach.getIdAvaliacao());
                attachedAvaliacaoprojetoList.add(avaliacaoprojetoListAvaliacaoprojetoToAttach);
            }
            projeto.setAvaliacaoprojetoList(attachedAvaliacaoprojetoList);
            List<Contem> attachedContemList = new ArrayList<Contem>();
            for (Contem contemListContemToAttach : projeto.getContemList()) {
                contemListContemToAttach = em.getReference(contemListContemToAttach.getClass(), contemListContemToAttach.getContemPK());
                attachedContemList.add(contemListContemToAttach);
            }
            projeto.setContemList(attachedContemList);
            List<Pontodecontrole> attachedPontodecontroleList = new ArrayList<Pontodecontrole>();
            for (Pontodecontrole pontodecontroleListPontodecontroleToAttach : projeto.getPontodecontroleList()) {
                pontodecontroleListPontodecontroleToAttach = em.getReference(pontodecontroleListPontodecontroleToAttach.getClass(), pontodecontroleListPontodecontroleToAttach.getIdPontoDeControle());
                attachedPontodecontroleList.add(pontodecontroleListPontodecontroleToAttach);
            }
            projeto.setPontodecontroleList(attachedPontodecontroleList);
            List<Aviso> attachedAvisoList = new ArrayList<Aviso>();
            for (Aviso avisoListAvisoToAttach : projeto.getAvisoList()) {
                avisoListAvisoToAttach = em.getReference(avisoListAvisoToAttach.getClass(), avisoListAvisoToAttach.getIdAviso());
                attachedAvisoList.add(avisoListAvisoToAttach);
            }
            projeto.setAvisoList(attachedAvisoList);
            List<Marcodoprojeto> attachedMarcodoprojetoList = new ArrayList<Marcodoprojeto>();
            for (Marcodoprojeto marcodoprojetoListMarcodoprojetoToAttach : projeto.getMarcodoprojetoList()) {
                marcodoprojetoListMarcodoprojetoToAttach = em.getReference(marcodoprojetoListMarcodoprojetoToAttach.getClass(), marcodoprojetoListMarcodoprojetoToAttach.getIdMarcoDoProjeto());
                attachedMarcodoprojetoList.add(marcodoprojetoListMarcodoprojetoToAttach);
            }
            projeto.setMarcodoprojetoList(attachedMarcodoprojetoList);
            em.persist(projeto);
            if (idOrganizacao != null) {
                idOrganizacao.getProjetoList().add(projeto);
                idOrganizacao = em.merge(idOrganizacao);
            }
            for (Avaliacaoprojeto avaliacaoprojetoListAvaliacaoprojeto : projeto.getAvaliacaoprojetoList()) {
                Projeto oldIdProjetoOfAvaliacaoprojetoListAvaliacaoprojeto = avaliacaoprojetoListAvaliacaoprojeto.getIdProjeto();
                avaliacaoprojetoListAvaliacaoprojeto.setIdProjeto(projeto);
                avaliacaoprojetoListAvaliacaoprojeto = em.merge(avaliacaoprojetoListAvaliacaoprojeto);
                if (oldIdProjetoOfAvaliacaoprojetoListAvaliacaoprojeto != null) {
                    oldIdProjetoOfAvaliacaoprojetoListAvaliacaoprojeto.getAvaliacaoprojetoList().remove(avaliacaoprojetoListAvaliacaoprojeto);
                    oldIdProjetoOfAvaliacaoprojetoListAvaliacaoprojeto = em.merge(oldIdProjetoOfAvaliacaoprojetoListAvaliacaoprojeto);
                }
            }
            for (Contem contemListContem : projeto.getContemList()) {
                Projeto oldProjetoOfContemListContem = contemListContem.getProjeto();
                contemListContem.setProjeto(projeto);
                contemListContem = em.merge(contemListContem);
                if (oldProjetoOfContemListContem != null) {
                    oldProjetoOfContemListContem.getContemList().remove(contemListContem);
                    oldProjetoOfContemListContem = em.merge(oldProjetoOfContemListContem);
                }
            }
            for (Pontodecontrole pontodecontroleListPontodecontrole : projeto.getPontodecontroleList()) {
                Projeto oldIdProjetoOfPontodecontroleListPontodecontrole = pontodecontroleListPontodecontrole.getIdProjeto();
                pontodecontroleListPontodecontrole.setIdProjeto(projeto);
                pontodecontroleListPontodecontrole = em.merge(pontodecontroleListPontodecontrole);
                if (oldIdProjetoOfPontodecontroleListPontodecontrole != null) {
                    oldIdProjetoOfPontodecontroleListPontodecontrole.getPontodecontroleList().remove(pontodecontroleListPontodecontrole);
                    oldIdProjetoOfPontodecontroleListPontodecontrole = em.merge(oldIdProjetoOfPontodecontroleListPontodecontrole);
                }
            }
            for (Aviso avisoListAviso : projeto.getAvisoList()) {
                Projeto oldIdProjetoOfAvisoListAviso = avisoListAviso.getIdProjeto();
                avisoListAviso.setIdProjeto(projeto);
                avisoListAviso = em.merge(avisoListAviso);
                if (oldIdProjetoOfAvisoListAviso != null) {
                    oldIdProjetoOfAvisoListAviso.getAvisoList().remove(avisoListAviso);
                    oldIdProjetoOfAvisoListAviso = em.merge(oldIdProjetoOfAvisoListAviso);
                }
            }
            for (Marcodoprojeto marcodoprojetoListMarcodoprojeto : projeto.getMarcodoprojetoList()) {
                Projeto oldIdProjetoOfMarcodoprojetoListMarcodoprojeto = marcodoprojetoListMarcodoprojeto.getIdProjeto();
                marcodoprojetoListMarcodoprojeto.setIdProjeto(projeto);
                marcodoprojetoListMarcodoprojeto = em.merge(marcodoprojetoListMarcodoprojeto);
                if (oldIdProjetoOfMarcodoprojetoListMarcodoprojeto != null) {
                    oldIdProjetoOfMarcodoprojetoListMarcodoprojeto.getMarcodoprojetoList().remove(marcodoprojetoListMarcodoprojeto);
                    oldIdProjetoOfMarcodoprojetoListMarcodoprojeto = em.merge(oldIdProjetoOfMarcodoprojetoListMarcodoprojeto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Projeto projeto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto persistentProjeto = em.find(Projeto.class, projeto.getIdProjeto());
            Organizacao idOrganizacaoOld = persistentProjeto.getIdOrganizacao();
            Organizacao idOrganizacaoNew = projeto.getIdOrganizacao();
            List<Avaliacaoprojeto> avaliacaoprojetoListOld = persistentProjeto.getAvaliacaoprojetoList();
            List<Avaliacaoprojeto> avaliacaoprojetoListNew = projeto.getAvaliacaoprojetoList();
            List<Contem> contemListOld = persistentProjeto.getContemList();
            List<Contem> contemListNew = projeto.getContemList();
            List<Pontodecontrole> pontodecontroleListOld = persistentProjeto.getPontodecontroleList();
            List<Pontodecontrole> pontodecontroleListNew = projeto.getPontodecontroleList();
            List<Aviso> avisoListOld = persistentProjeto.getAvisoList();
            List<Aviso> avisoListNew = projeto.getAvisoList();
            List<Marcodoprojeto> marcodoprojetoListOld = persistentProjeto.getMarcodoprojetoList();
            List<Marcodoprojeto> marcodoprojetoListNew = projeto.getMarcodoprojetoList();
            List<String> illegalOrphanMessages = null;
            for (Avaliacaoprojeto avaliacaoprojetoListOldAvaliacaoprojeto : avaliacaoprojetoListOld) {
                if (!avaliacaoprojetoListNew.contains(avaliacaoprojetoListOldAvaliacaoprojeto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliacaoprojeto " + avaliacaoprojetoListOldAvaliacaoprojeto + " since its idProjeto field is not nullable.");
                }
            }
            for (Contem contemListOldContem : contemListOld) {
                if (!contemListNew.contains(contemListOldContem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contem " + contemListOldContem + " since its projeto field is not nullable.");
                }
            }
            for (Pontodecontrole pontodecontroleListOldPontodecontrole : pontodecontroleListOld) {
                if (!pontodecontroleListNew.contains(pontodecontroleListOldPontodecontrole)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pontodecontrole " + pontodecontroleListOldPontodecontrole + " since its idProjeto field is not nullable.");
                }
            }
            for (Aviso avisoListOldAviso : avisoListOld) {
                if (!avisoListNew.contains(avisoListOldAviso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Aviso " + avisoListOldAviso + " since its idProjeto field is not nullable.");
                }
            }
            for (Marcodoprojeto marcodoprojetoListOldMarcodoprojeto : marcodoprojetoListOld) {
                if (!marcodoprojetoListNew.contains(marcodoprojetoListOldMarcodoprojeto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Marcodoprojeto " + marcodoprojetoListOldMarcodoprojeto + " since its idProjeto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idOrganizacaoNew != null) {
                idOrganizacaoNew = em.getReference(idOrganizacaoNew.getClass(), idOrganizacaoNew.getIdOrganizacao());
                projeto.setIdOrganizacao(idOrganizacaoNew);
            }
            List<Avaliacaoprojeto> attachedAvaliacaoprojetoListNew = new ArrayList<Avaliacaoprojeto>();
            for (Avaliacaoprojeto avaliacaoprojetoListNewAvaliacaoprojetoToAttach : avaliacaoprojetoListNew) {
                avaliacaoprojetoListNewAvaliacaoprojetoToAttach = em.getReference(avaliacaoprojetoListNewAvaliacaoprojetoToAttach.getClass(), avaliacaoprojetoListNewAvaliacaoprojetoToAttach.getIdAvaliacao());
                attachedAvaliacaoprojetoListNew.add(avaliacaoprojetoListNewAvaliacaoprojetoToAttach);
            }
            avaliacaoprojetoListNew = attachedAvaliacaoprojetoListNew;
            projeto.setAvaliacaoprojetoList(avaliacaoprojetoListNew);
            List<Contem> attachedContemListNew = new ArrayList<Contem>();
            for (Contem contemListNewContemToAttach : contemListNew) {
                contemListNewContemToAttach = em.getReference(contemListNewContemToAttach.getClass(), contemListNewContemToAttach.getContemPK());
                attachedContemListNew.add(contemListNewContemToAttach);
            }
            contemListNew = attachedContemListNew;
            projeto.setContemList(contemListNew);
            List<Pontodecontrole> attachedPontodecontroleListNew = new ArrayList<Pontodecontrole>();
            for (Pontodecontrole pontodecontroleListNewPontodecontroleToAttach : pontodecontroleListNew) {
                pontodecontroleListNewPontodecontroleToAttach = em.getReference(pontodecontroleListNewPontodecontroleToAttach.getClass(), pontodecontroleListNewPontodecontroleToAttach.getIdPontoDeControle());
                attachedPontodecontroleListNew.add(pontodecontroleListNewPontodecontroleToAttach);
            }
            pontodecontroleListNew = attachedPontodecontroleListNew;
            projeto.setPontodecontroleList(pontodecontroleListNew);
            List<Aviso> attachedAvisoListNew = new ArrayList<Aviso>();
            for (Aviso avisoListNewAvisoToAttach : avisoListNew) {
                avisoListNewAvisoToAttach = em.getReference(avisoListNewAvisoToAttach.getClass(), avisoListNewAvisoToAttach.getIdAviso());
                attachedAvisoListNew.add(avisoListNewAvisoToAttach);
            }
            avisoListNew = attachedAvisoListNew;
            projeto.setAvisoList(avisoListNew);
            List<Marcodoprojeto> attachedMarcodoprojetoListNew = new ArrayList<Marcodoprojeto>();
            for (Marcodoprojeto marcodoprojetoListNewMarcodoprojetoToAttach : marcodoprojetoListNew) {
                marcodoprojetoListNewMarcodoprojetoToAttach = em.getReference(marcodoprojetoListNewMarcodoprojetoToAttach.getClass(), marcodoprojetoListNewMarcodoprojetoToAttach.getIdMarcoDoProjeto());
                attachedMarcodoprojetoListNew.add(marcodoprojetoListNewMarcodoprojetoToAttach);
            }
            marcodoprojetoListNew = attachedMarcodoprojetoListNew;
            projeto.setMarcodoprojetoList(marcodoprojetoListNew);
            projeto = em.merge(projeto);
            if (idOrganizacaoOld != null && !idOrganizacaoOld.equals(idOrganizacaoNew)) {
                idOrganizacaoOld.getProjetoList().remove(projeto);
                idOrganizacaoOld = em.merge(idOrganizacaoOld);
            }
            if (idOrganizacaoNew != null && !idOrganizacaoNew.equals(idOrganizacaoOld)) {
                idOrganizacaoNew.getProjetoList().add(projeto);
                idOrganizacaoNew = em.merge(idOrganizacaoNew);
            }
            for (Avaliacaoprojeto avaliacaoprojetoListNewAvaliacaoprojeto : avaliacaoprojetoListNew) {
                if (!avaliacaoprojetoListOld.contains(avaliacaoprojetoListNewAvaliacaoprojeto)) {
                    Projeto oldIdProjetoOfAvaliacaoprojetoListNewAvaliacaoprojeto = avaliacaoprojetoListNewAvaliacaoprojeto.getIdProjeto();
                    avaliacaoprojetoListNewAvaliacaoprojeto.setIdProjeto(projeto);
                    avaliacaoprojetoListNewAvaliacaoprojeto = em.merge(avaliacaoprojetoListNewAvaliacaoprojeto);
                    if (oldIdProjetoOfAvaliacaoprojetoListNewAvaliacaoprojeto != null && !oldIdProjetoOfAvaliacaoprojetoListNewAvaliacaoprojeto.equals(projeto)) {
                        oldIdProjetoOfAvaliacaoprojetoListNewAvaliacaoprojeto.getAvaliacaoprojetoList().remove(avaliacaoprojetoListNewAvaliacaoprojeto);
                        oldIdProjetoOfAvaliacaoprojetoListNewAvaliacaoprojeto = em.merge(oldIdProjetoOfAvaliacaoprojetoListNewAvaliacaoprojeto);
                    }
                }
            }
            for (Contem contemListNewContem : contemListNew) {
                if (!contemListOld.contains(contemListNewContem)) {
                    Projeto oldProjetoOfContemListNewContem = contemListNewContem.getProjeto();
                    contemListNewContem.setProjeto(projeto);
                    contemListNewContem = em.merge(contemListNewContem);
                    if (oldProjetoOfContemListNewContem != null && !oldProjetoOfContemListNewContem.equals(projeto)) {
                        oldProjetoOfContemListNewContem.getContemList().remove(contemListNewContem);
                        oldProjetoOfContemListNewContem = em.merge(oldProjetoOfContemListNewContem);
                    }
                }
            }
            for (Pontodecontrole pontodecontroleListNewPontodecontrole : pontodecontroleListNew) {
                if (!pontodecontroleListOld.contains(pontodecontroleListNewPontodecontrole)) {
                    Projeto oldIdProjetoOfPontodecontroleListNewPontodecontrole = pontodecontroleListNewPontodecontrole.getIdProjeto();
                    pontodecontroleListNewPontodecontrole.setIdProjeto(projeto);
                    pontodecontroleListNewPontodecontrole = em.merge(pontodecontroleListNewPontodecontrole);
                    if (oldIdProjetoOfPontodecontroleListNewPontodecontrole != null && !oldIdProjetoOfPontodecontroleListNewPontodecontrole.equals(projeto)) {
                        oldIdProjetoOfPontodecontroleListNewPontodecontrole.getPontodecontroleList().remove(pontodecontroleListNewPontodecontrole);
                        oldIdProjetoOfPontodecontroleListNewPontodecontrole = em.merge(oldIdProjetoOfPontodecontroleListNewPontodecontrole);
                    }
                }
            }
            for (Aviso avisoListNewAviso : avisoListNew) {
                if (!avisoListOld.contains(avisoListNewAviso)) {
                    Projeto oldIdProjetoOfAvisoListNewAviso = avisoListNewAviso.getIdProjeto();
                    avisoListNewAviso.setIdProjeto(projeto);
                    avisoListNewAviso = em.merge(avisoListNewAviso);
                    if (oldIdProjetoOfAvisoListNewAviso != null && !oldIdProjetoOfAvisoListNewAviso.equals(projeto)) {
                        oldIdProjetoOfAvisoListNewAviso.getAvisoList().remove(avisoListNewAviso);
                        oldIdProjetoOfAvisoListNewAviso = em.merge(oldIdProjetoOfAvisoListNewAviso);
                    }
                }
            }
            for (Marcodoprojeto marcodoprojetoListNewMarcodoprojeto : marcodoprojetoListNew) {
                if (!marcodoprojetoListOld.contains(marcodoprojetoListNewMarcodoprojeto)) {
                    Projeto oldIdProjetoOfMarcodoprojetoListNewMarcodoprojeto = marcodoprojetoListNewMarcodoprojeto.getIdProjeto();
                    marcodoprojetoListNewMarcodoprojeto.setIdProjeto(projeto);
                    marcodoprojetoListNewMarcodoprojeto = em.merge(marcodoprojetoListNewMarcodoprojeto);
                    if (oldIdProjetoOfMarcodoprojetoListNewMarcodoprojeto != null && !oldIdProjetoOfMarcodoprojetoListNewMarcodoprojeto.equals(projeto)) {
                        oldIdProjetoOfMarcodoprojetoListNewMarcodoprojeto.getMarcodoprojetoList().remove(marcodoprojetoListNewMarcodoprojeto);
                        oldIdProjetoOfMarcodoprojetoListNewMarcodoprojeto = em.merge(oldIdProjetoOfMarcodoprojetoListNewMarcodoprojeto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = projeto.getIdProjeto();
                if (findProjeto(id) == null) {
                    throw new NonexistentEntityException("The projeto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projeto projeto;
            try {
                projeto = em.getReference(Projeto.class, id);
                projeto.getIdProjeto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The projeto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Avaliacaoprojeto> avaliacaoprojetoListOrphanCheck = projeto.getAvaliacaoprojetoList();
            for (Avaliacaoprojeto avaliacaoprojetoListOrphanCheckAvaliacaoprojeto : avaliacaoprojetoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Avaliacaoprojeto " + avaliacaoprojetoListOrphanCheckAvaliacaoprojeto + " in its avaliacaoprojetoList field has a non-nullable idProjeto field.");
            }
            List<Contem> contemListOrphanCheck = projeto.getContemList();
            for (Contem contemListOrphanCheckContem : contemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Contem " + contemListOrphanCheckContem + " in its contemList field has a non-nullable projeto field.");
            }
            List<Pontodecontrole> pontodecontroleListOrphanCheck = projeto.getPontodecontroleList();
            for (Pontodecontrole pontodecontroleListOrphanCheckPontodecontrole : pontodecontroleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Pontodecontrole " + pontodecontroleListOrphanCheckPontodecontrole + " in its pontodecontroleList field has a non-nullable idProjeto field.");
            }
            List<Aviso> avisoListOrphanCheck = projeto.getAvisoList();
            for (Aviso avisoListOrphanCheckAviso : avisoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Aviso " + avisoListOrphanCheckAviso + " in its avisoList field has a non-nullable idProjeto field.");
            }
            List<Marcodoprojeto> marcodoprojetoListOrphanCheck = projeto.getMarcodoprojetoList();
            for (Marcodoprojeto marcodoprojetoListOrphanCheckMarcodoprojeto : marcodoprojetoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Projeto (" + projeto + ") cannot be destroyed since the Marcodoprojeto " + marcodoprojetoListOrphanCheckMarcodoprojeto + " in its marcodoprojetoList field has a non-nullable idProjeto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Organizacao idOrganizacao = projeto.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao.getProjetoList().remove(projeto);
                idOrganizacao = em.merge(idOrganizacao);
            }
            em.remove(projeto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Projeto> findProjetoEntities() throws Exception {
        return findProjetoEntities(true, -1, -1);
    }

    public List<Projeto> findProjetoEntities(int maxResults, int firstResult) throws Exception {
        return findProjetoEntities(false, maxResults, firstResult);
    }

    private List<Projeto> findProjetoEntities(boolean all, int maxResults, int firstResult) throws Exception {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Projeto.class));
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

    public Projeto findProjeto(Integer id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            return em.find(Projeto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjetoCount() throws Exception {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Projeto> rt = cq.from(Projeto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
