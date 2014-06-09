/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.PoliticaorganizacionalJpaController;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import model.Politicaorganizacional;

/**
 *
 * @author MarcosSenna
 */
public class PoliticaOrganizacionalFacade {
    
    public Politicaorganizacional getPolitica(){
    Politicaorganizacional politicaOrg = null;
    PoliticaorganizacionalJpaController politicaOrgJPA = new PoliticaorganizacionalJpaController();
    politicaOrg = politicaOrgJPA.findPoliticaorganizacional(1);
    
    return politicaOrg;
    }
    
    public void setPolitica(Politicaorganizacional objetoPolitica) throws IllegalOrphanException, NonexistentEntityException, Exception{
        PoliticaorganizacionalJpaController politicaOrgJPA = new PoliticaorganizacionalJpaController();
        politicaOrgJPA.edit(objetoPolitica);
    }
    
    
}
