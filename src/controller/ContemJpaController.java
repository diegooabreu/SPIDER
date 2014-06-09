/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Categoriaderisco;
import model.Projeto;
import model.Risco;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Contem;
import model.ContemPK;

/**
 *
 * @author Mariano
 */
public class ContemJpaController implements Serializable {

    public ContemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ContemJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contem contem) throws PreexistingEntityException, Exception {
        if (contem.getContemPK() == null) {
            contem.setContemPK(new ContemPK());
        }
        if (contem.getRiscoList() == null) {
            contem.setRiscoList(new ArrayList<Risco>());
        }
        contem.getContemPK().setIdCategoriaDeRisco(contem.getCategoriaderisco().getIdCategoriaDeRisco());
        contem.getContemPK().setIdProjeto(contem.getProjeto().getIdProjeto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoriaderisco categoriaderisco = contem.getCategoriaderisco();
            if (categoriaderisco != null) {
                categoriaderisco = em.getReference(categoriaderisco.getClass(), categoriaderisco.getIdCategoriaDeRisco());
                contem.setCategoriaderisco(categoriaderisco);
            }
            Projeto projeto = contem.getProjeto();
            if (projeto != null) {
                projeto = em.getReference(projeto.getClass(), projeto.getIdProjeto());
                contem.setProjeto(projeto);
            }
            List<Risco> attachedRiscoList = new ArrayList<Risco>();
            for (Risco riscoListRiscoToAttach : contem.getRiscoList()) {
                riscoListRiscoToAttach = em.getReference(riscoListRiscoToAttach.getClass(), riscoListRiscoToAttach.getIdRisco());
                attachedRiscoList.add(riscoListRiscoToAttach);
            }
            contem.setRiscoList(attachedRiscoList);
            em.persist(contem);
            if (categoriaderisco != null) {
                categoriaderisco.getContemList().add(contem);
                categoriaderisco = em.merge(categoriaderisco);
            }
            if (projeto != null) {
                projeto.getContemList().add(contem);
                projeto = em.merge(projeto);
            }
            for (Risco riscoListRisco : contem.getRiscoList()) {
                Contem oldContemOfRiscoListRisco = riscoListRisco.getContem();
                riscoListRisco.setContem(contem);
                riscoListRisco = em.merge(riscoListRisco);
                if (oldContemOfRiscoListRisco != null) {
                    oldContemOfRiscoListRisco.getRiscoList().remove(riscoListRisco);
                    oldContemOfRiscoListRisco = em.merge(oldContemOfRiscoListRisco);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContem(contem.getContemPK()) != null) {
                throw new PreexistingEntityException("Contem " + contem + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contem contem) throws IllegalOrphanException, NonexistentEntityException, Exception {
        contem.getContemPK().setIdCategoriaDeRisco(contem.getCategoriaderisco().getIdCategoriaDeRisco());
        contem.getContemPK().setIdProjeto(contem.getProjeto().getIdProjeto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contem persistentContem = em.find(Contem.class, contem.getContemPK());
            Categoriaderisco categoriaderiscoOld = persistentContem.getCategoriaderisco();
            Categoriaderisco categoriaderiscoNew = contem.getCategoriaderisco();
            Projeto projetoOld = persistentContem.getProjeto();
            Projeto projetoNew = contem.getProjeto();
            List<Risco> riscoListOld = persistentContem.getRiscoList();
            List<Risco> riscoListNew = contem.getRiscoList();
            List<String> illegalOrphanMessages = null;
            for (Risco riscoListOldRisco : riscoListOld) {
                if (!riscoListNew.contains(riscoListOldRisco)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Risco " + riscoListOldRisco + " since its contem field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoriaderiscoNew != null) {
                categoriaderiscoNew = em.getReference(categoriaderiscoNew.getClass(), categoriaderiscoNew.getIdCategoriaDeRisco());
                contem.setCategoriaderisco(categoriaderiscoNew);
            }
            if (projetoNew != null) {
                projetoNew = em.getReference(projetoNew.getClass(), projetoNew.getIdProjeto());
                contem.setProjeto(projetoNew);
            }
            List<Risco> attachedRiscoListNew = new ArrayList<Risco>();
            for (Risco riscoListNewRiscoToAttach : riscoListNew) {
                riscoListNewRiscoToAttach = em.getReference(riscoListNewRiscoToAttach.getClass(), riscoListNewRiscoToAttach.getIdRisco());
                attachedRiscoListNew.add(riscoListNewRiscoToAttach);
            }
            riscoListNew = attachedRiscoListNew;
            contem.setRiscoList(riscoListNew);
            contem = em.merge(contem);
            if (categoriaderiscoOld != null && !categoriaderiscoOld.equals(categoriaderiscoNew)) {
                categoriaderiscoOld.getContemList().remove(contem);
                categoriaderiscoOld = em.merge(categoriaderiscoOld);
            }
            if (categoriaderiscoNew != null && !categoriaderiscoNew.equals(categoriaderiscoOld)) {
                categoriaderiscoNew.getContemList().add(contem);
                categoriaderiscoNew = em.merge(categoriaderiscoNew);
            }
            if (projetoOld != null && !projetoOld.equals(projetoNew)) {
                projetoOld.getContemList().remove(contem);
                projetoOld = em.merge(projetoOld);
            }
            if (projetoNew != null && !projetoNew.equals(projetoOld)) {
                projetoNew.getContemList().add(contem);
                projetoNew = em.merge(projetoNew);
            }
            for (Risco riscoListNewRisco : riscoListNew) {
                if (!riscoListOld.contains(riscoListNewRisco)) {
                    Contem oldContemOfRiscoListNewRisco = riscoListNewRisco.getContem();
                    riscoListNewRisco.setContem(contem);
                    riscoListNewRisco = em.merge(riscoListNewRisco);
                    if (oldContemOfRiscoListNewRisco != null && !oldContemOfRiscoListNewRisco.equals(contem)) {
                        oldContemOfRiscoListNewRisco.getRiscoList().remove(riscoListNewRisco);
                        oldContemOfRiscoListNewRisco = em.merge(oldContemOfRiscoListNewRisco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ContemPK id = contem.getContemPK();
                if (findContem(id) == null) {
                    throw new NonexistentEntityException("The contem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ContemPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contem contem;
            try {
                contem = em.getReference(Contem.class, id);
                contem.getContemPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contem with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Risco> riscoListOrphanCheck = contem.getRiscoList();
            for (Risco riscoListOrphanCheckRisco : riscoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contem (" + contem + ") cannot be destroyed since the Risco " + riscoListOrphanCheckRisco + " in its riscoList field has a non-nullable contem field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoriaderisco categoriaderisco = contem.getCategoriaderisco();
            if (categoriaderisco != null) {
                categoriaderisco.getContemList().remove(contem);
                categoriaderisco = em.merge(categoriaderisco);
            }
            Projeto projeto = contem.getProjeto();
            if (projeto != null) {
                projeto.getContemList().remove(contem);
                projeto = em.merge(projeto);
            }
            em.remove(contem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contem> findContemEntities() {
        return findContemEntities(true, -1, -1);
    }

    public List<Contem> findContemEntities(int maxResults, int firstResult) {
        return findContemEntities(false, maxResults, firstResult);
    }

    private List<Contem> findContemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contem.class));
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

    public Contem findContem(ContemPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contem.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Contem> findContemByIdCategoriaDeRisco(int idCategoria){
        List<Contem> contem = null;
        EntityManager em = getEntityManager();
        try {
            contem =  em.createNamedQuery("Contem.findByIdCategoriaDeRisco")
                    .setParameter("idCategoriaDeRisco", idCategoria)
                    .getResultList();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return contem;
    }

    public int getContemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contem> rt = cq.from(Contem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Contem> findContemByIdProjeto(int idProjeto){
        List<Contem> contem = null;
        EntityManager em = getEntityManager();
        try {
            contem =  em.createNamedQuery("Contem.findByIdProjeto")
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findContemByIdCategoriaDeRisco da classe ContemJpaController");
            e.printStackTrace();
        }
        return contem;
    }
    
    
    
}
