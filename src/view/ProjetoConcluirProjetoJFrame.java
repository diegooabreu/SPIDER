/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.ProjetoCalendarioFacade;
import facade.ProjetoConcluirProjetoFacade;
import facade.ProjetoFacade;
import facade.RiscosGerenciarRiscosFacade;
import facade.RiscosRiscosOcorridosFacade;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contem;
import model.Historicorisco;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Diego
 */
public class ProjetoConcluirProjetoJFrame extends javax.swing.JFrame {

    Projeto projetoSelecionado;

    /**
     * Creates new form ProjetoConcluirProjetoJFrame
     */
    public ProjetoConcluirProjetoJFrame() {
        initComponents();

    }

    public void preencherRelatorio(Projeto projeto) {
        projetoSelecionado = projeto;
        nomeProjetoJLabel2.setText(projeto.getNomeProjeto());
        responsavelProjetoJLabel2.setText(projeto.getResponsavelProjeto());
        responsavelGerenciaRiscosJLabel2.setText(projeto.getResponsavelGerenciaRiscos());
        descricaoProjetoJTextArea.setText(projeto.getDescricaoProjeto());

        ProjetoConcluirProjetoFacade projetoConcluirProjetoFacade = new ProjetoConcluirProjetoFacade();

        List<Contem> listaContem = projetoConcluirProjetoFacade.getListaContemWhereIdProjeto(projeto.getIdProjeto());

        int quantidadeNovasCategorias = 0;
        for (int i = 0; i < listaContem.size(); i++) {
            if (listaContem.get(i).getCategoriaderisco().getEarOrganizacional() == false) {
                quantidadeNovasCategorias = quantidadeNovasCategorias + 1;
            }
        }
        quantidadeNovasCategoriasJLabel2.setText(Integer.toString(quantidadeNovasCategorias));

        RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
        List<Risco> listaDeRiscos = riscosGerenciarRiscosFacade.listarRiscosByProjeto(projetoSelecionado);
        respostaQuantidadeDeRiscosTotalJLabel.setText(Integer.toString(listaDeRiscos.size()));

        ProjetoFacade projetoFacade = new ProjetoFacade();
        int quantidadeDeRiscosMitigando = 0;
        for (int i = 0; i < listaDeRiscos.size(); i++) {
            if (listaDeRiscos.get(i).getStatusRisco().equals("Mitigando")) {
                quantidadeDeRiscosMitigando++;
            }

        }

        respostaStatusMitigandoJLabel.setText(Integer.toString(quantidadeDeRiscosMitigando));
        
        int quantidadeDeRiscosNovo = 0;
        for (int i = 0; i < listaDeRiscos.size(); i++) {
            if (listaDeRiscos.get(i).getStatusRisco().equals("Novo")) {
                quantidadeDeRiscosNovo++;
            }

        }
        
        respostaStatusNovoJLabel.setText(Integer.toString(quantidadeDeRiscosNovo));
        
        int quantidadeDeRiscosContingenciando = 0;
        for (int i = 0; i < listaDeRiscos.size(); i++) {
            if (listaDeRiscos.get(i).getStatusRisco().equals("Contingenciando")) {
                quantidadeDeRiscosContingenciando++;
            }

        }
        
        respostaStatusContingenciandoJLabel.setText(Integer.toString(quantidadeDeRiscosContingenciando));
        
        RiscosRiscosOcorridosFacade riscosRiscosOcorridosFacade = new RiscosRiscosOcorridosFacade();
        List<Historicorisco> listaDeRiscosOcorridos = riscosRiscosOcorridosFacade.getListaHistoricoriscosByIdProjeto(projetoSelecionado.getIdProjeto());
        
        respostaQuantidadeDeRiscosOcorridosJLabel.setText(Integer.toString(listaDeRiscosOcorridos.size()));
        
        ProjetoCalendarioFacade projetoCalendarioFacade = new ProjetoCalendarioFacade();
        List<Marcodoprojeto> listaMarcosDoProjeto = projetoCalendarioFacade.getListaMarcosDoProjetoSelecionado(projetoSelecionado);
        
        respostaQuantidadeDeMarcosJLabel.setText(Integer.toString(listaMarcosDoProjeto.size()));
        
        List<Pontodecontrole> listaPontosDeControle = projetoCalendarioFacade.getListaPontosDeControleDoProjetoSelecionado(projetoSelecionado);
        
        respostaQuantidadeDePontosDeControleJLabel.setText(Integer.toString(listaPontosDeControle.size()));
        
        List<Planocontingencia> listaPlanosContingenciaPendentes = projetoFacade.buscaPlanosDeContingenciaPendentes(projetoSelecionado);
        List<Planomitigacao> listaPlanosMitigacaoPendentes = projetoFacade.buscaPlanosDeMitigacaoPendentes(projetoSelecionado);
        
        respostaQuantidadeDePlanosPendentesJLabel.setText(Integer.toString(listaPlanosContingenciaPendentes.size() + listaPlanosMitigacaoPendentes.size()));
        
        List<Planocontingencia> listaPlanosContingenciaRealizados = projetoFacade.buscaPlanosDeContingenciaRealizados(projetoSelecionado);
        List<Planomitigacao> listaPlanosMitigacaoRealizados = projetoFacade.buscaPlanosDeMitigacaoRealizados(projetoSelecionado);
        
        respostaQuantidadeDePlanosRealizadosJLabel.setText(Integer.toString(listaPlanosContingenciaRealizados.size() + listaPlanosMitigacaoRealizados.size()));

        if (projeto.getConcluido()) {
            concluirProjetoJButton.setEnabled(false);
        } else {
            concluirProjetoJButton.setEnabled(true);
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
        nomeProjetoJLabel = new javax.swing.JLabel();
        responsavelProjetoJLabel = new javax.swing.JLabel();
        responsavelGerenciaRiscosJLabel = new javax.swing.JLabel();
        descricaoProjetoJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoProjetoJTextArea = new javax.swing.JTextArea();
        quantidadeNovasCategoriasJLabel = new javax.swing.JLabel();
        nomeProjetoJLabel2 = new javax.swing.JLabel();
        responsavelProjetoJLabel2 = new javax.swing.JLabel();
        responsavelGerenciaRiscosJLabel2 = new javax.swing.JLabel();
        quantidadeNovasCategoriasJLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        quantidadeDeRiscosTotalJLabel = new javax.swing.JLabel();
        respostaQuantidadeDeRiscosTotalJLabel = new javax.swing.JLabel();
        respostaQuantidadeDeRiscosOcorridosJLabel = new javax.swing.JLabel();
        QuantidadeDeRiscosNovosJLabel = new javax.swing.JLabel();
        statusNovoJLabel = new javax.swing.JLabel();
        respostaStatusNovoJLabel = new javax.swing.JLabel();
        statusMitigandoJLabel = new javax.swing.JLabel();
        respostaStatusMitigandoJLabel = new javax.swing.JLabel();
        statusContingenciandoJLabel = new javax.swing.JLabel();
        respostaStatusContingenciandoJLabel = new javax.swing.JLabel();
        quantidadeDeMarcosJLabel = new javax.swing.JLabel();
        respostaQuantidadeDeMarcosJLabel = new javax.swing.JLabel();
        quantidadeDePontosDeControleJLabel = new javax.swing.JLabel();
        respostaQuantidadeDePontosDeControleJLabel = new javax.swing.JLabel();
        quantidadeDePlanosPendentesJLabel = new javax.swing.JLabel();
        respostaQuantidadeDePlanosPendentesJLabel = new javax.swing.JLabel();
        quantidadeDePlanosRealizadosJLabel = new javax.swing.JLabel();
        respostaQuantidadeDePlanosRealizadosJLabel = new javax.swing.JLabel();
        concluirProjetoJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório do Projeto"));

        nomeProjetoJLabel.setText("Nome do Projeto:");

        responsavelProjetoJLabel.setText("Responsável pelo Projeto:");

        responsavelGerenciaRiscosJLabel.setText("Responsável pela Gerência de Riscos:");

        descricaoProjetoJLabel.setText("Descrição do Projeto:");

        descricaoProjetoJTextArea.setEditable(false);
        descricaoProjetoJTextArea.setColumns(20);
        descricaoProjetoJTextArea.setLineWrap(true);
        descricaoProjetoJTextArea.setRows(5);
        descricaoProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descricaoProjetoJTextArea);

        quantidadeNovasCategoriasJLabel.setText("Quantidade de novas Categorias de Risco:");

        nomeProjetoJLabel2.setText("nome");

        responsavelProjetoJLabel2.setText("responsavel");

        responsavelGerenciaRiscosJLabel2.setText("responsavel riscos");

        quantidadeNovasCategoriasJLabel2.setText("0");

        jLabel1.setText("Quantidade de riscos ocorridos:");

        quantidadeDeRiscosTotalJLabel.setText("Quantidade de riscos (Total):");

        respostaQuantidadeDeRiscosTotalJLabel.setText("0");

        respostaQuantidadeDeRiscosOcorridosJLabel.setText("0");

        QuantidadeDeRiscosNovosJLabel.setText("Quantidade de riscos por status:");

        statusNovoJLabel.setText("Novo:");

        respostaStatusNovoJLabel.setText("0");

        statusMitigandoJLabel.setText("Mitigando");

        respostaStatusMitigandoJLabel.setText("0");

        statusContingenciandoJLabel.setText("Contingenciando");

        respostaStatusContingenciandoJLabel.setText("0");

        quantidadeDeMarcosJLabel.setText("Quantidade de marcos de projeto:");

        respostaQuantidadeDeMarcosJLabel.setText("0");

        quantidadeDePontosDeControleJLabel.setText("Quantidade de pontos de controle:");

        respostaQuantidadeDePontosDeControleJLabel.setText("0");

        quantidadeDePlanosPendentesJLabel.setText("Quantidade de planos pendentes:");

        respostaQuantidadeDePlanosPendentesJLabel.setText("0");

        quantidadeDePlanosRealizadosJLabel.setText("Quantidade de planos realizados:");

        respostaQuantidadeDePlanosRealizadosJLabel.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(respostaQuantidadeDeRiscosOcorridosJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantidadeNovasCategoriasJLabel)
                                    .addComponent(quantidadeDeRiscosTotalJLabel)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(QuantidadeDeRiscosNovosJLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(statusNovoJLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(quantidadeNovasCategoriasJLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                    .addComponent(respostaStatusNovoJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(respostaQuantidadeDeRiscosTotalJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(respostaQuantidadeDeMarcosJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(respostaQuantidadeDePontosDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(respostaQuantidadeDePlanosPendentesJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(respostaQuantidadeDePlanosRealizadosJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(statusMitigandoJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(respostaStatusMitigandoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(statusContingenciandoJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(respostaStatusContingenciandoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descricaoProjetoJLabel)
                            .addComponent(quantidadeDeMarcosJLabel)
                            .addComponent(quantidadeDePontosDeControleJLabel)
                            .addComponent(quantidadeDePlanosPendentesJLabel)
                            .addComponent(quantidadeDePlanosRealizadosJLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(responsavelGerenciaRiscosJLabel)
                                    .addComponent(responsavelProjetoJLabel)
                                    .addComponent(nomeProjetoJLabel))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeProjetoJLabel2)
                                    .addComponent(responsavelGerenciaRiscosJLabel2)
                                    .addComponent(responsavelProjetoJLabel2))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeProjetoJLabel)
                    .addComponent(nomeProjetoJLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsavelProjetoJLabel)
                    .addComponent(responsavelProjetoJLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsavelGerenciaRiscosJLabel)
                    .addComponent(responsavelGerenciaRiscosJLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descricaoProjetoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeNovasCategoriasJLabel)
                    .addComponent(quantidadeNovasCategoriasJLabel2))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeDeRiscosTotalJLabel)
                    .addComponent(respostaQuantidadeDeRiscosTotalJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QuantidadeDeRiscosNovosJLabel)
                    .addComponent(statusNovoJLabel)
                    .addComponent(respostaStatusNovoJLabel)
                    .addComponent(statusMitigandoJLabel)
                    .addComponent(respostaStatusMitigandoJLabel)
                    .addComponent(statusContingenciandoJLabel)
                    .addComponent(respostaStatusContingenciandoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(respostaQuantidadeDeRiscosOcorridosJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeDeMarcosJLabel)
                    .addComponent(respostaQuantidadeDeMarcosJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeDePontosDeControleJLabel)
                    .addComponent(respostaQuantidadeDePontosDeControleJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeDePlanosPendentesJLabel)
                    .addComponent(respostaQuantidadeDePlanosPendentesJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeDePlanosRealizadosJLabel)
                    .addComponent(respostaQuantidadeDePlanosRealizadosJLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        concluirProjetoJButton.setText("Concluir Projeto");
        concluirProjetoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                concluirProjetoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(concluirProjetoJButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(concluirProjetoJButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void concluirProjetoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_concluirProjetoJButtonActionPerformed

        int n = JOptionPane.showConfirmDialog(this, "Ao concluir o projeto você não poderá alterar suas informações.\nTem certeza que deseja concluir o projeto?", "Concluir Projeto", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {

            projetoSelecionado.setConcluido(true);

            ProjetoConcluirProjetoFacade projetoConcluirProjetoFacade = new ProjetoConcluirProjetoFacade();

            projetoConcluirProjetoFacade.setProjeto(projetoSelecionado);

            JOptionPane.showMessageDialog(this, "O projeto foi concluído, agora você pode fazer uma avaliação para este projeto na opção Portifólio.");

            this.setVisible(false);

        } else {
            JOptionPane.showMessageDialog(this, "Projeto não concluído.");
        }

    }//GEN-LAST:event_concluirProjetoJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ProjetoConcluirProjetoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjetoConcluirProjetoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjetoConcluirProjetoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjetoConcluirProjetoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjetoConcluirProjetoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel QuantidadeDeRiscosNovosJLabel;
    private javax.swing.JButton concluirProjetoJButton;
    private javax.swing.JLabel descricaoProjetoJLabel;
    private javax.swing.JTextArea descricaoProjetoJTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomeProjetoJLabel;
    private javax.swing.JLabel nomeProjetoJLabel2;
    private javax.swing.JLabel quantidadeDeMarcosJLabel;
    private javax.swing.JLabel quantidadeDePlanosPendentesJLabel;
    private javax.swing.JLabel quantidadeDePlanosRealizadosJLabel;
    private javax.swing.JLabel quantidadeDePontosDeControleJLabel;
    private javax.swing.JLabel quantidadeDeRiscosTotalJLabel;
    private javax.swing.JLabel quantidadeNovasCategoriasJLabel;
    private javax.swing.JLabel quantidadeNovasCategoriasJLabel2;
    private javax.swing.JLabel responsavelGerenciaRiscosJLabel;
    private javax.swing.JLabel responsavelGerenciaRiscosJLabel2;
    private javax.swing.JLabel responsavelProjetoJLabel;
    private javax.swing.JLabel responsavelProjetoJLabel2;
    private javax.swing.JLabel respostaQuantidadeDeMarcosJLabel;
    private javax.swing.JLabel respostaQuantidadeDePlanosPendentesJLabel;
    private javax.swing.JLabel respostaQuantidadeDePlanosRealizadosJLabel;
    private javax.swing.JLabel respostaQuantidadeDePontosDeControleJLabel;
    private javax.swing.JLabel respostaQuantidadeDeRiscosOcorridosJLabel;
    private javax.swing.JLabel respostaQuantidadeDeRiscosTotalJLabel;
    private javax.swing.JLabel respostaStatusContingenciandoJLabel;
    private javax.swing.JLabel respostaStatusMitigandoJLabel;
    private javax.swing.JLabel respostaStatusNovoJLabel;
    private javax.swing.JLabel statusContingenciandoJLabel;
    private javax.swing.JLabel statusMitigandoJLabel;
    private javax.swing.JLabel statusNovoJLabel;
    // End of variables declaration//GEN-END:variables
}
