/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Risco.RiscosRiscosOcorridosJPanel;
import view.Risco.RiscosResumoDeRiscosJPanel;
import view.Risco.RiscosPriorizarRiscosJPanel;
import view.Risco.RiscosGerenciarRiscosJPanel;
import view.Monitoramento.MonitoracaoAnaliseDosRiscosJPanel;
import view.Monitoramento.MonitoracaoTarefasPlanosPendentesJPanel;
import view.Monitoramento.MonitoracaoTabelaDeAlteracaoJPanel;
import view.Monitoramento.MonitoracaoResumoDeMonitoracao;
import view.Monitoramento.MonitoracaoTarefasPlanosRealizadosJPanel;
import view.Projeto.ProjetoCalendarioJPanel;
import view.Projeto.ProjetoPlanoRiscoJPanel;
import view.Projeto.ProjetoEstruturaAnaliticaRiscosJpanel;
import view.Projeto.ProjetoDetalhesJPanel;
import view.Organizacional.OrganizacionalPortfolioJPanel;
import view.Organizacional.OrganizacionalEditarEARJPanel;
import view.Organizacional.OrganizacionalEARJPanel;
import view.Organizacional.OrganizacionalDetalhesJPanel;
import view.Organizacional.OrganizacionalPoliticaOrganizacionalJPanel;
import configuracao.ConfiguracaoBancoDeDados;
import facade.MonitoracaoAnaliseDosRiscosFacade;
import facade.PrincipalFacade;
import facade.ProjetoCalendarioFacade;
import facade.ProjetoFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;
import model.Risco;

/**
 *
 * @author Mariano
 */
public class PrincipalJFrame extends javax.swing.JFrame {

    PrincipalFacade principalFacade = new PrincipalFacade();

    private Projeto projetoSelecionado;

    private List<Projeto> listaProjetos = principalFacade.listarProjetos();

    // Criando Objetos dos Frames //
    final OrganizacionalDetalhesJPanel organizacionalDetalhesJPanel = new OrganizacionalDetalhesJPanel();
    final OrganizacionalEARJPanel organizacionalEARJPanel = new OrganizacionalEARJPanel();
    final OrganizacionalPoliticaOrganizacionalJPanel organizacionalPoliticaOrganizacionalJPanel = new OrganizacionalPoliticaOrganizacionalJPanel();
    final OrganizacionalPortfolioJPanel organizacionalPortfolioJPanel = new OrganizacionalPortfolioJPanel();
    final ProjetoDetalhesJPanel projetoDetalhesJPanel = new ProjetoDetalhesJPanel();
    final ProjetoPlanoRiscoJPanel projetoPlanoRiscoJpanel = new ProjetoPlanoRiscoJPanel();
    final ProjetoEstruturaAnaliticaRiscosJpanel projetoEstruturaAnaliticaRiscosJpanel = new ProjetoEstruturaAnaliticaRiscosJpanel();
    final ProjetoCalendarioJPanel projetoCalendarioJPanel = new ProjetoCalendarioJPanel();
    final ConfiguracoesFerramentaJPanel configuracoesFerramentaJPanel = new ConfiguracoesFerramentaJPanel();
    final RiscosGerenciarRiscosJPanel riscosGerenciarRiscosJPanel = new RiscosGerenciarRiscosJPanel();
    final NovoProjetoJPanel novoProjetoJPanel = new NovoProjetoJPanel();
    final OrganizacionalEditarEARJPanel organizacionalEditarEARJPanel = new OrganizacionalEditarEARJPanel();
    final RiscosPriorizarRiscosJPanel riscosPriorizarRiscosJPanel = new RiscosPriorizarRiscosJPanel();
    final RiscosRiscosOcorridosJPanel riscosRiscosOcorridosJPanel = new RiscosRiscosOcorridosJPanel();
    final RiscosResumoDeRiscosJPanel riscosResumoDeRiscosJPanel = new RiscosResumoDeRiscosJPanel();
    final MonitoracaoResumoDeMonitoracao monitoracaoResumoDeMonitoracao = new MonitoracaoResumoDeMonitoracao();
    final HomeSpider_Jpanel homeSpider_Jpanel = new HomeSpider_Jpanel();

    final MonitoracaoAnaliseDosRiscosJPanel monitoracaoAnaliseDosRiscosJPanel = new MonitoracaoAnaliseDosRiscosJPanel();

    final MonitoracaoTabelaDeAlteracaoJPanel monitoracoaTabelaDeAlteracaoJPanel = new MonitoracaoTabelaDeAlteracaoJPanel();
    final MonitoracaoTarefasPlanosPendentesJPanel monitoracaoPlanosPendentesJPanel = new MonitoracaoTarefasPlanosPendentesJPanel();
    final MonitoracaoTarefasPlanosRealizadosJPanel monitoracaoPlanosRealizadosJPanel = new MonitoracaoTarefasPlanosRealizadosJPanel();

