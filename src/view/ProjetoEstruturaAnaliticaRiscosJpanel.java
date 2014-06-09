/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import facade.ProjetoEstruturaAnaliticaRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.Categoriaderisco;
import model.Contem;

/**
 *
 * @author Diogo
 */
public class ProjetoEstruturaAnaliticaRiscosJpanel extends javax.swing.JPanel {

    
    ProjetoEstruturaAnaliticaRiscosFacade projetoEstruturaAnaliticaRiscosFacade = new ProjetoEstruturaAnaliticaRiscosFacade();
    
    //criando arvore//
    private DefaultMutableTreeNode categorias; // nó raiz
    private DefaultMutableTreeNode categoriasOrganizacional;
    
    private JTree arvoreEARProjeto;
    private JTree arvoreEARProjetoSelecionarPai;
    private JTree arvoreAdicionarEAROrganizacionalNoProjeto;
    private JTree arvoreCategoriasEAROrganizacional;
    
    // criando tabela //
    private JTable tabelaEAROrganizacional;
    private DefaultTableModel tabelaEAROrganizacionalModel;
    
    Categoriaderisco categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto;
    Categoriaderisco categoriaSelecionada;
    Categoriaderisco categoriaSelecionadaPAI;
    
    
    Object userObj3;
    Object userObj2;
    Object userObj;
    int idProjetoSelecionado;
    
    List<Categoriaderisco> listaCategoriaEAR;
    
    
    /**
     * Creates new form ProjetoEstruturaAnaliticaRiscos
     */
    public ProjetoEstruturaAnaliticaRiscosJpanel() {
        initComponents();
        criarArvore();
        definirEventosArvore();
        definirEventosArvoreSelecionarPai();
    }

    
    
    public void habilitarProjetoEstruturaAnaliticaRiscosJpanel(){
        removerCategoriaJButton.setEnabled(true);
        categoriaSelecionadaJTextField.setEditable(true);
        descricaoJTextArea.setEditable(true);
        possiveisOrigensJTextArea.setEditable(true);
        criteriosImpactoJTextArea.setEditable(true);
        criteriosProbabilidadeJTextArea.setEditable(true);
        salvarJButton.setEnabled(true);
        
        nomeNovaCategoriaJTextField.setEnabled(true);
        descricaoNovaCategoriaJTextArea.setEnabled(true);
        possiveisOrigensNovaCategoriaJTextArea.setEnabled(true);
        criteriosProbabilidadeNovaCategoriaJTextArea.setEnabled(true);
        criteriosImpactoNovaCategoriaJTextArea.setEnabled(true);
        salvaNovaCategoriaJButton.setEnabled(true);
    }
    
    public void desabilitarProjetoEstruturaAnaliticaRiscosJpanel(){
        removerCategoriaJButton.setEnabled(false);
        categoriaSelecionadaJTextField.setEditable(false);
        descricaoJTextArea.setEditable(false);
        possiveisOrigensJTextArea.setEditable(false);
        criteriosImpactoJTextArea.setEditable(false);
        criteriosProbabilidadeJTextArea.setEditable(false);
        salvarJButton.setEnabled(false);
        
        nomeNovaCategoriaJTextField.setEnabled(false);
        descricaoNovaCategoriaJTextArea.setEnabled(false);
        possiveisOrigensNovaCategoriaJTextArea.setEnabled(false);
        criteriosProbabilidadeNovaCategoriaJTextArea.setEnabled(false);
        criteriosImpactoNovaCategoriaJTextArea.setEnabled(false);
        salvaNovaCategoriaJButton.setEnabled(false);
    }
    
    
    public void criarArvore(){
        categorias = new DefaultMutableTreeNode("Projeto"); // nó raiz = "Projeto"
        categoriasOrganizacional = new DefaultMutableTreeNode("Projeto"); // nó raiz da EAR Organizacional = "Projeto"
        
        arvoreEARProjeto = new JTree(categorias);
        arvoreEARProjeto.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos do Projeto"));
        
        arvoreEARProjetoSelecionarPai = new JTree(categorias);
        arvoreEARProjetoSelecionarPai.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o Pai para a nova categoria:"));
        
        arvoreAdicionarEAROrganizacionalNoProjeto = new JTree(categorias);
        arvoreAdicionarEAROrganizacionalNoProjeto.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o Pai para a nova categoria:"));
        
        arvoreCategoriasEAROrganizacional = new JTree(categoriasOrganizacional);
        arvoreCategoriasEAROrganizacional.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o Pai para a nova categoria:"));
        
        
        earProjetoJScrollPane.setViewportView(arvoreEARProjeto);
        arvoreAdicionarEAROrganizacionalNoProjetoScrollPane.setViewportView(arvoreAdicionarEAROrganizacionalNoProjeto);
        repaint();
    }
    
    
    private void percorrerFilhos(DefaultMutableTreeNode Pai, DefaultMutableTreeNode filho, String paiDaCategoria) {
        for (int i = 0; i < Pai.getChildCount(); i++) {

            DefaultMutableTreeNode FilhoDePai = (DefaultMutableTreeNode) Pai.getChildAt(i);

            if (paiDaCategoria.equals(FilhoDePai.toString())) {

                FilhoDePai.add(filho);

            }

            if (FilhoDePai.getChildCount() > 0) {
                percorrerFilhos(FilhoDePai, filho, paiDaCategoria);
            }

        }

    }
    
