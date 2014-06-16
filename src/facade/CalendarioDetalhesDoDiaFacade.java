/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.PlanocontingenciaJpaController;
import controller.PlanomitigacaoJpaController;
import java.util.Date;
import java.util.List;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Diego
 */
public class CalendarioDetalhesDoDiaFacade {
    
    public List<Planocontingencia> getPlanosContingenciaByDateAndIdProjetoAndStatusRisco(Projeto projetoSelecionado, String statusRisco, Date dataLimite){
        
        PlanocontingenciaJpaController pcJPA = new PlanocontingenciaJpaController();
        List<Planocontingencia> listaPC = null;
        
        try{
            
            listaPC = pcJPA.getPlanoContingenciaByStatusRiscoAndDataLimiteAndIdProjeto(statusRisco, dataLimite, projetoSelecionado);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro no metodo getPlanosContingenciaByDateAndIdProjetoAndStatusRisco da classe calendarioDetalhesDoDiaFacade ");
        }
        
        return listaPC;
        
    }
    
    public List<Planomitigacao> getPlanosMitigacaoByDateAndIdProjetoAndStatusRisco(Projeto projetoSelecionado, String statusRisco, Date dataLimite){
        
        PlanomitigacaoJpaController pmJPA = new PlanomitigacaoJpaController();
        List<Planomitigacao> listaPM = null;
        
        try{
            
            listaPM = pmJPA.getPlanoMitigacaoByStatusRiscoAndDataLimiteAndIdProjeto(statusRisco, dataLimite, projetoSelecionado);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro no metodo getPlanosMitigacaoByDateAndIdProjetoAndStatusRisco da classe calendarioDetalhesDoDiaFacade ");
        }
        
        return listaPM;
        
    }
    
}
