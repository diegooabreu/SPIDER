/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Organizacional;

import facade.OrganizacionalEARFacade;
import facade.ProjetoEstruturaAnaliticaRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.Categoriaderisco;
import model.Organizacao;

/**
 *
 * @author Diego
 */
public class OrganizacionalEditarEARJPanel extends javax.swing.JPanel {

    
        
    // Variavel List para armazenamento das categorias
    private List<Categoriaderisco> listaCategoria;

    OrganizacionalEARFacade organizacionalEARFacade = new OrganizacionalEARFacade();

    public void getlistaCategoria() {
        listaCategoria = organizacionalEARFacade.listarCategorias();
    }

    // Variavel para armazenar o ID da categoria selecionada no momento
    private Categoriaderisco CatSelecionada;
    Categoriaderisco categoriaSelecionadaPAI;

    // Criando Arvore EAR // 
    private JTree arvoreEARProjetoSelecionarPai;
    private JTree arvoreEARJTree;
    private DefaultMutableTreeNode categorias;
    private DefaultMutableTreeNode categoriasPai;
    
    
    Object userObj;
    Object userObj2;
    Organizacao organizacao;
    
    /**
     * Creates new form OrganizacionalEditarEAR
     */
    
    
    public OrganizacionalEditarEARJPanel() {
        initComponents();
        getlistaCategoria();
        criarArvore();
        popularArvore();
        //popularArvorePai();
        definirEventosArvore();
        definirEventosArvoreSelecionarPai();
    }

    
    public void criarArvore() {
        categorias = new DefaultMutableTreeNode("Projeto");
        categoriasPai = new DefaultMutableTreeNode("Projeto");
        
        arvoreEARJTree = new JTree(categorias);
        arvoreEARJTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos Organizacional"));
        earOrganizacionalJScrollPane.setViewportView(arvoreEARJTree);
        
        arvoreEARProjetoSelecionarPai = new JTree(categorias);
        arvoreEARProjetoSelecionarPai.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o Pai para a nova categoria:"));
        earSelecionarProjetoJScrollPane.setViewportView(arvoreEARProjetoSelecionarPai);
        
        repaint();
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
    
    public void popularArvorePai() {

        getlistaCategoria();

        for (int i = 0; i < listaCategoria.size(); i++) {

            if (listaCategoria.get(i).getEarOrganizacional() == true) { // Se EarOrganizacional == true , a categoria pertence à arvore

                if (listaCategoria.get(i).getFkidCategoriaDeRisco() == null) {
                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoria.get(i).getNomeCategoria());
                    categoriasPai.add(filho);
                    arvoreEARProjetoSelecionarPai = new JTree(categoriasPai);
                    earSelecionarProjetoJScrollPane.setViewportView(arvoreEARJTree);
                    repaint();
                } else {

                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoria.get(i).getNomeCategoria());

                    for (int row = 0; row < categoriasPai.getChildCount(); row++) {

                        DefaultMutableTreeNode Pai = (DefaultMutableTreeNode) categoriasPai.getChildAt(row);

                        if (listaCategoria.get(i).getFkidCategoriaDeRisco().getNomeCategoria().equals(Pai.toString())) {
                            Pai.add(filho);

                        } else {

                        }

                        if (Pai.getChildCount() > 0) {
                            percorrerFilhos(Pai, filho, i);
                        }

                    }

                }
            }
  
            
        }
        
