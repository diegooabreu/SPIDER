/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.ContemJpaController;
import java.util.List;
import model.Contem;
import model.Projeto;

/**
 *
 * @author MarcosSenna
 */
public class ContemFacade {
    
    public List<Contem> findCategoriasByIdProjeto(Projeto projeto){
        ContemJpaController contemJPA = new ContemJpaController();
        
        return contemJPA.findContemByIdProjeto(projeto.getIdProjeto());
    }
    
}
