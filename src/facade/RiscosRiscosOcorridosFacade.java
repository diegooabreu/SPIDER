/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.RiscoJpaController;
import java.util.List;
import model.Risco;

/**
 *
 * @author Diego
 */
public class RiscosRiscosOcorridosFacade {
    
    
    public List<Risco> getListaDeRiscos(){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        List<Risco> listaRiscos = riscoJPA.findRiscoEntities();
        
        return listaRiscos;
    }
}
