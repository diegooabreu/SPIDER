/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testetabela;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Diego
 */
public class SocioTableModelTest extends JFrame {
    
    private JTable tblSocios;
    private SocioTableModel tableModel;
     
    public SocioTableModelTest() {
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
 
    private SocioTableModel getTableModel() {
        if (tableModel == null) {
            tableModel = new SocioTableModel(criaSocios());
        }
        return tableModel;
    }
 
    // cria uma lista com 5 sócios meramente ilustrativos
    private List<Socio> criaSocios() {
        List<Socio> socios = new ArrayList<Socio>();
        for (int i = 1; i <= 5; i++) {
            Socio socio = new Socio();
            socio.setNome("Nome" + i);
            socio.setEndereco("Endereço" + i);
            socios.add(socio);
        }
        return socios;
    }
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SocioTableModelTest().setVisible(true);
            }
        });
    }
 

}
