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
import java.util.Collection;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marcodoprojeto;
import model.Pontodecontrole;
import model.Projeto;
import net.sf.nachocalendar.tasks.DefaultTask;
import net.sf.nachocalendar.tasks.TaskDataModel;

/**
 *
 * @author Diogo
 */
public class ProjetoCalendarioJPanel extends javax.swing.JPanel {

    
    final CalendarioDetalhesDoDiaJFrame calendarioDetalhesDoDiaJFrame = new CalendarioDetalhesDoDiaJFrame();
    
    ProjetoCalendarioFacade projetoCalendarioFacade = new ProjetoCalendarioFacade();
    
    
    
    JTable tabelaMarcosEPontosDeControle;
    DefaultTableModel modeloTabelaMarcosEPontosDeControle;
    
    
    List<Marcodoprojeto> listaMarcosDoProjeto;
    List<Pontodecontrole> listaPontosDeControle;
    
    Projeto projetoSelecionado;
    Marcodoprojeto marcoSelecionado = null;
    Pontodecontrole pontoDeControleSelecionado = null;
    
    public ProjetoCalendarioJPanel() {
        initComponents();
        
        calendarioDetalhesDoDiaJFrame.setVisible(false);

        
        criarTarefasCalendario();
        
        criarTabelasMarcoPontoDeControle();
        popularTabelaMarcoPontoDeControle();
        
        
        definirEventosTabelaMarcoEPontosDeControle();
        
        popularCalendario();
        
    }
    
    public void criarTarefasCalendario(){
        
        
        
        datePanel1.setRenderer(taskRenderer1);
        taskRenderer1.setVisible(false);
        
       
    }
    
    
    
    public void definirEventosCalendario(){
        
        datePanel1.addMouseListener(new MouseAdapter() {
           
            @Override
            public void mousePressed(MouseEvent j) {
                if(j.getClickCount() == 1){
                    
                    Date dataSelecionada = datePanel1.getDate();
                    
                    Collection tarefas = taskDataModel1.getTasks(dataSelecionada);
                    
                    System.out.println("Tarefas: " + tarefas);
                    
                }
            } 
        });
        
       
        
       
        
        
    }
    
    public void popularCalendario(){
        
        //taskDataModel1.clear();
        
        taskDataModel1 = new net.sf.nachocalendar.tasks.TaskDataModel();
        
        datePanel1.setModel(taskDataModel1);
        
        datePanel1.refresh();
        
        if(listaMarcosDoProjeto != null){
        for(int i=0; i < listaMarcosDoProjeto.size(); i++){
            
            DefaultTask tarefa = new DefaultTask();
            tarefa.setDate(listaMarcosDoProjeto.get(i).getDataMarcoProjeto());
            tarefa.setName(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto());
            taskDataModel1.addTask(tarefa);
            datePanel1.refresh();
            
            }
        }
        
        if(listaPontosDeControle != null){
        for(int i=0; i < listaPontosDeControle.size(); i++){
            
            DefaultTask tarefa = new DefaultTask();
            tarefa.setDate(listaPontosDeControle.get(i).getDataPontoControle());
            tarefa.setName(listaPontosDeControle.get(i).getNomePontoDeControle());
            taskDataModel1.addTask(tarefa);
            datePanel1.refresh();
            
            }
        }
        
        definirEventosCalendario();
        
    }
    
    public void getProjetoSelecionado(Projeto projetoSelecionado){
        this.projetoSelecionado = projetoSelecionado;
    }
    
    public void criarTabelasMarcoPontoDeControle(){
        
        
        tabelaMarcosEPontosDeControle = new JTable();
        modeloTabelaMarcosEPontosDeControle = new DefaultTableModel();
        
        modeloTabelaMarcosEPontosDeControle.setColumnIdentifiers(new Object[]{"Tipo" , "Nome" , "Data"});
        
        tabelaMarcosEPontosDeControle.setModel(modeloTabelaMarcosEPontosDeControle);
        
        tabelaMarcoEPontosDeControleJScrollPane.setViewportView(tabelaMarcosEPontosDeControle);
        
        
    }
    
