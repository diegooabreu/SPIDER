/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.AvaliacaoCategoriaFacade;
import facade.AvaliacaoProjetoFacade;
import facade.OrganizacionalPortfolioFacade;
import facade.ProjetoFacade;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Avaliacaocategoria;
import model.Avaliacaoprojeto;
import model.Categoriaderisco;
import model.Contem;
import model.Projeto;

/**
 *
 * @author Mariano
 */
public class OrganizacionalPortfolioJPanel extends javax.swing.JPanel {

    /**
     * Creates new form JPanel_Organizacional_Portfolio
     */
    //Variável List para armazenamento dos projetos
    private List<Projeto> listaProjetos;
    private List<Contem> listaContem;
    private List<Categoriaderisco> listaCategorias;

    OrganizacionalPortfolioFacade organizacionalPortfolioFacade = new OrganizacionalPortfolioFacade();
    AvaliacaoProjetoFacade avaliacaoProjetoFacade = new AvaliacaoProjetoFacade();
    AvaliacaoCategoriaFacade avaliacaoCategoriaFacade = new AvaliacaoCategoriaFacade();
    ProjetoFacade projetoFacade = new ProjetoFacade();

    public void getListaProjetos() {
        listaProjetos = organizacionalPortfolioFacade.listarProjetos();
    }

    public void getListaContem() {
        listaContem = organizacionalPortfolioFacade.listarContem();
    }

    public void getListaCategorias() {
        listaCategorias = organizacionalPortfolioFacade.listarCategorias();
    }

    // Criando tabela Portfolio
    private JTable portfolioTabelaJTable = null;
    private JTable categoriasAdicionadasJTable = null;
    private DefaultTableModel modeloPortfoliTabelaJTable = null;
    private DefaultTableModel modeloCategoriasAdicionadasJTable = null;

    // Objeto Projeto que armazena o Projeto Selecionado
    private Projeto projetoSelecionado = new Projeto(1); // Utiliza o id do projeto no Banco de Dados
    // Objeto Avaliacaocategoria que armazena a categoria selecionada
    Avaliacaocategoria avaliacaoCategoria = new Avaliacaocategoria(1);
    // Objeto Categoriaderisco que armazena a categoria seleionada
    Categoriaderisco categoriaRisco = new Categoriaderisco(1);

    public OrganizacionalPortfolioJPanel() {
        initComponents();
        getListaProjetos();
        getListaContem();
        getListaCategorias();
        criarTabelaProjetos();
        criarTabelaCategoriasAdicionadas();
        preencherDadosTabelaProjeto();
        definirEventosTabelaProjeto();
        definirEventosTabelaCategoriasAdicionadas();
        iniciarBotoesDesativados();
        desabilitaCamposTextoProjeto();
        desabilitaCamposTextoCategoria();
    }

    private void iniciarBotoesDesativados() {
        criarAvaliacaoCategoriaJButton.setEnabled(false);
        criarAvaliacaoProjetoJButton.setEnabled(false);
        salvarAlteracoesCategoriaJButton.setEnabled(false);
        salvarAlteracoesProjetoJButton.setEnabled(false);
    }
    
    public void reiniciarTabelaPortfolio(){
        getListaProjetos();
        getListaCategorias();
        getListaContem();
        modeloPortfoliTabelaJTable = null;
        criarTabelaProjetos();
        preencherDadosTabelaProjeto();
        definirEventosTabelaProjeto();
        desabilitaCamposTextoProjeto();
        criarAvaliacaoProjetoJButton.setEnabled(false);
        salvarAlteracoesProjetoJButton.setEnabled(false);
    }

    private void criarTabelaProjetos() {
        portfolioTabelaJTable = new JTable();
        modeloPortfoliTabelaJTable = new DefaultTableModel(){
            public boolean isCellEditable(int row, int col) {  
            return false;  
            }  
        };
        modeloPortfoliTabelaJTable.setColumnIdentifiers(new String[]{"Nome do Projeto", "Gerente de Projeto", "Responsável pela Gerência de Riscos", "Status do Projeto", "Status da Avaliação"});
        portfolioTabelaJTable.setModel(modeloPortfoliTabelaJTable);
        tabelaPortfolioJScrollPane.setViewportView(portfolioTabelaJTable);
    }

