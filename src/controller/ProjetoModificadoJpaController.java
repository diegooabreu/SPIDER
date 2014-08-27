/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import facade.ContemFacade;
import facade.ProjetoCalendarioFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.util.List;
import model.Contem;
import model.Marcodoprojeto;
import model.Pontodecontrole;
import model.Projeto;
import model.Risco;

/**
 *
 * @author ITAU
 */
public class ProjetoModificadoJpaController extends ProjetoJpaController{
    
    public void destroy (Projeto projeto){
        RiscoModificadoJpaController riscoModificadoJpaController = new RiscoModificadoJpaController();
        MarcodoprojetoJpaController marcodoprojetoJpaController = new MarcodoprojetoJpaController();
        PontodecontroleJpaController pontodecontroleJpaController = new PontodecontroleJpaController();
        ContemJpaController contemJpaController = new ContemJpaController();
        
        try {
            //Removendo Riscos do Projeto Selecionado
            List<Risco> listaDeRiscos = new RiscosGerenciarRiscosFacade().listarRiscosByProjeto(projeto);
            for (int i=0; i < listaDeRiscos.size(); i++){
                riscoModificadoJpaController.destroy(listaDeRiscos.get(i));
            }
            // Removendo Marcos do Projeto Selecionado
            List<Marcodoprojeto> listaMarcosDoProjeto = new ProjetoCalendarioFacade().getListaMarcosDoProjetoSelecionado(projeto);
            for (int i=0; i < listaMarcosDoProjeto.size(); i++){
                marcodoprojetoJpaController.destroy(listaMarcosDoProjeto.get(i).getIdMarcoDoProjeto());
            }
            // Removendo Pontos de controle do Projeto selecionado
            List<Pontodecontrole> listaPontosDeControle = new ProjetoCalendarioFacade().getListaPontosDeControleDoProjetoSelecionado(projeto);
            for (int i=0; i < listaPontosDeControle.size(); i++){
                pontodecontroleJpaController.destroy(listaPontosDeControle.get(i).getIdPontoDeControle());
            }
            // Removendo Categoria de Risco do projeto Selecionado
            List<Contem> listaCategoriaDeRiscos = new ContemFacade().findCategoriasByIdProjeto(projeto);
            for (int i=0; i < listaCategoriaDeRiscos.size(); i++){
                contemJpaController.destroy(listaCategoriaDeRiscos.get(i).getContemPK());
            }
            // Removendo Projeto Selecionado
            super.destroy(projeto.getIdProjeto());
            
        }catch(Exception error){
            System.out.println("Erro no método destroy da classe ProjetoModificadoJpaController ");
            error.printStackTrace();
        }
    }
}