    private void getListaMarcosPontosDeControle(){
        
        listaMarcosDoProjeto = projetoCalendarioFacade.getListaMarcosDoProjetoSelecionado(projetoSelecionado);
        listaPontosDeControle = projetoCalendarioFacade.getListaPontosDeControleDoProjetoSelecionado(projetoSelecionado);
        
        
    }
    
    public void popularTabelaMarcoPontoDeControle(){
        
        getListaMarcosPontosDeControle();
        
        
        // populando os pontos de controle
        if (listaPontosDeControle == null){
            System.out.println("Lista de Pontos de Controle nula, não existem pontos de controle.");
        } else {
            for(int i = 0; i < listaPontosDeControle.size(); i++){
                
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                Date data = listaPontosDeControle.get(i).getDataPontoControle();
               
                Object[] linha = new Object[]{ "Ponto de Controle", listaPontosDeControle.get(i).getNomePontoDeControle() , df.format(data) };
                modeloTabelaMarcosEPontosDeControle.addRow(linha);
            }
        }
        
        // populando os marcos do projeto
        if (listaMarcosDoProjeto == null){
            System.out.println("Lista de Marcos do Projeto nula, não existem marcos no projeto.");
        } else {
           for(int i = 0; i < listaMarcosDoProjeto.size(); i++){
               
               DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               Date data = (Date) listaMarcosDoProjeto.get(i).getDataMarcoProjeto();
               
                            
               
               Object[] linha = new Object[]{"Marco" , listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto() , df.format(data)};
               modeloTabelaMarcosEPontosDeControle.addRow(linha);
               
            } 
        }
        
        
        
        definirEventosTabelaMarcoEPontosDeControle();
        
        popularCalendario();
        
    }
    
    public void definirEventosTabelaMarcoEPontosDeControle(){
        tabelaMarcosEPontosDeControle.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    
                    
                    int selected = tabelaMarcosEPontosDeControle.getSelectedRow();

                    //tabelaPontoDeControle.clearSelection();
                    //pontoDeControleSelecionado = null;

                    if(modeloTabelaMarcosEPontosDeControle.getValueAt(selected, 0).equals("Marco")){
                    for (int i = 0; i < listaMarcosDoProjeto.size(); i++) {
                        
                        if (modeloTabelaMarcosEPontosDeControle.getValueAt(selected, 1).equals(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto())){
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
                    
                    if(modeloTabelaMarcosEPontosDeControle.getValueAt(selected, 0).equals("Ponto de Controle")){
                    for (int i = 0; i < listaPontosDeControle.size(); i++) {
                        
                        if (tabelaMarcosEPontosDeControle.getValueAt(selected, 1).equals(listaPontosDeControle.get(i).getNomePontoDeControle())){
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        datePanel1 = new net.sf.nachocalendar.components.DatePanel();
        novoMarcoPontoJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoMarcoPontoDeControleJTextArea = new javax.swing.JTextArea();
        criarAlterarMarcoOuPontoDeControleJButton = new javax.swing.JButton();
        excluirMarcoPontoDeControleJButton = new javax.swing.JButton();
        limparCamposJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        marcoPontoDeControleSelecionadoJLabel2 = new javax.swing.JLabel();
        nomeMarcosPontosDeControleJLabel = new javax.swing.JLabel();
        nomeMarcosPontosDeControleJTextField = new javax.swing.JTextField();
        dataMarcosPontosDeControleJLabel = new javax.swing.JLabel();
        dataMarcoPontoDeControleJDateChooser = new net.sf.nachocalendar.components.DateField();
        MarcoPontoDeControleJComboBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        tabelaMarcoEPontosDeControleJScrollPane = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        taskRenderer1 = new net.sf.nachocalendar.tasks.TaskRenderer();
        diaDetalhesJButton = new javax.swing.JButton();

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        datePanel1.setAntiAliased(true);
        datePanel1.setModel(taskDataModel1);
        datePanel1.setShowToday(true);
        datePanel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePanel1ActionPerformed(evt);
            }
        });

        descricaoMarcoPontoDeControleJTextArea.setColumns(20);
        descricaoMarcoPontoDeControleJTextArea.setLineWrap(true);
        descricaoMarcoPontoDeControleJTextArea.setRows(5);
        descricaoMarcoPontoDeControleJTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descricaoMarcoPontoDeControleJTextArea);

        criarAlterarMarcoOuPontoDeControleJButton.setText("Criar/Alterar Marco ou Ponto de Controle");
        criarAlterarMarcoOuPontoDeControleJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarAlterarMarcoOuPontoDeControleJButtonActionPerformed(evt);
            }
        });