    // Criando Arvore de Funcionalidades - Menu //
    private JTree arvoreFuncionalidadesJTree;
    private DefaultMutableTreeNode funcionalidades;
    private DefaultMutableTreeNode organizacional;
    private DefaultMutableTreeNode projeto;
    private DefaultMutableTreeNode politicaOrganizacional;
    private DefaultMutableTreeNode estruturaAnaliticaRiscos;
    private DefaultMutableTreeNode portfolio;
    private DefaultMutableTreeNode planoDeRisco;
    private DefaultMutableTreeNode earProjeto;
    private DefaultMutableTreeNode calendario;
    private DefaultMutableTreeNode riscos;
    private DefaultMutableTreeNode gerenciarRiscos;
    private DefaultMutableTreeNode priorizarRiscos;
    private DefaultMutableTreeNode riscosOcorridos;
    private DefaultMutableTreeNode monitoracao;
    private DefaultMutableTreeNode analiseRiscos;
    private DefaultMutableTreeNode tabelaAlteracoes;
    private DefaultMutableTreeNode tarefas;
    private DefaultMutableTreeNode planosPendentes;
    private DefaultMutableTreeNode planosRealizados;
    private DefaultMutableTreeNode resumoDeRiscos;

    //*****************************************//
    /**
     * Creates new form FramePrincipal
     */
    ConfiguracaoBancoDeDados configuracaoBancoDeDados = new ConfiguracaoBancoDeDados(null, true);

    public PrincipalJFrame() {
        initComponents();
        adicionarFrames();
        esconderFrames();
        criarArvore();
        definirEventosArvore();
        definirEventoRedimensionamento();
        //Desabilita o botão maximizar da tela Principal
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        homeSpider_Jpanel.setVisible(true);

        popularComboBox();
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/img/spiderES9.png")).getImage());
    }

    public void definirEventoRedimensionamento() {
        this.addComponentListener(new java.awt.event.ComponentListener() {
            public void componentResized(java.awt.event.ComponentEvent e) {

                organizacionalDetalhesJPanel.setBounds(camadasJDesktopPane.getBounds());

                organizacionalEARJPanel.setBounds(camadasJDesktopPane.getBounds());

                organizacionalPoliticaOrganizacionalJPanel.setBounds(camadasJDesktopPane.getBounds());

                organizacionalPortfolioJPanel.setBounds(camadasJDesktopPane.getBounds());

                projetoDetalhesJPanel.setBounds(camadasJDesktopPane.getBounds());

                projetoPlanoRiscoJpanel.setBounds(camadasJDesktopPane.getBounds());

                projetoEstruturaAnaliticaRiscosJpanel.setBounds(camadasJDesktopPane.getBounds());

                projetoCalendarioJPanel.setBounds(camadasJDesktopPane.getBounds());

                configuracoesFerramentaJPanel.setBounds(camadasJDesktopPane.getBounds());

                riscosResumoDeRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());

                monitoracaoResumoDeMonitoracao.setBounds(camadasJDesktopPane.getBounds());

                riscosGerenciarRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());

                novoProjetoJPanel.setBounds(camadasJDesktopPane.getBounds());

                organizacionalEditarEARJPanel.setBounds(camadasJDesktopPane.getBounds());

                riscosPriorizarRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());

                riscosRiscosOcorridosJPanel.setBounds(camadasJDesktopPane.getBounds());

                monitoracaoAnaliseDosRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());

                monitoracoaTabelaDeAlteracaoJPanel.setBounds(camadasJDesktopPane.getBounds());

                monitoracaoPlanosPendentesJPanel.setBounds(camadasJDesktopPane.getBounds());

                monitoracaoPlanosRealizadosJPanel.setBounds(camadasJDesktopPane.getBounds());

                homeSpider_Jpanel.setBounds(camadasJDesktopPane.getBounds());

