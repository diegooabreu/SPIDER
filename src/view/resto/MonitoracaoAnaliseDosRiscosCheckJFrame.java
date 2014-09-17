/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.resto;

import facade.MonitoracaoAnaliseDosRiscosFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Historicoalteracao;
import model.Historicorisco;
import model.Risco;
import model.Subcondicao;
import view.tabelas.CondicaoTabela;
import view.tabelas.CondicaoTabelaModel;

/**
 *
 * @author Diego
 */
public class MonitoracaoAnaliseDosRiscosCheckJFrame extends javax.swing.JFrame {

    MonitoracaoAnaliseDosRiscosFacade monitoracaoAnaliseDosRiscosFacade = new MonitoracaoAnaliseDosRiscosFacade();
    
    JTable tabelaCondicoes;
    CondicaoTabelaModel modeloTabelaCondicoes;
    
    List<CondicaoTabela> listaCondicaoTabela;
    List<Subcondicao> listaCondicao;
    
    Risco riscoSel;
    /**
     * Creates new form MonitoracaoAnaliseDosRiscosCheckJFrame
     */
    public MonitoracaoAnaliseDosRiscosCheckJFrame() {
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
        
        /*
        tabelaCondicoes = new JTable();
        
        
        listaCondicao = monitoracaoAnaliseDosRiscosFacade.getListaSubcondicoes(risco);
        listaCondicaoTabela = null;
        
        if(listaCondicao.size() > 0){
            criarListaCondicaoTabela(listaCondicao);  
            modeloTabelaCondicoes = new CondicaoTabelaModel(listaCondicaoTabela);
            tabelaCondicoes.setModel(modeloTabelaCondicoes);
        } else {
            modeloTabelaCondicoes = new CondicaoTabelaModel();
            tabelaCondicoes.setModel(modeloTabelaCondicoes);
        }
        
        
        tabelaCondicoesRiscoJScrollPane.setViewportView(tabelaCondicoes);
        */
     
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
        
        /*
        for(int i=0; i < listaSubcondicoes.size(); i++){
            //CondicaoTabela novoCondicaoTabela = null;
            
            //novoCondicaoTabela.setDescricaoCondicao(listaSubcondicoes.get(i).getDescricaoSubcondicao());
            //novoCondicaoTabela.setIdCondicao(listaSubcondicoes.get(i).getIdSubcondicao());
            //novoCondicaoTabela.setIdentificacaoCondicao(listaSubcondicoes.get(i).getIdentificacaoSubcondicao());
            
            String descricao = listaSubcondicoes.get(i).getDescricaoSubcondicao();
            int id = listaSubcondicoes.get(i).getIdSubcondicao();
            String identificacao = listaSubcondicoes.get(i).getIdentificacaoSubcondicao();
            
            boolean status;
            
            if(listaSubcondicoes.get(i).getStatusSubcondicao().equals("Ocorrido")){
                //novoCondicaoTabela.setStatusCondicao(true);
                status = true;
            } else {
                //novoCondicaoTabela.setStatusCondicao(false);
                status = false;
            }
            
            CondicaoTabela novoCondicaoTabela = new CondicaoTabela(descricao, id, identificacao, status);
            
            listaCondicaoTabela.add(novoCondicaoTabela);
            
        }
        */
    }
    
    public void preencherInformacoes(Risco risco){
        riscoSel  = risco;
        identificacaRiscoJLabel.setText(risco.getIdentificacao());
        descricaoRiscoJTextArea.setText(risco.getDescricao());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        statusRiscoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Novo", "Contingenciando" }));

        jLabel3.setText("Próximo status do Risco:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmarJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(identificacaRiscoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusRiscoJComboBox, 0, 376, Short.MAX_VALUE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusRiscoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarJButtonActionPerformed

       
        
        for(int i = 0; i < listaCondicaoTabela.size(); i++){
            if((Boolean)modeloTabelaCondicoes.getValueAt(i, 0) == true){
                listaCondicao.get(i).setStatusSubcondicao("Ocorrido");
                
                if(statusRiscoJComboBox.getSelectedItem().equals("Novo")){
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
        
        
        JOptionPane.showMessageDialog(this, "Risco analisado.");
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MonitoracaoAnaliseDosRiscosCheckJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitoracaoAnaliseDosRiscosCheckJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitoracaoAnaliseDosRiscosCheckJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitoracaoAnaliseDosRiscosCheckJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitoracaoAnaliseDosRiscosCheckJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmarJButton;
    private javax.swing.JTextArea descricaoRiscoJTextArea;
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
