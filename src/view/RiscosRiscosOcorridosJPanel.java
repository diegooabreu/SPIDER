/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import facade.RiscosRiscosOcorridosFacade;
import facade.RiscosSelecionarRiscosParaMonitorarFacade;
import javax.swing.JFrame;

/**
 *
 * @author Victor
 */
public class RiscosRiscosOcorridosJPanel extends javax.swing.JPanel {

    RiscosRiscosOcorridosFacade riscosOcorridosFacade = new RiscosRiscosOcorridosFacade();
    
    /**
     * Creates new form RiscosRiscosOcorridosJPanel
     */
    public RiscosRiscosOcorridosJPanel() {
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

        RiscosRiscosOcorridosJPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        RiscosRiscosOcorridosJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Riscos Ocorridos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jButton1.setText("selecionar riscos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout RiscosRiscosOcorridosJPanelLayout = new javax.swing.GroupLayout(RiscosRiscosOcorridosJPanel);
        RiscosRiscosOcorridosJPanel.setLayout(RiscosRiscosOcorridosJPanelLayout);
        RiscosRiscosOcorridosJPanelLayout.setHorizontalGroup(
            RiscosRiscosOcorridosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiscosRiscosOcorridosJPanelLayout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RiscosRiscosOcorridosJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RiscosRiscosOcorridosJPanelLayout.setVerticalGroup(
            RiscosRiscosOcorridosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiscosRiscosOcorridosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RiscosRiscosOcorridosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RiscosRiscosOcorridosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RiscosRiscosOcorridosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        RiscosSelecionarRiscosParaMonitorarJFrame selecionar = new RiscosSelecionarRiscosParaMonitorarJFrame();
        
        RiscosSelecionarRiscosParaMonitorarFacade selecionarRiscosFacade = new RiscosSelecionarRiscosParaMonitorarFacade();
        
        //selecionar.criarTabelaSelecionarRiscos(selecionarRiscosFacade.getListaDeRiscosDoProjeto(projetoSelecionado));
        selecionar.setLocationRelativeTo(this);
        selecionar.setVisible(true);
        selecionar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RiscosRiscosOcorridosJPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
