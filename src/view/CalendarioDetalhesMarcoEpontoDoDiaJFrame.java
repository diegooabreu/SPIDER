/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import facade.CalendarioDetalhesDoDiaFacade;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Marcodoprojeto;
import model.Planocontingencia;
import model.Planomitigacao;
import model.Pontodecontrole;
import model.Projeto;

/**
 *
 * @author Diego
 */
public class CalendarioDetalhesMarcoEpontoDoDiaJFrame extends javax.swing.JFrame {

    CalendarioDetalhesDoDiaFacade calendarioDetalhesDoDiaFacade = new CalendarioDetalhesDoDiaFacade();
    
    
    private JTable listaTarefasPontoJTable;
    private JTable listaTarefasMarcoJTable;
    private JTable listaTarefasPontoSomentePontoJTable;
    private JTable listaTarefasMarcoSomenteMarcoJTable;
    private DefaultTableModel listaTarefasMarcoSomenteMarcoJTableModel;
    private DefaultTableModel listaTarefasPontoJTableModel;
    private DefaultTableModel listaTarefasMarcoJTableModel;
    private DefaultTableModel listaTarefasPontoSomentePontoJTableModel;
    
    /**
     * Creates new form CalendarioDetalhesDoDiaJFrame
     */
    public CalendarioDetalhesMarcoEpontoDoDiaJFrame() {
        initComponents();
        criarListaDeTarefas();
    }

    
    
    public void criarListaDeTarefas(){
        listaTarefasPontoJTable = new JTable();
        listaTarefasPontoJTableModel = new DefaultTableModel();
        listaTarefasPontoJTableModel.setColumnIdentifiers(new Object[]{"Risco" , "Status","Tarefa", "Responsavel" , "Data de Realização"});
        listaTarefasPontoJTable.setModel(listaTarefasPontoJTableModel);
        listaTarefasJScrollPane.setViewportView(listaTarefasPontoJTable);
        
        listaTarefasMarcoJTable = new JTable();
        listaTarefasMarcoJTableModel = new DefaultTableModel();
        listaTarefasMarcoJTableModel.setColumnIdentifiers(new Object[]{"Risco" , "Status","Tarefa", "Responsavel" , "Data de Realização"});
        listaTarefasMarcoJTable.setModel(listaTarefasMarcoJTableModel);
        listaTarefasMarcoJScrollPane.setViewportView(listaTarefasMarcoJTable);
        
        
    }
    
    public void criarListaDeTarefasMarcoSomenteMarco(){
        listaTarefasMarcoSomenteMarcoJTable = new JTable();
        listaTarefasMarcoSomenteMarcoJTableModel = new DefaultTableModel();
        listaTarefasMarcoSomenteMarcoJTableModel.setColumnIdentifiers(new Object[]{"Risco" ,"Status", "Tarefa", "Responsavel" , "Data de Realização"});
        listaTarefasMarcoSomenteMarcoJTable.setModel(listaTarefasMarcoSomenteMarcoJTableModel);
        listaTarefasMacroSomenteMarcoJScrollPane.setViewportView(listaTarefasMarcoSomenteMarcoJTable);
        
    }
    
    public void criarListaDeTarefasPontoSomentePonto(){
        listaTarefasPontoSomentePontoJTable = new JTable();
        listaTarefasPontoSomentePontoJTableModel = new DefaultTableModel();
        listaTarefasPontoSomentePontoJTableModel.setColumnIdentifiers(new Object[]{"Risco" ,"Status", "Tarefa" , "Responsável" , "Data de Realização"});
        listaTarefasPontoSomentePontoJTable.setModel(listaTarefasPontoSomentePontoJTableModel);
        listaTarefasPontoSomentePontoJScrollPane.setViewportView(listaTarefasPontoSomentePontoJTable);
    }
    
    public void limparTela(){
        //set visible false para os paineis
        marcoEpontoDetalhesJPanel.setVisible(false);
        somentePontoDeControleJPanel.setVisible(false);
        somenteMarcoJPanel.setVisible(false);
                
        //limpado painel marco e ponto
        nomeMarcoDoProjetoJLabel.setText(null);
        nomePontoDeControleJLabel.setText(null);
        dataPontoDeControleJLabel.setText(null);
        
        //limpando painel somente marco
        nomeMarcoSomenteMarcoJLabel.setText(null);
        dataMarcoSomenteMarcoJLabel.setText(null);
        descricaoMarcoSomenteMarcoJTextArea.setText(null);
        
        //limpando painel somente ponto
        
    }
    
