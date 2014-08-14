/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.util.List;
import model.Gruporelacao;
import model.Historicoalteracao;
import model.Historicorisco;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author ITAU
 */
public class RiscoModificadoJpaController extends RiscoJpaController {

    HistoricoalteracaoJpaController historicoalteracaoJpaController = new HistoricoalteracaoJpaController();
    PlanocontingenciaJpaController planocontingenciaJpaController = new PlanocontingenciaJpaController();
    PlanomitigacaoJpaController planomitigacaoJpaController = new PlanomitigacaoJpaController();
    SubcondicaoJpaController subcondicaoJpaController = new SubcondicaoJpaController();
    GruporelacaoJpaController gruporelacaoJpaController = new GruporelacaoJpaController();
    HistoricoriscoJpaController historicoriscoJpaController = new HistoricoriscoJpaController();
    
    public void destroy(Risco risco) throws IllegalOrphanException, NonexistentEntityException {

        try {
            // Removendo Historicos de alterações relacionados ao Risco.
            List<Historicoalteracao> listaHistoricoAlteracao = historicoalteracaoJpaController.findHistoricoByIdRisco(risco);
            for (int i = 0; i < listaHistoricoAlteracao.size(); i++) {
                historicoalteracaoJpaController.destroy(listaHistoricoAlteracao.get(i).getIdHistoricoAlteracao());
            }

            // Removendo planos de Contingência relacionados ao Risco
            List<Planocontingencia> listaPlanoContingencia = planocontingenciaJpaController.getListPlanoContingenciaByIdRisco(risco);
            for (int i = 0; i < listaPlanoContingencia.size(); i++) {
                planocontingenciaJpaController.destroy(listaPlanoContingencia.get(i).getIdPlanoContingencia());
            }

            // Removendo planos de Mitigação relacionados ao Risco
            List<Planomitigacao> listaPlanomitigacao = planomitigacaoJpaController.getListPlanoMitigacaoByIdRisco(risco);
            for (int i = 0; i < listaPlanomitigacao.size(); i++) {
                planomitigacaoJpaController.destroy(listaPlanomitigacao.get(i).getIdPlanoMitigacao());
            }
            
            // Removendo relações de Risco relacionados ao Risco
            for (int i=0; i < risco.getRiscoList2().size(); i++){
                risco.getRiscoList2().remove(i);
            }
            super.edit(risco);
            // Remoção do Historicorisco relacionado ao Risco.
            List<Historicorisco> listaHistoricorisco = historicoriscoJpaController.findHistoricoRiscoByIdRisco(risco.getIdRisco());
            for (int i=0; i < listaHistoricorisco.size(); i++){
                historicoriscoJpaController.destroy(listaHistoricorisco.get(i).getIdHistoricoRisco());
            }
            
            // Removendo das Subcondiçôes e Historico risco do risco.
            List<Subcondicao> listaSubcondicao = subcondicaoJpaController.findSubcondicaoByIdRisco(risco);
            List<Gruporelacao> listaGruporelacaos = gruporelacaoJpaController.findByRisco(risco);
            for (int i=0; i < listaGruporelacaos.size(); i++){
                gruporelacaoJpaController.destroy(listaGruporelacaos.get(i).getIdGrupo());
            }
            
            for (int i=0; i < listaSubcondicao.size(); i++){
                for (int j=0; j < listaSubcondicao.get(i).getHistoricoriscoList().size(); j++){
                    listaSubcondicao.get(i).getHistoricoriscoList().remove(j);
                }
                subcondicaoJpaController.edit(listaSubcondicao.get(i));
                subcondicaoJpaController.destroy(listaSubcondicao.get(i).getIdSubcondicao());
            }
            
            super.destroy(risco.getIdRisco());
        } catch (Exception e) {
            System.out.println("Erro no metodo destroy da classe RiscoModificadoJpaController");
            e.printStackTrace();
        }
    }
}