    List<Contem> listaContem;
    
    public List<Contem> getListaContem(int idProjetoSelecionado){
        listaContem = projetoEstruturaAnaliticaRiscosFacade.getListaContemWhereIdProjeto(idProjetoSelecionado);
        return listaContem;
    }
    
    
    
   
    
    private void percorrerFilhos2(DefaultMutableTreeNode Pai, DefaultMutableTreeNode filho, int j, List<Categoriaderisco> listaCategorias) {
        for (int i = 0; i < Pai.getChildCount(); i++) {

            List<Categoriaderisco> listaCategoria = listaCategorias;
            
            DefaultMutableTreeNode FilhoDePai = (DefaultMutableTreeNode) Pai.getChildAt(i);

            if (listaCategoria.get(j).getFkidCategoriaDeRisco().getNomeCategoria().equals(FilhoDePai.toString())) {

                FilhoDePai.add(filho);

            }

            if (FilhoDePai.getChildCount() > 0) {
                percorrerFilhos2(FilhoDePai, filho, j, listaCategoria);
            }

        }

    }
    
    private void getListaCategoriaEAR(){
       listaCategoriaEAR = projetoEstruturaAnaliticaRiscosFacade.getCategorias();
    }
    
    public void popularArvoreCategoriasEAROrganizacional() {

        
        getListaCategoriaEAR();
        
        for (int i = 0; i < listaCategoriaEAR.size(); i++) {

            if (listaCategoriaEAR.get(i).getEarOrganizacional() == true) { // Se EarOrganizacional == true , a categoria pertence à arvore

                if (listaCategoriaEAR.get(i).getFkidCategoriaDeRisco() == null) {
                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoriaEAR.get(i).getNomeCategoria());
                    categoriasOrganizacional.add(filho);
                    arvoreCategoriasEAROrganizacional = new JTree(categoriasOrganizacional);
                    arvoreCategoriasEAROrganizacionalScrollPane.setViewportView(arvoreCategoriasEAROrganizacional);
                    repaint();
                } else {

                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoriaEAR.get(i).getNomeCategoria());

                    for (int row = 0; row < categoriasOrganizacional.getChildCount(); row++) {

                        DefaultMutableTreeNode Pai = (DefaultMutableTreeNode) categoriasOrganizacional.getChildAt(row);

                        if (listaCategoriaEAR.get(i).getFkidCategoriaDeRisco().getNomeCategoria().equals(Pai.toString())) {
                            Pai.add(filho);

                        } else {

                        }

                        if (Pai.getChildCount() > 0) {
                            percorrerFilhos2(Pai, filho, i, listaCategoriaEAR);
                        }

                    }

                }
            }

                

        }
        arvoreCategoriasEAROrganizacional = new JTree(categoriasOrganizacional);
        arvoreCategoriasEAROrganizacional.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos Organizacional"));
        arvoreCategoriasEAROrganizacionalScrollPane.setViewportView(arvoreCategoriasEAROrganizacional);
        repaint();
    }
    
    public void definirEventosArvoreCategoriasEAROrganizacional() {
        arvoreCategoriasEAROrganizacional.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    TreePath selPath = arvoreCategoriasEAROrganizacional.getPathForLocation(e.getX(),
                            e.getY());
                    if (selPath == null) {
                        return;
                    }

                    arvoreAdicionarEAROrganizacionalNoProjeto.clearSelection();

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                   /* Object */userObj3 = node.getUserObject();
                    System.out.println("Usuario escolheu: " + userObj3);

                    if (userObj3 == "Projeto") {
                        categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto = null;
                    } else {

                        System.out.println(listaCategoriaEAR);
                        
                        getListaCategoriaEAR();
                        
                        for (int i = 0; i < listaCategoriaEAR.size(); i++) {
                            if ((userObj3.equals(listaCategoriaEAR.get(i).getNomeCategoria())) && (listaCategoriaEAR.get(i).getEarOrganizacional() == true)) {
                                categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto = listaCategoriaEAR.get(i);
                                categoriaSelecionadaAdicionarEAROrganizacionalJLabel.setText(listaCategoriaEAR.get(i).getNomeCategoria());
                                descricaoAdicionarCategoriaEAROrganizacionalJTextArea.setText(listaCategoriaEAR.get(i).getDescricaoCategoria());
                                //categoriaSelecionada2JLabel.setText(listaCategoria.get(i).getNomeCategoria());
                                //descricaoJTextArea.setText(listaCategoria.get(i).getDescricaoCategoria());
                                //possiveisOrigensJTextArea.setText(listaCategoria.get(i).getPossiveisOrigens());
                                //criteriosDeterminacaoJTextArea.setText(listaCategoria.get(i).getCriteriosParaProbabilidade());
                                //criteriosDeterminacaoImpactoJTextArea.setText(listaCategoria.get(i).getCriteriosParaImpacto());

                            }

                        }
                        
                    }
                }
            }

        });
    }
    
    
    
    
    public void popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional(int idProjetoSelecionado){
        
        this.idProjetoSelecionado = idProjetoSelecionado;
        getListaContem(idProjetoSelecionado);
        
        for (int i = 0; i < listaContem.size(); i++) {
            
            if (listaContem.get(i).getCategoriaderisco().getFkidCategoriaDeRisco() == null) {
                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaContem.get(i).getCategoriaderisco().getNomeCategoria());
                    categorias.add(filho);
                    arvoreEARProjeto = new JTree(categorias);
                    earProjetoJScrollPane.setViewportView(arvoreEARProjeto);
                    repaint();
                } else {

                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaContem.get(i).getCategoriaderisco().getNomeCategoria());

                    for (int row = 0; row < categorias.getChildCount(); row++) {

                        DefaultMutableTreeNode Pai = (DefaultMutableTreeNode) categorias.getChildAt(row);

                        if (listaContem.get(i).getCategoriaderisco().getFkidCategoriaDeRisco().getNomeCategoria().equals(Pai.toString())) {
                            Pai.add(filho);

                        } else {

                        }

                        if (Pai.getChildCount() > 0) {
                            percorrerFilhos(Pai, filho, listaContem.get(i).getCategoriaderisco().getFkidCategoriaDeRisco().getNomeCategoria());
                        }

                    }

                }
        }
        
        arvoreEARProjeto = new JTree(categorias);
        arvoreEARProjeto.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos do Projeto"));
        
        arvoreEARProjetoSelecionarPai = new JTree(categorias);
        arvoreEARProjetoSelecionarPai.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o Pai para a nova categoria:"));
        
        earSelecionarProjetoJScrollPane.setViewportView(arvoreEARProjetoSelecionarPai);        
        earProjetoJScrollPane.setViewportView(arvoreEARProjeto);
        
        repaint();
        definirEventosArvore();
        definirEventosArvoreSelecionarPai();
        
        popularArvoreCategoriasEAROrganizacional();
        
        definirEventosArvoreCategoriasEAROrganizacional();
        definirEventosArvoreAdicionarEAROrganizacionalNoProjeto();
        
        
    }
    
    

    
    private void definirEventosArvore() {
        arvoreEARProjeto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    TreePath selPath = arvoreEARProjeto.getPathForLocation(e.getX(),
                            e.getY());
                    if (selPath == null) {
                        return;
                    }

                    

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    userObj = node.getUserObject();
                    System.out.println("Usuario escolheu: " + userObj);

                    if (userObj == "Projeto"){
                        categoriaSelecionada = null;
                    } else {
                    
                    for (int i = 0; i < listaContem.size(); i++) {
                        if (userObj.equals(listaContem.get(i).getCategoriaderisco().getNomeCategoria())) {
                            categoriaSelecionada = listaContem.get(i).getCategoriaderisco();
                            categoriaSelecionadaJTextField.setText(listaContem.get(i).getCategoriaderisco().getNomeCategoria());
                            descricaoJTextArea.setText(listaContem.get(i).getCategoriaderisco().getDescricaoCategoria());
                            possiveisOrigensJTextArea.setText(listaContem.get(i).getCategoriaderisco().getPossiveisOrigens());
                            criteriosProbabilidadeJTextArea.setText(listaContem.get(i).getCategoriaderisco().getCriteriosParaProbabilidade());
                            criteriosImpactoJTextArea.setText(listaContem.get(i).getCategoriaderisco().getCriteriosParaImpacto());
                         

                        }

                    }
                  }
                }
            }

        });
    }
    
    
    private void definirEventosArvoreAdicionarEAROrganizacionalNoProjeto() {
        arvoreAdicionarEAROrganizacionalNoProjeto.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    TreePath selPath = arvoreAdicionarEAROrganizacionalNoProjeto.getPathForLocation(e.getX(),
                            e.getY());
                    if (selPath == null) {
                        return;
                    }

                    

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    userObj3 = node.getUserObject();
                    System.out.println("Usuario escolheu: " + userObj3);

                    arvoreCategoriasEAROrganizacional.clearSelection();
                    
                    if (userObj3 == "Projeto"){
                        categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto = null;
                    } else {
                    
                    for (int i = 0; i < listaContem.size(); i++) {
                        if (userObj3.equals(listaContem.get(i).getCategoriaderisco().getNomeCategoria())) {
                            
                            categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto = listaContem.get(i).getCategoriaderisco();
                            categoriaSelecionadaAdicionarEAROrganizacionalJLabel.setText(listaContem.get(i).getCategoriaderisco().getNomeCategoria());
                            descricaoAdicionarCategoriaEAROrganizacionalJTextArea.setText(listaContem.get(i).getCategoriaderisco().getDescricaoCategoria());
                            
                            //categoriaSelecionada = listaContem.get(i).getCategoriaderisco();
                            //categoriaSelecionadaJTextField.setText(listaContem.get(i).getCategoriaderisco().getNomeCategoria());
                            //descricaoJTextArea.setText(listaContem.get(i).getCategoriaderisco().getDescricaoCategoria());
                            //possiveisOrigensJTextArea.setText(listaContem.get(i).getCategoriaderisco().getPossiveisOrigens());
                            //criteriosProbabilidadeJTextArea.setText(listaContem.get(i).getCategoriaderisco().getCriteriosParaProbabilidade());
                            //criteriosImpactoJTextArea.setText(listaContem.get(i).getCategoriaderisco().getCriteriosParaImpacto());
                         

                        }

                    }
                  }
                }
            }

        });
    }
    
    private void definirEventosArvoreSelecionarPai(){
        
        arvoreEARProjetoSelecionarPai.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    TreePath selPath = arvoreEARProjetoSelecionarPai.getPathForLocation(e.getX(),
                            e.getY());
                    if (selPath == null) {
                        
                        return;
                    }
                    
                    
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    userObj2 = node.getUserObject();
                    System.out.println("Usuario escolheu: " + userObj2);
                    categoriaSelecionadaPAI = null;
                    for (int i = 0; i < listaContem.size(); i++) {
                        if (userObj2.equals(listaContem.get(i).getCategoriaderisco().getNomeCategoria())) {
                            categoriaSelecionadaPAI = listaContem.get(i).getCategoriaderisco();

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

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        categoriaSelecionadaJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoJTextArea = new javax.swing.JTextArea();
        salvarJButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        criteriosProbabilidadeJTextArea = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        possiveisOrigensJTextArea = new javax.swing.JTextArea();
        earProjetoJScrollPane = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        criteriosImpactoJTextArea = new javax.swing.JTextArea();
        removerCategoriaJButton = new javax.swing.JButton();
        categoriaSelecionadaJTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        nomeNovaCategoriaJLabel = new javax.swing.JLabel();
        nomeNovaCategoriaJTextField = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        possiveisOrigensNovaCategoriaJTextArea = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        descricaoNovaCategoriaJTextArea = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        criteriosProbabilidadeNovaCategoriaJTextArea = new javax.swing.JTextArea();
        salvaNovaCategoriaJButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        criteriosImpactoNovaCategoriaJTextArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        earSelecionarProjetoJScrollPane = new javax.swing.JScrollPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        arvoreCategoriasEAROrganizacionalScrollPane = new javax.swing.JScrollPane();
        jLabel6 = new javax.swing.JLabel();
        categoriaSelecionadaAdicionarEAROrganizacionalJLabel = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        descricaoAdicionarCategoriaEAROrganizacionalJTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        arvoreAdicionarEAROrganizacionalNoProjetoScrollPane = new javax.swing.JScrollPane();

        jScrollPane3.setHorizontalScrollBar(null);

        categoriaSelecionadaJLabel.setText("Categoria Selecionada");

        descricaoJTextArea.setColumns(20);
        descricaoJTextArea.setLineWrap(true);
        descricaoJTextArea.setRows(4);
        descricaoJTextArea.setWrapStyleWord(true);
        descricaoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jScrollPane1.setViewportView(descricaoJTextArea);

        salvarJButton.setText("Salvar Alterações");
        salvarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarJButtonActionPerformed(evt);
            }
        });

        criteriosProbabilidadeJTextArea.setColumns(20);
        criteriosProbabilidadeJTextArea.setLineWrap(true);
        criteriosProbabilidadeJTextArea.setRows(4);
        criteriosProbabilidadeJTextArea.setWrapStyleWord(true);
        criteriosProbabilidadeJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Probabilidade "));
        jScrollPane8.setViewportView(criteriosProbabilidadeJTextArea);

        possiveisOrigensJTextArea.setColumns(20);
        possiveisOrigensJTextArea.setLineWrap(true);
        possiveisOrigensJTextArea.setRows(4);
        possiveisOrigensJTextArea.setWrapStyleWord(true);
        possiveisOrigensJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Possíveis Origens"));
        jScrollPane9.setViewportView(possiveisOrigensJTextArea);

        criteriosImpactoJTextArea.setColumns(20);
        criteriosImpactoJTextArea.setLineWrap(true);
        criteriosImpactoJTextArea.setRows(4);
        criteriosImpactoJTextArea.setWrapStyleWord(true);
        criteriosImpactoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Impacto"));
        jScrollPane5.setViewportView(criteriosImpactoJTextArea);

        removerCategoriaJButton.setText("Remover Categoria");
        removerCategoriaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerCategoriaJButtonActionPerformed(evt);
            }
        });

        categoriaSelecionadaJTextField.setText("Nenhum");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(categoriaSelecionadaJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoriaSelecionadaJTextField))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(removerCategoriaJButton)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(salvarJButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                            .addComponent(jScrollPane9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(earProjetoJScrollPane)
                            .addComponent(jScrollPane5))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(earProjetoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removerCategoriaJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriaSelecionadaJLabel)
                    .addComponent(categoriaSelecionadaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salvarJButton)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel1);

        jTabbedPane1.addTab("Estrutura Analitica de Riscos do Projeto", jScrollPane3);

        jScrollPane2.setHorizontalScrollBar(null);

        nomeNovaCategoriaJLabel.setText("Nome da nova Categoria de Risco:");

        possiveisOrigensNovaCategoriaJTextArea.setColumns(20);
        possiveisOrigensNovaCategoriaJTextArea.setLineWrap(true);
        possiveisOrigensNovaCategoriaJTextArea.setRows(4);
        possiveisOrigensNovaCategoriaJTextArea.setWrapStyleWord(true);
        possiveisOrigensNovaCategoriaJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Possíveis Origens"));
        jScrollPane10.setViewportView(possiveisOrigensNovaCategoriaJTextArea);

        descricaoNovaCategoriaJTextArea.setColumns(20);
        descricaoNovaCategoriaJTextArea.setLineWrap(true);
        descricaoNovaCategoriaJTextArea.setRows(4);
        descricaoNovaCategoriaJTextArea.setWrapStyleWord(true);
        descricaoNovaCategoriaJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jScrollPane11.setViewportView(descricaoNovaCategoriaJTextArea);

        criteriosProbabilidadeNovaCategoriaJTextArea.setColumns(20);
        criteriosProbabilidadeNovaCategoriaJTextArea.setLineWrap(true);
        criteriosProbabilidadeNovaCategoriaJTextArea.setRows(4);
        criteriosProbabilidadeNovaCategoriaJTextArea.setWrapStyleWord(true);
        criteriosProbabilidadeNovaCategoriaJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Probabilidade"));
        jScrollPane12.setViewportView(criteriosProbabilidadeNovaCategoriaJTextArea);

        salvaNovaCategoriaJButton.setText("Salvar Nova Categoria");
        salvaNovaCategoriaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaNovaCategoriaJButtonActionPerformed(evt);
            }
        });

        criteriosImpactoNovaCategoriaJTextArea.setColumns(20);
        criteriosImpactoNovaCategoriaJTextArea.setLineWrap(true);
        criteriosImpactoNovaCategoriaJTextArea.setRows(4);
        criteriosImpactoNovaCategoriaJTextArea.setWrapStyleWord(true);
        criteriosImpactoNovaCategoriaJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Impacto"));
        jScrollPane4.setViewportView(criteriosImpactoNovaCategoriaJTextArea);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
            .addComponent(jScrollPane11)
            .addComponent(jScrollPane12)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(salvaNovaCategoriaJButton))
            .addComponent(jScrollPane4)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salvaNovaCategoriaJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(earSelecionarProjetoJScrollPane)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(earSelecionarProjetoJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(nomeNovaCategoriaJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeNovaCategoriaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 364, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeNovaCategoriaJLabel)
                    .addComponent(nomeNovaCategoriaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane1.addTab("Adicionar Nova Categoria", jScrollPane2);

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jButton2.setText("Adicionar Categoria ao Projeto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Categoria selecionada:");

        descricaoAdicionarCategoriaEAROrganizacionalJTextArea.setColumns(20);
        descricaoAdicionarCategoriaEAROrganizacionalJTextArea.setRows(5);
        descricaoAdicionarCategoriaEAROrganizacionalJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));
        jScrollPane15.setViewportView(descricaoAdicionarCategoriaEAROrganizacionalJTextArea);

        jLabel1.setText("Estrutura Analítica de Riscos Organizacional:");

        jLabel5.setText("Estrutura Analítica de Riscos do Projeto:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
            .addComponent(arvoreAdicionarEAROrganizacionalNoProjetoScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arvoreAdicionarEAROrganizacionalNoProjetoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoriaSelecionadaAdicionarEAROrganizacionalJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane15)
                    .addComponent(arvoreCategoriasEAROrganizacionalScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(arvoreCategoriasEAROrganizacionalScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(categoriaSelecionadaAdicionarEAROrganizacionalJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel15);

        jTabbedPane1.addTab("Adicionar Categoria da EAR Organizacional ao Projeto", jScrollPane7);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salvarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarJButtonActionPerformed
        
        Categoriaderisco categoria = categoriaSelecionada;
        
        
        categoria.setNomeCategoria(categoriaSelecionadaJTextField.getText());
        categoria.setDescricaoCategoria(descricaoJTextArea.getText());
        categoria.setPossiveisOrigens(possiveisOrigensJTextArea.getText());
        categoria.setCriteriosParaProbabilidade(criteriosProbabilidadeJTextArea.getText());
        categoria.setCriteriosParaImpacto(criteriosImpactoJTextArea.getText());
        projetoEstruturaAnaliticaRiscosFacade.salvarAlteracoes(categoria);
        JOptionPane.showMessageDialog(this, "Dados alterados com sucesso.");
        getListaContem(listaContem.get(0).getProjeto().getIdProjeto());
        criarArvore();
        popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional(idProjetoSelecionado);
        definirEventosArvore();
        definirEventosArvoreSelecionarPai();
        repaint();

// TODO add your handling code here:
    }//GEN-LAST:event_salvarJButtonActionPerformed

    private void salvaNovaCategoriaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaNovaCategoriaJButtonActionPerformed
        // variavel auxiliar para manipular a existencia de categorias de mesmo nome atraves do for
        int mesmoNome = 0;
        
        for (int i = 0; i < listaContem.size(); i++) {
            if ((nomeNovaCategoriaJTextField.getText().equals(listaContem.get(i).getCategoriaderisco().getNomeCategoria())) 
                    || (nomeNovaCategoriaJTextField.getText().equals("Projeto") )) {
                JOptionPane.showMessageDialog(this, "Já existe uma categoria com este nome no projeto, por favor escolha um novo nome.");
                mesmoNome = 1;
                break;
                }
        }           
        
        if (mesmoNome == 0){
          if (userObj2 == null){
            JOptionPane.showMessageDialog(this, "Por favor selecione um pai para a nova categoria.");
        } else {
        
        Categoriaderisco novaCategoria = new Categoriaderisco();
        
        novaCategoria.setNomeCategoria(nomeNovaCategoriaJTextField.getText());
        novaCategoria.setDescricaoCategoria(descricaoNovaCategoriaJTextArea.getText());
        novaCategoria.setPossiveisOrigens(possiveisOrigensNovaCategoriaJTextArea.getText());
        novaCategoria.setCriteriosParaProbabilidade(criteriosProbabilidadeNovaCategoriaJTextArea.getText());
        novaCategoria.setCriteriosParaImpacto(criteriosImpactoNovaCategoriaJTextArea.getText());
        novaCategoria.setEarOrganizacional(false);        
        novaCategoria.setIdOrganizacao(projetoEstruturaAnaliticaRiscosFacade.getOrganizacao());
        novaCategoria.setFkidCategoriaDeRisco(categoriaSelecionadaPAI);
        
        projetoEstruturaAnaliticaRiscosFacade.criarCategoria(novaCategoria);
        
        
        
        Contem novaContem = new Contem( idProjetoSelecionado/* listaContem.get(0).getProjeto().getIdProjeto() */ , projetoEstruturaAnaliticaRiscosFacade.getIdUltimaCategoria() );
        novaContem.setProjeto(projetoEstruturaAnaliticaRiscosFacade.getProjeto(idProjetoSelecionado));
        novaContem.setCategoriaderisco(novaCategoria);
        
        
        projetoEstruturaAnaliticaRiscosFacade.criarContem(novaContem);
        

        criarArvore();
        popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional( idProjetoSelecionado /* listaContem.get(0).getProjeto().getIdProjeto() */);
        
        nomeNovaCategoriaJTextField.setText(null);
        descricaoNovaCategoriaJTextArea.setText(null);
        possiveisOrigensNovaCategoriaJTextArea.setText(null);
        criteriosProbabilidadeNovaCategoriaJTextArea.setText(null);
        criteriosImpactoNovaCategoriaJTextArea.setText(null);
        
        
        userObj2 = null;
        JOptionPane.showMessageDialog(this, "Categoria Criada com sucesso!");
        
          
            
        
          }
        
        }   
        
        mesmoNome = 0;
        
// TODO add your handling code here:
    }//GEN-LAST:event_salvaNovaCategoriaJButtonActionPerformed

    private void removerCategoriaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerCategoriaJButtonActionPerformed
        
        try {
        // if para saber se existe categoria selecionada
        if (userObj == null){
        
            JOptionPane.showMessageDialog(this, "Por favor selecione uma categoria para remover.");
        
            // if para ver se a categoria tem filhos
        } else if (((DefaultMutableTreeNode) arvoreEARProjeto.getLastSelectedPathComponent()).getChildCount() > 0){
            
            JOptionPane.showMessageDialog(this, "A categoria selecionada ( " + categoriaSelecionada.getNomeCategoria() + " )\npossui filhos, remova todos os filhos antes de removê-la.");
        
            // if para ver se ela pertence à ear organizacional
        } else if (categoriaSelecionada.getEarOrganizacional() == true){
            int n = JOptionPane.showConfirmDialog(this, "Deseja remover a categoria (" + categoriaSelecionada.getNomeCategoria() + ") deste Projeto?" , "Remover Categoria" , JOptionPane.YES_NO_OPTION);

            if ( n == JOptionPane.YES_OPTION){
                projetoEstruturaAnaliticaRiscosFacade.destroyContem(idProjetoSelecionado, categoriaSelecionada.getIdCategoriaDeRisco());
                
                JOptionPane.showMessageDialog(this, "Categoria removida do projeto com sucesso.");
                
                criarArvore();
                popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional(idProjetoSelecionado);
                definirEventosArvore();
                definirEventosArvoreSelecionarPai();
                
            }
            userObj = null;
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Deseja excluir permanentemente a categoria (" + categoriaSelecionada.getNomeCategoria() + ") ?" , "Remover Categoria" , JOptionPane.YES_NO_OPTION);
            
            if ( n == JOptionPane.YES_OPTION){
                projetoEstruturaAnaliticaRiscosFacade.destroyContem(idProjetoSelecionado, categoriaSelecionada.getIdCategoriaDeRisco());
                projetoEstruturaAnaliticaRiscosFacade.destroyCategoriaDeRisco(categoriaSelecionada.getIdCategoriaDeRisco());
                
                JOptionPane.showMessageDialog(this, "Categoria excluida com sucesso.");
                
                criarArvore();
                popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional(idProjetoSelecionado);
                definirEventosArvore();
                definirEventosArvoreSelecionarPai();
                
            }
        userObj = null;
        }
        
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria válida.");
        }  
        // TODO add your handling code here:

    }//GEN-LAST:event_removerCategoriaJButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean existeCategoriaSelecionadaNoProjeto = false;
        
        for (int i = 0; i < listaContem.size(); i++){
            
            if (listaContem.get(i).getCategoriaderisco().getIdCategoriaDeRisco() == categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto.getIdCategoriaDeRisco()){
                existeCategoriaSelecionadaNoProjeto = true;
            }
            
        }
        
        if (existeCategoriaSelecionadaNoProjeto == true){
            JOptionPane.showMessageDialog(this, "Já existe uma categoria de mesmo nome no projeto.");
        } else {
            
            int n = JOptionPane.showConfirmDialog(this, "Você deseja adicionar a categoria ( " + categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto.getNomeCategoria() + " )\npara o projeto?","Adicionar Categoria ao Projeto", JOptionPane.YES_NO_OPTION);
                    
            if (n == JOptionPane.YES_OPTION){
            Contem contem = new Contem();
            contem.setCategoriaderisco(categoriaSelecionadaAdicionarEAROrganizacionalNoProjeto);
            contem.setProjeto(projetoEstruturaAnaliticaRiscosFacade.getProjeto(idProjetoSelecionado));
            
            projetoEstruturaAnaliticaRiscosFacade.criarContem(contem);
            
            criarArvore();
            popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional( idProjetoSelecionado /* listaContem.get(0).getProjeto().getIdProjeto() */);
            
            JOptionPane.showMessageDialog(this, "Categoria adicionada ao projeto com sucesso.");
            
            } else {
                JOptionPane.showMessageDialog(this, "Categoria não adicionada.");
            }
            
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane arvoreAdicionarEAROrganizacionalNoProjetoScrollPane;
    private javax.swing.JScrollPane arvoreCategoriasEAROrganizacionalScrollPane;
    private javax.swing.JLabel categoriaSelecionadaAdicionarEAROrganizacionalJLabel;
    private javax.swing.JLabel categoriaSelecionadaJLabel;
    private javax.swing.JTextField categoriaSelecionadaJTextField;
    private javax.swing.JTextArea criteriosImpactoJTextArea;
    private javax.swing.JTextArea criteriosImpactoNovaCategoriaJTextArea;
    private javax.swing.JTextArea criteriosProbabilidadeJTextArea;
    private javax.swing.JTextArea criteriosProbabilidadeNovaCategoriaJTextArea;
    private javax.swing.JTextArea descricaoAdicionarCategoriaEAROrganizacionalJTextArea;
    private javax.swing.JTextArea descricaoJTextArea;
    private javax.swing.JTextArea descricaoNovaCategoriaJTextArea;
    private javax.swing.JScrollPane earProjetoJScrollPane;
    private javax.swing.JScrollPane earSelecionarProjetoJScrollPane;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nomeNovaCategoriaJLabel;
    private javax.swing.JTextField nomeNovaCategoriaJTextField;
    private javax.swing.JTextArea possiveisOrigensJTextArea;
    private javax.swing.JTextArea possiveisOrigensNovaCategoriaJTextArea;
    private javax.swing.JButton removerCategoriaJButton;
    private javax.swing.JButton salvaNovaCategoriaJButton;
    private javax.swing.JButton salvarJButton;
    private javax.swing.JScrollPane tabelaAdicionarEAROrganizacionalNoProjetoScrollPane;
    private javax.swing.JScrollPane tabelaAdicionarEAROrganizacionalNoProjetoScrollPane1;
    // End of variables declaration//GEN-END:variables
}
