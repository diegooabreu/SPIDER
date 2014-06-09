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
 * @author Diogo
 */
public class ProjetoPlanoRiscoFacade {
    
    public List<Projeto> getProjetos(){
        List<Projeto> projeto;
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        projeto = projetoJPA.findProjetoEntities();
        return projeto;
    }
    
    public void alterarPlanoRisco(Projeto projeto){
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        
        try{
            projetoJPA.edit(projeto);
        
        }
        catch (Exception e){
            System.out.println("Erro no método Alterar Planos de Risco");
            e.printStackTrace();
        }
    }
    
    public Projeto getProjetoWhereId(int id){
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        Projeto projeto = new Projeto();
        projeto = projetoJPA.findProjeto(id);
        
        return projeto;
    }
    
}
