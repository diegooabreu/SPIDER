/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.tabelas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego
 */
public class PlanoTabelaModel extends AbstractTableModel{
    
    private static final int REALIZADO = 0;
    private static final int IDPLANO = 1;
    private static final int IDENTIFICACAOPLANO = 2;
    private static final int DESCRICAOPLANO = 3;
    private static final int RESPONSAVEL = 4;
    private static final int DATALIMITE = 5;
    private static final int IDENTIFICACAORISCO = 6;
    private static final int DATAREALIZACAO = 7;
    private static final int TIPO = 8;
    
    // Array com os nomes das colunas.
    private String[] colunas = new String[] {"Realizar", "Id", "Identificacao", "Descrição", "Responsável","Data Limite","Risco", "Data da Realização", "Tipo de Plano" };

    // Lista de Sócios a serem exibidos na tabela
    private List<PlanoTabela> linhas;
    
    
    // Cria um PlanoTabelaModel sem nenhuma linha
    public PlanoTabelaModel() {
        linhas = new ArrayList<PlanoTabela>();
    }
    
    // Cria um PlanoTableModel contendo a lista recebida por parâmetro
    public PlanoTabelaModel(List<PlanoTabela> listaDePlanoTabela) {
        linhas = new ArrayList<PlanoTabela>(listaDePlanoTabela);
    }
    
    public int getRowCount() {
    return linhas.size();
    }
    
    public int getColumnCount() {
    return colunas.length;
    }
    
    public String getColumnName(int columnIndex) {
    return colunas[columnIndex];
    };
    
    
    public Class<?> getColumnClass(int columnIndex) {
    switch (columnIndex) {
    case REALIZADO:
        return Boolean.class;
    case IDPLANO:
        return Integer.class;
    case IDENTIFICACAOPLANO:
        return String.class;
    case DESCRICAOPLANO:
        return String.class;
    case RESPONSAVEL:
        return String.class;
    case DATALIMITE:
        return Date.class;
    case IDENTIFICACAORISCO:
        return String.class;
    case DATAREALIZACAO:
        return Date.class;
    case TIPO:
        return String.class;
   
    
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
                
         return false;
    
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
    // Pega o plano referente a linha especificada.
        PlanoTabela planoTabela = linhas.get(rowIndex);
        
       
 
    switch (columnIndex) {
        case REALIZADO:
            return planoTabela.isRealizado();
        case IDPLANO:
            return planoTabela.getIdPlano();
        case IDENTIFICACAOPLANO:
            return planoTabela.getIdentificacaoPlano();
        case DESCRICAOPLANO:
            return planoTabela.getDescricaoPlano();
        case RESPONSAVEL:
            return planoTabela.getResponsavel();
        case DATALIMITE:
            return planoTabela.getDataLimite();
        case IDENTIFICACAORISCO:
            return planoTabela.getIdentificacaoRisco();
        case DATAREALIZACAO:
            return planoTabela.getDataRealizacao();
        case TIPO:
            return planoTabela.getTipo();
        //case DATAOCORRENCIACONDICAO:
          //  return condicaoTabela.getDataOcorrenciaCondicao();
        
                
    //case ATIVO:
     //       return socio.isAtivo();
            
            
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
    }
}
    
     public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    // Pega o plano referente a linha especificada.
        PlanoTabela planoTabela = linhas.get(rowIndex);
        
 
    switch (columnIndex) {
    case REALIZADO:
        planoTabela.setRealizado((Boolean) aValue);
        break;
    case IDPLANO:
        planoTabela.setIdPlano((Integer) aValue);
        break;
    case IDENTIFICACAOPLANO:
        planoTabela.setIdentificacaoPlano((String) aValue);
        break;
    case DESCRICAOPLANO:
        planoTabela.setDescricaoPlano((String) aValue);
        break;
    case RESPONSAVEL:
        planoTabela.setResponsavel((String) aValue);
        break;
    case DATALIMITE:
        planoTabela.setDataLimite((Date) aValue);
        break;
    case IDENTIFICACAORISCO:
        planoTabela.setIdentificacaoRisco((String) aValue);
        break;
    case DATAREALIZACAO:
        planoTabela.setDataRealizacao((Date) aValue);
        break;
    case TIPO:
        planoTabela.setTipo((String) aValue);
        break;
    
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
    }
     
    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }
    
    // Retorna a planoTabela referente a linha especificada
    public PlanoTabela getPlanoTabela(int indiceLinha) {
        return linhas.get(indiceLinha);
}
    
    // Adiciona o plano especificado ao modelo
    public void addPlanoTabela(PlanoTabela planoTabela) {
    // Adiciona o registro.
    linhas.add(planoTabela);
 
    // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() - 1;
 
    // Notifica a mudança.
    fireTableRowsInserted(ultimoIndice, ultimoIndice);
}
    
    // Remove o sócio da linha especificada.
    public void removeCondicaoTabela(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);
 
        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
    public void addListaDePlanos(List<PlanoTabela> listaPlanoTabela) {
       // Pega o tamanho antigo da tabela, que servirá
       // como índice para o primeiro dos novos registros
       int indice = getRowCount();

       // Adiciona os registros.
       linhas.addAll(listaPlanoTabela);

       // Notifica a mudança.
       fireTableRowsInserted(indice, indice + listaPlanoTabela.size());
   }
    
    // Adiciona uma lista de sócios no final da lista.
    public void addListaDeCondicao(List<PlanoTabela> listaPlanoTabela) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();
 
        // Adiciona os registros.
        linhas.addAll(listaPlanoTabela);
 
        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + listaPlanoTabela.size());
    }
    
    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
        linhas.clear();
 
        // Notifica a mudança.
        fireTableDataChanged();
}
    
    
    
}
