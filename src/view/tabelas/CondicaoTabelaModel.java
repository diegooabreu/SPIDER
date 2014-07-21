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
public class CondicaoTabelaModel extends AbstractTableModel{
    
private static final int STATUSCONDICAO = 0;
private static final int IDCONDICAO = 1;
private static final int IDENTIFICACAOCONDICAO = 2;
private static final int DESCRICAOCONDICAO = 3;
//private static final int DATAOCORRENCIACONDICAO = 4;


// Array com os nomes das colunas.
    private String[] colunas = new String[] { "Status", "Id", "Identificação", "Descrição" /*, "Data de Ocorrência"*/};

    
    // Lista de Sócios a serem exibidos na tabela
    private List<CondicaoTabela> linhas;
    
    // Cria um SocioTableModel sem nenhuma linha
    public CondicaoTabelaModel() {
        linhas = new ArrayList<CondicaoTabela>();
    }
    
     // Cria um SocioTableModel contendo a lista recebida por parâmetro
    public CondicaoTabelaModel(List<CondicaoTabela> listaDeCondicaoTabela) {
        linhas = new ArrayList<CondicaoTabela>(listaDeCondicaoTabela);
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
    case STATUSCONDICAO:
        return Boolean.class;
    case IDCONDICAO:
        return Integer.class;
    case IDENTIFICACAOCONDICAO:
        return String.class;
    case DESCRICAOCONDICAO:
        return String.class;
    //case DATAOCORRENCIACONDICAO:
      //  return Date.class;
    
    
    //case ATIVO:
    //    return Boolean.class;
    
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    
    
     public boolean isCellEditable(int rowIndex, int columnIndex) {
        // apenas o campo "MONITORAR" será editável
        //return columnIndex == MONITORAR;
         
         
         return false;
    
}
     
     public Object getValueAt(int rowIndex, int columnIndex) {
    // Pega o sócio referente a linha especificada.
        CondicaoTabela condicaoTabela = linhas.get(rowIndex);
        
       // Socio socio = linhas.get(rowIndex);
 
    switch (columnIndex) {
        case STATUSCONDICAO:
            return condicaoTabela.isStatusCondicao();
        case IDCONDICAO:
            return condicaoTabela.getIdCondicao();
        case IDENTIFICACAOCONDICAO:
            return condicaoTabela.getIdentificacaoCondicao();
        case DESCRICAOCONDICAO:
            return condicaoTabela.getDescricaoCondicao();
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
    // Pega o sócio referente a linha especificada.
        CondicaoTabela condicaoTabela = linhas.get(rowIndex);
        //Socio socio = linhas.get(rowIndex);
 
    switch (columnIndex) {
    case STATUSCONDICAO:
        condicaoTabela.setStatusCondicao((Boolean) aValue);
        break;
    case IDCONDICAO:
        condicaoTabela.setIdCondicao((Integer) aValue);
        break;
    case IDENTIFICACAOCONDICAO:
        condicaoTabela.setIdentificacaoCondicao((String) aValue);
        break;
    case DESCRICAOCONDICAO:
        condicaoTabela.setDescricaoCondicao((String) aValue);
        break;
    //case DATAOCORRENCIACONDICAO:
      //  condicaoTabela.setDataOcorrenciaCondicao((Date) aValue);
        //break;
    
   // case ATIVO:
      //      socio.setAtivo((Boolean) aValue);
        //    break;
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
    }
     
    fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }
    
     // Retorna a condicaoTabela referente a linha especificada
public CondicaoTabela getCondicaoTabela(int indiceLinha) {
    return linhas.get(indiceLinha);
}


// Adiciona o sócio especificado ao modelo
public void addCondicaoTabela(CondicaoTabela condicaoTabela) {
    // Adiciona o registro.
    linhas.add(condicaoTabela);
 
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

// Adiciona uma lista de sócios no final da lista.
public void addListaDeCondicao(List<CondicaoTabela> listaCondicaoTabela) {
    // Pega o tamanho antigo da tabela, que servirá
    // como índice para o primeiro dos novos registros
    int indice = getRowCount();
 
    // Adiciona os registros.
    linhas.addAll(listaCondicaoTabela);
 
    // Notifica a mudança.
    fireTableRowsInserted(indice, indice + listaCondicaoTabela.size());
}

// Remove todos os registros.
public void limpar() {
    // Remove todos os elementos da lista de sócios.
    linhas.clear();
 
    // Notifica a mudança.
    fireTableDataChanged();
}

    
}
