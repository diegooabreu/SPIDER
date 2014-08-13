/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProjetoJpaController;
import controller.RiscoJpaController;
import facade.PlanosFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Projeto;
import model.Risco;
import view.tabelas.PlanoTabela;
import view.tabelas.PlanosPendentesTabelaModel;

/**
 *
 * @author Victor
 */
public class MonitoracaoTarefasPlanosPendentesJPanel extends javax.swing.JPanel {

    private PlanosPendentesTabelaModel modeloTabelaPlanosPendentes;
    private JTable tabelaPlanosPendentesJTable;
    private PlanosPendentesTabelaModel planoTabelaModel;
    private List<Planocontingencia> listaPlaContigencia;
    private List<Planomitigacao> listaPlaMitigacao;
    private List<PlanoTabela> listaplanosTabela;
    private List<Risco> todosOsRiscos;
    private Projeto projetoSelecionado;

    PlanoTabela planoTabela = new PlanoTabela();

    /**
     * Creates new form MonitoracaoTarefasTarefasPendentesJPanel
     */
    public MonitoracaoTarefasPlanosPendentesJPanel() {
        initComponents();
    }

    public void criarTabelaPlanosPendentes(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {
        tabelaPlanosPendentesJTable = new JTable();
        planoTabelaModel = new PlanosPendentesTabelaModel();

        tabelaPlanosPendentesJTable.setModel(planoTabelaModel);

        tabelaPlanosPendentesJScrollPane.setViewportView(tabelaPlanosPendentesJTable);

        listaPlaContigencia = listaPlanoContigencia;
        listaPlaMitigacao = listaPlanoMitigacao;

        planoTabelaModel.addListaDePlanos(criaListaDePlanosTabela(listaPlaContigencia, listaPlaMitigacao));

        tabelaPlanosPendentesJTable.getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaPlanosPendentesJTable.getColumnModel().getColumn(1).setMinWidth(0);
        tabelaPlanosPendentesJTable.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaPlanosPendentesJTable.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        modeloTabelaPlanosPendentes = new PlanosPendentesTabelaModel();
        modeloTabelaPlanosPendentes = planoTabelaModel;

        tabelaPlanosPendentesJTable.setModel(modeloTabelaPlanosPendentes);

        tabelaPlanosPendentesJScrollPane.setViewportView(tabelaPlanosPendentesJTable);

        definirEventosTabela();
    }

    public List<PlanoTabela> criaListaDePlanosTabela(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {
        listaplanosTabela = new ArrayList<PlanoTabela>();

        for (int i = 0; i < listaPlanoContigencia.size(); i++) {
            planoTabela = new PlanoTabela();

            planoTabela.setIdentificacaoPlano(listaPlanoContigencia.get(i).getIdentificacaoPlanoContingencia());
            planoTabela.setIdentificacaoRisco(listaPlanoContigencia.get(i).getIdRisco().toString());
            planoTabela.setDescricaoPlano(listaPlanoContigencia.get(i).getDescricaoPlanoContingencia());
            planoTabela.setResponsavel(listaPlanoContigencia.get(i).getResponsavel());
            if (listaPlanoContigencia.get(i).getIdMarcoDoProjeto() == null) {
                planoTabela.setDataLimite(listaPlanoContigencia.get(i).getIdPontoDeControle().getDataPontoControle());
            } else {
                planoTabela.setDataLimite(listaPlanoContigencia.get(i).getIdMarcoDoProjeto().getDataMarcoProjeto());
            }
            planoTabela.setIdentificacaoRisco(listaPlanoContigencia.get(i).getIdRisco().getIdentificacao());
            planoTabela.setTipo("Contingencia");
            if (listaPlanoContigencia.get(i).getDataRealizacao() == null) {
                planoTabela.setRealizado(false);
            }

            listaplanosTabela.add(planoTabela);
        }

        for (int i = 0; i < listaPlanoMitigacao.size(); i++) {
            planoTabela = new PlanoTabela();

            planoTabela.setIdentificacaoPlano(listaPlanoMitigacao.get(i).getIdentificacaoPlanoMitigacao());
            planoTabela.setIdentificacaoRisco(listaPlanoMitigacao.get(i).getIdRisco().toString());
            planoTabela.setDescricaoPlano(listaPlanoMitigacao.get(i).getDescricaoPlanoMitigacao());
            planoTabela.setResponsavel(listaPlanoMitigacao.get(i).getResponsavel());
            if (listaPlanoMitigacao.get(i).getIdMarcoDoProjeto() == null) {
                planoTabela.setDataLimite(listaPlanoMitigacao.get(i).getIdPontoDeControle().getDataPontoControle());
            } else {
                planoTabela.setDataLimite(listaPlanoMitigacao.get(i).getIdMarcoDoProjeto().getDataMarcoProjeto());
            }
            planoTabela.setIdentificacaoRisco(listaPlanoMitigacao.get(i).getIdRisco().getIdentificacao());
            planoTabela.setTipo("Mitigação");
            if (listaPlanoMitigacao.get(i).getDataRealizacao() == null) {
                planoTabela.setRealizado(false);
            }

            listaplanosTabela.add(planoTabela);
        }

        return listaplanosTabela;

    }

    int selected;

    public void definirEventosTabela() {
        tabelaPlanosPendentesJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 1) {

                    selected = tabelaPlanosPendentesJTable.getSelectedRow();
                    planoTabelaModel.isCellEditable(selected, 0);

                    if ((boolean) planoTabelaModel.getValueAt(selected, 0) == false) {
                        planoTabelaModel.setValueAt(true, selected, 0);
                        // se monitorar estiver marcado entao desmarca
                    } else {
                        planoTabelaModel.setValueAt(false, selected, 0);
                    }

                }

                if (e.getClickCount() == 2) {
                    int selected = tabelaPlanosPendentesJTable.getSelectedRow();
                    int idPlano;

                    for (int i = 0; i < listaplanosTabela.size(); i++) {
                        if (i == selected) {

                            if (listaplanosTabela.get(selected).getTipo().equals("Mitigação")) {
                                if (listaplanosTabela.get(i).getIdPlano() == listaplanosTabela.get(selected).getIdPlano()) {
                                    idPlano = listaplanosTabela.get(i).getIdPlano();
                                    PrincipalJFrame.monitorarPlanosPendentesMaisInformaçõesInternalFrame.preencheTelaParaPlanoDeMitigacao(listaPlaMitigacao, idPlano);
                                    PrincipalJFrame.aparecerInternalFramePlanosPendentes();
                                    break;
                                }
                            }
                            if (listaplanosTabela.get(selected).getTipo().equals("Contingencia")) {
                                if (listaplanosTabela.get(i).getIdPlano() == listaplanosTabela.get(selected).getIdPlano()) {
                                    idPlano = listaplanosTabela.get(i).getIdPlano();
                                    PrincipalJFrame.monitorarPlanosPendentesMaisInformaçõesInternalFrame.preencheTelaParaPlanoDeContingencia(listaPlaContigencia, idPlano);
                                    PrincipalJFrame.aparecerInternalFramePlanosPendentes();
                                    break;

                                }
                            }
                        }
                    }
                }
            }
        }
        );
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
        jPanel1 = new javax.swing.JPanel();
        tabelaPlanosPendentesJScrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        SalvarRealizaçãoDePlanosJButton = new javax.swing.JButton();

        MonitoracaoTarefasTarefasPendentesJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planos Pendentes", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaPlanosPendentesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaPlanosPendentesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        SalvarRealizaçãoDePlanosJButton.setText("Salvar realização de planos");
        SalvarRealizaçãoDePlanosJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarRealizaçãoDePlanosJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(SalvarRealizaçãoDePlanosJButton))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(SalvarRealizaçãoDePlanosJButton)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MonitoracaoTarefasTarefasPendentesJPanelLayout = new javax.swing.GroupLayout(MonitoracaoTarefasTarefasPendentesJPanel);
        MonitoracaoTarefasTarefasPendentesJPanel.setLayout(MonitoracaoTarefasTarefasPendentesJPanelLayout);
        MonitoracaoTarefasTarefasPendentesJPanelLayout.setHorizontalGroup(
            MonitoracaoTarefasTarefasPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonitoracaoTarefasTarefasPendentesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonitoracaoTarefasTarefasPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MonitoracaoTarefasTarefasPendentesJPanelLayout.setVerticalGroup(
            MonitoracaoTarefasTarefasPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonitoracaoTarefasTarefasPendentesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void SalvarRealizaçãoDePlanosJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarRealizaçãoDePlanosJButtonActionPerformed
        Date dataAtual = new Date();
        PlanosFacade planosFacade = new PlanosFacade();
        for (int i = 0; i < listaplanosTabela.size(); i++) {
            if ((Boolean) planoTabelaModel.getValueAt(i, 0) == true) {

                if (listaplanosTabela.get(i).getTipo().equals("Mitigação")) {
                    for (int j = 0; j < listaPlaMitigacao.size(); j++) {
                        if (listaPlaMitigacao.get(j).getIdentificacaoPlanoMitigacao().equals(listaplanosTabela.get(i).getIdentificacaoPlano())) {
                            listaPlaMitigacao.get(j).setDataRealizacao(dataAtual);
                            planosFacade.editarPlanodeMitigacao(listaPlaMitigacao.get(j));
                            break;
                        }
                    }
                } else if (listaplanosTabela.get(i).getTipo().equals("Contingencia")) {
                    for (int j = 0; j < listaPlaContigencia.size(); j++) {
                        if (listaPlaContigencia.get(j).getIdentificacaoPlanoContingencia().equals(listaplanosTabela.get(i).getIdentificacaoPlano())) {
                            listaPlaContigencia.get(j).setDataRealizacao(dataAtual);
                            planosFacade.editarPlanodeContingencia(listaPlaContigencia.get(j));
                            break;
                        }
                    }
                }
            }
            if (listaplanosTabela.get(i).getDataRealizacao() != null) {
                RiscosGerenciarRiscosFacade riscosFacade = new RiscosGerenciarRiscosFacade();
                List<Risco> listaDeRiscos = riscosFacade.listarRiscos();
                for (int l = 0; l < listaplanosTabela.size(); l++) {
                    String identificaDoRisco = listaplanosTabela.get(l).getIdentificacaoRisco();
                    for (int k = 0; k < listaplanosTabela.size(); k++) {
                        if (identificaDoRisco.equals(listaDeRiscos.get(k).getIdentificacao())) {
                            listaDeRiscos.get(k).setStatusRisco("Novo");
                        }
                    }
                }
            }
        }
        criarTabelaPlanosPendentes(listaPlaContigencia, listaPlaMitigacao);
        JOptionPane.showMessageDialog(this, "O(s) Plano(s) selecionados foram marcados como realizados");
    }//GEN-LAST:event_SalvarRealizaçãoDePlanosJButtonActionPerformed

    public void mudaStatusDeRisco(){
        ProjetoJpaController projeto = new ProjetoJpaController();
        RiscoJpaController Riscos = new RiscoJpaController();
        todosOsRiscos = Riscos.findRiscosByIdProjeto(projetoSelecionado);
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MonitoracaoTarefasTarefasPendentesJPanel;
    private javax.swing.JButton SalvarRealizaçãoDePlanosJButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane tabelaPlanosPendentesJScrollPane;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the projetoSelecionado
     */
    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    /**
     * @param projetoSelecionado the projetoSelecionado to set
     */
    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }
}
