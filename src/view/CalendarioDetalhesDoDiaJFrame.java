/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import facade.CalendarioDetalhesDoDiaFacade;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class CalendarioDetalhesDoDiaJFrame extends javax.swing.JFrame {

    CalendarioDetalhesDoDiaFacade calendarioDetalhesDoDiaFacade = new CalendarioDetalhesDoDiaFacade();
    
    
    private JTable listaTarefasJTable;
    private DefaultTableModel listaTarefasJTableModel;
    
    /**
     * Creates new form CalendarioDetalhesDoDiaJFrame
     */
    public CalendarioDetalhesDoDiaJFrame() {
        initComponents();
        criarListaDeTarefas();
    }

    public void criarListaDeTarefas(){
        listaTarefasJTable = new JTable();
        listaTarefasJTableModel = new DefaultTableModel();
        listaTarefasJTableModel.setColumnIdentifiers(new Object[]{"Risco" , "Tarefa", "Responsavel" , "Data de Realização"});
        listaTarefasJTable.setModel(listaTarefasJTableModel);
        listaTarefasJScrollPane.setViewportView(listaTarefasJTable);
    }
    
    public void limarTela(){
        nomeMarcoDoProjetoJLabel.setText(null);
        
        
        nomePontoDeControleJLabel.setText(null);
        dataPontoDeControleJLabel.setText(null);
    }
        
    public void mostraInformacoesPontoDeControle(Pontodecontrole ponto){
        if(ponto != null){
            nomePontoDeControleJLabel.setText(ponto.getNomePontoDeControle());
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            Date dataPontoDeControle = ponto.getDataPontoControle();
            dataPontoDeControleJLabel.setText(df.format(dataPontoDeControle));
            descricaoPontoDeCOntroleJTextArea.setText(ponto.getDescricaoPontoControle());
        }
    }
    public void mostraInformacoesMarcoDoProjeto(Marcodoprojeto marco){
        
        if(marco != null){
        nomeMarcoDoProjetoJLabel.setText(marco.getNomeMarcoDoProjeto());
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date dataMarcoDoProjeto = marco.getDataMarcoProjeto();
        dataMarcoDoProjetoJLabel.setText(df.format(dataMarcoDoProjeto));
        descricaoMarcoDoProjetoJTextArea.setText(marco.getDescricaoMarcoProjeto());
        }
    }
    
    public void popularListaDeTarefas(Projeto projetoSelecionado, Date dataLimite){
        
        String dataRealizacaoPMString = "Não realizada";
        String dataRealizacaoPCString = "Não realizada";
        
        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacao = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByDateAndIdProjetoAndStatusRisco(projetoSelecionado, "Mitigando", dataLimite);
        
        for(int i=0; i < listaPlanosDeMitigacao.size(); i++){
            //String[] linha = String[]{listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao()};
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               
            Date dataRealizacaoPM = listaPlanosDeMitigacao.get(i).getDataRealizacao();
            
            if(dataRealizacaoPM != null){
                dataRealizacaoPMString = df.format(dataRealizacaoPM);
            }
            
            Object[] linha = new Object[]{listaPlanosDeMitigacao.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao() ,
                listaPlanosDeMitigacao.get(i).getResponsavel() ,
                dataRealizacaoPMString };
                
            listaTarefasJTableModel.addRow(linha);
            dataRealizacaoPMString = "Não realizada";
            
        }
        
        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        
        List<Planocontingencia> listaPlanosContingencia = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByDateAndIdProjetoAndStatusRisco(projetoSelecionado, "Contingenciando", dataLimite);
        
        for(int i=0; i < listaPlanosContingencia.size(); i++){
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            
            Date dataRealizacaoPC = listaPlanosContingencia.get(i).getDataRealizacao();
            
            if(dataRealizacaoPC != null){
                dataRealizacaoPCString = df.format(dataRealizacaoPCString);
            }
            
            Object[] linha = new Object[]{listaPlanosContingencia.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosContingencia.get(i).getIdentificacaoPlanoContingencia() ,
                listaPlanosContingencia.get(i).getResponsavel() ,
                dataRealizacaoPCString };
            
           
            
            listaTarefasJTableModel.addRow(linha);
            dataRealizacaoPCString = "Não realizada";
            
        }
        
        
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nomePontoDeControleJLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dataPontoDeControleJLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        listaTarefasJScrollPane = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descricaoPontoDeCOntroleJTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        nomeMarcoDoProjetoJLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dataMarcoDoProjetoJLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoMarcoDoProjetoJTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        listaTarefasMarcoJScrollPane = new javax.swing.JScrollPane();
        fecharJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Ponto de Controle:");

        jLabel4.setText("Data do Ponto de Controle:");

        jLabel7.setText("Lista de Tarefas para este ponto de controle:");

        jLabel2.setText("Descrição do Ponto de Controle:");

        descricaoPontoDeCOntroleJTextArea.setEditable(false);
        descricaoPontoDeCOntroleJTextArea.setColumns(20);
        descricaoPontoDeCOntroleJTextArea.setRows(5);
        jScrollPane3.setViewportView(descricaoPontoDeCOntroleJTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataPontoDeControleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomePontoDeControleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(listaTarefasJScrollPane)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(0, 316, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomePontoDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dataPontoDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaTarefasJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ponto de Controle", jPanel2);

        jPanel3.setForeground(new java.awt.Color(240, 240, 240));

        jLabel8.setText("Nome do Marco do Projeto:");

        jLabel3.setText("Data do Marco do Projeto:");

        jLabel5.setText("Descrição do Marco do Projeto:");

        descricaoMarcoDoProjetoJTextArea.setEditable(false);
        descricaoMarcoDoProjetoJTextArea.setColumns(20);
        descricaoMarcoDoProjetoJTextArea.setRows(5);
        jScrollPane1.setViewportView(descricaoMarcoDoProjetoJTextArea);

        jLabel6.setText("Lista de tarefas para este marco:");

        listaTarefasMarcoJScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(listaTarefasMarcoJScrollPane)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dataMarcoDoProjetoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nomeMarcoDoProjetoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nomeMarcoDoProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dataMarcoDoProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaTarefasMarcoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Marco", jPanel3);

        fecharJButton.setText("Fechar");
        fecharJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(fecharJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecharJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecharJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharJButtonActionPerformed

        
        nomePontoDeControleJLabel.setText(null);
        dataPontoDeControleJLabel.setText(null);
        descricaoPontoDeCOntroleJTextArea.setText(null);
        
        nomeMarcoDoProjetoJLabel.setText(null);
        dataMarcoDoProjetoJLabel.setText(null);
        descricaoMarcoDoProjetoJTextArea.setText(null);

        this.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_fecharJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CalendarioDetalhesDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarioDetalhesDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarioDetalhesDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarioDetalhesDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalendarioDetalhesDoDiaJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dataMarcoDoProjetoJLabel;
    private javax.swing.JLabel dataPontoDeControleJLabel;
    private javax.swing.JTextArea descricaoMarcoDoProjetoJTextArea;
    private javax.swing.JTextArea descricaoPontoDeCOntroleJTextArea;
    private javax.swing.JButton fecharJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane listaTarefasJScrollPane;
    private javax.swing.JScrollPane listaTarefasMarcoJScrollPane;
    private javax.swing.JLabel nomeMarcoDoProjetoJLabel;
    private javax.swing.JLabel nomePontoDeControleJLabel;
    // End of variables declaration//GEN-END:variables
}
