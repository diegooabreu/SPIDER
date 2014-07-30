/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.PlanomitigacaoJpaController;
import controller.RiscoJpaController;
import controller.SubcondicaoJpaController;
import java.util.List;
import model.Planomitigacao;
import model.Projeto;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author Diego
 */
public class RiscosSelecionarRiscosParaMonitorarFacade {
    
    public List<Planomitigacao> getListaPlanosMitigacaoByRisco(Risco risco){
        
        PlanomitigacaoJpaController pmJPA = new PlanomitigacaoJpaController();
        List<Planomitigacao> listaPM = null;
        
        try{
            
            listaPM = pmJPA.getListPlanoMitigacaoByIdRisco(risco);
            
        }catch (Exception e){
            System.out.println("Erro no metodo getListaPlanosMitigacaoByRisco da classe RiscosSelecionarRiscosParaMonitorarFacade");
        
        }
        
        return listaPM;
        
    }
    
    public List<Risco> getListaDeRiscosDoProjeto(Projeto projetoSelecionado){
        
        RiscoJpaController riscoJPA = new RiscoJpaController();
        List<Risco> lista = null;
        try{
            
            lista = riscoJPA.findRiscosByIdProjeto(projetoSelecionado);
            
            
        } catch (Exception e){
            System.out.println("Erro no metodo getListaDeRiscos da classe RiscosSelecionarRiscosParaMonitorarFacade");
        }
        
        return lista;
    }
    
    public void editRisco(Risco risco){
        
        RiscoJpaController riscoJPA = new RiscoJpaController();
        
        try{
            
            riscoJPA.edit(risco);
            
            
        } catch (Exception e){
            System.out.println("Erro no metodo editRisco da classe RiscosSelecionarRiscosParaMonitorarFacade");
        }
        
        
    }
    
    public void editCondicao(Subcondicao condicao){
        SubcondicaoJpaController condicaoJPA = new SubcondicaoJpaController();
        
        try{
            
            condicaoJPA.edit(condicao);
            
        } catch (Exception e){
            System.out.println("Erro no metodo editCondicao da classe RiscosSelecionarRiscosParaMonitorarFacade");
        }
    }
    
}