    public void setAllVisibleFalse(){
        marcoEpontoDetalhesJPanel.setVisible(false);
    }
    public void setVisibleMarcoEpontoJPanel(boolean status){
        marcoEpontoDetalhesJPanel.setVisible(status);
    }
    public void setVisibleSomenteMarcoJPanel(boolean status){
        somenteMarcoJPanel.setVisible(status);
    }
    public void setVisibleSomentePonto(boolean status){
        somentePontoDeControleJPanel.setVisible(status);
        
    }
        
    public void mostraInformacoesPontoDeControle(Pontodecontrole ponto){
        
            nomePontoDeControleJLabel.setText(ponto.getNomePontoDeControle());
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            Date dataPontoDeControle = ponto.getDataPontoControle();
            dataPontoDeControleJLabel.setText(df.format(dataPontoDeControle));
            descricaoPontoDeCOntroleJTextArea.setText(ponto.getDescricaoPontoControle());
        
    }
    
    public void mostraInformacoesMarcoSomenteMarco(Marcodoprojeto marco){
        nomeMarcoSomenteMarcoJLabel.setText(marco.getNomeMarcoDoProjeto());
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date dataMarcoDoProjeto = marco.getDataMarcoProjeto();
        dataMarcoSomenteMarcoJLabel.setText(df.format(dataMarcoDoProjeto));
        descricaoMarcoSomenteMarcoJTextArea.setText(marco.getDescricaoMarcoProjeto());
    }
    
    public void mostraInformacoesPontoSomentePonto(Pontodecontrole ponto){
        nomePontoSomentePontoJLabel.setText(ponto.getNomePontoDeControle());
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date dataPonto = ponto.getDataPontoControle();
        dataPontoSomentePontoJLabel.setText(df.format(dataPonto));
        descricaoPontoSomentePontoJTextArea.setText(ponto.getDescricaoPontoControle());
        
    }
    
    public void mostraInformacoesMarcoDoProjeto(Marcodoprojeto marco){
        
        nomeMarcoDoProjetoJLabel.setText(marco.getNomeMarcoDoProjeto());
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date dataMarcoDoProjeto = marco.getDataMarcoProjeto();
        dataMarcoDoProjetoJLabel.setText(df.format(dataMarcoDoProjeto));
        descricaoMarcoDoProjetoJTextArea.setText(marco.getDescricaoMarcoProjeto());
        
    }
    
        
    public void popularListaDeTarefasPontoSomentePonto(Pontodecontrole pontoSelecionado){
        
        String dataRealizacaoPMString = "Não realizada";
        String dataRealizacaoPCString = "Não realizada";
        
        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacao = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByPontoDeControle(pontoSelecionado);
        
        for(int i=0; i < listaPlanosDeMitigacao.size(); i++){

            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               
            Date dataRealizacaoPM = listaPlanosDeMitigacao.get(i).getDataRealizacao();
            
            if(dataRealizacaoPM != null){
                dataRealizacaoPMString = df.format(dataRealizacaoPM);
            }
            
            Object[] linha = new Object[]{listaPlanosDeMitigacao.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco(),
                listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao() ,
                listaPlanosDeMitigacao.get(i).getResponsavel() ,
                dataRealizacaoPMString };
                
            listaTarefasPontoSomentePontoJTableModel.addRow(linha);
            dataRealizacaoPMString = "Não realizada";
  
        }
        
        
        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        
        List<Planocontingencia> listaPlanosContingencia = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByPontoDeControle(pontoSelecionado);
        
        for(int i=0; i < listaPlanosContingencia.size(); i++){
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            
            Date dataRealizacaoPC = listaPlanosContingencia.get(i).getDataRealizacao();
            
            if(dataRealizacaoPC != null){
                dataRealizacaoPCString = df.format(dataRealizacaoPCString);
            }
            
            Object[] linha = new Object[]{listaPlanosContingencia.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosContingencia.get(i).getIdRisco().getStatusRisco(),
                listaPlanosContingencia.get(i).getIdentificacaoPlanoContingencia() ,
                listaPlanosContingencia.get(i).getResponsavel() ,
                dataRealizacaoPCString };
            
           
            
            listaTarefasPontoSomentePontoJTableModel.addRow(linha);
            dataRealizacaoPCString = "Não realizada";
            
        }
        
        
    }
    
