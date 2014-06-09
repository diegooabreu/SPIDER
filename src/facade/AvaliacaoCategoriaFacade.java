/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.AvaliacaocategoriaJpaController;
import javax.persistence.NoResultException;
import model.Avaliacaocategoria;
import model.Categoriaderisco;

/**
 *
 * @author MarcosSenna
 */
public class AvaliacaoCategoriaFacade {
    
    /*public List<Avaliacaocategoria> buscaAvaliacao(Categoriaderisco id){
        AvaliacaocategoriaJpaController avaliacaoCategoriaJPA = new AvaliacaocategoriaJpaController();
        
        return avaliacaoCategoriaJPA.findAvaliacaoCategoriaByIdCategoriaDeRisco(id);
    }*/
    
    public void criarAvaliacao(Avaliacaocategoria avaliacaoCategoria){
        AvaliacaocategoriaJpaController avaliacaoCategoriaJPA = new AvaliacaocategoriaJpaController();
        
        avaliacaoCategoriaJPA.create(avaliacaoCategoria);
    }
    
    public Avaliacaocategoria buscaAvaliacaoByIdCategoriaDeRisco(int idCategoriaDeRisco){
        Avaliacaocategoria avaliacaoCategoria = new Avaliacaocategoria();
        AvaliacaocategoriaJpaController avaliacaoCategoriaJpa = new AvaliacaocategoriaJpaController();
        OrganizacionalEARFacade organizacionalFacade = new OrganizacionalEARFacade();
        
        try{
            avaliacaoCategoria = avaliacaoCategoriaJpa.getAvaliacaoByIdCategoriaDeRisco(organizacionalFacade.getCategoria(idCategoriaDeRisco));
        } catch (NoResultException e){
            return null;
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
        
        
        return avaliacaoCategoria;
    }
    
    public void alterarAvaliacao(Avaliacaocategoria avaliacaoCategoria){
        AvaliacaocategoriaJpaController avaliacaoCategoriaJpa = new AvaliacaocategoriaJpaController();
        
        try{
            avaliacaoCategoriaJpa.edit(avaliacaoCategoria);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
