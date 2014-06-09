/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.AvaliacaocategoriaJpaController;
import controller.AvaliacaoprojetoJpaController;
import controller.CategoriaderiscoJpaController;
import controller.ContemJpaController;
import controller.HistoricoalteracaoJpaController;
import controller.ProjetoJpaController;
import java.util.List;
import model.Avaliacaocategoria;
import model.Avaliacaoprojeto;
import model.Categoriaderisco;
import model.Contem;
import model.Historicoalteracao;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Mariano
 */

// Adicionar facades de avaliacaoCategoria e avaliacaoProjeto neste Facade
public class OrganizacionalPortfolioFacade {
    
    public List<Projeto> listarProjetos(){
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        
        List<Projeto> lista = null;
        
        try{
            lista = projetoJPA.findProjetoEntities();
        } catch(Exception e){
            System.out.println("Erro no método listarProjetos na classe OrganizacionalPortfolioFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public List<Contem> listarContem(){
        ContemJpaController contemJPA = new ContemJpaController();
        
        List<Contem> lista = null;
        
        try{
            lista = contemJPA.findContemEntities();
        } catch(Exception e){
            System.out.println("Erro no método listarContem na classe OrganizacionalPortfolioFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public List<Categoriaderisco> listarCategorias(){
        CategoriaderiscoJpaController categoriaJPA = new CategoriaderiscoJpaController();
        
        List<Categoriaderisco> lista = null;
        
        try{
            lista = categoriaJPA.findCategoriaderiscoEntities();
        } catch(Exception e){
            System.out.println("Erro no método listarCategorias na classe OrganizacionalPortfolioFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public List<Avaliacaoprojeto> listarAvaliacaoProjeto(){
        AvaliacaoprojetoJpaController avaliacaoProjetoJPA = new AvaliacaoprojetoJpaController();
        
        List<Avaliacaoprojeto> lista = null;
        
        try{
            lista = avaliacaoProjetoJPA.findAvaliacaoprojetoEntities();
        } catch(Exception e){
            System.out.println("Erro no método listarAvaliacaoProjeto na classe OrganizacionalPortfolioFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public List<Avaliacaocategoria> listarAvaliacaoCategoria(){
        AvaliacaocategoriaJpaController avaliacaoCategoriaJPA = new AvaliacaocategoriaJpaController();
        
        List<Avaliacaocategoria> lista = null;
        
        try{
            lista = avaliacaoCategoriaJPA.findAvaliacaocategoriaEntities();
        } catch (Exception e){
            System.out.println("Erro no método listarAvaliacaoCategoria na classe OrganizacionalPortfolioFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public List<Historicoalteracao> listarHistoricoAlteracoesByIdRisco(Risco idRisco){
        HistoricoalteracaoJpaController historicoAlteracaoJPA = new HistoricoalteracaoJpaController();
        
        List<Historicoalteracao> lista = null;
        
        try{
            lista = historicoAlteracaoJPA.findHistoricoByIdRisco(idRisco);
        } catch (Exception e){
            System.out.println("Erro no método listarHistoricoAlteracoesByIdRisco na classe OrganizacionalPortfolioFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
}
