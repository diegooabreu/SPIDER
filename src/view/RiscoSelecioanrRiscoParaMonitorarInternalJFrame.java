/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.OrganizacionalPortfolioFacade;
import facade.RiscosGerenciarRiscosFacade;
import facade.RiscosRiscosOcorridosFacade;
import facade.RiscosSelecionarRiscosParaMonitorarFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Historicoalteracao;
import model.Projeto;
import model.Risco;
import model.Subcondicao;
import view.tabelas.RiscoTabela;
import view.tabelas.RiscoTabelaModel;

/**
 *
 * @author H
 */
public class RiscoSelecioanrRiscoParaMonitorarInternalJFrame extends javax.swing.JInternalFrame {

    // Instanciando classe Facade de Riscos
    RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();

    RiscosSelecionarRiscosParaMonitorarFacade riscosSelecionarRiscosParaMonitorarFacade = new RiscosSelecionarRiscosParaMonitorarFacade();
    RiscosRiscosOcorridosFacade riscosRiscosOcorridosFacade = new RiscosRiscosOcorridosFacade();

    private JTable tabelaSelecionarRiscosMonitorarJTable;
    private RiscoTabelaModel riscoTabelaModel;

    private RiscosPriorizarRiscosJPanel priorizar = new RiscosPriorizarRiscosJPanel();

    List<RiscoTabela> listaRiscosTabela = null;
    List<Risco> listaRiscos = null;

    Projeto projetoSelecionado;

    // Instanciando variável que armazena a lista de histórico de alterações do risco selecionado
    private List<Historicoalteracao> listaHistoricoAlteracao;
    //Instanciando classe Facade de OrganizacionalPortfolio para obter lista de categorias
    OrganizacionalPortfolioFacade organizacionalPortfolioFacade = new OrganizacionalPortfolioFacade();

    //Método que atualiza a lista de Histórico de Alterações do risco selecionado
    void getListaHistoricoAlteracoes(Risco idRisco) {
        listaHistoricoAlteracao = organizacionalPortfolioFacade.listarHistoricoAlteracoesByIdRisco(idRisco);
    }

