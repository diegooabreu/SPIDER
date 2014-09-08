/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.OrganizacaoDetalhesFacade;
import facade.ProjetoFacade;
import javax.swing.JOptionPane;
import model.Projeto;

/**
 *
 * @author Diogo
 */
public class NovoProjetoJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NovoProjetoJPanel
     */
    public NovoProjetoJPanel() {
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

        novoProjetoJPanel = new javax.swing.JPanel();
        dadosDoProjetoJPanel = new javax.swing.JPanel();
        nomeDoProjetoJLabel = new javax.swing.JLabel();
        nomeDoProjetoJTextField = new javax.swing.JTextField();
        responsavelPeloProjetoJLabel = new javax.swing.JLabel();
        responsavelGerenciaRiscosJLabel = new javax.swing.JLabel();
        responsavelPeloProjetoJTextField = new javax.swing.JTextField();
        responsavelGerenciaRiscosJTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoDoProjetoJTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButtonSalvarProjeto = new javax.swing.JButton();

        novoProjetoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Projeto"));

        nomeDoProjetoJLabel.setText("Nome do Projeto:*");

        nomeDoProjetoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeDoProjetoJTextFieldActionPerformed(evt);
            }
        });

        responsavelPeloProjetoJLabel.setText("Responsável pelo Projeto:*");

        responsavelGerenciaRiscosJLabel.setText("Responsável pela Gerência de Riscos:*");

        javax.swing.GroupLayout dadosDoProjetoJPanelLayout = new javax.swing.GroupLayout(dadosDoProjetoJPanel);
        dadosDoProjetoJPanel.setLayout(dadosDoProjetoJPanelLayout);
        dadosDoProjetoJPanelLayout.setHorizontalGroup(
            dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosDoProjetoJPanelLayout.createSequentialGroup()
                .addGroup(dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(responsavelGerenciaRiscosJLabel)
                    .addComponent(responsavelPeloProjetoJLabel)
                    .addComponent(nomeDoProjetoJLabel))
                .addGap(18, 18, 18)
                .addGroup(dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(responsavelPeloProjetoJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(responsavelGerenciaRiscosJTextField)
                    .addComponent(nomeDoProjetoJTextField))
                .addContainerGap(404, Short.MAX_VALUE))
        );
        dadosDoProjetoJPanelLayout.setVerticalGroup(
            dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosDoProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeDoProjetoJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nomeDoProjetoJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(responsavelPeloProjetoJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(responsavelPeloProjetoJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dadosDoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsavelGerenciaRiscosJLabel)
                    .addComponent(responsavelGerenciaRiscosJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição do Projeto*"));

        descricaoDoProjetoJTextArea.setColumns(20);
        descricaoDoProjetoJTextArea.setLineWrap(true);
        descricaoDoProjetoJTextArea.setRows(3);
        descricaoDoProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descricaoDoProjetoJTextArea);

        jLabel1.setText("* = Campo obrigatório");

        jButtonSalvarProjeto.setText("Salvar Novo Projeto");
        jButtonSalvarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarProjetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout novoProjetoJPanelLayout = new javax.swing.GroupLayout(novoProjetoJPanel);
        novoProjetoJPanel.setLayout(novoProjetoJPanelLayout);
        novoProjetoJPanelLayout.setHorizontalGroup(
            novoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(novoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, novoProjetoJPanelLayout.createSequentialGroup()
                        .addGroup(novoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(novoProjetoJPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSalvarProjeto)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dadosDoProjetoJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        novoProjetoJPanelLayout.setVerticalGroup(
            novoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dadosDoProjetoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(novoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(novoProjetoJPanelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(novoProjetoJPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(novoProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSalvarProjeto)
                            .addComponent(jLabel1))))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(novoProjetoJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(novoProjetoJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nomeDoProjetoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeDoProjetoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeDoProjetoJTextFieldActionPerformed

    private void jButtonSalvarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarProjetoActionPerformed
        // TODO add your handling code here:
         Projeto projeto = new Projeto();
        int campoNulo = 0;
        int contCamposNulos = 0;
        projeto.setNomeProjeto(nomeDoProjetoJTextField.getText());
        if ("".equals(projeto.getNomeProjeto())) {
            campoNulo = 1;
            contCamposNulos++;
        }
        projeto.setResponsavelProjeto(responsavelPeloProjetoJTextField.getText());
        if ("".equals(projeto.getResponsavelProjeto())) {
            campoNulo = 2;
            contCamposNulos++;
        }
        projeto.setResponsavelGerenciaRiscos(responsavelGerenciaRiscosJTextField.getText());
        if ("".equals(projeto.getResponsavelGerenciaRiscos())) {
            campoNulo = 3;
            contCamposNulos++;
        }
        projeto.setDescricaoProjeto(descricaoDoProjetoJTextArea.getText());
        if ("".equals(projeto.getDescricaoProjeto())) {
            campoNulo = 4;
            contCamposNulos++;
        }

        if (contCamposNulos > 1){
            JOptionPane.showMessageDialog(this, "Um ou mais campos estão vazios. "
                    + "Para cadastrar um novo Projeto é necessário preencher todos os campos.");
            campoNulo = -1;
        }
        
        switch (campoNulo) {
            case 1:
                JOptionPane.showMessageDialog(this, "O campo 'Nome do projeto' está vazio. Preencha este campo");
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "O campo 'Responsável pelo Projeto' está vazio. Preencha este campo");
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "O campo 'Responsável pela Gerência de riscos' está vazio. Preencha este campo");
                break;
            case 4:
                JOptionPane.showMessageDialog(this, "O campo 'Descrição do Projeto' está vazio. Preencha este campo");
                break;
            case 0: {
                projeto.setConcluido(false);

                OrganizacaoDetalhesFacade detalhesFacade = new OrganizacaoDetalhesFacade();

                projeto.setIdOrganizacao(detalhesFacade.getDetalhes());

                ProjetoFacade projetoFachada = new ProjetoFacade();
                projetoFachada.salvarNovoProjeto(projeto);
                projetoFachada.adicionarEAR(projeto);

                JOptionPane.showMessageDialog(this, "Projeto criado com sucesso!");

                nomeDoProjetoJTextField.setText(null);
                responsavelPeloProjetoJTextField.setText(null);
                responsavelGerenciaRiscosJTextField.setText(null);
                descricaoDoProjetoJTextArea.setText(null);
                
                break;
            }
        }
    }//GEN-LAST:event_jButtonSalvarProjetoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dadosDoProjetoJPanel;
    private javax.swing.JTextArea descricaoDoProjetoJTextArea;
    private javax.swing.JButton jButtonSalvarProjeto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomeDoProjetoJLabel;
    private javax.swing.JTextField nomeDoProjetoJTextField;
    private javax.swing.JPanel novoProjetoJPanel;
    private javax.swing.JLabel responsavelGerenciaRiscosJLabel;
    private javax.swing.JTextField responsavelGerenciaRiscosJTextField;
    private javax.swing.JLabel responsavelPeloProjetoJLabel;
    private javax.swing.JTextField responsavelPeloProjetoJTextField;
    // End of variables declaration//GEN-END:variables
}
