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
 * @author Diogo
 */
public class PlanosPendentesTabelaModel extends AbstractTableModel {

    public static final int identificacaoPlano = 0;
    public static final int idPlanoDeMitigacao = 1;
    public static final int datalimite = 2;
    public static final int realizado = 3;

    private String[] colunas = new String[]{"Identificação do Plano", "Risco", "Data Limite", "Realizado"};

    private List<PlanosPendentesTabela> linhas;

    public PlanosPendentesTabelaModel() {
        linhas = new ArrayList<PlanosPendentesTabela>();
    }

    public PlanosPendentesTabelaModel(List<PlanosPendentesTabela> planosPendentesTabela) {
        linhas = new ArrayList<PlanosPendentesTabela>(planosPendentesTabela);
    }

    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case identificacaoPlano:
                return Boolean.class;
            case idPlanoDeMitigacao:
                return Integer.class;
            case datalimite:
                return Date.class;
            case realizado:
                return String.class;
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
        PlanosPendentesTabela planosPendentes = linhas.get(rowIndex);

        // Socio socio = linhas.get(rowIndex);
        switch (columnIndex) {
            case identificacaoPlano:
                return planosPendentes.getIdentificacaoPlano();
            case idPlanoDeMitigacao:
                return planosPendentes.getIdPlanoDeMitigacao();
            case datalimite:
                return planosPendentes.getDataLimite();
            case realizado:
                return planosPendentes.isRealizado();
            
            //case ATIVO:
            //       return socio.isAtivo();
            default:
                // Não deve ocorrer, pois só existem 2 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // Pega o sócio referente a linha especificada.
        PlanosPendentesTabela planosPendentes = linhas.get(rowIndex);
        //Socio socio = linhas.get(rowIndex);

        switch (columnIndex) {
            case realizado:
                planosPendentes.setRealizado((Boolean) aValue);
                break;
            case idPlanoDeMitigacao:
                planosPendentes.setIdentificacaoPlano((String) aValue);
                break;
            case datalimite:
                planosPendentes.setDataLimite((Date) aValue);
                break;
            case identificacaoPlano:
                planosPendentes.setIdentificacaoPlano((String) aValue);
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
    public PlanosPendentesTabela getPlanosTabela(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

// Adiciona o sócio especificado ao modelo
    public void addPlanoTabela(PlanosPendentesTabela planosPendentes) {
        // Adiciona o registro.
        linhas.add(planosPendentes);

        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

// Remove o sócio da linha especificada.
    public void removePlanosPendentesTabela(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

// Adiciona uma lista de sócios no final da lista.
    public void addListaDePlanos(List<PlanosPendentesTabela> planos) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();

        // Adiciona os registros.
        linhas.addAll(planos);

        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + planos.size());
    }

// Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
        linhas.clear();

        // Notifica a mudança.
        fireTableDataChanged();
    }
}
