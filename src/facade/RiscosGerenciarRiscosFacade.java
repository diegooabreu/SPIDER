/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import controller.GruporelacaoJpaController;
import controller.HistoricoalteracaoJpaController;
import controller.MarcodoprojetoJpaController;
import controller.PlanocontingenciaJpaController;
import controller.PlanomitigacaoJpaController;
import controller.PontodecontroleJpaController;
import controller.RelacaosubcondicaoJpaController;
import controller.RiscoJpaController;
import controller.SubcondicaoJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gruporelacao;
import model.Historicoalteracao;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;
import model.Relacaosubcondicao;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author Diego
 */
public class RiscosGerenciarRiscosFacade {
    
    // Metodos da aba Informacoes Gerais
    public List<Risco> listarRiscos(){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        List<Risco> listaRiscos = riscoJPA.findRiscoEntities();
        
        return listaRiscos;
    }
    
    public List<Risco> listarRiscosPOrdemGrauDeEsposicao (Projeto projetoSelecionado){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        List<Risco> listaRiscos = riscoJPA.findRiscosPOrdemGrauDeEsposicao(projetoSelecionado);
        return listaRiscos;
    }
    
    public void editarRisco(Risco risco){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        try{
            riscoJPA.edit(risco);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro no método editar risco em RiscosGerenciarRiscosFacade");
            
        }
    }
    
    public void removerRisco(Risco risco){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        
        try{
            riscoJPA.destroy(risco.getIdRisco());
        } catch(Exception e){
            System.out.println("Erro no método remover risco em RiscosGerenciarRiscosFacade");
        }
    }
    
