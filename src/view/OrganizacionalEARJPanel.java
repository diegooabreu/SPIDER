/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.OrganizacionalEARFacade;
import facade.TabelaMonitorarRiscosTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import model.Categoriaderisco;
import model.Contem;

/**
 *
 * @author H
 */
public class OrganizacionalEARJPanel extends javax.swing.JPanel {

    // Variavel List para armazenamento das categorias
    private List<Categoriaderisco> listaCategoria;

    OrganizacionalEARFacade organizacionalEARFacade = new OrganizacionalEARFacade();

    public void getlistaCategoria() {
        listaCategoria = organizacionalEARFacade.listarCategorias();
    }

    // Variavel para armazenar o ID da categoria selecionada no momento
    private Categoriaderisco CatSelecionada;

    // Criando Arvore EAR // 
    private JTree arvoreEARJTree;
    private DefaultMutableTreeNode categorias;

    //**********************//
    // Criando Tabela Categorias //
    private JTable categoriasTabelaJTable = null;
    private DefaultTableModel modeloCategoriasTabelaJTable = null;
    

    //**************************//
    /**
     * Creates new form OrganizacionalEAR2JPanel
     */
    public OrganizacionalEARJPanel() {
        initComponents();
        getlistaCategoria();
        criarArvore();
        criarTabela();
        popularArvoreEtabela();
        definirEventosArvore();
        definirEventosTabela();
    }

    public void criarArvore() {
        categorias = new DefaultMutableTreeNode("Projeto");
        arvoreEARJTree = new JTree(categorias);
        arvoreEARJTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos Organizacional"));
        earJScrollPane.setViewportView(arvoreEARJTree);
        repaint();
    }

