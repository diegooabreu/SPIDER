/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controller.CategoriaderiscoJpaController;
import controller.ContemJpaController;
import controller.ProjetoJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoriaderisco;
import model.Contem;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class ProjetoFacade {

    public Projeto getProjeto(int i) {
        Projeto projeto;
        ProjetoJpaController projetoJPA = new ProjetoJpaController();
        projeto = projetoJPA.findProjeto(i);

        return projeto;
    }

    public void adicionarEAR(Projeto projeto){
        
       // Contem novaContem = new Contem( idProjetoSelecionado/* listaContem.get(0).getProjeto().getIdProjeto() */ , projetoEstruturaAnaliticaRiscosFacade.getIdUltimaCategoria() );
       // novaContem.setProjeto(projetoEstruturaAnaliticaRiscosFacade.getProjeto(idProjetoSelecionado));
       // novaContem.setCategoriaderisco(novaCategoria);
        
        
        //projetoEstruturaAnaliticaRiscosFacade.criarContem(novaContem);
     
        
        // PEGAR LISTA DE CATEGORIAAS DA EAR ORGANIZACIONAL // 
        
        ContemJpaController contemJPA = new ContemJpaController();
        
        CategoriaderiscoJpaController categoriaderiscoJPA = new CategoriaderiscoJpaController();
        List<Categoriaderisco> lista = categoriaderiscoJPA.findCategoriaderiscoEntities();
        
        for(int i = 0;i<lista.size(); i++){
            if (lista.get(i).getEarOrganizacional() == true){
                
                Contem novaContem = new Contem( /*idProjetoSelecionado , projetoEstruturaAnaliticaRiscosFacade.getIdUltimaCategoria() */);
                novaContem.setProjeto(projeto);
                novaContem.setCategoriaderisco(lista.get(i));
                
                try{
            
            contemJPA.create(novaContem);
            
        } catch (Exception e){
            System.out.println("Erro no método criarContem da classe ProjetoEstruturaAnaliticaRiscosFacade");
             e.printStackTrace();
        }
                
            }
        
        }
        
        
        
        
        
        
        
    }

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
    
    public void salvarNovoProjeto(Projeto projeto){
        ProjetoJpaController projetoJpa = new ProjetoJpaController();
        projetoJpa.create(projeto);
    }

}
