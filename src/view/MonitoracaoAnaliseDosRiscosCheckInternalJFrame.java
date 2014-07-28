/*
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
    
    public MonitoracaoAnaliseDosRiscosCheckInternalJFrame() {
        initComponents();
    }
    
    public void definirEventosTabelaCondicoes(){
        tabelaCondicoes.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 1) {

                    
                    
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
                    
                    if(checagemOcorrenciaRisco(riscoSel, listaDeCondicoesMarcadas)){
                        statusRiscoJComboBox.setSelectedItem("Contingenciando");
                    }
                    

                }
            }
        });
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
    }
    
//     public String statusRisco (Risco risco){
//          List<Subcondicao> listaSubcondicao = monitoracaoAnaliseDosRiscosFacade.getListaSubcondicoes(risco);
//          String statusCondicao = null;
//          
//          for (int i=0; i < listaSubcondicao.size(); i++){
//              if (Objects.equals(listaSubcondicao.get(i).getIdRisco().getIdRisco(), risco.getIdRisco())){
//                 statusCondicao = listaSubcondicao.get(i).getStatusSubcondicao();
//              }
//          }
//         return statusCondicao;
//     }
     
     List<Gruporelacao> listaGruporelacao = null;
     public boolean checagemOcorrenciaRisco (Risco riscoSelecionado, List<CondicaoTabela> listaCondicao2){
         boolean riscoOcorreu = false; 
         RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
              
             this.listaGruporelacao = riscosGerenciarRiscosFacade.getListaGrupoRelacaoByRisco(riscoSelecionado);
            
             // verificando condições independentes (que não possuem nenhuma relação)
         boolean temRelacao = false;
         for (int i=0; i < listaCondicao2.size(); i++){
            
            for (int j=0; j < listaGruporelacao.size(); j++){
                if (listaGruporelacao.get(j).getIdSubcondicao1() == listaCondicao2.get(i).getIdCondicao()
                        || listaGruporelacao.get(j).getIdSubcondicao2() == listaCondicao2.get(i).getIdCondicao()){
                   temRelacao = true;
                }
            }
         }
         
         if (temRelacao == false){
             return true;
         }
         
         return riscoOcorreu;
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
                     if(checarOcorrenciaPorRelacao(listaR.get(k))){
                         riscoOcorreu = true;
                     }
                 }
             }
             
         }
      
         return riscoOcorreu;
     }
     
      
     
     private boolean checarOcorrenciaPorRelacao(Gruporelacao relacao){
         
         boolean riscoOcorreu = false;
         
         int idCondicaoOperando1 = 0;
         int idCondicaoOperando2 = 0;
         int idRelacaoOperando1 = 0;
         int idRelacaoOperando2 = 0;
         
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
         
         /*
         if(relacao.getIdSubcondicao1() != null){
             idCondicaoOperando1 = relacao.getIdSubcondicao1();
         } 
         
         if(relacao.getIdSubcondicao2() != null){
             idCondicaoOperando2 = relacao.getIdSubcondicao2();
         }
         
         if(relacao.getIdRelacao1() != null){
             idRelacaoOperando1 = relacao.getIdRelacao1();
         }
         
         if(relacao.getIdRelacao2() != null){
             idRelacaoOperando2 = relacao.getIdRelacao2();
         }
         */
         
         
         
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

        statusRiscoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Novo", "Contingenciando" }));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmarJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(identificacaRiscoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusRiscoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(fechar)
                .addContainerGap(248, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusRiscoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarJButtonActionPerformed

        for(int i = 0; i < listaCondicaoTabela.size(); i++){
            if((Boolean)modeloTabelaCondicoes.getValueAt(i, 0) == true){
                listaCondicao.get(i).setStatusSubcondicao("Ocorrido");

                if (statusRiscoJComboBox.getSelectedItem().equals("Novo")){
                    listaCondicao.get(i).getIdRisco().setStatusRisco("Novo");
                } else {
                    listaCondicao.get(i).getIdRisco().setStatusRisco("Contingenciando");
                }

                monitoracaoAnaliseDosRiscosFacade.editRisco(listaCondicao.get(i).getIdRisco());
                monitoracaoAnaliseDosRiscosFacade.editCondicao(listaCondicao.get(i));

            } else {
                listaCondicao.get(i).setStatusSubcondicao("Não Ocorrido");

                if(statusRiscoJComboBox.getSelectedItem().equals("Novo")){
                    listaCondicao.get(i).getIdRisco().setStatusRisco("Novo");
                } else {
                    listaCondicao.get(i).getIdRisco().setStatusRisco("Contingenciando");
                }

                monitoracaoAnaliseDosRiscosFacade.editRisco(listaCondicao.get(i).getIdRisco());
                monitoracaoAnaliseDosRiscosFacade.editCondicao(listaCondicao.get(i));

            }

        }

        riscoSel = monitoracaoAnaliseDosRiscosFacade.getRisco(riscoSel.getIdRisco());

        if(riscoSel.getStatusRisco().equals("Contingenciando")){
            Historicorisco historico = new Historicorisco();
            Calendar c = Calendar.getInstance();
            historico.setDataOcorrencia(c.getTime());
            historico.setIdRisco(riscoSel);
            monitoracaoAnaliseDosRiscosFacade.criarHistorico(historico);

        } else if(riscoSel.getStatusRisco().equals("Novo")){
            Historicoalteracao historico = new Historicoalteracao();
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSel);
            historico.setDescricaoAlteracao("Status do Risco Alterado para 'Novo'.");
            monitoracaoAnaliseDosRiscosFacade.criarHistoricoAlteracao(historico);

        }

        if (statusRiscoJComboBox.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Escolha um Status para o Risco.");
            this.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Risco analisado.");
            this.setVisible(false);
        }
          
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox statusRiscoJComboBox;
    private javax.swing.JScrollPane tabelaCondicoesRiscoJScrollPane;
    // End of variables declaration//GEN-END:variables

}
