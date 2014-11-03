package configuracao;

import facade.PrincipalFacade;
import java.io.IOException;
import view.PrincipalJFrame;

/**
 *
 * @author Spider
 */
public class TestaConexaoComOBancoDeDados {

    public static String user;
    public static String senha;
    public static String porta;

    public static void main(String[] args) {
        TestaConexaoComOBancoDeDados testaConexaoComOBancoDeDados = new TestaConexaoComOBancoDeDados();

        if (testaConexaoComOBancoDeDados.existeConexaoComBanco("spider_rm", "spider_rm", "3306")) {
            new PrincipalJFrame().main(args);
        } else {
            ConfiguracaoBancoDeDados configuracaoBancoDeDados = new ConfiguracaoBancoDeDados(null, true);
            configuracaoBancoDeDados.setVisible(true);

            user = configuracaoBancoDeDados.getjTextFieldUsuario().getText();
            senha = new String(configuracaoBancoDeDados.getjPasswordFieldSenha().getPassword());
            porta = configuracaoBancoDeDados.getjTextFieldPorta().getText();

            if (testaConexaoComOBancoDeDados.existeConexaoComBanco(user, senha, porta)) {
                new PrincipalJFrame().main(args);
            } else {
                new ConfiguracaoBancoDeDados(null, true).executaPontoBat();
                boolean existeExcecao = true;
                while (existeExcecao) {
                    try {
                        new PrincipalJFrame().main(args);
                        existeExcecao = false;
                    } catch (Exception error) {
                        System.err.println("--Erro:" + error.getMessage());
                        existeExcecao = true;
                    }
                };
            }
        }

    }

    public boolean existeConexaoComBanco(String user, String senha, String porta) {
        return new ConexaoTeste().conecta(user, senha, porta);
    }
}
