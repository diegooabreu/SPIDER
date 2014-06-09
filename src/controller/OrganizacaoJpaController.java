/*
 * To change this template, choose Tools | Templates
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
import model.Politicaorganizacional;
import model.Projeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Categoriaderisco;
import model.Organizacao;

/**
 *
 * @author Mariano
 */
public class OrganizacaoJpaController implements Serializable {

    public OrganizacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public OrganizacaoJpaController() {
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Organizacao organizacao) {
        if (organizacao.getProjetoList() == null) {
            organizacao.setProjetoList(new ArrayList<Projeto>());
        }
        if (organizacao.getCategoriaderiscoList() == null) {
            organizacao.setCategoriaderiscoList(new ArrayList<Categoriaderisco>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Politicaorganizacional idPoliticaOrganizacional = organizacao.getIdPoliticaOrganizacional();
            if (idPoliticaOrganizacional != null) {
                idPoliticaOrganizacional = em.getReference(idPoliticaOrganizacional.getClass(), idPoliticaOrganizacional.getIdPoliticaOrganizacional());
                organizacao.setIdPoliticaOrganizacional(idPoliticaOrganizacional);
            }
            List<Projeto> attachedProjetoList = new ArrayList<Projeto>();
            for (Projeto projetoListProjetoToAttach : organizacao.getProjetoList()) {
                projetoListProjetoToAttach = em.getReference(projetoListProjetoToAttach.getClass(), projetoListProjetoToAttach.getIdProjeto());
                attachedProjetoList.add(projetoListProjetoToAttach);
            }
            organizacao.setProjetoList(attachedProjetoList);
            List<Categoriaderisco> attachedCategoriaderiscoList = new ArrayList<Categoriaderisco>();
            for (Categoriaderisco categoriaderiscoListCategoriaderiscoToAttach : organizacao.getCategoriaderiscoList()) {
                categoriaderiscoListCategoriaderiscoToAttach = em.getReference(categoriaderiscoListCategoriaderiscoToAttach.getClass(), categoriaderiscoListCategoriaderiscoToAttach.getIdCategoriaDeRisco());
                attachedCategoriaderiscoList.add(categoriaderiscoListCategoriaderiscoToAttach);
            }
            organizacao.setCategoriaderiscoList(attachedCategoriaderiscoList);
            em.persist(organizacao);
            if (idPoliticaOrganizacional != null) {
                idPoliticaOrganizacional.getOrganizacaoList().add(organizacao);
                idPoliticaOrganizacional = em.merge(idPoliticaOrganizacional);
            }
            for (Projeto projetoListProjeto : organizacao.getProjetoList()) {
                Organizacao oldIdOrganizacaoOfProjetoListProjeto = projetoListProjeto.getIdOrganizacao();
                projetoListProjeto.setIdOrganizacao(organizacao);
                projetoListProjeto = em.merge(projetoListProjeto);
                if (oldIdOrganizacaoOfProjetoListProjeto != null) {
                    oldIdOrganizacaoOfProjetoListProjeto.getProjetoList().remove(projetoListProjeto);
                    oldIdOrganizacaoOfProjetoListProjeto = em.merge(oldIdOrganizacaoOfProjetoListProjeto);
                }
            }
            for (Categoriaderisco categoriaderiscoListCategoriaderisco : organizacao.getCategoriaderiscoList()) {
                Organizacao oldIdOrganizacaoOfCategoriaderiscoListCategoriaderisco = categoriaderiscoListCategoriaderisco.getIdOrganizacao();
                categoriaderiscoListCategoriaderisco.setIdOrganizacao(organizacao);
                categoriaderiscoListCategoriaderisco = em.merge(categoriaderiscoListCategoriaderisco);
                if (oldIdOrganizacaoOfCategoriaderiscoListCategoriaderisco != null) {
                    oldIdOrganizacaoOfCategoriaderiscoListCategoriaderisco.getCategoriaderiscoList().remove(categoriaderiscoListCategoriaderisco);
                    oldIdOrganizacaoOfCategoriaderiscoListCategoriaderisco = em.merge(oldIdOrganizacaoOfCategoriaderiscoListCategoriaderisco);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Organizacao organizacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizacao persistentOrganizacao = em.find(Organizacao.class, organizacao.getIdOrganizacao());
            Politicaorganizacional idPoliticaOrganizacionalOld = persistentOrganizacao.getIdPoliticaOrganizacional();
            Politicaorganizacional idPoliticaOrganizacionalNew = organizacao.getIdPoliticaOrganizacional();
            List<Projeto> projetoListOld = persistentOrganizacao.getProjetoList();
            List<Projeto> projetoListNew = organizacao.getProjetoList();
            List<Categoriaderisco> categoriaderiscoListOld = persistentOrganizacao.getCategoriaderiscoList();
            List<Categoriaderisco> categoriaderiscoListNew = organizacao.getCategoriaderiscoList();
            List<String> illegalOrphanMessages = null;
            for (Projeto projetoListOldProjeto : projetoListOld) {
                if (!projetoListNew.contains(projetoListOldProjeto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Projeto " + projetoListOldProjeto + " since its idOrganizacao field is not nullable.");
                }
            }
            for (Categoriaderisco categoriaderiscoListOldCategoriaderisco : categoriaderiscoListOld) {
                if (!categoriaderiscoListNew.contains(categoriaderiscoListOldCategoriaderisco)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Categoriaderisco " + categoriaderiscoListOldCategoriaderisco + " since its idOrganizacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPoliticaOrganizacionalNew != null) {
                idPoliticaOrganizacionalNew = em.getReference(idPoliticaOrganizacionalNew.getClass(), idPoliticaOrganizacionalNew.getIdPoliticaOrganizacional());
                organizacao.setIdPoliticaOrganizacional(idPoliticaOrganizacionalNew);
            }
            List<Projeto> attachedProjetoListNew = new ArrayList<Projeto>();
            for (Projeto projetoListNewProjetoToAttach : projetoListNew) {
                projetoListNewProjetoToAttach = em.getReference(projetoListNewProjetoToAttach.getClass(), projetoListNewProjetoToAttach.getIdProjeto());
                attachedProjetoListNew.add(projetoListNewProjetoToAttach);
            }
            projetoListNew = attachedProjetoListNew;
            organizacao.setProjetoList(projetoListNew);
            List<Categoriaderisco> attachedCategoriaderiscoListNew = new ArrayList<Categoriaderisco>();
            for (Categoriaderisco categoriaderiscoListNewCategoriaderiscoToAttach : categoriaderiscoListNew) {
                categoriaderiscoListNewCategoriaderiscoToAttach = em.getReference(categoriaderiscoListNewCategoriaderiscoToAttach.getClass(), categoriaderiscoListNewCategoriaderiscoToAttach.getIdCategoriaDeRisco());
                attachedCategoriaderiscoListNew.add(categoriaderiscoListNewCategoriaderiscoToAttach);
            }
            categoriaderiscoListNew = attachedCategoriaderiscoListNew;
            organizacao.setCategoriaderiscoList(categoriaderiscoListNew);
            organizacao = em.merge(organizacao);
            if (idPoliticaOrganizacionalOld != null && !idPoliticaOrganizacionalOld.equals(idPoliticaOrganizacionalNew)) {
                idPoliticaOrganizacionalOld.getOrganizacaoList().remove(organizacao);
                idPoliticaOrganizacionalOld = em.merge(idPoliticaOrganizacionalOld);
            }
            if (idPoliticaOrganizacionalNew != null && !idPoliticaOrganizacionalNew.equals(idPoliticaOrganizacionalOld)) {
                idPoliticaOrganizacionalNew.getOrganizacaoList().add(organizacao);
                idPoliticaOrganizacionalNew = em.merge(idPoliticaOrganizacionalNew);
            }
            for (Projeto projetoListNewProjeto : projetoListNew) {
                if (!projetoListOld.contains(projetoListNewProjeto)) {
                    Organizacao oldIdOrganizacaoOfProjetoListNewProjeto = projetoListNewProjeto.getIdOrganizacao();
                    projetoListNewProjeto.setIdOrganizacao(organizacao);
                    projetoListNewProjeto = em.merge(projetoListNewProjeto);
                    if (oldIdOrganizacaoOfProjetoListNewProjeto != null && !oldIdOrganizacaoOfProjetoListNewProjeto.equals(organizacao)) {
                        oldIdOrganizacaoOfProjetoListNewProjeto.getProjetoList().remove(projetoListNewProjeto);
                        oldIdOrganizacaoOfProjetoListNewProjeto = em.merge(oldIdOrganizacaoOfProjetoListNewProjeto);
                    }
                }
            }
            for (Categoriaderisco categoriaderiscoListNewCategoriaderisco : categoriaderiscoListNew) {
                if (!categoriaderiscoListOld.contains(categoriaderiscoListNewCategoriaderisco)) {
                    Organizacao oldIdOrganizacaoOfCategoriaderiscoListNewCategoriaderisco = categoriaderiscoListNewCategoriaderisco.getIdOrganizacao();
                    categoriaderiscoListNewCategoriaderisco.setIdOrganizacao(organizacao);
                    categoriaderiscoListNewCategoriaderisco = em.merge(categoriaderiscoListNewCategoriaderisco);
                    if (oldIdOrganizacaoOfCategoriaderiscoListNewCategoriaderisco != null && !oldIdOrganizacaoOfCategoriaderiscoListNewCategoriaderisco.equals(organizacao)) {
                        oldIdOrganizacaoOfCategoriaderiscoListNewCategoriaderisco.getCategoriaderiscoList().remove(categoriaderiscoListNewCategoriaderisco);
                        oldIdOrganizacaoOfCategoriaderiscoListNewCategoriaderisco = em.merge(oldIdOrganizacaoOfCategoriaderiscoListNewCategoriaderisco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organizacao.getIdOrganizacao();
                if (findOrganizacao(id) == null) {
                    throw new NonexistentEntityException("The organizacao with id " + id + " no longer exists.");
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
            Organizacao organizacao;
            try {
                organizacao = em.getReference(Organizacao.class, id);
                organizacao.getIdOrganizacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Projeto> projetoListOrphanCheck = organizacao.getProjetoList();
            for (Projeto projetoListOrphanCheckProjeto : projetoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Projeto " + projetoListOrphanCheckProjeto + " in its projetoList field has a non-nullable idOrganizacao field.");
            }
            List<Categoriaderisco> categoriaderiscoListOrphanCheck = organizacao.getCategoriaderiscoList();
            for (Categoriaderisco categoriaderiscoListOrphanCheckCategoriaderisco : categoriaderiscoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizacao (" + organizacao + ") cannot be destroyed since the Categoriaderisco " + categoriaderiscoListOrphanCheckCategoriaderisco + " in its categoriaderiscoList field has a non-nullable idOrganizacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Politicaorganizacional idPoliticaOrganizacional = organizacao.getIdPoliticaOrganizacional();
            if (idPoliticaOrganizacional != null) {
                idPoliticaOrganizacional.getOrganizacaoList().remove(organizacao);
                idPoliticaOrganizacional = em.merge(idPoliticaOrganizacional);
            }
            em.remove(organizacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Organizacao> findOrganizacaoEntities() {
        return findOrganizacaoEntities(true, -1, -1);
    }

    public List<Organizacao> findOrganizacaoEntities(int maxResults, int firstResult) {
        return findOrganizacaoEntities(false, maxResults, firstResult);
    }

    private List<Organizacao> findOrganizacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Organizacao.class));
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

    public Organizacao findOrganizacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Organizacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Organizacao> rt = cq.from(Organizacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