    private void criarTabelaCategoriasAdicionadas() {
        categoriasAdicionadasJTable = new JTable();
        modeloCategoriasAdicionadasJTable = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col) {  
            return false;  
            }  
        };
        modeloCategoriasAdicionadasJTable.setColumnIdentifiers(new String[]{"Categorias Adicionadas no projeto"});
        categoriasAdicionadasJTable.setModel(modeloCategoriasAdicionadasJTable);
        tabelaCategoriasAdicionadasJScrollPane.setViewportView(categoriasAdicionadasJTable);
    }

    private void preencherDadosTabelaProjeto() {
        for (int i = 0; i < listaProjetos.size(); i++) {
            String statusAvaliacao = null;
            String statusProjeto = null;

            if (listaProjetos.get(i).getConcluido() == true) {
                statusProjeto = "Concluído";

                if (avaliacaoProjetoFacade.buscaAvaliacao(listaProjetos.get(i)) == null) {
                    statusAvaliacao = "Avaliação Pendente";
                } else {
                    statusAvaliacao = "Avaliado";
                }
            } else {
                statusProjeto = "Não Concluído";
                statusAvaliacao = "Indisponível";
            }

            String[] linha = new String[]{listaProjetos.get(i).getNomeProjeto(), listaProjetos.get(i).getResponsavelProjeto(), listaProjetos.get(i).getResponsavelGerenciaRiscos(), statusProjeto, statusAvaliacao};

            modeloPortfoliTabelaJTable.addRow(linha);
        }
    }

    private void preencherDadosTabelaCategoriasAdicionadas() {
        for (int i = 0; i < listaContem.size(); i++) {
            if (listaContem.get(i).getProjeto().getIdProjeto() == projetoSelecionado.getIdProjeto()) {
                String[] linha = new String[]{listaContem.get(i).getCategoriaderisco().getNomeCategoria()};

                modeloCategoriasAdicionadasJTable.addRow(linha);
            }
        }
    }

    private void desabilitaCamposTextoProjeto() {
        pontosFortesProjetoJTextArea.setEditable(false);
        pontosFracosProjetoJTextArea.setEditable(false);
        oportunidadesMelhoriaProjetoJTextArea.setEditable(false);
        informacoesAdicionaisProjetoJTextArea.setEditable(false);

        pontosFortesProjetoJTextArea.setBackground(Color.LIGHT_GRAY);
        pontosFracosProjetoJTextArea.setBackground(Color.LIGHT_GRAY);
        oportunidadesMelhoriaProjetoJTextArea.setBackground(Color.LIGHT_GRAY);
        informacoesAdicionaisProjetoJTextArea.setBackground(Color.LIGHT_GRAY);

        pontosFortesProjetoJTextArea.setText(null);
        pontosFracosProjetoJTextArea.setText(null);
        oportunidadesMelhoriaProjetoJTextArea.setText(null);
        informacoesAdicionaisProjetoJTextArea.setText(null);
    }

    private void reabilitarCamposTextoProjeto() {
        pontosFortesProjetoJTextArea.setEditable(true);
        pontosFracosProjetoJTextArea.setEditable(true);
        oportunidadesMelhoriaProjetoJTextArea.setEditable(true);
        informacoesAdicionaisProjetoJTextArea.setEditable(true);

        pontosFortesProjetoJTextArea.setBackground(Color.WHITE);
        pontosFracosProjetoJTextArea.setBackground(Color.WHITE);
        oportunidadesMelhoriaProjetoJTextArea.setBackground(Color.WHITE);
        informacoesAdicionaisProjetoJTextArea.setBackground(Color.WHITE);
    }

    private void desabilitaCamposTextoCategoria() {
        pontosFortesCategoriaJTextArea.setEditable(false);
        pontosFracosCategoriaJTextArea.setEditable(false);
        oportunidadesMelhoriaCategoriaJTextArea.setEditable(false);
        informacoesAdicionaisCategoriaJTextArea.setEditable(false);

        pontosFortesCategoriaJTextArea.setBackground(Color.LIGHT_GRAY);
        pontosFracosCategoriaJTextArea.setBackground(Color.LIGHT_GRAY);
        oportunidadesMelhoriaCategoriaJTextArea.setBackground(Color.LIGHT_GRAY);
        informacoesAdicionaisCategoriaJTextArea.setBackground(Color.LIGHT_GRAY);

        pontosFortesCategoriaJTextArea.setText(null);
        pontosFracosCategoriaJTextArea.setText(null);
        oportunidadesMelhoriaCategoriaJTextArea.setText(null);
        informacoesAdicionaisCategoriaJTextArea.setText(null);
    }

    private void reabilitarCamposTextoCategoria() {
        pontosFortesCategoriaJTextArea.setEditable(true);
        pontosFracosCategoriaJTextArea.setEditable(true);
        oportunidadesMelhoriaCategoriaJTextArea.setEditable(true);
        informacoesAdicionaisCategoriaJTextArea.setEditable(true);

        pontosFortesCategoriaJTextArea.setBackground(Color.WHITE);
        pontosFracosCategoriaJTextArea.setBackground(Color.WHITE);
        oportunidadesMelhoriaCategoriaJTextArea.setBackground(Color.WHITE);
        informacoesAdicionaisCategoriaJTextArea.setBackground(Color.WHITE);
    }

    private void definirEventosTabelaCategoriasAdicionadas() {
        categoriasAdicionadasJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    int selected = categoriasAdicionadasJTable.getSelectedRow();

                    // procura a categoria selecionada
                    for (int i = 0; i < listaCategorias.size(); i++) {
                        if (categoriasAdicionadasJTable.getValueAt(selected, 0).equals(listaCategorias.get(i).getNomeCategoria())) {
                            categoriaRisco = listaCategorias.get(i);
                            break;
                        }
                    }

                    // busca a avaliação da categoria selecionada
                    avaliacaoCategoria = avaliacaoCategoriaFacade.buscaAvaliacaoByIdCategoriaDeRisco(categoriaRisco.getIdCategoriaDeRisco());

                    // se não possuir avaliação, desabilita campos de texto e permite criação da avaliação
                    if (avaliacaoCategoria == null) {
                        criarAvaliacaoCategoriaJButton.setEnabled(true);
                        salvarAlteracoesCategoriaJButton.setEnabled(false);
                        desabilitaCamposTextoCategoria();
                    } // se possuir, preenche os dados e permite edição da avaliação
                    else {
                        pontosFortesCategoriaJTextArea.setText(avaliacaoCategoria.getPontosFortes());
                        pontosFracosCategoriaJTextArea.setText(avaliacaoCategoria.getPontosFracos());
                        oportunidadesMelhoriaCategoriaJTextArea.setText(avaliacaoCategoria.getOportunidadesMelhoria());
                        informacoesAdicionaisCategoriaJTextArea.setText(avaliacaoCategoria.getInformacoesAdicionais());

                        criarAvaliacaoCategoriaJButton.setEnabled(false);
                        salvarAlteracoesCategoriaJButton.setEnabled(true);
                        reabilitarCamposTextoCategoria();
                    }
                }
            }
        });
    }

    /*
     private void definirEventosTabelaCategoriasAdicionadas() {
     categoriasAdicionadasJTable.addMouseListener(new MouseAdapter() {
     public void mousePressed(MouseEvent e) {
     if (e.getClickCount() == 1) {

     int selected = categoriasAdicionadasJTable.getSelectedRow();

     for (int i = 0; i < listaCategorias.size(); i++) {

     Avaliacaocategoria avaliacaoCategoria = null;

     if ((categoriasAdicionadasJTable.getValueAt(selected, 0).equals(listaCategorias.get(i).getNomeCategoria()))) {
     //idCategoriaSelecionadaEmListaCategorias = listaCategorias.get(i).getIdCategoriaDeRisco();

     for (int j = 0; i < listaAvaliacaoCategoria.size(); j++) {
     Categoriaderisco categoriaRisco = listaAvaliacaoCategoria.get(j).getIdCategoriaDeRisco();
     if (listaCategorias.get(i).getIdCategoriaDeRisco() == categoriaRisco.getIdCategoriaDeRisco()) {
     avaliacaoCategoria = listaAvaliacaoCategoria.get(j);
     }
     }

     if (avaliacaoCategoria != null) {
     System.out.println("Esta avaliado");
     criarAvaliacaoCategoriaJButton.setEnabled(false);
     salvarAlteracoesCategoriaJButton.setEnabled(true);
     reabilitarCamposTextoCategoria();
     pontosFortesCategoriaJTextArea.setText(avaliacaoCategoria.getPontosFortes());
     pontosFracosCategoriaJTextArea.setText(avaliacaoCategoria.getPontosFracos());
     oportunidadesMelhoriaCategoriaJTextArea.setText(avaliacaoCategoria.getOportunidadesMelhoria());
     informacoesAdicionaisCategoriaJTextArea.setText(avaliacaoCategoria.getInformacoesAdicionais());
     } else {
     System.out.println("Não esta avaliado");
     criarAvaliacaoCategoriaJButton.setEnabled(true);
     desabilitaCamposTextoCategoria();
     salvarAlteracoesCategoriaJButton.setEnabled(false);
     }
     }
     }
     }
     }
     });
     }
     */
    private void definirEventosTabelaProjeto() {
        portfolioTabelaJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selected = portfolioTabelaJTable.getSelectedRow(); // recupera a linha selecionada na tabela de Projetos

                    // procura o projeto selecionado
                    for (int i = 0; i < listaProjetos.size(); i++) {
                        if (portfolioTabelaJTable.getValueAt(selected, 0).equals(listaProjetos.get(i).getNomeProjeto())) {
                            projetoSelecionado = listaProjetos.get(i);
                            break;
                        }
                    }

                    // modela a tabela 'categoriasAdicionadas' conforme o projeto selecionado
                    // desabilita campos de texto e botões na aba 'Novas Categorias do Projeto'
                    modeloCategoriasAdicionadasJTable = null;
                    criarTabelaCategoriasAdicionadas();
                    preencherDadosTabelaCategoriasAdicionadas();
                    definirEventosTabelaCategoriasAdicionadas();
                    desabilitaCamposTextoCategoria();
                    criarAvaliacaoCategoriaJButton.setEnabled(false);
                    salvarAlteracoesCategoriaJButton.setEnabled(false);

                    // procura a avaliacao do projeto selecionado
                    Avaliacaoprojeto avaliacaoProjeto = new Avaliacaoprojeto();
                    avaliacaoProjeto = avaliacaoProjetoFacade.buscaAvaliacaoByIdProjeto(projetoSelecionado.getIdProjeto());

                    // Se o projeto não tiver avaliação
                    if (avaliacaoProjeto == null) {
                        // e, além disso, não estiver concluído
                        if (projetoSelecionado.getConcluido() == false) {
                            criarAvaliacaoProjetoJButton.setEnabled(false);
                            salvarAlteracoesProjetoJButton.setEnabled(false);
                            desabilitaCamposTextoProjeto();
                        } // não possui avaliação e está concluído
                        else {
                            criarAvaliacaoProjetoJButton.setEnabled(true);
                            salvarAlteracoesProjetoJButton.setEnabled(false);
                            desabilitaCamposTextoProjeto();
                        }
                    } // se ele tiver avaliacao
                    else {
                        // preenche informações da avaliação do projeto e permite edição
                        categoriasAdicionadasJTable.repaint();
                        pontosFortesProjetoJTextArea.setText(avaliacaoProjeto.getPontosFortes());
                        pontosFracosProjetoJTextArea.setText(avaliacaoProjeto.getPontosFracos());
                        oportunidadesMelhoriaProjetoJTextArea.setText(avaliacaoProjeto.getOportunidadesMelhoria());
                        informacoesAdicionaisProjetoJTextArea.setText(avaliacaoProjeto.getInformacoesAdicionais());
                        criarAvaliacaoProjetoJButton.setEnabled(false);
                        salvarAlteracoesProjetoJButton.setEnabled(true);
                        reabilitarCamposTextoProjeto();
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
        portfolioJLabel = new javax.swing.JLabel();
        painelComAbasJTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pontosFortesProjetoJPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pontosFortesProjetoJTextArea = new javax.swing.JTextArea();
        pontosFracosProjetoJPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pontosFracosProjetoJTextArea = new javax.swing.JTextArea();
        oportunidadesMelhoriaProjetoJPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        oportunidadesMelhoriaProjetoJTextArea = new javax.swing.JTextArea();
        informacoesAdicionaisProjetoJPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        informacoesAdicionaisProjetoJTextArea = new javax.swing.JTextArea();
        salvarAlteracoesProjetoJButton = new javax.swing.JButton();
        criarAvaliacaoProjetoJButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        pontosFortesCategoriaJPanel = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        pontosFortesCategoriaJTextArea = new javax.swing.JTextArea();
        pontosFracosCategoriaJPanel = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        pontosFracosCategoriaJTextArea = new javax.swing.JTextArea();
        oportunidadesMelhoriaCategoriaJPanel = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        oportunidadesMelhoriaCategoriaJTextArea = new javax.swing.JTextArea();
        informacoesAdicionaisCategoriaJPanel = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        informacoesAdicionaisCategoriaJTextArea = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        salvarAlteracoesCategoriaJButton = new javax.swing.JButton();
        criarAvaliacaoCategoriaJButton = new javax.swing.JButton();
        tabelaCategoriasAdicionadasJScrollPane = new javax.swing.JScrollPane();
        tabelaPortfolioJScrollPane = new javax.swing.JScrollPane();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        portfolioJLabel.setText("Portfólio");

        pontosFortesProjetoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos Fortes"));

        pontosFortesProjetoJTextArea.setColumns(20);
        pontosFortesProjetoJTextArea.setLineWrap(true);
        pontosFortesProjetoJTextArea.setRows(6);
        pontosFortesProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane3.setViewportView(pontosFortesProjetoJTextArea);

        javax.swing.GroupLayout pontosFortesProjetoJPanelLayout = new javax.swing.GroupLayout(pontosFortesProjetoJPanel);
        pontosFortesProjetoJPanel.setLayout(pontosFortesProjetoJPanelLayout);
        pontosFortesProjetoJPanelLayout.setHorizontalGroup(
            pontosFortesProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFortesProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        pontosFortesProjetoJPanelLayout.setVerticalGroup(
            pontosFortesProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFortesProjetoJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        pontosFracosProjetoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos Fracos"));

        pontosFracosProjetoJTextArea.setColumns(20);
        pontosFracosProjetoJTextArea.setLineWrap(true);
        pontosFracosProjetoJTextArea.setRows(6);
        pontosFracosProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane4.setViewportView(pontosFracosProjetoJTextArea);

        javax.swing.GroupLayout pontosFracosProjetoJPanelLayout = new javax.swing.GroupLayout(pontosFracosProjetoJPanel);
        pontosFracosProjetoJPanel.setLayout(pontosFracosProjetoJPanelLayout);
        pontosFracosProjetoJPanelLayout.setHorizontalGroup(
            pontosFracosProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFracosProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        pontosFracosProjetoJPanelLayout.setVerticalGroup(
            pontosFracosProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFracosProjetoJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        oportunidadesMelhoriaProjetoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Oportunidades de Melhoria"));

        oportunidadesMelhoriaProjetoJTextArea.setColumns(20);
        oportunidadesMelhoriaProjetoJTextArea.setLineWrap(true);
        oportunidadesMelhoriaProjetoJTextArea.setRows(6);
        oportunidadesMelhoriaProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane5.setViewportView(oportunidadesMelhoriaProjetoJTextArea);

        javax.swing.GroupLayout oportunidadesMelhoriaProjetoJPanelLayout = new javax.swing.GroupLayout(oportunidadesMelhoriaProjetoJPanel);
        oportunidadesMelhoriaProjetoJPanel.setLayout(oportunidadesMelhoriaProjetoJPanelLayout);
        oportunidadesMelhoriaProjetoJPanelLayout.setHorizontalGroup(
            oportunidadesMelhoriaProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oportunidadesMelhoriaProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        oportunidadesMelhoriaProjetoJPanelLayout.setVerticalGroup(
            oportunidadesMelhoriaProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oportunidadesMelhoriaProjetoJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        informacoesAdicionaisProjetoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais"));

        informacoesAdicionaisProjetoJTextArea.setColumns(20);
        informacoesAdicionaisProjetoJTextArea.setLineWrap(true);
        informacoesAdicionaisProjetoJTextArea.setRows(6);
        informacoesAdicionaisProjetoJTextArea.setWrapStyleWord(true);
        jScrollPane6.setViewportView(informacoesAdicionaisProjetoJTextArea);

        javax.swing.GroupLayout informacoesAdicionaisProjetoJPanelLayout = new javax.swing.GroupLayout(informacoesAdicionaisProjetoJPanel);
        informacoesAdicionaisProjetoJPanel.setLayout(informacoesAdicionaisProjetoJPanelLayout);
        informacoesAdicionaisProjetoJPanelLayout.setHorizontalGroup(
            informacoesAdicionaisProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacoesAdicionaisProjetoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        informacoesAdicionaisProjetoJPanelLayout.setVerticalGroup(
            informacoesAdicionaisProjetoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacoesAdicionaisProjetoJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        salvarAlteracoesProjetoJButton.setText("Salvar Alterações");
        salvarAlteracoesProjetoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarAlteracoesProjetoJButtonActionPerformed(evt);
            }
        });

        criarAvaliacaoProjetoJButton.setText("Criar Avaliação");
        criarAvaliacaoProjetoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarAvaliacaoProjetoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(informacoesAdicionaisProjetoJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oportunidadesMelhoriaProjetoJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pontosFracosProjetoJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(pontosFortesProjetoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(criarAvaliacaoProjetoJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salvarAlteracoesProjetoJButton)
                                .addContainerGap())))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {oportunidadesMelhoriaProjetoJPanel, pontosFortesProjetoJPanel, pontosFracosProjetoJPanel});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pontosFortesProjetoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pontosFracosProjetoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oportunidadesMelhoriaProjetoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(informacoesAdicionaisProjetoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarAlteracoesProjetoJButton)
                    .addComponent(criarAvaliacaoProjetoJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {oportunidadesMelhoriaProjetoJPanel, pontosFortesProjetoJPanel, pontosFracosProjetoJPanel});

        painelComAbasJTabbedPane.addTab("Avaliação do Projeto", jPanel2);

        pontosFortesCategoriaJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos Fortes"));

        pontosFortesCategoriaJTextArea.setColumns(20);
        pontosFortesCategoriaJTextArea.setLineWrap(true);
        pontosFortesCategoriaJTextArea.setRows(4);
        pontosFortesCategoriaJTextArea.setWrapStyleWord(true);
        jScrollPane12.setViewportView(pontosFortesCategoriaJTextArea);

        javax.swing.GroupLayout pontosFortesCategoriaJPanelLayout = new javax.swing.GroupLayout(pontosFortesCategoriaJPanel);
        pontosFortesCategoriaJPanel.setLayout(pontosFortesCategoriaJPanelLayout);
        pontosFortesCategoriaJPanelLayout.setHorizontalGroup(
            pontosFortesCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFortesCategoriaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        pontosFortesCategoriaJPanelLayout.setVerticalGroup(
            pontosFortesCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFortesCategoriaJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        pontosFracosCategoriaJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pontos Fracos"));

        pontosFracosCategoriaJTextArea.setColumns(20);
        pontosFracosCategoriaJTextArea.setLineWrap(true);
        pontosFracosCategoriaJTextArea.setRows(4);
        pontosFracosCategoriaJTextArea.setWrapStyleWord(true);
        jScrollPane13.setViewportView(pontosFracosCategoriaJTextArea);

        javax.swing.GroupLayout pontosFracosCategoriaJPanelLayout = new javax.swing.GroupLayout(pontosFracosCategoriaJPanel);
        pontosFracosCategoriaJPanel.setLayout(pontosFracosCategoriaJPanelLayout);
        pontosFracosCategoriaJPanelLayout.setHorizontalGroup(
            pontosFracosCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFracosCategoriaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        pontosFracosCategoriaJPanelLayout.setVerticalGroup(
            pontosFracosCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontosFracosCategoriaJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        oportunidadesMelhoriaCategoriaJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Oportunidades de Melhoria"));

        oportunidadesMelhoriaCategoriaJTextArea.setColumns(20);
        oportunidadesMelhoriaCategoriaJTextArea.setLineWrap(true);
        oportunidadesMelhoriaCategoriaJTextArea.setRows(4);
        oportunidadesMelhoriaCategoriaJTextArea.setWrapStyleWord(true);
        jScrollPane14.setViewportView(oportunidadesMelhoriaCategoriaJTextArea);

        javax.swing.GroupLayout oportunidadesMelhoriaCategoriaJPanelLayout = new javax.swing.GroupLayout(oportunidadesMelhoriaCategoriaJPanel);
        oportunidadesMelhoriaCategoriaJPanel.setLayout(oportunidadesMelhoriaCategoriaJPanelLayout);
        oportunidadesMelhoriaCategoriaJPanelLayout.setHorizontalGroup(
            oportunidadesMelhoriaCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, oportunidadesMelhoriaCategoriaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        oportunidadesMelhoriaCategoriaJPanelLayout.setVerticalGroup(
            oportunidadesMelhoriaCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oportunidadesMelhoriaCategoriaJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        informacoesAdicionaisCategoriaJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais"));

        informacoesAdicionaisCategoriaJTextArea.setColumns(20);
        informacoesAdicionaisCategoriaJTextArea.setLineWrap(true);
        informacoesAdicionaisCategoriaJTextArea.setRows(4);
        informacoesAdicionaisCategoriaJTextArea.setWrapStyleWord(true);
        jScrollPane15.setViewportView(informacoesAdicionaisCategoriaJTextArea);

        javax.swing.GroupLayout informacoesAdicionaisCategoriaJPanelLayout = new javax.swing.GroupLayout(informacoesAdicionaisCategoriaJPanel);
        informacoesAdicionaisCategoriaJPanel.setLayout(informacoesAdicionaisCategoriaJPanelLayout);
        informacoesAdicionaisCategoriaJPanelLayout.setHorizontalGroup(
            informacoesAdicionaisCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacoesAdicionaisCategoriaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        informacoesAdicionaisCategoriaJPanelLayout.setVerticalGroup(
            informacoesAdicionaisCategoriaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacoesAdicionaisCategoriaJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        salvarAlteracoesCategoriaJButton.setText("Salvar Alterações");
        salvarAlteracoesCategoriaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarAlteracoesCategoriaJButtonActionPerformed(evt);
            }
        });

        criarAvaliacaoCategoriaJButton.setText("Criar Avaliação");
        criarAvaliacaoCategoriaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarAvaliacaoCategoriaJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(criarAvaliacaoCategoriaJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salvarAlteracoesCategoriaJButton)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarAlteracoesCategoriaJButton)
                    .addComponent(criarAvaliacaoCategoriaJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelaCategoriasAdicionadasJScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pontosFortesCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pontosFracosCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesAdicionaisCategoriaJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(oportunidadesMelhoriaCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(tabelaCategoriasAdicionadasJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pontosFortesCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pontosFracosCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oportunidadesMelhoriaCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(informacoesAdicionaisCategoriaJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        painelComAbasJTabbedPane.addTab("Avaliação das novas Categorias do Projeto", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tabelaPortfolioJScrollPane)
                    .addComponent(painelComAbasJTabbedPane)
                    .addComponent(portfolioJLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(portfolioJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaPortfolioJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelComAbasJTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void salvarAlteracoesProjetoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarAlteracoesProjetoJButtonActionPerformed
        // verifica se é um id válido
        if (projetoSelecionado.getIdProjeto() > 0) {
            // busca avaliação do projeto selecionado
            Avaliacaoprojeto avaliacaoProjeto = avaliacaoProjetoFacade.buscaAvaliacao(projetoSelecionado);

            // preenche com novos dados
            avaliacaoProjeto.setPontosFortes(pontosFortesProjetoJTextArea.getText());
            avaliacaoProjeto.setPontosFracos(pontosFracosProjetoJTextArea.getText());
            avaliacaoProjeto.setOportunidadesMelhoria(oportunidadesMelhoriaProjetoJTextArea.getText());
            avaliacaoProjeto.setInformacoesAdicionais(informacoesAdicionaisProjetoJTextArea.getText());

            // realiza o upload no Banco de Dados
            avaliacaoProjetoFacade.alterarAvaliacao(avaliacaoProjeto);
            JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso na avaliação do projeto: '" + projetoSelecionado.getNomeProjeto() + "'.");
        }
    }//GEN-LAST:event_salvarAlteracoesProjetoJButtonActionPerformed

    private void criarAvaliacaoCategoriaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarAvaliacaoCategoriaJButtonActionPerformed
        // verifica se é um id válido
        if (categoriaRisco.getIdCategoriaDeRisco() > 0) {
            int linhaSelecionada = categoriasAdicionadasJTable.getSelectedRow();
            Avaliacaocategoria avaliacaoCategoria = new Avaliacaocategoria();

            // cria uma nova avaliação da categoria com valores vazios
            avaliacaoCategoria.setIdCategoriaDeRisco(categoriaRisco);
            avaliacaoCategoria.setPontosFortes(" ");
            avaliacaoCategoria.setPontosFracos(" ");
            avaliacaoCategoria.setOportunidadesMelhoria(" ");
            avaliacaoCategoria.setInformacoesAdicionais(" ");

            // realiza o upload no Banco e atualiza a lista de categorias
            avaliacaoCategoriaFacade.criarAvaliacao(avaliacaoCategoria);
            getListaCategorias();

            // limpa a seleção e desabilita os botões
            //categoriasAdicionadasJTable.clearSelection();
            //criarAvaliacaoCategoriaJButton.setEnabled(false);
            //salvarAlteracoesCategoriaJButton.setEnabled(false);
            // busca a avaliação da categoria selecionada
            avaliacaoCategoria = avaliacaoCategoriaFacade.buscaAvaliacaoByIdCategoriaDeRisco(categoriaRisco.getIdCategoriaDeRisco());

            // se não possuir avaliação, desabilita campos de texto e permite criação da avaliação
            if (avaliacaoCategoria == null) {
                criarAvaliacaoCategoriaJButton.setEnabled(true);
                salvarAlteracoesCategoriaJButton.setEnabled(false);
                desabilitaCamposTextoCategoria();
            } // se possuir, preenche os dados e permite edição da avaliação
            else {
                pontosFortesCategoriaJTextArea.setText(avaliacaoCategoria.getPontosFortes());
                pontosFracosCategoriaJTextArea.setText(avaliacaoCategoria.getPontosFracos());
                oportunidadesMelhoriaCategoriaJTextArea.setText(avaliacaoCategoria.getOportunidadesMelhoria());
                informacoesAdicionaisCategoriaJTextArea.setText(avaliacaoCategoria.getInformacoesAdicionais());

                criarAvaliacaoCategoriaJButton.setEnabled(false);
                salvarAlteracoesCategoriaJButton.setEnabled(true);
                reabilitarCamposTextoCategoria();
            }
            JOptionPane.showMessageDialog(null, "Avaliacao da categoria '" + categoriaRisco.getNomeCategoria() + "' foi criada com sucesso.");
            categoriasAdicionadasJTable.setRowSelectionInterval(linhaSelecionada, linhaSelecionada);
        }

    }//GEN-LAST:event_criarAvaliacaoCategoriaJButtonActionPerformed

    private void criarAvaliacaoProjetoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarAvaliacaoProjetoJButtonActionPerformed
        // verifica se é um id válido
        if (projetoSelecionado.getIdProjeto() > 0) {
            
            int linhaSelecionada = portfolioTabelaJTable.getSelectedRow();
            
            Avaliacaoprojeto avaliacaoProjeto = new Avaliacaoprojeto();

            //cria uma avaliação do projeto com valores vazios
            avaliacaoProjeto.setIdProjeto(projetoSelecionado);
            avaliacaoProjeto.setPontosFortes(" ");
            avaliacaoProjeto.setPontosFracos(" ");
            avaliacaoProjeto.setOportunidadesMelhoria(" ");
            avaliacaoProjeto.setInformacoesAdicionais(" ");

            // realiza o upload no banco e atualiza a lista e tabela de projetos
            avaliacaoProjetoFacade.criarAvaliacao(avaliacaoProjeto);
            getListaProjetos();
            getListaCategorias();
            getListaContem();
            modeloPortfoliTabelaJTable = null;
            criarTabelaProjetos();
            preencherDadosTabelaProjeto();
            definirEventosTabelaProjeto();
            portfolioTabelaJTable.addRowSelectionInterval(linhaSelecionada, linhaSelecionada);

            // procura a avaliacao do projeto selecionado
            Avaliacaoprojeto avaliacaoProjetoNovo = new Avaliacaoprojeto();
            avaliacaoProjetoNovo = avaliacaoProjetoFacade.buscaAvaliacaoByIdProjeto(projetoSelecionado.getIdProjeto());

            // Se o projeto não tiver avaliação
            if (avaliacaoProjetoNovo == null) {
                // e, além disso, não estiver concluído
                if (projetoSelecionado.getConcluido() == false) {
                    criarAvaliacaoProjetoJButton.setEnabled(false);
                    salvarAlteracoesProjetoJButton.setEnabled(false);
                    desabilitaCamposTextoProjeto();
                } // não possui avaliação e está concluído
                else {
                    criarAvaliacaoProjetoJButton.setEnabled(true);
                    salvarAlteracoesProjetoJButton.setEnabled(false);
                    desabilitaCamposTextoProjeto();
                }
            } // se ele tiver avaliacao
            else {
                // preenche informações da avaliação do projeto e permite edição
                categoriasAdicionadasJTable.repaint();
                pontosFortesProjetoJTextArea.setText(avaliacaoProjetoNovo.getPontosFortes());
                pontosFracosProjetoJTextArea.setText(avaliacaoProjetoNovo.getPontosFracos());
                oportunidadesMelhoriaProjetoJTextArea.setText(avaliacaoProjetoNovo.getOportunidadesMelhoria());
                informacoesAdicionaisProjetoJTextArea.setText(avaliacaoProjetoNovo.getInformacoesAdicionais());
                criarAvaliacaoProjetoJButton.setEnabled(false);
                salvarAlteracoesProjetoJButton.setEnabled(true);
                reabilitarCamposTextoProjeto();
            }

            JOptionPane.showMessageDialog(null, "Avaliação criada para o projeto: '" + projetoSelecionado.getNomeProjeto() + "'.");
        }
    }//GEN-LAST:event_criarAvaliacaoProjetoJButtonActionPerformed

    private void salvarAlteracoesCategoriaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarAlteracoesCategoriaJButtonActionPerformed
        // verifica se é um id válido
        if (categoriaRisco.getIdCategoriaDeRisco() > 0) {
            // busca avaliação da categoria selecionada
            Avaliacaocategoria avaliacaoCategoria = avaliacaoCategoriaFacade.buscaAvaliacaoByIdCategoriaDeRisco(categoriaRisco.getIdCategoriaDeRisco());

            //preenche com novos dados
            avaliacaoCategoria.setPontosFortes(pontosFortesCategoriaJTextArea.getText());
            avaliacaoCategoria.setPontosFracos(pontosFracosCategoriaJTextArea.getText());
            avaliacaoCategoria.setOportunidadesMelhoria(oportunidadesMelhoriaCategoriaJTextArea.getText());
            avaliacaoCategoria.setInformacoesAdicionais(informacoesAdicionaisCategoriaJTextArea.getText());

            //realiza o upload no banco
            avaliacaoCategoriaFacade.alterarAvaliacao(avaliacaoCategoria);
            JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso na avaliação da categoria: '" + categoriaRisco.getNomeCategoria() + "'.");
        }
    }//GEN-LAST:event_salvarAlteracoesCategoriaJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton criarAvaliacaoCategoriaJButton;
    private javax.swing.JButton criarAvaliacaoProjetoJButton;
    private javax.swing.JPanel informacoesAdicionaisCategoriaJPanel;
    private javax.swing.JTextArea informacoesAdicionaisCategoriaJTextArea;
    private javax.swing.JPanel informacoesAdicionaisProjetoJPanel;
    private javax.swing.JTextArea informacoesAdicionaisProjetoJTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel oportunidadesMelhoriaCategoriaJPanel;
    private javax.swing.JTextArea oportunidadesMelhoriaCategoriaJTextArea;
    private javax.swing.JPanel oportunidadesMelhoriaProjetoJPanel;
    private javax.swing.JTextArea oportunidadesMelhoriaProjetoJTextArea;
    private javax.swing.JTabbedPane painelComAbasJTabbedPane;
    private javax.swing.JPanel pontosFortesCategoriaJPanel;
    private javax.swing.JTextArea pontosFortesCategoriaJTextArea;
    private javax.swing.JPanel pontosFortesProjetoJPanel;
    private javax.swing.JTextArea pontosFortesProjetoJTextArea;
    private javax.swing.JPanel pontosFracosCategoriaJPanel;
    private javax.swing.JTextArea pontosFracosCategoriaJTextArea;
    private javax.swing.JPanel pontosFracosProjetoJPanel;
    private javax.swing.JTextArea pontosFracosProjetoJTextArea;
    private javax.swing.JLabel portfolioJLabel;
    private javax.swing.JButton salvarAlteracoesCategoriaJButton;
    private javax.swing.JButton salvarAlteracoesProjetoJButton;
    private javax.swing.JScrollPane tabelaCategoriasAdicionadasJScrollPane;
    private javax.swing.JScrollPane tabelaPortfolioJScrollPane;
    // End of variables declaration//GEN-END:variables
}
