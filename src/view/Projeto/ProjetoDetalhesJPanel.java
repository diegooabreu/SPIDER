/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Projeto;

import facade.ProjetoFacade;
import javax.swing.JOptionPane;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class ProjetoDetalhesJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProjetoDetalhesJPanel
     */
    ProjetoFacade projetoFacade = new ProjetoFacade();

    private static Projeto projetoSelecionado;

    public ProjetoDetalhesJPanel() {
        initComponents();
    }

    public void preencheDetalhes(Projeto projeto) {

        projetoSelecionado = projeto;
        nomeProjetoJTextField.setText(projeto.getNomeProjeto());
        responsavelProjetoJTextField.setText(projeto.getResponsavelProjeto());
        responsavelGerenciaRiscosJTextField.setText(projeto.getResponsavelGerenciaRiscos());
        descricaoProjetoJTextArea.setText(projeto.getDescricaoProjeto());
        statusProjetoJLabel.setText("Em andamento");
        if (projeto.getConcluido()) {
            statusProjetoJLabel.setText("Concluído");
        }

    }

    public  void atualizaDetalhesDoProjeto() {
        this.repaint();
    }

//    public void habilitarProjetoDetalhesJPanel() {
//        //nomeProjetoJTextField.setEnabled(true);
//        responsavelProjetoJTextField.setEnabled(true);
//        responsavelGerenciaRiscosJTextField.setEnabled(true);
//        descricaoProjetoJTextArea.setEnabled(true);
//
//        salvarAlteracoesJButton.setEnabled(true);
//    }

    public void habilitarProjetoDetalhesJPanel(Boolean habilita) {
        // nomeProjetoJTextField.setEnabled(false);
        responsavelProjetoJTextField.setEnabled(habilita);
        responsavelGerenciaRiscosJTextField.setEnabled(habilita);
        descricaoProjetoJTextArea.setEnabled(habilita);

        salvarAlteracoesJButton.setEnabled(habilita);
        
        ProjetoConcluirProjetoDialog projetoConcluirProjetoDialog =  new ProjetoConcluirProjetoDialog(null, true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        nomeProjetoJLabel = new javax.swing.JLabel();
        nomeProjetoJTextField = new javax.swing.JTextField();
        responsavelProjetoJLabel = new javax.swing.JLabel();
        responsavelGerenciaRiscosJLabel = new javax.swing.JLabel();
        descricaoProjetoJLabel = new javax.swing.JLabel();
        responsavelProjetoJTextField = new javax.swing.JTextField();
        responsavelGerenciaRiscosJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoProjetoJTextArea = new javax.swing.JTextArea();
        salvarAlteracoesJButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        statusProjetoJLabel = new javax.swing.JLabel();
        removerProjeto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        nomeProjetoJLabel.setText("Nome do Projeto:");

        nomeProjetoJTextField.setEditable(false);
        nomeProjetoJTextField.setEnabled(false);

        responsavelProjetoJLabel.setText("Responsável pelo Projeto:");

        responsavelGerenciaRiscosJLabel.setText("Responsável pela Gerência de Riscos:");

        descricaoProjetoJLabel.setText("Descrição do Projeto:");

        responsavelProjetoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responsavelProjetoJTextFieldActionPerformed(evt);
            }
        });

        descricaoProjetoJTextArea.setColumns(20);
        descricaoProjetoJTextArea.setLineWrap(true);
        descricaoProjetoJTextArea.setRows(5);
        descricaoProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descricaoProjetoJTextArea);

        salvarAlteracoesJButton.setText("Salvar Alterações");
        salvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Status do Projeto:");

        statusProjetoJLabel.setText("status");

        removerProjeto.setText("Remover");
        removerProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerProjetoActionPerformed(evt);
            }
        });

        jButton2.setText("Resumo do Projeto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removerProjeto)
                        .addGap(37, 37, 37)
                        .addComponent(salvarAlteracoesJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descricaoProjetoJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(responsavelProjetoJLabel)
                                    .addComponent(nomeProjetoJLabel))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nomeProjetoJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                    .addComponent(responsavelProjetoJTextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(responsavelGerenciaRiscosJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(responsavelGerenciaRiscosJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 428, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeProjetoJLabel)
                    .addComponent(nomeProjetoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsavelProjetoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsavelProjetoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsavelGerenciaRiscosJLabel)
                    .addComponent(responsavelGerenciaRiscosJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(statusProjetoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descricaoProjetoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarAlteracoesJButton)
                    .addComponent(removerProjeto))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void responsavelProjetoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responsavelProjetoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_responsavelProjetoJTextFieldActionPerformed

    private void salvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarAlteracoesJButtonActionPerformed

        if (projetoSelecionado.getConcluido()) {
            JOptionPane.showMessageDialog(this, "O projeto selecionado está concluiro e não pode mais ter suas informações alteradas.");
            habilitarProjetoDetalhesJPanel(false);
        } else {

            //seto as alterações
            projetoSelecionado.setNomeProjeto(nomeProjetoJTextField.getText());
            projetoSelecionado.setResponsavelProjeto(responsavelProjetoJTextField.getText());
            projetoSelecionado.setResponsavelGerenciaRiscos(responsavelGerenciaRiscosJTextField.getText());
            projetoSelecionado.setDescricaoProjeto(descricaoProjetoJTextArea.getText());
            //salvo as alterações no banco de dados
            projetoFacade.setProjeto(projetoSelecionado);

            repaint();
            JOptionPane.showMessageDialog(this, "Dados alterados com sucesso.");
        }


    }//GEN-LAST:event_salvarAlteracoesJButtonActionPerformed
          
    private void removerProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerProjetoActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que você deseja remover este projeto?");
        if (resposta == 0) {
            ProjetoFacade projetoFacade = new ProjetoFacade();
            if (projetoSelecionado.getConcluido()) {
                JOptionPane.showMessageDialog(null, "Projeto concluído não pode ser removido.");
            } else {
                projetoFacade.removeProjeto(projetoSelecionado);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_removerProjetoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ProjetoConcluirProjetoDialog projetoConcluirProjetoDialog = new ProjetoConcluirProjetoDialog(null, true);
        projetoConcluirProjetoDialog.preencherRelatorio(projetoSelecionado);
        projetoConcluirProjetoDialog.setLocationRelativeTo(null);
        projetoConcluirProjetoDialog.setVisible(true);
        projetoSelecionado = new ProjetoFacade().getProjeto(projetoSelecionado.getIdProjeto());
        preencheDetalhes(projetoSelecionado);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descricaoProjetoJLabel;
    private javax.swing.JTextArea descricaoProjetoJTextArea;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nomeProjetoJLabel;
    private javax.swing.JTextField nomeProjetoJTextField;
    private javax.swing.JButton removerProjeto;
    private javax.swing.JLabel responsavelGerenciaRiscosJLabel;
    private javax.swing.JTextField responsavelGerenciaRiscosJTextField;
    private javax.swing.JLabel responsavelProjetoJLabel;
    private javax.swing.JTextField responsavelProjetoJTextField;
    private javax.swing.JButton salvarAlteracoesJButton;
    private javax.swing.JLabel statusProjetoJLabel;
    // End of variables declaration//GEN-END:variables

}
