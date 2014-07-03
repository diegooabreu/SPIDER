/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.RiscoJpaController;
import model.Risco;

/**
 *
 * @author Diego
 */
public class RiscosSelecionarRiscosParaMonitorarFacade {
    
    public void editRisco(Risco risco){
        
        RiscoJpaController riscoJPA = new RiscoJpaController();
        
        try{
            
            riscoJPA.edit(risco);
            
            
        } catch (Exception e){
            System.out.println("Erro no metodo editRisco da classe RiscosSelecionarRiscosParaMonitorarFacade");
        }
        
        
    }
    
    
}
