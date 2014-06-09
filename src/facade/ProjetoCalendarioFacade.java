/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.MarcodoprojetoJpaController;
import controller.PontodecontroleJpaController;
import java.util.List;
import model.Marcodoprojeto;
import model.Pontodecontrole;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class ProjetoCalendarioFacade {
    public void alterarPontoDeControle(Pontodecontrole ponto){
        
        PontodecontroleJpaController pontoJPA = new PontodecontroleJpaController();
        
        try {
            pontoJPA.edit(ponto);
        } catch (Exception e){
             e.printStackTrace();
            System.out.println("Erro no metodo alterarPontoDeControle da classe ProjetoCalendarioFacade");
        }
        
    }
    
    public void alterarMarcoDoProjeto(Marcodoprojeto marco){
        
        MarcodoprojetoJpaController marcoJPA = new MarcodoprojetoJpaController();
        
        try {
            
            marcoJPA.edit(marco);
            
        } catch(Exception e){
             e.printStackTrace();
            System.out.println("Erro no metodo alterarMarcoDoProjeto da classe ProjetoCalendarioFacade");
        }
    }
    
    public void excluirPontoDeControle(int idPontoDeControle){
        
        PontodecontroleJpaController pontoJPA = new PontodecontroleJpaController();
        
        try{
            
            pontoJPA.destroy(idPontoDeControle);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro no metodo excluirPontoDeControle da classe ProjetoCalendarioFacade");
        }
    }
    

    public void excluirMarcoDoProjeto(int idMarcoDoProjeto){
        
        MarcodoprojetoJpaController marcoJPA = new MarcodoprojetoJpaController();
        
        try {
        
            marcoJPA.destroy(idMarcoDoProjeto);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro no metodo excluirMarcoDoProjeto da classe ProjetoCalendarioFacade");
        }
        
    }
    
    
    public void criarPontoDeControle(Pontodecontrole pontoDeControle){
        
        PontodecontroleJpaController pontoJPA = new PontodecontroleJpaController();
        
        try {
            
            pontoJPA.create(pontoDeControle);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro no metodo criarPontoDeControle da classe ProjetoCalendarioFacade");
        }
        
    }
    
    public void criarMarcoDoProjeto(Marcodoprojeto marcoDoProjeto){
        
        MarcodoprojetoJpaController marcoJPA = new MarcodoprojetoJpaController();
        
        try {
            
            marcoJPA.create(marcoDoProjeto);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro no metodo criarMarcoDoProjeto da classe ProjetoCalendarioFacade");
        }
        
    }
    
    public List<Pontodecontrole> getListaPontosDeControleDoProjetoSelecionado(Projeto projetoSelecionado){
        
        PontodecontroleJpaController pontoJPA = new PontodecontroleJpaController();
        
        List<Pontodecontrole> listaPontosDeControle = null;
        
        try{
            
            listaPontosDeControle = pontoJPA.findPontodecontroleByIdProjeto(projetoSelecionado);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro no metodo getListaPontosDeControleDoProjetoSelecionado da classe ProjetoCalendarioFacade");
        }
        
        return listaPontosDeControle;
        
    }
    
    public List<Marcodoprojeto> getListaMarcosDoProjetoSelecionado(Projeto projetoSelecionado){
    
        MarcodoprojetoJpaController marcoJPA = new MarcodoprojetoJpaController();
        
        List<Marcodoprojeto> listaMarcosDoProjeto = null;
        
        try{
            
            listaMarcosDoProjeto = marcoJPA.findMarcodoprojetoByIdProjeto(projetoSelecionado);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro no metodo getListaMarcosDoProjetoSelecionado da classe ProjetoCalendarioFacade");
        }
        
        return listaMarcosDoProjeto;
        
        
    }
}
