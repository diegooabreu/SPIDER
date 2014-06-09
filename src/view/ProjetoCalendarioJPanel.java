/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;


import facade.ProjetoCalendarioFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.DateFormat;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marcodoprojeto;
import model.Pontodecontrole;
import model.Projeto;
import net.sf.nachocalendar.tasks.DefaultTask;

/**
 *
 * @author Diogo
 */
public class ProjetoCalendarioJPanel extends javax.swing.JPanel {

    ProjetoCalendarioFacade projetoCalendarioFacade = new ProjetoCalendarioFacade();
    
    
    JTable tabelaPontoDeControle;
    JTable tabelaMarcoDoProjeto;
    DefaultTableModel modeloTabelaMarcoDoProjeto;
    DefaultTableModel modeloTabelaPontoDeControle;

    
    List<Marcodoprojeto> listaMarcosDoProjeto;
    List<Pontodecontrole> listaPontosDeControle;
    
    Projeto projetoSelecionado;
    Marcodoprojeto marcoSelecionado;
    Pontodecontrole pontoDeControleSelecionado;
    
    public ProjetoCalendarioJPanel() {
        initComponents();
        datePanel1.setRenderer(taskRenderer1);
        taskRenderer1.setVisible(false);
        
        criarTabelasMarcoPontoDeControle();
        popularTabelaMarcoPontoDeControle();
        definirEventosTabelaMarcosDoProjeto();
        definirEventosTabelaPontosDeControle();
        
    }

    
    
    public void getProjetoSelecionado(Projeto projetoSelecionado){
        this.projetoSelecionado = projetoSelecionado;
    }
    
    public void criarTabelasMarcoPontoDeControle(){
        
        tabelaMarcoDoProjeto = new JTable();
        tabelaPontoDeControle = new JTable();
        
        modeloTabelaMarcoDoProjeto = new DefaultTableModel();
        modeloTabelaPontoDeControle = new DefaultTableModel();
        
        modeloTabelaMarcoDoProjeto.setColumnIdentifiers(new Object[]{"Marco do Projeto" , "Data"});
        modeloTabelaPontoDeControle.setColumnIdentifiers(new Object[]{"Ponto de Controle" , "Data"});
        
        tabelaMarcoDoProjeto.setModel(modeloTabelaMarcoDoProjeto);
        tabelaPontoDeControle.setModel(modeloTabelaPontoDeControle);
        
        tabelaMarcosDoProjetoJScrollPane.setViewportView(tabelaMarcoDoProjeto);
        tabelaPontoDeControleJScrollPane.setViewportView(tabelaPontoDeControle);
        
    }
    
    private void getListaMarcosPontosDeControle(){
        
        listaMarcosDoProjeto = projetoCalendarioFacade.getListaMarcosDoProjetoSelecionado(projetoSelecionado);
        listaPontosDeControle = projetoCalendarioFacade.getListaPontosDeControleDoProjetoSelecionado(projetoSelecionado);
        
        
    }
    
    public void popularTabelaMarcoPontoDeControle(){
        
        getListaMarcosPontosDeControle();
        
        // Popular tabela Pontos de Controle
        if (listaPontosDeControle == null){
            System.out.println("Lista de Pontos de Controle nula, não existem pontos de controle.");
        } else {
            for(int i = 0; i < listaPontosDeControle.size(); i++){
                
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                Date data = listaPontosDeControle.get(i).getDataPontoControle();
               
                Object[] linha = new Object[]{listaPontosDeControle.get(i).getNomePontoDeControle() , df.format(data) };
                modeloTabelaPontoDeControle.addRow(linha);
            }
        }
        
        
        // Popular tabela Marcos do Projeto
        if (listaMarcosDoProjeto == null){
            System.out.println("Lista de Marcos do Projeto nula, não existem marcos no projeto.");
        } else {
           for(int i = 0; i < listaMarcosDoProjeto.size(); i++){
               
               DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               Date data = (Date) listaMarcosDoProjeto.get(i).getDataMarcoProjeto();
               
                            
               
               Object[] linha = new Object[]{listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto() , df.format(data)};
               modeloTabelaMarcoDoProjeto.addRow(linha);
               
            } 
        }
        
        definirEventosTabelaMarcosDoProjeto();
        definirEventosTabelaPontosDeControle();
        
    }
    
