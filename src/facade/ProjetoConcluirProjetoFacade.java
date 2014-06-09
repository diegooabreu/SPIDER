/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.ContemJpaController;
import controller.ProjetoJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contem;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class ProjetoConcluirProjetoFacade {
    
        public void setProjeto(Projeto projeto) {
        ProjetoJpaController projetoJpa = new ProjetoJpaController();
        try {
            projetoJpa.edit(projeto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public List<Contem> getListaContemWhereIdProjeto(int idProjeto){
        
        ContemJpaController contemJPA = new ContemJpaController();
        List<Contem> contem = null;
        
        try {
            
            contem = contemJPA.findContemByIdProjeto(idProjeto);
            
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("erro no metodo getListaContemWhereIdProjeto da classe ProjetoEstruturaAnaliticaRiscosFacade");
            
        }
        
        return contem;
    }
    
    
    
}