        excluirMarcoPontoDeControleJButton.setText("Excluir Marco ou Ponto de Controle");
        excluirMarcoPontoDeControleJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirMarcoPontoDeControleJButtonActionPerformed(evt);
            }
        });

        limparCamposJButton.setText("Limpar Campos");
        limparCamposJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCamposJButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Descrição do Marco/Ponto do Controle ");

        jLabel2.setText("Marco/Ponto de Controle selecionado:");

        marcoPontoDeControleSelecionadoJLabel2.setText("Nenhum");

        nomeMarcosPontosDeControleJLabel.setText("Nome:");

        nomeMarcosPontosDeControleJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeMarcosPontosDeControleJTextFieldActionPerformed(evt);
            }
        });

        dataMarcosPontosDeControleJLabel.setText("Data:");

        MarcoPontoDeControleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Marco do Projeto", "Ponto de Controle" }));

        javax.swing.GroupLayout novoMarcoPontoJPanelLayout = new javax.swing.GroupLayout(novoMarcoPontoJPanel);
        novoMarcoPontoJPanel.setLayout(novoMarcoPontoJPanelLayout);
        novoMarcoPontoJPanelLayout.setHorizontalGroup(
            novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoMarcoPontoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(novoMarcoPontoJPanelLayout.createSequentialGroup()
                        .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeMarcosPontosDeControleJLabel)
                            .addComponent(dataMarcosPontosDeControleJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(novoMarcoPontoJPanelLayout.createSequentialGroup()
                                .addComponent(dataMarcoPontoDeControleJDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MarcoPontoDeControleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nomeMarcosPontosDeControleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(novoMarcoPontoJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marcoPontoDeControleSelecionadoJLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(novoMarcoPontoJPanelLayout.createSequentialGroup()
                            .addComponent(limparCamposJButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(excluirMarcoPontoDeControleJButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(criarAlterarMarcoOuPontoDeControleJButton))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        novoMarcoPontoJPanelLayout.setVerticalGroup(
            novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, novoMarcoPontoJPanelLayout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(marcoPontoDeControleSelecionadoJLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeMarcosPontosDeControleJLabel)
                    .addComponent(nomeMarcosPontosDeControleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dataMarcosPontosDeControleJLabel)
                        .addComponent(MarcoPontoDeControleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataMarcoPontoDeControleJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(novoMarcoPontoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excluirMarcoPontoDeControleJButton)
                    .addComponent(criarAlterarMarcoOuPontoDeControleJButton)
                    .addComponent(limparCamposJButton)))
        );

        jLabel3.setText("Marcos e Pontos de Controle do projeto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaMarcoEPontosDeControleJScrollPane)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaMarcoEPontosDeControleJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );

        taskRenderer1.setText("taskRenderer1");

        diaDetalhesJButton.setText("Detalhes do Dia");
        diaDetalhesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaDetalhesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(diaDetalhesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(datePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 50, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(novoMarcoPontoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(430, 430, 430)
                    .addComponent(taskRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(359, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diaDetalhesJButton)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(novoMarcoPontoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(473, 473, 473)
                    .addComponent(taskRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(431, Short.MAX_VALUE)))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout projetoCalendarioGeralJPanelLayout = new javax.swing.GroupLayout(projetoCalendarioGeralJPanel);
        projetoCalendarioGeralJPanel.setLayout(projetoCalendarioGeralJPanelLayout);
        projetoCalendarioGeralJPanelLayout.setHorizontalGroup(
            projetoCalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projetoCalendarioGeralJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        projetoCalendarioGeralJPanelLayout.setVerticalGroup(
            projetoCalendarioGeralJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(projetoCalendarioGeralJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(projetoCalendarioGeralJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void excluirMarcoPontoDeControleJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirMarcoPontoDeControleJButtonActionPerformed

        if (marcoSelecionado != null){
            
            int n = JOptionPane.showConfirmDialog(this, "Deseja excluir o marco do projeto ( " + marcoSelecionado.getNomeMarcoDoProjeto() + " )?","Excluir marco do projeto", JOptionPane.YES_NO_OPTION);

            if(n == JOptionPane.YES_OPTION){
                
                projetoCalendarioFacade.excluirMarcoDoProjeto(marcoSelecionado.getIdMarcoDoProjeto());
                
                JOptionPane.showMessageDialog(this, "Marco excluido com sucesso.");
                
                criarTabelasMarcoPontoDeControle();
                popularTabelaMarcoPontoDeControle();
                
                definirEventosTabelaMarcoEPontosDeControle();
                
            }
            
            
        } else if(pontoDeControleSelecionado != null){
            
             int n = JOptionPane.showConfirmDialog(this, "Deseja excluir o ponto de controle ( " + pontoDeControleSelecionado.getNomePontoDeControle() + " )?","Excluir ponto de controle", JOptionPane.YES_NO_OPTION);

            if(n == JOptionPane.YES_OPTION){
                
                projetoCalendarioFacade.excluirPontoDeControle(pontoDeControleSelecionado.getIdPontoDeControle());
                                
                JOptionPane.showMessageDialog(this, "Ponto de Controle excluido com sucesso.");
            
                criarTabelasMarcoPontoDeControle();
                popularTabelaMarcoPontoDeControle();
                
                
                definirEventosTabelaMarcoEPontosDeControle();               
                
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
            
            DefaultTask tarefa = new DefaultTask();
            tarefa.setDate(marcoDoProjeto.getDataMarcoProjeto());
            tarefa.setName(marcoDoProjeto.getNomeMarcoDoProjeto());
            taskDataModel1.addTask(tarefa);
            datePanel1.refresh();
            
            
            JOptionPane.showMessageDialog(this, "Marco do Projeto criado com sucesso.");
            
          //  }
            
        } else {
            
            
            
            Pontodecontrole pontoDeControle = new Pontodecontrole();
            
            pontoDeControle.setNomePontoDeControle(nomeMarcosPontosDeControleJTextField.getText());
            pontoDeControle.setIdProjeto(projetoSelecionado);
            pontoDeControle.setDescricaoPontoControle(descricaoMarcoPontoDeControleJTextArea.getText());
            pontoDeControle.setDataPontoControle((Date)dataMarcoPontoDeControleJDateChooser.getValue()); 
            
            projetoCalendarioFacade.criarPontoDeControle(pontoDeControle);
            
            DefaultTask tarefa = new DefaultTask();
            tarefa.setDate(pontoDeControle.getDataPontoControle());
            tarefa.setName(pontoDeControle.getNomePontoDeControle());
            taskDataModel1.addTask(tarefa);
            datePanel1.refresh();
            
            JOptionPane.showMessageDialog(this, "Ponto de Controle criado com sucesso.");
            
            
        }
        
        criarTabelasMarcoPontoDeControle();
        popularTabelaMarcoPontoDeControle();
        //definirEventosTabelaMarcosDoProjeto();
        //definirEventosTabelaPontosDeControle();
        
        definirEventosTabelaMarcoEPontosDeControle();
        
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
               
        //tabelaMarcoDoProjeto.clearSelection();
        //tabelaPontoDeControle.clearSelection();
        
        marcoPontoDeControleSelecionadoJLabel2.setText("Nenhum");
        
        nomeMarcosPontosDeControleJTextField.setText("");
        descricaoMarcoPontoDeControleJTextArea.setText("");
        dataMarcoPontoDeControleJDateChooser.setValue(null);
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_limparCamposJButtonActionPerformed

    private void datePanel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePanel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datePanel1ActionPerformed

    private void diaDetalhesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaDetalhesJButtonActionPerformed
        Date dataLimite = null;
        Marcodoprojeto diaSelecionadoMarco = null;
        Pontodecontrole diaSelecionadoPonto = null;
        
        Collection listaTarefas = taskDataModel1.getTasks(datePanel1.getDate());

        
        if (listaTarefas != null ){
 
            for(Iterator it = listaTarefas.iterator(); it.hasNext(); )  {       
                DefaultTask tarefa = (DefaultTask) it.next();
                
                String nomeTarefa = tarefa.toString();          
                System.out.println(nomeTarefa); 
                
                if(diaSelecionadoPonto == null){
                    for(int i = 0; i < listaPontosDeControle.size(); i++){
                    if(nomeTarefa.equals(listaPontosDeControle.get(i).getNomePontoDeControle())){
                        diaSelecionadoPonto = listaPontosDeControle.get(i);
                        dataLimite = listaPontosDeControle.get(i).getDataPontoControle();
                    }
                }
                }
                
                if(diaSelecionadoMarco == null){
                   for(int i = 0; i < listaMarcosDoProjeto.size(); i++){
                   if(nomeTarefa.equals(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto())){
                        diaSelecionadoMarco = listaMarcosDoProjeto.get(i);
                        dataLimite = listaMarcosDoProjeto.get(i).getDataMarcoProjeto();
                    }
                } 
                }
                
                
            
        }
        
            
            
        calendarioDetalhesDoDiaJFrame.limarTela();
        calendarioDetalhesDoDiaJFrame.criarListaDeTarefas();
        calendarioDetalhesDoDiaJFrame.mostraInformacoesPontoDeControle(diaSelecionadoPonto);
        calendarioDetalhesDoDiaJFrame.mostraInformacoesMarcoDoProjeto(diaSelecionadoMarco);
        calendarioDetalhesDoDiaJFrame.popularListaDeTarefas(projetoSelecionado, dataLimite);
        
        calendarioDetalhesDoDiaJFrame.setVisible(true);
        calendarioDetalhesDoDiaJFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum ponto de controle ou marco do projeto para este dia.");
        }
        
        
    }//GEN-LAST:event_diaDetalhesJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox MarcoPontoDeControleJComboBox;
    private javax.swing.JButton criarAlterarMarcoOuPontoDeControleJButton;
    private net.sf.nachocalendar.components.DateField dataMarcoPontoDeControleJDateChooser;
    private javax.swing.JLabel dataMarcosPontosDeControleJLabel;
    private net.sf.nachocalendar.components.DatePanel datePanel1;
    private javax.swing.JTextArea descricaoMarcoPontoDeControleJTextArea;
    private javax.swing.JButton diaDetalhesJButton;
    private javax.swing.JButton excluirMarcoPontoDeControleJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton limparCamposJButton;
    private javax.swing.JLabel marcoPontoDeControleSelecionadoJLabel2;
    private javax.swing.JLabel nomeMarcosPontosDeControleJLabel;
    private javax.swing.JTextField nomeMarcosPontosDeControleJTextField;
    private javax.swing.JPanel novoMarcoPontoJPanel;
    private javax.swing.JPanel projetoCalendarioGeralJPanel;
    private javax.swing.JScrollPane tabelaMarcoEPontosDeControleJScrollPane;
    private net.sf.nachocalendar.tasks.TaskDataModel taskDataModel1;
    private net.sf.nachocalendar.tasks.TaskRenderer taskRenderer1;
    // End of variables declaration//GEN-END:variables
}
