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
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Diego
 */
public class CalendarioDetalhesDoDiaFacade {
    
    
    public List<Planocontingencia> getPlanosContingenciaByPontoDeControle(Pontodecontrole ponto){
        
        PlanocontingenciaJpaController pcJPA = new PlanocontingenciaJpaController();
        List<Planocontingencia> listaPC = null;
        
        try{
            
            listaPC = pcJPA.getPlanoContingenciaByIdPontoDeControle(ponto);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro no metodo getPlanosContingenciaByDateAndIdProjetoAndStatusRisco da classe calendarioDetalhesDoDiaFacade ");
        }
        
        return listaPC;
        
    }
    
    public List<Planomitigacao> getPlanosMitigacaoByPontoDeControle(Pontodecontrole ponto){
        
        PlanomitigacaoJpaController pmJPA = new PlanomitigacaoJpaController();
        List<Planomitigacao> listaPM = null;
        
        try{
            
            listaPM = pmJPA.getListPlanoMitigacaoByIdPontoDeControle(ponto);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro no metodo getPlanosMitigacaoByPontoDeControle da classe calendarioDetalhesDoDiaFacade ");
        }
        
        return listaPM;
        
    }
    
    public List<Planocontingencia> getPlanosContingenciaByMarcoDoProjeto(Marcodoprojeto marco){
         
        PlanocontingenciaJpaController pcJPA = new PlanocontingenciaJpaController();
        List<Planocontingencia> listaPC = null;
        
        try{
            
            listaPC = pcJPA.getPlanoContingenciaByIdMarcoDoProjeto(marco);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro no metodo getPlanosContingenciaByDateAndIdProjetoAndStatusRisco da classe calendarioDetalhesDoDiaFacade ");
        }
        
        return listaPC;
        
    }
    
    public List<Planomitigacao> getPlanosMitigacaoByMarcoDoProjeto(Marcodoprojeto marco){
        
        PlanomitigacaoJpaController pmJPA = new PlanomitigacaoJpaController();
        List<Planomitigacao> listaPM = null;
        
        try{
            
            listaPM = pmJPA.getListPlanoMitigacaoByIdMarcoDoProjeto(marco);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro no metodo getPlanosMitigacaoByPontoDeControle da classe calendarioDetalhesDoDiaFacade ");
        }
        
        return listaPM;
    }
    
    
}