        arvoreEARProjetoSelecionarPai = new JTree(categoriasPai);
        arvoreEARProjetoSelecionarPai.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos Organizacional"));
        earSelecionarProjetoJScrollPane.setViewportView(arvoreEARProjetoSelecionarPai);
        
    }
    
    public void popularArvore() {

        getlistaCategoria();

        for (int i = 0; i < listaCategoria.size(); i++) {

            if (listaCategoria.get(i).getEarOrganizacional() == true) { // Se EarOrganizacional == true , a categoria pertence à arvore

                if (listaCategoria.get(i).getFkidCategoriaDeRisco() == null) {
                    DefaultMutableTreeNode filho = new DefaultMutableTreeNode(listaCategoria.get(i).getNomeCategoria());
                    categorias.add(filho);
                    arvoreEARJTree = new JTree(categorias);
                    earOrganizacionalJScrollPane.setViewportView(arvoreEARJTree);
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
            }
  
            
        }
        
        arvoreEARJTree = new JTree(categorias);
        arvoreEARJTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Estrutura Analítica de Riscos Organizacional"));
        earOrganizacionalJScrollPane.setViewportView(arvoreEARJTree);
        repaint();
        
        
        //popularArvorePai();
        
        
    }

    public void definirEventosArvoreSelecionarPai(){
        
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
                    for (int i = 0; i < listaCategoria.size(); i++) {
                        if (userObj2.equals(listaCategoria.get(i).getNomeCategoria())) {
                            categoriaSelecionadaPAI = listaCategoria.get(i);
                            System.out.println("No PAI - " + categoriaSelecionadaPAI.getNomeCategoria());
                        }

                    }

                }
            }

        });
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

                    

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    userObj = node.getUserObject();
                    System.out.println("Usuario escolheu: " + userObj);

                    if (userObj == "Projeto") {
                        CatSelecionada = null;
                    } else {

                        for (int i = 0; i < listaCategoria.size(); i++) {
                            if ((userObj.equals(listaCategoria.get(i).getNomeCategoria())) && (listaCategoria.get(i).getEarOrganizacional() == true)) {
                                CatSelecionada = listaCategoria.get(i);
                                nomeCategoriaSelecionadaJTextField.setText(listaCategoria.get(i).getNomeCategoria());
                                descricaoJTextArea.setText(listaCategoria.get(i).getDescricaoCategoria());
                                possiveisOrigensJTextArea.setText(listaCategoria.get(i).getPossiveisOrigens());
                                criteriosDeterminacaoJTextArea.setText(listaCategoria.get(i).getCriteriosParaProbabilidade());
                                jTextArea2.setText(listaCategoria.get(i).getCriteriosParaImpacto());

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        earOrganizacionalJScrollPane = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        categoriaSelecionadaJLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        descricaoJTextArea = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        possiveisOrigensJTextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        criteriosDeterminacaoJTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        salvarAlteracoesJButton = new javax.swing.JButton();
        nomeCategoriaSelecionadaJTextField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
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
        jPanel9 = new javax.swing.JPanel();
        earSelecionarProjetoJScrollPane = new javax.swing.JScrollPane();
        removerCategoriaJButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(earOrganizacionalJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(earOrganizacionalJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
        );

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

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Impacto:"));
        jScrollPane2.setViewportView(jTextArea2);

        salvarAlteracoesJButton.setText("Salvar Alterações");
        salvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(salvarAlteracoesJButton))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(categoriaSelecionadaJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeCategoriaSelecionadaJTextField))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriaSelecionadaJLabel)
                    .addComponent(nomeCategoriaSelecionadaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salvarAlteracoesJButton)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informações da Categoria Selecionada", jPanel4);

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

        salvaNovaCategoriaJButton.setText("Criar nova Categoria");
        salvaNovaCategoriaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaNovaCategoriaJButtonActionPerformed(evt);
            }
        });

        criteriosImpactoNovaCategoriaJTextArea.setColumns(20);
        criteriosImpactoNovaCategoriaJTextArea.setLineWrap(true);
        criteriosImpactoNovaCategoriaJTextArea.setRows(5);
        criteriosImpactoNovaCategoriaJTextArea.setWrapStyleWord(true);
        criteriosImpactoNovaCategoriaJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Critérios para determinação de Impacto"));
        jScrollPane4.setViewportView(criteriosImpactoNovaCategoriaJTextArea);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salvaNovaCategoriaJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(earSelecionarProjetoJScrollPane)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(0, 351, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeNovaCategoriaJLabel)
                    .addComponent(nomeNovaCategoriaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Adicionar nova Categoria de Riscos à EAR Organizacional", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        removerCategoriaJButton.setText("Remover Categoria de Riscos");
        removerCategoriaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerCategoriaJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(removerCategoriaJButton)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removerCategoriaJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salvaNovaCategoriaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaNovaCategoriaJButtonActionPerformed

        int mesmoNome = 0;
        
        for (int i = 0; i < listaCategoria.size(); i++) {
            if ((nomeNovaCategoriaJTextField.getText().equals(listaCategoria.get(i).getNomeCategoria())) 
                    || (nomeNovaCategoriaJTextField.getText().equals("Projeto") )) {
                JOptionPane.showMessageDialog(this, "Já existe uma categoria com este nome, por favor escolha um novo nome.");
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
        novaCategoria.setEarOrganizacional(true);        
        novaCategoria.setIdOrganizacao(organizacao);
        novaCategoria.setFkidCategoriaDeRisco(categoriaSelecionadaPAI);
          
        ProjetoEstruturaAnaliticaRiscosFacade projetoEstruturaAnaliticaRiscosFacade = new ProjetoEstruturaAnaliticaRiscosFacade();
        
        projetoEstruturaAnaliticaRiscosFacade.criarCategoria(novaCategoria);  
        
        criarArvore();
        popularArvore();
        //popularArvorePai();
        definirEventosArvore();
        definirEventosArvoreSelecionarPai();
        
        
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

    public void getOrganizacao(Organizacao getOrganizacao){
        organizacao = getOrganizacao;
    }
    
    private void salvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarAlteracoesJButtonActionPerformed

        
        Categoriaderisco categoria = CatSelecionada;
        categoria.setNomeCategoria(nomeCategoriaSelecionadaJTextField.getText());
        categoria.setDescricaoCategoria(descricaoJTextArea.getText());
        categoria.setPossiveisOrigens(possiveisOrigensJTextArea.getText());
        categoria.setCriteriosParaProbabilidade(criteriosDeterminacaoJTextArea.getText());
        categoria.setCriteriosParaImpacto(jTextArea2.getText());
        organizacionalEARFacade.salvarAlteracoes(categoria);
        
        getlistaCategoria();
        
        criarArvore();
        popularArvore();
        definirEventosArvore();
        definirEventosArvoreSelecionarPai();
        repaint();
        
        JOptionPane.showMessageDialog(this, "Dados alterados com sucesso.");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_salvarAlteracoesJButtonActionPerformed

    private void removerCategoriaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerCategoriaJButtonActionPerformed

        try {
        // if para saber se existe categoria selecionada
        if (userObj == null){
        
            JOptionPane.showMessageDialog(this, "Por favor selecione uma categoria para remover.");
        
            // if para ver se a categoria tem filhos
        } else if (((DefaultMutableTreeNode) arvoreEARJTree.getLastSelectedPathComponent()).getChildCount() > 0){
            
            JOptionPane.showMessageDialog(this, "A categoria selecionada ( " + CatSelecionada.getNomeCategoria() + " )\npossui filhos, remova todos os filhos antes de removê-la.");
        
            // if para ver se ela pertence à ear organizacional
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Deseja remover a categoria (" + CatSelecionada.getNomeCategoria() + ") da Estrutura Analítica Organizacional?" , "Remover Categoria" , JOptionPane.YES_NO_OPTION);
            
            if ( n == JOptionPane.YES_OPTION){
                
                CatSelecionada.setEarOrganizacional(false);
                organizacionalEARFacade.salvarAlteracoes(CatSelecionada);
                
                JOptionPane.showMessageDialog(this, "Categoria removida com sucesso.");
                
                criarArvore();
                popularArvore();
                //popularArvorePai();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categoriaSelecionadaJLabel;
    private javax.swing.JTextArea criteriosDeterminacaoJTextArea;
    private javax.swing.JTextArea criteriosImpactoNovaCategoriaJTextArea;
    private javax.swing.JTextArea criteriosProbabilidadeNovaCategoriaJTextArea;
    private javax.swing.JTextArea descricaoJTextArea;
    private javax.swing.JTextArea descricaoNovaCategoriaJTextArea;
    private javax.swing.JScrollPane earOrganizacionalJScrollPane;
    private javax.swing.JScrollPane earSelecionarProjetoJScrollPane;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField nomeCategoriaSelecionadaJTextField;
    private javax.swing.JLabel nomeNovaCategoriaJLabel;
    private javax.swing.JTextField nomeNovaCategoriaJTextField;
    private javax.swing.JTextArea possiveisOrigensJTextArea;
    private javax.swing.JTextArea possiveisOrigensNovaCategoriaJTextArea;
    private javax.swing.JButton removerCategoriaJButton;
    private javax.swing.JButton salvaNovaCategoriaJButton;
    private javax.swing.JButton salvarAlteracoesJButton;
    // End of variables declaration//GEN-END:variables
}