    public void criarTabela() {

        categoriasTabelaJTable = new JTable(){@Override
        public boolean isCellEditable(int rowIndex, int colIndex) { return false; }};  
        modeloCategoriasTabelaJTable = new DefaultTableModel();
        modeloCategoriasTabelaJTable.setColumnIdentifiers(new String[]{"Id", "Categoria", "Categoria Pai", "Projeto", "Status da Avaliação"});
        categoriasTabelaJTable.setModel(modeloCategoriasTabelaJTable);
        categoriasTabelaJScrollPane.setViewportView(categoriasTabelaJTable);
        categoriasTabelaJTable.getColumnModel().getColumn(0).setMaxWidth(0);
        categoriasTabelaJTable.getColumnModel().getColumn(0).setMinWidth(0);
        categoriasTabelaJTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        categoriasTabelaJTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    private void percorrerFilhos(DefaultMutableTreeNode Pai, DefaultMutableTreeNode filho, int j) {
        for (int i = 0; i < Pai.getChildCount(); i++) {

            DefaultMutableTreeNode FilhoDePai = (DefaultMutableTreeNode) Pai.getChildAt(i);

            if (listaCategoria.get(j).getFkidCategoriaDeRisco().getNomeCategoria().equals(FilhoDePai.toString())) {

                FilhoDePai.add(filho);

            }

            if (FilhoDePai.getChildCount() > 0) {
                percorrerFilhos(FilhoDePai, filho, j);
            }

        }

    }

    public void popularArvoreEtabela() {

        getlistaCategoria();

        for (int i = 0; i < listaCategoria.size(); i++) {

            if (listaCategoria.get(i).getEarOrganizacional() == true) { // Se EarOrganizacional == true , a categoria pertence à arvore

                if (listaCategoria.get(i).getFkidCategoriaDeRisco() == null) {
                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoria.get(i).getNomeCategoria());
                    categorias.add(filho);
                    arvoreEARJTree = new JTree(categorias);
                    earJScrollPane.setViewportView(arvoreEARJTree);
                    repaint();
                } else {

                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoria.get(i).getNomeCategoria());

                    for (int row = 0; row < categorias.getChildCount(); row++) {

                        DefaultMutableTreeNode Pai = (DefaultMutableTreeNode) categorias.getChildAt(row);

                        if (listaCategoria.get(i).getFkidCategoriaDeRisco().getNomeCategoria().equals(Pai.toString())) {
                            Pai.add(filho);

                        } else {

                        }

                        if (Pai.getChildCount() > 0) {
                            percorrerFilhos(Pai, filho, i);
                        }

                    }

                }
            } else {

                // ************************* Populando a tabelaCategoriasJTable **************************//
                String statusAvaliacao = "Não avaliado";
                String nomeprojeto = null;
                String nomeCategoriaPai = "Projeto";

                if (organizacionalEARFacade.getListaContemWhereIdCategoria(listaCategoria.get(i).getIdCategoriaDeRisco()).size() > 0) {
                    int p = 0;
                    nomeprojeto = organizacionalEARFacade.getListaContemWhereIdCategoria(listaCategoria.get(i).getIdCategoriaDeRisco()).get(p).getProjeto().getNomeProjeto();
                }

                if (organizacionalEARFacade.getListaAvaliacaoCategoriaWhereIdCategoria(listaCategoria.get(i)).size() > 0) {
                    statusAvaliacao = "Avaliado";
                }

                if (listaCategoria.get(i).getFkidCategoriaDeRisco() != null) {
                    nomeCategoriaPai = listaCategoria.get(i).getFkidCategoriaDeRisco().getNomeCategoria();
                }

                String[] linha = new String[]{listaCategoria.get(i).getIdCategoriaDeRisco().toString(),listaCategoria.get(i).getNomeCategoria(), nomeCategoriaPai, nomeprojeto, statusAvaliacao};
                modeloCategoriasTabelaJTable.addRow(linha);

                //*************************************************************************************//
            }

        }
        arvoreEARJTree = new JTree(categorias);
        arvoreEARJTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos Organizacional"));
        earJScrollPane.setViewportView(arvoreEARJTree);
        repaint();
    }

    public void definirEventosArvore() {
        arvoreEARJTree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    TreePath selPath = arvoreEARJTree.getPathForLocation(e.getX(),
                            e.getY());
                    if (selPath == null) {
                        return;
                    }

                    categoriasTabelaJTable.clearSelection();

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    Object userObj = node.getUserObject();
                    System.out.println("Usuario escolheu: " + userObj);

                    if (userObj == "Projeto") {
                        CatSelecionada = null;
                    } else {

                        for (int i = 0; i < listaCategoria.size(); i++) {
                            if ((userObj.equals(listaCategoria.get(i).getNomeCategoria())) && (listaCategoria.get(i).getEarOrganizacional() == true)) {
                                CatSelecionada = listaCategoria.get(i);
                                categoriaSelecionada2JLabel.setText(listaCategoria.get(i).getNomeCategoria());
                                descricaoJTextArea.setText(listaCategoria.get(i).getDescricaoCategoria());
                                possiveisOrigensJTextArea.setText(listaCategoria.get(i).getPossiveisOrigens());
                                criteriosDeterminacaoJTextArea.setText(listaCategoria.get(i).getCriteriosParaProbabilidade());
                                criteriosDeterminacaoImpactoJTextArea.setText(listaCategoria.get(i).getCriteriosParaImpacto());

                            }

                        }
                        
                    }
                }
            }

        });
    }

    public void definirEventosTabela() {
        categoriasTabelaJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    
                    
                    int selected = categoriasTabelaJTable.getSelectedRow();

                    arvoreEARJTree.clearSelection();

                    for (int i = 0; i < listaCategoria.size(); i++) {
                        
                        if (categoriasTabelaJTable.getValueAt(categoriasTabelaJTable.getSelectedRow(), 0).equals(listaCategoria.get(i).getIdCategoriaDeRisco().toString())){
                                CatSelecionada = listaCategoria.get(i);
                                categoriaSelecionada2JLabel.setText(listaCategoria.get(i).getNomeCategoria());
                                descricaoJTextArea.setText(listaCategoria.get(i).getDescricaoCategoria());
                                possiveisOrigensJTextArea.setText(listaCategoria.get(i).getPossiveisOrigens());
                                criteriosDeterminacaoJTextArea.setText(listaCategoria.get(i).getCriteriosParaProbabilidade());
                                criteriosDeterminacaoImpactoJTextArea.setText(listaCategoria.get(i).getCriteriosParaImpacto());    
                        }
                        /*
                        if ((categoriasTabelaJTable.getValueAt(categoriasTabelaJTable.getSelectedRow(), 0).equals(listaCategoria.get(i).getNomeCategoria()))) {
                            
                            if (listaCategoria.get(i).getFkidCategoriaDeRisco() == null) {
                                CatSelecionada = listaCategoria.get(i);
                                categoriaSelecionada2JLabel.setText(listaCategoria.get(i).getNomeCategoria());
                                descricaoJTextArea.setText(listaCategoria.get(i).getDescricaoCategoria());
                                possiveisOrigensJTextArea.setText(listaCategoria.get(i).getPossiveisOrigens());
                                criteriosDeterminacaoJTextArea.setText(listaCategoria.get(i).getCriteriosParaProbabilidade());
                                jTextArea2.setText(listaCategoria.get(i).getCriteriosParaImpacto());

                            } else if (categoriasTabelaJTable.getValueAt(categoriasTabelaJTable.getSelectedRow(), 1).equals(listaCategoria.get(i).getFkidCategoriaDeRisco().getNomeCategoria())) {
                                CatSelecionada = listaCategoria.get(i);
                                categoriaSelecionada2JLabel.setText(listaCategoria.get(i).getNomeCategoria());
                                descricaoJTextArea.setText(listaCategoria.get(i).getDescricaoCategoria());
                                possiveisOrigensJTextArea.setText(listaCategoria.get(i).getPossiveisOrigens());
                                criteriosDeterminacaoJTextArea.setText(listaCategoria.get(i).getCriteriosParaProbabilidade());
                                jTextArea2.setText(listaCategoria.get(i).getCriteriosParaImpacto());
                            } 

                        } */
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        earJScrollPane = new javax.swing.JScrollPane();
        categoriasTabelaJScrollPane = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        categoriaSelecionadaJLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        descricaoJTextArea = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        possiveisOrigensJTextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        criteriosDeterminacaoJTextArea = new javax.swing.JTextArea();
        categoriaSelecionada2JLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        criteriosDeterminacaoImpactoJTextArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        salvarAlteracoesJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        adicionarEstruturaAnaliticaJButton = new javax.swing.JButton();
        removerCategoriaDaEARJButton = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jButton1.setText("jButton1");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        categoriaSelecionadaJLabel.setText("Categoria Selecionada:");

        descricaoJTextArea.setColumns(20);
        descricaoJTextArea.setLineWrap(true);
        descricaoJTextArea.setRows(5);
        descricaoJTextArea.setWrapStyleWord(true);
        descricaoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jScrollPane5.setViewportView(descricaoJTextArea);

        possiveisOrigensJTextArea.setColumns(20);
        possiveisOrigensJTextArea.setLineWrap(true);
        possiveisOrigensJTextArea.setRows(5);
        possiveisOrigensJTextArea.setWrapStyleWord(true);
        possiveisOrigensJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Possíveis Origens"));
        jScrollPane6.setViewportView(possiveisOrigensJTextArea);

        criteriosDeterminacaoJTextArea.setColumns(20);
        criteriosDeterminacaoJTextArea.setLineWrap(true);
        criteriosDeterminacaoJTextArea.setRows(5);
        criteriosDeterminacaoJTextArea.setWrapStyleWord(true);
        criteriosDeterminacaoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Probabilidade:"));
        jScrollPane7.setViewportView(criteriosDeterminacaoJTextArea);

        categoriaSelecionada2JLabel.setText(" ");

        criteriosDeterminacaoImpactoJTextArea.setColumns(20);
        criteriosDeterminacaoImpactoJTextArea.setLineWrap(true);
        criteriosDeterminacaoImpactoJTextArea.setRows(5);
        criteriosDeterminacaoImpactoJTextArea.setWrapStyleWord(true);
        criteriosDeterminacaoImpactoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Impacto:"));
        criteriosDeterminacaoImpactoJTextArea.setPreferredSize(new java.awt.Dimension(176, 115));
        jScrollPane2.setViewportView(criteriosDeterminacaoImpactoJTextArea);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        salvarAlteracoesJButton.setText("Salvar Alterações");
        salvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(categoriaSelecionadaJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoriaSelecionada2JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salvarAlteracoesJButton)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriaSelecionadaJLabel)
                    .addComponent(categoriaSelecionada2JLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(salvarAlteracoesJButton))))
        );

        adicionarEstruturaAnaliticaJButton.setText("Adicionar à Estrutura Analítica");
        adicionarEstruturaAnaliticaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarEstruturaAnaliticaJButtonActionPerformed(evt);
            }
        });

        removerCategoriaDaEARJButton.setText("Remover da Estrutura Analítica");
        removerCategoriaDaEARJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerCategoriaDaEARJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adicionarEstruturaAnaliticaJButton)
                .addGap(18, 18, 18)
                .addComponent(removerCategoriaDaEARJButton))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarEstruturaAnaliticaJButton)
                    .addComponent(removerCategoriaDaEARJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoriasTabelaJScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addComponent(earJScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(earJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(categoriasTabelaJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarAlteracoesJButtonActionPerformed

        Categoriaderisco categoria = CatSelecionada;
        categoria.setDescricaoCategoria(descricaoJTextArea.getText());
        categoria.setPossiveisOrigens(possiveisOrigensJTextArea.getText());
        categoria.setCriteriosParaProbabilidade(criteriosDeterminacaoJTextArea.getText());
        categoria.setCriteriosParaImpacto(criteriosDeterminacaoImpactoJTextArea.getText());
        organizacionalEARFacade.salvarAlteracoes(categoria);
        JOptionPane.showMessageDialog(this, "Dados alterados com sucesso.");
        getlistaCategoria();
        repaint();

        // TODO add your handling code here:
    }//GEN-LAST:event_salvarAlteracoesJButtonActionPerformed

    private Boolean existeCategoriaDeMesmoNome(String nomeCategoria){
        
        Boolean existe = false;
        
        for (int i = 0; i < listaCategoria.size(); i++) {
                if ((listaCategoria.get(i).getEarOrganizacional() == true) && ( (listaCategoria.get(i).getNomeCategoria().equals(nomeCategoria))) || nomeCategoria == null || nomeCategoria == "" ) {
                    existe = true;
                }
         }
                return existe;
    }
    
    private void adicionarEstruturaAnaliticaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarEstruturaAnaliticaJButtonActionPerformed

        Boolean pai = false;
        Boolean existeCategoriaNaEARcomEsteNome = false;

        try {

            if (CatSelecionada.getEarOrganizacional() == true) {
                JOptionPane.showMessageDialog(this, "A categoria selecionada já pertence à Estrutura Analítica de Riscos Organizacional");

            }
            
            for (int i = 0; i < listaCategoria.size(); i++) {
                if ((listaCategoria.get(i).getEarOrganizacional() == true) && ( listaCategoria.get(i).getNomeCategoria().equals(CatSelecionada.getNomeCategoria())) ) {
                    
                    int n = JOptionPane.showConfirmDialog(this, "Já existe uma categoria com este nome na Estrutura Analítica de Riscos Organizacional.\nDeseja adicioná-la com outro nome?","Adicionar Categoria à EAR", JOptionPane.YES_NO_OPTION);
                    
                    if (n == JOptionPane.YES_OPTION){
                        String nomeNovaCategoria = JOptionPane.showInputDialog(this, "Digite o novo nome para a categoria ( " + listaCategoria.get(i).getNomeCategoria() + " ):");
                        
                        
                            
                        if ( nomeNovaCategoria.equals(null)) {
                            JOptionPane.showMessageDialog(this, "Categoria NÃO adicionada à EAR.");
                            existeCategoriaNaEARcomEsteNome = true;
                            break;
                        }
                        if ( nomeNovaCategoria.equals("")) {
                            JOptionPane.showMessageDialog(this, "Categoria NÃO adicionada à EAR.");
                            existeCategoriaNaEARcomEsteNome = true;
                            break;
                        }
                        
                        if (existeCategoriaDeMesmoNome(nomeNovaCategoria)){
                            JOptionPane.showMessageDialog(this, "Já existe uma categoria com este nome na Estrutura Analítica de Riscos Organizacional.", "Ja existe esta categoria na EAR", JOptionPane.WARNING_MESSAGE);
                            
                        } else {
                        
                        Categoriaderisco categoriaNomeAlterado = CatSelecionada;
                        CatSelecionada.setNomeCategoria(nomeNovaCategoria);
                        organizacionalEARFacade.salvarAlteracoes(CatSelecionada);
                        existeCategoriaNaEARcomEsteNome = false;
                        
                        break;
                        }
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Categoria não adicionada à EAR.");
                        existeCategoriaNaEARcomEsteNome = true;
                        break;
                    } 
                    
                    existeCategoriaNaEARcomEsteNome = true;
                }

            }

            if (existeCategoriaNaEARcomEsteNome == false) {
                if (CatSelecionada.getFkidCategoriaDeRisco() == null) {

                    int n = JOptionPane.showConfirmDialog(this, "Você confirma a adição da categoria ( " + CatSelecionada.getNomeCategoria() + " )\ncomo filha de (  Projeto  )\npara a Estrutura Analítica de Riscos Organizacional?", "Acidionar Categoria à EAR", JOptionPane.YES_NO_OPTION);

                    if (n == JOptionPane.YES_OPTION) {

                        CatSelecionada.setEarOrganizacional(true);
                        organizacionalEARFacade.salvarAlteracoes(CatSelecionada);

                        getlistaCategoria();
                        arvoreEARJTree = null;
                        categoriasTabelaJTable = null;
                        criarArvore();
                        criarTabela();

                        popularArvoreEtabela();

                        definirEventosArvore();
                        definirEventosTabela();
                    }
                } else {

                    int idPai = CatSelecionada.getFkidCategoriaDeRisco().getIdCategoriaDeRisco();
                    //for para saber se o pai ja esta na ear organizacional
                    for (int i = 0; i < listaCategoria.size(); i++) {
                        if ((idPai == listaCategoria.get(i).getIdCategoriaDeRisco()) && (listaCategoria.get(i).getEarOrganizacional() != false)) {

                            pai = true;
                        }
                    }

                    if (pai == false) {
                        JOptionPane.showMessageDialog(this, "A categoria Pai de ( " + CatSelecionada.getNomeCategoria() + " )\nainda não está na Estrutura Analítica de Riscos Organizacional.\nFavor adicionar a categoria ( " + CatSelecionada.getFkidCategoriaDeRisco().getNomeCategoria() + " ) à EAR.");
                    } else {

                        String nomePai = "erro";
                        for (int i = 0; i < listaCategoria.size(); i++) {
                            if (listaCategoria.get(i).getIdCategoriaDeRisco() == CatSelecionada.getFkidCategoriaDeRisco().getIdCategoriaDeRisco()) {
                                nomePai = listaCategoria.get(i).getNomeCategoria();
                            }
                        }

                        int p = JOptionPane.showConfirmDialog(this, "Você confirma a adição da categoria ( " + CatSelecionada.getNomeCategoria() + " )\ncomo filha de ( " + nomePai /*CatSelecionada.getFkidCategoriaDeRisco().getNomeCategoria()*/ + " )\npara a Estrutura Analítica de Riscos Organizacional?", "Acidionar Categoria à EAR", JOptionPane.YES_NO_OPTION);

                        if (p == JOptionPane.YES_OPTION) {

                            CatSelecionada.setEarOrganizacional(true);
                            organizacionalEARFacade.salvarAlteracoes(CatSelecionada);

                            getlistaCategoria();
                            arvoreEARJTree = null;
                            categoriasTabelaJTable = null;
                            criarArvore();
                            criarTabela();

                            popularArvoreEtabela();

                            definirEventosArvore();
                            definirEventosTabela();

                        } else {
                            JOptionPane.showMessageDialog(this, "Categoria NAO adicionada na EAR.");
                        }

                    }

                }
            }

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria válida.");
        }


    }//GEN-LAST:event_adicionarEstruturaAnaliticaJButtonActionPerformed

    private void removerCategoriaDaEARJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerCategoriaDaEARJButtonActionPerformed

        try {

            if (CatSelecionada.getEarOrganizacional() == false) {

                JOptionPane.showMessageDialog(this, "A categoria selecionada não pertence à Estrutura Analítica de Riscos Organizacional.");

            } else if (((DefaultMutableTreeNode) arvoreEARJTree.getLastSelectedPathComponent()).getChildCount() > 0) {
                JOptionPane.showMessageDialog(this, "A categoria selecionada ( " + CatSelecionada.getNomeCategoria() + " )\npossui filhos, remova todos os filhos antes de removê-la.");

            } else {

                int n = JOptionPane.showConfirmDialog(this, "Você confirma a remoção da categoria ( " + CatSelecionada.getNomeCategoria() + " )\nda Estrutura Analítica de Riscos Organizacional?", "Remover Categoria da EAR", JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {

                    CatSelecionada.setEarOrganizacional(false);
                    organizacionalEARFacade.salvarAlteracoes(CatSelecionada);

                    getlistaCategoria();
                    arvoreEARJTree = null;
                    categoriasTabelaJTable = null;
                    criarArvore();
                    criarTabela();

                    popularArvoreEtabela();

                    definirEventosArvore();
                    definirEventosTabela();

                } else {
                    JOptionPane.showMessageDialog(this, "Remoção cancelada.");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria válida.");
        }

    }//GEN-LAST:event_removerCategoriaDaEARJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarEstruturaAnaliticaJButton;
    private javax.swing.JLabel categoriaSelecionada2JLabel;
    private javax.swing.JLabel categoriaSelecionadaJLabel;
    private javax.swing.JScrollPane categoriasTabelaJScrollPane;
    private javax.swing.JTextArea criteriosDeterminacaoImpactoJTextArea;
    private javax.swing.JTextArea criteriosDeterminacaoJTextArea;
    private javax.swing.JTextArea descricaoJTextArea;
    private javax.swing.JScrollPane earJScrollPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea possiveisOrigensJTextArea;
    private javax.swing.JButton removerCategoriaDaEARJButton;
    private javax.swing.JButton salvarAlteracoesJButton;
    // End of variables declaration//GEN-END:variables

}
