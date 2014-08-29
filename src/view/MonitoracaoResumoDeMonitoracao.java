/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Risco;
import view.tabelas.PlanoTabela;

/**
 *
 * @author Diogo
 */
public class MonitoracaoResumoDeMonitoracao extends javax.swing.JPanel {

    private JTable tabelaResumoDeStatusDeRiscos;
    private DefaultTableModel modeloTabelaDeResumoDeStatusDeRiscos;
    private JTable tabelaResumoPlanosPendentes;
    private DefaultTableModel modeloTabelaDeResumoPlanosPendentes;
    private JTable tabelaResumoDeMarcosEPontosDeControle;
    private DefaultTableModel modeloTabelaDeResumoDeMarcosEPontosDeControle;

    /**
     * Creates new form MonitoracaoResumoDeMonitoracao
     */
    public MonitoracaoResumoDeMonitoracao() {
        initComponents();
    }

    public void criaTabelResumoDeStatusDeRiscos() {
        tabelaResumoDeStatusDeRiscos = new JTable();
        modeloTabelaDeResumoDeStatusDeRiscos = new DefaultTableModel();

        modeloTabelaDeResumoDeStatusDeRiscos.setColumnIdentifiers(new String[]{"Identificação", "Emissor", "Estado"});
        tabelaResumoDeStatusDeRiscos.setModel(modeloTabelaDeResumoDeStatusDeRiscos);
        tabelaResumoStatusDeRiscosJScrollPane.setViewportView(tabelaResumoDeStatusDeRiscos);
    }

    public void preencherDadosTabelResumoDeStatusDeRiscos(List<Risco> listaRiscos) {
        for (int i = 0; i < listaRiscos.size(); i++) {
            if (!(listaRiscos.get(i).getStatusRisco().equals("Novo"))) {
                String identificacao = listaRiscos.get(i).getIdentificacao();
                String emissor = (listaRiscos.get(i).getEmissor());
                String statusRisco = listaRiscos.get(i).getStatusRisco();

                String[] linha = new String[]{identificacao, emissor, statusRisco};
                modeloTabelaDeResumoDeStatusDeRiscos.addRow(linha);
            }
        }
    }

    public void criaTabelResumoDePlanosPendentes() {
        tabelaResumoPlanosPendentes = new JTable();
        modeloTabelaDeResumoPlanosPendentes = new DefaultTableModel();

        modeloTabelaDeResumoPlanosPendentes.setColumnIdentifiers(new String[]{"Identificação do Plano", "Identificação do Risco", "Responsável"});
        tabelaResumoPlanosPendentes.setModel(modeloTabelaDeResumoPlanosPendentes);
        tabelaResumoDePlanosPendentesJScrollPane.setViewportView(tabelaResumoPlanosPendentes);
    }

    public void criaListaDePlanosTabela(List<Planocontingencia> listaPlanoContigencia, List<Planomitigacao> listaPlanoMitigacao) {
        for (int i = 0; i < listaPlanoMitigacao.size(); i++) {
            String identificacaoPlano = listaPlanoMitigacao.get(i).getIdentificacaoPlanoMitigacao();
            String identificacaoRisco = listaPlanoMitigacao.get(i).getIdRisco().getIdentificacao();
            String responsavel = listaPlanoMitigacao.get(i).getResponsavel();

            String[] linha = new String[]{identificacaoPlano, identificacaoRisco, responsavel};
            modeloTabelaDeResumoPlanosPendentes.addRow(linha);
        }

        for (int j = 0; j < listaPlanoContigencia.size(); j++) {
            String identificacaoPlano = listaPlanoContigencia.get(j).getIdentificacaoPlanoContingencia();
            String identificacaoRisco = listaPlanoContigencia.get(j).getIdRisco().getIdentificacao();
            String responsavel = listaPlanoContigencia.get(j).getResponsavel();

            String[] linha = new String[]{identificacaoPlano, identificacaoRisco, responsavel};
            modeloTabelaDeResumoPlanosPendentes.addRow(linha);
        }

        respostaTotalDePlanosPendentesJLabel.setText(Integer.toString(listaPlanoContigencia.size() + listaPlanoMitigacao.size()));
    }

    public void criaTabelResumoDeMarcosEPontosDeControle() {
        tabelaResumoDeMarcosEPontosDeControle = new JTable();
        modeloTabelaDeResumoDeMarcosEPontosDeControle = new DefaultTableModel();

        modeloTabelaDeResumoDeMarcosEPontosDeControle.setColumnIdentifiers(new String[]{"Tipo", "Data"});
        tabelaResumoDeMarcosEPontosDeControle.setModel(modeloTabelaDeResumoDeMarcosEPontosDeControle);
        tabelaResumosDeMarcosEPontosDeControleJScrollPane.setViewportView(tabelaResumoDeMarcosEPontosDeControle);
    }

