/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.OrganizacaoJpaController;
import model.Organizacao;

/**
 *
 * @author H
 */
public class OrganizacaoDetalhesFacade {
    
    public void alterarDetalhes(Organizacao org){
        OrganizacaoJpaController organizacaoJPA = new OrganizacaoJpaController();
        
        try {
            organizacaoJPA.edit(org);
        }
         catch (Exception e){
             System.out.println("Erro no método alterarDetalhes da classe OrganizacionalDetalhesFacade");
             e.printStackTrace();
         }
        
    }
    
    
    public Organizacao getDetalhes() {
        Organizacao org = null;
        OrganizacaoJpaController organizacaoJPA = new OrganizacaoJpaController();
        org = organizacaoJPA.findOrganizacao(1);
        
        return org;
    }
    
}
