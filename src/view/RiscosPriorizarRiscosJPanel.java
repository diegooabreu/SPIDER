/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.java.accessibility.AccessBridge;
import controller.RiscoJpaController;
import facade.RiscosGerenciarRiscosFacade;
import facade.RiscosSelecionarRiscosParaMonitorarFacade;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.sql.RowSetEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.Historicoalteracao;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Victor
 */
public class RiscosPriorizarRiscosJPanel extends javax.swing.JPanel {

    private JTable tabelaDeRiscosJTable;
    private DefaultTableModel modeloTabelaDeRiscosJTable;
    private List<Risco> listaDeRisco;
    private Risco RiscoSelecionado;
    private List<Risco> listaDeRiscoModificada;
    public int riscoSelecionado;
    public Projeto projetoSelecionado;
    RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();

    /**
     * Creates new form RiscosPriorizarRiscosJPanel
     */
    public RiscosPriorizarRiscosJPanel() {
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

        RiscosPriorizarRiscosJPanel = new javax.swing.JPanel();
        RiscosPriorizarRiscosSalvarAlteracoesJButton = new javax.swing.JButton();
        RiscosPriorizarRiscosAcoesJPanel = new javax.swing.JPanel();
        RiscosPriorizarRiscosAcoesAumentarPrioridadeJButton = new javax.swing.JButton();
        RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButton = new javax.swing.JButton();
        TabelaDeRiscosJScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        emissorJLabel = new javax.swing.JLabel();
        probabilidadeJLabel = new javax.swing.JLabel();
        impactoJLabel = new javax.swing.JLabel();
        grauDeSeveridadeJLabel = new javax.swing.JLabel();
        emissorJTextField = new javax.swing.JTextField();
        probabilidadeJTextField = new javax.swing.JTextField();
        impactoJTextField = new javax.swing.JTextField();
        grauDeSeveridadeJTextField = new javax.swing.JTextField();
        descartarAlteraçõesJButton = new javax.swing.JButton();
        resetarPrioridadesJButton = new javax.swing.JButton();
        MonitorarRisco = new javax.swing.JButton();

        RiscosPriorizarRiscosJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Priorizar Riscos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        RiscosPriorizarRiscosSalvarAlteracoesJButton.setText("Salvar alterações de risco");
        RiscosPriorizarRiscosSalvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RiscosPriorizarRiscosSalvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        RiscosPriorizarRiscosAcoesJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        RiscosPriorizarRiscosAcoesAumentarPrioridadeJButton.setText("Aumentar prioridade");
        RiscosPriorizarRiscosAcoesAumentarPrioridadeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RiscosPriorizarRiscosAcoesAumentarPrioridadeJButtonActionPerformed(evt);
            }
        });

        RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButton.setText("Diminuir prioridade");
        RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RiscosPriorizarRiscosAcoesJPanelLayout = new javax.swing.GroupLayout(RiscosPriorizarRiscosAcoesJPanel);
        RiscosPriorizarRiscosAcoesJPanel.setLayout(RiscosPriorizarRiscosAcoesJPanelLayout);
        RiscosPriorizarRiscosAcoesJPanelLayout.setHorizontalGroup(
            RiscosPriorizarRiscosAcoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiscosPriorizarRiscosAcoesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RiscosPriorizarRiscosAcoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RiscosPriorizarRiscosAcoesAumentarPrioridadeJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RiscosPriorizarRiscosAcoesJPanelLayout.setVerticalGroup(
            RiscosPriorizarRiscosAcoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RiscosPriorizarRiscosAcoesJPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(RiscosPriorizarRiscosAcoesAumentarPrioridadeJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButton)
                .addGap(25, 25, 25))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Outras informações"));

        emissorJLabel.setText("Emissor:");

        probabilidadeJLabel.setText("Probabilidade:");

        impactoJLabel.setText("Impacto:");

        grauDeSeveridadeJLabel.setText("Grau de Severidade");

        emissorJTextField.setEditable(false);
        emissorJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emissorJTextFieldActionPerformed(evt);
            }
        });

        probabilidadeJTextField.setEditable(false);

        impactoJTextField.setEditable(false);

        grauDeSeveridadeJTextField.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(probabilidadeJLabel)
                            .addComponent(emissorJLabel))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emissorJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(probabilidadeJTextField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(grauDeSeveridadeJLabel)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(impactoJLabel)
                                .addGap(70, 70, 70)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(impactoJTextField)
                            .addComponent(grauDeSeveridadeJTextField))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emissorJLabel)
                    .addComponent(emissorJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(probabilidadeJLabel)
                    .addComponent(probabilidadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(impactoJLabel)
                    .addComponent(impactoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grauDeSeveridadeJLabel)
                    .addComponent(grauDeSeveridadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        descartarAlteraçõesJButton.setText("Descartar alterações");
        descartarAlteraçõesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descartarAlteraçõesJButtonActionPerformed(evt);
            }
        });

        resetarPrioridadesJButton.setText("Resetar Prioridades");
        resetarPrioridadesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetarPrioridadesJButtonActionPerformed(evt);
            }
        });

        MonitorarRisco.setText("Selecionar risco para monitorar");
        MonitorarRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonitorarRiscoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RiscosPriorizarRiscosJPanelLayout = new javax.swing.GroupLayout(RiscosPriorizarRiscosJPanel);
        RiscosPriorizarRiscosJPanel.setLayout(RiscosPriorizarRiscosJPanelLayout);
        RiscosPriorizarRiscosJPanelLayout.setHorizontalGroup(
            RiscosPriorizarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiscosPriorizarRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RiscosPriorizarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabelaDeRiscosJScrollPane)
                    .addGroup(RiscosPriorizarRiscosJPanelLayout.createSequentialGroup()
                        .addComponent(RiscosPriorizarRiscosAcoesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(RiscosPriorizarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RiscosPriorizarRiscosSalvarAlteracoesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descartarAlteraçõesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resetarPrioridadesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(RiscosPriorizarRiscosJPanelLayout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(MonitorarRisco)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RiscosPriorizarRiscosJPanelLayout.setVerticalGroup(
            RiscosPriorizarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RiscosPriorizarRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabelaDeRiscosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MonitorarRisco)
                .addGap(18, 18, 18)
                .addGroup(RiscosPriorizarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(RiscosPriorizarRiscosJPanelLayout.createSequentialGroup()
                        .addGroup(RiscosPriorizarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RiscosPriorizarRiscosAcoesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(RiscosPriorizarRiscosJPanelLayout.createSequentialGroup()
                        .addComponent(resetarPrioridadesJButton)
                        .addGap(18, 18, 18)
                        .addComponent(descartarAlteraçõesJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RiscosPriorizarRiscosSalvarAlteracoesJButton)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RiscosPriorizarRiscosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RiscosPriorizarRiscosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void getProjetoSelecionado(Projeto projeto) {
        projetoSelecionado = projeto;
    }

    private void RiscosPriorizarRiscosSelecionarMonitorarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RiscosPriorizarRiscosSelecionarMonitorarJButtonActionPerformed

        RiscosSelecionarRiscosParaMonitorarJFrame selecionar = new RiscosSelecionarRiscosParaMonitorarJFrame();

        selecionar.criarTabelaSelecionarRiscos(listaDeRisco);
        selecionar.setLocationRelativeTo(this);
        selecionar.setVisible(true);
        selecionar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
// TODO add your handling code here:
    }//GEN-LAST:event_RiscosPriorizarRiscosSelecionarMonitorarJButtonActionPerformed

    private void RiscosPriorizarRiscosAcoesAumentarPrioridadeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RiscosPriorizarRiscosAcoesAumentarPrioridadeJButtonActionPerformed
        int linhaSelecionada = tabelaDeRiscosJTable.getSelectedRow();
        if (linhaSelecionada > 0) {
            listaDeRiscoModificada = listaDeRisco;
            Risco riscoSelecionado = listaDeRiscoModificada.get(linhaSelecionada);

            listaDeRiscoModificada.set(linhaSelecionada, listaDeRiscoModificada.get(linhaSelecionada - 1));
            listaDeRiscoModificada.set(linhaSelecionada - 1, riscoSelecionado);

            criaTabela();
            populaTabelaDeRiscos(listaDeRiscoModificada, true);

            tabelaDeRiscosJTable.addRowSelectionInterval(linhaSelecionada - 1, linhaSelecionada - 1);
        }

    }//GEN-LAST:event_RiscosPriorizarRiscosAcoesAumentarPrioridadeJButtonActionPerformed

    private void RiscosPriorizarRiscosSalvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RiscosPriorizarRiscosSalvarAlteracoesJButtonActionPerformed
        for (int i = 0; i < modeloTabelaDeRiscosJTable.getRowCount(); i++) {
            String identificadorRisco = (String) tabelaDeRiscosJTable.getValueAt(i, 0);
            for (int j = 0; j < listaDeRiscoModificada.size(); j++) {
                if (identificadorRisco == listaDeRiscoModificada.get(j).getIdentificacao()) {
                    Risco objRisco = listaDeRiscoModificada.get(j);
                    objRisco.setPrioridade(i + 1);
                    RiscosGerenciarRiscosFacade riscoFacade = new RiscosGerenciarRiscosFacade();
                    riscoFacade.editarRisco(objRisco);

                    Historicoalteracao historico = new Historicoalteracao();
                    historico.setDescricaoAlteracao("Prioridade do Risco alterada");
                    Calendar c = Calendar.getInstance();
                    historico.setDataAlteracao(c.getTime());
                    historico.setIdRisco(objRisco);
                    riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                    break;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Riscos priorizados com sucesso");
    }//GEN-LAST:event_RiscosPriorizarRiscosSalvarAlteracoesJButtonActionPerformed

    private void RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButtonActionPerformed
        int linhaSelecionada = tabelaDeRiscosJTable.getSelectedRow();
        if (linhaSelecionada < listaDeRisco.size() - 1) {
            listaDeRiscoModificada = listaDeRisco;
            Risco riscoSelecionado = listaDeRiscoModificada.get(linhaSelecionada);

            listaDeRiscoModificada.set(linhaSelecionada, listaDeRiscoModificada.get(linhaSelecionada + 1));
            listaDeRiscoModificada.set(linhaSelecionada + 1, riscoSelecionado);

            criaTabela();
            populaTabelaDeRiscos(listaDeRiscoModificada, true);

            tabelaDeRiscosJTable.addRowSelectionInterval(linhaSelecionada + 1, linhaSelecionada + 1);
        }
    }//GEN-LAST:event_RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButtonActionPerformed

    private void emissorJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emissorJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emissorJTextFieldActionPerformed

    private void resetarPrioridadesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetarPrioridadesJButtonActionPerformed
        listaDeRiscoModificada = preencheListaModificada(listaDeRisco);
        for (int j = 0; j < listaDeRiscoModificada.size(); j++) {
            Risco objRisco = listaDeRiscoModificada.get(j);
            objRisco.setPrioridade(0);
            RiscosGerenciarRiscosFacade riscoFacade = new RiscosGerenciarRiscosFacade();
            riscoFacade.editarRisco(objRisco);
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Prioridade do Risco resetada");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(objRisco);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
        }
        RiscosGerenciarRiscosFacade rGRfacade = new RiscosGerenciarRiscosFacade();
        List<Risco> listaDeRiscoLocal = rGRfacade.listarRiscosPOrdemGrauDeEsposicao(projetoSelecionado);
        criaTabela();
        populaTabelaDeRiscos(listaDeRiscoLocal, true);
        definirEventosTabelaPriorizarRiscos();
        JOptionPane.showMessageDialog(this, "Prioridade dos riscos foram resetadas");
    }//GEN-LAST:event_resetarPrioridadesJButtonActionPerformed

    private void descartarAlteraçõesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descartarAlteraçõesJButtonActionPerformed
        RiscosGerenciarRiscosFacade rGRfacade = new RiscosGerenciarRiscosFacade();
        List<Risco> listaDeRiscoLocal = rGRfacade.listarRiscosPOrdemGrauDeEsposicao(projetoSelecionado);
        criaTabela();
        populaTabelaDeRiscos(listaDeRiscoLocal, true);
        definirEventosTabelaPriorizarRiscos();
    }//GEN-LAST:event_descartarAlteraçõesJButtonActionPerformed

    private void MonitorarRiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonitorarRiscoActionPerformed
        RiscoSelecioanrRiscoParaMonitorarInternalJFrame selecionar = new RiscoSelecioanrRiscoParaMonitorarInternalJFrame();
        selecionar.setVisible(true);
        selecionar.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        PrincipalJFrame.riscoSelecioanrRiscoParaMonitorarInternalJFrame.criarTabelaSelecionarRiscos(listaDeRisco);
        PrincipalJFrame.aparecerInternalFrame();
    }//GEN-LAST:event_MonitorarRiscoActionPerformed

    public void definirEventosTabelaPriorizarRiscos() {
        tabelaDeRiscosJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    int selected = tabelaDeRiscosJTable.getSelectedRow();

                    //arvoreEARJTree.clearSelection();
                    for (int i = 0; i < listaDeRisco.size(); i++) {

                        if (tabelaDeRiscosJTable.getValueAt(tabelaDeRiscosJTable.getSelectedRow(), 0).equals(listaDeRisco.get(i).getIdentificacao())) {
                            RiscoSelecionado = listaDeRisco.get(i);
                            break;
                        }
                    }

                    emissorJTextField.setText(RiscoSelecionado.getEmissor());
                    probabilidadeJTextField.setText(Integer.toString(RiscoSelecionado.getProbabilidade()));
                    impactoJTextField.setText((RiscoSelecionado.getImpacto()));
                    grauDeSeveridadeJTextField.setText(Double.toString(RiscoSelecionado.getGrauSeveridade()));

                }

            }
        });
    }

    public void criaTabela() {
        limparCamposOutrasInformacoes();
        tabelaDeRiscosJTable = new JTable();
        modeloTabelaDeRiscosJTable = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col) {  
            return false;  
            }  
        };
        modeloTabelaDeRiscosJTable.setColumnIdentifiers(new String[]{"Identificação", "Descrição", "Estado"});
        tabelaDeRiscosJTable.setModel(modeloTabelaDeRiscosJTable);
        TabelaDeRiscosJScrollPane.setViewportView(tabelaDeRiscosJTable);

        tabelaDeRiscosJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int riscoSelecionado = e.getClickCount();

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
                modeloTabelaDeRiscosJTable.addRow(linha);
            } else {
                listaTemp.add(novaListaDeRisco.get(i));
            }

        }

        for (int i = 0; i < listaTemp.size(); i++) {
            String[] linha = new String[]{novaListaDeRisco.get(i).getIdentificacao().toString(),
                novaListaDeRisco.get(i).getDescricao().toString(),
                novaListaDeRisco.get(i).getStatusRisco()};
            modeloTabelaDeRiscosJTable.addRow(linha);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MonitorarRisco;
    private javax.swing.JButton RiscosPriorizarRiscosAcoesAumentarPrioridadeJButton;
    private javax.swing.JButton RiscosPriorizarRiscosAcoesDiminuirPrioridadeJButton;
    private javax.swing.JPanel RiscosPriorizarRiscosAcoesJPanel;
    private javax.swing.JPanel RiscosPriorizarRiscosJPanel;
    private javax.swing.JButton RiscosPriorizarRiscosSalvarAlteracoesJButton;
    private javax.swing.JScrollPane TabelaDeRiscosJScrollPane;
    private javax.swing.JButton descartarAlteraçõesJButton;
    private javax.swing.JLabel emissorJLabel;
    private javax.swing.JTextField emissorJTextField;
    private javax.swing.JLabel grauDeSeveridadeJLabel;
    private javax.swing.JTextField grauDeSeveridadeJTextField;
    private javax.swing.JLabel impactoJLabel;
    private javax.swing.JTextField impactoJTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel probabilidadeJLabel;
    private javax.swing.JTextField probabilidadeJTextField;
    private javax.swing.JButton resetarPrioridadesJButton;
    // End of variables declaration//GEN-END:variables

    private List<Risco> preencheListaModificada(List<Risco> listaDeRisco) {
        List<Risco> listaModificada = new ArrayList<Risco>();
        for (int i = 0; i < listaDeRisco.size(); i++) {
            listaModificada.add(listaDeRisco.get(i));
        }
        return listaModificada;
    }

    private void limparCamposOutrasInformacoes() {
        emissorJTextField.setText("");
        probabilidadeJTextField.setText("");
        impactoJTextField.setText("");
        grauDeSeveridadeJTextField.setText("");
    }
}
