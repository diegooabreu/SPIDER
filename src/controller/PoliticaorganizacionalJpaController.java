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
import model.Organizacao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Politicaorganizacional;

/**
 *
 * @author Mariano
 */
public class PoliticaorganizacionalJpaController implements Serializable {

    public PoliticaorganizacionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PoliticaorganizacionalJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Politicaorganizacional politicaorganizacional) {
        if (politicaorganizacional.getOrganizacaoList() == null) {
            politicaorganizacional.setOrganizacaoList(new ArrayList<Organizacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Organizacao> attachedOrganizacaoList = new ArrayList<Organizacao>();
            for (Organizacao organizacaoListOrganizacaoToAttach : politicaorganizacional.getOrganizacaoList()) {
                organizacaoListOrganizacaoToAttach = em.getReference(organizacaoListOrganizacaoToAttach.getClass(), organizacaoListOrganizacaoToAttach.getIdOrganizacao());
                attachedOrganizacaoList.add(organizacaoListOrganizacaoToAttach);
            }
            politicaorganizacional.setOrganizacaoList(attachedOrganizacaoList);
            em.persist(politicaorganizacional);
            for (Organizacao organizacaoListOrganizacao : politicaorganizacional.getOrganizacaoList()) {
                Politicaorganizacional oldIdPoliticaOrganizacionalOfOrganizacaoListOrganizacao = organizacaoListOrganizacao.getIdPoliticaOrganizacional();
                organizacaoListOrganizacao.setIdPoliticaOrganizacional(politicaorganizacional);
                organizacaoListOrganizacao = em.merge(organizacaoListOrganizacao);
                if (oldIdPoliticaOrganizacionalOfOrganizacaoListOrganizacao != null) {
                    oldIdPoliticaOrganizacionalOfOrganizacaoListOrganizacao.getOrganizacaoList().remove(organizacaoListOrganizacao);
                    oldIdPoliticaOrganizacionalOfOrganizacaoListOrganizacao = em.merge(oldIdPoliticaOrganizacionalOfOrganizacaoListOrganizacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Politicaorganizacional politicaorganizacional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Politicaorganizacional persistentPoliticaorganizacional = em.find(Politicaorganizacional.class, politicaorganizacional.getIdPoliticaOrganizacional());
            List<Organizacao> organizacaoListOld = persistentPoliticaorganizacional.getOrganizacaoList();
            List<Organizacao> organizacaoListNew = politicaorganizacional.getOrganizacaoList();
            List<String> illegalOrphanMessages = null;
            for (Organizacao organizacaoListOldOrganizacao : organizacaoListOld) {
                if (!organizacaoListNew.contains(organizacaoListOldOrganizacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Organizacao " + organizacaoListOldOrganizacao + " since its idPoliticaOrganizacional field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Organizacao> attachedOrganizacaoListNew = new ArrayList<Organizacao>();
            for (Organizacao organizacaoListNewOrganizacaoToAttach : organizacaoListNew) {
                organizacaoListNewOrganizacaoToAttach = em.getReference(organizacaoListNewOrganizacaoToAttach.getClass(), organizacaoListNewOrganizacaoToAttach.getIdOrganizacao());
                attachedOrganizacaoListNew.add(organizacaoListNewOrganizacaoToAttach);
            }
            organizacaoListNew = attachedOrganizacaoListNew;
            politicaorganizacional.setOrganizacaoList(organizacaoListNew);
            politicaorganizacional = em.merge(politicaorganizacional);
            for (Organizacao organizacaoListNewOrganizacao : organizacaoListNew) {
                if (!organizacaoListOld.contains(organizacaoListNewOrganizacao)) {
                    Politicaorganizacional oldIdPoliticaOrganizacionalOfOrganizacaoListNewOrganizacao = organizacaoListNewOrganizacao.getIdPoliticaOrganizacional();
                    organizacaoListNewOrganizacao.setIdPoliticaOrganizacional(politicaorganizacional);
                    organizacaoListNewOrganizacao = em.merge(organizacaoListNewOrganizacao);
                    if (oldIdPoliticaOrganizacionalOfOrganizacaoListNewOrganizacao != null && !oldIdPoliticaOrganizacionalOfOrganizacaoListNewOrganizacao.equals(politicaorganizacional)) {
                        oldIdPoliticaOrganizacionalOfOrganizacaoListNewOrganizacao.getOrganizacaoList().remove(organizacaoListNewOrganizacao);
                        oldIdPoliticaOrganizacionalOfOrganizacaoListNewOrganizacao = em.merge(oldIdPoliticaOrganizacionalOfOrganizacaoListNewOrganizacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = politicaorganizacional.getIdPoliticaOrganizacional();
                if (findPoliticaorganizacional(id) == null) {
                    throw new NonexistentEntityException("The politicaorganizacional with id " + id + " no longer exists.");
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
            Politicaorganizacional politicaorganizacional;
            try {
                politicaorganizacional = em.getReference(Politicaorganizacional.class, id);
                politicaorganizacional.getIdPoliticaOrganizacional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The politicaorganizacional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Organizacao> organizacaoListOrphanCheck = politicaorganizacional.getOrganizacaoList();
            for (Organizacao organizacaoListOrphanCheckOrganizacao : organizacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Politicaorganizacional (" + politicaorganizacional + ") cannot be destroyed since the Organizacao " + organizacaoListOrphanCheckOrganizacao + " in its organizacaoList field has a non-nullable idPoliticaOrganizacional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(politicaorganizacional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Politicaorganizacional> findPoliticaorganizacionalEntities() {
        return findPoliticaorganizacionalEntities(true, -1, -1);
    }

    public List<Politicaorganizacional> findPoliticaorganizacionalEntities(int maxResults, int firstResult) {
        return findPoliticaorganizacionalEntities(false, maxResults, firstResult);
    }

    private List<Politicaorganizacional> findPoliticaorganizacionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Politicaorganizacional.class));
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

    public Politicaorganizacional findPoliticaorganizacional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Politicaorganizacional.class, id);
        } finally {
            em.close();
        }
    }

    public int getPoliticaorganizacionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Politicaorganizacional> rt = cq.from(Politicaorganizacional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
