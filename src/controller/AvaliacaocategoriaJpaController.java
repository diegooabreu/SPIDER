/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Avaliacaocategoria;
import model.Categoriaderisco;

/**
 *
 * @author Mariano
 */
public class AvaliacaocategoriaJpaController implements Serializable {

    public AvaliacaocategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AvaliacaocategoriaJpaController(){
        emf = Persistence.createEntityManagerFactory("SpiderRMPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliacaocategoria avaliacaocategoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoriaderisco idCategoriaDeRisco = avaliacaocategoria.getIdCategoriaDeRisco();
            if (idCategoriaDeRisco != null) {
                idCategoriaDeRisco = em.getReference(idCategoriaDeRisco.getClass(), idCategoriaDeRisco.getIdCategoriaDeRisco());
                avaliacaocategoria.setIdCategoriaDeRisco(idCategoriaDeRisco);
            }
            em.persist(avaliacaocategoria);
            if (idCategoriaDeRisco != null) {
                idCategoriaDeRisco.getAvaliacaocategoriaList().add(avaliacaocategoria);
                idCategoriaDeRisco = em.merge(idCategoriaDeRisco);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacaocategoria avaliacaocategoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacaocategoria persistentAvaliacaocategoria = em.find(Avaliacaocategoria.class, avaliacaocategoria.getIdavaliacaoCategoria());
            Categoriaderisco idCategoriaDeRiscoOld = persistentAvaliacaocategoria.getIdCategoriaDeRisco();
            Categoriaderisco idCategoriaDeRiscoNew = avaliacaocategoria.getIdCategoriaDeRisco();
            if (idCategoriaDeRiscoNew != null) {
                idCategoriaDeRiscoNew = em.getReference(idCategoriaDeRiscoNew.getClass(), idCategoriaDeRiscoNew.getIdCategoriaDeRisco());
                avaliacaocategoria.setIdCategoriaDeRisco(idCategoriaDeRiscoNew);
            }
            avaliacaocategoria = em.merge(avaliacaocategoria);
            if (idCategoriaDeRiscoOld != null && !idCategoriaDeRiscoOld.equals(idCategoriaDeRiscoNew)) {
                idCategoriaDeRiscoOld.getAvaliacaocategoriaList().remove(avaliacaocategoria);
                idCategoriaDeRiscoOld = em.merge(idCategoriaDeRiscoOld);
            }
            if (idCategoriaDeRiscoNew != null && !idCategoriaDeRiscoNew.equals(idCategoriaDeRiscoOld)) {
                idCategoriaDeRiscoNew.getAvaliacaocategoriaList().add(avaliacaocategoria);
                idCategoriaDeRiscoNew = em.merge(idCategoriaDeRiscoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacaocategoria.getIdavaliacaoCategoria();
                if (findAvaliacaocategoria(id) == null) {
                    throw new NonexistentEntityException("The avaliacaocategoria with id " + id + " no longer exists.");
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
            Avaliacaocategoria avaliacaocategoria;
            try {
                avaliacaocategoria = em.getReference(Avaliacaocategoria.class, id);
                avaliacaocategoria.getIdavaliacaoCategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacaocategoria with id " + id + " no longer exists.", enfe);
            }
            Categoriaderisco idCategoriaDeRisco = avaliacaocategoria.getIdCategoriaDeRisco();
            if (idCategoriaDeRisco != null) {
                idCategoriaDeRisco.getAvaliacaocategoriaList().remove(avaliacaocategoria);
                idCategoriaDeRisco = em.merge(idCategoriaDeRisco);
            }
            em.remove(avaliacaocategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacaocategoria> findAvaliacaocategoriaEntities() {
        return findAvaliacaocategoriaEntities(true, -1, -1);
    }

    public List<Avaliacaocategoria> findAvaliacaocategoriaEntities(int maxResults, int firstResult) {
        return findAvaliacaocategoriaEntities(false, maxResults, firstResult);
    }

    private List<Avaliacaocategoria> findAvaliacaocategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacaocategoria.class));
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

    public Avaliacaocategoria findAvaliacaocategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacaocategoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaocategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacaocategoria> rt = cq.from(Avaliacaocategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Avaliacaocategoria> findAvaliacaoCategoriaByIdCategoriaDeRisco(Categoriaderisco idCategoria){
        List<Avaliacaocategoria> avaliacaoCategoria = null;
        EntityManager em = getEntityManager();
        try {
            avaliacaoCategoria = em.createNamedQuery("Avaliacaocategoria.findByIdCategoriaDeRisco")
                    .setParameter("idCategoriaDeRisco", idCategoria)
                    .getResultList();
            
        } catch (Exception e) {
            System.out.println("Erro no método findAvaliacaoCategoriaByIdCategoriaDeRisco da classe OrganizacionalEARFacade");
             e.printStackTrace();
        }
        
        return avaliacaoCategoria;
    }
    
    public Avaliacaocategoria getAvaliacaoByIdCategoriaDeRisco(Categoriaderisco categoriaRisco){
        Avaliacaocategoria avaliacaoCategoria = new Avaliacaocategoria();
        EntityManager em = getEntityManager();
        
        try{
            avaliacaoCategoria = (Avaliacaocategoria) em.createNamedQuery("Avaliacaocategoria.findByIdCategoriaDeRisco")
                    .setParameter("idCategoriaDeRisco", categoriaRisco)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        
        catch(Exception e){
            System.out.println("Erro no método getAvaliacaoByIdCategoriaDeRisco da classe AvaliacaocategoriaJpaController");
            e.printStackTrace();
        }
        
        return avaliacaoCategoria;
    }
    
}
