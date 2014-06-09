/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.ProjetoJpaController;
import java.util.List;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class PrincipalFacade {

    public List<Projeto> listarProjetos(){
        
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        
        List<Projeto> listaProjetos = projetoJPA.findProjetoEntities();
        
        return listaProjetos;      
        
    }
    
}