    public void popularListaDeTarefasMarcoSomenteMarco(Marcodoprojeto marcoSelecionado){
        
        String dataRealizacaoPMString = "Não realizada";
        String dataRealizacaoPCString = "Não realizada";
        
        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacao = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByMarcoDoProjeto(marcoSelecionado);
        
        for(int i=0; i < listaPlanosDeMitigacao.size(); i++){

            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               
            Date dataRealizacaoPM = listaPlanosDeMitigacao.get(i).getDataRealizacao();
            
            if(dataRealizacaoPM != null){
                dataRealizacaoPMString = df.format(dataRealizacaoPM);
            }
            
            Object[] linha = new Object[]{listaPlanosDeMitigacao.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco(),
                listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao() ,
                listaPlanosDeMitigacao.get(i).getResponsavel() ,
                dataRealizacaoPMString };
                
            listaTarefasMarcoSomenteMarcoJTableModel.addRow(linha);
            dataRealizacaoPMString = "Não realizada";
  
        }
        
        
        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        
        List<Planocontingencia> listaPlanosContingencia = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByMarcoDoProjeto(marcoSelecionado);
        
        for(int i=0; i < listaPlanosContingencia.size(); i++){
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            
            Date dataRealizacaoPC = listaPlanosContingencia.get(i).getDataRealizacao();
            
            if(dataRealizacaoPC != null){
                dataRealizacaoPCString = df.format(dataRealizacaoPCString);
            }
            
            Object[] linha = new Object[]{listaPlanosContingencia.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosContingencia.get(i).getIdRisco().getStatusRisco(),
                listaPlanosContingencia.get(i).getIdentificacaoPlanoContingencia() ,
                listaPlanosContingencia.get(i).getResponsavel() ,
                dataRealizacaoPCString };
            
           
            
            listaTarefasMarcoSomenteMarcoJTableModel.addRow(linha);
            dataRealizacaoPCString = "Não realizada";
            
        }
        
    }
    
