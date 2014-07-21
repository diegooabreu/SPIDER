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
import model.Contem;
import model.Risco;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Projeto;

/**
 *
 * @author MarcosSenna
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
        if (risco.getRiscoList() == null) {
            risco.setRiscoList(new ArrayList<Risco>());
        }
        if (risco.getRiscoList1() == null) {
            risco.setRiscoList1(new ArrayList<Risco>());
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
            List<Risco> attachedRiscoList = new ArrayList<Risco>();
            for (Risco riscoListRiscoToAttach : risco.getRiscoList()) {
                riscoListRiscoToAttach = em.getReference(riscoListRiscoToAttach.getClass(), riscoListRiscoToAttach.getIdRisco());
                attachedRiscoList.add(riscoListRiscoToAttach);
            }
            risco.setRiscoList(attachedRiscoList);
            List<Risco> attachedRiscoList1 = new ArrayList<Risco>();
            for (Risco riscoList1RiscoToAttach : risco.getRiscoList1()) {
                riscoList1RiscoToAttach = em.getReference(riscoList1RiscoToAttach.getClass(), riscoList1RiscoToAttach.getIdRisco());
                attachedRiscoList1.add(riscoList1RiscoToAttach);
            }
            risco.setRiscoList1(attachedRiscoList1);
            em.persist(risco);
            if (contem != null) {
                contem.getRiscoList().add(risco);
                contem = em.merge(contem);
            }
            for (Risco riscoListRisco : risco.getRiscoList()) {
                riscoListRisco.getRiscoList().add(risco);
                riscoListRisco = em.merge(riscoListRisco);
            }
            for (Risco riscoList1Risco : risco.getRiscoList1()) {
                riscoList1Risco.getRiscoList().add(risco);
                riscoList1Risco = em.merge(riscoList1Risco);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Risco risco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Risco persistentRisco = em.find(Risco.class, risco.getIdRisco());
            Contem contemOld = persistentRisco.getContem();
            Contem contemNew = risco.getContem();
            List<Risco> riscoListOld = persistentRisco.getRiscoList();
            List<Risco> riscoListNew = risco.getRiscoList();
            List<Risco> riscoList1Old = persistentRisco.getRiscoList1();
            List<Risco> riscoList1New = risco.getRiscoList1();
            if (contemNew != null) {
                contemNew = em.getReference(contemNew.getClass(), contemNew.getContemPK());
                risco.setContem(contemNew);
            }
            List<Risco> attachedRiscoListNew = new ArrayList<Risco>();
            for (Risco riscoListNewRiscoToAttach : riscoListNew) {
                riscoListNewRiscoToAttach = em.getReference(riscoListNewRiscoToAttach.getClass(), riscoListNewRiscoToAttach.getIdRisco());
                attachedRiscoListNew.add(riscoListNewRiscoToAttach);
            }
            riscoListNew = attachedRiscoListNew;
            risco.setRiscoList(riscoListNew);
            List<Risco> attachedRiscoList1New = new ArrayList<Risco>();
            for (Risco riscoList1NewRiscoToAttach : riscoList1New) {
                riscoList1NewRiscoToAttach = em.getReference(riscoList1NewRiscoToAttach.getClass(), riscoList1NewRiscoToAttach.getIdRisco());
                attachedRiscoList1New.add(riscoList1NewRiscoToAttach);
            }
            riscoList1New = attachedRiscoList1New;
            risco.setRiscoList1(riscoList1New);
            risco = em.merge(risco);
            if (contemOld != null && !contemOld.equals(contemNew)) {
                contemOld.getRiscoList().remove(risco);
                contemOld = em.merge(contemOld);
            }
            if (contemNew != null && !contemNew.equals(contemOld)) {
                contemNew.getRiscoList().add(risco);
                contemNew = em.merge(contemNew);
            }
            for (Risco riscoListOldRisco : riscoListOld) {
                if (!riscoListNew.contains(riscoListOldRisco)) {
                    riscoListOldRisco.getRiscoList().remove(risco);
                    riscoListOldRisco = em.merge(riscoListOldRisco);
                }
            }
            for (Risco riscoListNewRisco : riscoListNew) {
                if (!riscoListOld.contains(riscoListNewRisco)) {
                    riscoListNewRisco.getRiscoList().add(risco);
                    riscoListNewRisco = em.merge(riscoListNewRisco);
                }
            }
            for (Risco riscoList1OldRisco : riscoList1Old) {
                if (!riscoList1New.contains(riscoList1OldRisco)) {
                    riscoList1OldRisco.getRiscoList().remove(risco);
                    riscoList1OldRisco = em.merge(riscoList1OldRisco);
                }
            }
            for (Risco riscoList1NewRisco : riscoList1New) {
                if (!riscoList1Old.contains(riscoList1NewRisco)) {
                    riscoList1NewRisco.getRiscoList().add(risco);
                    riscoList1NewRisco = em.merge(riscoList1NewRisco);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            Contem contem = risco.getContem();
            if (contem != null) {
                contem.getRiscoList().remove(risco);
                contem = em.merge(contem);
            }
            List<Risco> riscoList = risco.getRiscoList();
            for (Risco riscoListRisco : riscoList) {
                riscoListRisco.getRiscoList().remove(risco);
                riscoListRisco = em.merge(riscoListRisco);
            }
            List<Risco> riscoList1 = risco.getRiscoList1();
            for (Risco riscoList1Risco : riscoList1) {
                riscoList1Risco.getRiscoList().remove(risco);
                riscoList1Risco = em.merge(riscoList1Risco);
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
    
    public List<Risco> findRiscoByStatusAndIdProjeto(String status, int idProjeto){
        
        List<Risco> listaRiscos = null;
        EntityManager em = getEntityManager();
        
        try{
            
            listaRiscos = em.createNamedQuery("Risco.findByStatusRiscoAndIdProjeto")
                    .setParameter("statusRisco", status)
                    .setParameter("idProjeto", idProjeto)
                    .getResultList();
            
        } catch (Exception e){
            System.out.println("erro no metodo findRiscoByStatus da classe RiscoJpaController");
            e.printStackTrace();
        }
        
        return listaRiscos;
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
