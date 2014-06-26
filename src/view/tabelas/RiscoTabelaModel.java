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
public class RiscoTabelaModel extends AbstractTableModel{

private static final int IDRISCO = 0;
private static final int DATAIDENTIFICACAO = 1;
private static final int EMISSOR = 2;
private static final int PROBABILIDADE = 3;
private static final int IMPACTO = 4;
private static final int STATUSRISCO = 5;
private static final int DESCRICAO = 6;
private static final int PRIORIDADE = 7;
private static final int GRAUSEVERIDADE = 8;
private static final int IDENTIFICACAO = 9;

// Array com os nomes das colunas.
    private String[] colunas = new String[] { "Id", "Data de Identificação", "Emissor", "Probabilidade", "Impacto", 
    "Status", "Descrição", "Prioridade", "Grau Severidade", "Identificação"};
    
    // Lista de Sócios a serem exibidos na tabela
    private List<RiscoTabela> linhas;
    
    // Cria um SocioTableModel sem nenhuma linha
    public RiscoTabelaModel() {
        linhas = new ArrayList<RiscoTabela>();
    }
    
    // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public RiscoTabelaModel(List<RiscoTabela> listaDeRiscoTabela) {
        linhas = new ArrayList<RiscoTabela>(listaDeRiscoTabela);
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
    case IDRISCO:
        return Integer.class;
    case DATAIDENTIFICACAO:
        return Date.class;
    case EMISSOR:
        return String.class;
    case PROBABILIDADE:
        return Integer.class;
    case IMPACTO:
        return String.class;
    case STATUSRISCO:
        return String.class;
    case DESCRICAO:
        return String.class;
    case PRIORIDADE:
        return Integer.class;
    case GRAUSEVERIDADE:
        return Integer.class;
    case IDENTIFICACAO:
        return String.class;
    
    //case ATIVO:
    //    return Boolean.class;
    
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    /*
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        // apenas o campo "ATIVO" será editável
        //return columnIndex == ATIVO;
    
}
    */
    
    
    public Object getValueAt(int rowIndex, int columnIndex) {
    // Pega o sócio referente a linha especificada.
        RiscoTabela riscoTabela = linhas.get(rowIndex);
        
       // Socio socio = linhas.get(rowIndex);
 
    switch (columnIndex) {
        case IDRISCO:
            return riscoTabela.getIdRisco();
        case DATAIDENTIFICACAO:
            return riscoTabela.getDataIdentificacao();
        case EMISSOR:
            return riscoTabela.getEmissor();
        case PROBABILIDADE:
            return riscoTabela.getProbabilidade();
        case IMPACTO:
            return riscoTabela.getImpacto();
        case STATUSRISCO:
            return riscoTabela.getStatusRisco();
        case DESCRICAO:
            return riscoTabela.getDescricao();
        case PRIORIDADE:
            return riscoTabela.getPrioridade();
        case GRAUSEVERIDADE:
            return riscoTabela.getGrauSeveridade();
        case IDENTIFICACAO:
            return riscoTabela.getIdentificacao();
        
    //case ATIVO:
     //       return socio.isAtivo();
            
            
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
    }
}
    
    
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    // Pega o sócio referente a linha especificada.
        RiscoTabela riscoTabela = linhas.get(rowIndex);
        //Socio socio = linhas.get(rowIndex);
 
    switch (columnIndex) {
    case IDRISCO:
        riscoTabela.setIdRisco((Integer) aValue);
        break;
    case DATAIDENTIFICACAO:
        riscoTabela.setDataIdentificacao((Date) aValue);
        break;
    case PROBABILIDADE:
        riscoTabela.setProbabilidade((Integer) aValue);
        break;
    case IMPACTO:
        riscoTabela.setImpacto((String) aValue);
        break;
    case STATUSRISCO:
        riscoTabela.setStatusRisco((String) aValue);
        break;
    case DESCRICAO:
        riscoTabela.setDescricao((String) aValue);
        break;
    case PRIORIDADE:
        riscoTabela.setPrioridade((Integer) aValue);
        break;
    case GRAUSEVERIDADE:
        riscoTabela.setGrauSeveridade((Integer) aValue);
        break;
    case IDENTIFICACAO:
        riscoTabela.setIdentificacao((String) aValue);
        break;
   // case ATIVO:
      //      socio.setAtivo((Boolean) aValue);
        //    break;
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
    }
     
    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }
    
     // Retorna o sócio referente a linha especificada
public RiscoTabela getRiscoTabela(int indiceLinha) {
    return linhas.get(indiceLinha);
}


// Adiciona o sócio especificado ao modelo
public void addRiscoTabela(RiscoTabela riscoTabela) {
    // Adiciona o registro.
    linhas.add(riscoTabela);
 
    // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() - 1;
 
    // Notifica a mudança.
    fireTableRowsInserted(ultimoIndice, ultimoIndice);
}
    
// Remove o sócio da linha especificada.
public void removeRiscoTabela(int indiceLinha) {
    // Remove o registro.
    linhas.remove(indiceLinha);
 
    // Notifica a mudança.
    fireTableRowsDeleted(indiceLinha, indiceLinha);
}

// Adiciona uma lista de sócios no final da lista.
public void addListaDeRiscos(List<RiscoTabela> riscos) {
    // Pega o tamanho antigo da tabela, que servirá
    // como índice para o primeiro dos novos registros
    int indice = getRowCount();
 
    // Adiciona os registros.
    linhas.addAll(riscos);
 
    // Notifica a mudança.
    fireTableRowsInserted(indice, indice + riscos.size());
}

// Remove todos os registros.
public void limpar() {
    // Remove todos os elementos da lista de sócios.
    linhas.clear();
 
    // Notifica a mudança.
    fireTableDataChanged();
}


}