                System.out.println("componentResized()"); // TODO Auto-generated Event stub componentResized()  
            }

            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {
            }

            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
            }

            @Override
            public void componentHidden(java.awt.event.ComponentEvent e) {
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        configuracoesFerramentaJButton = new javax.swing.JButton();
        projetoSelecionadoJComboBox = new javax.swing.JComboBox();
        projetoSelecionadoJLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        editarEARJButton = new javax.swing.JButton();
        jLabel_Logo = new javax.swing.JLabel();
        menuJPanel = new javax.swing.JPanel();
        arvoreFuncionalidadesJScrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        camadasJDesktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPIDER - Risk Management");
        setIconImages(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        configuracoesFerramentaJButton.setText("Configurações da Ferramenta");
        configuracoesFerramentaJButton.setToolTipText("Configurações da Ferramenta");
        configuracoesFerramentaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracoesFerramentaJButtonActionPerformed(evt);
            }
        });

        projetoSelecionadoJComboBox.setToolTipText("");
        projetoSelecionadoJComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projetoSelecionadoJComboBoxMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                projetoSelecionadoJComboBoxMousePressed(evt);
            }
        });
        projetoSelecionadoJComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                projetoSelecionadoJComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });
        projetoSelecionadoJComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                projetoSelecionadoJComboBoxItemStateChanged(evt);
            }
        });
        projetoSelecionadoJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projetoSelecionadoJComboBoxActionPerformed(evt);
            }
        });
        projetoSelecionadoJComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                projetoSelecionadoJComboBoxFocusGained(evt);
            }
        });

        projetoSelecionadoJLabel.setText("Projeto:");

        jButton1.setText("Novo Projeto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        editarEARJButton.setText("Editar EAR");
        editarEARJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarEARJButtonActionPerformed(evt);
            }
        });

        jLabel_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/spiderES7.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configuracoesFerramentaJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarEARJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Logo)
                .addGap(127, 127, 127)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projetoSelecionadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projetoSelecionadoJLabel))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editarEARJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(configuracoesFerramentaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(projetoSelecionadoJLabel)
                        .addGap(2, 2, 2)
                        .addComponent(projetoSelecionadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_Logo))
                .addContainerGap())
        );

        menuJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout menuJPanelLayout = new javax.swing.GroupLayout(menuJPanel);
        menuJPanel.setLayout(menuJPanelLayout);
        menuJPanelLayout.setHorizontalGroup(
            menuJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arvoreFuncionalidadesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
        );
        menuJPanelLayout.setVerticalGroup(
            menuJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arvoreFuncionalidadesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );

        jPanel2.setAutoscrolls(true);
        jPanel2.setPreferredSize(new java.awt.Dimension(861, 513));

        camadasJDesktopPane.setRequestFocusEnabled(false);

        javax.swing.GroupLayout camadasJDesktopPaneLayout = new javax.swing.GroupLayout(camadasJDesktopPane);
        camadasJDesktopPane.setLayout(camadasJDesktopPaneLayout);
        camadasJDesktopPaneLayout.setHorizontalGroup(
            camadasJDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );
        camadasJDesktopPaneLayout.setVerticalGroup(
            camadasJDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(camadasJDesktopPane)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(camadasJDesktopPane)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(menuJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void configuracoesFerramentaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracoesFerramentaJButtonActionPerformed
        esconderFrames();
        configuracoesFerramentaJPanel.setVisible(true);
        arvoreFuncionalidadesJTree.clearSelection();
    }//GEN-LAST:event_configuracoesFerramentaJButtonActionPerformed

    private void projetoSelecionadoJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projetoSelecionadoJComboBoxActionPerformed
        if (projetoSelecionadoJComboBox.getSelectedItem() != null) {
            if (projetoSelecionadoJComboBox.getSelectedItem() != null) {
                for (int j = 0; j < listaProjetos.size(); j++) {
                    if (listaProjetos.get(j).getNomeProjeto().equals(projetoSelecionadoJComboBox.getSelectedItem())) {
                        //Caso projeto tenha sido concluído
                        if (listaProjetos.get(j).getConcluido()) {
                            projetoSelecionadoJLabel.setText("Projeto [Concluído]:");
                        } else {
                            projetoSelecionadoJLabel.setText("Projeto:");
                        }

                        projetoSelecionado = listaProjetos.get(j);

                        projetoDetalhesJPanel.preencheDetalhes(projetoSelecionado);

                        projetoPlanoRiscoJpanel.preenchePlanoRisco(projetoSelecionado);

                        projetoEstruturaAnaliticaRiscosJpanel.criarArvore();
                        projetoEstruturaAnaliticaRiscosJpanel.popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional(projetoSelecionado.getIdProjeto());

                        configuracoesFerramentaJPanel.setProjetoSelecionado(projetoSelecionado.getIdProjeto());
                        configuracoesFerramentaJPanel.preencherCaminhoPlanoRisco(projetoSelecionado.getIdProjeto());

                        //Na tela Resumo de Riscos
                        RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
                        List<Risco> listaDeRiscoPorProjeto = riscosGerenciarRiscosFacade.listarRiscosByProjeto(projetoSelecionado);
                        riscosResumoDeRiscosJPanel.criaTabelResumoDeRiscos();
                        riscosResumoDeRiscosJPanel.preencherDadosTabelaResumoDeRiscos(listaDeRiscoPorProjeto);

                        //Na tela Resumo de Monitoração
                        ProjetoCalendarioFacade projetoCalendarioFacade = new ProjetoCalendarioFacade();
                        ProjetoFacade projetoFacade = new ProjetoFacade();
                        List<Marcodoprojeto> listaDeMarcosDoProjetoPorProjetoParaResumoDeMonitoracao = projetoCalendarioFacade.getListaMarcosDoProjetoSelecionado(projetoSelecionado);
                        List<Pontodecontrole> listaDePontosDeControlePorProjetoParaResumoDeMonitoracao = projetoCalendarioFacade.getListaPontosDeControleDoProjetoSelecionado(projetoSelecionado);
                        List<Risco> listaDeRiscoPorProjetoParaResumoDeMonitoracao = riscosGerenciarRiscosFacade.listarRiscosByProjeto(projetoSelecionado);
                        List<Planocontingencia> listaPlanoContingenciaPorProjetoParaResumoDeMonitoracao = projetoFacade.buscaPlanosDeContingenciaPendentes(projetoSelecionado);
                        List<Planomitigacao> listaPlanoMitigacaoContingenciaPorProjetoParaResumoDeMonitoracao = projetoFacade.buscaPlanosDeMitigacaoPendentes(projetoSelecionado);

                        monitoracaoResumoDeMonitoracao.criaTabelResumoDeStatusDeRiscos();
                        monitoracaoResumoDeMonitoracao.preencherDadosTabelResumoDeStatusDeRiscos(listaDeRiscoPorProjetoParaResumoDeMonitoracao);

                        monitoracaoResumoDeMonitoracao.criaTabelResumoDePlanosPendentes();
                        monitoracaoResumoDeMonitoracao.criaListaDePlanosTabela(listaPlanoContingenciaPorProjetoParaResumoDeMonitoracao, listaPlanoMitigacaoContingenciaPorProjetoParaResumoDeMonitoracao);

                        monitoracaoResumoDeMonitoracao.criaTabelResumoDeMarcosEPontosDeControle();
                        monitoracaoResumoDeMonitoracao.preencheTabelResumoDeMarcosEPontoDeControle(listaDeMarcosDoProjetoPorProjetoParaResumoDeMonitoracao, listaDePontosDeControlePorProjetoParaResumoDeMonitoracao);

                        // Na tela Gerenciar Riscos
                        riscosGerenciarRiscosJPanel.limpaCamposGerenciarRisco();
                        //Na aba Informações Gerais
                        riscosGerenciarRiscosJPanel.setProjetoSelecionado(projetoSelecionado);
                        riscosGerenciarRiscosJPanel.limparCampos();
                        riscosGerenciarRiscosJPanel.reiniciarTabelaRiscos();
                        riscosGerenciarRiscosJPanel.preencheComboBox();
                        riscosGerenciarRiscosJPanel.iniciaBotosRiscoCinza();
                        //Na aba Plano de Mitigacao
                        riscosGerenciarRiscosJPanel.iniciaBotoesCinzaPlanoMitigacao();
                        riscosGerenciarRiscosJPanel.limparListaPlanoMitigacao();
                        riscosGerenciarRiscosJPanel.limparCamposPlanoMitigacao();
                        riscosGerenciarRiscosJPanel.getListaMarcosProjeto(projetoSelecionado);
                        riscosGerenciarRiscosJPanel.getListaPontosControle(projetoSelecionado);
                        //Na aba Plano de Contingencia
                        riscosGerenciarRiscosJPanel.iniciaBotoesCinzaPlanoContingencia();
                        riscosGerenciarRiscosJPanel.limparListaPlanoContingencia();
                        riscosGerenciarRiscosJPanel.limparCamposPlanoContingencia();
                        //Na aba Relações
                        riscosGerenciarRiscosJPanel.limparListaRiscosInfluencia();
                        riscosGerenciarRiscosJPanel.limparListaRiscosInfluenciados();
                        // Na tela Calendario
                        projetoCalendarioJPanel.getProjetoSelecionado(projetoSelecionado);
                        projetoCalendarioJPanel.criarTabelasMarcoPontoDeControle();
                        projetoCalendarioJPanel.popularTabelaMarcoPontoDeControle();

                        // Na tela Priorizar Riscos
                        riscosPriorizarRiscosJPanel.getProjetoSelecionado(projetoSelecionado);
                        riscosPriorizarRiscosJPanel.criaTabela();
                        RiscosGerenciarRiscosFacade rGRfacade = new RiscosGerenciarRiscosFacade();
                        List<Risco> listaDeRisco = rGRfacade.listarRiscosPOrdemGrauDeEsposicao(projetoSelecionado);
                        riscosPriorizarRiscosJPanel.populaTabelaDeRiscos(listaDeRisco, false);
                        riscosPriorizarRiscosJPanel.definirEventosTabelaPriorizarRiscos();

                        // Na tela Riscos Ocorridos
                        riscosRiscosOcorridosJPanel.criarTabelaRiscosOcorridos();
                        riscosRiscosOcorridosJPanel.getProjeto(projetoSelecionado);
                        riscosRiscosOcorridosJPanel.popularTabelaRiscosOcorridos();

                        // Na tela Planos Pendentes
                        monitoracaoPlanosPendentesJPanel.criarTabelaPlanosPendentes(projetoFacade.buscaPlanosDeContingenciaPendentes(projetoSelecionado), projetoFacade.buscaPlanosDeMitigacaoPendentes(projetoSelecionado));
                        monitoracaoPlanosPendentesJPanel.setProjetoSelecionado(projetoSelecionado);

                        // Na tela Planos Realizados
                        monitoracaoPlanosRealizadosJPanel.criarTabelaPlanosRealizados(projetoFacade.buscaPlanosDeContingenciaRealizados(projetoSelecionado), projetoFacade.buscaPlanosDeMitigacaoRealizados(projetoSelecionado));

                        // Na tela Analizar Riscos
                        monitoracaoAnaliseDosRiscosJPanel.criarTabelaAnalisarRiscos();
                        MonitoracaoAnaliseDosRiscosFacade analiseFacade = new MonitoracaoAnaliseDosRiscosFacade();
                        List<Risco> listaDeRiscoAnalizarRiscos = analiseFacade.listarRiscosPOrdemGrauDeEsposicaoByStatus(projetoSelecionado, "Mitigando");
                        monitoracaoAnaliseDosRiscosJPanel.populaTabelaDeRiscos(listaDeRiscoAnalizarRiscos, false);
                        monitoracaoAnaliseDosRiscosJPanel.definirEventosTabelaDeRiscos();

                        if (listaProjetos.get(j).getConcluido()) {
                            habilitarProjetoConcluido(false);
                        } else {
                            habilitarProjetoConcluido(true);
                        }

                    }
                }
            }
        }


    }//GEN-LAST:event_projetoSelecionadoJComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        esconderFrames();
        novoProjetoJPanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void projetoSelecionadoJComboBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_projetoSelecionadoJComboBoxFocusGained

    }//GEN-LAST:event_projetoSelecionadoJComboBoxFocusGained

    private void projetoSelecionadoJComboBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projetoSelecionadoJComboBoxMousePressed
        listaProjetos.clear();
        listaProjetos = principalFacade.listarProjetos();
        projetoSelecionadoJComboBox.removeAllItems();
        popularComboBox();
    }//GEN-LAST:event_projetoSelecionadoJComboBoxMousePressed

    private void projetoSelecionadoJComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projetoSelecionadoJComboBoxMouseClicked

    }//GEN-LAST:event_projetoSelecionadoJComboBoxMouseClicked

    private void projetoSelecionadoJComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_projetoSelecionadoJComboBoxItemStateChanged

    }//GEN-LAST:event_projetoSelecionadoJComboBoxItemStateChanged

    private void projetoSelecionadoJComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_projetoSelecionadoJComboBoxPopupMenuWillBecomeVisible
        listaProjetos.clear();
        listaProjetos = principalFacade.listarProjetos();
        projetoSelecionadoJComboBox.removeAllItems();
        popularComboBox();
    }//GEN-LAST:event_projetoSelecionadoJComboBoxPopupMenuWillBecomeVisible

    private void editarEARJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarEARJButtonActionPerformed
        if (projetoSelecionadoJComboBox.getSelectedItem() != "--Selecione um Projeto--") {
            esconderFrames();
            organizacionalEditarEARJPanel.setVisible(true);
            arvoreFuncionalidadesJTree.clearSelection();
            organizacionalEditarEARJPanel.getOrganizacao(projetoSelecionado.getIdOrganizacao());
            organizacionalEditarEARJPanel.criarArvore();
            organizacionalEditarEARJPanel.popularArvore();
            organizacionalEditarEARJPanel.definirEventosArvore();
            organizacionalEditarEARJPanel.definirEventosArvoreSelecionarPai();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um projeto no Combobox.");
        }
    }//GEN-LAST:event_editarEARJButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        esconderFrames();
        //teste.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    System.out.println("nome  dos looks: " + info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalJFrame().setVisible(true);

            }
        });
    }

    public void popularComboBox() {
        projetoSelecionadoJComboBox.addItem("--Selecione um Projeto--");
        if (listaProjetos.size() > 0) {

            for (int i = 0; i < listaProjetos.size(); i++) {
                projetoSelecionadoJComboBox.addItem(listaProjetos.get(i).getNomeProjeto());
            }

        } else {
            System.out.println("Não existem projetos. Crie um.");
        }
    }

    // Adicionando Frames no Painel em Camadas - camadasJLayeredPane //
    private void adicionarFrames() {

        //organizacionalDetalhesJPanel.setBounds(0, 0, 861, 529);
        organizacionalDetalhesJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(organizacionalDetalhesJPanel);

        organizacionalEARJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(organizacionalEARJPanel);

        organizacionalPoliticaOrganizacionalJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(organizacionalPoliticaOrganizacionalJPanel);

        organizacionalPortfolioJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(organizacionalPortfolioJPanel);

        projetoDetalhesJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(projetoDetalhesJPanel);

        projetoPlanoRiscoJpanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(projetoPlanoRiscoJpanel);

        projetoEstruturaAnaliticaRiscosJpanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(projetoEstruturaAnaliticaRiscosJpanel);

        projetoCalendarioJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(projetoCalendarioJPanel);

        configuracoesFerramentaJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(configuracoesFerramentaJPanel);

        riscosGerenciarRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(riscosGerenciarRiscosJPanel);

        novoProjetoJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(novoProjetoJPanel);

        organizacionalEditarEARJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(organizacionalEditarEARJPanel);

        riscosResumoDeRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(riscosResumoDeRiscosJPanel);

        monitoracaoResumoDeMonitoracao.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(monitoracaoResumoDeMonitoracao);

        riscosPriorizarRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(riscosPriorizarRiscosJPanel);

        riscosRiscosOcorridosJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(riscosRiscosOcorridosJPanel);

        monitoracaoAnaliseDosRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(monitoracaoAnaliseDosRiscosJPanel);

        monitoracoaTabelaDeAlteracaoJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(monitoracoaTabelaDeAlteracaoJPanel);

        monitoracaoPlanosPendentesJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(monitoracaoPlanosPendentesJPanel);

        monitoracaoPlanosRealizadosJPanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(monitoracaoPlanosRealizadosJPanel);

        homeSpider_Jpanel.setBounds(camadasJDesktopPane.getBounds());
        camadasJDesktopPane.add(homeSpider_Jpanel);

    }
    //**************************************************************//

    public void esconderFrames() {
        organizacionalDetalhesJPanel.setVisible(false);
        organizacionalEARJPanel.setVisible(false);
        organizacionalPoliticaOrganizacionalJPanel.setVisible(false);
        organizacionalPortfolioJPanel.setVisible(false);
        projetoDetalhesJPanel.setVisible(false);
        projetoPlanoRiscoJpanel.setVisible(false);
        projetoEstruturaAnaliticaRiscosJpanel.setVisible(false);
        projetoCalendarioJPanel.setVisible(false);
        configuracoesFerramentaJPanel.setVisible(false);
        riscosGerenciarRiscosJPanel.setVisible(false);
        riscosResumoDeRiscosJPanel.setVisible(false);
        monitoracaoResumoDeMonitoracao.setVisible(false);
        novoProjetoJPanel.setVisible(false);
        organizacionalEditarEARJPanel.setVisible(false);
        riscosPriorizarRiscosJPanel.setVisible(false);
        riscosRiscosOcorridosJPanel.setVisible(false);
        monitoracaoAnaliseDosRiscosJPanel.setVisible(false);
        monitoracoaTabelaDeAlteracaoJPanel.setVisible(false);
        monitoracaoPlanosPendentesJPanel.setVisible(false);
        monitoracaoPlanosRealizadosJPanel.setVisible(false);
        homeSpider_Jpanel.setVisible(false);

    }

    // Criando os nós da arvore e adicionando-os //
    private void criarArvore() {
        funcionalidades = new DefaultMutableTreeNode("Funcionalidades");

        organizacional = new DefaultMutableTreeNode("Organizacional");
        funcionalidades.add(organizacional);

        projeto = new DefaultMutableTreeNode("Projeto");
        funcionalidades.add(projeto);

        politicaOrganizacional = new DefaultMutableTreeNode("Política Organizacional");
        organizacional.add(politicaOrganizacional);

        estruturaAnaliticaRiscos = new DefaultMutableTreeNode("Estrutura Analítica dos Riscos");
        organizacional.add(estruturaAnaliticaRiscos);

        portfolio = new DefaultMutableTreeNode("Portfólio");
        organizacional.add(portfolio);

        planoDeRisco = new DefaultMutableTreeNode("Plano de Risco");
        projeto.add(planoDeRisco);

        earProjeto = new DefaultMutableTreeNode("Estrutura Analítica de Riscos do Projeto");
        projeto.add(earProjeto);

        calendario = new DefaultMutableTreeNode("Calendário");
        projeto.add(calendario);

        riscos = new DefaultMutableTreeNode("Riscos");
        projeto.add(riscos);

        gerenciarRiscos = new DefaultMutableTreeNode("Gerenciar Riscos");
        riscos.add(gerenciarRiscos);

        priorizarRiscos = new DefaultMutableTreeNode("Priorizar Riscos");
        riscos.add(priorizarRiscos);

        riscosOcorridos = new DefaultMutableTreeNode("Riscos Ocorridos");
        riscos.add(riscosOcorridos);

        monitoracao = new DefaultMutableTreeNode("Monitoracao");
        projeto.add(monitoracao);

        analiseRiscos = new DefaultMutableTreeNode("Analisar Riscos");
        monitoracao.add(analiseRiscos);

        planosPendentes = new DefaultMutableTreeNode("Planos Pendentes");
        monitoracao.add(planosPendentes);

        planosRealizados = new DefaultMutableTreeNode("Planos Realizados");
        monitoracao.add(planosRealizados);

        arvoreFuncionalidadesJTree = new JTree(funcionalidades);
        arvoreFuncionalidadesJScrollPane.setViewportView(arvoreFuncionalidadesJTree);
        repaint();
    }
    //*********************************************************************//

    private void definirEventosArvore() {
        arvoreFuncionalidadesJTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    TreePath selPath = arvoreFuncionalidadesJTree.getPathForLocation(e.getX(),
                            e.getY());
                    if (selPath == null) {
                        return;
                    }

                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                    Object userObj = node.getUserObject();
                    System.out.println("Usuario escolheu: " + selPath);

                    esconderFrames();

                    if (projetoSelecionadoJComboBox.getSelectedItem() == "--Selecione um Projeto--") {
                        homeSpider_Jpanel.setVisible(true);
                        if (node == funcionalidades) {
                        } else if (node == organizacional) {
                            organizacionalDetalhesJPanel.setVisible(true);
                            organizacionalDetalhesJPanel.preencheForm();

                        } else if (node == politicaOrganizacional) {
                            organizacionalPoliticaOrganizacionalJPanel.setVisible(true);
                            organizacionalPoliticaOrganizacionalJPanel.preenchePolitica();
                        } else if (node == estruturaAnaliticaRiscos) {
                            organizacionalEARJPanel.setVisible(true);
                            organizacionalEARJPanel.criarArvore();
                            organizacionalEARJPanel.criarTabela();
                            organizacionalEARJPanel.popularArvoreEtabela();
                            organizacionalEARJPanel.definirEventosArvore();
                            organizacionalEARJPanel.definirEventosTabela();
                        } else if (node == portfolio) {
                            organizacionalPortfolioJPanel.setVisible(true);
                            organizacionalPortfolioJPanel.reiniciarTabelaPortfolio();
                        } else {
                            JOptionPane.showMessageDialog(null, "É necessário selecionar um Projeto para acessar essa área.");
                        }
                    } else {
                        if (node == organizacional) {
                            organizacionalDetalhesJPanel.setVisible(true);
                            organizacionalDetalhesJPanel.preencheForm();

                        } else if (node == politicaOrganizacional) {
                            organizacionalPoliticaOrganizacionalJPanel.setVisible(true);
                            organizacionalPoliticaOrganizacionalJPanel.preenchePolitica();
                        } else if (node == estruturaAnaliticaRiscos) {
                            organizacionalEARJPanel.setVisible(true);
                            organizacionalEARJPanel.criarArvore();
                            organizacionalEARJPanel.criarTabela();
                            organizacionalEARJPanel.popularArvoreEtabela();
                            organizacionalEARJPanel.definirEventosArvore();
                            organizacionalEARJPanel.definirEventosTabela();
                        } else if (node == portfolio) {
                            organizacionalPortfolioJPanel.setVisible(true);
                            organizacionalPortfolioJPanel.reiniciarTabelaPortfolio();
                        } else if (node == projeto) {
                            projetoDetalhesJPanel.setVisible(true);
                            projetoDetalhesJPanel.preencheDetalhes(projetoSelecionado);
                        } else if (node == planoDeRisco) {
                            projetoPlanoRiscoJpanel.setVisible(true);
                            projetoPlanoRiscoJpanel.preenchePlanoRisco(projetoSelecionado);
                        } else if (node == gerenciarRiscos) {
                            riscosGerenciarRiscosJPanel.setVisible(true);
                            riscosGerenciarRiscosJPanel.limpaCamposGerenciarRisco();
                            riscosGerenciarRiscosJPanel.reiniciarTabelaRiscos();
                        } else if (node == earProjeto) {
                            projetoEstruturaAnaliticaRiscosJpanel.setVisible(true);
                        } else if (node == calendario) {
                            projetoCalendarioJPanel.setVisible(true);
                        } else if (node == priorizarRiscos) {
                            riscosPriorizarRiscosJPanel.setVisible(true);
                            riscosPriorizarRiscosJPanel.criaTabela();
                            RiscosGerenciarRiscosFacade rGRfacade = new RiscosGerenciarRiscosFacade();
                            List<Risco> listaDeRisco = rGRfacade.listarRiscosPOrdemGrauDeEsposicao(projetoSelecionado);
                            riscosPriorizarRiscosJPanel.populaTabelaDeRiscos(listaDeRisco, false);
                            riscosPriorizarRiscosJPanel.definirEventosTabelaPriorizarRiscos();
                        } else if (node == riscosOcorridos) {
                            riscosRiscosOcorridosJPanel.setVisible(true);
                            riscosRiscosOcorridosJPanel.criarTabelaRiscosOcorridos();
                            riscosRiscosOcorridosJPanel.getProjeto(projetoSelecionado);
                            riscosRiscosOcorridosJPanel.popularTabelaRiscosOcorridos();
                        } else if (node == analiseRiscos) {
                            monitoracaoAnaliseDosRiscosJPanel.setVisible(true);
                            monitoracaoAnaliseDosRiscosJPanel.criarTabelaAnalisarRiscos();
                            MonitoracaoAnaliseDosRiscosFacade analiseFacade = new MonitoracaoAnaliseDosRiscosFacade();
                            List<Risco> listaDeRisco = analiseFacade.listarRiscosPOrdemGrauDeEsposicaoByStatus(projetoSelecionado, "Mitigando");
                            monitoracaoAnaliseDosRiscosJPanel.populaTabelaDeRiscos(listaDeRisco, false);
                            monitoracaoAnaliseDosRiscosJPanel.definirEventosTabelaDeRiscos();
                        } else if (node == planosPendentes) {
                            ProjetoFacade projetoFacade = new ProjetoFacade();
                            monitoracaoPlanosPendentesJPanel.criarTabelaPlanosPendentes(projetoFacade.buscaPlanosDeContingenciaPendentes(projetoSelecionado), projetoFacade.buscaPlanosDeMitigacaoPendentes(projetoSelecionado));
                            monitoracaoPlanosPendentesJPanel.setProjetoSelecionado(projetoSelecionado);
                            monitoracaoPlanosPendentesJPanel.setVisible(true);
                        } else if (node == planosRealizados) {
                            ProjetoFacade projetoFacade = new ProjetoFacade();
                            monitoracaoPlanosRealizadosJPanel.criarTabelaPlanosRealizados(projetoFacade.buscaPlanosDeContingenciaRealizados(projetoSelecionado), projetoFacade.buscaPlanosDeMitigacaoRealizados(projetoSelecionado));
                            monitoracaoPlanosRealizadosJPanel.setVisible(true);
                        } else if (node == riscos) {
                            RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
                            List<Risco> listaDeRiscoPorProjeto = riscosGerenciarRiscosFacade.listarRiscosByProjeto(projetoSelecionado);
                            riscosResumoDeRiscosJPanel.criaTabelResumoDeRiscos();
                            riscosResumoDeRiscosJPanel.preencherDadosTabelaResumoDeRiscos(listaDeRiscoPorProjeto);
                            riscosResumoDeRiscosJPanel.setVisible(true);
                        } else if (node == monitoracao) {
                            RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
                            ProjetoCalendarioFacade projetoCalendarioFacade = new ProjetoCalendarioFacade();
                            ProjetoFacade projetoFacade = new ProjetoFacade();
                            List<Marcodoprojeto> listaDeMarcosDoProjetoPorProjetoParaResumoDeMonitoracao = projetoCalendarioFacade.getListaMarcosDoProjetoSelecionado(projetoSelecionado);
                            List<Pontodecontrole> listaDePontosDeControlePorProjetoParaResumoDeMonitoracao = projetoCalendarioFacade.getListaPontosDeControleDoProjetoSelecionado(projetoSelecionado);
                            List<Risco> listaDeRiscoPorProjetoParaResumoDeMonitoracao = riscosGerenciarRiscosFacade.listarRiscosByProjeto(projetoSelecionado);
                            List<Planocontingencia> listaPlanoContingenciaPorProjetoParaResumoDeMonitoracao = projetoFacade.buscaPlanosDeContingenciaPendentes(projetoSelecionado);
                            List<Planomitigacao> listaPlanoMitigacaoContingenciaPorProjetoParaResumoDeMonitoracao = projetoFacade.buscaPlanosDeMitigacaoPendentes(projetoSelecionado);

                            monitoracaoResumoDeMonitoracao.criaTabelResumoDeStatusDeRiscos();
                            monitoracaoResumoDeMonitoracao.preencherDadosTabelResumoDeStatusDeRiscos(listaDeRiscoPorProjetoParaResumoDeMonitoracao);

                            monitoracaoResumoDeMonitoracao.criaTabelResumoDePlanosPendentes();
                            monitoracaoResumoDeMonitoracao.criaListaDePlanosTabela(listaPlanoContingenciaPorProjetoParaResumoDeMonitoracao, listaPlanoMitigacaoContingenciaPorProjetoParaResumoDeMonitoracao);

                            monitoracaoResumoDeMonitoracao.criaTabelResumoDeMarcosEPontosDeControle();
                            monitoracaoResumoDeMonitoracao.preencheTabelResumoDeMarcosEPontoDeControle(listaDeMarcosDoProjetoPorProjetoParaResumoDeMonitoracao, listaDePontosDeControlePorProjetoParaResumoDeMonitoracao);

                            monitoracaoResumoDeMonitoracao.setVisible(true);
                        }
                        if (projetoSelecionado.getConcluido()) {
                            habilitarProjetoConcluido(false);
                        } else {
                            habilitarProjetoConcluido(true);
                        }
                    }
                }
            }
        });
    }

