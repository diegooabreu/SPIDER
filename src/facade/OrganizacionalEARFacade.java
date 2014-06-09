/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.AvaliacaocategoriaJpaController;
import controller.CategoriaderiscoJpaController;
import controller.ContemJpaController;
import controller.ProjetoJpaController;
import java.util.List;
import model.Avaliacaocategoria;
import model.Categoriaderisco;
import model.Contem;
import model.ContemPK;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class OrganizacionalEARFacade {
    
    public List<Categoriaderisco> listarCategorias(){

        CategoriaderiscoJpaController categoriaJPA = new CategoriaderiscoJpaController();
        
        
        List<Categoriaderisco> lista = null;
        
        try {
             
            lista =  categoriaJPA.findCategoriaderiscoEntities();
            
            
        }
         catch (Exception e){
             System.out.println("Erro no método listarCategorias da classe OrganizacionalEARFacade");
             e.printStackTrace();
         }
        
        return lista;
        
        
    }
    
    public List<Contem> getListaContemWhereIdCategoria(int i){
        ContemJpaController contemJPA = new ContemJpaController();
        
        List<Contem> contem = null;
        
        try {
                contem = contemJPA.findContemByIdCategoriaDeRisco(i);
                
        }
        
        catch (Exception e){
            System.out.println("Erro no método getListaContemWhereIdCategoria da classe OrganizacionalEARFacade");
             e.printStackTrace();
        }
        
        
        
        return contem;
        
    }
    
    
    public List<Avaliacaocategoria> getListaAvaliacaoCategoriaWhereIdCategoria(Categoriaderisco categoria ){
        AvaliacaocategoriaJpaController avaliacaoCategoriaJPA = new AvaliacaocategoriaJpaController();
        
        List<Avaliacaocategoria> avaliacaoCategoria = null;
        
        try {
                avaliacaoCategoria = avaliacaoCategoriaJPA.findAvaliacaoCategoriaByIdCategoriaDeRisco(categoria);
            
        } catch (Exception e) {
            System.out.println("Erro no método getListaAvaliacaoCategoriaWhereIdCategoria da classe OrganizacionalEARFacade");
             e.printStackTrace();
        }
        
        return avaliacaoCategoria;
    }
    
    
    public Categoriaderisco getCategoria(int i){
        Categoriaderisco categoria;
        CategoriaderiscoJpaController categoriaderiscoJPA = new CategoriaderiscoJpaController();
        
        categoria = categoriaderiscoJPA.findCategoriaderisco(i);
        
        return categoria;
    }
    
    public void salvarAlteracoes(Categoriaderisco categoria){
        CategoriaderiscoJpaController categoriaderiscoJPA = new CategoriaderiscoJpaController();
        
        try{
            categoriaderiscoJPA.edit(categoria);
        }catch (Exception e) {
             System.out.println("Erro no método alterarDetalhes da classe OrganizacionalDetalhesFacade");
             e.printStackTrace();
        }
    }
    
}