    public void definirEventosTabelaMarcosDoProjeto(){
        tabelaMarcoDoProjeto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    
                    
                    int selected = tabelaMarcoDoProjeto.getSelectedRow();

                    tabelaPontoDeControle.clearSelection();
                    pontoDeControleSelecionado = null;

                    for (int i = 0; i < listaMarcosDoProjeto.size(); i++) {
                        
                        if (tabelaMarcoDoProjeto.getValueAt(tabelaMarcoDoProjeto.getSelectedRow(), 0).equals(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto())){
                                marcoSelecionado = listaMarcosDoProjeto.get(i);
                                pontoDeControleSelecionado = null;
                                marcoPontoDeControleSelecionadoJLabel2.setText(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto());
                                nomeMarcosPontosDeControleJTextField.setText(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto());
                                dataMarcoPontoDeControleJDateChooser.setValue(listaMarcosDoProjeto.get(i).getDataMarcoProjeto());
                                descricaoMarcoPontoDeControleJTextArea.setText(listaMarcosDoProjeto.get(i).getDescricaoMarcoProjeto());
                                MarcoPontoDeControleJComboBox.setSelectedItem("Marco do Projeto");
                        }

                    }

                }
            }
        });
    }
    
    public void definirEventosTabelaPontosDeControle(){
        tabelaPontoDeControle.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    
                    
                    int selected = tabelaPontoDeControle.getSelectedRow();

                    tabelaMarcoDoProjeto.clearSelection();
                    marcoSelecionado = null;

                    for (int i = 0; i < listaPontosDeControle.size(); i++) {
                        
                        if (tabelaPontoDeControle.getValueAt(tabelaPontoDeControle.getSelectedRow(), 0).equals(listaPontosDeControle.get(i).getNomePontoDeControle())){
                                pontoDeControleSelecionado = listaPontosDeControle.get(i);
                                marcoSelecionado = null;
                                marcoPontoDeControleSelecionadoJLabel2.setText(listaPontosDeControle.get(i).getNomePontoDeControle());
                                nomeMarcosPontosDeControleJTextField.setText(listaPontosDeControle.get(i).getNomePontoDeControle());
                                dataMarcoPontoDeControleJDateChooser.setValue(listaPontosDeControle.get(i).getDataPontoControle());
                                descricaoMarcoPontoDeControleJTextArea.setText(listaPontosDeControle.get(i).getDescricaoPontoControle());
                                MarcoPontoDeControleJComboBox.setSelectedItem("Ponto de Controle");
                        }

                    }

                }
            }
        });
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskDataModel1 = new net.sf.nachocalendar.tasks.TaskDataModel();
        projetoCalendarioGeralJPanel = new javax.swing.JPanel();
        ProjetoCalendarioJTabbedPane = new javax.swing.JTabbedPane();
        calendarioJScrollPane = new javax.swing.JScrollPane();
        CalendarioGeralJPanel = new javax.swing.JPanel();
        legendaJLabel = new javax.swing.JLabel();
        datePanel1 = new net.sf.nachocalendar.components.DatePanel();
        taskRenderer1 = new net.sf.nachocalendar.tasks.TaskRenderer();
        jPanel1 = new javax.swing.JPanel();
        marcoPControleJLabel = new javax.swing.JLabel();
        marcoPControleJTextField = new javax.swing.JTextField();
        dateField1 = new net.sf.nachocalendar.components.DateField();
        jButton3 = new javax.swing.JButton();
        marcosPontosDeControleJScrollPane2 = new javax.swing.JScrollPane();
        marcosPontosDeProjetoGeralJPanel = new javax.swing.JPanel();
        pontosDeControleJLabel = new javax.swing.JLabel();
        marcosDoProjetoJLabel = new javax.swing.JLabel();
        nomeMarcosPontosDeControleJLabel = new javax.swing.JLabel();
        nomeMarcosPontosDeControleJTextField = new javax.swing.JTextField();
        dataMarcosPontosDeControleJLabel = new javax.swing.JLabel();
        MarcoPontoDeControleJComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoMarcoPontoDeControleJTextArea = new javax.swing.JTextArea();
        excluirMarcoPontoDeControleJButton = new javax.swing.JButton();
        criarAlterarMarcoOuPontoDeControleJButton = new javax.swing.JButton();
        limparCamposJButton = new javax.swing.JButton();
        tabelaPontoDeControleJScrollPane = new javax.swing.JScrollPane();
        tabelaMarcosDoProjetoJScrollPane = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        marcoPontoDeControleSelecionadoJLabel2 = new javax.swing.JLabel();
        dataMarcoPontoDeControleJDateChooser = new net.sf.nachocalendar.components.DateField();

        calendarioJScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        calendarioJScrollPane.setHorizontalScrollBar(null);

        legendaJLabel.setText("Legenda:");

        datePanel1.setModel(taskDataModel1);
        datePanel1.setRenderer(taskRenderer1);
        datePanel1.setShowToday(true);
        datePanel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePanel1ActionPerformed(evt);
            }
        });

        taskRenderer1.setText("taskRenderer1");

        marcoPControleJLabel.setText("Marco/Ponto de Controle");

        marcoPControleJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcoPControleJTextFieldActionPerformed(evt);
            }
        });

        jButton3.setText("Salvar Marco/Ponto de Controle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marcoPControleJLabel)
                    .addComponent(marcoPControleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(marcoPControleJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(marcoPControleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CalendarioGeralJPanelLayout = new javax.swing.GroupLayout(CalendarioGeralJPanel);
        CalendarioGeralJPanel.setLayout(CalendarioGeralJPanelLayout);
        CalendarioGeralJPanelLayout.setHorizontalGroup(
            CalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalendarioGeralJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(legendaJLabel)
                    .addGroup(CalendarioGeralJPanelLayout.createSequentialGroup()
                        .addComponent(datePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(CalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taskRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(856, Short.MAX_VALUE))
        );
        CalendarioGeralJPanelLayout.setVerticalGroup(
            CalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalendarioGeralJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taskRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(legendaJLabel)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        calendarioJScrollPane.setViewportView(CalendarioGeralJPanel);

        ProjetoCalendarioJTabbedPane.addTab("Calendário", calendarioJScrollPane);

        marcosPontosDeControleJScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pontosDeControleJLabel.setText("Pontos de Controle");

        marcosDoProjetoJLabel.setText("Marcos do Projeto");

        nomeMarcosPontosDeControleJLabel.setText("Nome:");

        nomeMarcosPontosDeControleJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeMarcosPontosDeControleJTextFieldActionPerformed(evt);
            }
        });

        dataMarcosPontosDeControleJLabel.setText("Data:");

        MarcoPontoDeControleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Marco do Projeto", "Ponto de Controle" }));

        jLabel1.setText("Descrição do Marco/Ponto do Controle ");

        descricaoMarcoPontoDeControleJTextArea.setColumns(20);
        descricaoMarcoPontoDeControleJTextArea.setLineWrap(true);
        descricaoMarcoPontoDeControleJTextArea.setRows(5);
        descricaoMarcoPontoDeControleJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descricaoMarcoPontoDeControleJTextArea);

        excluirMarcoPontoDeControleJButton.setText("Excluir Marco ou Ponto de Controle");
        excluirMarcoPontoDeControleJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirMarcoPontoDeControleJButtonActionPerformed(evt);
            }
        });

        criarAlterarMarcoOuPontoDeControleJButton.setText("Criar/Alterar Marco ou Ponto de Controle");
        criarAlterarMarcoOuPontoDeControleJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarAlterarMarcoOuPontoDeControleJButtonActionPerformed(evt);
            }
        });

        limparCamposJButton.setText("Limpar Campos");
        limparCamposJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCamposJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(limparCamposJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirMarcoPontoDeControleJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(criarAlterarMarcoOuPontoDeControleJButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excluirMarcoPontoDeControleJButton)
                    .addComponent(criarAlterarMarcoOuPontoDeControleJButton)
                    .addComponent(limparCamposJButton)))
        );

        jLabel2.setText("Marco/Ponto de Controle selecionado:");

        marcoPontoDeControleSelecionadoJLabel2.setText("Nenhum");

        javax.swing.GroupLayout marcosPontosDeProjetoGeralJPanelLayout = new javax.swing.GroupLayout(marcosPontosDeProjetoGeralJPanel);
        marcosPontosDeProjetoGeralJPanel.setLayout(marcosPontosDeProjetoGeralJPanelLayout);
        marcosPontosDeProjetoGeralJPanelLayout.setHorizontalGroup(
            marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createSequentialGroup()
                        .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeMarcosPontosDeControleJLabel)
                            .addComponent(dataMarcosPontosDeControleJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createSequentialGroup()
                                .addComponent(dataMarcoPontoDeControleJDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MarcoPontoDeControleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nomeMarcosPontosDeControleJTextField)))
                    .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createSequentialGroup()
                        .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pontosDeControleJLabel)
                            .addComponent(tabelaPontoDeControleJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(marcosDoProjetoJLabel)
                            .addComponent(tabelaMarcosDoProjetoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marcoPontoDeControleSelecionadoJLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        marcosPontosDeProjetoGeralJPanelLayout.setVerticalGroup(
            marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pontosDeControleJLabel)
                    .addComponent(marcosDoProjetoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabelaMarcosDoProjetoJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(tabelaPontoDeControleJScrollPane))
                .addGap(25, 25, 25)
                .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(marcoPontoDeControleSelecionadoJLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeMarcosPontosDeControleJLabel)
                    .addComponent(nomeMarcosPontosDeControleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(marcosPontosDeProjetoGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dataMarcosPontosDeControleJLabel)
                        .addComponent(MarcoPontoDeControleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataMarcoPontoDeControleJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        marcosPontosDeControleJScrollPane2.setViewportView(marcosPontosDeProjetoGeralJPanel);

        ProjetoCalendarioJTabbedPane.addTab("Marcos e Pontos de Controle", marcosPontosDeControleJScrollPane2);

        javax.swing.GroupLayout projetoCalendarioGeralJPanelLayout = new javax.swing.GroupLayout(projetoCalendarioGeralJPanel);
        projetoCalendarioGeralJPanel.setLayout(projetoCalendarioGeralJPanelLayout);
        projetoCalendarioGeralJPanelLayout.setHorizontalGroup(
            projetoCalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjetoCalendarioJTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
        );
        projetoCalendarioGeralJPanelLayout.setVerticalGroup(
            projetoCalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ProjetoCalendarioJTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projetoCalendarioGeralJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projetoCalendarioGeralJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void marcoPControleJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcoPControleJTextFieldActionPerformed
      
    }//GEN-LAST:event_marcoPControleJTextFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTask tarefa = new DefaultTask();
        tarefa.setDate((Date) dateField1.getValue());
        tarefa.setName(marcoPControleJTextField.getText());
        
        taskDataModel1.addTask(tarefa);
        
        datePanel1.refresh();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void excluirMarcoPontoDeControleJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirMarcoPontoDeControleJButtonActionPerformed

        if (marcoSelecionado != null){
            
            int n = JOptionPane.showConfirmDialog(this, "Deseja excluir o marco do projeto ( " + marcoSelecionado.getNomeMarcoDoProjeto() + " )?","Excluir marco do projeto", JOptionPane.YES_NO_OPTION);

            if(n == JOptionPane.YES_OPTION){
                
                projetoCalendarioFacade.excluirMarcoDoProjeto(marcoSelecionado.getIdMarcoDoProjeto());
                
                JOptionPane.showMessageDialog(this, "Marco excluido com sucesso.");
                
                criarTabelasMarcoPontoDeControle();
                popularTabelaMarcoPontoDeControle();
                definirEventosTabelaMarcosDoProjeto();
                definirEventosTabelaPontosDeControle();
                
            }
            
            
        } else if(pontoDeControleSelecionado != null){
            
             int n = JOptionPane.showConfirmDialog(this, "Deseja excluir o ponto de controle ( " + pontoDeControleSelecionado.getNomePontoDeControle() + " )?","Excluir ponto de controle", JOptionPane.YES_NO_OPTION);

            if(n == JOptionPane.YES_OPTION){
                
                projetoCalendarioFacade.excluirPontoDeControle(pontoDeControleSelecionado.getIdPontoDeControle());
                                
                JOptionPane.showMessageDialog(this, "Ponto de Controle excluido com sucesso.");
            
                criarTabelasMarcoPontoDeControle();
                popularTabelaMarcoPontoDeControle();
                definirEventosTabelaMarcosDoProjeto();
                definirEventosTabelaPontosDeControle();
                
            }
            
            
        } else {
            
            JOptionPane.showMessageDialog(this, "Por favor selecione um marco ou ponto de controle para excluir.");
                            
        }

    }//GEN-LAST:event_excluirMarcoPontoDeControleJButtonActionPerformed

    private void nomeMarcosPontosDeControleJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeMarcosPontosDeControleJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeMarcosPontosDeControleJTextFieldActionPerformed

    private void criarAlterarMarcoOuPontoDeControleJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarAlterarMarcoOuPontoDeControleJButtonActionPerformed

        
        // CRIAR projeto se nenhum marco ou ponto de controle esttiver selecionado
        if ((marcoSelecionado == null) && (pontoDeControleSelecionado == null)){
        
        if (MarcoPontoDeControleJComboBox.getSelectedItem().equals("Marco do Projeto")){
            
            //boolean existeMarcoDeMesmoNome = false;
            
            // checa se existe marco com este nome no projeto
            //for(int i = 0; i < listaMarcosDoProjeto.size(); i++){           
            //    if (listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto().equals(nomeMarcosPontosDeControleJTextField.getText())){
            //        existeMarcoDeMesmoNome = true;
           //     }   
           // }
            
            //if (existeMarcoDeMesmoNome = true){
                
            //    JOptionPane.showMessageDialog(this, "Já existe um marco com este nome no projeto.");
                        
           // } else {
            
            Marcodoprojeto marcoDoProjeto = new Marcodoprojeto();
            
            marcoDoProjeto.setNomeMarcoDoProjeto(nomeMarcosPontosDeControleJTextField.getText());
            marcoDoProjeto.setIdProjeto(projetoSelecionado);
            marcoDoProjeto.setDescricaoMarcoProjeto(descricaoMarcoPontoDeControleJTextArea.getText());
            marcoDoProjeto.setDataMarcoProjeto((Date)dataMarcoPontoDeControleJDateChooser.getValue());  
            
            projetoCalendarioFacade.criarMarcoDoProjeto(marcoDoProjeto);
            
            JOptionPane.showMessageDialog(this, "Marco do Projeto criado com sucesso.");
            
          //  }
            
        } else {
            
            
            
            Pontodecontrole pontoDeControle = new Pontodecontrole();
            
            pontoDeControle.setNomePontoDeControle(nomeMarcosPontosDeControleJTextField.getText());
            pontoDeControle.setIdProjeto(projetoSelecionado);
            pontoDeControle.setDescricaoPontoControle(descricaoMarcoPontoDeControleJTextArea.getText());
            pontoDeControle.setDataPontoControle((Date)dataMarcoPontoDeControleJDateChooser.getValue()); 
            
            projetoCalendarioFacade.criarPontoDeControle(pontoDeControle);
            
            JOptionPane.showMessageDialog(this, "Ponto de Controle criado com sucesso.");
            
            
        }
        
        criarTabelasMarcoPontoDeControle();
        popularTabelaMarcoPontoDeControle();
        definirEventosTabelaMarcosDoProjeto();
        definirEventosTabelaPontosDeControle();
        }
        
        //ALTERAR - Se nao tem marco selecionado entao tem ponto de controle selecionado
        else if((marcoSelecionado == null) && (pontoDeControleSelecionado != null)) {
            
            pontoDeControleSelecionado.setNomePontoDeControle(nomeMarcosPontosDeControleJTextField.getText());
            pontoDeControleSelecionado.setDescricaoPontoControle(descricaoMarcoPontoDeControleJTextArea.getText());
            pontoDeControleSelecionado.setDataPontoControle((Date)dataMarcoPontoDeControleJDateChooser.getValue());
            
            projetoCalendarioFacade.alterarPontoDeControle(pontoDeControleSelecionado);
            
            JOptionPane.showMessageDialog(this, "Ponto de controle alterado com sucesso.");
            
        //ALTERAR - Se nao tem ponto de controle selecionado entao tem marco selecionado    
        } else if((pontoDeControleSelecionado == null) && (marcoSelecionado != null)){
            
            marcoSelecionado.setNomeMarcoDoProjeto(nomeMarcosPontosDeControleJTextField.getText());
            marcoSelecionado.setDescricaoMarcoProjeto(descricaoMarcoPontoDeControleJTextArea.getText());
            marcoSelecionado.setDataMarcoProjeto((Date)dataMarcoPontoDeControleJDateChooser.getValue());
            
            projetoCalendarioFacade.alterarMarcoDoProjeto(marcoSelecionado);
            
            JOptionPane.showMessageDialog(this, "Marco do projeto alterado com sucesso.");            
        }
        

    }//GEN-LAST:event_criarAlterarMarcoOuPontoDeControleJButtonActionPerformed

    private void limparCamposJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCamposJButtonActionPerformed

        
        marcoSelecionado = null;
        pontoDeControleSelecionado = null;
               
        tabelaMarcoDoProjeto.clearSelection();
        tabelaPontoDeControle.clearSelection();
        
        marcoPontoDeControleSelecionadoJLabel2.setText("Nenhum");
        
        nomeMarcosPontosDeControleJTextField.setText("");
        descricaoMarcoPontoDeControleJTextArea.setText("");
        dataMarcoPontoDeControleJDateChooser.setValue(null);
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_limparCamposJButtonActionPerformed

    private void datePanel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePanel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datePanel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CalendarioGeralJPanel;
    private javax.swing.JComboBox MarcoPontoDeControleJComboBox;
    private javax.swing.JTabbedPane ProjetoCalendarioJTabbedPane;
    private javax.swing.JScrollPane calendarioJScrollPane;
    private javax.swing.JButton criarAlterarMarcoOuPontoDeControleJButton;
    private net.sf.nachocalendar.components.DateField dataMarcoPontoDeControleJDateChooser;
    private javax.swing.JLabel dataMarcosPontosDeControleJLabel;
    private net.sf.nachocalendar.components.DateField dateField1;
    private net.sf.nachocalendar.components.DatePanel datePanel1;
    private javax.swing.JTextArea descricaoMarcoPontoDeControleJTextArea;
    private javax.swing.JButton excluirMarcoPontoDeControleJButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel legendaJLabel;
    private javax.swing.JButton limparCamposJButton;
    private javax.swing.JLabel marcoPControleJLabel;
    private javax.swing.JTextField marcoPControleJTextField;
    private javax.swing.JLabel marcoPontoDeControleSelecionadoJLabel2;
    private javax.swing.JLabel marcosDoProjetoJLabel;
    private javax.swing.JScrollPane marcosPontosDeControleJScrollPane2;
    private javax.swing.JPanel marcosPontosDeProjetoGeralJPanel;
    private javax.swing.JLabel nomeMarcosPontosDeControleJLabel;
    private javax.swing.JTextField nomeMarcosPontosDeControleJTextField;
    private javax.swing.JLabel pontosDeControleJLabel;
    private javax.swing.JPanel projetoCalendarioGeralJPanel;
    private javax.swing.JScrollPane tabelaMarcosDoProjetoJScrollPane;
    private javax.swing.JScrollPane tabelaPontoDeControleJScrollPane;
    private net.sf.nachocalendar.tasks.TaskDataModel taskDataModel1;
    private net.sf.nachocalendar.tasks.TaskRenderer taskRenderer1;
    // End of variables declaration//GEN-END:variables
}
