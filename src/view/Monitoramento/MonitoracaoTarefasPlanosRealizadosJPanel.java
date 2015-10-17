/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Monitoramento;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import model.Planocontingencia;
import model.Planomitigacao;
import view.tabelas.PlanoTabela;
import view.tabelas.PlanosRealizadosTabelaModel;

/**
 *
 * @author Victor
 */
public class MonitoracaoTarefasPlanosRealizadosJPanel extends javax.swing.JPanel {
    private JTable tabelaPlanosRealizadosJTable;
    private PlanosRealizadosTabelaModel planoTabelaModel;
    private List<Planocontingencia> listaPlaContigencia;
    private List<Planomitigacao> listaPlaMitigacao;
    private PlanosRealizadosTabelaModel modeloTabelaPlanosRealizados;
    private ArrayList<PlanoTabela> listaplanoTabela;

    /**
     * Creates new form MonitoracaoTarefasHistoricoDeTarefasJPanel
     */
    public MonitoracaoTarefasPlanosRealizadosJPanel() {
        initComponents();
    }
    
    public void criarTabelaPlanosRealizados(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {

        tabelaPlanosRealizadosJTable = new JTable();
        planoTabelaModel = new PlanosRealizadosTabelaModel();

        tabelaPlanosRealizadosJTable.setModel(planoTabelaModel);

        tabelaPlanosRealizadosJScrollPane.setViewportView(tabelaPlanosRealizadosJTable);

        listaPlaContigencia = listaPlanoContigencia;
        listaPlaMitigacao = listaPlanoMitigacao;

        planoTabelaModel.addListaDePlanos(criaListaDePlanosTabela(listaPlaContigencia, listaPlaMitigacao));

        tabelaPlanosRealizadosJTable.getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaPlanosRealizadosJTable.getColumnModel().getColumn(1).setMinWidth(0);
        tabelaPlanosRealizadosJTable.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabelaPlanosRealizadosJTable.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        modeloTabelaPlanosRealizados = new PlanosRealizadosTabelaModel();
        modeloTabelaPlanosRealizados = planoTabelaModel;

        tabelaPlanosRealizadosJTable.setModel(modeloTabelaPlanosRealizados);

        tabelaPlanosRealizadosJScrollPane.setViewportView(tabelaPlanosRealizadosJTable);
        
        for (int i = 0; i < (listaPlanoContigencia.size() + listaPlanoMitigacao.size()); i++){
            planoTabelaModel.isCellEditable(i, 0);
        }
    }
    
    public List<PlanoTabela> criaListaDePlanosTabela(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {
       listaplanoTabela = new ArrayList<PlanoTabela>();
       
       
       for (int i = 0; i < listaPlanoContigencia.size(); i++) {
           PlanoTabela planoTabela = new PlanoTabela();
           
           planoTabela.setIdentificacaoPlano(listaPlanoContigencia.get(i).getIdentificacaoPlanoContingencia());
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
           else{
               planoTabela.setDataRealizacao(listaPlanoContigencia.get(i).getDataRealizacao());
           }

           listaplanoTabela.add(planoTabela);

       }
       
       for (int i = 0; i < listaPlanoMitigacao.size(); i++) {
           PlanoTabela planoTabela = new PlanoTabela();
           
           planoTabela.setIdentificacaoPlano(listaPlanoMitigacao.get(i).getIdentificacaoPlanoMitigacao());
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
           else{
               planoTabela.setDataRealizacao(listaPlanoMitigacao.get(i).getDataRealizacao());
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

        MonitoracaoTarefasPlanosRealizadasJPanel = new javax.swing.JPanel();
        tabelaPlanosRealizadosJScrollPane = new javax.swing.JScrollPane();

        MonitoracaoTarefasPlanosRealizadasJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planos Realizados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout MonitoracaoTarefasPlanosRealizadasJPanelLayout = new javax.swing.GroupLayout(MonitoracaoTarefasPlanosRealizadasJPanel);
        MonitoracaoTarefasPlanosRealizadasJPanel.setLayout(MonitoracaoTarefasPlanosRealizadasJPanelLayout);
        MonitoracaoTarefasPlanosRealizadasJPanelLayout.setHorizontalGroup(
            MonitoracaoTarefasPlanosRealizadasJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaPlanosRealizadosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );
        MonitoracaoTarefasPlanosRealizadasJPanelLayout.setVerticalGroup(
            MonitoracaoTarefasPlanosRealizadasJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaPlanosRealizadosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonitoracaoTarefasPlanosRealizadasJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonitoracaoTarefasPlanosRealizadasJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MonitoracaoTarefasPlanosRealizadasJPanel;
    private javax.swing.JScrollPane tabelaPlanosRealizadosJScrollPane;
    // End of variables declaration//GEN-END:variables
}