    public RiscoSelecioanrRiscoParaMonitorarInternalJFrame() {
        initComponents();

    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    private void getListaRiscosNoBanco() {
        listaRiscos = riscosSelecionarRiscosParaMonitorarFacade.getListaDeRiscosDoProjeto(projetoSelecionado);
    }

    public void criarTabelaSelecionarRiscos(List<Risco> listaDeRiscos) {

        if (listaDeRiscos != null) {
            projetoSelecionado = listaDeRiscos.get(0).getContem().getProjeto();
        }

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

        //tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(2).setMaxWidth(0);
        //tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(2).setMinWidth(0);
        //tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        //tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        definirEventosTabela();

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

    private List<RiscoTabela> criaListaDeRiscoTabela(List<Risco> listaRiscos) {
        listaRiscosTabela = new ArrayList<RiscoTabela>();

        for (int i = 0; i < listaRiscos.size(); i++) {
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

            if (listaRiscos.get(i).getStatusRisco().equals("Mitigando") || listaRiscos.get(i).getStatusRisco().equals("Contingenciando")) {
                riscoTabela.setMonitorar(true);
            }

            listaRiscosTabela.add(riscoTabela);

        }

        return listaRiscosTabela;

    }

    int selected;

    public void definirEventosTabela() {
        tabelaSelecionarRiscosMonitorarJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 1) {

                    selected = tabelaSelecionarRiscosMonitorarJTable.getSelectedRow();
                    riscoTabelaModel.isCellEditable(selected, 0);

                    if (listaRiscos.get(selected).getPlanomitigacaoList().isEmpty()) {
                        JOptionPane.showMessageDialog(tabelaSelecionarRiscosMonitorarJTable, "O risco selecionado não possui Planos de Mitigação.\nPara monitorá-lo inclua novo plano de mitigação.");
                    } else {
                        //se monitorar nao estiver marcado entao marca
                        if ((boolean) riscoTabelaModel.getValueAt(selected, 0) == false) {
                            riscoTabelaModel.setValueAt(true, selected, 0);
                            // se monitorar estiver marcado entao desmarca
                        } else {
                            riscoTabelaModel.setValueAt(false, selected, 0);
                        }

                    }
                }
            }
        });
    }

    private void tabelaSelecionarRiscosMonitorarJTableMouseClicked(java.awt.event.MouseEvent evt) {
        JOptionPane.showMessageDialog(null, "MouseCliked");
        Double valor = 0.0;

        int selected = tabelaSelecionarRiscosMonitorarJTable.getSelectedRow();

        if (listaRiscos.get(selected).getPlanomitigacaoList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O risco selecionado não possui Planos de Mitigação.\nPara monitorá-lo inclua novo plano de mitigação.");

            listaRiscosTabela.get(selected).setMonitorar(false);
        }

    }

   
    public void registraHistoricoAlteracoes(Risco riscoSelecionado, String mensagem) {
        riscosSelecionarRiscosParaMonitorarFacade.editRisco(riscoSelecionado);
        Historicoalteracao historicoalteracao = new Historicoalteracao();
        historicoalteracao.setDescricaoAlteracao(mensagem);
        Calendar c = Calendar.getInstance();
        historicoalteracao.setDataAlteracao(c.getTime());
        historicoalteracao.setIdRisco(riscoSelecionado);
        riscosGerenciarRiscosFacade.criaHistorioAlteracao(historicoalteracao);
        getListaHistoricoAlteracoes(riscoSelecionado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        monitorarJButton = new javax.swing.JButton();
        tabelaJPanel = new javax.swing.JPanel();
        tabelaJScrollPane = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();

        setTitle("Monitorar Riscos");

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
            .addComponent(tabelaJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabelaJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
                        .addComponent(monitorarJButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(monitorarJButton))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monitorarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorarJButtonActionPerformed

        Risco RiscoSelecionado;
        for (int i = 0; i < listaRiscosTabela.size(); i++) {
            if ((Boolean) riscoTabelaModel.getValueAt(i, 0) == true) {

                if (listaRiscosTabela.get(i).getStatusRisco().equals("Novo")) {
                    listaRiscosTabela.get(i).setStatusRisco("Mitigando");
                    listaRiscos.get(i).setStatusRisco("Mitigando");
                    List<Subcondicao> listaDeCondicoes = listaRiscos.get(i).getSubcondicaoList();

                    for (int k = 0; k < listaDeCondicoes.size(); k++) {
                        listaDeCondicoes.get(k).setStatusSubcondicao("Não Ocorrido");
                        riscosSelecionarRiscosParaMonitorarFacade.editCondicao(listaDeCondicoes.get(k));
                    }

                    registraHistoricoAlteracoes(listaRiscos.get(i), "Risco " + listaRiscos.get(i).getIdentificacao() + " entra em Monitoração.");
                }

            } else {

                listaRiscosTabela.get(i).setStatusRisco("Novo");
                listaRiscos.get(i).setStatusRisco("Novo");

                riscosSelecionarRiscosParaMonitorarFacade.editRisco(listaRiscos.get(i));
                registraHistoricoAlteracoes(listaRiscos.get(i), "Risco " + listaRiscos.get(i).getIdentificacao() + " deixou de ser monitorado, status alterado para 'Novo'.");

            }
        }

        criarTabelaSelecionarRiscos(listaRiscos);

        JOptionPane.showMessageDialog(this, "Riscos selecionados agora estão sendo monitorados, seus planos de mitigação agora são\ntarefas a serem realizadas.");

        // TODO add your handling code here:
    }//GEN-LAST:event_monitorarJButtonActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        RiscosPriorizarRiscosJPanel riscosPriorizarRiscosJPanel = new RiscosPriorizarRiscosJPanel();
        riscosPriorizarRiscosJPanel.atualizaTabelaPriorizarRiscos(listaRiscos);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton monitorarJButton;
    private javax.swing.JPanel tabelaJPanel;
    private javax.swing.JScrollPane tabelaJScrollPane;
    // End of variables declaration//GEN-END:variables
}
