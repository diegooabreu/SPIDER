/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.MonitoracaoAnaliseDosRiscosFacade;
import facade.PrincipalFacade;
import facade.ProjetoFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.Planocontingencia;
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
    RiscosGerenciarRiscosJPanel riscosGerenciarRiscosJPanel = new RiscosGerenciarRiscosJPanel();
    final NovoProjetoJPanel novoProjetoJPanel = new NovoProjetoJPanel();
    final OrganizacionalEditarEARJPanel organizacionalEditarEARJPanel = new OrganizacionalEditarEARJPanel();
    final RiscosPriorizarRiscosJPanel riscosPriorizarRiscosJPanel = new RiscosPriorizarRiscosJPanel();
    final RiscosRiscosOcorridosJPanel riscosRiscosOcorridosJPanel = new RiscosRiscosOcorridosJPanel();

    static MonitoracaoAnaliseDosRiscosJPanel monitoracaoAnaliseDosRiscosJPanel = new MonitoracaoAnaliseDosRiscosJPanel();

    final MonitoracaoTabelaDeAlteracaoJPanel monitoracoaTabelaDeAlteracaoJPanel = new MonitoracaoTabelaDeAlteracaoJPanel();
    final MonitoracaoTarefasPlanosPendentesJPanel monitoracaoPlanosPendentesJPanel = new MonitoracaoTarefasPlanosPendentesJPanel();
    final MonitoracaoTarefasPlanosRealizadosJPanel monitoracaoPlanosRealizadosJPanel = new MonitoracaoTarefasPlanosRealizadosJPanel();

    // static TesteInternalFrame testeInternalFrame = new TesteInternalFrame();
    static RiscoSelecioanrRiscoParaMonitorarInternalJFrame riscoSelecioanrRiscoParaMonitorarInternalJFrame = new RiscoSelecioanrRiscoParaMonitorarInternalJFrame();
    static MonitoracaoAnaliseDosRiscosCheckInternalJFrame monitoracaoAnaliseDosRiscosCheckInternalJFrame = new MonitoracaoAnaliseDosRiscosCheckInternalJFrame();
    static RiscosRiscosOcorridosJInternalFrame riscosRiscosOcorridosJInternalFrame = new RiscosRiscosOcorridosJInternalFrame();
    static CalendarioDetalhesMarcoEpontoDoDiaInternalJFrame calendarioDetalhesMarcoEpontoDoDiaInternalJFrame = new CalendarioDetalhesMarcoEpontoDoDiaInternalJFrame();
    static MonitorarPlanosPendentesMaisInformaçõesInternalFrame monitorarPlanosPendentesMaisInformaçõesInternalFrame = new MonitorarPlanosPendentesMaisInformaçõesInternalFrame();
    
    
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

    //*****************************************//
    /**
     * Creates new form FramePrincipal
     */
    public PrincipalJFrame() {
        initComponents();
        popularComboBox();
        adicionarFrames();
        esconderFrames();
        criarArvore();
        definirEventosArvore();
        definirEventoRedimensionamento();
        //teste.setVisible(true);

    }

    public void definirEventoRedimensionamento(){
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
        
            riscosGerenciarRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            novoProjetoJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            organizacionalEditarEARJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            riscosPriorizarRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            riscosRiscosOcorridosJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            monitoracaoAnaliseDosRiscosJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            monitoracoaTabelaDeAlteracaoJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            monitoracaoPlanosPendentesJPanel.setBounds(camadasJDesktopPane.getBounds());
        
            monitoracaoPlanosRealizadosJPanel.setBounds(camadasJDesktopPane.getBounds());
        
        
                
                System.out.println("componentResized()"); // TODO Auto-generated Event stub componentResized()  
            }  
            public void componentMoved(java.awt.event.ComponentEvent e) {  
            }  
            public void componentShown(java.awt.event.ComponentEvent e) {  
            }  
            public void componentHidden(java.awt.event.ComponentEvent e) {  
            }  
        });  
    }
    /*
    this.addComponentListener(new java.awt.event.ComponentListener() {  
            public void componentResized(java.awt.event.ComponentEvent e) {  
                System.out.println("componentResized()"); // TODO Auto-generated Event stub componentResized()  
            }  
            public void componentMoved(java.awt.event.ComponentEvent e) {  
            }  
            public void componentShown(java.awt.event.ComponentEvent e) {  
            }  
            public void componentHidden(java.awt.event.ComponentEvent e) {  
            }  
        });  
    */
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        configuracoesFerramentaJButton = new javax.swing.JButton();
        projetoSelecionadoJComboBox = new javax.swing.JComboBox();
        projetoSelecionadoJLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        editarEARJButton = new javax.swing.JButton();
        menuJPanel = new javax.swing.JPanel();
        arvoreFuncionalidadesJScrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        camadasJDesktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPIDER - Risk Management");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configuracoesFerramentaJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarEARJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(projetoSelecionadoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(projetoSelecionadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(editarEARJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(projetoSelecionadoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(projetoSelecionadoJLabel))
                    .addComponent(configuracoesFerramentaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menuJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout menuJPanelLayout = new javax.swing.GroupLayout(menuJPanel);
        menuJPanel.setLayout(menuJPanelLayout);
        menuJPanelLayout.setHorizontalGroup(
            menuJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arvoreFuncionalidadesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        menuJPanelLayout.setVerticalGroup(
            menuJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arvoreFuncionalidadesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        jPanel2.setAutoscrolls(true);
        jPanel2.setPreferredSize(new java.awt.Dimension(861, 513));

        camadasJDesktopPane.setRequestFocusEnabled(false);

        javax.swing.GroupLayout camadasJDesktopPaneLayout = new javax.swing.GroupLayout(camadasJDesktopPane);
        camadasJDesktopPane.setLayout(camadasJDesktopPaneLayout);
        camadasJDesktopPaneLayout.setHorizontalGroup(
            camadasJDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(menuJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    static void aparecerInternalFrame() {
        //testeInternalFrame.setVisible(true);
        riscoSelecioanrRiscoParaMonitorarInternalJFrame.setVisible(true);
    }

    static void aparecerInternalFrameMonitoracao(){
        monitoracaoAnaliseDosRiscosCheckInternalJFrame.setVisible(true);
    }

    static void aparecerInternalFrameRiscosOcorridos(){
        riscosRiscosOcorridosJInternalFrame.setVisible(true);
    }
    
    static void aparecerInternalCalendarioDetalhesMarcoEPonto(){
        calendarioDetalhesMarcoEpontoDoDiaInternalJFrame.setVisible(true);
    }
    
    static void aparecerInternalFramePlanosPendentes(){
        monitorarPlanosPendentesMaisInformaçõesInternalFrame.setVisible(true);
    }

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
                        projetoSelecionado = listaProjetos.get(j);

                        projetoDetalhesJPanel.preencheDetalhes(projetoSelecionado);

                        projetoPlanoRiscoJpanel.preenchePlanoRisco(projetoSelecionado);

                        projetoEstruturaAnaliticaRiscosJpanel.criarArvore();
                        projetoEstruturaAnaliticaRiscosJpanel.popularArvoreEARProjetoEArvoreSelecionarPaiEArvoreAdicionarEAROrganizacional(projetoSelecionado.getIdProjeto());

                        configuracoesFerramentaJPanel.setProjetoSelecionado(projetoSelecionado.getIdProjeto());
                        configuracoesFerramentaJPanel.preencherCaminhoPlanoRisco(projetoSelecionado.getIdProjeto());

                        // Na tela Gerenciar Riscos
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

                        if (listaProjetos.get(j).getConcluido()) {
                            desabilitarProjetoConcluido();
                        } else {
                            habilitarProjetoConcluido();
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
        esconderFrames();
        organizacionalEditarEARJPanel.setVisible(true);
        arvoreFuncionalidadesJTree.clearSelection();
        organizacionalEditarEARJPanel.getOrganizacao(projetoSelecionado.getIdOrganizacao());
        organizacionalEditarEARJPanel.criarArvore();
        organizacionalEditarEARJPanel.popularArvore();
        organizacionalEditarEARJPanel.definirEventosArvore();
        organizacionalEditarEARJPanel.definirEventosArvoreSelecionarPai();

// TODO add your handling code here:
    }//GEN-LAST:event_editarEARJButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        esconderFrames();
        //teste.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
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
            public void run() {
                new PrincipalJFrame().setVisible(true);
            }
        });
    }

    public void popularComboBox() {

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

        camadasJDesktopPane.add(riscoSelecioanrRiscoParaMonitorarInternalJFrame);

        camadasJDesktopPane.add(monitoracaoAnaliseDosRiscosCheckInternalJFrame);

        camadasJDesktopPane.add(riscosRiscosOcorridosJInternalFrame);
        
        calendarioDetalhesMarcoEpontoDoDiaInternalJFrame.setBounds(0, 0, 615, 420);
        camadasJDesktopPane.add(calendarioDetalhesMarcoEpontoDoDiaInternalJFrame);
        
        camadasJDesktopPane.add(monitorarPlanosPendentesMaisInformaçõesInternalFrame);

        //riscosPriorizarRiscosJPanel.setBounds(0, 0, 861, 529);
        //camadasJDesktopPane.add(riscosPriorizarRiscosJPanel);

        //camadasJDesktopPane.add(riscosSelecionarRiscosParaMonitorarInternalJFrame);

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
        novoProjetoJPanel.setVisible(false);
        organizacionalEditarEARJPanel.setVisible(false);
        riscosPriorizarRiscosJPanel.setVisible(false);
        riscosRiscosOcorridosJPanel.setVisible(false);
        monitoracaoAnaliseDosRiscosJPanel.setVisible(false);
        monitoracoaTabelaDeAlteracaoJPanel.setVisible(false);
        monitoracaoPlanosPendentesJPanel.setVisible(false);
        monitoracaoPlanosRealizadosJPanel.setVisible(false);

        //InternaisFrame
        riscoSelecioanrRiscoParaMonitorarInternalJFrame.setVisible(false);
        monitoracaoAnaliseDosRiscosCheckInternalJFrame.setVisible(false);
        riscosRiscosOcorridosJInternalFrame.setVisible(false);
        calendarioDetalhesMarcoEpontoDoDiaInternalJFrame.setVisible(false);
        monitorarPlanosPendentesMaisInformaçõesInternalFrame.setVisible(false);
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

        tabelaAlteracoes = new DefaultMutableTreeNode("Tabela de Alterações");
        monitoracao.add(tabelaAlteracoes);

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
                    }
                }
            }

        });
    }

    private void habilitarProjetoConcluido() {
        projetoDetalhesJPanel.habilitarProjetoDetalhesJPanel();
        projetoPlanoRiscoJpanel.habilitarProjetoPlanoRiscoJPanel();
        projetoEstruturaAnaliticaRiscosJpanel.habilitarProjetoEstruturaAnaliticaRiscosJpanel();
    }

    private void desabilitarProjetoConcluido() {
        projetoDetalhesJPanel.desabilitarProjetoDetalhesJPanel();
        projetoPlanoRiscoJpanel.desabilitarProjetoPlanoRiscoJPanel();
        projetoEstruturaAnaliticaRiscosJpanel.desabilitarProjetoEstruturaAnaliticaRiscosJpanel();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane arvoreFuncionalidadesJScrollPane;
    private javax.swing.JDesktopPane camadasJDesktopPane;
    private javax.swing.JButton configuracoesFerramentaJButton;
    private javax.swing.JButton editarEARJButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel menuJPanel;
    private javax.swing.JComboBox projetoSelecionadoJComboBox;
    private javax.swing.JLabel projetoSelecionadoJLabel;
    // End of variables declaration//GEN-END:variables
}
