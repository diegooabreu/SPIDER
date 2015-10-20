package instalador;

import view.PrincipalJFrame;

/**
 *
 * @author Bleno Vale
 */
public class TestaConexao {

    public static void main(String[] args) {
        jaExisteBanco(args);           
    }
    
    private static void jaExisteBanco(String[] args) {
        ExecutaBanco executaBanco = new ExecutaBanco("jdbc:mysql://localhost:3306/spider_rm", "spider_rm", "spider_rm");
        boolean existe = executaBanco.checaConexao();

        if (existe) {
            new PrincipalJFrame().main(args);
        } else {
            new Instalador().main(args);
        }
    }
   
}
