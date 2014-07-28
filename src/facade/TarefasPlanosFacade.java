/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.TarefasPlanosJpaController;
import model.Tarefasplanos;

/**
 *
 * @author Diogo
 */
public class TarefasPlanosFacade {

    public void inserirTarefasPlanos(Tarefasplanos tarefasPlanos) {
        TarefasPlanosJpaController tarefasPlanosJpaController = new TarefasPlanosJpaController();
        tarefasPlanosJpaController.create(tarefasPlanos);
    }
}