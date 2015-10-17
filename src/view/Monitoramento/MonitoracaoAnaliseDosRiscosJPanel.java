/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Monitoramento;

import facade.MonitoracaoAnaliseDosRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Risco;

/**
 *
 * @author Victor
 */
public class MonitoracaoAnaliseDosRiscosJPanel extends javax.swing.JPanel {

    private List<Risco> listaDeRisco;

    private JTable tabelaAnalisarRiscos;
    private DefaultTableModel modeloTabelaAnalisarRiscos;

    Risco riscoSelecionado;

    MonitoracaoAnaliseDosRiscosCheckDialog monitoracaoAnaliseDosRiscosCheckDialog = new MonitoracaoAnaliseDosRiscosCheckDialog(null, true);

    /**
     * Creates new form MonitoracaoQuadroDeAvisosJPanel
     */
    public MonitoracaoAnaliseDosRiscosJPanel() {
        initComponents();
    }

    public void criarTabelaAnalisarRiscos() {

        tabelaAnalisarRiscos = new JTable();
        modeloTabelaAnalisarRiscos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modeloTabelaAnalisarRiscos.setColumnIdentifiers(new String[]{"Identificação", "Descrição", "Estado"});
        tabelaAnalisarRiscos.setModel(modeloTabelaAnalisarRiscos);
        tabelaRiscosAnaliseScrollPane.setViewportView(tabelaAnalisarRiscos);
        /*
         tabelaAnalisarRiscos.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent e){
         int riscoSelecionado = e.getClickCount();
                
         }
         });
         */
    }

    public void definirEventosTabelaDeRiscos() {
        tabelaAnalisarRiscos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    int selected = tabelaAnalisarRiscos.getSelectedRow();

                    //arvoreEARJTree.clearSelection();
                    for (int i = 0; i < listaDeRisco.size(); i++) {
                        
                        if (tabelaAnalisarRiscos.getValueAt(tabelaAnalisarRiscos.getSelectedRow(), 0).equals(listaDeRisco.get(i).getIdentificacao())) {
                            riscoSelecionado = listaDeRisco.get(i);
                            break;
                        }
                    }

                    //emissorJTextField.setText(RiscoSelecionado.getEmissor());
                    //probabilidadeJTextField.setText(Integer.toString(RiscoSelecionado.getProbabilidade()));
                    //impactoJTextField.setText((RiscoSelecionado.getImpacto()));
                    //grauDeSeveridadeJTextField.setText(Integer.toString(RiscoSelecionado.getGrauSeveridade()));
                } else if (e.getClickCount() == 2) {
                    int selected = tabelaAnalisarRiscos.getSelectedRow();

                    //arvoreEARJTree.clearSelection();
                    for (int i = 0; i < listaDeRisco.size(); i++) {

                        if (tabelaAnalisarRiscos.getValueAt(tabelaAnalisarRiscos.getSelectedRow(), 0).equals(listaDeRisco.get(i).getIdentificacao())) {
                            riscoSelecionado = listaDeRisco.get(i);

                            if (riscoSelecionado.getContem().getProjeto().getConcluido()) {
                                JOptionPane.showMessageDialog(null, "Projeto já concluído.");
                                riscoSelecionado = null;
                            } else {
                                monitoracaoAnaliseDosRiscosCheckDialog.setRiscoOcorreuFalse();
                                monitoracaoAnaliseDosRiscosCheckDialog.getListaGrupoRelacao(riscoSelecionado);
                                monitoracaoAnaliseDosRiscosCheckDialog.preencherInformacoes(riscoSelecionado);
                                monitoracaoAnaliseDosRiscosCheckDialog.criarTabelaCondicoes(riscoSelecionado);
                                monitoracaoAnaliseDosRiscosCheckDialog.setLocationRelativeTo(null);
                                monitoracaoAnaliseDosRiscosCheckDialog.setVisible(true);

                                List<Risco> novaListaDeRisco = new MonitoracaoAnaliseDosRiscosFacade().listarRiscosPOrdemGrauDeEsposicaoByStatus(riscoSelecionado.getContem().getProjeto(), "Mitigando");
                                atualizarTabelaDeAnaliseDeRiscos(novaListaDeRisco);
                                riscoSelecionado = null;
                            }
                            break;
                        }
                    }
                }

            }
        });
    }

    public void populaTabelaDeRiscos(List<Risco> novaListaDeRisco, boolean atualizacao) {
        if (atualizacao == false) {
            listaDeRisco = novaListaDeRisco;
        }
        List<Risco> listaTemp = new ArrayList<Risco>();
        for (int i = 0; i < novaListaDeRisco.size(); i++) {
            if (novaListaDeRisco.get(i).getPrioridade() > 0) {
                String[] linha = new String[]{novaListaDeRisco.get(i).getIdentificacao().toString(),
                    novaListaDeRisco.get(i).getDescricao().toString(),
                    novaListaDeRisco.get(i).getStatusRisco()};
                modeloTabelaAnalisarRiscos.addRow(linha);
            } else {
                listaTemp.add(novaListaDeRisco.get(i));
            }

        }

        for (int i = 0; i < listaTemp.size(); i++) {
            String[] linha = new String[]{novaListaDeRisco.get(i).getIdentificacao().toString(),
                novaListaDeRisco.get(i).getDescricao().toString(),
                novaListaDeRisco.get(i).getStatusRisco()};
            modeloTabelaAnalisarRiscos.addRow(linha);
        }
    }

    public void atualizarTabelaDeAnaliseDeRiscos(List<Risco> listaRiscos) {
        criarTabelaAnalisarRiscos();
        populaTabelaDeRiscos(listaRiscos, true);
        definirEventosTabelaDeRiscos();
    }

    public void habilitarBotoesMonitoracaoAnaliseDeRiscosJpanel(Boolean habilitar) {
        checarRiscoInternal.setEnabled(habilitar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabelaRiscosAnaliseScrollPane = new javax.swing.JScrollPane();
        checarRiscoInternal = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaRiscosAnaliseScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaRiscosAnaliseScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );

        checarRiscoInternal.setText("Checar Risco  ");
        checarRiscoInternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checarRiscoInternalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(checarRiscoInternal)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checarRiscoInternal)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checarRiscoInternalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checarRiscoInternalActionPerformed
        // TODO add your handling code here:

        if (riscoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um risco para checar.");
        } else {

            monitoracaoAnaliseDosRiscosCheckDialog.setRiscoOcorreuFalse();
            monitoracaoAnaliseDosRiscosCheckDialog.getListaGrupoRelacao(riscoSelecionado);
            monitoracaoAnaliseDosRiscosCheckDialog.preencherInformacoes(riscoSelecionado);
            monitoracaoAnaliseDosRiscosCheckDialog.criarTabelaCondicoes(riscoSelecionado);
            monitoracaoAnaliseDosRiscosCheckDialog.setLocationRelativeTo(null);
            monitoracaoAnaliseDosRiscosCheckDialog.setVisible(true);

            List<Risco> novaListaDeRisco = new MonitoracaoAnaliseDosRiscosFacade().listarRiscosPOrdemGrauDeEsposicaoByStatus(riscoSelecionado.getContem().getProjeto(), "Mitigando");
            atualizarTabelaDeAnaliseDeRiscos(novaListaDeRisco);
            riscoSelecionado = null;
        }
    }//GEN-LAST:event_checarRiscoInternalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checarRiscoInternal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane tabelaRiscosAnaliseScrollPane;
    // End of variables declaration//GEN-END:variables
}
