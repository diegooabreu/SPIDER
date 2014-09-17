package view;

import java.text.DateFormat;
import java.util.List;
import model.Planocontingencia;
import model.Planomitigacao;

/**
 *
 * @author BlenoVale
 */
public class MonitorarPlanosPendentesMaisInformaçõesDialog extends javax.swing.JDialog {

    List<Planomitigacao> listaPlanoMitigacaoAtualizar;
    List<Planocontingencia> listaPlanoContingenciaAtualizar;
    private int idPlanoAtualizaMitigacao;
    private int idPlanoAtualizaContingencia;

    public MonitorarPlanosPendentesMaisInformaçõesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    void preencheTelaParaPlanoDeMitigacao(Planomitigacao planoMitigacao) {
        DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dataStr;

        identificacaoDoPlanoJTextField.setText(planoMitigacao.getIdentificacaoPlanoMitigacao());
        responsavelPeloPlanoJTextField.setText(planoMitigacao.getResponsavel());
        if (planoMitigacao.getIdMarcoDoProjeto() == null) {
            dataStr = dataFormatada.format(planoMitigacao.getIdPontoDeControle().getDataPontoControle());
            dataLimiteJTextField.setText(dataStr);
        } else if (planoMitigacao.getIdPontoDeControle() == null) {
            dataStr = dataFormatada.format(planoMitigacao.getIdMarcoDoProjeto().getDataMarcoProjeto());
            dataLimiteJTextField.setText(dataStr);
        }
        descricaoJTextArea.setText(planoMitigacao.getDescricaoPlanoMitigacao());
        comoSeraFeitoJTextArea.setText(planoMitigacao.getComoRealizar());
        informacoesAdicionaisJTextArea.setText(planoMitigacao.getInformacoesAdicionais());
    }

    void preencheTelaParaPlanoDeContingencia(Planocontingencia planoContigencia) {
        DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dataStr;

        identificacaoDoPlanoJTextField.setText(planoContigencia.getIdentificacaoPlanoContingencia());
        responsavelPeloPlanoJTextField.setText(planoContigencia.getResponsavel());
        if (planoContigencia.getIdMarcoDoProjeto() == null) {
            dataStr = dataFormatada.format(planoContigencia.getIdPontoDeControle().getDataPontoControle());
            dataLimiteJTextField.setText(dataStr);
        } else if (planoContigencia.getIdPontoDeControle() == null) {
            dataStr = dataFormatada.format(planoContigencia.getIdMarcoDoProjeto().getDataMarcoProjeto());
            dataLimiteJTextField.setText(dataStr);
        }
        descricaoJTextArea.setText(planoContigencia.getDescricaoPlanoContingencia());
        comoSeraFeitoJTextArea.setText(planoContigencia.getComoRealizar());
        informacoesAdicionaisJTextArea.setText(planoContigencia.getInformacoesAdicionais());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoJTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        comoSeraFeitoJTextArea = new javax.swing.JTextArea();
        identificacaoDoPlanoJLabel = new javax.swing.JLabel();
        responsavelPeloPlanoJTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        informacoesAdicionaisJTextArea = new javax.swing.JTextArea();
        DataLimiteJLabel = new javax.swing.JLabel();
        responsavelPeloPlanoJLabel = new javax.swing.JLabel();
        identificacaoDoPlanoJTextField = new javax.swing.JTextField();
        dataLimiteJTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Planos Pendentes"));

        descricaoJTextArea.setEditable(false);
        descricaoJTextArea.setColumns(20);
        descricaoJTextArea.setLineWrap(true);
        descricaoJTextArea.setRows(1);
        descricaoJTextArea.setWrapStyleWord(true);
        descricaoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jScrollPane1.setViewportView(descricaoJTextArea);

        comoSeraFeitoJTextArea.setEditable(false);
        comoSeraFeitoJTextArea.setColumns(20);
        comoSeraFeitoJTextArea.setLineWrap(true);
        comoSeraFeitoJTextArea.setRows(1);
        comoSeraFeitoJTextArea.setWrapStyleWord(true);
        comoSeraFeitoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Como será feito"));
        jScrollPane2.setViewportView(comoSeraFeitoJTextArea);

        identificacaoDoPlanoJLabel.setText("Identificação do Plano");

        responsavelPeloPlanoJTextField.setEditable(false);

        informacoesAdicionaisJTextArea.setEditable(false);
        informacoesAdicionaisJTextArea.setColumns(20);
        informacoesAdicionaisJTextArea.setLineWrap(true);
        informacoesAdicionaisJTextArea.setRows(1);
        informacoesAdicionaisJTextArea.setWrapStyleWord(true);
        informacoesAdicionaisJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais"));
        jScrollPane3.setViewportView(informacoesAdicionaisJTextArea);

        DataLimiteJLabel.setText("Data Limite");

        responsavelPeloPlanoJLabel.setText("Responsavel");

        identificacaoDoPlanoJTextField.setEditable(false);
        identificacaoDoPlanoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificacaoDoPlanoJTextFieldActionPerformed(evt);
            }
        });

        dataLimiteJTextField.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(identificacaoDoPlanoJLabel)
                            .addComponent(responsavelPeloPlanoJLabel)
                            .addComponent(DataLimiteJLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataLimiteJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(responsavelPeloPlanoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(identificacaoDoPlanoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identificacaoDoPlanoJLabel)
                    .addComponent(identificacaoDoPlanoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsavelPeloPlanoJLabel)
                    .addComponent(responsavelPeloPlanoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DataLimiteJLabel)
                    .addComponent(dataLimiteJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void identificacaoDoPlanoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificacaoDoPlanoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificacaoDoPlanoJTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(MonitorarPlanosPendentesMaisInformaçõesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitorarPlanosPendentesMaisInformaçõesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitorarPlanosPendentesMaisInformaçõesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitorarPlanosPendentesMaisInformaçõesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MonitorarPlanosPendentesMaisInformaçõesDialog dialog = new MonitorarPlanosPendentesMaisInformaçõesDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DataLimiteJLabel;
    private javax.swing.JTextArea comoSeraFeitoJTextArea;
    private javax.swing.JTextField dataLimiteJTextField;
    private javax.swing.JTextArea descricaoJTextArea;
    private javax.swing.JLabel identificacaoDoPlanoJLabel;
    private javax.swing.JTextField identificacaoDoPlanoJTextField;
    private javax.swing.JTextArea informacoesAdicionaisJTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel responsavelPeloPlanoJLabel;
    private javax.swing.JTextField responsavelPeloPlanoJTextField;
    // End of variables declaration//GEN-END:variables
}
