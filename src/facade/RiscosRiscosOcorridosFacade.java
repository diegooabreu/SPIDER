/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.HistoricoriscoJpaController;
import controller.RiscoJpaController;
import java.util.List;
import model.Historicorisco;
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
    
    public List<Historicorisco> getListaHistoricoRiscoByIdRisco (int idRisco){
        HistoricoriscoJpaController historicoRiscoJPA = new HistoricoriscoJpaController();
        List<Historicorisco> listaHistoricoRiscos = historicoRiscoJPA.findHistoricoRiscoByIdRisco(idRisco);
        return listaHistoricoRiscos;
    }
    
    public List<Historicorisco> getListaHistoricoriscosByIdProjetoAndStatusRisco (String statusRisco, int idProjeto){
        HistoricoriscoJpaController historicoRiscoJPA = new HistoricoriscoJpaController();
        List<Historicorisco> listaHistoricoRiscos = historicoRiscoJPA.findHistoricoRiscoByIdProjetoAndStatusRisco(statusRisco, idProjeto);
        return listaHistoricoRiscos;
    }
}