    public void preencheTabelResumoDeMarcosEPontoDeControle(List<Marcodoprojeto> listaMarcosDoProjeto, List<Pontodecontrole> listaPontoDeControle) {
        //SimpleDateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dataFormatada = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dataString;
        Date dataAtual = new Date();
        dataString = dataFormatada.format(dataAtual);
        
        for (int i = 0; i < listaMarcosDoProjeto.size(); i++) {
            String dataMP = dataFormatada.format(listaMarcosDoProjeto.get(i).getDataMarcoProjeto());
            if ((listaMarcosDoProjeto.get(i).getDataMarcoProjeto().after(dataAtual)) || (dataMP.equals(dataString))) {
                String tipo = "Marco do Projeto";

                String[] linha = new String[]{tipo, dataMP};
                modeloTabelaDeResumoDeMarcosEPontosDeControle.addRow(linha);
            }
        }

        for (int i = 0; i < listaPontoDeControle.size(); i++) {
            String dataPC = dataFormatada.format(listaPontoDeControle.get(i).getDataPontoControle());
            if ((listaPontoDeControle.get(i).getDataPontoControle().after(dataAtual)) || (dataPC.equals(dataString))) {
                String tipo = "Ponto de Controle";

                String[] linha = new String[]{tipo, dataPC};
                modeloTabelaDeResumoDeMarcosEPontosDeControle.addRow(linha);
            }

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

        tabelaResumoStatusDeRiscosJPanel = new javax.swing.JPanel();
        tabelaResumoStatusDeRiscosJScrollPane = new javax.swing.JScrollPane();
        tabelaResumoDePlanosPendentesJPanel = new javax.swing.JPanel();
        tabelaResumoDePlanosPendentesJScrollPane = new javax.swing.JScrollPane();
        totalDePlanosPendentesJLabel = new javax.swing.JLabel();
        respostaTotalDePlanosPendentesJLabel = new javax.swing.JLabel();
        tabelaResumosDeMarcosEPontosDeControleJPanel = new javax.swing.JPanel();
        tabelaResumosDeMarcosEPontosDeControleJScrollPane = new javax.swing.JScrollPane();

        tabelaResumoStatusDeRiscosJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Riscos Mitigando/Contingenciando"));

        javax.swing.GroupLayout tabelaResumoStatusDeRiscosJPanelLayout = new javax.swing.GroupLayout(tabelaResumoStatusDeRiscosJPanel);
        tabelaResumoStatusDeRiscosJPanel.setLayout(tabelaResumoStatusDeRiscosJPanelLayout);
        tabelaResumoStatusDeRiscosJPanelLayout.setHorizontalGroup(
            tabelaResumoStatusDeRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumoStatusDeRiscosJScrollPane)
        );
        tabelaResumoStatusDeRiscosJPanelLayout.setVerticalGroup(
            tabelaResumoStatusDeRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumoStatusDeRiscosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        tabelaResumoDePlanosPendentesJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumo de Planos Pendentes"));

        javax.swing.GroupLayout tabelaResumoDePlanosPendentesJPanelLayout = new javax.swing.GroupLayout(tabelaResumoDePlanosPendentesJPanel);
        tabelaResumoDePlanosPendentesJPanel.setLayout(tabelaResumoDePlanosPendentesJPanelLayout);
        tabelaResumoDePlanosPendentesJPanelLayout.setHorizontalGroup(
            tabelaResumoDePlanosPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumoDePlanosPendentesJScrollPane)
        );
        tabelaResumoDePlanosPendentesJPanelLayout.setVerticalGroup(
            tabelaResumoDePlanosPendentesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumoDePlanosPendentesJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        totalDePlanosPendentesJLabel.setText("Total de Planos Pendentes:");

        tabelaResumosDeMarcosEPontosDeControleJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Próximos Marcos/Pontos de Controle"));

        javax.swing.GroupLayout tabelaResumosDeMarcosEPontosDeControleJPanelLayout = new javax.swing.GroupLayout(tabelaResumosDeMarcosEPontosDeControleJPanel);
        tabelaResumosDeMarcosEPontosDeControleJPanel.setLayout(tabelaResumosDeMarcosEPontosDeControleJPanelLayout);
        tabelaResumosDeMarcosEPontosDeControleJPanelLayout.setHorizontalGroup(
            tabelaResumosDeMarcosEPontosDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumosDeMarcosEPontosDeControleJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        tabelaResumosDeMarcosEPontosDeControleJPanelLayout.setVerticalGroup(
            tabelaResumosDeMarcosEPontosDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaResumosDeMarcosEPontosDeControleJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelaResumoStatusDeRiscosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabelaResumoDePlanosPendentesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabelaResumosDeMarcosEPontosDeControleJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(totalDePlanosPendentesJLabel)
                        .addGap(10, 10, 10)
                        .addComponent(respostaTotalDePlanosPendentesJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 644, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabelaResumoStatusDeRiscosJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabelaResumoDePlanosPendentesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalDePlanosPendentesJLabel)
                    .addComponent(respostaTotalDePlanosPendentesJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(tabelaResumosDeMarcosEPontosDeControleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel respostaTotalDePlanosPendentesJLabel;
    private javax.swing.JPanel tabelaResumoDePlanosPendentesJPanel;
    private javax.swing.JScrollPane tabelaResumoDePlanosPendentesJScrollPane;
    private javax.swing.JPanel tabelaResumoStatusDeRiscosJPanel;
    private javax.swing.JScrollPane tabelaResumoStatusDeRiscosJScrollPane;
    private javax.swing.JPanel tabelaResumosDeMarcosEPontosDeControleJPanel;
    private javax.swing.JScrollPane tabelaResumosDeMarcosEPontosDeControleJScrollPane;
    private javax.swing.JLabel totalDePlanosPendentesJLabel;
    // End of variables declaration//GEN-END:variables
}
