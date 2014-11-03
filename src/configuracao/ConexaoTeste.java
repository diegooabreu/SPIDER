package configuracao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bleno Vale
 */
public class ConexaoTeste {

    final private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/spider_rm";
//    final private String usuario = "root";
//    final private String senha = "spider";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;

    public boolean conecta(String usuario, String senha, String porta) {
        url = "jdbc:mysql://localhost:"+porta+"/spider_rm";
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException Driver) {
            result = false;
        } catch (SQLException Fonte) {
            result = false;
        }
        return result;

    }
}
