/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.CategoriaderiscoJpaController;
import controller.ContemJpaController;
import controller.OrganizacaoJpaController;
import controller.ProjetoJpaController;
import java.util.List;
import model.Categoriaderisco;
import model.Contem;
import model.ContemPK;
import model.Organizacao;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class ProjetoEstruturaAnaliticaRiscosFacade {
    
    public Projeto getProjeto(int idProjeto){
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        Projeto projeto = projetoJPA.findProjeto(idProjeto);
        
        return projeto;
    }
    
    public Organizacao getOrganizacao(){
        
        OrganizacaoJpaController org = new OrganizacaoJpaController();
        
        Organizacao org2 = org.findOrganizacao(1);
        
        return org2;
    }
            
    public List<Categoriaderisco> getCategorias(){
        CategoriaderiscoJpaController categoriaJPA = new CategoriaderiscoJpaController();
        List<Categoriaderisco> listaCategorias = null;
        
        try{
            
            listaCategorias = categoriaJPA.findCategoriaderiscoEntities();
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro no metodo getCategoriasOrganizacionais da classe ProjetoEstruturaAnaliticaRiscosFacade");
            
        }
        
        return listaCategorias;
    }
    
    public List<Contem> getListaContemWhereIdProjeto(int idProjeto){
        
        ContemJpaController contemJPA = new ContemJpaController();
        List<Contem> contem = null;
        
        try {
            
            contem = contemJPA.findContemByIdProjeto(idProjeto);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro no metodo getListaContemWhereIdProjeto da classe ProjetoEstruturaAnaliticaRiscosFacade");
            
        }
        
        return contem;
    }
    
    public void salvarAlteracoes(Categoriaderisco categoria){
        
        CategoriaderiscoJpaController categoriaderiscoJPA = new CategoriaderiscoJpaController();
        
        try {
            
            categoriaderiscoJPA.edit(categoria);
            
        } catch (Exception e){
            System.out.println("Erro no método salvarAlteracoes da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
    }
    
    public int getIdUltimaCategoria(){
        
        CategoriaderiscoJpaController categoriaderiscoJPA = new CategoriaderiscoJpaController();
        int idUltimaCategoria = 0;
        
        try{
           // idUltimaCategoria = categoriaderiscoJPA.findCategoriaderiscoUltimoID();
            
            List<Categoriaderisco> lista = categoriaderiscoJPA.findCategoriaderiscoEntities();
            Categoriaderisco ultimoElemento = lista.get(lista.size() - 1);
            idUltimaCategoria = ultimoElemento.getIdCategoriaDeRisco();
            
            
        }catch (Exception e){
            System.out.println("Erro no método getIdUltimaCategoria da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
        
        return idUltimaCategoria;
    }
    
    
    public void criarCategoria(Categoriaderisco categoria){
        
        CategoriaderiscoJpaController categoriaderiscoJPA = new CategoriaderiscoJpaController();
        
        try {
            
            categoriaderiscoJPA.create(categoria);
            
            
        } catch (Exception e){
            System.out.println("Erro no método criarCategoria da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
        
    }
    
    public void criarContem(Contem contem){
        
        ContemJpaController contemJPA = new ContemJpaController();
        
        try{
            
            contemJPA.create(contem);
            
        } catch (Exception e){
            System.out.println("Erro no método criarContem da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
        
    }

    public void destroyContem(int idProjeto, int idCategoriaDeRisco){
        
        ContemJpaController contemJPA = new ContemJpaController();
        ContemPK contemPK = new ContemPK(idProjeto, idCategoriaDeRisco);
        
        try{
            
            contemJPA.destroy(contemPK);

        } catch (Exception e){
            System.out.println("Erro no método destroyContem da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
        
        
        
    }
    
    
    
    public void destroyCategoriaDeRisco(int idCategoriaDeRisco){
        
        CategoriaderiscoJpaController categoriaJPA = new CategoriaderiscoJpaController();
        
        try{
            
            categoriaJPA.destroy(idCategoriaDeRisco);            
            
        } catch (Exception e){
            System.out.println("Erro no método destroyCategoriaDeRisco da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
        
    }
    
}

    
    

