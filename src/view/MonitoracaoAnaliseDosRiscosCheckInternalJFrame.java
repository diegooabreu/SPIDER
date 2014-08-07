/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import facade.MonitoracaoAnaliseDosRiscosFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Gruporelacao;
import model.Historicoalteracao;
import model.Historicorisco;
import static model.Relacaosubcondicao_.subcondicao;
import model.Risco;
import model.Subcondicao;
import view.tabelas.CondicaoTabela;
import view.tabelas.CondicaoTabelaModel;

/**
 *
 * @author BlenoVale
 */
public class MonitoracaoAnaliseDosRiscosCheckInternalJFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form MonitorarAnaliseDosRiscosCheckInternalJFrame
     */
    
     MonitoracaoAnaliseDosRiscosFacade monitoracaoAnaliseDosRiscosFacade = new MonitoracaoAnaliseDosRiscosFacade();
    
    JTable tabelaCondicoes;
    CondicaoTabelaModel modeloTabelaCondicoes;
    
    List<CondicaoTabela> listaCondicaoTabela;
    List<Subcondicao> listaCondicao;
    
    Risco riscoSel;
    boolean riscoOcorreu = false;
    List<Gruporelacao> listaGruporelacao = null;
    
    public MonitoracaoAnaliseDosRiscosCheckInternalJFrame() {
        initComponents();
    }
    
    public void definirEventosTabelaCondicoes(){
        tabelaCondicoes.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 1) {

                    riscoOcorreu = false;
                    
                    int selected = tabelaCondicoes.getSelectedRow();
                    
                    if((boolean)modeloTabelaCondicoes.getValueAt(selected, 0) == false){
                            modeloTabelaCondicoes.setValueAt(true, selected, 0);
                        // se monitorar estiver marcado entao desmarca
                        } else {
                            modeloTabelaCondicoes.setValueAt(false, selected, 0);
                        }
                    
                    List<CondicaoTabela> listaDeCondicoesMarcadas = new ArrayList<CondicaoTabela>();
                    
                    for (int i = 0; i < listaCondicaoTabela.size(); i++){
                        if (listaCondicaoTabela.get(i).isStatusCondicao()){
                            listaDeCondicoesMarcadas.add(listaCondicaoTabela.get(i));
                        }
                    }
                    
                    
                    
                    
                    if(existeCondicaoIndependenteMarcada(listaDeCondicoesMarcadas)){
                        
                        riscoOcorreu = true;
                    } else {
                        
                        if(existeRelacaoFinalTrue()){
                            
                            riscoOcorreu = true;
                        }
                    }
                    
                    if(riscoOcorreu){
                        riscoOcorreuJLabel.setText("Sim");
                        statusRiscoJComboBox.setSelectedItem("Contingenciando");
                    } else {
                        riscoOcorreuJLabel.setText("Não");
                        statusRiscoJComboBox.setSelectedItem("Novo");
                    }

                }
            }
        });
    }
    
    private List<Subcondicao> getSubcondicoesMarcadas(){
        List<Subcondicao> listaSubcondicoesMarcadas = new ArrayList<Subcondicao>();
        
        for (int i = 0; i < listaCondicaoTabela.size(); i++){
                        if (listaCondicaoTabela.get(i).isStatusCondicao()){
                            for(int k = 0; k < listaCondicao.size(); k++){
                                if(listaCondicaoTabela.get(i).getIdCondicao() == listaCondicao.get(k).getIdSubcondicao()){
                                    listaSubcondicoesMarcadas.add(listaCondicao.get(k));
                                }
                            }
                        }
                    }
        
        return listaSubcondicoesMarcadas;
    }
    
    public void setRiscoOcorreuFalse(){
        riscoOcorreu = false;
    }
    
    public void criarTabelaCondicoes(Risco risco){
        
        tabelaCondicoes = new JTable();
        modeloTabelaCondicoes = new CondicaoTabelaModel();
        
        tabelaCondicoes.setModel(modeloTabelaCondicoes);
        tabelaCondicoesRiscoJScrollPane.setViewportView(tabelaCondicoes);
        
        listaCondicao = monitoracaoAnaliseDosRiscosFacade.getListaSubcondicoes(risco);
        
        modeloTabelaCondicoes.addListaDeCondicao(criarListaCondicaoTabela(listaCondicao));
        
        definirEventosTabelaCondicoes();
     
    }

     public List<CondicaoTabela> criarListaCondicaoTabela(List<Subcondicao> listaSubcondicoes){
        
         listaCondicaoTabela = new ArrayList<CondicaoTabela>();
         
         for(int i=0; i < listaSubcondicoes.size(); i++){
            CondicaoTabela novoCondicaoTabela = new CondicaoTabela();
            
            novoCondicaoTabela.setDescricaoCondicao(listaSubcondicoes.get(i).getDescricaoSubcondicao());
            novoCondicaoTabela.setIdCondicao(listaSubcondicoes.get(i).getIdSubcondicao());
            novoCondicaoTabela.setIdentificacaoCondicao(listaSubcondicoes.get(i).getIdentificacaoSubcondicao());
            if(listaSubcondicoes.get(i).getStatusSubcondicao().equals("Ocorrido")){
                novoCondicaoTabela.setStatusCondicao(true);
                
            } else {
                novoCondicaoTabela.setStatusCondicao(false);
                
            }
            
            listaCondicaoTabela.add(novoCondicaoTabela);
            
         }
         
         return listaCondicaoTabela;
        
    }
     
     public void preencherInformacoes(Risco risco){
        riscoSel  = risco;
        identificacaRiscoJLabel.setText(risco.getIdentificacao());
        descricaoRiscoJTextArea.setText(risco.getDescricao());
        
        riscoOcorreuJLabel.setText("Não");
    }
     
     public void getListaGrupoRelacao(Risco risco){
         RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
              
             this.listaGruporelacao = riscosGerenciarRiscosFacade.getListaGrupoRelacaoByRisco(risco);
            
     }
     
     private boolean existeRelacaoFinalTrue(){
         boolean existe = false;
         
         List<Gruporelacao> listaRelacoesFinais = getListaGrupoRelacaoFinal();
         
         for(int i = 0; i < listaRelacoesFinais.size(); i++){
             
             if(checarRelacao(listaRelacoesFinais.get(i))){
                 existe = true;
             }
             
         }
         
         return existe;
     }
     
     private boolean existeCondicaoIndependenteMarcada (List<CondicaoTabela> listaCondicoesMarcadas){
         boolean existe = false;
         boolean temRelacao = false;
         
         for (int i=0; i < listaCondicoesMarcadas.size(); i++){
            temRelacao = false;
            if(listaGruporelacao != null){
            for (int j=0; j < listaGruporelacao.size(); j++){
                
                if((listaGruporelacao.get(j).getIdSubcondicao1() != null) &&(listaGruporelacao.get(j).getIdSubcondicao2() != null)){
                    if (listaGruporelacao.get(j).getIdSubcondicao1() == listaCondicoesMarcadas.get(i).getIdCondicao()
                        || listaGruporelacao.get(j).getIdSubcondicao2() == listaCondicoesMarcadas.get(i).getIdCondicao()){
                    temRelacao = true;
                   
                    }
                    
                } else if((listaGruporelacao.get(j).getIdSubcondicao1() == null) &&(listaGruporelacao.get(j).getIdSubcondicao2() != null)){
                    if(listaGruporelacao.get(j).getIdSubcondicao2() == listaCondicoesMarcadas.get(i).getIdCondicao()){
                        temRelacao = true;
                    }
                } else if((listaGruporelacao.get(j).getIdSubcondicao1() != null) && (listaGruporelacao.get(j).getIdSubcondicao2() == null)){
                    if(listaGruporelacao.get(j).getIdSubcondicao1() == listaCondicoesMarcadas.get(i).getIdCondicao()){
                        temRelacao = true;
                    }
                
                    
                
            
            
                }
            
            
            
         }
            
            if(temRelacao == false){
                existe = true;
            }
            
         }
         }
         
         if(existe){
             return true;
         } else {
             return false;
         }
         
         /*
         if(temRelacao){
             return false;
         } else if((temRelacao == false) && (listaCondicoesMarcadas.size() > 0)){
             return true;
         } else {
             return false;
         }
         */
        
     }
     
     public List<Gruporelacao> getListaGrupoRelacaoFinal(){
         List<Gruporelacao> listaRelacoesFinais = new ArrayList<Gruporelacao>();
         boolean eRelacaoFinal = true;
         if(listaGruporelacao != null){
         for(int i = 0; i < listaGruporelacao.size(); i++ ){
             for(int k = 0; k < listaGruporelacao.size(); k++){
                 if((listaGruporelacao.get(i).getIdGrupo() == listaGruporelacao.get(k).getIdRelacao1()) 
                         || (listaGruporelacao.get(i).getIdGrupo() == listaGruporelacao.get(k).getIdRelacao2())){
                 eRelacaoFinal = false;
             }
             }
             if(eRelacaoFinal){
                listaRelacoesFinais.add(listaGruporelacao.get(i));
             } else {
                 eRelacaoFinal = true;
             }
         }
     }
         return listaRelacoesFinais;
     }
     
     public boolean getListaRelacoesFinais (List<Gruporelacao> listaR, boolean riscoOcorreu){
         List<Gruporelacao> listaRR = new ArrayList<Gruporelacao>();
          
         
         for(int i = 0; i < listaR.size(); i++){
             int contadorRelacoes = 0;
             for(int j = 0; j < listaR.size(); j++){
                 if((listaR.get(j).getIdRelacao1() == listaR.get(i).getIdGrupo()) || (listaR.get(j).getIdRelacao2() == listaR.get(i).getIdGrupo())){
                     contadorRelacoes = contadorRelacoes + 1;
                     listaRR.add(listaR.get(j));
                 }
             }
             
             if(listaRR.size() > 0){
                 return getListaRelacoesFinais(listaRR, riscoOcorreu);
             } else {
                 for(int k=0; k < listaR.size(); k++){
                     if(checarRelacao(listaR.get(k))){
                         riscoOcorreu = true;
                     }
                 }
             }
             
         }
      
         return riscoOcorreu;
     }
     
         
     private boolean checarRelacao(Gruporelacao relacao){
         
         boolean riscoOcorreu = false;
         
         // checar ocorrencia se a relacao for entre condicao1 e condicao 2  e o tipo for "E"
         if((relacao.getIdSubcondicao1() != null) && (relacao.getIdSubcondicao2() != null) && (relacao.getRelacao().equals("E"))){
             
             boolean condicao1 = false;
             boolean condicao2 = false;
             
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao1()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao1 = true;
                     }
                 }
             }
             
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao2()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao2 = true;
                     }
                 }
             }
             
             if(condicao1 && condicao2){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao1 e condicao 2  e o tipo for "OU"
         if((relacao.getIdSubcondicao1() != null) && (relacao.getIdSubcondicao2() != null) && (relacao.getRelacao().equals("OU"))){
             
             boolean condicao1 = false;
             boolean condicao2 = false;
             
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao1()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao1 = true;
                     }
                 }
             }
             
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao2()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao2 = true;
                     }
                 }
             }
             
             if(condicao1 || condicao2){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao1 e relacao1  e o tipo for "E"
         if((relacao.getIdSubcondicao1() != null) && (relacao.getIdRelacao1() != null) && (relacao.getRelacao().equals("E"))){
             
             boolean condicao1 = false;
             boolean relacao1 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao1()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao1 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao1()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao1 = true;
                     }
                 }
             }
             
             if(condicao1 && relacao1){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao1 e relacao1  e o tipo for "OU"
         if((relacao.getIdSubcondicao1() != null) && (relacao.getIdRelacao1() != null) && (relacao.getRelacao().equals("OU"))){
             
             boolean condicao1 = false;
             boolean relacao1 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao1()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao1 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao1()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao1 = true;
                     }
                 }
             }
             
             if(condicao1 || relacao1){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao1 e relacao2  e o tipo for "E"
         if((relacao.getIdSubcondicao1() != null) && (relacao.getIdRelacao2() != null) && (relacao.getRelacao().equals("E"))){
             
             boolean condicao1 = false;
             boolean relacao2 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao1()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao1 = true;
                     }
                 }
             }
             
             // checando relacao2
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao2()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao2 = true;
                     }
                 }
             }
             
             if(condicao1 && relacao2){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao1 e relacao2  e o tipo for "OU"
         if((relacao.getIdSubcondicao1() != null) && (relacao.getIdRelacao2() != null) && (relacao.getRelacao().equals("OU"))){
             
             boolean condicao1 = false;
             boolean relacao2 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao1()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao1 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao2()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao2 = true;
                     }
                 }
             }
             
             if(condicao1 || relacao2){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao2 e relacao1  e o tipo for "E"
         if((relacao.getIdSubcondicao2() != null) && (relacao.getIdRelacao1() != null) && (relacao.getRelacao().equals("E"))){
             
             boolean condicao2 = false;
             boolean relacao1 = false;
             
             // checando condicao2
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao2()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao2 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao1()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao1 = true;
                     }
                 }
             }
             
             if(condicao2 && relacao1){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao2 e relacao1  e o tipo for "OU"
         if((relacao.getIdSubcondicao2() != null) && (relacao.getIdRelacao1() != null) && (relacao.getRelacao().equals("OU"))){
             
             boolean condicao2 = false;
             boolean relacao1 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao2()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao2 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao1()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao1 = true;
                     }
                 }
             }
             
             if(condicao2 || relacao1){
                 riscoOcorreu = true;
             }
             
         }
         
         // checar ocorrencia se a relacao for entre condicao2 e relacao2  e o tipo for "E"
         if((relacao.getIdSubcondicao2() != null) && (relacao.getIdRelacao1() != null) && (relacao.getRelacao().equals("E"))){
             
             boolean condicao2 = false;
             boolean relacao2 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao2()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao2 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao2()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao2 = true;
                     }
                 }
             }
             
             if(condicao2 && relacao2){
                 riscoOcorreu = true;
             }
             
         }
         
         
         // checar ocorrencia se a relacao for entre condicao2 e relacao2  e o tipo for "OU"
         if((relacao.getIdSubcondicao2() != null) && (relacao.getIdRelacao1() != null) && (relacao.getRelacao().equals("OU"))){
             
             boolean condicao2 = false;
             boolean relacao2 = false;
             
             // checando condicao1
             for(int u=0; u < listaCondicaoTabela.size(); u++){
                 if(listaCondicaoTabela.get(u).getIdCondicao() == relacao.getIdSubcondicao2()){
                     if(listaCondicaoTabela.get(u).isStatusCondicao()){
                         condicao2 = true;
                     }
                 }
             }
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao2()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao2 = true;
                     }
                 }
             }
             
             if(condicao2 || relacao2){
                 riscoOcorreu = true;
             }
             
         }
         
         
         // checar ocorrencia se a relacao for entre relacao1 e relacao2  e o tipo for "E"
         if((relacao.getIdRelacao1() != null) && (relacao.getIdRelacao2() != null) && (relacao.getRelacao().equals("E"))){
             
             boolean relacao1 = false;
             boolean relacao2 = false;
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao1()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao1 = true;
                     }
                 }
             }
             
             // checando relacao2
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao2()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao2 = true;
                     }
                 }
             }
             
             if(relacao1 && relacao2){
                 riscoOcorreu = true;
             }
             
         }
         
         
         // checar ocorrencia se a relacao for entre relacao1 e relacao2  e o tipo for "OU"
         if((relacao.getIdRelacao1() != null) && (relacao.getIdRelacao2() != null) && (relacao.getRelacao().equals("OU"))){
             
             boolean relacao1 = false;
             boolean relacao2 = false;
             
             // checando relacao1
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao1()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao1 = true;
                     }
                 }
             }
             
             // checando relacao2
             for(int u=0; u < listaGruporelacao.size(); u++){
                 if(listaGruporelacao.get(u).getIdGrupo() == relacao.getIdRelacao2()){
                     if(checarRelacao(listaGruporelacao.get(u))){
                         relacao2 = true;
                     }
                 }
             }
             
             if(relacao1 || relacao2){
                 riscoOcorreu = true;
             }
             
         }
         
         
              
         return riscoOcorreu;
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        identificacaRiscoJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoRiscoJTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tabelaCondicoesRiscoJScrollPane = new javax.swing.JScrollPane();
        confirmarJButton = new javax.swing.JButton();
        statusRiscoJComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        fechar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        riscoOcorreuJLabel = new javax.swing.JLabel();

        jLabel1.setText("Identificação do Risco:");

        jLabel2.setText("Descrição do Risco:");

        descricaoRiscoJTextArea.setEditable(false);
        descricaoRiscoJTextArea.setColumns(20);
        descricaoRiscoJTextArea.setRows(5);
        jScrollPane1.setViewportView(descricaoRiscoJTextArea);

        jLabel4.setText("Condições para a ocorrência do risco:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaCondicoesRiscoJScrollPane)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaCondicoesRiscoJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
        );

        confirmarJButton.setText("Confirmar Ocorrências");
        confirmarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarJButtonActionPerformed(evt);
            }
        });

        statusRiscoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Novo", "Contingenciando" }));
        statusRiscoJComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusRiscoJComboBoxItemStateChanged(evt);
            }
        });
        statusRiscoJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusRiscoJComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Próximo status do Risco:");

        fechar.setText("Fechar");
        fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharActionPerformed(evt);
            }
        });

        jLabel5.setText("Risco ocorreu:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(confirmarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(identificacaRiscoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(riscoOcorreuJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(fechar)
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusRiscoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(identificacaRiscoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(riscoOcorreuJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusRiscoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechar)
                .addContainerGap())
        );

        statusRiscoJComboBox.getAccessibleContext().setAccessibleName("");
        statusRiscoJComboBox.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarJButtonActionPerformed

       
        
        
        for(int i = 0; i < listaCondicaoTabela.size(); i++){
            if((Boolean)modeloTabelaCondicoes.getValueAt(i, 0) == true){
                
                listaCondicao.get(i).setStatusSubcondicao("Ocorrido");
                monitoracaoAnaliseDosRiscosFacade.editCondicao(listaCondicao.get(i));

            } else {
                
                listaCondicao.get(i).setStatusSubcondicao("Não Ocorrido");
                monitoracaoAnaliseDosRiscosFacade.editCondicao(listaCondicao.get(i));

            }

        }
        
        
        

        
        //if(riscoSel.getStatusRisco().equals("Contingenciando")){
        if(riscoOcorreu){
            Historicorisco historico = new Historicorisco();
            Calendar c = Calendar.getInstance();
            historico.setDataOcorrencia(c.getTime());
            historico.setIdRisco(riscoSel);
            historico.setSubcondicaoList(getSubcondicoesMarcadas());
            monitoracaoAnaliseDosRiscosFacade.criarHistorico(historico);

        } 
        
        if(riscoSel.getStatusRisco().equals("Novo")){
            Historicoalteracao historico = new Historicoalteracao();
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSel);
            historico.setDescricaoAlteracao("Status do Risco Alterado para 'Novo'.");
            monitoracaoAnaliseDosRiscosFacade.criarHistoricoAlteracao(historico);

        } else if(riscoSel.getStatusRisco().equals("Contingenciando")){
            Historicoalteracao historico = new Historicoalteracao();
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSel);
            historico.setDescricaoAlteracao("Status do Risco Alterado para 'Contingenciando'.");
            monitoracaoAnaliseDosRiscosFacade.criarHistoricoAlteracao(historico);
        }
        
        
        if(statusRiscoJComboBox.getSelectedItem().equals("Novo")){
            riscoSel.setStatusRisco("Novo");
            monitoracaoAnaliseDosRiscosFacade.editRisco(riscoSel);
        } else if (statusRiscoJComboBox.getSelectedItem().equals("Contingenciando")){
            riscoSel.setStatusRisco("Contingenciando");
            monitoracaoAnaliseDosRiscosFacade.editRisco(riscoSel);
        }
        
            PrincipalJFrame.monitoracaoAnaliseDosRiscosJPanel.criarTabelaAnalisarRiscos();
            MonitoracaoAnaliseDosRiscosFacade analiseFacade = new MonitoracaoAnaliseDosRiscosFacade();
            List<Risco> listaDeRisco = analiseFacade.listarRiscosPOrdemGrauDeEsposicaoByStatus(riscoSel.getContem().getProjeto(), "Mitigando");
            PrincipalJFrame.monitoracaoAnaliseDosRiscosJPanel.populaTabelaDeRiscos(listaDeRisco, maximizable);
            PrincipalJFrame.monitoracaoAnaliseDosRiscosJPanel.definirEventosTabelaDeRiscos();
            JOptionPane.showMessageDialog(this, "Risco analisado.");
            this.setVisible(false);
        
          
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarJButtonActionPerformed

    private void fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_fecharActionPerformed

    private void statusRiscoJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusRiscoJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusRiscoJComboBoxActionPerformed

    private void statusRiscoJComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusRiscoJComboBoxItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_statusRiscoJComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmarJButton;
    private javax.swing.JTextArea descricaoRiscoJTextArea;
    private javax.swing.JButton fechar;
    private javax.swing.JLabel identificacaRiscoJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel riscoOcorreuJLabel;
    private javax.swing.JComboBox statusRiscoJComboBox;
    private javax.swing.JScrollPane tabelaCondicoesRiscoJScrollPane;
    // End of variables declaration//GEN-END:variables

}
