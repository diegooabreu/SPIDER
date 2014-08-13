/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controller.PlanocontingenciaJpaController;
import controller.PlanomitigacaoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Planocontingencia;
import model.Planomitigacao;

/**
 *
 * @author Diogo
 */
public class PlanosFacade {

    public void editarPlanodeMitigacao(Planomitigacao planoMitigacao) {
        PlanomitigacaoJpaController planoMitigacaoJpaController = new PlanomitigacaoJpaController();
        try {
            planoMitigacaoJpaController.edit(planoMitigacao);
        } catch (Exception ex) {
            Logger.getLogger(PlanosFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Erro ao editar plano de mitigação");
        }
    }

    public void editarPlanodeContingencia(Planocontingencia planoContingencia) {
        PlanocontingenciaJpaController planoContingenciaJpaController = new PlanocontingenciaJpaController();
        try {
            planoContingenciaJpaController.edit(planoContingencia);
        } catch (Exception ex) {
            Logger.getLogger(PlanosFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Erro ao editar plano de contingencia");
        }
    }

    public Planomitigacao buscaPlanoMitigacaoById(int id){
        PlanomitigacaoJpaController planomitigacaoJpaController = new PlanomitigacaoJpaController();
        return planomitigacaoJpaController.findPlanomitigacao(id);
    }
    
    public Planocontingencia buscaPlanoContingenciaById(int id){
        PlanocontingenciaJpaController planocontingenciaJpaController = new PlanocontingenciaJpaController();
        return planocontingenciaJpaController.findPlanocontingencia(id);
    }
        
}
