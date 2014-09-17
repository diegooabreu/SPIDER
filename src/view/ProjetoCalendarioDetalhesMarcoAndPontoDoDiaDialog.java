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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import resources.Constantes;

/**
 *
 * @author BlenoVale
 */
public class ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog extends javax.swing.JDialog {

    CalendarioDetalhesDoDiaFacade calendarioDetalhesDoDiaFacade = new CalendarioDetalhesDoDiaFacade();

    private JTable listaTarefasPontoSomentePontoJTable;
    private JTable listaTarefasMarcoSomenteMarcoJTable;
    private DefaultTableModel listaTarefasMarcoSomenteMarcoJTableModel;
    private DefaultTableModel listaTarefasPontoSomentePontoJTableModel;

    public ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listaTarefasMarcoSomenteMarcoJTable = new JTable();
        listaTarefasMarcoSomenteMarcoJTableModel = new DefaultTableModel();

        listaTarefasPontoSomentePontoJTable = new JTable();
        listaTarefasPontoSomentePontoJTableModel = new DefaultTableModel();
    }

    public void criarListaDeTarefasMarcoSomenteMarco() {
        listaTarefasMarcoSomenteMarcoJTableModel.setColumnIdentifiers(new Object[]{"Risco", "Status", "Plano", "Responsavel", "Data de Realização"});
        listaTarefasMarcoSomenteMarcoJTable.setModel(listaTarefasMarcoSomenteMarcoJTableModel);
        jScrollPanelistaTarefasMarco.setViewportView(listaTarefasMarcoSomenteMarcoJTable);
    }

    public void criarListaDeTarefasPontoSomentePonto() {

        listaTarefasPontoSomentePontoJTableModel.setColumnIdentifiers(new Object[]{"Risco", "Status", "Plano", "Responsável", "Data de Realização"});
        listaTarefasPontoSomentePontoJTable.setModel(listaTarefasPontoSomentePontoJTableModel);
        jscrollPaneListatarefasPontosDeControle.setViewportView(listaTarefasPontoSomentePontoJTable);
    }

    public void limparTela() {
        //limapar campos marco do projeto
        nomeMarcoDoProjetoJLabel.setText("");
        dataMarcoDoProjetoJLabel.setText("");
        descricaoMarcoDoProjetoJTextArea.setText("");
        listaTarefasMarcoSomenteMarcoJTableModel.setNumRows(0);
        jTabbedPaneCalendarioDetalhes.setEnabledAt(0, true);
        jTabbedPaneCalendarioDetalhes.setSelectedIndex(0);

        //limpar campos ponto de controle
        nomePontoDeControleJLabel.setText("");
        dataPontoDeControleJLabel.setText("");
        descricaoPontoDeCOntroleJTextArea.setText("");
        listaTarefasPontoSomentePontoJTableModel.setNumRows(0);
        jTabbedPaneCalendarioDetalhes.setEnabledAt(1, true);
    }

    public void mostraInformacoesPontoDeControle(Pontodecontrole ponto) {

        nomePontoDeControleJLabel.setText(ponto.getNomePontoDeControle());
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date dataPontoDeControle = ponto.getDataPontoControle();
        dataPontoDeControleJLabel.setText(df.format(dataPontoDeControle));
        descricaoPontoDeCOntroleJTextArea.setText(ponto.getDescricaoPontoControle());

    }

    public void mostraInformacoesMarcoDoProjeto(Marcodoprojeto marco) {

        nomeMarcoDoProjetoJLabel.setText(marco.getNomeMarcoDoProjeto());
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date dataMarcoDoProjeto = marco.getDataMarcoProjeto();
        dataMarcoDoProjetoJLabel.setText(df.format(dataMarcoDoProjeto));
        descricaoMarcoDoProjetoJTextArea.setText(marco.getDescricaoMarcoProjeto());

    }

    public void popularListaDeTarefasPontoSomentePonto(Pontodecontrole pontoSelecionado) {

        String dataRealizacaoPMString = "Não realizada";
        String dataRealizacaoPCString = "Não realizada";

        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacao = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByPontoDeControle(pontoSelecionado);

        for (int i = 0; i < listaPlanosDeMitigacao.size(); i++) {
            if (Constantes.MITIGANDO.equals(listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco())) {
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

                Date dataRealizacaoPM = listaPlanosDeMitigacao.get(i).getDataRealizacao();

                if (dataRealizacaoPM != null) {
                    dataRealizacaoPMString = df.format(dataRealizacaoPM);
                }

                Object[] linha = new Object[]{listaPlanosDeMitigacao.get(i).getIdRisco().getIdentificacao(),
                    listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco(),
                    listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao(),
                    listaPlanosDeMitigacao.get(i).getResponsavel(),
                    dataRealizacaoPMString};

                listaTarefasPontoSomentePontoJTableModel.addRow(linha);
                dataRealizacaoPMString = "Não realizada";

            }
        }

        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        List<Planocontingencia> listaPlanosContingencia = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByPontoDeControle(pontoSelecionado);

        for (int i = 0; i < listaPlanosContingencia.size(); i++) {

            if (Constantes.CONTINGENCIANDO.equals(listaPlanosContingencia.get(i).getIdRisco().getStatusRisco())) {

                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

                Date dataRealizacaoPC = listaPlanosContingencia.get(i).getDataRealizacao();

                if (dataRealizacaoPC != null) {
                    dataRealizacaoPCString = df.format(dataRealizacaoPCString);
                }

                Object[] linha = new Object[]{listaPlanosContingencia.get(i).getIdRisco().getIdentificacao(),
                    listaPlanosContingencia.get(i).getIdRisco().getStatusRisco(),
                    listaPlanosContingencia.get(i).getIdentificacaoPlanoContingencia(),
                    listaPlanosContingencia.get(i).getResponsavel(),
                    dataRealizacaoPCString};

                listaTarefasPontoSomentePontoJTableModel.addRow(linha);
                dataRealizacaoPCString = "Não realizada";

            }

        }
    }

    public void popularListaDeTarefasMarcoSomenteMarco(Marcodoprojeto marcoSelecionado) {

        String dataRealizacaoPMString = "Não realizada";
        String dataRealizacaoPCString = "Não realizada";

        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacao = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByMarcoDoProjeto(marcoSelecionado);

        for (int i = 0; i < listaPlanosDeMitigacao.size(); i++) {
            if (Constantes.MITIGANDO.equals(listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco())) {
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

                Date dataRealizacaoPM = listaPlanosDeMitigacao.get(i).getDataRealizacao();

                if (dataRealizacaoPM != null) {
                    dataRealizacaoPMString = df.format(dataRealizacaoPM);
                }

                Object[] linha = new Object[]{listaPlanosDeMitigacao.get(i).getIdRisco().getIdentificacao(),
                    listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco(),
                    listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao(),
                    listaPlanosDeMitigacao.get(i).getResponsavel(),
                    dataRealizacaoPMString};

                listaTarefasMarcoSomenteMarcoJTableModel.addRow(linha);
                dataRealizacaoPMString = "Não realizada";

            }
        }

        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        List<Planocontingencia> listaPlanosContingencia = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByMarcoDoProjeto(marcoSelecionado);

        for (int i = 0; i < listaPlanosContingencia.size(); i++) {
            if (Constantes.CONTINGENCIANDO.equals(listaPlanosContingencia.get(i).getIdRisco().getStatusRisco())) {
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

                Date dataRealizacaoPC = listaPlanosContingencia.get(i).getDataRealizacao();

                if (dataRealizacaoPC != null) {
                    dataRealizacaoPCString = df.format(dataRealizacaoPCString);
                }

                Object[] linha = new Object[]{listaPlanosContingencia.get(i).getIdRisco().getIdentificacao(),
                    listaPlanosContingencia.get(i).getIdRisco().getStatusRisco(),
                    listaPlanosContingencia.get(i).getIdentificacaoPlanoContingencia(),
                    listaPlanosContingencia.get(i).getResponsavel(),
                    dataRealizacaoPCString};

                listaTarefasMarcoSomenteMarcoJTableModel.addRow(linha);
                dataRealizacaoPCString = "Não realizada";

            }
        }
    }

    public void desabilitaAbaPontoDeControle() {
        jTabbedPaneCalendarioDetalhes.setEnabledAt(0, false);
        jTabbedPaneCalendarioDetalhes.setSelectedIndex(1);
    }
    
    public void desabilitaAbaMarcoDoProjeto() {
        jTabbedPaneCalendarioDetalhes.setEnabledAt(1, false);
        jTabbedPaneCalendarioDetalhes.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneCalendarioDetalhes = new javax.swing.JTabbedPane();
        pontoPainelJPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nomePontoDeControleJLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dataPontoDeControleJLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jscrollPaneListatarefasPontosDeControle = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        descricaoPontoDeCOntroleJTextArea = new javax.swing.JTextArea();
        marcoPainelJPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        nomeMarcoDoProjetoJLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dataMarcoDoProjetoJLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoMarcoDoProjetoJTextArea = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPanelistaTarefasMarco = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Nome do Ponto de Controle:");

        jLabel5.setText("Data do Ponto de Controle:");

        jLabel8.setText("Lista de Tarefas para este ponto de controle:");

        jLabel6.setText("Descrição do Ponto de Controle:");

        descricaoPontoDeCOntroleJTextArea.setEditable(false);
        descricaoPontoDeCOntroleJTextArea.setColumns(20);
        descricaoPontoDeCOntroleJTextArea.setRows(5);
        jScrollPane4.setViewportView(descricaoPontoDeCOntroleJTextArea);

        javax.swing.GroupLayout pontoPainelJPanel3Layout = new javax.swing.GroupLayout(pontoPainelJPanel3);
        pontoPainelJPanel3.setLayout(pontoPainelJPanel3Layout);
        pontoPainelJPanel3Layout.setHorizontalGroup(
            pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontoPainelJPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(pontoPainelJPanel3Layout.createSequentialGroup()
                        .addGroup(pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataPontoDeControleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomePontoDeControleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jscrollPaneListatarefasPontosDeControle)
                    .addGroup(pontoPainelJPanel3Layout.createSequentialGroup()
                        .addGroup(pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(0, 357, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pontoPainelJPanel3Layout.setVerticalGroup(
            pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontoPainelJPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomePontoDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pontoPainelJPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dataPontoDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrollPaneListatarefasPontosDeControle, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPaneCalendarioDetalhes.addTab("Ponto de Controle", pontoPainelJPanel3);

        marcoPainelJPanel.setForeground(new java.awt.Color(240, 240, 240));

        jLabel9.setText("Nome do Marco do Projeto:");

        jLabel10.setText("Data do Marco do Projeto:");

        jLabel11.setText("Descrição do Marco do Projeto:");

        descricaoMarcoDoProjetoJTextArea.setEditable(false);
        descricaoMarcoDoProjetoJTextArea.setColumns(20);
        descricaoMarcoDoProjetoJTextArea.setRows(5);
        jScrollPane1.setViewportView(descricaoMarcoDoProjetoJTextArea);

        jLabel12.setText("Lista de tarefas para este marco:");

        jScrollPanelistaTarefasMarco.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout marcoPainelJPanelLayout = new javax.swing.GroupLayout(marcoPainelJPanel);
        marcoPainelJPanel.setLayout(marcoPainelJPanelLayout);
        marcoPainelJPanelLayout.setHorizontalGroup(
            marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addComponent(jScrollPanelistaTarefasMarco)
                    .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                        .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, marcoPainelJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(10, 10, 10)))
                                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dataMarcoDoProjetoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nomeMarcoDoProjetoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        marcoPainelJPanelLayout.setVerticalGroup(
            marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(nomeMarcoDoProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(dataMarcoDoProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanelistaTarefasMarco, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneCalendarioDetalhes.addTab("Marco", marcoPainelJPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCalendarioDetalhes)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCalendarioDetalhes)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog dialog = new ProjetoCalendarioDetalhesMarcoAndPontoDoDiaDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel dataMarcoDoProjetoJLabel;
    private javax.swing.JLabel dataPontoDeControleJLabel;
    private javax.swing.JTextArea descricaoMarcoDoProjetoJTextArea;
    private javax.swing.JTextArea descricaoPontoDeCOntroleJTextArea;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPanelistaTarefasMarco;
    private javax.swing.JTabbedPane jTabbedPaneCalendarioDetalhes;
    private javax.swing.JScrollPane jscrollPaneListatarefasPontosDeControle;
    private javax.swing.JPanel marcoPainelJPanel;
    private javax.swing.JLabel nomeMarcoDoProjetoJLabel;
    private javax.swing.JLabel nomePontoDeControleJLabel;
    private javax.swing.JPanel pontoPainelJPanel3;
    // End of variables declaration//GEN-END:variables
}
