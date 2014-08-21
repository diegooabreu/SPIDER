/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Diogo
 */
public class RiscosResumoDeRiscosJPanel extends javax.swing.JPanel {

    private JTable tabelaResumoDeRiscos;
    private DefaultTableModel modeloTabelaDeResumoDeRiscos;

    /**
     * Creates new form ResmumoDeRiscos
     */
    public RiscosResumoDeRiscosJPanel() {
        initComponents();
    }

    public void criaTabelResumoDeRiscos() {
        tabelaResumoDeRiscos = new JTable();
        modeloTabelaDeResumoDeRiscos = new DefaultTableModel();

        modeloTabelaDeResumoDeRiscos.setColumnIdentifiers(new String[]{"Identificação", "Prioridade", "Estado", "Plano de Mitigação", "Plano de Contingencia"});
        tabelaResumoDeRiscos.setModel(modeloTabelaDeResumoDeRiscos);
        tabelaResumoDeRiscosJScrollPane.setViewportView(tabelaResumoDeRiscos);
    }

    public void preencherDadosTabelaResumoDeRiscos(List<Risco> listaRiscos) {
        for (int i = 0; i < listaRiscos.size(); i++) {
            //Verifica se o ID do projeto selecionado é igual ao ID do Projeto do Contém do Risco atual
            String identificacao = listaRiscos.get(i).getIdentificacao();
            String prioridade = Integer.toString(listaRiscos.get(i).getPrioridade());
            String statusRisco = listaRiscos.get(i).getStatusRisco();
            String planoMitigacao;
            String planoContingencia;
            if (listaRiscos.get(i).getPlanomitigacaoList().isEmpty()){
                planoMitigacao = "--";
            }
            else{
                planoMitigacao = Integer.toString(listaRiscos.get(i).getPlanomitigacaoList().size()) + " Plano(s)";
            }
            
            if (listaRiscos.get(i).getPlanocontingenciaList().isEmpty()){
                planoContingencia = "--";
            }
            else{
                planoContingencia = Integer.toString(listaRiscos.get(i).getPlanocontingenciaList().size()) + " Plano(s)";
            }
            

            String[] linha = new String[]{identificacao, prioridade, statusRisco, planoMitigacao, planoContingencia};
            modeloTabelaDeResumoDeRiscos.addRow(linha);
        }
    }

    public void reiniciarTabelaResumoDeRiscos(List<Risco> novaListaDeRisco) {
        modeloTabelaDeResumoDeRiscos = null;
        criaTabelResumoDeRiscos();
        preencherDadosTabelaResumoDeRiscos(novaListaDeRisco);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabelaResumoDeRiscosJPanel = new javax.swing.JPanel();
        tabelaResumoDeRiscosJScrollPane = new javax.swing.JScrollPane();

        tabelaResumoDeRiscosJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumo de Riscos"));

        javax.swing.GroupLayout tabelaResumoDeRiscosJPanelLayout = new javax.swing.GroupLayout(tabelaResumoDeRiscosJPanel);
        tabelaResumoDeRiscosJPanel.setLayout(tabelaResumoDeRiscosJPanelLayout);
        tabelaResumoDeRiscosJPanelLayout.setHorizontalGroup(
            tabelaResumoDeRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelaResumoDeRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabelaResumoDeRiscosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabelaResumoDeRiscosJPanelLayout.setVerticalGroup(
            tabelaResumoDeRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabelaResumoDeRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabelaResumoDeRiscosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumoDeRiscosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumoDeRiscosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel tabelaResumoDeRiscosJPanel;
    private javax.swing.JScrollPane tabelaResumoDeRiscosJScrollPane;
    // End of variables declaration//GEN-END:variables
}