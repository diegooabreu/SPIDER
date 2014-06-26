/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Risco;

/**
 *
 * @author Diego
 */
public class TabelaMonitorarRiscosTableModel extends AbstractTableModel{
    
    List<Risco> linhas;
    
    
    // Array com os nomes das colunas.
    private String[] colunas = new String[] { "Nome", "Endereço" };
    
    // Constantes representando o índice das colunas
    private static final int NOME = 0;
    private static final int ENDERECO = 1;
    
    
    
    public void TabelaMonitorarRiscosTableModel(){
        
        //linhas = new ArrayList<Risco>();
        String[] linhas2 = new String[]{"nome" , "end"};
    }

    public void TabelaMonitorarRiscosTableModel(List<Risco> listaDeRiscos){
        linhas = new ArrayList<Risco>(listaDeRiscos);
    }
    
    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