    public void adicionarRisco(Risco risco){
        RiscoJpaController riscoJPA = new RiscoJpaController();
        
        try{
            riscoJPA.create(risco);
        } catch(Exception e){
            System.out.println("Erro no método adicionar risco em RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
    }
    
    // Métodos da aba Plano Mitigacao
    public List<Planomitigacao> listarPlanosMitigacao(Risco risco){
        PlanomitigacaoJpaController planoMitigacaoJPA = new PlanomitigacaoJpaController();
        List<Planomitigacao> listaMitigacao = planoMitigacaoJPA.getListPlanoMitigacaoByIdRisco(risco);
        
        return listaMitigacao;
    }
    
    public void removerPlanoMitigacao(Planomitigacao planoMitigacao){
        PlanomitigacaoJpaController planoMitigacaoJPA = new PlanomitigacaoJpaController();
        try{
            planoMitigacaoJPA.destroy(planoMitigacao.getIdPlanoMitigacao());
        }catch(Exception e){
            System.out.println("Erro no método removerPlanoMitigacao em RiscosGerenciarRiscosFacade");
        }
    }
    
    public void editarPlanoMitigacao(Planomitigacao planoMitigacao){
        PlanomitigacaoJpaController planoMitigacaoJPA = new PlanomitigacaoJpaController();
        
        try{
            planoMitigacaoJPA.edit(planoMitigacao);
        }catch (Exception ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro no método editarPlanoMitigacao em RiscosGerenciarRiscosFacade");
        }
    }
    
    public void adicionarPlanoMitigacao(Planomitigacao planoMitigacao){
        PlanomitigacaoJpaController planoMitigacaoJPA = new PlanomitigacaoJpaController();
        
        try{
            planoMitigacaoJPA.create(planoMitigacao);
        } catch(Exception e){
            System.out.println("Erro no método criarPlanoMitigacao em RiscosGerenciarRiscosFacade");
        }
    }
    
    //Métodos da aba Plano de Contingência
    
    public List<Planocontingencia> listarPlanosContingencia(Risco risco){
        PlanocontingenciaJpaController planoContingenciaJPA = new PlanocontingenciaJpaController();
        List<Planocontingencia> listaContingencia = planoContingenciaJPA.getListPlanoContingenciaByIdRisco(risco);
        
        return listaContingencia;
    }
    
    public void adicionarPlanoContingencia(Planocontingencia planoContingencia){
        PlanocontingenciaJpaController planoContingenciaJPA = new PlanocontingenciaJpaController();
        
        try{
            planoContingenciaJPA.create(planoContingencia);
        } catch(Exception e){
            System.out.println("Erro no método criarPlanoContingencia em RiscosGerenciarRiscosFacade");
        }
    }
    
    public void removerPlanoContingencia(Planocontingencia planoContingencia){
        PlanocontingenciaJpaController planoContingenciaJPA = new PlanocontingenciaJpaController();
        try{
            planoContingenciaJPA.destroy(planoContingencia.getIdPlanoContingencia());
        }catch(Exception e){
            System.out.println("Erro no método removerPlanoContingencia em RiscosGerenciarRiscosFacade");
        }
        
    }
    
    public void editarPlanoContingencia(Planocontingencia planocontingencia){
        PlanocontingenciaJpaController planoContingenciaJPA = new PlanocontingenciaJpaController();
        
        try{
            planoContingenciaJPA.edit(planocontingencia);
        }catch (Exception ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro no método editarPlanoContingencia em RiscosGerenciarRiscosFacade");
        }
    }
    
    public List<Subcondicao> listarSubcondicoesByIdRisco(Risco idRisco){
        SubcondicaoJpaController subcondicaoJPA = new SubcondicaoJpaController();
        List<Subcondicao> listaSubcondicao = null;
        
        try{
            listaSubcondicao = subcondicaoJPA.findSubcondicaoByIdRisco(idRisco);
        }catch(Exception e){
            System.out.println("Erro no método listarSubcondicoesByIdRisco na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }   
        return listaSubcondicao;
    }
    
    public List<Relacaosubcondicao> listarRelacaoSubcondicaoByIdSubcondicao1(int idSubcondicao1){
        RelacaosubcondicaoJpaController relacaoJPA = new RelacaosubcondicaoJpaController();
        List<Relacaosubcondicao> listaRelacaoSubcondicao = null;
        
        try{
            listaRelacaoSubcondicao = relacaoJPA.findRelacaoSubcondicaoByIdSubcondicao1(idSubcondicao1);
        }catch(Exception e){
            System.out.println("Erro no método listarRelacaoSubcondicoesByIdSubcondicao1 na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
        return listaRelacaoSubcondicao;
    }
    
    public void editarSubcondicao(Subcondicao subcondicao){
        SubcondicaoJpaController subcondicaoJPA = new SubcondicaoJpaController();
        
        try{
            subcondicaoJPA.edit(subcondicao);
        }catch(Exception e){
            System.out.println("Erro no método editarSubcondicao em RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
    }
    
    public List<Gruporelacao> getListaGrupoRelacaoByRisco(Risco riscoSelecionado){
        
        GruporelacaoJpaController relacaoJPA = new GruporelacaoJpaController();
        
        List<Gruporelacao> listaRelacoes = new ArrayList<Gruporelacao>();
        
        try {
            
            listaRelacoes = relacaoJPA.findByRisco(riscoSelecionado);
            
        } catch (Exception e){
            System.out.println("Erro no método getListaGrupoRelacaoByRisco na classe RiscosGerenciarRiscosFacade");
                e.printStackTrace();
        }
        
        return listaRelacoes;
    }
    
    public List<Gruporelacao> getListaGrupoRelacaoByListaDeCondicoes(List<Subcondicao> listaCondicoes){
        GruporelacaoJpaController relacaoJPA = new GruporelacaoJpaController();
        
        List<Gruporelacao> listaRelacoes = new ArrayList<Gruporelacao>();
        
        List<Gruporelacao> listaRelacoesCondicao1 = new ArrayList<Gruporelacao>();
        List<Gruporelacao> listaRelacoesCondicao2 = new ArrayList<Gruporelacao>();
        
        for(int i=0; i < listaCondicoes.size(); i++){
            
            // procurando relações por subCondicao1
            try{
                listaRelacoesCondicao1 = relacaoJPA.findByIdSubcondicao1(listaCondicoes.get(i));
            } catch (Exception e){
                System.out.println("Erro no método getListaGrupoRelacaoByCondicao na classe RiscosGerenciarRiscosFacade");
                e.printStackTrace();
            }
            
            for(int j=0; j < listaRelacoesCondicao1.size(); j++){
                
                
                
                boolean jaExisteNaListaRelacoes = false;
                if(listaRelacoes != null){
                    for(int k=0; k < listaRelacoes.size(); k++){
                    if(listaRelacoes.get(k) == listaRelacoesCondicao1.get(j)){
                        jaExisteNaListaRelacoes = true;
                    }
                }
                }
                
                
                if(jaExisteNaListaRelacoes == false){
                    listaRelacoes.add(listaRelacoesCondicao1.get(j));
                    
                }
                
            }
            
            // procurando relações por subCondicao2
            
            try{
                listaRelacoesCondicao2 = relacaoJPA.findByIdSubcondicao2(listaCondicoes.get(i));
            } catch (Exception e){
                System.out.println("Erro no método getListaGrupoRelacaoByCondicao na classe RiscosGerenciarRiscosFacade");
                e.printStackTrace();
            }
            
            for(int j=0; j < listaRelacoesCondicao2.size(); j++){
                
                boolean jaExisteNaListaRelacoes = false;
                if(listaRelacoes != null){
                for(int k=0; k < listaRelacoes.size(); k++){
                    if(listaRelacoes.get(k) == listaRelacoesCondicao2.get(j)){
                        jaExisteNaListaRelacoes = true;
                    }
                }
                }
                
                if(jaExisteNaListaRelacoes == false){
                    listaRelacoes.add(listaRelacoesCondicao2.get(j));
                }
                
            }
            
            
        }
        
        
        
        return listaRelacoes;
    }
    
    public void criarNovaRelacao(Gruporelacao novaRelacao){
        
        GruporelacaoJpaController relacaoJPA = new GruporelacaoJpaController();
        
        try{
            
            relacaoJPA.create(novaRelacao);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro no método criarNovaRelacao na classe RiscosGerenciarRiscosFacade");
        }
    }
    
    public List<Relacaosubcondicao> listarTabelasSubcondicao(){
        RelacaosubcondicaoJpaController relacaoJPA = new RelacaosubcondicaoJpaController();
        List<Relacaosubcondicao> lista = null;
        
        try{
            relacaoJPA.findRelacaosubcondicaoEntities();
        }catch(Exception e){
            System.out.println("Erro no método listarTabelasSubcondicao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public void buscarRelacaoSubcondicao(Relacaosubcondicao relacaoSubcondicao){
        RelacaosubcondicaoJpaController relacaoJPA = new RelacaosubcondicaoJpaController();
        
        try{
            
        }catch(Exception e){
            
        }
    }
    
    public void criarRelacaoSubcondicao(Relacaosubcondicao relacaoSubcondicao){
        RelacaosubcondicaoJpaController relacaoJPA = new RelacaosubcondicaoJpaController();
        
        try{
            relacaoJPA.create(relacaoSubcondicao);
        }catch(Exception e){
            System.out.println("Erro no método criarRelacaoSubcondicao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
    }
    
    public Subcondicao buscaSubcondicao(int idSubcondicao){
        SubcondicaoJpaController subcondicaoJPA = new SubcondicaoJpaController();
        Subcondicao subcondicaoBusca = null;
        
        try{
            subcondicaoJPA.findSubcondicao(idSubcondicao);
        }catch(Exception e){
            System.out.println("Erro no método buscaSubcondicao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
        return subcondicaoBusca;
    }
    
    public void criaSubcondicao(Subcondicao novaSubcondicao){
        SubcondicaoJpaController subcondicaoJPA = new SubcondicaoJpaController();
        
        try{
            subcondicaoJPA.create(novaSubcondicao);
        } catch(Exception e){
            System.out.println("Erro no método criaSubcondicao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
    }
    
    public void criaHistorioAlteracao(Historicoalteracao historico){
        HistoricoalteracaoJpaController historicoJPA = new HistoricoalteracaoJpaController();
        
        try{
            historicoJPA.create(historico);
        }catch(Exception e){
            System.out.println("Erro no método criaHistoricoAlteracao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
    }
    
    public List<Pontodecontrole> listarPontosControleProjetoSelecionado(Projeto Projeto){
        PontodecontroleJpaController pontoJPA = new PontodecontroleJpaController();
        List<Pontodecontrole> listaPontos = null;
        
        try{
            listaPontos = pontoJPA.findPontodecontroleByIdProjeto(Projeto);
        }catch(Exception e){
            System.out.println("Erro no método listarPontosControleProjetoSelecionado na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
        return listaPontos;
    }
    
    public List<Marcodoprojeto> listarMarcosProjetoProjetoSelecionado(Projeto projeto){
        MarcodoprojetoJpaController marcoJPA = new MarcodoprojetoJpaController();
        List<Marcodoprojeto> listaMarcos = null;
        
        try{
            listaMarcos = marcoJPA.findMarcodoprojetoByIdProjeto(projeto);
        }catch(Exception e){
            System.out.println("Erro no método listarMarcosProjetoProjetoSelecionado na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
        return listaMarcos;
    }
    
    public void deletarSubCondicao(Subcondicao condicao){
        
        SubcondicaoJpaController condicaoJPA = new SubcondicaoJpaController();
        
        try{
            
            condicaoJPA.destroy(condicao.getIdSubcondicao());
            
        } catch (Exception e){
            System.out.println("Erro no método deletarSubcondicao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
    }
    
    public void alterarSubCondicao(Subcondicao condicao){
        SubcondicaoJpaController condicaoJPA = new SubcondicaoJpaController();
        
        try{
            
            condicaoJPA.edit(condicao);
            
        } catch (Exception e){
            System.out.println("Erro no método alterarSubcondicao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
    }
    
    public void deletarRelacao(Gruporelacao relacao){
        
        GruporelacaoJpaController relacaoJPA = new GruporelacaoJpaController();
        
        try{
            
            relacaoJPA.destroy(relacao.getIdGrupo());
            
        } catch (Exception e){
            System.out.println("Erro no método deletarRelacao na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        
    }
    
    public List<Risco> findListaRiscoByIdProjeto(Projeto projetoSelecioando){
        RiscoJpaController riscoJpaController = new RiscoJpaController();
        List<Risco> listaRiscoDoProjeto = null;
        try {
            listaRiscoDoProjeto = riscoJpaController.findRiscosByIdProjeto(projetoSelecioando);
        } catch (Exception e){
             System.out.println("Erro no método findListaRiscoByIdProjeto na classe RiscosGerenciarRiscosFacade");
            e.printStackTrace();
        }
        return listaRiscoDoProjeto;
    }
    
}
