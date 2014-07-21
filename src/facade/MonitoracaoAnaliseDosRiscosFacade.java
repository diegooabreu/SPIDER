/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.RiscoJpaController;
import controller.SubcondicaoJpaController;
import java.util.List;
import model.Projeto;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author Diego
 */
public class MonitoracaoAnaliseDosRiscosFacade {
 
    public List<Risco> listarRiscosPOrdemGrauDeEsposicaoByStatus (Projeto projetoSelecionado, String statusRisco){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        List<Risco> listaRiscos = riscoJPA.findRiscosPOrdemGrauDeEsposicaoAndStatus(projetoSelecionado, statusRisco);
        return listaRiscos;
    }
    
    public List<Subcondicao> getListaSubcondicoes(Risco risco){
        
        SubcondicaoJpaController condicaoJPA = new SubcondicaoJpaController();
        List<Subcondicao> listaCondicoes = null;
        
        try{
            
            listaCondicoes = condicaoJPA.findSubcondicaoByIdRisco(risco);
            
        } catch (Exception e){
            System.out.println("Erro no metodo getListaSubcondicoes da classe monitoracaoanalisefacade");
        }
        
        return listaCondicoes;
    }
    
    public void editCondicao(Subcondicao condicao){
        SubcondicaoJpaController condicaoJPA = new SubcondicaoJpaController();
        try{
           
            condicaoJPA.edit(condicao);
            
        } catch (Exception e){
        System.out.println("Erro no metodo editCondicao da classe monitoracaoanalisefacade");
        
    }
        
    }
    
    public void editRisco(Risco risco){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        try{
           
            riscoJPA.edit(risco);
            
        } catch (Exception e){
        System.out.println("Erro no metodo editRisco da classe monitoracaoanalisefacade");
        
    }
        
    }
    
}
