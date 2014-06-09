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
import model.Categoriaderisco;
import model.Organizacao;
import model.Contem;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Avaliacaocategoria;

/**
 *
 * @author Diego
 */
public class CategoriaderiscoJpaController implements Serializable {

    public CategoriaderiscoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
     public CategoriaderiscoJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoriaderisco categoriaderisco) {
        if (categoriaderisco.getContemList() == null) {
            categoriaderisco.setContemList(new ArrayList<Contem>());
        }
        if (categoriaderisco.getCategoriaderiscoList() == null) {
            categoriaderisco.setCategoriaderiscoList(new ArrayList<Categoriaderisco>());
        }
        if (categoriaderisco.getAvaliacaocategoriaList() == null) {
            categoriaderisco.setAvaliacaocategoriaList(new ArrayList<Avaliacaocategoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoriaderisco fkidCategoriaDeRisco = categoriaderisco.getFkidCategoriaDeRisco();
            if (fkidCategoriaDeRisco != null) {
                fkidCategoriaDeRisco = em.getReference(fkidCategoriaDeRisco.getClass(), fkidCategoriaDeRisco.getIdCategoriaDeRisco());
                categoriaderisco.setFkidCategoriaDeRisco(fkidCategoriaDeRisco);
            }
            Organizacao idOrganizacao = categoriaderisco.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao = em.getReference(idOrganizacao.getClass(), idOrganizacao.getIdOrganizacao());
                categoriaderisco.setIdOrganizacao(idOrganizacao);
            }
            List<Contem> attachedContemList = new ArrayList<Contem>();
            for (Contem contemListContemToAttach : categoriaderisco.getContemList()) {
                contemListContemToAttach = em.getReference(contemListContemToAttach.getClass(), contemListContemToAttach.getContemPK());
                attachedContemList.add(contemListContemToAttach);
            }
            categoriaderisco.setContemList(attachedContemList);
            List<Categoriaderisco> attachedCategoriaderiscoList = new ArrayList<Categoriaderisco>();
            for (Categoriaderisco categoriaderiscoListCategoriaderiscoToAttach : categoriaderisco.getCategoriaderiscoList()) {
                categoriaderiscoListCategoriaderiscoToAttach = em.getReference(categoriaderiscoListCategoriaderiscoToAttach.getClass(), categoriaderiscoListCategoriaderiscoToAttach.getIdCategoriaDeRisco());
                attachedCategoriaderiscoList.add(categoriaderiscoListCategoriaderiscoToAttach);
            }
            categoriaderisco.setCategoriaderiscoList(attachedCategoriaderiscoList);
            List<Avaliacaocategoria> attachedAvaliacaocategoriaList = new ArrayList<Avaliacaocategoria>();
            for (Avaliacaocategoria avaliacaocategoriaListAvaliacaocategoriaToAttach : categoriaderisco.getAvaliacaocategoriaList()) {
                avaliacaocategoriaListAvaliacaocategoriaToAttach = em.getReference(avaliacaocategoriaListAvaliacaocategoriaToAttach.getClass(), avaliacaocategoriaListAvaliacaocategoriaToAttach.getIdavaliacaoCategoria());
                attachedAvaliacaocategoriaList.add(avaliacaocategoriaListAvaliacaocategoriaToAttach);
            }
            categoriaderisco.setAvaliacaocategoriaList(attachedAvaliacaocategoriaList);
            em.persist(categoriaderisco);
            if (fkidCategoriaDeRisco != null) {
                fkidCategoriaDeRisco.getCategoriaderiscoList().add(categoriaderisco);
                fkidCategoriaDeRisco = em.merge(fkidCategoriaDeRisco);
            }
            if (idOrganizacao != null) {
                idOrganizacao.getCategoriaderiscoList().add(categoriaderisco);
                idOrganizacao = em.merge(idOrganizacao);
            }
            for (Contem contemListContem : categoriaderisco.getContemList()) {
                Categoriaderisco oldCategoriaderiscoOfContemListContem = contemListContem.getCategoriaderisco();
                contemListContem.setCategoriaderisco(categoriaderisco);
                contemListContem = em.merge(contemListContem);
                if (oldCategoriaderiscoOfContemListContem != null) {
                    oldCategoriaderiscoOfContemListContem.getContemList().remove(contemListContem);
                    oldCategoriaderiscoOfContemListContem = em.merge(oldCategoriaderiscoOfContemListContem);
                }
            }
            for (Categoriaderisco categoriaderiscoListCategoriaderisco : categoriaderisco.getCategoriaderiscoList()) {
                Categoriaderisco oldFkidCategoriaDeRiscoOfCategoriaderiscoListCategoriaderisco = categoriaderiscoListCategoriaderisco.getFkidCategoriaDeRisco();
                categoriaderiscoListCategoriaderisco.setFkidCategoriaDeRisco(categoriaderisco);
                categoriaderiscoListCategoriaderisco = em.merge(categoriaderiscoListCategoriaderisco);
                if (oldFkidCategoriaDeRiscoOfCategoriaderiscoListCategoriaderisco != null) {
                    oldFkidCategoriaDeRiscoOfCategoriaderiscoListCategoriaderisco.getCategoriaderiscoList().remove(categoriaderiscoListCategoriaderisco);
                    oldFkidCategoriaDeRiscoOfCategoriaderiscoListCategoriaderisco = em.merge(oldFkidCategoriaDeRiscoOfCategoriaderiscoListCategoriaderisco);
                }
            }
            for (Avaliacaocategoria avaliacaocategoriaListAvaliacaocategoria : categoriaderisco.getAvaliacaocategoriaList()) {
                Categoriaderisco oldIdCategoriaDeRiscoOfAvaliacaocategoriaListAvaliacaocategoria = avaliacaocategoriaListAvaliacaocategoria.getIdCategoriaDeRisco();
                avaliacaocategoriaListAvaliacaocategoria.setIdCategoriaDeRisco(categoriaderisco);
                avaliacaocategoriaListAvaliacaocategoria = em.merge(avaliacaocategoriaListAvaliacaocategoria);
                if (oldIdCategoriaDeRiscoOfAvaliacaocategoriaListAvaliacaocategoria != null) {
                    oldIdCategoriaDeRiscoOfAvaliacaocategoriaListAvaliacaocategoria.getAvaliacaocategoriaList().remove(avaliacaocategoriaListAvaliacaocategoria);
                    oldIdCategoriaDeRiscoOfAvaliacaocategoriaListAvaliacaocategoria = em.merge(oldIdCategoriaDeRiscoOfAvaliacaocategoriaListAvaliacaocategoria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoriaderisco categoriaderisco) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoriaderisco persistentCategoriaderisco = em.find(Categoriaderisco.class, categoriaderisco.getIdCategoriaDeRisco());
            Categoriaderisco fkidCategoriaDeRiscoOld = persistentCategoriaderisco.getFkidCategoriaDeRisco();
            Categoriaderisco fkidCategoriaDeRiscoNew = categoriaderisco.getFkidCategoriaDeRisco();
            Organizacao idOrganizacaoOld = persistentCategoriaderisco.getIdOrganizacao();
            Organizacao idOrganizacaoNew = categoriaderisco.getIdOrganizacao();
            List<Contem> contemListOld = persistentCategoriaderisco.getContemList();
            List<Contem> contemListNew = categoriaderisco.getContemList();
            List<Categoriaderisco> categoriaderiscoListOld = persistentCategoriaderisco.getCategoriaderiscoList();
            List<Categoriaderisco> categoriaderiscoListNew = categoriaderisco.getCategoriaderiscoList();
            List<Avaliacaocategoria> avaliacaocategoriaListOld = persistentCategoriaderisco.getAvaliacaocategoriaList();
            List<Avaliacaocategoria> avaliacaocategoriaListNew = categoriaderisco.getAvaliacaocategoriaList();
            List<String> illegalOrphanMessages = null;
            for (Contem contemListOldContem : contemListOld) {
                if (!contemListNew.contains(contemListOldContem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contem " + contemListOldContem + " since its categoriaderisco field is not nullable.");
                }
            }
            for (Avaliacaocategoria avaliacaocategoriaListOldAvaliacaocategoria : avaliacaocategoriaListOld) {
                if (!avaliacaocategoriaListNew.contains(avaliacaocategoriaListOldAvaliacaocategoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliacaocategoria " + avaliacaocategoriaListOldAvaliacaocategoria + " since its idCategoriaDeRisco field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkidCategoriaDeRiscoNew != null) {
                fkidCategoriaDeRiscoNew = em.getReference(fkidCategoriaDeRiscoNew.getClass(), fkidCategoriaDeRiscoNew.getIdCategoriaDeRisco());
                categoriaderisco.setFkidCategoriaDeRisco(fkidCategoriaDeRiscoNew);
            }
            if (idOrganizacaoNew != null) {
                idOrganizacaoNew = em.getReference(idOrganizacaoNew.getClass(), idOrganizacaoNew.getIdOrganizacao());
                categoriaderisco.setIdOrganizacao(idOrganizacaoNew);
            }
            List<Contem> attachedContemListNew = new ArrayList<Contem>();
            for (Contem contemListNewContemToAttach : contemListNew) {
                contemListNewContemToAttach = em.getReference(contemListNewContemToAttach.getClass(), contemListNewContemToAttach.getContemPK());
                attachedContemListNew.add(contemListNewContemToAttach);
            }
            contemListNew = attachedContemListNew;
            categoriaderisco.setContemList(contemListNew);
            List<Categoriaderisco> attachedCategoriaderiscoListNew = new ArrayList<Categoriaderisco>();
            for (Categoriaderisco categoriaderiscoListNewCategoriaderiscoToAttach : categoriaderiscoListNew) {
                categoriaderiscoListNewCategoriaderiscoToAttach = em.getReference(categoriaderiscoListNewCategoriaderiscoToAttach.getClass(), categoriaderiscoListNewCategoriaderiscoToAttach.getIdCategoriaDeRisco());
                attachedCategoriaderiscoListNew.add(categoriaderiscoListNewCategoriaderiscoToAttach);
            }
            categoriaderiscoListNew = attachedCategoriaderiscoListNew;
            categoriaderisco.setCategoriaderiscoList(categoriaderiscoListNew);
            List<Avaliacaocategoria> attachedAvaliacaocategoriaListNew = new ArrayList<Avaliacaocategoria>();
            for (Avaliacaocategoria avaliacaocategoriaListNewAvaliacaocategoriaToAttach : avaliacaocategoriaListNew) {
                avaliacaocategoriaListNewAvaliacaocategoriaToAttach = em.getReference(avaliacaocategoriaListNewAvaliacaocategoriaToAttach.getClass(), avaliacaocategoriaListNewAvaliacaocategoriaToAttach.getIdavaliacaoCategoria());
                attachedAvaliacaocategoriaListNew.add(avaliacaocategoriaListNewAvaliacaocategoriaToAttach);
            }
            avaliacaocategoriaListNew = attachedAvaliacaocategoriaListNew;
            categoriaderisco.setAvaliacaocategoriaList(avaliacaocategoriaListNew);
            categoriaderisco = em.merge(categoriaderisco);
            if (fkidCategoriaDeRiscoOld != null && !fkidCategoriaDeRiscoOld.equals(fkidCategoriaDeRiscoNew)) {
                fkidCategoriaDeRiscoOld.getCategoriaderiscoList().remove(categoriaderisco);
                fkidCategoriaDeRiscoOld = em.merge(fkidCategoriaDeRiscoOld);
            }
            if (fkidCategoriaDeRiscoNew != null && !fkidCategoriaDeRiscoNew.equals(fkidCategoriaDeRiscoOld)) {
                fkidCategoriaDeRiscoNew.getCategoriaderiscoList().add(categoriaderisco);
                fkidCategoriaDeRiscoNew = em.merge(fkidCategoriaDeRiscoNew);
            }
            if (idOrganizacaoOld != null && !idOrganizacaoOld.equals(idOrganizacaoNew)) {
                idOrganizacaoOld.getCategoriaderiscoList().remove(categoriaderisco);
                idOrganizacaoOld = em.merge(idOrganizacaoOld);
            }
            if (idOrganizacaoNew != null && !idOrganizacaoNew.equals(idOrganizacaoOld)) {
                idOrganizacaoNew.getCategoriaderiscoList().add(categoriaderisco);
                idOrganizacaoNew = em.merge(idOrganizacaoNew);
            }
            for (Contem contemListNewContem : contemListNew) {
                if (!contemListOld.contains(contemListNewContem)) {
                    Categoriaderisco oldCategoriaderiscoOfContemListNewContem = contemListNewContem.getCategoriaderisco();
                    contemListNewContem.setCategoriaderisco(categoriaderisco);
                    contemListNewContem = em.merge(contemListNewContem);
                    if (oldCategoriaderiscoOfContemListNewContem != null && !oldCategoriaderiscoOfContemListNewContem.equals(categoriaderisco)) {
                        oldCategoriaderiscoOfContemListNewContem.getContemList().remove(contemListNewContem);
                        oldCategoriaderiscoOfContemListNewContem = em.merge(oldCategoriaderiscoOfContemListNewContem);
                    }
                }
            }
            for (Categoriaderisco categoriaderiscoListOldCategoriaderisco : categoriaderiscoListOld) {
                if (!categoriaderiscoListNew.contains(categoriaderiscoListOldCategoriaderisco)) {
                    categoriaderiscoListOldCategoriaderisco.setFkidCategoriaDeRisco(null);
                    categoriaderiscoListOldCategoriaderisco = em.merge(categoriaderiscoListOldCategoriaderisco);
                }
            }
            for (Categoriaderisco categoriaderiscoListNewCategoriaderisco : categoriaderiscoListNew) {
                if (!categoriaderiscoListOld.contains(categoriaderiscoListNewCategoriaderisco)) {
                    Categoriaderisco oldFkidCategoriaDeRiscoOfCategoriaderiscoListNewCategoriaderisco = categoriaderiscoListNewCategoriaderisco.getFkidCategoriaDeRisco();
                    categoriaderiscoListNewCategoriaderisco.setFkidCategoriaDeRisco(categoriaderisco);
                    categoriaderiscoListNewCategoriaderisco = em.merge(categoriaderiscoListNewCategoriaderisco);
                    if (oldFkidCategoriaDeRiscoOfCategoriaderiscoListNewCategoriaderisco != null && !oldFkidCategoriaDeRiscoOfCategoriaderiscoListNewCategoriaderisco.equals(categoriaderisco)) {
                        oldFkidCategoriaDeRiscoOfCategoriaderiscoListNewCategoriaderisco.getCategoriaderiscoList().remove(categoriaderiscoListNewCategoriaderisco);
                        oldFkidCategoriaDeRiscoOfCategoriaderiscoListNewCategoriaderisco = em.merge(oldFkidCategoriaDeRiscoOfCategoriaderiscoListNewCategoriaderisco);
                    }
                }
            }
            for (Avaliacaocategoria avaliacaocategoriaListNewAvaliacaocategoria : avaliacaocategoriaListNew) {
                if (!avaliacaocategoriaListOld.contains(avaliacaocategoriaListNewAvaliacaocategoria)) {
                    Categoriaderisco oldIdCategoriaDeRiscoOfAvaliacaocategoriaListNewAvaliacaocategoria = avaliacaocategoriaListNewAvaliacaocategoria.getIdCategoriaDeRisco();
                    avaliacaocategoriaListNewAvaliacaocategoria.setIdCategoriaDeRisco(categoriaderisco);
                    avaliacaocategoriaListNewAvaliacaocategoria = em.merge(avaliacaocategoriaListNewAvaliacaocategoria);
                    if (oldIdCategoriaDeRiscoOfAvaliacaocategoriaListNewAvaliacaocategoria != null && !oldIdCategoriaDeRiscoOfAvaliacaocategoriaListNewAvaliacaocategoria.equals(categoriaderisco)) {
                        oldIdCategoriaDeRiscoOfAvaliacaocategoriaListNewAvaliacaocategoria.getAvaliacaocategoriaList().remove(avaliacaocategoriaListNewAvaliacaocategoria);
                        oldIdCategoriaDeRiscoOfAvaliacaocategoriaListNewAvaliacaocategoria = em.merge(oldIdCategoriaDeRiscoOfAvaliacaocategoriaListNewAvaliacaocategoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriaderisco.getIdCategoriaDeRisco();
                if (findCategoriaderisco(id) == null) {
                    throw new NonexistentEntityException("The categoriaderisco with id " + id + " no longer exists.");
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
            Categoriaderisco categoriaderisco;
            try {
                categoriaderisco = em.getReference(Categoriaderisco.class, id);
                categoriaderisco.getIdCategoriaDeRisco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaderisco with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Contem> contemListOrphanCheck = categoriaderisco.getContemList();
            for (Contem contemListOrphanCheckContem : contemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoriaderisco (" + categoriaderisco + ") cannot be destroyed since the Contem " + contemListOrphanCheckContem + " in its contemList field has a non-nullable categoriaderisco field.");
            }
            List<Avaliacaocategoria> avaliacaocategoriaListOrphanCheck = categoriaderisco.getAvaliacaocategoriaList();
            for (Avaliacaocategoria avaliacaocategoriaListOrphanCheckAvaliacaocategoria : avaliacaocategoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoriaderisco (" + categoriaderisco + ") cannot be destroyed since the Avaliacaocategoria " + avaliacaocategoriaListOrphanCheckAvaliacaocategoria + " in its avaliacaocategoriaList field has a non-nullable idCategoriaDeRisco field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoriaderisco fkidCategoriaDeRisco = categoriaderisco.getFkidCategoriaDeRisco();
            if (fkidCategoriaDeRisco != null) {
                fkidCategoriaDeRisco.getCategoriaderiscoList().remove(categoriaderisco);
                fkidCategoriaDeRisco = em.merge(fkidCategoriaDeRisco);
            }
            Organizacao idOrganizacao = categoriaderisco.getIdOrganizacao();
            if (idOrganizacao != null) {
                idOrganizacao.getCategoriaderiscoList().remove(categoriaderisco);
                idOrganizacao = em.merge(idOrganizacao);
            }
            List<Categoriaderisco> categoriaderiscoList = categoriaderisco.getCategoriaderiscoList();
            for (Categoriaderisco categoriaderiscoListCategoriaderisco : categoriaderiscoList) {
                categoriaderiscoListCategoriaderisco.setFkidCategoriaDeRisco(null);
                categoriaderiscoListCategoriaderisco = em.merge(categoriaderiscoListCategoriaderisco);
            }
            em.remove(categoriaderisco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoriaderisco> findCategoriaderiscoEntities() {
        return findCategoriaderiscoEntities(true, -1, -1);
    }

    public List<Categoriaderisco> findCategoriaderiscoEntities(int maxResults, int firstResult) {
        return findCategoriaderiscoEntities(false, maxResults, firstResult);
    }

    private List<Categoriaderisco> findCategoriaderiscoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoriaderisco.class));
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

    public Categoriaderisco findCategoriaderisco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoriaderisco.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaderiscoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoriaderisco> rt = cq.from(Categoriaderisco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        /*
        public int findCategoriaderiscoUltimoID(){
        EntityManager em = getEntityManager();
        
        int idUltimaCategoria = 0;
        
        
        
        try{
             //idUltimaCategoria = em.createNamedQuery("Categoriaderisco.findLastCategoriaAdd").getFirstResult();
             //idUltimaCategoria = em.createQuery("select MAX(idCategoriaDeRisco) from Categoriaderisco").getFirstResult();
            //idUltimaCategoria = em.createQuery("SELECT idCategoriaDeRisco FROM Categoriaderisco ORDER BY idCategoriaDeRisco DESC LIMIT 1 ").getFirstResult();
            
//Categoriaderisco categoriaultima = em.createQuery("select from Categoriaderisco where id=(select MAX idCategoriaDeRisco from Categoriaderisco)").getSingleResult();
            
        } catch (Exception e){
            System.out.println("erro no metodo findCategoriaderiscoUltimoID da classe CategoriaderiscoJpaController");
            e.printStackTrace();
        }
        
        return idUltimaCategoria;
    } */

    
}
