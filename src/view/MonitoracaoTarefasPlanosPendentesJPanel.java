/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Planocontingencia;
import model.Planomitigacao;
import view.tabelas.PlanoTabela;
import view.tabelas.PlanoTabelaModel;

/**
 *
 * @author Victor
 */
public class MonitoracaoTarefasPlanosPendentesJPanel extends javax.swing.JPanel {

    private JTable tabelaPlanosPendentes;
    private PlanoTabelaModel modeloTabelaPlanosPendentes;
    private JTable tabelaPlanosPendentesJTable;
    private PlanoTabelaModel planoTabelaModel;
    private List<Planocontingencia> listaPlaContigencia;
    private List<Planomitigacao> listaPlaMitigacao;
    private List<PlanoTabela> listaplanoTabela;

    /**
     * Creates new form MonitoracaoTarefasTarefasPendentesJPanel
     */
    public MonitoracaoTarefasPlanosPendentesJPanel() {
        initComponents();
    }

    public void criarTabelaPlanosPendentes(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {

        tabelaPlanosPendentesJTable = new JTable();
        planoTabelaModel = new PlanoTabelaModel();

        tabelaPlanosPendentesJTable.setModel(planoTabelaModel);

        tabelaPlanosPendentesJScrollPane.setViewportView(tabelaPlanosPendentesJTable);

        listaPlaContigencia = listaPlanoContigencia;
        listaPlaMitigacao = listaPlanoMitigacao;

        planoTabelaModel.addListaDePlanos(criaListaDePlanosTabela(listaPlaContigencia, listaPlaMitigacao));

        tabelaPlanosPendentesJTable.getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaPlanosPendentesJTable.getColumnModel().getColumn(1).setMinWidth(0);
        tabelaPlanosPendentesJTable.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaPlanosPendentesJTable.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

       //tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(2).setMaxWidth(0);
        //tabelaSelecionarRiscosMonitorarJTable.getColumnModel().getColumn(2).setMinWidth(0);
        //tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        //tabelaSelecionarRiscosMonitorarJTable.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        modeloTabelaPlanosPendentes = new PlanoTabelaModel();
        modeloTabelaPlanosPendentes = planoTabelaModel;

        tabelaPlanosPendentesJTable.setModel(modeloTabelaPlanosPendentes);

        tabelaPlanosPendentesJScrollPane.setViewportView(tabelaPlanosPendentesJTable);

    }
    
    public List<PlanoTabela> criaListaDePlanosTabela(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {
       listaplanoTabela = new ArrayList<PlanoTabela>();
       
       
       for (int i = 0; i < listaPlanoContigencia.size(); i++) {
           PlanoTabela planoTabela = new PlanoTabela();
           
           planoTabela.setIdPlano(listaPlanoContigencia.get(i).getIdPlanoContingencia());
           planoTabela.setIdentificacaoRisco(listaPlanoContigencia.get(i).getIdRisco().toString());
           planoTabela.setDescricaoPlano(listaPlanoContigencia.get(i).getDescricaoPlanoContingencia());
           planoTabela.setResponsavel(listaPlanoContigencia.get(i).getResponsavel());
           if(listaPlanoContigencia.get(i).getIdMarcoDoProjeto() == null){
               planoTabela.setDataLimite(listaPlanoContigencia.get(i).getIdPontoDeControle().getDataPontoControle());
           }
           else{
               planoTabela.setDataLimite(listaPlanoContigencia.get(i).getIdMarcoDoProjeto().getDataMarcoProjeto());
           }
           planoTabela.setIdentificacaoRisco(listaPlanoContigencia.get(i).getIdRisco().getIdentificacao());
           planoTabela.setTipo("Contingencia");
           if(listaPlanoContigencia.get(i).getDataRealizacao() == null){
               planoTabela.setRealizado(false);
           }

           listaplanoTabela.add(planoTabela);

       }
       
       for (int i = 0; i < listaPlanoMitigacao.size(); i++) {
           PlanoTabela planoTabela = new PlanoTabela();
           
           planoTabela.setIdPlano(listaPlanoMitigacao.get(i).getIdPlanoMitigacao());
           planoTabela.setIdentificacaoRisco(listaPlanoMitigacao.get(i).getIdRisco().toString());
           planoTabela.setDescricaoPlano(listaPlanoMitigacao.get(i).getDescricaoPlanoMitigacao());
           planoTabela.setResponsavel(listaPlanoMitigacao.get(i).getResponsavel());
           if(listaPlanoMitigacao.get(i).getIdMarcoDoProjeto() == null){
               planoTabela.setDataLimite(listaPlanoMitigacao.get(i).getIdPontoDeControle().getDataPontoControle());
           }
           else{
               planoTabela.setDataLimite(listaPlanoMitigacao.get(i).getIdMarcoDoProjeto().getDataMarcoProjeto());
           }
           planoTabela.setIdentificacaoRisco(listaPlanoMitigacao.get(i).getIdRisco().getIdentificacao());
           planoTabela.setTipo("Mitigação");
           if(listaPlanoMitigacao.get(i).getDataRealizacao() == null){
               planoTabela.setRealizado(false);
           }

           listaplanoTabela.add(planoTabela);

       }
       
       

       return listaplanoTabela;

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MonitoracaoTarefasTarefasPendentesJPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane tabelaPlanosPendentesJScrollPane;
    // End of variables declaration//GEN-END:variables
}
