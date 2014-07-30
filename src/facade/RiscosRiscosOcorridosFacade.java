/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.HistoricoriscoJpaController;
import controller.RiscoJpaController;
import controller.SubcondicaoJpaController;
import java.util.List;
import model.Historicorisco;
import model.Risco;
import model.Subcondicao;

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
    
    public List<Historicorisco> getListaHistoricoriscosByIdProjeto (int idProjeto){
        HistoricoriscoJpaController historicoRiscoJPA = new HistoricoriscoJpaController();
        List<Historicorisco> listaHistoricoRiscos = historicoRiscoJPA.findHistoricoRiscoByIdProjeto(idProjeto);
        return listaHistoricoRiscos;
    }
    
    public List<Subcondicao> getListaSubcondicaoByIdHistoricoRisco (Historicorisco historicorisco){
       SubcondicaoJpaController subcondicaoJpaController = new SubcondicaoJpaController();
       return subcondicaoJpaController.findHistoricoRiscoByIdHistoricoRisco(historicorisco);   
    }
}
