/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testetabela;

import view.tabelas.RiscoTabela;
import view.tabelas.RiscoTabelaModel;
import facade.RiscosRiscosOcorridosFacade;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Risco;

/**
 *
 * @author Diego
 */
public class TesteTabelaRisco extends JFrame{
    
    RiscosRiscosOcorridosFacade riscoFacade = new RiscosRiscosOcorridosFacade();
            
    private JTable tblSocios;
    private RiscoTabelaModel tableModel;
     
    public TesteTabelaRisco() {
        super("SocioTableModelTest");
        initialize();
    }
 
    private void initialize() {
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new JScrollPane(getTblSocios()));
    }
 
    private JTable getTblSocios() {
        if (tblSocios == null) {
            tblSocios = new JTable();
            tblSocios.setModel(getTableModel());
        }
        return tblSocios;
    }
 
    private RiscoTabelaModel getTableModel() {
        if (tableModel == null) {
            tableModel = new RiscoTabelaModel(criaListaDeRiscoTabela(riscoFacade.getListaDeRiscos()));
        }
        return tableModel;
    }
 
    // cria uma lista de <RiscoTabela> para ser criada a tabela a partir de uma lista de <Risco>
    private List<RiscoTabela> criaListaDeRiscoTabela(List<Risco> listaRiscos) {
        List<RiscoTabela> listaRiscosTabela = new ArrayList<RiscoTabela>();
        
        for(int i=0; i < listaRiscos.size(); i++){
            RiscoTabela riscoTabela = new RiscoTabela();
            riscoTabela.setDataIdentificacao(listaRiscos.get(i).getDataIdentificacao());
            riscoTabela.setDescricao(listaRiscos.get(i).getDescricao());
            riscoTabela.setEmissor(listaRiscos.get(i).getEmissor());
            riscoTabela.setGrauSeveridade(listaRiscos.get(i).getGrauSeveridade());
            riscoTabela.setIdRisco(listaRiscos.get(i).getIdRisco());
            riscoTabela.setIdentificacao(listaRiscos.get(i).getIdentificacao());
            riscoTabela.setImpacto(listaRiscos.get(i).getImpacto());
            riscoTabela.setPrioridade(listaRiscos.get(i).getPrioridade());
            riscoTabela.setProbabilidade(listaRiscos.get(i).getProbabilidade());
            riscoTabela.setStatusRisco(listaRiscos.get(i).getStatusRisco());
            
            listaRiscosTabela.add(riscoTabela);
            
        }
        
        return listaRiscosTabela;
        
        
    }
    
    
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TesteTabelaRisco().setVisible(true);
            }
        });
    }
 
    
}