    public void popularListaDeTarefas(Marcodoprojeto marco, Pontodecontrole ponto){
        
        String dataRealizacaoPMString = "Não realizada";
        String dataRealizacaoPCString = "Não realizada";
        
        // POPULANDO LISTA DE TAREFAS DO PONTO DE CONTROLE
        
        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacao = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByPontoDeControle(ponto);
        
        for(int i=0; i < listaPlanosDeMitigacao.size(); i++){

            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               
            Date dataRealizacaoPM = listaPlanosDeMitigacao.get(i).getDataRealizacao();
            
            if(dataRealizacaoPM != null){
                dataRealizacaoPMString = df.format(dataRealizacaoPM);
            }
            
            Object[] linha = new Object[]{listaPlanosDeMitigacao.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosDeMitigacao.get(i).getIdRisco().getStatusRisco(),
                listaPlanosDeMitigacao.get(i).getIdentificacaoPlanoMitigacao() ,
                listaPlanosDeMitigacao.get(i).getResponsavel() ,
                dataRealizacaoPMString };
                
            listaTarefasPontoJTableModel.addRow(linha);
            dataRealizacaoPMString = "Não realizada";
  
        }
        
        
        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        
        List<Planocontingencia> listaPlanosContingencia = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByPontoDeControle(ponto);
        
        for(int i=0; i < listaPlanosContingencia.size(); i++){
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            
            Date dataRealizacaoPC = listaPlanosContingencia.get(i).getDataRealizacao();
            
            if(dataRealizacaoPC != null){
                dataRealizacaoPCString = df.format(dataRealizacaoPCString);
            }
            
            Object[] linha = new Object[]{listaPlanosContingencia.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosContingencia.get(i).getIdRisco().getStatusRisco(),
                listaPlanosContingencia.get(i).getIdentificacaoPlanoContingencia() ,
                listaPlanosContingencia.get(i).getResponsavel() ,
                dataRealizacaoPCString };
            
           
            
            listaTarefasPontoJTableModel.addRow(linha);
            dataRealizacaoPCString = "Não realizada";
            
        }
        
        // POPULANDO LISTA DE TAREFAS DO MARCO DO PROJETO
        
        // inserindo os planos de mitigação dos riscos sendo monitorados(Em estado = Mitigando)
        List<Planomitigacao> listaPlanosDeMitigacaoMarco = calendarioDetalhesDoDiaFacade.getPlanosMitigacaoByMarcoDoProjeto(marco);
        
        for(int i=0; i < listaPlanosDeMitigacaoMarco.size(); i++){

            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
               
            Date dataRealizacaoPM = listaPlanosDeMitigacaoMarco.get(i).getDataRealizacao();
            
            if(dataRealizacaoPM != null){
                dataRealizacaoPMString = df.format(dataRealizacaoPM);
            }
            
            Object[] linha = new Object[]{listaPlanosDeMitigacaoMarco.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosDeMitigacaoMarco.get(i).getIdRisco().getStatusRisco(),
                listaPlanosDeMitigacaoMarco.get(i).getIdentificacaoPlanoMitigacao() ,
                listaPlanosDeMitigacaoMarco.get(i).getResponsavel() ,
                dataRealizacaoPMString };
                
            listaTarefasMarcoJTableModel.addRow(linha);
            dataRealizacaoPMString = "Não realizada";
  
        }
        
        
        //inserindo os planos de contingencia dos riscos sendo contingenciados (Estado = Contingenciando)
        
        List<Planocontingencia> listaPlanosContingenciaMarco = calendarioDetalhesDoDiaFacade.getPlanosContingenciaByMarcoDoProjeto(marco);
        
        for(int i=0; i < listaPlanosContingenciaMarco.size(); i++){
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            
            Date dataRealizacaoPC = listaPlanosContingenciaMarco.get(i).getDataRealizacao();
            
            if(dataRealizacaoPC != null){
                dataRealizacaoPCString = df.format(dataRealizacaoPCString);
            }
            
            Object[] linha = new Object[]{listaPlanosContingenciaMarco.get(i).getIdRisco().getIdentificacao() ,
                listaPlanosContingenciaMarco.get(i).getIdRisco().getStatusRisco(),
                listaPlanosContingenciaMarco.get(i).getIdentificacaoPlanoContingencia() ,
                listaPlanosContingenciaMarco.get(i).getResponsavel() ,
                dataRealizacaoPCString };
            
           
            
            listaTarefasMarcoJTableModel.addRow(linha);
            dataRealizacaoPCString = "Não realizada";
            
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        marcoEpontoDetalhesJPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pontoPainelJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nomePontoDeControleJLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dataPontoDeControleJLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        listaTarefasJScrollPane = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descricaoPontoDeCOntroleJTextArea = new javax.swing.JTextArea();
        marcoPainelJPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        nomeMarcoDoProjetoJLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dataMarcoDoProjetoJLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoMarcoDoProjetoJTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        listaTarefasMarcoJScrollPane = new javax.swing.JScrollPane();
        fecharJButton = new javax.swing.JButton();
        somenteMarcoJPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricaoMarcoSomenteMarcoJTextArea = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        listaTarefasMacroSomenteMarcoJScrollPane = new javax.swing.JScrollPane();
        fecharMarcoSomenteMarcoJButton = new javax.swing.JButton();
        nomeMarcoSomenteMarcoJLabel = new javax.swing.JLabel();
        dataMarcoSomenteMarcoJLabel = new javax.swing.JLabel();
        somentePontoDeControleJPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        descricaoPontoSomentePontoJTextArea = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        listaTarefasPontoSomentePontoJScrollPane = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        nomePontoSomentePontoJLabel = new javax.swing.JLabel();
        dataPontoSomentePontoJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Ponto de Controle:");

        jLabel4.setText("Data do Ponto de Controle:");

        jLabel7.setText("Lista de Tarefas para este ponto de controle:");

        jLabel2.setText("Descrição do Ponto de Controle:");

        descricaoPontoDeCOntroleJTextArea.setEditable(false);
        descricaoPontoDeCOntroleJTextArea.setColumns(20);
        descricaoPontoDeCOntroleJTextArea.setRows(5);
        jScrollPane3.setViewportView(descricaoPontoDeCOntroleJTextArea);

        javax.swing.GroupLayout pontoPainelJPanelLayout = new javax.swing.GroupLayout(pontoPainelJPanel);
        pontoPainelJPanel.setLayout(pontoPainelJPanelLayout);
        pontoPainelJPanelLayout.setHorizontalGroup(
            pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontoPainelJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pontoPainelJPanelLayout.createSequentialGroup()
                        .addGroup(pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataPontoDeControleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomePontoDeControleJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(listaTarefasJScrollPane)
                    .addGroup(pontoPainelJPanelLayout.createSequentialGroup()
                        .addGroup(pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(0, 336, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pontoPainelJPanelLayout.setVerticalGroup(
            pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pontoPainelJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomePontoDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pontoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dataPontoDeControleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaTarefasJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ponto de Controle", pontoPainelJPanel);

        marcoPainelJPanel.setForeground(new java.awt.Color(240, 240, 240));

        jLabel8.setText("Nome do Marco do Projeto:");

        jLabel3.setText("Data do Marco do Projeto:");

        jLabel5.setText("Descrição do Marco do Projeto:");

        descricaoMarcoDoProjetoJTextArea.setEditable(false);
        descricaoMarcoDoProjetoJTextArea.setColumns(20);
        descricaoMarcoDoProjetoJTextArea.setRows(5);
        jScrollPane1.setViewportView(descricaoMarcoDoProjetoJTextArea);

        jLabel6.setText("Lista de tarefas para este marco:");

        listaTarefasMarcoJScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout marcoPainelJPanelLayout = new javax.swing.GroupLayout(marcoPainelJPanel);
        marcoPainelJPanel.setLayout(marcoPainelJPanelLayout);
        marcoPainelJPanelLayout.setHorizontalGroup(
            marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addComponent(listaTarefasMarcoJScrollPane)
                    .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                        .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, marcoPainelJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(10, 10, 10)))
                                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dataMarcoDoProjetoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nomeMarcoDoProjetoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        marcoPainelJPanelLayout.setVerticalGroup(
            marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcoPainelJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nomeMarcoDoProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(marcoPainelJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dataMarcoDoProjetoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaTarefasMarcoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Marco", marcoPainelJPanel);

        fecharJButton.setText("Fechar");
        fecharJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout marcoEpontoDetalhesJPanelLayout = new javax.swing.GroupLayout(marcoEpontoDetalhesJPanel);
        marcoEpontoDetalhesJPanel.setLayout(marcoEpontoDetalhesJPanelLayout);
        marcoEpontoDetalhesJPanelLayout.setHorizontalGroup(
            marcoEpontoDetalhesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(marcoEpontoDetalhesJPanelLayout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(fecharJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        marcoEpontoDetalhesJPanelLayout.setVerticalGroup(
            marcoEpontoDetalhesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marcoEpontoDetalhesJPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecharJButton)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        somenteMarcoJPanel.setPreferredSize(new java.awt.Dimension(579, 440));
        somenteMarcoJPanel.setRequestFocusEnabled(false);

        jLabel9.setText("Nome do Marco do Projeto:");

        jLabel10.setText("Data do Marco do Projeto:");

        jLabel11.setText("Descrição do Marco do Projeto:");

        descricaoMarcoSomenteMarcoJTextArea.setColumns(20);
        descricaoMarcoSomenteMarcoJTextArea.setRows(5);
        jScrollPane2.setViewportView(descricaoMarcoSomenteMarcoJTextArea);

        jLabel12.setText("Lista de Tarefas para este Marco:");

        fecharMarcoSomenteMarcoJButton.setText("Fechar");
        fecharMarcoSomenteMarcoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharMarcoSomenteMarcoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout somenteMarcoJPanelLayout = new javax.swing.GroupLayout(somenteMarcoJPanel);
        somenteMarcoJPanel.setLayout(somenteMarcoJPanelLayout);
        somenteMarcoJPanelLayout.setHorizontalGroup(
            somenteMarcoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(somenteMarcoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(somenteMarcoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(listaTarefasMacroSomenteMarcoJScrollPane)
                    .addGroup(somenteMarcoJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeMarcoSomenteMarcoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(somenteMarcoJPanelLayout.createSequentialGroup()
                        .addGroup(somenteMarcoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(somenteMarcoJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dataMarcoSomenteMarcoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(somenteMarcoJPanelLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(fecharMarcoSomenteMarcoJButton)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        somenteMarcoJPanelLayout.setVerticalGroup(
            somenteMarcoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(somenteMarcoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(somenteMarcoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomeMarcoSomenteMarcoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(somenteMarcoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataMarcoSomenteMarcoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaTarefasMacroSomenteMarcoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(fecharMarcoSomenteMarcoJButton)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        somentePontoDeControleJPanel.setPreferredSize(new java.awt.Dimension(579, 440));

        jLabel13.setText("Nome do Ponto de Controle:");

        jLabel14.setText("Data do Ponto de Controle:");

        jLabel15.setText("Descrição do Ponto de Controle:");

        descricaoPontoSomentePontoJTextArea.setColumns(20);
        descricaoPontoSomentePontoJTextArea.setRows(5);
        jScrollPane4.setViewportView(descricaoPontoSomentePontoJTextArea);

        jLabel16.setText("Lista de Tarefas para este Ponto de Controle:");

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout somentePontoDeControleJPanelLayout = new javax.swing.GroupLayout(somentePontoDeControleJPanel);
        somentePontoDeControleJPanel.setLayout(somentePontoDeControleJPanelLayout);
        somentePontoDeControleJPanelLayout.setHorizontalGroup(
            somentePontoDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(somentePontoDeControleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(somentePontoDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(somentePontoDeControleJPanelLayout.createSequentialGroup()
                        .addGroup(somentePontoDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(listaTarefasPontoSomentePontoJScrollPane)
                    .addGroup(somentePontoDeControleJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomePontoSomentePontoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(somentePontoDeControleJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dataPontoSomentePontoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(somentePontoDeControleJPanelLayout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jButton1)
                .addContainerGap(263, Short.MAX_VALUE))
        );
        somentePontoDeControleJPanelLayout.setVerticalGroup(
            somentePontoDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(somentePontoDeControleJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(somentePontoDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomePontoSomentePontoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(somentePontoDeControleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataPontoSomentePontoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaTarefasPontoSomentePontoJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(marcoEpontoDetalhesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(somenteMarcoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(somentePontoDeControleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(marcoEpontoDetalhesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(somenteMarcoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(somentePontoDeControleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDesktopPane1.setLayer(marcoEpontoDetalhesJPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(somenteMarcoJPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(somentePontoDeControleJPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecharJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharJButtonActionPerformed

        
        nomePontoDeControleJLabel.setText(null);
        dataPontoDeControleJLabel.setText(null);
        descricaoPontoDeCOntroleJTextArea.setText(null);
        
        nomeMarcoDoProjetoJLabel.setText(null);
        dataMarcoDoProjetoJLabel.setText(null);
        descricaoMarcoDoProjetoJTextArea.setText(null);

        this.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_fecharJButtonActionPerformed

    private void fecharMarcoSomenteMarcoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharMarcoSomenteMarcoJButtonActionPerformed

        this.setVisible(false);      
        
// TODO add your handling code here:
    }//GEN-LAST:event_fecharMarcoSomenteMarcoJButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

            
        this.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(CalendarioDetalhesMarcoEpontoDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarioDetalhesMarcoEpontoDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarioDetalhesMarcoEpontoDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarioDetalhesMarcoEpontoDoDiaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalendarioDetalhesMarcoEpontoDoDiaJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dataMarcoDoProjetoJLabel;
    private javax.swing.JLabel dataMarcoSomenteMarcoJLabel;
    private javax.swing.JLabel dataPontoDeControleJLabel;
    private javax.swing.JLabel dataPontoSomentePontoJLabel;
    private javax.swing.JTextArea descricaoMarcoDoProjetoJTextArea;
    private javax.swing.JTextArea descricaoMarcoSomenteMarcoJTextArea;
    private javax.swing.JTextArea descricaoPontoDeCOntroleJTextArea;
    private javax.swing.JTextArea descricaoPontoSomentePontoJTextArea;
    private javax.swing.JButton fecharJButton;
    private javax.swing.JButton fecharMarcoSomenteMarcoJButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane listaTarefasJScrollPane;
    private javax.swing.JScrollPane listaTarefasMacroSomenteMarcoJScrollPane;
    private javax.swing.JScrollPane listaTarefasMarcoJScrollPane;
    private javax.swing.JScrollPane listaTarefasPontoSomentePontoJScrollPane;
    private javax.swing.JPanel marcoEpontoDetalhesJPanel;
    private javax.swing.JPanel marcoPainelJPanel;
    private javax.swing.JLabel nomeMarcoDoProjetoJLabel;
    private javax.swing.JLabel nomeMarcoSomenteMarcoJLabel;
    private javax.swing.JLabel nomePontoDeControleJLabel;
    private javax.swing.JLabel nomePontoSomentePontoJLabel;
    private javax.swing.JPanel pontoPainelJPanel;
    private javax.swing.JPanel somenteMarcoJPanel;
    private javax.swing.JPanel somentePontoDeControleJPanel;
    // End of variables declaration//GEN-END:variables
}
