/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.ContemFacade;
import facade.OrganizacionalPortfolioFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Categoriaderisco;
import model.Contem;
import model.ContemPK;
import model.Historicoalteracao;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Projeto;
import model.Relacaosubcondicao;
import model.RelacaosubcondicaoPK;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author Victor
 */
public class RiscosGerenciarRiscosJPanel extends javax.swing.JPanel {

    /**
     * Creates new form RiscosGerenciarRiscosJPanel
     */
    // Instanciando classe Facade de Riscos
    RiscosGerenciarRiscosFacade riscosGerenciarRiscosFacade = new RiscosGerenciarRiscosFacade();
    //Instanciando classe Facade de OrganizacionalPortfolio para obter lista de categorias
    OrganizacionalPortfolioFacade organizacionalPortfolioFacade = new OrganizacionalPortfolioFacade();
    //Instanciando classe Facade de Contem
    ContemFacade contemFacade = new ContemFacade();

    // Instanciando Tabela de Riscos
    private JTable tabelaRiscosJTable = null;
    // Instanciando modelo dos dados da Tabela de Riscos
    private DefaultTableModel modeloTabelaRiscosJTable = null;

    // Instanciando variável que armazena o projeto selecionado
    private Projeto projetoSelecionado = new Projeto(1);
    // Instanciando variável que armazena o risco selecionado
    private Risco riscoSelecionado = new Risco();

    // Instanciando variável que armazena a lista de Riscos do projeto selecionado
    private List<Risco> listaRiscos;
    // Instanciando variável que armazena a lista de Categorias de Risco pertencentes ao projeto selecionado
    private List<Categoriaderisco> listaCategorias;
    // Instanciando variável que armazena a lista da tabela Contém
    private List<Contem> listaContem;
    // Instanciando variável que armazena a lista de histórico de alterações do risco selecionado
    private List<Historicoalteracao> listaHistoricoAlteracao;
    //Variável que armazena a subcondição selecionada
    Subcondicao subcondicaoSelecionada = null;

    public RiscosGerenciarRiscosJPanel() {
        initComponents();
        criarTabelaRiscos();
        //criarListaPlanoMitigacao();
        //criarListaPlanoContingencia();
        getListaRiscos();
        getListaCategorias();
        preencherDadosTabelaRiscos();
        definirEventosTabelaRiscos();
        iniciaBotoesCinzaPlanoMitigacao();
        iniciaBotoesCinzaPlanoContingencia();
        iniciaBotosRiscoCinza();
        criaTabelaHistoricoAlteracoes();
        criarTabelaSubcondicoes();
    }

    // -------------------------***************************--------------------------*******************************
    // Aba Informacoes Gerais
    //Método que preenche o comboBox com as categorias de risco do projeto selecionado
    public void preencheComboBox() {
        informacoesGeraisCategoriaDeRiscoJComboBox.removeAllItems();
        List<Contem> listaC = null;
        listaC = contemFacade.findCategoriasByIdProjeto(projetoSelecionado);
        if (listaC.size() > 0) {
            for (int i = 0; i < listaC.size(); i++) {
                informacoesGeraisCategoriaDeRiscoJComboBox.addItem(listaC.get(i).getCategoriaderisco().getNomeCategoria());
            }
        }
    }

    //Método que inicia os botões de Salvar alterações de risco e remover risco selecionado desativados
    public void iniciaBotosRiscoCinza() {
        informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(false);
        informacoesGeraisRemoverRiscoJButton.setEnabled(false);
    }

    //Método que seleciona o projeto selecionado
    public void setProjetoSelecionado(Projeto projeto) {
        projetoSelecionado = projeto;
        getListaRiscos();
    }

    //Método que atualiza a lista de riscos do projeto selecionado
    private void getListaRiscos() {
        listaRiscos = riscosGerenciarRiscosFacade.listarRiscos();
    }

    //Método que atualiza a lista de categorias de risco
    private void getListaCategorias() {
        listaCategorias = organizacionalPortfolioFacade.listarCategorias();
    }

    //Método que atualiza a lista de Histórico de Alterações do risco selecionado
    private void getListaHistoricoAlteracoes(Risco idRisco) {
        listaHistoricoAlteracao = organizacionalPortfolioFacade.listarHistoricoAlteracoesByIdRisco(idRisco);
    }

    //Método que atualiza a lista contém
    private void getListaContem() {
        listaContem = organizacionalPortfolioFacade.listarContem();
    }

    //Método que constrói a Tabela de Riscos
    private void criarTabelaRiscos() {
        tabelaRiscosJTable = new JTable();
        modeloTabelaRiscosJTable = new DefaultTableModel();
        modeloTabelaRiscosJTable.setColumnIdentifiers(new String[]{"Identificação", "Probabilidade", "Impacto", "Grau de Severidade", "Prioridade", "Estado"});
        tabelaRiscosJTable.setModel(modeloTabelaRiscosJTable);
        tabelaRiscosJScrollPane.setViewportView(tabelaRiscosJTable);
    }
    /*
     //Método que desabilita as caixas de status do risco
     private void desabilitaCheckBoxStatusRisco() {
     informacoesGeraisMitigandoJCheckBox.setSelected(false);
     informacoesGeraisContingenciandoJCheckBox.setSelected(false);
     informacoesGeraisNovoJCheckBox.setSelected(false);
     informacoesGeraisResolvidoJCheckBox.setSelected(false);
     }
     */

    //Método que preenche os dados da Tabela de Riscos, conforme o projeto selecionado
    private void preencherDadosTabelaRiscos() {
        for (int i = 0; i < listaRiscos.size(); i++) {
            //Verifica se o ID do projeto selecionado é igual ao ID do Projeto do Contém do Risco atual
            if (projetoSelecionado.getIdProjeto() == listaRiscos.get(i).getContem().getProjeto().getIdProjeto()) {
                String identificacao = listaRiscos.get(i).getIdentificacao();
                String probabilidade = Integer.toString(listaRiscos.get(i).getProbabilidade());
                String impacto = listaRiscos.get(i).getImpacto();
                String grauSeveridade = Integer.toString(listaRiscos.get(i).getGrauSeveridade());
                String prioridade = Integer.toString(listaRiscos.get(i).getPrioridade());
                String statusRisco = listaRiscos.get(i).getStatusRisco();

                String[] linha = new String[]{identificacao, probabilidade, impacto, grauSeveridade, prioridade, statusRisco};
                modeloTabelaRiscosJTable.addRow(linha);
            }
        }
    }

    //Método para reconstruir a tabela quando for selecionado um novo risco
    public void reiniciarTabelaRiscos() {
        getListaRiscos();
        getListaCategorias();
        modeloTabelaRiscosJTable = null;
        criarTabelaRiscos();
        preencherDadosTabelaRiscos();
        definirEventosTabelaRiscos();
    }

    //Método que limpa todos os campos de Informações Gerais
    public void limparCampos() {
        informacoesGeraisIdentificacaoDeRiscoJTextField.setText("");
        informacoesGeraisDataDeIdentificacaoJDateChooser.setCalendar(null);
        informacoesGeraisEmissorJTextField.setText("");
        InformacoesGeraisDescricaoDeRiscoJTextArea.setText("");
        informacoesGeraisCategoriaDeRiscoJComboBox.removeAllItems();
        informacoesGeraisProbabilidadeJSpinner.setValue(0);
        informacoesGeraisImpactoJComboBox.setSelectedIndex(2);
        //desabilitaCheckBoxStatusRisco();
        informacoesGeraisGrauDeSeveridadeJTextField.setText("0");
        tabelaRiscosJTable.clearSelection();
    }

