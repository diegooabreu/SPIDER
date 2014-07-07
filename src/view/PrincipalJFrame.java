/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.PrincipalFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
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
    final MonitoracaoQuadroDeAvisosJPanel monitoracaoQuadroDeAvisoJPanel = new MonitoracaoQuadroDeAvisosJPanel();
    final MonitoracaoTabelaDeAlteracaoJPanel monitoracoaTabelaDeAlteracaoJPanel = new MonitoracaoTabelaDeAlteracaoJPanel();
    final MonitoracaoTarefasTarefasPendentesJPanel monitoracaoTarefasTarefasPendentesJPanel = new MonitoracaoTarefasTarefasPendentesJPanel();
    final MonitoracaoTarefasHistoricoDeTarefasJPanel monitoracaoTarefasHistoricoDeTarefasJPanel = new MonitoracaoTarefasHistoricoDeTarefasJPanel();

    //***************************/
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
    private DefaultMutableTreeNode quadroAvisos;
    private DefaultMutableTreeNode tabelaAlteracoes;
    private DefaultMutableTreeNode tarefas;
    private DefaultMutableTreeNode tarefasPendentes;
    private DefaultMutableTreeNode historicoTarefas;

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

    }

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout camadasJDesktopPaneLayout = new javax.swing.GroupLayout(camadasJDesktopPane);
        camadasJDesktopPane.setLayout(camadasJDesktopPaneLayout);
        camadasJDesktopPaneLayout.setHorizontalGroup(
            camadasJDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );
        camadasJDesktopPaneLayout.setVerticalGroup(
            camadasJDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(camadasJDesktopPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(camadasJDesktopPane)
                    .addComponent(menuJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        organizacionalDetalhesJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(organizacionalDetalhesJPanel);

        organizacionalEARJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(organizacionalEARJPanel);

        organizacionalPoliticaOrganizacionalJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(organizacionalPoliticaOrganizacionalJPanel);

        organizacionalPortfolioJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(organizacionalPortfolioJPanel);

        projetoDetalhesJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(projetoDetalhesJPanel);

        projetoPlanoRiscoJpanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(projetoPlanoRiscoJpanel);

        projetoEstruturaAnaliticaRiscosJpanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(projetoEstruturaAnaliticaRiscosJpanel);

        projetoCalendarioJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(projetoCalendarioJPanel);

        configuracoesFerramentaJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(configuracoesFerramentaJPanel);

        riscosGerenciarRiscosJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(riscosGerenciarRiscosJPanel);

        novoProjetoJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(novoProjetoJPanel);

        organizacionalEditarEARJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(organizacionalEditarEARJPanel);

        riscosPriorizarRiscosJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(riscosPriorizarRiscosJPanel);

        riscosRiscosOcorridosJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(riscosRiscosOcorridosJPanel);

        monitoracaoQuadroDeAvisoJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(monitoracaoQuadroDeAvisoJPanel);

        monitoracoaTabelaDeAlteracaoJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(monitoracoaTabelaDeAlteracaoJPanel);

        monitoracaoTarefasTarefasPendentesJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(monitoracaoTarefasTarefasPendentesJPanel);

        monitoracaoTarefasHistoricoDeTarefasJPanel.setBounds(0, 0, 861, 529);
        camadasJDesktopPane.add(monitoracaoTarefasHistoricoDeTarefasJPanel);

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
        monitoracaoQuadroDeAvisoJPanel.setVisible(false);
        monitoracoaTabelaDeAlteracaoJPanel.setVisible(false);
        monitoracaoTarefasTarefasPendentesJPanel.setVisible(false);
        monitoracaoTarefasHistoricoDeTarefasJPanel.setVisible(false);
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

        quadroAvisos = new DefaultMutableTreeNode("Quadro de Avisos");
        monitoracao.add(quadroAvisos);

        tabelaAlteracoes = new DefaultMutableTreeNode("Tabela de Alterações");
        monitoracao.add(tabelaAlteracoes);

        tarefas = new DefaultMutableTreeNode("Tarefas");
        monitoracao.add(tarefas);

        tarefasPendentes = new DefaultMutableTreeNode("Tarefas Pendentes");
        tarefas.add(tarefasPendentes);

        historicoTarefas = new DefaultMutableTreeNode("Histórico de Tarefas");
        tarefas.add(historicoTarefas);

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
                    } else if (node == quadroAvisos) {
                        monitoracaoQuadroDeAvisoJPanel.setVisible(true);
                    } else if (node == tabelaAlteracoes) {
                        monitoracoaTabelaDeAlteracaoJPanel.setVisible(true);
                    } else if (node == tarefasPendentes) {
                        monitoracaoTarefasTarefasPendentesJPanel.setVisible(true);
                    } else if (node == historicoTarefas) {
                        monitoracaoTarefasHistoricoDeTarefasJPanel.setVisible(true);
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
    private javax.swing.JPanel menuJPanel;
    private javax.swing.JComboBox projetoSelecionadoJComboBox;
    private javax.swing.JLabel projetoSelecionadoJLabel;
    // End of variables declaration//GEN-END:variables
}
