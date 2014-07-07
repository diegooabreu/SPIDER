/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import facade.RiscosRiscosOcorridosFacade;
import facade.RiscosSelecionarRiscosParaMonitorarFacade;
import facade.TabelaMonitorarRiscosTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Risco;
import view.tabelas.RiscoTabela;
import view.tabelas.RiscoTabelaModel;
import testetabela.TesteTabelaRisco;

/**
 *
 * @author Diego
 */
public class RiscosSelecionarRiscosParaMonitorarJFrame extends javax.swing.JFrame {

    RiscosSelecionarRiscosParaMonitorarFacade riscosSelecionarRiscosParaMonitorarFacade = new RiscosSelecionarRiscosParaMonitorarFacade();
    RiscosRiscosOcorridosFacade riscosOcorridosFacade = new RiscosRiscosOcorridosFacade();
    
    private JTable tabelaSelecionarRiscosMonitorarJTable;
    private RiscoTabelaModel riscoTabelaModel;
    
    
    List<RiscoTabela> listaRiscosTabela = null;
    List<Risco> listaRiscos = null;
    
    /**
     * Creates new form SelecionarRiscosParaMonitorarJFrame
     */
    public RiscosSelecionarRiscosParaMonitorarJFrame() {
        initComponents();
        //criarTabelaSelecionarRiscos(riscosOcorridosFacade.getListaDeRiscos());
        //tabelaJPanel.add(new JScrollPane(getTabelaSelecionarRiscosMonitorarJTable(riscosOcorridosFacade.getListaDeRiscos())));
        criarTabelaSelecionarRiscos(riscosOcorridosFacade.getListaDeRiscos());
        
    }

    
    public Class<?> getColumnClass(int columnIndex) {       
        if (columnIndex == 0) 
            return Boolean.class;
        else 
            return String.class;
    }
    
    public void criarTabelaSelecionarRiscos(List<Risco> listaDeRiscos){
        
        tabelaSelecionarRiscosMonitorarJTable = new JTable();
        riscoTabelaModel = new RiscoTabelaModel();
        
        tabelaSelecionarRiscosMonitorarJTable.setModel(riscoTabelaModel);
        
        tabelaJScrollPane.setViewportView(tabelaSelecionarRiscosMonitorarJTable);
        
        listaRiscos = listaDeRiscos;
        
        riscoTabelaModel.addListaDeRiscos(criaListaDeRiscoTabela(listaRiscos));
        
        tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(1).setMinWidth(0);
        tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
        tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(2).setMaxWidth(0);
        tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(2).setMinWidth(0);
        tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        
    }
    
    private JTable getTabelaSelecionarRiscosMonitorarJTable(List<Risco> listaDeRiscos) {
        if (tabelaSelecionarRiscosMonitorarJTable == null) {
            tabelaSelecionarRiscosMonitorarJTable = new JTable();
            tabelaSelecionarRiscosMonitorarJTable.setModel(getRiscoTabelaModel(listaDeRiscos));
        }
        return tabelaSelecionarRiscosMonitorarJTable;
    }
    
    private RiscoTabelaModel getRiscoTabelaModel(List<Risco> listaDeRiscos) {
        if (riscoTabelaModel == null) {
            riscoTabelaModel = new RiscoTabelaModel(criaListaDeRiscoTabela(listaDeRiscos));
        }
        return riscoTabelaModel;
    }
    
    // cria uma lista de <RiscoTabela> para ser criada a tabela a partir de uma lista de <Risco>
    private List<RiscoTabela> criaListaDeRiscoTabela(List<Risco> listaRiscos) {
        listaRiscosTabela = new ArrayList<RiscoTabela>();
        
        for(int i=0; i < listaRiscos.size(); i++){
            RiscoTabela riscoTabela = new RiscoTabela();
            riscoTabela.setDataIdentificacao(listaRiscos.get(i).getDataIdentificacao());
            riscoTabela.setDescricao(listaRiscos.get(i).getDescricao());
            riscoTabela.setEmissor(listaRiscos.get(i).getEmissor());
            riscoTabela.setGrauSeveridade(listaRiscos.get(i).getGrauSeveridade());
            riscoTabela.setIdRisco(listaRiscos.get(i).getIdRisco());
            riscoTabela.setIdentificacao(listaRiscos.get(i).getIdentificacao());
            riscoTabela.setImpacto(listaRiscos.get(i).getImpacto());
            riscoTabela.setPrioridade(listaRiscos.get(i).getPrioridade());
            riscoTabela.setProbabilidade(listaRiscos.get(i).getProbabilidade());
            riscoTabela.setStatusRisco(listaRiscos.get(i).getStatusRisco());
            
            if(listaRiscos.get(i).getStatusRisco().equals("Mitigando")){
                riscoTabela.setMonitorar(true);
            }
            
            listaRiscosTabela.add(riscoTabela);
            
        }
        
        return listaRiscosTabela;
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        monitorarJButton = new javax.swing.JButton();
        tabelaJPanel = new javax.swing.JPanel();
        tabelaJScrollPane = new javax.swing.JScrollPane();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Selecione os Riscos que deseja Monitorar:");

        monitorarJButton.setText("Monitorar Riscos Selecionados");
        monitorarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorarJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabelaJPanelLayout = new javax.swing.GroupLayout(tabelaJPanel);
        tabelaJPanel.setLayout(tabelaJPanelLayout);
        tabelaJPanelLayout.setHorizontalGroup(
            tabelaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaJScrollPane)
        );
        tabelaJPanelLayout.setVerticalGroup(
            tabelaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(500, Short.MAX_VALUE)
                        .addComponent(monitorarJButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(0, 466, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabelaJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monitorarJButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monitorarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorarJButtonActionPerformed

        for(int i=0; i < listaRiscosTabela.size(); i++){
            if((Boolean)riscoTabelaModel.getValueAt(i, 0) == true){
                
                // verificando se existem planos de mitigação para o risco selecionado e se nao existir, exigir a criação
                
                
                
                listaRiscosTabela.get(i).setStatusRisco("Mitigando");
                listaRiscos.get(i).setStatusRisco("Mitigando");
                riscosSelecionarRiscosParaMonitorarFacade.editRisco(listaRiscos.get(i));
                
            } else {
                listaRiscosTabela.get(i).setStatusRisco("Novo");
                listaRiscos.get(i).setStatusRisco("Novo");
                
                riscosSelecionarRiscosParaMonitorarFacade.editRisco(listaRiscos.get(i));
            }
        }
       
        criarTabelaSelecionarRiscos(listaRiscos);
        
        JOptionPane.showMessageDialog(this, "Riscos selecionados agora estão sendo monitorados, seus planos de mitigação agora são\ntarefas a serem realizadas.");
// TODO add your handling code here:
    }//GEN-LAST:event_monitorarJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RiscosSelecionarRiscosParaMonitorarJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiscosSelecionarRiscosParaMonitorarJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiscosSelecionarRiscosParaMonitorarJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiscosSelecionarRiscosParaMonitorarJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiscosSelecionarRiscosParaMonitorarJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton monitorarJButton;
    private javax.swing.JPanel tabelaJPanel;
    private javax.swing.JScrollPane tabelaJScrollPane;
    // End of variables declaration//GEN-END:variables
}