    //Define os eventos para quando um item da tabela for selecionado
    private void definirEventosTabelaRiscos() {
        tabelaRiscosJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selected = tabelaRiscosJTable.getSelectedRow();

                    //Procura o risco selecionado
                    for (int i = 0; i < listaRiscos.size(); i++) {
                        if (tabelaRiscosJTable.getValueAt(selected, 0).equals(listaRiscos.get(i).getIdentificacao())) {
                            riscoSelecionado = listaRiscos.get(i);
                            break;
                        }
                    }
                    //Preenche os dados do Risco no painel de informações
                    informacoesGeraisIdentificacaoDeRiscoJTextField.setText(riscoSelecionado.getIdentificacao());

                    //Busca as informações de histórico do risco selecionado
                    getListaHistoricoAlteracoes(riscoSelecionado);
                    limparTabelaHistoricoAlteracoes();
                    preencheTabelaHistoricoAlteracoes();

                    //Preenche comboBox
                    preencheComboBox();

                    //Preenche lista de riscos que influencia
                    preencheListaRiscosInfluencia();
                    preencheListaRiscosInfluenciados();

                    //Preenche tabela de subcondições do risco selecionado
                    getListaSubcondicoes(riscoSelecionado);
                    limparCamposSubcondicao();
                    limparListaRelacoesSubcondicoes();
                    reiniciarTabelaSubcondicoes();

                    Calendar dataIdentificacao = Calendar.getInstance();
                    dataIdentificacao.setTime(riscoSelecionado.getDataIdentificacao());
                    informacoesGeraisDataDeIdentificacaoJDateChooser.setCalendar(dataIdentificacao);

                    informacoesGeraisEmissorJTextField.setText(riscoSelecionado.getEmissor());
                    informacoesGeraisProbabilidadeJSpinner.setValue(riscoSelecionado.getProbabilidade());
                    informacoesGeraisGrauDeSeveridadeJTextField.setText(Integer.toString(riscoSelecionado.getGrauSeveridade()));
                    /*
                     // Determina qual caixa de status do risco será selecionada
                     if (riscoSelecionado.getStatusRisco().equals("Novo")) {
                     desabilitaCheckBoxStatusRisco();
                     informacoesGeraisNovoJCheckBox.setSelected(true);
                     } else if (riscoSelecionado.getStatusRisco().equals("Contingenciando")) {
                     desabilitaCheckBoxStatusRisco();
                     informacoesGeraisContingenciandoJCheckBox.setSelected(true);
                     } else if (riscoSelecionado.getStatusRisco().equals("Mitigando")) {
                     desabilitaCheckBoxStatusRisco();
                     informacoesGeraisMitigandoJCheckBox.setSelected(true);
                     } else if (riscoSelecionado.getStatusRisco().equals("Resolvido")) {
                     desabilitaCheckBoxStatusRisco();
                     informacoesGeraisResolvidoJCheckBox.setSelected(true);
                     }
                     */
                    // Determina qual campo do comboBox de Impacto será selecionado
                    if (riscoSelecionado.getImpacto().equals("Alto")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(1);
                    } else if (riscoSelecionado.getImpacto().equals("Médio")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(2);
                    } else if (riscoSelecionado.getImpacto().equals("Baixo")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(3);
                    } else if (riscoSelecionado.getImpacto().equals("Crítico")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(0);
                    }

                    //Determina qual campo do comboBox de Categoria de Risco será selecionado
                    String categoria = riscoSelecionado.getContem().getCategoriaderisco().getNomeCategoria();
                    for (int i = 0; i < informacoesGeraisCategoriaDeRiscoJComboBox.getItemCount(); i++) {
                        if (categoria.equals(informacoesGeraisCategoriaDeRiscoJComboBox.getItemAt(i))) {
                            informacoesGeraisCategoriaDeRiscoJComboBox.setSelectedIndex(i);
                        }
                    }

                    //Escreve a descrição do risco
                    InformacoesGeraisDescricaoDeRiscoJTextArea.setText(riscoSelecionado.getDescricao());

                    //Habilita os botões de salvar alterações de risco e remover risco selecionado
                    informacoesGeraisRemoverRiscoJButton.setEnabled(true);
                    informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(true);

                    //******************Na aba Plano de Mitigacao***********************
                    // Reinicia a lista de Planos de Mitigação
                    planoMitigacaoListModel.clear();

                    //Desativa os botões de Plano de Mitigação
                    iniciaBotoesCinzaPlanoMitigacao();

                    // Atualiza a lista de planos, preenche a lista e define eventos da JList
                    atualizaPreencheEventosListaPlanoMitigacao();

                    //Limpa os campos de Plano de Mitigação
                    limparCamposPlanoMitigacao();

                    planoDeMitigacaoAdicionarPlanoJButton.setEnabled(true);
                    //******************Na aba Plano de Contingência*******************

                    // Reinicia a lista de Planos de Contingencia
                    planoContingenciaListModel.clear();

                    //Desativa os botões de Plano de Contingencia
                    iniciaBotoesCinzaPlanoContingencia();

                    // Atualiza a lista de planos, preenche a lista e define eventos da JList
                    atualizaPreencheEventosListaPlanoContingencia();

                    //Limpa os campos de Plano de Contingencia
                    limparCamposPlanoContingencia();

                    planoDeContingenciaAdicionarPlanoJButton.setEnabled(true);
                }
            }
        });
    }

    // -------------------------------*******************************--------------------------************************
    // Fim da aba Informacoes Gerais
    // -------------------------------*******************************--------------------------************************
    // Aba Plano de Mitigacao
    //Instanciando variável que armazena a lista de planos de mitigacao do risco selecionado
    private List<Planomitigacao> listaMitigacao;
    //Instanciando variável que armazena o Plano de Mitigacao Selecionado no momento
    private Planomitigacao planoMitigacaoSelecionado;

    //Criando model da Lista de Planos de Mitigacao
    private DefaultListModel planoMitigacaoListModel = new DefaultListModel();

    //Método que lista os planos de mitigacao do risco selecionado
    private void getListaMitigacaoDoRisco(Risco risco) {
        listaMitigacao = riscosGerenciarRiscosFacade.listarPlanosMitigacao(risco);
    }

    //Método que limpa a lista de Planos de Mitigação
    public void limparListaPlanoMitigacao() {
        planoMitigacaoListModel.clear();
    }

    //Método que preenche a lista de planos de mitigacao
    private void preencheListaPlanosMitigacao() {
        planoMitigacaoListModel = null;
        planoMitigacaoListModel = new DefaultListModel();
        if (listaMitigacao.size() > 0) {
            for (int i = 0; i < listaMitigacao.size(); i++) {
                planoMitigacaoListModel.addElement(listaMitigacao.get(i).getIdentificacaoPlanoMitigacao());
            }
            planoDeMitigacaoListaPlanosJList.setModel(planoMitigacaoListModel);
        }
    }

    // Método que recria a lista de Planos de Mitigação
    public void criarListaPlanoMitigacao() {
        planoMitigacaoListModel = null;
        planoDeMitigacaoListaPlanosJList = new JList();
        planoMitigacaoListModel = new DefaultListModel();
        planoDeMitigacaoListaPlanosJList.setModel(planoMitigacaoListModel);
    }

    //Método que agrupa as funcionalidades de atualizar lista de Planos de Mitigacao, preencher JList e definir eventos da mesma
    public void atualizaPreencheEventosListaPlanoMitigacao() {
        getListaMitigacaoDoRisco(riscoSelecionado);
        //planoMitigacaoListModel.clear();
        preencheListaPlanosMitigacao();
        definirEventosListaPlanoMitigacao();
    }

    //Método que inicia a aba Plano de Mitigação com os botões deselecionados e a lista vazia de Mitigação
    public void iniciaBotoesCinzaPlanoMitigacao() {
        planoDeMitigacaoAdicionarPlanoJButton.setEnabled(false);
        planoDeMitigacaoSalvarAlteracoesJButton.setEnabled(false);
        planoDeMitigacaoRemoverPlanoJButton.setEnabled(false);
    }

    //Método que limpa os campos de Plano de Mitigação
    public void limparCamposPlanoMitigacao() {
        planoDeMitigacaoIdentificacaoJTextField.setText("");
        planoDeMitigacaoResponsavelJTextField.setText("");
        planoDeMitigacaoDataLimiteJDateChooser.setDate(null);
        planoDeMitigacaoDescricaoJTextArea.setText("");
        planoDeMitigacaoComoSeraFeitoJTextArea.setText("");
        planoDeMitigacaoInfAdicionaisJTextArea.setText("");
    }

    //Método que cria um evento quando for selecionado um Plano de Mitigação da Lista de Planos de Mitigação
    private void definirEventosListaPlanoMitigacao() {
        planoDeMitigacaoListaPlanosJList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int indexSelecionado = planoDeMitigacaoListaPlanosJList.getSelectedIndex();
                    String selecao = planoDeMitigacaoListaPlanosJList.getModel().getElementAt(indexSelecionado).toString();
                    //int selecaoInt = Integer.parseInt(selecao);

                    //Procura e define o Plano de Mitigacao selecionado
                    for (int i = 0; i < listaMitigacao.size(); i++) {
                        if (selecao.equals(listaMitigacao.get(i).getIdentificacaoPlanoMitigacao())) {
                            planoMitigacaoSelecionado = listaMitigacao.get(i);
                        }
                    }

                    //Preenchendo as informações nos campos de Plano de Mitigação
                    planoDeMitigacaoIdentificacaoJTextField.setText(planoMitigacaoSelecionado.getIdentificacaoPlanoMitigacao());
                    planoDeMitigacaoResponsavelJTextField.setText(planoMitigacaoSelecionado.getResponsavel());
                    planoDeMitigacaoDescricaoJTextArea.setText(planoMitigacaoSelecionado.getDescricaoPlanoMitigacao());
                    planoDeMitigacaoComoSeraFeitoJTextArea.setText(planoMitigacaoSelecionado.getComoRealizar());
                    planoDeMitigacaoInfAdicionaisJTextArea.setText(planoMitigacaoSelecionado.getInformacoesAdicionais());

                    Calendar dataLimite = Calendar.getInstance();
                    dataLimite.setTime(planoMitigacaoSelecionado.getDataLimite());
                    planoDeMitigacaoDataLimiteJDateChooser.setCalendar(dataLimite);

                    planoDeMitigacaoRemoverPlanoJButton.setEnabled(true);
                    planoDeMitigacaoSalvarAlteracoesJButton.setEnabled(true);
                }
            }
        });
    }

    // Fim da aba Plano de Mitigacao
    // -------------------------------*******************************--------------------------************************
    // Aba Plano de Contingência
    // -------------------------------*******************************--------------------------************************
    //Instanciando variável que armazena a lista de Planos de Contingência
    private List<Planocontingencia> listaContingencia = null;
    //Instanciando variável que armazena o Plano de Contingência selecionado no momento
    private Planocontingencia planoContingenciaSelecionado;

    //Criando model da lista de Planos de Contingência
    private DefaultListModel planoContingenciaListModel = new DefaultListModel();

    // Método que lista os Planos de Contingência do risco selecionado
    private void getListaContingenciaDoRisco(Risco risco) {
        listaContingencia = riscosGerenciarRiscosFacade.listarPlanosContingencia(risco);
    }

    //Método que limpa a lista de Planos de Contingencia
    public void limparListaPlanoContingencia() {
        planoContingenciaListModel.clear();
    }

    // Método que preenche a lista de Planos de Contingência
    private void preencheListaPlanosContingencia() {
        planoContingenciaListModel = null;
        planoContingenciaListModel = new DefaultListModel();
        if (listaContingencia.size() > 0) {
            for (int i = 0; i < listaContingencia.size(); i++) {
                planoContingenciaListModel.addElement(listaContingencia.get(i).getIdentificacaoPlanoContingencia());
            }
            planoDeContingenciaListaPlanosJList.setModel(planoContingenciaListModel);
        }
    }

    //Método que recria a lista de Planos de Contingência
    public void criarListaPlanoContingencia() {
        planoContingenciaListModel = null;
        planoDeContingenciaListaPlanosJList = new JList();
        planoContingenciaListModel = new DefaultListModel();
        planoDeContingenciaListaPlanosJList.setModel(planoContingenciaListModel);
    }

    //Método que agrupa as funcionalidades de atualizar lista de Planos de Contingência, preencher JList e definir eventos da mesma
    private void atualizaPreencheEventosListaPlanoContingencia() {
        getListaContingenciaDoRisco(riscoSelecionado);
        //planoContingenciaListModel.clear();
        preencheListaPlanosContingencia();
        definirEventosListaPlanoContingencia();
    }

    //Método que inicia a aba Plano de Contingência com os botões deselecionados e a lista vazia de Contingência
    public void iniciaBotoesCinzaPlanoContingencia() {
        planoDeContingenciaAdicionarPlanoJButton.setEnabled(false);
        planoDeContingenciaSalvarAlteracoesJButton.setEnabled(false);
        planoDeContingenciaRemoverPlanoJButton.setEnabled(false);
    }

    //Método que limpa os campos de Plano de Contingência
    public void limparCamposPlanoContingencia() {
        planoDeContingenciaIdentificacaoJTextField.setText("");
        planoDeContingenciaResponsavelJTextField.setText("");
        planoDeContingenciaDataLimiteJDateChooser.setDate(null);
        planoDeContingenciaDescricaoJTextArea.setText("");
        planoDeContingenciaComoSeraFeitoJTextArea.setText("");
        planoDeContingenciaInfAdicionaisJTextArea.setText("");
    }

    //Método que cria um evento quando for selecionado um Plano de Contingência da Lista de Planos de Contingência
    private void definirEventosListaPlanoContingencia() {
        planoDeContingenciaListaPlanosJList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int indexSelecionado = planoDeContingenciaListaPlanosJList.getSelectedIndex();
                    String selecao = planoDeContingenciaListaPlanosJList.getModel().getElementAt(indexSelecionado).toString();
                    //int selecaoInt = Integer.parseInt(selecao);

                    //Procura e define o Plano de Contingência selecionado
                    for (int i = 0; i < listaContingencia.size(); i++) {
                        if (selecao.equals(listaContingencia.get(i).getIdentificacaoPlanoContingencia())) {
                            planoContingenciaSelecionado = listaContingencia.get(i);
                        }
                    }

                    //Preenchendo as informações nos campos de Plano de Mitigação
                    planoDeContingenciaIdentificacaoJTextField.setText(planoContingenciaSelecionado.getIdentificacaoPlanoContingencia());
                    planoDeContingenciaResponsavelJTextField.setText(planoContingenciaSelecionado.getResponsavel());
                    planoDeContingenciaDescricaoJTextArea.setText(planoContingenciaSelecionado.getDescricaoPlanoContingencia());
                    planoDeContingenciaComoSeraFeitoJTextArea.setText(planoContingenciaSelecionado.getComoRealizar());
                    planoDeContingenciaInfAdicionaisJTextArea.setText(planoContingenciaSelecionado.getInformacoesAdicionais());

                    Calendar dataLimite = Calendar.getInstance();
                    dataLimite.setTime(planoContingenciaSelecionado.getDataLimite());
                    planoDeContingenciaDataLimiteJDateChooser.setCalendar(dataLimite);

                    planoDeContingenciaRemoverPlanoJButton.setEnabled(true);
                    planoDeContingenciaSalvarAlteracoesJButton.setEnabled(true);
                }
            }
        });
    }

    // Fim da aba Plano de Contingência
    // -------------------------------*******************************--------------------------************************
    // Aba Relações
    // -------------------------------*******************************--------------------------************************
    // *******Para a Lista de riscos que influencia
    //Criando model da lista de riscos que influencia
    private DefaultListModel modelListaRiscosInfluencia = new DefaultListModel();

    //Método que limpa a lista de riscos que influencia
    public void limparListaRiscosInfluencia() {
        modelListaRiscosInfluencia.clear();
    }

    //Método que preenche a lista de riscos que influencia
    private void preencheListaRiscosInfluencia() {
        modelListaRiscosInfluencia = null;
        modelListaRiscosInfluencia = new DefaultListModel();
        if (listaRiscos.size() > 0) {
            for (int i = 0; i < listaRiscos.size(); i++) {
                modelListaRiscosInfluencia.addElement(listaRiscos.get(i).getIdentificacao().toString());
            }
            relacoesListaInfluenciaJList.setModel(modelListaRiscosInfluencia);
        }
    }

    // *******Para a Lista de riscos que são influenciados
    //Criando model da lista de riscos que influencia
    private DefaultListModel modelListaRiscosInfluenciados = new DefaultListModel();

    //Método que limpa a lista de riscos que influencia
    public void limparListaRiscosInfluenciados() {
        modelListaRiscosInfluenciados.clear();
    }

    //Método que preenche a lista de riscos que são influenciados
    private void preencheListaRiscosInfluenciados() {
        modelListaRiscosInfluenciados = null;
        modelListaRiscosInfluenciados = new DefaultListModel();
        if (listaRiscos.size() > 0) {
            for (int i = 0; i < listaRiscos.size(); i++) {
                modelListaRiscosInfluenciados.addElement(listaRiscos.get(i).getIdentificacao().toString());
            }
            relacoesInfluenciadoJList.setModel(modelListaRiscosInfluenciados);
        }
    }

    // Fim da aba Relações
    // -------------------------------*******************************--------------------------************************
    // Aba Histórico de alterações
    // -------------------------------*******************************--------------------------************************
    // TODO Criar eventos para adicionar mudanças para criar Histórico de Alterações
    //Instanciando tabela de Histórico de alterações
    JTable tabelaHistoricoAlteracoes = null;
    //Instanciando modelo dos dados da tabela de Histórico de alterações
    DefaultTableModel modeloTabelaHistoricoAlteracoes = null;

    //Metodo que constrói a tabela de Histórico de Alterações
    private void criaTabelaHistoricoAlteracoes() {
        tabelaHistoricoAlteracoes = new JTable();
        modeloTabelaHistoricoAlteracoes = new DefaultTableModel();
        modeloTabelaHistoricoAlteracoes.setColumnIdentifiers(new String[]{"Alteração", "Data de realização"});
        tabelaHistoricoAlteracoes.setModel(modeloTabelaHistoricoAlteracoes);
        tabelaHistoricoAlteracoesJScrollPane.setViewportView(tabelaHistoricoAlteracoes);
    }

    private void preencheTabelaHistoricoAlteracoes() {
        if (listaHistoricoAlteracao.size() > 0) {
            for (int i = 0; i < listaHistoricoAlteracao.size(); i++) {
                String descricao = listaHistoricoAlteracao.get(i).getDescricaoAlteracao();
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                Date data = listaHistoricoAlteracao.get(i).getDataAlteracao();
                Calendar c = Calendar.getInstance();
                c.setTime(data);
                String dataImprimida = df.format(c.getTime());

                String[] linha = new String[]{descricao, dataImprimida};
                modeloTabelaHistoricoAlteracoes.addRow(linha);
            }
        }
    }

    private void limparTabelaHistoricoAlteracoes() {
        modeloTabelaHistoricoAlteracoes.setNumRows(0);
    }
    // Fim da aba Histórico de alterações
    // -------------------------------*******************************--------------------------************************

    // Aba Subcondições
    // -------------------------------*******************************--------------------------************************
    //Instanciando tabela de subcondições
    private JTable tabelaSubcondicoesJTable = null;
    //Instanciando modelo de dados da tabela de subcondições
    private DefaultTableModel modeloTabelaSubcondicoesJTable = new DefaultTableModel();
    //Instanciando variável que armazena a lista de subcondições do risco selecionado
    private List<Subcondicao> listaSubcondicoes;
    //Instanciando variável que armazena a lista de relações da subcondição selecionada
    private List<Relacaosubcondicao> listaRelacaoSubcondicao;
    //Instanciando variável que armazena todas as tabelas de relação de subcondição
    private List<Relacaosubcondicao> listaTabelasSubcondicao;

    //Criando model da lista de relações de subcondições
    DefaultListModel modeloListaSubcondicoes = new DefaultListModel();

    //Método que constrói a Tabela de Subcondições
    private void criarTabelaSubcondicoes() {
        tabelaSubcondicoesJTable = new JTable();
        modeloTabelaSubcondicoesJTable = new DefaultTableModel();
        modeloTabelaSubcondicoesJTable.setColumnIdentifiers(new String[]{"Identificação da condição", "Grupo de relações", "Ocorreu?"});
        tabelaSubcondicoesJTable.setModel(modeloTabelaSubcondicoesJTable);
        tabelaSubcondicoesJScrollPane.setViewportView(tabelaSubcondicoesJTable);
    }

    //Método que atualiza a lista de subcondicoes do risco selecionado
    private void getListaSubcondicoes(Risco idRisco) {
        listaSubcondicoes = riscosGerenciarRiscosFacade.listarSubcondicoesByIdRisco(idRisco);
    }

    //Método que preenche os dados da Tabela de Subcondições, conforme o risco selecionado
    private void preencherDadosTabelaSubcondicoes() {
        if (listaSubcondicoes.size() > 0) {
            habilitaCamposSubcondicao();
            for (int i = 0; i < listaSubcondicoes.size(); i++) {
                String identificacaoSubcondicao = listaSubcondicoes.get(i).getIdentificacaoSubcondicao().toString();
                String statusSubcondicao = listaSubcondicoes.get(i).getStatusSubcondicao().toString();

                String[] linha = new String[]{identificacaoSubcondicao, "Sem grupo", statusSubcondicao};
                modeloTabelaSubcondicoesJTable.addRow(linha);
            }
        } else {
            desabilitaCamposSubcondicao();
        }
    }

    //Método que reseta a tabela de subcondições
    private void reiniciarTabelaSubcondicoes() {
        modeloTabelaSubcondicoesJTable = null;
        criarTabelaSubcondicoes();
        preencherDadosTabelaSubcondicoes();
        definirEventosTabelaSubcondicoes();
    }

    //Definindo eventos de seleção na tabela de subcondições
    private void definirEventosTabelaSubcondicoes() {
        tabelaSubcondicoesJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selected = tabelaSubcondicoesJTable.getSelectedRow();

                    //Procura a subcondição selecionada
                    for (int i = 0; i < listaSubcondicoes.size(); i++) {
                        if (tabelaSubcondicoesJTable.getValueAt(selected, 0).equals(listaSubcondicoes.get(i).getIdentificacaoSubcondicao())) {
                            subcondicaoSelecionada = listaSubcondicoes.get(i);
                        }

                    }

                    //Primeiro limpa os campos de subcondição
                    limparCamposSubcondicao();
                    //Preenche os campos de informações da subcondição
                    subcondicoesIdentificacaoDaCondicaoJTextField.setText(subcondicaoSelecionada.getIdentificacaoSubcondicao().toString());
                    subcondicoesDescricaoJTextPane.setText(subcondicaoSelecionada.getDescricaoSubcondicao().toString());

                    //Limpa e preenche a lista de relações entre subcondições
                    limparListaRelacoesSubcondicoes();
                    preencheListaRelacoesSubcondicoes();

                    //Verifica se possui relação entre subcondições
                    getRelacaoSubcondicao(subcondicaoSelecionada);
                    getTabelasRelacaoSubcondicao();
                    marcaSubcondicao();
                }
            }
        });
    }

    //Método que limpa os campos de informações da subcondição
    private void limparCamposSubcondicao() {
        subcondicoesDescricaoJTextPane.setText("");
        subcondicoesIdentificacaoDaCondicaoJTextField.setText("");
    }

    //Desabilita campos de texto de subcondição
    private void desabilitaCamposSubcondicao() {
        subcondicoesDescricaoJTextPane.setEnabled(false);
        subcondicoesIdentificacaoDaCondicaoJTextField.setEnabled(false);
        subcondicoesListaRelacoesJList.setEnabled(false);
        subcondicoesListaRelacoesJList.setBackground(Color.LIGHT_GRAY);
    }

    //Habilita campos de texto de subcondicao
    private void habilitaCamposSubcondicao() {
        subcondicoesDescricaoJTextPane.setEnabled(true);
        subcondicoesIdentificacaoDaCondicaoJTextField.setEnabled(true);
        subcondicoesListaRelacoesJList.setEnabled(true);
        subcondicoesListaRelacoesJList.setBackground(Color.WHITE);
    }

    //Método que preenche a lista de grupo de relacoes
    private void preencheListaRelacoesSubcondicoes() {
        modeloListaSubcondicoes = null;
        modeloListaSubcondicoes = new DefaultListModel();
        if (listaSubcondicoes.size() > 0) {
            for (int i = 0; i < listaSubcondicoes.size(); i++) {
                modeloListaSubcondicoes.addElement(listaSubcondicoes.get(i).getIdentificacaoSubcondicao());
            }
            subcondicoesListaRelacoesJList.setModel(modeloListaSubcondicoes);
        }
    }

    //Método que limpa a lista de relações de subcondições
    private void limparListaRelacoesSubcondicoes() {
        modeloListaSubcondicoes.clear();
    }

    //Método que busca as relações da subcondição selecionada
    private void getRelacaoSubcondicao(Subcondicao subcondicao) {
        listaRelacaoSubcondicao = riscosGerenciarRiscosFacade.listarRelacaoSubcondicaoByIdSubcondicao1(subcondicao.getIdSubcondicao());
    }

    //Método que busca todas as tabelas de relações
    private void getTabelasRelacaoSubcondicao() {
        listaTabelasSubcondicao = riscosGerenciarRiscosFacade.listarTabelasSubcondicao();
    }

    //Método que marca as subcondições que possui relação com a subcondição selecionada
    private void marcaSubcondicao() {
        if (listaRelacaoSubcondicao.size() > 0) {
            for (int i = 0; i < listaRelacaoSubcondicao.size(); i++) {
                for (int j = 0; j < listaSubcondicoes.size(); j++) {
                    if (listaRelacaoSubcondicao.get(i).getSubcondicao().getIdentificacaoSubcondicao().equals(listaSubcondicoes.get(j).getIdentificacaoSubcondicao())) {
                        subcondicoesListaRelacoesJList.addSelectionInterval(j, j);
                    }
                }
            }
        }
    }

    //Método que atualiza e preenche as informaçoes de subcondição
    private void atualizaPreencheSubcondicao() {
        getListaSubcondicoes(riscoSelecionado);
        modeloTabelaSubcondicoesJTable = null;
        modeloTabelaSubcondicoesJTable = new DefaultTableModel();
        preencherDadosTabelaSubcondicoes();
        definirEventosTabelaSubcondicoes();
    }

    // Fim da aba Subcondições
    // -------------------------------*******************************--------------------------************************
    //Adicionar Método para preencher o comboBox com as categorias do projeto selecionado
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gerenciarRiscosJPanel = new javax.swing.JPanel();
        gerenciarRiscosJTabbedPane = new javax.swing.JTabbedPane();
        InformacoesGeraisJPanel = new javax.swing.JPanel();
        informacoesGeraisIdentificacaoDeRiscoJLabel = new javax.swing.JLabel();
        informacoesGeraisDataDeIdentificacaoJLabel = new javax.swing.JLabel();
        informacoesGeraisEmissorJLabel = new javax.swing.JLabel();
        informacoesGeraisCategoriaDeRiscoJLabel = new javax.swing.JLabel();
        informacoesGeraisProbabilidadeJLabel = new javax.swing.JLabel();
        informacoesGeraisImpactoJLabel = new javax.swing.JLabel();
        informacoesGeraisEstadoAtualJLabel = new javax.swing.JLabel();
        informacoesGeraisGrauDeSeveridadeJLabel = new javax.swing.JLabel();
        informacoesGeraisIdentificacaoDeRiscoJTextField = new javax.swing.JTextField();
        informacoesGeraisEmissorJTextField = new javax.swing.JTextField();
        informacoesGeraisCategoriaDeRiscoJComboBox = new javax.swing.JComboBox();
        informacoesGeraisImpactoJComboBox = new javax.swing.JComboBox();
        informacoesGeraisSalvarAlteracoesDoRiscoJButton = new javax.swing.JButton();
        informacoesGeraisDescricaoDeRiscoJScrollPane = new javax.swing.JScrollPane();
        InformacoesGeraisDescricaoDeRiscoJTextArea = new javax.swing.JTextArea();
        informacoesGeraisGrauDeSeveridadeJTextField = new javax.swing.JTextField();
        informacoesGeraisProbabilidadeJSpinner = new javax.swing.JSpinner();
        informacoesGeraisProbabilidadePorcentagemJLabel = new javax.swing.JLabel();
        informacoesGeraisDataDeIdentificacaoJDateChooser = new com.toedter.calendar.JDateChooser();
        informacoesGeraisAdicionarNovoRiscoJButton = new javax.swing.JButton();
        informacoesGeraisLimparCamposJButton = new javax.swing.JButton();
        informacoesGeraisRemoverRiscoJButton = new javax.swing.JButton();
        estadoAtualRiscoJLabel = new javax.swing.JLabel();
        RelacoesJPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        relacoesListaInfluenciaJList = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        relacoesInfluenciadoJList = new javax.swing.JList();
        relacoesSalvarAlteracoesJButton = new javax.swing.JButton();
        SubcondicoesJPanel = new javax.swing.JPanel();
        subcondicoesIdentificacaoDaCondicaoJLabel = new javax.swing.JLabel();
        subcondicoesDescricaoJLabel = new javax.swing.JLabel();
        subcondicoesGrupoDeRelacoesJLabel = new javax.swing.JLabel();
        subcondicoesIdentificacaoDaCondicaoJTextField = new javax.swing.JTextField();
        subcondicoesDescricaoJScrollPane = new javax.swing.JScrollPane();
        subcondicoesDescricaoJTextPane = new javax.swing.JTextPane();
        subcondicoesTipoDeRelacaoJLabel = new javax.swing.JLabel();
        subcondicoesTipoDeRelacaoJComboBox = new javax.swing.JComboBox();
        subcondicoesSalvarAlteracoesDaCondicaoJButton = new javax.swing.JButton();
        subcondicoesCriarCondicaoJButton = new javax.swing.JButton();
        tabelaSubcondicoesJScrollPane = new javax.swing.JScrollPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        subcondicoesListaRelacoesJList = new javax.swing.JList();
        PlanoDeMitigacaoJPanel = new javax.swing.JPanel();
        planoDeMitigacaoPlanoDeMitigacaoJLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        planoDeMitigacaoResponsavelJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        planoDeMitigacaoDescricaoJTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        planoDeMitigacaoDataLimiteJDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        planoDeMitigacaoComoSeraFeitoJTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        planoDeMitigacaoInfAdicionaisJTextArea = new javax.swing.JTextArea();
        planoDeMitigacaoIdentificacaoJTextField = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        planoDeMitigacaoListaPlanosJList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        planoDeMitigacaoAdicionarPlanoJButton = new javax.swing.JButton();
        planoDeMitigacaoSalvarAlteracoesJButton = new javax.swing.JButton();
        planoDeMitigacaoRemoverPlanoJButton = new javax.swing.JButton();
        PlanoContigenciaJPanel = new javax.swing.JPanel();
        planoContigenciaPlanoDeContigenciaJLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        planoDeContingenciaListaPlanosJList = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        planoDeContingenciaResponsavelJTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        planoDeContingenciaDataLimiteJDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        planoDeContingenciaDescricaoJTextArea = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        planoDeContingenciaComoSeraFeitoJTextArea = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        planoDeContingenciaInfAdicionaisJTextArea = new javax.swing.JTextArea();
        planoDeContingenciaIdentificacaoJTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        planoDeContingenciaAdicionarPlanoJButton = new javax.swing.JButton();
        planoDeContingenciaRemoverPlanoJButton = new javax.swing.JButton();
        planoDeContingenciaSalvarAlteracoesJButton = new javax.swing.JButton();
        HistoricoDeAlteracoesJPanel = new javax.swing.JPanel();
        historicoDeAlteracoesSalvarAlteracoesDeRiscoJButton = new javax.swing.JButton();
        tabelaHistoricoAlteracoesJScrollPane = new javax.swing.JScrollPane();
        TabelaDeRiscosJPanel = new javax.swing.JPanel();
        tabelaRiscosJScrollPane = new javax.swing.JScrollPane();

        informacoesGeraisIdentificacaoDeRiscoJLabel.setText("Identificação de Risco:");

        informacoesGeraisDataDeIdentificacaoJLabel.setText("Data de Identificação:");

        informacoesGeraisEmissorJLabel.setText("Emissor:");

        informacoesGeraisCategoriaDeRiscoJLabel.setText("Categoria de Risco:");

        informacoesGeraisProbabilidadeJLabel.setText("Probabilidade:");

        informacoesGeraisImpactoJLabel.setText("Impacto:");

        informacoesGeraisEstadoAtualJLabel.setText("Estado Atual:");

        informacoesGeraisGrauDeSeveridadeJLabel.setText("Grau de Severidade:");

        informacoesGeraisIdentificacaoDeRiscoJTextField.setToolTipText("");
        informacoesGeraisIdentificacaoDeRiscoJTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        informacoesGeraisImpactoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Crítico", "Alto", "Médio", "Baixo" }));

        informacoesGeraisSalvarAlteracoesDoRiscoJButton.setText("Salvar alterações do risco");
        informacoesGeraisSalvarAlteracoesDoRiscoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesGeraisSalvarAlteracoesDoRiscoJButtonActionPerformed(evt);
            }
        });

        InformacoesGeraisDescricaoDeRiscoJTextArea.setColumns(20);
        InformacoesGeraisDescricaoDeRiscoJTextArea.setRows(5);
        InformacoesGeraisDescricaoDeRiscoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descrição de Risco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        informacoesGeraisDescricaoDeRiscoJScrollPane.setViewportView(InformacoesGeraisDescricaoDeRiscoJTextArea);

        informacoesGeraisProbabilidadePorcentagemJLabel.setText("%");

        informacoesGeraisAdicionarNovoRiscoJButton.setText("Adicionar Novo Risco");
        informacoesGeraisAdicionarNovoRiscoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesGeraisAdicionarNovoRiscoJButtonActionPerformed(evt);
            }
        });

        informacoesGeraisLimparCamposJButton.setText("Limpar Campos");
        informacoesGeraisLimparCamposJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesGeraisLimparCamposJButtonActionPerformed(evt);
            }
        });

        informacoesGeraisRemoverRiscoJButton.setText("Remover Risco Selecionado");
        informacoesGeraisRemoverRiscoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesGeraisRemoverRiscoJButtonActionPerformed(evt);
            }
        });

        estadoAtualRiscoJLabel.setText(" ");

        javax.swing.GroupLayout InformacoesGeraisJPanelLayout = new javax.swing.GroupLayout(InformacoesGeraisJPanel);
        InformacoesGeraisJPanel.setLayout(InformacoesGeraisJPanelLayout);
        InformacoesGeraisJPanelLayout.setHorizontalGroup(
            InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addComponent(informacoesGeraisEstadoAtualJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadoAtualRiscoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(informacoesGeraisProbabilidadeJLabel)
                                    .addComponent(informacoesGeraisCategoriaDeRiscoJLabel)
                                    .addComponent(informacoesGeraisImpactoJLabel))
                                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(informacoesGeraisCategoriaDeRiscoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(informacoesGeraisImpactoJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                                .addComponent(informacoesGeraisProbabilidadeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(informacoesGeraisProbabilidadePorcentagemJLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE))))))
                            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(informacoesGeraisIdentificacaoDeRiscoJLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(informacoesGeraisEmissorJLabel)
                                            .addComponent(informacoesGeraisDataDeIdentificacaoJLabel))
                                        .addGap(2, 2, 2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(informacoesGeraisEmissorJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(informacoesGeraisIdentificacaoDeRiscoJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(informacoesGeraisDataDeIdentificacaoJDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(31, 31, 31)))
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(informacoesGeraisAdicionarNovoRiscoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(informacoesGeraisLimparCamposJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(informacoesGeraisSalvarAlteracoesDoRiscoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(informacoesGeraisRemoverRiscoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addComponent(informacoesGeraisGrauDeSeveridadeJLabel)
                        .addGap(18, 18, 18)
                        .addComponent(informacoesGeraisGrauDeSeveridadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(informacoesGeraisDescricaoDeRiscoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        InformacoesGeraisJPanelLayout.setVerticalGroup(
            InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisIdentificacaoDeRiscoJLabel)
                            .addComponent(informacoesGeraisIdentificacaoDeRiscoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(informacoesGeraisDataDeIdentificacaoJLabel)
                            .addComponent(informacoesGeraisDataDeIdentificacaoJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisEmissorJLabel)
                            .addComponent(informacoesGeraisEmissorJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisCategoriaDeRiscoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisCategoriaDeRiscoJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisProbabilidadeJLabel)
                            .addComponent(informacoesGeraisProbabilidadeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisProbabilidadePorcentagemJLabel))
                        .addGap(13, 13, 13)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisImpactoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisImpactoJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisEstadoAtualJLabel)
                            .addComponent(estadoAtualRiscoJLabel))
                        .addGap(25, 25, 25))
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addComponent(informacoesGeraisDescricaoDeRiscoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisSalvarAlteracoesDoRiscoJButton)
                            .addComponent(informacoesGeraisLimparCamposJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisRemoverRiscoJButton)
                            .addComponent(informacoesGeraisAdicionarNovoRiscoJButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisGrauDeSeveridadeJLabel)
                            .addComponent(informacoesGeraisGrauDeSeveridadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66))
        );

        gerenciarRiscosJTabbedPane.addTab("Informações Gerais", InformacoesGeraisJPanel);

        jLabel13.setText("O risco influencia em:");

        jScrollPane1.setViewportView(relacoesListaInfluenciaJList);

        jLabel14.setText("O risco é influenciado por:");

        jScrollPane10.setViewportView(relacoesInfluenciadoJList);

        relacoesSalvarAlteracoesJButton.setText("Salvar Alterações");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(relacoesSalvarAlteracoesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                    .addComponent(jLabel13))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(88, 88, 88))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(relacoesSalvarAlteracoesJButton))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(jScrollPane10))))
                .addContainerGap())
        );

        javax.swing.GroupLayout RelacoesJPanelLayout = new javax.swing.GroupLayout(RelacoesJPanel);
        RelacoesJPanel.setLayout(RelacoesJPanelLayout);
        RelacoesJPanelLayout.setHorizontalGroup(
            RelacoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RelacoesJPanelLayout.setVerticalGroup(
            RelacoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gerenciarRiscosJTabbedPane.addTab("Relações", RelacoesJPanel);

        subcondicoesIdentificacaoDaCondicaoJLabel.setText("Identificação da Condição:");

        subcondicoesDescricaoJLabel.setText("Descrição:");

        subcondicoesGrupoDeRelacoesJLabel.setText("Grupo de Relações:");

        subcondicoesDescricaoJScrollPane.setViewportView(subcondicoesDescricaoJTextPane);

        subcondicoesTipoDeRelacaoJLabel.setText("Tipo de Relação:");

        subcondicoesTipoDeRelacaoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E", "OU" }));
        subcondicoesTipoDeRelacaoJComboBox.setBorder(null);

        subcondicoesSalvarAlteracoesDaCondicaoJButton.setText("Salvar alterações da condição");
        subcondicoesSalvarAlteracoesDaCondicaoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subcondicoesSalvarAlteracoesDaCondicaoJButtonActionPerformed(evt);
            }
        });

        subcondicoesCriarCondicaoJButton.setText("Criar nova condição");
        subcondicoesCriarCondicaoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subcondicoesCriarCondicaoJButtonActionPerformed(evt);
            }
        });

        jScrollPane11.setViewportView(subcondicoesListaRelacoesJList);

        javax.swing.GroupLayout SubcondicoesJPanelLayout = new javax.swing.GroupLayout(SubcondicoesJPanel);
        SubcondicoesJPanel.setLayout(SubcondicoesJPanelLayout);
        SubcondicoesJPanelLayout.setHorizontalGroup(
            SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                        .addComponent(subcondicoesIdentificacaoDaCondicaoJLabel)
                        .addGap(10, 10, 10)
                        .addComponent(subcondicoesIdentificacaoDaCondicaoJTextField))
                    .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                        .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subcondicoesGrupoDeRelacoesJLabel)
                            .addComponent(subcondicoesDescricaoJLabel))
                        .addGap(10, 10, 10)
                        .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                                        .addComponent(subcondicoesTipoDeRelacaoJLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(subcondicoesTipoDeRelacaoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                                        .addComponent(subcondicoesSalvarAlteracoesDaCondicaoJButton))
                                    .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(subcondicoesCriarCondicaoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(subcondicoesDescricaoJScrollPane)))
                    .addComponent(tabelaSubcondicoesJScrollPane))
                .addContainerGap())
        );
        SubcondicoesJPanelLayout.setVerticalGroup(
            SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subcondicoesIdentificacaoDaCondicaoJLabel)
                    .addComponent(subcondicoesIdentificacaoDaCondicaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subcondicoesDescricaoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subcondicoesDescricaoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                        .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SubcondicoesJPanelLayout.createSequentialGroup()
                                .addGap(0, 5, Short.MAX_VALUE)
                                .addComponent(subcondicoesSalvarAlteracoesDaCondicaoJButton))
                            .addGroup(SubcondicoesJPanelLayout.createSequentialGroup()
                                .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(subcondicoesTipoDeRelacaoJLabel)
                                        .addComponent(subcondicoesTipoDeRelacaoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(subcondicoesGrupoDeRelacoesJLabel))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subcondicoesCriarCondicaoJButton))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addComponent(tabelaSubcondicoesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gerenciarRiscosJTabbedPane.addTab("Subcondições", SubcondicoesJPanel);

        planoDeMitigacaoPlanoDeMitigacaoJLabel.setText("Plano de Mitigação:");

        jLabel1.setText("Identificação do Plano: ");

        jLabel2.setText("Responsável: ");

        jLabel3.setText("Descrição:");

        planoDeMitigacaoDescricaoJTextArea.setColumns(20);
        planoDeMitigacaoDescricaoJTextArea.setLineWrap(true);
        planoDeMitigacaoDescricaoJTextArea.setRows(1);
        planoDeMitigacaoDescricaoJTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(planoDeMitigacaoDescricaoJTextArea);

        jLabel4.setText("Data Limite: ");

        jLabel5.setText("Como será feito:");

        planoDeMitigacaoComoSeraFeitoJTextArea.setColumns(20);
        planoDeMitigacaoComoSeraFeitoJTextArea.setLineWrap(true);
        planoDeMitigacaoComoSeraFeitoJTextArea.setRows(1);
        planoDeMitigacaoComoSeraFeitoJTextArea.setWrapStyleWord(true);
        jScrollPane3.setViewportView(planoDeMitigacaoComoSeraFeitoJTextArea);

        jLabel6.setText("Informações Adicionais:");

        planoDeMitigacaoInfAdicionaisJTextArea.setColumns(20);
        planoDeMitigacaoInfAdicionaisJTextArea.setLineWrap(true);
        planoDeMitigacaoInfAdicionaisJTextArea.setRows(1);
        planoDeMitigacaoInfAdicionaisJTextArea.setWrapStyleWord(true);
        jScrollPane4.setViewportView(planoDeMitigacaoInfAdicionaisJTextArea);

        jScrollPane9.setViewportView(planoDeMitigacaoListaPlanosJList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(43, 43, 43)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(planoDeMitigacaoResponsavelJTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(planoDeMitigacaoDataLimiteJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(planoDeMitigacaoIdentificacaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(planoDeMitigacaoIdentificacaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(planoDeMitigacaoResponsavelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(planoDeMitigacaoDataLimiteJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9))
        );

        planoDeMitigacaoAdicionarPlanoJButton.setText("Adicionar Plano");
        planoDeMitigacaoAdicionarPlanoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoDeMitigacaoAdicionarPlanoJButtonActionPerformed(evt);
            }
        });

        planoDeMitigacaoSalvarAlteracoesJButton.setText("Salvar Alterações do Plano");
        planoDeMitigacaoSalvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoDeMitigacaoSalvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        planoDeMitigacaoRemoverPlanoJButton.setText("Remover Plano");
        planoDeMitigacaoRemoverPlanoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoDeMitigacaoRemoverPlanoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(planoDeMitigacaoRemoverPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(planoDeMitigacaoSalvarAlteracoesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(planoDeMitigacaoAdicionarPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(planoDeMitigacaoAdicionarPlanoJButton)
                .addGap(5, 5, 5)
                .addComponent(planoDeMitigacaoRemoverPlanoJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(planoDeMitigacaoSalvarAlteracoesJButton))
        );

        javax.swing.GroupLayout PlanoDeMitigacaoJPanelLayout = new javax.swing.GroupLayout(PlanoDeMitigacaoJPanel);
        PlanoDeMitigacaoJPanel.setLayout(PlanoDeMitigacaoJPanelLayout);
        PlanoDeMitigacaoJPanelLayout.setHorizontalGroup(
            PlanoDeMitigacaoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlanoDeMitigacaoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlanoDeMitigacaoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlanoDeMitigacaoJPanelLayout.createSequentialGroup()
                        .addComponent(planoDeMitigacaoPlanoDeMitigacaoJLabel)
                        .addContainerGap(749, Short.MAX_VALUE))
                    .addGroup(PlanoDeMitigacaoJPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PlanoDeMitigacaoJPanelLayout.setVerticalGroup(
            PlanoDeMitigacaoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlanoDeMitigacaoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(planoDeMitigacaoPlanoDeMitigacaoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PlanoDeMitigacaoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PlanoDeMitigacaoJPanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        gerenciarRiscosJTabbedPane.addTab("Plano de Mitigação", PlanoDeMitigacaoJPanel);

        planoContigenciaPlanoDeContigenciaJLabel.setText("Plano de Contigência:");

        jScrollPane5.setViewportView(planoDeContingenciaListaPlanosJList);

        jLabel7.setText("Identificação do Plano: ");

        jLabel8.setText("Responsável:");

        jLabel9.setText("Data Limite:");

        jLabel10.setText("Descrição:");

        planoDeContingenciaDescricaoJTextArea.setColumns(20);
        planoDeContingenciaDescricaoJTextArea.setLineWrap(true);
        planoDeContingenciaDescricaoJTextArea.setRows(1);
        planoDeContingenciaDescricaoJTextArea.setWrapStyleWord(true);
        jScrollPane6.setViewportView(planoDeContingenciaDescricaoJTextArea);

        jLabel11.setText("Como será feito:");

        planoDeContingenciaComoSeraFeitoJTextArea.setColumns(20);
        planoDeContingenciaComoSeraFeitoJTextArea.setLineWrap(true);
        planoDeContingenciaComoSeraFeitoJTextArea.setRows(1);
        planoDeContingenciaComoSeraFeitoJTextArea.setWrapStyleWord(true);
        jScrollPane7.setViewportView(planoDeContingenciaComoSeraFeitoJTextArea);

        jLabel12.setText("Informações Adicionais:");

        planoDeContingenciaInfAdicionaisJTextArea.setColumns(20);
        planoDeContingenciaInfAdicionaisJTextArea.setLineWrap(true);
        planoDeContingenciaInfAdicionaisJTextArea.setRows(1);
        planoDeContingenciaInfAdicionaisJTextArea.setWrapStyleWord(true);
        jScrollPane8.setViewportView(planoDeContingenciaInfAdicionaisJTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(37, 37, 37)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(planoDeContingenciaResponsavelJTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(planoDeContingenciaDataLimiteJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(planoDeContingenciaIdentificacaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(planoDeContingenciaIdentificacaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(planoDeContingenciaResponsavelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(planoDeContingenciaDataLimiteJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        planoDeContingenciaAdicionarPlanoJButton.setText("Adicionar Plano");
        planoDeContingenciaAdicionarPlanoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoDeContingenciaAdicionarPlanoJButtonActionPerformed(evt);
            }
        });

        planoDeContingenciaRemoverPlanoJButton.setText("Remover Plano");
        planoDeContingenciaRemoverPlanoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoDeContingenciaRemoverPlanoJButtonActionPerformed(evt);
            }
        });

        planoDeContingenciaSalvarAlteracoesJButton.setText("Salvar Alterações do Plano");
        planoDeContingenciaSalvarAlteracoesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoDeContingenciaSalvarAlteracoesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(planoDeContingenciaSalvarAlteracoesJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(planoDeContingenciaRemoverPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(planoDeContingenciaAdicionarPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(planoDeContingenciaAdicionarPlanoJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(planoDeContingenciaRemoverPlanoJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(planoDeContingenciaSalvarAlteracoesJButton))
        );

        javax.swing.GroupLayout PlanoContigenciaJPanelLayout = new javax.swing.GroupLayout(PlanoContigenciaJPanel);
        PlanoContigenciaJPanel.setLayout(PlanoContigenciaJPanelLayout);
        PlanoContigenciaJPanelLayout.setHorizontalGroup(
            PlanoContigenciaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlanoContigenciaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlanoContigenciaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlanoContigenciaJPanelLayout.createSequentialGroup()
                        .addComponent(planoContigenciaPlanoDeContigenciaJLabel)
                        .addContainerGap(738, Short.MAX_VALUE))
                    .addGroup(PlanoContigenciaJPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PlanoContigenciaJPanelLayout.setVerticalGroup(
            PlanoContigenciaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlanoContigenciaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(planoContigenciaPlanoDeContigenciaJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PlanoContigenciaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlanoContigenciaJPanelLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(219, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)))
        );

        gerenciarRiscosJTabbedPane.addTab("Plano de Contigência", PlanoContigenciaJPanel);

        historicoDeAlteracoesSalvarAlteracoesDeRiscoJButton.setText("Salvar alterações de risco");

        javax.swing.GroupLayout HistoricoDeAlteracoesJPanelLayout = new javax.swing.GroupLayout(HistoricoDeAlteracoesJPanel);
        HistoricoDeAlteracoesJPanel.setLayout(HistoricoDeAlteracoesJPanelLayout);
        HistoricoDeAlteracoesJPanelLayout.setHorizontalGroup(
            HistoricoDeAlteracoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoricoDeAlteracoesJPanelLayout.createSequentialGroup()
                .addGroup(HistoricoDeAlteracoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HistoricoDeAlteracoesJPanelLayout.createSequentialGroup()
                        .addContainerGap(687, Short.MAX_VALUE)
                        .addComponent(historicoDeAlteracoesSalvarAlteracoesDeRiscoJButton))
                    .addComponent(tabelaHistoricoAlteracoesJScrollPane))
                .addContainerGap())
        );
        HistoricoDeAlteracoesJPanelLayout.setVerticalGroup(
            HistoricoDeAlteracoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HistoricoDeAlteracoesJPanelLayout.createSequentialGroup()
                .addComponent(tabelaHistoricoAlteracoesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historicoDeAlteracoesSalvarAlteracoesDeRiscoJButton)
                .addContainerGap())
        );

        gerenciarRiscosJTabbedPane.addTab("Histórico de Alterações", HistoricoDeAlteracoesJPanel);

        TabelaDeRiscosJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabela de Riscos"));

        javax.swing.GroupLayout TabelaDeRiscosJPanelLayout = new javax.swing.GroupLayout(TabelaDeRiscosJPanel);
        TabelaDeRiscosJPanel.setLayout(TabelaDeRiscosJPanelLayout);
        TabelaDeRiscosJPanelLayout.setHorizontalGroup(
            TabelaDeRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabelaDeRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabelaRiscosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                .addContainerGap())
        );
        TabelaDeRiscosJPanelLayout.setVerticalGroup(
            TabelaDeRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaRiscosJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gerenciarRiscosJPanelLayout = new javax.swing.GroupLayout(gerenciarRiscosJPanel);
        gerenciarRiscosJPanel.setLayout(gerenciarRiscosJPanelLayout);
        gerenciarRiscosJPanelLayout.setHorizontalGroup(
            gerenciarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gerenciarRiscosJPanelLayout.createSequentialGroup()
                .addGroup(gerenciarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gerenciarRiscosJPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TabelaDeRiscosJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gerenciarRiscosJTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        gerenciarRiscosJPanelLayout.setVerticalGroup(
            gerenciarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gerenciarRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gerenciarRiscosJTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabelaDeRiscosJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gerenciarRiscosJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 857, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gerenciarRiscosJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void informacoesGeraisAdicionarNovoRiscoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisAdicionarNovoRiscoJButtonActionPerformed
        // Pertence a aba Informacoes Gerais
        Risco novoRisco = new Risco();
        boolean valorNulo = false;
        novoRisco.setEmissor(informacoesGeraisEmissorJTextField.getText());
        if (novoRisco.getEmissor() == null) {
            valorNulo = true;
        }
        novoRisco.setDataIdentificacao(informacoesGeraisDataDeIdentificacaoJDateChooser.getDate());
        if (novoRisco.getDataIdentificacao() == null) {
            valorNulo = true;
        }
        novoRisco.setDescricao(InformacoesGeraisDescricaoDeRiscoJTextArea.getText());
        if (novoRisco.getDescricao() == null) {
            valorNulo = true;
        }
        novoRisco.setIdentificacao(informacoesGeraisIdentificacaoDeRiscoJTextField.getText());
        if (novoRisco.getIdentificacao() == null) {
            valorNulo = true;
        }
        novoRisco.setProbabilidade(Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString()));
        if (novoRisco.getProbabilidade() == 0) {
            valorNulo = true;
        }

        if (valorNulo == true) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios, preencha-os antes de adicionar um novo risco");
        } else {

            boolean probabilidadeInvalida = false;
            boolean riscoOcorreu = false;

            if (informacoesGeraisCategoriaDeRiscoJComboBox.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não há Categorias de Risco disponíveis no projeto.");
            } else {
                if (novoRisco.getProbabilidade() >= 1 && novoRisco.getProbabilidade() <= 100) {
                    //nada
                    if (novoRisco.getProbabilidade() == 100) {
                        riscoOcorreu = true;
                    }
                } else {
                    probabilidadeInvalida = true;
                }

                if (probabilidadeInvalida == true) {
                    JOptionPane.showMessageDialog(null, "Valor de Probabilidade Inválido");
                } else {
                    boolean identificacaoRepetido = false;
                    for (int i = 0; i < listaRiscos.size(); i++) {
                        if (novoRisco.getIdentificacao().equals(listaRiscos.get(i).getIdentificacao())) {
                            identificacaoRepetido = true;
                        }
                    }

                    if (identificacaoRepetido == false) {

                        String categoriaSelecionada = informacoesGeraisCategoriaDeRiscoJComboBox.getSelectedItem().toString();
                        Categoriaderisco categoriaComboBox = null;
                        for (int i = 0; i < listaCategorias.size(); i++) {
                            if (listaCategorias.get(i).getNomeCategoria().equals(categoriaSelecionada)) {
                                categoriaComboBox = listaCategorias.get(i);
                            }
                        }

                        Contem contemComboBox = new Contem();
                        contemComboBox.setCategoriaderisco(categoriaComboBox);
                        contemComboBox.setProjeto(projetoSelecionado);

                        ContemPK contemPK = new ContemPK();

                        contemPK.setIdCategoriaDeRisco(categoriaComboBox.getIdCategoriaDeRisco());
                        contemPK.setIdProjeto(projetoSelecionado.getIdProjeto());
                        getListaRiscos();
                        contemComboBox.setContemPK(contemPK);

                        novoRisco.setContem(contemComboBox);

                        String impactoSelecionado = informacoesGeraisImpactoJComboBox.getSelectedItem().toString();
                        novoRisco.setImpacto(impactoSelecionado);

                        novoRisco.setGrauSeveridade(Integer.parseInt(informacoesGeraisGrauDeSeveridadeJTextField.getText()));
                        if (novoRisco.getGrauSeveridade() == 0) {
                            int grauSeveridade = 0;
                            double auxiliar = 0;

                            if (novoRisco.getImpacto() == "Alto") {
                                auxiliar = novoRisco.getProbabilidade() * 0.75;
                                grauSeveridade = (int) Math.round(auxiliar);
                            } else if (novoRisco.getImpacto() == "Médio") {
                                auxiliar = novoRisco.getProbabilidade() * 0.5;
                                grauSeveridade = (int) Math.round(auxiliar);
                            } else if (novoRisco.getImpacto() == "Baixo") {
                                auxiliar = novoRisco.getProbabilidade() * 0.25;
                                grauSeveridade = (int) Math.round(auxiliar);
                            } else if (novoRisco.getImpacto() == "Crítico") {
                                grauSeveridade = novoRisco.getProbabilidade();
                            }

                            novoRisco.setGrauSeveridade(grauSeveridade);
                        }

                        if (riscoOcorreu == false) {
                            novoRisco.setStatusRisco("Novo");
                        } else {
                            novoRisco.setStatusRisco("Contingenciando");
                        }

                        riscosGerenciarRiscosFacade.adicionarRisco(novoRisco);
                        JOptionPane.showMessageDialog(null, "Risco adicionado com sucesso");
                        reiniciarTabelaRiscos();
                        Historicoalteracao historico = new Historicoalteracao();
                        historico.setDescricaoAlteracao("Risco criado.");
                        Calendar c = Calendar.getInstance();
                        historico.setDataAlteracao(c.getTime());
                        historico.setIdRisco(riscoSelecionado);
                        riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                        getListaHistoricoAlteracoes(riscoSelecionado);
                        limparTabelaHistoricoAlteracoes();
                        preencheTabelaHistoricoAlteracoes();
                    } else {
                        JOptionPane.showMessageDialog(null, "Um risco com a mesma identificação já foi previamente adicionado!\n"
                                + "Mude o nome da Identificação");
                    }
                }
            }

        }


    }//GEN-LAST:event_informacoesGeraisAdicionarNovoRiscoJButtonActionPerformed

    private void informacoesGeraisLimparCamposJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisLimparCamposJButtonActionPerformed
        //Pertence a aba Informacoes Gerais
        limparCampos();
        preencheComboBox();
        iniciaBotosRiscoCinza();
        JOptionPane.showMessageDialog(null, "Os campos foram limpados");
    }//GEN-LAST:event_informacoesGeraisLimparCamposJButtonActionPerformed

    private void informacoesGeraisSalvarAlteracoesDoRiscoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisSalvarAlteracoesDoRiscoJButtonActionPerformed
        //Pertence a aba Informacoes Gerais
        getListaCategorias();
        if (riscoSelecionado.getIdRisco() > 0) {
            int linhaSelecionada = tabelaRiscosJTable.getSelectedRow();
            riscoSelecionado.setEmissor(informacoesGeraisEmissorJTextField.getText());
            riscoSelecionado.setDataIdentificacao(informacoesGeraisDataDeIdentificacaoJDateChooser.getDate());
            riscoSelecionado.setDescricao(InformacoesGeraisDescricaoDeRiscoJTextArea.getText());
            riscoSelecionado.setIdentificacao(informacoesGeraisIdentificacaoDeRiscoJTextField.getText());
            riscoSelecionado.setGrauSeveridade(Integer.parseInt(informacoesGeraisGrauDeSeveridadeJTextField.getText()));
            riscoSelecionado.setProbabilidade(Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString()));

            String categoriaSelecionada = informacoesGeraisCategoriaDeRiscoJComboBox.getSelectedItem().toString();
            Categoriaderisco categoriaComboBox = null;
            for (int i = 0; i < listaCategorias.size(); i++) {
                if (listaCategorias.get(i).getNomeCategoria().equals(categoriaSelecionada)) {
                    categoriaComboBox = listaCategorias.get(i);
                }
            }

            Contem contemComboBox = new Contem();
            contemComboBox = riscoSelecionado.getContem();
            contemComboBox.setCategoriaderisco(categoriaComboBox);

            riscoSelecionado.setContem(contemComboBox);

            String impactoSelecionado = informacoesGeraisImpactoJComboBox.getSelectedItem().toString();
            riscoSelecionado.setImpacto(impactoSelecionado);

            riscosGerenciarRiscosFacade.editarRisco(riscoSelecionado);
            JOptionPane.showMessageDialog(null, "Informações do risco alteradas com sucesso");
            reiniciarTabelaRiscos();
            tabelaRiscosJTable.setRowSelectionInterval(linhaSelecionada, linhaSelecionada);
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Informações do risco alteradas.");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        }
    }//GEN-LAST:event_informacoesGeraisSalvarAlteracoesDoRiscoJButtonActionPerformed

    private void informacoesGeraisRemoverRiscoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisRemoverRiscoJButtonActionPerformed
        //Pertence a aba Informacoes Gerais
        // TODO Alterar método para verificar tabelas relacionadas
        if (riscoSelecionado.getIdRisco() > 0) {
            riscosGerenciarRiscosFacade.removerRisco(riscoSelecionado);
            JOptionPane.showMessageDialog(null, "Risco removido com sucesso");
            reiniciarTabelaRiscos();
        }
    }//GEN-LAST:event_informacoesGeraisRemoverRiscoJButtonActionPerformed

    private void planoDeMitigacaoRemoverPlanoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeMitigacaoRemoverPlanoJButtonActionPerformed
        //Pertence a aba Plano de Mitigação
        if (planoMitigacaoSelecionado.getIdPlanoMitigacao() > 0) {
            riscosGerenciarRiscosFacade.removerPlanoMitigacao(planoMitigacaoSelecionado);
            JOptionPane.showMessageDialog(null, "Plano de Mitigação removido com sucesso");
            //Método que 'reinicia' a lista de Planos de Mitigação
            planoMitigacaoListModel.clear();
            limparCamposPlanoMitigacao();
            iniciaBotoesCinzaPlanoMitigacao();
            planoDeMitigacaoAdicionarPlanoJButton.setEnabled(true);
            atualizaPreencheEventosListaPlanoMitigacao();
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Plano de mitigação " + planoMitigacaoSelecionado.getIdentificacaoPlanoMitigacao() + " removido.");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        }
    }//GEN-LAST:event_planoDeMitigacaoRemoverPlanoJButtonActionPerformed

    private void planoDeMitigacaoSalvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeMitigacaoSalvarAlteracoesJButtonActionPerformed
        //Pertence a aba Plano de Mitigação
        if (planoMitigacaoSelecionado.getIdPlanoMitigacao() > 0) {
            int indexSelecionado = planoDeMitigacaoListaPlanosJList.getSelectedIndex();

            planoMitigacaoSelecionado.setIdentificacaoPlanoMitigacao(planoDeMitigacaoIdentificacaoJTextField.getText());
            planoMitigacaoSelecionado.setResponsavel(planoDeMitigacaoResponsavelJTextField.getText());
            planoMitigacaoSelecionado.setDataLimite(planoDeMitigacaoDataLimiteJDateChooser.getDate());
            planoMitigacaoSelecionado.setDescricaoPlanoMitigacao(planoDeMitigacaoDescricaoJTextArea.getText());
            planoMitigacaoSelecionado.setComoRealizar(planoDeMitigacaoComoSeraFeitoJTextArea.getText());
            planoMitigacaoSelecionado.setInformacoesAdicionais(planoDeMitigacaoInfAdicionaisJTextArea.getText());

            riscosGerenciarRiscosFacade.editarPlanoMitigacao(planoMitigacaoSelecionado);
            JOptionPane.showMessageDialog(null, "Informações do Plano de Mitigacao alteradas com sucesso");
            atualizaPreencheEventosListaPlanoMitigacao();
            planoDeMitigacaoListaPlanosJList.setSelectedIndex(indexSelecionado);
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Plano de mitigação " + planoContingenciaSelecionado.getIdentificacaoPlanoContingencia() + " alterado.");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        }
    }//GEN-LAST:event_planoDeMitigacaoSalvarAlteracoesJButtonActionPerformed

    private void planoDeMitigacaoAdicionarPlanoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeMitigacaoAdicionarPlanoJButtonActionPerformed
        //Pertence a aba Plano de Mitigação

        Planomitigacao novoPlanoMitigacao = new Planomitigacao();
        novoPlanoMitigacao.setResponsavel(planoDeMitigacaoResponsavelJTextField.getText());
        //Calendar dataIdentificacao = Calendar.getInstance();
        //Date data = dataIdentificacao.getTime();

        novoPlanoMitigacao.setDataLimite(planoDeMitigacaoDataLimiteJDateChooser.getDate());
        novoPlanoMitigacao.setDescricaoPlanoMitigacao(planoDeMitigacaoDescricaoJTextArea.getText());
        novoPlanoMitigacao.setComoRealizar(planoDeMitigacaoComoSeraFeitoJTextArea.getText());
        novoPlanoMitigacao.setInformacoesAdicionais(planoDeMitigacaoInfAdicionaisJTextArea.getText());
        novoPlanoMitigacao.setIdRisco(riscoSelecionado);
        novoPlanoMitigacao.setIdentificacaoPlanoMitigacao(planoDeMitigacaoIdentificacaoJTextField.getText());

        boolean nulo = false;

        if (novoPlanoMitigacao.getDataLimite() == null) {
            nulo = true;
        }
        if (novoPlanoMitigacao.getResponsavel() == null) {
            nulo = true;
        }
        if (novoPlanoMitigacao.getComoRealizar() == null) {
            nulo = true;
        }
        if (novoPlanoMitigacao.getIdentificacaoPlanoMitigacao() == null) {
            nulo = true;
        }
        if (novoPlanoMitigacao.getDescricaoPlanoMitigacao() == null) {
            nulo = true;
        }

        if (nulo == true) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos. Preencha-os antes de adicionar um plano de mitigação.");
        } else {
            boolean repetido = false;
            for (int i = 0; i < listaMitigacao.size(); i++) {
                if (novoPlanoMitigacao.getIdentificacaoPlanoMitigacao().equals(listaMitigacao.get(i).getIdentificacaoPlanoMitigacao())) {
                    repetido = true;
                }
            }

            if (repetido == true) {
                JOptionPane.showMessageDialog(null, "Já existe um identificador para tal Plano de Mitigação. Escolha outro nome.");
            } else {
                riscosGerenciarRiscosFacade.adicionarPlanoMitigacao(novoPlanoMitigacao);
                iniciaBotoesCinzaPlanoMitigacao();
                planoDeMitigacaoAdicionarPlanoJButton.setEnabled(true);
                atualizaPreencheEventosListaPlanoMitigacao();
                limparCamposPlanoMitigacao();

                JOptionPane.showMessageDialog(null, "Plano de Mitigação criado com sucesso para o risco: " + riscoSelecionado.getIdentificacao());
                Historicoalteracao historico = new Historicoalteracao();
                historico.setDescricaoAlteracao("Plano de mitigacao " + novoPlanoMitigacao.getIdentificacaoPlanoMitigacao() + " criado.");
                Calendar c = Calendar.getInstance();
                historico.setDataAlteracao(c.getTime());
                historico.setIdRisco(riscoSelecionado);
                riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                getListaHistoricoAlteracoes(riscoSelecionado);
                limparTabelaHistoricoAlteracoes();
                preencheTabelaHistoricoAlteracoes();
            }

        }

    }//GEN-LAST:event_planoDeMitigacaoAdicionarPlanoJButtonActionPerformed

    private void planoDeContingenciaAdicionarPlanoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeContingenciaAdicionarPlanoJButtonActionPerformed
        //Pertence a aba Plano de Contingencia
        Planocontingencia novoPlanoContingencia = new Planocontingencia();
        novoPlanoContingencia.setResponsavel(planoDeContingenciaResponsavelJTextField.getText());
        //Calendar dataIdentificacao = Calendar.getInstance();
        //Date data = dataIdentificacao.getTime();

        novoPlanoContingencia.setDataLimite(planoDeContingenciaDataLimiteJDateChooser.getDate());
        novoPlanoContingencia.setDescricaoPlanoContingencia(planoDeContingenciaDescricaoJTextArea.getText());
        novoPlanoContingencia.setComoRealizar(planoDeContingenciaComoSeraFeitoJTextArea.getText());
        novoPlanoContingencia.setInformacoesAdicionais(planoDeContingenciaInfAdicionaisJTextArea.getText());
        novoPlanoContingencia.setIdRisco(riscoSelecionado);
        novoPlanoContingencia.setIdentificacaoPlanoContingencia(planoDeContingenciaIdentificacaoJTextField.getText());

        boolean nulo = false;

        if (novoPlanoContingencia.getDataLimite() == null) {
            nulo = true;
        }
        if (novoPlanoContingencia.getResponsavel() == null) {
            nulo = true;
        }
        if (novoPlanoContingencia.getComoRealizar() == null) {
            nulo = true;
        }
        if (novoPlanoContingencia.getIdentificacaoPlanoContingencia() == null) {
            nulo = true;
        }
        if (novoPlanoContingencia.getDescricaoPlanoContingencia() == null) {
            nulo = true;
        }

        if (nulo == true) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão nulos. Preencha-os antes de adicionar um plano de contingencia.");
        } else {
            boolean repetido = false;
            for (int i = 0; i < listaContingencia.size(); i++) {
                if (novoPlanoContingencia.getIdentificacaoPlanoContingencia().equals(listaContingencia.get(i).getIdentificacaoPlanoContingencia())) {
                    repetido = true;
                }
            }

            if (repetido == true) {
                JOptionPane.showMessageDialog(null, "Já existe um identificador para tal Plano de Contingência. Escolha outro nome.");
            } else {
                riscosGerenciarRiscosFacade.adicionarPlanoContingencia(novoPlanoContingencia);
                atualizaPreencheEventosListaPlanoContingencia();
                iniciaBotoesCinzaPlanoContingencia();
                planoDeContingenciaAdicionarPlanoJButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Plano de Contingência criado com sucesso para o risco: " + riscoSelecionado.getIdentificacao());
                Historicoalteracao historico = new Historicoalteracao();
                historico.setDescricaoAlteracao("Plano de contingência " + novoPlanoContingencia.getIdentificacaoPlanoContingencia() + " criado.");
                Calendar c = Calendar.getInstance();
                historico.setDataAlteracao(c.getTime());
                historico.setIdRisco(riscoSelecionado);
                riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                getListaHistoricoAlteracoes(riscoSelecionado);
                limparTabelaHistoricoAlteracoes();
                preencheTabelaHistoricoAlteracoes();
            }
        }


    }//GEN-LAST:event_planoDeContingenciaAdicionarPlanoJButtonActionPerformed

    private void planoDeContingenciaRemoverPlanoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeContingenciaRemoverPlanoJButtonActionPerformed
        //Pertence a aba Plano de ContingÊncia
        if (planoContingenciaSelecionado.getIdPlanoContingencia() > 0) {
            riscosGerenciarRiscosFacade.removerPlanoContingencia(planoContingenciaSelecionado);
            JOptionPane.showMessageDialog(null, "Plano de Contingencia removido com sucesso");
            //Método que 'reinicia' a lista de Planos de Mitigação
            planoContingenciaListModel.clear();
            limparCamposPlanoContingencia();
            iniciaBotoesCinzaPlanoContingencia();
            planoDeContingenciaAdicionarPlanoJButton.setEnabled(true);
            atualizaPreencheEventosListaPlanoContingencia();
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Plano de contingência " + planoContingenciaSelecionado.getIdentificacaoPlanoContingencia() + " removido.");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        }
    }//GEN-LAST:event_planoDeContingenciaRemoverPlanoJButtonActionPerformed

    private void planoDeContingenciaSalvarAlteracoesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeContingenciaSalvarAlteracoesJButtonActionPerformed
        //Pertence a aba Plano de Contingencia
        if (planoContingenciaSelecionado.getIdPlanoContingencia() > 0) {
            int indexSelecionado = planoDeContingenciaListaPlanosJList.getSelectedIndex();

            planoContingenciaSelecionado.setResponsavel(planoDeContingenciaResponsavelJTextField.getText());
            planoContingenciaSelecionado.setDataLimite(planoDeContingenciaDataLimiteJDateChooser.getDate());
            planoContingenciaSelecionado.setDescricaoPlanoContingencia(planoDeContingenciaDescricaoJTextArea.getText());
            planoContingenciaSelecionado.setComoRealizar(planoDeContingenciaComoSeraFeitoJTextArea.getText());
            planoContingenciaSelecionado.setInformacoesAdicionais(planoDeContingenciaInfAdicionaisJTextArea.getText());

            riscosGerenciarRiscosFacade.editarPlanoContingencia(planoContingenciaSelecionado);
            JOptionPane.showMessageDialog(null, "Informações do Plano de Contingência alteradas com sucesso");
            atualizaPreencheEventosListaPlanoContingencia();
            planoDeContingenciaListaPlanosJList.setSelectedIndex(indexSelecionado);
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Plano de contingência " + planoContingenciaSelecionado.getIdentificacaoPlanoContingencia() + " alterado.");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        }
    }//GEN-LAST:event_planoDeContingenciaSalvarAlteracoesJButtonActionPerformed

    private void subcondicoesSalvarAlteracoesDaCondicaoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subcondicoesSalvarAlteracoesDaCondicaoJButtonActionPerformed
        if (subcondicaoSelecionada != null) {
            int indexSelecionado = tabelaSubcondicoesJTable.getSelectedRow();

            subcondicaoSelecionada.setDescricaoSubcondicao(subcondicoesDescricaoJTextPane.getText());
            subcondicaoSelecionada.setIdentificacaoSubcondicao(subcondicoesIdentificacaoDaCondicaoJTextField.getText());
            /*List elementosSelecionados = subcondicoesListaRelacoesJList.getSelectedValuesList();
             System.out.println("Linha: " + elementosSelecionados.get(0));
             for (int i = 0; i < elementosSelecionados.size(); i++) {
             Relacaosubcondicao relacaoTemp = null;
             relacaoTemp.setSubcondicao1(subcondicaoSelecionada);
             Subcondicao subcondicaoRelacionada = null;
             for (int j = 0; j < listaSubcondicoes.size(); j++) {
             String pesquisa = elementosSelecionados.get(i).toString();
             if (pesquisa.equals(listaSubcondicoes.get(j).getIdentificacaoSubcondicao().toString())) {
             subcondicaoRelacionada = listaSubcondicoes.get(i);
             }
             }
             relacaoTemp.setSubcondicao(subcondicaoRelacionada);
             RelacaosubcondicaoPK relacaoPK = null;

             }*/

            riscosGerenciarRiscosFacade.editarSubcondicao(subcondicaoSelecionada);
            JOptionPane.showMessageDialog(null, "Informações da Subcondição alteradas com sucesso");
            atualizaPreencheSubcondicao();
            tabelaSubcondicoesJTable.setRowSelectionInterval(indexSelecionado, indexSelecionado);
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Alterações realizadas na subcondição " + subcondicaoSelecionada.getIdentificacaoSubcondicao());
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        }
    }//GEN-LAST:event_subcondicoesSalvarAlteracoesDaCondicaoJButtonActionPerformed

    private void subcondicoesCriarCondicaoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subcondicoesCriarCondicaoJButtonActionPerformed
        Subcondicao novaSubcondicao = new Subcondicao();
        novaSubcondicao.setDescricaoSubcondicao(subcondicoesDescricaoJTextPane.getText());
        novaSubcondicao.setIdentificacaoSubcondicao(subcondicoesIdentificacaoDaCondicaoJTextField.getText());
        novaSubcondicao.setStatusSubcondicao("Não Ocorreu");
        novaSubcondicao.setIdRisco(riscoSelecionado);
        boolean nulo = false;
        if (novaSubcondicao.getIdentificacaoSubcondicao() == null) {
            nulo = true;
        }
        if (novaSubcondicao.getDescricaoSubcondicao() == null) {
            nulo = true;
        }

        if (nulo == true) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios. Preencha-os");
        } else {
            boolean repetido = false;
            for (int i = 0; i < listaSubcondicoes.size(); i++) {
                if (novaSubcondicao.getIdentificacaoSubcondicao().equals(listaSubcondicoes.get(i).getIdentificacaoSubcondicao())) {
                    repetido = true;
                }
            }

            if (repetido == true) {
                JOptionPane.showMessageDialog(null, "Já existe um identificador para tal subcondição. Escolha outro nome.");
            } else {
                riscosGerenciarRiscosFacade.criaSubcondicao(novaSubcondicao);
                JOptionPane.showMessageDialog(null, "Subcondição criada com sucesso para o risco " + riscoSelecionado.getIdentificacao());
                getListaSubcondicoes(riscoSelecionado);
                limparCamposSubcondicao();
                limparListaRelacoesSubcondicoes();
                reiniciarTabelaSubcondicoes();
                Historicoalteracao historico = new Historicoalteracao();
                historico.setDescricaoAlteracao("Criação da subcondição " + novaSubcondicao.getIdentificacaoSubcondicao());
                Calendar c = Calendar.getInstance();
                historico.setDataAlteracao(c.getTime());
                historico.setIdRisco(riscoSelecionado);
                riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                getListaHistoricoAlteracoes(riscoSelecionado);
                limparTabelaHistoricoAlteracoes();
                preencheTabelaHistoricoAlteracoes();
            }
        }
    }//GEN-LAST:event_subcondicoesCriarCondicaoJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HistoricoDeAlteracoesJPanel;
    private javax.swing.JTextArea InformacoesGeraisDescricaoDeRiscoJTextArea;
    private javax.swing.JPanel InformacoesGeraisJPanel;
    private javax.swing.JPanel PlanoContigenciaJPanel;
    private javax.swing.JPanel PlanoDeMitigacaoJPanel;
    private javax.swing.JPanel RelacoesJPanel;
    private javax.swing.JPanel SubcondicoesJPanel;
    private javax.swing.JPanel TabelaDeRiscosJPanel;
    private javax.swing.JLabel estadoAtualRiscoJLabel;
    private javax.swing.JPanel gerenciarRiscosJPanel;
    private javax.swing.JTabbedPane gerenciarRiscosJTabbedPane;
    private javax.swing.JButton historicoDeAlteracoesSalvarAlteracoesDeRiscoJButton;
    private javax.swing.JButton informacoesGeraisAdicionarNovoRiscoJButton;
    private javax.swing.JComboBox informacoesGeraisCategoriaDeRiscoJComboBox;
    private javax.swing.JLabel informacoesGeraisCategoriaDeRiscoJLabel;
    private com.toedter.calendar.JDateChooser informacoesGeraisDataDeIdentificacaoJDateChooser;
    private javax.swing.JLabel informacoesGeraisDataDeIdentificacaoJLabel;
    private javax.swing.JScrollPane informacoesGeraisDescricaoDeRiscoJScrollPane;
    private javax.swing.JLabel informacoesGeraisEmissorJLabel;
    private javax.swing.JTextField informacoesGeraisEmissorJTextField;
    private javax.swing.JLabel informacoesGeraisEstadoAtualJLabel;
    private javax.swing.JLabel informacoesGeraisGrauDeSeveridadeJLabel;
    private javax.swing.JTextField informacoesGeraisGrauDeSeveridadeJTextField;
    private javax.swing.JLabel informacoesGeraisIdentificacaoDeRiscoJLabel;
    private javax.swing.JTextField informacoesGeraisIdentificacaoDeRiscoJTextField;
    private javax.swing.JComboBox informacoesGeraisImpactoJComboBox;
    private javax.swing.JLabel informacoesGeraisImpactoJLabel;
    private javax.swing.JButton informacoesGeraisLimparCamposJButton;
    private javax.swing.JLabel informacoesGeraisProbabilidadeJLabel;
    private javax.swing.JSpinner informacoesGeraisProbabilidadeJSpinner;
    private javax.swing.JLabel informacoesGeraisProbabilidadePorcentagemJLabel;
    private javax.swing.JButton informacoesGeraisRemoverRiscoJButton;
    private javax.swing.JButton informacoesGeraisSalvarAlteracoesDoRiscoJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel planoContigenciaPlanoDeContigenciaJLabel;
    private javax.swing.JButton planoDeContingenciaAdicionarPlanoJButton;
    private javax.swing.JTextArea planoDeContingenciaComoSeraFeitoJTextArea;
    private com.toedter.calendar.JDateChooser planoDeContingenciaDataLimiteJDateChooser;
    private javax.swing.JTextArea planoDeContingenciaDescricaoJTextArea;
    private javax.swing.JTextField planoDeContingenciaIdentificacaoJTextField;
    private javax.swing.JTextArea planoDeContingenciaInfAdicionaisJTextArea;
    private javax.swing.JList planoDeContingenciaListaPlanosJList;
    private javax.swing.JButton planoDeContingenciaRemoverPlanoJButton;
    private javax.swing.JTextField planoDeContingenciaResponsavelJTextField;
    private javax.swing.JButton planoDeContingenciaSalvarAlteracoesJButton;
    private javax.swing.JButton planoDeMitigacaoAdicionarPlanoJButton;
    private javax.swing.JTextArea planoDeMitigacaoComoSeraFeitoJTextArea;
    private com.toedter.calendar.JDateChooser planoDeMitigacaoDataLimiteJDateChooser;
    private javax.swing.JTextArea planoDeMitigacaoDescricaoJTextArea;
    private javax.swing.JTextField planoDeMitigacaoIdentificacaoJTextField;
    private javax.swing.JTextArea planoDeMitigacaoInfAdicionaisJTextArea;
    private javax.swing.JList planoDeMitigacaoListaPlanosJList;
    private javax.swing.JLabel planoDeMitigacaoPlanoDeMitigacaoJLabel;
    private javax.swing.JButton planoDeMitigacaoRemoverPlanoJButton;
    private javax.swing.JTextField planoDeMitigacaoResponsavelJTextField;
    private javax.swing.JButton planoDeMitigacaoSalvarAlteracoesJButton;
    private javax.swing.JList relacoesInfluenciadoJList;
    private javax.swing.JList relacoesListaInfluenciaJList;
    private javax.swing.JButton relacoesSalvarAlteracoesJButton;
    private javax.swing.JButton subcondicoesCriarCondicaoJButton;
    private javax.swing.JLabel subcondicoesDescricaoJLabel;
    private javax.swing.JScrollPane subcondicoesDescricaoJScrollPane;
    private javax.swing.JTextPane subcondicoesDescricaoJTextPane;
    private javax.swing.JLabel subcondicoesGrupoDeRelacoesJLabel;
    private javax.swing.JLabel subcondicoesIdentificacaoDaCondicaoJLabel;
    private javax.swing.JTextField subcondicoesIdentificacaoDaCondicaoJTextField;
    private javax.swing.JList subcondicoesListaRelacoesJList;
    private javax.swing.JButton subcondicoesSalvarAlteracoesDaCondicaoJButton;
    private javax.swing.JComboBox subcondicoesTipoDeRelacaoJComboBox;
    private javax.swing.JLabel subcondicoesTipoDeRelacaoJLabel;
    private javax.swing.JScrollPane tabelaHistoricoAlteracoesJScrollPane;
    private javax.swing.JScrollPane tabelaRiscosJScrollPane;
    private javax.swing.JScrollPane tabelaSubcondicoesJScrollPane;
    // End of variables declaration//GEN-END:variables
}
