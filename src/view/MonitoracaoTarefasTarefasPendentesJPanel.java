/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class MonitoracaoTarefasTarefasPendentesJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MonitoracaoTarefasTarefasPendentesJPanel
     */
    public MonitoracaoTarefasTarefasPendentesJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MonitoracaoTarefasTarefasPendentesJPanel = new javax.swing.JPanel();
        MonitoracaoTarefasTarefasPendentesJScrollPane = new javax.swing.JScrollPane();
        MonitoracaoTarefasTarefasPendentesJTable = new javax.swing.JTable();
        MonitoracaoTarefasTarefasPendentesMarcarFeitaJButton = new javax.swing.JButton();

        MonitoracaoTarefasTarefasPendentesJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tarefas Pendentes", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        MonitoracaoTarefasTarefasPendentesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"M01R01", "30/05/2014", "Visualizar"},
                {"Checar ocorrência de riscos: Ponto 10", "25/05/2014", "Checar"},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Terefa", "Data Limite", "Ações"
            }
        ));
        MonitoracaoTarefasTarefasPendentesJScrollPane.setViewportView(MonitoracaoTarefasTarefasPendentesJTable);

        MonitoracaoTarefasTarefasPendentesMarcarFeitaJButton.setText("Marcar como feita");
        MonitoracaoTarefasTarefasPendentesMarcarFeitaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonitoracaoTarefasTarefasPendentesMarcarFeitaJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MonitoracaoTarefasTarefasPendentesJPanelLayout = new javax.swing.GroupLayout(MonitoracaoTarefasTarefasPendentesJPanel);
        MonitoracaoTarefasTarefasPendentesJPanel.setLayout(MonitoracaoTarefasTarefasPendentesJPanelLayout);
        MonitoracaoTarefasTarefasPendentesJPanelLayout.setHorizontalGroup(
            MonitoracaoTarefasTarefasPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonitoracaoTarefasTarefasPendentesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
            .addGroup(MonitoracaoTarefasTarefasPendentesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonitoracaoTarefasTarefasPendentesMarcarFeitaJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MonitoracaoTarefasTarefasPendentesJPanelLayout.setVerticalGroup(
            MonitoracaoTarefasTarefasPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonitoracaoTarefasTarefasPendentesJPanelLayout.createSequentialGroup()
                .addComponent(MonitoracaoTarefasTarefasPendentesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(MonitoracaoTarefasTarefasPendentesMarcarFeitaJButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonitoracaoTarefasTarefasPendentesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonitoracaoTarefasTarefasPendentesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MonitoracaoTarefasTarefasPendentesMarcarFeitaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonitoracaoTarefasTarefasPendentesMarcarFeitaJButtonActionPerformed

        JOptionPane.showConfirmDialog(this, "Deseja checar os riscos Monitorados?" , "Checar Riscos" , JOptionPane.YES_NO_OPTION);
        
        ChecarRiscosJFrame checar = new ChecarRiscosJFrame();
        checar.setLocationRelativeTo(this);
        checar.setVisible(true);
        checar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
// TODO add your handling code here:
    }//GEN-LAST:event_MonitoracaoTarefasTarefasPendentesMarcarFeitaJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MonitoracaoTarefasTarefasPendentesJPanel;
    private javax.swing.JScrollPane MonitoracaoTarefasTarefasPendentesJScrollPane;
    private javax.swing.JTable MonitoracaoTarefasTarefasPendentesJTable;
    private javax.swing.JButton MonitoracaoTarefasTarefasPendentesMarcarFeitaJButton;
    // End of variables declaration//GEN-END:variables
}
