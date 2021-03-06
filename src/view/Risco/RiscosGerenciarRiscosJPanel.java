/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Risco;

import facade.ContemFacade;
import facade.OrganizacionalPortfolioFacade;
import facade.RiscosGerenciarRiscosFacade;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Categoriaderisco;
import model.Contem;
import model.ContemPK;
import model.Gruporelacao;
import model.Historicoalteracao;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;
import model.Risco;
import model.Subcondicao;

/**
 *
 * @author Bleno
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
//    private List<Risco> listaRiscos;
    // Instanciando variável que armazena a lista de Categorias de Risco pertencentes ao projeto selecionado
    private List<Categoriaderisco> listaCategorias;
    // Instanciando variável que armazena a lista da tabela Contém
    private List<Contem> listaContem;
    // Instanciando variável que armazena a lista de histórico de alterações do risco selecionado
    private List<Historicoalteracao> listaHistoricoAlteracao;
    //Variável que armazena a subcondição selecionada
    Subcondicao subcondicaoSelecionada = null;
    //Variável que armazena a relação entre subcondições selecionada
    Gruporelacao relacaoSelecionada = null;
    //Variável que verifica se mitigação é marco ou ponto de controle selecionado
    private boolean mitigacaoEhMarco;
    //Variável que verifica se o plano de mitigação possui marco ou ponto de controle
    private boolean MitigacaoPossuiMarco;
    //Variável que verifica se em plano de contingência é o é marco ou ponto de controle selecionado
    private boolean contingenciaEhMarco;
    //Variável que verifica se o plano de contigência possui marco ou ponto de controle
    private boolean ContingenciaPossuiMarco;

    List<Risco> listaRiscosDoProjeto;
    private Object[] listaVazia = {};

    public RiscosGerenciarRiscosJPanel() {
        initComponents();
        // riscoSelecionado = new Risco();
        criarTabelaRiscos();
        getListaderiscosDoprojeto();
        getListaCategorias();
        preencherDadosTabelaRiscos();
        definirEventosTabelaRiscos();
        iniciaBotoesCinzaPlanoMitigacao();
        iniciaBotoesCinzaPlanoContingencia();
        iniciaBotosRiscoCinza();
        criaTabelaHistoricoAlteracoes();
        criarTabelaSubcondicoes();
        habilitaBotoesSeTemRiscoSelecionado();

        informacoesGeraisProbabilidadeJSpinner.setEditor(new JSpinner.NumberEditor(informacoesGeraisProbabilidadeJSpinner, "#"));
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
        //se projeto estiver concluído não habilita
        if (projetoSelecionado.getConcluido()) {
            informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(false);
            informacoesGeraisRemoverRiscoJButton.setEnabled(false);
        } else {
            informacoesGeraisLimparCamposJButton.setEnabled(true);
            informacoesGeraisAdicionarNovoRiscoJButton.setEnabled(true);
        }
    }

    //Método que seleciona o projeto selecionado
    public void setProjetoSelecionado(Projeto projeto) {
        projetoSelecionado = projeto;
        getListaderiscosDoprojeto();
    }

    //Método que atualiza a lista de riscos do projeto selecionado
//    private void getListaRiscos() {
//        listaRiscos = riscosGerenciarRiscosFacade.listarRiscosByProjeto(projetoSelecionado);
//    }
    private void getListaderiscosDoprojeto() {
        listaRiscosDoProjeto = riscosGerenciarRiscosFacade.findListaRiscoByIdProjeto(projetoSelecionado);
    }

    //Método que atualiza a lista de categorias de risco
    private void getListaCategorias() {
        listaCategorias = organizacionalPortfolioFacade.listarCategorias();
    }

    //Método que atualiza a lista de Histórico de Alterações do risco selecionado
    void getListaHistoricoAlteracoes(Risco idRisco) {
        listaHistoricoAlteracao = organizacionalPortfolioFacade.listarHistoricoAlteracoesByIdRisco(idRisco);
    }

    //Método que atualiza a lista contém
    private void getListaContem() {
        listaContem = organizacionalPortfolioFacade.listarContem();
    }

    //Método que limpa todos os Campos e componentes de Gerenciar Risco
    public void limpaCamposGerenciarRisco() {
        riscoSelecionado = new Risco();
        riscoSelecionado.setIdRisco(null);

        //[Informações Gerais]limpa  todos os campos de informações gerais
        limparCampos();
        //[Relações] limpa tabela de riscos apresentados compossibilidade de ter relação com risco selecioando
        limparListaRiscosApresentados();
        //[Relações] limpa tabela de ricos que influenciam no risco selecionado
        limparListaRiscosInfluencia();
        //[Relações] limpa tabelade riscos que são influesnciados pelo risco selecioando
        limparListaRiscosInfluenciados();
        //[Condições] limpa todos os Campos de Subcondições 
        limparCamposSubcondicao();
        //[Condições] limpa todos os Campos de Relações entre as Subcondições 
        limparListaRelacoesSubcondicoes();
        //[Condições] limapa as tabelas
        limpaTabelaSubcondicoesERelacoeas();
        //[Plano Mitigação] limpa todos os campos
        limparCamposPlanoMitigacao();
        //[Plano Mitigação] limpa a lista de planos de mitigação
        limparListaPlanoMitigacao();
        //[Plano Contigência] limpa todos os campos
        limparCamposPlanoContingencia();
        //[Plano Contingência] limpa a lista de planos de Contingência
        limparListaPlanoContingencia();
        //[Histórico de Risco]limpa a tabela
        limparTabelaHistoricoAlteracoes();

        //método para Habilitar os botões
        habilitaBotoesSeTemRiscoSelecionado();
        //método que inicia os botões de Plano Mitigação
        iniciaBotoesCinzaPlanoMitigacao();
        //método que inicia os botões de Plano Contigência
        iniciaBotoesCinzaPlanoContingencia();
    }

    //Método que habilita os botões caso um risco tenha si selecionado;  
    private void habilitaBotoesSeTemRiscoSelecionado() {
        if (riscoSelecionado.getIdRisco() == null && (!projetoSelecionado.getConcluido())) {
            // Aba relações
            relacoesInfluenciarRiscoJButton.setEnabled(false);
            relacoesRemoverInfluenciaJButton.setEnabled(false);
            // Abacondições
            deletarCondicaoJButton.setEnabled(false);
            limparCamposJButton.setEnabled(false);
            subcondicoesSalvarAlteracoesDaCondicaoJButtob.setEnabled(false);
            subcondicoesCriarCondicaoJButton.setEnabled(false);

            inserirCondicaoCampo1JButton.setEnabled(false);
            inserirRelacaoCampo1JButton.setEnabled(false);
            inserirCondicaoCampo2JButton.setEnabled(false);
            inserirRelacaoCampo2JButton.setEnabled(false);
            criarRelacaoJButton.setEnabled(false);
            deletarRelacaoJButton.setEnabled(false);
            //Aba Informações Gerais
            informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(false);
            informacoesGeraisRemoverRiscoJButton.setEnabled(false);
            informacoesGeraisLimparCamposJButton.setEnabled(true);
            informacoesGeraisAdicionarNovoRiscoJButton.setEnabled(true);
        } else if (!projetoSelecionado.getConcluido()) {
            // Aba relações
            relacoesInfluenciarRiscoJButton.setEnabled(true);
            relacoesRemoverInfluenciaJButton.setEnabled(true);
            // Abacondições
            deletarCondicaoJButton.setEnabled(true);
            limparCamposJButton.setEnabled(true);
            subcondicoesSalvarAlteracoesDaCondicaoJButtob.setEnabled(true);
            subcondicoesCriarCondicaoJButton.setEnabled(true);

            inserirCondicaoCampo1JButton.setEnabled(true);
            inserirRelacaoCampo1JButton.setEnabled(true);
            inserirCondicaoCampo2JButton.setEnabled(true);
            inserirRelacaoCampo2JButton.setEnabled(true);
            criarRelacaoJButton.setEnabled(true);
            deletarRelacaoJButton.setEnabled(true);
            //Aba Informações Gerais
            informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(true);
            informacoesGeraisRemoverRiscoJButton.setEnabled(true);
        } else {
            System.err.println("bloqueia todos os botões Gerenciar Riscos-Projeto Concluído");
            desabilitaBotoesSeTemRiscoSelecionado();
        }
    }

    public void desabilitaBotoesSeTemRiscoSelecionado() {
        if (projetoSelecionado.getConcluido()) {
            // Aba relações
            relacoesInfluenciarRiscoJButton.setEnabled(false);
            relacoesRemoverInfluenciaJButton.setEnabled(false);
            // Abacondições
            deletarCondicaoJButton.setEnabled(false);
            limparCamposJButton.setEnabled(false);
            subcondicoesSalvarAlteracoesDaCondicaoJButtob.setEnabled(false);
            subcondicoesCriarCondicaoJButton.setEnabled(false);

            inserirCondicaoCampo1JButton.setEnabled(false);
            inserirRelacaoCampo1JButton.setEnabled(false);
            inserirCondicaoCampo2JButton.setEnabled(false);
            inserirRelacaoCampo2JButton.setEnabled(false);
            criarRelacaoJButton.setEnabled(false);
            deletarRelacaoJButton.setEnabled(false);
            //Aba Informações Gerais
            informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(false);
            informacoesGeraisRemoverRiscoJButton.setEnabled(false);
            informacoesGeraisLimparCamposJButton.setEnabled(false);
            informacoesGeraisAdicionarNovoRiscoJButton.setEnabled(false);
        }
    }

    //Método que constrói a Tabela de Riscos
    void criarTabelaRiscos() {
        tabelaRiscosJTable = new JTable();
        modeloTabelaRiscosJTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modeloTabelaRiscosJTable.setColumnIdentifiers(new String[]{"Identificação", "Probabilidade", "Impacto", "Grau de Severidade", "Prioridade", "Estado"});
        tabelaRiscosJTable.setModel(modeloTabelaRiscosJTable);
        tabelaRiscosJScrollPane.setViewportView(tabelaRiscosJTable);
    }

    //Método que preenche os dados da Tabela de Riscos, conforme o projeto selecionado
    void preencherDadosTabelaRiscos() {
        for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
            //Verifica se o ID do projeto selecionado é igual ao ID do Projeto do Contém do Risco atual
            if (projetoSelecionado.getIdProjeto() == listaRiscosDoProjeto.get(i).getContem().getProjeto().getIdProjeto()) {
                String identificacao = listaRiscosDoProjeto.get(i).getIdentificacao();
                String probabilidade = Integer.toString(listaRiscosDoProjeto.get(i).getProbabilidade());
                String impacto = listaRiscosDoProjeto.get(i).getImpacto();
                String grauSeveridade = Double.toString(listaRiscosDoProjeto.get(i).getGrauSeveridade());
                String prioridade = Integer.toString(listaRiscosDoProjeto.get(i).getPrioridade());
                String statusRisco = listaRiscosDoProjeto.get(i).getStatusRisco();

                String[] linha = new String[]{identificacao, probabilidade, impacto, grauSeveridade, prioridade, statusRisco};
                modeloTabelaRiscosJTable.addRow(linha);
            }
        }
    }

    //Método para reconstruir a tabela quando for selecionado um novo risco
    public void reiniciarTabelaRiscos() {
        getListaderiscosDoprojeto();
        getListaCategorias();
        modeloTabelaRiscosJTable = null;
        criarTabelaRiscos();
        preencherDadosTabelaRiscos();
        definirEventosTabelaRiscos();
    }

    //Método que limpa todos os campos de Informações Gerais
    public void limparCampos() {
        informacoesGeraisIdentificacaoDeRiscoJTextField.setText("");
        dataIdentificacaoDoRisco.setValue(null);
        informacoesGeraisEmissorJTextField.setText("");
        InformacoesGeraisDescricaoDeRiscoJTextArea.setText("");
        //informacoesGeraisCategoriaDeRiscoJComboBox.removeAllItems();
        informacoesGeraisProbabilidadeJSpinner.setValue(0);
        informacoesGeraisImpactoJComboBox.setSelectedIndex(0);
        estadoAtualRiscoJLabel.setText("");
        //desabilitaCheckBoxStatusRisco();
        informacoesGeraisGrauDeSeveridadeJTextField.setText("0");
        tabelaRiscosJTable.clearSelection();
        relacoesListaRiscosJList.setListData(listaVazia);

    }

    //Define os eventos para quando um item da tabela for selecionado
    void definirEventosTabelaRiscos() {
        tabelaRiscosJTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selected = tabelaRiscosJTable.getSelectedRow();

                    //Procura o risco selecionado
                    for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
                        if (tabelaRiscosJTable.getValueAt(selected, 0).equals(listaRiscosDoProjeto.get(i).getIdentificacao())) {
                            riscoSelecionado = listaRiscosDoProjeto.get(i);
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

                    //Retorna lista de marcos e pontos de controle
                    getListaMarcosProjeto(projetoSelecionado);
                    getListaPontosControle(projetoSelecionado);
                    //Preenche combo box de data limite em plano de mitigação
                    preencheComboBoxDataLimiteMitigacao();

                    //Preenche combo box de data limite em plano de contingência
                    preencheComboBoxDataLimiteContingencia();

                    //Preenche lista de riscos que influencia
                    if (riscosNaoAdd.size() > 0) {
                        riscosNaoAdd = null;
                        riscosNaoAdd = riscosGerenciarRiscosFacade.listarRiscos();
                        riscosNaoAdd.clear();
                    }
                    modelListaRiscosApresentados.clear();
                    modelListaRiscosInfluencia.clear();
                    modelListaRiscosInfluenciados.clear();
                    atualizaPreencheListaRiscosInfluencia();
                    preencheListaRiscosInfluenciados();
                    atualizaPreencheListaRiscosApresentados();
                    filtraListaRiscosApresentados();

                    //Preenche tabela de subcondições do risco selecionado
                    getListaSubcondicoes(riscoSelecionado);
                    limparCamposSubcondicao();
                    limparListaRelacoesSubcondicoes();
                    reiniciarTabelaSubcondicoes();

                    dataIdentificacaoDoRisco.setValue(riscoSelecionado.getDataIdentificacao());

                    informacoesGeraisEmissorJTextField.setText(riscoSelecionado.getEmissor());
                    informacoesGeraisProbabilidadeJSpinner.setValue(riscoSelecionado.getProbabilidade());
                    informacoesGeraisGrauDeSeveridadeJTextField.setText(Double.toString(riscoSelecionado.getGrauSeveridade()));
                    estadoAtualRiscoJLabel.setText(riscoSelecionado.getStatusRisco());

                    campo1Condicao = null;
                    campo1Relacao = null;
                    campo1JTextField.setText("");
                    campo2Condicao = null;
                    campo2Relacao = null;
                    campo2JTextField.setText("");

                    // Determina qual campo do comboBox de Impacto será selecionado
                    if (riscoSelecionado.getImpacto().equals("Alto")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(2);
                    } else if (riscoSelecionado.getImpacto().equals("Médio")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(3);
                    } else if (riscoSelecionado.getImpacto().equals("Baixo")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(4);
                    } else if (riscoSelecionado.getImpacto().equals("Crítico")) {
                        informacoesGeraisImpactoJComboBox.setSelectedIndex(1);
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
//                    informacoesGeraisRemoverRiscoJButton.setEnabled(true);
//                    informacoesGeraisSalvarAlteracoesDoRiscoJButton.setEnabled(true);
                    //******************Na aba Plano de Mitigacao***********************
                    // Reinicia a lista de Planos de Mitigação
                    planoMitigacaoListModel.clear();

                    //Desativa os botões de Plano de Mitigação
                    iniciaBotoesCinzaPlanoMitigacao();

                    // Atualiza a lista de planos, preenche a lista e define eventos da JList
                    atualizaPreencheEventosListaPlanoMitigacao();

                    //Limpa os campos de Plano de Mitigação
                    limparCamposPlanoMitigacao();

                    if (!projetoSelecionado.getConcluido()) {
                        planoDeMitigacaoAdicionarPlanoJButton.setEnabled(true);
                    }
                    //******************Na aba Plano de Contingência*******************
                    // Reinicia a lista de Planos de Contingencia
                    planoContingenciaListModel.clear();

                    //Desativa os botões de Plano de Contingencia
                    iniciaBotoesCinzaPlanoContingencia();

                    // Atualiza a lista de planos, preenche a lista e define eventos da JList
                    atualizaPreencheEventosListaPlanoContingencia();

                    //Limpa os campos de Plano de Contingencia
                    limparCamposPlanoContingencia();

                    if (!projetoSelecionado.getConcluido()) {
                        planoDeContingenciaAdicionarPlanoJButton.setEnabled(true);
                    }

                    //Habilita botões da tela relação 
                    habilitaBotoesSeTemRiscoSelecionado();
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
    //Variável que armazena a data selecionada
    Calendar dataLimiteSelecionada = Calendar.getInstance();
    //Variável que armazena a lista de marcos do projeto
    private List<Marcodoprojeto> listaMarcosProjeto;
    //Variável que armazena a lista de pontos de controle do projeto
    private List<Pontodecontrole> listaPontosControle;
    //Variável que armazena o Marco Selecionado
    private Marcodoprojeto marcoSelecionado = null;
    //Variável que armazena o ponto de controle selecionado
    private Pontodecontrole pontoControleSelecionado = null;

    //Criando model da Lista de Planos de Mitigacao
    private DefaultListModel planoMitigacaoListModel = new DefaultListModel();

    //Método que lista os planos de mitigacao do risco selecionado
    private void getListaMitigacaoDoRisco(Risco risco) {
        listaMitigacao = riscosGerenciarRiscosFacade.listarPlanosMitigacao(risco);
    }

    //Método que retorna a lista de pontos de controle do projeto selecionado
    public void getListaPontosControle(Projeto projeto) {
        listaPontosControle = riscosGerenciarRiscosFacade.listarPontosControleProjetoSelecionado(projeto);
    }

    //Método que retorna a lista de marcos do projeto selecionado
    public void getListaMarcosProjeto(Projeto projeto) {
        listaMarcosProjeto = riscosGerenciarRiscosFacade.listarMarcosProjetoProjetoSelecionado(projeto);
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
        planoMitigacaoDataSelecionadaJLabel.setText("");
        marcoSelecionado = null;
        pontoControleSelecionado = null;
        planoMitigacaoDataLimiteJComboBox.setSelectedItem("--Selecione--");
        planoDeMitigacaoDescricaoJTextArea.setText("");
        planoDeMitigacaoComoSeraFeitoJTextArea.setText("");
        planoDeMitigacaoInfAdicionaisJTextArea.setText("");
    }

    //Método que preenche o comboBox de data limite em planos de Mitigação
    public void preencheComboBoxDataLimiteMitigacao() {
        planoMitigacaoDataLimiteJComboBox.removeAllItems();
        List<Pontodecontrole> listaPontoControle = null;
        List<Marcodoprojeto> listaMarcosDoProjeto = null;
        listaPontoControle = riscosGerenciarRiscosFacade.listarPontosControleProjetoSelecionado(projetoSelecionado);
        listaMarcosDoProjeto = riscosGerenciarRiscosFacade.listarMarcosProjetoProjetoSelecionado(projetoSelecionado);
        planoMitigacaoDataLimiteJComboBox.addItem("--Selecione--");

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dataAtual = df.format(new Date());
        if (listaPontoControle.size() > 0) {

            for (int i = 0; i < listaPontoControle.size(); i++) {
                Calendar dataPontoSelecionado = Calendar.getInstance();

                dataPontoSelecionado.setTime(listaPontosControle.get(i).getDataPontoControle());
                String dataPonto = df.format(dataPontoSelecionado.getTime());
                // se Data do ponto de controle for igual ou maior a data atual
                if (dataPontoSelecionado.getTime().after(new Date()) || dataPonto.equals(dataAtual)) {
                    planoMitigacaoDataLimiteJComboBox.addItem(listaPontoControle.get(i).getNomePontoDeControle());
                }

            }
        }
        if (listaMarcosDoProjeto.size() > 0) {
            for (int i = 0; i < listaMarcosDoProjeto.size(); i++) {
                Calendar dataMarcosProjeto = Calendar.getInstance();

                dataMarcosProjeto.setTime(listaMarcosDoProjeto.get(i).getDataMarcoProjeto());
                String dataMarco = df.format(dataMarcosProjeto.getTime());
                // se Data do Marco do projeto for igual ou maior a data atual
                if (dataMarcosProjeto.getTime().after(new Date()) || dataMarco.equals(dataAtual)) {
                    planoMitigacaoDataLimiteJComboBox.addItem(listaMarcosDoProjeto.get(i).getNomeMarcoDoProjeto());
                }

            }
        }
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

                    //Procura se o plano de mitigação possui um Marco ou ponto de controle já definido
                    if (planoMitigacaoSelecionado.getIdMarcoDoProjeto() != null) {
                        String nomeMarco = planoMitigacaoSelecionado.getIdMarcoDoProjeto().getNomeMarcoDoProjeto();
                        for (int i = 0; i < planoMitigacaoDataLimiteJComboBox.getItemCount(); i++) {
                            if (planoMitigacaoDataLimiteJComboBox.getItemAt(i).equals(nomeMarco)) {
                                planoMitigacaoDataLimiteJComboBox.setSelectedIndex(i);
                            }
                        }
                    } else {
                        if (planoMitigacaoSelecionado.getIdPontoDeControle() != null) {
                            String nomePonto = planoMitigacaoSelecionado.getIdPontoDeControle().getNomePontoDeControle();
                            for (int i = 0; i < planoMitigacaoDataLimiteJComboBox.getItemCount(); i++) {
                                if (planoMitigacaoDataLimiteJComboBox.getItemAt(i).equals(nomePonto)) {
                                    planoMitigacaoDataLimiteJComboBox.setSelectedIndex(i);
                                }
                            }
                        }
                    }

                    //Preenchendo as informações nos campos de Plano de Mitigação
                    planoDeMitigacaoIdentificacaoJTextField.setText(planoMitigacaoSelecionado.getIdentificacaoPlanoMitigacao());
                    planoDeMitigacaoResponsavelJTextField.setText(planoMitigacaoSelecionado.getResponsavel());
                    planoDeMitigacaoDescricaoJTextArea.setText(planoMitigacaoSelecionado.getDescricaoPlanoMitigacao());
                    planoDeMitigacaoComoSeraFeitoJTextArea.setText(planoMitigacaoSelecionado.getComoRealizar());
                    planoDeMitigacaoInfAdicionaisJTextArea.setText(planoMitigacaoSelecionado.getInformacoesAdicionais());

                    if (listaMitigacao.get(indexSelecionado).getDataRealizacao() == null) {
                        planoDeMitigacaoSalvarAlteracoesJButton.setEnabled(true);
                        planoDeMitigacaoRemoverPlanoJButton.setEnabled(true);
                    } else {
                        planoDeMitigacaoSalvarAlteracoesJButton.setEnabled(false);
                        planoDeMitigacaoRemoverPlanoJButton.setEnabled(false);
                    }
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

    //Método que preenche o comboBox de data limite em planos de contingência
    public void preencheComboBoxDataLimiteContingencia() {
        planoContingenciaDataLimiteJComboBox.removeAllItems();
        List<Pontodecontrole> listaPontoControle = null;
        List<Marcodoprojeto> listaMarcosProjeto = null;
        listaPontoControle = riscosGerenciarRiscosFacade.listarPontosControleProjetoSelecionado(projetoSelecionado);
        listaMarcosProjeto = riscosGerenciarRiscosFacade.listarMarcosProjetoProjetoSelecionado(projetoSelecionado);
        planoContingenciaDataLimiteJComboBox.addItem("--Selecione--");
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dataAtual = df.format(new Date());
        if (listaPontoControle.size() > 0) {
            for (int i = 0; i < listaPontoControle.size(); i++) {
                Calendar dataPontoSelecionado = Calendar.getInstance();

                dataPontoSelecionado.setTime(listaPontosControle.get(i).getDataPontoControle());
                String dataPonto = df.format(dataPontoSelecionado.getTime());
                // se Data do ponto de controle for igual ou maior a data atual
                if (dataPontoSelecionado.getTime().after(new Date()) || dataPonto.equals(dataAtual)) {
                    planoContingenciaDataLimiteJComboBox.addItem(listaPontoControle.get(i).getNomePontoDeControle());
                }
            }
        }
        if (listaMarcosProjeto.size() > 0) {
            for (int i = 0; i < listaMarcosProjeto.size(); i++) {
                Calendar dataMarcosProjeto = Calendar.getInstance();

                dataMarcosProjeto.setTime(listaMarcosProjeto.get(i).getDataMarcoProjeto());
                String dataMarco = df.format(dataMarcosProjeto.getTime());
                // se Data do Marco do projeto for igual ou maior a data atual
                if (dataMarcosProjeto.getTime().after(new Date()) || dataMarco.equals(dataAtual)) {
                    planoContingenciaDataLimiteJComboBox.addItem(listaMarcosProjeto.get(i).getNomeMarcoDoProjeto());
                }
            }
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
        planoDeContingenciaRemoverPlanoJButton.setEnabled(false);
        planoDeContingenciaSalvarAlteracoesJButton.setEnabled(false);
    }

    //Método que limpa os campos de Plano de Contingência
    public void limparCamposPlanoContingencia() {
        planoDeContingenciaIdentificacaoJTextField.setText("");
        planoDeContingenciaResponsavelJTextField.setText("");
        marcoSelecionado = null;
        pontoControleSelecionado = null;
        planoDeContingenciaDescricaoJTextArea.setText("");
        planoDeContingenciaComoSeraFeitoJTextArea.setText("");
        planoDeContingenciaInfAdicionaisJTextArea.setText("");
        planoContingenciaDataLimiteJComboBox.setSelectedItem("--Selecione--");
        planoContingenciaDataSelecionadaJLabel.setText("");
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

                    //Procura se o plano de contingência possui um Marco ou ponto de controle já definido
                    if (planoContingenciaSelecionado.getIdMarcoDoProjeto() != null) {
                        String nomeMarco = planoContingenciaSelecionado.getIdMarcoDoProjeto().getNomeMarcoDoProjeto();
                        for (int i = 0; i < planoContingenciaDataLimiteJComboBox.getItemCount(); i++) {
                            if (planoContingenciaDataLimiteJComboBox.getItemAt(i).equals(nomeMarco)) {
                                planoContingenciaDataLimiteJComboBox.setSelectedIndex(i);
                            }
                        }
                    } else {
                        if (planoContingenciaSelecionado.getIdPontoDeControle() != null) {
                            String nomePonto = planoContingenciaSelecionado.getIdPontoDeControle().getNomePontoDeControle();
                            for (int i = 0; i < planoContingenciaDataLimiteJComboBox.getItemCount(); i++) {
                                if (planoContingenciaDataLimiteJComboBox.getItemAt(i).equals(nomePonto)) {
                                    planoContingenciaDataLimiteJComboBox.setSelectedIndex(i);
                                }
                            }
                        }
                    }

                    //Preenchendo as informações nos campos de Plano de Mitigação
                    planoDeContingenciaIdentificacaoJTextField.setText(planoContingenciaSelecionado.getIdentificacaoPlanoContingencia());
                    planoDeContingenciaResponsavelJTextField.setText(planoContingenciaSelecionado.getResponsavel());
                    planoDeContingenciaDescricaoJTextArea.setText(planoContingenciaSelecionado.getDescricaoPlanoContingencia());
                    planoDeContingenciaComoSeraFeitoJTextArea.setText(planoContingenciaSelecionado.getComoRealizar());
                    planoDeContingenciaInfAdicionaisJTextArea.setText(planoContingenciaSelecionado.getInformacoesAdicionais());

                    if (listaContingencia.get(indexSelecionado).getDataRealizacao() == null) {
                        planoDeContingenciaSalvarAlteracoesJButton.setEnabled(true);
                        planoDeContingenciaRemoverPlanoJButton.setEnabled(true);
                    } else {
                        planoDeContingenciaSalvarAlteracoesJButton.setEnabled(false);
                        planoDeContingenciaRemoverPlanoJButton.setEnabled(false);
                    }
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
    //Define o risco selecionado em JList de influencia
    private Risco riscoSelecionadoInfluencia = new Risco();

    //Método que limpa a lista de riscos que influencia
    public void limparListaRiscosInfluencia() {
        modelListaRiscosInfluencia.clear();
    }

    //Método que agrupa preencher e definir eventos da JList de riscos que influencia
    private void atualizaPreencheListaRiscosInfluencia() {
        preencheListaRiscosInfluencia();
        definirEventosListaRiscosInfluencia();
    }

    //Método que preenche a lista de riscos que influencia
    private void preencheListaRiscosInfluencia() {
        limparListaRiscosInfluencia();
        modelListaRiscosInfluencia = null;
        modelListaRiscosInfluencia = new DefaultListModel();

        List<Risco> listaRiscosInfluencia = riscoSelecionado.getRiscoList2(); // Retorna a lista de riscos influenciados pelo selecionado

        if (listaRiscosInfluencia.size() > 0) {
            for (int i = 0; i < listaRiscosInfluencia.size(); i++) {
                if (Objects.equals(listaRiscosInfluencia.get(i).getContem().getProjeto().getIdProjeto(), projetoSelecionado.getIdProjeto())) {
                    modelListaRiscosInfluencia.addElement(listaRiscosInfluencia.get(i).getIdentificacao());
                    //Adiciona o risco na lista riscosNaoAdd para não aparecer na lista de riscos apresentados
                    riscosNaoAdd.add(listaRiscosInfluencia.get(i));
                }
            }
            relacoesListaInfluenciaJList.setModel(modelListaRiscosInfluencia);
        }
    }

    //Definindo evento para lista de riscos que influencia
    private void definirEventosListaRiscosInfluencia() {
        relacoesListaInfluenciaJList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int indexSelecionado = relacoesListaInfluenciaJList.getSelectedIndex();
                    String selecao = relacoesListaInfluenciaJList.getModel().getElementAt(indexSelecionado).toString();

                    //Procura e seleciona o risco selecionado na JList
                    for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
                        if (selecao.equals(listaRiscosDoProjeto.get(i).getIdentificacao())) {
                            riscoSelecionadoInfluencia = listaRiscosDoProjeto.get(i);
                        }
                    }

                    relacoesListaRiscosJList.clearSelection();
                    //System.out.println("Risco: " + riscoSelecionadoInfluencia.getIdentificacao());
                }
            }
        });
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
        limparListaRiscosInfluenciados();
        modelListaRiscosInfluenciados = null;
        modelListaRiscosInfluenciados = new DefaultListModel();
        // Procurando em todos os riscos, se o riscoSelecionado aparece no campo riscoInfluenciado de algum risco
        if (listaRiscosDoProjeto.size() > 0) {
            for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
                List<Risco> listaRiscosInfluencia2 = listaRiscosDoProjeto.get(i).getRiscoList2();
                //Dentro da lista de riscos que influencia...
                if (listaRiscosInfluencia2.size() > 0) {
                    for (int j = 0; j < listaRiscosInfluencia2.size(); j++) {
                        //Se o risco atual influencia no riscoSelecionado
                        if (listaRiscosInfluencia2.get(j) == riscoSelecionado) {
                            //Então o riscoSelecionado é INFLUENCIADO pelo riscoAtual
                            riscosNaoAdd.add(listaRiscosDoProjeto.get(i));
                            modelListaRiscosInfluenciados.addElement(listaRiscosDoProjeto.get(i).getIdentificacao());
                        }
                    }
                }
            }
        }

        relacoesInfluenciadoJList.setModel(modelListaRiscosInfluenciados);
        //relacoesInfluenciadoJList.getModel();
    }

    // ********Para a lista de riscos apresentados
    //Criando model da lista de riscos apresentados
    private DefaultListModel modelListaRiscosApresentados = new DefaultListModel();
    //Lista de riscos para não serem adicionados à ela
    private List<Risco> riscosNaoAdd = riscosGerenciarRiscosFacade.listarRiscos();
    //Define o risco selecionado na aba Relações
    private Risco riscoSelecionadoRelacoes = new Risco();

    //Método que limpa a lista de riscos apresentados
    public void limparListaRiscosApresentados() {
        modelListaRiscosApresentados.clear();
    }

    //Método que agrupa preencher e definir eventos da lista de riscos apresentados
    private void atualizaPreencheListaRiscosApresentados() {
        preencheListaRiscosApresentados();
        definirEventosListaRiscosApresentados();
    }

    //Método que preenche a lista de riscos apresentados
    private void preencheListaRiscosApresentados() {
        modelListaRiscosApresentados = null;
        modelListaRiscosApresentados = new DefaultListModel();
        String proprioRisco = riscoSelecionado.getIdentificacao().toString();
        getListaderiscosDoprojeto();
        if (listaRiscosDoProjeto.size() > 0) {
            for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
                if (listaRiscosDoProjeto.get(i).getIdentificacao().toString().equals(proprioRisco)) {

                } else {
                    modelListaRiscosApresentados.addElement(listaRiscosDoProjeto.get(i).getIdentificacao().toString());
                }

            }
            relacoesListaRiscosJList.setModel(modelListaRiscosApresentados);
        }
        filtraListaRiscosApresentados();
    }

    //Metódo que filtra a lista de riscos apresentados
    private void filtraListaRiscosApresentados() {
        //System.out.println("Linhas: " + modelListaRiscosApresentados.getSize());
        if (!(modelListaRiscosApresentados == null) || (modelListaRiscosApresentados.getSize() > 0)) {
            for (int i = 0; i < modelListaRiscosApresentados.getSize(); i++) {
                for (int j = 0; j < riscosNaoAdd.size(); j++) {
                    if (modelListaRiscosApresentados.get(i).toString().equals(riscosNaoAdd.get(j).getIdentificacao())) {
                        modelListaRiscosApresentados.remove(i);
                        if (modelListaRiscosApresentados.size() == 0) {
                            break;
                        }
                        i = 0;
                    }
                }
            }
        }
    }

    //Definindo evento para lista de riscos apresentados
    private void definirEventosListaRiscosApresentados() {
        relacoesListaRiscosJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int indexSelecionado = relacoesListaRiscosJList.getSelectedIndex();
                    String selecao = relacoesListaRiscosJList.getModel().getElementAt(indexSelecionado).toString();

                    //Procura e seleciona o risco selecionado na JList
                    for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
                        if (selecao.equals(listaRiscosDoProjeto.get(i).getIdentificacao())) {
                            riscoSelecionadoRelacoes = listaRiscosDoProjeto.get(i);
                        }
                    }
                    relacoesListaInfluenciaJList.clearSelection();
                }
            }
        });
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
        modeloTabelaHistoricoAlteracoes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
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
    //Instanciando tabela de relações
    private JTable tabelaRelacoes;
    //Instanciando modelo de dados da tabela de relações
    private DefaultTableModel modeloTabelaRelacoes;
    //Instanciando variavel que armazena a lista de relacoes das subcondições do risco
    private List<Gruporelacao> listaRelacoes;
    //Instanciando variável que armazena a condição que está no campo1JTextField
    Subcondicao campo1Condicao = new Subcondicao();
    //Instanciando variável que armazena a relação entre condições que está no campo1JTextField
    Gruporelacao campo1Relacao = new Gruporelacao();
    //Instanciando variável que armazena a condição que está no campo2JTextField
    Subcondicao campo2Condicao = new Subcondicao();
    //Instanciando variável que armazena a relação entre condições que está no campo2JTextField
    Gruporelacao campo2Relacao = new Gruporelacao();

    //Criando model da lista de relações de subcondições
    DefaultListModel modeloListaSubcondicoes = new DefaultListModel();

    //Método que constrói a Tabela de Subcondições
    private void criarTabelaSubcondicoes() {
        tabelaSubcondicoesJTable = new JTable();
        modeloTabelaSubcondicoesJTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modeloTabelaSubcondicoesJTable.setColumnIdentifiers(new String[]{"Identificação da condição", "Grupo de relações", "Ocorreu?"});
        tabelaSubcondicoesJTable.setModel(modeloTabelaSubcondicoesJTable);
        tabelaSubcondicoesJScrollPane.setViewportView(tabelaSubcondicoesJTable);
    }

    //Método que controi a tabela de Relações
    private void criarTabelaRelacoes() {
        tabelaRelacoes = new JTable();
        modeloTabelaRelacoes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modeloTabelaRelacoes.setColumnIdentifiers(new Object[]{"Relação", "Condição 1", "Condição 2", "Relação 1", "Relação 2", "Tipo da Relação"});
        tabelaRelacoes.setModel(modeloTabelaRelacoes);
        tabelaRelacoesJScrollPane.setViewportView(tabelaRelacoes);
    }

    //Método que atualiza a lista de relações das condições do risco selecionado
    private void getListaRelacoes() {
        listaRelacoes = riscosGerenciarRiscosFacade.getListaGrupoRelacaoByRisco(riscoSelecionado);
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
            // desabilitaCamposSubcondicao();
        }
    }

    //Método que preenche os dados da tabela de relações das condições do risco selecionado
    private void preencherDadosTabelaRelacoes() {
        getListaRelacoes();
        if (listaRelacoes != null) {
            for (int i = 0; i < listaRelacoes.size(); i++) {

                String idRelacao = null;
                String condicao1 = null;
                String condicao2 = null;
                String relacao1 = null;
                String relacao2 = null;
                String tipoRelacao = null;

                //preenchendo condicao 1
                if (listaRelacoes.get(i).getIdSubcondicao1() != null) {
                    for (int j = 0; j < listaSubcondicoes.size(); j++) {
                        if (listaSubcondicoes.get(j).getIdSubcondicao() == listaRelacoes.get(i).getIdSubcondicao1()) {
                            condicao1 = listaSubcondicoes.get(j).getIdentificacaoSubcondicao();
                        }
                    }
                }

                //preenchendo condicao 2
                if (listaRelacoes.get(i).getIdSubcondicao2() != null) {
                    for (int j = 0; j < listaSubcondicoes.size(); j++) {
                        if (listaSubcondicoes.get(j).getIdSubcondicao() == listaRelacoes.get(i).getIdSubcondicao2()) {
                            condicao2 = listaSubcondicoes.get(j).getIdentificacaoSubcondicao();
                        }
                    }
                }

                //preenchendo relacao 1
                if (listaRelacoes.get(i).getIdRelacao1() != null) {
                    relacao1 = listaRelacoes.get(i).getIdRelacao1().toString();
                }
                //preenchendo relacao 2
                if (listaRelacoes.get(i).getIdRelacao2() != null) {
                    relacao2 = listaRelacoes.get(i).getIdRelacao2().toString();
                }
                //preenchendo tipo da relação
                tipoRelacao = listaRelacoes.get(i).getRelacao();

                //preenchendo id da relação
                idRelacao = listaRelacoes.get(i).getIdGrupo().toString();

                Object[] linha = new Object[]{idRelacao, condicao1, condicao2, relacao1, relacao2, tipoRelacao};
                modeloTabelaRelacoes.addRow(linha);

            }
        }
    }

    //Método que reseta a tabela de subcondições
    private void reiniciarTabelaSubcondicoes() {
        modeloTabelaSubcondicoesJTable = null;
        criarTabelaSubcondicoes();
        preencherDadosTabelaSubcondicoes();
        definirEventosTabelaSubcondicoes();

        reiniciarTabelaRelacoes();
    }

    //método que reseta a tabela de relações
    private void reiniciarTabelaRelacoes() {
        modeloTabelaRelacoes = null;
        criarTabelaRelacoes();
        preencherDadosTabelaRelacoes();
        definirEventosTabelaRelações();
    }

    //Definindo eventos de seleção na tabela de subcondições
    private void definirEventosTabelaSubcondicoes() {
        tabelaSubcondicoesJTable.addMouseListener(new MouseAdapter() {
            @Override
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
                    descricaoSubcondicaoJTextArea.setText(subcondicaoSelecionada.getDescricaoSubcondicao().toString());

                    //Limpa e preenche a lista de relações entre subcondições
                    limparListaRelacoesSubcondicoes();
                    preencheListaRelacoesSubcondicoes();

                    //Verifica se possui relação entre subcondições
                    //marcaSubcondicao();
                }
            }
        });
    }

    //Definindo eventos de seleção ma tabela de relações
    private void definirEventosTabelaRelações() {
        tabelaRelacoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    int selected = tabelaRelacoes.getSelectedRow();

                    for (int i = 0; i < listaRelacoes.size(); i++) {
                        if (tabelaRelacoes.getValueAt(selected, 0).equals(listaRelacoes.get(i).getIdGrupo().toString())) {
                            relacaoSelecionada = listaRelacoes.get(i);
                        }
                    }

                }
            }
        });
    }

    //Método que limpa os campos de informações da subcondição
    private void limparCamposSubcondicao() {
        descricaoSubcondicaoJTextArea.setText("");
        subcondicoesIdentificacaoDaCondicaoJTextField.setText("");
    }

    private void limpaTabelaSubcondicoesERelacoeas() {
        if (modeloTabelaSubcondicoesJTable.getRowCount() != 0) {
            modeloTabelaSubcondicoesJTable.setNumRows(0);
        }
        if (modeloTabelaRelacoes != null) {
            modeloTabelaRelacoes.setNumRows(0);
        }
    }

    //Desabilita campos de texto de subcondição
    private void desabilitaCamposSubcondicao() {
        descricaoSubcondicaoJTextArea.setEnabled(false);
        subcondicoesIdentificacaoDaCondicaoJTextField.setEnabled(false);
        //subcondicoesListaRelacoesJList.setEnabled(false);
        //subcondicoesListaRelacoesJList.setBackground(Color.LIGHT_GRAY);
    }

    //Habilita campos de texto de subcondicao
    private void habilitaCamposSubcondicao() {
        descricaoSubcondicaoJTextArea.setEnabled(true);
        subcondicoesIdentificacaoDaCondicaoJTextField.setEnabled(true);
        //subcondicoesListaRelacoesJList.setEnabled(true);
        //subcondicoesListaRelacoesJList.setBackground(Color.WHITE);
    }

    //Método que preenche a lista de grupo de relacoes
    private void preencheListaRelacoesSubcondicoes() {
        modeloListaSubcondicoes = null;
        modeloListaSubcondicoes = new DefaultListModel();
        if (listaSubcondicoes.size() > 0) {
            for (int i = 0; i < listaSubcondicoes.size(); i++) {
                modeloListaSubcondicoes.addElement(listaSubcondicoes.get(i).getIdentificacaoSubcondicao());
            }
            //subcondicoesListaRelacoesJList.setModel(modeloListaSubcondicoes);
        }
    }

    //Método que limpa a lista de relações de subcondições
    private void limparListaRelacoesSubcondicoes() {
        modeloListaSubcondicoes.clear();
    }

    //Método que marca as subcondições que possui relação com a subcondição selecionada
    private void marcaSubcondicao() {

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
        informacoesGeraisAdicionarNovoRiscoJButton = new javax.swing.JButton();
        informacoesGeraisLimparCamposJButton = new javax.swing.JButton();
        informacoesGeraisRemoverRiscoJButton = new javax.swing.JButton();
        estadoAtualRiscoJLabel = new javax.swing.JLabel();
        dataIdentificacaoDoRisco = new net.sf.nachocalendar.components.DateField();
        RelacoesJPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        relacoesListaInfluenciaJList = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        relacoesInfluenciadoJList = new javax.swing.JList();
        jScrollPane12 = new javax.swing.JScrollPane();
        relacoesListaRiscosJList = new javax.swing.JList();
        jLabel15 = new javax.swing.JLabel();
        relacoesInfluenciarRiscoJButton = new javax.swing.JButton();
        relacoesRemoverInfluenciaJButton = new javax.swing.JButton();
        SubcondicoesJPanel = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tabelaSubcondicoesJScrollPane = new javax.swing.JScrollPane();
        inserirCondicaoCampo1JButton = new javax.swing.JButton();
        campo1JTextField = new javax.swing.JTextField();
        inserirRelacaoCampo1JButton = new javax.swing.JButton();
        subcondicoesCriarCondicaoJButton = new javax.swing.JButton();
        subcondicoesSalvarAlteracoesDaCondicaoJButtob = new javax.swing.JButton();
        subcondicoesTipoDeRelacaoJComboBox = new javax.swing.JComboBox();
        jScrollPane15 = new javax.swing.JScrollPane();
        descricaoSubcondicaoJTextArea = new javax.swing.JTextPane();
        tabelaRelacoesJScrollPane = new javax.swing.JScrollPane();
        inserirCondicaoCampo2JButton = new javax.swing.JButton();
        inserirRelacaoCampo2JButton = new javax.swing.JButton();
        campo2JTextField = new javax.swing.JTextField();
        criarRelacaoJButton = new javax.swing.JButton();
        limparCamposJButton = new javax.swing.JButton();
        subcondicoesIdentificacaoDaCondicaoJTextField = new javax.swing.JTextField();
        deletarCondicaoJButton = new javax.swing.JButton();
        deletarRelacaoJButton = new javax.swing.JButton();
        PlanoDeMitigacaoJPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        planoDeMitigacaoResponsavelJTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        planoDeMitigacaoDescricaoJTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        planoDeMitigacaoComoSeraFeitoJTextArea = new javax.swing.JTextArea();
        planoDeMitigacaoIdentificacaoJTextField = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        planoDeMitigacaoListaPlanosJList = new javax.swing.JList();
        planoMitigacaoDataLimiteJComboBox = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        planoMitigacaoDataSelecionadaJLabel = new javax.swing.JLabel();
        planoDeMitigacaoAdicionarPlanoJButton = new javax.swing.JButton();
        planoDeMitigacaoSalvarAlteracoesJButton = new javax.swing.JButton();
        planoDeMitigacaoRemoverPlanoJButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        planoDeMitigacaoInfAdicionaisJTextArea = new javax.swing.JTextArea();
        planoDeMitigacaoPlanoDeMitigacaoJLabel = new javax.swing.JLabel();
        PlanoContigenciaJPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        planoDeContingenciaListaPlanosJList = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        planoDeContingenciaResponsavelJTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        planoDeContingenciaDescricaoJTextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        planoDeContingenciaComoSeraFeitoJTextArea = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        planoDeContingenciaInfAdicionaisJTextArea = new javax.swing.JTextArea();
        planoDeContingenciaIdentificacaoJTextField = new javax.swing.JTextField();
        planoContingenciaDataLimiteJComboBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        planoContingenciaDataSelecionadaJLabel = new javax.swing.JLabel();
        planoDeContingenciaAdicionarPlanoJButton = new javax.swing.JButton();
        planoDeContingenciaRemoverPlanoJButton = new javax.swing.JButton();
        planoDeContingenciaSalvarAlteracoesJButton = new javax.swing.JButton();
        planoContigenciaPlanoDeContigenciaJLabel = new javax.swing.JLabel();
        HistoricoDeAlteracoesJPanel = new javax.swing.JPanel();
        tabelaHistoricoAlteracoesJScrollPane = new javax.swing.JScrollPane();
        tabelaRiscosJScrollPane = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();

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

        informacoesGeraisImpactoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selecione--", "Crítico", "Alto", "Médio", "Baixo" }));
        informacoesGeraisImpactoJComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                informacoesGeraisImpactoJComboBoxItemStateChanged(evt);
            }
        });
        informacoesGeraisImpactoJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesGeraisImpactoJComboBoxActionPerformed(evt);
            }
        });
        informacoesGeraisImpactoJComboBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                informacoesGeraisImpactoJComboBoxFocusLost(evt);
            }
        });

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

        informacoesGeraisGrauDeSeveridadeJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesGeraisGrauDeSeveridadeJTextFieldActionPerformed(evt);
            }
        });

        informacoesGeraisProbabilidadeJSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        informacoesGeraisProbabilidadeJSpinner.setFocusable(false);
        informacoesGeraisProbabilidadeJSpinner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                informacoesGeraisProbabilidadeJSpinnerMouseClicked(evt);
            }
        });
        informacoesGeraisProbabilidadeJSpinner.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                informacoesGeraisProbabilidadeJSpinnerFocusLost(evt);
            }
        });
        informacoesGeraisProbabilidadeJSpinner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                informacoesGeraisProbabilidadeJSpinnerKeyTyped(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InformacoesGeraisJPanelLayout.createSequentialGroup()
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(informacoesGeraisAdicionarNovoRiscoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(informacoesGeraisSalvarAlteracoesDoRiscoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(informacoesGeraisEmissorJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisDataDeIdentificacaoJLabel)
                            .addComponent(informacoesGeraisIdentificacaoDeRiscoJLabel)
                            .addComponent(informacoesGeraisProbabilidadeJLabel)
                            .addComponent(informacoesGeraisCategoriaDeRiscoJLabel)
                            .addComponent(informacoesGeraisImpactoJLabel)
                            .addComponent(informacoesGeraisEstadoAtualJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                .addComponent(informacoesGeraisProbabilidadeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(informacoesGeraisProbabilidadePorcentagemJLabel))
                            .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(informacoesGeraisCategoriaDeRiscoJComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(informacoesGeraisEmissorJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(informacoesGeraisIdentificacaoDeRiscoJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(dataIdentificacaoDoRisco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(informacoesGeraisImpactoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estadoAtualRiscoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InformacoesGeraisJPanelLayout.createSequentialGroup()
                                .addComponent(informacoesGeraisGrauDeSeveridadeJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(informacoesGeraisGrauDeSeveridadeJTextField))
                            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                                .addComponent(informacoesGeraisLimparCamposJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(informacoesGeraisRemoverRiscoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(informacoesGeraisDescricaoDeRiscoJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        InformacoesGeraisJPanelLayout.setVerticalGroup(
            InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisIdentificacaoDeRiscoJLabel)
                            .addComponent(informacoesGeraisIdentificacaoDeRiscoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(informacoesGeraisDataDeIdentificacaoJLabel)
                            .addComponent(dataIdentificacaoDoRisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisEmissorJLabel)
                            .addComponent(informacoesGeraisEmissorJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisCategoriaDeRiscoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisCategoriaDeRiscoJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisProbabilidadeJLabel)
                            .addComponent(informacoesGeraisProbabilidadeJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisProbabilidadePorcentagemJLabel))
                        .addGap(13, 13, 13)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisImpactoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informacoesGeraisImpactoJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisEstadoAtualJLabel)
                            .addComponent(estadoAtualRiscoJLabel)))
                    .addGroup(InformacoesGeraisJPanelLayout.createSequentialGroup()
                        .addComponent(informacoesGeraisDescricaoDeRiscoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(informacoesGeraisGrauDeSeveridadeJLabel)
                            .addComponent(informacoesGeraisGrauDeSeveridadeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(informacoesGeraisSalvarAlteracoesDoRiscoJButton)
                    .addComponent(informacoesGeraisAdicionarNovoRiscoJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InformacoesGeraisJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(informacoesGeraisRemoverRiscoJButton)
                    .addComponent(informacoesGeraisLimparCamposJButton))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        gerenciarRiscosJTabbedPane.addTab("Informações Gerais", InformacoesGeraisJPanel);

        jLabel13.setText("O risco influencia em:");

        jScrollPane1.setViewportView(relacoesListaInfluenciaJList);

        jLabel14.setText("O risco é influenciado por:");

        jScrollPane10.setViewportView(relacoesInfluenciadoJList);

        jScrollPane12.setViewportView(relacoesListaRiscosJList);

        jLabel15.setText("Lista de riscos:");

        relacoesInfluenciarRiscoJButton.setText("Marcar Influência - v");
        relacoesInfluenciarRiscoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relacoesInfluenciarRiscoJButtonActionPerformed(evt);
            }
        });

        relacoesRemoverInfluenciaJButton.setText("Remover Influência - ^");
        relacoesRemoverInfluenciaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relacoesRemoverInfluenciaJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(relacoesInfluenciarRiscoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(55, 55, 55)
                                .addComponent(relacoesRemoverInfluenciaJButton))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relacoesInfluenciarRiscoJButton)
                    .addComponent(relacoesRemoverInfluenciaJButton))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel16.setText("Identificação da Condição:");

        jLabel17.setText("Descrição: ");

        tabelaSubcondicoesJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Condições"));

        inserirCondicaoCampo1JButton.setText("Inserir condição (campo 1) - v");
        inserirCondicaoCampo1JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirCondicaoCampo1JButtonActionPerformed(evt);
            }
        });

        campo1JTextField.setEditable(false);

        inserirRelacaoCampo1JButton.setText("Inserir relação (campo 1) - ^");
        inserirRelacaoCampo1JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirRelacaoCampo1JButtonActionPerformed(evt);
            }
        });

        subcondicoesCriarCondicaoJButton.setText("Criar Condição");
        subcondicoesCriarCondicaoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subcondicoesCriarCondicaoJButtonActionPerformed(evt);
            }
        });

        subcondicoesSalvarAlteracoesDaCondicaoJButtob.setText("Salvar Alterações da Condicao");
        subcondicoesSalvarAlteracoesDaCondicaoJButtob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subcondicoesSalvarAlteracoesDaCondicaoJButtobActionPerformed(evt);
            }
        });

        subcondicoesTipoDeRelacaoJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E", "OU" }));

        jScrollPane15.setViewportView(descricaoSubcondicaoJTextArea);

        tabelaRelacoesJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Relações"));

        inserirCondicaoCampo2JButton.setText("Inserir condição (campo 2) - v");
        inserirCondicaoCampo2JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirCondicaoCampo2JButtonActionPerformed(evt);
            }
        });

        inserirRelacaoCampo2JButton.setText("Inserir relação (campo 2) - ^");
        inserirRelacaoCampo2JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirRelacaoCampo2JButtonActionPerformed(evt);
            }
        });

        campo2JTextField.setEditable(false);

        criarRelacaoJButton.setText("Criar Relação");
        criarRelacaoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarRelacaoJButtonActionPerformed(evt);
            }
        });

        limparCamposJButton.setText("Limpar Campos");
        limparCamposJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparCamposJButtonActionPerformed(evt);
            }
        });

        subcondicoesIdentificacaoDaCondicaoJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subcondicoesIdentificacaoDaCondicaoJTextFieldActionPerformed(evt);
            }
        });

        deletarCondicaoJButton.setText("Deletar Condição");
        deletarCondicaoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarCondicaoJButtonActionPerformed(evt);
            }
        });

        deletarRelacaoJButton.setText("Deletar Relação");
        deletarRelacaoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarRelacaoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subcondicoesIdentificacaoDaCondicaoJTextField))
                    .addComponent(tabelaRelacoesJScrollPane)
                    .addComponent(tabelaSubcondicoesJScrollPane)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(deletarCondicaoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(limparCamposJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subcondicoesSalvarAlteracoesDaCondicaoJButtob)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subcondicoesCriarCondicaoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inserirCondicaoCampo1JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campo1JTextField)
                                    .addComponent(inserirRelacaoCampo1JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subcondicoesTipoDeRelacaoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inserirCondicaoCampo2JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inserirRelacaoCampo2JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campo2JTextField))
                                .addGap(93, 93, 93)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(criarRelacaoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(150, 150, 150))
                                    .addComponent(deletarRelacaoJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(subcondicoesIdentificacaoDaCondicaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subcondicoesCriarCondicaoJButton)
                    .addComponent(subcondicoesSalvarAlteracoesDaCondicaoJButtob)
                    .addComponent(limparCamposJButton)
                    .addComponent(deletarCondicaoJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaSubcondicoesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inserirCondicaoCampo1JButton)
                    .addComponent(inserirCondicaoCampo2JButton)
                    .addComponent(criarRelacaoJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campo1JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subcondicoesTipoDeRelacaoJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo2JTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletarRelacaoJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inserirRelacaoCampo1JButton)
                    .addComponent(inserirRelacaoCampo2JButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaRelacoesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane13.setViewportView(jPanel6);

        javax.swing.GroupLayout SubcondicoesJPanelLayout = new javax.swing.GroupLayout(SubcondicoesJPanel);
        SubcondicoesJPanel.setLayout(SubcondicoesJPanelLayout);
        SubcondicoesJPanelLayout.setHorizontalGroup(
            SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        SubcondicoesJPanelLayout.setVerticalGroup(
            SubcondicoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        gerenciarRiscosJTabbedPane.addTab("Condições", SubcondicoesJPanel);

        jLabel1.setText("Identificação do Plano: ");

        jLabel2.setText("Responsável: ");

        planoDeMitigacaoDescricaoJTextArea.setColumns(20);
        planoDeMitigacaoDescricaoJTextArea.setLineWrap(true);
        planoDeMitigacaoDescricaoJTextArea.setRows(1);
        planoDeMitigacaoDescricaoJTextArea.setWrapStyleWord(true);
        planoDeMitigacaoDescricaoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição:"));
        jScrollPane2.setViewportView(planoDeMitigacaoDescricaoJTextArea);

        jLabel4.setText("Data Limite: ");

        planoDeMitigacaoComoSeraFeitoJTextArea.setColumns(20);
        planoDeMitigacaoComoSeraFeitoJTextArea.setLineWrap(true);
        planoDeMitigacaoComoSeraFeitoJTextArea.setRows(1);
        planoDeMitigacaoComoSeraFeitoJTextArea.setWrapStyleWord(true);
        planoDeMitigacaoComoSeraFeitoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Como será feito:"));
        jScrollPane3.setViewportView(planoDeMitigacaoComoSeraFeitoJTextArea);

        jScrollPane9.setViewportView(planoDeMitigacaoListaPlanosJList);

        planoMitigacaoDataLimiteJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoMitigacaoDataLimiteJComboBoxActionPerformed(evt);
            }
        });

        jLabel18.setText("Data:");

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

        planoDeMitigacaoInfAdicionaisJTextArea.setColumns(20);
        planoDeMitigacaoInfAdicionaisJTextArea.setLineWrap(true);
        planoDeMitigacaoInfAdicionaisJTextArea.setRows(1);
        planoDeMitigacaoInfAdicionaisJTextArea.setWrapStyleWord(true);
        planoDeMitigacaoInfAdicionaisJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais:"));
        jScrollPane4.setViewportView(planoDeMitigacaoInfAdicionaisJTextArea);

        planoDeMitigacaoPlanoDeMitigacaoJLabel.setText("Plano de Mitigação:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(planoDeMitigacaoAdicionarPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(planoDeMitigacaoRemoverPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(planoDeMitigacaoSalvarAlteracoesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(planoDeMitigacaoPlanoDeMitigacaoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(planoDeMitigacaoResponsavelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(41, 41, 41)
                                        .addComponent(planoMitigacaoDataSelecionadaJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(planoMitigacaoDataLimiteJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(planoDeMitigacaoIdentificacaoJTextField)))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(planoDeMitigacaoIdentificacaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(planoDeMitigacaoPlanoDeMitigacaoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(planoDeMitigacaoResponsavelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(planoMitigacaoDataLimiteJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(planoMitigacaoDataSelecionadaJLabel)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                    .addComponent(jScrollPane9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(planoDeMitigacaoAdicionarPlanoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planoDeMitigacaoRemoverPlanoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planoDeMitigacaoSalvarAlteracoesJButton))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        javax.swing.GroupLayout PlanoDeMitigacaoJPanelLayout = new javax.swing.GroupLayout(PlanoDeMitigacaoJPanel);
        PlanoDeMitigacaoJPanel.setLayout(PlanoDeMitigacaoJPanelLayout);
        PlanoDeMitigacaoJPanelLayout.setHorizontalGroup(
            PlanoDeMitigacaoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PlanoDeMitigacaoJPanelLayout.setVerticalGroup(
            PlanoDeMitigacaoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlanoDeMitigacaoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gerenciarRiscosJTabbedPane.addTab("Plano de Mitigação", PlanoDeMitigacaoJPanel);

        jScrollPane5.setViewportView(planoDeContingenciaListaPlanosJList);

        jLabel7.setText("Identificação do Plano: ");

        jLabel8.setText("Responsável:");

        jLabel9.setText("Data Limite:");

        planoDeContingenciaDescricaoJTextArea.setColumns(20);
        planoDeContingenciaDescricaoJTextArea.setLineWrap(true);
        planoDeContingenciaDescricaoJTextArea.setRows(1);
        planoDeContingenciaDescricaoJTextArea.setWrapStyleWord(true);
        planoDeContingenciaDescricaoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição:"));
        jScrollPane6.setViewportView(planoDeContingenciaDescricaoJTextArea);

        planoDeContingenciaComoSeraFeitoJTextArea.setColumns(20);
        planoDeContingenciaComoSeraFeitoJTextArea.setLineWrap(true);
        planoDeContingenciaComoSeraFeitoJTextArea.setRows(1);
        planoDeContingenciaComoSeraFeitoJTextArea.setWrapStyleWord(true);
        planoDeContingenciaComoSeraFeitoJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Como será feito:"));
        jScrollPane7.setViewportView(planoDeContingenciaComoSeraFeitoJTextArea);

        planoDeContingenciaInfAdicionaisJTextArea.setColumns(20);
        planoDeContingenciaInfAdicionaisJTextArea.setLineWrap(true);
        planoDeContingenciaInfAdicionaisJTextArea.setRows(1);
        planoDeContingenciaInfAdicionaisJTextArea.setWrapStyleWord(true);
        planoDeContingenciaInfAdicionaisJTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais:"));
        jScrollPane8.setViewportView(planoDeContingenciaInfAdicionaisJTextArea);

        planoContingenciaDataLimiteJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planoContingenciaDataLimiteJComboBoxActionPerformed(evt);
            }
        });

        jLabel19.setText("Data:");

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

        planoContigenciaPlanoDeContigenciaJLabel.setText("Plano de Contigência:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(planoContigenciaPlanoDeContigenciaJLabel)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(planoDeContingenciaResponsavelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(planoContingenciaDataSelecionadaJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE))
                            .addComponent(planoDeContingenciaIdentificacaoJTextField)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(planoDeContingenciaAdicionarPlanoJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(planoDeContingenciaRemoverPlanoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(planoDeContingenciaSalvarAlteracoesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(297, 297, 297)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(planoContingenciaDataLimiteJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(planoDeContingenciaIdentificacaoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(planoContigenciaPlanoDeContigenciaJLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(planoDeContingenciaResponsavelJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(planoContingenciaDataLimiteJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(planoContingenciaDataSelecionadaJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(planoDeContingenciaAdicionarPlanoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planoDeContingenciaRemoverPlanoJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planoDeContingenciaSalvarAlteracoesJButton))
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );

        javax.swing.GroupLayout PlanoContigenciaJPanelLayout = new javax.swing.GroupLayout(PlanoContigenciaJPanel);
        PlanoContigenciaJPanel.setLayout(PlanoContigenciaJPanelLayout);
        PlanoContigenciaJPanelLayout.setHorizontalGroup(
            PlanoContigenciaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PlanoContigenciaJPanelLayout.setVerticalGroup(
            PlanoContigenciaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlanoContigenciaJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gerenciarRiscosJTabbedPane.addTab("Plano de Contigência", PlanoContigenciaJPanel);

        javax.swing.GroupLayout HistoricoDeAlteracoesJPanelLayout = new javax.swing.GroupLayout(HistoricoDeAlteracoesJPanel);
        HistoricoDeAlteracoesJPanel.setLayout(HistoricoDeAlteracoesJPanelLayout);
        HistoricoDeAlteracoesJPanelLayout.setHorizontalGroup(
            HistoricoDeAlteracoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaHistoricoAlteracoesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        HistoricoDeAlteracoesJPanelLayout.setVerticalGroup(
            HistoricoDeAlteracoesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabelaHistoricoAlteracoesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        gerenciarRiscosJTabbedPane.addTab("Histórico de Alterações", HistoricoDeAlteracoesJPanel);

        jLabel3.setText("Tabela de Riscos:");

        javax.swing.GroupLayout gerenciarRiscosJPanelLayout = new javax.swing.GroupLayout(gerenciarRiscosJPanel);
        gerenciarRiscosJPanel.setLayout(gerenciarRiscosJPanelLayout);
        gerenciarRiscosJPanelLayout.setHorizontalGroup(
            gerenciarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gerenciarRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gerenciarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gerenciarRiscosJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabelaRiscosJScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gerenciarRiscosJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 837, Short.MAX_VALUE))
                .addContainerGap())
        );
        gerenciarRiscosJPanelLayout.setVerticalGroup(
            gerenciarRiscosJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gerenciarRiscosJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gerenciarRiscosJTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaRiscosJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gerenciarRiscosJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gerenciarRiscosJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void informacoesGeraisAdicionarNovoRiscoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisAdicionarNovoRiscoJButtonActionPerformed
        // Pertence a aba Informacoes Gerais
        Risco novoRisco = new Risco();
        boolean valorNulo = false;
        novoRisco.setEmissor(informacoesGeraisEmissorJTextField.getText());
        if ("".equals(novoRisco.getEmissor())) {
            valorNulo = true;
        }
        novoRisco.setDataIdentificacao((Date) dataIdentificacaoDoRisco.getValue());
        if (novoRisco.getDataIdentificacao() == null) {
            valorNulo = true;
        }
        novoRisco.setDescricao(InformacoesGeraisDescricaoDeRiscoJTextArea.getText());
        if ("".equals(novoRisco.getDescricao())) {
            valorNulo = true;
        }
        novoRisco.setIdentificacao(informacoesGeraisIdentificacaoDeRiscoJTextField.getText());
        if ("".equals(novoRisco.getIdentificacao())) {
            valorNulo = true;
        }

        novoRisco.setProbabilidade(Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString()));
        if (!(novoRisco.getProbabilidade() >= 0) && (novoRisco.getProbabilidade() <= 100)) {
            valorNulo = true;
        }

        novoRisco.setImpacto(informacoesGeraisImpactoJComboBox.getSelectedItem().toString());
        if (novoRisco.getImpacto().equals("--Selecione--")) {
            valorNulo = true;
        }

        if (valorNulo == true) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos apresentam valores invalidos. Preencha-os corretamente antes de adicionar um novo risco");
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
                    getListaderiscosDoprojeto();
                    for (int i = 0; i < listaRiscosDoProjeto.size(); i++) {
                        if (novoRisco.getIdentificacao().equals(listaRiscosDoProjeto.get(i).getIdentificacao())) {
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
                        getListaderiscosDoprojeto();
                        contemComboBox.setContemPK(contemPK);

                        novoRisco.setContem(contemComboBox);

                        String impactoSelecionado = informacoesGeraisImpactoJComboBox.getSelectedItem().toString();
                        novoRisco.setImpacto(impactoSelecionado);

                        novoRisco.setGrauSeveridade(Double.parseDouble(informacoesGeraisGrauDeSeveridadeJTextField.getText()));
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
                        historico.setIdRisco(novoRisco);
                        riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                        getListaHistoricoAlteracoes(novoRisco);
                        limparTabelaHistoricoAlteracoes();
                        preencheTabelaHistoricoAlteracoes();

                        limparCampos();
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
        Risco novoRisco = new Risco();
        boolean valorNulo = false;
        novoRisco.setEmissor(informacoesGeraisEmissorJTextField.getText());
        if ("".equals(novoRisco.getEmissor())) {
            valorNulo = true;
        }
        novoRisco.setDataIdentificacao((Date) dataIdentificacaoDoRisco.getValue());
        if (novoRisco.getDataIdentificacao() == null) {
            valorNulo = true;
        }
        novoRisco.setDescricao(InformacoesGeraisDescricaoDeRiscoJTextArea.getText());
        if ("".equals(novoRisco.getDescricao())) {
            valorNulo = true;
        }
        novoRisco.setIdentificacao(informacoesGeraisIdentificacaoDeRiscoJTextField.getText());
        if ("".equals(novoRisco.getIdentificacao())) {
            valorNulo = true;
        }

        novoRisco.setProbabilidade(Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString()));
        if (!(novoRisco.getProbabilidade() >= 0) && (novoRisco.getProbabilidade() <= 100)) {
            valorNulo = true;
        }

        novoRisco.setImpacto(informacoesGeraisImpactoJComboBox.getSelectedItem().toString());
        if (novoRisco.getImpacto().equals("--Selecione--")) {
            valorNulo = true;
        }

        if (valorNulo == true) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos apresentam valores invalidos. Preencha-os corretamente antes de adicionar um novo risco");

        } else if (riscoSelecionado.getIdRisco() > 0) {
            int linhaSelecionada = tabelaRiscosJTable.getSelectedRow();
            riscoSelecionado.setEmissor(informacoesGeraisEmissorJTextField.getText());
            riscoSelecionado.setDataIdentificacao((Date) dataIdentificacaoDoRisco.getValue());
            riscoSelecionado.setDescricao(InformacoesGeraisDescricaoDeRiscoJTextArea.getText());
            riscoSelecionado.setIdentificacao(informacoesGeraisIdentificacaoDeRiscoJTextField.getText());
            riscoSelecionado.setGrauSeveridade(Double.parseDouble(informacoesGeraisGrauDeSeveridadeJTextField.getText()));
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

            limparCampos();
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
        if (planoMitigacaoSelecionado.getIdPlanoMitigacao() > 0 && planoMitigacaoDataLimiteJComboBox.getSelectedItem() != "--Selecione--") {
            int indexSelecionado = planoDeMitigacaoListaPlanosJList.getSelectedIndex();

            planoMitigacaoSelecionado.setIdentificacaoPlanoMitigacao(planoDeMitigacaoIdentificacaoJTextField.getText());
            planoMitigacaoSelecionado.setResponsavel(planoDeMitigacaoResponsavelJTextField.getText());

            if (mitigacaoEhMarco == true) {

                planoMitigacaoSelecionado.setIdMarcoDoProjeto(marcoSelecionado);
                planoMitigacaoSelecionado.setIdPontoDeControle(null);
            } else {

                planoMitigacaoSelecionado.setIdPontoDeControle(pontoControleSelecionado);
                planoMitigacaoSelecionado.setIdMarcoDoProjeto(null);
            }

            planoMitigacaoSelecionado.setDescricaoPlanoMitigacao(planoDeMitigacaoDescricaoJTextArea.getText());
            planoMitigacaoSelecionado.setComoRealizar(planoDeMitigacaoComoSeraFeitoJTextArea.getText());
            planoMitigacaoSelecionado.setInformacoesAdicionais(planoDeMitigacaoInfAdicionaisJTextArea.getText());

            riscosGerenciarRiscosFacade.editarPlanoMitigacao(planoMitigacaoSelecionado);
            JOptionPane.showMessageDialog(null, "Informações do Plano de Mitigacao alteradas com sucesso");
            atualizaPreencheEventosListaPlanoMitigacao();
            planoDeMitigacaoListaPlanosJList.setSelectedIndex(indexSelecionado);
            Historicoalteracao historico = new Historicoalteracao();
            historico.setDescricaoAlteracao("Plano de mitigação " + planoMitigacaoSelecionado.getIdentificacaoPlanoMitigacao() + " alterado.");
            Calendar c = Calendar.getInstance();
            historico.setDataAlteracao(c.getTime());
            historico.setIdRisco(riscoSelecionado);
            riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
            getListaHistoricoAlteracoes(riscoSelecionado);
            limparTabelaHistoricoAlteracoes();
            preencheTabelaHistoricoAlteracoes();
        } else {
            JOptionPane.showMessageDialog(null, "Você precisa escolher uma data limite.");
        }
    }//GEN-LAST:event_planoDeMitigacaoSalvarAlteracoesJButtonActionPerformed

    private void planoDeMitigacaoAdicionarPlanoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeMitigacaoAdicionarPlanoJButtonActionPerformed
        //Pertence a aba Plano de Mitigação

        Planomitigacao novoPlanoMitigacao = new Planomitigacao();
        novoPlanoMitigacao.setResponsavel(planoDeMitigacaoResponsavelJTextField.getText());

        if (mitigacaoEhMarco == true) {

            novoPlanoMitigacao.setIdMarcoDoProjeto(marcoSelecionado);
        } else {

            novoPlanoMitigacao.setIdPontoDeControle(pontoControleSelecionado);
        }
        novoPlanoMitigacao.setDescricaoPlanoMitigacao(planoDeMitigacaoDescricaoJTextArea.getText());
        novoPlanoMitigacao.setComoRealizar(planoDeMitigacaoComoSeraFeitoJTextArea.getText());
        novoPlanoMitigacao.setInformacoesAdicionais(planoDeMitigacaoInfAdicionaisJTextArea.getText());
        novoPlanoMitigacao.setIdRisco(riscoSelecionado);
        novoPlanoMitigacao.setIdentificacaoPlanoMitigacao(planoDeMitigacaoIdentificacaoJTextField.getText());

        boolean nulo = false;

        if ("".equals(novoPlanoMitigacao.getResponsavel())) {
            nulo = true;
        }
        if ("".equals(novoPlanoMitigacao.getComoRealizar())) {
            nulo = true;
        }
        if ("".equals(novoPlanoMitigacao.getIdentificacaoPlanoMitigacao())) {
            nulo = true;
        }
        if ("".equals(novoPlanoMitigacao.getDescricaoPlanoMitigacao())) {
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
                if (planoMitigacaoDataLimiteJComboBox.getSelectedItem() != "--Selecione--") {
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
                } else {
                    JOptionPane.showMessageDialog(null, "Você precisa escolher uma data limite.");
                }
            }

        }

    }//GEN-LAST:event_planoDeMitigacaoAdicionarPlanoJButtonActionPerformed

    private void planoDeContingenciaAdicionarPlanoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoDeContingenciaAdicionarPlanoJButtonActionPerformed
        //Pertence a aba Plano de Contingencia
        Planocontingencia novoPlanoContingencia = new Planocontingencia();
        novoPlanoContingencia.setResponsavel(planoDeContingenciaResponsavelJTextField.getText());
        //Calendar dataIdentificacao = Calendar.getInstance();
        //Date data = dataIdentificacao.getTime();

        if (contingenciaEhMarco == true) {

            novoPlanoContingencia.setIdMarcoDoProjeto(marcoSelecionado);
        } else {

            novoPlanoContingencia.setIdPontoDeControle(pontoControleSelecionado);
        }
        novoPlanoContingencia.setDescricaoPlanoContingencia(planoDeContingenciaDescricaoJTextArea.getText());
        novoPlanoContingencia.setComoRealizar(planoDeContingenciaComoSeraFeitoJTextArea.getText());
        novoPlanoContingencia.setInformacoesAdicionais(planoDeContingenciaInfAdicionaisJTextArea.getText());
        novoPlanoContingencia.setIdRisco(riscoSelecionado);
        novoPlanoContingencia.setIdentificacaoPlanoContingencia(planoDeContingenciaIdentificacaoJTextField.getText());

        boolean nulo = false;

        if ("".equals(novoPlanoContingencia.getResponsavel())) {
            nulo = true;
        }
        if ("".equals(novoPlanoContingencia.getComoRealizar())) {
            nulo = true;
        }
        if ("".equals(novoPlanoContingencia.getIdentificacaoPlanoContingencia())) {
            nulo = true;
        }
        if ("".equals(novoPlanoContingencia.getDescricaoPlanoContingencia())) {
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
                if (planoContingenciaDataLimiteJComboBox.getSelectedItem() != "--Selecione--") {
                    riscosGerenciarRiscosFacade.adicionarPlanoContingencia(novoPlanoContingencia);
                    iniciaBotoesCinzaPlanoContingencia();
                    atualizaPreencheEventosListaPlanoContingencia();
                    planoDeContingenciaAdicionarPlanoJButton.setEnabled(true);
                    limparCamposPlanoContingencia();

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
                } else {
                    JOptionPane.showMessageDialog(null, "Você precisa escolher uma data limite.");
                }
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
        if (planoContingenciaSelecionado.getIdPlanoContingencia() > 0 && planoContingenciaDataLimiteJComboBox.getSelectedItem() != "--Selecione--") {
            int indexSelecionado = planoDeContingenciaListaPlanosJList.getSelectedIndex();

            planoContingenciaSelecionado.setResponsavel(planoDeContingenciaResponsavelJTextField.getText());
            if (contingenciaEhMarco == true) {

                planoContingenciaSelecionado.setIdMarcoDoProjeto(marcoSelecionado);
                planoContingenciaSelecionado.setIdPontoDeControle(null);
            } else {

                planoContingenciaSelecionado.setIdPontoDeControle(pontoControleSelecionado);
                planoContingenciaSelecionado.setIdMarcoDoProjeto(null);
            }

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
        } else {
            JOptionPane.showMessageDialog(null, "Você precisa escolher uma data limite.");
        }
    }//GEN-LAST:event_planoDeContingenciaSalvarAlteracoesJButtonActionPerformed

    private void relacoesInfluenciarRiscoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relacoesInfluenciarRiscoJButtonActionPerformed

        try {
            if (riscoSelecionadoRelacoes != null) {
                List<Risco> listaInfluneciaTemp = riscoSelecionado.getRiscoList2();
                listaInfluneciaTemp.add(riscoSelecionadoRelacoes);
                riscoSelecionado.setRiscoList2(listaInfluneciaTemp);
                try {
                    //riscosGerenciarRiscosFacade.editarRisco(riscoSelecionado);
                    riscosGerenciarRiscosFacade.inserirRelacoesEntreRiscos(riscoSelecionadoRelacoes, riscoSelecionado);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar influência. Tabela desatualizada.");
                }

                JOptionPane.showMessageDialog(null, "O risco " + riscoSelecionado.getIdentificacao() + " agora influencia em " + riscoSelecionadoRelacoes.getIdentificacao());
                if (riscosNaoAdd.size() > 0) {
                    riscosNaoAdd = null;
                    riscosNaoAdd = riscosGerenciarRiscosFacade.listarRiscos();
                    riscosNaoAdd.clear();
                }
                modelListaRiscosApresentados.clear();
                modelListaRiscosInfluencia.clear();
                modelListaRiscosInfluenciados.clear();
                atualizaPreencheListaRiscosInfluencia();
                preencheListaRiscosInfluenciados();
                atualizaPreencheListaRiscosApresentados();
                riscoSelecionadoRelacoes = null;
                Historicoalteracao historico = new Historicoalteracao();
                historico.setDescricaoAlteracao("Modificação nas relações do risco " + riscoSelecionado.getIdentificacao());
                Calendar c = Calendar.getInstance();
                historico.setDataAlteracao(c.getTime());
                historico.setIdRisco(riscoSelecionado);
                riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                getListaHistoricoAlteracoes(riscoSelecionado);
                limparTabelaHistoricoAlteracoes();
                preencheTabelaHistoricoAlteracoes();
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum risco selecionado na tabela acima. Selecione um para adicionar influência.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum risco selecionado na tabela acima. Selecione um para adicionar influência.");
        }
    }//GEN-LAST:event_relacoesInfluenciarRiscoJButtonActionPerformed

    private void relacoesRemoverInfluenciaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relacoesRemoverInfluenciaJButtonActionPerformed

        try {
            if (riscoSelecionadoInfluencia != null && riscoSelecionado != null) {
                List<Risco> listaInfluenciaTemp = riscoSelecionado.getRiscoList2();
                if (listaInfluenciaTemp.size() > 0) {
                    for (int i = 0; i < listaInfluenciaTemp.size(); i++) {
                        if (Objects.equals(listaInfluenciaTemp.get(i), riscoSelecionadoInfluencia)) {
                            listaInfluenciaTemp.remove(i);
                            break;
                        }
                    }
                }
                riscoSelecionado.setRiscoList2(listaInfluenciaTemp);
                try {
                    riscosGerenciarRiscosFacade.editarRisco(riscoSelecionado);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao remover influência do risco. Tabela desatualizada.");
                }
                JOptionPane.showMessageDialog(null, "Influência de " + riscoSelecionadoInfluencia.getIdentificacao() + " removida do risco " + riscoSelecionado.getIdentificacao());
                if (riscosNaoAdd.size() > 0) {
                    riscosNaoAdd = null;
                    riscosNaoAdd = riscosGerenciarRiscosFacade.listarRiscos();
                    riscosNaoAdd.clear();
                }
                modelListaRiscosApresentados.clear();
                modelListaRiscosInfluencia.clear();
                modelListaRiscosInfluenciados.clear();
                atualizaPreencheListaRiscosInfluencia();
                preencheListaRiscosInfluenciados();
                atualizaPreencheListaRiscosApresentados();
                riscoSelecionadoInfluencia = null;
                Historicoalteracao historico = new Historicoalteracao();
                historico.setDescricaoAlteracao("Modificação nas relações do risco " + riscoSelecionado.getIdentificacao());
                Calendar c = Calendar.getInstance();
                historico.setDataAlteracao(c.getTime());
                historico.setIdRisco(riscoSelecionado);
                riscosGerenciarRiscosFacade.criaHistorioAlteracao(historico);
                getListaHistoricoAlteracoes(riscoSelecionado);
                limparTabelaHistoricoAlteracoes();
                preencheTabelaHistoricoAlteracoes();
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum risco selecionado na tabela abaixo à esquerda. Selecione um para remover influência");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum risco selecionado na tabela abaixo à esquerda. Selecione um para remover influência");
        }
    }//GEN-LAST:event_relacoesRemoverInfluenciaJButtonActionPerformed

    private void planoMitigacaoDataLimiteJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoMitigacaoDataLimiteJComboBoxActionPerformed

        if (planoMitigacaoDataLimiteJComboBox.getSelectedItem() != null) {
            String nomeItem = planoMitigacaoDataLimiteJComboBox.getSelectedItem().toString();
            mitigacaoEhMarco = false;

            for (int i = 0; i < listaMarcosProjeto.size(); i++) {
                if (listaMarcosProjeto.get(i).getNomeMarcoDoProjeto().equals(nomeItem)) {
                    mitigacaoEhMarco = true;
                    Calendar dataMarcoSelecionado = Calendar.getInstance();

                    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                    dataMarcoSelecionado.setTime(listaMarcosProjeto.get(i).getDataMarcoProjeto());
                    String dataImprimida = df.format(dataMarcoSelecionado.getTime());
                    planoMitigacaoDataSelecionadaJLabel.setText(dataImprimida);
                    marcoSelecionado = listaMarcosProjeto.get(i);
                    pontoControleSelecionado = null;
                    System.out.println("Item: " + marcoSelecionado.getNomeMarcoDoProjeto());
                }
            }

            if (mitigacaoEhMarco == false) {
                for (int i = 0; i < listaPontosControle.size(); i++) {
                    if (listaPontosControle.get(i).getNomePontoDeControle().equals(nomeItem)) {
                        Calendar dataPontoSelecionado = Calendar.getInstance();

                        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                        dataPontoSelecionado.setTime(listaPontosControle.get(i).getDataPontoControle());
                        String dataImprimida = df.format(dataPontoSelecionado.getTime());
                        planoMitigacaoDataSelecionadaJLabel.setText(dataImprimida);
                        pontoControleSelecionado = listaPontosControle.get(i);
                        marcoSelecionado = null;
                        System.out.println("Item: " + pontoControleSelecionado.getNomePontoDeControle());
                    }
                }
            }
        }
    }//GEN-LAST:event_planoMitigacaoDataLimiteJComboBoxActionPerformed

    private void planoContingenciaDataLimiteJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planoContingenciaDataLimiteJComboBoxActionPerformed
        if (planoContingenciaDataLimiteJComboBox.getSelectedItem() != null) {
            String nomeItem = planoContingenciaDataLimiteJComboBox.getSelectedItem().toString();
            contingenciaEhMarco = false;

            for (int i = 0; i < listaMarcosProjeto.size(); i++) {
                if (listaMarcosProjeto.get(i).getNomeMarcoDoProjeto().equals(nomeItem)) {
                    contingenciaEhMarco = true;
                    Calendar dataMarcoSelecionado = Calendar.getInstance();

                    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                    dataMarcoSelecionado.setTime(listaMarcosProjeto.get(i).getDataMarcoProjeto());
                    String dataImprimida = df.format(dataMarcoSelecionado.getTime());
                    planoContingenciaDataSelecionadaJLabel.setText(dataImprimida);
                    marcoSelecionado = listaMarcosProjeto.get(i);
                    pontoControleSelecionado = null;
                    System.out.println("Item: " + marcoSelecionado.getNomeMarcoDoProjeto());
                }
            }

            if (contingenciaEhMarco == false) {
                for (int i = 0; i < listaPontosControle.size(); i++) {
                    if (listaPontosControle.get(i).getNomePontoDeControle().equals(nomeItem)) {
                        Calendar dataPontoSelecionado = Calendar.getInstance();

                        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                        dataPontoSelecionado.setTime(listaPontosControle.get(i).getDataPontoControle());
                        String dataImprimida = df.format(dataPontoSelecionado.getTime());
                        planoContingenciaDataSelecionadaJLabel.setText(dataImprimida);
                        pontoControleSelecionado = listaPontosControle.get(i);
                        marcoSelecionado = null;
                        System.out.println("Item: " + pontoControleSelecionado.getNomePontoDeControle());
                    }
                }
            }
        }
    }//GEN-LAST:event_planoContingenciaDataLimiteJComboBoxActionPerformed

    private void informacoesGeraisProbabilidadeJSpinnerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_informacoesGeraisProbabilidadeJSpinnerFocusLost

    }//GEN-LAST:event_informacoesGeraisProbabilidadeJSpinnerFocusLost

    private void informacoesGeraisImpactoJComboBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_informacoesGeraisImpactoJComboBoxFocusLost

    }//GEN-LAST:event_informacoesGeraisImpactoJComboBoxFocusLost

    private void informacoesGeraisImpactoJComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_informacoesGeraisImpactoJComboBoxItemStateChanged
        double impactoDouble;
        int probabilidade = Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString());
        String impactoTemp = informacoesGeraisImpactoJComboBox.getSelectedItem().toString();

        if (impactoTemp == "Alto") {
            impactoDouble = 0.75;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Médio") {
            impactoDouble = 0.5;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Baixo") {
            impactoDouble = 0.25;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Crítico") {
            impactoDouble = 1;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        }
    }//GEN-LAST:event_informacoesGeraisImpactoJComboBoxItemStateChanged

    private void informacoesGeraisProbabilidadeJSpinnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informacoesGeraisProbabilidadeJSpinnerMouseClicked
        double impactoDouble;
        int probabilidade = Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString());
        String impactoTemp = informacoesGeraisImpactoJComboBox.getSelectedItem().toString();

        if (impactoTemp == "Alto") {
            impactoDouble = 0.75;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Médio") {
            impactoDouble = 0.5;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Baixo") {
            impactoDouble = 0.25;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Crítico") {
            impactoDouble = 1;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        }
    }//GEN-LAST:event_informacoesGeraisProbabilidadeJSpinnerMouseClicked

    private void informacoesGeraisProbabilidadeJSpinnerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_informacoesGeraisProbabilidadeJSpinnerKeyTyped
        double impactoDouble;
        int probabilidade = Integer.parseInt(informacoesGeraisProbabilidadeJSpinner.getValue().toString());
        String impactoTemp = informacoesGeraisImpactoJComboBox.getSelectedItem().toString();

        if (impactoTemp == "Alto") {
            impactoDouble = 0.75;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Médio") {
            impactoDouble = 0.5;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Baixo") {
            impactoDouble = 0.25;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        } else if (impactoTemp == "Crítico") {
            impactoDouble = 1;
            String grauDeSeveridade = String.valueOf(impactoDouble * probabilidade);
            informacoesGeraisGrauDeSeveridadeJTextField.setText((grauDeSeveridade));
        }
    }//GEN-LAST:event_informacoesGeraisProbabilidadeJSpinnerKeyTyped

    private void informacoesGeraisImpactoJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisImpactoJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_informacoesGeraisImpactoJComboBoxActionPerformed

    private void subcondicoesCriarCondicaoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subcondicoesCriarCondicaoJButtonActionPerformed

        if (riscoSelecionado.getIdRisco() != null) {

            boolean existeCondicaoComEsteNome = false;
            for (int k = 0; k < listaSubcondicoes.size(); k++) {
                if (listaSubcondicoes.get(k).getIdentificacaoSubcondicao().equals(subcondicoesIdentificacaoDaCondicaoJTextField.getText())) {
                    existeCondicaoComEsteNome = true;
                }
            }

            if (existeCondicaoComEsteNome) {
                JOptionPane.showMessageDialog(this, "Ja existe uma condição com este nome.");
            } else {

                Subcondicao novaCondicao = new Subcondicao();

                String identificacao = subcondicoesIdentificacaoDaCondicaoJTextField.getText();

                novaCondicao.setIdentificacaoSubcondicao(identificacao);

                novaCondicao.setDescricaoSubcondicao(descricaoSubcondicaoJTextArea.getText());
                novaCondicao.setStatusSubcondicao("Não Ocorrido");
                novaCondicao.setIdRisco(riscoSelecionado);

                riscosGerenciarRiscosFacade.criaSubcondicao(novaCondicao);

                subcondicaoSelecionada = null;
                subcondicoesIdentificacaoDaCondicaoJTextField.setText("");
                descricaoSubcondicaoJTextArea.setText("");

                criarTabelaSubcondicoes();
                reiniciarTabelaSubcondicoes();
                JOptionPane.showMessageDialog(this, "Condição criada com sucesso.");

                getListaSubcondicoes(riscoSelecionado);
                reiniciarTabelaSubcondicoes();

            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um risco para criar uma condição.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_subcondicoesCriarCondicaoJButtonActionPerformed

    private void subcondicoesSalvarAlteracoesDaCondicaoJButtobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subcondicoesSalvarAlteracoesDaCondicaoJButtobActionPerformed

        if (subcondicaoSelecionada != null) {

            subcondicaoSelecionada.setIdentificacaoSubcondicao(subcondicoesIdentificacaoDaCondicaoJTextField.getText());
            subcondicaoSelecionada.setDescricaoSubcondicao(descricaoSubcondicaoJTextArea.getText());

            riscosGerenciarRiscosFacade.alterarSubCondicao(subcondicaoSelecionada);

            tabelaSubcondicoesJTable.clearSelection();
            subcondicaoSelecionada = null;
            subcondicoesIdentificacaoDaCondicaoJTextField.setText("");
            descricaoSubcondicaoJTextArea.setText("");
            getListaSubcondicoes(riscoSelecionado);
            reiniciarTabelaSubcondicoes();

            JOptionPane.showMessageDialog(this, "Condição alterada com sucesso.");

        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecione uma subcondição.");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_subcondicoesSalvarAlteracoesDaCondicaoJButtobActionPerformed

    private void limparCamposJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparCamposJButtonActionPerformed

        tabelaSubcondicoesJTable.clearSelection();
        subcondicaoSelecionada = null;

        subcondicoesIdentificacaoDaCondicaoJTextField.setText("");
        descricaoSubcondicaoJTextArea.setText("");

        // TODO add your handling code here:
    }//GEN-LAST:event_limparCamposJButtonActionPerformed

    private void subcondicoesIdentificacaoDaCondicaoJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subcondicoesIdentificacaoDaCondicaoJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subcondicoesIdentificacaoDaCondicaoJTextFieldActionPerformed

    private void inserirCondicaoCampo1JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirCondicaoCampo1JButtonActionPerformed
        if (subcondicaoSelecionada != null) {
            campo1JTextField.setText(subcondicaoSelecionada.getIdentificacaoSubcondicao());
            campo1Condicao = subcondicaoSelecionada;
            campo1Relacao = new Gruporelacao();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirCondicaoCampo1JButtonActionPerformed

    private void inserirCondicaoCampo2JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirCondicaoCampo2JButtonActionPerformed
        if (subcondicaoSelecionada != null) {
            campo2JTextField.setText(subcondicaoSelecionada.getIdentificacaoSubcondicao());
            campo2Condicao = subcondicaoSelecionada;
            campo2Relacao = new Gruporelacao();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_inserirCondicaoCampo2JButtonActionPerformed

    private void inserirRelacaoCampo1JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirRelacaoCampo1JButtonActionPerformed

        if (relacaoSelecionada != null) {
            campo1JTextField.setText(relacaoSelecionada.getIdGrupo().toString());
            campo1Condicao = new Subcondicao();
            campo1Relacao = relacaoSelecionada;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirRelacaoCampo1JButtonActionPerformed

    private void inserirRelacaoCampo2JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirRelacaoCampo2JButtonActionPerformed

        if (relacaoSelecionada != null) {
            campo2JTextField.setText(relacaoSelecionada.getIdGrupo().toString());
            campo2Condicao = new Subcondicao();
            campo2Relacao = relacaoSelecionada;
        }
// TODO add your handling code here:
    }//GEN-LAST:event_inserirRelacaoCampo2JButtonActionPerformed

    private void criarRelacaoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarRelacaoJButtonActionPerformed

        if (campo1JTextField.getText().equals(null) || campo2JTextField.getText().equals(null)) {
            JOptionPane.showMessageDialog(this, "Finalize os campos para criação da relação.");
        } else {

            Gruporelacao novaRelacao = new Gruporelacao();
            if (campo1Condicao.getIdSubcondicao() != null) {
                novaRelacao.setIdSubcondicao1(campo1Condicao.getIdSubcondicao());
            }
            if (campo2Condicao.getIdSubcondicao() != null) {
                novaRelacao.setIdSubcondicao2(campo2Condicao.getIdSubcondicao());
            }
            if (campo1Relacao.getIdGrupo() != null) {
                novaRelacao.setIdRelacao1(campo1Relacao.getIdGrupo());
            }
            if (campo2Relacao.getIdGrupo() != null) {
                novaRelacao.setIdRelacao2(campo2Relacao.getIdGrupo());
            }

            novaRelacao.setRelacao(subcondicoesTipoDeRelacaoJComboBox.getSelectedItem().toString());

            boolean existeRelacaoIgual = false;
            for (int i = 0; i < listaRelacoes.size(); i++) {
                if ((listaRelacoes.get(i).getIdRelacao1() == novaRelacao.getIdRelacao1())
                        && (listaRelacoes.get(i).getIdRelacao2() == novaRelacao.getIdRelacao2())
                        && (listaRelacoes.get(i).getIdSubcondicao1() == novaRelacao.getIdSubcondicao1())
                        && (listaRelacoes.get(i).getIdSubcondicao2() == novaRelacao.getIdSubcondicao2())
                        && (listaRelacoes.get(i).getRelacao().equals(novaRelacao.getRelacao()))) {
                    existeRelacaoIgual = true;
                }
            }

            if (existeRelacaoIgual) {
                JOptionPane.showMessageDialog(this, "Ja existe uma relação igual a esta.");
            } else {

                riscosGerenciarRiscosFacade.criarNovaRelacao(novaRelacao);

                reiniciarTabelaSubcondicoes();

                campo1Condicao = null;
                campo1Relacao = null;
                campo1JTextField.setText("");
                campo2Condicao = null;
                campo2Relacao = null;
                campo2JTextField.setText("");

                JOptionPane.showMessageDialog(this, "Nova relação criada com sucesso.");

            }

        }


    }//GEN-LAST:event_criarRelacaoJButtonActionPerformed

    private void deletarCondicaoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarCondicaoJButtonActionPerformed

        if (subcondicaoSelecionada != null) {

            boolean existeRelacaoComASubcondicaoSelecionada = false;

            for (int i = 0; i < listaRelacoes.size(); i++) {

                if (subcondicaoSelecionada.getIdSubcondicao() == listaRelacoes.get(i).getIdSubcondicao1() || subcondicaoSelecionada.getIdSubcondicao() == listaRelacoes.get(i).getIdSubcondicao2()) {
                    existeRelacaoComASubcondicaoSelecionada = true;
                }

            }

            if (existeRelacaoComASubcondicaoSelecionada) {
                JOptionPane.showMessageDialog(this, "Existem relações com esta condição.");
            } else {

                riscosGerenciarRiscosFacade.deletarSubCondicao(subcondicaoSelecionada);

                getListaSubcondicoes(riscoSelecionado);
                reiniciarTabelaSubcondicoes();

                tabelaSubcondicoesJTable.clearSelection();
                subcondicaoSelecionada = null;

                subcondicoesIdentificacaoDaCondicaoJTextField.setText("");
                descricaoSubcondicaoJTextArea.setText("");

                JOptionPane.showMessageDialog(this, "Condição deletada com sucesso.");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecione uma condição.");
        }


    }//GEN-LAST:event_deletarCondicaoJButtonActionPerformed

    private void deletarRelacaoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarRelacaoJButtonActionPerformed

        if (relacaoSelecionada != null) {

            boolean existeRelacaoComARelacaoSelecionada = false;
            for (int i = 0; i < listaRelacoes.size(); i++) {
                if (listaRelacoes.get(i).getIdRelacao1() == relacaoSelecionada.getIdGrupo() || listaRelacoes.get(i).getIdRelacao2() == relacaoSelecionada.getIdGrupo()) {
                    existeRelacaoComARelacaoSelecionada = true;
                }
            }

            if (existeRelacaoComARelacaoSelecionada) {
                JOptionPane.showMessageDialog(this, "Existem relações envolvendo esta relação");
            } else {

                riscosGerenciarRiscosFacade.deletarRelacao(relacaoSelecionada);

                reiniciarTabelaRelacoes();
                relacaoSelecionada = null;

                campo1Condicao = null;
                campo1Relacao = null;
                campo1JTextField.setText("");
                campo2Condicao = null;
                campo2Relacao = null;
                campo2JTextField.setText("");

                JOptionPane.showMessageDialog(this, "Relação deletada com sucesso.");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecione uma relação.");
        }

    }//GEN-LAST:event_deletarRelacaoJButtonActionPerformed

    private void informacoesGeraisGrauDeSeveridadeJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesGeraisGrauDeSeveridadeJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_informacoesGeraisGrauDeSeveridadeJTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HistoricoDeAlteracoesJPanel;
    private javax.swing.JTextArea InformacoesGeraisDescricaoDeRiscoJTextArea;
    private javax.swing.JPanel InformacoesGeraisJPanel;
    private javax.swing.JPanel PlanoContigenciaJPanel;
    private javax.swing.JPanel PlanoDeMitigacaoJPanel;
    private javax.swing.JPanel RelacoesJPanel;
    private javax.swing.JPanel SubcondicoesJPanel;
    private javax.swing.JTextField campo1JTextField;
    private javax.swing.JTextField campo2JTextField;
    private javax.swing.JButton criarRelacaoJButton;
    private net.sf.nachocalendar.components.DateField dataIdentificacaoDoRisco;
    private javax.swing.JButton deletarCondicaoJButton;
    private javax.swing.JButton deletarRelacaoJButton;
    private javax.swing.JTextPane descricaoSubcondicaoJTextArea;
    private javax.swing.JLabel estadoAtualRiscoJLabel;
    private javax.swing.JPanel gerenciarRiscosJPanel;
    private javax.swing.JTabbedPane gerenciarRiscosJTabbedPane;
    private javax.swing.JButton informacoesGeraisAdicionarNovoRiscoJButton;
    private javax.swing.JComboBox informacoesGeraisCategoriaDeRiscoJComboBox;
    private javax.swing.JLabel informacoesGeraisCategoriaDeRiscoJLabel;
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
    private javax.swing.JButton inserirCondicaoCampo1JButton;
    private javax.swing.JButton inserirCondicaoCampo2JButton;
    private javax.swing.JButton inserirRelacaoCampo1JButton;
    private javax.swing.JButton inserirRelacaoCampo2JButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton limparCamposJButton;
    private javax.swing.JLabel planoContigenciaPlanoDeContigenciaJLabel;
    private javax.swing.JComboBox planoContingenciaDataLimiteJComboBox;
    private javax.swing.JLabel planoContingenciaDataSelecionadaJLabel;
    private javax.swing.JButton planoDeContingenciaAdicionarPlanoJButton;
    private javax.swing.JTextArea planoDeContingenciaComoSeraFeitoJTextArea;
    private javax.swing.JTextArea planoDeContingenciaDescricaoJTextArea;
    private javax.swing.JTextField planoDeContingenciaIdentificacaoJTextField;
    private javax.swing.JTextArea planoDeContingenciaInfAdicionaisJTextArea;
    private javax.swing.JList planoDeContingenciaListaPlanosJList;
    private javax.swing.JButton planoDeContingenciaRemoverPlanoJButton;
    private javax.swing.JTextField planoDeContingenciaResponsavelJTextField;
    private javax.swing.JButton planoDeContingenciaSalvarAlteracoesJButton;
    private javax.swing.JButton planoDeMitigacaoAdicionarPlanoJButton;
    private javax.swing.JTextArea planoDeMitigacaoComoSeraFeitoJTextArea;
    private javax.swing.JTextArea planoDeMitigacaoDescricaoJTextArea;
    private javax.swing.JTextField planoDeMitigacaoIdentificacaoJTextField;
    private javax.swing.JTextArea planoDeMitigacaoInfAdicionaisJTextArea;
    private javax.swing.JList planoDeMitigacaoListaPlanosJList;
    private javax.swing.JLabel planoDeMitigacaoPlanoDeMitigacaoJLabel;
    private javax.swing.JButton planoDeMitigacaoRemoverPlanoJButton;
    private javax.swing.JTextField planoDeMitigacaoResponsavelJTextField;
    private javax.swing.JButton planoDeMitigacaoSalvarAlteracoesJButton;
    private javax.swing.JComboBox planoMitigacaoDataLimiteJComboBox;
    private javax.swing.JLabel planoMitigacaoDataSelecionadaJLabel;
    private javax.swing.JList relacoesInfluenciadoJList;
    private javax.swing.JButton relacoesInfluenciarRiscoJButton;
    private javax.swing.JList relacoesListaInfluenciaJList;
    private javax.swing.JList relacoesListaRiscosJList;
    private javax.swing.JButton relacoesRemoverInfluenciaJButton;
    private javax.swing.JButton subcondicoesCriarCondicaoJButton;
    private javax.swing.JTextField subcondicoesIdentificacaoDaCondicaoJTextField;
    private javax.swing.JButton subcondicoesSalvarAlteracoesDaCondicaoJButtob;
    private javax.swing.JComboBox subcondicoesTipoDeRelacaoJComboBox;
    private javax.swing.JScrollPane tabelaHistoricoAlteracoesJScrollPane;
    private javax.swing.JScrollPane tabelaRelacoesJScrollPane;
    private javax.swing.JScrollPane tabelaRiscosJScrollPane;
    private javax.swing.JScrollPane tabelaSubcondicoesJScrollPane;
    // End of variables declaration//GEN-END:variables
}