//    private void habilitarProjetoConcluido() {
//        projetoDetalhesJPanel.habilitarProjetoDetalhesJPanel();
//        projetoPlanoRiscoJpanel.habilitarProjetoPlanoRiscoJPanel();
//        projetoEstruturaAnaliticaRiscosJpanel.habilitarProjetoEstruturaAnaliticaRiscosJpanel();
//    }
    private void habilitarProjetoConcluido(Boolean habilitar) {
        projetoDetalhesJPanel.habilitarProjetoDetalhesJPanel(habilitar);
        projetoEstruturaAnaliticaRiscosJpanel.habilitarProjetoEstruturaAnaliticaRiscosJpanel(habilitar);
        riscosGerenciarRiscosJPanel.desabilitaBotoesSeTemRiscoSelecionado();
        projetoCalendarioJPanel.habilitaBotoesProjetoCalendarioJpanel(habilitar);
        riscosPriorizarRiscosJPanel.habilitarbotõesPriorizarRiscosJpanel(habilitar);
        monitoracaoAnaliseDosRiscosJPanel.habilitarBotoesMonitoracaoAnaliseDeRiscosJpanel(habilitar);
        monitoracaoPlanosPendentesJPanel.habilitaBotoesMonitorarTarefasPlanosPendentes(habilitar);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane arvoreFuncionalidadesJScrollPane;
    private javax.swing.JDesktopPane camadasJDesktopPane;
    private javax.swing.JButton configuracoesFerramentaJButton;
    private javax.swing.JButton editarEARJButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel_Logo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel menuJPanel;
    private javax.swing.JComboBox projetoSelecionadoJComboBox;
    private javax.swing.JLabel projetoSelecionadoJLabel;
    // End of variables declaration//GEN-END:variables
}
